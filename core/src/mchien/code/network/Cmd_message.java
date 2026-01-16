package mchien.code.network;

/**
 * Lớp Cmd_message - Định nghĩa các mã lệnh (command) cho message client-server
 * 
 * Mỗi message gửi/nhận đều có 1 byte command để định danh loại message.
 * Command được chia thành các nhóm theo chức năng để dễ quản lý.
 * 
 * Quy ước:
 * - Command > 0: Các message thông thường (đăng nhập, di chuyển, chiến đấu,...)
 * - Command < 0: Các message đặc biệt/mở rộng
 * - Command 127: Message có kích thước lớn (4 bytes size)
 * 
 * @author NLTB8 Team
 */
public class Cmd_message {
    
    // ==================== AUTHENTICATION & SESSION (1-14) ====================
    /**
     * Đăng nhập vào game
     */
    public static final byte LOGIN = 1;
    
    /**
     * Đăng xuất khỏi game
     */
    public static final byte LOGOUT = 2;
    
    /**
     * Thông tin nhân vật chính
     */
    public static final byte INFO_MAIN_CHAR = 3;
    
    /**
     * Tạo tài khoản mới
     */
    public static final byte NEW_ACOUNT = 11;
    
    /**
     * Chuyển map
     */
    public static final byte CHANGE_MAP = 12;
    
    /**
     * Hoàn thành intro/hướng dẫn
     */
    public static final byte FINISH_INTRO = 13;
    
    /**
     * Tạo nhân vật mới
     */
    public static final byte CREATE_CHAR = 14;
    
    /**
     * Đăng ký tài khoản
     */
    public static final byte REQUEST_REGISTER = 39;

    // ==================== CHARACTER MOVEMENT & INFO (4-8, 15-17) ====================
    /**
     * Di chuyển nhân vật
     */
    public static final byte MOVE_CHAR = 4;
    
    /**
     * Vị trí của actor
     */
    public static final byte INFO_ACTOR_POS = 4;
    
    /**
     * Thông tin nhân vật
     */
    public static final byte CHAR_INFO = 5;
    
    /**
     * Nhân vật rời khỏi màn hình
     */
    public static final byte CHAR_OUT = 8;
    
    /**
     * Trang bị của nhân vật
     */
    public static final byte CHAR_WEARING = 15;
    
    /**
     * Hành trang của nhân vật
     */
    public static final byte CHAR_INVENTORY = 16;

    // ==================== COMBAT SYSTEM (6, 9-10, 17, 83, 89-90) ====================
    /**
     * Player tấn công player
     */
    public static final byte PLAYER_ATTACK_PLAYER = 6;
    
    /**
     * Player tấn công monster
     */
    public static final byte PLAYER_ATTACK_MONSTER = 9;
    
    /**
     * Monster tấn công player
     */
    public static final byte MONSTER_ATTACK_PLAYER = 10;
    
    /**
     * Monster chết
     */
    public static final byte MONSTER_DIE = 17;
    
    /**
     * Boss tấn công
     */
    public static final byte BOSS_ATTACK = 83;
    
    /**
     * Sử dụng buff
     */
    public static final byte USE_BUFF = 51;
    
    /**
     * Buff attack
     */
    public static final byte BUFF_ATTACK = 89;
    
    /**
     * Pet buff
     */
    public static final byte PET_BUFF = 71;
    
    /**
     * Actor (player/monster) chết
     */
    public static final byte ACTOR_DIE = 90;
    
    /**
     * Đổi chế độ PK
     */
    public static final byte CHANGE_PK = 49;
    
    /**
     * Thách đấu
     */
    public static final byte THACH_DAU = 54;
    
    /**
     * Attack nhiều mục tiêu
     */
    public static final byte CMD_ATTACK_MULTI_TARGET = 106;

    // ==================== MONSTER SYSTEM (7, 26, 58) ====================
    /**
     * Thông tin monster
     */
    public static final byte MONSTER_INFO = 7;
    
    /**
     * Template của monster
     */
    public static final byte MONSTER_TEMPLATE = 26;
    
    /**
     * Danh sách monster trong map
     */
    public static final byte LIST_MONSTER_MAP = 58;

    // ==================== ITEM SYSTEM (18-19, 21-22, 24-25, 28-29, 63-65, 67-68, 72-78) ====================
    /**
     * Nhặt item từ ground
     */
    public static final byte GET_ITEM_FROM_GROUND = 18;
    
    /**
     * Nhặt potion từ ground
     */
    public static final byte GET_POTION_FROM_GROUND = 19;
    
