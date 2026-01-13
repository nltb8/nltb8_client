package mchien.code.model;

import mchien.code.main.GameCanvas;
import mchien.code.network.GameService;
import mchien.code.screen.screen.GameScr;
import mchien.code.screen.screen.MainMenu;

import java.util.Random;

/**
 * Created by khiem on 7/9/2025.
 *
 * @author Monkey
 */
public class LatHinh implements Runnable{
    public static long time = 10L;
    public final int solanlat;
    public static int lap;
    public static int check;
    public static int id;
    public static Random random = new Random();

    private boolean[] usedGroup1 = new boolean[6];  // 0-5
    private boolean[] usedGroup2 = new boolean[5];  // 6-10
    private boolean[] usedGroup3 = new boolean[4];  // 11-14

    public LatHinh(int a) {
        this.solanlat = a;
    }


    private int getRandomFromGroup(int groupIndex) {
        switch (groupIndex) {
            case 0: // Nhóm 0-5
                return getRandomFromGroup1();
            case 1: // Nhóm 6-10
                return getRandomFromGroup2();
            case 2: // Nhóm 11-14
                return getRandomFromGroup3();
            default:
                return 0;
        }
    }

    private int getRandomFromGroup1() {
        // Kiểm tra xem còn số nào chưa dùng không
        boolean hasAvailable = false;
        for (boolean used : usedGroup1) {
            if (!used) {
                hasAvailable = true;
                break;
            }
        }

        // Nếu hết số, reset lại
        if (!hasAvailable) {
            for (int i = 0; i < usedGroup1.length; i++) {
                usedGroup1[i] = false;
            }
        }

        // Tìm số ngẫu nhiên chưa dùng
        int randomNum;
        do {
            randomNum = random.nextInt(6); // 0-5
        } while (usedGroup1[randomNum]);

        usedGroup1[randomNum] = true;
        return randomNum;
    }

    private int getRandomFromGroup2() {
        boolean hasAvailable = false;
        for (boolean used : usedGroup2) {
            if (!used) {
                hasAvailable = true;
                break;
            }
        }

        if (!hasAvailable) {
            for (int i = 0; i < usedGroup2.length; i++) {
                usedGroup2[i] = false;
            }
        }

        int randomNum;
        do {
            randomNum = random.nextInt(5); // 0-4 (tương ứng với 6-10)
        } while (usedGroup2[randomNum]);

        usedGroup2[randomNum] = true;
        return randomNum + 6; // Chuyển về 6-10
    }

    private int getRandomFromGroup3() {
        boolean hasAvailable = false;
        for (boolean used : usedGroup3) {
            if (!used) {
                hasAvailable = true;
                break;
            }
        }

        if (!hasAvailable) {
            for (int i = 0; i < usedGroup3.length; i++) {
                usedGroup3[i] = false;
            }
        }

        int randomNum;
        do {
            randomNum = random.nextInt(4); // 0-3 (tương ứng với 11-14)
        } while (usedGroup3[randomNum]);

        usedGroup3[randomNum] = true;
        return randomNum + 11; // Chuyển về 11-14
    }

    @Override
    public void run() {
        try {
            if(!GameCanvas.gameScr.nameMap.equals("Tuyết Sơn Trấn")){
                GameCanvas.startOKDlg("Chỉ có thể auto ở Tuyết Sơn Trấn.");
            }else {
                GameCanvas.addNotify("Auto lật "+LatHinh.lap+" phiếu", (byte)0);
                int i = 0;
                do {
                    i+=3;
                    if(GameScr.mainChar == null||!GameScr.mainChar.haveItem("Vé thử vận may")){
                        GameCanvas.startOKDlg("Đã hết vé thử vận may");
                        return;
                    }
                    GameService.gI().Dynamic_Menu((short) 36, (byte) 0, (byte) 0);
                    for (int j = 0; j < 3; j++) {
                        Thread.sleep(time);
                        GameService.gI().buyItem(getRandomFromGroup(j), 36, 3, 2, 1);
                    }
                    MainMenu.gI().closeShop();
                }while (i<= solanlat);

            }
        } catch (Exception e) {

        }

    }
}
