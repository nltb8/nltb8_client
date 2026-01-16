package mchien.code.network;

import mchien.code.main.GameCanvas;
import mchien.code.main.GameMidlet;
import mchien.code.model.IActionListener;
import mchien.code.screen.screen.FontTeam;
import mchien.code.screen.screen.GameScr;
import lib.Cout;
import lib.mGraphics;
import lib.mHashtable;
import lib.mSystem;
import lib2.IMessageHandler;
import lib2.Message;
import mchien.code.screen.screen.goinap.GoiNapScr;

/**
 * Lớp MessageHandler - Xử lý các message nhận từ server
 * 
 * Chức năng chính:
 * 1. Implement IMessageHandler để nhận message từ Session
 * 2. Phân loại và xử lý message theo command
 * 3. Xử lý các sự kiện kết nối (connect, disconnect, connection fail)
 * 4. Delegate xử lý message cụ thể cho các class chuyên biệt (GameCanvas.readMessenge)
 * 
 * Pattern: Singleton
 * 
 * @author NLTB8 Team
 */
public class MessageHandler extends Cmd_message implements IMessageHandler, IActionListener {
    // ==================== SINGLETON ====================
    protected static MessageHandler instance;
    
    // ==================== PROPERTIES ====================
    /**
     * Hashtable đếm số lượng message theo loại
     */
    public static mHashtable countmsg = new mHashtable();
    
    /**
     * ID của button hiện tại
     */
    public int idB;
    
    /**
     * ID của popup hiện tại
     */
    public int idPopup;
    
    /**
     * Mảng giá trị tạm thời
     */
    public short[] ar;

    /**
     * Lấy instance singleton của MessageHandler
     * 
     * @return Instance duy nhất của MessageHandler
     */
    public static MessageHandler gI() {
        if (instance == null) {
            instance = new MessageHandler();
        }
        return instance;
    }

    // ==================== CONNECTION EVENT HANDLERS ====================
    
    /**
     * Xử lý sự kiện kết nối thành công
     * Được gọi khi socket kết nối đến server thành công
     */
    public void onConnectOK() {
        GameCanvas.gameScr.onConnectOK();
    }

    /**
     * Xử lý sự kiện kết nối thất bại
     * Được gọi khi không thể kết nối đến server
     */
    public void onConnectionFail() {
        GameCanvas.gameScr.onConnectFail();
    }

    /**
     * Xử lý sự kiện mất kết nối
     * Được gọi khi kết nối bị ngắt sau khi đã kết nối thành công
     */
    public void onDisconnected() {
        GameCanvas.gameScr.onDisconnect("messagehandler");
    }

    // ==================== MESSAGE HANDLER ====================
    
