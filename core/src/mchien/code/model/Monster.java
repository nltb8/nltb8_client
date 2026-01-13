/*
 * Decompiled with CFR 0.152.
 */
package mchien.code.model;

import mchien.code.effect.EffectSkillMonster;
import mchien.code.effect.new_skill.EffectSkill;
import mchien.code.main.GameCanvas;
import mchien.code.model.Actor;
import mchien.code.model.DataEffect;
import mchien.code.model.EffectManager;
import mchien.code.model.LiveActor;
import mchien.code.model.MonsterInfo;
import mchien.code.model.MonsterTemplate;
import mchien.code.model.T;
import mchien.code.network.GameService;
import mchien.code.screen.Res;
import mchien.code.screen.Util;
import mchien.code.screen.Utils;
import mchien.code.screen.screen.GameScr;
import lib.Tilemap;
import lib.mGraphics;
import lib.mRandom;
import lib.mSystem;
import lib.mVector;
import lib2.mFont;

public class Monster
extends LiveActor {
    public int idChar = -1;
    public short range = (short)40;
    public short xTo;
    public short yTo;
    public short p1;
    public short p2;
    public short p3;
    public short monster_type;
    public short mtoX;
    public short xTarget;
    public short yTarget;
    public short mtoY;
    public static final byte STAND = 0;
    public static final byte DEAD = 1;
    public static final byte WALK = 2;
    public static final byte ATTACK = 3;
    public static final byte INJURE = 4;
    public static final byte DEADFLY = 5;
    public static final byte MOVE_TO_TARGET = 6;
    public static final byte GO_TO_XYFIRST = 7;
    public static final byte WAIT = 8;
    public static final byte MOVE_AROUND_TARGET = 9;
    public static final byte FLASH_TO_TARGET = 11;
    public static final byte RETURN = 12;
    public short saveXfirst;
    public short saveYfirst;
    public mVector ntarget = new mVector();
    public static final byte MOVE_AND_FIRE = 0;
    public static final byte FLASH = 1;
    public static final byte FIRE_ARROW = 2;
    public static final byte FIRE_MAGIC = 3;
    public byte typeattack;
    public byte typeDie;
    public short idEffect;
    int timeWait;
    int timeLimit;
    short lastDir = (short)-100;
    public static final byte TYPE0 = 4;
    private int timeStand;
    byte vx;
    byte vy;
    byte idShadow;
    byte countAttack;
    public short savex;
    public short savey;
    public int tickDie;
    public int f;
    public byte Typepaint;
    private long timeDelay;
    public boolean isMineral;
    public boolean isSetInfo;
    short idTemplate;
    public short timepaint = (short)10;
    public short saveXvang;
    public short saveYvang;
    public short timefly;
    public static mRandom r = new mRandom(mSystem.currentTimeMillis());
    public boolean isbossOffline = false;
    short dx;
    short dy;
    public int[] mDame;
    public String oldname;
    byte huongY = 0;
    byte flip = 0;
    byte frame;
    private boolean isFreeze;
    private boolean canrevives = true;
    public Actor target;
    byte idSkill = 0;
    short[] angle;
    boolean isv;
    public static final byte COLOR_MONSTER_WHITE = 0;
    public static final byte COLOR_MONSTER_GREEN = 1;
    public static final byte COLOR_MONSTER_OGRANGE = 2;
    public static final byte COLOR_MONSTER_YELLOW = 3;
    public static final byte COLOR_MONSTER_BLUE = 4;
    public short dyPaintPk;
    byte changSpeed;
    boolean getMonster_Template;
    long timeBeginDie;
    long timeRevive;
    public long timeGetInfo;
    long timeCreate;
    byte[] sp;
    public short timevang;
    public short ang;
    int vxx;
    int vyy;
    public short[] rx;
    public short[] ry;
    byte currentFrame;
    byte rangestop;
    byte cout;
    boolean isStand;
    private int xAround;
    private int yAround;
    private int tickmove;
    private int[] dmove;
    byte totalWay;
    private String nameTieu;
    int vxDie;
    int vyDie;
    int vyStyleDie;
    int vyStyleStand;
    public int frameDie;
    public int timeBienmat;
    private byte countDie;
    public boolean canpaint;

    public Monster() {
        short[] sArray = new short[7];
        sArray[1] = 45;
        sArray[2] = 90;
        sArray[3] = 135;
        sArray[4] = 180;
        sArray[5] = 225;
        sArray[6] = 270;
        this.angle = sArray;
        this.isv = false;
        this.dyPaintPk = 0;
        this.getMonster_Template = false;
        this.timeGetInfo = mSystem.currentTimeMillis();
        byte[] byArray = new byte[5];
        byArray[0] = -2;
        byArray[1] = -1;
        byArray[3] = 1;
        byArray[4] = 2;
        this.sp = byArray;
        this.timevang = 0;
        this.rx = new short[]{-16, 16, 8, -8, 12, -12, 20, -20};
        this.ry = new short[]{-16, 16, 8, -8, 12, -12, 20, -20};
        this.currentFrame = 0;
        this.rangestop = (byte)25;
        this.tickmove = 20;
        this.dmove = new int[]{5, -5, 16, -16};
        this.totalWay = (byte)2;
        this.nameTieu = "";
    }

    public Monster(int idTemplate) {
        short[] sArray = new short[7];
        sArray[1] = 45;
        sArray[2] = 90;
        sArray[3] = 135;
        sArray[4] = 180;
        sArray[5] = 225;
        sArray[6] = 270;
        this.angle = sArray;
        this.isv = false;
        this.dyPaintPk = 0;
        this.getMonster_Template = false;
        this.timeGetInfo = mSystem.currentTimeMillis();
        byte[] byArray = new byte[5];
        byArray[0] = -2;
        byArray[1] = -1;
        byArray[3] = 1;
        byArray[4] = 2;
        this.sp = byArray;
        this.timevang = 0;
        this.rx = new short[]{-16, 16, 8, -8, 12, -12, 20, -20};
        this.ry = new short[]{-16, 16, 8, -8, 12, -12, 20, -20};
        this.currentFrame = 0;
        this.rangestop = (byte)25;
        this.tickmove = 20;
        this.dmove = new int[]{5, -5, 16, -16};
        this.totalWay = (byte)2;
        this.nameTieu = "";
        this.catagory = 1;
        this.idTemplate = (short)idTemplate;
        this._isDie = false;
        this.dir = (byte)r.nextInt(4);
        this.timeLimit = Res.random(10, 20);
        this.state = 0;
        this.timeWait = 0;
        this.p3 = 0;
        this.p2 = 0;
        this.p1 = 0;
        MonsterTemplate mons = (MonsterTemplate)MonsterTemplate.ALL_MONSTER_TEMPLATE.get("" + idTemplate);
        this.oldname = this.name = mons.name;
        this.hp = 100;
        this.canmove = true;
        this.canpaint = true;
        this.speed = mons.speed;
        if (!Tilemap.isMapIntro()) {
            this.typeattack = mons.typeMove;
            this.idEffect = mons.idEff;
            this.idShadow = mons.idShadow;
        } else {
            this.setSkillFire(mons.idImg);
            this.idShadow = 0;
        }
        if (this.typeattack == 5 || this.typeattack == 4) {
            this.canmove = false;
            this.speed = 0;
        }
    }

    @Override
    public int getTypeMove() {
        return this.typeattack;
    }

    private void setSkillFire(int idTem) {
        switch (idTem) {
            case 0: {
                this.typeattack = (byte)2;
                this.idEffect = (short)26;
                break;
            }
            case 3: {
                this.typeattack = (byte)2;
                this.idEffect = (short)7;
                break;
            }
            case 6: {
                this.typeattack = (byte)2;
                this.idEffect = (short)27;
                break;
            }
            case 7: {
                this.typeattack = (byte)2;
                this.idEffect = (short)25;
                break;
            }
            case 18: {
                this.typeattack = (byte)2;
                this.idEffect = (short)2;
                break;
            }
            case 20: {
                this.typeattack = (byte)2;
                this.idEffect = (short)4;
                break;
            }
            case 23: {
                this.typeattack = (byte)2;
                this.idEffect = (short)25;
                break;
            }
            case 24: {
                this.typeattack = (byte)2;
                this.idEffect = (short)21;
                break;
            }
            case 25: {
                this.typeattack = (byte)2;
                this.idEffect = 0;
                break;
            }
            case 27: {
                this.typeattack = (byte)2;
                this.idEffect = (short)27;
                break;
            }
            case 28: {
                this.typeattack = (byte)2;
                this.idEffect = (short)5;
                break;
            }
            case 29: {
                this.typeattack = (byte)2;
                this.idEffect = (short)17;
                break;
            }
            case 30: {
                this.typeattack = (byte)2;
                this.idEffect = (short)9;
                break;
            }
            case 32: {
                this.typeattack = (byte)2;
                this.idEffect = (short)7;
                break;
            }
            case 36: {
                this.typeattack = (byte)2;
                this.idEffect = (short)24;
                break;
            }
            case 38: {
                this.typeattack = (byte)2;
                this.idEffect = (short)20;
                break;
            }
            case 42: {
                this.typeattack = (byte)2;
                this.idEffect = (short)11;
                break;
            }
            case 45: {
                this.typeattack = (byte)2;
                this.idEffect = 1;
                break;
            }
            case 77: {
                this.typeattack = (byte)2;
                this.idEffect = (short)31;
                break;
            }
            case 72: {
                this.typeattack = (byte)2;
                this.idEffect = (short)-1;
                break;
            }
            case 1: 
            case 11: 
            case 15: 
            case 16: 
            case 33: {
                this.typeattack = 0;
                break;
            }
            case 2: 
            case 4: 
            case 5: 
            case 8: 
            case 9: 
            case 10: 
            case 12: 
            case 13: 
            case 14: 
            case 17: 
            case 19: 
            case 21: 
            case 22: 
            case 26: 
            case 31: 
            case 34: 
            case 35: 
            case 37: 
            case 39: 
            case 40: 
            case 41: 
            case 43: 
            case 44: {
                this.typeattack = 1;
                break;
            }
            default: {
                this.typeattack = (byte)2;
                this.idEffect = (short)26;
            }
        }
    }

    @Override
    public void setMove2Target(Actor target) {
        this.target = target;
        this.state = (byte)4;
        this.p1 = 0;
        this.timeStand = 0;
        if (!this.isbossOffline && this.timeDelay - mSystem.currentTimeMillis() < 0L) {
            this.timeDelay = mSystem.currentTimeMillis() + 1500L;
        }
    }

    @Override
    public void DropHP(int hp) {
        this.hp -= hp;
    }

    @Override
    public void setRealHP(int realHP) {
        this.hp = this.realHP = realHP;
        this.realHPSyncTime = 20;
    }

    public void startAttack() {
        if (this.state == 12) {
            return;
        }
        if (this.typeattack == 1) {
            this.state = (byte)11;
            this.savex = this.x;
            this.savey = this.y;
        } else if (this.typeattack == 0) {
            this.state = (byte)6;
            this.xTarget = (short)(this.target.x + (Res.random(10) < 5 ? 16 : -16));
            this.yTarget = (short)(this.target.y + (Res.random(10) < 5 ? 16 : -16));
        } else {
            this.state = (byte)6;
            this.xTarget = (short)(this.target.x + (Res.random(10) < 5 ? 40 : -40));
            this.yTarget = (short)(this.target.y + (Res.random(10) < 5 ? 40 : -40));
        }
        if (this.timeLive == -1) {
            this.state = (byte)3;
        }
        this.p3 = 0;
        this.p2 = 0;
        this.p1 = 0;
        this.countAttack = 0;
        this.countAttack = 0;
    }

    public void startAttack(int idSkill) {
        this.idSkill = (byte)idSkill;
        this.canpaint = true;
        if (this.state == 12) {
            return;
        }
        this.currentFrame = 0;
        if (this.timeLive > 0 || this.timeLive == -2) {
            if (this.typeattack == 1) {
                this.savex = this.x;
                this.savey = this.y;
                this.state = (byte)11;
            } else if (this.typeattack == 0) {
                this.state = (byte)6;
                this.xTarget = (short)(this.target.x + (Res.random(10) < 5 ? 16 : -16));
                this.yTarget = (short)(this.target.y + (Res.random(10) < 5 ? 16 : -16));
            } else {
                this.state = (byte)3;
            }
        }
        if (this.timeLive == -1) {
            this.state = (byte)3;
            this.idEffect = (short)idSkill;
        }
        this.p3 = 0;
        this.p2 = 0;
        this.p1 = 0;
        this.countAttack = 0;
    }

    public short getIDSkillBoss() {
        return this.idSkill;
    }

    @Override
    public void vang() {
        if (!Tilemap.tileTypeAtPixel(this.x - this.vxx, this.y - this.vyy, 2) && !this.isv) {
            this.x = (short)(this.x - this.vxx);
            this.y = (short)(this.y - this.vyy);
            --this.vxx;
            --this.vyy;
        } else {
            this.x = (short)(this.x + this.vxx / 2);
            this.y = (short)(this.y + this.vyy / 2);
            this.isv = true;
        }
        if (this.target != null && Utils.getDistance(this.x, this.y, this.target.x, this.target.y) > 36) {
            this.vxx = 0;
            this.vyy = 0;
            this.timevang = 0;
        }
    }

    public boolean isFly() {
        DataEffect deff;
        mVector data = new mVector();
        MonsterTemplate mons1 = (MonsterTemplate)MonsterTemplate.ALL_MONSTER_TEMPLATE.get("" + this.idTemplate);
        if (mons1 != null) {
            data = mons1.getDataMonster();
        }
        return (deff = (DataEffect)data.elementAt(0)) != null && deff.isFly <= -5;
    }

    @Override
    public int getStartPointPaintFly() {
        DataEffect deff;
        mVector data = new mVector();
        MonsterTemplate mons1 = (MonsterTemplate)MonsterTemplate.ALL_MONSTER_TEMPLATE.get("" + this.idTemplate);
        if (mons1 != null) {
            data = mons1.getDataMonster();
        }
        if ((deff = (DataEffect)data.elementAt(0)) != null && deff.isFly <= -5) {
            return deff.isFly;
        }
        return 0;
    }

    @Override
    public void paint(mGraphics g) {
        if (this.state == 8 && this.tickDie <= 0) {
            return;
        }
        if (!this.canpaint) {
            return;
        }
        mVector data = new mVector();
        MonsterTemplate mons1 = (MonsterTemplate)MonsterTemplate.ALL_MONSTER_TEMPLATE.get("" + this.idTemplate);
        if (mons1 != null) {
            data = mons1.getDataMonster();
        }
        if (data.size() == 0) {
            return;
        }
        try {
            this.paintIconPK(g);
            DataEffect deff = (DataEffect)data.elementAt(0);
            byte idShadow = 0;
            if (deff != null) {
                idShadow = deff.idShadow;
                if (GameCanvas.gameScr.focusedActor != null && this.equals(GameCanvas.gameScr.focusedActor)) {
                    g.drawRegion(GameScr.imgtinhanh, 0, this.f * 14, 36, 14, 0, this.x, this.y + 7, 3, false);
                }
                this.paintBottomDataEff(g, this.getStartPointPaintFly());
                MonsterTemplate mons = (MonsterTemplate)MonsterTemplate.ALL_MONSTER_TEMPLATE.get("" + this.idTemplate);
                if (mons != null) {
                    if (this.width == 0 || this.height == 0) {
                        this.width = deff.getWith();
                        this.height = deff.getHeight();
                    }
                    byte status = this.state;
                    if (this.state == 9 && this.isStand) {
                        status = 0;
                    }
                    if (idShadow > -1) {
                        byte[] dxdyshadow = deff.getDxDyShadow(deff.getFrame(this.frame, status, this.huongY));
                        if (status == 3 && mons.fAttack != null && mons.fAttack.length > 0) {
                            dxdyshadow = deff.getDxDyShadow(this.frame);
                        }
                        if (this.isFly()) {
                            if (this.canPaint()) {
                                int xsd = this.x + this.vangx + dxdyshadow[0];
                                int ysd = this.y - this._jum + this.vangy + this.vyStyleDie + dxdyshadow[1];
                                g.drawImage(GameScr.imgShadow[idShadow], xsd, ysd, 0, false);
                            }
                        } else if (!this.isWater && this.canPaint()) {
                            int xsd = this.x + this.vangx + dxdyshadow[0];
                            int ysd = this.y - this._jum + this.vangy + this.vyStyleDie + dxdyshadow[1];
                            g.drawImage(GameScr.imgShadow[idShadow], xsd, ysd, 0, false);
                        }
                    }
                    if (this.canPaint()) {
                        if (status == 3 && mons.fAttack != null && mons.fAttack.length > 0) {
                            deff.paint(g, this.frame, this.x + this.vangx, this.y - this._jum + this.vangy - this.yFly + this.vyStyleDie, this.flip, mons.getImage(0));
                        } else {
                            deff.paint(g, deff.getFrame(this.frame, status, this.huongY), this.x + this.vangx, this.y - this._jum + this.vangy - this.yFly + this.vyStyleDie, this.flip, mons.getImage(0));
                        }
                    }
                }
            }
            this.paintTopDataEff(g, this.getStartPointPaintFly());
            if (this.isWater && !this.isFly()) {
                int ystart = this.state == 0 || this.dir == 1 || this.dir == 0 ? 0 : 40;
                short xs = 0;
                g.drawRegion(GameScr.imgWater, 0, ystart + GameCanvas.gameTick / 2 % 2 * 20, 44, 20, 0, this.x + xs, this.y, 3, false);
            }
            super.paint(g);
        }
        catch (Exception e) {
            mSystem.println(" loi ne monster: " + this.ID + " > " + this.idTemplate + " >> " + e.toString());
        }
    }

    public void paintIconPK(mGraphics g) {
        if (!this.nameTieu.equals("")) {
            mFont.tahoma_7_black.drawString(g, this.nameTieu, this.x + 1, this.y + 15 - (this.getHeight() + 15) - this.getYfly() + this.getDyWater() + this.dyPaintPk, 2, false);
            mFont.tahoma_7_yellow.drawString(g, this.nameTieu, this.x, this.y + 15 - (this.getHeight() + 15) - this.getYfly() + this.getDyWater() + this.dyPaintPk, 2, false);
        }
        if (this.typePK >= 0 && this.typePK <= 6) {
            g.drawRegion(GameScr.imgPK, 0, 12 * (this.typePK * 3 + GameCanvas.gameTick / 3 % 3), 12, 12, 0, this.x, this.y + 30 - (this.getHeight() + 15) - this.getYfly() + this.getDyWater() + this.dyPaintPk, 3, false);
        }
    }

    public void setType(short type) {
        this.monster_type = type;
        if (Res.monsterTemplates != null && Res.monsterTemplates[type] == null) {
            Res.monsterTemplates[type] = new MonsterTemplate();
            GameService.gI().sendGetMonsterTEmplate(0, this.monster_type, "");
        }
        this.timeCreate = System.currentTimeMillis() + 10000L;
    }

    @Override
    public void settimevang(short tv) {
        this.timevang = tv;
    }

    @Override
    public void startAttack(mVector target, int idskill) {
    }

    @Override
    public void actorDie() {
        this.hp = 0;
        this.removeAllEff();
        this.state = 1;
    }

    @Override
    public void comehome() {
        this.xAround = 0;
        this.yAround = 0;
        this.target = null;
        if (this.state == 8 || this.state == 1 || this.state == 5) {
            return;
        }
        if (Utils.getDistance(this.x, this.y, this.xFirst, this.yFirst) <= 50) {
            this.state = (byte)7;
        } else {
            EffectSkill.createHiEfect(this.x, this.y, 81);
            this.x = this.xFirst;
            this.y = this.yFirst;
            this.state = 0;
            EffectSkill.createHiEfect(this.x, this.y, 81);
        }
    }

    public boolean canMove2NextPos(int xl, int xr, int yu, int yd, int dir, Actor target) {
        if (target != null) {
            Actor ac = target;
            if (dir == 2 ? Res.inRangeActor(ac.x - 16, ac.y - 16, xl, yu, 32) || Res.inRangeActor(ac.x - 16, ac.y - 16, xl, yd, 32) : (dir == 3 ? Res.inRangeActor(ac.x - 16, ac.y - 16, xr, yu, 32) || Res.inRangeActor(ac.x - 16, ac.y - 16, xr, yd, 32) : (dir == 1 ? Res.inRangeActor(ac.x - 16, ac.y - 16, xl, yu, 32) || Res.inRangeActor(ac.x - 16, ac.y - 16, xr, yu, 32) : dir == 0 && (Res.inRangeActor(ac.x - 16, ac.y - 16, xl, yd, 32) || Res.inRangeActor(ac.x - 16, ac.y - 16, xr, yd, 32))))) {
                return false;
            }
        }
        int i = 0;
        while (i < GameCanvas.gameScr.actors.size()) {
            Actor ac = (Actor)GameCanvas.gameScr.actors.elementAt(i);
            if (ac != null && ac.catagory == 0 && (dir == 2 ? Res.inRangeActor(ac.x - 16, ac.y - 16, xl, yu, 32) || Res.inRangeActor(ac.x - 16, ac.y - 16, xl, yd, 32) : (dir == 3 ? Res.inRangeActor(ac.x - 16, ac.y - 16, xr, yu, 32) || Res.inRangeActor(ac.x - 16, ac.y - 16, xr, yd, 32) : (dir == 1 ? Res.inRangeActor(ac.x - 16, ac.y - 16, xl, yu, 32) || Res.inRangeActor(ac.x - 16, ac.y - 16, xr, yu, 32) : dir == 0 && (Res.inRangeActor(ac.x - 16, ac.y - 16, xl, yd, 32) || Res.inRangeActor(ac.x - 16, ac.y - 16, xr, yd, 32)))))) {
                return false;
            }
            ++i;
        }
        return true;
    }

    public int[] getXYMoveTo(int xto, int yto, int dir, int speed) {
        int xx = 0;
        int yy = 0;
        if (dir == 1) {
            yy = this.y;
            int i = this.y;
            while (i > yto) {
                if (Tilemap.tileTypeAtPixel(xto, yy - speed, 2)) {
                    return new int[]{xto, yy};
                }
                yy -= speed;
                i -= speed;
            }
            if (yy < yto) {
                yy = yto;
            }
            return new int[]{this.x, yy};
        }
        if (dir == 0) {
            yy = this.y;
            int i = this.y;
            while (i < yto) {
                if (Tilemap.tileTypeAtPixel(xto, yy + speed, 2)) {
                    return new int[]{xto, yy};
                }
                yy += speed;
                i += speed;
            }
            if (yy > yto) {
                yy = yto;
            }
            return new int[]{this.x, yy};
        }
        if (dir == 2) {
            xx = this.x;
            int i = this.x;
            while (i > xto) {
                if (Tilemap.tileTypeAtPixel(xx - speed, yto, 2)) {
                    return new int[]{xx, yto};
                }
                xx -= speed;
                i -= speed;
            }
            if (xx < xto) {
                xx = xto;
            }
            return new int[]{xx, this.y};
        }
        if (dir == 3) {
            xx = this.x;
            int i = this.x;
            while (i < xto) {
                if (Tilemap.tileTypeAtPixel(xx + speed, yto, 2)) {
                    return new int[]{xx, yto};
                }
                xx += speed;
                i += speed;
            }
            if (xx > xto) {
                xx = xto;
            }
            return new int[]{xx, this.y};
        }
        return new int[]{xto, yto};
    }

    public void UpdateAttack() {
        MonsterTemplate mons = this.getMonsterTemplate();
        if (mons != null && mons.fAttack != null && mons.fAttack.length > 0) {
            byte[] tem = mons.fAttack[this.idSkill][this.huongY];
            this.frame = tem[this.currentFrame];
            this.currentFrame = (byte)(this.currentFrame + 1);
            if (this.currentFrame > tem.length - 1) {
                this.currentFrame = 0;
                this.frame = 0;
                this.state = 0;
            }
            return;
        }
        this.p1 = (short)(this.p1 + 1);
        if (this.countAttack > 2) {
            this.state = 0;
            return;
        }
        if (this.timeLive < 0 && this.timeLive != -2) {
            if (this.p1 >= 6) {
                EffectSkillMonster skill = new EffectSkillMonster(this.idEffect, this, this.ntarget, this.mDame);
                EffectManager.addHiEffect(skill);
                this.state = 0;
            }
            if (this.p1 > 6) {
                this.p1 = 0;
            }
        } else {
            int damef;
            if (this.p1 > 6) {
                if ((this.typeattack == 2 || this.typeattack == 3) && this.countAttack == 0) {
                    if (this.ntarget.size() > 0) {
                        int i = 0;
                        while (i < this.ntarget.size()) {
                            Actor ac = (Actor)this.ntarget.elementAt(i);
                            if (ac != null) {
                                int damef2 = 0;
                                damef2 = GameScr.isIntro ? Res.random(200, 250) : this.mDame[i];
                                EffectSkillMonster skill = new EffectSkillMonster(this.typeattack, this.idEffect, this, ac, damef2);
                                EffectManager.addHiEffect(skill);
                                if (i == 0) {
                                    this.target = ac;
                                }
                            }
                            ++i;
                        }
                    } else {
                        damef = 0;
                        damef = GameScr.isIntro ? 200 : this.mDame[0];
                        EffectSkillMonster skill = new EffectSkillMonster(this.typeattack, this.idEffect, this, this.target, damef);
                        if (this.idEffect == 21) {
                            EffectManager.addLowEffect(skill);
                        } else {
                            EffectManager.addHiEffect(skill);
                        }
                    }
                } else if (this.typeattack == 1) {
                    this.state = 0;
                }
                if (this.timeLive == -1) {
                    this.state = 0;
                }
                this.p1 = 0;
                this.countAttack = (byte)(this.countAttack + 1);
            }
            if (this.typeattack == 1 && this.p1 > 5 && this.countAttack == 0) {
                damef = 0;
                damef = GameScr.isIntro ? 200 : this.mDame[0];
                GameCanvas.gameScr.startFlyText("-" + damef, 2, this.target.x + 0, this.target.y - 35, -1, -2);
                if (this.target != null) {
                    this.target.jum();
                }
                EffectSkill.createHiEfect(this.target.x, this.target.y - 35, 30);
            }
            if (this.typeattack == 0 && this.p1 > 5) {
                damef = 0;
                damef = GameScr.isIntro ? 200 : this.mDame[0];
                if (this.countAttack == 0) {
                    GameCanvas.gameScr.startFlyText("-" + damef, 2, this.target.x + 0, this.target.y - 15, -1, -2);
                    this.target.jum();
                    EffectSkill.createHiEfect(this.target.x, this.target.y - 20, 30);
                }
                if (this.state != 0 && this.countAttack > 2) {
                    this.state = 0;
                }
                this.p1 = 0;
                this.countAttack = (byte)(this.countAttack + 1);
            }
        }
    }

    public void doChangeFrameBoss() {
        mVector data = new mVector();
        MonsterTemplate mons1 = (MonsterTemplate)MonsterTemplate.ALL_MONSTER_TEMPLATE.get("" + this.idTemplate);
        if (mons1 != null) {
            data = mons1.getDataMonster();
        }
        DataEffect deff = null;
        if (data.size() > 0) {
            deff = (DataEffect)data.elementAt(0);
            byte status = this.state;
            if (this.isStand) {
                status = 0;
            }
            this.frame = (byte)((this.frame + 1) % deff.getAnim((int)status, (int)this.huongY).frame.length);
        }
    }

    @Override
    public void update() {
        if (this.state != 5 && this.state != 8 && this.state != 1 && !this.canpaint) {
            this.canpaint = true;
        }
        if (GameCanvas.gameTick % 2 == 0) {
            this.f = (this.f + 1) % 3;
        }
        try {
            if (this.tickDie >= 0) {
                --this.tickDie;
            }
            if (this._jum >= 0) {
                this._jum = (short)(this._jum - 5);
            }
            if (this.timevang >= 0) {
                this.vang();
                this.timevang = (short)(this.timevang - 1);
            } else {
                this.isv = false;
            }
            if (!this.canmove && this.yFly <= 0) {
                ++this.timeStand;
            }
            if (GameScr.isIntro && Utils.getDistance(this.saveXfirst, this.saveYfirst, this.x, this.y) > 160 && this.state != 7 && !this.isbossOffline) {
                this.target = null;
                this.state = (byte)7;
            }
            if (this.timeStand > 15 && this.typeattack != 4 && this.typeattack != 5 && this.timeLive != -1) {
                this.timeStand = 0;
                this.canmove = true;
            }
            this.doChangeFrameBoss();
            if (!(this.isSetInfo && this.hp <= this.maxhp || mSystem.currentTimeMillis() - this.timeGetInfo <= 15000L)) {
                this.timeGetInfo = mSystem.currentTimeMillis();
                GameCanvas.gameScr.gameService.requestMonsterInfo(this.ID);
            }
            if (Res.monsterTemplates != null && Res.monsterTemplates[this.monster_type] != null && !this.getMonster_Template) {
                this.setType(this.monster_type);
                this.getMonster_Template = true;
                this.speed = Res.monsterTemplates[this.monster_type].speed;
                if (Res.monsterTemplates[this.monster_type].getImage(this.monster_type) == null) {
                    Res.monsterTemplates[this.monster_type].loadImage();
                }
            }
            if (this.beStune && mSystem.currentTimeMillis() > this.timeBeStune) {
                this.beStune = false;
            }
            if (this.timeLive == -1 && this.state != 3) {
                this.moveToXY();
            }
            switch (this.state) {
                case 4: {
                    this.p1 = (short)(this.p1 + 1);
                    if (this.p1 <= 5) break;
                    this.p1 = 0;
                    if (this.typeattack == 4 || this.typeattack == 5) break;
                    this.state = 0;
                    break;
                }
                case 0: {
                    if (this.target != null && this.timeLive != -1 && this.canmove) {
                        this.dir = this.x > this.target.x ? (short)2 : (short)3;
                        this.setHuong(this.target.x, this.target.y);
                    }
                    if (this.target == null && this.canmove && Utils.getDistance(this, GameScr.mainChar) <= 80) {
                        this.setHuong(GameScr.mainChar.x, GameScr.mainChar.y);
                    }
                    if (!this.canmove || this.timeLive == -1) {
                        this.vx = 0;
                        this.vy = 0;
                        break;
                    }
                    this.changSpeed = this.sp[r.nextInt(this.sp.length - 1)];
                    ++this.timeWait;
                    if (this.timeWait <= this.timeLimit || this.idChar != -1) break;
                    this.range = (short)Res.random(16, 32);
                    short xL = (short)(this.xFirst - this.range);
                    short xR = (short)(this.xFirst + this.range);
                    short yU = (short)(this.yFirst - this.range);
                    short yD = (short)(this.yFirst + this.range);
                    if (this.target != null) {
                        xL = (short)((this.x + this.target.x) / 2 - this.range);
                        xR = (short)((this.x + this.target.x) / 2 + this.range);
                        yU = (short)((this.y + this.target.y) / 2 - this.range);
                        yD = (short)((this.y + this.target.y) / 2 + this.range);
                    }
                    short mDir = (short)r.nextInt(4);
                    if (this.lastDir != -100 && this.lastDir == mDir) {
                        if (mDir == 2) {
                            mDir = 3;
                        } else if (mDir == 3) {
                            mDir = 2;
                        } else if (mDir == 1) {
                            mDir = 0;
                        } else if (mDir == 0) {
                            mDir = 1;
                        }
                    }
                    if (mDir == 2) {
                        this.mtoX = xL;
                        this.mtoY = this.y;
                    } else if (mDir == 3) {
                        this.mtoX = xR;
                        this.mtoY = this.y;
                    } else if (mDir == 1) {
                        this.mtoX = this.x;
                        this.mtoY = (short)(yU - 15);
                    } else if (mDir == 0) {
                        this.mtoX = this.x;
                        this.mtoY = yD;
                    }
                    int[] posto = this.getXYMoveTo(this.mtoX, this.mtoY, mDir, this.speed);
                    this.mtoX = (short)posto[0];
                    this.mtoY = (short)posto[1];
                    if (this.checkmoveChar(this.mtoX, this.mtoY) && !Tilemap.tileTypeAtPixel(this.x, this.y, 2)) {
                        this.dir = mDir;
                        this.state = (byte)2;
                        this.timeWait = 0;
                        this.lastDir = mDir;
                    }
                    if (!Tilemap.tileTypeAtPixel(this.x, this.y, 2)) break;
                    this.state = (byte)7;
                    break;
                }
                case 3: {
                    this.UpdateAttack();
                    break;
                }
                case 5: {
                    this.frame = (byte)3;
                    this.p1 = (short)(this.p1 + 1);
                    if (!this.isMineral) {
                        this.x = (short)(this.x + this.p2);
                        this.y = (short)(this.y + this.p3);
                    }
                    this.p2 = (short)(this.p2 >> 1);
                    this.p3 = (short)(this.p3 >> 1);
                    this.setDie();
                    break;
                }
                case 8: {
                    if (GameCanvas.gameScr.focusedActor != null && GameCanvas.gameScr.focusedActor == this && GameScr.mainChar.state == 0) {
                        GameCanvas.gameScr.focusedActor = null;
                    }
                    if (!(this.timeLive != -1 && this.timeLive != -2 || Tilemap.isMapIntro())) {
                        this.wantDestroy = true;
                        return;
                    }
                    if (!this.canrevives) {
                        this.wantDestroy = true;
                        return;
                    }
                    if (this.timeRevive - mSystem.currentTimeMillis() >= 0L || !this.canrevives) break;
                    this.Revive();
                    break;
                }
                case 6: {
                    if (this.target == null) break;
                    this.movetoTarget(this.xTarget, this.yTarget);
                    break;
                }
                case 2: 
                case 7: {
                    if (!this.canmove) {
                        this.vx = 0;
                        this.vy = 0;
                        break;
                    }
                    this.p1 = (short)(this.p1 + 1);
                    if (this.p1 > 6) {
                        this.p1 = 0;
                    }
                    if (this.isFreeze) break;
                    if (this.idChar > -1) {
                        this.move();
                        break;
                    }
                    if (this.state == 7) {
                        this.setMove(this.xFirst, this.yFirst);
                        break;
                    }
                    if (this.state != 2) break;
                    this.move();
                    break;
                }
                case 11: {
                    EffectSkill.createHiEfect(this.x, this.y, 81);
                    int xff = 16;
                    int yf = -16;
                    if (GameCanvas.gameTick % 2 == 0) {
                        xff *= -1;
                        yf *= -1;
                    }
                    this.x = (short)(this.target.x + xff);
                    this.y = (short)(this.target.y + yf);
                    this.state = (byte)3;
                    break;
                }
                case 12: {
                    EffectSkill.createHiEfect(this.x, this.y, 81);
                    this.x = this.savex;
                    this.y = this.savey;
                    this.state = 0;
                }
            }
            if (this.state != 8 && this.state != 1 && this.state != 5) {
                int xv = this.x + this.dvangx;
                int yv = this.y + this.dvangy;
                int dxvangcu = this.dvangx;
                int dyvangcu = this.dvangy;
                if (!Tilemap.tileTypeAtPixel(xv, yv, 2)) {
                    this.x = (short)(this.x + this.dvangx);
                    this.y = (short)(this.y + this.dvangy);
                } else {
                    this.dvangx = 0;
                    this.dvangy = 0;
                }
                if (this.dvangx > 0) {
                    --this.dvangx;
                }
                if (this.dvangx < 0) {
                    ++this.dvangx;
                }
                if (this.dvangy > 0) {
                    --this.dvangy;
                }
                if (this.dvangy < 0) {
                    ++this.dvangy;
                }
                if (this.dvangx == 0 && this.dvangy == 0 && (dxvangcu != 0 || dyvangcu != 0)) {
                    this.moveTo(this.saveXvang, this.saveYvang);
                }
            }
            this.updateDataEff();
            if (GameScr.isIntro && this.hp <= 0 && this.state != 5 && this.state != 1 && this.state != 8) {
                this.die();
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        super.update();
    }

    private void move() {
        if (!this.mcanmove()) {
            return;
        }
        if (this.timeLive == -1) {
            return;
        }
        if (!this.canmove) {
            return;
        }
        this.movetoXY(this.mtoX, this.mtoY);
    }

    private void moveAroundTarget() {
    }

    @Override
    public MonsterTemplate getMonsterTemplate() {
        return (MonsterTemplate)MonsterTemplate.ALL_MONSTER_TEMPLATE.get("" + this.idTemplate);
    }

    public void setInfo(MonsterInfo monsterInfo) {
        this.timeLive = monsterInfo.timeLive;
        this.xFirst = monsterInfo.default_x;
        this.yFirst = monsterInfo.default_y;
        this.saveXfirst = monsterInfo.default_x;
        this.saveYfirst = monsterInfo.default_y;
        this.x = monsterInfo.x;
        this.y = monsterInfo.y;
        this.rangestop = (byte)(r.nextInt(10) + 6);
        this.maxhp = this.hp = monsterInfo.hp;
        this.he = monsterInfo.he;
        this.p1 = 0;
        this.p2 = 0;
        this.p3 = 0;
        this.level = (byte)monsterInfo.lv;
        this.isSetInfo = true;
        this.x = monsterInfo.default_x;
        this.y = monsterInfo.default_y;
        this.xTo = this.x;
        this.yTo = this.y;
        this.setCanFocus(monsterInfo.canFocus);
        this.colorName = monsterInfo.colorName;
        this._beFire = monsterInfo.beFire;
        if (this.colorName == 3) {
            this.name = String.valueOf(this.oldname) + " " + T.tinhanh;
        }
        if (this.colorName == 1) {
            this.name = String.valueOf(this.oldname) + " " + T.thulinh;
        }
        if (this.hp <= 0) {
            this.hp = 0;
            this.state = (byte)8;
            this.timeRevive = System.currentTimeMillis() + (long)this.timeLive;
            this._isDie = true;
        }
        this.totalWay = monsterInfo.totalWay;
        this.dyPaintPk = monsterInfo.dyPaintPk;
        this.nameTieu = monsterInfo.nameTieu;
    }

    @Override
    public void setMove(int i, int j) {
        boolean isMatchX = false;
        boolean isMatchY = false;
        int dx1 = Math.abs(this.x - i);
        int dy1 = Math.abs(this.y - j);
        if (dx1 <= this.speed) {
            this.x = (short)i;
            isMatchX = true;
        }
        if (dy1 < this.speed) {
            this.y = (short)j;
            isMatchY = true;
        }
        if (isMatchX && isMatchY || !this.checkmoveChar(i, j)) {
            this.p3 = 0;
            this.p2 = 0;
            this.p1 = 0;
            this.state = 0;
            this.timeLimit = Res.random(20, 30);
            this.vy = 0;
            this.vx = 0;
        } else if (this.x < i) {
            this.x = (short)(this.x + this.speed);
            this.dir = (short)3;
        } else if (this.x > i) {
            this.x = (short)(this.x - this.speed);
            this.dir = (short)2;
        } else if (this.y > j) {
            this.y = (short)(this.y - this.speed);
            this.dir = 1;
        } else if (this.y < j) {
            this.dir = 0;
            this.y = (short)(this.y + this.speed);
        }
        this.setHuong(i, j);
    }

    public void movetoXY(int i, int j) {
        if (!this.mcanmove()) {
            return;
        }
        if (i == 0 || j == 0) {
            this.state = 0;
            return;
        }
        if (this.timeLive == -1) {
            return;
        }
        boolean isMatchX = false;
        boolean isMatchY = false;
        int dx1 = Math.abs(this.x - i);
        int dy1 = Math.abs(this.y - j);
        if (dx1 <= this.speed) {
            this.x = (short)i;
            isMatchX = true;
        }
        if (dy1 < this.speed) {
            this.y = (short)j;
            isMatchY = true;
        }
        if (Tilemap.tileTypeAtPixel(this.x, this.y, 2) && !this.isFly()) {
            EffectSkill.createHiEfect(this.x, this.y, 81);
            int xff = 16;
            int yf = -16;
            if (GameCanvas.gameTick % 2 == 0) {
                xff *= -1;
                yf *= -1;
            }
            this.x = (short)(i + xff);
            this.y = (short)(j + yf);
            isMatchX = true;
            isMatchY = true;
        }
        if (isMatchX && isMatchY) {
            this.p3 = 0;
            this.p2 = 0;
            this.p1 = 0;
            this.state = 0;
            this.vy = 0;
            this.vx = 0;
            if (this.target != null) {
                this.dir = this.x > this.target.x ? (short)2 : (short)3;
            }
            this.timeLimit = Res.random(50, 70);
            if (this.target != null) {
                this.dir = Util.findDirection(this, this.target);
            }
        } else if (this.x < i) {
            this.x = (short)(this.x + this.speed);
            this.dir = (short)3;
        } else if (this.x > i) {
            this.x = (short)(this.x - this.speed);
            this.dir = (short)2;
        } else if (this.y > j) {
            this.y = (short)(this.y - this.speed);
            this.dir = 1;
        } else if (this.y < j) {
            this.dir = 0;
            this.y = (short)(this.y + this.speed);
        }
        this.setHuong(i, j);
    }

    public void movetoTarget(int i, int j) {
        if (!this.mcanmove()) {
            return;
        }
        if (this.timeLive == -1) {
            return;
        }
        boolean isMatchX = false;
        boolean isMatchY = false;
        int dx1 = Math.abs(this.x - i);
        int dy1 = Math.abs(this.y - j);
        if (dx1 <= this.speed * 2) {
            this.x = (short)i;
            isMatchX = true;
        }
        if (dy1 < this.speed * 2) {
            this.y = (short)j;
            isMatchY = true;
        }
        if (Tilemap.tileTypeAtPixel(this.x, this.y, 2) && !this.isFly() || (dx1 >= 80 || dy1 >= 80) && this.typeattack == 0) {
            EffectSkill.createHiEfect(this.x, this.y, 81);
            int xff = 16;
            int yf = -16;
            if (GameCanvas.gameTick % 2 == 0) {
                xff *= -1;
                yf *= -1;
            }
            this.x = (short)(i + xff);
            this.y = (short)(j + yf);
            isMatchX = true;
            isMatchY = true;
        }
        if (!(this.typeattack != 2 && this.typeattack != 3 || dx1 > 70 && dy1 > 70)) {
            isMatchX = true;
            isMatchY = true;
        }
        if (dx1 <= 10 && dy1 <= 10 || isMatchX && isMatchY) {
            this.dir = this.x > this.target.x ? (short)2 : (short)3;
            this.p3 = 0;
            this.p2 = 0;
            this.p1 = 0;
            this.state = (byte)3;
            this.frame = 0;
            this.vy = 0;
            this.vx = 0;
            if (this.target != null) {
                this.dir = Util.findDirection(this, this.target);
            }
            this.lastDir = this.dir;
        } else if (this.x < i) {
            this.x = (short)(this.x + this.speed);
            this.dir = (short)3;
        } else if (this.x > i) {
            this.x = (short)(this.x - this.speed);
            this.dir = (short)2;
        } else if (this.y > j) {
            this.y = (short)(this.y - this.speed);
            this.dir = 1;
        } else if (this.y < j) {
            this.dir = 0;
            this.y = (short)(this.y + this.speed);
        }
        this.setHuong(i, j);
    }

    @Override
    public void moveTo(short xTo, short yTo) {
        if (this.state == 8 || this.state == 1 || this.state == 5) {
            return;
        }
        if (this.state == 3) {
            return;
        }
        if (this.xTo == xTo && this.yTo == yTo) {
            this.state = 0;
            return;
        }
        if (!this.mcanmove()) {
            return;
        }
        this.xTo = xTo;
        this.yTo = yTo;
        this.state = (byte)2;
    }

    public void moveToXY() {
        if (!this.mcanmove()) {
            return;
        }
        if (this.xTo == this.x && this.yTo == this.y) {
            this.state = 0;
            return;
        }
        boolean isMatchX = false;
        boolean isMatchY = false;
        int dx1 = Math.abs(this.x - this.xTo);
        int dy1 = Math.abs(this.y - this.yTo);
        if (dx1 <= this.speed) {
            this.x = this.xTo;
            isMatchX = true;
        }
        if (dy1 < this.speed) {
            this.y = this.yTo;
            isMatchY = true;
        }
        if (Tilemap.tileTypeAtPixel(this.x, this.y, 2)) {
            EffectSkill.createHiEfect(this.x, this.y, 81);
            this.x = this.xTo;
            this.y = this.yTo;
            isMatchX = true;
            isMatchY = true;
            this.state = 0;
        }
        int disrang = Utils.getDistance(this.x, this.y, this.xTo, this.yTo);
        if (isMatchX && isMatchY || disrang <= 25) {
            this.p3 = 0;
            this.p2 = 0;
            this.p1 = 0;
            this.state = 0;
            this.vy = 0;
            this.vx = 0;
        } else if (this.x < this.xTo) {
            this.x = (short)(this.x + this.speed);
            this.dir = (short)3;
        } else if (this.x > this.xTo) {
            this.x = (short)(this.x - this.speed);
            this.dir = (short)2;
        } else if (this.y > this.yTo) {
            this.y = (short)(this.y - this.speed);
            this.dir = 1;
        } else if (this.y < this.yTo) {
            this.dir = 0;
            this.y = (short)(this.y + this.speed);
        }
        this.setHuong(this.xTo, this.yTo);
    }

    public void setHuong(int xto, int yto) {
        this.huongY = (byte)(this.y > yto ? 1 : 0);
        this.flip = (byte)(this.x - xto > 0 ? 0 : 2);
        this.flip = 0;
        if (this.dir == 3) {
            this.flip = (byte)2;
            if (this.totalWay == 3) {
                this.huongY = (byte)2;
            }
        } else if (this.dir == 2) {
            if (this.totalWay == 3) {
                this.huongY = (byte)2;
            }
        } else if (this.dir == 0) {
            this.huongY = 0;
        } else if (this.dir == 1) {
            this.huongY = 1;
        }
    }

    @Override
    public boolean isMonster() {
        return true;
    }

    @Override
    public void setPosition(int x, int y) {
        this.x = (short)x;
        this.y = (short)y;
    }

    @Override
    public void addEffectWhenActorDie(int ideffect) {
    }

    @Override
    public boolean inCamera(int cmx, int cmy) {
        return this.x + 50 >= cmx && this.x - 50 <= cmx + GameCanvas.w && this.y + 50 >= cmy && this.y - 50 <= cmy + GameCanvas.h;
    }

    @Override
    public int getState() {
        return this.state;
    }

    @Override
    public void setMove(boolean canmove) {
        this.canmove = canmove;
        this.timeStand = 0;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setCanrevives(boolean canrevives) {
        this.canrevives = canrevives;
    }

    @Override
    public void setmuntiTarget(mVector target) {
        if (this.hp <= 0 || this.state == 1 || this.state == 5 || this.state == 8) {
            this.Revive();
        }
        this.ntarget = target;
        Actor ac = (Actor)this.ntarget.elementAt(0);
        if (ac != null) {
            this.target = ac;
        }
        this.dir = Util.findDirection(this, this.target);
        this.setHuong(this.target.x, this.target.y);
        if (this.timeLive > 0) {
            this.state = (byte)6;
            this.xTarget = (short)(this.target.x + (Res.random(10) < 5 ? 16 : -16));
            this.yTarget = (short)(this.target.y + (Res.random(10) < 5 ? 16 : -16));
        }
    }

    @Override
    public void setDie() {
        if (this.tickDie > 0) {
            return;
        }
        this.removeAllEff();
        if (this.typeDie == 0) {
            this.countDie = (byte)(this.countDie - 1);
            if (this.countDie > 0) {
                this.canpaint = this.countDie % 2 == 0;
            }
            if (this.countDie <= 0 && this.timeLive != -1 && this.state != 8) {
                this.state = (byte)8;
                this.timeRevive = System.currentTimeMillis() + (long)this.timeLive;
                EffectSkill.createHiEfect(this.x, this.y, 81);
                this._isDie = true;
            }
        } else if (this.typeDie > 0) {
            ++this.frameDie;
            if (!this._isDie) {
                this.x = (short)(this.x + this.vxDie);
                this.y = (short)(this.y + this.vyDie);
                if (this.frameDie >= this.timeBienmat) {
                    this.countDie = (byte)(this.countDie - 1);
                    if (this.countDie > 0) {
                        this.canpaint = this.countDie % 2 == 0;
                    }
                    if (this.countDie <= 0 && this.timeLive != -1 && this.state != 8) {
                        this.state = (byte)8;
                        this.timeRevive = System.currentTimeMillis() + (long)this.timeLive;
                        EffectSkill.createHiEfect(this.x, this.y, 81);
                        this._isDie = true;
                    }
                }
            }
        }
    }

    private void die() {
        this._isDie = false;
        this.countDie = 0;
        this.vx = 0;
        this.vy = 0;
        this.p1 = 0;
        if (this.state != 8) {
            this.state = (byte)8;
            this.timeRevive = System.currentTimeMillis() + (long)this.timeLive;
            this._isDie = true;
        }
    }

    public void resetXY() {
        this.xTo = this.x;
        this.yTo = this.y;
        this.vx = 0;
        this.vy = 0;
        this.vx = 0;
        this.vy = 0;
        this.p1 = 0;
        this.vxDie = this.dx;
        this.vyDie = this.dy;
        this.frameDie = 0;
        this._isDie = false;
        this.countDie = 0;
        this.canpaint = true;
        this.vangx = 0;
        this.vangy = 0;
    }

    @Override
    public void setFlyDame(int[] dame) {
        this.mDame = new int[dame.length];
        int i = 0;
        while (i < this.mDame.length) {
            this.mDame[i] = dame[i];
            ++i;
        }
    }

    @Override
    public void jumpVang(Actor causeBy) {
        if (this.isbossOffline) {
            return;
        }
        if (this.typeattack == 5 || this.typeattack == 4 || !this.canmove || !this.mcanmove()) {
            return;
        }
        if (causeBy == null) {
            return;
        }
        if (this.state == 6) {
            return;
        }
        if (Utils.getDistance(this.xFirst, this.yFirst, this.x, this.y) > 80) {
            return;
        }
        this.saveXvang = this.x;
        this.saveYvang = this.y;
        this.z = -3;
        this.dz = -3;
        this.state = (byte)4;
        this.dvangx = (short)((this.x - causeBy.x) * 3);
        this.dvangy = (short)((this.y - causeBy.y) * 3);
        while (this.dvangx > 4 || this.dvangy > 4 || this.dvangx < -4 || this.dvangy < -4) {
            this.dvangx >>= 1;
            this.dvangy >>= 1;
        }
        if (GameScr.isIntro) {
            if (causeBy == GameScr.mainChar) {
                int value = 4;
                if (this.dvangx > 0) {
                    this.dvangx += value;
                }
                if (this.dvangx < 0) {
                    this.dvangx -= value;
                }
                if (this.dvangy > 0) {
                    this.dvangy += value;
                }
                if (this.dvangy < 0) {
                    this.dvangy -= value;
                }
            } else {
                this.dvangx = 0;
                this.dvangy = 0;
            }
        } else if (causeBy == GameScr.mainChar) {
            if (Res.inRangeActor(this, GameScr.mainChar, 32)) {
                int value = 4;
                if (this.dvangx > 0) {
                    this.dvangx += value;
                }
                if (this.dvangx < 0) {
                    this.dvangx -= value;
                }
                if (this.dvangy > 0) {
                    this.dvangy += value;
                }
                if (this.dvangy < 0) {
                    this.dvangy -= value;
                }
            }
        } else {
            int value = 4;
            if (this.dvangx > 0) {
                this.dvangx += value;
            }
            if (this.dvangx < 0) {
                this.dvangx -= value;
            }
            if (this.dvangy > 0) {
                this.dvangy += value;
            }
            if (this.dvangy < 0) {
                this.dvangy -= value;
            }
        }
    }

    @Override
    public byte getColorName() {
        return this.colorName;
    }

    @Override
    public boolean mcanmove() {
        if (!this.canmove) {
            return false;
        }
        return super.mcanmove();
    }

    public void Revive() {
        this.removeAllEff();
        this.wantDestroy = false;
        this._isDie = false;
        this.canpaint = true;
        this.tickDie = -1;
        this.dir = (byte)r.nextInt(4);
        this.timeLimit = Res.random(10, 20);
        this.state = 0;
        this.timeWait = 0;
        this.timeRevive = 0L;
        this.p3 = 0;
        this.p2 = 0;
        this.p1 = 0;
        this.hp = this.maxhp;
        this.vyStyleDie = 0;
        this.vyStyleStand = 0;
        this.vyDie = 0;
        this.vxDie = 0;
        this.frameDie = 0;
        this.resetXY();
        this.x = this.xTo = this.xFirst;
        this.y = this.yTo = this.yFirst;
        this.target = null;
    }

    @Override
    public void dieActor(Actor from) {
        this.removeAllEff();
        this.hp = 0;
        if (this.idTemplate == 88) {
            GameScr.Ghost = null;
            GameScr.isGost = false;
            GameCanvas.gameScr.actors.removeElement(this);
            GameCanvas.gameScr.focusedActor = null;
            return;
        }
        if (GameCanvas.gameScr.focusedActor != null && GameCanvas.gameScr.focusedActor.equals(this)) {
            GameCanvas.gameScr.focusedActor = null;
        }
        if (this.timeLive == -1 || this.timeLive == -2) {
            this.wantDestroy = true;
            return;
        }
        if (from == null) {
            this._isDie = true;
            EffectSkill.createHiEfect(this.x, this.y, 81);
            this.state = (byte)8;
            this.timeRevive = mSystem.currentTimeMillis() + (long)this.timeLive;
        } else {
            this.typeDie = (byte)Res.random(3);
            this.state = (byte)5;
            if (this.typeDie == 0) {
                this.countDie = (byte)10;
            } else if (this.typeDie > 0) {
                int vs;
                this._isDie = false;
                this.frameDie = 0;
                this.vxDie = (this.x - from.x) * 2;
                this.vyDie = (this.y - from.y) * 2;
                this.vxDie = this.vxDie * 2 / 3;
                this.vyDie = this.vyDie * 2 / 3;
                this.vyDie = this.dy;
                int fdx = this.x - from.x;
                int fdy = this.y - from.y;
                int angle = Util.angle(fdx, fdy);
                int vvv = Res.random(5, 8);
                this.vxDie = vvv * Util.cos(angle) >> 10;
                this.vyDie = vvv * Util.sin(angle) >> 10;
                this.vyStyleDie = vs = Res.random(3, 6);
                this.vyStyleStand = vs;
                this.countDie = (byte)10;
                this.timeBienmat = 5;
            }
        }
        if (GameCanvas.gameScr.focusedActor != null && GameCanvas.gameScr.focusedActor.equals(this)) {
            GameCanvas.gameScr.focusedActor = null;
        }
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    @Override
    public void setTickDie(int value) {
        this.hp = 0;
        if (this.timeLive == -1 || this.timeLive == -2) {
            this.wantDestroy = true;
        }
        this.tickDie = value;
    }

    public boolean checkmoveChar(int mx, int my) {
        int i = 0;
        while (i < GameCanvas.gameScr.actors.size()) {
            Actor ac = (Actor)GameCanvas.gameScr.actors.elementAt(i);
            if (ac != null && ac.catagory == 0 && Res.inRangeActor(ac.x - 16, ac.y - 16, mx, my, 32)) {
                return false;
            }
            ++i;
        }
        return true;
    }

    @Override
    public boolean canFocusMonster() {
        return this.state != 1 && this.state != 5 && this.state != 8;
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getYmonster() {
        return this.target != null ? this.y : this.yFirst;
    }

    @Override
    public int getXmonster() {
        return this.target != null ? this.x : this.xFirst;
    }

    public boolean isBoss() {
        return false;
    }

    @Override
    public int getHeNguHanh() {
        return this.he;
    }
}
