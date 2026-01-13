package mchien.code.screen.screen.baucua;

import com.badlogic.gdx.graphics.g2d.Sprite;
import lib.mGraphics;
import lib.mVector;
import lib2.mFont;
import mchien.code.main.GameCanvas;
import mchien.code.model.IActionListener;
import mchien.code.model.ImageIcon;
import mchien.code.model.T;
import mchien.code.model.mCommand;
import mchien.code.network.GameService;
import mchien.code.screen.Res;
import mchien.code.screen.Utils;
import mchien.code.screen.event.EventScreen;
import mchien.code.screen.screen.ChangScr;
import mchien.code.screen.screen.GameData;
import mchien.code.screen.screen.GameScr;
import mchien.code.screen.screen.ScreenTeam;

import javax.microedition.lcdui.Image;
import java.util.Vector;

/**
 * Created by khiem on 8/13/2025.
 *
 * @author Monkey
 */
public class BauCua extends ScreenTeam implements IActionListener {

    public static Image imgBG;
    public static int idBG, xBG, yBG, wBG, hBG;
    public static Image[] icons;
    public static Image[] xucXac;
    public static Image[] coinImages; // 5 hình xẻng từ ID 13-16
    public static Sprite[] miniIcons; //

    // Dice animation constants
    private static final int DICE_COUNT = 3;
    private static final long DICE_ANIMATION_DURATION = 15000; // 3 giây
    private static final int DICE_ANIMATION_SPEED = 500; // ms giữa các frame
    // Layout constants cho 6 con vật
    private static final int ANIMALS_PER_ROW = 3;
    private static final int ANIMAL_SPACING_X = 80; // Khoảng cách ngang giữa các con vật
    private static final int ANIMAL_SPACING_Y = 80;  // Khoảng cách dọc giữa 2 hàng


    // Thay thế các biến hiệu ứng cũ bằng những biến này:
    private float[] diceRotationAngle = new float[DICE_COUNT];    // Góc xoay hiện tại (0-360)
    private float[] diceRotationSpeed = new float[DICE_COUNT];    // Tốc độ xoay (độ/giây)
    private long lastRotationUpdate = 0;

    // Dice animation state
    public boolean isDiceRolling = false;
    private long diceRollStartTime = 0;
    private long lastDiceFrameTime = 0;
    private int[] currentDiceFrames = new int[DICE_COUNT]; // Frame hiện tại của từng xúc xắc
    public int[] finalDiceResults = new int[DICE_COUNT];  // Kết quả cuối cùng

    // Room management
    public BauCuaRoom currentRoom;
    public Vector<BauCuaPlayer> bauCuaPlayers = new Vector<BauCuaPlayer>();
    public BauCuaPlayer myPlayer;
    
    // Betting preview system
    private long[] previewBets = new long[6]; // Preview cược chưa đặt
    private boolean hasPreviewBets = false;
    private mCommand cmdPlaceBet; // Nút "Đặt" ở giữa
    private mCommand cmdCancelBet; // Nút "Hủy" preview
    
    // Money transfer animation system
    private Vector<MoneyTransferAnim> moneyTransfers = new Vector<MoneyTransferAnim>();
    
    // Text animation system for win/lose messages
    private Vector<TextFlyAnim> textAnimations = new Vector<TextFlyAnim>();


    // Inner class cho animation chuyển tiền
    private class MoneyTransferAnim {
        public int fromPlayerIndex;
        public int toPlayerIndex;
        public long amount;
        public long startTime;
        public float progress; // 0.0 - 1.0
        public static final long ANIMATION_DURATION = 2000; // 2 giây
        
        public MoneyTransferAnim(int from, int to, long amount) {
            this.fromPlayerIndex = from;
            this.toPlayerIndex = to;
            this.amount = amount;
            this.startTime = System.currentTimeMillis();
            this.progress = 0f;
        }
        
        public void update() {
            long elapsed = System.currentTimeMillis() - startTime;
            progress = Math.min(1f, elapsed / (float) ANIMATION_DURATION);
        }
        
        public boolean isFinished() {
            return progress >= 1f;
        }
    }
    
    // Inner class cho text animation bay
    private class TextFlyAnim {
        public int fromPlayerIndex;
        public int toPlayerIndex;
        public String text;
        public int textColor;
        public long startTime;
        public float progress; // 0.0 - 1.0
        public static final long ANIMATION_DURATION = 2500; // 2.5 giây

        public TextFlyAnim(int from, int to, String text, int color) {
            this.fromPlayerIndex = from;
            this.toPlayerIndex = to;
            this.text = text;
            this.textColor = color;
            this.startTime = System.currentTimeMillis();
            this.progress = 0f;
        }

        public void update() {
            long currentTime = System.currentTimeMillis();
            long elapsed = currentTime - startTime;
            
            // Chỉ bắt đầu animation khi đã đến thời gian (xử lý delay)
            if (elapsed < 0) {
                progress = 0f;
            } else {
                progress = Math.min(1f, elapsed / (float) ANIMATION_DURATION);
            }
        }

        public boolean isFinished() {
            return progress >= 1f;
        }
    }

    private int f1, f2, f3, f4, f5;
    private int fr1, fr2, fr3, fr4, fr5;
    private long timeLoadImg;
    private mCommand cmdReady;
    private mCommand menu;
    private mCommand startGame;
    private mCommand cmdChat;


    public BauCua() {
        for (int i = 0; i < DICE_COUNT; i++) {
            diceRotationAngle[i] = 0f;
            diceRotationSpeed[i] = 180f + Utils.random(360); // 180-540 độ/giây
        }

        // Initialize room - sẽ được set từ server
        currentRoom = null;

        // Tạo player cho main character - sẽ được add khi server gửi room info
        myPlayer = null;

        // Không lăn xúc xắc khi khởi tạo - chỉ lăn khi hết thời gian đặt cược
        isDiceRolling = false;

        // Khởi tạo kết quả xúc xắc mặc định
        for (int i = 0; i < DICE_COUNT; i++) {
            finalDiceResults[i] = 0;
            currentDiceFrames[i] = 0;
            diceRotationAngle[i] = 0f;
            diceRotationSpeed[i] = 180f + Utils.random(360);
        }
        
        // Tạo các command
        menu = new mCommand("Menu", this, 1000);
        menu.x = 0;
        menu.y = GameCanvas.h - GameCanvas.h / 10;

        startGame = new mCommand("Bắt đầu", this, 1002);
        int w0 = mGraphics.getImageWidth(GameScr.imgButton[0]);
        startGame.x = GameCanvas.w - w0;
        startGame.y = GameCanvas.h - GameCanvas.h / 10;

        cmdReady = new mCommand("Sẵn sàng", this, 1003);
        cmdReady.x = GameCanvas.w - w0;
        cmdReady.y = GameCanvas.h - GameCanvas.h / 10;

        cmdChat = new mCommand("Chat", this, 1004);
        cmdChat.x = GameCanvas.w/10;
        cmdChat.y = 0;
        
        // Nút đặt cược ở giữa (ẩn ban đầu)
        cmdPlaceBet = new mCommand("Đặt cược", this, 1015);
        cmdPlaceBet.x = GameCanvas.w / 2 - 40;
        cmdPlaceBet.y =  GameCanvas.h - GameCanvas.h / 10;
        
        // Nút hủy preview (vị trí giống nút sẵn sàng)
        cmdCancelBet = new mCommand("Hủy", this, 1016);
        cmdCancelBet.x = GameCanvas.w - w0;
        cmdCancelBet.y = GameCanvas.h - GameCanvas.h / 10;
        
        // Load images
        imgBG = loadImage(0);
        if (imgBG != null) {
            wBG = imgBG.getWidth();
            hBG = imgBG.getHeight();
            xBG = GameCanvas.w / 2 - wBG / 2;
            yBG = GameCanvas.h / 2 - hBG / 2;
        }
        icons = new Image[6]; // 6 con vật
        xucXac = new Image[6]; // 6 xúc xắc
        coinImages = new Image[4]; // 5 hình xẻng (ID 13-16, chỉ có 4 hình)
        miniIcons = new Sprite[4]; // Mini icons cho các con vật
        loadImageGame();

        System.out.println("BauCua UI initialized - waiting for server room data");
    }

    private void loadImageGame() {
        for (int i = 0; i < icons.length; i++) {
            icons[i] = loadImage(i + 1);
        }
        for (int i = 0; i < xucXac.length; i++) {
            xucXac[i] = loadImage(i + 7); // Giả sử các xúc xắc bắt đầu từ ID 7
        }
        for (int i = 0; i < coinImages.length; i++) {
            coinImages[i] = loadImage(i + 13); // Hình xẻng từ ID 13-16 (4 hình)
            if( coinImages[i] != null) {
                miniIcons[i] = new Sprite(coinImages[i].texture);
            }
        }
    }

    public boolean checkLoadImage() {
        if (imgBG == null || icons == null || xucXac == null || coinImages == null) {
            return false;
        }
        for (Image icon : icons) {
            if (icon == null) return false;
        }
        for (Image xucXacImage : xucXac) {
            if (xucXacImage == null) return false;
        }
        for (Image coinImage : coinImages) {
            if (coinImage == null) return false;
        }
        return true;
    }



