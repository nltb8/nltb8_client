/*
 * Decompiled with CFR 0.152.
 */
package mchien.code.screen.screen;

import mchien.code.main.GameCanvas;
import mchien.code.main.GameMidlet;
import mchien.code.model.Char;
import mchien.code.model.IActionListener;
import mchien.code.model.ImageIcon;
import mchien.code.model.MainChar;
import mchien.code.model.Scroll;
import mchien.code.model.ScrollResult;
import mchien.code.model.T;
import mchien.code.model.mCommand;
import mchien.code.network.GameService;
import mchien.code.screen.MenuLogin;
import mchien.code.screen.Res;
import mchien.code.screen.SkillTemplate;
import mchien.code.screen.Util;
import mchien.code.screen.event.EventScreen;
import mchien.code.screen.screen.FontTeam;
import mchien.code.screen.screen.GameData;
import mchien.code.screen.screen.GameScr;
import mchien.code.screen.screen.MainMenu;
import mchien.code.screen.screen.ScreenTeam;
import mchien.code.screen.screen.SetInfoData;
import javax.microedition.lcdui.Image;
import lib.Cout;
import lib.Rms;
import lib.Session_ME;
import lib.TCanvas;
import lib.mGraphics;
import lib.mSound;
import lib.mSystem;
import lib.mVector;
import lib2.SoundMn;
import lib2.mFont;

