# Menu Rendering Architecture

## Overview
This document describes the refactored menu rendering system for the NLTB8 game client.

## Main Components

### Constants and Configuration
- **MenuConstants.java** - Centralized tab indices and item selection types
- **MenuColors.java** - All color constants used in menu rendering
- **EquipmentSlot.java** - Equipment position management

### Enums
- **MenuTab.java** - Type-safe enum for main menu tabs with display names

### Helper Classes
- **MenuPaintHelper.java** - Reusable methods for common painting operations
  - Background rendering
  - Border drawing
  - Character area rendering
  - Item slot rendering
  
- **MenuRenderer.java** - Helper for simple menu rendering
  - Menu item buttons
  - Menu headers
  - Menu corners
  - Layout calculations

### Main Menu Classes
- **MainMenu.java** - Primary menu with tabs (Shop, Inventory, Equipment, Skills, Quest)
- **MainMenu2.java** - Alternative menu implementation
- **Menu.java** - NPC dialog menu
- **MenuSelectItem.java** - Quick access menu with auto-fight settings
- **Menu2.java** - Utility menu (PK, Friends, Party)
- **PauseMenu.java** - Pause menu overlay

## Menu Tabs

### Main Menu Tabs (MainMenu.java)
1. **Shop (Cửa hàng)** - Purchase items from NPCs
2. **Inventory (Hành trang)** - View and manage inventory
3. **Equipment (Trang bị)** - Manage equipped items and character stats
4. **Skills (Kỹ năng)** - View and upgrade skills
5. **Quest (Nhiệm vụ)** - View active and completed quests
6. **Recharge (Nạp xu)** - In-app purchases
7. **Clan (Bang hội)** - Clan management
8. **Activity (Hoạt động)** - Event activities
9. **Upgrade (Đập đồ)** - Item upgrade system
10. **Transform (Chuyển hóa)** - Item transformation
11. **Create Mount (Tạo phi phong)** - Mount creation
12. **Pet** - Pet management

## Equipment Slots

### Available Equipment Positions
- HAT (Nón) - Head slot
- CHAIN (Dây chuyền) - Necklace
- JEWELRY (Ngọc bội) - Amulet
- RING_LEFT (Nhẫn trái) - Left ring
- RING_RIGHT (Nhẫn phải) - Right ring
- BODY (Áo) - Body armor
- BELT (Đai) - Belt
- GLOVE (Găng tay) - Gloves
- SHOES (Giày) - Shoes
- WEAPON (Vũ khí) - Weapon
- WING (Phi phong) - Wings
- MOUNT (Thú cưỡi) - Mount
- COSTUME (Thời trang) - Costume
- FOOD (Đồ ăn) - Food
- PET - Pet companion

## Refactoring Benefits

### Before Refactoring
- Magic numbers scattered throughout code
- Duplicate background painting code (50+ lines repeated)
- Hard-coded color values
- Switch statements for equipment positions
- No centralized documentation

### After Refactoring
- ✅ Named constants for all tab indices
- ✅ Centralized color management
- ✅ Reusable helper methods
- ✅ Type-safe enums
- ✅ Equipment position helper with clear logic
- ✅ Reduced code duplication (50+ lines → 1 method call)
- ✅ Better documentation
- ✅ Easier maintenance

## Usage Examples

### Using MenuTab Enum
```java
// Before
if (indexMainTab == 0) { /* shop logic */ }

// After - More readable
MenuTab currentTab = MenuTab.fromId(indexMainTab);
if (currentTab == MenuTab.SHOP) { /* shop logic */ }
```

### Using MenuPaintHelper
```java
// Before (30+ lines of repeated code)
g.setColor(-13232632);
g.fillRect(x, y, w, h, false);
g.setColor(-1596632);
// ... many more lines

// After (1 line)
MenuPaintHelper.paintMenuBackground(g, x, y, w, h);
```

### Using EquipmentSlot
```java
// Before (50+ lines switch statement)
public static int getPos(ItemTemplate it, int pos) {
    switch (it.type) {
        case 0: return 0;
        case 1: return 1;
        // ... 15 more cases
    }
}

// After (delegates to helper)
public static int getPos(ItemTemplate it, int pos) {
    return EquipmentSlot.getEquipmentPosition(it, pos);
}
```

## Code Organization

```
core/src/mchien/code/screen/screen/
├── MenuConstants.java      - Tab and position constants
├── MenuColors.java         - Color constants
├── MenuTab.java           - Tab enum
├── EquipmentSlot.java     - Equipment position helper
├── MenuPaintHelper.java   - Common painting operations
├── MenuRenderer.java      - Simple menu rendering
├── MainMenu.java          - Main menu (refactored)
├── MainMenu2.java         - Alternative menu
├── Menu.java              - NPC menu
├── MenuSelectItem.java    - Quick menu
└── Menu2.java             - Utility menu
```

## Future Improvements
- Extract shop rendering logic to ShopRenderer class
- Extract inventory rendering to InventoryRenderer class
- Extract equipment rendering to EquipmentRenderer class
- Extract skill rendering to SkillRenderer class
- Extract quest rendering to QuestRenderer class
- Add unit tests for helper classes
- Further reduce code duplication in paint methods