    /**
     * Thông tin item
     */
    public static final byte ITEM_INFO = 21;
    
    /**
     * Sử dụng potion
     */
    public static final byte USE_POTION = 22;
    
    /**
     * Mua item từ NPC
     */
    public static final byte BUY_ITEM_FROM_NPC = 24;
    
    /**
     * Template của item
     */
    public static final byte ITEM_TEMPLATE = 25;
    
    /**
     * Bán item
     */
    public static final byte SELL_ITEM = 28;
    
    /**
     * Sử dụng item
     */
    public static final byte USE_ITEM = 29;
    
    /**
     * Nhận item từ shop
     */
    public static final byte USE_ITEM_SHOP = 63;
    
    /**
     * Cho item
     */
    public static final byte GIVE_ITEM = 63;
    
    /**
     * Drop list
     */
    public static final byte DROP_LIST = 64;
    
    /**
     * Sử dụng item PK
     */
    public static final byte USE_ITEM_PK = 65;
    
    /**
     * Đập đồ/Nâng cấp item
     */
    public static final byte DAP_DO = 67;
    
    /**
     * Tạo item
     */
    public static final byte CREATE_ITEM = 68;
    
    /**
     * Sửa chữa item
     */
    public static final byte REPAIR_ITEM = 72;
    
    /**
     * Gem item
     */
    public static final byte GEM_ITEM = 73;
    
    /**
     * Mua gem item từ NPC
     */
    public static final byte BUY_GEM_ITEM_FROM_NPC = 74;
    
    /**
     * Thêm item vào imbue
     */
    public static final byte ADD_ITEM_IMBUE = 75;
    
    /**
     * Thêm gem item vào imbue
     */
    public static final byte ADD_GEM_ITEM_IMBUE = 76;
    
    /**
     * Special item
     */
    public static final byte SPECIAL_ITEM = 76;
    
    /**
     * Thực hiện imbue item
     */
    public static final byte DO_IMBUE_ITEM = 77;
    
    /**
     * Bán gem item
     */
    public static final byte SELL_GEM_ITEM = 78;
    
    /**
     * Shop item
     */
    public static final byte SHOP_ITEM = 84;
    
    /**
     * Mua item đặc biệt
     */
    public static final byte BUY_ITEM_SPECIAL = 85;
    
    /**
     * Sử dụng item đặc biệt
     */
    public static final byte USE_ITEM_SPECIAL = 86;

    // ==================== NPC SYSTEM (23, 55) ====================
    /**
     * Thông tin NPC
     */
    public static final byte NPC_INFO = 23;
    
    /**
     * Danh sách NPC bán hàng
     */
    public static final byte LIST_NPC_SELL = 55;

    // ==================== CHARACTER STATS (30, 32-36, 91) ====================
    /**
     * Set XP
     */
    public static final byte SET_XP = 30;
    
    /**
     * Cập nhật HP/MP
     */
    public static final byte UPDATE_HP_MP = 36;
    
    /**
     * Set thuộc tính nhân vật
     */
    public static final byte SET_CHAR_PROPERTIES = 32;
    
    /**
     * Level up
     */
    public static final byte LEVEL_UP = 33;
    
    /**
     * Thêm điểm cơ bản
     */
    public static final byte ADD_BASEPOINT = 34;
    
    /**
     * Thông tin skill
     */
    public static final byte SKILL_INFO = 35;
    
    /**
     * Điểm trang bị
     */
    public static final byte WEARING_POINT = 91;

    // ==================== CHAT & SOCIAL (27, 71, 101-102) ====================
    /**
     * Chat
     */
    public static final byte CHAT = 27;
    
    /**
     * Chat thế giới
     */
    public static final byte CHAT_WORLD = 71;
    
    /**
     * Thêm bạn
     */
    public static final byte CMD_ADD_FRIEND = 101;
    
    /**
     * Lấy danh sách bạn
     */
    public static final byte CMD_GET_FRIENDLIST = 102;

    // ==================== PARTY SYSTEM (48-50) ====================
    /**
     * Tạo party
     */
    public static final byte CREATE_PARTY = 48;
    
    /**
     * Thêm vào party
     */
    public static final byte ADD_TO_PARTY = 49;
    
    /**
     * Thông tin danh sách
     */
    public static final byte LIST_INFO = 50;

    // ==================== QUEST SYSTEM (52-53, 56-57, 59) ====================
    /**
     * Quest/Nhiệm vụ
     */
    public static final byte QUEST = 52;
    
    /**
     * Thông báo nhiệm vụ
     */
    public static final byte NOTIFY_INFO = 53;
    
