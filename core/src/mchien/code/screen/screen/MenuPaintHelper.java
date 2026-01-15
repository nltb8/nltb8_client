package mchien.code.screen.screen;

import lib.mGraphics;

/**
 * Helper class for common menu painting operations
 * Provides reusable methods for drawing menu backgrounds, borders, and decorations
 */
public class MenuPaintHelper {
    
    /**
     * Paints a standard menu background with border
     * 
     * @param g Graphics context
     * @param x X position
     * @param y Y position
     * @param width Width of background
     * @param height Height of background
     */
    public static void paintMenuBackground(mGraphics g, int x, int y, int width, int height) {
        // Draw shadow layers
        g.setColor(MenuColors.BACKGROUND_DARK);
        g.fillRect(x, y, width, height, false);
        
        g.setColor(MenuColors.BACKGROUND_BORDER_1);
        g.fillRect(x + 1, y + 1, width - 2, height - 2, false);
        
        g.setColor(MenuColors.BACKGROUND_DARK);
        g.fillRect(x + 2, y + 2, width - 4, height - 4, false);
        
        // Fill with main background color
        g.setColor(MenuColors.BACKGROUND_LIGHT);
        g.fillRect(x + 3, y + 3, width - 6, height - 6, false);
    }
    
    /**
     * Paints a character display area background
     * 
     * @param g Graphics context
     * @param x X position
     * @param y Y position
     * @param width Width
     * @param height Height
     * @param isFocused Whether the area is currently focused
     */
    public static void paintCharacterArea(mGraphics g, int x, int y, int width, int height, boolean isFocused) {
        g.setColor(MenuColors.BORDER_GRAY);
        g.fillRect(x + 3, y, width - 6, height, false);
        g.fillRect(x, y + 3, width, height - 6, false);
        
        g.setColor(MenuColors.BORDER_DARK);
        g.drawLine(x + 3, y + 3 + height - 6 + 1, x + width - 6, y + 3 + height - 6 + 1, false);
        
        if (isFocused && GameCanvas.gameTick / 4 % 4 != 0) {
            g.setColor(MenuColors.BACKGROUND_FOCUSED);
        } else {
            g.setColor(MenuColors.BACKGROUND_LIGHT);
        }
        g.fillRect(x + 3, y + 3, width - 6, height - 6, false);
        
        // Top highlight
        g.setColor(MenuColors.BORDER_LIGHT);
        g.fillRect(x + 3, y + 1, width - 6, 1, false);
        
        // Side borders
        g.setColor(MenuColors.BORDER_ACCENT);
        g.fillRect(x + 1, y + 16, 1, height - 18, false);
        g.fillRect(x - 2 + width, y + 16, 1, height - 18, false);
    }
    
    /**
     * Paints a simple border around an area
     * 
     * @param g Graphics context
     * @param x X position
     * @param y Y position
     * @param width Width
     * @param height Height
     */
    public static void paintBorder(mGraphics g, int x, int y, int width, int height) {
        g.setColor(MenuColors.BORDER_GRAY);
        g.drawRect(x, y, width, height, false);
        g.drawRect(x + 2, y + 2, width - 4, height - 4, false);
        
        g.setColor(MenuColors.BORDER_ACCENT);
        g.drawRect(x + 1, y + 1, width - 2, height - 2, false);
    }
    
    /**
     * Paints a separator line
     * 
     * @param g Graphics context
     * @param x X position
     * @param y Y position
     * @param width Width of line
     */
    public static void paintSeparator(mGraphics g, int x, int y, int width) {
        g.setColor(MenuColors.SEPARATOR_LINE);
        g.fillRect(x, y, width, 1, true);
    }
    
    /**
     * Paints item slot background
     * 
     * @param g Graphics context
     * @param x X position
     * @param y Y position
     * @param size Size of slot
     */
    public static void paintItemSlot(mGraphics g, int x, int y, int size) {
        g.setColor(MenuColors.ITEM_BORDER);
        g.drawRect(x, y, size, size, true);
    }
    
    private MenuPaintHelper() {
        // Utility class - prevent instantiation
    }
}