    /**
     * Xử lý message nhận được từ server
     * 
     * Phân loại message theo command và delegate đến các handler tương ứng.
     * Tất cả các handler cụ thể được định nghĩa trong GameCanvas.readMessenge.
     * 
     * @param msg Message cần xử lý
     */
    public void onMessage(Message msg) {
        try {
            System.out.println("Read " + msg.command);
            
            switch (msg.command) {
                // ==================== MOUNT SYSTEM ====================
                case -80:
                    // Trang bị mount
                    GameCanvas.readMessenge.onMountWearing(msg);
                    break;
                    
                case -82:
                    // Thông tin mount
                    GameCanvas.readMessenge.onMountInfo(msg);
                    break;
                
                // ==================== SYSTEM ====================
                case -126:
                    // Bảo trì
                    GameCanvas.readMessenge.onBaoTri(msg);
                    break;
                    
                case -125:
                    // Data nhân vật
                    GameCanvas.readMessenge.onCharData(msg);
                    break;
                    
                case -124:
                    // Shop tóc
                    GameCanvas.readMessenge.onShopHair(msg);
                    break;
                
                // ==================== HORSE/ANIMAL ====================
                case -100:
                    // Thông tin ngựa mới
                    GameCanvas.readMessenge.readHorseNew(msg);
                    break;
                
                // ==================== IN-APP PURCHASE ====================
                case -75:
                    // Google/Apple purchase
                    mSystem.handleMessage(msg);
                    break;
                
                // ==================== MAP & GRAPHICS ====================
                case -74:
                    // Image big map
                    GameCanvas.readMessenge.onImgBigmap(msg);
                    break;
                
                // ==================== CHAT ====================
                case -73:
                    // Chat
                    GameCanvas.readMessenge.onChat(msg);
                    break;
                
                // ==================== TUTORIAL ====================
                case -72:
                    // Hướng dẫn
                    GameCanvas.readMessenge.huongdan(msg);
                    break;
                
                // ==================== EFFECTS ====================
                case -71:
                    // Dynamic effect
                    GameCanvas.readMessenge.onDynamicEffect(msg);
                    break;
                
                // ==================== DATA LOADING ====================
                case -57:
                    // Load tất cả image
                    GameCanvas.readMessenge.onLoadAllImage(msg);
                    break;
                    
                case -54:
                    // Cập nhật tất cả image
                    GameCanvas.readMessenge.onUpdateAllImage(msg);
                    break;
                
                // ==================== SERVER DATA ====================
                case -52:
                    // Byte data từ server
                    GameCanvas.readMessenge.onByteDataServer(msg);
                    break;
                    
                case -51:
                    // Image từ server
                    GameCanvas.readMessenge.onImageFromServer(msg);
                    return;
                    
                case -50:
                    // Set info NPC từ server
                    GameCanvas.readMessenge.onsetInfoNPC_Server(msg);
                    break;
                
                // ==================== WEATHER ====================
                case -39:
                    // Thời tiết
                    GameCanvas.readMessenge.onWeather(msg);
                    break;
                
                // ==================== BẦU CUA GAME ====================
                case 110: // BAUCUA_CREATE_ROOM
                case 111: // BAUCUA_JOIN_ROOM  
                case 112: // BAUCUA_LEAVE_ROOM
                case 113: // BAUCUA_READY
                case 114: // BAUCUA_PLACE_BET
                    // Các message này chỉ gửi lên server, không cần xử lý response
                    break;
                    
                case 115: // BAUCUA_DICE_RESULT
                    // Kết quả xúc xắc Bầu Cua
                    GameCanvas.readMessenge.onBauCuaDiceResult(msg);
                    break;
                    
                case 116: // BAUCUA_ROOM_INFO
                    // Thông tin phòng Bầu Cua
                    GameCanvas.readMessenge.onBauCuaRoomInfo(msg);
                    break;
                    
                case 117: // BAUCUA_CHAT
                    // Chat trong phòng Bầu Cua
                    GameCanvas.readMessenge.onBauCuaChat(msg);
                    break;

                case 119: // BAUCUA_KICK_PLAYER
                    // Bị đuổi khỏi phòng
                    GameCanvas.readMessenge.kickPlayer();
                    break;
                    
                case 120: // BAUCUA_SET_PASSWORD
                    break;
                    
                case 121: // BAUCUA_SET_DEFAULT_BET
                    // Cập nhật tiền cược mặc định
                    GameCanvas.readMessenge.updateBetBauCau(msg);
                    break;
                    
                case 122: // BAUCUA_START_GAME
                    // Các message này chỉ gửi lên server, response sẽ qua ROOM_INFO hoặc GAME_STATE
                    break;
                    
                case 124:
                    // Kết quả vòng quay
                    GameCanvas.readMessenge.onVongQuayResult(msg);
                    break;
                
                // ==================== UI ELEMENTS ====================
                case -32:
                    // Popup từ server
                    GameCanvas.readMessenge.onPopupServer(msg);
                    break;
                    
                case -31:
                    // Text box từ server
                    GameCanvas.readMessenge.TexBox_Server(msg);
                    break;
                    
                case -30:
                    // Menu option
                    GameCanvas.readMessenge.onMenu_Option(msg);
                    break;
                    
                case -22:
                case 14:
                default:
                    // Không xử lý
                    break;
                
                // ==================== PET ====================
                case -21:
                    // Thông tin pet
                    GameCanvas.readMessenge.onPetinfo(msg);
                    break;
                
                // ==================== AUTHENTICATION ====================
                case 1:
                    // Đăng nhập thành công
                    GameCanvas.readMessenge.onLoginSuccess(msg);
                    if (mSystem.isAndroidStore() || mSystem.isIosAppStore()) {
                        mSystem.handleAllMessage();
                    }
                    break;
                    
                case 2:
                    // Đăng xuất
                    GameCanvas.readMessenge.onLogOut(msg);
                    break;
                
                // ==================== CHARACTER INFO ====================
                case 3:
                    // Thông tin nhân vật chính
                    GameCanvas.readMessenge.onMainCharInfo(msg);
                    break;
                    
                case 5:
                    // Thông tin nhân vật
                    GameCanvas.readMessenge.onCharInfo(msg);
                    break;
                    
                case 13:
                    // Danh sách nhân vật
                    GameCanvas.readMessenge.ongetCharList(msg);
                    break;
                    
                case 15:
                    // Trang bị của nhân vật
                    GameCanvas.readMessenge.onCharWearing(msg);
                    break;
                    
                case 16:
                    // Hành trang
                    GameCanvas.readMessenge.onCharInventory(msg);
                    break;
                
                // ==================== MOVEMENT ====================
                case 4:
                    // Di chuyển
                    GameCanvas.readMessenge.onMove(msg);
                    break;
                    
                case 8:
                    // Nhân vật rời khỏi
                    GameCanvas.readMessenge.charOut(msg);
                    break;
                
                // ==================== COMBAT ====================
                case 6:
                    // Tấn công player
                    GameCanvas.readMessenge.onAttackPlayer(msg);
                    break;
                    
                case 9:
                    // Tấn công monster
                    GameCanvas.readMessenge.onAttackMonster(msg);
                    break;
                    
                case 10:
                    // Monster tấn công player
                    GameCanvas.readMessenge.onMonsterAttackPlayer(msg);
                    break;
                    
                case 17:
                    // Monster chết
                    GameCanvas.readMessenge.onMosterDie_(msg);
                    break;
                    
                case 90:
                    // Actor chết
                    GameCanvas.readMessenge.onActorDie(msg);
                    break;
                    
                case 89:
                    // Bắt đầu buff
                    GameCanvas.readMessenge.startBuff(msg);
                    break;
                    
                case 69:
                    // Pet tấn công
                    GameCanvas.readMessenge.onPetattack(msg);
                    break;
                    
                case 71:
                    // Pet buff
                    GameCanvas.readMessenge.onPetBuff(msg);
                    break;
                
                // ==================== MONSTER INFO ====================
                case 7:
                    // Thông tin monster
                    GameCanvas.readMessenge.onMonsterInfo(msg);
                    break;
                    
                case 26:
                    // Template monster
                    GameCanvas.readMessenge.onMonsterTemplate(msg);
                    break;
                
                // ==================== ITEMS ====================
                case 18:
                    // Nhặt item
                    GameCanvas.readMessenge.onGetItem(msg);
                    break;
                    
                case 25:
                    // Template item
                    GameCanvas.readMessenge.onItemTemplate(msg);
                    if (mSystem.isAndroidStore() || mSystem.isIosAppStore()) {
                        mSystem.handleAllMessage();
                    }
                    break;
                    
                case 63:
                    // Cho item
                    GameCanvas.readMessenge.onGiveItem(msg);
                    break;
                    
                case 67:
                    // Nâng cấp item
                    GameCanvas.readMessenge.onUpgradeItem(msg);
                    break;
                    
                case 68:
                    // Tạo item
                    GameCanvas.readMessenge.onCreateItem(msg);
                    break;
                    
                case 70:
                    // Ghép phi phong
                    GameCanvas.readMessenge.onGhepPhiPhong(msg);
                    break;
                
                // ==================== NPC ====================
                case 23:
                    // Thông tin NPC
                    GameCanvas.readMessenge.ongetInfoNPC(msg);
                    break;
                    
                case 55:
                    // Danh sách NPC bán hàng
                    GameCanvas.readMessenge.onListNPCsell(msg);
                    break;
                
                // ==================== CHAT ====================
                case 27:
                    // Chat
                    GameCanvas.readMessenge.chat(msg);
                    break;
                
                // ==================== MAP ====================
                case 12:
                    // Map
                    GameCanvas.readMessenge.onMap(msg);
                    break;
                    
                case 47:
                    // Clear load map
                    GameCanvas.readMessenge.clearloadMap(msg);
                    break;
                
                // ==================== CHARACTER STATS ====================
                case 30:
                    // Set XP
                    GameCanvas.readMessenge.Set_XP(msg);
                    break;
                    
                case 33:
                    // Level up
                    GameCanvas.readMessenge.Level_Up(msg);
                    break;
                    
                case 35:
                    // Thông tin skill
                    GameCanvas.readMessenge.onSkillInfo(msg);
                    break;
                    
                case 36:
                    // Cập nhật HP/MP
                    GameCanvas.readMessenge.onUpdateHP_MP(msg);
                    break;
                
                // ==================== SERVER MESSAGES ====================
                case 37:
                    // Alert từ server
                    GameCanvas.readMessenge.onAlertPopUp(msg);
                    break;
                    
                case 11:
                    // Tài khoản mới
                    GameCanvas.readMessenge.onNEwAccount(msg);
                    break;
                
                // ==================== PARTY ====================
                case 48:
                    // Danh sách party
                    GameCanvas.readMessenge.onListParty(msg);
                    break;
                
                // ==================== PK ====================
                case 49:
                    // Đổi chế độ PK
                    GameCanvas.readMessenge.changPK(msg);
                    break;
                
                // ==================== LIST INFO ====================
                case 50:
                    // Thông tin danh sách
                    GameCanvas.readMessenge.List_info(msg);
                    break;
                
                // ==================== QUEST ====================
                case 52:
                    // Nhận nhiệm vụ
                    GameCanvas.readMessenge.receiveQuest(msg);
                    break;
                    
                case 53:
                    // Thông báo
                    GameCanvas.readMessenge.addNotify(msg);
                    break;
                
                // ==================== ARENA ====================
                case 54:
                    // Thách đấu
                    GameCanvas.readMessenge.ThacDau(msg);
                    break;
                
                // ==================== COUNTDOWN ====================
                case 60:
                    // Đếm ngược
                    GameCanvas.readMessenge.onCountDown(msg);
                    break;
                
                // ==================== BOX ====================
                case 61:
                    // Mở hộp
                    GameCanvas.readMessenge.opentBox(msg);
                    break;
                
                // ==================== SERVER INFO ====================
                case 62:
                    // Thông tin từ server
                    GameCanvas.readMessenge.onInfofromserver(msg);
                    break;
                
                // ==================== CLAN ====================
                case 66:
                    // Mời vào clan
                    GameCanvas.readMessenge.inviteClan(msg);
                    break;
                
                // ==================== FRIEND ====================
                case 101:
                    // Thêm bạn
                    GameCanvas.readMessenge.Add_Friend(msg);
                    break;
                
                // ==================== TRADE ====================
                case -77:
                    // Giao dịch
                    GameCanvas.readMessenge.readTrade(msg);
                    break;
                
                // ==================== PAYMENT ====================
                case -78:
                    // Gói nạp
                    GoiNapScr.gI().read(msg);
                    break;
                
                // ==================== CONFIG ====================
                case 104:
                    try {
                        GameScr.loadConfig(msg.reader().readByte());
                        byte var2 = msg.reader().readByte();
                    } catch (Exception var3) {
                        Cout.println("LOI CHO NAY NE " + var3.toString());
                    }
                    break;
                
                // ==================== RANKING ====================
                case 105:
                    // Bảng xếp hạng damage
                    GameCanvas.readMessenge.rankingDamage(msg);
                    break;
            }
        } catch (Exception var4) {
            var4.printStackTrace();
            System.out.println(msg.command + " tai cmd nay");
            Cout.println(msg.command + " tai cmd nay");
        }
    }

