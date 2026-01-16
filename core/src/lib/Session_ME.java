package lib;

import com.badlogic.gdx.Gdx;
import com.mchien.ngulong.MyGdxGame;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import lib2.IMessageHandler;
import lib2.ISession;
import lib2.Message;

/**
 * Lớp Session_ME - Quản lý session kết nối giữa client và server
 * 
 * Chức năng chính:
 * 1. Quản lý kết nối socket đến game server
 * 2. Gửi/nhận message với cơ chế mã hóa XOR
 * 3. Xử lý đa luồng cho việc gửi/nhận message
 * 4. Quản lý encryption key từ server
 * 
 * Luồng hoạt động:
 * - NetworkInit: Thread khởi tạo kết nối
 * - Sender: Thread gửi message đến server
 * - MessageCollector: Thread nhận message từ server
 * 
 * @author NLTB8 Team
 */
public class Session_ME implements ISession {
    // ==================== SINGLETON INSTANCE ====================
    protected static Session_ME instance = new Session_ME();

    // ==================== NETWORK STREAMS ====================
    /**
     * Output stream để gửi dữ liệu đến server
     */
    private DataOutputStream dos;
    
    /**
     * Input stream để nhận dữ liệu từ server
     */
    public DataInputStream dis;
    
    /**
     * Socket kết nối đến server
     */
    private mSocket sc;

    // ==================== MESSAGE HANDLER ====================
    /**
     * Handler xử lý message nhận từ server
     */
    public static IMessageHandler messageHandler;
    
    /**
     * Queue chứa message nhận được từ thread khác (không phải main thread)
     * Các message này sẽ được xử lý ở main thread
     */
    public static mVector recieveMsg = new mVector();

    // ==================== CONNECTION STATUS ====================
    /**
     * Trạng thái đã kết nối thành công
     */
    public boolean connected;
    
    /**
     * Trạng thái đang trong quá trình kết nối
     */
    public boolean connecting;
    
    /**
     * Thời điểm kết nối thành công (milliseconds)
     */
    long timeConnected;
    
    /**
     * Địa chỉ host server
     */
    public static String host;
    
    /**
     * Cổng kết nối server
     */
    public static int port;
    
    /**
     * Flag đánh dấu việc kết nối bị hủy
     */
    public static boolean isCancel;

    // ==================== THREADS ====================
    /**
     * Thread khởi tạo kết nối
     */
    public Thread initThread;
    
    /**
     * Thread thu thập message từ server
     */
    public Thread collectorThread;
    
    /**
     * Thread gửi message đến server
     */
    public Thread sendThread;
    
    /**
     * Đối tượng Sender quản lý queue message cần gửi
     */
    private final Session_ME.Sender sender = new Session_ME.Sender();

    // ==================== ENCRYPTION ====================
    /**
     * Key mã hóa nhận từ server (XOR encryption)
     */
    public byte[] key = null;
    
    /**
     * Flag đánh dấu đã nhận key mã hóa từ server
     */
    boolean getKeyComplete;
    
    /**
     * Con trỏ hiện tại trong key khi đọc (decrypt)
     */
    private byte curR;
    
    /**
     * Con trỏ hiện tại trong key khi ghi (encrypt)
     */
    private byte curW;

    // ==================== STATISTICS ====================
    /**
     * Tổng số byte đã gửi
     */
    public int sendByteCount;
    
    /**
     * Tổng số byte đã nhận
     */
    public int recvByteCount;
    
    /**
     * String hiển thị số byte đã nhận
     */
    public String strRecvByteCount = "";
    
    /**
     * Đếm số message đã xử lý
     */
    int countMsg = 0;
    
    /**
     * Đếm số lần đọc message
     */
    public static int countRead = 0;

    // ==================== PUBLIC METHODS ====================
    
    /**
     * Lấy instance singleton của Session_ME
     * 
     * @return Instance duy nhất của Session_ME
     */
    public static Session_ME gI() {
        return instance;
    }

    /**
     * Kiểm tra trạng thái kết nối
     * 
     * @return true nếu đang kết nối, false nếu không
     */
    public boolean isConnected() {
        return this.connected;
    }

    /**
     * Đăng ký handler để xử lý message
     * 
     * @param messageHandler Handler xử lý message và các sự kiện kết nối
     */
    public void setHandler(IMessageHandler messageHandler) {
        Session_ME.messageHandler = messageHandler;
    }

