package mchien.code.screen.screen.goinap;

import javax.microedition.lcdui.Image;

import lib.mGraphics;
import lib.mVector;
import lib2.mFont;
import mchien.code.main.GameCanvas;
import mchien.code.model.ImageIcon;
import mchien.code.model.Item;
import mchien.code.model.Scroll;
import mchien.code.model.ScrollResult;
import mchien.code.network.GameService;
import mchien.code.screen.Res;
import mchien.code.screen.screen.GameData;
import mchien.code.screen.screen.GameScr;
import mchien.code.screen.screen.InfoTextShow;
import mchien.code.screen.screen.MainMenu;

public class BoxItem {
    public final short idGoiNap;
    public final byte idBox;
    private Image imgBorder, imgTile;
    public Image imgBtn;
    public short idIconBorder, idIconTile;
    public short x, y, w, h, wScroll;
    public short xBtn, yBtn, wBtn, hBtn;
    private long timeLoadImg;

    public mVector items;
    private Scroll cmrItem;
    public Scroll cmrShowText;
    private int selectedItem;
    public boolean isActiveBtn;
    public short iconBtn;
    public String textBtn;
    private boolean loadSuccess;
    private mVector showText;
    private int numItemStart;
    private int laststar;
    private boolean isHalfstart;
    private int numItemStart2;
    private int laststar2;
    private boolean isHalfstart2;
    public boolean isShowText;
    private int totalInfoshow;
    private short xShowText, yShowText, wShowText, hShowText, disString;
    public boolean isPointerDown;

    public BoxItem(int idGoiNap, int idBox, int iconBorder, int idIconTile, int x, int y){
        this.idGoiNap = (short)idGoiNap;
        this.idBox = (byte) idBox;
        this.idIconBorder = (short) iconBorder;
        this.idIconTile = (short) idIconTile;
        this.x = (short) x;
        this.y = (short) y;
        this.w = 185;
        this.h = 31;

        wBtn = 60;
        hBtn = 25;
        xBtn = (short) (x + w - wBtn - 2);
        yBtn = (short) (y + h/2 - hBtn/2);

        wShowText = (short) (GameCanvas.h/3);
        hShowText = (short) (GameCanvas.h/3);
        disString = 12;

        wScroll = (short) (w- wBtn - 10);
        cmrItem = new Scroll();
        cmrShowText = new Scroll();
        items = new mVector("Box Item");
        showText = new mVector("Show Text");
        loadSuccess = false;
    }

    public void paint(mGraphics g, boolean isPaintSelect){
        GameCanvas.resetTrans(g);
        if(imgBorder != null)
            g.drawImage(imgBorder, x, y, 0, false);
        if(imgBorder != null)
            paintListItem(g, isPaintSelect);
        if(imgTile != null)
            g.drawImage(imgTile, x + 8, y - 7, 0, false);
        if(imgBtn != null){
            g.drawImage(imgBtn, xBtn, yBtn, 0, false);
            int xT = xBtn+wBtn / 2;
            int yT = yBtn+hBtn / 2;
            mFont.tahoma_7b_yellow.drawString(g, textBtn, xT, yT - 5, mGraphics.VCENTER, false);
        }
    }