    /**
     * Thông tin quest khi login
     */
    public static final byte INFO_QUEST_LOGIN = 56;
    
    /**
     * Thông tin quest tiếp theo khi login
     */
    public static final byte INFO_NEXT_QUEST_LOGIN = 57;
    
    /**
     * Quà nhiệm vụ
     */
    public static final byte GIFT_QUEST = 59;

    // ==================== PET & MOUNT SYSTEM (69) ====================
    /**
     * Pet tấn công
     */
    public static final byte ON_PET_ATTACK = 69;
    
    /**
     * Xuống ngựa
     */
    public static final byte CMD_DOWN_HORSE = 108;

    // ==================== MISCELLANEOUS (31, 37-38, 47, 60-62, 70, 79-82, 87-88, 96, 100, 103-109) ====================
    /**
     * Về nhà khi chết
     */
    public static final byte COME_HOME = 31;
    
    /**
     * Cảnh báo từ server
     */
    public static final byte SERVER_Alert = 37;
    
    /**
     * Thông tin server
     */
    public static final byte SERVER_INFO = 38;
    
    /**
     * Clear load map
     */
    public static final byte CLEAR_LOADMAP = 47;
    
    /**
     * Đếm ngược thời gian
     */
    public static final byte TIME_COUNT_DOWN = 60;
    
    /**
     * Mở hộp
     */
    public static final byte OPEN_BOX = 61;
    
    /**
     * Thông tin từ server
     */
    public static final byte INFO_FROM_SERVER = 62;
    
    /**
     * Ghép phi phong
     */
    public static final byte GHEP_PHI_PHONG = 70;
    
    /**
     * Di chuyển đến map
     */
    public static final byte MOVE_TO_MAP = 79;
    
    /**
     * Template Xà Phụ
     */
    public static final byte XAPHU_TEMPLATE = 80;
    
    /**
     * Mua vé
     */
    public static final byte BUY_TICKET = 81;
    
    /**
     * Lên tàu
     */
    public static final byte UP_TO_BOARD = 82;
    
    /**
     * Vòng quay may mắn
     */
    public static final byte DIAlLUCKY = 87;
    
    /**
     * Request thông tin nhân vật chính
     */
    public static final byte RQ_MAINCHAR_INFO = 88;
    
    /**
     * Yêu cầu bán item
     */
    public static final byte REQUEST_SELL_ITEM = 92;
    
    /**
     * Gửi item bán
     */
    public static final byte DEPOSITE_SELL_ITEM = 93;
    
    /**
     * Lấy item đã gửi
     */
    public static final byte GET_DEPOSITE_ITEM = 94;
    
    /**
     * Mua item đã gửi
     */
    public static final byte BUY_DEPOSITE_ITEM = 95;
    
    /**
     * Load resource
     */
    public static final byte LOAD_RES = 96;
    
    /**
     * Lấy info template
     */
    public static final byte GET_INFO_TEMPLATE = 100;
    
    /**
     * Lấy SMS nạp
     */
    public static final byte CMD_GET_SMSNAP = 103;
    
    /**
     * Cấu hình
     */
    public static final byte CMD_CONFIG = 104;
    
    /**
     * Vị trí
     */
    public static final byte CMD_LOCATION = 105;
    
    /**
     * Nạp tiền
     */
    public static final byte NAP_TIEN = 107;
    
    /**
     * Học skill
     */
    public static final byte CMD_LEAR_SKILL = 109;
    
    /**
     * Xóa actor
     */
    public static final byte REMOVE_ACTOR = 20;
    
    /**
     * Message kích thước lớn (4 bytes size)
     */
    public static final byte CMD_FULL_SIZE = 127;

    // ==================== BẦU CUA GAME (110-122) ====================
    /**
     * Tạo phòng Bầu Cua
     */
    public static final byte BAUCUA_CREATE_ROOM = 110;
    
    /**
     * Tham gia phòng Bầu Cua
     */
    public static final byte BAUCUA_JOIN_ROOM = 111;
    
    /**
     * Rời phòng Bầu Cua
     */
    public static final byte BAUCUA_LEAVE_ROOM = 112;
    
    /**
     * Sẵn sàng chơi Bầu Cua
     */
    public static final byte BAUCUA_READY = 113;
    
    /**
     * Đặt cược Bầu Cua
     */
    public static final byte BAUCUA_PLACE_BET = 114;
    
    /**
     * Kết quả xúc xắc Bầu Cua
     */
    public static final byte BAUCUA_DICE_RESULT = 115;
    
