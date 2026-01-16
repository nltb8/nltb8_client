package lib;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Lớp mSocket - Wrapper cho Java Socket
 * 
 * Cung cấp các phương thức đơn giản để làm việc với socket kết nối đến game server.
 * Đóng gói các thao tác cơ bản: tạo kết nối, lấy input/output stream, đóng kết nối.
 * 
 * @author NLTB8 Team
 */
public class mSocket {
    /**
     * Socket kết nối đến server
     */
    private Socket socket;

    /**
     * Khởi tạo socket và kết nối đến server
     * 
     * @param host Địa chỉ IP hoặc hostname của server
     * @param port Cổng kết nối
     */
    public mSocket(String host, int port) {
        try {
            this.socket = new Socket(host, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Đóng kết nối socket
     */
    public void close() {
        try {
            if (this.socket != null) {
                this.socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Bật/tắt chế độ keep-alive cho socket
     * Keep-alive giúp duy trì kết nối bằng cách gửi các gói tin định kỳ
     * 
     * @param keepAlive true để bật keep-alive, false để tắt
     */
    public void setKeepAlive(boolean keepAlive) {
        try {
            if (this.socket != null) {
                this.socket.setKeepAlive(keepAlive);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Lấy output stream để gửi dữ liệu đến server
     * 
     * @return DataOutputStream để ghi dữ liệu, hoặc null nếu có lỗi
     */
    public DataOutputStream getOutputStream() {
        try {
            DataOutputStream dos = new DataOutputStream(this.socket.getOutputStream());
            return dos;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Lấy input stream để nhận dữ liệu từ server
     * 
     * @return DataInputStream để đọc dữ liệu, hoặc null nếu có lỗi
     */
    public DataInputStream getInputStream() {
        try {
            DataInputStream dis = new DataInputStream(this.socket.getInputStream());
            return dis;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