    /**
     * Xử lý message nhận được
     * 
     * Nếu đang ở main thread: xử lý trực tiếp
     * Nếu đang ở thread khác: thêm vào queue để xử lý sau ở main thread
     * 
     * @param msg Message cần xử lý
     */
    public static void onRecieveMsg(Message msg) {
        if (Thread.currentThread().getName() == MyGdxGame.mainThreadName) {
            // Đang ở main thread - xử lý trực tiếp
            messageHandler.onMessage(msg);
        } else {
            // Đang ở thread khác - thêm vào queue
            System.out.println("RA NGOAI MAIN THREAD");
            recieveMsg.addElement(msg);
        }
    }

    /**
     * Gửi message đến server
     * Message sẽ được thêm vào queue và gửi bởi Sender thread
     * 
     * @param message Message cần gửi
     */
    public void sendMessage(Message message) {
        this.sender.AddMessage(message);
    }

    /**
     * Thực hiện gửi message đến server (synchronized)
     * 
     * Format message gửi đi:
     * 1. Command byte (1 byte) - có thể được mã hóa
     * 2. Data size (2 bytes hoặc 4 bytes nếu command = 127) - có thể được mã hóa
     * 3. Data content (n bytes) - có thể được mã hóa
     * 
     * Nếu đã nhận key: tất cả dữ liệu sẽ được mã hóa bằng XOR với key
     * 
     * @param m Message cần gửi
     * @throws IOException Lỗi khi gửi dữ liệu
     */
    private synchronized void doSendMessage(Message m) throws IOException {
        byte[] data = m.getData();

        try {
            // Bước 1: Ghi command byte
            if (this.getKeyComplete) {
                // Đã có key - mã hóa command
                byte encryptedCmd = this.writeKey(m.command);
                this.dos.writeByte(encryptedCmd);
            } else {
                // Chưa có key - ghi command gốc
                this.dos.writeByte(m.command);
            }

            // Bước 2: Ghi data size và data content
            if (data != null) {
                int size = data.length;
                
                // Ghi data size (2 bytes)
                if (this.getKeyComplete) {
                    // Đã có key - mã hóa size
                    int byte1 = this.writeKey((byte)(size >> 8));
                    this.dos.writeByte(byte1);
                    int byte2 = this.writeKey((byte)(size & 255));
                    this.dos.writeByte(byte2);
                } else {
                    // Chưa có key - ghi size gốc
                    this.dos.writeShort(size);
                }

                // Mã hóa data content nếu đã có key
                if (this.getKeyComplete) {
                    for(int i = 0; i < data.length; ++i) {
                        data[i] = this.writeKey(data[i]);
                    }
                }

                // Ghi data content
                this.dos.write(data);
                this.sendByteCount += 5 + data.length;
            } else {
                // Không có data - ghi size = 0
                this.dos.writeShort(0);
                this.sendByteCount += 5;
            }

            // Flush để đảm bảo dữ liệu được gửi ngay
            this.dos.flush();
        } catch (IOException var6) {
            var6.printStackTrace();
        }
    }

    /**
     * Cập nhật và xử lý message trong queue
     * 
     * Được gọi từ main thread để xử lý các message
     * nhận được từ các thread khác
     */
    public static void update() {
        while(recieveMsg.size() > 0) {
            Message msg = (Message)recieveMsg.elementAt(0);
            if (msg == null) {
                recieveMsg.removeElementAt(0);
                return;
            }

            messageHandler.onMessage(msg);
            recieveMsg.removeElementAt(0);
        }
    }

    // ==================== ENCRYPTION METHODS ====================
    
    /**
     * Giải mã một byte bằng key (XOR decryption)
     * 
     * Sử dụng con trỏ curR để lấy byte từ key
     * Con trỏ sẽ tăng lên và quay vòng khi hết key
     * 
     * @param b Byte cần giải mã
     * @return Byte đã giải mã
     */
    private byte readKey(byte b) {
        byte[] keyArray = this.key;
        byte currentReadIndex = this.curR;
        this.curR = (byte)(currentReadIndex + 1);
        byte result = (byte)(keyArray[currentReadIndex] & 255 ^ b & 255);
        
        // Quay vòng con trỏ khi hết key
        if (this.curR >= this.key.length) {
            this.curR = (byte)(this.curR % this.key.length);
        }

        return result;
    }

