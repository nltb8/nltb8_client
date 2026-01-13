/*
 * Decompiled with CFR 0.152.
 */
package mchien.code.model;

import mchien.code.effect.EffectData;
import mchien.code.main.GameCanvas;
import mchien.code.network.GameService;
import mchien.code.network.MessageHandler;
import mchien.code.screen.MenuLogin;
import mchien.code.screen.Res;
import mchien.code.screen.SkillTemplate;
import mchien.code.screen.Util;
import mchien.code.screen.Utils;
import mchien.code.screen.screen.ChangScr;
import mchien.code.screen.screen.CharSelectScr;
import mchien.code.screen.screen.GameData;
import mchien.code.screen.screen.GameScr;
import mchien.code.screen.screen.InfoOtherCharScr;
import mchien.code.screen.screen.Khu;
import mchien.code.screen.screen.LoadingScr;
import mchien.code.screen.screen.Location;
import mchien.code.screen.screen.MainMenu;
import mchien.code.screen.screen.MenuSelectItem;
import mchien.code.screen.screen.ScreenClan;
import mchien.code.screen.screen.ServerListScr;
import mchien.code.screen.screen.SetInfoData;
import mchien.code.screen.screen.ShopHairScreen;
import mchien.code.screen.screen.baucua.BauCua;
import mchien.code.screen.screen.baucua.BauCuaManager;
import mchien.code.screen.screen.baucua.BauCuaPlayer;
import mchien.code.screen.screen.baucua.BauCuaRoom;
import mchien.code.screen.screen.baucua.BauCuaUsageExample;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import javax.microedition.lcdui.Image;
import lib.Cout;
import lib.Rms;
import lib.Session_ME;
import lib.Tilemap;
import lib.mGraphics;
import lib.mHashtable;
import lib.mSound;
import lib.mSystem;
import lib.mVector;
import lib2.Message;
import lib2.mFont;
import mchien.code.screen.screen.TradeScr;

