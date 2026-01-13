package mchien.code.screen.screen.baucua;

/**
 * Singleton Manager cho BauCua game
 * Quản lý lifecycle và tài nguyên của game
 */
public class BauCuaManager {
    
    private static BauCuaManager instance;
    private BauCua currentGame;
    private boolean isInitialized = false;
    
    // Private constructor cho Singleton
    private BauCuaManager() {}
    
    /**
     * Lấy instance duy nhất của manager
     */
    public static BauCuaManager gI() {
        if (instance == null) {
            instance = new BauCuaManager();
        }
        return instance;
    }
    
    /**
     * Khởi tạo game BauCua - chỉ tạo UI, không tự hiển thị menu
     * Server sẽ chủ động gửi room data sau khi tạo UI này
     */
    public BauCua createGame() {
        if (currentGame == null) {
            currentGame = new BauCua();
            isInitialized = true;
            System.out.println("BauCua UI created - waiting for server room data");
        }
        return currentGame;
    }
    
    /**
     * Lấy game hiện tại (có thể null)
     */
    public BauCua getCurrentGame() {
        return currentGame;
    }
    
    /**
     * Kiểm tra game đã được khởi tạo chưa
     */
    public boolean isGameInitialized() {
        return isInitialized && currentGame != null;
    }
    

    
    /**
     * Cleanup tài nguyên khi thoát game
     * GỌI METHOD NÀY KHI NGƯỜI CHƠI THOÁT!
     * Chỉ cleanup game data, GIỮ LẠI HÌNH ẢNH để tái sử dụng
     */
    public void cleanup() {
        if (currentGame != null) {
            // Chỉ cleanup game data, không cleanup hình ảnh
            currentGame.cleanup();
            currentGame = null;
        }
        
        // KHÔNG cleanup static resources (hình ảnh) để tái sử dụng
        // BauCua.cleanupStaticResources(); // REMOVED
        
        isInitialized = false;
        System.out.println("BauCua game data cleaned up (images preserved)");
    }
    
    /**
     * Reset về trạng thái ban đầu (không cleanup resources)
     */
    public void resetGame() {
        if (currentGame != null) {
            currentGame.resetToInitialState();
            System.out.println("BauCua game reset");
        }
    }
    
    /**
     * Destroy singleton (chỉ dùng khi app shutdown)
     * Cleanup hoàn toàn bao gồm cả hình ảnh
     */
    public static void destroyInstance() {
        if (instance != null) {
            instance.cleanup();
            
            // Chỉ cleanup hình ảnh khi app shutdown hoàn toàn
            BauCua.cleanupStaticResources();
            
            instance = null;
            System.out.println("BauCuaManager instance destroyed (including images)");
        }
    }
}
