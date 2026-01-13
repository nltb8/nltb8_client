/*
 * Decompiled with CFR 0.152.
 */
package mchien.code.screen.screen;

import mchien.code.effect.new_skill.EffectEnd;
import mchien.code.main.GameCanvas;
import mchien.code.model.Effect;
import mchien.code.model.ImageIcon;
import mchien.code.model.Item;
import mchien.code.model.RunWord;
import mchien.code.model.T;
import mchien.code.model.mCommand;
import mchien.code.screen.Res;
import mchien.code.screen.screen.Dialog;
import mchien.code.screen.screen.FontTeam;
import mchien.code.screen.screen.GameData;
import mchien.code.screen.screen.GameScr;
import lib.Cout;
import lib.mGraphics;
import lib.mSound;
import lib.mSystem;
import lib.mVector;
import lib2.mFont;

public class MsgDlg
extends Dialog {
    public String[] info;
    public boolean isWait;
    public boolean isWaitDialog;
    int h;
    public int idNPCicon = -1;
    public int wShowEvent;
    public int hShowEvent;
    private int padLeft = 35;
    RunWord runtext;
    long timewait;
    public String npcName;
    public boolean isNPC;
    public int wShowPaper;
    public int maxWShow;
    public int vShow = 1;
    boolean Canpaint;
    public String tile;
    public mVector vecItemBox = new mVector();
    public boolean isBox = false;
    public int xbox;
    public int ybox;
    public int wbox;
    public int hbox;
    public int Count;
    public int delay;
    public int[] xitem;
    public mCommand cmdNhan;
    mVector Effbox = new mVector();

    @Override
    public void paint(mGraphics g, int idAction, int x, int y, Object paint) {
    }

    public MsgDlg() {
        if (GameCanvas.w <= 176) {
            this.padLeft = 10;
        }
        if (GameCanvas.w > 320) {
            this.padLeft = 80;
        }
    }

    public void pleasewait() {
        this.setInfo("Xin ch\u1edd", null, null, null);
        GameCanvas.currentDialog = this;
    }

    public void setShowNPC(int idIcon) {
        this.idNPCicon = idIcon;
        this.isNPC = true;
    }

    @Override
    public void show() {
        GameCanvas.currentDialog = this;
    }

    public void setInfo(String info, mCommand left, mCommand center, mCommand right) {
        this.isWaitDialog = false;
        this.isBox = false;
        this.left = left;
        this.center = center;
        this.right = right;
        this.Canpaint = false;
        this.maxWShow = FontTeam.arialFont.getWidth(info) + 20;
        if (this.maxWShow > GameCanvas.w - 20) {
            this.maxWShow = GameCanvas.w - 30;
        }
        if (this.maxWShow < GameCanvas.hw) {
            this.maxWShow = GameCanvas.hw + 20;
        }
        this.wShowPaper = 10;
        this.info = FontTeam.arialFont.splitFontArray(info, this.maxWShow - 40);
        this.h = 80;
        if (this.info.length >= 5) {
            this.h = this.info.length * FontTeam.arialFont.getHeight() + 20;
        }
        if (GameCanvas.isTouch) {
            if (left != null) {
                this.left.x = GameCanvas.w / 2 - 78 - 5;
                this.left.y = GameCanvas.h - 40;
            }
            if (right != null) {
                this.right.x = GameCanvas.w / 2 + 5;
                this.right.y = GameCanvas.h - 40;
            }
            if (center != null) {
                this.center.x = GameCanvas.w / 2 - 37;
                this.center.y = GameCanvas.h - 40;
            }
        }
        this.idNPCicon = -1;
        this.isNPC = false;
        this.isWait = false;
    }

    public void startWaitingDialog() {
        this.isBox = false;
        this.isWaitDialog = true;
        this.wShowEvent = 122;
        this.timewait = mSystem.currentTimeMillis() + 10000L;
    }

    public void setInfo(String name_NPC, String info, mCommand left, mCommand right, boolean isNPC, int idicon) {
        this.isWaitDialog = false;
        this.isBox = false;
        this.left = left;
        this.right = right;
        this.npcName = name_NPC;
        this.isNPC = isNPC;
        this.idNPCicon = idicon;
        this.center = null;
        this.Canpaint = false;
        this.maxWShow = FontTeam.arialFont.getWidth(info) + 20;
        if (this.maxWShow > GameCanvas.w - 20) {
            this.maxWShow = GameCanvas.w - 30;
        }
        if (this.maxWShow < GameCanvas.hw) {
            this.maxWShow = GameCanvas.hw + 20;
        }
        this.info = FontTeam.arialFont.splitFontArray(info, this.maxWShow - 40);
        this.wShowPaper = 10;
        this.h = 80;
        if (this.info.length >= 5) {
            this.h = this.info.length * FontTeam.arialFont.getHeight() + 20;
        }
        if (GameCanvas.isTouch) {
            if (left != null) {
                this.left.x = GameCanvas.w / 2 - 78 - 5;
                this.left.y = GameCanvas.h - 40;
            }
            if (right != null) {
                this.right.x = GameCanvas.w / 2 + 5;
                this.right.y = GameCanvas.h - 40;
            }
        }
        this.isWait = false;
        this.wShowPaper = this.maxWShow;
        this.runtext = new RunWord();
        this.runtext.startDialogChain(info, 0, GameCanvas.hw - this.wShowPaper / 2 + 10, GameCanvas.h - this.h - 6 * this.info.length - 10 + GameCanvas.hText - 3, this.maxWShow - 40, mFont.tahoma_7_black);
        Cout.Debug("RunWord 2 ");
    }

    public void setInfo(String info, mCommand left, mCommand right) {
        this.isWaitDialog = false;
        this.isBox = false;
        this.left = left;
        this.right = right;
        this.center = null;
        this.Canpaint = false;
        this.maxWShow = FontTeam.arialFont.getWidth(info) + 20;
        if (this.maxWShow > GameCanvas.w - 20) {
            this.maxWShow = GameCanvas.w - 30;
        }
        if (this.maxWShow < GameCanvas.w / 2) {
            this.maxWShow = GameCanvas.hw + 20;
        }
        this.info = FontTeam.arialFont.splitFontArray(info, this.maxWShow - 40);
        this.wShowPaper = 10;
        this.h = 80;
        if (this.info.length >= 5) {
            this.h = this.info.length * FontTeam.arialFont.getHeight() + 20;
        }
        if (GameCanvas.isTouch) {
            if (left != null) {
                this.left.x = GameCanvas.w / 2 - 78 - 5;
                this.left.y = GameCanvas.h - 40;
            }
            if (right != null) {
                this.right.x = GameCanvas.w / 2 + 5;
                this.right.y = GameCanvas.h - 40;
            }
        }
        this.isNPC = false;
        this.isWait = false;
    }

    public void setInfo(String info) {
        this.isWaitDialog = false;
        this.isBox = false;
        this.left = null;
        this.right = null;
        this.center = null;
        this.Canpaint = false;
        this.maxWShow = FontTeam.arialFont.getWidth(info) + 20;
        if (this.maxWShow > GameCanvas.w - 20) {
            this.maxWShow = GameCanvas.w - 30;
        }
        if (this.maxWShow < GameCanvas.w / 2) {
            this.maxWShow = GameCanvas.hw + 20;
        }
        this.info = FontTeam.arialFont.splitFontArray(info, this.maxWShow - 40);
        this.wShowPaper = 10;
        this.h = 80;
        if (this.info.length >= 5) {
            this.h = this.info.length * FontTeam.arialFont.getHeight() + 20;
        }
        if (GameCanvas.isTouch) {
            if (this.left != null) {
                this.left.x = GameCanvas.w / 2 - 78 - 5;
                this.left.y = GameCanvas.h - 40;
            }
            if (this.right != null) {
                this.right.x = GameCanvas.w / 2 + 5;
                this.right.y = GameCanvas.h - 40;
            }
        }
        this.isNPC = false;
        this.isWait = false;
    }

    public void setInfo(String info, mCommand center, int idnpc) {
        this.isWaitDialog = false;
        this.isBox = false;
        this.left = null;
        this.right = null;
        this.center = center;
        this.Canpaint = false;
        this.idNPCicon = idnpc;
        this.maxWShow = FontTeam.arialFont.getWidth(info) + 20;
        if (this.maxWShow > GameCanvas.w - 20) {
            this.maxWShow = GameCanvas.w - 30;
        }
        if (this.maxWShow < GameCanvas.hw) {
            this.maxWShow = GameCanvas.hw + 20;
        }
        this.info = FontTeam.arialFont.splitFontArray(info, this.maxWShow - 40);
        this.wShowPaper = 10;
        this.h = 80;
        if (this.info.length >= 5) {
            this.h = this.info.length * FontTeam.arialFont.getHeight() + 20;
        }
        if (GameCanvas.isTouch) {
            if (this.left != null) {
                this.left.x = GameCanvas.w / 2 - 78 - 5;
                this.left.y = GameCanvas.h - 40;
            }
            if (this.right != null) {
                this.right.x = GameCanvas.w / 2 + 5;
                this.right.y = GameCanvas.h - 40;
            }
        }
        this.isNPC = true;
        this.wShowPaper = this.maxWShow;
        this.runtext = new RunWord();
        this.runtext.startDialogChain(info, 0, GameCanvas.hw - this.wShowPaper / 2 + 10, GameCanvas.h - this.h - 6 * this.info.length - 10 + GameCanvas.hText, this.maxWShow - 40, mFont.tahoma_7_black);
        this.isWait = false;
        Cout.Debug("RunWord 1 ");
    }

    public void paintNPC(mGraphics g) {
        if (this.idNPCicon != -1) {
            ImageIcon img;
            if (GameCanvas.isTouch) {
                if (this.left != null) {
                    this.left.y = GameCanvas.h - 33;
                }
                if (this.right != null) {
                    this.right.y = GameCanvas.h - 33;
                }
                if (this.center != null) {
                    this.center.y = GameCanvas.h - 33;
                }
            }
            if ((img = GameData.getImgIcon((short)this.idNPCicon)) != null && img.img != null) {
                g.drawImage(img.img, GameCanvas.hw + this.wShowPaper / 2, GameCanvas.h - this.h - (this.isNPC ? 24 : 34), mGraphics.BOTTOM | mGraphics.RIGHT, false);
            }
            g.setClip(0, 0, GameCanvas.w, GameCanvas.h);
            int yDlg = GameCanvas.h - this.h - (this.isNPC ? 30 : 38);
            GameCanvas.paintz.paintPaper(g, GameCanvas.hw - this.wShowPaper / 2, yDlg, this.wShowPaper, this.h, this.maxWShow);
            if (this.runtext != null) {
                this.runtext.paintText(g, 0);
            }
        }
    }

    public void paintWaitDialog(mGraphics g) {
        GameCanvas.resetTrans(g);
        int x = GameCanvas.w - this.wShowEvent - 4;
        int yev = 39;
        int hevemt = 35;
        g.setColor(-11197952);
        g.fillRect(x - 2, yev - hevemt - 2, this.wShowEvent + 4, hevemt + 4, false);
        g.setColor(-3377408);
        g.fillRect(x - 1, yev - hevemt - 1, this.wShowEvent + 2, hevemt + 2, false);
        g.setClip(x, yev - hevemt, this.wShowEvent, hevemt);
        g.ClipRec(x, yev - hevemt, this.wShowEvent, hevemt);
        int i = 0;
        while (i < this.wShowEvent / 32 + 1) {
            int j = 0;
            while (j < hevemt / 32 + 1) {
                g.drawImage(GameScr.imgBgMainMenu, x + i * 32, yev - hevemt + j * 32 - 5, 0, true);
                ++j;
            }
            ++i;
        }
        g.restoreCanvas();
        GameCanvas.resetTrans(g);
        g.setColor(-14194607);
        g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 7, x + this.wShowEvent, yev, mGraphics.BOTTOM | mGraphics.RIGHT, false);
        g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 1, x, yev, mGraphics.BOTTOM | mGraphics.LEFT, false);
        g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 0, x, yev - hevemt, mGraphics.TOP | mGraphics.LEFT, false);
        g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 2, x + this.wShowEvent, yev - hevemt, mGraphics.TOP | mGraphics.RIGHT, false);
        mFont.tahoma_7_white.drawString(g, T.xincho, x + this.wShowEvent / 2, hevemt / 2 + (yev - hevemt - 2) / 2 - mFont.tahoma_7_white.getHeight() / 4, 2, false);
    }

    @Override
    public void paint(mGraphics g) {
        if (this.isWaitDialog) {
            this.paintWaitDialog(g);
            return;
        }
        GameCanvas.resetTrans(g);
        if (this.isBox) {
            this.paintBoxInfo(g);
            return;
        }
        if (this.isNPC) {
            this.paintNPC(g);
        } else {
            g.setClip(0, 0, GameCanvas.w, GameCanvas.h);
            int yDlg = GameCanvas.h - this.h - (this.isNPC ? 30 : 38);
            GameCanvas.paintz.paintPaper(g, GameCanvas.hw - this.wShowPaper / 2, yDlg, this.wShowPaper, this.h, this.maxWShow);
            int hFont = FontTeam.arialFont.getHeight();
            int leght = this.info.length;
            int yBegin = yDlg + this.h / 2 - leght * hFont / 2;
            if (this.isWait) {
                GameCanvas.paintShukiren(GameCanvas.hw, (yBegin += 8) - 12, g);
            }
            if (this.Canpaint) {
                int i = 0;
                while (i < this.info.length) {
                    FontTeam.arialFont.drawString(g, this.info[i], GameCanvas.hw, yBegin, 2, false);
                    yBegin += hFont;
                    ++i;
                }
            }
        }
        GameCanvas.resetTrans(g);
        super.paint(g);
    }

    public void paintBoxInfo(mGraphics g) {
        Res.paintDlgDragonFullNew(g, this.xbox, this.ybox, this.wbox, this.hbox, 60, 60, GameScr.imgBk[0], false);
        g.setColor(-9751532);
        g.fillRect(this.xbox, this.ybox - 28, this.wbox, 28, false);
        int i = 0;
        while (i < 3) {
            g.setColor(Res.nColor[i]);
            g.drawRect(this.xbox + i, this.ybox - 28 + i, this.wbox - i * 2, 28 - i * 2, false);
            ++i;
        }
        g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 7, this.xbox + this.wbox + 1, this.ybox + 1, mGraphics.BOTTOM | mGraphics.RIGHT, false);
        g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 1, this.xbox, this.ybox + 1, mGraphics.BOTTOM | mGraphics.LEFT, false);
        g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 0, this.xbox, this.ybox - 28, mGraphics.TOP | mGraphics.LEFT, false);
        g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 2, this.xbox + this.wbox + 1, this.ybox - 28, mGraphics.TOP | mGraphics.RIGHT, false);
        i = 0;
        while (i < 7) {
            g.drawRegion(GameScr.imgBoder[5], 0, 25, 12, 25, 0, this.xbox + this.wbox / 2 - 42 + i * 12, this.ybox - 14, mGraphics.VCENTER | mGraphics.LEFT, false);
            ++i;
        }
        g.drawRegion(GameScr.imgBoder[5], 0, 0, 12, 25, 0, this.xbox + this.wbox / 2 - 44, this.ybox - 14, mGraphics.VCENTER | mGraphics.LEFT, false);
        int ys = 0;
        if (!mSystem.isj2me) {
            ys = -1;
        }
        g.drawRegion(GameScr.imgBoder[5], 0, 0, 12, 25, 2, this.xbox + this.wbox / 2 + 44, this.ybox - 14 + 1 + ys, mGraphics.VCENTER | mGraphics.RIGHT, false);
        FontTeam.fontTile.drawString(g, this.tile, this.xbox + this.wbox / 2, this.ybox - 19, 2, false);
        int row = 0;
        if (this.vecItemBox.size() > 4) {
            row = 1;
        }
        int ypp = this.ybox + this.hbox / 2 - 12;
        if (row == 1) {
            ypp = this.ybox + this.hbox / 2 - 12 - 12;
        }
        int i2 = 0;
        while (i2 < this.vecItemBox.size()) {
            Item item = (Item)this.vecItemBox.elementAt(i2);
            if (item != null) {
                if (i2 < 4) {
                    item.paint(g, this.xitem[i2], ypp);
                } else {
                    item.paint(g, this.xitem[i2], ypp + 26);
                }
            }
            if (i2 < 4) {
                g.drawImage(GameScr.imgfocus[3], this.xitem[i2], ypp, 0, false);
            } else {
                g.drawImage(GameScr.imgfocus[3], this.xitem[i2], ypp + row * 26 + 3, 0, false);
            }
            ++i2;
        }
        if (!GameCanvas.isTouch) {
            super.paint(g);
        }
        if (this.cmdNhan != null && GameCanvas.isTouch) {
            this.cmdNhan.paint(g);
        }
        i2 = 0;
        while (i2 < this.Effbox.size()) {
            Effect eff = (Effect)this.Effbox.elementAt(i2);
            if (eff != null) {
                eff.paint(g);
            }
            ++i2;
        }
    }

    public void updatePaper() {
        if (this.wShowPaper < this.maxWShow) {
            this.wShowPaper += this.vShow;
            if (this.wShowPaper >= this.maxWShow) {
                this.wShowPaper = this.maxWShow;
                this.Canpaint = true;
                this.vShow = 20;
            }
            if (this.vShow <= 100) {
                this.vShow += 25;
                if (this.vShow > 100) {
                    this.vShow = 100;
                }
            }
        }
    }

    @Override
    public void update() {
        if (this.isWaitDialog) {
            if (this.timewait - mSystem.currentTimeMillis() <= 0L) {
                this.isWaitDialog = false;
                GameCanvas.endDlg();
            }
            return;
        }
        if (this.center != null && GameCanvas.isKeyPressed(5)) {
            this.center.performAction();
        }
        if (this.isBox) {
            if (GameCanvas.isKeyPressed(5)) {
                this.cmdNhan.performAction();
            }
            if (this.Count < this.vecItemBox.size()) {
                if (this.delay >= 0) {
                    --this.delay;
                }
                if (this.delay <= 0) {
                    EffectEnd eff = new EffectEnd(0, this.xitem[this.Count] + 5, this.ybox + this.hbox / 2 - 17 + 5);
                    this.Effbox.addElement(eff);
                    ++this.Count;
                    this.delay = 2;
                }
            }
            if (this.cmdNhan != null && GameCanvas.isTouch && this.getCmdPointerLast(this.cmdNhan)) {
                GameCanvas.isPointerJustRelease[0] = false;
                this.cmdNhan.performAction();
            }
            int i = 0;
            while (i < this.Effbox.size()) {
                Effect eff = (Effect)this.Effbox.elementAt(i);
                if (eff != null) {
                    eff.update();
                    if (eff.wantDestroy) {
                        this.Effbox.removeElement(eff);
                    }
                }
                ++i;
            }
            if (!GameCanvas.isTouch) {
                super.update();
            }
            return;
        }
        if (this.left != null && GameCanvas.isKeyPressed(5)) {
            this.left.performAction();
        }
        this.updatePaper();
        super.update();
        if (this.isNPC) {
            if (this.runtext != null) {
                this.runtext.updateDlg();
            }
            if (GameCanvas.isTouch) {
                if (this.left != null) {
                    this.left.y = GameCanvas.h - 35;
                }
                if (this.right != null) {
                    this.right.y = GameCanvas.h - 35;
                }
                if (this.center != null) {
                    this.center.y = GameCanvas.h - 35;
                }
            }
        }
    }

    public void setInfoBox(String tile, mVector vecitem) {
        mSound.playSound(27, mSound.volumeSound);
        this.isWaitDialog = false;
        this.isBox = true;
        this.tile = tile;
        this.vecItemBox = vecitem;
        this.wbox = 162;
        this.hbox = 81;
        this.xbox = GameCanvas.w / 2 - this.wbox / 2;
        this.ybox = GameCanvas.h / 2 - this.hbox / 2;
        this.xitem = new int[vecitem.size()];
        int xs = this.xitem.length;
        if (xs > 4) {
            xs = 4;
        }
        int i = 0;
        while (i < this.xitem.length) {
            this.xitem[i] = this.xbox + this.wbox / 2 - xs * 14 + i % 4 * 30 + 1 - 3;
            ++i;
        }
        this.cmdNhan = new mCommand(T.nhan, this, 0);
        if (!GameCanvas.isTouch) {
            this.center = this.cmdNhan;
        }
        if (GameCanvas.isTouch) {
            this.cmdNhan.setXY(this.xbox + this.wbox / 2 - 40, this.ybox + this.hbox + 3);
        }
        this.Count = 0;
    }

    public void setinfo(String info, mVector cmd) {
        if (cmd.size() == 1) {
            this.center = (mCommand)cmd.elementAt(0);
        } else if (cmd.size() == 2) {
            this.left = (mCommand)cmd.elementAt(0);
            this.right = (mCommand)cmd.elementAt(1);
        } else if (cmd.size() == 3) {
            this.left = (mCommand)cmd.elementAt(0);
            this.center = (mCommand)cmd.elementAt(1);
            this.right = (mCommand)cmd.elementAt(2);
        }
        this.Canpaint = false;
        this.maxWShow = FontTeam.arialFont.getWidth(info) + 20;
        if (this.maxWShow > GameCanvas.w - 20) {
            this.maxWShow = GameCanvas.w - 30;
        }
        if (this.maxWShow < GameCanvas.hw) {
            this.maxWShow = GameCanvas.hw + 20;
        }
        this.wShowPaper = 10;
        this.info = FontTeam.arialFont.splitFontArray(info, this.maxWShow - 40);
        this.h = 80;
        if (this.info.length >= 5) {
            this.h = this.info.length * FontTeam.arialFont.getHeight() + 20;
        }
        if (GameCanvas.isTouch) {
            if (this.left != null) {
                this.left.x = GameCanvas.w / 2 - 78 - 5;
                this.left.y = GameCanvas.h - 40;
            }
            if (this.right != null) {
                this.right.x = GameCanvas.w / 2 + 5;
                this.right.y = GameCanvas.h - 40;
            }
            if (this.center != null) {
                this.center.x = GameCanvas.w / 2 - 37;
                this.center.y = GameCanvas.h - 40;
            }
        }
        this.idNPCicon = -1;
        this.isNPC = false;
        this.isWait = false;
    }

    @Override
    public void perform(int idAction, Object p) {
        switch (idAction) {
            case 0: {
                this.isBox = false;
                this.vecItemBox.removeAllElements();
                this.isWaitDialog = false;
                GameCanvas.endDlg();
                break;
            }
        }
    }
}