    private void paintListItem(mGraphics g, boolean isPaintSelect){
        int maxInventory = items.size();
        mFont.tahoma_7_white.drawString(g, " ", -10, -10, 1, false);

        int size  = 26;
        int colum = maxInventory ;
        int t = Math.min(4, colum);
        int xbg = x+7;
        int ybg = y+3;
        this.cmrItem.setStyle(colum, size, xbg , ybg, wScroll, size , false, colum);
        g.ClipRec(this.cmrItem.xPos - 1, this.cmrItem.yPos, this.cmrItem.width + 2, this.cmrItem.height + 2);
        this.cmrItem.setClip(g, this.cmrItem.xPos - 1, this.cmrItem.yPos, this.cmrItem.width + 2, this.cmrItem.height + 2);
        int i4 = 0;

        while (i4 < maxInventory) {
            g.setColor(-16114410);
            g.fillRect(xbg + i4 % colum * size , ybg + i4 / colum * size, size, size, true);
            g.setColor(-14458807);
            g.drawRect(xbg + i4 % colum * size, ybg + i4 / colum * size, size, size, true);
            ++i4;
        }
        i4 = 0;
        while (i4 < maxInventory) {
            if (i4 < items.size()) {
                Item it = (Item)items.elementAt(i4);
                it.paint(g, xbg + i4 % colum * size + size / 2 +1, ybg + i4 / colum * size + size / 2+1, true);
            }
            ++i4;
        }


        if (isPaintSelect && selectedItem > -1 && selectedItem < items.size()) {
            g.drawImage(GameScr.imgfocus[3], xbg + selectedItem % colum * size + size / 2, ybg + selectedItem / colum * size + size / 2, 3, true);
        }

        mFont.tahoma_7_white.drawString(g, " ", -10, -10, 1, true);
        GameCanvas.resetTrans(g);
        mGraphics.resetTransAndroid(g);
        g.restoreCanvas();

    }

    public void update(){
        if(imgBorder == null)
            imgBorder = loadImage(idIconBorder);
        else if(imgTile == null)
            imgTile = loadImage(idIconTile);
        else if(imgBtn == null){
            imgBtn = loadImage(iconBtn);
        }
//        else if(imgBtnSharder == null){
//            int w = imgBtn._getWidth();
//            int h = imgBtn._getHeight();
//            int[] rgb = new int[w * h];
//            imgBtn.getRGB(rgb, 0, w, 0, 0, w, h);
//            imgBtnSharder = Image.createImage(rgb, w, h);
//        }
        else{
            if(!loadSuccess)
                loadSuccess = true;
            cmrItem.updatecm();
            if(isShowText)
                cmrShowText.updatecm();
        }
    }
    public boolean updateKey(){
        if(!loadSuccess)
            return false;
        ScrollResult r = cmrItem.updateKey();
        if(r.isDowning && !isPointerDown){
            isPointerDown = true;
        }
        else if(r.isFinish){
            selectedItem = r.selected;
            isPointerDown = false;
            if (selectedItem > -1 && selectedItem < items.size()){
                doShowInfoItem((Item)items.elementAt(selectedItem));
                setLocationShowText(cmrItem.pointerDownLastX[0], y + h/2);
                return true;
            }
            else
                closeText();
        }
        else if(!r.isDowning && isPointerDown){
            selectedItem = -1;
            isPointerDown = false;
            closeText();
        }
        cmrItem.updateKey();

        if(GameCanvas.isTouch && isActiveBtn){
            if (GameCanvas.isPointer(xBtn, yBtn, wBtn, hBtn, 0) && GameCanvas.canTouch()) {
                if (GameCanvas.isPointerJustRelease[0] && GameCanvas.isPointerClick[0]) {
                    GameCanvas.isPointerClick[0] = false;
                    buyGoiNap();
                }
            }
        }

        return false;
    }

    private void buyGoiNap() {
        GameService.gI().buyGoiNap(this.idGoiNap, this.idBox);
    }

    private Image loadImage(int id){
        long time = System.currentTimeMillis();
        if(time - timeLoadImg > 200){
            timeLoadImg = time;
            ImageIcon img = GameData.getImgIcon((short)(id + Res.ID_IMAGE_GOINAP));
            if(img != null && img.img != null)
                return img.img;
        }
        return null;
    }

    public void doShowInfoItem(Item item) {
        this.setShowText(item.getInfoItemShow(null, true), item, true, false);
    }