    private Image loadImage(int id) {
        ImageIcon img = GameData.getImgIcon((short) (id + Res.ID_IMAGE_BAUCUA));
        if (img != null && img.img != null)
            return img.img;
        else
            return null;
    }

    public void switchToMe(ScreenTeam lastSRC) {
        super.switchToMe(lastSRC);
    }

    @Override
    public void perform(int var1, Object var2) {
        switch (var1) {
            case 1000: // Menu
                showGameMenu();
                break;

            case 1001: // Chat dialog OK
                String message = GameCanvas.inputDlg.tfInput.getText();
                GameCanvas.endDlg();
                if (message != null && message.trim().length() > 0) {
                    sendChatMessage(message.trim());
                }
                break;

            case 1002: // Bắt đầu game (chủ phòng)
                if (myPlayer != null && myPlayer.isRoomOwner && currentRoom != null) {
                    // Gửi lệnh bắt đầu game lên server - server sẽ validate và trả kết quả
                    GameService.gI().bauCuaStartGame();
                    System.out.println("Sent start game request to server");
                }
                break;

            case 1003: // Sẵn sàng/Hủy sẵn sàng
                toggleReady();
                break;

            case 1004: // Chat
                openChatDialog();
                break;

            case 1005: // Thoát game
                GameCanvas.gameScr.switchToMe(this);
                break;

            case 1006: // Cài đặt (chủ phòng)
                showSettingsMenu();
                break;

            case 1007: // Đuổi người chơi (chủ phòng)
                showKickPlayerMenu();
                break;

            case 1008: // Rời bàn
                leaveRoom();
                break;

            case 1009: // Cài mật khẩu
                setRoomPassword();
                break;

            case 1010: // Cài tiền cược
                setBettingAmount();
                break;

            case 1011: // Xác nhận rời bàn
                confirmLeaveRoom();
                break;

            case 1012: // Hủy rời bàn
                // Không làm gì cả
                GameCanvas.endDlg();
                break;

            case 1013: // Xác nhận đặt mật khẩu
                confirmSetPassword();
                break;

            case 1014: // Xác nhận đặt tiền cược
                confirmSetBettingAmount();
                break;

            case 1015: // Đặt cược (xác nhận preview)
                confirmPreviewBets();
                break;

            case 1016: // Hủy preview bets
                cancelPreviewBets();
                break;
                
            case 1017: // Test text animations (debug)
                testTextAnimations();
                break;
//            case 1018:
//                if (EventScreen.vecListEvent.size() == 0) {
//                    GameCanvas.addNotify(T.khongcotinnhan, (byte)0);
//                } else if (GameCanvas.mevent != null) {
//                    GameCanvas.mevent.init();
//                    GameCanvas.mevent.switchToMe(GameCanvas.gameScr);
//                }
//                break;

            default:
                // Xử lý kick player (ID từ 2000+)
                if (var1 >= 2000 && var1 < 3000) {
                    int playerIndex = var1 - 2000;
                    kickPlayer(playerIndex);
                }
                // Xử lý confirm kick player (ID từ 3000+)
                else if (var1 >= 3000 && var1 < 4000) {
                    int playerIndex = var1 - 3000;
                    confirmKickPlayer(playerIndex);
                }
                break;
        }
    }

    @Override
    public void updateKey() {
        if (GameCanvas.currentScreen != this) return;

        // Xử lý touch events
        if (GameCanvas.isPointerClick[0] && GameCanvas.isPointerJustRelease[0]) {
            handleTouch(GameCanvas.px[0], GameCanvas.py[0]);
            GameCanvas.isPointerClick[0] = false;
            GameCanvas.isPointerJustRelease[0] = false;
        }

        super.updateKey();
    }

    private void handleTouch(int x, int y) {
        if (currentRoom == null || myPlayer == null) return;

        // Kiểm tra touch vào mCommand buttons
        if (checkCommandTouch(x, y)) {
            return;
        }

        // Kiểm tra touch vào hình con vật để đặt cược (chỉ player thường, không phải chủ phòng)
        if (currentRoom.gameState == BauCuaRoom.STATE_BETTING && icons != null && myPlayer != null && !myPlayer.isRoomOwner) {
            checkAnimalTouch(x, y);
        }
    }

    private boolean checkCommandTouch(int x, int y) {
        // Kiểm tra menu command
        if (isTouchInCommand(x, y, menu)) {
            menu.performAction();
            return true;
        }

        // Kiểm tra chat command
        if (isTouchInCommand(x, y, cmdChat)) {
            cmdChat.performAction();
            return true;
        }

        // Kiểm tra nút đặt cược (chỉ player thường, không phải chủ phòng)
        if (hasPreviewBets && currentRoom.gameState == BauCuaRoom.STATE_BETTING && myPlayer != null && !myPlayer.isRoomOwner && isTouchInCommand(x, y, cmdPlaceBet)) {
            cmdPlaceBet.performAction();
            return true;
        }
        
        // Kiểm tra nút hủy preview (chỉ player thường, không phải chủ phòng)
        if (hasPreviewBets && currentRoom.gameState == BauCuaRoom.STATE_BETTING && myPlayer != null && !myPlayer.isRoomOwner && isTouchInCommand(x, y, cmdCancelBet)) {
            cmdCancelBet.performAction();
            return true;
        }

        // Kiểm tra ready/start command tùy theo trạng thái
        if (currentRoom.gameState == BauCuaRoom.STATE_WAITING) {
            if (myPlayer.isRoomOwner) {
                // Chủ phòng thấy nút "Bắt đầu"
                if (isTouchInCommand(x, y, startGame)) {
                    startGame.performAction();
                    return true;
                }
            } else {
                // Player thường thấy nút "Sẵn sàng"
                if (isTouchInCommand(x, y, cmdReady)) {
                    cmdReady.performAction();
                    return true;
                }
            }
        }

        return false;
    }

    private void checkAnimalTouch(int x, int y) {
        // Tính toán vị trí của 6 con vật theo layout 3x2
        int totalWidth = (ANIMALS_PER_ROW - 1) * ANIMAL_SPACING_X;
        int startX = GameCanvas.w / 2 - totalWidth / 2;
        int startY = GameCanvas.h / 2 - ANIMAL_SPACING_Y / 2;

        for (int i = 0; i < 6; i++) {
            int row = i / ANIMALS_PER_ROW;
            int col = i % ANIMALS_PER_ROW;

            int animalX = startX + col * ANIMAL_SPACING_X;
            int animalY = startY + row * ANIMAL_SPACING_Y;

            // Kiểm tra touch trong vùng hình con vật (giả sử mỗi hình có kích thước 60x60)
            int animalSize = 60;
            if (x >= animalX - animalSize / 2 && x <= animalX + animalSize / 2 &&
                    y >= animalY - animalSize / 2 && y <= animalY + animalSize / 2) {
                placeBet(i);
                return;
            }
        }
    }

    private boolean isTouchInCommand(int x, int y, mCommand cmd) {
        if (cmd == null) return false;

        return getCmdPointerLast(cmd);
    }
    public void updateResuilts(long[][][] bets) {
        if (bets == null || bets.length == 0) return;
        for (int i = 1; i < bets.length; i++) {
            if (i >= currentRoom.players.size()) continue; // Tránh lỗi nếu có ít player hơn bets
            BauCuaPlayer player = currentRoom.players.get(i);
            if (player == null) continue;
            for (int j = 0; j < bets[i].length; j++) {
                long[] bet = bets[i][j];
                boolean isWin = bet[0] > 0;
                long amount = bet[1];
                if(amount > 0) {
                    if (isWin) {
                        addPlayerWinAnimation(i, amount); // Thêm animation thắng
                        player.setChat("+" + Res.getDotNumber(amount));
                    } else  {
                        addHouseWinAnimation(i, amount); // Thêm animation thua
                        player.setChat("-" + Res.getDotNumber(amount));
                        currentRoom.players.get(0).setChat("+" + Res.getDotNumber(amount));
                    }
                }
            }
        }
    }

    private void toggleReady() {
        if (myPlayer != null) {
            myPlayer.isReady = !myPlayer.isReady;
            GameService.gI().bauCuaReady(myPlayer.isReady);
        }
    }

    private void openChatDialog() {
        // Tạo input dialog để nhập chat
        GameCanvas.inputDlg.setInfo("Nhập tin nhắn:", new mCommand("Gửi", this, 1001), 0, 50, true);
        GameCanvas.inputDlg.show();
    }

    private void sendChatMessage(String message) {
        if (myPlayer != null) {
            myPlayer.setChat(message);
            GameService.gI().bauCuaChat(myPlayer.character.name,message);
        }
    }

    private void placeBet(int animalIndex) {
        if (myPlayer == null || currentRoom == null) return;

        // Chủ phòng không được đặt cược (là nhà cái)
        if (myPlayer.isRoomOwner) {
            return;
        }

        // Thêm vào preview bets (tối đa 6 lần)
        long betAmount = currentRoom.defaultBetAmount;
        
        if (getTotalPreviewBets() + betAmount <= myPlayer.character.charXu && previewBets[animalIndex] < 6 * betAmount) {
            previewBets[animalIndex] += betAmount;
            hasPreviewBets = true;
            
        }
    }
    
