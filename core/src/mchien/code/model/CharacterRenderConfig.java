package mchien.code.model;

/**
 * Configuration class for character rendering operations.
 * Reduces parameter passing and provides a clean API for rendering options.
 */
public class CharacterRenderConfig {
    // Rendering options
    private final boolean includeEffects;
    private final boolean includeShadow;
    private final boolean includeMount;
    private final boolean isPreview;
    
    // Position and state
    private final int xOffset;
    private final int yOffset;
    private final boolean isWater;
    
    private CharacterRenderConfig(Builder builder) {
        this.includeEffects = builder.includeEffects;
        this.includeShadow = builder.includeShadow;
        this.includeMount = builder.includeMount;
        this.isPreview = builder.isPreview;
        this.xOffset = builder.xOffset;
        this.yOffset = builder.yOffset;
        this.isWater = builder.isWater;
    }
    
    public boolean shouldIncludeEffects() {
        return includeEffects;
    }
    
    public boolean shouldIncludeShadow() {
        return includeShadow;
    }
    
    public boolean shouldIncludeMount() {
        return includeMount;
    }
    
    public boolean isPreview() {
        return isPreview;
    }
    
    public int getXOffset() {
        return xOffset;
    }
    
    public int getYOffset() {
        return yOffset;
    }
    
    public boolean isWater() {
        return isWater;
    }
    
    public static Builder newBuilder() {
        return new Builder();
    }
    
    /**
     * Creates a default configuration for normal gameplay rendering.
     * This provides sensible defaults for typical in-game character rendering.
     * For custom configurations, use newBuilder() to create a customized config.
     * 
     * @return CharacterRenderConfig with all features enabled
     */
    public static CharacterRenderConfig createDefault() {
        return newBuilder()
                .withEffects(true)
                .withShadow(true)
                .withMount(true)
                .build();
    }
    
    /**
     * Creates a configuration for preview/shop rendering.
     * Disables heavy effects and mounts for cleaner UI display.
     * For custom configurations, use newBuilder() to create a customized config.
     * 
     * @return CharacterRenderConfig optimized for UI previews
     */
    public static CharacterRenderConfig createPreview() {
        return newBuilder()
                .withEffects(false)
                .withShadow(true)
                .withMount(false)
                .asPreview(true)
                .build();
    }
    
    public static class Builder {
        private boolean includeEffects = true;
        private boolean includeShadow = true;
        private boolean includeMount = true;
        private boolean isPreview = false;
        private int xOffset = 0;
        private int yOffset = 0;
        private boolean isWater = false;
        
        public Builder withEffects(boolean include) {
            this.includeEffects = include;
            return this;
        }
        
        public Builder withShadow(boolean include) {
            this.includeShadow = include;
            return this;
        }
        
        public Builder withMount(boolean include) {
            this.includeMount = include;
            return this;
        }
        
        public Builder asPreview(boolean preview) {
            this.isPreview = preview;
            return this;
        }
        
        public Builder withOffset(int xOffset, int yOffset) {
            this.xOffset = xOffset;
            this.yOffset = yOffset;
            return this;
        }
        
        public Builder inWater(boolean isWater) {
            this.isWater = isWater;
            return this;
        }
        
        public CharacterRenderConfig build() {
            return new CharacterRenderConfig(this);
        }
    }
}