    /**
     * Mã hóa một byte bằng key (XOR encryption)
     * 
     * Sử dụng con trỏ curW để lấy byte từ key
     * Con trỏ sẽ tăng lên và quay vòng khi hết key
     * 
     * @param b Byte cần mã hóa
     * @return Byte đã mã hóa
     */
    private byte writeKey(byte b) {
        byte[] keyArray = this.key;
        byte currentWriteIndex = this.curW;
        this.curW = (byte)(currentWriteIndex + 1);
        byte result = (byte)(keyArray[currentWriteIndex] & 255 ^ b & 255);
        
        // Quay vòng con trỏ khi hết key
        if (this.curW >= this.key.length) {
            this.curW = (byte)(this.curW % this.key.length);
        }

        return result;
    }

    // ==================== CONNECTION MANAGEMENT ====================
    
    /**
     * Đóng kết nối
     */
    public void close() {
        this.cleanNetwork();
    }

    /**
     * Dọn dẹp và giải phóng tài nguyên network
     * 
     * Bao gồm:
     * - Reset encryption key
     * - Đóng socket và streams
     * - Dừng các threads
     * - Gọi garbage collector
     */
    private void cleanNetwork() {
        // Reset encryption
        this.key = null;
        this.curR = 0;
        this.curW = 0;

        try {
            // Đặt trạng thái ngắt kết nối
            this.connected = false;
            this.connecting = false;
            
            // Đóng socket
            if (this.sc != null) {
                this.sc.close();
                this.sc = null;
            }

            // Đóng output stream
            if (this.dos != null) {
                this.dos.close();
                this.dos = null;
            }

            // Đóng input stream
            if (this.dis != null) {
                this.dis.close();
                this.dis = null;
            }

            // Clear thread references
            this.sendThread = null;
            this.collectorThread = null;
            
            // Force garbage collection
            System.gc();
        } catch (Exception var2) {
            var2.printStackTrace();
        }
    }

    /**
     * Kết nối đến server
     * 
     * Tạo thread mới để thực hiện kết nối không đồng bộ
     * 
     * @param host Địa chỉ IP hoặc hostname
     * @param port Cổng kết nối (dạng String)
     */
    public void connect(String host, String port) {
//      host="127.0.0.1";
//      port="19129";
        if (!this.connected && !this.connecting) {
            // Khởi tạo trạng thái ban đầu
            this.getKeyComplete = false;
            this.sc = null;
            this.sender.clear();
            
            // Tạo và chạy thread kết nối
            this.initThread = new Thread(new Session_ME.NetworkInit(host, Integer.parseInt(port)));
            this.initThread.start();
            Cout.Debug("Connect to Ip " + host + "- " + port);
        }
    }

    // ==================== INNER CLASSES ====================

    /**
     * MessageCollector - Thread thu thập message từ server
     * 
     * Chạy trong vòng lặp vô hạn để:
     * 1. Đọc message từ input stream
     * 2. Xử lý message đặc biệt (như key exchange)
     * 3. Gửi message thường đến handler để xử lý
     * 4. Xử lý lỗi kết nối và disconnect
     */
    class MessageCollector implements Runnable {
        public void run() {
            while(true) {
                try {
                    if (Session_ME.this.isConnected()) {
                        // Đọc message từ server
                        Message message = this.readMessage();
                        final Message msg = message;
                        
                        if (message != null) {
                            try {
                                // Xử lý message đặc biệt: Key exchange (command = -40)
                                if (message.command == -40) {
                                    this.getKey(message);
                                    continue;
                                }

                                // Gửi message thường đến main thread để xử lý
                                Gdx.app.postRunnable(new Runnable() {
                                    public void run() {
                                        Session_ME.messageHandler.onMessage(msg);
                                    }
                                });
                            } catch (Exception var4) {
                                var4.printStackTrace();
                            }
                            continue;
                        }
                    }
                } catch (Exception var5) {
                    // Lỗi khi đọc message - có thể mất kết nối
                }

                // Kết nối bị ngắt - thông báo đến handler
                if (Session_ME.this.connected) {
                    if (Session_ME.messageHandler != null) {
                        // Kiểm tra thời gian kết nối để phân biệt disconnect vs connection fail
                        if (mSystem.currentTimeMillis() - Session_ME.this.timeConnected > 500L) {
                            // Đã kết nối được > 500ms - là mất kết nối
                            Session_ME.messageHandler.onDisconnected();
                        } else {
                            // Mới kết nối < 500ms - là lỗi kết nối
                            Session_ME.messageHandler.onConnectionFail();
                        }
                    }

                    // Dọn dẹp kết nối
                    if (Session_ME.this.sc != null) {
                        Session_ME.this.cleanNetwork();
                    }
                }

                return;
            }
        }