    private long getTotalPreviewBets() {
        long total = 0;
        for (int i = 0; i < previewBets.length; i++) {
            total += previewBets[i];
        }
        return total;
    }
    
    private void confirmPreviewBets() {
        if (!hasPreviewBets || myPlayer == null) return;
        
        long totalBet = getTotalPreviewBets();
        if (myPlayer.character.charXu >= totalBet) {
            // Gửi tất cả cược lên server và đợi server update
            for (int i = 0; i < previewBets.length; i++) {
                if (previewBets[i] > 0) {
                    long betAmount = currentRoom.defaultBetAmount;
                    int betCount = (int) (previewBets[i] / betAmount);
                    
                    // Gửi từng lần đặt cược lên server
                    for (int j = 0; j < betCount; j++) {
                        GameService.gI().bauCuaPlaceBet(i, betAmount);
                    }
                }
            }
            
            // Trừ tiền ngay
            myPlayer.character.charXu -= totalBet;
            
            // Clear preview - server sẽ update betCounts sau
            clearPreviewBets();
            
            System.out.println("Đã gửi cược lên server, đợi update: " + totalBet + " xu");
        }
    }
    
    private void clearPreviewBets() {
        for (int i = 0; i < previewBets.length; i++) {
            previewBets[i] = 0;
        }
        hasPreviewBets = false;
    }
    
    private void cancelPreviewBets() {
        clearPreviewBets();
        System.out.println("Đã hủy preview bets");
    }
    
    // ========== SERVER UPDATE METHODS ==========
    
    /**
     * Method để clear preview sau khi server đã update betting info
     * Được gọi từ onBauCuaRoomInfo khi nhận được update từ server
     */
    public void clearPreviewAfterServerUpdate() {
        if (hasPreviewBets) {
            clearPreviewBets();
            System.out.println("Cleared preview bets after server update");
        }
    }

    // ========== MONEY TRANSFER ANIMATION METHODS ==========
    
    /**
     * Tạo animation chuyển tiền từ player này sang player khác
     */
    public void addMoneyTransferAnimation(int fromPlayerIndex, int toPlayerIndex, long amount) {
        if (fromPlayerIndex != toPlayerIndex && amount > 0) {
            MoneyTransferAnim anim = new MoneyTransferAnim(fromPlayerIndex, toPlayerIndex, amount);
            moneyTransfers.addElement(anim);
        }
    }
    
    /**
     * Tạo text animation bay từ player này sang player khác
     * @param fromPlayerIndex Player gửi text (thua)
     * @param toPlayerIndex Player nhận text (thắng)
     * @param text Text hiển thị (ví dụ: "+1000 xu", "Thắng!")
     * @param color Màu text (0x00FF00 = xanh lá cho thắng, 0xFF0000 = đỏ cho thua)
     */
    public void addTextFlyAnimation(int fromPlayerIndex, int toPlayerIndex, String text, int color) {
        if (fromPlayerIndex != toPlayerIndex && text != null && text.length() > 0) {
            // Thêm delay nhỏ dựa trên số animations hiện có để tránh đè lên nhau
            TextFlyAnim anim = new TextFlyAnim(fromPlayerIndex, toPlayerIndex, text, color);
            
            // Delay animation dựa trên số text animations hiện có (mỗi cái cách nhau 200ms)
            long delayOffset = textAnimations.size() * 200;
            anim.startTime += delayOffset;
            
            textAnimations.addElement(anim);
        }
    }
    
    /**
     * Tạo text animation khi player thắng - text bay từ chủ phòng tới player
     */
    public void addPlayerWinAnimation(int playerIndex, long winAmount) {
        if (currentRoom != null && playerIndex > 0 && playerIndex < currentRoom.players.size()) {
            // Text bay từ chủ phòng (index 0) tới player thắng - chỉ hiển thị số xu
            String winText = Res.getDotNumber(winAmount);
            addTextFlyAnimation(0, playerIndex, winText, 0x00FF00); // Màu xanh lá
        }
    }
    
    /**
     * Tạo text animation khi chủ phòng thắng - text bay từ player tới chủ phòng
     */
    public void addHouseWinAnimation(int playerIndex, long loseAmount) {
        if (currentRoom != null && playerIndex > 0 && playerIndex < currentRoom.players.size()) {
            // Text bay từ player thua tới chủ phòng (index 0) - chỉ hiển thị số xu
            String loseText = Res.getDotNumber(loseAmount);
            addTextFlyAnimation(playerIndex, 0, loseText, 0xFF0000); // Màu đỏ
        }
    }

    
    /**
     * Method demo để test text animations - có thể gọi từ menu hoặc debug
     */
    public void testTextAnimations() {
        if (currentRoom == null || currentRoom.players.size() < 2) {
            System.out.println("Need at least 2 players to test text animations");
            return;
        }
        
        // Clear existing animations trước
        textAnimations.removeAllElements();
        
        // Test player thắng - text bay từ chủ phòng tới player 1
        addPlayerWinAnimation(1, 5000);
        
        // Test chủ phòng thắng - text bay từ player 2 tới chủ phòng (nếu có player 2)
        if (currentRoom.players.size() > 2) {
            addHouseWinAnimation(2, 3000);
        }
        
        // Test thêm một số animations khác - chỉ với số xu
        if (currentRoom.players.size() > 3) {
            addTextFlyAnimation(3, 0, "-1.500", 0xFF0000); // Player 3 thua
        }
        
        if (currentRoom.players.size() > 4) {
            addTextFlyAnimation(0, 4, "+2.000", 0x00FF00); // Player 4 thắng
        }
        
        System.out.println("Test text animations created: " + textAnimations.size() + " animations");
    }
    
    /**
     * Cập nhật tất cả money transfer animations
     */
    private void updateMoneyTransferAnimations() {
        for (int i = moneyTransfers.size() - 1; i >= 0; i--) {
            MoneyTransferAnim anim = (MoneyTransferAnim) moneyTransfers.elementAt(i);
            anim.update();
            
            if (anim.isFinished()) {
                moneyTransfers.removeElementAt(i);
            }
        }
    }
    
    /**
     * Cập nhật tất cả text fly animations
     */
    private void updateTextFlyAnimations() {
        for (int i = textAnimations.size() - 1; i >= 0; i--) {
            TextFlyAnim anim = (TextFlyAnim) textAnimations.elementAt(i);
            anim.update();
            
            if (anim.isFinished()) {
                textAnimations.removeElementAt(i);
            }
        }
    }
    
    /**
     * Vẽ tất cả money transfer animations
     */
    private void paintMoneyTransferAnimations(mGraphics g) {
        if (currentRoom == null) return;
        
        for (int i = 0; i < moneyTransfers.size(); i++) {
            MoneyTransferAnim anim = (MoneyTransferAnim) moneyTransfers.elementAt(i);
            paintMoneyTransferAnimation(g, anim);
        }
    }
    
    /**
     * Vẽ tất cả text fly animations
     */
    private void paintTextFlyAnimations(mGraphics g) {
        if (currentRoom == null) return;
        
        for (int i = 0; i < textAnimations.size(); i++) {
            TextFlyAnim anim = (TextFlyAnim) textAnimations.elementAt(i);
            paintTextFlyAnimation(g, anim);
        }
    }
    
    /**
     * Vẽ một animation chuyển tiền cụ thể
     */
    private void paintMoneyTransferAnimation(mGraphics g, MoneyTransferAnim anim) {
        // Lấy vị trí của from player và to player
        int[] fromPos = getPlayerPosition(anim.fromPlayerIndex);
        int[] toPos = getPlayerPosition(anim.toPlayerIndex);
        
        if (fromPos == null || toPos == null) return;
        
        // Tính toán vị trí hiện tại của animation dựa trên progress
        int currentX = (int) (fromPos[0] + (toPos[0] - fromPos[0]) * anim.progress);
        int currentY = (int) (fromPos[1] + (toPos[1] - fromPos[1]) * anim.progress);
        
        // Vẽ đồng xu bay (hình vuông thay vì tròn)
        g.setColor(0xFFD700); // Màu vàng
        g.fillRect(currentX - 5, currentY - 5, 10, 10, false);
        g.setColor(0x000000);
        g.drawRect(currentX - 5, currentY - 5, 10, 10, false);
        
        // Vẽ text số tiền
        String amountText = Res.getDotNumber(anim.amount);
        mFont.tahoma_7b_yellow.drawString(g, amountText, currentX, currentY - 15, 2, false);
    }
    
