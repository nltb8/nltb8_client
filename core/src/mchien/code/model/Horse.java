package mchien.code.model;

import lib.mGraphics;

/**
 * Represents a mount/horse that a character can ride.
 * Handles rendering of mount sprites.
 * 
 * Note: The actual mount rendering is primarily handled through DataSkillEff
 * (see Char.loadPartPhiPhongThuCuoi). This class is a simplified placeholder.
 */
public class Horse {
    /** Image ID for the horse sprite */
    public int idImg;
    
    /**
     * Creates a new Horse with the specified image ID.
     * @param idimg The image ID for the horse sprite
     */
    public Horse(int idimg) {
        this.idImg = idimg;
    }
    
    /**
     * Paints the horse at the specified position.
     * Currently a placeholder - actual rendering done via DataSkillEff system.
     * 
     * @param g Graphics context
     * @param x X coordinate
     * @param y Y coordinate
     */
    public void paint(mGraphics g, int x, int y) {
        // Placeholder - actual horse rendering is handled through
        // DataSkillEff in Char.paintCharByChunkHide()
    }
}
