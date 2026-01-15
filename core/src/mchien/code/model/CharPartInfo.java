package mchien.code.model;

import javax.microedition.lcdui.Image;
import lib.mGraphics;

/**
 * Contains information and rendering logic for a character body part.
 * Each part (head, body, leg, weapon, etc.) has its own CharPartInfo instance
 * with specific image data and positioning information.
 */
public class CharPartInfo {
    /** Type of this part (HEAD, BODY, LEG, WEAPON, etc.) */
    public int type;
    
    /** Unique identifier for this part */
    public int id;
    
    /** Time remaining before this part is removed (for temporary effects) */
    public int timeRemove = 0;
    
    // Static arrays for part rendering data
    public static int[][][] x;      // X position in sprite sheet
    public static int[][][] y;      // Y position in sprite sheet
    public static int[][][] w;      // Width of sprite region
    public static int[][][] h;      // Height of sprite region
    public static int[][][] dx;     // X offset when rendering
    public static int[][][] dy;     // Y offset when rendering
    
    /** The sprite image for this part */
    public Image image;
    
    /** Last time this part was painted */
    long timePaint;
    
    /** Animation time counter */
    public int time;
    
    /** Frame mapping for different part types and animations */
    public static final byte[][] PART_OF_FRAME = new byte[][]{
        {0, 0, 1, 2, 3, 4},  // Part type 0
        {0, 0, 1, 2, 3, 4},  // Part type 1
        new byte[6],         // Part type 2
        new byte[6],         // Part type 3
        {0, 1, 0, 1, 0, 1}   // Part type 4
    };
    
    static int[][] xhd = new int[][]{{1, 1, 0}, {1, 1, 0}};
    static int[][] yhd = new int[][]{{3, 2, 2}, {3, 2, 2}};
    static int wFrame = 17;
    static int[] indexHeadFrame = new int[]{0, 2, 1};

    /**
     * Loads character part data from game resources.
     * Should be called during game initialization.
     */
    public static void loadDataCharPart() {
    }

    /**
     * Loads part data from byte array.
     * @param arr Raw part data
     * @param type Part type identifier
     * @param Id Part ID
     */
    public void load(byte[] arr, int type, int Id) {
    }

    /**
     * Loads coat/armor part data.
     * @param type Coat type
     * @param id Coat ID
     */
    public void loadCoat(int type, int id) {
    }

    /**
     * Loads part data by type and ID.
     * @param type Part type
     * @param id Part ID
     */
    public void load(int type, int id) {
    }

    /**
     * Creates a new CharPartInfo with the specified type and ID.
     * @param type Part type (HEAD, BODY, LEG, etc.)
     * @param id Part ID
     */
    public CharPartInfo(int type, int id) {
        this.type = (short)type;
        this.id = (short)id;
    }

    /**
     * Paints this character part at the specified position with direction and animation.
     * 
     * @param g Graphics context
     * @param xp X position to paint at
     * @param yp Y position to paint at
     * @param dir Character direction (0-3)
     * @param frame Animation frame index
     */
    public void paint(mGraphics g, int xp, int yp, int dir, int frame) {
        if (!isValidForPainting()) {
            return;
        }
        
        // Calculate rotation and direction
        byte rotation = 0;
        int normalizedDir = dir;
        if (dir > 2) {
            rotation = 2;  // Flip horizontally for right-facing
            normalizedDir = 2;
        }
        
        // Only paint valid part types
        if (this.type == 1 || this.type == 2 || this.type == 0) {
            if (this.type != 0) {
                // Non-head parts
                int frameIndex = PART_OF_FRAME[this.type][frame];
                int srcX = x[this.type][normalizedDir][frameIndex];
                int srcY = y[this.type][normalizedDir][frameIndex];
                int width = w[this.type][normalizedDir][frameIndex];
                int height = h[this.type][normalizedDir][frameIndex];
                int offsetX = dx[this.type][normalizedDir][frame];
                int offsetY = dy[this.type][normalizedDir][frame];
                
                // Flip offset for right-facing direction
                int finalX = xp + (dir != 3 ? offsetX : -(offsetX + width));
                int finalY = yp + offsetY;
                
                g.drawRegion(this.image, srcX, srcY, width, height, rotation, finalX, finalY, 0, false);
            } else {
                // Head part - special handling
                int srcX = indexHeadFrame[normalizedDir] * wFrame;
                int width = wFrame;
                int height = this.image.getHeight();
                int offsetX = dx[this.type][normalizedDir][frame] + xhd[0][normalizedDir];
                int offsetY = dy[this.type][normalizedDir][frame] + yhd[0][normalizedDir];
                
                int finalX = xp + (dir != 3 ? offsetX : -(offsetX + width));
                int finalY = yp + offsetY;
                
                g.drawRegion(this.image, srcX, 0, width, height, rotation, finalX, finalY, 0, false);
            }
        }
        
        this.timePaint = System.currentTimeMillis();
    }
    
    /**
     * Checks if this part is valid and ready for painting.
     * @return true if part has valid type and loaded image
     */
    private boolean isValidForPainting() {
        return this.type >= 0 && this.image != null;
    }

    /**
     * Paints this part in a static position (no character animation).
     * Used for UI displays like inventory or equipment screens.
     * 
     * @param g Graphics context
     * @param xp X position
     * @param yp Y position
     * @param dir Direction
     * @param frame Frame index
     */
    public void paintStatic(mGraphics g, short xp, short yp, int dir, int frame) {
        if (this.image != null) {
            int frameIndex = PART_OF_FRAME[this.type][frame];
            g.drawRegion(
                this.image, 
                x[this.type][dir][frameIndex], 
                y[this.type][dir][frameIndex], 
                w[this.type][dir][frameIndex], 
                h[this.type][dir][frameIndex], 
                0, 
                xp, 
                yp, 
                3, 
                false
            );
        }
    }

    /**
     * Paints this part as an avatar icon (small portrait).
     * 
     * @param g Graphics context
     * @param xp X position
     * @param yp Y position
     * @param frame Frame index
     */
    public void paintAvatar(mGraphics g, short xp, short yp, int frame) {
        // Placeholder for avatar painting
    }

    /**
     * Paints just the image without any transformations.
     * 
     * @param g Graphics context
     * @param x X position
     * @param y Y position
     */
    public void paintImage(mGraphics g, int x, int y) {
        // Placeholder for simple image painting
    }
}

