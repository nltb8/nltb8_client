package mchien.code.model;

import lib.mVector;

/**
 * Utility class for loading and managing character parts (chunks).
 * Provides fallback to default parts when custom parts are not available.
 */
public class CharacterPartLoader {
    
    /**
     * Loads a character part chunk with fallback to default.
     * 
     * @param partIndex The part index (HEAD, BODY, LEG, WEAPON)
     * @param partId The ID of the part to load
     * @param gender The character gender (0 or 1)
     * @param defaultChunks The default chunk array for fallback
     * @return The loaded Chunk, or null if partIndex is invalid
     */
    public static Chunk loadPartWithFallback(int partIndex, short partId, int gender, Chunk[][] defaultChunks) {
        Chunk chunk = null;
        
        // Try to load the specific part
        switch (partIndex) {
            case CharacterRenderConstants.PART_HEAD:
                chunk = Chunk.getHead(partId, gender);
                break;
            case CharacterRenderConstants.PART_BODY:
                chunk = Chunk.getBody(partId, gender);
                break;
            case CharacterRenderConstants.PART_LEG:
                chunk = Chunk.getLeg(partId, gender);
                break;
            case CharacterRenderConstants.PART_WEAPON:
                chunk = Chunk.getWeapon(partId);
                break;
            default:
                // Invalid part index - fall through to use default
                break;
        }
        
        // Fallback to default if loading failed or invalid part index
        if (chunk == null && defaultChunks != null && defaultChunks[gender] != null) {
            chunk = defaultChunks[gender][partIndex];
        }
        
        return chunk;
    }
    
    /**
     * Ensures a part vector has all required parts loaded.
     * Fills in missing parts with defaults.
     * 
     * @param partPaint The vector of parts to validate
     * @param partIds The array of part IDs
     * @param gender The character gender
     * @param defaultChunks The default chunks for fallback
     */
    public static void ensurePartsLoaded(mVector partPaint, short[] partIds, int gender, Chunk[][] defaultChunks) {
        if (partPaint.size() < 4) {
            // Ensure vector has space for all parts
            while (partPaint.size() < 4) {
                partPaint.addElement(null);
            }
        }
        
        // Load or update each part
        for (int i = 0; i < 4; i++) {
            Chunk existing = (Chunk) partPaint.elementAt(i);
            if (existing == null && partIds != null && i < partIds.length) {
                Chunk loaded = loadPartWithFallback(i, partIds[i], gender, defaultChunks);
                partPaint.setElementAt(loaded, i);
            }
        }
    }
    
    /**
     * Creates a complete part vector from part IDs.
     * 
     * @param partIds Array of part IDs [HEAD, BODY, LEG, WEAPON]
     * @param gender Character gender
     * @return Vector of Chunks
     */
    public static mVector createPartVector(short[] partIds, int gender) {
        mVector parts = new mVector();
        
        if (partIds != null && partIds.length >= 4) {
            parts.addElement(Chunk.getHead(partIds[CharacterRenderConstants.PART_HEAD], gender));
            parts.addElement(Chunk.getBody(partIds[CharacterRenderConstants.PART_BODY], gender));
            parts.addElement(Chunk.getLeg(partIds[CharacterRenderConstants.PART_LEG], gender));
            parts.addElement(Chunk.getWeapon(partIds[CharacterRenderConstants.PART_WEAPON]));
        }
        
        return parts;
    }
}