        /**
         * Nhận và xử lý encryption key từ server
         * 
         * Format key message:
         * - Byte 1: Kích thước key
         * - Byte 2-n: Các byte của key
         * 
         * Key được XOR với nhau để tạo key cuối cùng
         * 
         * @param message Message chứa key
         * @throws IOException Lỗi khi đọc key
         */
        private void getKey(Message message) throws IOException {
            byte keySize = message.reader().readByte();
            Session_ME.this.key = new byte[keySize];

            // Đọc các byte của key
            int i;
            for(i = 0; i < keySize; ++i) {
                Session_ME.this.key[i] = message.reader().readByte();
            }

            // XOR các byte với nhau để tạo key cuối cùng
            for(i = 0; i < Session_ME.this.key.length - 1; ++i) {
                byte[] var10000 = Session_ME.this.key;
                var10000[i + 1] ^= Session_ME.this.key[i];
            }

            // Đánh dấu đã nhận key hoàn tất
            Session_ME.this.getKeyComplete = true;
        }

        /**
         * Đọc một message từ input stream
         * 
         * Format message:
         * 1. Command byte (1 byte)
         * 2. Data size (2 bytes hoặc 4 bytes nếu command = 127)
         * 3. Data content (n bytes)
         * 
         * Tất cả đều có thể được mã hóa nếu đã có key
         * 
         * @return Message đã đọc
         * @throws Exception Lỗi khi đọc message
         */
        private Message readMessage() throws Exception {
            ++Session_ME.countRead;
            
            // Bước 1: Đọc command byte
            byte cmd = Session_ME.this.dis.readByte();
            if (Session_ME.this.getKeyComplete) {
                cmd = Session_ME.this.readKey(cmd);
            }

            int size;
            byte[] data;
            
            // Bước 2: Đọc data size
            if (Session_ME.this.getKeyComplete) {
                // Kiểm tra command đặc biệt 127 (full size - 4 bytes)
                if (cmd == 127) {
                    cmd = Session_ME.this.dis.readByte();
                    cmd = Session_ME.this.readKey(cmd);
                    
                    // Đọc 4 bytes size
                    data = new byte[]{
                        Session_ME.this.dis.readByte(), 
                        Session_ME.this.dis.readByte(), 
                        Session_ME.this.dis.readByte(), 
                        Session_ME.this.dis.readByte()
                    };
                    size = Session_ME.this.readKey(data[3]) & 255 
                         | (Session_ME.this.readKey(data[2]) & 255) << 8 
                         | (Session_ME.this.readKey(data[1]) & 255) << 16 
                         | (Session_ME.this.readKey(data[0]) & 255) << 24;
                } else {
                    // Đọc 2 bytes size thông thường
                    byte b1 = Session_ME.this.dis.readByte();
                    byte b2 = Session_ME.this.dis.readByte();
                    size = (Session_ME.this.readKey(b1) & 255) << 8 
                         | Session_ME.this.readKey(b2) & 255;
                }
            } else {
                // Chưa có key - đọc size gốc (2 bytes)
                size = Session_ME.this.dis.readUnsignedShort();
            }

            // Bước 3: Đọc data content
            data = new byte[size];
            int len = 0;
            int byteRead = 0;

            // Đọc cho đến khi đủ size bytes
            while(len != -1 && byteRead < size) {
                len = Session_ME.this.dis.read(data, byteRead, size - byteRead);
                if (len > 0) {
                    byteRead += len;
                    Session_ME var10000 = Session_ME.this;
                    var10000.recvByteCount += 5 + byteRead;
                }
            }

            // Giải mã data nếu đã có key
            if (Session_ME.this.getKeyComplete) {
                for(int i = 0; i < data.length; ++i) {
                    data[i] = Session_ME.this.readKey(data[i]);
                }
            }

            // Tạo message từ command và data
            Message msg = new Message(cmd, data);
            return msg;
        }
    }

    /**
     * NetworkInit - Thread khởi tạo kết nối đến server
     * 
     * Thực hiện:
     * 1. Tạo timeout thread (20 giây)
     * 2. Kết nối socket đến server
     * 3. Tạo các thread gửi/nhận message
     * 4. Gửi message đầu tiên để request key
     */
    class NetworkInit implements Runnable {
        private final String host;
        private final int port;

        NetworkInit(String host, int port) {
            this.host = host;
            this.port = port;
        }