        /**
     * Vẽ một text animation bay cụ thể
     */
    private void paintTextFlyAnimation(mGraphics g, TextFlyAnim anim) {
        // Không vẽ nếu animation chưa bắt đầu (đang delay)
        if (anim.progress <= 0f) return;
        
        // Lấy vị trí của from player và to player
        int[] fromPos = getPlayerPosition(anim.fromPlayerIndex);
        int[] toPos = getPlayerPosition(anim.toPlayerIndex);
 
        if (fromPos == null || toPos == null) return;
 
        // Tính toán vị trí hiện tại của animation dựa trên progress
        int currentX = (int) (fromPos[0] + (toPos[0] - fromPos[0]) * anim.progress);
        int currentY = (int) (fromPos[1] + (toPos[1] - fromPos[1]) * anim.progress);
 
        // Tạo hiệu ứng bay lên một chút (parabolic path) - bay cao hơn để tránh đè
        float heightOffset = (float) Math.sin(anim.progress * Math.PI) * 60; // Bay lên 60 pixel
        currentY -= (int) heightOffset;
        
        // Thêm deterministic offset dựa trên animation index để tránh text đè lên nhau
        int animIndex = 0;
        for (int i = 0; i < textAnimations.size(); i++) {
            if (textAnimations.elementAt(i) == anim) {
                animIndex = i;
                break;
            }
        }
        
        // Tạo offset pattern để các text không đè lên nhau
        int offsetX = (animIndex % 3 - 1) * 25; // -25, 0, +25 pixel
        int offsetY = (animIndex / 3) * 20; // 0, 20, 40 pixel cho các hàng
        currentX += offsetX;
        currentY += offsetY;
 
        // Tính toán fade effect dựa trên progress
        boolean shouldShow = true;
        if (anim.progress < 0.05f || anim.progress > 0.95f) {
            // Fade in/out bằng cách skip một số frame
            shouldShow = (System.currentTimeMillis() / 150) % 2 == 0;
        }
        
        if (!shouldShow) return;
 
        // Chọn font dựa trên nội dung text - sử dụng font rõ ràng nhất
        mFont fontToUse;
        if (anim.text.contains("+")) {
            fontToUse = mFont.tahoma_7b_yellow;
        } else if (anim.text.contains("-")) {
            fontToUse = mFont.tahoma_7b_white;
        } else {
            fontToUse = mFont.tahoma_7b_yellow;
        }

        fontToUse.drawString(g, anim.text, currentX, currentY, 2, false);

    }

    /**
     * Lấy vị trí màn hình của player theo index
     */
    private int[] getPlayerPosition(int playerIndex) {
        if (currentRoom == null || playerIndex < 0 || playerIndex >= currentRoom.players.size()) {
            return null;
        }
        
        BauCuaPlayer player = (BauCuaPlayer) currentRoom.players.elementAt(playerIndex);
        if (player == null || player.character == null) return null;
        
        int[] pos = new int[2];
        
        if (playerIndex == 0) {
            // Player 0 (Chủ) - vị trí giữa dưới
            pos[0] = GameCanvas.w / 2;
            pos[1] = GameCanvas.h - player.character.getHeight() / 3;
        } else if (playerIndex == 1) {
            // Player 1 - vị trí phải dưới
            pos[0] = GameCanvas.w - player.character.getWidth();
            pos[1] = GameCanvas.h - player.character.getHeight();
        } else if (playerIndex == 2) {
            // Player 2 - vị trí phải trên
            pos[0] = GameCanvas.w - player.character.getWidth();
            pos[1] = 0 + player.character.getHeight() * 2;
        } else if (playerIndex == 3) {
            // Player 3 - vị trí trái dưới
            pos[0] = 0 + player.character.getWidth();
            pos[1] = GameCanvas.h - player.character.getHeight();
        } else if (playerIndex == 4) {
            // Player 4 - vị trí trái trên
            pos[0] = 0 + player.character.getWidth();
            pos[1] = 0 + player.character.getHeight() * 2;
        }
        
        return pos;
    }

    // ========== MENU METHODS ==========

    /**
     * Hiển thị menu chính (khác nhau tùy trạng thái)
     */
    private void showGameMenu() {
        mVector menuItems = new mVector();

        if (currentRoom == null) {
            // Chưa vào phòng - chỉ có thoát vì server sẽ chủ động mở UI
            menuItems.addElement(new mCommand("Thoát", this, 1005));
        } else if (myPlayer != null && myPlayer.isRoomOwner) {
            // Menu cho chủ phòng
            menuItems.addElement(new mCommand("Cài đặt", this, 1006));
            menuItems.addElement(new mCommand("Đuổi", this, 1007));
//            menuItems.addElement(new mCommand("Chat", this, 1018));
            menuItems.addElement(new mCommand("Rời bàn", this, 1008));

        } else {
            // Menu cho người chơi thường
//            menuItems.addElement(new mCommand("Chat", this, 1018));
            menuItems.addElement(new mCommand("Rời bàn", this, 1008));
        }

        GameCanvas.menu.startAt(menuItems, 2);
    }

    /**
     * Hiển thị menu cài đặt (chỉ chủ phòng)
     */
    private void showSettingsMenu() {
        if (myPlayer == null || !myPlayer.isRoomOwner) return;

        mVector settingsItems = new mVector();
        settingsItems.addElement(new mCommand("Cài mật khẩu", this, 1009));
        settingsItems.addElement(new mCommand("Cài tiền cược", this, 1010));

        GameCanvas.menu.startAt(settingsItems, 2);
    }

    /**
     * Hiển thị menu đuổi người chơi (chỉ chủ phòng)
     */
    private void showKickPlayerMenu() {
        if (myPlayer == null || !myPlayer.isRoomOwner || currentRoom == null) return;

        mVector kickItems = new mVector();

        // Thêm tất cả players khác (không phải chủ phòng) vào menu
        for (int i = 0; i < currentRoom.players.size(); i++) {
            BauCuaPlayer player = (BauCuaPlayer) currentRoom.players.elementAt(i);
            if (player != null && player.character != null && !player.isRoomOwner) {
                // Sử dụng action ID từ 2000 + index để identify player
                kickItems.addElement(new mCommand("Đuổi " + player.character.name, this, 2000 + i));
            }
        }

        if (kickItems.size() > 0) {
            GameCanvas.menu.startAt(kickItems, 2);
        } else {
            GameCanvas.startOKDlg("Không có người chơi nào để đuổi!");
        }
    }

    /**
     * Rời khỏi phòng game
     */
    private void leaveRoom() {
        GameCanvas.startYesNoDlg("Bạn có chắc muốn rời bàn?",
                new mCommand("Có", this, 1011),
                new mCommand("Không", this, 1012));
    }

    /**
     * Cài đặt mật khẩu phòng
     */
    private void setRoomPassword() {
        if (myPlayer == null || !myPlayer.isRoomOwner) return;

        GameCanvas.inputDlg.setInfo("Nhập mật khẩu phòng:", new mCommand("Đặt", this, 1013), 0, 20, true);
        GameCanvas.inputDlg.show();
    }

    /**
     * Cài đặt số tiền cược mặc định
     */
    private void setBettingAmount() {
        if (myPlayer == null || !myPlayer.isRoomOwner) return;

        GameCanvas.inputDlg.setInfo("Nhập tiền cược mặc định:", new mCommand("Đặt", this, 1014), 1, 10, true);
        GameCanvas.inputDlg.show();
    }

    /**
     * Xác nhận rời phòng
     */
    private void confirmLeaveRoom() {
        // Gửi lệnh rời phòng lên server
        GameService.gI().bauCuaLeaveRoom();

        // Cleanup resources khi thoát
        BauCuaManager.gI().cleanup();

        // Quay về game chính
        GameCanvas.gameScr.switchToMe(this);
    }

    /**
     * Xác nhận đặt mật khẩu
     */
    private void confirmSetPassword() {
        String password = GameCanvas.inputDlg.tfInput.getText();
        GameCanvas.endDlg();

        if (password != null && password.trim().length() > 0) {
            // Gửi lệnh đặt mật khẩu lên server
            GameService.gI().bauCuaSetRoomPassword(password.trim());
            GameCanvas.startOKDlg("Đã đặt mật khẩu phòng: " + password.trim());
        } else {
            GameCanvas.startOKDlg("Mật khẩu không hợp lệ!");
        }
    }

    /**
     * Xác nhận đặt tiền cược mặc định
     */
    private void confirmSetBettingAmount() {
        String amountStr = GameCanvas.inputDlg.tfInput.getText();
        GameCanvas.endDlg();

        try {
            long amount = Long.parseLong(amountStr);
            if (amount > 0) {
                // Gửi lệnh đặt tiền cược mặc định lên server
                GameService.gI().bauCuaSetDefaultBet(amount);
            } else {
                GameCanvas.startOKDlg("Số tiền phải lớn hơn 0!");
            }
        } catch (NumberFormatException e) {
            GameCanvas.startOKDlg("Số tiền không hợp lệ!");
        }
    }
    


    /**
     * Đuổi người chơi khỏi phòng
     */
    private void kickPlayer(int playerIndex) {
        if (myPlayer == null || !myPlayer.isRoomOwner || currentRoom == null) return;

        if (playerIndex >= 0 && playerIndex < currentRoom.players.size()) {
            BauCuaPlayer targetPlayer = (BauCuaPlayer) currentRoom.players.elementAt(playerIndex);

            if (targetPlayer != null && targetPlayer.character != null && !targetPlayer.isRoomOwner) {
                String playerName = targetPlayer.character.name;

                GameCanvas.startYesNoDlg("Đuổi " + playerName + " khỏi bàn?",
                        new mCommand("Có", this, 3000 + playerIndex),
                        new mCommand("Không", this, 1012));
            }
        }
    }

