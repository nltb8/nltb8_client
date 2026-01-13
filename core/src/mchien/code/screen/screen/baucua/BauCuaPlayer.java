package mchien.code.screen.screen.baucua;

import com.badlogic.gdx.graphics.Color;
import mchien.code.model.Char;
import java.util.Vector;

/**
 * Chat message class để lưu thông tin từng tin nhắn
 */
class ChatMessage {
    public String message;
    public long displayTime;
    public boolean isDisplaying; // Đánh dấu tin nhắn đang được hiển thị
    public static final long CHAT_DISPLAY_DURATION = 3000; // 3 giây
    
    public ChatMessage(String message) {
        this.message = message;
        this.displayTime = 0; // Chưa bắt đầu hiển thị
        this.isDisplaying = false;
    }
    
    /**
     * Bắt đầu hiển thị tin nhắn (set thời gian bắt đầu)
     */
    public void startDisplay() {
        if (!isDisplaying) {
            this.displayTime = System.currentTimeMillis();
            this.isDisplaying = true;
        }
    }
    
    public boolean shouldDisplay() {
        if (!isDisplaying) return true; // Tin nhắn chưa bắt đầu hiển thị thì vẫn valid
        return message != null && !message.isEmpty() && 
               (System.currentTimeMillis() - displayTime) < CHAT_DISPLAY_DURATION;
    }
    
    public boolean isExpired() {
        if (!isDisplaying) return false; // Tin nhắn chưa hiển thị thì chưa hết hạn
        return (System.currentTimeMillis() - displayTime) >= CHAT_DISPLAY_DURATION;
    }
}

/**
 * Thông tin player trong game Bầu Cua
 */
public class BauCuaPlayer {
    public Char character;
    public int playerId; // ID duy nhất của player
    public boolean isReady = false;
    public boolean isRoomOwner = false;
    public long[] bets = new long[6]; // Cược cho 6 con vật (Bầu, Cua, Tôm, Cá, Nai, Gà)
    public int[] betCounts = new int[6]; // Số lần click cho mỗi con vật (1-6)
    public long totalBet = 0;
    public Vector<ChatMessage> chatMessages = new Vector<ChatMessage>();
    public static final int MAX_CHAT_MESSAGES = 15; // Tối đa 5 tin nhắn hiển thị cùng lúc
    
    // Win/Lose result display
    public String resultMessage = ""; // "+1000 xu" hoặc "-500 xu"
    public long resultDisplayTime = 0;
    public static final long RESULT_DISPLAY_DURATION = 5000; // 5 giây
    
    // Coin system
    public int playerColor = 0xFFFFFF; // Màu đồng xèng của player
    public static final int[] PLAYER_COLORS = {
        0xFF0000, // Đỏ
            Color.BLUE.toIntBits(), // Xanh dương
        Color.ORANGE.toIntBits(), // Xanh lá
        Color.VIOLET.toIntBits(), // Vàng
        Color.GREEN.toIntBits()  // Tím
    };
    
    public BauCuaPlayer(Char character) {
        this.character = character;
        this.playerId = character != null ? character.ID : -1; // Sử dụng ID từ Char
        resetBets();
    }
    
    public BauCuaPlayer(Char character, int playerId) {
        this.character = character;
        this.playerId = playerId;
        resetBets();
    }
    
    public void setPlayerIndex(int index) {
        if (index >= 0 && index < PLAYER_COLORS.length) {
            this.playerColor = PLAYER_COLORS[index];
        }
    }
    
    public void resetBets() {
        for (int i = 0; i < bets.length; i++) {
            bets[i] = 0;
            betCounts[i] = 0;
        }
        totalBet = 0;
    }
    
    public void placeBet(int animalIndex, long amount) {
        if (animalIndex >= 0 && animalIndex < 6 && amount > 0) {
            bets[animalIndex] += amount;
            totalBet += amount;
        }
    }
    
    public boolean addBetClick(int animalIndex, long defaultBetAmount) {
        if (animalIndex >= 0 && animalIndex < 6 && betCounts[animalIndex] < 6) {
            betCounts[animalIndex]++;
            bets[animalIndex] += defaultBetAmount;
            totalBet += defaultBetAmount;
            return true;
        }
        return false;
    }
    