public class ReadMessenge
implements IActionListener {
    short idDialog;
    byte typeDialog;
    public short idNpcReceive = (short)5;
    public static short[] listIDChangeTreeJava = null;
    public static mVector allImageSaveRms = new mVector();
    public static byte versionImage = (byte)-1;
    public static int totalImg = 0;
    public static int allImage;
    LoadingScr loadImgScr = null;
    public static final byte LIST_PARTY = 0;
    public static final byte INVITE_MEMBER = 1;
    public static final byte ACCEPT_MEMBER = 2;
    public static final byte KICK_MEMBER = 3;
    public static final byte DEL_PARTY = 4;
    public static final byte OUT_PARTY = 5;

    public void onNEwAccount(Message msg) {
        try {
            String name = msg.reader().readUTF();
            String pass = msg.reader().readUTF();
            Rms.saveRMSString(Rms.User_Quick_Play, name);
            Rms.saveRMSString(Rms.Pass_Quick_Play, pass);
            this.savePass(name, pass);
            MainChar.newQuickSlot();
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void savePass(String user, String pass) {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bo);
        try {
            dos.writeBoolean(true);
            dos.writeUTF(user);
            dos.writeUTF(pass);
            Rms.saveRMS(Rms.User_Pass, bo.toByteArray());
            dos.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onsetInfoNPC_Server(Message msg) {
        try {
            int size = msg.reader().readByte();
            int k = 0;
            while (k < size) {
                String name = msg.reader().readUTF();
                String strTalk = msg.reader().readUTF();
                String cmdname = msg.reader().readUTF();
                short id = msg.reader().readShort();
                short x = msg.reader().readShort();
                short y = msg.reader().readShort();
                short xBlock = msg.reader().readShort();
                short yBlock = msg.reader().readShort();
                byte dx = msg.reader().readByte();
                byte dy = msg.reader().readByte();
                int lenght = msg.reader().readShort();
                byte[] data = new byte[lenght];
                int i = 0;
                while (i < lenght) {
                    data[i] = msg.reader().readByte();
                    ++i;
                }
                short idimage = msg.reader().readShort();
                short idicon = msg.reader().readShort();
                byte typefocus = msg.reader().readByte();
                NPC npc = null;
                Actor acNPC = GameCanvas.gameScr.findActor(id, 2);
                if (acNPC != null) {
                    acNPC.setinfoNPC(id, x, y, name, idimage, data, idicon);
                } else {
                    npc = new NPC(id, x, y, name, idimage, data, idicon);
                    npc.setText(strTalk, cmdname);
                    npc.typeFocus = typefocus;
                }
                Tilemap.setArialBlock(xBlock, yBlock, dx, dy);
                int i2 = 0;
                while (i2 < GameCanvas.gameScr.actors.size()) {
                    Actor ac = (Actor)GameCanvas.gameScr.actors.elementAt(i2);
                    if (ac != null && ac.isNPC() && x == ac.x && y == ac.y) {
                        return;
                    }
                    ++i2;
                }
                GameCanvas.gameScr.actors.addElement(npc);
                ++k;
            }
        }
        catch (Exception e) {
            mSystem.println("loi ham add npc " + e.toString());
            e.printStackTrace();
        }
    }

    public void onMenu_Option(Message msg) {
        try {
            byte typemenu = msg.reader().readByte();
            short idNPC = msg.reader().readShort();
            byte ID = msg.reader().readByte();
            int length = msg.reader().readByte();
            mVector cmd = new mVector();
            int i = 0;
            while (i < length) {
                String cap = msg.reader().readUTF();
                short time = msg.reader().readShort();
                mCommand c = null;
                if (typemenu == 0) {
                    c = new mCommand(cap, (IActionListener)GameCanvas.menu, typemenu == 0 ? -1 : 3, mSystem.currentTimeMillis() + (long)(time * 1000));
                }
                if (typemenu == 1) {
                    c = new mCommand(cap, (IActionListener)GameCanvas.menu2, typemenu == 0 ? -1 : 3, mSystem.currentTimeMillis() + (long)(time * 1000));
                }
                cmd.addElement(c);
                ++i;
            }
            String infoShow = msg.reader().readUTF();
            short idBig = msg.reader().readShort();
            if (typemenu == 0) {
                GameCanvas.menu.startAt_MenuOption(cmd, ID, idNPC, infoShow, idBig);
            }
            if (typemenu == 1) {
                GameCanvas.menu2.startArt(cmd, 5, infoShow, ID, idNPC);
            }
        }
        catch (Exception e) {
            mSystem.println("loi trong ham nhan me nu");
        }
    }

    public void onPopupServer(Message msg) {
        try {
            GameCanvas.endDlg();
            this.idDialog = msg.reader().readShort();
            this.typeDialog = msg.reader().readByte();
            String str = msg.reader().readUTF();
            mCommand yesAction = new mCommand("Ok", this, 0);
            mCommand noAction = new mCommand("H\u1ee7y", this, 1);
            GameCanvas.startYesNoDlg(str, yesAction, noAction);
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void addNotify(Message msg) {
        try {
            String textnotify = msg.reader().readUTF();
            byte type = msg.reader().readByte();
            if (!textnotify.equals("")) {
                GameCanvas.addNotify(textnotify, type);
                if (type == 1) {
                    GameCanvas.mevent.addEvent(T.tinden, (byte)0, String.valueOf(T.tinden) + ": " + T.tinden, 0);
                    GameCanvas.msgchat.addNewChat(T.kenhTG, String.valueOf(T.tinden) + ": ", textnotify, (byte)1, false);
                }
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void TexBox_Server(Message msg) {
        try {
            GameCanvas.endDlg();
            short ID = msg.reader().readShort();
            byte typeDL = msg.reader().readByte();
            String str = msg.reader().readUTF();
            int sizecmd = msg.reader().readByte();
            String[] minfo = new String[sizecmd];
            byte[] typeinPut = new byte[sizecmd];
            int i = 0;
            while (i < sizecmd) {
                minfo[i] = msg.reader().readUTF();
                typeinPut[i] = msg.reader().readByte();
                ++i;
            }
            String tilebox = msg.reader().readUTF();
            String drec = msg.reader().readUTF();
            GameCanvas.inputDlg.setInfo(minfo, str, typeinPut, 100, false, typeDL, ID, tilebox, drec);
            GameCanvas.inputDlg.show();
            GameCanvas.gameScr.hideGUI = 0;
            System.out.println("HIEN LEN NOI DUNG XAC THUC");
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void ongetCharList(Message msg) {
        try {
            byte clazz = msg.reader().readByte();
            short currentHead = msg.reader().readShort();
            short bodyStyle = msg.reader().readShort();
            short legStyle = msg.reader().readShort();
            short weaponStyle = msg.reader().readShort();
            byte type = msg.reader().readByte();
            if (type == 0) {
                GameScr.mainChar.clazz = clazz;
                short[] listpard = new short[]{currentHead, bodyStyle, legStyle, weaponStyle};
                GameScr.mainChar.setInfoWearing(listpard);
                GameScr.mainChar.currentHead = currentHead;
                GameScr.mainChar.bodyStyle = bodyStyle;
                GameScr.mainChar.legStyle = legStyle;
                GameScr.mainChar.weaponStyle = weaponStyle;
            }
            if (type == 1) {
                CharSelectScr.gI().switchToMe();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clearloadMap(Message msg) {
        try {
            int size = msg.reader().readByte();
            listIDChangeTreeJava = new short[size];
            int i = 0;
            while (i < size) {
                ReadMessenge.listIDChangeTreeJava[i] = msg.reader().readShort();
                ++i;
            }
        }
        catch (Exception size) {
            // empty catch block
        }
        ChangScr loadingscreen = new ChangScr();
        loadingscreen.switchToMe(GameCanvas.gameScr);
        loadingscreen.isnextmap = false;
        GameScr.isnextmap = false;
        Tilemap.clear();
        GameScr.Bossintro = null;
        GameScr.isIntro = false;
        MainChar.blockkey = false;
        if (GameScr.vecCharintro != null) {
            GameScr.vecCharintro.removeAllElements();
        }
        GameCanvas.clearKeyHold();
        MonsterTemplate.ALL_DATE_NEW_MONSTER.clear();
        GameCanvas.currentDialog = null;
        if (GameCanvas.gameScr.actors != null) {
            GameCanvas.gameScr.actors.removeAllElements();
        }
        if (EffectManager.hiEffects != null) {
            EffectManager.hiEffects.removeAllElements();
        }
        if (EffectManager.lowEffects != null) {
            EffectManager.lowEffects.removeAllElements();
        }
        GameScr.nameMap = "";
        GameCanvas.gameScr.focusedActor = null;
        GameData.removeAllImgTree();
        GameData.listbyteData.clear();
        GameData.listImgIcon.clear();
        GameScr.imgBigMap = null;
        MonsterTemplate.ALL_DATE_NEW_MONSTER.clear();
        GameScr.arrowsUp.removeAllElements();
    }

    public void onMap(Message msg) {
        try {
            MessageHandler.countmsg.clear();
            GameCanvas.gameScr.idMapColor = -1;
            GameCanvas.gameScr.isCloud = false;
            GameCanvas.gameScr.vecCloud.removeAllElements();
            GameScr.effAnimate.removeAllElements();
            mSound.stopSoundAll();
            mSound.pauseCurMusic();
            GameCanvas.gameScr.vecCharParty.removeAllElements();
            GameCanvas.gameScr.itemPick.removeAllElements();
            GameScr.charcountdonw = null;
            GameCanvas.hideAllDialog();
            ChangScr loadingscreen = new ChangScr();
            loadingscreen.switchToMe(GameCanvas.gameScr);
            GameScr.isnextmap = false;
            Tilemap.clear();
            GameScr.Bossintro = null;
            GameScr.isIntro = false;
            MainChar.blockkey = false;
            if (GameScr.vecCharintro != null) {
                GameScr.vecCharintro.removeAllElements();
            }
            GameCanvas.clearKeyHold();
            MonsterTemplate.ALL_DATE_NEW_MONSTER.clear();
            GameCanvas.currentDialog = null;
            if (GameCanvas.gameScr.actors != null) {
                GameCanvas.gameScr.actors.removeAllElements();
            }
            if (EffectManager.hiEffects != null) {
                EffectManager.hiEffects.removeAllElements();
            }
            if (EffectManager.lowEffects != null) {
                EffectManager.lowEffects.removeAllElements();
            }
            GameScr.nameMap = "";
            GameCanvas.gameScr.focusedActor = null;
            short pMapid = msg.reader().readShort();
            GameScr.mainChar.x = (short)(msg.reader().readUnsignedByte() * 16);
            GameScr.mainChar.y = (short)(msg.reader().readUnsignedByte() * 16);
            GameScr.mainChar.xTo = GameScr.mainChar.x;
            GameScr.mainChar.yTo = GameScr.mainChar.y;
            GameCanvas.gameScr.beginAuto = false;
            GameScr.mainChar.setCanPaint(true);
            short idMapload = msg.reader().readShort();
            GameScr.nameMap = msg.reader().readUTF();
            short lenByte = msg.reader().readShort();
            byte[] datamap = null;
            if (lenByte > 0) {
                datamap = new byte[lenByte];
                msg.reader().read(datamap);
            }
            if (mSystem.isj2me) {
                Chunk.arr.clear();
            }
            Tilemap.loadMap(idMapload, datamap);
            GameScr.allLocation = null;
            int nWayPoint = msg.reader().readByte();
            GameScr.allLocation = new Location[nWayPoint];
            int i = 0;
            while (i < nWayPoint) {
                Location loca = new Location();
                loca.x = msg.reader().readShort();
                loca.y = msg.reader().readShort();
                int size = 100;
                if (loca.x <= Tilemap.w * 16 / 2 && loca.y > size && loca.y < Tilemap.h * 16 - 100) {
                    loca.dis = 0;
                    loca.x2 = loca.x - 8;
                    loca.y2 = loca.y - 18;
                    loca.f = 0;
                    loca.vx = -1;
                } else if (loca.x > Tilemap.w * 16 / 2 && loca.y > size && loca.y < Tilemap.h * 16 - 100) {
                    loca.dis = 1;
                    loca.f = 1;
                    loca.x2 = loca.x + 8;
                    loca.y2 = loca.y - 18;
                    loca.vx = 1;
                } else if (loca.y < Tilemap.h * 16 / 2) {
                    loca.y -= 10;
                    loca.dis = 2;
                    loca.f = 2;
                    loca.x2 = loca.x;
                    loca.y2 = loca.y + 10;
                    loca.vy = -1;
                } else {
                    loca.dis = 3;
                    loca.f = 2;
                    loca.x2 = loca.x;
                    loca.y2 = loca.y - 20;
                    loca.vy = 1;
                }
                loca.nameMapOut = msg.reader().readUTF();
                GameScr.allLocation[i] = loca;
                ++i;
            }
            Tilemap.ismapLang = msg.reader().readBoolean();
            GameScr.arena = msg.reader().readUnsignedByte();
            GameData.removeAllImgTree();
            int allT = msg.reader().readByte();
            int i2 = 0;
            while (i2 < allT) {
                short id = msg.reader().readShort();
                short size = msg.reader().readShort();
                if (size > 0) {
                    byte[] data = new byte[size];
                    msg.reader().read(data);
                    GameData.setImgIcon(id, data);
                }
                ++i2;
            }
            byte tam = Tilemap.sizeBigmap;
            Tilemap.sizeBigmap = msg.reader().readByte();
            Tilemap.sizeBigmap = tam;
            GameCanvas.gameScr.isStartAutoAttack = false;
            GameScr.mainChar.resetAllSkill();
            GameScr.mainChar.setState(0);
            GameScr.mainChar.posTransRoad = null;
            GameCanvas.gameScr.actors.addElement(GameScr.mainChar);
            int index = 0;
            if (GameScr.allLocation != null && GameScr.allLocation.length > 0 && !GameScr.isIntro && GameScr.lastMap != -1 && GameScr.lastMap != Tilemap.lv) {
                int min = 10000000;
                int i3 = 0;
                while (i3 < GameScr.allLocation.length) {
                    if (Utils.getDistance(GameScr.mainChar.x, GameScr.mainChar.y, GameScr.allLocation[i3].x, GameScr.allLocation[i3].y) < min) {
                        min = Utils.getDistance(GameScr.mainChar.x, GameScr.mainChar.y, GameScr.allLocation[i3].x, GameScr.allLocation[i3].y);
                        index = i3;
                    }
                    ++i3;
                }
                int mdir = GameScr.allLocation[index].dis;
                GameScr.mainChar.dir = mdir == 0 ? (short)3 : (mdir == 1 ? (short)2 : (mdir == 2 ? (short)0 : 1));
                int xto = GameScr.mainChar.x;
                int yto = GameScr.mainChar.y;
                if (GameScr.mainChar.dir == 2) {
                    xto = GameScr.mainChar.x - 16;
                } else if (GameScr.mainChar.dir == 3) {
                    xto = GameScr.mainChar.x + 16;
                } else if (GameScr.mainChar.dir == 1) {
                    yto = GameScr.mainChar.y - 16;
                } else if (GameScr.mainChar.dir == 0) {
                    yto = GameScr.mainChar.y + 16;
                }
            }
            GameScr.lastMap = Tilemap.lv;
            loadingscreen.isnextmap = true;
            GameScr.isnextmap = true;
            GameScr.mainChar.sendMove = true;
            if (Tilemap.lv != 0) {
                if (Tilemap.idTile == 0 && Tilemap.lv != 56 && Tilemap.lv != 34) {
                    mSound.playMus(1, mSound.volumeMusic, true);
                }
                if (Tilemap.idTile == 5) {
                    mSound.playMus(2, mSound.volumeMusic, true);
                }
                if (Tilemap.idTile == 4) {
                    mSound.playMus(3, mSound.volumeMusic, true);
                }
                if (Tilemap.idTile == 3) {
                    mSound.playMus(4, mSound.volumeMusic, true);
                }
                if (Tilemap.idTile == 6) {
                    mSound.playMus(6, mSound.volumeMusic, true);
                }
                if (Tilemap.idTile == 0 && (Tilemap.lv == 56 || Tilemap.lv == 34 || Tilemap.lv == 58 || Tilemap.lv == 35)) {
                    mSound.playMus(5, mSound.volumeMusic, true);
                }
            }
            try {
                GameCanvas.gameScr.idMapColor = msg.reader().readInt();
                GameCanvas.gameScr.isCloud = msg.reader().readBoolean();
            }
            catch (Exception e) {
                GameCanvas.gameScr.idMapColor = -1;
                GameCanvas.gameScr.isCloud = false;
            }
            if (GameCanvas.gameScr.isCloud) {
                GameCanvas.gameScr.createCloud();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onMove(Message msg) {
        try {
            byte cat = msg.reader().readByte();
            short type = msg.reader().readShort();
            short id = msg.reader().readShort();
            short x = msg.reader().readShort();
            short y = msg.reader().readShort();
            byte iskiller = msg.reader().readByte();
            byte colorname = msg.reader().readByte();
            Actor ac = GameCanvas.gameScr.findActor(id, cat);
            if (ac != null) {
                if (ac.equals(GameScr.mainChar)) {
                    GameScr.mainChar.posTransRoad = null;
                }
                ac.moveTo(x, y);
                ac.setTypePK(iskiller);
            } else {
                ac = GameScr.CreateActor(cat, type, id, x, y, iskiller);
                if (ac != null && ac.catagory > 2) {
                    ac.setColorname(colorname);
                    ((Item)ac).name = msg.reader().readUTF();
                }
                if (ac != null) {
                    GameCanvas.gameScr.actors.addElement(ac);
                }
                if (type == 88 && ac != null && cat == 1) {
                    GameScr.Ghost = ac;
                    GameCanvas.gameScr.focusedActor = ac;
                }
            }
            ac = null;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onAttackMonster(Message msg) {
        try {
            short idAttacker = msg.reader().readShort();
            Actor ac = GameCanvas.gameScr.findActor(idAttacker, 0);
            byte idskill = msg.reader().readByte();
            int size = msg.reader().readByte();
            mVector target = new mVector();
            int[] arrdame = new int[size];
            int i = 0;
            while (i < size) {
                short id = msg.reader().readShort();
                int dam = msg.reader().readInt();
                long leftHp = msg.reader().readLong();
                Monster mons = GameCanvas.gameScr.findMonsterByID(id);
                if (mons != null) {
                    ((Actor)mons).setHp(leftHp);
                    target.addElement(mons);
                }
                arrdame[i] = dam;
                ++i;
            }
            int leftmp = msg.reader().readInt();
            byte idcount = msg.reader().readByte();
            if (ac != null) {
                if (ac.ID != GameScr.mainChar.ID) {
                    mVector vec_skill = (mVector)GameCanvas.gameScr.ALL_SKILL.elementAt(ac.getClazz());
                    SkillTemplate skill = (SkillTemplate)vec_skill.elementAt(idskill);
                    ac.startAttack(target, skill.idSkillCode, skill.idEffStartSkill, arrdame);
                } else if (ac.ID == GameScr.mainChar.ID) {
                    ItemOption item;
                    boolean isadd = false;
                    if (MainChar.itemOptionMainChar != null && MainChar.idItemOptioninVector != -1 && (item = (ItemOption)GameScr.mainChar.options.elementAt(MainChar.idItemOptioninVector)) != null) {
                        --item.value;
                        if (item.value <= 0) {
                            item.value = 0;
                            GameCanvas.gameScr.idIconX2 = (short)-1;
                            MainChar.itemOptionMainChar = null;
                            MainChar.idItemOptioninVector = (short)-1;
                        }
                    }
                    if (GameScr.mainChar.currentSkill != null && idcount == GameScr.mainChar.currentSkill.idEffServer) {
                        GameScr.mainChar.currentSkill.arrDame = arrdame;
                        isadd = true;
                        GameScr.mainChar.currentSkill.addDame();
                        if (GameScr.mainChar.currentSkill.isFlydame()) {
                            isadd = false;
                        }
                    }
                    if (GameScr.mainChar.clazz == Char.THIEU_LAM && idskill == 6) {
                        isadd = false;
                    }
                    if (!isadd) {
                        int i2 = 0;
                        while (i2 < target.size()) {
                            Actor ac1 = (Actor)target.elementAt(i2);
                            if (ac1 != null) {
                                GameCanvas.gameScr.startFlyText("- " + arrdame[i2], 0, ac1.x, ac1.y - 40, 1, -2);
                            }
                            ++i2;
                        }
                    }
                    if (target.size() > 0) {
                        GameScr.mainChar.mp = leftmp;
                    }
                }
            }
        }
        catch (Exception exception) {
            // empty catch block
            exception.printStackTrace();
        }
    }

    public void onAttackPlayer(Message msg) {
        try {
            short idAttacker = msg.reader().readShort();
            Actor ac = GameCanvas.gameScr.findActor(idAttacker, 0);
            byte idskill = msg.reader().readByte();
            int size = msg.reader().readByte();
            mVector target = new mVector();
            int dam = 0;
            System.out.println("ng tan cong: " + idAttacker);
            int[] arrdame = new int[size];
            int i = 0;
            while (i < size) {
                short id = msg.reader().readShort();
                dam = msg.reader().readInt();
                long leftHp = msg.reader().readLong();
                Actor mons = GameCanvas.gameScr.findActor(id, 0);
                if (mons != null) {
                    mons.setHp(leftHp);
                    mons.setIsCatSkill(true);
                    target.addElement(mons);
                }
                arrdame[i] = dam;
                ++i;
            }
            int charHP = msg.reader().readInt();
            int charMP = msg.reader().readInt();
            byte idcount = msg.reader().readByte();
            if (ac != null) {
                if (ac.ID != GameScr.mainChar.ID) {
                    mVector vec_skill = (mVector)GameCanvas.gameScr.ALL_SKILL.elementAt(ac.getClazz());
                    SkillTemplate skill = (SkillTemplate)vec_skill.elementAt(idskill);
                    ac.startAttack(target, skill.idSkillCode, skill.idEffStartSkill, arrdame);
                } else if (ac.ID == GameScr.mainChar.ID && target.size() > 0) {
                    GameScr.mainChar.hp = charHP;
                    GameScr.mainChar.mp = charMP;
                    boolean isadd = false;
                    if (GameScr.mainChar.currentSkill != null && idcount == GameScr.mainChar.currentSkill.idEffServer) {
                        GameScr.mainChar.currentSkill.arrDame = arrdame;
                        isadd = true;
                        GameScr.mainChar.currentSkill.addDame();
                        if (GameScr.mainChar.currentSkill.isFlydame()) {
                            isadd = false;
                        }
                    }
                    if (!isadd) {
                        int i2 = 0;
                        while (i2 < target.size()) {
                            Actor ac1 = (Actor)target.elementAt(i2);
                            if (ac1 != null) {
                                GameCanvas.gameScr.startFlyText("- " + arrdame[i2], 0, ac1.x, ac1.y - 40, 1, -2);
                            }
                            ++i2;
                        }
                    }
                }
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void onMonsterAttackPlayer(Message msg) {
        try {
            Monster mt;
            byte isAttack = msg.reader().readByte();
            short Attacker = msg.reader().readShort();
            if (isAttack == 1) {
                int hp = msg.reader().readInt();
                byte targetcat = msg.reader().readByte();
                int numberAttacked = msg.reader().readByte();
                mVector vecTarget = new mVector();
                int[] dame = new int[numberAttacked];
                int i = 0;
                while (i < numberAttacked) {
                    short Attacked = msg.reader().readShort();
                    int hplose = msg.reader().readInt();
                    int hpcon = msg.reader().readInt();
                    Actor ac = GameCanvas.gameScr.findActor(Attacked, targetcat);
                    if (ac != null) {
                        ac.setHp(hpcon);
                        if (ac.equals(GameScr.mainChar) && hplose > 0) {
                            GameCanvas.gameScr.tickHP = 2;
                            GameCanvas.gameScr.inDexHP = 2;
                        }
                        if (hplose > 0) {
                            dame[i] = hplose;
                        }
                        vecTarget.addElement(ac);
                    }
                    ++i;
                }
                byte idskill = msg.reader().readByte();
                Monster mt2 = GameCanvas.gameScr.findMonsterByID(Attacker);
                if (mt2 != null) {
                    mt2.setmuntiTarget(vecTarget);
                    mt2.startAttack(idskill);
                    mt2.setFlyDame(dame);
                }
            }
            if (isAttack == 0 && (mt = GameCanvas.gameScr.findMonsterByID(Attacker)) != null) {
                mt.comehome();
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void onCharInventory(Message m) {
        int type = -1;
        try {
            type = m.reader().readByte();
            if (type == 0) {
                int size = m.reader().readShort();
                mVector vitem = new mVector();
                int i = 0;
                while (i < size) {
                    Item it0 = new Item();
                    it0.ID = m.reader().readShort();
                    it0.catagory = m.reader().readByte();
                    it0.level = m.reader().readShort();
                    it0.charClazz = m.reader().readByte();
                    it0.name = m.reader().readUTF();
                    it0.lock = m.reader().readByte();
                    it0.plus = m.reader().readByte();
                    it0.idIcon = m.reader().readShort();
                    it0.colorname = m.reader().readByte();
                    it0.cantrade = m.reader().readBoolean();
                    it0.cansell = m.reader().readBoolean();
                    it0.priceShop = m.reader().readInt();
                    it0.quantity = m.reader().readInt();
                    int nOption = m.reader().readByte();
                    int j = 0;
                    while (j < nOption) {
                        ItemOption itoption = new ItemOption();
                        itoption.id = m.reader().readShort();
                        itoption.idColor = m.reader().readByte();
                        itoption.value = m.reader().readInt();
                        itoption.value2 = m.reader().readInt();
                        it0.options.addElement(itoption);
                        ++j;
                    }
                    it0.type = m.reader().readByte();
                    it0.numKhamNgoc = m.reader().readByte();
                    it0.idNgocKham = new int[it0.numKhamNgoc];
                    for (int k = 0; k < it0.numKhamNgoc; k++) {
                        it0.idNgocKham[k] = m.reader().readInt();
                    }
                    vitem.addElement(it0);
                    ++i;
                }
                GameScr.mainChar.charXu = m.reader().readLong();
                GameScr.mainChar.luong = m.reader().readInt();
                GameScr.mainChar.luongKhoa = m.reader().readInt();
                GameScr.mainChar.updateAllitemInInventory(vitem);
            } else if (type == 1) {
                Item it0 = new Item();
                it0.ID = m.reader().readShort();
                it0.catagory = m.reader().readByte();
                it0.level = m.reader().readShort();
                it0.charClazz = m.reader().readByte();
                it0.name = m.reader().readUTF();
                it0.lock = m.reader().readByte();
                it0.plus = m.reader().readByte();
                it0.idIcon = m.reader().readShort();
                it0.colorname = m.reader().readByte();
                it0.cantrade = m.reader().readBoolean();
                it0.cansell = m.reader().readBoolean();
                it0.priceShop = m.reader().readInt();
                it0.quantity = m.reader().readInt();
                int nOption = m.reader().readByte();
                int j = 0;
                while (j < nOption) {
                    ItemOption itoption = new ItemOption();
                    itoption.id = m.reader().readShort();
                    itoption.idColor = m.reader().readByte();
                    itoption.value = m.reader().readInt();
                    itoption.value2 = m.reader().readInt();
                    it0.options.addElement(itoption);
                    ++j;
                }
                it0.type = m.reader().readByte();
                it0.numKhamNgoc = m.reader().readByte();
                it0.idNgocKham = new int[it0.numKhamNgoc];
                for (int k = 0; k < it0.numKhamNgoc; k++) {
                    it0.idNgocKham[k] = m.reader().readInt();
                }
                GameScr.mainChar.addnewItem(it0);
            } else if (type == 2) {
                Item it0 = new Item();
                it0.ID = m.reader().readShort();
                it0.catagory = m.reader().readByte();
                it0.level = m.reader().readShort();
                it0.charClazz = m.reader().readByte();
                it0.name = m.reader().readUTF();
                it0.lock = m.reader().readByte();
                it0.plus = m.reader().readByte();
                it0.idIcon = m.reader().readShort();
                it0.colorname = m.reader().readByte();
                it0.cantrade = m.reader().readBoolean();
                it0.cansell = m.reader().readBoolean();
                it0.priceShop = m.reader().readInt();
                it0.quantity = m.reader().readInt();
                int nOption = m.reader().readByte();
                int j = 0;
                while (j < nOption) {
                    ItemOption itoption = new ItemOption();
                    itoption.id = m.reader().readShort();
                    itoption.idColor = m.reader().readByte();
                    itoption.value = m.reader().readInt();
                    itoption.value2 = m.reader().readInt();
                    it0.options.addElement(itoption);
                    ++j;
                }
                it0.type = m.reader().readByte();
                it0.numKhamNgoc = m.reader().readByte();
                it0.idNgocKham = new int[it0.numKhamNgoc];
                for (int k = 0; k < it0.numKhamNgoc; k++) {
                    it0.idNgocKham[k] = m.reader().readInt();
                }
                GameScr.mainChar.updateItemInventory(it0);
            } else if (type == 3) {
                byte id = m.reader().readByte();
                byte cat = m.reader().readByte();
                GameScr.mainChar.deleteItem(id, cat);
                GameScr.mainChar.charXu = m.reader().readLong();
                GameScr.mainChar.luong = m.reader().readInt();
                GameScr.mainChar.luongKhoa = m.reader().readInt();
                MainMenu.gI().RestItemHanhTrang();
            } else if (type == 4) {
                GameScr.mainChar.charXu = m.reader().readLong();
                GameScr.mainChar.luong = m.reader().readInt();
                GameScr.mainChar.luongKhoa = m.reader().readInt();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onActorDie(Message msg) {
        try {
            short actorID = msg.reader().readShort();
            byte cat = msg.reader().readByte();
            int size0 = GameCanvas.gameScr.actors.size();
            Actor ac = null;
            int i = 0;
            while (i < size0) {
                ac = (Actor)GameCanvas.gameScr.actors.elementAt(i);
                if (ac.ID == actorID && ac.catagory == cat) {
                    if (cat == 1) {
                        ac.dieActor(null);
                    } else if (cat == 0) {
                        GameCanvas.gameScr.isStartAutoAttack = false;
                        ac.actorDie();
                    } else {
                        ac.wantDestroy = true;
                    }
                    break;
                }
                ++i;
            }
        }
        catch (Exception e) {
            Cout.println("LOI TRONG HAM ONACTORDIE TRONG GAMESCR");
        }
    }

    public void onMonsterInfo(Message msg) {
        try {
            boolean beFire;
            MonsterInfo monsterInfo = new MonsterInfo();
            monsterInfo.id = msg.reader().readShort();
            monsterInfo.x = msg.reader().readShort();
            monsterInfo.y = msg.reader().readShort();
            monsterInfo.default_x = msg.reader().readShort();
            monsterInfo.default_y = msg.reader().readShort();
            monsterInfo.hp = msg.reader().readLong();
            monsterInfo.lv = msg.reader().readByte();
            monsterInfo.he = msg.reader().readByte();
            monsterInfo.maxhp = msg.reader().readLong();
            monsterInfo.timeLive = msg.reader().readInt();
            monsterInfo.canFocus = msg.reader().readBoolean();
            monsterInfo.colorName = msg.reader().readByte();
            monsterInfo.beFire = beFire = msg.reader().readBoolean();
            try {
                monsterInfo.totalWay = msg.reader().readByte();
                monsterInfo.dyPaintPk = msg.reader().readShort();
                monsterInfo.nameTieu = msg.reader().readUTF();
            }
            catch (Exception exception) {
                // empty catch block
            }
            GameCanvas.gameScr.onMonsterInfo(monsterInfo);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onMainCharInfo(Message msg) {
        try {
            byte[] dyh;
            short idBot;
            GameCanvas.gameScr.idIconX2 = (short)-1;
            MainChar.itemOptionMainChar = null;
            MainChar.idItemOptioninVector = (short)-1;
            GameScr.mainChar.ID = msg.reader().readShort();
            GameScr.mainChar.name = msg.reader().readUTF();
            Rms.saveMainCharName();
            GameScr.mainChar.hp = msg.reader().readInt();
            GameScr.mainChar.maxhp = msg.reader().readInt();
            GameScr.mainChar.mp = msg.reader().readInt();
            GameScr.mainChar.maxmp = msg.reader().readInt();
            GameScr.mainChar.clazz = msg.reader().readByte();
            GameScr.mainChar.options.removeAllElements();
            int nOption = msg.reader().readByte();
            int i = 0;
            while (i < nOption) {
                ItemOption itop = new ItemOption();
                itop.id = msg.reader().readShort();
                itop.idColor = msg.reader().readByte();
                itop.value = msg.reader().readInt();
                itop.value2 = msg.reader().readInt();
                GameScr.mainChar.options.addElement(itop);
                if (GameCanvas.gameScr.idIconX2 == -1 && itop.value > 0) {
                    if (itop.id == 86) {
                        GameCanvas.gameScr.idIconX2 = (short)(318 + Res.ID_ITEM);
                        MainChar.itemOptionMainChar = itop;
                        MainChar.idItemOptioninVector = (short)i;
                    } else if (itop.id == 87) {
                        GameCanvas.gameScr.idIconX2 = (short)(319 + Res.ID_ITEM);
                        MainChar.itemOptionMainChar = itop;
                        MainChar.idItemOptioninVector = (short)i;
                    } else if (itop.id == 88) {
                        GameCanvas.gameScr.idIconX2 = (short)(320 + Res.ID_ITEM);
                        MainChar.itemOptionMainChar = itop;
                        MainChar.idItemOptioninVector = (short)i;
                    }
                }
                ++i;
            }
            int sizeLvSkill = msg.reader().readByte();
            Char.levelSkill = new byte[sizeLvSkill];
            Char.Max_Skill_Learn = 0;
            int i2 = 0;
            while (i2 < sizeLvSkill) {
                Char.levelSkill[i2] = msg.reader().readByte();
                if (Char.levelSkill[i2] > 0) {
                    Char.Max_Skill_Learn = (byte)(Char.Max_Skill_Learn + 1);
                }
                ++i2;
            }
            GameScr.mainChar.level = msg.reader().readShort();
            GameScr.mainChar.lvpercent = msg.reader().readShort();
            int size = msg.reader().readByte();
            Item.moneyType = new String[size];
            int i3 = 0;
            while (i3 < size) {
                Item.moneyType[i3] = msg.reader().readUTF();
                ++i3;
            }
            MainChar.MaxInven = msg.reader().readByte();
            Char.Diemtiemnang = msg.reader().readShort();
            Char.Skill_Point = msg.reader().readShort();
            Char.sucmanh = msg.reader().readShort();
            Char.linhkhi = msg.reader().readShort();
            Char.sinhkhi = msg.reader().readShort();
            Char.thanphap = msg.reader().readShort();
            GameScr.mainChar.idNhom = msg.reader().readShort();
            int sizeGhost = msg.reader().readShort();
            if (sizeGhost > 0) {
                byte[] imgArr = new byte[sizeGhost];
                int j = 0;
                while (j < sizeGhost) {
                    imgArr[j] = msg.reader().readByte();
                    ++j;
                }
                GameScr.imgGhost = Image.createImage(imgArr, 0, imgArr.length);
            }
            GameScr.isGost = false;
            if (sizeGhost > 0) {
                GameScr.isGost = true;
            }
            if (!GameScr.isGost) {
                GameScr.Ghost = null;
            }
            GameScr.numMSG = 0;
            byte speed = msg.reader().readByte();
            GameScr.mainChar.speed = speed;
            int sizeAuto = msg.reader().readByte();
            int i4 = 0;
            while (i4 < sizeAuto) {
                byte typeAuto = msg.reader().readByte();
                MenuSelectItem.FocusAutoNhat[typeAuto] = true;
                MenuSelectItem.isAutoNhat[typeAuto] = true;
                ++i4;
            }
            GameCanvas.currentScreen.doChangeInfo(false);
            GameScr.mainChar.idClan = msg.reader().readShort();
            GameScr.mainChar.idIconClan = msg.reader().readShort();
            GameScr.mainChar.chuc_vu_clan = msg.reader().readByte();
            GameScr.mainChar.aliasNameClan = msg.reader().readUTF();
            int noptionClan = msg.reader().readByte();
            MainChar.infoOptionClan = new String[noptionClan];
            int i5 = 0;
            while (i5 < noptionClan) {
                MainChar.infoOptionClan[i5] = msg.reader().readUTF();
                ++i5;
            }
            if (!MenuLogin.isLoadQL) {
                MainChar.loadQuickSlot();
                MenuLogin.isLoadQL = true;
            }
            int totalQuickLot = msg.reader().readByte();
            int i6 = 0;
            while (i6 < totalQuickLot) {
                byte idSkill = msg.reader().readByte();
                if (idSkill > -1) {
                    if (MainChar.mQuickslot[i6] == null) {
                        MainChar.mQuickslot[i6] = new QuickSlot(i6);
                    }
                    MainChar.CheckQuicSlotSkill(idSkill);
                    SkillTemplate skill = (SkillTemplate)GameScr.vec_skill.elementAt(idSkill);
                    boolean isbuff = false;
                    if (skill != null && skill.type == SkillTemplate.TYPE_BUFF) {
                        isbuff = true;
                    }
                    MainChar.mQuickslot[i6].setIsSkill(idSkill, isbuff);
                }
                ++i6;
            }
            GameService.gI().doSendChangeQuickSlot();
            GameScr.mainChar.idBot = idBot = msg.reader().readShort();
            boolean isphihanh = false;
            try {
                isphihanh = msg.reader().readBoolean();
            }
            catch (Exception e) {
                isphihanh = false;
            }
            GameScr.mainChar.setPhihanh(isphihanh);
            short idpet = -1;
            try {
                idpet = msg.reader().readShort();
            }
            catch (Exception e) {
                idpet = -1;
            }
            Actor pet2 = GameScr.isHavePet(GameScr.mainChar.ID);
            if (idpet != -1) {
                if (pet2 == null) {
                    Pet p = new Pet(GameScr.mainChar, idpet);
                    GameCanvas.gameScr.actors.addElement(p);
                } else {
                    pet2.setidTemplatePet(idpet);
                }
            } else if (pet2 != null) {
                GameCanvas.gameScr.actors.removeElement(pet2);
            }
            int idPhiPhong = -1;
            try {
                idPhiPhong = msg.reader().readShort();
            }
            catch (Exception e) {
                idPhiPhong = -1;
            }
            GameScr.mainChar.setPhiPhong(idPhiPhong);
            GameScr.mainChar.dxyPhiphong = new byte[msg.reader().readByte()];
            msg.reader().read(GameScr.mainChar.dxyPhiphong);
            byte isauto = msg.reader().readByte();
            GameCanvas.gameScr.checkAutoAttack(isauto);
            short idHorse = msg.reader().readShort();
            GameScr.mainChar.setHorse(idHorse);
            byte[] dxh = new byte[msg.reader().readByte()];
            if (dxh.length > 0) {
                msg.reader().read(dxh);
                int i7 = 0;
                while (i7 < dxh.length) {
                    GameScr.mainChar.dxHorse[dxh[i7]] = dxh[i7 + 1];
                    i7 += 2;
                }
            }
            if ((dyh = new byte[msg.reader().readByte()]).length > 0) {
                msg.reader().read(dyh);
                int i8 = 0;
                while (i8 < dyh.length) {
                    GameScr.mainChar.dyHorse[dyh[i8]] = dyh[i8 + 1];
                    i8 += 2;
                }
            }
            short idhead = -1;
            try {
                idhead = msg.reader().readShort();
            }
            catch (Exception e) {
                idhead = -1;
            }
            GameScr.mainChar.setidHead(idhead);
            short idMatNa = -1;
            short dxMN = 0;
            short dyMN = 0;
            boolean hideHead=false;
            try {
                idMatNa = msg.reader().readShort();
                dxMN = msg.reader().readShort();
                dyMN=msg.reader().readShort();
                hideHead = msg.reader().readBoolean();
            }
            catch (Exception e) {
                idMatNa = -1;
                dxMN = 0;
                dyMN = 0;
                hideHead=false;
            }
            GameScr.mainChar.setidMatNa(idMatNa);
            GameScr.mainChar.dxMN =dxMN;
            GameScr.mainChar.dyMN = dyMN;
            GameScr.mainChar.hideHead = hideHead;

        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void onCharInfo(Message msg) {
        block27: {
            try {
                byte[] dyh;
                short id = msg.reader().readShort();
                Char ac = (Char)GameCanvas.gameScr.findActor(id, 0);
                if (ac == null) break block27;
                ac.name = msg.reader().readUTF();
                ac.hp = msg.reader().readInt();
                ac.maxhp = msg.reader().readInt();
                ac.clazz = msg.reader().readByte();
                short[] listpart = new short[msg.reader().readByte()];
                int i = 0;
                while (i < listpart.length) {
                    listpart[i] = msg.reader().readShort();
                    ++i;
                }
                ac.idBot = msg.reader().readShort();
                ac.mp = msg.reader().readInt();
                ac.maxmp = msg.reader().readInt();
                ac.setInfoWearing(listpart);
                boolean canFire = msg.reader().readBoolean();
                ac.setCanFocus(canFire);
                byte typePK = msg.reader().readByte();
                short idNhom = msg.reader().readShort();
                short lv = msg.reader().readShort();
                boolean beFire = msg.reader().readBoolean();
                ac.SetbeFire(beFire);
                ac.level = lv;
                ac.setidNhom(idNhom);
                if (idNhom != -1) {
                    boolean canadd = true;
                    int i2 = 0;
                    while (i2 < GameCanvas.gameScr.vecCharParty.size()) {
                        Actor actv = (Actor)GameCanvas.gameScr.vecCharParty.elementAt(i2);
                        if (actv != null && actv.equals(ac)) {
                            canadd = false;
                            break;
                        }
                        ++i2;
                    }
                    if (canadd) {
                        GameCanvas.gameScr.vecCharParty.addElement(ac);
                    }
                }
                if (idNhom == -1 && GameCanvas.gameScr.vecCharParty.size() > 0) {
                    int i3 = 0;
                    while (i3 < GameCanvas.gameScr.vecCharParty.size()) {
                        Actor act = (Actor)GameCanvas.gameScr.vecCharParty.elementAt(i3);
                        if (act != null && act != null && act.ID == ac.ID) {
                            GameCanvas.gameScr.vecCharParty.removeElement(act);
                        }
                        ++i3;
                    }
                }
                ac.setTypePK(typePK);
                ac.setCanFocus(canFire);
                byte speed = msg.reader().readByte();
                ac.speed = speed;
                ac.setCanPaint(true);
                ac.idClan = msg.reader().readShort();
                ac.idIconClan = msg.reader().readShort();
                ac.chuc_vu_clan = msg.reader().readByte();
                ac.aliasNameClan = msg.reader().readUTF();
                boolean isphihanh = false;
                try {
                    isphihanh = msg.reader().readBoolean();
                }
                catch (Exception e) {
                    isphihanh = false;
                }
                ac.setPhihanh(isphihanh);
                short idpet = -1;
                try {
                    idpet = msg.reader().readShort();
                }
                catch (Exception e) {
                    idpet = -1;
                }
                Actor pet2 = GameScr.isHavePet(ac.ID);
                if (idpet != -1) {
                    if (pet2 == null) {
                        Pet p = new Pet(ac, idpet);
                        GameCanvas.gameScr.actors.addElement(p);
                    } else {
                        pet2.setidTemplatePet(idpet);
                    }
                } else if (pet2 != null) {
                    GameCanvas.gameScr.actors.removeElement(pet2);
                }
                int idPhiPhong = -1;
                try {
                    idPhiPhong = msg.reader().readShort();
                }
                catch (Exception e) {
                    idPhiPhong = -1;
                }
                ac.setPhiPhong(idPhiPhong);
                ac.dxyPhiphong = new byte[msg.reader().readByte()];
                msg.reader().read(ac.dxyPhiphong);
                short idHorse = msg.reader().readShort();
                ac.setHorse(idHorse);
                byte[] dxh = new byte[msg.reader().readByte()];
                if (dxh.length > 0) {
                    msg.reader().read(dxh);
                    int i4 = 0;
                    while (i4 < dxh.length) {
                        ac.dxHorse[dxh[i4]] = dxh[i4 + 1];
                        i4 += 2;
                    }
                }
                if ((dyh = new byte[msg.reader().readByte()]).length > 0) {
                    msg.reader().read(dyh);
                    int i5 = 0;
                    while (i5 < dyh.length) {
                        ac.dyHorse[dyh[i5]] = dyh[i5 + 1];
                        i5 += 2;
                    }
                }
                short idhead = -1;
                try {
                    idhead = msg.reader().readShort();
                }
                catch (Exception e) {
                    idhead = -1;
                }
                ac.setidHead(idhead);
                short idMatNa = -1;
                short dxMN = 0;
                short dyMN = 0;
                boolean hideHead=false;
                try {
                    idMatNa = msg.reader().readShort();
                    dxMN = msg.reader().readShort();
                    dyMN=msg.reader().readShort();
                    hideHead = msg.reader().readBoolean();
                }
                catch (Exception e) {
                    idMatNa = -1;
                    dxMN = 0;
                    dyMN = 0;
                    hideHead=false;
                }
                ac.setidMatNa(idMatNa);
                ac.dxMN =dxMN;
                ac.dyMN = dyMN;
                ac.hideHead = hideHead;
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
    }

    public void ongetInfoNPC(Message m) {
        try {
            short idNPC = m.reader().readShort();
            byte cat = m.reader().readByte();
            if (idNPC != -126) {
                String nameShop = m.reader().readUTF();
                byte showInven = m.reader().readByte();
                String[] tile = showInven == 1 ? new String[]{nameShop, T.hanhtrang} : new String[]{nameShop};
                int numTab = m.reader().readByte();
                int k = 0;
                while (k < numTab) {
                    int size = m.reader().readShort();
                    mVector vitem = new mVector();
                    int i = 0;
                    while (i < size) {
                        Item it0 = new Item();
                        it0.isItemShop = true;
                        it0.ID = m.reader().readShort();
                        it0.catagory = m.reader().readByte();
                        it0.level = m.reader().readShort();
                        it0.name = m.reader().readUTF();
                        it0.charClazz = m.reader().readByte();
                        it0.lock = m.reader().readByte();
                        it0.plus = m.reader().readByte();
                        it0.idIcon = m.reader().readShort();
                        it0.colorname = m.reader().readByte();
                        it0.cantrade = m.reader().readBoolean();
                        it0.cansell = m.reader().readBoolean();
                        it0.priceShop = m.reader().readInt();
                        it0.quantity = m.reader().readInt();
                        it0.priceType = m.reader().readByte();
                        int nOption = m.reader().readByte();
                        int j = 0;
                        while (j < nOption) {
                            ItemOption itoption = new ItemOption();
                            itoption.id = m.reader().readShort();
                            itoption.idColor = m.reader().readByte();
                            itoption.value = m.reader().readInt();
                            itoption.value2 = m.reader().readInt();
                            it0.options.addElement(itoption);
                            ++j;
                        }
                        it0.type = m.reader().readByte();
                        it0.numKhamNgoc = m.reader().readByte();
                        it0.idNgocKham = new int[it0.numKhamNgoc];
                        for (int l = 0; l < it0.numKhamNgoc; l++) {
                            it0.idNgocKham[l] = m.reader().readInt();
                        }
                        vitem.addElement(it0);
                        ++i;
                    }
                    MainMenu.gI().PutItemSHop(vitem);
                    MainMenu.gI().switchToMe(GameCanvas.gameScr);
                    MainMenu.gI().setMainTabSelect(tile);
                    MainMenu.gI().numtab = tile.length;
                    if (!GameCanvas.isTouch) {
                        MainMenu.gI().left = MainMenu.cmdBuy;
                    }
                    MainMenu.gI().idNPC_Shop = idNPC;
                    MainMenu.gI().catNPC_Shop = cat;
                    ++k;
                }
            }
            if (idNPC == -126) {
                GameScr.shop.removeAllElements();
                int numTab = m.reader().readByte();
                GameScr.idNPCshopInven = idNPC;
                GameScr.catNPCshopInven = cat;
                int k = 0;
                while (k < numTab) {
                    int size = m.reader().readShort();
                    int i = 0;
                    while (i < size) {
                        Item it0 = new Item();
                        it0.isItemShop = true;
                        it0.ID = m.reader().readShort();
                        it0.catagory = m.reader().readByte();
                        it0.level = m.reader().readShort();
                        it0.name = m.reader().readUTF();
                        it0.charClazz = m.reader().readByte();
                        it0.lock = m.reader().readByte();
                        it0.plus = m.reader().readByte();
                        it0.idIcon = m.reader().readShort();
                        it0.colorname = m.reader().readByte();
                        it0.cantrade = m.reader().readBoolean();
                        it0.cansell = m.reader().readBoolean();
                        it0.priceShop = m.reader().readInt();
                        it0.quantity = m.reader().readInt();
                        it0.priceType = m.reader().readByte();
                        int nOption = m.reader().readByte();
                        int j = 0;
                        while (j < nOption) {
                            ItemOption itoption = new ItemOption();
                            itoption.id = m.reader().readShort();
                            itoption.idColor = m.reader().readByte();
                            itoption.value = m.reader().readInt();
                            itoption.value2 = m.reader().readInt();
                            it0.options.addElement(itoption);
                            ++j;
                        }
                        it0.type = m.reader().readByte();
                        it0.numKhamNgoc = m.reader().readByte();
                        it0.idNgocKham = new int[it0.numKhamNgoc];
                        for (int l = 0; l < it0.numKhamNgoc; l++) {
                            it0.idNgocKham[l] = m.reader().readInt();
                        }
                        GameScr.shop.addElement(it0);
                        ++i;
                    }
                    ++k;
                }
            }
            MainMenu.captionServer = m.reader().readUTF();
            MainMenu.infoBuySellServer = m.reader().readUTF();
            if (!MainMenu.captionServer.equals("")) {
                MainMenu.cmdBuy.caption = MainMenu.captionServer;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void receiveQuest(Message m) {
        try {
            byte type_Group_Quest = m.reader().readByte();
            int size = m.reader().readByte();
            if (type_Group_Quest == 2) {
                GameScr.allQuestWorking.removeAllElements();
                GameScr.hashQuestWorking.clear();
            }
            if (type_Group_Quest == 1) {
                GameScr.allQuestFinish.removeAllElements();
                GameScr.hashQuestFinish.clear();
            }
            if (type_Group_Quest == 0) {
                GameScr.allQuestCanReceive.removeAllElements();
                GameScr.hashQuestCanReceive.clear();
            }
            int i2 = 0;
            while (i2 < size) {
                short idQuest = m.reader().readShort();
                Quest q = new Quest(idQuest);
                q.stateQuest = type_Group_Quest;
                q.main_sub = m.reader().readByte();
                q.name = m.reader().readUTF();
                switch (type_Group_Quest) {
                    case 0: {
                        q.idNpcReceive = m.reader().readByte();
                        GameScr.hashQuestCanReceive.put(String.valueOf(q.idNpcReceive), "0");
                        String noidung = m.reader().readUTF();
                        String[] data = Util.split(noidung, ">");
                        int j = 0;
                        while (j < data.length) {
                            q.content.addElement(data[j]);
                            ++j;
                        }
                        q.type = m.reader().readByte();
                        q.deltaLv = m.reader().readByte();
                        GameScr.allQuestCanReceive.addElement(q);
                        break;
                    }
                    case 1: {
                        q.idNpcResponse = m.reader().readByte();
                        GameScr.hashQuestFinish.put(String.valueOf(q.idNpcResponse), "1");
                        String[] data = Util.split(m.reader().readUTF(), ">");
                        int j = 0;
                        while (j < data.length) {
                            q.rescontent.addElement(data[j]);
                            ++j;
                        }
                        q.decript = m.reader().readUTF();
                        m.reader().readUTF();
                        GameScr.allQuestFinish.addElement(q);
                        break;
                    }
                    case 2: {
                        short s;
                        short itemGot;
                        short iditem;
                        int j;
                        q.type = m.reader().readByte();
                        q.decript = m.reader().readUTF();
                        q.idNpcResponse = m.reader().readByte();
                        GameScr.hashQuestWorking.put(String.valueOf(q.idNpcResponse), "2");
                        q.deltaLv = m.reader().readByte();
                        m.reader().readUTF();
                        if (q.type == 2) {
                            int nitemGet = m.reader().readByte();
                            j = 0;
                            while (j < nitemGet) {
                                iditem = m.reader().readShort();
                                itemGot = m.reader().readShort();
                                s = m.reader().readShort();
                                ++j;
                            }
                        } else if (q.type == 0) {
                            byte nmonkil = m.reader().readByte();
                            j = 0;
                            while (j < nmonkil) {
                                iditem = m.reader().readShort();
                                itemGot = m.reader().readShort();
                                s = m.reader().readShort();
                                ++j;
                            }
                        } else if (q.type == 4) {
                            m.reader().readShort();
                            m.reader().readShort();
                        }
                        GameScr.allQuestWorking.addElement(q);
                    }
                }
                if (type_Group_Quest != 1) {
                    if (q.main_sub == 0) {
                        GameScr.mainQuest = q;
                    } else if (q.main_sub == 1) {
                        GameScr.subQuest = q;
                    } else if (q.main_sub == 2) {
                        GameScr.clanQuest = q;
                    }
                }
                ++i2;
            }
            if (type_Group_Quest == 1 && GameScr.isSendFinishQuest) {
                try {
                    GameCanvas.gameScr.QuestAgain();
                }
                catch (Exception i22) {
                    // empty catch block
                }
                GameScr.isSendFinishQuest = false;
            }
            int qsize = m.reader().readByte();
            MainMenu.QuestTile = new String[qsize];
            MainMenu.ListQuest = new mVector[qsize];
            int i3 = 0;
            while (i3 < qsize) {
                MainMenu.ListQuest[i3] = new mVector();
                MainMenu.QuestTile[i3] = m.reader().readUTF();
                int qsize2 = m.reader().readByte();
                int j = 0;
                while (j < qsize2) {
                    QuestInfo q = new QuestInfo();
                    q.name = m.reader().readUTF();
                    q.info = m.reader().readUTF();
                    q.idMap = m.reader().readShort();
                    q.status = m.reader().readByte();
                    q.mainsub = m.reader().readByte();
                    q.idQuest = m.reader().readShort();
                    MainMenu.ListQuest[i3].addElement(q);
                    ++j;
                }
                ++i3;
            }
            GameScr.checkQuest();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onImageFromServer(Message msg) {
        try {
            short idImg3 = msg.reader().readShort();
            byte[] arrayImg1 = new byte[msg.reader().available()];
            msg.reader().read(arrayImg1);
            if (allImage > 0) {
                if (++totalImg >= allImage || totalImg * 100 / allImage >= 99) {
                    if (totalImg >= allImage) {
                        allImage = 0;
                        totalImg = 0;
                    }
                    Rms.saveRMSString("vsImg", String.valueOf(versionImage));
                    this.loadImgScr.isFinish = true;
                }
                Rms.saveRMS(String.valueOf(idImg3), arrayImg1);
            } else {
                ImageIcon img = (ImageIcon)GameData.listImgIcon.get("" + idImg3);
                if (mSystem.isAndroid || mSystem.isPC || mSystem.isIos || mSystem.isIosAppStore() || mSystem.isIP) {
                    Rms.saveRMS(String.valueOf(idImg3), arrayImg1);
                }
                if (img != null) {
                    img.isLoad = false;
                    img.img = Image.createImage(arrayImg1, 0, arrayImg1.length);
                    if (img.img != null && idImg3 >= Res.ID_ITEM_MAP && idImg3 < Res.ID_START_SKILL) {
                        Res.maxHTree = img.img.getHeight() > Res.maxHTree ? img.img.getHeight() : Res.maxHTree;
                        Res.maxWTree = img.img.getWidth() > Res.maxWTree ? img.img.getWidth() : Res.maxWTree;
                    }
                } else {
                    img = new ImageIcon();
                    GameData.listImgIcon.put(String.valueOf(idImg3), img);
                    img.isLoad = false;
                    img.img = Image.createImage(arrayImg1, 0, arrayImg1.length);
                    if (img.img != null && idImg3 >= Res.ID_ITEM_MAP && idImg3 < Res.ID_START_SKILL) {
                        Res.maxHTree = img.img.getHeight() > Res.maxHTree ? img.img.getHeight() : Res.maxHTree;
                        Res.maxWTree = img.img.getWidth() > Res.maxWTree ? img.img.getWidth() : Res.maxWTree;
                    }
                    img.timeRemove = mSystem.currentTimeMillis() + 60000L;
                }
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void onLoadAllImage(Message msg) {
        try {
            versionImage = msg.reader().readByte();
            short totalImage = msg.reader().readShort();
            allImage = totalImage;
            this.loadImgScr = new LoadingScr();
            this.loadImgScr.switchToMe(GameCanvas.gameScr);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onUpdateAllImage(Message msg) {
    }

    public void huongdan(Message msg) {
        try {
            byte idNPC = msg.reader().readByte();
            Actor obj = GameCanvas.gameScr.findActor(idNPC, 2);
            if (obj == null) {
                return;
            }
            String strhelp = msg.reader().readUTF();
            GameScr.strHelpNPC = mFont.split(strhelp, "\b");
            GameScr.StepHelpServer = 0;
            SetInfoData mdata = new SetInfoData();
            mdata.idIcon = obj.getIDicon();
            mCommand cmd = new mCommand(T.next, (IActionListener)this, 2, (Object)mdata);
            GameCanvas.StartHelp(GameScr.strHelpNPC[GameScr.StepHelpServer], cmd, mdata.idIcon, false);
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void onGetItem(Message msg) {
        try {
            short whoget = msg.reader().readShort();
            short cat = msg.reader().readShort();
            short iditemget = msg.reader().readShort();
            GameCanvas.gameScr.onGetItemFromGround(whoget, iditemget, cat);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void chat(Message msg) {
        try {
            short id = msg.reader().readShort();
            String text = msg.reader().readUTF();
            byte cat = msg.reader().readByte();
            Actor ac = GameCanvas.gameScr.findActor(id, cat);
            if (ac != null) {
                GameScr.addChat(ac, text, 100);
                if (ac.isNPC()) {
                    GameCanvas.msgchat.addNewChat(T.tinnpc, String.valueOf(ac.getName()) + ": ", text, (byte)1, false);
                } else {
                    GameCanvas.msgchat.addNewChat(T.tinden, String.valueOf(ac.getName()) + ": ", text, (byte)1, false);
                }
                GameCanvas.mevent.addEvent(T.tinden, (byte)0, String.valueOf(T.tinden) + ": " + T.tinden, 0);
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void charOut(Message msg) {
        try {
            short charID = msg.reader().readShort();
            GameCanvas.gameScr.charOutGame(charID);
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void startBuff(Message msg) {
        try {
            mVector vec_skill;
            SkillTemplate skill;
            short idchar = msg.reader().readShort();
            byte idbuff = msg.reader().readByte();
            Actor ac = GameCanvas.gameScr.findActor(idchar, 0);
            if (ac != null && ac != GameScr.mainChar && (skill = (SkillTemplate)(vec_skill = (mVector)GameCanvas.gameScr.ALL_SKILL.elementAt(ac.getClazz())).elementAt(idbuff)) != null) {
                ac.startBuff(skill.idEffStartSkill);
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void Set_XP(Message msg) {
        try {
            short idObj = msg.reader().readShort();
            Actor obj = GameCanvas.gameScr.findActor(idObj, 0);
            if (obj != null) {
                short lv = msg.reader().readShort();
                int xpPaint = msg.reader().readInt();
                if (obj.equals(GameScr.mainChar)) {
                    GameCanvas.gameScr.inDexfont = 2;
                    GameCanvas.gameScr.tickpaintFont = 15;
                    String text = msg.reader().readUTF();
                    obj.setlvPercent(lv);
                    GameCanvas.gameScr.textinfomainChar = text;
                }
                if (!obj.equals(GameScr.mainChar)) {
                    GameScr.addFlyText("+" + xpPaint + "exp", obj.getX(), obj.getY() - 40, 3, true);
                }
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public String getPercent(int percent) {
        return String.valueOf(percent / 10) + "." + percent % 10 + "%";
    }

    public void Level_Up(Message msg) {
        try {
            short idObj = msg.reader().readShort();
            Actor obj = GameCanvas.gameScr.findActor(idObj, 0);
            if (obj != null) {
                byte lv = msg.reader().readByte();
                obj.setLV(lv);
                obj.addEffectSkillTime(47, obj.x, obj.y, 0L);
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void onSkillInfo(Message msg) {
        GameCanvas.gameScr.ALL_SKILL.removeAllElements();
        try {
            int j;
            SkillTemplate skill;
            int nclass = msg.reader().readByte();
            int i = 0;
            while (i < nclass) {
                mVector skinfo = new mVector();
                GameCanvas.gameScr.ALL_SKILL.addElement(skinfo);
                int nskill = msg.reader().readByte();
                int k = 0;
                while (k < nskill) {
                    skill = new SkillTemplate();
                    skill.idSkillCode = msg.reader().readShort();
                    skill.idLastEff = msg.reader().readShort();
                    skill.idArrow = msg.reader().readShort();
                    skill.idTailMagic = msg.reader().readShort();
                    skill.idEffStartSkill = msg.reader().readShort();
                    String[] data = Util.split(msg.reader().readUTF(), ",");
                    skill.idArrowTool = new short[data.length];
                    j = 0;
                    while (j < data.length) {
                        skill.idArrowTool[j] = Short.parseShort(data[j]);
                        ++j;
                    }
                    skinfo.addElement(skill);
                    ++k;
                }
                ++i;
            }
            byte myCLass = msg.reader().readByte();
            GameCanvas.gameScr.charclass = myCLass;
            mVector mySkill = (mVector)GameCanvas.gameScr.ALL_SKILL.elementAt(myCLass);
            int nlevel = 0;
            int i2 = 0;
            while (i2 < mySkill.size()) {
                skill = (SkillTemplate)mySkill.elementAt(i2);
                skill.name = msg.reader().readUTF();
                skill.type = msg.reader().readByte();
                skill.iconId = msg.reader().readShort();
                skill.range = msg.reader().readShort();
                skill.decription = msg.reader().readUTF();
                nlevel = msg.reader().readByte();
                skill.lvRequire = new byte[nlevel];
                msg.reader().read(skill.lvRequire);
                if (skill.type != SkillTemplate.TYPE_PASSIVE) {
                    skill.mp = new short[nlevel];
                    skill.nTarget = new short[nlevel];
                    if (skill.type == SkillTemplate.TYPE_BUFF) {
                        skill.timeLive = new int[nlevel];
                    }
                    int j2 = 0;
                    while (j2 < nlevel) {
                        skill.mp[j2] = msg.reader().readShort();
                        skill.nTarget[j2] = msg.reader().readShort();
                        if (skill.type == SkillTemplate.TYPE_BUFF) {
                            skill.timeLive[j2] = msg.reader().readInt();
                        }
                        ++j2;
                    }
                }
                byte nOption = msg.reader().readByte();
                j = 0;
                while (j < nOption) {
                    OptionSkill op = new OptionSkill();
                    op.id = msg.reader().readShort();
                    int len = msg.reader().readByte();
                    op.value = new int[len][];
                    int k = 0;
                    while (k < len) {
                        op.value[k] = new int[nlevel];
                        int m = 0;
                        while (m < nlevel) {
                            op.value[k][m] = msg.reader().readInt();
                            ++m;
                        }
                        ++k;
                    }
                    skill.options.addElement(op);
                    ++j;
                }
                skill.coolDown = new int[nlevel];
                j = 0;
                while (j < nlevel) {
                    skill.coolDown[j] = msg.reader().readInt();
                    ++j;
                }
                GameScr.vec_skill = (mVector)GameCanvas.gameScr.ALL_SKILL.elementAt(GameCanvas.gameScr.charclass);
                ++i2;
            }
            if (GameScr.isIntro) {
                Char.levelSkill = new byte[GameScr.vec_skill.size()];
                i2 = 0;
                while (i2 < Char.levelSkill.length) {
                    Char.levelSkill[i2] = 1;
                    ++i2;
                }
                MainChar.putSkill2QuickSlot();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onMosterDie_(Message msg) {
        try {
            short idattacker = msg.reader().readShort();
            short idattacked = msg.reader().readShort();
            Actor ac = GameCanvas.gameScr.findCharByID(idattacker);
            Monster mt = GameCanvas.gameScr.findMonsterByID(idattacked);
            if (mt != null) {
                if (mt.idTemplate == 88) {
                    GameScr.Ghost = null;
                    GameScr.isGost = false;
                    GameCanvas.gameScr.actors.removeElement(mt);
                    GameCanvas.gameScr.focusedActor = null;
                } else {
                    if (GameCanvas.gameScr.focusedActor != null && mt.equals(GameCanvas.gameScr.focusedActor)) {
                        GameCanvas.gameScr.focusedActor = null;
                    }
                    mt.state = (byte)5;
                    mt.setTickDie(10);
                }
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void onMonsterTemplate(Message msg) {
        try {
            int nmonster = msg.reader().readShort();
            int i = 0;
            while (i < nmonster) {
                String name = msg.reader().readUTF();
                short idtemplate = msg.reader().readShort();
                short idImg = msg.reader().readShort();
                byte typemove = msg.reader().readByte();
                short idEff = msg.reader().readShort();
                byte idShadow = msg.reader().readByte();
                MonsterTemplate monsterTemplate = new MonsterTemplate(idtemplate, name, typemove, idImg, idEff, idShadow);
                int allSkill = msg.reader().readByte();
                monsterTemplate.fAttack = new byte[allSkill][][];
                int j = 0;
                while (j < allSkill) {
                    int allWay = msg.reader().readByte();
                    monsterTemplate.fAttack[j] = new byte[allWay][];
                    int k = 0;
                    while (k < allWay) {
                        monsterTemplate.fAttack[j][k] = new byte[msg.reader().readByte()];
                        msg.reader().read(monsterTemplate.fAttack[j][k]);
                        ++k;
                    }
                    ++j;
                }
                MonsterTemplate.ALL_MONSTER_TEMPLATE.put("" + idtemplate, monsterTemplate);
                ++i;
            }
            nmonster = msg.reader().readByte();
            GameScr.ID_BOSS = new mHashtable();
            GameScr.ALL_SKILL_TEMPLATE_BOSS = new mHashtable();
            i = 0;
            while (i < nmonster) {
                short idBoss = msg.reader().readShort();
                GameScr.ID_BOSS.put(String.valueOf(idBoss), new Short(idBoss));
                int totalSkill = msg.reader().readByte();
                int j = 0;
                while (j < totalSkill) {
                    short idSkill = msg.reader().readShort();
                    int nAnim = msg.reader().readByte();
                    byte[][] arrayFrameAnimUp = new byte[nAnim][];
                    byte[][] arrayFrameAnimDown = new byte[nAnim][];
                    int k = 0;
                    while (k < nAnim) {
                        int nFrame = msg.reader().readByte();
                        arrayFrameAnimUp[k] = new byte[nFrame];
                        arrayFrameAnimDown[k] = new byte[nFrame];
                        int l = 0;
                        while (l < nFrame) {
                            arrayFrameAnimUp[k][l] = msg.reader().readByte();
                            ++l;
                        }
                        l = 0;
                        while (l < nFrame) {
                            arrayFrameAnimDown[k][l] = msg.reader().readByte();
                            ++l;
                        }
                        ++k;
                    }
                    SkillBossTemplate skillTem = new SkillBossTemplate();
                    skillTem.arrayAnimAttackUp = arrayFrameAnimUp;
                    skillTem.arrayAnimAttackDown = arrayFrameAnimDown;
                    GameScr.ALL_SKILL_TEMPLATE_BOSS.put(String.valueOf(idSkill), skillTem);
                    ++j;
                }
                ++i;
            }
            BauCuaManager.gI().createGame();
            System.out.println("onMonsterTemplate-- DONE");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onCharWearing(Message msg) {
        block12: {
            try {
                short id = msg.reader().readShort();
                Actor ac = GameCanvas.gameScr.findActor(id, 0);
                if (ac == null) {
                    ac = new Char();
                    if (GameScr.mainChar != null && id != GameScr.mainChar.ID) {
                        ac.ID = id;
                    }
                }
                if (GameScr.mainChar != null && id == GameScr.mainChar.ID) {
                    ac = GameScr.mainChar;
                }
                if (ac == null) break block12;
                short[] listpart = new short[msg.reader().readByte()];
                int i = 0;
                while (i < listpart.length) {
                    listpart[i] = msg.reader().readShort();
                    ++i;
                }
                ac.setInfoWearing(listpart);
                int len = msg.reader().readByte();
                Item[] it = new Item[len];
                int i2 = 0;
                while (i2 < len) {
                    it[i2] = null;
                    byte iditem = msg.reader().readByte();
                    if (iditem > -1) {
                        it[i2] = new Item();
                        it[i2].ID = iditem;
                        it[i2].charClazz = msg.reader().readByte();
                        it[i2].level = msg.reader().readShort();
                        it[i2].name = msg.reader().readUTF();
                        it[i2].plus = msg.reader().readByte();
                        it[i2].idIcon = msg.reader().readShort();
                        it[i2].colorname = msg.reader().readByte();
                        it[i2].setTypeItem(msg.reader().readByte());
                        int sizeOption = msg.reader().readByte();
                        it[i2].options = new mVector();
                        int j = 0;
                        while (j < sizeOption) {
                            ItemOption itop = new ItemOption();
                            itop.id = msg.reader().readShort();
                            itop.idColor = msg.reader().readByte();
                            itop.value = msg.reader().readInt();
                            itop.value2 = msg.reader().readInt();
                            it[i2].options.addElement(itop);
                            ++j;
                        }
                        it[i2].type = msg.reader().readByte();
                        it[i2].numKhamNgoc = msg.reader().readByte();
                        it[i2].idNgocKham = new int[it[i2].numKhamNgoc];
                        for (int k = 0; k < it[i2].numKhamNgoc; k++) {
                            it[i2].idNgocKham[k] = msg.reader().readInt();
                        }
                    }
                    ++i2;
                }
                String name = "";
                int hp = 100;
                int maxhp = 100;
                int mp = 100;
                int maxmp = 100;
                try {
                    name = msg.reader().readUTF();
                    hp = msg.reader().readInt();
                    maxhp = msg.reader().readInt();
                    mp = msg.reader().readInt();
                    maxmp = msg.reader().readInt();
                    ac.setName(name);
                    ac.setHp(hp);
                    ac.setMP(mp);
                    ac.setMaxHP(maxhp);
                    ac.setMaxMP(maxmp);
                }
                catch (Exception e) {
                    name = "";
                    hp = 100;
                    maxhp = 100;
                    maxmp = 100;
                    mp = 100;
                }
                ac.setEquipChar(it);
                if (id != GameScr.mainChar.ID) {
                    InfoOtherCharScr.charFocus = (Char)ac;
                    InfoOtherCharScr.gI().switchToMe(GameCanvas.gameScr);
                    GameCanvas.endDlg();
                }
                ac.setEffWeapon(msg.reader().readShort());
                try{
                    InfoOtherCharScr.gI().options.removeAllElements();
                    int nOption = msg.reader().readByte();
                    i = 0;
                    while (i < nOption) {
                        ItemOption itop = new ItemOption();
                        itop.id = msg.reader().readShort();
                        itop.idColor = msg.reader().readByte();
                        itop.value = msg.reader().readInt();
                        itop.value2 = msg.reader().readInt();
                        InfoOtherCharScr.gI().options.addElement(itop);
                        ++i;
                    }
                    // Sort options by idColor
                    for (int a = 0; a < InfoOtherCharScr.gI().options.size() - 1; a++) {
                        for (int b = a + 1; b < InfoOtherCharScr.gI().options.size(); b++) {
                            ItemOption option1 = (ItemOption) InfoOtherCharScr.gI().options.elementAt(a);
                            ItemOption option2 = (ItemOption) InfoOtherCharScr.gI().options.elementAt(b);
                            if (option1.getColorPaint(false) > option2.getColorPaint(false)) {
                                InfoOtherCharScr.gI().options.setElementAt(option2, a);
                                InfoOtherCharScr.gI().options.setElementAt(option1, b);
                            }
                        }
                    }
                } catch (Exception e) {

                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void onItemTemplate(Message msg) {
        try {
            int nitem = msg.reader().readShort();
            int i = 0;
            while (i < nitem) {
                short id = msg.reader().readShort();
                String name = msg.reader().readUTF();
                String decript = msg.reader().readUTF();
                byte cat = msg.reader().readByte();
                String nameMoney = msg.reader().readUTF();
                byte gender = msg.reader().readByte();
                byte type = msg.reader().readByte();
                ItemTemplate item = new ItemTemplate();
                item.id = id;
                item.name = name;
                item.decript = decript;
                item.cat = cat;
                item.namemoney = nameMoney;
                item.gender = gender;
                item.type = type;
                ItemTemplate.ALL_ITEM_TEMPLATE.put(String.valueOf(id), item);
                ++i;
            }
            nitem = msg.reader().readShort();
            System.out.println("so luong ten: " + nitem);
            ItemTemplate.ALL_NAME_ATTRIBUTE_ITEM.removeAllElements();
            i = 0;
            while (i < nitem) {
                NameAttributeItem name = new NameAttributeItem(i, msg.reader().readUTF(), msg.reader().readByte());
                name.idColor = msg.reader().readByte();
                ItemTemplate.ALL_NAME_ATTRIBUTE_ITEM.addElement(name);
                ++i;
            }
            int sizeauto = msg.reader().readByte();
            GameScr.infoAutoGetItem = new String[sizeauto];
            MenuSelectItem.FocusAutoNhat = new boolean[sizeauto];
            MenuSelectItem.isAutoNhat = new boolean[sizeauto];
            int i2 = 0;
            while (i2 < sizeauto) {
                GameScr.infoAutoGetItem[i2] = msg.reader().readUTF();
                ++i2;
            }
            GameScr.TIME_BETWEEN_ATTACK = new short[5];
            i2 = 0;
            while (i2 < GameScr.TIME_BETWEEN_ATTACK.length) {
                GameScr.TIME_BETWEEN_ATTACK[i2] = msg.reader().readShort();
                ++i2;
            }
            int sizeEffinfo = msg.reader().readShort();
            int i3 = 0;
            while (i3 < sizeEffinfo) {
                short id = msg.reader().readShort();
                byte pos = msg.reader().readByte();
                GameScr.hashEffInfo.put(String.valueOf(id), String.valueOf(pos));
                ++i3;
            }
            int size = -1;
            try {
                size = msg.reader().readByte();
            }
            catch (Exception e) {
                size = -1;
            }
            if (size > -1) {
                MainMenu.POS_ITEM_IN_EQUIP = new byte[size];
                int i4 = 0;
                while (i4 < size) {
                    MainMenu.POS_ITEM_IN_EQUIP[i4] = msg.reader().readByte();
                    ++i4;
                }
            }
            byte index12Plus = 0;
            try {
                index12Plus = msg.reader().readByte();
            }
            catch (Exception e) {
                index12Plus = 0;
            }
            if (index12Plus == 0) {
                GameCanvas.gameScr.ispaint12Plus = false;
            }
            if (index12Plus == 1) {
                GameCanvas.gameScr.ispaint12Plus = true;
            }
            int totalImgRmsRemove = msg.reader().readShort();
            int i5 = 0;
            while (i5 < totalImgRmsRemove) {
                Rms.DeleteRMS(String.valueOf(msg.reader().readInt()));
                ++i5;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onByteDataServer(Message msg) {
        try {
            EffectData eff;
            int idData = msg.reader().readUnsignedShort();
            byte[] arrayImg1 = new byte[msg.reader().available()];
            msg.reader().read(arrayImg1);
            if (idData < 1000) {
                MonsterTemplate mons = (MonsterTemplate)MonsterTemplate.ALL_MONSTER_TEMPLATE.get(String.valueOf(idData));
                mons.setDataMonster(arrayImg1);
                Rms.saveRMS("monst" + idData, arrayImg1);
            } else if ((idData < Res.ID_ITEM_MAP || idData >= Res.ID_START_SKILL) && (eff = (EffectData)GameData.listbyteData.get("" + idData)) != null) {
                eff.setdata(arrayImg1);
                Rms.saveRMS("eff" + idData, arrayImg1);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onAlertPopUp(Message msg) {
        try {
            String message = msg.reader().readUTF();
            String url = msg.reader().readUTF();
            if (url.equals("")) {
                GameCanvas.startOKDlg(message);
            } else {
                GameCanvas.startOKDlg(message, url);
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void onLoginSuccess(Message msg) {
        try {
            ServerListScr.addressSave = Session_ME.host;
            ServerListScr.portSave = (short)Session_ME.port;
            Rms.saveRMSString("portSave", String.valueOf(ServerListScr.portSave));
            Rms.saveRMSString("addressSave", ServerListScr.addressSave);
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void onDynamicEffect(Message msg) {
        try {
            byte type = msg.reader().readByte();
            short idEff = msg.reader().readShort();
            short idactor = msg.reader().readShort();
            int timenew = msg.reader().readInt();
            long timeExist = (long)timenew + (timenew > 0 ? System.currentTimeMillis() : 0L);
            short x = msg.reader().readShort();
            short y = msg.reader().readShort();
            byte cat = msg.reader().readByte();
            byte top_bottom = msg.reader().readByte();
            byte stop = msg.reader().readByte();
            byte add_remove = msg.reader().readByte();
            int dame = msg.reader().readInt();
            byte loop = msg.reader().readByte();
            byte canPaintActor = msg.reader().readByte();
            byte typeFight = msg.reader().readByte();
            byte Waitloop = 0;
            try {
                Waitloop = msg.reader().readByte();
            }
            catch (Exception e) {
                Waitloop = 0;
            }
            Actor ac = GameCanvas.gameScr.findActor(idactor, cat);
            boolean canmove = false;
            int colordame = 2;
            if (cat == 0) {
                colordame = 1;
            }
            if (dame > 0 && ac != null) {
                GameCanvas.gameScr.startFlyText("-" + dame, colordame, ac.x, ac.y - ac.getHeight(), 1, -2);
            }
            if (stop == 1) {
                canmove = true;
            }
            boolean canpaint = false;
            if (canPaintActor == 0) {
                canpaint = true;
            }
            boolean canFight = false;
            if (typeFight == 0) {
                canFight = true;
            }
            if (type == 0) {
                DataSkillEff eff = new DataSkillEff((int)idEff, (int)x, (int)y, (long)loop);
                eff.isEffAuto = true;
                eff.setWaitLoop(Waitloop);
                if (top_bottom == 0) {
                    GameCanvas.gameScr.actors.addElement(eff);
                } else {
                    EffectManager.addHiEffect(eff);
                }
            } else if (type == 2) {
                DataSkillEff eff = new DataSkillEff((int)idEff, (int)x, (int)y, timeExist, loop);
                eff.isEffAuto = true;
                eff.setWaitLoop(Waitloop);
                if (top_bottom == 0) {
                    GameCanvas.gameScr.actors.addElement(eff);
                } else {
                    EffectManager.addHiEffect(eff);
                }
            }
            if (type == 1 && ac != null) {
                ac.addEffectSkillTime(idEff, x, y, timeExist, canmove, canpaint, canFight, loop, Waitloop);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onUpdateHP_MP(Message msg) {
        try {
            short id = msg.reader().readShort();
            long hp = msg.reader().readLong();
            int mp = msg.reader().readInt();
            int hpChange = msg.reader().readInt();
            int mpChange = msg.reader().readInt();
            byte cat = msg.reader().readByte();
            byte idColor = msg.reader().readByte();
            String infoShow = msg.reader().readUTF();
            Actor ac = GameCanvas.gameScr.findActor(id, cat);
            int idimgFly = -1;
            try {
                idimgFly = msg.reader().readByte();
            }
            catch (Exception e) {
                idimgFly = -1;
            }
            if (idimgFly != -1 && ac != null) {
                if (!ac.equals(GameScr.mainChar) && !GameCanvas.menuSelectitem.isTabFocus[2]) {
                    return;
                }
                if (idimgFly == 0) {
                    GameScr.addFlyText("", ac.x, ac.y - 35, 8, true);
                } else if (idimgFly == 1) {
                    GameScr.addFlyText("", ac.x, ac.y - 35, 9, true);
                } else if (idimgFly == 2) {
                    GameScr.addFlyText("", ac.x, ac.y - 35, 10, true);
                } else if (idimgFly == 3) {
                    GameScr.addFlyText("", ac.x, ac.y - 35, 11, true);
                }
            }
            if (ac != null) {
                ac.setHp(hp);
                ac.setMP(mp);
                if (!ac.equals(GameScr.mainChar) && !GameCanvas.menuSelectitem.isTabFocus[2]) {
                    return;
                }
                if (hpChange > 0) {
                    GameScr.addFlyText("+" + hpChange, ac.x, ac.y - 35, 7, true);
                } else if (hpChange < 0) {
                    GameScr.addFlyText("" + hpChange, ac.x, ac.y - 35, 1, true);
                }
                if (mpChange > 0) {
                    GameScr.addFlyText("+" + mpChange, ac.x, ac.y - 35, 6, true);
                } else if (mpChange < 0) {
                    GameScr.addFlyText("" + mpChange, ac.x, ac.y - 35, 5, true);
                }
                if (!infoShow.equals("")) {
                    GameScr.addFlyText(infoShow, ac.x, ac.y - 35, idColor, true);
                }
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void onChat(Message msg) {
        try {
            String name = msg.reader().readUTF();
            String info = msg.reader().readUTF();
            byte typeShow = msg.reader().readByte();
            String nameOther = msg.reader().readUTF();
            if (!nameOther.equals("")) {
                if (!GameCanvas.msgchat.isShow) {
                    GameCanvas.mevent.addEvent(nameOther, (byte)0, String.valueOf(name) + ": " + T.tinden, 0);
                }
                GameCanvas.msgchat.addNewChat(nameOther, String.valueOf(name) + ": ", info, (byte)0, false);
                if (GameCanvas.msgchat != null && !GameCanvas.msgchat.isShow && GameScr.numMSG <= 0) {
                    GameScr.numMSG = (byte)(GameScr.numMSG + 1);
                }
            } else {
                if (!GameCanvas.msgchat.isShow) {
                    GameCanvas.mevent.addEvent(name, (byte)0, T.tinden, 0);
                }
                GameCanvas.msgchat.addNewChat(name, String.valueOf(name) + ": ", info, (byte)0, false);
                if (GameCanvas.msgchat != null && !GameCanvas.msgchat.isShow && GameScr.numMSG <= 0) {
                    GameScr.numMSG = (byte)(GameScr.numMSG + 1);
                }
                if (typeShow != 0 && typeShow == 1) {
                    GameCanvas.start_Chat_Dialog();
                }
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void changPK(Message msg) {
        try {
            short id = msg.reader().readShort();
            byte cat = msg.reader().readByte();
            byte typePK = msg.reader().readByte();
            Actor ac = GameCanvas.gameScr.findActor(id, cat);
            if (ac != null) {
                ac.setTypePK(typePK);
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    // ========== BẦU CUA MESSAGE HANDLERS ==========
    
    public void onBauCuaRoomInfo(Message msg) {
        try {
            int roomId = msg.reader().readInt();
            int gameState = msg.reader().readByte();
            long stateStartTime = msg.reader().readLong();
            
            // Đọc danh sách players
            int playerCount = msg.reader().readByte();
            
            BauCua bauCuaScreen = null;
            
            // Kiểm tra xem BauCua UI đã mở chưa
            if (GameCanvas.currentScreen instanceof BauCua) {
                bauCuaScreen = (BauCua) GameCanvas.currentScreen;
            } else {
                // Chưa mở UI - tự động mở BauCua UI
                bauCuaScreen = BauCuaUsageExample.openBauCuaUI();
                System.out.println("Auto-opened BauCua UI from server room info");
            }
            
            if (bauCuaScreen != null) {
                
                // Khởi tạo hoặc cập nhật room info
                if (bauCuaScreen.currentRoom == null) {
                    // Lần đầu - tạo room mới
                    bauCuaScreen.currentRoom = new BauCuaRoom(roomId);
                    System.out.println("Created new BauCua room with ID: " + roomId);
                }
                
                // Cập nhật room info
                bauCuaScreen.currentRoom.roomId = roomId;
                bauCuaScreen.currentRoom.gameState = gameState;
                bauCuaScreen.currentRoom.stateStartTime = stateStartTime;
                bauCuaScreen.currentRoom.players.removeAllElements();
                
                // Đọc thông tin từng player
                for (int i = 0; i < playerCount; i++) {
                    int playerId = msg.reader().readInt();
                    String playerName = msg.reader().readUTF();
                    boolean isReady = msg.reader().readBoolean();
                    boolean isRoomOwner = msg.reader().readBoolean();
                    long totalBet = msg.reader().readLong();
                    
                    // Đọc cược cho từng con vật
                    long[] bets = new long[6];
                    for (int j = 0; j < 6; j++) {
                        bets[j] = msg.reader().readLong();
                    }
                    
                    // Tạo BauCuaPlayer với thông tin từ server
                    BauCuaPlayer player = null;
                    
                    if (playerId == GameScr.mainChar.ID) {
                        // Đây là main character - tạo hoặc cập nhật myPlayer
                        if (bauCuaScreen.myPlayer == null) {
                            bauCuaScreen.myPlayer = new BauCuaPlayer(GameScr.mainChar, playerId);
                        }
                        player = bauCuaScreen.myPlayer;
                    } else {
                        // Tạo player khác với thông tin từ server
                        Char character = new Char();
                        character.name = playerName;
                        character.ID = (short) playerId;
                        player = new BauCuaPlayer(character, playerId);
                    }
                    player.setPlayerIndex(i);
                    short[] listpart = new short[msg.reader().readByte()];
                    int k = 0;
                    while (k < listpart.length) {
                        listpart[k] = msg.reader().readShort();
                        ++k;
                    }
                    int len = msg.reader().readByte();
                    Item[] it = new Item[len];
                    int i2 = 0;
                    while (i2 < len) {
                        it[i2] = null;
                        byte iditem = msg.reader().readByte();
                        if (iditem > -1) {
                            it[i2] = new Item();
                            it[i2].ID = iditem;
                            it[i2].charClazz = msg.reader().readByte();
                            it[i2].level = msg.reader().readShort();
                            it[i2].name = msg.reader().readUTF();
                            it[i2].plus = msg.reader().readByte();
                            it[i2].idIcon = msg.reader().readShort();
                            it[i2].colorname = msg.reader().readByte();
                            it[i2].setTypeItem(msg.reader().readByte());
                            int sizeOption = msg.reader().readByte();
                            it[i2].options = new mVector();
                            int j = 0;
                            while (j < sizeOption) {
                                ItemOption itop = new ItemOption();
                                itop.id = msg.reader().readShort();
                                itop.idColor = msg.reader().readByte();
                                itop.value = msg.reader().readInt();
                                itop.value2 = msg.reader().readInt();
                                it[i2].options.addElement(itop);
                                ++j;
                            }
                            it[i2].type = msg.reader().readByte();
                        }
                        ++i2;
                    }
                    long xu = msg.reader().readLong();
                    
                    // Cập nhật thông tin player từ server
                    if (player != null) {
                        player.character.setInfoWearing(listpart);
                        player.character.charXu = xu;
                        player.character.setEquipChar(it);
                        player.playerId = playerId;
                        player.isReady = isReady;
                        player.isRoomOwner = isRoomOwner;
                        player.totalBet = totalBet;
                        player.bets = bets;
                        
                        // Sync betCounts từ bets array
                        player.syncBetCounts(bauCuaScreen.currentRoom.defaultBetAmount);
                        
                        bauCuaScreen.currentRoom.players.addElement(player);
                    }
                }
                
                // Clear preview bets sau khi server đã update betting info
                bauCuaScreen.clearPreviewAfterServerUpdate();
                
                // Xử lý chuyển đổi game state
                switch (gameState) {
                    case BauCuaRoom.STATE_ROLLING:
                        // Bắt đầu lăn xúc xắc khi server gửi state ROLLING
                        if (!bauCuaScreen.isDiceRolling) {
                            bauCuaScreen.startDiceRoll();
                            bauCuaScreen.isDiceRolling = true;
                        }
                        break;
                    case BauCuaRoom.STATE_RESULT:
                        // Dừng lăn xúc xắc khi có kết quả
                        bauCuaScreen.isDiceRolling = false;
                        break;
                    case BauCuaRoom.STATE_WAITING:
                    case BauCuaRoom.STATE_BETTING:
                        // Đảm bảo xúc xắc không lăn ở các state khác
                        bauCuaScreen.isDiceRolling = false;
                        break;
                }
                
                System.out.println("BauCua room info updated - Room ID: " + roomId + 
                                 ", State: " + gameState + 
                                 ", Players: " + playerCount);
            }
        } catch (Exception e) {
            System.out.println("Error processing BauCua room info: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void onBauCuaDiceResult(Message msg) {
        try {
            int[] diceResults = new int[3];
            diceResults[0] = msg.reader().readByte();
            diceResults[1] = msg.reader().readByte();
            diceResults[2] = msg.reader().readByte();
            byte size = msg.reader().readByte();
            long[][][] bets = new long[size][6][2];
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < 6; j++) {
                    bets[i][j][0] = msg.reader().readLong(); // bet amount
                    bets[i][j][1] = msg.reader().readLong(); // bet count
                }
            }
            
            if (GameCanvas.currentScreen instanceof BauCua) {
                BauCua bauCuaScreen = (BauCua) GameCanvas.currentScreen;
                
                // Cập nhật kết quả xúc xắc từ server
                bauCuaScreen.finalDiceResults = diceResults;
                if (bauCuaScreen.currentRoom != null) {
                    bauCuaScreen.currentRoom.diceResults = diceResults;
                    
                    // Chuyển sang trạng thái hiển thị kết quả
                    bauCuaScreen.currentRoom.gameState = BauCuaRoom.STATE_RESULT;
                    bauCuaScreen.currentRoom.stateStartTime = System.currentTimeMillis();
                }
                
                // Dừng animation lăn xúc xắc
                bauCuaScreen.isDiceRolling = false;
                bauCuaScreen.updateResuilts(bets);
                
                System.out.println("Nhận kết quả từ server: " + 
                    diceResults[0] + ", " + diceResults[1] + ", " + diceResults[2]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void onBauCuaChat(Message msg) {
        try {
            String playerName = msg.reader().readUTF();
            String message = msg.reader().readUTF();
            
            if (GameCanvas.currentScreen instanceof BauCua) {
                BauCua bauCuaScreen = (BauCua) GameCanvas.currentScreen;
                
                // Tìm player và set chat message
                if (bauCuaScreen.currentRoom != null) {
                    for (int i = 0; i < bauCuaScreen.currentRoom.players.size(); i++) {
                        BauCuaPlayer player = (BauCuaPlayer) bauCuaScreen.currentRoom.players.elementAt(i);
                        if (player.character != null && player.character.name.equals(playerName)) {
                            player.setChat(message);
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    


    public void Add_Friend(Message msg) {
        try {
            byte type = msg.reader().readByte();
            if (type == 0) {
                String name = msg.reader().readUTF();
                GameCanvas.mevent.addEvent(name, (byte)1, T.loimoikb, 0);
                GameScr.numMSG = (byte)(GameScr.numMSG + 1);
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void ThacDau(Message msg) {
        try {
            short id = msg.reader().readShort();
            String text = msg.reader().readUTF();
            int price = msg.reader().readInt();
            GameCanvas.mevent.addEvent(text, (byte)4, T.loimoiThachdau, price, id);
            GameScr.numMSG = (byte)(GameScr.numMSG + 1);
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void List_info(Message msg) {
        try {
            byte type = msg.reader().readByte();
            String title = msg.reader().readUTF();
            int size = msg.reader().readShort();
            if (type == 0) {
                mVector listChar = new mVector();
                int i = 0;
                while (i < size) {
                    Char cF = new Char();
                    cF.idiConList = msg.reader().readShort();
                    cF.name = msg.reader().readUTF();
                    int lv = msg.reader().readUnsignedByte();
                    cF.level = (short)lv;
                    cF.luong = msg.reader().readInt();
                    cF.xu = msg.reader().readLong();
                    cF.online = msg.reader().readByte();
                    String option = msg.reader().readUTF();
                    if (type == 1) {
                        byte by = msg.reader().readByte();
                    }
                    short[] listpart = new short[msg.reader().readByte()];
                    int j = 0;
                    while (j < listpart.length) {
                        listpart[j] = msg.reader().readShort();
                        ++j;
                    }
                    cF.setInfoWearing(listpart);
                    listChar.addElement(cF);
                    ++i;
                }
                int nAction = msg.reader().readByte();
                String[] listTextcmd = new String[nAction];
                int i2 = 0;
                while (i2 < nAction) {
                    listTextcmd[i2] = msg.reader().readUTF();
                    ++i2;
                }
                GameCanvas.menu2.startArt(listChar, (int)type, title, listTextcmd);
            } else if (type == 1) {
                mVector listKhu = new mVector();
                int i = 0;
                while (i < size) {
                    String khu = msg.reader().readUTF();
                    byte index = msg.reader().readByte();
                    listKhu.addElement(new Khu((byte)i, index, khu));
                    ++i;
                }
                int nAction = msg.reader().readByte();
                String[] listTextcmd = new String[nAction];
                int i3 = 0;
                while (i3 < nAction) {
                    listTextcmd[i3] = msg.reader().readUTF();
                    ++i3;
                }
                GameCanvas.menu2.startArt(listKhu, (int)type, title, listTextcmd);
            } else if (type == 2) {
                mVector listChar = new mVector();
                int i = 0;
                while (i < size) {
                    Char cF = new Char();
                    cF.idiConList = msg.reader().readShort();
                    cF.name = msg.reader().readUTF();
                    int lv = msg.reader().readUnsignedByte();
                    cF.level = (short)lv;
                    cF.luong = msg.reader().readInt();
                    cF.xu = msg.reader().readLong();
                    cF.online = msg.reader().readByte();
                    int rank = msg.reader().readInt();
                    short[] listpart = new short[msg.reader().readByte()];
                    int j = 0;
                    while (j < listpart.length) {
                        listpart[j] = msg.reader().readShort();
                        ++j;
                    }
                    cF.setInfoWearing(listpart);
                    listChar.addElement(cF);
                    ++i;
                }
                int nAction = msg.reader().readByte();
                String[] listTextcmd = new String[nAction];
                int i4 = 0;
                while (i4 < nAction) {
                    listTextcmd[i4] = msg.reader().readUTF();
                    ++i4;
                }
                GameCanvas.menu2.startArt(listChar, (int)type, title, listTextcmd);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onListParty(Message msg) {
        try {
            byte type = msg.reader().readByte();
            switch (type) {
                case 0: {
                    mVector ListParty = new mVector();
                    String masterName = msg.reader().readUTF();
                    int size = msg.reader().readByte();
                    int i = 0;
                    while (i < size) {
                        Info_Party info = new Info_Party();
                        info.nameMember = msg.reader().readUTF();
                        if (info.nameMember.equals(masterName)) {
                            info.isMaster = true;
                        }
                        info.menberLv = msg.reader().readShort();
                        info.nameMap = msg.reader().readUTF();
                        info.menberArena = msg.reader().readByte();
                        ListParty.addElement(info);
                        ++i;
                    }
                    GameCanvas.menu2.startArtParty(ListParty, 3, T.nhom1, masterName);
                    GameCanvas.gameScr.hideGUI = 0;
                    break;
                }
                case 1: {
                    String invite_CharName = msg.reader().readUTF();
                    GameCanvas.mevent.addEvent(invite_CharName, (byte)2, T.moivaonhom, 0);
                    GameScr.numMSG = (byte)(GameScr.numMSG + 1);
                    break;
                }
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    @Override
    public void paint(mGraphics g, int idAction, int x, int y, Object paint) {
    }

    public void onCountDown(Message msg) {
        try {
            byte cat = msg.reader().readByte();
            short id = msg.reader().readShort();
            String tile = msg.reader().readUTF();
            int time_ = msg.reader().readInt();
            short idicon = -1;
            byte count = -1;
            try {
                idicon = msg.reader().readShort();
                count = msg.reader().readByte();
            }
            catch (Exception e) {
                idicon = -1;
                count = -1;
            }
            long time = mSystem.currentTimeMillis() + (long)(time_ * 1000);
            if (cat != -1) {
                if (time_ > 0) {
                    GameScr.charcountdonw = new CharCountDown(time, tile);
                }
                if (time_ == 0) {
                    GameScr.charcountdonw = null;
                }
            }
            if (cat == -1 && time_ >= 0) {
                TimecountDown t = this.findTime(id);
                int second = time_;
                if (t == null) {
                    t = new TimecountDown(id, idicon, second, tile, count);
                    GameScr.VecTime.addElement(t);
                }
                if (t != null) {
                    t.tile = tile;
                    t.idIcon = idicon;
                    t.setsecond(second);
                    if (count == -2) {
                        t.wantdestroy = true;
                    }
                }
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public TimecountDown findTime(int id) {
        int i = 0;
        while (i < GameScr.VecTime.size()) {
            TimecountDown t = (TimecountDown)GameScr.VecTime.elementAt(i);
            if (t != null && t.id == id) {
                return t;
            }
            ++i;
        }
        return null;
    }

    public void opentBox(Message msg) {
        try {
            int num = msg.reader().readByte();
            String tile = msg.reader().readUTF();
            mVector vec = new mVector();
            int i = 0;
            while (i < num) {
                Item item = new Item();
                item.idIcon = msg.reader().readShort();
                item.colorname = msg.reader().readByte();
                item.quantity = msg.reader().readInt();
                mSystem.println("so luong item " + item.quantity);
                vec.addElement(item);
                ++i;
            }
            GameCanvas.starBoxDialog(tile, vec);
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void onImgBigmap(Message msg) {
        try {
            byte id = msg.reader().readByte();
            int size = msg.reader().readInt();
            if (size > 0) {
                byte[] data = new byte[size];
                int i = 0;
                while (i < size) {
                    data[i] = msg.reader().readByte();
                    ++i;
                }
                String path = GameCanvas.getPath("bigimgmap" + id + "@" + Tilemap.lv);
                Rms.saveRMS(path, data);
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void onListNPCsell(Message msg) {
        try {
            GameCanvas.gameScr.idNPC_Sell = null;
            int size = msg.reader().readByte();
            GameCanvas.gameScr.idNPC_Sell = new short[size];
            int i = 0;
            while (i < size) {
                GameCanvas.gameScr.idNPC_Sell[i] = msg.reader().readShort();
                ++i;
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void onInfofromserver(Message msg) {
        try {
            GameCanvas.menu2.idIcon = (short)-1;
            mVector vecinfo = new mVector();
            String tile = msg.reader().readUTF();
            int nitem = 0;
            int size = msg.reader().readByte();
            nitem += size;
            int i = 0;
            while (i < size) {
                ServerInfo svif = new ServerInfo();
                svif.tile = msg.reader().readUTF();
                int infosize = msg.reader().readByte();
                nitem += infosize;
                svif.info = new String[infosize];
                int j = 0;
                while (j < infosize) {
                    svif.info[j] = msg.reader().readUTF();
                    ++j;
                }
                vecinfo.addElement(svif);
                ++i;
            }
            GameCanvas.menu2.idIcon = msg.reader().readShort();
            GameCanvas.menu2.startArt(vecinfo, 4, tile, nitem);
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void onBaoTri(Message msg) {
        try {
            String info = msg.reader().readUTF();
            byte time = msg.reader().readByte();
            if (time == -1) {
                MenuLogin.gI().switchToMe();
                GameCanvas.startOKDlg(info);
            } else {
                ChangScr.timenextLogin = mSystem.currentTimeMillis() + (long)(time * 1000);
                Session_ME.gI().close();
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void onGiveItem(Message msg) {
        try {
            int id = msg.reader().readInt();
            String title = msg.reader().readUTF();
            String infoconfirm = msg.reader().readUTF();
            String charname = msg.reader().readUTF();
            Item it0 = new Item();
            it0.ID = msg.reader().readShort();
            it0.catagory = msg.reader().readByte();
            it0.level = msg.reader().readShort();
            it0.charClazz = msg.reader().readByte();
            it0.name = msg.reader().readUTF();
            it0.lock = msg.reader().readByte();
            it0.plus = msg.reader().readByte();
            it0.idIcon = msg.reader().readShort();
            it0.colorname = msg.reader().readByte();
            it0.cantrade = msg.reader().readBoolean();
            it0.cansell = msg.reader().readBoolean();
            it0.priceShop = msg.reader().readInt();
            it0.quantity = msg.reader().readInt();
            int nOption = msg.reader().readByte();
            int j = 0;
            while (j < nOption) {
                ItemOption itoption = new ItemOption();
                itoption.id = msg.reader().readShort();
                itoption.idColor = msg.reader().readByte();
                itoption.value = msg.reader().readInt();
                itoption.value2 = msg.reader().readInt();
                it0.options.addElement(itoption);
                ++j;
            }
            it0.type = msg.reader().readByte();
            it0.numKhamNgoc = msg.reader().readByte();
            it0.idNgocKham = new int[it0.numKhamNgoc];
            for (int k = 0; k < it0.numKhamNgoc; k++) {
                it0.idNgocKham[k] = msg.reader().readInt();
            }
            GameCanvas.mevent.addEvent(charname, (byte)8, T.quatang, 0, id, it0, title, infoconfirm);
            GameScr.numMSG = (byte)(GameScr.numMSG + 1);
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void onCharData(Message msg) {
        try {
            short id;
            Res.VERSION_CHUNK = msg.reader().readByte();
            int chunkLen = msg.reader().readInt();
            byte[] datatemp = null;
            int i = 0;
            while (i < chunkLen) {
                id = msg.reader().readShort();
                short len = msg.reader().readShort();
                byte[] data = new byte[len];
                msg.reader().read(data);
                Res.loadChunkPrivate(data, id);
                if (mSystem.isj2me) {
                    datatemp = data;
                    Res.listGetChunk.remove(String.valueOf(id));
                }
                ++i;
            }
            Char.load();
            i = 0;
            while (i < chunkLen) {
                id = msg.reader().readShort();
                short idBig = msg.reader().readShort();
                Chunk chunk = (Chunk)Chunk.arr.get(String.valueOf(id));
                if (idBig > -1) {
                    chunk.idBig = idBig;
                    if (mSystem.isj2me && chunkLen == 1) {
                        ByteArrayOutputStream bo = new ByteArrayOutputStream();
                        DataOutputStream d = new DataOutputStream(bo);
                        d.writeShort(idBig);
                        d.write(datatemp);
                        Rms.saveRMS("ck" + id, bo.toByteArray());
                        bo.close();
                    }
                }
                ++i;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onShopHair(Message msg) {
        try {
            ShopHairScreen.vecItemHair.removeAllElements();
            int size = msg.reader().readByte();
            int i = 0;
            while (i < size) {
                Item it0 = new Item();
                it0.ID = msg.reader().readShort();
                it0.isItemShop = true;
                it0.name = msg.reader().readUTF();
                it0.level = msg.reader().readShort();
                it0.charClazz = msg.reader().readByte();
                it0.idIcon = msg.reader().readShort();
                it0.colorname = msg.reader().readByte();
                it0.priceShop = msg.reader().readInt();
                it0.priceType = msg.reader().readByte();
                int nOption = msg.reader().readByte();
                int j = 0;
                while (j < nOption) {
                    ItemOption itoption = new ItemOption();
                    itoption.id = msg.reader().readShort();
                    itoption.idColor = msg.reader().readByte();
                    itoption.value = msg.reader().readInt();
                    itoption.value2 = msg.reader().readInt();
                    it0.options.addElement(itoption);
                    ++j;
                }
                it0.type = msg.reader().readByte();
                int partSize = msg.reader().readByte();
                short[] arr = new short[partSize];
                int k = 0;
                while (k < partSize) {
                    arr[k] = msg.reader().readShort();
                    ++k;
                }
                it0.arrPart = arr;
                int ncommand = msg.reader().readByte();
                String[] cap = new String[ncommand];
                int m = 0;
                while (m < ncommand) {
                    cap[m] = msg.reader().readUTF();
                    ++m;
                }
                ShopHairScreen.vecItemHair.addElement(it0);
                ShopHairScreen.gI().switchToMe(GameCanvas.gameScr);
                ++i;
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void onUpgradeItem(Message msg) {
        try {
            byte type = msg.reader().readByte();
            switch (type) {
                case 0: {
                    MainMenu.vecItemDapDo.removeAllElements();
                    short luckypoint = msg.reader().readShort();
                    int size = msg.reader().readUnsignedByte();
                    int i = 0;
                    while (i < size) {
                        Item it0 = new Item();
                        it0.ID = msg.reader().readShort();
                        it0.catagory = msg.reader().readByte();
                        it0.level = msg.reader().readShort();
                        it0.charClazz = msg.reader().readByte();
                        it0.name = msg.reader().readUTF();
                        it0.lock = msg.reader().readByte();
                        it0.plus = msg.reader().readByte();
                        it0.idIcon = msg.reader().readShort();
                        it0.colorname = msg.reader().readByte();
                        it0.cantrade = msg.reader().readBoolean();
                        it0.cansell = msg.reader().readBoolean();
                        it0.priceShop = msg.reader().readInt();
                        it0.quantity = msg.reader().readInt();
                        int nOption = msg.reader().readByte();
                        int j = 0;
                        while (j < nOption) {
                            ItemOption itoption = new ItemOption();
                            itoption.id = msg.reader().readShort();
                            itoption.idColor = msg.reader().readByte();
                            itoption.value = msg.reader().readInt();
                            itoption.value2 = msg.reader().readInt();
                            it0.options.addElement(itoption);
                            ++j;
                        }
                        it0.type = msg.reader().readByte();
                        it0.pos = msg.reader().readByte();
                        it0.numKhamNgoc = msg.reader().readByte();
                        it0.idNgocKham = new int[it0.numKhamNgoc];
                        for (int k = 0; k < it0.numKhamNgoc; k++) {
                            it0.idNgocKham[k] = msg.reader().readInt();
                        }
                        if (it0.pos != -1) {
                            MainMenu.vecMaterial.addElement(it0);
                        }
                        if (!MainMenu.isHopda && MainMenu.mItem != null && MainMenu.mItem.name.equals(it0.name)) {
                            MainMenu.mItem = it0;
                            MainMenu.gI().updateItemDapdo();
                        }
                        MainMenu.vecItemDapDo.addElement(it0);
                        ++i;
                    }
                    MainMenu.isDapdo = true;
                    MainMenu.gI().switchToMe(GameCanvas.gameScr);
                    break;
                }
                case 1: {
                    MainMenu.textPercent = msg.reader().readUTF();
                    MainMenu.xuCuongHoa = msg.reader().readInt();
                    MainMenu.moneyType = msg.reader().readByte();
                    break;
                }
                case 2: {
                    byte result = msg.reader().readByte();
                    MainMenu.gI().Upgraderesult(result);
                    break;
                }
                case 3: {
                    MainMenu.vecItemDapDo.removeAllElements();
                    int size3 = msg.reader().readUnsignedByte();
                    int i = 0;
                    while (i < size3) {
                        Item it0 = new Item();
                        it0.ID = msg.reader().readShort();
                        it0.catagory = msg.reader().readByte();
                        it0.level = msg.reader().readShort();
                        it0.charClazz = msg.reader().readByte();
                        it0.name = msg.reader().readUTF();
                        it0.lock = msg.reader().readByte();
                        it0.plus = msg.reader().readByte();
                        it0.idIcon = msg.reader().readShort();
                        it0.colorname = msg.reader().readByte();
                        it0.cantrade = msg.reader().readBoolean();
                        it0.cansell = msg.reader().readBoolean();
                        it0.priceShop = msg.reader().readInt();
                        it0.quantity = msg.reader().readInt();
                        int nOption = msg.reader().readByte();
                        int j = 0;
                        while (j < nOption) {
                            ItemOption itoption = new ItemOption();
                            itoption.id = msg.reader().readShort();
                            itoption.idColor = msg.reader().readByte();
                            itoption.value = msg.reader().readInt();
                            itoption.value2 = msg.reader().readInt();
                            it0.options.addElement(itoption);
                            ++j;
                        }
                        it0.type = msg.reader().readByte();
                        it0.pos = msg.reader().readByte();
                        it0.numKhamNgoc = msg.reader().readByte();
                        it0.idNgocKham = new int[it0.numKhamNgoc];
                        for (int k = 0; k < it0.numKhamNgoc; k++) {
                            it0.idNgocKham[k] = msg.reader().readInt();
                        }
                        if (it0.pos != -1) {
                            MainMenu.vecMaterial.addElement(it0);
                        }
                        if (!MainMenu.isHopda && MainMenu.mItem != null && MainMenu.mItem.name.equals(it0.name)) {
                            MainMenu.mItem = it0;
                            MainMenu.gI().updateItemDapdo();
                        }
                        MainMenu.vecItemDapDo.addElement(it0);
                        ++i;
                    }
                    MainMenu.isDapdo = true;
                    MainMenu.isHopda = true;
                    MainMenu.gI().switchToMe(GameCanvas.gameScr);
                    break;
                }
                case 4: {
                    MainMenu.textPercent = msg.reader().readUTF();
                    MainMenu.xuCuongHoa = msg.reader().readInt();
                    MainMenu.moneyType = msg.reader().readByte();
                    MainMenu.ID_CUONG_HOA = msg.reader().readShort();
                    break;
                }
                case 5: {
                    int size2 = msg.reader().readByte();
                    MainMenu.vecItemCreate.removeAllElements();
                    int i = 0;
                    while (i < size2) {
                        Item it0 = new Item();
                        it0.ID = msg.reader().readShort();
                        it0.catagory = msg.reader().readByte();
                        it0.level = msg.reader().readShort();
                        it0.charClazz = msg.reader().readByte();
                        it0.name = msg.reader().readUTF();
                        it0.lock = msg.reader().readByte();
                        it0.plus = msg.reader().readByte();
                        it0.idIcon = msg.reader().readShort();
                        it0.colorname = msg.reader().readByte();
                        it0.cantrade = msg.reader().readBoolean();
                        it0.cansell = msg.reader().readBoolean();
                        it0.priceShop = msg.reader().readInt();
                        it0.quantity = msg.reader().readInt();
                        int nOption = msg.reader().readByte();
                        int j = 0;
                        while (j < nOption) {
                            ItemOption itoption = new ItemOption();
                            itoption.id = msg.reader().readShort();
                            itoption.idColor = msg.reader().readByte();
                            itoption.value = msg.reader().readInt();
                            itoption.value2 = msg.reader().readInt();
                            it0.options.addElement(itoption);
                            ++j;
                        }
                        MainMenu.vecItemCreate.addElement(it0);
                        if (MainMenu.isChuyenHoa && MainMenu.itemChuyenHoa0 != null && MainMenu.itemChuyenHoa0.name.equals(it0.name)) {
                            MainMenu.itemChuyenHoa0 = it0;
                        }
                        ++i;
                    }
                    MainMenu.isChuyenHoa = true;
                    MainMenu.gI().switchToMe(GameCanvas.gameScr);
                    break;
                }
                case 6: {
                    MainMenu.gI().ChuyenHoaItemresult();
                    break;
                }
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
            // empty catch block
        }
    }

    public void inviteClan(Message msg) {
        try {
            byte type = msg.reader().readByte();
            switch (type) {
                case -1: {
                    ScreenClan.vecmember.removeAllElements();
                    ScreenClan.infoClan.removeAllElements();
                    ScreenClan.nameCmd = null;
                    ScreenClan.nameOption = null;
                    mVector vecinfo = new mVector();
                    int nitem = 0;
                    int size = msg.reader().readByte();
                    nitem += size;
                    int i = 0;
                    while (i < size) {
                        ServerInfo svif = new ServerInfo();
                        svif.tile = msg.reader().readUTF();
                        int infosize = msg.reader().readByte();
                        nitem += infosize;
                        svif.info = new String[infosize];
                        int j = 0;
                        while (j < infosize) {
                            svif.info[j] = msg.reader().readUTF();
                            ++j;
                        }
                        vecinfo.addElement(svif);
                        ++i;
                    }
                    ScreenClan.iTemSize = nitem;
                    ScreenClan.infoClan = vecinfo;
                    mVector vecmenber = new mVector();
                    int size2 = msg.reader().readUnsignedByte();
                    int i2 = 0;
                    while (i2 < size2) {
                        Char c = new Char();
                        c.name = msg.reader().readUTF();
                        c.lv = (short)msg.reader().readUnsignedByte();
                        c.chucvu = msg.reader().readUTF();
                        c.conghien = msg.reader().readInt();
                        c.online = msg.reader().readByte();
                        c.PartHead = msg.reader().readShort();
                        vecmenber.addElement(c);
                        ++i2;
                    }
                    ScreenClan.vecmember = vecmenber;
                    int sizeCmdName = msg.reader().readByte();
                    ScreenClan.nameCmd = new String[sizeCmdName];
                    int i3 = 0;
                    while (i3 < sizeCmdName) {
                        ScreenClan.nameCmd[i3] = msg.reader().readUTF();
                        ++i3;
                    }
                    int sizeOption = msg.reader().readByte();
                    ScreenClan.nameOption = new String[sizeOption];
                    int i4 = 0;
                    while (i4 < sizeOption) {
                        ScreenClan.nameOption[i4] = msg.reader().readUTF();
                        ++i4;
                    }
                    ScreenClan.gI().switchToMe(GameCanvas.gameScr);
                    break;
                }
                case 0: {
                    break;
                }
                case 1: {
                    String name = msg.reader().readUTF();
                    GameCanvas.mevent.addEvent(name, (byte)5, T.moivaoclan, 0);
                    GameScr.numMSG = (byte)(GameScr.numMSG + 1);
                    break;
                }
                case 11: {
                    break;
                }
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void onCreateItem(Message msg) {
        try {
            MainMenu.gI().miniItem = 0;
            MainMenu.vecItemCreate.removeAllElements();
            byte type = msg.reader().readByte();
            switch (type) {
                case 0: {
                    int min;
                    MainMenu.gI().miniItem = min = msg.reader().readUnsignedByte();
                    int size = msg.reader().readByte();
                    int i = 0;
                    while (i < size) {
                        Item it0 = new Item();
                        it0.ID = msg.reader().readShort();
                        it0.catagory = msg.reader().readByte();
                        it0.level = msg.reader().readShort();
                        it0.charClazz = msg.reader().readByte();
                        it0.name = msg.reader().readUTF();
                        it0.lock = msg.reader().readByte();
                        it0.plus = msg.reader().readByte();
                        it0.idIcon = msg.reader().readShort();
                        it0.colorname = msg.reader().readByte();
                        it0.cantrade = msg.reader().readBoolean();
                        it0.cansell = msg.reader().readBoolean();
                        it0.priceShop = msg.reader().readInt();
                        it0.quantity = msg.reader().readInt();
                        int nOption = msg.reader().readByte();
                        int j = 0;
                        while (j < nOption) {
                            ItemOption itoption = new ItemOption();
                            itoption.id = msg.reader().readShort();
                            itoption.idColor = msg.reader().readByte();
                            itoption.value = msg.reader().readInt();
                            itoption.value2 = msg.reader().readInt();
                            it0.options.addElement(itoption);
                            ++j;
                        }
                        it0.pos = msg.reader().readByte();
                        it0.numKhamNgoc = msg.reader().readByte();
                        it0.idNgocKham = new int[it0.numKhamNgoc];
                        for (int k = 0; k < it0.numKhamNgoc; k++) {
                            it0.idNgocKham[k] = msg.reader().readInt();
                        }
                        MainMenu.vecItemCreate.addElement(it0);
                        ++i;
                    }
                    MainMenu.isCheDo = true;
                    MainMenu.gI().switchToMe(GameCanvas.gameScr);
                    break;
                }
                case 1: {
                    byte rs = msg.reader().readByte();
                    short idIcon = msg.reader().readShort();
                    MainMenu.gI().CreateItemresult(rs, idIcon);
                    break;
                }
                case 2: {
                    int size2 = msg.reader().readByte();
                    int i = 0;
                    while (i < size2) {
                        Item it0 = new Item();
                        it0.ID = msg.reader().readShort();
                        it0.catagory = msg.reader().readByte();
                        it0.level = msg.reader().readShort();
                        it0.charClazz = msg.reader().readByte();
                        it0.name = msg.reader().readUTF();
                        it0.lock = msg.reader().readByte();
                        it0.plus = msg.reader().readByte();
                        it0.idIcon = msg.reader().readShort();
                        it0.colorname = msg.reader().readByte();
                        it0.cantrade = msg.reader().readBoolean();
                        it0.cansell = msg.reader().readBoolean();
                        it0.priceShop = msg.reader().readInt();
                        it0.quantity = msg.reader().readInt();
                        int nOption = msg.reader().readByte();
                        int j = 0;
                        while (j < nOption) {
                            ItemOption itoption = new ItemOption();
                            itoption.id = (short)msg.reader().readUnsignedByte();
                            itoption.idColor = msg.reader().readByte();
                            itoption.value = msg.reader().readInt();
                            itoption.value2 = msg.reader().readInt();
                            it0.options.addElement(itoption);
                            ++j;
                        }
                        it0.pos = msg.reader().readByte();
                        it0.numKhamNgoc = msg.reader().readByte();
                        it0.idNgocKham = new int[it0.numKhamNgoc];
                        for (int k = 0; k < it0.numKhamNgoc; k++) {
                            it0.idNgocKham[k] = msg.reader().readInt();
                        }
                        MainMenu.vecItemCreate.addElement(it0);
                        ++i;
                    }
                    MainMenu.isCheDo = true;
                    MainMenu.gI().switchToMe(GameCanvas.gameScr);
                    break;
                }
                case 3: {
                    MainMenu.gI().ChuyenHoaItemresult();
                    break;
                }
                case 4: {
                    int size4 = msg.reader().readByte();
                    int i = 0;
                    while (i < size4) {
                        Item it0 = new Item();
                        it0.ID = msg.reader().readShort();
                        it0.catagory = msg.reader().readByte();
                        it0.level = msg.reader().readShort();
                        it0.charClazz = msg.reader().readByte();
                        it0.name = msg.reader().readUTF();
                        it0.lock = msg.reader().readByte();
                        it0.plus = msg.reader().readByte();
                        it0.idIcon = msg.reader().readShort();
                        it0.colorname = msg.reader().readByte();
                        it0.cantrade = msg.reader().readBoolean();
                        it0.cansell = msg.reader().readBoolean();
                        it0.priceShop = msg.reader().readInt();
                        it0.quantity = msg.reader().readInt();
                        int nOption = msg.reader().readByte();
                        int j = 0;
                        while (j < nOption) {
                            ItemOption itoption = new ItemOption();
                            itoption.id = (short)msg.reader().readUnsignedByte();
                            itoption.idColor = msg.reader().readByte();
                            itoption.value = msg.reader().readInt();
                            itoption.value2 = msg.reader().readInt();
                            it0.options.addElement(itoption);
                            ++j;
                        }
                        it0.pos = msg.reader().readByte();
                        it0.numKhamNgoc = msg.reader().readByte();
                        it0.idNgocKham = new int[it0.numKhamNgoc];
                        for (int k = 0; k < it0.numKhamNgoc; k++) {
                            it0.idNgocKham[k] = msg.reader().readInt();
                        }
                        MainMenu.vecItemCreate.addElement(it0);
                        ++i;
                    }
                    MainMenu.isPhiPhong = true;
                    MainMenu.gI().switchToMe(GameCanvas.gameScr);
                    break;
                }
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void onWeather(Message msg) {
        try {
            byte weather = msg.reader().readByte();
            short number = msg.reader().readShort();
            int timeLimit = msg.reader().readInt();
            GameScr.onWeather(weather, number, timeLimit);
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void onInfoClan(Message msg) {
    }

    public void onPetattack(Message msg) {
        try {
            short idpet = msg.reader().readShort();
            byte idSkill = msg.reader().readByte();
            int sizeTarget = msg.reader().readByte();
            mVector ntarget = new mVector();
            int[] dame = new int[sizeTarget];
            int i = 0;
            while (i < sizeTarget) {
                int hpLost;
                short idTarget = msg.reader().readShort();
                byte catTarget = msg.reader().readByte();
                dame[i] = hpLost = msg.reader().readInt();
                long hpCon = msg.reader().readLong();
                Actor ac = GameCanvas.gameScr.findActor(idTarget, catTarget);
                if (ac != null) {
                    ac.setHp(hpCon);
                    ntarget.addElement(ac);
                }
                ++i;
            }
            short range = 30;
            try {
                range = msg.reader().readShort();
            }
            catch (Exception e) {
                range = 30;
            }
            Actor pet = GameScr.isHavePet(idpet);
            if (pet != null) {
                pet.petAttack(idSkill, ntarget, dame, range);
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void onGhepPhiPhong(Message msg) {
        try {
            byte type = msg.reader().readByte();
            switch (type) {
                case 0: {
                    int min;
                    MainMenu.xuCuongHoa = 0;
                    byte typeCreate = msg.reader().readByte();
                    MainMenu.gI().typePhiPhong = typeCreate;
                    MainMenu.vecItemCreate.removeAllElements();
                    MainMenu.gI().miniItem = min = msg.reader().readUnsignedByte();
                    int size = msg.reader().readByte();
                    int i = 0;
                    while (i < size) {
                        Item it0 = new Item();
                        it0.ID = msg.reader().readShort();
                        it0.catagory = msg.reader().readByte();
                        it0.level = msg.reader().readShort();
                        it0.charClazz = msg.reader().readByte();
                        it0.name = msg.reader().readUTF();
                        it0.lock = msg.reader().readByte();
                        it0.plus = msg.reader().readByte();
                        it0.idIcon = msg.reader().readShort();
                        it0.colorname = msg.reader().readByte();
                        it0.cantrade = msg.reader().readBoolean();
                        it0.cansell = msg.reader().readBoolean();
                        it0.priceShop = msg.reader().readInt();
                        it0.quantity = msg.reader().readInt();
                        int nOption = msg.reader().readByte();
                        int j = 0;
                        while (j < nOption) {
                            ItemOption itoption = new ItemOption();
                            itoption.id = msg.reader().readShort();
                            itoption.idColor = msg.reader().readByte();
                            itoption.value = msg.reader().readInt();
                            itoption.value2 = msg.reader().readInt();
                            it0.options.addElement(itoption);
                            ++j;
                        }
                        it0.pos = msg.reader().readByte();
                        it0.numKhamNgoc = msg.reader().readByte();
                        it0.idNgocKham = new int[it0.numKhamNgoc];
                        for (int k = 0; k < it0.numKhamNgoc; k++) {
                            it0.idNgocKham[k] = msg.reader().readInt();
                        }
                        MainMenu.vecItemCreate.addElement(it0);
                        ++i;
                    }
                    MainMenu.xuCuongHoa = msg.reader().readInt();
                    MainMenu.isPhiPhong = true;
                    MainMenu.gI().switchToMe(GameCanvas.gameScr);
                    break;
                }
                case 1: {
                    short idIcon = msg.reader().readShort();
                    MainMenu.gI().CreatePhiPhongresult(0, idIcon);
                    break;
                }
                case 2: {
                    short min2 = msg.reader().readShort();
                    MainMenu.gI().miniItem = min2;
                    break;
                }
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void onPetBuff(Message msg) {
        try {
            byte type = msg.reader().readByte();
            switch (type) {
                case 0: {
                    short idchar = msg.reader().readShort();
                    byte idskill = msg.reader().readByte();
                    int time = msg.reader().readInt();
                    byte cat = msg.reader().readByte();
                    Actor ac = GameCanvas.gameScr.findActorByID(idchar, cat);
                    if (ac != null) {
                        ac.addMainBuff(idskill, time);
                    }
                    break;
                }
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void onPetinfo(Message msg) {
        try {
            byte type = msg.reader().readByte();
            switch (type) {
                case 0: {
                    int size = msg.reader().readByte();
                    MainMenu.petInfo = new short[size];
                    int i = 0;
                    while (i < size) {
                        MainMenu.petInfo[i] = msg.reader().readShort();
                        ++i;
                    }
                    MainMenu.vecPetEat.removeAllElements();
                    int sizeitem = 0;
                    try {
                        sizeitem = msg.reader().readByte();
                    }
                    catch (Exception e) {
                        sizeitem = -1;
                    }
                    if (sizeitem == -1) {
                        MainMenu.vecPetEat = Char.inventory;
                    }
                    if (sizeitem > 0) {
                        int i2 = 0;
                        while (i2 < sizeitem) {
                            Item it0 = new Item();
                            it0.ID = msg.reader().readShort();
                            it0.catagory = msg.reader().readByte();
                            it0.level = msg.reader().readShort();
                            it0.charClazz = msg.reader().readByte();
                            it0.name = msg.reader().readUTF();
                            it0.lock = msg.reader().readByte();
                            it0.plus = msg.reader().readByte();
                            it0.idIcon = msg.reader().readShort();
                            it0.colorname = msg.reader().readByte();
                            it0.cantrade = msg.reader().readBoolean();
                            it0.cansell = msg.reader().readBoolean();
                            it0.priceShop = msg.reader().readInt();
                            it0.quantity = msg.reader().readInt();
                            int nOption = msg.reader().readByte();
                            int j = 0;
                            while (j < nOption) {
                                ItemOption itoption = new ItemOption();
                                itoption.id = msg.reader().readShort();
                                itoption.idColor = msg.reader().readByte();
                                itoption.value = msg.reader().readInt();
                                itoption.value2 = msg.reader().readInt();
                                it0.options.addElement(itoption);
                                ++j;
                            }
                            MainMenu.vecPetEat.addElement(it0);
                            ++i2;
                        }
                    }
                    break;
                }
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    @Override
    public void perform(int idAction, Object p) {
        switch (idAction) {
            case 0: {
                GameCanvas.endDlg();
                GameService.gI().OnPopUp_Server(this.idDialog, this.typeDialog, (byte)1);
                break;
            }
            case 1: {
                GameCanvas.endDlg();
                GameService.gI().OnPopUp_Server(this.idDialog, this.typeDialog, (byte)0);
                break;
            }
            case 2: {
                SetInfoData da_ta = (SetInfoData)p;
                SetInfoData mdata = new SetInfoData();
                GameScr.StepHelpServer = (byte)(GameScr.StepHelpServer + 1);
                if (GameScr.StepHelpServer >= GameScr.strHelpNPC.length - 1) {
                    GameCanvas.StartHelp(GameScr.strHelpNPC[GameScr.StepHelpServer], null, da_ta.idIcon, true);
                    break;
                }
                mdata.idIcon = da_ta.idIcon;
                mCommand cmd = new mCommand(T.next, (IActionListener)this, 2, (Object)mdata);
                GameCanvas.StartHelp(GameScr.strHelpNPC[GameScr.StepHelpServer], cmd, mdata.idIcon, false);
                break;
            }
        }
    }

    public void onLogOut(Message msg) {
        try {
            GameScr.isMeLogin = msg.reader().readBoolean();
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public static void onInappPurchaseAndroid(Message msg) {
        try {
            if (mSystem.isAndroidStore()) {
                String productId = msg.reader().readUTF();
                mSystem.Debug(productId);
                AndroidInappBilling.instance().consumePurchase(productId);
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void readTrade(Message m)throws IOException {
        byte b = m.reader().readByte();
        switch (b){
            case -2:
                TradeScr.gI().doClose();
                break;
            case 0:
                int size = m.reader().readShort();
                mVector vitem = new mVector();
                int i = 0;
                while (i < size) {
                    Item it0 = new Item();
                    it0.read(m);
                    vitem.addElement(it0);
                    ++i;
                }
                TradeScr.gI().maxItemTrade = m.reader().readShort();
                String name = m.reader().readUTF();
                TradeScr.gI().buttons.get(2).Tile = name;
                GameCanvas.endDlg();
                TradeScr.gI().setItemBag(vitem);
                TradeScr.gI().switchToMe(GameCanvas.gameScr);
                break;
            case 1:
                TradeScr.gI().isLock = true;
                break;
            case 2:
                int xu = m.reader().readInt();
                size = m.reader().readShort();
                vitem = new mVector();
                i = 0;
                while (i < size) {
                    Item it0 = new Item();
                    it0.read(m);
                    vitem.addElement(it0);
                    ++i;
                }
                TradeScr.gI().setItemReceive(xu, vitem);
                break;
        }
    }

    public void readHorseNew(Message msg) {
        try {
            short id = msg.reader().readShort();
            Char ac = (Char) GameCanvas.gameScr.findActor(id, 0);
            if (GameScr.mainChar != null && id == GameScr.mainChar.ID) {
                ac = GameScr.mainChar;
            }
            if (ac == null)
                return;
            boolean isNew = msg.reader().readBoolean();
            int dx = msg.reader().readShort();
            int dy = msg.reader().readShort();
            ac.isHorseNew = isNew;
            ac.dxHorseNew= dx;
            ac.dyHorseNew = dy;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void kickPlayer( ) {
        try {
            if (GameCanvas.currentScreen instanceof BauCua) {
                BauCuaManager.gI().cleanup();

                // Quay về game chính
                GameCanvas.gameScr.switchToMe(GameCanvas.currentScreen);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateBetBauCau(Message msg) {
        try {
            if (GameCanvas.currentScreen instanceof BauCua) {
                BauCuaRoom room = BauCuaManager.gI().getCurrentGame().currentRoom;
                if (room != null) {
                    room.defaultBetAmount = msg.reader().readLong(); // Cập nhật tiền cược mặc định
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void rankingDamage(Message msg) {
        try {
            boolean isShow = msg.reader().readBoolean();
            if (isShow) {
                GameScr.showDamageRanking = true;
                int size = msg.reader().readByte();
                GameScr.damageRankingList.removeAllElements();
                for (int i = 0; i < size; i++) {
                    String clanName = msg.reader().readUTF();
                    long damage = msg.reader().readLong();
                    int iconId = msg.reader().readShort();
                    GameScr.damageRankingList.addElement(new GameScr.DamageRankingInfo(clanName, damage, iconId));
                }
            } else {
                GameScr.showDamageRanking = false;
            }
        } catch (Exception e) {

        }
    }

    public void onMountWearing(Message msg) {
        try {
            if (GameScr.mainChar != null) {
                GameScr.mainChar.mount = new Item[6];
                int i2 = 0;
                while (i2 < GameScr.mainChar.mount.length) {
                    GameScr.mainChar.mount[i2] = null;
                    byte iditem = msg.reader().readByte();
                    if (iditem > -1) {
                        GameScr.mainChar.mount[i2] = new Item();
                        GameScr.mainChar.mount[i2].ID = iditem;
                        GameScr.mainChar.mount[i2].charClazz = msg.reader().readByte();
                        GameScr.mainChar.mount[i2].level = msg.reader().readShort();
                        GameScr.mainChar.mount[i2].name = msg.reader().readUTF();
                        GameScr.mainChar.mount[i2].plus = msg.reader().readByte();
                        GameScr.mainChar.mount[i2].idIcon = msg.reader().readShort();
                        GameScr.mainChar.mount[i2].colorname = msg.reader().readByte();
                        GameScr.mainChar.mount[i2].setTypeItem(msg.reader().readByte());
                        int sizeOption = msg.reader().readByte();
                        GameScr.mainChar.mount[i2].options = new mVector();
                        int j = 0;
                        while (j < sizeOption) {
                            ItemOption itop = new ItemOption();
                            itop.id = msg.reader().readShort();
                            itop.idColor = msg.reader().readByte();
                            itop.value = msg.reader().readInt();
                            itop.value2 = msg.reader().readInt();
                            GameScr.mainChar.mount[i2].options.addElement(itop);
                            ++j;
                        }
                        GameScr.mainChar.mount[i2].type = msg.reader().readByte();
                        GameScr.mainChar.mount[i2].numKhamNgoc = msg.reader().readByte();
                        GameScr.mainChar.mount[i2].idNgocKham = new int[GameScr.mainChar.mount[i2].numKhamNgoc];
                        for (int k = 0; k < GameScr.mainChar.mount[i2].numKhamNgoc; k++) {
                            GameScr.mainChar.mount[i2].idNgocKham[k] = msg.reader().readInt();
                        }
                    }
                    ++i2;
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void onMountInfo(Message msg) {
        try {
            byte type = msg.reader().readByte();
            switch (type) {
                case 0: {
                    MainMenu.vecMountEat.removeAllElements();
                    int sizeitem = 0;
                    try {
                        sizeitem = msg.reader().readByte();
                    } catch (Exception e) {
                        sizeitem = -1;
                    }
                    if (sizeitem == -1) {
                        MainMenu.vecMountEat = Char.inventory;
                    }
                    if (sizeitem > 0) {
                        int i2 = 0;
                        while (i2 < sizeitem) {
                            Item it0 = new Item();
                            it0.ID = msg.reader().readShort();
                            it0.catagory = msg.reader().readByte();
                            it0.level = msg.reader().readShort();
                            it0.charClazz = msg.reader().readByte();
                            it0.name = msg.reader().readUTF();
                            it0.lock = msg.reader().readByte();
                            it0.plus = msg.reader().readByte();
                            it0.idIcon = msg.reader().readShort();
                            it0.colorname = msg.reader().readByte();
                            it0.cantrade = msg.reader().readBoolean();
                            it0.cansell = msg.reader().readBoolean();
                            it0.priceShop = msg.reader().readInt();
                            it0.quantity = msg.reader().readInt();
                            int nOption = msg.reader().readByte();
                            int j = 0;
                            while (j < nOption) {
                                ItemOption itoption = new ItemOption();
                                itoption.id = msg.reader().readShort();
                                itoption.idColor = msg.reader().readByte();
                                itoption.value = msg.reader().readInt();
                                itoption.value2 = msg.reader().readInt();
                                it0.options.addElement(itoption);
                                ++j;
                            }
                            MainMenu.vecMountEat.addElement(it0);
                            ++i2;
                        }
                    }
                    break;
                }
            }
        } catch (Exception exception) {
            // empty catch block
        }
    }
}
