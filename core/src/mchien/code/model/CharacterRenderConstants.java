package mchien.code.model;

/**
 * Constants used for character rendering.
 * Extracted from Char.java to improve readability and maintainability.
 */
public final class CharacterRenderConstants {
    
    // Direction constants
    public static final byte DIR_DOWN = 0;
    public static final byte DIR_UP = 1;
    public static final byte DIR_LEFT = 2;
    public static final byte DIR_RIGHT = 3;
    
    // State constants
    public static final byte STATE_STAND = 0;
    public static final byte STATE_RUN = 1;
    public static final byte STATE_ATTACK = 2;
    public static final byte STATE_DEAD = 3;
    public static final byte STATE_COME_HOME = 4;
    public static final byte STATE_MOVE_TO_FIRST = 5;
    public static final byte STATE_RUN_AND_ATTACK = 6;
    public static final byte STATE_RUN_FRAME_ATTACK = 7;
    public static final byte STATE_FLASH = 8;
    
    // Character part constants
    public static final byte PART_HEAD = 0;
    public static final byte PART_BODY = 1;
    public static final byte PART_LEG = 2;
    public static final byte PART_WEAPON = 3;
    public static final byte PART_PHI_PHONG = 4;
    
    // Character class constants
    public static final byte CLASS_THIEU_LAM = 0;
    public static final byte CLASS_CAI_BANG = 1;
    public static final byte CLASS_NGA_MI = 2;
    public static final byte CLASS_VO_DANG = 3;
    public static final byte CLASS_NGU_DOC = 4;
    
    // Shadow offset constants
    public static final int SHADOW_OFFSET_DEFAULT = 0;
    public static final int SHADOW_Y_OFFSET = -2;
    public static final int SHADOW_Y_OFFSET_STAND = -3;
    
    // Horse/mount offset constants
    public static final int MOUNT_DY_DEFAULT = 6;
    
    // Animation timing constants
    public static final int FRAME_UPDATE_INTERVAL = 5;
    public static final int HORSE_FRAME_UPDATE_INTERVAL = 6;
    public static final int HEAD_DYNAMIC_UPDATE_INTERVAL = 10;
    
    // Name display offset
    public static final int NAME_Y_OFFSET = 75;
    
    // Quest icon constants
    public static final int QUEST_ICON_FRAME_HEIGHT = 14;
    public static final int QUEST_ICON_WIDTH = 12;
    
    // Effect constants
    public static final byte FRAME_ADD_EFF_ATTACK = 6;
    
    // Prevent instantiation
    private CharacterRenderConstants() {
        throw new AssertionError("Cannot instantiate constants class");
    }
}