        public void run() {
            Session_ME.isCancel = false;
            
            // Tạo timeout thread (20 giây)
            (new Thread(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(20000L);
                    } catch (InterruptedException var3) {
                    }

                    // Timeout - hủy kết nối nếu vẫn đang connecting
                    if (Session_ME.this.connecting) {
                        try {
                            Session_ME.this.sc.close();
                        } catch (Exception var2) {
                        }

                        Session_ME.isCancel = true;
                        Session_ME.this.connecting = false;
                        Session_ME.this.connected = false;
                        Session_ME.messageHandler.onConnectionFail();
                    }
                }
            })).start();
            
            Session_ME.this.connecting = true;
            Thread.currentThread().setPriority(1);
            Session_ME.this.connected = true;

            try {
                // Thực hiện kết nối
                this.doConnect(this.host, this.port);
                
                // Thông báo kết nối thành công
                Session_ME.messageHandler.onConnectOK();
            } catch (Exception var4) {
                try {
                    Thread.sleep(500L);
                } catch (InterruptedException var3) {
                }

                // Kiểm tra nếu bị cancel thì return
                if (Session_ME.isCancel) {
                    return;
                }

                // Thông báo kết nối thất bại
                if (Session_ME.messageHandler != null) {
                    Session_ME.this.close();
                    Session_ME.messageHandler.onConnectionFail();
                }
            }
        }

        /**
         * Thực hiện kết nối đến server
         * 
         * Các bước:
         * 1. Tạo socket và kết nối
         * 2. Bật keep-alive
         * 3. Lấy input/output stream
         * 4. Tạo và chạy Sender thread
         * 5. Tạo và chạy MessageCollector thread
         * 6. Gửi message request key (command -40)
         * 
         * @param host Địa chỉ server
         * @param port Cổng kết nối
         * @throws Exception Lỗi khi kết nối
         */
        public void doConnect(String host, int port) throws Exception {
            // Tạo socket
            Session_ME.this.sc = new mSocket(host, port);
            Session_ME.this.sc.setKeepAlive(true);
            
            // Lấy input/output stream
            Session_ME.this.dos = Session_ME.this.sc.getOutputStream();
            Session_ME.this.dis = Session_ME.this.sc.getInputStream();
            
            // Tạo và chạy Sender thread
            (new Thread(Session_ME.this.sender)).start();
            
            // Tạo và chạy MessageCollector thread
            Session_ME.this.collectorThread = new Thread(Session_ME.this.new MessageCollector());
            Session_ME.this.collectorThread.start();
            
            // Lưu thời gian kết nối
            Session_ME.this.timeConnected = mSystem.currentTimeMillis();
            
            // Gửi message request key encryption (command -40)
            Session_ME.this.doSendMessage(new Message((byte)-40));
            
            // Kết nối hoàn tất
            Session_ME.this.connecting = false;
        }
    }

    /**
     * Sender - Thread gửi message đến server
     * 
     * Quản lý queue message cần gửi và gửi tuần tự từng message
     * Chỉ gửi khi đã nhận được key từ server
     */
    private class Sender implements Runnable {
        /**
         * Queue chứa các message cần gửi
         */
        public final mVector sendingMessage = new mVector();

        public Sender() {
        }

        /**
         * Thêm message vào queue để gửi
         * 
         * @param message Message cần gửi
         */
        public void AddMessage(Message message) {
            this.sendingMessage.addElement(message);
        }

        /**
         * Xóa tất cả message trong queue
         */
        public void removeAllMessage() {
            if (this.sendingMessage != null) {
                this.sendingMessage.removeAllElements();
            }
        }

        /**
         * Vòng lặp gửi message
         * 
         * Liên tục kiểm tra queue và gửi message khi:
         * - Đã kết nối
         * - Đã nhận được key
         */
        public void run() {
            while(true) {
                try {
                    if (Session_ME.this.connected) {
                        // Chỉ gửi khi đã có key
                        if (Session_ME.this.getKeyComplete) {
                            // Gửi tất cả message trong queue
                            while(this.sendingMessage.size() > 0) {
                                Message m = (Message)this.sendingMessage.elementAt(0);
                                this.sendingMessage.removeElementAt(0);
                                Session_ME.this.doSendMessage(m);
                            }
                        }

                        try {
                            // Sleep 10ms để tránh busy waiting
                            Thread.sleep(10L);
                        } catch (InterruptedException var2) {
                        }
                        continue;
                    }
                } catch (IOException var3) {
                    var3.printStackTrace();
                }

                // Kết nối đã đóng - thoát thread
                return;
            }
        }

        /**
         * Xóa tất cả message trong queue
         */
        public void clear() {
            this.sendingMessage.removeAllElements();
        }
    }
}