public class MenuSelectItem
extends ScreenTeam
implements IActionListener {
    public boolean showMenu;
    public mVector menuItems = new mVector();
    public int menuSelectedItem;
    public int menuX;
    public int menuY;
    public int menuW;
    public int menuH;
    public static int[] menuTemY;
    public static int cmtoX;
    public static int cmx;
    public static int cmdy;
    public static int cmvy;
    public static int cmxLim;
    public static int xc;
    mCommand left = new mCommand("Ch\u1ecdn", 0);
    mCommand right = new mCommand("\u0110\u00f3ng", 0, GameCanvas.w - 71, GameCanvas.h - mCommand.hButtonCmd + 1);
    mCommand center = null;
    public static Image[] imgMenu;
    public final byte autoDanh = 0;
    public final byte autoNhat = 1;
    public final byte autoHoTro = (byte)2;
    public final byte tabFocus = (byte)3;
    public static int indexAuto;
    public int hpaintListSkillBuff = 40;
    public boolean isMenuAuto;
    public int indexPerHp = 51;
    public int indexPerMp = 5;
    public int tick;
    public boolean isViewTab;
    public int indexFocusViewTab = -1;
    public boolean[] isAutoDanh = new boolean[7];
    public boolean[] FocusAutoDanh = new boolean[7];
    public boolean[] isTabFocus = new boolean[8];
    public static boolean[] isAutoNhat;
    public static boolean[] FocusAutoNhat;
    public boolean[] FocusTabMucTieu = new boolean[8];
    public int[][] xyCheckAutoDanh = new int[7][];
    public int[][] xyCheckAutoNhat = new int[3][];
    public int[][] xyCheckTabFocus = new int[8][];
    public int[][] xySkillBuff = new int[3][];
    public int[][] xyNutTangHpMp = new int[4][];
    public int[][] idSkillBuff = new int[4][];
    public long[] CoolDownSkillBuff = new long[9];
    public int wKhungAuto = 180;
    public int hKhungAuto = 150;
    public int xKhungAuto = GameCanvas.w / 2 - this.wKhungAuto / 2;
    public int yKhungAuto = GameCanvas.h / 2 - this.hKhungAuto / 2;
    public Scroll listSkillBuf = new Scroll();
    public Scroll listAutoGetitem = new Scroll();
    public Scroll listTabMucTieu = new Scroll();
    boolean disableClose;
    boolean isOptionMenu = false;
    boolean isSelectSkill;
    int sizeauto;
    mCommand cmdClose;
    public mCommand cmdHopThu;
    public mCommand cmdDeoKhan;
    public mCommand cmdnhom;
    public mCommand cmdDoSat;
    public mCommand cmdQuest;
    public mCommand cmdBanBe;
    public mCommand cmdNapTien;
    public mCommand cmdAuto;
    public mCommand cmdDienDan;
    public mCommand cmdThoatGame;
    public mCommand cmdChatWorld;
    public mCommand cmdChangeTouch;
    public mCommand cmdBanghoi;
    public mCommand cmdHoatDong;
    public int IDNPC;
    public int ID;
    public int tDelay;
    public int w;
    int pa = 0;
    boolean trans = false;
    private int pointerDownTime;
    private int pointerDownFirstX;
    private int[] pointerDownLastX = new int[3];
    private boolean pointerIsDowning;
    private boolean isDownWhenRunning;
    private boolean wantUpdateList;
    private int waitToPerform;
    private int cmRun;
    private boolean touch;
    private boolean close;
    int cmvx;
    int cmdx;
    boolean isClose;
    public boolean[] isNotClose;

    public static void load() {
        try {
            if (imgMenu == null) {
                imgMenu = new Image[12];
                int[] nArray2 = new int[12];
                nArray2[1] = 1;
                nArray2[2] = 2;
                nArray2[3] = 3;
                nArray2[4] = 4;
                nArray2[5] = 5;
                nArray2[6] = 7;
                nArray2[7] = 8;
                nArray2[8] = 9;
                nArray2[9] = 10;
                nArray2[10] = 11;
                nArray2[11] = 6;
                int[] path = nArray2;
                if (mSystem.isPC) {
                    nArray2 = new int[12];
                    nArray2[1] = 1;
                    nArray2[2] = 2;
                    nArray2[3] = 3;
                    nArray2[4] = 4;
                    nArray2[5] = 5;
                    nArray2[6] = 7;
                    nArray2[7] = 8;
                    nArray2[8] = 9;
                    nArray2[9] = 10;
                    nArray2[10] = 11;
                    nArray2[11] = 6;
                    path = nArray2;
                    imgMenu = new Image[12];
                }
                if (mSystem.isAnNaptien()) {
                    int[] nArray3 = new int[10];
                    nArray3[1] = 1;
                    nArray3[2] = 2;
                    nArray3[3] = 3;
                    nArray3[4] = 4;
                    nArray3[5] = 5;
                    nArray3[6] = 7;
                    nArray3[7] = 9;
                    nArray3[8] = 11;
                    nArray3[9] = 6;
                    path = nArray3;
                    imgMenu = new Image[10];
                }
                int i = 0;
                while (i < imgMenu.length) {
                    if (imgMenu[i] == null) {
                        MenuSelectItem.imgMenu[i] = GameCanvas.loadImage("/hd/menu/c" + path[i] + ".png");
                    }
                    ++i;
                }
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public MenuSelectItem() {
        this.cmdHopThu = new mCommand(T.hopthu, this, 0);
        this.menuItems.addElement(this.cmdHopThu);
        this.cmdDeoKhan = new mCommand(T.deokhan, this, 1);
        this.menuItems.addElement(this.cmdDeoKhan);
        this.cmdDoSat = new mCommand(T.changpk, this, 2);
        this.menuItems.addElement(this.cmdDoSat);
        this.cmdBanBe = new mCommand(T.Banbe, this, 4);
        this.menuItems.addElement(this.cmdBanBe);
        this.cmdAuto = new mCommand(T.auto, this, 6);
        this.menuItems.addElement(this.cmdAuto);
        this.cmdnhom = new mCommand(T.nhom1, this, 9);
        this.menuItems.addElement(this.cmdnhom);
        this.cmdChatWorld = new mCommand(T.chatWorld, this, 10);
        this.menuItems.addElement(this.cmdChatWorld);
//        if (mSystem.isPC) {
            this.cmdChangeTouch = TCanvas.ScreenSize == 1 ? new mCommand(T.bigScreen, this, 13) : new mCommand(T.smallScreen, this, 13);
            this.menuItems.addElement(this.cmdChangeTouch);
//        }
        this.cmdBanghoi = new mCommand(T.banghoi, this, 15);
        this.menuItems.addElement(this.cmdBanghoi);
        if (!mSystem.isAnNaptien()) {
            this.cmdNapTien = new mCommand(T.naptien, this, 16);
            this.menuItems.addElement(this.cmdNapTien);
        }
        this.cmdHoatDong = new mCommand(T.hoatdong, this, 31);
        this.menuItems.addElement(this.cmdHoatDong);
        this.cmdThoatGame = new mCommand(T.thoat, this, 8);
        this.menuItems.addElement(this.cmdThoatGame);
        int i = 0;
        while (i < this.xyCheckAutoDanh.length) {
            this.xyCheckAutoDanh[i] = new int[2];
            this.xyCheckAutoDanh[i][0] = this.xKhungAuto + 14;
            this.xyCheckAutoDanh[i][1] = this.yKhungAuto + 30 + i * 20;
            ++i;
        }
        this.xyCheckAutoDanh[6][1]= this.xyCheckAutoDanh[3][1]+40;
        i = 0;
        while (i < this.xyCheckAutoNhat.length) {
            this.xyCheckAutoNhat[i] = new int[2];
            ++i;
        }
        i = 0;
        while (i < this.xyCheckTabFocus.length) {
            this.xyCheckTabFocus[i] = new int[2];
            this.xyCheckTabFocus[i][0] = this.xKhungAuto + 14;
            this.xyCheckTabFocus[i][1] = this.yKhungAuto + 36 + i * 20;
            ++i;
        }
        i = 0;
        while (i < this.isTabFocus.length) {
            this.isTabFocus[i] = true;
            ++i;
        }
        this.isTabFocus[3] = false;
        this.xyCheckAutoNhat[0][0] = this.xKhungAuto + 14;
        this.xyCheckAutoNhat[0][1] = this.yKhungAuto + 36;
        this.xyCheckAutoNhat[1][0] = this.xKhungAuto + 14;
        this.xyCheckAutoNhat[1][1] = this.yKhungAuto + 56;
        this.xyCheckAutoNhat[2][0] = this.xKhungAuto + 14;
        this.xyCheckAutoNhat[2][1] = this.yKhungAuto + 76;
        i = 0;
        while (i < this.xyNutTangHpMp.length) {
            this.xyNutTangHpMp[i] = new int[2];
            ++i;
        }
        this.xyNutTangHpMp[0][0] = this.xKhungAuto + 14;
        this.xyNutTangHpMp[0][1] = this.xyCheckAutoDanh[2][1] + 5;
        this.xyNutTangHpMp[1][0] = this.xKhungAuto + this.wKhungAuto - 14;
        this.xyNutTangHpMp[1][1] = this.xyCheckAutoDanh[2][1] + 5;
        this.xyNutTangHpMp[2][0] = this.xKhungAuto + 14;
        this.xyNutTangHpMp[2][1] = this.xyCheckAutoDanh[4][1] + 5;
        this.xyNutTangHpMp[3][0] = this.xKhungAuto + this.wKhungAuto - 14;
        this.xyNutTangHpMp[3][1] = this.xyCheckAutoDanh[4][1] + 5;
        this.cmdClose = new mCommand("\u0110\u00f3ng", this, 1000);
        if (GameCanvas.isTouch) {
            this.cmdClose.setXY(this.xKhungAuto + this.wKhungAuto / 2 - mCommand.wButtonCmd / 2, this.yKhungAuto + this.hKhungAuto + 2);
        }
        if (!GameCanvas.isTouch) {
            this.right = this.cmdClose;
        }
        i = 0;
        while (i < this.idSkillBuff.length) {
            this.idSkillBuff[i] = new int[4];
            this.idSkillBuff[i][0] = -1;
            this.idSkillBuff[i][1] = -1;
            this.idSkillBuff[i][2] = 0;
            this.idSkillBuff[i][3] = 0;
            ++i;
        }
        int size = 30;
        int i2 = 0;
        while (i2 < this.xySkillBuff.length) {
            this.xySkillBuff[i2] = new int[2];
            this.xySkillBuff[i2][0] = this.xKhungAuto + (this.wKhungAuto - size * 3) / 4 * (i2 + 1) + size / 2 + size * (i2 % 3);
            this.xySkillBuff[i2][1] = this.yKhungAuto + size / 2 + 30;
            ++i2;
        }
    }

    public void startWithoutCloseButton(mVector menuItems, int pos) {
        this.startAt(menuItems, pos);
        this.disableClose = true;
    }

    public void startAt() {
        if (this.showMenu) {
            return;
        }
        mSound.playSound(27, mSound.volumeSound);
        this.isClose = false;
        this.touch = false;
        this.close = false;
        this.tDelay = 0;
        if (this.menuItems.size() == 1) {
            this.menuSelectedItem = 0;
            mCommand c = (mCommand)this.menuItems.elementAt(0);
            if (c != null && c.caption.equals("N\u00f3i chuy\u1ec7n")) {
                c.performAction();
                this.showMenu = false;
                return;
            }
        }
        SoundMn.gI().openMenu();
        this.isNotClose = new boolean[this.menuItems.size()];
        int i = 0;
        while (i < this.isNotClose.length) {
            this.isNotClose[i] = false;
            ++i;
        }
        this.disableClose = false;
        if (this.menuItems.size() == 0) {
            return;
        }
        this.menuW = 34;
        this.menuH = 40;
        i = 0;
        while (i < this.menuItems.size()) {
            mCommand c = (mCommand)this.menuItems.elementAt(i);
            c.isPlaySoundButton = false;
            ++i;
        }
        menuTemY = new int[this.menuItems.size()];
        this.menuX = (GameCanvas.w - this.menuItems.size() * this.menuW) / 2;
        if (this.menuX < 1) {
            this.menuX = 1;
        }
        this.menuY = GameCanvas.h - this.menuH - 15 - 1;
        if (GameCanvas.isTouch) {
            this.menuY -= 3;
        }
        this.menuY += 17;
        i = 0;
        while (i < menuTemY.length) {
            MenuSelectItem.menuTemY[i] = GameCanvas.h;
            ++i;
        }
        this.showMenu = true;
        this.menuSelectedItem = 0;
        cmxLim = this.menuItems.size() * this.menuW - GameCanvas.w;
        if (cmxLim < 0) {
            cmxLim = 0;
        }
        cmtoX = 0;
        cmx = 0;
        xc = 50;
        this.w = this.menuItems.size() * this.menuW - 1;
        if (this.w > GameCanvas.w - 2) {
            this.w = GameCanvas.w - 2;
        }
        this.menuSelectedItem = -1;
    }

    public boolean isScrolling() {
        return !this.isClose && menuTemY[menuTemY.length - 1] > this.menuY || this.isClose && menuTemY[menuTemY.length - 1] < GameCanvas.h;
    }

    public void updateMenuKey() {
        int dx;
        if (!this.showMenu) {
            return;
        }
        if (this.isMenuAuto) {
            this.updateKeyAuto();
            return;
        }
        if (this.isScrolling()) {
            return;
        }
        boolean changeFocus = false;
        if (GameCanvas.keyPressedz[2] || GameCanvas.keyPressedz[4]) {
            changeFocus = true;
            --this.menuSelectedItem;
            if (this.menuSelectedItem < 0) {
                this.menuSelectedItem = this.menuItems.size() - 1;
            }
        } else if (GameCanvas.keyPressedz[8] || GameCanvas.keyPressedz[6]) {
            changeFocus = true;
            ++this.menuSelectedItem;
            if (this.menuSelectedItem > this.menuItems.size() - 1) {
                this.menuSelectedItem = 0;
            }
        } else if (GameCanvas.keyPressedz[5]) {
            if (this.center != null) {
                Cout.println(" ressedz[5]  center.idAction " + this.center.idAction);
                if (this.center.idAction > 0) {
                    if (this.center.actionListener == GameScr.gI()) {
                        Cout.println(" GameScr.gI()    ");
                    } else {
                        this.perform(this.center.idAction, this.center.p);
                    }
                }
            } else {
                this.waitToPerform = 2;
            }
        } else if (GameCanvas.keyPressedz[12]) {
            if (this.isScrolling()) {
                return;
            }
            if (this.left.idAction > 0) {
                this.perform(this.left.idAction, this.left.p);
            } else {
                this.waitToPerform = 2;
            }
            SoundMn.gI().buttonClose();
        } else if (!this.disableClose && GameCanvas.keyPressedz[13]) {
            if (this.isScrolling()) {
                return;
            }
            if (!this.close) {
                this.close = true;
            }
            this.isClose = true;
            SoundMn.gI().buttonClose();
        }
        if (changeFocus) {
            cmtoX = this.menuSelectedItem * this.menuW + this.menuW - GameCanvas.w / 2;
            if (cmtoX > cmxLim) {
                cmtoX = cmxLim;
            }
            if (cmtoX < 0) {
                cmtoX = 0;
            }
            if (this.menuSelectedItem == this.menuItems.size() - 1 || this.menuSelectedItem == 0) {
                cmx = cmtoX;
            }
        }
        if (!this.disableClose && GameCanvas.isPointerJustRelease[0] && !GameCanvas.isPointer(this.menuX, this.menuY, this.w, this.menuH, 0) && !this.pointerIsDowning) {
            if (this.isScrolling()) {
                return;
            }
            this.pointerDownFirstX = 0;
            this.pointerDownTime = 0;
            this.pointerIsDowning = false;
            GameCanvas.clearAllPointerEvent();
            this.isClose = true;
            this.close = true;
            SoundMn.gI().buttonClose();
            return;
        }
        if (GameCanvas.isPointerDown[0]) {
            if (!this.pointerIsDowning && GameCanvas.isPointer(this.menuX, this.menuY, this.w, this.menuH, 0)) {
                int i = 0;
                while (i < this.pointerDownLastX.length) {
                    this.pointerDownLastX[0] = GameCanvas.px[0];
                    ++i;
                }
                this.pointerDownFirstX = GameCanvas.px[0];
                this.pointerIsDowning = true;
                this.isDownWhenRunning = this.cmRun != 0;
                this.cmRun = 0;
            } else if (this.pointerIsDowning) {
                ++this.pointerDownTime;
                if (this.pointerDownTime > 5 && this.pointerDownFirstX == GameCanvas.px[0] && !this.isDownWhenRunning) {
                    this.pointerDownFirstX = -1000;
                    this.menuSelectedItem = (cmtoX + GameCanvas.px[0] - this.menuX) / this.menuW;
                }
                if ((dx = GameCanvas.px[0] - this.pointerDownLastX[0]) != 0 && this.menuSelectedItem != -1) {
                    this.menuSelectedItem = -1;
                }
                int i = this.pointerDownLastX.length - 1;
                while (i > 0) {
                    this.pointerDownLastX[i] = this.pointerDownLastX[i - 1];
                    --i;
                }
                this.pointerDownLastX[0] = GameCanvas.px[0];
                if ((cmtoX -= dx) < 0) {
                    cmtoX = 0;
                }
                if (cmtoX > cmxLim) {
                    cmtoX = cmxLim;
                }
                if (cmx < 0 || cmx > cmxLim) {
                    dx /= 2;
                }
                this.wantUpdateList = (cmx -= dx) < -(GameCanvas.h / 3);
            }
        }
        if (GameCanvas.isPointerJustRelease[0] && this.pointerIsDowning) {
            dx = GameCanvas.px[0] - this.pointerDownLastX[0];
            GameCanvas.isPointerJustRelease[0] = false;
            if (Util.abs(dx) < 20 && Util.abs(GameCanvas.px[0] - this.pointerDownFirstX) < 20 && !this.isDownWhenRunning) {
                this.cmRun = 0;
                cmtoX = cmx;
                this.pointerDownFirstX = -1000;
                this.menuSelectedItem = (cmtoX + GameCanvas.px[0] - this.menuX) / this.menuW;
                this.pointerDownTime = 0;
                this.waitToPerform = 5;
            } else if (this.menuSelectedItem != -1 && this.pointerDownTime > 5) {
                this.pointerDownTime = 0;
                this.waitToPerform = 1;
            } else if (this.menuSelectedItem == -1 && !this.isDownWhenRunning) {
                if (cmx < 0) {
                    cmtoX = 0;
                } else if (cmx > cmxLim) {
                    cmtoX = cmxLim;
                } else {
                    int s = GameCanvas.px[0] - this.pointerDownLastX[0] + (this.pointerDownLastX[0] - this.pointerDownLastX[1]) + (this.pointerDownLastX[1] - this.pointerDownLastX[2]);
                    s = s > 10 ? 10 : (s < -10 ? -10 : 0);
                    this.cmRun = -s * 100;
                }
            }
            this.pointerIsDowning = false;
            this.pointerDownTime = 0;
            GameCanvas.isPointerJustRelease[0] = false;
        }
        GameCanvas.clearKeyPressed();
        GameCanvas.clearKeyHold();
    }

    public void moveCamera() {
        if (this.cmRun != 0 && !this.pointerIsDowning) {
            if ((cmtoX += this.cmRun / 100) < 0) {
                cmtoX = 0;
            } else if (cmtoX > cmxLim) {
                cmtoX = cmxLim;
            } else {
                cmx = cmtoX;
            }
            this.cmRun = this.cmRun * 9 / 10;
            if (this.cmRun < 100 && this.cmRun > -100) {
                this.cmRun = 0;
            }
        }
        if (cmx != cmtoX && !this.pointerIsDowning) {
            this.cmvx = cmtoX - cmx << 2;
            this.cmdx += this.cmvx;
            cmx += this.cmdx >> 4;
            this.cmdx &= 0xF;
        }
    }

    public void paintMenu(mGraphics g) {
        g.translate(-g.getTranslateX(), -g.getTranslateY());
        g.translate(-cmx, 0);
        int i = 0;
        while (i < this.menuItems.size()) {
            if (i == this.menuSelectedItem) {
                g.drawRegion(imgMenu[i], 0, 34, 34, 34, 0, this.menuX + i * this.menuW + 1, menuTemY[i] + 1 - 10, 0, false);
            } else {
                g.drawRegion(imgMenu[i], 0, 34, 34, 34, 0, this.menuX + i * this.menuW + 1, menuTemY[i] + 1, 0, false);
            }
            String[] sc = ((mCommand)this.menuItems.elementAt((int)i)).subCaption;
            if (sc == null) {
                sc = new String[]{((mCommand)this.menuItems.elementAt((int)i)).caption};
            }
            int yCaptionStart = menuTemY[i] + (this.menuH - sc.length * 14) / 2 + 1 + (sc.length == 3 ? sc.length * 4 : 10);
            int k = 0;
            while (k < sc.length) {
                if (i == this.menuSelectedItem && !this.isClose) {
                    int xs = 4;
                    GameScr.Font3d(g, sc[k], xs + this.menuX + i * this.menuW + this.menuW / 2 - 2, yCaptionStart + k * 14 - 14 - sc.length * 2 - this.menuH / 2 - 10, 2, mFont.tahoma_7b_yellow);
                }
                ++k;
            }
            ++i;
        }
        g.translate(-g.getTranslateX(), -g.getTranslateY());
        if (this.isMenuAuto) {
            Res.paintDlgDragonFullNew(g, this.xKhungAuto, this.yKhungAuto, this.wKhungAuto, this.hKhungAuto, 60, 60, GameScr.imgBk[0], false);
            g.setColor(-9751532);
            g.fillRect(this.xKhungAuto, this.yKhungAuto - 28, this.wKhungAuto, 28, false);
            i = 0;
            while (i < 3) {
                g.setColor(Res.nColor[i]);
                g.drawRect(this.xKhungAuto + i, this.yKhungAuto - 28 + i, this.wKhungAuto - i * 2, 28 - i * 2, false);
                ++i;
            }
            g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 7, this.xKhungAuto + this.wKhungAuto + 1, this.yKhungAuto + 1, mGraphics.BOTTOM | mGraphics.RIGHT, false);
            g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 1, this.xKhungAuto, this.yKhungAuto + 1, mGraphics.BOTTOM | mGraphics.LEFT, false);
            g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 0, this.xKhungAuto, this.yKhungAuto - 28, mGraphics.TOP | mGraphics.LEFT, false);
            g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 2, this.xKhungAuto + this.wKhungAuto + 1, this.yKhungAuto - 28, mGraphics.TOP | mGraphics.RIGHT, false);
            i = 0;
            while (i < 7) {
                g.drawRegion(GameScr.imgBoder[5], 0, 25, 12, 25, 0, this.xKhungAuto + this.wKhungAuto / 2 - 42 + i * 12, this.yKhungAuto - 14, mGraphics.VCENTER | mGraphics.LEFT, false);
                ++i;
            }
            g.drawRegion(GameScr.imgBoder[5], 0, 0, 12, 25, 0, this.xKhungAuto + this.wKhungAuto / 2 - 44, this.yKhungAuto - 14, mGraphics.VCENTER | mGraphics.LEFT, false);
            int ys = 0;
            if (!mSystem.isj2me) {
                ys = -1;
            }
            g.drawRegion(GameScr.imgBoder[5], 0, 0, 12, 25, 2, this.xKhungAuto + this.wKhungAuto / 2 + 44 + 1, this.yKhungAuto - 14 + 1 + ys, mGraphics.VCENTER | mGraphics.RIGHT, false);
            FontTeam.fontTile.drawString(g, T.auto, this.xKhungAuto + this.wKhungAuto / 2, this.yKhungAuto - 19, 2, false);
            g.setColor(Res.nColor[1]);
            g.drawLine(this.xKhungAuto + 1, this.yKhungAuto + 22, this.xKhungAuto + this.wKhungAuto - 2, this.yKhungAuto + 22, false);
            int i2 = 0;
            while (i2 < T.nameTabAuto.length) {
                if (i2 != indexAuto || !this.isViewTab && this.tick < 20) {
                    g.setColor(-9751532);
                } else {
                    g.setColor(-6725831);
                }
                g.fillRect(this.xKhungAuto + i2 * this.sizeauto, this.yKhungAuto + 1, this.sizeauto - 1, 21, false);
                ++i2;
            }
            g.setColor(Res.nColor[1]);
            i2 = 0;
            while (i2 < T.nameTabAuto.length) {
                g.drawRect(this.xKhungAuto + i2 * this.sizeauto, this.yKhungAuto + 1, this.sizeauto - 1, 21, false);
                ++i2;
            }
            switch (indexAuto) {
                case 0: {
                    this.paintAutoDanh(g);
                    break;
                }
                case 1: {
                    this.paintAutoNhat(g);
                    break;
                }
                case 2: {
                    this.paintAutoBuff(g);
                    break;
                }
                case 3: {
                    this.paintTabMucTieu(g);
                }
            }
            if (GameCanvas.isTouch) {
                this.cmdClose.paint(g);
            }
            i2 = 0;
            while (i2 < T.nameTabAuto.length) {
                g.setColor(Res.nColor[1]);
                g.drawRect(this.xKhungAuto + i2 * this.sizeauto, this.yKhungAuto + 1, this.sizeauto - 1, 21, false);
                if (i2 != indexAuto) {
                    mFont.tahoma_7_white.drawString(g, T.nameTabAuto[i2], this.xKhungAuto + i2 * this.sizeauto + 21 + 1, this.yKhungAuto + 7, 2, false);
                } else {
                    mFont.tahoma_7_yellow.drawString(g, T.nameTabAuto[i2], this.xKhungAuto + i2 * this.sizeauto + 21 + 1, this.yKhungAuto + 7, 2, false);
                }
                ++i2;
            }
        }
        super.paint(g);
    }

    public void doCloseMenu() {
        mCommand c;
        if (this.menuSelectedItem != 4) {
            this.isClose = false;
            this.showMenu = false;
            SoundMn.gI().buttonClose();
        }
        if (this.touch && this.menuSelectedItem >= 0 && (c = (mCommand)this.menuItems.elementAt(this.menuSelectedItem)) != null) {
            SoundMn.gI().buttonClose();
            c.performAction();
        }
    }

    public void performSelect() {
        if (this.menuSelectedItem >= 0) {
            if (this.isOptionMenu) {
                GameService.gI().Dynamic_Menu((short)this.IDNPC, (byte)this.ID, (byte)this.menuSelectedItem);
                this.isOptionMenu = false;
            } else {
                mCommand c = (mCommand)this.menuItems.elementAt(this.menuSelectedItem);
                if (c != null) {
                    c.performAction();
                }
            }
        }
    }

    public void updateMenu() {
        int delta;
        ++this.tick;
        this.moveCamera();
		if (indexAuto == 1 && GameCanvas.isTouch) {
            if (GameCanvas.isPointerClick[0] && this.getCmdPointerLast(this.cmdClose)) {
                GameCanvas.isPointerJustRelease[0] = false;
                this.cmdClose.performAction();
                return;
            }
            ScrollResult r = this.listAutoGetitem.updateKey();
            this.listAutoGetitem.updatecm();
            if (GameCanvas.isPointerClick[0] && this.listAutoGetitem.selectedItem != -1 && !this.listAutoGetitem.isDownWhenRunning) {
                MenuSelectItem.isAutoNhat[this.listAutoGetitem.selectedItem] = !isAutoNhat[this.listAutoGetitem.selectedItem];
            }
            int i = 0;
            while (i < T.nameTabAuto.length) {
                if (GameCanvas.isPointerClick[0] && i != indexAuto && GameCanvas.isPointer(this.xKhungAuto + i * 40 + 1, this.yKhungAuto + 1, 40, 21, 0)) {
                    indexAuto = i;
                }
                ++i;
            }
		}
		if (indexAuto == 3 && GameCanvas.isTouch) {
			if (GameCanvas.isPointerClick[0] && this.getCmdPointerLast(this.cmdClose)) {
				GameCanvas.isPointerJustRelease[0] = false;
				this.cmdClose.performAction();
				return;
			}
			this.listTabMucTieu.setStyle(T.Tabmuctieu.length, 20, this.xKhungAuto, this.yKhungAuto + 30, this.wKhungAuto, this.hKhungAuto - 30, true, 1);
			ScrollResult r2 = this.listTabMucTieu.updateKey();
			this.listTabMucTieu.updatecm();
			if (GameCanvas.isPointerClick[0] && this.listTabMucTieu.selectedItem != -1 && !this.listTabMucTieu.isDownWhenRunning) {
				this.isTabFocus[this.listTabMucTieu.selectedItem] = !this.isTabFocus[this.listTabMucTieu.selectedItem];
				if (this.listTabMucTieu.selectedItem == 1) {
					if (!this.isTabFocus[1]) {
						mSound.pauseCurMusic();
					}
					if (this.isTabFocus[1]) {
						GameScr.playSound1();
					}
					Rms.saveSound();
				}
				this.listTabMucTieu.selectedItem = -1;
			}
			int i2 = 0;
			while (i2 < T.nameTabAuto.length) {
				if (GameCanvas.isPointerClick[0] && i2 != indexAuto && GameCanvas.isPointer(this.xKhungAuto + i2 * 40 + 1, this.yKhungAuto + 1, 40, 21, 0)) {
					indexAuto = i2;
				}
				++i2;
			}
		}
        if (this.isMenuAuto) {
            ScrollResult s1 = this.listSkillBuf.updateKey();
            this.listSkillBuf.updatecm();
            if (indexAuto == 2 && this.listSkillBuf.selectedItem != -1 && this.indexFocusViewTab != -1) {
                int lengSkillBuff = 0;
                int i = 0;
                while (i < GameScr.vec_skill.size()) {
                    SkillTemplate skill = (SkillTemplate)GameScr.vec_skill.elementAt(i);
                    if (skill != null && skill.type == 1) {
                        if (lengSkillBuff == this.listSkillBuf.selectedItem) {
                            if (this.idSkillBuff[this.indexFocusViewTab][0] == -1 || this.idSkillBuff[this.indexFocusViewTab][1] != skill.idEffStartSkill) {
                                boolean isAdded = false;
                                int j = 0;
                                while (j < this.idSkillBuff.length) {
                                    if (this.idSkillBuff[j][1] == skill.idEffStartSkill) {
                                        isAdded = true;
                                        break;
                                    }
                                    ++j;
                                }
                                if (!isAdded) {
                                    this.idSkillBuff[this.indexFocusViewTab][0] = i;
                                    this.idSkillBuff[this.indexFocusViewTab][1] = skill.idEffStartSkill;
                                    this.idSkillBuff[this.indexFocusViewTab][3] = skill.getCoolDown(Char.levelSkill[i] - 1);
                                    Rms.saveAutoBuff();
                                }
                            } else if (this.idSkillBuff[this.indexFocusViewTab][1] == skill.idEffStartSkill) {
                                this.idSkillBuff[this.indexFocusViewTab][0] = -1;
                                this.idSkillBuff[this.indexFocusViewTab][1] = -1;
                                Rms.saveAutoBuff();
                            }
                            this.listSkillBuf.selectedItem = -1;
                            break;
                        }
                        ++lengSkillBuff;
                    }
                    ++i;
                }
            }
            this.updatePointerAuto();
            return;
        }
        if (!this.isClose) {
            ++this.tDelay;
            int i = 0;
            while (i < menuTemY.length) {
                if (menuTemY[i] > this.menuY) {
                    delta = menuTemY[i] - this.menuY >> 1;
                    if (delta < 1) {
                        delta = 1;
                    }
                    if (this.tDelay > i) {
                        int n = i;
                        menuTemY[n] = menuTemY[n] - delta;
                    }
                }
                ++i;
            }
            if (menuTemY[menuTemY.length - 1] <= this.menuY) {
                this.tDelay = 0;
            }
        } else {
            ++this.tDelay;
            int i = 0;
            while (i < menuTemY.length) {
                if (menuTemY[i] < GameCanvas.h) {
                    delta = (GameCanvas.h - menuTemY[i] >> 1) + 2;
                    if (delta < 1) {
                        delta = 1;
                    }
                    if (this.tDelay > i) {
                        int n = i;
                        menuTemY[n] = menuTemY[n] + delta;
                    }
                }
                ++i;
            }
            if (menuTemY[menuTemY.length - 1] >= GameCanvas.h) {
                this.tDelay = 0;
                if (!this.isMenuAuto) {
                    this.doCloseMenu();
                }
            }
        }
        if (xc != 0 && (xc >>= 1) < 0) {
            xc = 0;
        }
        if (this.isScrolling()) {
            return;
        }
        if (this.waitToPerform > 0) {
            --this.waitToPerform;
            if (this.waitToPerform == 0) {
                if (!this.isNotClose[this.menuSelectedItem]) {
                    this.isClose = true;
                    this.touch = true;
                } else {
                    this.performSelect();
                }
            }
        }
        super.update();
    }

    @Override
    public void perform(int idAction, Object p) {
        mSound.playSound(26, mSound.volumeSound);
        switch (idAction) {
            case 0: {
                if (EventScreen.vecListEvent.size() == 0) {
                    GameCanvas.addNotify(T.khongcotinnhan, (byte)0);
                } else if (GameCanvas.mevent != null) {
                    GameCanvas.mevent.init();
                    GameCanvas.mevent.switchToMe(GameCanvas.gameScr);
                }
                GameScr.numMSG = 0;
                break;
            }
            case 1: {
                mVector vec = new mVector();
                int i = 0;
                while (i < T.listKhan.length) {
                    mCommand cmd = new mCommand(T.listKhan[i], GameCanvas.menu2, -1);
                    vec.addElement(cmd);
                    ++i;
                }
                GameCanvas.menu2.startArt(vec, 1, T.deokhan);
                break;
            }
            case 2: {
                if (GameScr.mainChar.typePK == -1) {
                    GameCanvas.startYesNoDlg(T.dosat, new mCommand(T.bat, this, 30));
                    break;
                }
                GameService.gI().changePK((byte)-1);
                break;
            }
            case 3: {
                MainMenu.gI().switchToMe(GameCanvas.gameScr);
                MainMenu.gI().showQuest();
                break;
            }
            case 4: {
                GameService.gI().Friend((byte)4, "");
                break;
            }
            case 5: {
                GameCanvas.gameScr.hideGUI = 2;
                GameService.gI().napDiamond();
                break;
            }
            case 6: {
                this.isMenuAuto = true;
                indexAuto = 0;
                this.sizeauto = this.wKhungAuto / 4;
                break;
            }
            case 7: {
                break;
            }
            case 8: {
                GameCanvas.endDlg();
                Session_ME.gI().close();
                GameCanvas.gameScr.clearloadMap();
                GameScr.removeAllchat();
                MenuLogin.gI().switchToMe();
                break;
            }
            case 9: {
                if (GameScr.mainChar.idNhom != -1) {
                    GameCanvas.gameScr.hideGUI = 2;
                    GameService.gI().doCreateParty((byte)0, (short)-1, (short)-1, "");
                    break;
                }
                GameCanvas.addNotify(T.khongconhom, (byte)0);
                break;
            }
            case 10: {
                GameCanvas.gameScr.chatWorld = true;
                GameCanvas.gameScr.tfChatWorld.doChangeToTextBox();
                if (!mSystem.isPC) break;
                GameCanvas.gameScr.tfChatWorld.setFocus(true);
                break;
            }
            case 11: {
                break;
            }
            case 12: {
                GameCanvas.gameScr.isMovebyTouch = !GameCanvas.gameScr.isMovebyTouch;
                break;
            }
            case 13: {
                GameCanvas.startYesNoDlg(T.tchangsize, new mCommand(T.OK, this, 14));
                break;
            }
            case 14: {
                TCanvas.ScreenSize = TCanvas.ScreenSize == 1 ? (byte)2 : (byte)1;
                Rms.saveScreenSize();
                Rms.deleteCacheFiles();
                GameCanvas.endDlg();
                System.exit(-1);
                break;
            }
            case 15: {
                if (GameScr.mainChar.idClan != -1 && MainChar.infoOptionClan != null) {
                    if (GameCanvas.isTouch) {
                        GameService.gI().RequestInfoClan((byte)-1, -1);
                        break;
                    }
                    mVector vecbang = new mVector();
                    int i = 0;
                    while (i < MainChar.infoOptionClan.length) {
                        mCommand cmd = new mCommand(MainChar.infoOptionClan[i], GameCanvas.menu2, 6);
                        vecbang.addElement(cmd);
                        ++i;
                    }
                    GameCanvas.menu2.startArt(vecbang, 0, T.banghoi);
                    break;
                }
                GameCanvas.startOKDlg(T.kcobang);
                break;
            }
            case 16: {
                if (mSystem.isAndroidStore() || mSystem.isIosAppStore()) {
                    mVector vecNapGoogle = new mVector();
                    int i = 0;
                    while (i < mSystem.google_listGems.length) {
                        mCommand cmd = new mCommand(mSystem.google_listGems[i], GameCanvas.menu2, 201);
                        vecNapGoogle.addElement(cmd);
                        ++i;
                    }
                    GameCanvas.menu2.startArt(vecNapGoogle, 0, T.napgoogle);
                    break;
                }
                GameService.gI().Dynamic_Menu((short)39, (byte)0, (byte)0);
                break;
            }
            case 30: {
                GameService.gI().changePK((byte)0);
                GameCanvas.endDlg();
                break;
            }
            case 31: {
                GameService.gI().Dynamic_Menu((short)55, (byte)0, (byte)4);
                break;
            }
            case 1000: {
                this.menuSelectedItem = -1;
                this.isMenuAuto = false;
                this.doCloseMenu();
                mVector vauto = new mVector();
                int i = 0;
                while (i < isAutoNhat.length) {
                    if (isAutoNhat[i]) {
                        SetInfoData sdt = new SetInfoData();
                        sdt.index = i;
                        vauto.addElement(sdt);
                    }
                    ++i;
                }
                if (vauto.size() <= 0) break;
                GameService.gI().autoGetitem(0, vauto);
            }
        }
        this.menuSelectedItem = -1;
    }

    @Override
    public void paint(mGraphics g, int idAction, int x, int y, Object paint) {
    }

    public void paintAutoDanh(mGraphics g) {
        if (!this.FocusAutoDanh[0] || this.FocusAutoDanh[0] && GameCanvas.gameTick % 4 == 0) {
            mFont.tahoma_7_white.drawString(g, T.menuAuto[0][0], this.xyCheckAutoDanh[0][0] + 10, this.xyCheckAutoDanh[0][1], 0, false);
        } else {
            mFont.tahoma_7_yellow.drawString(g, T.menuAuto[0][0], this.xyCheckAutoDanh[0][0] + 10, this.xyCheckAutoDanh[0][1], 0, false);
        }
        if (!this.FocusAutoDanh[1] || this.FocusAutoDanh[1] && GameCanvas.gameTick % 4 == 0) {
            mFont.tahoma_7_white.drawString(g, T.menuAuto[0][1], this.xyCheckAutoDanh[1][0] + 10, this.xyCheckAutoDanh[1][1], 0, false);
        } else {
            mFont.tahoma_7_yellow.drawString(g, T.menuAuto[0][1], this.xyCheckAutoDanh[1][0] + 10, this.xyCheckAutoDanh[1][1], 0, false);
        }
        if (!this.FocusAutoDanh[3] || this.FocusAutoDanh[3] && GameCanvas.gameTick % 4 == 0) {
            mFont.tahoma_7_white.drawString(g, T.menuAuto[0][2], this.xyCheckAutoDanh[3][0] + 10, this.xyCheckAutoDanh[3][1], 0, false);
        } else {
            mFont.tahoma_7_yellow.drawString(g, T.menuAuto[0][2], this.xyCheckAutoDanh[3][0] + 10, this.xyCheckAutoDanh[3][1], 0, false);
        }
        if (!this.FocusAutoDanh[6] || this.FocusAutoDanh[6] && GameCanvas.gameTick % 4 == 0) {
            mFont.tahoma_7_white.drawString(g, T.menuAuto[0][3], this.xyCheckAutoDanh[6][0] + 10, this.xyCheckAutoDanh[6][1], 0, false);
        } else {
            mFont.tahoma_7_yellow.drawString(g, T.menuAuto[0][3], this.xyCheckAutoDanh[6][0] + 10, this.xyCheckAutoDanh[6][1], 0, false);
        }
        g.drawRegion(GameScr.imgCheck, 0, this.isAutoDanh[0] ? GameScr.imgCheck.getHeight() / 2 : 0, GameScr.imgCheck.getWidth(), GameScr.imgCheck.getHeight() / 2, 0, this.xyCheckAutoDanh[0][0], this.xyCheckAutoDanh[0][1] + 5, mGraphics.VCENTER | mGraphics.HCENTER, false);
        g.drawRegion(GameScr.imgCheck, 0, this.isAutoDanh[1] ? GameScr.imgCheck.getHeight() / 2 : 0, GameScr.imgCheck.getWidth(), GameScr.imgCheck.getHeight() / 2, 0, this.xyCheckAutoDanh[1][0], this.xyCheckAutoDanh[1][1] + 5, mGraphics.VCENTER | mGraphics.HCENTER, false);
        g.drawRegion(GameScr.imgCheck, 0, this.isAutoDanh[3] ? GameScr.imgCheck.getHeight() / 2 : 0, GameScr.imgCheck.getWidth(), GameScr.imgCheck.getHeight() / 2, 0, this.xyCheckAutoDanh[3][0], this.xyCheckAutoDanh[3][1] + 5, mGraphics.VCENTER | mGraphics.HCENTER, false);
        g.drawRegion(GameScr.imgCheck, 0, this.isAutoDanh[6] ? GameScr.imgCheck.getHeight() / 2 : 0, GameScr.imgCheck.getWidth(), GameScr.imgCheck.getHeight() / 2, 0, this.xyCheckAutoDanh[6][0], this.xyCheckAutoDanh[6][1] + 5, mGraphics.VCENTER | mGraphics.HCENTER, false);

        if (!this.FocusAutoDanh[2] || this.FocusAutoDanh[2] && GameCanvas.gameTick % 4 == 0) {
            g.setColor(-9751532);
        } else {
            g.setColor(-16777216);
        }
        g.fillRect(this.xKhungAuto + 9, this.xyNutTangHpMp[0][1] - 5, this.wKhungAuto - 18, 10, false);
        if (!this.FocusAutoDanh[4] || this.FocusAutoDanh[4] && GameCanvas.gameTick % 4 == 0) {
            g.setColor(-9751532);
        } else {
            g.setColor(-16777216);
        }
        g.fillRect(this.xKhungAuto + 9, this.xyNutTangHpMp[2][1] - 5, this.wKhungAuto - 18, 10, false);
        g.setColor(-12772608);
        g.drawRect(this.xKhungAuto + 10, this.xyNutTangHpMp[0][1] - 5, this.wKhungAuto - 20, 10, false);
        g.drawRect(this.xKhungAuto + 10, this.xyNutTangHpMp[2][1] - 5, this.wKhungAuto - 20, 10, false);
        g.drawRegion(GameScr.imgIncrease, 0, 0, GameScr.imgIncrease.getWidth(), GameScr.imgIncrease.getHeight() / 2, 0, this.xyNutTangHpMp[0][0], this.xyNutTangHpMp[0][1], mGraphics.VCENTER | mGraphics.HCENTER, false);
        g.drawRegion(GameScr.imgIncrease, 0, GameScr.imgIncrease.getHeight() / 2, GameScr.imgIncrease.getWidth(), GameScr.imgIncrease.getHeight() / 2, 0, this.xyNutTangHpMp[1][0], this.xyNutTangHpMp[1][1], mGraphics.VCENTER | mGraphics.HCENTER, false);
        g.drawRegion(GameScr.imgIncrease, 0, 0, GameScr.imgIncrease.getWidth(), GameScr.imgIncrease.getHeight() / 2, 0, this.xyNutTangHpMp[2][0], this.xyNutTangHpMp[2][1], mGraphics.VCENTER | mGraphics.HCENTER, false);
        g.drawRegion(GameScr.imgIncrease, 0, GameScr.imgIncrease.getHeight() / 2, GameScr.imgIncrease.getWidth(), GameScr.imgIncrease.getHeight() / 2, 0, this.xyNutTangHpMp[3][0], this.xyNutTangHpMp[3][1], mGraphics.VCENTER | mGraphics.HCENTER, false);
        g.setColor(-4347277);
        g.fillRect(this.xKhungAuto + 20 + this.indexPerHp * ((this.wKhungAuto - 40) / 10) - 5, this.xyNutTangHpMp[0][1] - 4, 10, 9, false);
        g.fillRect(this.xKhungAuto + 20 + this.indexPerMp * ((this.wKhungAuto - 40) / 10) - 5, this.xyNutTangHpMp[2][1] - 4, 10, 9, false);
        mFont.tahoma_7_white.drawString(g, String.valueOf(this.indexPerHp * 10) + "%", this.xKhungAuto + this.wKhungAuto / 2, this.xyNutTangHpMp[0][1] - 5, 2, false);
        mFont.tahoma_7_white.drawString(g, String.valueOf(this.indexPerMp * 10) + "%", this.xKhungAuto + this.wKhungAuto / 2, this.xyNutTangHpMp[2][1] - 5, 2, false);
    }

    public void paintAutoNhat(mGraphics g) {
        GameCanvas.resetTrans(g);
        this.listAutoGetitem.setStyle(GameScr.infoAutoGetItem.length, 20, this.xKhungAuto, this.yKhungAuto + 30, this.wKhungAuto, this.hKhungAuto - 30, true, 1);
        g.ClipRec(this.xKhungAuto, this.yKhungAuto + 30, this.wKhungAuto, this.hKhungAuto - 30);
        this.listAutoGetitem.setClip(g, this.xKhungAuto, this.yKhungAuto + 30, this.wKhungAuto, this.hKhungAuto - 30);
        int i = 0;
        while (i < GameScr.infoAutoGetItem.length) {
            if (!GameCanvas.isTouch) {
                if (!FocusAutoNhat[i] || FocusAutoNhat[i] && GameCanvas.gameTick % 4 == 0) {
                    mFont.tahoma_7_white.drawString(g, GameScr.infoAutoGetItem[i], this.xKhungAuto + 25, this.yKhungAuto + 30 + 20 * i, 0, true);
                } else {
                    mFont.tahoma_7_yellow.drawString(g, GameScr.infoAutoGetItem[i], this.xKhungAuto + 25, this.yKhungAuto + 30 + 20 * i, 0, true);
                }
            } else {
                mFont.tahoma_7_white.drawString(g, GameScr.infoAutoGetItem[i], this.xKhungAuto + 25, this.yKhungAuto + 30 + 20 * i, 0, true);
            }
            g.drawRegion(GameScr.imgCheck, 0, isAutoNhat[i] ? GameScr.imgCheck.getHeight() / 2 : 0, GameScr.imgCheck.getWidth(), GameScr.imgCheck.getHeight() / 2, 0, this.xyCheckAutoNhat[0][0], this.xyCheckAutoNhat[0][1] + 20 * i, mGraphics.VCENTER | mGraphics.HCENTER, true);
            ++i;
        }
        mGraphics.resetTransAndroid(g);
        g.restoreCanvas();
        GameCanvas.resetTrans(g);
    }

    public void paintTabMucTieu(mGraphics g) {
        GameCanvas.resetTrans(g);
        this.listTabMucTieu.setStyle(T.Tabmuctieu.length, 20, this.xKhungAuto, this.yKhungAuto + 30, this.wKhungAuto, this.hKhungAuto - 30, true, 1);
        g.ClipRec(this.xKhungAuto, this.yKhungAuto + 30, this.wKhungAuto, this.hKhungAuto - 30);
        this.listTabMucTieu.setClip(g, this.xKhungAuto, this.yKhungAuto + 30, this.wKhungAuto, this.hKhungAuto - 30);
        int i = 0;
        while (i < T.Tabmuctieu.length) {
            if (!GameCanvas.isTouch) {
                if (!this.FocusTabMucTieu[i] || this.FocusTabMucTieu[i] && GameCanvas.gameTick % 4 == 0) {
                    mFont.tahoma_7_white.drawString(g, T.Tabmuctieu[i], this.xKhungAuto + 25, this.yKhungAuto + 30 + 20 * i, 0, true);
                } else {
                    mFont.tahoma_7_yellow.drawString(g, T.Tabmuctieu[i], this.xKhungAuto + 25, this.yKhungAuto + 30 + 20 * i, 0, true);
                }
            } else {
                mFont.tahoma_7_white.drawString(g, T.Tabmuctieu[i], this.xKhungAuto + 25, this.yKhungAuto + 30 + 20 * i, 0, true);
            }
            g.drawRegion(GameScr.imgCheck, 0, this.isTabFocus[i] ? GameScr.imgCheck.getHeight() / 2 : 0, GameScr.imgCheck.getWidth(), GameScr.imgCheck.getHeight() / 2, 0, this.xyCheckTabFocus[0][0], this.xyCheckTabFocus[0][1] + 20 * i, mGraphics.VCENTER | mGraphics.HCENTER, true);
            ++i;
        }
        mGraphics.resetTransAndroid(g);
        g.restoreCanvas();
        GameCanvas.resetTrans(g);
    }

    public void paintAutoBuff(mGraphics g) {
        ImageIcon imgskill;
        SkillTemplate skill;
        int hkill = mGraphics.getImageHeight(GameScr.imgTouchMove[3]) / 2;
        int wkill = mGraphics.getImageWidth(GameScr.imgTouchMove[3]);
        int size = 30;
        g.setColor(-11262464);
        g.drawRect(this.xKhungAuto + 19, this.yKhungAuto + this.hKhungAuto - size - 11, this.wKhungAuto - 39, size + 8, false);
        g.setColor(-15850710);
        g.fillRect(this.xKhungAuto + 20, this.yKhungAuto + this.hKhungAuto - size - 10, this.wKhungAuto - 40, size + 8, false);
        int i = 0;
        while (i < 3) {
            if (i == this.indexFocusViewTab) {
                MainMenu.gI().paintFocus(g, this.xySkillBuff[i][0], this.xySkillBuff[i][1], 1, true);
            }
            g.drawRegion(GameScr.imgTouchMove[3], 0, hkill, wkill, hkill, 0, this.xKhungAuto + (this.wKhungAuto - size * 3) / 4 * (i + 1) + size / 2 + size * (i % 3), this.yKhungAuto + size / 2 + 30, 3, false);
            int j = 0;
            while (j < GameScr.vec_skill.size()) {
                skill = (SkillTemplate)GameScr.vec_skill.elementAt(j);
                if (skill != null && j == this.idSkillBuff[i][0] && Char.levelSkill[this.idSkillBuff[i][0]] >= 0 && (imgskill = GameData.getImgIcon((short)(skill.iconId + Res.ID_ICON_SKILL))) != null && imgskill.img != null) {
                    g.drawImage(imgskill.img, this.xKhungAuto + (this.wKhungAuto - size * 3) / 4 * (i + 1) + size / 2 + size * (i % 3), this.yKhungAuto + size / 2 + 30, 3, false);
                }
                ++j;
            }
            ++i;
        }
        int lengSkillBuff = 0;
        this.listSkillBuf.setStyle(2, size + 5, this.xKhungAuto + 20, this.yKhungAuto + this.hKhungAuto - size - 10, this.wKhungAuto - 40, size + 10, false, 1);
        g.ClipRec(this.xKhungAuto + 20, this.yKhungAuto + this.hKhungAuto - size - 10, this.wKhungAuto - 40, size + 10);
        this.listSkillBuf.setClip(g, this.xKhungAuto + 20, this.yKhungAuto + this.hKhungAuto - size - 10, this.wKhungAuto - 40, size + 10);
        int i2 = 0;
        while (i2 < GameScr.vec_skill.size()) {
            skill = (SkillTemplate)GameScr.vec_skill.elementAt(i2);
            imgskill = GameData.getImgIcon((short)(skill.iconId + Res.ID_ICON_SKILL));
            if (imgskill != null && imgskill.img != null && skill.type == 1 && Char.levelSkill[i2] >= 0) {
                int lac = 0;
                int[] nArray = new int[8];
                nArray[0] = -2;
                nArray[1] = -1;
                nArray[3] = 1;
                nArray[4] = 2;
                nArray[5] = 1;
                nArray[7] = -1;
                int[] list = nArray;
                if (lengSkillBuff == this.listSkillBuf.selectedItem) {
                    lac = list[GameCanvas.gameTick % 8];
                }
                g.drawRegion(GameScr.imgTouchMove[3], 0, hkill, wkill, hkill, 0, this.xKhungAuto + 20 + (size + 5) * (lengSkillBuff + 1) - size / 2 + lac, this.yKhungAuto + this.hKhungAuto - size / 2 - 5, 3, false);
                g.drawImage(imgskill.img, this.xKhungAuto + 20 + (size + 5) * (lengSkillBuff + 1) - size / 2 + lac, this.yKhungAuto + this.hKhungAuto - size / 2 - 5, 3, false);
                ++lengSkillBuff;
            }
            ++i2;
        }
        mGraphics.resetTransAndroid(g);
        g.restoreCanvas();
        GameCanvas.resetTrans(g);
    }

    public void updateKeyAuto() {
        if (GameCanvas.keyPressedz[13]) {
            this.cmdClose.performAction();
        }
        boolean isUpdate = false;
        if (!this.isViewTab) {
            if (GameCanvas.keyPressedz[4]) {
                indexAuto = indexAuto - 1 < 0 ? 0 : indexAuto - 1;
                isUpdate = true;
            }
            if (GameCanvas.keyPressedz[6]) {
                indexAuto = indexAuto + 1 > T.nameTabAuto.length - 1 ? T.nameTabAuto.length - 1 : indexAuto + 1;
                isUpdate = true;
            }
        }
        if (GameCanvas.keyPressedz[2] && indexAuto != 2) {
            if (this.indexFocusViewTab > -1) {
                --this.indexFocusViewTab;
            }
            if (this.indexFocusViewTab == -1) {
                this.isViewTab = false;
                this.FocusAutoDanh[0] = false;
                MenuSelectItem.FocusAutoNhat[0] = false;
            }
            if (indexAuto == 1 && this.indexFocusViewTab != -1) {
                this.listAutoGetitem.moveTo(this.indexFocusViewTab * 20);
            }
            isUpdate = true;
        }
        if (GameCanvas.keyPressedz[8] && (indexAuto != 2 || indexAuto == 2 && this.indexFocusViewTab == -1)) {
            ++this.indexFocusViewTab;
            if (indexAuto == 1) {
                if (this.indexFocusViewTab > isAutoNhat.length - 1) {
                    this.indexFocusViewTab = 0;
                }
                this.listAutoGetitem.moveTo(this.indexFocusViewTab * 20);
            }
            if (this.indexFocusViewTab >= 0) {
                this.isViewTab = true;
            }
            isUpdate = true;
        }
        if (this.isViewTab) {
            switch (indexAuto) {
                case 0: {
                    if (this.indexFocusViewTab > this.FocusAutoDanh.length - 1) {
                        this.indexFocusViewTab = this.FocusAutoDanh.length - 1;
                    }
                    if (isUpdate) {
                        int i = 0;
                        while (i < this.FocusAutoDanh.length) {
                            this.FocusAutoDanh[i] = false;
                            ++i;
                        }
                    }
                    this.FocusAutoDanh[this.indexFocusViewTab] = true;
                    if (this.FocusAutoDanh[2] && this.isAutoDanh[1]) {
                        if (GameCanvas.keyPressedz[4]) {
                            int n = this.indexPerHp = this.indexPerHp - 1 <= 1 ? 1 : this.indexPerHp - 1;
                        }
                        if (GameCanvas.keyPressedz[6]) {
                            int n = this.indexPerHp = this.indexPerHp + 1 >= 9 ? 9 : this.indexPerHp + 1;
                        }
                    }
                    if (this.FocusAutoDanh[4] && this.isAutoDanh[3]) {
                        if (GameCanvas.keyPressedz[4]) {
                            int n = this.indexPerMp = this.indexPerMp - 1 <= 1 ? 1 : this.indexPerMp - 1;
                        }
                        if (GameCanvas.keyPressedz[6]) {
                            int n = this.indexPerMp = this.indexPerMp + 1 >= 9 ? 9 : this.indexPerMp + 1;
                        }
                    }
                    if (!GameCanvas.keyPressedz[5]) break;
                    if (this.FocusAutoDanh[0]) {
                        boolean bl = this.isAutoDanh[0] = !this.isAutoDanh[0];
                    }
                    if (this.FocusAutoDanh[1]) {
                        boolean bl = this.isAutoDanh[1] = !this.isAutoDanh[1];
                    }
                    if (this.FocusAutoDanh[3]) {
                        boolean bl = this.isAutoDanh[3] = !this.isAutoDanh[3];
                    }
                    if (this.FocusAutoDanh[6]) {
                        boolean bl = this.isAutoDanh[6] = !this.isAutoDanh[6];
                    }
                    if (!this.FocusAutoDanh[5]) break;
                    this.isAutoDanh[5] = !this.isAutoDanh[5];
                    break;
                }
                case 1: {
                    if (this.indexFocusViewTab > FocusAutoNhat.length - 1) {
                        this.indexFocusViewTab = FocusAutoNhat.length - 1;
                    }
                    if (isUpdate) {
                        int i = 0;
                        while (i < FocusAutoNhat.length) {
                            MenuSelectItem.FocusAutoNhat[i] = false;
                            ++i;
                        }
                        MenuSelectItem.FocusAutoNhat[this.indexFocusViewTab] = true;
                    }
                    if (!GameCanvas.keyPressedz[5] || this.indexFocusViewTab < 0) break;
                    if (isAutoNhat[this.indexFocusViewTab]) {
                        MenuSelectItem.isAutoNhat[this.indexFocusViewTab] = false;
                        break;
                    }
                    MenuSelectItem.isAutoNhat[this.indexFocusViewTab] = true;
                    break;
                }
                case 2: {
                    if (GameCanvas.keyPressedz[6] && !this.isSelectSkill) {
                        int n = this.indexFocusViewTab = this.indexFocusViewTab + 1 > 2 ? 2 : this.indexFocusViewTab + 1;
                    }
                    if (GameCanvas.keyPressedz[4] && !this.isSelectSkill) {
                        int n = this.indexFocusViewTab = this.indexFocusViewTab - 1 < 0 ? 0 : this.indexFocusViewTab - 1;
                    }
                    if (GameCanvas.keyPressedz[5]) {
                        this.isSelectSkill = !this.isSelectSkill;
                        this.listSkillBuf.selectedItem = this.isSelectSkill ? 0 : -1;
                    }
                    if (this.isSelectSkill) {
                        if (GameCanvas.keyPressedz[6]) {
                            ++this.listSkillBuf.selectedItem;
                        }
                        if (GameCanvas.keyPressedz[4]) {
                            --this.listSkillBuf.selectedItem;
                        }
                    }
                    if (!GameCanvas.keyPressedz[2]) break;
                    this.indexFocusViewTab = -1;
                    this.isViewTab = false;
                    break;
                }
                case 3: {
                    if (this.indexFocusViewTab > this.FocusTabMucTieu.length - 1) {
                        this.indexFocusViewTab = this.FocusTabMucTieu.length - 1;
                    }
                    if (isUpdate) {
                        int i = 0;
                        while (i < this.FocusTabMucTieu.length) {
                            this.FocusTabMucTieu[i] = false;
                            ++i;
                        }
                        this.FocusTabMucTieu[this.indexFocusViewTab] = true;
                    }
                    if (!GameCanvas.keyPressedz[5]) break;
                    boolean bl = this.isTabFocus[this.indexFocusViewTab] = !this.isTabFocus[this.indexFocusViewTab];
                    if (this.indexFocusViewTab == 1) {
                        if (!this.isTabFocus[this.indexFocusViewTab]) {
                            mSound.pauseCurMusic();
                        }
                        if (this.isTabFocus[this.indexFocusViewTab]) {
                            GameScr.playSound1();
                        }
                    }
                    Rms.saveSound();
                }
            }
        }
        GameCanvas.clearKeyPressed();
        GameCanvas.clearKeyHold();
    }

    public void updatePointerAuto() {
        if (this.getCmdPointerLast(this.cmdClose)) {
            GameCanvas.isPointerJustRelease[0] = false;
            if (this.cmdClose != null) {
                this.cmdClose.performAction();
            }
        }
        if (indexAuto == 1) {
            ScrollResult r = this.listAutoGetitem.updateKey();
            this.listAutoGetitem.updatecm();
        }
        int i = 0;
        while (i < T.nameTabAuto.length) {
            if (GameCanvas.isPointerClick[0] && i != indexAuto && GameCanvas.isPointer(this.xKhungAuto + i * this.sizeauto + 1, this.yKhungAuto + 1, this.sizeauto, 21, 0)) {
                indexAuto = i;
            }
            ++i;
        }
		switch (indexAuto) {
            case 0: {
                i = 0;
                while (i < this.xyCheckAutoDanh.length) {
                    if (GameCanvas.isPointerClick[0] && GameCanvas.isPointer(this.xyCheckAutoDanh[i][0] - 10, this.xyCheckAutoDanh[i][1] - 10, this.wKhungAuto - 20, 20, 0)) {
                        boolean bl = this.isAutoDanh[i] = !this.isAutoDanh[i];
                        if (i == 0) {
                            Rms.saveRMSInt(Rms.Auto_fight, this.isAutoDanh[i] ? 1 : 0);
                        }
                        if (i == 1) {
                            Rms.saveRMSInt(Rms.Auto_HP, this.isAutoDanh[i] ? 1 : 0);
                        }
                        if (i == 3) {
                            Rms.saveRMSInt(Rms.Auto_MP, this.isAutoDanh[i] ? 1 : 0);
                        }
                        if (i == 6) {
                            Rms.saveRMSInt(Rms.pickALL, this.isAutoDanh[i] ? 1 : 0);
                        }
                    }
                    ++i;
                }
                i = 0;
                while (i < this.xyNutTangHpMp.length) {
                    if (GameCanvas.isPointerClick[0] && GameCanvas.isPointer(this.xyNutTangHpMp[i][0] - 10, this.xyNutTangHpMp[i][1] - 10, 20, 20, 0)) {
                        if (i == 0) {
                            int n = this.indexPerHp = this.indexPerHp - 1 <= 1 ? 1 : this.indexPerHp - 1;
                        }
                        if (i == 1) {
                            int n = this.indexPerHp = this.indexPerHp + 1 >= 9 ? 9 : this.indexPerHp + 1;
                        }
                        if (i == 2) {
                            int n = this.indexPerMp = this.indexPerMp - 1 <= 1 ? 1 : this.indexPerMp - 1;
                        }
                        if (i == 3) {
                            this.indexPerMp = this.indexPerMp + 1 >= 9 ? 9 : this.indexPerMp + 1;
                        }
                        Rms.saveRMSInt(Rms.PerCent_HP, this.indexPerHp);
                        Rms.saveRMSInt(Rms.PerCent_MP, this.indexPerMp);
                    }
                    ++i;
                }
                break;
            }
            case 1: {
                break;
            }
            case 2: {
                if (GameCanvas.gameTick % 2 == 0) {
                    ++MainMenu.gI().coutFc;
                    if (MainMenu.gI().coutFc > 2) {
                        MainMenu.gI().coutFc = 0;
                    }
                }
                i = 0;
                while (i < this.xySkillBuff.length) {
                    if (GameCanvas.isPointerClick[0] && GameCanvas.isPointer(this.xySkillBuff[i][0] - 15, this.xySkillBuff[i][1] - 15, 30, 30, 0)) {
                        this.indexFocusViewTab = i;
                    }
                    ++i;
                }
                break;
            }
            case 3: {
				this.listTabMucTieu.setStyle(T.Tabmuctieu.length, 20, this.xKhungAuto, this.yKhungAuto + 30, this.wKhungAuto, this.hKhungAuto - 30, true, 1);
				ScrollResult r = this.listTabMucTieu.updateKey();
				this.listTabMucTieu.updatecm();
				if (GameCanvas.isPointerClick[0] && this.listTabMucTieu.selectedItem != -1 && !this.listTabMucTieu.isDownWhenRunning) {
					this.isTabFocus[this.listTabMucTieu.selectedItem] = !this.isTabFocus[this.listTabMucTieu.selectedItem];
					if (this.listTabMucTieu.selectedItem == 1) {
						if (!this.isTabFocus[1]) {
							mSound.pauseCurMusic();
						}
						if (this.isTabFocus[1]) {
							GameScr.playSound1();
						}
						Rms.saveSound();
					}
					this.listTabMucTieu.selectedItem = -1;
				}
                break;
            }
        }
    }

    public boolean isCanFind(byte typeActor) {
        if (this.isTabFocus[this.isTabFocus.length - 1]) {
            return true;
        }
        if (typeActor == 0 && this.isTabFocus[0]) {
            return true;
        }
        if (typeActor == 1 && this.isTabFocus[1]) {
            return true;
        }
        if (typeActor == 2 && this.isTabFocus[2]) {
            return true;
        }
        return (typeActor == 14 || typeActor == 3) && this.isTabFocus[3];
    }
}
