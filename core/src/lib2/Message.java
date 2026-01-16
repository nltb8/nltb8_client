package lib2;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import lib.MyStream;

/**
 * Lớp Message - Đóng gói dữ liệu giao tiếp giữa client và server
 * 
 * Message bao gồm:
 * - command: Mã lệnh định danh loại message (1 byte)
 * - data: Dữ liệu đi kèm (mảng byte)
 * 
 * Có 2 loại message:
 * 1. Message gửi đi (outgoing): Khởi tạo với command, ghi dữ liệu qua writer()
 * 2. Message nhận về (incoming): Khởi tạo với command + data, đọc dữ liệu qua reader()
 * 
 * @author NLTB8 Team
 */
public class Message {
    /**
     * Mã lệnh của message (1 byte)
     * Định danh loại message và cách xử lý tương ứng
     */
    public byte command;
    
    // Các stream để GHI dữ liệu (dùng khi tạo message gửi đi)
    private ByteArrayOutputStream os = null;
    private DataOutputStream dos = null;
    
    // Các stream để ĐỌC dữ liệu (dùng khi nhận message về)
    private ByteArrayInputStream is = null;
    private MyStream dis = null;

    /**
     * Constructor mặc định
     */
    public Message() {
    }

    /**
     * Tạo message mới để GỬI ĐI với mã lệnh cho trước
     * 
     * @param command Mã lệnh của message (int sẽ được cast về byte)
     */
    public Message(int command) {
        this.command = (byte)command;
        this.os = new ByteArrayOutputStream();
        this.dos = new DataOutputStream(this.os);
    }

    /**
     * Tạo message mới để GỬI ĐI với mã lệnh cho trước
     * 
     * @param command Mã lệnh của message
     */
    public Message(byte command) {
        this.command = command;
        this.os = new ByteArrayOutputStream();
        this.dos = new DataOutputStream(this.os);
    }

    /**
     * Tạo message từ dữ liệu NHẬN VỀ từ server
     * 
     * @param command Mã lệnh của message
     * @param data Dữ liệu đi kèm message
     */
    public Message(byte command, byte[] data) {
        this.command = command;
        this.is = new ByteArrayInputStream(data);
        this.dis = new MyStream(data, false);
    }

    /**
     * Lấy dữ liệu đã ghi vào message (dùng khi gửi message)
     * 
     * @return Mảng byte chứa dữ liệu message
     */
    public byte[] getData() {
        return this.os.toByteArray();
    }

    /**
     * Lấy reader để ĐỌC dữ liệu từ message nhận về
     * 
     * @return DataInputStream để đọc dữ liệu
     */
    public DataInputStream reader() {
        return this.dis.reader();
    }

    /**
     * Lấy writer để GHI dữ liệu vào message trước khi gửi
     * 
     * @return DataOutputStream để ghi dữ liệu
     */
    public DataOutputStream writer() {
        return this.dos;
    }

    /**
     * Giải phóng tài nguyên của message
     * Đóng các stream đang mở
     */
    public void cleanup() {
        try {
            if (this.dis != null) {
                this.dis.close();
            }

            if (this.dos != null) {
                this.dos.close();
            }
        } catch (IOException var2) {
            // Bỏ qua lỗi khi đóng stream
        }
    }
}