    /**
     * Thông tin phòng Bầu Cua
     */
    public static final byte BAUCUA_ROOM_INFO = 116;
    
    /**
     * Chat trong phòng Bầu Cua
     */
    public static final byte BAUCUA_CHAT = 117;
    
    /**
     * Trạng thái game Bầu Cua
     */
    public static final byte BAUCUA_GAME_STATE = 118;
    
    /**
     * Đuổi player khỏi phòng Bầu Cua
     */
    public static final byte BAUCUA_KICK_PLAYER = 119;
    
    /**
     * Đặt mật khẩu phòng Bầu Cua
     */
    public static final byte BAUCUA_SET_PASSWORD = 120;
    
    /**
     * Đặt tiền cược mặc định Bầu Cua
     */
    public static final byte BAUCUA_SET_DEFAULT_BET = 121;
    
    /**
     * Bắt đầu game Bầu Cua
     */
    public static final byte BAUCUA_START_GAME = 122;

    // ==================== CLAN SYSTEM (-20 to -7) ====================
    /**
     * Chuyển tiền clan
     */
    public static final byte TRANS_MONEY_CLAN = -20;
    
    /**
     * Top mạnh nhất/giàu nhất
     */
    public static final byte TOP_STRONGER_RICHER = -19;
    
    /**
     * Chat clan
     */
    public static final byte CHAT_CLAN = -18;
    
    /**
     * Message clan
     */
    public static final byte MSG_CLAN = -17;
    
    /**
     * Set slogan clan
     */
    public static final byte SET_SOLOGAN_CLAN = -16;
    
    /**
     * Rời clan
     */
    public static final byte OUT_CLAN = -15;
    
    /**
     * Giải tán clan
     */
    public static final byte DISSOLVE_CLAN = -14;
    
    /**
     * Đuổi khỏi clan
     */
    public static final byte EVICTION_CLAN = -13;
    
    /**
     * Thông tin clan
     */
    public static final byte CLAN_INFO = -12;
    
    /**
     * Thêm vào clan
     */
    public static final byte ADD_CLAN = -11;
    
    /**
     * Chọn icon clan
     */
    public static final byte CHOOSE_ICON_CLAN = -10;
    
    /**
     * Đăng ký clan
     */
    public static final byte REG_CLAN = -9;
    
    /**
     * Danh sách clan
     */
    public static final byte CLAN_LIST = -7;
    
    /**
     * Nhiệm vụ clan
     */
    public static final byte QUEST_CLAN = -37;
    
    /**
     * Clan
     */
    public static final byte CLAN = 66;

    // ==================== FRIEND & SOCIAL NEGATIVE RANGE (-6 to -5) ====================
    /**
     * Xóa bạn
     */
    public static final byte REMOVE_FRIEND = -6;
    
    /**
     * Tin nhắn riêng tư
     */
    public static final byte MESSAGE_PRIVATE = -5;

    // ==================== SPECIAL COMMANDS (NEGATIVE RANGE) ====================
    /**
     * Request thông tin chính
     */
    public static final byte REQUEST_MAIN_INFO = -2;
    
    /**
     * Request thông tin trang bị
     */
    public static final byte REQUEST_WEARING_INFO = -3;
    
    /**
     * Hướng dẫn
     */
    public static final byte HUONG_DAN = -72;
    
    /**
     * Lấy thông tin nhân vật chính
     */
    public static final byte GET_MAINCHAR_INFO = -100;
    
    /**
     * Bảo trì
     */
    public static final byte CMD_BAO_TRI = -126;
    
    /**
     * Lấy data nhân vật
     */
    public static final byte CMD_GET_DATA_CHAR = -125;
    
    /**
     * Shop tóc
     */
    public static final byte CMD_SHOP_HAIR = -124;
    
    /**
     * Nạp Apple Store
     */
    public static final byte GET_NAP_STORE_APPLE = -109;
    
    /**
     * Thông điệp mua hàng Google
     */
    public static final byte GOOGLE_PURCHASE_MESSAGE = -75;
    
    /**
     * Lấy any image map
     */
    public static final byte GET_ANY_IMAGE_MAP = -74;
    
    /**
     * Chat player
     */
    public static final byte CHAT_PLAYER = -73;
    
    /**
     * Điểm skill
     */
    public static final byte SKILL_POINT = -72;
    
    /**
     * Data effect từ server
     */
    public static final byte DATAEFF_FROMSV = -71;
    
    /**
     * Effect mới
     */
    public static final byte NEW_EFFECT = -70;
    
    /**
     * Auto imbue
     */
    public static final byte CMD_AUTO_IMBUE = -69;
    