    // ==================== ACTION LISTENER ====================
    
    /**
     * Lấy index class từ item ID
     * 
     * @param itemID ID của item
     * @return Index của class
     */
    public int getIndexClassFromItemID(int itemID) {
        if (itemID >= 79 && itemID <= 113) {
            return (itemID - 79) / 7;
        } else if (itemID >= 174 && itemID <= 213) {
            return (itemID - 174) / 8;
        } else {
            return itemID >= 214 && itemID <= 263 ? (itemID - 214) / 10 : 0;
        }
    }

    /**
     * Xử lý action khi user tương tác với UI
     * 
     * @param idAction ID của action
     * @param p Tham số đi kèm
     */
    public void perform(int idAction, Object p) {
        String inFo;
        switch (idAction) {
            case 0:
                short a = Short.parseShort((String) p);
                GameCanvas.endDlg();
                break;
            case 1:
                short a1 = Short.parseShort((String) p);
                GameCanvas.endDlg();
                break;
            case 2:
                GameCanvas.endDlg();
                break;
            case 3:
                GameCanvas.endDlg();
                break;
            case 4:
                inFo = (String) p;

                GameMidlet var10000;
                try {
                    var10000 = GameMidlet.instance;
                    GameMidlet.platformRequest(inFo);
                    Thread.sleep(500L);
                } catch (Exception var8) {
                }

                var10000 = GameMidlet.instance;
                GameMidlet.notifyDestroyed();
                break;
            case 5:
                GameCanvas.endDlg();
                break;
            case 6:
                inFo = (String) p;
                String[] array = FontTeam.splitString(inFo, ":");
                GameCanvas.startOKDlg("Tài khoản " + array[0] + " với M.Khẩu: " + array[1] + " đã được đăng ký.Vui lòng thoát game và chờ trong ít phút.");
                break;
            case 7:
                GameCanvas.startOKDlg("Có lỗi khi gửi tin nhắn đăng ký. Xin hãy thử lại");
            case 8:
            case 9:
        }
    }

    /**
     * Vẽ UI element
     * 
     * @param g Graphics object
     * @param idAction ID của action
     * @param x Tọa độ x
     * @param y Tọa độ y
     * @param paint Object cần vẽ
     */
    public void paint(mGraphics g, int idAction, int x, int y, Object paint) {
    }
}
