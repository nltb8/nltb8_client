/*
 * Decompiled with CFR 0.152.
 */
package mchien.code.screen.screen;

import mchien.code.main.GameCanvas;
import mchien.code.model.Char;
import mchien.code.model.IActionListener;
import mchien.code.model.ImageIcon;
import mchien.code.model.Item;
import mchien.code.model.Scroll;
import mchien.code.model.ScrollResult;
import mchien.code.model.ServerInfo;
import mchien.code.model.T;
import mchien.code.model.mCommand;
import mchien.code.network.GameService;
import mchien.code.screen.Res;
import mchien.code.screen.screen.FontTeam;
import mchien.code.screen.screen.GameData;
import mchien.code.screen.screen.GameScr;
import mchien.code.screen.screen.ScreenTeam;
import lib.mGraphics;
import lib.mSystem;
import lib.mVector;
import lib2.mFont;

public class ScreenClan
extends ScreenTeam
implements IActionListener {
    public static ScreenClan me;
    public int sX;
    public int sY;
    public int sW;
    public int sH;
    public int size;
    public int numtab;
    public int focus;
    public int nitem;
    public int sizeH;
    public int indexMember;
    public int size2;
    public int focusSetting;
    public String tile;
    public static mVector infoClan;
    public static mVector vecmember;
    public mVector vecCmdSetting = new mVector();
    public static String[] nameCmd;
    public static String[] nameOption;
    public static final byte INFO = 0;
    public static final byte MEMBER = 1;
    public static final byte SETTING = 2;
    public mCommand cmdClose;
    public mCommand cmd1;
    public mCommand cmd2;
    public mCommand cmd3;
    public mCommand cmd4;
    public mCommand cmd5;
    public mCommand cmd6;
    Scroll scrmenu = new Scroll();
    public static int iTemSize;
    public int focusmenu;
    Scroll scr = new Scroll();
    Scroll scrMember = new Scroll();
    public int[] xTab;

    static {
        infoClan = new mVector();
        vecmember = new mVector();
    }

    public static ScreenClan gI() {
        return me == null ? (me = new ScreenClan()) : me;
    }

    @Override
    public void switchToMe(ScreenTeam lastSCR) {
        super.switchToMe(lastSCR);
        this.init();
    }

    @Override
    public void init() {
        this.vecCmdSetting.removeAllElements();
        this.nitem = 0;
        this.sW = GameCanvas.w - 40;
        if (this.sW > GameCanvas.w) {
            this.sW = GameCanvas.w - 20;
        }
        this.sH = GameCanvas.h - 70;
        if (this.sH > GameCanvas.h) {
            this.sH = GameCanvas.h / 2 + GameCanvas.h / 6;
        }
        this.sX = GameCanvas.w / 2 - this.sW / 2;
        this.sY = GameCanvas.h / 2 - this.sH / 2;
        this.tile = T.banghoi;
        this.cmdClose = new mCommand("", this, 0);
        this.cmdClose.setindexImage(4);
        this.cmdClose.setXY(this.sX + this.sW - 22, this.sY - 26);
        this.numtab = 3;
        this.size = (this.sW - 4) / this.numtab;
        this.focus = 0;
        this.xTab = new int[this.numtab];
        int i = 0;
        while (i < this.numtab) {
            this.xTab[i] = this.sX + i * this.size + 2;
            ++i;
        }
        this.nitem += iTemSize;
        int wslip = 0;
        if (!GameCanvas.isTouch) {
            wslip = 20;
        }
        int i2 = 0;
        while (i2 < infoClan.size()) {
            ServerInfo sv = (ServerInfo)infoClan.elementAt(i2);
            if (sv != null) {
                int j = 0;
                while (j < sv.info.length) {
                    String[] arr = mFont.tahoma_7_white.splitFontArray(sv.info[j], this.sW / 3 + wslip - 20);
                    if (arr != null && arr.length > 1) {
                        this.nitem += arr.length - 1;
                    }
                    ++j;
                }
                String[] arr2 = mFont.tahoma_7b_white.splitFontArray(this.tile, this.sW / 3 + wslip - 35);
                if (arr2 != null && arr2.length > 1) {
                    this.nitem += arr2.length - 1;
                }
            }
            ++i2;
        }
        this.sizeH = 50;
        this.size2 = (this.sW - this.sW / 3) / 4;
        int num = 3;
        int yin = 25;
        int hclip = this.sH - 10;
        int hs = 20;
        int hsai = hclip - 20 - yin;
        int i3 = 0;
        while (i3 < nameOption.length) {
            mCommand cmd = new mCommand(nameOption[i3], this, 2);
            cmd.setPos(this.sX + this.sW / 3 + i3 * 85, this.sH - (this.sH - hsai) / 2 + 11);
            this.vecCmdSetting.addElement(cmd);
            ++i3;
        }
    }

    @Override
    public void paint(mGraphics g) {
        ImageIcon img;
        if (this.lastSCR != null) {
            this.lastSCR.paint(g);
        }
        Res.paintDlgDragonFullNew(g, this.sX, this.sY, this.sW, this.sH, 60, 60, GameScr.imgBk[0], false);
        g.setColor(-9751532);
        g.fillRect(this.sX, this.sY - 28, this.sW, 28, false);
        int i = 0;
        while (i < 3) {
            g.setColor(Res.nColor[i]);
            g.drawRect(this.sX + i, this.sY - 28 + i, this.sW - i * 2, 28 - i * 2, false);
            ++i;
        }
        this.paintinfo(g);
        i = 0;
        while (i < 7) {
            g.drawRegion(GameScr.imgBoder[5], 0, 25, 12, 25, 0, this.sX + this.sW / 2 - 42 + i * 12, this.sY - 14, mGraphics.VCENTER | mGraphics.LEFT, false);
            ++i;
        }
        g.drawRegion(GameScr.imgBoder[5], 0, 0, 12, 25, 0, this.sX + this.sW / 2 - 44, this.sY - 14, mGraphics.VCENTER | mGraphics.LEFT, false);
        int ys = 0;
        if (!mSystem.isj2me) {
            ys = -1;
        }
        g.drawRegion(GameScr.imgBoder[5], 0, 0, 12, 25, 2, this.sX + this.sW / 2 + 44, this.sY - 14 + 1 + ys, mGraphics.VCENTER | mGraphics.RIGHT, false);
        FontTeam.fontTile.drawString(g, this.tile, this.sX + this.sW / 2, this.sY - 20 + 1, 2, false);
        g.setColor(-9751532);
        g.fillRect(this.sX + 2, this.sY + 1, this.sW - 4, 21, false);
        g.setColor(-4034289);
        g.drawRect(this.sX + 2, this.sY + 1, this.sW - 3, 21, false);
        int i2 = 0;
        while (i2 < T.infoClan.length) {
            mFont.tahoma_7b_yellow.drawString(g, T.infoClan[i2], this.sX + this.sW / 3 + i2 * this.size2 + 2 + this.size2 / 2 - 5 + 10, this.sY + 1 + 6, 2, false);
            ++i2;
        }
        mFont.tahoma_7b_yellow.drawString(g, T.tClan[0], this.sX + 2 + this.size / 2 - 5, this.sY + 1 + 6, 2, false);
        g.fillRect(this.sX + 1 + this.sW / 3 - 10, this.sY + 1, 1, this.sH - 1, false);
        if (GameCanvas.isTouch && !GameCanvas.menu2.isShow) {
            this.cmdClose.paint(g);
        }
        if (GameScr.mainChar.idClan != -1 && (img = GameData.getImgIcon((short)(GameScr.mainChar.idIconClan + Res.ID_ICON_CLAN))) != null && img.img != null) {
            Item.eff_UpLv.paintUpgradeEffect(this.sX + 2 + this.size / 2 - 5 + 1, this.sY - 20 + 1 + 30 + 2 + 22, 15, 16, g, 1);
            g.drawImage(img.img, this.sX + 2 + this.size / 2 - 5 + 1, this.sY - 20 + 1 + 30 + 2 + 22, 3, false);
        }
    }

    public void paintinfo(mGraphics g) {
        GameCanvas.resetTrans(g);
        int yin = 25;
        int hclip = this.sH - 10;
        int hs = 20;
        GameCanvas.resetTrans(g);
        this.scr.setStyle(this.nitem + 1, 16, this.sX + 6, this.sY + 5 + hs + 20, this.sW - 12, hclip - 20 - yin, true, 1);
        g.ClipRec(this.sX + 6, this.sY + 5 + hs + 20, this.sW - 12, hclip - 20 - yin);
        this.scr.setClip(g, this.sX + 6, this.sY + 5 + hs + 20, this.sW - 12, hclip - 20 - yin);
        int yy = this.sY + 10;
        int wslip = 0;
        if (!GameCanvas.isTouch) {
            wslip = 20;
        }
        int i = 0;
        while (i < infoClan.size()) {
            ServerInfo sv = (ServerInfo)infoClan.elementAt(i);
            if (sv != null) {
                String[] arr2 = mFont.tahoma_7_white.splitFontArray(sv.tile, this.sW / 3 + wslip - 35);
                int k = 0;
                while (k < arr2.length) {
                    mFont.tahoma_7b_white.drawString(g, arr2[k], this.sX + 10 - 3, yy + 10 + yin, 0, true);
                    yy += 15;
                    ++k;
                }
                int j = 0;
                while (j < sv.info.length) {
                    String[] arr = mFont.tahoma_7_white.splitFontArray(sv.info[j], this.sW / 3 + wslip - 20);
                    int k2 = 0;
                    while (k2 < arr.length) {
                        mFont.tahoma_7_white.drawString(g, arr[k2], this.sX + 12 - 3, yy + 10 + yin, 0, true);
                        yy += 15;
                        ++k2;
                    }
                    ++j;
                }
            }
            ++i;
        }
        mGraphics.resetTransAndroid(g);
        g.restoreCanvas();
        GameCanvas.resetTrans(g);
        int yy2 = this.sY + 10;
        this.scrMember.setStyle(vecmember.size(), 35, this.sX + 1 + this.sW / 3 - 10, this.sY + hs + 20, this.sW - (1 + this.sW / 3 - 10), this.sH - 70 - 40, true, 1);
        g.ClipRec(this.sX + 1 + this.sW / 3 - 10, this.sY + hs + 20, this.sW - (1 + this.sW / 3 - 10), this.sH - 70 - 40);
        this.scrMember.setClip(g, this.sX + 1 + this.sW / 3 - 10, this.sY + hs + 20, this.sW - (1 + this.sW / 3 - 10), this.sH - 70 - 40);
        int ylech = 8;
        int i2 = 0;
        while (i2 < vecmember.size()) {
            Char c = (Char)vecmember.elementAt(i2);
            if (c != null) {
                if (this.focus == i2) {
                    Res.paintFocus(g, this.sX + 1 + this.sW / 3 - 10, this.sY + hs + 20 + i2 * 35 + 1, this.sW - (1 + this.sW / 3 - 10) - 3);
                }
                c.paintHead(g, this.sX + 1 + this.sW / 3 + 3, yy2 + 10 + 25 + 55 + 1);
                mFont.tahoma_7_white.drawString(g, c.name, this.sX + this.sW / 3 + 0 * this.size2 + 2 + this.size2 / 2 - 5 + 10, yy2 + 10 + 25, 2, true);
                mFont.tahoma_7_white.drawString(g, "Lv: " + c.lv, this.sX + this.sW / 3 + 0 * this.size2 + 2 + this.size2 / 2 - 5 + 10, yy2 + 10 + 25 + 16, 2, true);
                mFont.tahoma_7_white.drawString(g, c.chucvu, this.sX + this.sW / 3 + 1 * this.size2 + 2 + this.size2 / 2 - 5 + 10, yy2 + 10 + 25 + ylech, 2, true);
                mFont.tahoma_7_white.drawString(g, String.valueOf(c.conghien), this.sX + this.sW / 3 + 2 * this.size2 + 2 + this.size2 / 2 - 5 + 10, yy2 + 10 + 25 + ylech, 2, true);
                g.drawRegion(GameScr.statusInfo, 0, 11 * (c.online == 0 ? 2 : 0), 11, 11, 0, this.sX + this.sW / 3 + 3 * this.size2 + 2 + this.size2 / 2 - 5 + 10, yy2 + 10 + 25 + 4 + ylech, 3, true);
                yy2 += 35;
            }
            g.setColor(Res.nColor[1]);
            if (i2 < vecmember.size() - 1) {
                g.fillRect(this.sX + 1 + this.sW / 3 - 10, this.sY + hs + 20 + i2 * 35 + 35, this.sW - (1 + this.sW / 3 - 10), 1, true);
            }
            ++i2;
        }
        mGraphics.resetTransAndroid(g);
        g.restoreCanvas();
        GameCanvas.resetTrans(g);
        g.setColor(-4034289);
        g.fillRect(this.sX + 1 + this.sW / 3 - 10, this.sY + hs + 20 + (this.sH - 70 - 40), this.sW - (1 + this.sW / 3 - 10), 1, false);
        this.paintcmd(g);
    }

    public void paintcmd(mGraphics g) {
        g.fillRect(0, 0, 0, 0, false);
        GameCanvas.resetTrans(g);
        int yin = 25;
        int hclip = this.sH - 10;
        this.scrmenu.setStyle(this.vecCmdSetting.size(), 90, this.sX + 1 + this.sW / 3 - 10, this.sY + 20 + 20 + (this.sH - 70 - 40), this.sW - (1 + this.sW / 3 - 10), this.sH + 22 - (hclip - 20 - yin), false, 1);
        g.ClipRec(this.sX + 1 + this.sW / 3 - 10, this.sY + 20 + 20 + (this.sH - 70 - 40), this.sW - (1 + this.sW / 3 - 10) - 4, this.sH + 22 - (hclip - 20 - yin));
        this.scrmenu.setClip(g, this.sX + 1 + this.sW / 3 - 10, this.sY + 20 + 20 + (this.sH - 70 - 40), this.sW - (1 + this.sW / 3 - 10) - 4, this.sH + 22 - (hclip - 20 - yin));
        int i = 0;
        while (i < this.vecCmdSetting.size()) {
            mCommand cmd = (mCommand)this.vecCmdSetting.elementAt(i);
            if (cmd != null) {
                cmd.paint(g, true);
            }
            ++i;
        }
        mGraphics.resetTransAndroid(g);
        g.restoreCanvas();
        GameCanvas.resetTrans(g);
    }

    @Override
    public void update() {
        if (GameCanvas.menuSelectitem.showMenu) {
            return;
        }
        if (GameCanvas.currentDialog != null) {
            return;
        }
        if (GameCanvas.menu2.isShow || GameCanvas.menu.showMenu || GameCanvas.currentDialog != null) {
            return;
        }
        if (GameCanvas.isKeyPressed(13)) {
            this.cmdClose.performAction();
        }
        if (GameCanvas.isTouch && this.cmdClose != null && !GameCanvas.menu2.isShow && this.getCmdPointerLast(this.cmdClose)) {
            GameCanvas.isPointerJustRelease[0] = false;
            if (this.cmdClose != null) {
                this.cmdClose.performAction();
            }
        }
        this.scr.updatecm();
        if (GameCanvas.isPointer(this.sX, 0, this.sW / 3 - 10, GameCanvas.h, 0)) {
            ScrollResult scrollResult = this.scr.updateKey();
        }
        this.scrMember.updatecm();
        ScrollResult r2 = this.scrMember.updateKey();
        if (GameCanvas.isPointerClick[0] && GameCanvas.isPointer(this.sX + 1 + this.sW / 3 - 10, this.sY + 1, this.sW - (1 + this.sW / 3 - 10), this.sH - 70, 0)) {
            if (this.focus != this.scrMember.selectedItem) {
                this.focus = this.scrMember.selectedItem;
            } else if (this.focus >= 0 && this.focus < vecmember.size()) {
                mVector veccmd = new mVector();
                int i = 0;
                while (i < nameCmd.length) {
                    mCommand cmd = new mCommand(nameCmd[i], this, 1);
                    veccmd.addElement(cmd);
                    ++i;
                }
                GameCanvas.menu.startAt(veccmd, 2);
                GameCanvas.isPointerClick[0] = false;
            }
        }
        if (!GameCanvas.menu.showMenu) {
            this.scrmenu.updatecm();
            ScrollResult r3 = this.scrmenu.updateKey();
            int yin = 25;
            int hclip = this.sH - 10;
            if (GameCanvas.isPointer(this.sX + 1 + this.sW / 3 - 10, this.sY + 20 + 20 + (this.sH - 70 - 40), this.sW - (1 + this.sW / 3 - 10), this.sH + 22 - (hclip - 20 - yin), 0) && this.scrmenu.selectedItem >= 0 && this.scrmenu.selectedItem < this.vecCmdSetting.size()) {
                mCommand cmd;
                if (this.focusmenu != this.scrmenu.selectedItem) {
                    this.focusSetting = this.focusmenu = this.scrmenu.selectedItem;
                }
                if ((cmd = (mCommand)this.vecCmdSetting.elementAt(this.focusmenu)) != null && !cmd.isFocus) {
                    cmd.isFocus = true;
                }
                if (GameCanvas.isPointerClick[0]) {
                    cmd.performAction();
                }
            }
        }
        if (this.lastSCR != null) {
            this.lastSCR.update();
        }
    }

    @Override
    public void perform(int idAction, Object p) {
        switch (idAction) {
            case 0: {
                this.lastSCR.switchToMe();
                this.vecCmdSetting.removeAllElements();
                vecmember.removeAllElements();
                nameCmd = null;
                infoClan.removeAllElements();
                break;
            }
            case 1: {
                GameService.gI().RequestInfoClan((byte)this.focus, 15);
                break;
            }
            case 2: {
                GameService.gI().RequestInfoClan((byte)this.focusSetting, 14);
                int i = 0;
                while (i < this.vecCmdSetting.size()) {
                    mCommand cmd = (mCommand)this.vecCmdSetting.elementAt(i);
                    if (cmd != null) {
                        cmd.isFocus = false;
                    }
                    ++i;
                }
                break;
            }
            case 3: {
                break;
            }
            case 4: {
                break;
            }
            case 5: {
                break;
            }
        }
    }

    @Override
    public void paint(mGraphics g, int idAction, int x, int y, Object paint) {
    }
}