    /**
     * Tách nguyên liệu
     */
    public static final byte CMD_TACH_NGUYEN_LIEU = -68;
    
    /**
     * Tủ binh
     */
    public static final byte CMD_TUBINH = -67;
    
    /**
     * Trái cây
     */
    public static final byte CMD_FRUIT = -66;
    
    /**
     * Lấy item quest
     */
    public static final byte GET_ITEM_QUEST = -65;
    
    /**
     * Nhiệm vụ mới
     */
    public static final byte CMD_NEW_QUEST = -64;
    
    /**
     * Tickets
     */
    public static final byte CMD_TICKETS = -63;
    
    /**
     * Mini game
     */
    public static final byte MINI_GAME = -62;
    
    /**
     * Kết hợp động vật
     */
    public static final byte CMD_ANIMAL_COMBINED = -61;
    
    /**
     * Message thế giới
     */
    public static final byte CMD_MSG_WORLD = -60;
    
    /**
     * Tự động nhặt item
     */
    public static final byte CMD_AUTO_GETITEM = -59;
    
    /**
     * Lấy tất cả data
     */
    public static final int GET_ALL_DATA = -57;
    
    /**
     * Form nạp tiền
     */
    public static final byte GET_FORM_NAP = -56;
    
    /**
     * Drop item xuống đất
     */
    public static final byte DROP_ITEM_TO_GROUND = -55;
    
    /**
     * Cập nhật image data
     */
    public static final int UPDATE_IMG_DATA = -54;
    
    /**
     * Thông tin nạp
     */
    public static final byte GET_INFO_NAP = -53;
    
    /**
     * Byte data từ server
     */
    public static final byte BYTE_DATA_SERVER = -52;
    
    /**
     * Image từ server
     */
    public static final byte IMAGE_SERVER = -51;
    
    /**
     * NPC động
     */
    public static final byte DYNAMIC_NPC = -50;
    
    /**
     * Effect object
     */
    public static final byte EFFECT_OBJ = -49;
    
    /**
     * Load image cây
     */
    public static final byte LOAD_IMAGE_TREE = -48;
    
    /**
     * Load image monster
     */
    public static final byte LOAD_IMAGE_MONSTER = -47;
    
    /**
     * Data âm thanh
     */
    public static final byte SOUND_DATA = -46;
    
    /**
     * Cưỡi động vật
     */
    public static final byte RIDE_ANIMAL = -45;
    
    /**
     * Thông tin động vật của nhân vật
     */
    public static final byte INFOO_ANIMAL_CHAR = -44;
    
    /**
     * Object động
     */
    public static final byte DYNAMIC_OBJ = -43;
    
    /**
     * Set khảm nhân vật
     */
    public static final byte SET_CHAR_KHAM = -42;
    
    /**
     * Lấy gem từ ground
     */
    public static final byte GET_GEM_FROM_GROUND = -41;
    
    /**
     * Thời tiết
     */
    public static final byte WEATHER = -39;
    
    /**
     * Chiến đấu
     */
    public static final byte FIGHT = -38;
    
    /**
     * Ép ngọc
     */
    public static final byte EP_NGOC = -36;
    
    /**
     * Khảm item
     */
    public static final byte KHAM_ITEM = -35;
    
    /**
     * Request khảm
     */
    public static final byte REQUEST_KHAM = -34;
    
    /**
     * Mở map boss
     */
    public static final byte OPEN_MAP_BOSS = -33;
    
    /**
     * Popup tùy chỉnh
     */
    public static final byte CUSTOM_POPUP = -32;
    
    /**
     * Text box
     */
    public static final byte TEXT_BOX = -31;
    
    /**
     * Menu option
     */
    public static final byte MENU_OPTION = -30;
    
    /**
     * Drop than lan
     */
    public static final byte DROP_THANLAN = -29;
    
    /**
     * Captcha
     */
    public static final byte CAPCHA = -28;
    
    /**
     * Lấy vũ khí
     */
    public static final byte GET_WEAPONE = -27;
    
    /**
     * Message delay
     */
    public static final byte MESSAGE_DELAY = -26;
    
    /**
     * Thẻ đến NPC
     */
    public static final byte CARD_TO_NPC = -25;
    
    /**
     * Lấy string
     */
    public static final byte GET_STRING = -24;
    
    /**
     * Xóa gem item
     */
    public static final byte DELL_GEM_ITEM = -23;
    
    /**
     * Xem thông tin
     */
    public static final byte VIEW_INFO = -22;
    
    /**
     * Pet ăn
     */
    public static final byte PET_EAT = -21;
}
