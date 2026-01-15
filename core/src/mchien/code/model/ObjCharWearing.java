package mchien.code.model;

/**
 * Base class for character wearing items (equipment, pets, etc.).
 * Provides common interface for different types of wearable items.
 * 
 * This is a marker/interface class for polymorphic rendering of equipment.
 */
public class ObjCharWearing {
    
    /**
     * Shows information about this wearing item.
     * Override in subclasses to provide specific info display.
     */
    public void showInfo() {
    }
    
    /**
     * Checks if this is an Item type wearing.
     * @return true if this is an Item
     */
    public boolean isItem() {
        return false;
    }
    
    /**
     * Checks if this is a Pet type wearing.
     * @return true if this is a Pet
     */
    public boolean isPet() {
        return false;
    }
    
    /**
     * Gets the type identifier for this wearing item.
     * @return Type identifier
     */
    public int getType() {
        return 0;
    }
    
    /**
     * Gets the style identifier for rendering.
     * @return Style ID for visual appearance
     */
    public short getStyle() {
        return 0;
    }
}
