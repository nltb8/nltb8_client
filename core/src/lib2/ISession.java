package lib2;

/**
 * Interface ISession - Giao diện cho session kết nối client-server
 * 
 * Định nghĩa các phương thức cơ bản cho việc quản lý kết nối:
 * - Kết nối/ngắt kết nối
 * - Gửi/nhận message
 * - Kiểm tra trạng thái kết nối
 * 
 * @author NLTB8 Team
 */
public interface ISession {
    /**
     * Kiểm tra xem có đang kết nối với server không
     * 
     * @return true nếu đang kết nối, false nếu không
     */
    boolean isConnected();

    /**
     * Đăng ký handler để xử lý các message nhận từ server
     * 
     * @param messageHandler Handler xử lý message
     */
    void setHandler(IMessageHandler messageHandler);

    /**
     * Kết nối đến server
     * 
     * @param host Địa chỉ IP hoặc hostname của server
     * @param port Cổng kết nối (dạng String)
     */
    void connect(String host, String port);

    /**
     * Gửi message đến server
     * 
     * @param message Message cần gửi
     */
    void sendMessage(Message message);

    /**
     * Đóng kết nối và giải phóng tài nguyên
     */
    void close();
}