    /**
     * Xác nhận đuổi người chơi
     */
    private void confirmKickPlayer(int playerIndex) {
        if (myPlayer == null || !myPlayer.isRoomOwner || currentRoom == null) return;

        if (playerIndex >= 0 && playerIndex < currentRoom.players.size()) {
            BauCuaPlayer targetPlayer = (BauCuaPlayer) currentRoom.players.elementAt(playerIndex);

            if (targetPlayer != null && targetPlayer.character != null && !targetPlayer.isRoomOwner) {
                String playerName = targetPlayer.character.name;
                int playerId = targetPlayer.playerId;

                // Gửi lệnh đuổi player lên server với player ID
                GameService.gI().bauCuaKickPlayer(playerId);

//                // Tạm thời remove local (trong thực tế sẽ do server xử lý)
//                currentRoom.removePlayer(targetPlayer);
//                GameCanvas.startOKDlg("Đã đuổi " + playerName + " (ID: " + playerId + ") khỏi bàn!");
            }
        }
    }

    /**
     * Cập nhật animation và UI - KHÔNG tự xử lý game state transitions
     * Tất cả state changes đều do server quyết định
     */
    private void updateGameState() {
        if (currentRoom == null) return;

        // Chỉ cập nhật dice animation dựa trên state hiện tại
        switch (currentRoom.gameState) {
            case BauCuaRoom.STATE_ROLLING:
                // Đảm bảo dice animation đang chạy
                if (!isDiceRolling) {
                    startDiceRoll();
                    isDiceRolling = true;
                }
                break;
                
            case BauCuaRoom.STATE_RESULT:
                // Dừng dice animation khi có kết quả
                isDiceRolling = false;
                break;
                
            case BauCuaRoom.STATE_WAITING:
            case BauCuaRoom.STATE_BETTING:
                // Đảm bảo dice không lăn trong các state này
                isDiceRolling = false;
                break;
        }
        
        // Tất cả state transitions sẽ được xử lý qua server messages:
        // - BAUCUA_GAME_STATE: Thay đổi state
        // - BAUCUA_DICE_RESULT: Kết quả xúc xắc
        // - BAUCUA_ROOM_INFO: Cập nhật thông tin room
    }

    @Override
    public void update() {
        if (imgBG == null) {
            imgBG = loadImage(0);
            if (imgBG != null) {
                wBG = imgBG.getWidth();
                hBG = imgBG.getHeight();
                xBG = GameCanvas.w / 2 - wBG / 2;
                yBG = GameCanvas.h / 2 - hBG / 2;
            }
        }
        // Load background
        if (!checkLoadImage()) {
            loadImageGame();
            // Update loading animation frames
            this.fr1 = (this.fr1 + 1) % ChangScr.frame1.length;
            this.fr2 = (this.fr2 + 1) % ChangScr.frame2.length;
            this.fr3 = (this.fr3 + 1) % ChangScr.frame3.length;
            this.fr4 = (this.fr4 + 1) % ChangScr.frame4.length;
            this.fr5 = (this.fr5 + 1) % ChangScr.frame5.length;
            this.f1 = ChangScr.frame1[this.fr1];
            this.f2 = ChangScr.frame2[this.fr2];
            this.f3 = ChangScr.frame3[this.fr3];
            this.f4 = ChangScr.frame4[this.fr4];
            this.f5 = ChangScr.frame5[this.fr5];
            return;
        }

        // Cập nhật animation xúc xắc
        updateDiceAnimation();

        // Cập nhật game state và timing
        updateGameState();
        
        // Cập nhật money transfer animations
        updateMoneyTransferAnimations();
        
        // Cập nhật text fly animations
        updateTextFlyAnimations();

        // Cập nhật chat messages
        if (currentRoom != null) {
            for (int i = 0; i < currentRoom.players.size(); i++) {
                BauCuaPlayer player = (BauCuaPlayer) currentRoom.players.elementAt(i);
                player.updateChat();
                if (player.character != null) {
                    player.character.updateCharFrame();
                }
            }
        }
    }

    @Override
    public void paint(mGraphics g) {
        if (this.lastSCR != null) {
            this.lastSCR.paint(g);
        }
        GameCanvas.resetTrans(g);

        if (!checkLoadImage()) {
            paintLoading(g);
            return;
        }

        // Vẽ background
        g.drawImage(imgBG, xBG, yBG, 0, false);

        // Vẽ thông tin game (luôn hiển thị)
        paintGameInfo(g);

        // Chỉ vẽ game elements khi đã vào phòng
        if (currentRoom != null) {
            // Vẽ 6 con vật theo layout 3x2 (chỉ khi không phải trạng thái chờ)
            if (icons != null && currentRoom.gameState != BauCuaRoom.STATE_WAITING) {
                paintAnimalsGrid(g);
            }
            // Vẽ xúc xắc
            if (xucXac != null) {
                paintXucXac(g);
            }
            
            // Vẽ players
            paintPlayers(g);
            
            // Vẽ thông tin phòng (tiền cược, etc.)
            paintRoomInfo(g);
            
            // Vẽ money transfer animations (luôn ở trên cùng)
            paintMoneyTransferAnimations(g);
            
            // Vẽ text fly animations (luôn ở trên cùng)
            paintTextFlyAnimations(g);
        }

        // Vẽ UI controls
        paintUI(g);

    }

    private void paintPlayers(mGraphics g) {
        if (currentRoom == null) return;

        // Sử dụng logic vị trí chính xác như code cũ
        for (int i = 0; i < currentRoom.players.size() && i < 5; i++) {
            BauCuaPlayer player = (BauCuaPlayer) currentRoom.players.elementAt(i);
            if (player.character != null) {
                String nameText = player.character.name;
                if (player.isRoomOwner) {
                    nameText += "(Chủ)";
                }
                if (player.isReady) {
                    nameText += " [Sẵn sàng]";
                }

                if (i == 0) {
                    // Player 0 (Chủ) - vị trí giữa dưới
                    int nameX = GameCanvas.w / 2;
                    int nameY = GameCanvas.h - player.character.getHeight() / 2 - player.character.getHeight()+10;

                    mFont.tahoma_7b_white.drawString(g, nameText, nameX, nameY, 2, false);
                    mFont.tahoma_7b_yellow.drawString(g, Res.getDotNumber(player.character.charXu) + " Xu", GameCanvas.w / 2, GameCanvas.h - player.character.getHeight() / 3+10, 2, false);
                    player.character.paint(g, GameCanvas.w / 2, GameCanvas.h - player.character.getHeight() / 3+10, 0);

                    // Vẽ chat messages
                    if (player.shouldDisplayChat()) {
                        paintChatBubbles(g, GameCanvas.w / 2, GameCanvas.h - player.character.getHeight() / 3 - 50+10, player.getActiveChatMessages(), 0xFFFFFF);
                    }


                } else if (i == 1) {
                    // Player 1 - vị trí phải dưới
                    int nameX = GameCanvas.w - player.character.getWidth();
                    int nameY = GameCanvas.h - player.character.getHeight() * 2 - 10;
                    
                    // Vẽ ô vuông màu bên trái tên
                    paintPlayerColorSquare(g, nameX - mFont.tahoma_7b_white.getWidth(nameText) / 2 - 12, nameY, i);
                    
                    mFont.tahoma_7b_white.drawString(g, nameText, nameX, nameY, 2, false);
                    mFont.tahoma_7b_yellow.drawString(g, Res.getDotNumber(player.character.charXu) + " Xu", GameCanvas.w - player.character.getWidth(), GameCanvas.h - player.character.getHeight(), 2, false);
                    player.character.paint(g, GameCanvas.w - player.character.getWidth(), GameCanvas.h - player.character.getHeight(), 0);

                    // Vẽ chat messages
                    if (player.shouldDisplayChat()) {
                        paintChatBubbles(g, GameCanvas.w - player.character.getWidth(), GameCanvas.h - player.character.getHeight() - 50, player.getActiveChatMessages(), 0xFFFFFF);
                    }


                } else if (i == 2) {
                    // Player 2 - vị trí phải trên
                    int nameX = GameCanvas.w - player.character.getWidth();
                    int nameY = 0 + player.character.getHeight() - 10;
                    
                    // Vẽ ô vuông màu bên trái tên
                    paintPlayerColorSquare(g, nameX - mFont.tahoma_7b_white.getWidth(nameText) / 2 - 12, nameY, i);
                    
                    mFont.tahoma_7b_white.drawString(g, nameText, nameX, nameY, 2, false);
                    mFont.tahoma_7b_yellow.drawString(g, Res.getDotNumber(player.character.charXu) + " Xu", GameCanvas.w - player.character.getWidth(), 0 + player.character.getHeight() * 2, 2, false);
                    player.character.paint(g, GameCanvas.w - player.character.getWidth(), 0 + player.character.getHeight() * 2, 0);

                    // Vẽ chat messages
                    if (player.shouldDisplayChat()) {
                        paintChatBubbles(g, GameCanvas.w - player.character.getWidth(), 0 + player.character.getHeight() * 2 - 50, player.getActiveChatMessages(), 0xFFFFFF);
                    }

                } else if (i == 3) {
                    // Player 3 - vị trí trái dưới
                    int nameX = 0 + player.character.getWidth();
                    int nameY = GameCanvas.h - player.character.getHeight() * 2 - 10;
                    
                    // Vẽ ô vuông màu bên trái tên
                    paintPlayerColorSquare(g, nameX - mFont.tahoma_7b_white.getWidth(nameText) / 2 - 12, nameY, i);
                    
                    mFont.tahoma_7b_white.drawString(g, nameText, nameX, nameY, 2, false);
                    mFont.tahoma_7b_yellow.drawString(g, Res.getDotNumber(player.character.charXu) + " Xu", 0 + player.character.getWidth(), GameCanvas.h - player.character.getHeight(), 2, false);
                    player.character.paint(g, 0 + player.character.getWidth(), GameCanvas.h - player.character.getHeight(), 0);

                    // Vẽ chat messages
                    if (player.shouldDisplayChat()) {
                        paintChatBubbles(g, 0 + player.character.getWidth(), GameCanvas.h - player.character.getHeight() - 50, player.getActiveChatMessages(), 0xFFFFFF);
                    }


                } else if (i == 4) {
                    // Player 4 - vị trí trái trên
                    int nameX = 0 + player.character.getWidth();
                    int nameY = 0 + player.character.getHeight() - 10;
                    
                    // Vẽ ô vuông màu bên trái tên
                    paintPlayerColorSquare(g, nameX - mFont.tahoma_7b_white.getWidth(nameText) / 2 - 12, nameY, i);
                    
                    mFont.tahoma_7b_white.drawString(g, nameText, nameX, nameY, 2, false);
                    mFont.tahoma_7b_yellow.drawString(g, Res.getDotNumber(player.character.charXu) + " Xu", 0 + player.character.getWidth(), 0 + player.character.getHeight() * 2, 2, false);
                    player.character.paint(g, 0 + player.character.getWidth(), 0 + player.character.getHeight() * 2, 0);

                    // Vẽ chat messages
                    if (player.shouldDisplayChat()) {
                        paintChatBubbles(g, 0 + player.character.getWidth(), 0 + player.character.getHeight() * 2 - 50, player.getActiveChatMessages(), 0xFFFFFF);
                    }
                }
            }
        }
    }

