package mchien.code.model;

import javax.microedition.lcdui.Image;

import mchien.code.main.GameCanvas;
import mchien.code.screen.Res;
import mchien.code.screen.screen.GameData;

public class mcCmd extends mCommand {
    public Image imgBase;
    public Image imgSelect;

    public mcCmd(String caption, IActionListener actionListener, int action) {
        super(caption, actionListener, action);
    }

    public void setXYWH(int x, int y, int w, int h){
        setXY(x, y);
        this.wCmd = w;
        this.hCmd = h;
    }

    public void setImgBase(String path){
        imgBase = GameCanvas.loadImage(path);
        img = imgBase;
    }
    public void setImgSelect(String path){
        imgSelect = GameCanvas.loadImage(path);
    }

    public void update(){
        if(this.isPointerPressInside()){
            GameCanvas.isPointerClick[0] = false;
            this.performAction();
        }

        if(isFocus && img != imgSelect && imgSelect != null)
            img = imgSelect;
        else if(!isFocus && img != imgBase && imgBase != null)
            img = imgBase;
    }
}
