package mchien.code.screen.screen;

import lib.mGraphics;
import mchien.code.main.GameCanvas;
import mchien.code.model.mCommand;
import lib2.mFont;

/**
 * Helper class for rendering simple menu items
 * Reduces code duplication across Menu, Menu2, and MenuSelectItem
 */
public class MenuRenderer {
    
    /**
     * Paint a menu item button
     * 
     * @param g Graphics context
     * @param x X position
     * @param y Y position
     * @param width Width of button
     * @param height Height of button
     * @param caption Button text
     * @param isSelected Whether this item is selected
     */
    public static void paintMenuItem(mGraphics g, int x, int y, int width, int height, 
                                     String caption, boolean isSelected) {
        // Draw button background based on selection state
        if (isSelected) {
            g.setColor(MenuColors.FOCUS_YELLOW);
        } else {
            g.setColor(MenuColors.BORDER_GRAY);
        }
        g.fillRect(x, y, width, height, false);
        
        // Draw text centered
        int textX = x + width / 2;
        int textY = y + height / 2;
        
        if (isSelected) {
            FontTeam.fontTile.drawString(g, caption, textX, textY - MenuConstants.TEXT_Y_OFFSET, 
                                        MenuConstants.TEXT_CENTER_ALIGN, false);
        } else {
            mFont.tahoma_7b_white.drawString(g, caption, textX, textY - MenuConstants.TEXT_Y_OFFSET, 
                                             MenuConstants.TEXT_CENTER_ALIGN, false);
        }
    }
    
    /**
     * Paint menu header
     * 
     * @param g Graphics context
     * @param x X position
     * @param y Y position
     * @param width Width of header
     * @param title Header title text
     */
    public static void paintMenuHeader(mGraphics g, int x, int y, int width, String title) {
        g.setColor(MenuColors.BORDER_GRAY);
        g.fillRect(x, y - 28, width, 28, false);
        
        // Draw border
        for (int i = 0; i < 3; i++) {
            g.setColor(Res.nColor[i]);
            g.drawRect(x + i, y - 28 + i, width - i * 2, 28 - i * 2, false);
        }
        
        // Draw title decorations using constant
        int centerX = x + width / 2;
        for (int i = 0; i < MenuConstants.HEADER_DECORATION_COUNT; i++) {
            g.drawRegion(GameScr.imgBoder[5], 0, 25, 12, 25, 0, 
                        centerX - 42 + i * 12, y - 14, 
                        mGraphics.VCENTER | mGraphics.LEFT, false);
        }
        
        // Draw title ends
        g.drawRegion(GameScr.imgBoder[5], 0, 0, 12, 25, 0, 
                    centerX - 44, y - 14, 
                    mGraphics.VCENTER | mGraphics.LEFT, false);
        g.drawRegion(GameScr.imgBoder[5], 0, 0, 12, 25, 2, 
                    centerX + 44, y - 14 + 1, 
                    mGraphics.VCENTER | mGraphics.RIGHT, false);
        
        // Draw title text
        FontTeam.fontTile.drawString(g, title, centerX, y - 19, MenuConstants.TEXT_CENTER_ALIGN, false);
    }
    
    /**
     * Paint menu corners with border images
     * 
     * @param g Graphics context
     * @param x X position
     * @param y Y position
     * @param width Width
     * @param height Height
     */
    public static void paintMenuCorners(mGraphics g, int x, int y, int width, int height) {
        g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 7, 
                    x + width + 1, y + 1, 
                    mGraphics.BOTTOM | mGraphics.RIGHT, false);
        g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 1, 
                    x, y + 1, 
                    mGraphics.BOTTOM | mGraphics.LEFT, false);
        g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 0, 
                    x, y - 28, 
                    mGraphics.TOP | mGraphics.LEFT, false);
        g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 2, 
                    x + width + 1, y - 28, 
                    mGraphics.TOP | mGraphics.RIGHT, false);
    }
    
    /**
     * Calculate optimal menu width based on items
     * 
     * @param itemCount Number of menu items
     * @param itemWidth Width per item
     * @return Total menu width
     */
    public static int calculateMenuWidth(int itemCount, int itemWidth) {
        int totalWidth = itemCount * itemWidth - 1;
        if (totalWidth > GameCanvas.w - 2) {
            totalWidth = GameCanvas.w - 2;
        }
        return totalWidth;
    }
    
    /**
     * Center menu on screen
     * 
     * @param menuWidth Width of menu
     * @return X position to center menu
     */
    public static int centerMenuX(int menuWidth) {
        int x = (GameCanvas.w - menuWidth) / 2;
        return x < 1 ? 1 : x;
    }
    
    private MenuRenderer() {
        // Utility class - prevent instantiation
    }
}