    private void paintChatBubbles(mGraphics g, int x, int y, Vector<ChatMessage> chatMessages, int textColor) {
        if (chatMessages == null || chatMessages.size() == 0) return;

        // Chỉ hiển thị tin nhắn đầu tiên (tin nhắn hiện tại)
        ChatMessage chatMsg = chatMessages.elementAt(0);
        if (chatMsg == null || chatMsg.message == null || chatMsg.message.length() == 0) return;
        
        int bubbleWidth = mFont.tahoma_7b_white.getWidth(chatMsg.message) + 10;
        int bubbleHeight = 20;

        // Vẽ bubble background
        g.setColor(0x000000, 0.7f);
        g.fillRect(x - bubbleWidth / 2, y - bubbleHeight / 2, bubbleWidth, bubbleHeight, false);

        // Vẽ border
        g.setColor(textColor);
        g.drawRect(x - bubbleWidth / 2, y - bubbleHeight / 2, bubbleWidth, bubbleHeight, false);

        // Vẽ text
        mFont.tahoma_7b_white.drawString(g, chatMsg.message, x, y - 5, 2, false);
    }
    
    // Giữ lại phương thức cũ để backward compatibility (deprecated)
    private void paintChatBubble(mGraphics g, int x, int y, String message, int textColor) {
        if (message == null || message.length() == 0) return;

        int bubbleWidth = mFont.tahoma_7b_white.getWidth(message) + 10;
        int bubbleHeight = 20;

        // Vẽ bubble background
        g.setColor(0x000000, 0.7f); // Màu đen trong suốt
        g.fillRect(x - bubbleWidth / 2, y - bubbleHeight / 2, bubbleWidth, bubbleHeight, false);

        // Vẽ border với màu tương ứng
        g.setColor(textColor);
        g.drawRect(x - bubbleWidth / 2, y - bubbleHeight / 2, bubbleWidth, bubbleHeight, false);

        mFont.tahoma_7b_white.drawString(g, message, x, y - 5, 2, false);
    }

    /**
     * Vẽ ô vuông màu đại diện cho player bên cạnh tên
     */
    private void paintPlayerColorSquare(mGraphics g, int x, int y, int playerColor) {
        int index = playerColor-1;
        if( index < 0 || index >= miniIcons.length) {
            return; // Không hợp lệ
        }
        Sprite miniIcon = miniIcons[index];
        if(miniIcon!= null){
            y-= miniIcon.getHeight()/3;
            x-= miniIcon.getWidth()/10;
            g.drawRegionSpire(miniIcon, 0, 0, (int) miniIcon.getWidth(), (int) miniIcon.getHeight(), 0, x, y, 0);
        }
    }

    /**
     * Vẽ 6 con vật theo layout 3x2 (3 hàng trên, 3 hàng dưới)
     */
    private void paintAnimalsGrid(mGraphics g) {
        // Tính toán vị trí bắt đầu để căn giữa
        int totalWidth = (ANIMALS_PER_ROW - 1) * ANIMAL_SPACING_X;
        int startX = GameCanvas.w / 2 - totalWidth / 2;
        int startY = GameCanvas.h / 2 - ANIMAL_SPACING_Y / 2;

        for (int i = 0; i < icons.length; i++) {
            // Tính toán hàng và cột
            int row = i / ANIMALS_PER_ROW;    // Hàng (0 = trên, 1 = dưới)
            int col = i % ANIMALS_PER_ROW;    // Cột (0, 1, 2)

            // Tính toán vị trí x, y
            int x = startX + col * ANIMAL_SPACING_X;
            int y = startY + row * ANIMAL_SPACING_Y;

            if (icons[i] != null) {
                g.drawImage(icons[i], x, y, 3, false);
            } else {
                icons[i] = loadImage(i + 1);
            }
        }
    }


    private void paintXucXac(mGraphics g) {
        // Chỉ hiển thị xúc xắc khi game đang chạy (BETTING, ROLLING, hoặc RESULT)
        if (currentRoom == null || currentRoom.gameState == BauCuaRoom.STATE_WAITING) {
            return;
        }

        if (isDiceRolling && currentRoom.gameState == BauCuaRoom.STATE_ROLLING) {
            // Vẽ nền đen toàn màn hình khi đang lăn xúc xắc
            g.setColor(0x000000, 0.8f); // Nền đen trong suốt 80%
            g.fillRect(0, 0, GameCanvas.w, GameCanvas.h, false);
            
            // Tính toán vị trí trung tâm cho 3 xúc xắc
            int spacing = 140;  // Khoảng cách giữa các xúc xắc
            int totalWidth = (3 - 1) * spacing;
            int xStart = GameCanvas.w / 2 - totalWidth / 2;
            int yCenter = GameCanvas.h / 2; // Vị trí trung tâm màn hình

            // Đang lăn - vẽ với hiệu ứng xoay mượt ở trung tâm
            int flipValue = getFlipFromAngle(diceRotationAngle[0]); // Sử dụng góc xoay của xúc xắc đầu tiên

            for (int i = 0; i < 3; i++) {
                // Đảm bảo texture đã load
                if (xucXac[currentDiceFrames[i]] == null) {
                    xucXac[currentDiceFrames[i]] = loadImage(currentDiceFrames[i] + 7);
                }

                // Vẽ nếu texture không null
                if (xucXac[currentDiceFrames[i]] != null) {
                    int diceX = xStart + i * spacing;
                    int diceY = yCenter;

                    // Vẽ với flip effect ở trung tâm
                    g.drawImage(xucXac[currentDiceFrames[i]], diceX, diceY, 3, false, flipValue);
                }
            }

        } else if (currentRoom.gameState == BauCuaRoom.STATE_RESULT) {
            // Vẽ nền đen toàn màn hình khi hiển thị kết quả
            g.setColor(0x000000, 0.8f); // Nền đen trong suốt 80%
            g.fillRect(0, 0, GameCanvas.w, GameCanvas.h, false);
            
            // Tính toán vị trí trung tâm cho 3 xúc xắc
            int spacing = 140;  // Khoảng cách giữa các xúc xắc
            int totalWidth = (3 - 1) * spacing;
            int xStart = GameCanvas.w / 2 - totalWidth / 2;
            int yCenter = GameCanvas.h / 2; // Vị trí trung tâm màn hình
            
            // Hiển thị kết quả - vẽ ở trung tâm với kích thước lớn
            for (int i = 0; i < 3; i++) {
                // Đảm bảo texture đã load
                if (xucXac[finalDiceResults[i]] == null) {
                    xucXac[finalDiceResults[i]] = loadImage(finalDiceResults[i] + 7);
                }

                // Vẽ nếu texture không null
                if (xucXac[finalDiceResults[i]] != null) {
                    int diceX = xStart + i * spacing;
                    int diceY = yCenter;
                    
                    g.drawImage(xucXac[finalDiceResults[i]], diceX, diceY, 3, false, 0);
                }
            }

        }
        // Trong trạng thái BETTING: không hiển thị xúc xắc
    }

