package mchien.code.screen.screen.baucua;

import mchien.code.main.GameCanvas;

/**
 * Ví dụ sử dụng BauCua game trong ứng dụng thực tế
 * Server-controlled flow: Server chủ động mở UI và gửi room data
 * 
 * @author Monkey
 */
public class BauCuaUsageExample {
    
    /**
     * Mở BauCua UI khi server yêu cầu (từ ReadMessenge)
     * Được gọi khi server gửi lệnh mở BauCua UI
     */
    public static BauCua openBauCuaUI() {
        BauCuaManager manager = BauCuaManager.gI();
        BauCua game = manager.createGame();
        
        // Switch to BauCua screen
        game.switchToMe(GameCanvas.currentScreen);
        GameCanvas.currentScreen = game;
        
        System.out.println("BauCua UI opened by server request");
        return game;
    }
    
    /**
     * Xử lý khi player thoát game (về main menu)
     */
    public static void onPlayerExitGame() {
        BauCuaManager manager = BauCuaManager.gI();
        manager.cleanup(); // Cleans game data, preserves images
        
        // Switch back to main game screen
        GameCanvas.gameScr.switchToMe(GameCanvas.currentScreen);
        System.out.println("Player exited BauCua - game data cleaned, images preserved");
    }
    
    /**
     * Xử lý khi app shutdown hoàn toàn
     */
    public static void onAppShutdown() {
        BauCuaManager.destroyInstance(); // Full cleanup including images
        System.out.println("App shutdown - all BauCua resources destroyed including images");
    }
}