    private void setShowText(mVector text, Item item, boolean isTile, boolean isSetGroup) {
        String[] arr =null;
        if (text == null && arr == null) {
            return;
        }
        this.numItemStart = 0;
        this.laststar = 0;
        this.isHalfstart = false;
        this.numItemStart2 = 0;
        this.laststar2 = 0;
        this.isHalfstart2 = false;
        this.closeText();
        this.isShowText = true;

        this.totalInfoshow = 0;
        if (arr == null) {
            String[] data = null;
            int w0 = 0;
            this.showText = isSetGroup ? MainMenu.setGroupFull(text) : text;
            try {
                int i = 0;
                while (i < this.showText.size()) {
                    InfoTextShow info = (InfoTextShow)text.elementAt(i);
                    if (info != null && info.info != null) {
                        if (info.info[0] != null) {
                            if (isTile && i == 0) {
                                data = mFont.tahoma_7b_black.splitFontArray(info.info[0], this.wShowText - 20);
                                info.setInfo(data, Item.getColorPaintName(info.color));
                            } else {
                                data = mFont.tahoma_7_white.splitFontArray(info.info[0], this.wShowText - 20);
                                info.setInfo(data, Item.getColorPaintOption(info.color));
                            }
                        }
                        this.totalInfoshow = (byte)(this.totalInfoshow + info.info.length);
                        int ww = info.getMaxWidth();
                        w0 = w0 > ww ? w0 : ww;
                    } else {
                        this.totalInfoshow = (byte)(this.totalInfoshow + 1);
                    }
                    ++i;
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        boolean isitemPhiPhong = item.getTypeItem() == 55;

        if (isitemPhiPhong) {
            if (this.numItemStart > 16) {
                this.numItemStart2 = this.numItemStart - 16;
                this.numItemStart = 16;
            }
            this.laststar2 = this.numItemStart2;
            if (this.numItemStart2 % 2 != 0) {
                this.isHalfstart2 = true;
            }
            this.numItemStart2 /= 2;
            if (this.laststar2 == 1) {
                this.numItemStart2 = this.laststar2;
            }
        }
        if (this.numItemStart >= 15 && !isitemPhiPhong) {
            this.numItemStart = 16;
        }
        this.laststar = this.numItemStart;
        if (this.numItemStart % 2 != 0) {
            this.isHalfstart = true;
        }
        this.numItemStart /= 2;
        if (this.laststar == 1) {
            this.numItemStart = this.laststar;
        }
    }
    public void closeText() {
        isShowText = false;
        this.cmrShowText.clear();
    }

    public void paintShowText(mGraphics g) {
        if(!isShowText)
            return;
        int sw = wShowText / 32;
        int sh = hShowText / 32;
        GameCanvas.resetTrans(g);
        mFont.tahoma_7_white.drawString(g, " ", -10, -10, 1, false);
        g.setClip(this.xShowText, this.yShowText, this.wShowText, this.hShowText);
        g.ClipRec(this.xShowText, this.yShowText, this.wShowText, this.hShowText);
        for(int _w = 0; _w < sw +1; ++_w) {
            for(int _h = 0; _h < sh+1; ++_h) {
                g.drawImage(GameScr.imgBgMainMenu, this.xShowText + _w * 32, this.yShowText + _h * 32-5, 0, true);
            }
        }

//        mFont.tahoma_7_white.drawString(g, " ", -10, -10, 1, false);
        this.paintBgSub(g, this.xShowText, this.yShowText, wShowText, this.hShowText, true, true);

        mFont.tahoma_7_white.drawString(g, " ", -10, -10, 1, false);
        GameCanvas.resetTrans(g);
        this.cmrShowText.setStyle(this.totalInfoshow + 1, this.disString, this.xShowText, this.yShowText, this.wShowText, this.hShowText - 8, true, 0);
        g.ClipRec(this.cmrShowText.xPos, this.cmrShowText.yPos + 2, this.cmrShowText.width, this.cmrShowText.height + 4);
        this.cmrShowText.setClip(g, this.cmrShowText.xPos, this.cmrShowText.yPos + 2, this.cmrShowText.width, this.cmrShowText.height + 4);

        int i = 0;
        int yy=0;
        while (i < this.showText.size()) {
            InfoTextShow info = (InfoTextShow)this.showText.elementAt(i);
            if (info != null && info.info != null) {
                mFont f = info.f;
                if (i == 1) {
                    int k;
                    yy += this.disString;
                    if (this.laststar > 1) {
                        k = 0;
                        while (k < this.numItemStart) {
                            g.drawRegion(GameScr.imgStart, 0, 0, 10, 10, 0, this.xShowText + 15 + k * 11, this.yShowText + yy + 3, 3, true);
                            ++k;
                        }
                    }
                    if (this.isHalfstart) {
                        g.drawRegion(GameScr.imgStart, 0, 40, 10, 10, 0, this.xShowText + 15 + (this.laststar > 1 ? this.numItemStart * 11 : 0), this.yShowText + yy + 3, 3, true);
                    }
                    if (this.numItemStart2 > 0) {
                        yy += this.disString;
                        if (this.laststar2 > 1) {
                            k = 0;
                            while (k < this.numItemStart2) {
                                g.drawRegion(GameScr.imgStart, 0, 0, 10, 10, 0, this.xShowText + 15 + k * 11, this.yShowText + yy + 3, 3, true);
                                ++k;
                            }
                        }
                        if (this.isHalfstart2) {
                            g.drawRegion(GameScr.imgStart, 0, 40, 10, 10, 0, this.xShowText + 15 + (this.laststar2 > 1 ? this.numItemStart2 * 11 : 0), this.yShowText + yy + 3, 3, true);
                        }
                    }
                }
                if (info.info != null && info.info[info.info.length - 1] != null && f != null && info.info[info.info.length - 1] != null) {
                    if (info.idCompare > -1 && info.idCompare == 3) {
                        f = mFont.tahoma_7_gray;
                    }
                    int ws = f.getWidth(info.info[info.info.length - 1]);
                    int j = 0;
                    while (j < info.info.length) {
                        f.drawString(g, info.info[j], this.xShowText + 10, this.yShowText + 10 + yy, 0, true);
                        yy += this.disString;
                        ++j;
                    }
                    if (info.idCompare > -1 && info.idCompare != 3) {
                        g.drawImage(GameScr.imgSo[info.idCompare], this.xShowText + 10 + ws + 1, this.yShowText + 12 + yy - this.disString, 0, false);
                    }
                }
            } else {
                g.setColor(-1136094);
                g.fillRect(this.xShowText + 15, this.yShowText + 10 + (yy += this.disString / 2), this.wShowText - 30, 1, true);
                yy += this.disString / 2;
            }
            ++i;
        }
        mGraphics.resetTransAndroid(g);
        g.restoreCanvas();
        GameCanvas.resetTrans(g);
    }
    public void paintBgSub(mGraphics g, int x, int y, int w, int h, boolean isBoder, boolean useClip) {
        g.setColor(-9751532);
        g.drawRect(x, y, w, h, useClip);
        g.drawRect(x + 2, y + 2, w - 4, h - 4, useClip);


        g.setColor(-4034289);
        g.drawRect(x + 1, y + 1, w - 2, h - 2, useClip);

        if (isBoder) {
            g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 7, x + w + 1, y + h + 1, mGraphics.BOTTOM | mGraphics.RIGHT, useClip);
            g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 1, x, y + h + 1, mGraphics.BOTTOM | mGraphics.LEFT, useClip);
            g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 0, x, y, mGraphics.TOP | mGraphics.LEFT, useClip);
            g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 2, x + w + 1, y, mGraphics.TOP | mGraphics.RIGHT, useClip);
        }

    }

    public void setLocationShowText(int xSelect, int ySelect){
        int x = xSelect;
        int y = ySelect;

        if(GameCanvas.w < x + wShowText){
            int nx = x - wShowText;
            if(nx < 0){
                int r = x + wShowText - GameCanvas.w;
                int l = Math.abs(nx);
                if(r<l)
                    nx =  x + wShowText;
            }
            x = nx;
        }
        if(GameCanvas.h < y + hShowText){
            int nx = y - hShowText;
            if(nx < 0){
                int r = y + hShowText - GameCanvas.h;
                int l = Math.abs(nx);
                if(r<l)
                    nx =  y + hShowText;
            }
            x = nx;
        }
        xShowText = (short) x;
        yShowText = (short) y;

    }
}