    /**
     * Sync betCounts từ bets array khi server update
     */
    public void syncBetCounts(long defaultBetAmount) {
        if (defaultBetAmount <= 0) return;
        
        for (int i = 0; i < 6; i++) {
            betCounts[i] = (int) (bets[i] / defaultBetAmount);
        }
    }
    
    public void setChat(String message) {
        if (message != null && !message.trim().isEmpty()) {
            // Thêm tin nhắn mới vào cuối queue (FIFO - First In First Out)
            chatMessages.addElement(new ChatMessage(message.trim()));
            
            // Giới hạn số lượng tin nhắn tối đa (xóa tin nhắn cũ nhất nếu vượt quá)
            while (chatMessages.size() > MAX_CHAT_MESSAGES) {
                chatMessages.removeElementAt(0);
            }
        }
    }
    
    public boolean shouldDisplayChat() {
        return hasActiveChatMessages();
    }
    
    public boolean hasActiveChatMessages() {
        return getCurrentChatMessage() != null;
    }
    
    /**
     * Lấy tin nhắn hiện tại cần hiển thị (chỉ 1 tin nhắn tại một thời điểm)
     * Logic: Hiển thị tin nhắn đầu tiên trong queue, sau 3s chuyển sang tin nhắn tiếp theo
     */
    public ChatMessage getCurrentChatMessage() {
        if (chatMessages.size() == 0) return null;
        
        // Lấy tin nhắn đầu tiên (cũ nhất)
        ChatMessage firstMsg = (ChatMessage) chatMessages.elementAt(0);
        
        // Bắt đầu hiển thị tin nhắn nếu chưa được hiển thị
        if (!firstMsg.isDisplaying) {
            firstMsg.startDisplay();
        }
        
        // Nếu tin nhắn đầu tiên đã hết hạn, xóa nó và lấy tin nhắn tiếp theo
        if (firstMsg.isExpired()) {
            chatMessages.removeElementAt(0);
            return getCurrentChatMessage(); // Đệ quy để lấy tin nhắn tiếp theo
        }
        
        return firstMsg;
    }
    
    public Vector<ChatMessage> getActiveChatMessages() {
        Vector<ChatMessage> activeMessages = new Vector<ChatMessage>();
        ChatMessage currentMsg = getCurrentChatMessage();
        if (currentMsg != null) {
            activeMessages.addElement(currentMsg);
        }
        return activeMessages;
    }
    
    public void updateChat() {
        // Gọi getCurrentChatMessage() sẽ tự động xóa tin nhắn hết hạn và lấy tin nhắn tiếp theo
        getCurrentChatMessage();
        
        if (!shouldDisplayResult()) {
            resultMessage = "";
        }
    }
    
    public void setResult(long winAmount) {
        if (winAmount > 0) {
            this.resultMessage = "+" + winAmount + " xu";
        } else if (winAmount < 0) {
            this.resultMessage = winAmount + " xu";
        } else {
            this.resultMessage = "Hòa";
        }
        this.resultDisplayTime = System.currentTimeMillis();
    }
    
    public boolean shouldDisplayResult() {
        return !resultMessage.isEmpty() && 
               (System.currentTimeMillis() - resultDisplayTime) < RESULT_DISPLAY_DURATION;
    }
    
    /**
     * Set result message with custom text and win/lose status
     * @param message Custom message to display
     * @param isWin True if player won, false if lost
     */
    public void setResultMessage(String message, boolean isWin) {
        this.resultMessage = message;
        this.resultDisplayTime = System.currentTimeMillis();
    }
    
    /**
     * Debug method để xem trạng thái chat queue
     */
    public String getChatQueueDebugInfo() {
        if (chatMessages.size() == 0) return "Queue: Empty";
        
        StringBuilder sb = new StringBuilder();
        sb.append("Queue(").append(chatMessages.size()).append("): ");
        for (int i = 0; i < chatMessages.size(); i++) {
            ChatMessage msg = (ChatMessage) chatMessages.elementAt(i);
            sb.append("[").append(i).append(":").append(msg.message);
            if (msg.isDisplaying) {
                long remaining = ChatMessage.CHAT_DISPLAY_DURATION - (System.currentTimeMillis() - msg.displayTime);
                sb.append("(").append(remaining/1000).append("s)");
            } else {
                sb.append("(waiting)");
            }
            sb.append("] ");
        }
        return sb.toString();
    }
}
