package mchien.code.screen.screen;

import mchien.code.model.ItemTemplate;

/**
 * Helper class for managing equipment positions
 * Provides clear methods for equipment slot management
 */
public class EquipmentSlot {
    // Equipment position constants
    public static final byte POS_HAT = 0;
    public static final byte POS_CHAIN = 1;
    public static final byte POS_JEWELRY = 2;
    public static final byte POS_RING_LEFT = 3;
    public static final byte POS_RING_RIGHT = 4;
    public static final byte POS_BODY = 5;
    public static final byte POS_BELT = 6;
    public static final byte POS_GLOVE = 7;
    public static final byte POS_SHOES = 8;
    public static final byte POS_WEAPON = 9;
    public static final byte POS_WING = 10;
    public static final byte POS_MOUNT = 11;
    public static final byte POS_COSTUME = 12;
    public static final byte POS_FOOD = 13;
    public static final byte POS_PET = 14;
    
    // Mapping array for item types to equipment positions
    private static final byte[] ITEM_TYPE_TO_POSITION = {
        POS_HAT,    // 0: HAT
        POS_CHAIN,  // 1: CHAIN
        POS_JEWELRY,// 2: JEWELRY
        POS_WEAPON, // 3: WEAPON type 1
        POS_WEAPON, // 4: WEAPON type 2
        POS_WEAPON, // 5: WEAPON type 3
        POS_WEAPON, // 6: WEAPON type 4
        POS_WEAPON, // 7: WEAPON type 5
        -1,         // 8: Special (Body/Shoes based on position)
        POS_RING_RIGHT, // 9: RING
        POS_GLOVE,  // 10: GLOVE
        POS_RING_LEFT,  // 11: RING LEFT
        POS_SHOES,  // 12: SHOES
        POS_RING_LEFT,  // 13: Additional ring
        POS_HAT,    // 14: Additional hat
        POS_RING_LEFT,  // 15: Additional ring
        POS_CHAIN,  // 16: Additional chain
        POS_JEWELRY,// 17: Additional jewelry
        POS_RING_RIGHT  // 18: Additional ring right
    };
    
    /**
     * Get equipment position for an item template
     * 
     * @param itemTemplate Item template to check
     * @param pos Current position (used for special cases)
     * @return Equipment position or -1 if invalid
     */
    public static int getEquipmentPosition(ItemTemplate itemTemplate, int pos) {
        if (itemTemplate == null) {
            return -1;
        }
        
        int type = itemTemplate.type;
        
        // Special case for type 8 (Body/Shoes)
        if (type == 8) {
            return (pos != 1) ? POS_BELT : POS_BODY;
        }
        
        // Handle types within array bounds
        if (type >= 0 && type < ITEM_TYPE_TO_POSITION.length) {
            return ITEM_TYPE_TO_POSITION[type];
        }
        
        return -1;
    }
    
    /**
     * Check if position is a valid equipment slot
     * 
     * @param position Position to check
     * @return true if valid equipment position
     */
    public static boolean isValidEquipmentPosition(int position) {
        return position >= POS_HAT && position <= POS_PET;
    }
    
    /**
     * Get display name for equipment position
     * 
     * @param position Equipment position
     * @return Display name in Vietnamese
     */
    public static String getPositionName(int position) {
        switch (position) {
            case POS_HAT: return "Nón";
            case POS_CHAIN: return "Dây chuyền";
            case POS_JEWELRY: return "Ngọc bội";
            case POS_RING_LEFT: return "Nhẫn trái";
            case POS_RING_RIGHT: return "Nhẫn phải";
            case POS_BODY: return "Áo";
            case POS_BELT: return "Đai";
            case POS_GLOVE: return "Găng tay";
            case POS_SHOES: return "Giày";
            case POS_WEAPON: return "Vũ khí";
            case POS_WING: return "Phi phong";
            case POS_MOUNT: return "Thú cưỡi";
            case POS_COSTUME: return "Thời trang";
            case POS_FOOD: return "Đồ ăn";
            case POS_PET: return "Pet";
            default: return "Không xác định";
        }
    }
    
    private EquipmentSlot() {
        // Utility class - prevent instantiation
    }
}
