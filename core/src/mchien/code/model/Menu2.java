/*
 * Decompiled with CFR 0.152.
 */
package mchien.code.model;

import mchien.code.main.GameCanvas;
import mchien.code.model.Char;
import mchien.code.model.IActionListener;
import mchien.code.model.ImageIcon;
import mchien.code.model.Info_Party;
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
import mchien.code.screen.screen.Khu;
import mchien.code.screen.screen.MainMenu;
import lib.mGraphics;
import lib.mSystem;
import lib.mVector;
import lib2.mFont;

public class Menu2
implements IActionListener {
    public int xKhungAuto;
    public int yKhungAuto;
    public int wKhungAuto;
    public int hKhungAuto;
    public int sizeH = 40;
    public int indexSelect = -1;
    public static Menu2 me;
    public Scroll mScroll = new Scroll();
    public mCommand cmdClose;
    public mCommand cmdpk;
    public mCommand cmdchange;
    public mVector menuItem = new mVector();
    public mVector menucmdAction = new mVector();
    public byte type;
    public byte typeInfo;
    public static final byte TYPE_TEXT = 0;
    public static final byte TYPE_PK = 1;
    public static final byte TYPE_FRIEND = 2;
    public static final byte TYPE_PARTY = 3;
    public static final byte TYPE_INFO = 4;
    public static final byte TYPE_CHAR_DIE = 5;
    public String masterName = "";
    public boolean isShow;
    public String tile;
    public short idIcon;
    public int nitem;
    public int idNPC;
    public int idMenu;
    int[] xpk;

    public void startArt(mVector menuItem, int type, String tile, int numitem) {
        String[] arr;
        int wslip;
        int j;
        ServerInfo sv;
        int i;
        this.mScroll.moveTo(0);
        this.sizeH = 40;
        this.nitem = numitem;
        this.type = (byte)type;
        this.menuItem = menuItem;
        this.wKhungAuto = 140;
        this.hKhungAuto = 130;
        if (type == 1) {
            this.wKhungAuto = 170;
            this.hKhungAuto = GameCanvas.h / 4;
        }
        if (type == 4) {
            this.wKhungAuto = 200;
            if (this.wKhungAuto > GameCanvas.w) {
                this.wKhungAuto = GameCanvas.w;
            }
        }
        this.xKhungAuto = GameCanvas.w / 2 - this.wKhungAuto / 2;
        this.yKhungAuto = GameCanvas.h / 2 - this.hKhungAuto / 2;
        this.cmdClose = new mCommand(T.close, this, 200);
        int xcmd = this.xKhungAuto + this.wKhungAuto / 2 - mCommand.wButtonCmd / 2;
        this.indexSelect = -1;
        if (type == 1) {
            xcmd = this.xKhungAuto;
            this.xpk = new int[5];
            i = 0;
            while (i < this.xpk.length) {
                this.xpk[i] = this.xKhungAuto + 18 + 33 * i;
                ++i;
            }
            this.cmdpk = new mCommand(T.thaoco, this, 1);
            this.cmdpk.setXY(this.xKhungAuto + this.wKhungAuto / 2 + 5, this.yKhungAuto + this.hKhungAuto + 2);
            this.indexSelect = GameScr.mainChar.getTypePK() - 1;
        }
        this.cmdClose.setXY(xcmd, this.yKhungAuto + this.hKhungAuto + 2);
        this.tile = tile;
        if (type == 2) {
            i = 0;
            while (i < menuItem.size()) {
                sv = (ServerInfo)menuItem.elementAt(i);
                if (sv != null) {
                    j = 0;
                    while (j < sv.info.length) {
                        wslip = 0;
                        if (!GameCanvas.isTouch) {
                            wslip = 20;
                        }
                        arr = mFont.tahoma_7_white.splitFontArray(sv.info[j], this.wKhungAuto + wslip - 20);
                        this.nitem += arr.length;
                        ++j;
                    }
                }
                ++i;
            }
            this.sizeH = 50;
        }
        this.isShow = true;
        if (type == 4) {
            i = 0;
            while (i < menuItem.size()) {
                sv = (ServerInfo)menuItem.elementAt(i);
                if (sv != null) {
                    j = 0;
                    while (j < sv.info.length) {
                        wslip = 0;
                        if (!GameCanvas.isTouch) {
                            wslip = 20;
                        }
                        if ((arr = mFont.tahoma_7_white.splitFontArray(sv.info[j], this.wKhungAuto + wslip - 20)) != null && arr.length > 1) {
                            this.nitem += arr.length - 1;
                        }
                        ++j;
                    }
                }
                ++i;
            }
        }
    }

    public void startArt(mVector menuItem, int type, String tile) {
        this.mScroll.moveTo(0);
        this.sizeH = 40;
        this.type = (byte)type;
        this.menuItem = menuItem;
        this.wKhungAuto = 140;
        this.hKhungAuto = 130;
        if (type == 1) {
            this.wKhungAuto = 170;
            this.hKhungAuto = GameCanvas.h / 4;
        }
        if (type == 4) {
            this.wKhungAuto = 200;
            if (this.wKhungAuto > GameCanvas.w) {
                this.wKhungAuto = GameCanvas.w;
            }
        }
        this.xKhungAuto = GameCanvas.w / 2 - this.wKhungAuto / 2;
        this.yKhungAuto = GameCanvas.h / 2 - this.hKhungAuto / 2;
        this.cmdClose = new mCommand("Đóng", this, 200);
        int xcmd = this.xKhungAuto + this.wKhungAuto / 2 - mCommand.wButtonCmd / 2;
        this.indexSelect = -1;
        if (type == 1) {
            xcmd = this.xKhungAuto;
            this.xpk = new int[5];
            int i = 0;
            while (i < this.xpk.length) {
                this.xpk[i] = this.xKhungAuto + 18 + 33 * i;
                ++i;
            }
            this.cmdpk = new mCommand(T.thaoco, this, 1);
            this.cmdpk.setXY(this.xKhungAuto + this.wKhungAuto / 2 + 5, this.yKhungAuto + this.hKhungAuto + 2);
            this.indexSelect = GameScr.mainChar.getTypePK() - 1;
        }
        this.cmdClose.setXY(xcmd, this.yKhungAuto + this.hKhungAuto + 2);
        this.tile = tile;
        if (type == 2) {
            this.sizeH = 50;
        }
        this.isShow = true;
    }

    public void startArt(mVector menuItem, int type, String tile, int idmenu, int idNPC) {
        this.mScroll.moveTo(0);
        this.sizeH = 40;
        this.type = (byte)type;
        this.menuItem = menuItem;
        this.wKhungAuto = 140;
        this.hKhungAuto = 130;
        this.idMenu = idmenu;
        this.idNPC = idNPC;
        if (type == 1) {
            this.wKhungAuto = 170;
            this.hKhungAuto = GameCanvas.h / 4;
        }
        if (type == 5) {
            this.wKhungAuto = menuItem.size() * 65 + 10;
            this.hKhungAuto = 79;
            this.xKhungAuto = GameCanvas.w / 2 - this.wKhungAuto / 2;
            this.yKhungAuto = GameCanvas.h - this.hKhungAuto - 5;
            int i = 0;
            while (i < menuItem.size()) {
                mCommand cmd = (mCommand)menuItem.elementAt(i);
                if (cmd != null) {
                    cmd.setindexImage(2);
                    cmd.setPos(this.xKhungAuto + 7 + i * 65, this.yKhungAuto + 11);
                }
                ++i;
            }
        }
        if (type == 4) {
            this.wKhungAuto = 200;
            if (this.wKhungAuto > GameCanvas.w) {
                this.wKhungAuto = GameCanvas.w;
            }
        }
        this.cmdClose = new mCommand("\u0110\u00f3ng", this, 200);
        int xcmd = this.xKhungAuto + this.wKhungAuto / 2 - mCommand.wButtonCmd / 2;
        this.indexSelect = -1;
        if (type == 1) {
            xcmd = this.xKhungAuto;
            this.xpk = new int[5];
            int i = 0;
            while (i < this.xpk.length) {
                this.xpk[i] = this.xKhungAuto + 18 + 33 * i;
                ++i;
            }
            this.cmdpk = new mCommand(T.thaoco, this, 1);
            this.cmdpk.setXY(this.xKhungAuto + this.wKhungAuto / 2 + 5, this.yKhungAuto + this.hKhungAuto + 2);
            this.indexSelect = GameScr.mainChar.getTypePK() - 1;
        }
        this.cmdClose.setXY(xcmd, this.yKhungAuto + this.hKhungAuto + 2);
        this.tile = tile;
        if (type == 2) {
            this.sizeH = 50;
        }
        this.isShow = true;
        if (type == 5) {
            this.cmdClose = null;
        }
    }

    public void startArtParty(mVector menuItem, int type, String tile, String mastername) {
        this.mScroll.moveTo(0);
        this.sizeH = 40;
        this.masterName = mastername;
        this.type = (byte)type;
        this.menuItem = menuItem;
        this.wKhungAuto = 140;
        this.hKhungAuto = 130;
        this.xKhungAuto = GameCanvas.w / 2 - this.wKhungAuto / 2;
        this.yKhungAuto = GameCanvas.h / 2 - this.hKhungAuto / 2;
        this.cmdClose = new mCommand("\u0110\u00f3ng", this, 200);
        this.cmdClose.setXY(this.xKhungAuto + this.wKhungAuto / 2 - mCommand.wButtonCmd / 2, this.yKhungAuto + this.hKhungAuto + 2);
        this.menucmdAction.removeAllElements();
        this.menucmdAction.addElement(new mCommand("R\u1eddi \u0111\u1ed9i", this, 2));
        if (GameScr.mainChar.getName().equals(mastername)) {
            this.menucmdAction.addElement(new mCommand("Tr\u1ee5c xu\u1ea5t", this, 1));
            this.menucmdAction.addElement(new mCommand("Gi\u1ea3i t\u00e1n", this, 3));
        }
        this.menucmdAction.addElement(new mCommand("Ch\u00e1t nh\u00f3m", this, 4));
        this.tile = tile;
        this.indexSelect = -1;
        this.isShow = true;
    }

    public void startArt(mVector listChar, int type, String tile, String[] cmdAction) {
        this.mScroll.moveTo(0);
        this.typeInfo = (byte)type;
        this.type = (byte)2;
        this.menuItem = listChar;
        this.menucmdAction.removeAllElements();
        if (cmdAction != null) {
            int i = 0;
            while (i < cmdAction.length) {
                this.menucmdAction.addElement(new mCommand(cmdAction[i], this, i));
                ++i;
            }
        }
        this.sizeH = 40;
        this.wKhungAuto = 140;
        this.hKhungAuto = 130;
        if (this.type == 2 && this.typeInfo == 1) {
            this.wKhungAuto = 170;
        }
        this.xKhungAuto = GameCanvas.w / 2 - this.wKhungAuto / 2;
        this.yKhungAuto = GameCanvas.h / 2 - this.hKhungAuto / 2;
        this.cmdClose = new mCommand("\u0110\u00f3ng", this, 200);
        this.cmdClose.setXY(this.xKhungAuto + this.wKhungAuto / 2 - mCommand.wButtonCmd / 2, this.yKhungAuto + this.hKhungAuto + 2);
        this.tile = tile;
        this.indexSelect = -1;
        if (this.type == 2 && this.typeInfo == 0) {
            this.sizeH = 40;
        }
        if (this.type == 2 && this.typeInfo == 1) {
            this.cmdchange = new mCommand(T.doi, this, 2);
            if (cmdAction != null && cmdAction.length > 0) {
                this.cmdchange.caption = cmdAction[0];
            }
            this.cmdchange.setXY(this.xKhungAuto + this.wKhungAuto - mCommand.wButtonCmd, this.yKhungAuto + this.hKhungAuto + 2);
            this.indexSelect = GameScr.arena - 1;
            this.mScroll.moveTo(this.indexSelect * this.sizeH);
            this.cmdClose.x = this.xKhungAuto;
        }
        if (this.typeInfo == 1) {
            this.sizeH = 35;
        }
        this.isShow = true;
    }

    public static Menu2 gI() {
        return me == null ? (me = new Menu2()) : me;
    }

    public void update() {
        mCommand cmd;
        if (!this.isShow) {
            return;
        }
        if (GameCanvas.isKeyPressed(12)) {
            if (this.cmdClose != null) {
                this.cmdClose.performAction();
            }
            return;
        }
        if (GameCanvas.isKeyPressed(5)) {
            mCommand cmd2;
            if (this.type == 0 && (cmd2 = (mCommand)this.menuItem.elementAt(this.indexSelect)) != null) {
                cmd2.performAction();
            }
            if (this.cmdchange != null) {
                this.cmdchange.performAction();
            }
            if (this.cmdClose != null) {
                this.cmdClose.performAction();
            }
            return;
        }
        if (this.cmdClose != null && GameCanvas.isPointerHoldIn(this.cmdClose.x, this.cmdClose.y, mCommand.wButtonCmd, mCommand.hButtonCmd, 0) && !GameCanvas.menu.showMenu) {
            if (GameCanvas.isPointerDown[0]) {
                this.cmdClose.isFocus = true;
            }
            if (GameCanvas.isPointerJustRelease[0] && GameCanvas.isPointerClick[0] && GameCanvas.canTouch()) {
                this.cmdClose.performAction();
                GameCanvas.isPointerJustRelease[0] = false;
                GameCanvas.isPointerClick[0] = false;
                return;
            }
        } else if (this.cmdClose != null) {
            this.cmdClose.isFocus = false;
        }
        if (this.type == 1) {
            int i = 0;
            while (i < this.xpk.length) {
                if (GameCanvas.isPointer(this.xpk[i] - 15, this.yKhungAuto + this.hKhungAuto / 2 - 15, 30, 30, 0) && this.indexSelect != i) {
                    this.indexSelect = i;
                }
                ++i;
            }
            if (GameCanvas.isPointerHoldIn(this.cmdpk.x, this.cmdpk.y, mCommand.wButtonCmd, mCommand.hButtonCmd, 0)) {
                if (GameCanvas.isPointerDown[0]) {
                    this.cmdpk.isFocus = true;
                }
                if (GameCanvas.isPointerJustRelease[0] && GameCanvas.isPointerClick[0] && GameCanvas.canTouch()) {
                    this.cmdpk.performAction();
                    GameCanvas.isPointerJustRelease[0] = false;
                    GameCanvas.isPointerClick[0] = false;
                }
            } else {
                this.cmdpk.isFocus = false;
            }
            if (GameCanvas.isKeyPressed(4)) {
                --this.indexSelect;
                if (this.indexSelect < 0) {
                    this.indexSelect = 4;
                }
            }
            if (GameCanvas.isKeyPressed(6)) {
                ++this.indexSelect;
                if (this.indexSelect > 4) {
                    this.indexSelect = 0;
                }
            }
            if (GameCanvas.isKeyPressed(13)) {
                this.cmdpk.performAction();
            }
            if (GameCanvas.isKeyPressed(12)) {
                this.cmdClose.performAction();
            }
            if (GameCanvas.isKeyPressed(5)) {
                this.cmdClose.performAction();
            }
            if (this.cmdClose != null && GameCanvas.isPointerHoldIn(this.cmdClose.x, this.cmdClose.y, mCommand.wButtonCmd, mCommand.hButtonCmd, 0) && !GameCanvas.menu.showMenu) {
                if (GameCanvas.isPointerDown[0]) {
                    this.cmdClose.isFocus = true;
                }
                if (GameCanvas.isPointerJustRelease[0] && GameCanvas.isPointerClick[0] && GameCanvas.canTouch()) {
                    this.cmdClose.performAction();
                    GameCanvas.isPointerJustRelease[0] = false;
                    GameCanvas.isPointerClick[0] = false;
                }
            } else if (this.cmdClose != null) {
                this.cmdClose.isFocus = false;
            }
            return;
        }
        if (this.type != 4 && this.type != 5) {
            ScrollResult i = this.mScroll.updateKey();
        }
        if (this.cmdchange != null) {
            if (GameCanvas.isPointerHoldIn(this.cmdchange.x, this.cmdchange.y, mCommand.wButtonCmd, mCommand.hButtonCmd, 0)) {
                if (GameCanvas.isPointerDown[0]) {
                    this.cmdchange.isFocus = true;
                }
                if (GameCanvas.isPointerJustRelease[0] && GameCanvas.isPointerClick[0] && GameCanvas.canTouch()) {
                    this.cmdchange.performAction();
                    GameCanvas.isPointerJustRelease[0] = false;
                    GameCanvas.isPointerClick[0] = false;
                }
            } else {
                this.cmdchange.isFocus = false;
            }
        }
        if (this.cmdClose != null && GameCanvas.isPointerHoldIn(this.cmdClose.x, this.cmdClose.y, mCommand.wButtonCmd, mCommand.hButtonCmd, 0) && !GameCanvas.menu.showMenu) {
            if (GameCanvas.isPointerDown[0]) {
                this.cmdClose.isFocus = true;
            }
            if (GameCanvas.isPointerJustRelease[0] && GameCanvas.isPointerClick[0] && GameCanvas.canTouch()) {
                this.cmdClose.performAction();
                GameCanvas.isPointerJustRelease[0] = false;
                GameCanvas.isPointerClick[0] = false;
            }
        } else if (this.cmdClose != null) {
            this.cmdClose.isFocus = false;
        }
        if (GameCanvas.isPointerClick[0] && this.mScroll.selectedItem != -1 && !this.mScroll.isDownWhenRunning && this.type != 1 && this.type != 5 && GameCanvas.isPointer(this.xKhungAuto, this.yKhungAuto, this.wKhungAuto, this.hKhungAuto, 0)) {
            if (this.indexSelect != this.mScroll.selectedItem) {
                this.indexSelect = this.mScroll.selectedItem;
                return;
            }
            if (this.type == 0) {
                cmd = (mCommand)this.menuItem.elementAt(this.indexSelect);
                if (cmd != null) {
                    cmd.performAction();
                }
            } else if (this.type == 2) {
                if (this.typeInfo == 0) {
                    if (this.menucmdAction.size() == 1) {
                        cmd = (mCommand)this.menucmdAction.elementAt(0);
                        if (cmd != null) {
                            cmd.performAction();
                        }
                    } else {
                        GameCanvas.menu.startAt(this.menucmdAction, 2);
                    }
                } else if (this.typeInfo == 1) {
                    this.cmdchange.performAction();
                }
            } else if (this.type == 3) {
                if (this.menucmdAction.size() == 1) {
                    cmd = (mCommand)this.menucmdAction.elementAt(0);
                    if (cmd != null) {
                        cmd.performAction();
                    }
                } else {
                    GameCanvas.menu.startAt(this.menucmdAction, 2);
                }
            }
        }
        if (this.type == 4) {
            if (GameCanvas.isKeyPressed(2)) {
                --this.indexSelect;
                if (this.indexSelect < 0) {
                    this.indexSelect = this.nitem - 1;
                }
                this.mScroll.moveTo(this.indexSelect * 15);
            }
            if (GameCanvas.isKeyPressed(8)) {
                ++this.indexSelect;
                if (this.indexSelect > this.nitem - 1) {
                    this.indexSelect = 0;
                }
                this.mScroll.moveTo(this.indexSelect * 15);
            }
            if (GameCanvas.isKeyPressed(5)) {
                this.cmdClose.performAction();
            }
            this.mScroll.updateKey();
            this.mScroll.updatecm();
            if (GameCanvas.isKeyPressed(13)) {
                this.cmdClose.performAction();
            }
        }
        if (this.type != 1 && this.type != 4 && this.type != 5) {
            if (GameCanvas.isKeyPressed(2)) {
                --this.indexSelect;
                if (this.indexSelect < 0) {
                    this.indexSelect = this.menuItem.size() - 1;
                }
                this.mScroll.moveTo(this.indexSelect * this.sizeH);
            }
            if (GameCanvas.isKeyPressed(8)) {
                ++this.indexSelect;
                if (this.indexSelect > this.menuItem.size() - 1) {
                    this.indexSelect = 0;
                }
                this.mScroll.moveTo(this.indexSelect * this.sizeH);
            }
            if (GameCanvas.isKeyPressed(5)) {
                if (this.type == 0) {
                    cmd = (mCommand)this.menuItem.elementAt(this.indexSelect);
                    if (cmd != null) {
                        cmd.performAction();
                    }
                } else if (this.type == 2) {
                    if (this.menucmdAction.size() == 1) {
                        cmd = (mCommand)this.menucmdAction.elementAt(0);
                        if (cmd != null) {
                            cmd.performAction();
                        }
                    } else {
                        GameCanvas.menu.startAt(this.menucmdAction, 2);
                    }
                } else if (this.type == 3) {
                    if (this.menucmdAction.size() == 1) {
                        cmd = (mCommand)this.menucmdAction.elementAt(0);
                        if (cmd != null) {
                            cmd.performAction();
                        }
                    } else {
                        GameCanvas.menu.startAt(this.menucmdAction, 2);
                    }
                }
            }
            this.mScroll.updatecm();
            if (GameCanvas.isKeyPressed(13)) {
                this.cmdClose.performAction();
            }
        }
        if (this.type == 5) {
            if (GameCanvas.gameScr.chatMode || GameCanvas.gameScr.chatWorld || GameCanvas.currentScreen == MainMenu.gI() || GameCanvas.currentDialog != null) {
                return;
            }
            if (GameCanvas.isTouch) {
                int i = 0;
                while (i < this.menuItem.size()) {
                    mCommand cmd3 = (mCommand)this.menuItem.elementAt(i);
                    if (cmd3 != null) {
                        if (GameCanvas.isPointerHoldIn(cmd3.x, cmd3.y, 60, 59, 0)) {
                            if (GameCanvas.isPointerDown[0]) {
                                cmd3.isFocus = true;
                            }
                            if (GameCanvas.isPointerJustRelease[0] && GameCanvas.isPointerClick[0] && GameCanvas.canTouch()) {
                                this.indexSelect = i;
                                cmd3.performAction();
                                GameCanvas.isPointerJustRelease[0] = false;
                                GameCanvas.isPointerClick[0] = false;
                                this.isShow = false;
                            }
                        } else {
                            cmd3.isFocus = false;
                        }
                    }
                    ++i;
                }
            } else {
                mCommand cmdf;
                mCommand cmd4;
                if (GameCanvas.isKeyPressed(4)) {
                    --this.indexSelect;
                    if (this.indexSelect < 0) {
                        this.indexSelect = this.menuItem.size() - 1;
                    }
                    int i = 0;
                    while (i < this.menuItem.size()) {
                        cmd4 = (mCommand)this.menuItem.elementAt(i);
                        if (cmd4 != null) {
                            cmd4.isFocus = false;
                        }
                        ++i;
                    }
                    cmdf = (mCommand)this.menuItem.elementAt(this.indexSelect);
                    if (cmdf != null) {
                        cmdf.isFocus = true;
                    }
                }
                if (GameCanvas.isKeyPressed(6)) {
                    ++this.indexSelect;
                    if (this.indexSelect > this.menuItem.size() - 1) {
                        this.indexSelect = 0;
                    }
                    int i = 0;
                    while (i < this.menuItem.size()) {
                        cmd4 = (mCommand)this.menuItem.elementAt(i);
                        if (cmd4 != null) {
                            cmd4.isFocus = false;
                        }
                        ++i;
                    }
                    cmdf = (mCommand)this.menuItem.elementAt(this.indexSelect);
                    if (cmdf != null) {
                        cmdf.isFocus = true;
                    }
                }
                if (GameCanvas.isKeyPressed(5) && (cmdf = (mCommand)this.menuItem.elementAt(this.indexSelect)) != null) {
                    cmdf.performAction();
                    this.isShow = false;
                }
            }
        }
    }

    public void paint(mGraphics g) {
        ImageIcon img;
        if (!this.isShow) {
            return;
        }
        if (this.type == 5 && (GameCanvas.gameScr.chatMode || GameCanvas.gameScr.chatWorld || GameCanvas.currentScreen == MainMenu.gI() || GameCanvas.currentDialog != null)) {
            return;
        }
        Res.paintDlgDragonFullNew(g, this.xKhungAuto, this.yKhungAuto, this.wKhungAuto, this.hKhungAuto, 60, 60, GameScr.imgBk[0], false);
        g.setColor(-9751532);
        g.fillRect(this.xKhungAuto, this.yKhungAuto - 28, this.wKhungAuto, 28, false);
        int i = 0;
        while (i < 3) {
            g.setColor(Res.nColor[i]);
            g.drawRect(this.xKhungAuto + i, this.yKhungAuto - 28 + i, this.wKhungAuto - i * 2, 28 - i * 2, false);
            ++i;
        }
        GameCanvas.resetTrans(g);
        if (this.type != 1 && this.type != 5) {
            int hclip = this.hKhungAuto - 10;
            int hs = 0;
            if (this.type == 4) {
                hclip = this.hKhungAuto - 10 + 3 - (this.idIcon != -1 ? 20 : 0);
                if (this.idIcon != -1) {
                    hs = 20;
                }
                this.mScroll.setStyle(this.nitem, 16, this.xKhungAuto + 6, this.yKhungAuto, this.wKhungAuto - 12, this.hKhungAuto - 12, true, 1);
            } else {
                this.mScroll.setStyle(this.menuItem.size(), this.sizeH, this.xKhungAuto + 6, this.yKhungAuto, this.wKhungAuto - 12, this.hKhungAuto - 12, true, 1);
            }
            g.ClipRec(this.xKhungAuto + 6, this.yKhungAuto + 5 + hs, this.wKhungAuto - 12, hclip);
            this.mScroll.setClip(g, this.xKhungAuto + 6, this.yKhungAuto + 5 + hs, this.wKhungAuto - 12, hclip);
            int yy = this.yKhungAuto + 10;
            int i2 = 0;
            while (i2 < this.menuItem.size()) {
                if (i2 == this.indexSelect && this.type != 4) {
                    g.setColor(-15904409);
                    if (this.type == 2 && (this.typeInfo == 1 || this.typeInfo == 0)) {
                        if (this.typeInfo == 1) {
                            Res.paintFocus(g, this.xKhungAuto + 8, this.yKhungAuto + 6 + i2 * this.sizeH, this.wKhungAuto - 10);
                        }
                        if (this.typeInfo == 0) {
                            Res.paintFocus(g, this.xKhungAuto + 8, this.yKhungAuto + 8 + i2 * this.sizeH, this.wKhungAuto - 10);
                        }
                    } else if (this.type == 0) {
                        Res.paintFocus(g, this.xKhungAuto + 8, this.yKhungAuto + 8 + i2 * this.sizeH, this.wKhungAuto - 10);
                    } else {
                        g.fillRect(this.xKhungAuto + 8, this.yKhungAuto + 8 + i2 * this.sizeH, this.wKhungAuto - 10, this.sizeH - 1 - 5, true);
                    }
                }
                switch (this.type) {
                    case 0: {
                        mCommand cmd = (mCommand)this.menuItem.elementAt(i2);
                        if (cmd == null) break;
                        GameScr.Font3d(g, cmd.caption, GameCanvas.w / 2, this.yKhungAuto - 25 + (i2 + 1) * this.sizeH + 5, 2, mFont.tahoma_7b_white);
                        break;
                    }
                    case 2: {
                        Khu khu;
                        if (this.typeInfo == 0) {
                            Char chardem = (Char)this.menuItem.elementAt(i2);
                            if (chardem == null) break;
                            chardem.paintFriend(g, this.xKhungAuto + 30, this.yKhungAuto - 5 + (i2 + 1) * this.sizeH + 30, 1);
                            mFont.tahoma_7_white.drawString(g, chardem.getName(), this.xKhungAuto + 50, this.yKhungAuto - 60 + (i2 + 1) * this.sizeH + 30, 0, true);
                            mFont.tahoma_7_white.drawString(g, "Lv: " + chardem.getLevel(), this.xKhungAuto + 50, this.yKhungAuto - 50 + (i2 + 1) * this.sizeH + 30, 0, true);
                            mFont.tahoma_7_white.drawString(g, "Xu: "+Res.getDotNumber(chardem.xu), this.xKhungAuto + 50, this.yKhungAuto - 38 + (i2 + 1) * this.sizeH + 30, 0, true);

                            g.drawRegion(GameScr.statusInfo, 0, 11 * (chardem.online == 0 ? 2 : 0), 11, 11, 0, this.xKhungAuto + this.wKhungAuto - 30, this.yKhungAuto - 45 + (i2 + 1) * this.sizeH + 30, mGraphics.VCENTER | mGraphics.HCENTER, true);
                            ImageIcon img2 = GameData.getImgIcon(chardem.idiConList);
                            if (img2 == null || img2.img == null) break;
                            g.drawImage(img2.img, this.xKhungAuto + 10, this.yKhungAuto - 45 + (i2 + 1) * this.sizeH + 30 + 1, 3, true);
                            break;
                        }
                        if (this.typeInfo != 1 || (khu = (Khu)this.menuItem.elementAt(i2)) == null) break;
                        g.drawRegion(GameScr.statusInfo, 0, 11 * khu.getTrangThai(), 11, 11, 0, GameCanvas.w / 2 - mFont.tahoma_7b_white.getWidth(khu.getTitle()) / 2 - 10, this.yKhungAuto + (i2 + 1) * this.sizeH - this.sizeH / 2 + 4, 3, true);
                        GameScr.Font3d(g, khu.getTitle(), GameCanvas.w / 2, this.yKhungAuto + (i2 + 1) * this.sizeH - this.sizeH / 2, 2, mFont.tahoma_7b_white);
                        break;
                    }
                    case 3: {
                        Info_Party info = (Info_Party)this.menuItem.elementAt(i2);
                        if (info == null) break;
                        if (!info.isMaster) {
                            GameScr.Font3d(g, info.nameMember, GameCanvas.w / 2, this.yKhungAuto - 25 + (i2 + 1) * this.sizeH + 5, 2, mFont.tahoma_7b_white);
                            break;
                        }
                        mFont.tahoma_7_yellow.drawString(g, info.nameMember, GameCanvas.w / 2, this.yKhungAuto - 25 + (i2 + 1) * this.sizeH + 5, 2, true);
                        break;
                    }
                    case 4: {
                        ServerInfo sv = (ServerInfo)this.menuItem.elementAt(i2);
                        if (sv == null) break;
                        mFont.tahoma_7b_white.drawString(g, sv.tile, this.xKhungAuto + 10, yy + 10, 0, true);
                        yy += 15;
                        int j = 0;
                        while (j < sv.info.length) {
                            int wslip = 0;
                            if (!GameCanvas.isTouch) {
                                wslip = 20;
                            }
                            String[] arr = mFont.tahoma_7_white.splitFontArray(sv.info[j], this.wKhungAuto + wslip - 20);
                            int k = 0;
                            while (k < arr.length) {
                                mFont.tahoma_7_white.drawString(g, arr[k], this.xKhungAuto + 12, yy + 10, 0, true);
                                yy += 15;
                                ++k;
                            }
                            ++j;
                        }
                        break;
                    }
                }
                if (this.type != 4) {
                    g.setColor(Res.nColor[1]);
                    if (i2 < this.menuItem.size() - 1) {
                        g.fillRect(this.xKhungAuto + 8, this.yKhungAuto + 5 + (i2 + 1) * this.sizeH, this.wKhungAuto - 8, 1, true);
                    }
                }
                ++i2;
            }
            mGraphics.resetTransAndroid(g);
            g.restoreCanvas();
        } else if (this.type == 1) {
            this.paintPK(g);
        } else if (this.type == 5) {
            this.paintCharDie(g);
        }
        GameCanvas.resetTrans(g);
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
        g.drawRegion(GameScr.imgBoder[5], 0, 0, 12, 25, 2, this.xKhungAuto + this.wKhungAuto / 2 + 44, this.yKhungAuto - 14 + 1 + ys, mGraphics.VCENTER | mGraphics.RIGHT, false);
        FontTeam.fontTile.drawString(g, this.tile, this.xKhungAuto + this.wKhungAuto / 2, this.yKhungAuto - 20 + 1, 2, false);
        if (GameCanvas.isTouch && this.cmdClose != null) {
            this.cmdClose.paint(g);
            if (this.type == 2 && this.typeInfo == 1) {
                this.cmdchange.paint(g);
            }
        }
        g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 7, this.xKhungAuto + this.wKhungAuto + 1, this.yKhungAuto + 1, mGraphics.BOTTOM | mGraphics.RIGHT, false);
        g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 1, this.xKhungAuto, this.yKhungAuto + 1, mGraphics.BOTTOM | mGraphics.LEFT, false);
        g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 0, this.xKhungAuto, this.yKhungAuto - 28, mGraphics.TOP | mGraphics.LEFT, false);
        g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 2, this.xKhungAuto + this.wKhungAuto + 1, this.yKhungAuto - 28, mGraphics.TOP | mGraphics.RIGHT, false);
        if (this.type == 4 && this.idIcon != -1 && (img = GameData.getImgIcon(this.idIcon)) != null && img.img != null) {
            Item.eff_UpLv.paintUpgradeEffect(this.xKhungAuto + this.wKhungAuto / 2 + 1, this.yKhungAuto - 20 + 1 + 30 + 2, 15, 16, g, 1);
            g.drawImage(img.img, this.xKhungAuto + this.wKhungAuto / 2, this.yKhungAuto - 20 + 1 + 30 + 2, 3, false);
        }
    }

    public void paintCharDie(mGraphics g) {
        int i = 0;
        while (i < this.menuItem.size()) {
            mCommand cmd = (mCommand)this.menuItem.elementAt(i);
            if (cmd != null) {
                cmd.paintMenu2(g);
            }
            ++i;
        }
    }

    @Override
    public void paint(mGraphics g, int idAction, int x, int y, Object paint) {
    }

    public void paintPK(mGraphics g) {
        int j = 0;
        while (j < this.xpk.length) {
            int indexpk = j + 1;
            if (this.indexSelect != j) {
                g.drawRegion(GameScr.imgPK, 0, 12 * (indexpk * 3), 12, 12, 0, this.xpk[j], this.yKhungAuto + this.hKhungAuto / 2, 3, true);
            } else {
                g.drawRegion(GameScr.imgPK, 0, 12 * (indexpk * 3 + GameCanvas.gameTick / 3 % 3), 12, 12, 0, this.xpk[j], this.yKhungAuto + this.hKhungAuto / 2, 3, true);
            }
            ++j;
        }
        if (GameCanvas.isTouch) {
            this.cmdpk.paint(g);
        } else {
            if (this.cmdClose != null) {
                mFont.tahoma_7b_black.drawString(g, this.cmdClose.caption, 6, GameCanvas.h - 15 + 1, 0, false);
                FontTeam.fontTile.drawString(g, this.cmdClose.caption, 5, GameCanvas.h - 15, 0, false);
            }
            if (this.cmdpk != null) {
                mFont.tahoma_7b_black.drawString(g, this.cmdpk.caption, GameCanvas.w - 5 + 1, GameCanvas.h - 15 + 1, 1, false);
                FontTeam.fontTile.drawString(g, this.cmdpk.caption, GameCanvas.w - 5, GameCanvas.h - 15, 1, false);
            }
        }
    }

    @Override
    public void perform(int idAction, Object p) {
        if (this.type == 2 && this.typeInfo == 0 && idAction != 200) {
            GameService.gI().ListInfo(this.typeInfo, this.tile, (byte)idAction, (short)this.indexSelect);
            this.isShow = false;
            return;
        }
        if (this.type == 3 && idAction != 200) {
            this.performCmdParty(idAction);
            this.isShow = false;
            return;
        }
        switch (idAction) {
            case -1: {
                if (this.indexSelect == 0) {
                    GameService.gI().changePK((byte)-1);
                } else {
                    GameService.gI().changePK((byte)this.indexSelect);
                }
                this.isShow = false;
                break;
            }
            case 0: {
                if (this.type == 1 && this.indexSelect != -1) {
                    GameService.gI().changePK((byte)(this.indexSelect + 1));
                }
                this.isShow = false;
                break;
            }
            case 1: {
                GameService.gI().changePK((byte)-1);
                this.isShow = false;
                break;
            }
            case 2: {
                GameService.gI().ListInfo(this.typeInfo, this.tile, (byte)idAction, (short)this.indexSelect);
                this.isShow = false;
                break;
            }
            case 3: {
                GameService.gI().Dynamic_Menu((short)this.idNPC, (byte)this.idMenu, (byte)this.indexSelect);
                break;
            }
            case 4: {
                break;
            }
            case 5: {
                break;
            }
            case 6: {
                GameService.gI().RequestInfoClan((byte)this.indexSelect, 13);
                this.isShow = false;
                break;
            }
            case 100: {
                if (this.indexSelect != -1 && this.indexSelect < this.menuItem.size()) {
                    Char chardem = (Char)this.menuItem.elementAt(this.indexSelect);
                    GameCanvas.addChat(chardem.getName(), "", true);
                }
                this.isShow = false;
                break;
            }
            case 101: {
                if (this.indexSelect != -1 && this.indexSelect < this.menuItem.size()) {
                    mSystem.println("xem thong tin ne ");
                    Char chardem = (Char)this.menuItem.elementAt(this.indexSelect);
                    GameService.gI().requestSomeOneInfo(chardem.ID, (byte)0);
                }
                this.isShow = false;
                break;
            }
            case 102: {
                if (this.indexSelect != -1 && this.indexSelect < this.menuItem.size()) {
                    Char chardem = (Char)this.menuItem.elementAt(this.indexSelect);
                    GameService.gI().Friend((byte)3, chardem.getName());
                }
                this.isShow = false;
                break;
            }
            case 200: {
                if (this.type == 1 && this.indexSelect != -1) {
                    GameService.gI().changePK((byte)(this.indexSelect + 1));
                }
                this.isShow = false;
                break;
            }
            case 201: {
                String[] listProduct = mSystem.google_productIds;
                if (this.indexSelect < 0 || this.indexSelect > listProduct.length) {
                    return;
                }
                mSystem.makePurchase(listProduct[this.indexSelect]);
            }
        }
    }

    public void performCmdParty(int idAction) {
        switch (idAction) {
            case 0: {
                break;
            }
            case 1: {
                Info_Party info = (Info_Party)this.menuItem.elementAt(this.indexSelect);
                if (info == null) break;
                GameService.gI().doCreateParty((byte)3, (short)this.indexSelect, (short)-1, info.nameMember);
                mSystem.println("kich 1 thang ra khoi party " + info.nameMember + " " + this.indexSelect);
                break;
            }
            case 2: {
                GameService.gI().doCreateParty((byte)5, (short)-1, (short)-1, "");
                break;
            }
            case 3: {
                GameService.gI().doCreateParty((byte)4, (short)-1, (short)-1, "");
                break;
            }
            case 4: {
                GameCanvas.addChat(T.nhom1, "", true);
            }
        }
    }
}
