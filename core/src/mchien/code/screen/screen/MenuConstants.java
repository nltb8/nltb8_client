package mchien.code.screen.screen;

/**
 * Constants used across menu rendering system
 */
public class MenuConstants {
    // Tab indices - Main menu tabs
    public static final byte TAB_SHOP = 0;
    public static final byte TAB_INVENTORY = 1;
    public static final byte TAB_EQUIPMENT = 2;
    public static final byte TAB_SKILL = 3;
    public static final byte TAB_QUEST = 4;
    public static final byte TAB_RECHARGE = 5;
    public static final byte TAB_CLAN = 6;
    public static final byte TAB_ACTIVITY = 7;
    public static final byte TAB_UPGRADE = 8;
    public static final byte TAB_CHEDO = 9;
    public static final byte TAB_TRANSFORM = 10;
    public static final byte TAB_CREATE_MOUNT = 11;
    public static final byte TAB_PET = 12;
    public static final byte TAB_BAG = 22;
    public static final byte TAB_CLAN_SKILL = 29;
    
    // Equipment positions
    public static final byte EQUIP_POS_BODY = 5;
    public static final byte EQUIP_POS_HAT = 0;
    public static final byte EQUIP_POS_SHOES = 8;
    public static final byte EQUIP_POS_GLOVE = 7;
    public static final byte EQUIP_POS_WEAPON = 9;
    public static final byte EQUIP_POS_RING_LEFT = 3;
    public static final byte EQUIP_POS_RING_RIGHT = 4;
    public static final byte EQUIP_POS_CHAIN = 1;
    public static final byte EQUIP_POS_JEWELRY = 2;
    public static final byte EQUIP_POS_BELT = 6;
    public static final byte EQUIP_POS_MOUNT = 11;
    public static final byte EQUIP_POS_WING = 10;
    public static final byte EQUIP_POS_FOOD = 13;
    public static final byte EQUIP_POS_PET = 14;
    public static final byte EQUIP_POS_COSTUME = 12;
    
    // Item selection types
    public static final short SELECT_ITEM_INVENTORY = 79;
    public static final short SELECT_ITEM_SHOP = -2;
    public static final short SELECT_ITEM_WEARING = 4;
    
    // Max values
    public static final short MAX_POTION = 999;
    
    private MenuConstants() {
        // Utility class - prevent instantiation
    }
}