    /**
     * Chuyển đổi góc xoay (0-360) thành flip value (0-7) để tạo hiệu ứng xoay mượt
     */
    private int getFlipFromAngle(float angle) {
        // Normalize angle to 0-360
        while (angle >= 360) angle -= 360;
        while (angle < 0) angle += 360;

        // Chia 360 độ thành 8 khu vực (mỗi khu vực 45 độ)
        // Mỗi khu vực tương ứng với một flip value
        if (angle < 45) return 0;        // 0-45: Normal
        else if (angle < 90) return 1;   // 45-90: Flip Y
        else if (angle < 135) return 4;  // 90-135: Rotate 90
        else if (angle < 180) return 5;  // 135-180: Rotate 90 + Flip
        else if (angle < 225) return 3;  // 180-225: Rotate 180
        else if (angle < 270) return 2;  // 225-270: Flip X
        else if (angle < 315) return 7;  // 270-315: Rotate 270
        else return 6;                   // 315-360: Rotate 270 + Flip
    }

    /**
     * Version nâng cao: Smooth rotation với interpolation
     */
    private int getFlipFromAngleSmooth(float angle) {
        // Normalize angle
        while (angle >= 360) angle -= 360;
        while (angle < 0) angle += 360;

        // Tạo hiệu ứng mượt hơn bằng cách thay đổi flip theo sine wave
        float normalizedAngle = angle / 360f; // 0.0 - 1.0

        // Sử dụng sine wave để tạo chuyển động mượt
        float sineValue = (float) Math.sin(normalizedAngle * Math.PI * 2);

        if (sineValue > 0.7f) return 4;      // Rotate 90
        else if (sineValue > 0.3f) return 1; // Flip Y
        else if (sineValue > -0.3f) return 0; // Normal
        else if (sineValue > -0.7f) return 2; // Flip X
        else return 3;                        // Rotate 180
    }

    /**
     * Cập nhật animation xúc xắc với hiệu ứng xoay mượt - SMOOTH VERSION
     */
    private void updateDiceAnimation() {
        // Chỉ chạy animation khi đang trong trạng thái ROLLING và đang lăn
        if (!isDiceRolling || currentRoom == null || currentRoom.gameState != BauCuaRoom.STATE_ROLLING) {
            return;
        }

        long currentTime = System.currentTimeMillis();
        // Tính deltaTime cho smooth rotation
        float deltaTime = (currentTime - lastRotationUpdate) / 1.0f; // Convert to seconds
        lastRotationUpdate = currentTime;

        // Cập nhật góc xoay cho từng xúc xắc
        for (int i = 0; i < DICE_COUNT; i++) {
            // Cập nhật góc xoay dựa trên tốc độ và deltaTime
            diceRotationAngle[i] += diceRotationSpeed[i] * deltaTime;

            // Normalize angle (giữ trong khoảng 0-360)
            if (diceRotationAngle[i] >= 360) {
                diceRotationAngle[i] -= 360;
            }
        }

        // Cập nhật frame animation
        if (currentTime - lastDiceFrameTime >= DICE_ANIMATION_SPEED) {
            lastDiceFrameTime = currentTime;

            // Thay đổi frame ngẫu nhiên cho tất cả xúc xắc CÙNG LÚC
            for (int i = 0; i < DICE_COUNT; i++) {
                currentDiceFrames[i] = Utils.random(6);
            }
        }
    }

    /**
     * Method bắt đầu lăn xúc xắc với hiệu ứng xoay mượt
     */
    public void startDiceRoll() {
        isDiceRolling = true;
        diceRollStartTime = System.currentTimeMillis();
        lastRotationUpdate = diceRollStartTime;
        lastDiceFrameTime = 0;

        // Khởi tạo animation frames ngẫu nhiên
        for (int i = 0; i < DICE_COUNT; i++) {
            currentDiceFrames[i] = Utils.random(6);

            // Khởi tạo hiệu ứng xoay mượt
            diceRotationAngle[i] = Utils.random(360); // Góc bắt đầu ngẫu nhiên
            diceRotationSpeed[i] = 180f + Utils.random(360); // 180-540 độ/giây
        }

        // Kết quả cuối sẽ được set từ server hoặc timeout
        System.out.println("Bắt đầu lăn xúc xắc - chờ kết quả từ server...");
    }

    /**
     * Vẽ thông tin phòng (tiền cược mặc định, etc.)
     */
    private void paintRoomInfo(mGraphics g) {
        if (currentRoom == null||currentRoom.gameState != BauCuaRoom.STATE_WAITING) return;
        
        // Hiển thị tiền cược mặc định của phòng
        String betInfo = "Tiền cược: " + Res.getDotNumber(currentRoom.defaultBetAmount) + " xu";
        mFont.tahoma_7b_white.drawString(g, betInfo, GameCanvas.w/2, GameCanvas.h/2, 2, false);
        
        // Hiển thị ID phòng
        String roomInfo = "Phòng: " + currentRoom.roomId;
        mFont.tahoma_7b_white.drawString(g, roomInfo, GameCanvas.w/2, GameCanvas.h/2-20, 2, false);
    
    }

    /**
     * Vẽ thông tin game (tiền hiện tại, tổng cược, etc.)
     */
    private void paintGameInfo(mGraphics g) {
        // Vẽ trạng thái phòng
        String stateText = "";
        
        if (currentRoom == null) {
            stateText = "Đang chờ server gửi thông tin phòng...";
        } else {
            switch (currentRoom.gameState) {
                case BauCuaRoom.STATE_WAITING:
                    stateText = "Người chơi (" + currentRoom.players.size() + "/" + BauCuaRoom.MAX_PLAYERS + ")";
                    break;
                case BauCuaRoom.STATE_BETTING:
                    long timeLeft = currentRoom.getTimeRemaining() / 1000;
                    stateText = "Đặt cược: " + timeLeft + "s";
                    break;
                case BauCuaRoom.STATE_ROLLING:
                    stateText = "";
                    break;
                case BauCuaRoom.STATE_RESULT:
                    stateText = "Kết thúc ván đấu";
                    break;
            }
        }

        mFont.tahoma_7b_yellow.drawString(g, stateText, GameCanvas.w / 2, 0 + 20, 2, false);
    }

    /**
     * Vẽ UI controls (mCommand buttons)
     */
    private void paintUI(mGraphics g) {
        // Vẽ menu command (luôn hiện)
        if (menu != null) {
            menu.paint(g);
        }

        // Chỉ hiển thị các control khác khi đã vào phòng
        if (currentRoom == null) return;

        // Vẽ chat command (chỉ khi đã vào phòng)
        if (cmdChat != null) {
            cmdChat.paint(g);
        }

        // Vẽ ready/start command tùy theo trạng thái
        if (currentRoom.gameState == BauCuaRoom.STATE_WAITING && myPlayer != null) {
            if (myPlayer.isRoomOwner) {
                // Chủ phòng thấy nút "Bắt đầu"
                if (startGame != null) {
                    startGame.paint(g);
                }
            } else {
                // Player thường thấy nút "Sẵn sàng/Hủy sẵn sàng"
                if (cmdReady != null) {
                    cmdReady.caption = myPlayer.isReady ? "Hủy sẵn sàng" : "Sẵn sàng";
                    cmdReady.paint(g);
                }
            }
        }

        // Vẽ thông tin cược trên các con vật
        if (myPlayer != null) {
           if (currentRoom.gameState == BauCuaRoom.STATE_BETTING) {
                // Khi đang betting, hiển thị xẻng của tất cả players
                paintAllPlayerCoins(g);
            }
        }
        
        // Vẽ nút đặt cược khi có preview bets (chỉ player thường, không phải chủ phòng)
        if (hasPreviewBets && currentRoom.gameState == BauCuaRoom.STATE_BETTING && myPlayer != null && !myPlayer.isRoomOwner && cmdPlaceBet != null) {
            cmdPlaceBet.paint(g);
        }
        
        // Vẽ nút hủy khi có preview bets (chỉ player thường, không phải chủ phòng)
        if (hasPreviewBets && currentRoom.gameState == BauCuaRoom.STATE_BETTING && myPlayer != null && !myPlayer.isRoomOwner && cmdCancelBet != null) {
            cmdCancelBet.paint(g);
        }
        
        // Vẽ preview bets (chỉ player thường, không phải chủ phòng)
        if (hasPreviewBets && currentRoom.gameState == BauCuaRoom.STATE_BETTING && myPlayer != null && !myPlayer.isRoomOwner) {
            paintPreviewBets(g);
        }
    }

    
        /**
     * Vẽ preview bets với hình xẻng
     */
    private void paintPreviewBets(mGraphics g) {
        if (!hasPreviewBets || myPlayer == null || currentRoom == null) return;
 
        int totalWidth = (ANIMALS_PER_ROW - 1) * ANIMAL_SPACING_X;
        int startX = GameCanvas.w / 2 - totalWidth / 2;
        int startY = GameCanvas.h / 2 - ANIMAL_SPACING_Y / 2;
        
        // Tìm vị trí của myPlayer trong room để chọn hình xẻng
        int myPlayerIndex = -1;
        for (int j = 0; j < currentRoom.players.size(); j++) {
            BauCuaPlayer player = (BauCuaPlayer) currentRoom.players.elementAt(j);
            if (player != null && player == myPlayer) {
                myPlayerIndex = j;
                break;
            }
        }
        
        for (int i = 0; i < 6; i++) {
            if (previewBets[i] > 0) {
                int row = i / ANIMALS_PER_ROW;
                int col = i % ANIMALS_PER_ROW;

                int animalX = startX + col * ANIMAL_SPACING_X;
                int animalY = startY + row * ANIMAL_SPACING_Y;

                long betAmount = currentRoom.defaultBetAmount;
                int numCoins = (int) (previewBets[i] / betAmount);

                if (numCoins > 0 && myPlayerIndex >= 0) {

                    // Vẽ hình xẻng preview cho myPlayer
                    int coinX = animalX - 15;
                    int coinY = animalY - 15;

                    // Sử dụng vị trí của myPlayer trong room để chọn hình xẻng
                    paintCoinImage(g, coinX, coinY, myPlayerIndex);
                    int h = 0;
                    if (coinImages[myPlayerIndex-1] != null) {
                        h = coinImages[myPlayerIndex-1].getHeight();
                    }

                    // Vẽ số lần đặt cược ở giữa hình xẻng với màu xanh lá (preview)
                    String countText = String.valueOf(numCoins);
                    mFont.tahoma_7b_white.drawString(g, countText, coinX, coinY-h/2+mFont.tahoma_7b_white.getHeight()-3, 2, false);
                    
                    // Vẽ khung xanh lá để chỉ ra đây là preview
                    g.setColor(0x00FF00);
                    g.drawRect(animalX - 30, animalY - 30, 60, 60, false);
                }
            }
        }
    }
    
