package mchien.code.screen.screen.baucua;

import java.util.Vector;

/**
 * Thông tin phòng game Bầu Cua
 */
public class BauCuaRoom {
    public int roomId;
    public Vector<BauCuaPlayer> players = new Vector<BauCuaPlayer>();
    public static final int MAX_PLAYERS = 5;
    public static final int MIN_PLAYERS = 2;
    
    // Game states
    public static final int STATE_WAITING = 0;      // Chờ người chơi
    public static final int STATE_BETTING = 1;      // Đang đặt cược  
    public static final int STATE_ROLLING = 2;      // Đang lăn xúc xắc
    public static final int STATE_RESULT = 3;       // Hiển thị kết quả
    
    public int gameState = STATE_WAITING;
    public long stateStartTime = 0;
    public static final long BETTING_TIME = 30000;  // 30 giây đặt cược
    public static final long ROLLING_TIME = 5000;   // 5 giây lăn xúc xắc
    public static final long RESULT_TIME = 5000;    // 3 giây hiển thị kết quả
    
    // Dice results
    public int[] diceResults = new int[3];
    
    // Room betting settings
    public long defaultBetAmount = 10000; // Tiền cược mặc định của phòng
    public String roomPassword = ""; // Mật khẩu phòng
    
    public BauCuaRoom(int roomId) {
        this.roomId = roomId;
    }
    
    public boolean canStartGame() {
        if (players.size() < MIN_PLAYERS) {
            return false;
        }
        
        // Kiểm tra tất cả player (trừ chủ phòng) đã sẵn sàng
        for (int i = 0; i < players.size(); i++) {
            BauCuaPlayer player = players.get(i);
            if (!player.isRoomOwner && !player.isReady) {
                return false;
            }
        }
        return true;
    }
    
    public BauCuaPlayer getRoomOwner() {
        for (int i = 0; i < players.size(); i++) {
            BauCuaPlayer player = players.get(i);
            if (player.isRoomOwner) {
                return player;
            }
        }
        return null;
    }
    
    public void addPlayer(BauCuaPlayer player) {
        if (players.size() < MAX_PLAYERS) {
            if (players.size() == 0) {
                player.isRoomOwner = true;
            }
            // Màu sẽ được set dựa trên vị trí trong room khi vẽ
            players.addElement(player);
        }
    }
    
    public void removePlayer(BauCuaPlayer player) {
        players.removeElement(player);
        
        // Nếu chủ phòng rời đi, chọn chủ phòng mới
        if (player.isRoomOwner && players.size() > 0) {
            BauCuaPlayer newOwner = players.get(0);
            newOwner.isRoomOwner = true;
        }
    }
    
    public void resetAllPlayersReady() {
        for (int i = 0; i < players.size(); i++) {
            BauCuaPlayer player = players.get(i);
            if (!player.isRoomOwner) {
                player.isReady = false;
            }
        }
    }
    
    public void resetAllPlayersBets() {
        for (int i = 0; i < players.size(); i++) {
            players.get(i).resetBets();
        }
    }
    
    public long getTimeRemaining() {
        long elapsed = System.currentTimeMillis() - stateStartTime;
        switch (gameState) {
            case STATE_BETTING:
                return Math.max(0, BETTING_TIME - elapsed);
            case STATE_ROLLING:
                return Math.max(0, ROLLING_TIME - elapsed);
            case STATE_RESULT:
                return Math.max(0, RESULT_TIME - elapsed);
            default:
                return 0;
        }
    }
    
    public boolean isTimeUp() {
        return getTimeRemaining() <= 0;
    }
}
