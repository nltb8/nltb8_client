package mchien.code.screen.screen.goinap;

import javax.microedition.lcdui.Image;

import lib.mGraphics;
import lib.mSystem;
import lib2.mFont;
import mchien.code.main.GameCanvas;
import mchien.code.model.ImageIcon;
import mchien.code.screen.Res;
import mchien.code.screen.screen.FontTeam;
import mchien.code.screen.screen.GameData;
import mchien.code.screen.screen.GameScr;

public class mcBtn {
    public int idIconBtn;
    public Image image;
    public int x, y, w, h;
    private long timeLoadImg;
    private Runnable runnable;

    public mcBtn(int icon, int x, int y, int w, int h, Runnable runnable){
        this.idIconBtn = icon;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.runnable = runnable;
    }

    public void paint(mGraphics g){
        if(image != null)
            g.drawImage(image, x, y, 0, false);
    }

    public boolean update(){
        if(image == null)
            image = loadImage(idIconBtn);
        else {
            if (GameCanvas.isPointer(x, y, w, h, 0) && GameCanvas.canTouch()) {
                if (GameCanvas.isPointerJustRelease[0] && GameCanvas.isPointerClick[0]) {
                    GameCanvas.isPointerClick[0] = false;
                    runnable.run();
                    return true;
                }
            }
        }
        return false;
    }

    private Image loadImage(int id){
        long time = System.currentTimeMillis();

        if(time - timeLoadImg > 100){
            timeLoadImg = time;
            ImageIcon img = GameData.getImgIcon((short)(id + Res.ID_IMAGE_GOINAP));
            if(img != null && img.img != null)
                return img.img;
        }
        return null;
    }
}