    /**
     * Vẽ đồng xèng của tất cả players trên các con vật
     */
    private void paintAllPlayerCoins(mGraphics g) {
        if (currentRoom == null) return;
        
        // Tính toán vị trí của 6 con vật theo layout 3x2
        int totalWidth = (ANIMALS_PER_ROW - 1) * ANIMAL_SPACING_X;
        int startX = GameCanvas.w / 2 - totalWidth / 2;
        int startY = GameCanvas.h / 2 - ANIMAL_SPACING_Y / 2;

        for (int animalIndex = 0; animalIndex < 6; animalIndex++) {
            int row = animalIndex / ANIMALS_PER_ROW;
            int col = animalIndex % ANIMALS_PER_ROW;

            int animalX = startX + col * ANIMAL_SPACING_X;
            int animalY = startY + row * ANIMAL_SPACING_Y;
            
            // Vẽ coins của tất cả players cho con vật này
            paintCoinsForAnimal(g, animalX, animalY, animalIndex);
        }
    }
    
    /**
     * Vẽ đồng xèng của tất cả players cho một con vật cụ thể
     */
    private void paintCoinsForAnimal(mGraphics g, int animalX, int animalY, int animalIndex) {
        int displayIndex = 0; // Index để tính vị trí hiển thị (chỉ tính players có đặt cược)
        
        for (int i = 0; i < currentRoom.players.size(); i++) {
            BauCuaPlayer player = (BauCuaPlayer) currentRoom.players.elementAt(i);
            
            // Chỉ hiển thị xẻng cho players thường (không phải chủ phòng) và có đặt cược
            if (player != null && !player.isRoomOwner && player.bets[animalIndex] > 0) {
                // Tính số lần đặt cược từ bets array
                long betAmount = currentRoom.defaultBetAmount;
                int numCoins = (int) (player.bets[animalIndex] / betAmount);
                
                if (numCoins > 0) {
                    // Vẽ một hình xẻng đại diện cho player này
                    int coinX = animalX - 20 + (displayIndex % 2) * 15; // Xếp 2 cột
                    int coinY = animalY - 20 + (displayIndex / 2) * 15; // Nhiều hàng
                    
                    // Sử dụng vị trí player trong room để chọn hình xẻng
                    paintCoinImage(g, coinX, coinY, i);
                    int h = 0;
                    if (coinImages[i-1] != null) {
                        h = coinImages[i-1].getHeight();
                    }
                    // Vẽ số lần đặt cược ở giữa hình xẻng
                    String countText = String.valueOf(numCoins);
                    mFont.tahoma_7b_white.drawString(g, countText, coinX, coinY-h/2+mFont.tahoma_7b_white.getHeight()-3, 2, false);
                    
                    displayIndex++; // Tăng index hiển thị cho player tiếp theo
                }
            }
        }
    }
    
    /**
     * Vẽ hình xẻng theo player index
     */
    private void paintCoinImage(mGraphics g, int x, int y, int playerIndex) {
        // Đảm bảo không vượt quá số lượng hình xẻng có sẵn (4 hình)
        // Chủ phòng (vị trí 0) không đặt cược nên bỏ qua
        // Vị trí 1-4 sẽ dùng coinImages[0-3]
        int coinImageIndex = (playerIndex - 1) % coinImages.length;
        
        // Nếu là chủ phòng hoặc index không hợp lệ
        if (playerIndex == 0 || coinImageIndex < 0 || coinImageIndex >= coinImages.length) {
            // Fallback: vẽ hình vuông với màu player
            if (currentRoom != null && playerIndex < currentRoom.players.size()) {
                BauCuaPlayer player = (BauCuaPlayer) currentRoom.players.elementAt(playerIndex);
                if (player != null) {
                    paintCoin(g, x, y, player.playerColor);
                }
            }
            return;
        }
        
        if (coinImages[coinImageIndex] != null) {
            g.drawImage(coinImages[coinImageIndex], x, y, 3, false);
        } else {
            // Fallback: vẽ hình vuông với màu player
            if (currentRoom != null && playerIndex < currentRoom.players.size()) {
                BauCuaPlayer player = (BauCuaPlayer) currentRoom.players.elementAt(playerIndex);
                if (player != null) {
                    paintCoin(g, x, y, player.playerColor);
                }
            }
        }
    }
    
    /**
     * Vẽ một đồng xèng với màu cụ thể (fallback)
     */
    private void paintCoin(mGraphics g, int x, int y, int color) {
        // Vẽ đồng xèng hình vuông nhỏ
        g.setColor(color);
        g.fillRect(x - 3, y - 3, 6, 6, false);
        
        // Vẽ viền
        g.setColor(0x000000);
        g.drawRect(x - 3, y - 3, 6, 6, false);
        
        // Vẽ dấu chấm ở giữa
        g.setColor(0xFFFFFF);
        g.fillRect(x - 1, y - 1, 2, 2, false);
    }

    @Override
    public void paint(mGraphics g, int var2, int var3, int var4, Object var5) {

    }

    private void paintLoading(mGraphics g) {
        g.drawImage(ChangScr.loading1[this.f1], GameCanvas.w / 2, GameCanvas.h / 2 + mGraphics.getImageHeight(GameCanvas.getLogo()) / 2 + 10, 3, false);
        g.drawImage(ChangScr.loading2[this.f2], GameCanvas.w / 2, GameCanvas.h / 2 + mGraphics.getImageHeight(GameCanvas.getLogo()) / 2 + 10, 3, false);
        g.drawImage(ChangScr.loading3[this.f3], GameCanvas.w / 2, GameCanvas.h / 2 + mGraphics.getImageHeight(GameCanvas.getLogo()) / 2 + 10, 3, false);
        g.drawImage(ChangScr.loading4[this.f4], GameCanvas.w / 2, GameCanvas.h / 2 + mGraphics.getImageHeight(GameCanvas.getLogo()) / 2 + 10, 3, false);
        g.drawImage(ChangScr.loading5[this.f5], GameCanvas.w / 2, GameCanvas.h / 2 + mGraphics.getImageHeight(GameCanvas.getLogo()) / 2 + 10, 3, false);
    }

    // ========== CLEANUP METHODS ==========

    /**
     * Cleanup instance resources
     */
    public void cleanup() {
        // Stop dice animation
        isDiceRolling = false;

        // Clear room data
        if (currentRoom != null) {
            currentRoom.players.removeAllElements();
            currentRoom = null;
        }

        // Clear player references
        myPlayer = null;
        if (bauCuaPlayers != null) {
            bauCuaPlayers.removeAllElements();
        }

        // Clear commands
        menu = null;
        startGame = null;
        cmdReady = null;
        cmdChat = null;
        cmdPlaceBet = null;
        cmdCancelBet = null;
        
        // Clear preview bets
        clearPreviewBets();
        
        // Clear money transfer animations
        if (moneyTransfers != null) {
            moneyTransfers.removeAllElements();
        }
        
        // Clear text fly animations
        if (textAnimations != null) {
            textAnimations.removeAllElements();
        }

        System.out.println("BauCua instance cleaned up");
    }

    /**
     * Cleanup static resources (images)
     */
    public static void cleanupStaticResources() {
        // Clear static images
        imgBG = null;
        icons = null;
        xucXac = null;
        coinImages = null;

        System.out.println("BauCua static resources cleaned up");
    }

    /**
     * Reset game to initial state (keep resources)
     */
    public void resetToInitialState() {
        // Stop animations
        isDiceRolling = false;

        // Reset dice
        for (int i = 0; i < DICE_COUNT; i++) {
            finalDiceResults[i] = 0;
            currentDiceFrames[i] = 0;
            diceRotationAngle[i] = 0f;
            diceRotationSpeed[i] = 180f + Utils.random(360);
        }

        // Reset room state
        if (currentRoom != null) {
            currentRoom.gameState = BauCuaRoom.STATE_WAITING;
            currentRoom.stateStartTime = System.currentTimeMillis();
            currentRoom.resetAllPlayersReady();
            currentRoom.resetAllPlayersBets();
        }

        System.out.println("BauCua game reset to initial state");
    }
}