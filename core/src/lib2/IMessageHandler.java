package lib2;

/**
 * Interface IMessageHandler - Giao diện xử lý các sự kiện từ session
 * 
 * Các class implement interface này sẽ nhận và xử lý:
 * - Message từ server
 * - Các sự kiện kết nối (thành công, thất bại, mất kết nối)
 * 
 * @author NLTB8 Team
 */
public interface IMessageHandler {
    /**
     * Xử lý message nhận được từ server
     * 
     * @param message Message cần xử lý
     */
    void onMessage(Message message);

    /**
     * Xử lý sự kiện kết nối thất bại
     * Được gọi khi không thể kết nối đến server
     */
    void onConnectionFail();

    /**
     * Xử lý sự kiện mất kết nối
     * Được gọi khi kết nối bị ngắt sau khi đã kết nối thành công
     */
    void onDisconnected();

    /**
     * Xử lý sự kiện kết nối thành công
     * Được gọi khi kết nối đến server thành công
     */
    void onConnectOK();
}
