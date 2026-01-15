package mchien.code.screen.screen;

/**
 * Enum representing main menu tabs
 * Replaces magic number constants for better type safety and readability
 */
public enum MenuTab {
    SHOP(0, "Cửa hàng"),
    INVENTORY(1, "Hành trang"),
    EQUIPMENT(2, "Trang bị"),
    SKILL(3, "Kỹ năng"),
    QUEST(4, "Nhiệm vụ"),
    RECHARGE(5, "Nạp xu"),
    CLAN(6, "Bang hội"),
    ACTIVITY(7, "Hoạt động"),
    UPGRADE(8, "Đập đồ"),
    CHEDO(9, "Chế đồ"),
    TRANSFORM(10, "Chuyển hóa"),
    CREATE_MOUNT(11, "Tạo phi phong"),
    PET(12, "Pet"),
    BAG(22, "Kho"),
    CLAN_SKILL(29, "Kỹ năng bang");
    
    private final int id;
    private final String displayName;
    
    MenuTab(int id, String displayName) {
        this.id = id;
        this.displayName = displayName;
    }
    
    public int getId() {
        return id;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    /**
     * Get tab by ID
     * 
     * @param id Tab ID
     * @return MenuTab or null if not found
     */
    public static MenuTab fromId(int id) {
        for (MenuTab tab : values()) {
            if (tab.id == id) {
                return tab;
            }
        }
        return null;
    }
    
    /**
     * Check if this is a shop-related tab
     */
    public boolean isShopTab() {
        return this == SHOP;
    }
    
    /**
     * Check if this is an equipment-related tab
     */
    public boolean isEquipmentTab() {
        return this == EQUIPMENT || this == INVENTORY;
    }
    
    /**
     * Check if this tab requires sub-tabs
     */
    public boolean hasSubTabs() {
        return this == SHOP || this == INVENTORY || this == EQUIPMENT || 
               this == SKILL || this == QUEST || this == CLAN || this == ACTIVITY;
    }
}
