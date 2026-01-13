/*
 * Decompiled with CFR 0.152.
 */
package mchien.code.screen.screen;

import com.badlogic.gdx.graphics.Color;
import mchien.code.effect.new_skill.EffectEnd;
import mchien.code.effect.new_skill.EffectSkill;
import mchien.code.main.GameCanvas;
import mchien.code.model.*;
import mchien.code.model.arrow.WeaponsLazer;
import mchien.code.network.GameService;
import mchien.code.screen.Res;
import mchien.code.screen.SkillTemplate;
import mchien.code.screen.Util;
import mchien.code.screen.screen.ChangScr;
import mchien.code.screen.screen.FontTeam;
import mchien.code.screen.screen.GameData;
import mchien.code.screen.screen.GameScr;
import mchien.code.screen.screen.InfoTextShow;
import mchien.code.screen.screen.ScreenTeam;
import mchien.code.screen.screen.SetInfoData;

import javax.microedition.lcdui.Image;

import lib.Cout;
import lib.MainUnity;
import lib.Rms;
import lib.mGraphics;
import lib.mHashtable;
import lib.mSound;
import lib.mSystem;
import lib.mVector;
import lib2.TField;
import lib2.mFont;

public class MainMenu
        extends ScreenTeam
        implements IActionListener {
    public static MainMenu me;
    public int x;
    public int y;
    public int w;
    public int h;
    public int xbg;
    public int size;
    public int indexMainTab;
    public int xScItem;
    public int yScItem;
    public int hScItem;
    public int wScItem;
    public int indexSubTab;
    public int countFrame;
    public int sizeH;
    public int indexTabMiniShop;
    public int totalLineQuest;
    public int xcenter;
    public int ycenter;
    public int miniItem;
    public int wait;
    public int xSao;
    public int ySao;
    public int typePhiPhong;
    public int indexPet;
    public static int indexTypeQuest;
    public static int indexQuest;
    public static int typeresult;
    public static int idicon;
    public int prizeImbue;
    public short idCharSell;
    public static boolean isDapdo;
    public static boolean isCheDo;
    public static boolean isPhiPhong;
    public static boolean isChuyenHoa;
    public static boolean isHopda;
    public static final byte SHOP = 0;
    public static final byte HANHTRANG = 1;
    public static final byte TRANGBI = 2;
    public static final byte SKILL = 3;
    public static final byte QUEST = 4;
    public static final byte NAP_XU = 5;
    public static final byte BANG_HOI = 6;
    public static final byte HOAT_DONG = 7;
    public static final byte DAP_DO = 8;
    public static final byte CHE_DO = 9;
    public static final byte CHUYEN_HOA = 10;
    public static final byte TAO_PHI_PHONG = 11;
    public static final byte PET_ITEM = 12;
    public static final byte BAG = 22;
    public static final byte SKILL_CLAN = 29;
    public int[] maptopaintMenuIcon;
    public int[] maptopaintIconTrangBi;
    public static int[] indexpaintIconSettings;
    public static short ID_CUONG_HOA;
    public static short ID_BAO_HO;
    public static short ID_MAY_MAN;
    public static String[] mainTab;
    public static String[] myInfo;
    public static String[] bangHoi;
    public static String[] hoat_dong;
    public static String[] settings;
    public static String[] tabHanhTrang;
    public static String[] feaTures;
    public static String[] currnentTabDetail;
    String currentTileMainTab;
    TField tfStrength;
    TField tfAgility;
    TField tfSpirit;
    TField tfHealth;
    TField tfLucky;
    public static boolean isPet;
    private int lastSelect;
    public int colum;
    public int hIP;
    public int xShowText;
    public int yShowText;
    public int wShowText;
    public int hShowText;
    public int indexShowInfo;
    public int xShowTextSkill;
    public int yShowTextSkill;
    private mVector showText;
    private byte[] arrayKhamNgoc;
    public mVector list;
    public mVector readyBuy;
    public mVector listTemp;
    public mVector listEp;
    public static final short MAX_POTION = 999;
    public mVector inFoNap;
    public mVector sellItem;
    public mVector listItemAnimal;
    Scroll cmrItem;
    Scroll cmrSubTab;
    Scroll cmrShowText;
    Scroll cmrShowInfoMainChar;
    public static byte khoaImbue;
    public static byte idMonsterClanQuest;
    public boolean isLoad;
    public boolean isSkill;
    public boolean first;
    public boolean isFeatures;
    public boolean isCharWearing;
    public boolean isShowInFoChar;
    public boolean isQuest;
    public boolean isMoveQuest;
    public boolean isAnimal;
    public boolean isHanhTrang;
    public boolean isgangphim;
    public boolean isMiniShop;
    public boolean isFocusClose;
    public boolean isFocusCloseItemFill;
    public boolean isSkillClan;
    public boolean isQuestClan;
    public boolean Waitcreate;
    public static short numberQuestAll;
    public static short numberQuestGet;
    public int[][] arrFrame;
    public int[][] xyTiemNang;
    public int[][] xyDapDo;
    public int[] sizeEff;
    int[] xsai;
    int[] listNum;
    int[] postItem_PhiPhong_X;
    int[] postItem_PhiPhong_Y;
    int[] postItem;
    Item[] listItem;
    public static int xposItem;
    public static int ypostItem;
    public static mCommand cmdCongDiem;
    public static mCommand cmdcong1;
    public static mCommand cmdcong5;
    public static mCommand cmdcong10;
    public static mCommand cmdGangphim;
    public static mCommand cmdBuy;
    public static mCommand cmdMapScr;
    public static mCommand cmdHuyQuest;
    public static mCommand cmdChangeMapScr;
    public static mCommand cmdban;
    public static mCommand cmdvut;
    public static mCommand cmdsudung;
    public static mCommand cmdbanNhieu;
    public static mCommand cmdbovao;
    public static mCommand cmdlayra;
    public static mCommand cmdtao;
    public static mCommand cmdlayraPhiPhong;
    public static mCommand cmdChoan;
    public static mCommand cmdTB1;
    public static mCommand cmdTB2;
    public static mCommand cmdThao;
    public boolean isRestartAutoFight;
    public boolean beginChedo;
    public boolean isTouchCenter;
    public static mVector vecItemDapDo;
    public static mVector vecItemCreate;
    public static mVector vecPetEat;
    public static mVector vecMountEat;
    public static mVector vecMaterial;
    public static Item mItem;
    public static Item itemStone;
    public static Item itemBaohiem;
    public static Item itemBua;
    public static Item itemChuyenHoa0;
    public static Item itemChuyenHoa1;
    public static Item itemChuyenHoa2;
    public static int maxDap;
    public static int tabDapdo;
    public static int xuCuongHoa;
    public static int moneyType;
    public mVector vHanhTrang;
    public mVector vEffect;
    public static DataSkillEff effDapDo;
    public static String textPercent;
    public static boolean isBeginQuest;
    public static boolean isInven;
    public static int delay;
    public Item itemPP;
    public Pet mpet;
    public static short[] petInfo;
    Scroll scrDapdo;
    Scroll scrSkill;
    Scroll scrMainmenu;
    public int numStone;
    public byte indexWearing;
    public mVector mShop;
    int maxNumW;
    public static byte[] index;
    byte[] arrSell;
    public static String captionServer;
    public static String infoBuySellServer;
    public int[] xWearing;
    public int[] yWearing;
    int xInfoWearing;
    int yInfoWearing;
    int wInfoWearing;
    int hInfoWearing;
    int xItemFill;
    int yItemFill;
    int wItemFill;
    int hItemFill;
    private String[] animalInfo;
    public mVector totalItemFill;
    boolean isShowFill;
    int sizeDapdo;
    int slDapdo1;
    int slDapdo2;
    int tickChuyenhoa;
    int FocusPhiPhong;
    int FocusPet;
    public static byte POS_BODY;
    public static byte POS_HAT;
    public static byte POS_SHOES;
    public static byte POS_GLOVE;
    public static byte POS_WEAPON;
    public static byte POS_RING_LEFT;
    public static byte POS_RING_RIGHT;
    public static byte POS_CHAIN;
    public static byte POS_JEWELRY;
    public static byte POS_BELT;
    public static byte POS_THU_CUOI;
    public static byte POS_PHI_PHONG;
    public static byte POS_AN;
    public static byte POS_PET;
    public static byte POS_THOI_TRANG;
    public static byte[] POS_ITEM_IN_EQUIP;
    public mFont[] petfont;
    int coutFc;
    public String currentNameSkill;
    int f;
    int r;
    int goc;
    int disString;
    int indexShadow;
    int numItemStart;
    int laststar;
    int speedStart;
    int numItem;
    int indexPaintLineSkill;
    int numItemStart2;
    int laststar2;
    int speedStart2;
    int numKhamNgoc;
    int[] idNgocKham;
    boolean isHalfstart;
    boolean runStart;
    boolean isTextmua;
    boolean canbuy;
    boolean isHalfstart2;
    boolean runStart2;
    public String[] arrayText;
    public String textHoimua;
    public static boolean isFocusDetailMenu;
    public static boolean isChangeSubTab;
    public static boolean isFocusCharWearing;
    private int countL;
    private int countR;
    private byte focusTab;
    int timeAuToShowText;
    public boolean beGinShowText;
    public boolean isSell;
    public boolean isFocusPetWearing;
    private int idFrame;
    public static Char charWearing;
    public int countTextmua;
    boolean isMe;
    public static boolean isShow;
    public int numtab;
    mCommand cmdShowText;
    mCommand cmdSelectItem;
    String[] tile;
    mVector infochar;
    public boolean isShowText;
    private boolean isUseCmr;
    private boolean isSetXYCmdSelect;
    private boolean isPaintMoney;
    public Image imgWeaponAvatar;
    public mVector potionShop;
    private mVector questInfo;
    public static byte lvThu;
    public static final byte TAM = 8;
    public static final byte MUOI = 10;
    public static final byte MUOICHIN = 19;
    public static final byte HAIMOT = 21;
    public static final byte HAIHAI = 22;
    public static final byte HAILAM = 25;
    public int idSeller;
    public int shopIdSell;
    public int idNPC_Shop;
    public int catNPC_Shop;
    short oldSelect;
    byte totalInfoshow;
    Image imgCharWear;
    public String name;
    public static final short SELECT_ITEM_INVENTORY = 79;
    public static final short SELECT_ITEM_SHOP = -2;
    public static final short SELECT_ITEM_WEARING = 4;
    public static String[] QuestTile;
    public static mVector[] ListQuest;

    static {
        QuestTile=new String[]{""};
        ListQuest=new mVector[0];
        isDapdo = false;
        isCheDo = false;
        isPhiPhong = false;
        isChuyenHoa = false;
        isHopda = false;
        int[] nArray = new int[6];
        nArray[0] = 5;
        indexpaintIconSettings = nArray;
        isPet = true;
        idMonsterClanQuest = (byte) -1;
        numberQuestGet = 0;
        vecItemDapDo = new mVector();
        vecItemCreate = new mVector();
        vecPetEat = new mVector();
        vecMountEat = new mVector();
        vecMaterial = new mVector();
        effDapDo = new DataSkillEff();
        textPercent = "0";
        isBeginQuest = false;
        delay = 0;
        index = new byte[1];
        captionServer = "";
        infoBuySellServer = "";
        POS_BODY = (byte) 5;
        POS_HAT = 0;
        POS_SHOES = (byte) 8;
        POS_GLOVE = (byte) 7;
        POS_WEAPON = (byte) 9;
        POS_RING_LEFT = (byte) 3;
        POS_RING_RIGHT = (byte) 4;
        POS_CHAIN = 1;
        POS_JEWELRY = (byte) 2;
        POS_BELT = (byte) 6;
        POS_THU_CUOI = (byte) 11;
        POS_PHI_PHONG = (byte) 10;
        POS_AN = (byte) 13;
        POS_PET = (byte) 14;
        POS_THOI_TRANG = (byte) 12;
        POS_ITEM_IN_EQUIP = new byte[] { POS_BODY, POS_HAT, POS_SHOES, POS_GLOVE, POS_WEAPON, POS_WEAPON, POS_WEAPON,
                POS_WEAPON, POS_WEAPON, POS_RING_LEFT, POS_CHAIN, POS_JEWELRY, POS_BELT, -1, -1, -1, -1, -1, -1, -1, -1,
                -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, POS_PET, -1, -1, POS_AN, -1, -1, -1, -1,
                POS_PET, POS_THOI_TRANG, -1, -1, -1, -1, -1, -1 };
    }

    public short frameThuCuoi;
    public int Fhorse;
    public int frameThuCuoiNew;
    private int frame;
    private int p1;
    private int dir = 0;


    public static MainMenu gI() {
        return me == null ? (me = new MainMenu()) : me;
    }

    public MainMenu() {
        int[] nArray = new int[15];
        nArray[0] = 5;
        nArray[1] = 9;
        nArray[2] = 3;
        nArray[3] = 11;
        nArray[4] = 2;
        nArray[6] = 7;
        nArray[7] = 8;
        nArray[8] = 1;
        nArray[9] = 6;
        nArray[10] = 10;
        nArray[11] = 14;
        nArray[12] = 12;
        nArray[13] = 13;
        nArray[14] = -1;
        this.maptopaintIconTrangBi = nArray;
        this.currentTileMainTab = "";
        this.tfStrength = new TField();
        this.tfAgility = new TField();
        this.tfSpirit = new TField();
        this.tfHealth = new TField();
        this.tfLucky = new TField();
        this.lastSelect = -1;
        this.colum = 7;
        this.list = new mVector();
        this.readyBuy = new mVector();
        this.listTemp = new mVector();
        this.listEp = new mVector();
        this.inFoNap = new mVector();
        this.sellItem = new mVector();
        this.listItemAnimal = new mVector();
        this.cmrItem = new Scroll();
        this.cmrSubTab = new Scroll();
        this.cmrShowText = new Scroll();
        this.cmrShowInfoMainChar = new Scroll();
        this.isLoad = false;
        int[][] nArrayArray = new int[2][];
        int[] nArray2 = new int[3];
        nArray2[1] = 1;
        nArray2[2] = 2;
        nArrayArray[0] = nArray2;
        int[] nArray3 = new int[2];
        nArray3[1] = 1;
        nArrayArray[1] = nArray3;
        this.arrFrame = nArrayArray;
        this.xyTiemNang = new int[4][];
        this.xyDapDo = new int[3][];
        this.sizeEff = new int[] { 2, 2, 2, 2, 2, 2 };
        this.xsai = new int[] { 38, 38, -4, -4, -4, 38 };
        this.listNum = new int[6];
        this.postItem = new int[] { 30, 90, 150, 210, 270, 330 };
        this.listItem = new Item[6];
        this.vHanhTrang = new mVector();
        this.vEffect = new mVector();
        this.scrDapdo = new Scroll();
        this.scrSkill = new Scroll();
        this.scrMainmenu = new Scroll();
        this.mShop = new mVector();
        this.maxNumW = 3;
        this.totalItemFill = new mVector();
        this.sizeDapdo = 26;
        this.petfont = new mFont[] { mFont.tahoma_7_green, mFont.tahoma_7_red, mFont.tahoma_7_blue,
                mFont.tahoma_7_violet };
        this.currentNameSkill = "";
        this.disString = 12;
        this.numItem = 0;
        this.tile = new String[2];
        this.infochar = new mVector();
        this.potionShop = new mVector();
        this.oldSelect = 0;
        this.totalInfoshow = 0;
        this.cmdSelectItem = new mCommand(this);
        this.right = new mCommand("\u0110\u00f3ng", this, 1);
        if (GameCanvas.isTouch) {
            this.right.caption = "";
            this.right.idAction = 1000;
        }
        cmdTB1 = new mCommand(T.trangbi1, this, 48);
        cmdTB2 = new mCommand(T.trangbi2, this, 49);
        cmdThao = new mCommand(T.thaoTbi, this, 50);
        this.indexWearing = 0;
        cmdCongDiem = new mCommand(T.congDiem, this, 334);
        cmdcong1 = new mCommand(String.valueOf(T.congDiem), this, 19);
        cmdGangphim = new mCommand(T.Gangphim, this, 40);
        cmdBuy = new mCommand(T.Buy, this, 5);
        cmdMapScr = new mCommand(T.map, this, 15);
        if (!GameCanvas.isTouch) {
            cmdChangeMapScr = new mCommand(T.map, this, 18);
        }
        cmdHuyQuest = new mCommand(T.cancel, this, 17);
        cmdbanNhieu = new mCommand(T.bannhieu, this, 25);
    }

    public void PutItemSHop(mVector shop) {
        this.mShop = shop;
    }

    public void setInven() {
        this.indexMainTab = 1;
    }

    public void set_maptopaintMenuIcon(byte[] numTab) {
        this.maptopaintMenuIcon = null;
        this.maptopaintMenuIcon = new int[numTab.length];
        int i = 0;
        while (i < numTab.length) {
            this.maptopaintMenuIcon[i] = numTab[i];
            ++i;
        }
    }

    public static String getInfoBuySell(String utf1, String utf2, Item it) {
        if (it == null) {
            return "";
        }
        utf2 = Util.replaceString(utf2, "#", String.valueOf(utf1) + " " + it.getName());
        utf2 = Util.replaceString(utf2, "@", String.valueOf(utf1) + " " + it.getName());
        return utf2;
    }

    public void setInfo(int id, boolean isSell, byte[] info) {
        this.isSkillClan = false;
        int c = 0;
        if (!this.isMe) {
            int i = 0;
            while (i < info.length) {
                if (info[i] != 0) {
                    ++c;
                }
                ++i;
            }
            index = new byte[c];
            i = 0;
            while (i < info.length) {
                if (info[i] != 0) {
                    int j = 0;
                    while (j < index.length) {
                        if (index[j] == 0) {
                            MainMenu.index[j] = info[i];
                            break;
                        }
                        ++j;
                    }
                }
                ++i;
            }
        }
        this.isSell = isSell;
        if (isSell) {
            this.arrSell = index;
        }
        if (this.isMe) {
            this.setSelectTab("setInfo 1");
            return;
        }
        this.indexTabMiniShop = 0;
        this.isMiniShop = true;
        this.indexMainTab = id;
        this.setInfoTab(index[id]);
        this.setSelectTab("setInfo 2");
    }

    public void setInfoTab(int id) {
        switch (id) {
            case 9: {
                currnentTabDetail = new String[] { "Qu\u1ea7y 1" };
                this.isHanhTrang = true;
                break;
            }
            case 11: {
                mainTab = new String[] { "Cửa hàng", "Hành trang" };
                currnentTabDetail = new String[] { "\u00c1o", "Quần", "N\u00f3n", "Nh\u1eabn",
                        "D\u00e2y chuy\u1ec1n", "Gi\u00e0y", "G\u0103ng tay", "Ng\u1ecdc b\u1ed9i" };
                this.isHanhTrang = true;
                break;
            }
            case 12: {
                break;
            }
            case 13: {
                break;
            }
            case 10:
            case 19:
            case 23:
            case 24: {
                mainTab = new String[] { "C\u1eeda h\u00e0ng", "H\u00e0nh trang" };
                currnentTabDetail = new String[] { "Qu\u1ea7y 1" };
                this.isHanhTrang = true;
                break;
            }
            case 22: {
                mainTab = new String[] { "Kho", "H\u00e0nh trang" };
                currnentTabDetail = new String[] { "" };
                this.isHanhTrang = true;
                break;
            }
            case 29: {
                currnentTabDetail = new String[] { bangHoi[2] };
                this.isSkillClan = true;
                break;
            }
        }
    }

    public void initName() {
        bangHoi = GameScr.mainChar.idClan == -1 ? new String[] { "Top", "\u0110\u0103ng k\u00fd" }
                : (GameScr.mainChar.isMaster == 0
                ? new String[] { "Th\u00f4ng tin", "Tin nh\u1eafn", "K\u1ef9 n\u0103ng", " N.v\u1ee5",
                "Q.g\u00f3p", "T.vi\u00ean", "Top", "Chat", "Gi\u1ea3i t\u00e1n" }
                : new String[] { "Th\u00f4ng tin", "Tin nh\u1eafn", "K\u1ef9 n\u0103ng", " N.v\u1ee5",
                "Q.g\u00f3p", "T.vi\u00ean", "Top", "Chat", "R\u1eddi bang" });
    }

    public void setPosTfFeatures(int x, int y, String Strength, String Agility, String Spirit, String Health,
                                 String Lucky) {
        this.tfStrength = new TField();
        this.tfStrength.x = x;
        this.tfStrength.y = y;
        this.tfStrength.setFocus(false);
        this.tfStrength.setIputType(1);
        this.tfStrength.width = this.size + 10;
        this.tfStrength.height = this.size - 5;
        this.tfStrength.setText(Strength);
        this.tfAgility = new TField();
        this.tfAgility.x = x;
        this.tfAgility.y = y += this.size + 2;
        this.tfAgility.setFocus(false);
        this.tfAgility.setIputType(1);
        this.tfAgility.width = this.size + 10;
        this.tfAgility.height = this.size - 5;
        this.tfAgility.setText(Agility);
        this.tfSpirit = new TField();
        this.tfSpirit.x = x;
        this.tfSpirit.y = y += this.size + 2;
        this.tfSpirit.setFocus(false);
        this.tfSpirit.setIputType(1);
        this.tfSpirit.width = this.size + 10;
        this.tfSpirit.height = this.size - 5;
        this.tfSpirit.setText(Spirit);
        this.tfHealth = new TField();
        this.tfHealth.x = x;
        this.tfHealth.y = y += this.size + 2;
        this.tfHealth.setFocus(false);
        this.tfHealth.setIputType(1);
        this.tfHealth.width = this.size + 10;
        this.tfHealth.height = this.size - 5;
        this.tfHealth.setText(Lucky);
        this.tfLucky = new TField();
        this.tfLucky.x = x;
        this.tfLucky.y = y += this.size + 2;
        this.tfLucky.setFocus(false);
        this.tfLucky.setIputType(1);
        this.tfLucky.width = this.size + 10;
        this.tfLucky.height = this.size - 5;
        this.tfLucky.setText(Health);
    }

    @Override
    public boolean keyPress(int keyCode) {
        return super.keyPress(keyCode);
    }

    public boolean isPaintSub() {
        return this.isSkill || this.isFeatures || this.isCharWearing || this.isQuest || this.isAnimal
                || this.isHanhTrang || this.isQuestClan || this.indexMainTab == 0 || isCheDo || isChuyenHoa
                || isPhiPhong || this.indexMainTab == 12|| this.indexMainTab == 4;
    }

    public void setPosWearing(boolean isSkill) {
        int i;
        int i2;
        int ybg = this.y + this.h - 2 * this.size - 3 - (isSkill ? 12 : 0);
        if (isSkill) {
            ybg = this.y + this.h / 2 - 30;
        }
        this.xWearing = new int[isSkill ? GameScr.vec_skill.size() : 15];
        this.yWearing = new int[isSkill ? GameScr.vec_skill.size() : 15];
        int xbgg = this.xbg;
        if (this.isAnimal) {
            this.xWearing = new int[6];
            this.yWearing = new int[6];
            i2 = 0;
            while (i2 < this.xWearing.length) {
                this.xWearing[i2] = xbgg + i2 % 6 * (this.size + 1);
                this.yWearing[i2] = ybg + i2 / 6 * this.size + this.size + this.hIP / 2;
                ++i2;
            }
        } else if (isSkill) {
            int kc = (this.colum - 4) * this.size / 5 + 12;
            i = 0;
            while (i < this.xWearing.length) {
                this.xWearing[i] = xbgg + i % (isSkill ? 3 : 7) * this.size + (i % (isSkill ? 3 : 7) + 1) * kc
                        + (this.colum - 4) * this.size % 5 / 2;
                this.yWearing[i] = ybg + i / (isSkill ? 3 : 7) * (this.size + 12) - this.size + this.hIP / 2;
                ++i;
            }
        } else {
            this.xWearing[0] = xbgg;
            this.yWearing[0] = ybg - this.size + this.hIP / 2;
            this.xWearing[1] = xbgg + this.size;
            this.yWearing[1] = ybg - this.size + this.hIP / 2;
            i2 = 0;
            while (i2 < this.xWearing.length) {
                this.xWearing[i2] = xbgg + i2 % 5 * this.size + 16;
                this.yWearing[i2] = ybg + i2 / 5 * (this.size - this.hIP / 4) - this.size + this.hIP / 2;
                ++i2;
            }
        }
        if (this.indexPet == 1) {
            this.xWearing[0] = xbgg;
            this.yWearing[0] = ybg - this.size + this.hIP / 2;
            this.xWearing[1] = xbgg + this.size;
            this.yWearing[1] = ybg - this.size + this.hIP / 2;
            i2 = 0;
            while (i2 < this.xWearing.length) {
                this.xWearing[i2] = xbgg + i2 % 5 * this.size + 16;
                this.yWearing[i2] = ybg + i2 / 5 * (this.size - this.hIP / 4) - this.size + this.hIP / 2;
                ++i2;
            }
        }
        this.xInfoWearing = xbgg + 2 * this.size;
        this.yInfoWearing = this.y + this.sizeH;
        this.wInfoWearing = 3 * this.size + 5;
        this.hInfoWearing = this.h - this.sizeH - 2 * this.size;
        int ybgTN = 0;
        i = 0;
        while (i < this.xyTiemNang.length) {
            this.xyTiemNang[i] = new int[2];
            ybgTN = this.y + this.sizeH + this.size / 4 + (GameCanvas.w < 200 ? 30 : 34) * (i > 1 ? 1 : 0);
            this.xyTiemNang[i][0] = this.xInfoWearing + 10 * (i % 2) + (10 + (this.wInfoWearing - 42) / 3) * (i % 2 + 1)
                    + 2;
            int ys = 0;
            int ys2 = 3;
            if (i < 2 && mSystem.isIP && mGraphics.zoomLevel == 3) {
                ys = 3;
            }
            if (mSystem.isIP && mGraphics.zoomLevel == 3) {
                ys2 = 0;
            }
            this.xyTiemNang[i][1] = ybgTN + 28 - this.hIP + 3 + ys - ys2;
            ++i;
        }
        this.xyDapDo[0] = new int[2];
        this.xyDapDo[0][0] = this.xyTiemNang[0][0] + 18;
        this.xyDapDo[0][1] = this.xyTiemNang[0][1] - 12 + 5;
        this.xyDapDo[1] = new int[2];
        this.xyDapDo[1][0] = this.xyTiemNang[2][0];
        this.xyDapDo[1][1] = this.xyTiemNang[2][1] - 10 + 5;
        this.xyDapDo[2] = new int[2];
        this.xyDapDo[2][0] = this.xyTiemNang[3][0];
        this.xyDapDo[2][1] = this.xyTiemNang[3][1] - 10 + 5;
        xposItem = this.xyDapDo[0][0] - 70;
        ypostItem = this.xyDapDo[0][1];
    }

    void dosetIDCmdTouch(String caption, int id) {
        if (!GameCanvas.isTouch) {
            return;
        }
        this.cmdSelectItem.caption = "";
        this.cmdSelectItem.idAction = id;
        this.dosetXYCmddTouch();
    }

    void dosetXYCmddTouch() {
        if (!GameCanvas.isTouch) {
            return;
        }
        this.cmdSelectItem.setXY(this.xShowText + this.wShowText / 2 - this.cmdSelectItem.wCmd / 2,
                this.y + this.h - this.cmdSelectItem.hCmd - 2);
        if (this.cmdSelectItem.caption.equals("")) {
            this.cmdSelectItem.x = -100;
        }
    }

    public static int getPos(ItemTemplate it, int pos) {
        if (it == null) {
            return -1;
        }
        switch (it.type) {
            case 0:
            case 14: {
                return 0;
            }
            case 1:
            case 16: {
                return 1;
            }
            case 2:
            case 17: {
                return 2;
            }
            case 3: {
                return 9;
            }
            case 4: {
                return 9;
            }
            case 5: {
                return 9;
            }
            case 6: {
                return 9;
            }
            case 7: {
                return 9;
            }
            case 8: {
                if (pos != 1) {
                    return 6;
                }
                return 5;
            }
            case 9:
            case 18: {
                return 4;
            }
            case 10: {
                return 7;
            }
            case 11: {
                return 3;
            }
            case 12: {
                return 8;
            }
            case 13:
            case 15: {
                return 3;
            }
        }
        return -1;
    }

    private void paintDapDo(mGraphics g, boolean isChar) {
        ImageIcon img;
        int yShowText1 = this.yInfoWearing + 2;
        int xShowText1 = this.xInfoWearing;
        int wShowText1 = this.wInfoWearing + 2;
        int hShowText1 = this.hInfoWearing - 4 + (this.isAnimal ? this.size : 0) - this.size + this.hIP / 2;
        g.setColor(-13232632);
        g.fillRect(xShowText1, yShowText1, wShowText1, hShowText1, false);
        g.setColor(-1596632);
        g.fillRect(xShowText1 + 1, yShowText1 + 1, wShowText1 - 2, hShowText1 - 2, false);
        g.setColor(-13232632);
        g.fillRect(xShowText1 + 2, yShowText1 + 2, wShowText1 - 4, hShowText1 - 4, false);
        g.setColor(-14864849);
        g.fillRect(xShowText1 + 3, yShowText1 + 3, wShowText1 - 6, hShowText1 - 6, false);
        g.drawRegion(GameScr.imgBoder[6], 0, 0, 9, 9, 7, xShowText1 + wShowText1, yShowText1 + hShowText1,
                mGraphics.BOTTOM | mGraphics.RIGHT, false);
        g.drawRegion(GameScr.imgBoder[6], 0, 0, 9, 9, 1, xShowText1, yShowText1 + hShowText1,
                mGraphics.BOTTOM | mGraphics.LEFT, false);
        g.drawRegion(GameScr.imgBoder[6], 0, 0, 9, 9, 0, xShowText1, yShowText1, mGraphics.TOP | mGraphics.LEFT, false);
        g.drawRegion(GameScr.imgBoder[6], 0, 0, 9, 9, 2, xShowText1 + wShowText1, yShowText1,
                mGraphics.TOP | mGraphics.RIGHT, false);
        int xbg = this.x + this.size + 8;
        int ybg = yShowText1;
        if (GameCanvas.w > 200) {
            g.setColor(-9751532);
            int hh0 = hShowText1 + (this.isAnimal ? this.size : 0);
            g.fillRect(xbg + 3, ybg, 2 * this.size - 6, hh0, false);
            g.fillRect(xbg, ybg + 3, 2 * this.size, hh0 - 6, false);
            g.setColor(-4891370);
            g.drawLine(xbg + 3, ybg + 3 + hh0 - 6 + 1, xbg + 2 * this.size - 6, ybg + 3 + hh0 - 6 + 1, false);
            if (isFocusCharWearing && GameCanvas.gameTick / 4 % 4 != 0) {
                g.setColor(-12246258);
            } else {
                g.setColor(-14864849);
            }
            g.fillRect(xbg + 3, ybg + 3, 2 * this.size - 6, hh0 - 6, false);
            g.setColor(-110);
            g.fillRect(xbg + 3, ybg + 1, 2 * this.size - 6, 1, false);
            g.setColor(-4034289);
            g.fillRect(xbg + 1, ybg + 16, 1, hh0 - 18, false);
            g.fillRect(xbg - 2 + 2 * this.size, ybg + 16, 1, hh0 - 18, false);
        }
        g.drawRegion(GameScr.imgBoder[4], 0, 16, 16, 16, 2, xbg + 2 * this.size, ybg,
                mGraphics.BOTTOM | mGraphics.RIGHT, false);
        GameCanvas.resetTrans(g);
        int maxx = vecItemDapDo.size();
        if (maxx < 45) {
            maxx = 45;
        }
        int t = maxx / this.colum + (MainChar.MaxInven % this.colum != 0 ? 1 : 0);
        int hip3 = 0;
        if (mSystem.isIP && mGraphics.zoomLevel == 3) {
            hip3 = -5;
        }
        this.scrDapdo.setStyle(t + 3, this.sizeDapdo + 2, this.xWearing[0] - 10, this.yWearing[0] + 9,
                this.colum * this.sizeDapdo, 5 * this.sizeDapdo, true, this.colum);
        g.ClipRec(this.xWearing[0] - 20, this.yWearing[0] + 3, 142, 74 + hip3);
        this.scrDapdo.setClip(g, this.xWearing[0] - 20, this.yWearing[0] + 3, 142, 74 + hip3);
        int i = 0;
        while (i < maxx) {
            g.setColor(-14458807);
            g.drawRect(this.xWearing[0] + i % this.colum * this.sizeDapdo + 2 - 5 - 10,
                    this.yWearing[0] + i / this.colum * this.sizeDapdo + 10 - 2 + 5 - 9, this.sizeDapdo, this.sizeDapdo,
                    true);
            ++i;
        }
        i = 0;
        while (i < vecItemDapDo.size()) {
            Item it = (Item) vecItemDapDo.elementAt(i);
            if (it != null) {
                it.paint(g, this.xWearing[0] + i % this.colum * this.sizeDapdo + 1,
                        this.yWearing[0] + i / this.colum * this.sizeDapdo + this.sizeDapdo - 9, true);
            }
            ++i;
        }
        if (GameCanvas.isTouch) {
            if (this.slDapdo1 > -1 && this.slDapdo1 < vecItemDapDo.size()) {
                this.paintFocus(g, this.xWearing[0] + this.slDapdo1 % this.colum * this.sizeDapdo + 2 - 2,
                        1 + this.yWearing[0] + this.slDapdo1 / this.colum * this.sizeDapdo + this.sizeDapdo / 2 + 4, 3,
                        true);
            }
            this.paintFocus(g, this.xWearing[0] - 40, 1 + this.yWearing[0], 3, true);
        }
        if (!GameCanvas.isTouch && this.slDapdo1 > -1 && this.slDapdo1 < vecItemDapDo.size() && tabDapdo == 1) {
            this.paintFocus(g, this.xWearing[0] + this.slDapdo1 % this.colum * this.sizeDapdo + 2 - 1,
                    this.yWearing[0] + this.slDapdo1 / this.colum * this.sizeDapdo + this.sizeDapdo / 2 + 4, 3, true);
        }
        mGraphics.resetTransAndroid(g);
        g.restoreCanvas();
        GameCanvas.resetTrans(g);
        i = 0;
        while (i < this.xyDapDo.length) {
            g.drawImage(GameScr.imgBackItem, this.xyDapDo[i][0], this.xyDapDo[i][1],
                    mGraphics.VCENTER | mGraphics.HCENTER, false);
            ++i;
        }
        if (itemStone != null) {
            itemStone.paintIconWearing(g, this.xyDapDo[0][0], this.xyDapDo[0][1]);
            FontTeam.numberSmall_yeallow.drawString(g, String.valueOf(this.numStone), this.xyDapDo[0][0] - 2,
                    this.xyDapDo[0][1] + 12, 2, false);
        }
        if (itemBaohiem != null) {
            itemBaohiem.paintIconWearing(g, this.xyDapDo[1][0], this.xyDapDo[1][1]);
        }
        if (itemBua != null) {
            itemBua.paintIconWearing(g, this.xyDapDo[2][0], this.xyDapDo[2][1]);
        }
        if (!GameCanvas.isTouch && selected > 0 && selected < 4 && tabDapdo == 0) {
            this.paintFocus(g, this.xyDapDo[selected - 1][0] - this.size / 2 + this.size / 2,
                    this.xyDapDo[selected - 1][1] - this.size / 2 + this.size / 2, 0, true);
        }
        g.drawImage(GameScr.imgBackItem, xposItem, ypostItem, mGraphics.VCENTER | mGraphics.HCENTER, false);
        if (mItem != null) {
            mItem.paintIconWearing(g, xposItem, ypostItem);
        }
        if (isHopda && ID_CUONG_HOA != -1 && (img = GameData.getImgIcon((short) (ID_CUONG_HOA + Res.ID_ITEM))) != null
                && img.img != null) {
            g.drawImage(img.img, xposItem, ypostItem, 3, true);
        }
        if (selected == 3 && GameCanvas.isTouch) {
            this.paintFocus(g, xposItem - this.size / 2 + this.size / 2, ypostItem - this.size / 2 + this.size / 2, 0,
                    true);
        }
        if (!GameCanvas.isTouch && selected == 0 && tabDapdo == 0) {
            this.paintFocus(g, xposItem - this.size / 2 + this.size / 2, ypostItem - this.size / 2 + this.size / 2, 0,
                    true);
        }
        if (!textPercent.equals("")) {
            mFont.tahoma_7_green.drawString(g, T.tile, xposItem, ypostItem + 13 + 20, 2, false);
            mFont.tahoma_7_green.drawString(g, textPercent, xposItem, ypostItem + 10 + 13 + 20, 2, false);
        }
        g.drawRegion(GameScr.imgBoder[4], 0, 0, 16, 16, 0, xbg, ybg, mGraphics.TOP | mGraphics.LEFT, false);
        g.drawRegion(GameScr.imgBoder[4], 0, 0, 16, 16, 2, xbg + 2 * this.size, ybg, mGraphics.TOP | mGraphics.RIGHT,
                false);
        g.drawRegion(GameScr.imgBoder[4], 0, 16, 16, 16, 0, xbg, ybg += hShowText1 + (this.isAnimal ? this.size : 0),
                mGraphics.BOTTOM | mGraphics.LEFT, false);
        g.drawRegion(GameScr.imgBoder[4], 0, 16, 16, 16, 2, xbg + 2 * this.size, ybg,
                mGraphics.BOTTOM | mGraphics.RIGHT, false);
        if (effDapDo != null) {
            effDapDo.paintTop(g, xposItem, ypostItem);
        }
        if (xuCuongHoa > 0) {
            g.drawRegion(GameScr.imgMoney, 0, moneyType == 1 ? 0 : 7, 9, 7, 0, this.xyDapDo[1][0] - 15,
                    this.xyDapDo[1][1] + 19, 0, false);
            FontTeam.numberSmall_white.drawString(g, Res.getDotNumber(xuCuongHoa), this.xyDapDo[1][0] - 5,
                    this.xyDapDo[1][1] + 18, 0, false);
        }
    }

    private void paintCharWearing(mGraphics g, boolean isChar) {
        int yShowText1 = this.yInfoWearing + 2;
        int xShowText1 = this.xInfoWearing;
        int wShowText1 = this.wInfoWearing + 2;
        int hShowText1 = this.hInfoWearing - 4 + (this.isAnimal ? this.size : 0) - this.size + this.hIP / 2;
        g.setColor(-13232632);
        g.fillRect(xShowText1, yShowText1, wShowText1, hShowText1, false);
        g.setColor(-1596632);
        g.fillRect(xShowText1 + 1, yShowText1 + 1, wShowText1 - 2, hShowText1 - 2, false);
        g.setColor(-13232632);
        g.fillRect(xShowText1 + 2, yShowText1 + 2, wShowText1 - 4, hShowText1 - 4, false);
        g.setColor(-14864849);
        g.fillRect(xShowText1 + 3, yShowText1 + 3, wShowText1 - 6, hShowText1 - 6, false);
        g.drawRegion(GameScr.imgBoder[6], 0, 0, 9, 9, 7, xShowText1 + wShowText1, yShowText1 + hShowText1,
                mGraphics.BOTTOM | mGraphics.RIGHT, false);
        g.drawRegion(GameScr.imgBoder[6], 0, 0, 9, 9, 1, xShowText1, yShowText1 + hShowText1,
                mGraphics.BOTTOM | mGraphics.LEFT, false);
        g.drawRegion(GameScr.imgBoder[6], 0, 0, 9, 9, 0, xShowText1, yShowText1, mGraphics.TOP | mGraphics.LEFT, false);
        g.drawRegion(GameScr.imgBoder[6], 0, 0, 9, 9, 2, xShowText1 + wShowText1, yShowText1,
                mGraphics.TOP | mGraphics.RIGHT, false);
        int xbg = this.x + this.size + 8;
        int ybg = yShowText1;
        if (GameCanvas.w > 200) {
            g.setColor(-9751532);
            int hh0 = hShowText1 + (this.isAnimal ? this.size : 0);
            g.fillRect(xbg + 3, ybg, 2 * this.size - 6, hh0, false);
            g.fillRect(xbg, ybg + 3, 2 * this.size, hh0 - 6, false);
            g.setColor(-4891370);
            g.drawLine(xbg + 3, ybg + 3 + hh0 - 6 + 1, xbg + 2 * this.size - 6, ybg + 3 + hh0 - 6 + 1, false);
            if (isFocusCharWearing && GameCanvas.gameTick / 4 % 4 != 0) {
                g.setColor(-12246258);
            } else {
                g.setColor(-14864849);
            }
            g.fillRect(xbg + 3, ybg + 3, 2 * this.size - 6, hh0 - 6, false);
            g.setColor(-110);
            g.fillRect(xbg + 3, ybg + 1, 2 * this.size - 6, 1, false);
            g.setColor(-4034289);
            g.fillRect(xbg + 1, ybg + 16, 1, hh0 - 18, false);
            g.fillRect(xbg - 2 + 2 * this.size, ybg + 16, 1, hh0 - 18, false);
        }
        g.drawRegion(GameScr.imgBoder[4], 0, 0, 16, 16, 0, xbg, ybg, mGraphics.TOP | mGraphics.LEFT, false);
        g.drawRegion(GameScr.imgBoder[4], 0, 0, 16, 16, 2, xbg + 2 * this.size, ybg, mGraphics.TOP | mGraphics.RIGHT,
                false);
        g.drawRegion(GameScr.imgBoder[4], 0, 16, 16, 16, 0, xbg, ybg += hShowText1 + (this.isAnimal ? this.size : 0),
                mGraphics.BOTTOM | mGraphics.LEFT, false);
        g.drawRegion(GameScr.imgBoder[4], 0, 16, 16, 16, 2, xbg + 2 * this.size, ybg,
                mGraphics.BOTTOM | mGraphics.RIGHT, false);
        FontTeam.numberSmall_white.drawString(g, this.tile[0], xbg + this.size,
                this.y + this.h - 2 * this.size - this.size / 2 - (GameCanvas.isSmallScreen ? 24 : 38) + this.hIP + 2,
                2, false);
        if (isChar && charWearing != null) {
            charWearing.paint(g, xbg + this.size - 1,
                    this.y + this.h - 2 * this.size - this.size / 2 - (GameCanvas.isSmallScreen ? 24 : 38) + this.hIP,
                    0);
        } else {
            charWearing.paint(g, xbg + this.size - 1, this.y + this.h - 3 * this.size - this.size / 2
                    - (GameCanvas.isSmallScreen ? 24 : 38) + this.size + this.hIP, 0);
        }
        int i = 0;
        while (i < this.xWearing.length) {
            g.drawImage(GameScr.imgBackItem, this.xWearing[i], this.yWearing[i] + this.size / 2,
                    mGraphics.VCENTER | mGraphics.HCENTER, false);
            ++i;
        }
        if (isChar) {
            if (this.indexWearing <= 0) {
                i = 0;
                while (i < MainMenu.charWearing.wearing.length) {
                    if (charWearing == null || MainMenu.charWearing.equip == null
                            || this.maptopaintIconTrangBi[i] >= MainMenu.charWearing.equip.length
                            || MainMenu.charWearing.equip[this.maptopaintIconTrangBi[i]] == null) {
                        if (GameScr.imgW != null && i < 12) {
                            g.drawRegion(GameScr.imgW, 0, (i + 1) * 17, 17, 17, 0,
                                    this.xWearing[this.maptopaintIconTrangBi[i]],
                                    this.yWearing[this.maptopaintIconTrangBi[i]] + this.size / 2,
                                    mGraphics.VCENTER | mGraphics.HCENTER, false);
                        } else if (this.maptopaintIconTrangBi[i] != -1) {
                            g.drawRegion(GameScr.imgW, 0, 0, 17, 17, 0, this.xWearing[this.maptopaintIconTrangBi[i]],
                                    this.yWearing[this.maptopaintIconTrangBi[i]] + this.size / 2,
                                    mGraphics.VCENTER | mGraphics.HCENTER, false);
                        }
                    }
                    if (i == 2 && GameScr.imgW != null && i < 12) {
                        g.drawRegion(GameScr.imgW, 0, (i + 1) * 17, 17, 17, 0, this.xWearing[4],
                                this.yWearing[4] + this.size / 2, mGraphics.VCENTER | mGraphics.HCENTER, false);
                    }
                    ++i;
                }
            }
            i = 0;
            while (i < this.xWearing.length) {
                if (charWearing != null && MainMenu.charWearing.equip != null
                        && i + this.indexWearing < MainMenu.charWearing.equip.length
                        && MainMenu.charWearing.equip[i + this.indexWearing] != null) {
                    MainMenu.charWearing.equip[i + this.indexWearing].paintIconWearing(g, this.xWearing[i],
                            this.yWearing[i] + this.size / 2);
                }
                ++i;
            }
        }
        if (this.isShowFill) {
            int h0 = GameCanvas.isTouch ? 24 : 0;
            g.setColor(-13232632);
            g.fillRect(this.xItemFill, this.yItemFill - h0, this.wItemFill - 1, this.hItemFill + h0, false);
            g.setColor(-3377408);
            g.drawRect(this.xItemFill, this.yItemFill - h0, this.wItemFill - 1, this.hItemFill + h0, false);
            if (GameCanvas.isTouch) {
                int hc = mGraphics.getImageHeight(GameScr.imgButton[4]) / 2;
                int wc = mGraphics.getImageWidth(GameScr.imgButton[4]);
                g.drawRegion(GameScr.imgButton[4], 0, (this.isFocusCloseItemFill ? 1 : 0) * hc, wc, hc, 0,
                        this.xItemFill + this.wItemFill - wc - 2, this.yItemFill - h0 + 2, 0, false);
                FontTeam.normalFont[0].drawString(g, "Vật phẩm", this.xItemFill + this.wItemFill / 2,
                        this.yItemFill - h0 / 2 - 5, 2, false);
                g.fillRect(this.xItemFill + 2, this.yItemFill, this.wItemFill - 5, 1, false);
            }
            int size0 = this.totalItemFill.size();
            GameCanvas.resetTrans(g);
            this.cmrItem.setStyle(size0, this.size, this.xItemFill, this.yItemFill, this.wItemFill, this.hItemFill,
                    false, this.colum);
            g.ClipRec(this.cmrItem.xPos + 1, this.cmrItem.yPos, this.cmrItem.width - 2, this.cmrItem.height);
            this.cmrItem.setClip(g, this.cmrItem.xPos + 1, this.cmrItem.yPos, this.cmrItem.width - 2,
                    this.cmrItem.height);
            if (selected > -1 && selected <= this.totalItemFill.size() - 1 && !isFocusCharWearing
                    && !isFocusDetailMenu) {
                this.paintFocus(g, this.xItemFill + selected * this.size - 4 + this.size / 2,
                        this.yItemFill - 2 + this.size / 2, 0, true);
            }
            mGraphics.resetTransAndroid(g);
            g.restoreCanvas();
        }
        if (selected > -1 && selected < this.xWearing.length && !isFocusCharWearing && !isFocusDetailMenu) {
            this.paintFocus(g, this.xWearing[selected] - this.size / 2 + this.size / 2,
                    this.yWearing[selected] + this.size / 2, 0, true);
        }
        if (!GameCanvas.isTouch) {
            GameCanvas.resetTrans(g);
        }
        int w0 = FontTeam.smallFontArr[0].getWidth("\u0110i\u1ec3m: ");
        FontTeam.smallFontArr[0].drawString(g, "\u0110i\u1ec3m: ", this.xyTiemNang[0][0] - this.size / 2 + 3,
                this.y + this.size + 18 - this.hIP / 3 - 1, 0, false);
        FontTeam.normalFont[1].drawString(g, "" + Char.Diemtiemnang, this.xyTiemNang[0][0] - this.size / 2 + 3 + w0,
                this.y + this.size + 18 - this.hIP / 3 - 1, 0, false);
        String[] a = new String[] { String.valueOf(Char.sucmanh) + "".trim(), String.valueOf(Char.thanphap) + "".trim(),
                String.valueOf(Char.linhkhi) + "".trim(), String.valueOf(Char.sinhkhi) + "".trim() };
        int ysss = 0;
        if (mSystem.isIP && mGraphics.zoomLevel == 3) {
            ysss = 1;
        }
        int i2 = 0;
        while (i2 < 4) {
            g.drawImage(GameScr.imgBackItem, this.xyTiemNang[i2][0], this.xyTiemNang[i2][1],
                    mGraphics.VCENTER | mGraphics.HCENTER, false);
            g.drawRegion(GameScr.imgIconTN, 0, 19 * i2, 19, 19, 0, this.xyTiemNang[i2][0], this.xyTiemNang[i2][1],
                    mGraphics.VCENTER | mGraphics.HCENTER, false);
            if (a != null && i2 < a.length) {
                FontTeam.numberSmall_white.drawString(g, a[i2], this.xyTiemNang[i2][0],
                        this.xyTiemNang[i2][1] + this.size / 2 - 2 - ysss, 2, false);
            }
            if (selected == i2 && selected > -1 && selected < this.xyTiemNang.length && isFocusDetailMenu) {
                this.paintFocus(g, this.xyTiemNang[i2][0] - this.size / 2 + this.size / 2,
                        this.xyTiemNang[i2][1] - this.size / 2 + this.size / 2, 0, true);
            }
            ++i2;
        }
    }

    private void paintPetWearing(mGraphics g, boolean isChar) {
        if (!isPet) {
            return;
        }
        int yShowText1 = this.yInfoWearing + 2;
        int xShowText1 = this.xInfoWearing;
        int wShowText1 = this.wInfoWearing + 2;
        int hShowText1 = this.hInfoWearing - 4 + (this.isAnimal ? this.size : 0) - this.size + this.hIP / 2;
        g.setColor(-13232632);
        g.fillRect(xShowText1, yShowText1, wShowText1, hShowText1, false);
        g.setColor(-1596632);
        g.fillRect(xShowText1 + 1, yShowText1 + 1, wShowText1 - 2, hShowText1 - 2, false);
        g.setColor(-13232632);
        g.fillRect(xShowText1 + 2, yShowText1 + 2, wShowText1 - 4, hShowText1 - 4, false);
        g.setColor(-14864849);
        g.fillRect(xShowText1 + 3, yShowText1 + 3, wShowText1 - 6, hShowText1 - 6, false);
        g.drawRegion(GameScr.imgBoder[6], 0, 0, 9, 9, 7, xShowText1 + wShowText1, yShowText1 + hShowText1,
                mGraphics.BOTTOM | mGraphics.RIGHT, false);
        g.drawRegion(GameScr.imgBoder[6], 0, 0, 9, 9, 1, xShowText1, yShowText1 + hShowText1,
                mGraphics.BOTTOM | mGraphics.LEFT, false);
        g.drawRegion(GameScr.imgBoder[6], 0, 0, 9, 9, 0, xShowText1, yShowText1, mGraphics.TOP | mGraphics.LEFT, false);
        g.drawRegion(GameScr.imgBoder[6], 0, 0, 9, 9, 2, xShowText1 + wShowText1, yShowText1,
                mGraphics.TOP | mGraphics.RIGHT, false);
        int xbg = this.x + this.size + 8;
        int ybg = yShowText1;
        if (GameCanvas.w > 200) {
            g.setColor(-9751532);
            int hh0 = hShowText1 + (this.isAnimal ? this.size : 0);
            g.fillRect(xbg + 3, ybg, 2 * this.size - 6, hh0, false);
            g.fillRect(xbg, ybg + 3, 2 * this.size, hh0 - 6, false);
            g.setColor(-4891370);
            g.drawLine(xbg + 3, ybg + 3 + hh0 - 6 + 1, xbg + 2 * this.size - 6, ybg + 3 + hh0 - 6 + 1, false);
            if (this.isFocusPetWearing && GameCanvas.gameTick / 4 % 4 != 0) {
                g.setColor(-12246258);
            } else {
                g.setColor(-14864849);
            }
            g.fillRect(xbg + 3, ybg + 3, 2 * this.size - 6, hh0 - 6, false);
            g.setColor(-110);
            g.fillRect(xbg + 3, ybg + 1, 2 * this.size - 6, 1, false);
            g.setColor(-4034289);
            g.fillRect(xbg + 1, ybg + 16, 1, hh0 - 18, false);
            g.fillRect(xbg - 2 + 2 * this.size, ybg + 16, 1, hh0 - 18, false);
        }
        g.drawRegion(GameScr.imgBoder[4], 0, 0, 16, 16, 0, xbg, ybg, mGraphics.TOP | mGraphics.LEFT, false);
        g.drawRegion(GameScr.imgBoder[4], 0, 0, 16, 16, 2, xbg + 2 * this.size, ybg, mGraphics.TOP | mGraphics.RIGHT,
                false);
        g.drawRegion(GameScr.imgBoder[4], 0, 16, 16, 16, 0, xbg, ybg += hShowText1 + (this.isAnimal ? this.size : 0),
                mGraphics.BOTTOM | mGraphics.LEFT, false);
        g.drawRegion(GameScr.imgBoder[4], 0, 16, 16, 16, 2, xbg + 2 * this.size, ybg,
                mGraphics.BOTTOM | mGraphics.RIGHT, false);
        int i = 0;
        while (i < 4) {
            g.drawRegion(GameScr.petinfo, 0, 10 * i, 10, 10, 0, this.xyTiemNang[0][0] - 20,
                    this.xyTiemNang[0][1] + i * 18 - 18, 0, false);
            if (petInfo != null) {
                this.petfont[i].drawString(g, this.getPercent(petInfo[i]), this.xyTiemNang[0][0] - 5,
                        this.xyTiemNang[0][1] + i * 18 - 18 + 1, 0, false);
            }
            ++i;
        }
        g.fillRect(0, 0, 0, 0, false);
        this.paintCell(g);
        if (this.mpet != null) {
            this.mpet.paintnoHeight(g, xbg + this.size - 1,
                    this.y + this.h - 2 * this.size - this.size / 2 - (GameCanvas.isSmallScreen ? 24 : 38) + this.hIP);
        }
        if (cmdChoan != null && !GameCanvas.isTouch) {
            cmdChoan.paint(g);
        }
    }

    public void paintCell(mGraphics g) {
        GameCanvas.resetTrans(g);
        int maxx = vecPetEat.size();
        int t = maxx / this.colum + (vecPetEat.size() % this.colum != 0 ? 1 : 0);
        int hip3 = 0;
        if (mSystem.isIP && mGraphics.zoomLevel == 3) {
            hip3 = -5;
        }
        this.scrDapdo.setStyle(t + 3, this.sizeDapdo + 2, this.xWearing[0] - 10, this.yWearing[0] + 9,
                this.colum * this.sizeDapdo, 5 * this.sizeDapdo, true, this.colum);
        g.ClipRec(this.xWearing[0] - 20, this.yWearing[0] + 3, 142, 74 + hip3);
        this.scrDapdo.setClip(g, this.xWearing[0] - 20, this.yWearing[0] + 3, 142, 74 + hip3);
        int i = 0;
        while (i < maxx) {
            g.setColor(-14458807);
            g.drawRect(this.xWearing[0] + i % this.colum * this.sizeDapdo + 2 - 5 - 10,
                    this.yWearing[0] + i / this.colum * this.sizeDapdo + 10 - 2 + 5 - 9, this.sizeDapdo, this.sizeDapdo,
                    true);
            ++i;
        }
        i = 0;
        while (i < vecPetEat.size()) {
            Item it = (Item) vecPetEat.elementAt(i);
            if (it != null) {
                it.paint(g, this.xWearing[0] + i % this.colum * this.sizeDapdo + 1,
                        this.yWearing[0] + i / this.colum * this.sizeDapdo + this.sizeDapdo - 9, true);
            }
            ++i;
        }
        if (GameCanvas.isTouch) {
            if (selected > -1 && selected < vecPetEat.size()) {
                this.paintFocus(g, this.xWearing[0] + selected % this.colum * this.sizeDapdo + 2 - 2,
                        1 + this.yWearing[0] + selected / this.colum * this.sizeDapdo + this.sizeDapdo / 2 + 4, 3,
                        true);
            }
            this.paintFocus(g, this.xWearing[0] - 40, 1 + this.yWearing[0], 3, true);
        }
        if (!GameCanvas.isTouch && selected > -1 && selected < vecPetEat.size()) {
            this.paintFocus(g, this.xWearing[0] + selected % this.colum * this.sizeDapdo + 2 - 1,
                    this.yWearing[0] + selected / this.colum * this.sizeDapdo + this.sizeDapdo / 2 + 4, 3, true);
        }
        mGraphics.resetTransAndroid(g);
        g.restoreCanvas();
        GameCanvas.resetTrans(g);
    }

    public void paintFeatures(mGraphics g) {
        int ybg = this.y + this.sizeH + 10;
        int w0 = FontTeam.smallFontArr[0].getWidth("\u0110i\u1ec3m: ");
        FontTeam.smallFontArr[0].drawString(g, "\u0110i\u1ec3m: ", this.xbg + 2, this.y + this.h - 15, 0, false);
        FontTeam.normalFont[1].drawString(g, "" + GameScr.mainChar.basePointLeft, this.xbg + 4 + w0,
                this.y + this.h - 14, 0, false);
        String[] a = new String[] { String.valueOf(GameScr.mainChar.strength) + "".trim(),
                String.valueOf(GameScr.mainChar.agility) + "".trim(),
                String.valueOf(GameScr.mainChar.spirit) + "".trim(),
                String.valueOf(GameScr.mainChar.health) + "".trim(),
                String.valueOf(GameScr.mainChar.luck) + "".trim() };
        int i = 0;
        while (i < feaTures.length) {
            int id = 0;
            g.drawRegion(GameScr.imgTextfileld, 0, id * 18, 76, 18, 0, this.xbg + this.colum * this.size / 2, ybg + 6,
                    mGraphics.VCENTER | mGraphics.HCENTER, false);
            if (a != null && i < a.length - 1) {
                FontTeam.fontTile.drawString(g, String.valueOf(feaTures[i]) + ":" + a[i],
                        this.xbg + this.colum * this.size / 2 - 35, ybg, 0, false);
            }
            ybg += GameCanvas.w < 200 ? 22 : 30;
            ++i;
        }
    }

    public void paintFocus(mGraphics g, int x, int y, int idimage, boolean isSetclip) {
        g.drawImage(GameScr.imgfocus[idimage], x, y, 3, isSetclip);
    }

    public void paintFocus(mGraphics g, int x, int y, int idimage, int ar, boolean isSetclip) {
        g.drawImage(GameScr.imgfocus[idimage], x, y, ar, isSetclip);
    }

    public void paintFocusInvem(mGraphics g, int x, int y, int w, int h, int color, boolean isClip) {
        g.setColor(color);
        g.fillRect(x, y, w, 1, isClip);
        g.fillRect(x, y, 1, h, isClip);
        g.fillRect(x + w, y, 1, h, isClip);
        g.fillRect(x, y + h, w, 1, isClip);
    }

    public void paintFocus(mGraphics g, int x, int y, int w, int h) {
        g.drawImage(GameScr.imgBoder[2], x - 2 + this.coutFc, y - 2 + this.coutFc, 0, false);
        g.drawRegion(GameScr.imgBoder[2], 0, 0, 8, 9, 2, x + w + 3 - this.coutFc, y - 2 + this.coutFc,
                mGraphics.TOP | mGraphics.RIGHT, false);
        g.drawRegion(GameScr.imgBoder[2], 0, 0, 8, 9, 7, x + w + 3 - this.coutFc, y + h + 3 - this.coutFc,
                mGraphics.BOTTOM | mGraphics.RIGHT, false);
        g.drawRegion(GameScr.imgBoder[2], 0, 0, 8, 9, 6, x - 2 + this.coutFc, y + h + 3 - this.coutFc,
                mGraphics.BOTTOM | mGraphics.LEFT, false);
    }

    private void paintSkill(mGraphics g) {
        int hkill = mGraphics.getImageHeight(GameScr.imgTouchMove[3]) / 2;
        int wkill = mGraphics.getImageWidth(GameScr.imgTouchMove[3]);
        GameCanvas.resetTrans(g);
        int sizesk = GameScr.vec_skill.size();
        this.scrSkill.setStyle((sizesk + 1) / 3, 40, this.xWearing[0] + 5 - 15, this.yWearing[0] + this.size * 2 - 15,
                this.wInfoWearing + 20 + this.size, this.size * 5, true, 3);
        g.ClipRec(this.xWearing[0] - this.size / 2, this.yWearing[0] + this.size + 7,
                this.wInfoWearing + 20 + this.size, this.size * 5 - 10);
        this.scrSkill.setClip(g, this.xWearing[0] - this.size / 2, this.yWearing[0] + this.size + 7,
                this.wInfoWearing + 20 + this.size, this.size * 5 - 10);
        int i = 0;
        while (i < sizesk) {
            g.drawRegion(GameScr.imgTouchMove[3], 0, hkill, wkill, hkill, 0, this.xWearing[i] + 5,
                    this.yWearing[i] + this.size * 2, 3, true);
            if (selected == i) {
                this.paintFocus(g, this.xWearing[i] + 5, this.yWearing[i] + this.size * 2, 1, true);
            }
            SkillTemplate skill = (SkillTemplate) GameScr.vec_skill.elementAt(i);
            ImageIcon imgskill = GameData
                    .getImgIcon((short) (skill.iconId + (Char.levelSkill[i] == -1 ? 200 : 0) + Res.ID_ICON_SKILL));
            if (imgskill != null && imgskill.img != null) {
                g.drawImage(imgskill.img, this.xWearing[i] + 5, this.yWearing[i] + this.size * 2, 3, true);
            } else {
                g.drawRegion(GameScr.imgloading, 0, GameCanvas.gameTick % 12 * ChangScr.himg, ChangScr.wimg,
                        ChangScr.himg, 0, this.xWearing[i] + 5, this.yWearing[i] + this.size * 2, 3, true);
            }
            ++i;
        }
        mGraphics.resetTransAndroid(g);
        g.restoreCanvas();
        GameCanvas.resetTrans(g);
        mFont.tahoma_7b_white.drawString(g, T.diem, this.xWearing[1] + this.size / 4, this.yWearing[0], 2, false);
        FontTeam.normalFont[1].drawString(g, "" + Char.Skill_Point, this.xWearing[1] + this.size / 4 + 20,
                this.yWearing[0], 0, false);
        g.setColor(-1136094);
        g.fillRect(this.xWearing[0], this.yWearing[0] + this.size - 3, this.wInfoWearing + 20, 1, false);
    }

    public void paintLoop(mGraphics g, int x, int y, Image img) {
        int i = GameCanvas.w % 64;
        while (i < GameCanvas.w + 64) {
            int j = GameCanvas.h % 64;
            while (j < GameCanvas.h + 64) {
                g.drawImage(GameScr.imgBgMainMenu, GameCanvas.w - i, GameCanvas.h - j, mGraphics.TOP | mGraphics.LEFT,
                        false);
                j += 64;
            }
            i += 64;
        }
    }

    public void paintMoney(mGraphics g) {
        if (GameCanvas.isTouch) {
            this.paintHP(g);
        }
        if (GameCanvas.isTouch) {
            int xss = 0;
            int yp = 0;
            yp = (this.y + this.sizeH - 14) / 2 + (this.y + this.sizeH - 14) / 2 - 4;
            xss = this.xShowText + this.wShowText - 42;
            String xu = Res.getDotNumber(GameScr.mainChar.charXu);
            String luong = Res.getDotNumber(GameScr.mainChar.luong);
            int ypp = 0;
            if (mSystem.isAndroid) {
                ypp = -3;
            }
            if (mSystem.isIP) {
                ypp = -1;
            }
            g.drawRegion(GameScr.imgMoney, 0, 7, 9, 7, 0, xss + 5, yp, 0, false);
            FontTeam.numberSmall_white.drawString(g, xu, xss, yp + ypp - 1, 1, false);
            g.drawRegion(GameScr.imgMoney, 0, 0, 9, 7, 0, xss + 5, yp + 11, 0, false);
            FontTeam.numberSmall_white.drawString(g, luong, xss, yp + 10 + ypp, 1, false);
        } else {
            int xpxu = 0;
            int xpluong = 0;
            int xss = 0;
            int xmn = 0;
            String xu = Res.getDotNumber(GameScr.mainChar.charXu);
            String luong = Res.getDotNumber(GameScr.mainChar.luong);
            xpxu = FontTeam.numberSmall_white.getWidth(xu);
            xss = xpxu > (xpluong = FontTeam.numberSmall_white.getWidth(luong)) ? xpxu : xpluong;
            xmn = GameCanvas.w - 50 - xss;
            if (!GameCanvas.isTouch) {
                xmn = 10;
            }
            this.paintMoney(g, xmn, 5, xmn, 5, xu, luong);
        }
    }

    public void paintMoney(mGraphics g, int x1, int y1, int x2, int y2, String xu, String luong) {
        g.drawRegion(GameScr.imgMoney, 0, 7, 9, 7, 0, x1, y1, 0, false);
        FontTeam.numberSmall_white.drawString(g, xu, x1 + 10, y1 - 2, 0, false);
        g.drawRegion(GameScr.imgMoney, 0, 0, 9, 7, 0, x2, y2 + 9, 0, false);
        FontTeam.numberSmall_white.drawString(g, luong, x2 + 10, y2 + 9, 0, false);
    }

    public void setTile() {
        if (this.indexMainTab == 12 || this.indexMainTab == 8 || this.indexMainTab == 9 || this.indexMainTab == 10
                || this.indexMainTab == 11|| this.indexMainTab == 4) {
            return;
        }
        this.currentTileMainTab = this.indexMainTab < mainTab.length ? mainTab[this.indexMainTab] : "";
    }

    private void setSelectedKeepAndSellItem() {
        this.center = new mCommand("", this, 33);
    }

    public void paintHP(mGraphics g) {
        if (mSystem.isPC) {
            return;
        }
        int h123 = (this.y + this.sizeH - 18) / 2 + (this.y + this.sizeH - 18) / 2 - 1;
        int xp = this.x;
        g.drawRegion(GameScr.imghealth[2], 0, 0, mGraphics.getImageWidth(GameScr.imghealth[2]),
                mGraphics.getImageHeight(GameScr.imghealth[2]) / 2, 0, xp, h123, 0, false);
        g.drawRegion(GameScr.imghealth[2], 0, mGraphics.getImageHeight(GameScr.imghealth[2]) / 2,
                mGraphics.getImageWidth(GameScr.imghealth[2]), mGraphics.getImageHeight(GameScr.imghealth[2]) / 2, 0,
                xp, h123 + 10, 0, false);
        int hpPaint = 0;
        int mpPaint = 0;
        if (GameScr.mainChar.hp > 0) {
            hpPaint = (int) (GameScr.mainChar.hp * 60 / GameScr.mainChar.maxhp);
            if (hpPaint <= 0) {
                hpPaint = 1;
            } else if (hpPaint > 60) {
                hpPaint = 60;
            }
            g.drawRegion(GameScr.imghealth[1], 0, 0, hpPaint, mGraphics.getImageHeight(GameScr.imghealth[1]) / 2, 0,
                    xp + 1, h123 + 1, 0, true);
        }
        if (GameScr.mainChar.mp > 0) {
            mpPaint = GameScr.mainChar.mp * 60 / GameScr.mainChar.maxmp;
            if (mpPaint <= 0) {
                mpPaint = 1;
            } else if (mpPaint > 60) {
                mpPaint = 60;
            }
            g.drawRegion(GameScr.imghealth[1], 0, mGraphics.getImageHeight(GameScr.imghealth[1]) / 2, mpPaint,
                    mGraphics.getImageHeight(GameScr.imghealth[1]) / 2, 0, xp + 1, h123 + 11, 0, true);
        }
        FontTeam.numberSmall_white.drawString(g, String.valueOf(GameScr.mainChar.hp) + "/" + GameScr.mainChar.maxhp,
                xp + 30, h123, 2, false);
        FontTeam.numberSmall_white.drawString(g, String.valueOf(GameScr.mainChar.mp) + "/" + GameScr.mainChar.maxmp,
                xp + 30, h123 + 10, 2, false);
    }

    public void paintChuyenhoa(mGraphics g) {
        int yShowText1 = this.yInfoWearing + 2;
        int xShowText1 = this.xInfoWearing;
        int wShowText1 = this.wInfoWearing + 2;
        int xbg = this.x + this.size + 9;
        int hShowText1 = this.hInfoWearing - 4 + (this.isAnimal ? this.size : 0) - this.size + this.hIP / 2 + 20;
        int ws = 135;
        g.setColor(-13232632);
        g.fillRect(xbg, yShowText1, ws, hShowText1, false);
        g.setColor(-1596632);
        g.fillRect(xbg + 1, yShowText1 + 1, ws - 2, hShowText1 - 2, false);
        g.setColor(-13232632);
        g.fillRect(xbg + 2, yShowText1 + 2, ws - 4, hShowText1 - 4, false);
        g.setColor(-14864849);
        g.fillRect(xbg + 3, yShowText1 + 3, ws - 6, hShowText1 - 6, false);
        g.drawRegion(GameScr.imgBoder[6], 0, 0, 9, 9, 7, xShowText1 + wShowText1 - 1, yShowText1 + hShowText1,
                mGraphics.BOTTOM | mGraphics.RIGHT, false);
        g.drawRegion(GameScr.imgBoder[6], 0, 0, 9, 9, 1, xbg, yShowText1 + hShowText1,
                mGraphics.BOTTOM | mGraphics.LEFT, false);
        g.drawRegion(GameScr.imgBoder[6], 0, 0, 9, 9, 0, xbg, yShowText1, mGraphics.TOP | mGraphics.LEFT, false);
        g.drawRegion(GameScr.imgBoder[6], 0, 0, 9, 9, 2, xShowText1 + wShowText1 - 1, yShowText1,
                mGraphics.TOP | mGraphics.RIGHT, false);
        g.drawRegion(GameScr.imgBoder[4], 0, 16, 16, 16, 2, -100, -100, mGraphics.BOTTOM | mGraphics.RIGHT, false);
        GameCanvas.resetTrans(g);
        int maxx = vecItemCreate.size();
        if (maxx < 45) {
            maxx = 45;
        }
        int t = maxx / this.colum + (MainChar.MaxInven % this.colum != 0 ? 1 : 0);
        int hip3 = 0;
        if (mSystem.isIP && mGraphics.zoomLevel == 3) {
            hip3 = -5;
        }
        this.scrDapdo.setStyle(t + 3, this.sizeDapdo + 2, this.xWearing[0] - 10, this.yWearing[0] + 9 + 10,
                this.colum * this.sizeDapdo, 5 * this.sizeDapdo, true, this.colum);
        g.ClipRec(this.xWearing[0] - 20, this.yWearing[0] + 3 + 20, 142, 74 + hip3 - 20);
        this.scrDapdo.setClip(g, this.xWearing[0] - 20, this.yWearing[0] + 3 + 20, 142, 74 + hip3 - 20);
        int i = 0;
        while (i < maxx) {
            g.setColor(-14458807);
            g.drawRect(this.xWearing[0] + i % this.colum * this.sizeDapdo + 2 - 5 - 10,
                    this.yWearing[0] + i / this.colum * this.sizeDapdo + 10 - 2 + 5 - 9 + 20, this.sizeDapdo,
                    this.sizeDapdo, true);
            ++i;
        }
        i = 0;
        while (i < vecItemCreate.size()) {
            Item it = (Item) vecItemCreate.elementAt(i);
            if (it != null) {
                it.paint(g, this.xWearing[0] + i % this.colum * this.sizeDapdo + 1,
                        this.yWearing[0] + i / this.colum * this.sizeDapdo + this.sizeDapdo - 9 + 20, true);
            }
            ++i;
        }
        if (this.slDapdo1 > -1) {
            g.setColor(-1);
            g.drawRect(this.xWearing[0] + this.slDapdo1 % this.colum * this.sizeDapdo + 2 - 1 - 13,
                    1 + this.yWearing[0] + this.slDapdo1 / this.colum * this.sizeDapdo + this.sizeDapdo / 2 + 4 + 7, 25,
                    25, true);
        }
        mGraphics.resetTransAndroid(g);
        g.restoreCanvas();
        GameCanvas.resetTrans(g);
        if (!mSystem.isj2me) {
            g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 1, this.x + 1 + this.wShowText - 15, this.y + this.h,
                    mGraphics.BOTTOM | mGraphics.LEFT, false);
            g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 0, this.x + this.wShowText + 1 - 15, this.y + this.sizeH,
                    mGraphics.TOP | mGraphics.LEFT, false);
        }
        g.drawImage(GameScr.imgSao, this.xSao, this.ySao, 3, false);
        g.drawImage(GameScr.imgBackItem, this.xcenter, this.ycenter - 30 + 5, 3, false);
        g.drawImage(GameScr.imgBackItem, this.xcenter - 42 + 10, this.ycenter + 30, 3, false);
        g.drawImage(GameScr.imgBackItem, this.xcenter + 42 - 10, this.ycenter + 30, 3, false);
        if (itemChuyenHoa0 != null) {
            itemChuyenHoa0.paintIconWearing(g, this.xcenter, this.ycenter - 30 + 5);
        }
        if (itemChuyenHoa1 != null) {
            itemChuyenHoa1.paintIconWearing(g, this.xcenter - 42 + 10, this.ycenter + 30);
        }
        if (itemChuyenHoa2 != null) {
            itemChuyenHoa2.paintIconWearing(g, this.xcenter + 42 - 10, this.ycenter + 30);
        }
        g.setColor(-1);
        if (this.tickChuyenhoa == 0) {
            g.drawRect(this.xcenter - 11, this.ycenter - 11 - 30 + 5, 21, 21, true);
        }
        if (this.tickChuyenhoa == 1) {
            g.drawRect(this.xcenter - 42 - 11 + 10, this.ycenter - 11 + 30, 21, 21, true);
        }
        if (this.tickChuyenhoa == 2) {
            g.drawRect(this.xcenter + 42 - 11 - 10, this.ycenter - 11 + 30, 21, 21, true);
        }
        if (!mSystem.isj2me) {
            g.setColor(-4034289);
            g.fillRect(this.x + this.wShowText - 15, this.y + this.sizeH + 2, 1, this.h - this.sizeH - 3, false);
            g.setColor(-11262464);
            g.fillRect(this.x + this.wShowText - 1 - 15, this.y + this.sizeH + 2 + 1, 1, this.h - this.sizeH - 3 - 2,
                    false);
            g.fillRect(this.x + this.wShowText + 1 - 15, this.y + this.sizeH + 2 + 1, 1, this.h - this.sizeH - 3 - 2,
                    false);
        }
        if (effDapDo != null) {
            effDapDo.paintTop(g, this.xcenter, this.ycenter - 30);
        }
        this.paintEffectChuyenHoa(g);
    }

    public void paintEffectChuyenHoa(mGraphics g) {
        int i = 0;
        while (i < this.vEffect.size()) {
            Effect e = (Effect) this.vEffect.elementAt(i);
            if (e != null) {
                e.paint(g);
            }
            ++i;
        }
        if (this.beginChedo) {
            Image img = EffectSkill.getImage(37);
            Image img2 = EffectSkill.getImage(42);
            try {
                if (img != null) {
                    g.drawRegion(img, 0, this.f * EffectSkill.h[37], EffectSkill.w[37], EffectSkill.h[37], 0,
                            this.xcenter, this.ycenter - 30 + 10, mGraphics.VCENTER | mGraphics.HCENTER, false);
                }
                if (img2 != null) {
                    g.drawRegion(img2, 0, this.f * EffectSkill.h[42], EffectSkill.w[42], EffectSkill.h[42], 0,
                            this.xcenter - 42 + 10, this.ycenter + 30, mGraphics.VCENTER | mGraphics.HCENTER, false);
                    if (itemChuyenHoa2 != null) {
                        g.drawRegion(img2, 0, this.f * EffectSkill.h[42], EffectSkill.w[42], EffectSkill.h[42], 0,
                                this.xcenter + 42 - 10, this.ycenter + 30, mGraphics.VCENTER | mGraphics.HCENTER,
                                false);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void paintPhiPhong(mGraphics g) {
        int yShowText1 = this.yInfoWearing + 2;
        int xShowText1 = this.xInfoWearing;
        int wShowText1 = this.wInfoWearing + 2;
        int xbg = this.x + this.size + 9;
        int hShowText1 = this.hInfoWearing - 4 + (this.isAnimal ? this.size : 0) - this.size + this.hIP / 2 + 20;
        int ws = 135;
        g.setColor(-13232632);
        g.fillRect(xbg, yShowText1, ws, hShowText1, false);
        g.setColor(-1596632);
        g.fillRect(xbg + 1, yShowText1 + 1, ws - 2, hShowText1 - 2, false);
        g.setColor(-13232632);
        g.fillRect(xbg + 2, yShowText1 + 2, ws - 4, hShowText1 - 4, false);
        g.setColor(-14864849);
        g.fillRect(xbg + 3, yShowText1 + 3, ws - 6, hShowText1 - 6, false);
        g.drawRegion(GameScr.imgBoder[6], 0, 0, 9, 9, 7, xShowText1 + wShowText1 - 1, yShowText1 + hShowText1,
                mGraphics.BOTTOM | mGraphics.RIGHT, false);
        g.drawRegion(GameScr.imgBoder[6], 0, 0, 9, 9, 1, xbg, yShowText1 + hShowText1,
                mGraphics.BOTTOM | mGraphics.LEFT, false);
        g.drawRegion(GameScr.imgBoder[6], 0, 0, 9, 9, 0, xbg, yShowText1, mGraphics.TOP | mGraphics.LEFT, false);
        g.drawRegion(GameScr.imgBoder[6], 0, 0, 9, 9, 2, xShowText1 + wShowText1 - 1, yShowText1,
                mGraphics.TOP | mGraphics.RIGHT, false);
        g.drawRegion(GameScr.imgBoder[4], 0, 16, 16, 16, 2, -100, -100, mGraphics.BOTTOM | mGraphics.RIGHT, false);
        GameCanvas.resetTrans(g);
        int maxx = vecItemCreate.size();
        if (maxx < 45) {
            maxx = 45;
        }
        int t = maxx / this.colum + (MainChar.MaxInven % this.colum != 0 ? 1 : 0);
        int hip3 = 0;
        if (mSystem.isIP && mGraphics.zoomLevel == 3) {
            hip3 = -5;
        }
        this.scrDapdo.setStyle(t + 3, this.sizeDapdo + 2, this.xWearing[0] - 10, this.yWearing[0] + 9 + 10,
                this.colum * this.sizeDapdo, 5 * this.sizeDapdo, true, this.colum);
        g.ClipRec(this.xWearing[0] - 20, this.yWearing[0] + 3 + 20, 142, 74 + hip3 - 20);
        this.scrDapdo.setClip(g, this.xWearing[0] - 20, this.yWearing[0] + 3 + 20, 142, 74 + hip3 - 20);
        int i = 0;
        while (i < maxx) {
            g.setColor(-14458807);
            g.drawRect(this.xWearing[0] + i % this.colum * this.sizeDapdo + 2 - 5 - 10,
                    this.yWearing[0] + i / this.colum * this.sizeDapdo + 10 - 2 + 5 - 9 + 20, this.sizeDapdo,
                    this.sizeDapdo, true);
            ++i;
        }
        i = 0;
        while (i < vecItemCreate.size()) {
            Item it = (Item) vecItemCreate.elementAt(i);
            if (it != null) {
                it.paint(g, this.xWearing[0] + i % this.colum * this.sizeDapdo + 1,
                        this.yWearing[0] + i / this.colum * this.sizeDapdo + this.sizeDapdo - 9 + 20, true);
            }
            ++i;
        }
        if (this.slDapdo1 > -1) {
            this.paintFocus(g, this.xWearing[0] + this.slDapdo1 % this.colum * this.sizeDapdo + 2 - 2, 1
                            + this.yWearing[0] + this.slDapdo1 / this.colum * this.sizeDapdo + this.sizeDapdo / 2 + 4 + 20 - 1,
                    3, true);
        }
        mGraphics.resetTransAndroid(g);
        g.restoreCanvas();
        GameCanvas.resetTrans(g);
        if (!mSystem.isj2me) {
            g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 1, this.x + 1 + this.wShowText - 15, this.y + this.h,
                    mGraphics.BOTTOM | mGraphics.LEFT, false);
            g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 0, this.x + this.wShowText + 1 - 15, this.y + this.sizeH,
                    mGraphics.TOP | mGraphics.LEFT, false);
        }
        g.drawImage(GameScr.imgBackItem, this.xcenter, this.ycenter, 3, false);
        g.drawImage(GameScr.imgSao, this.xcenter, this.ycenter, 3, false);
        int r = 40;
        int i2 = 0;
        while (i2 < 6) {
            Item itp;
            g.drawImage(GameScr.imgBackItem, Util.cos(i2 * 60 + 30) * r / 1024 + (xbg + ws / 2),
                    Util.sin(i2 * 60 + 30) * r / 1024 + (yShowText1 + hShowText1 / 2), 3, false);
            if (this.typePhiPhong == 0 && (itp = (Item) vecMaterial.elementAt(i2)) != null) {
                itp.paintIcon(g, Util.cos(i2 * 60 + 30) * r / 1024 + (xbg + ws / 2) - 14,
                        Util.sin(i2 * 60 + 30) * r / 1024 + (yShowText1 + hShowText1 / 2) - 13);
            }
            if (this.typePhiPhong == 1 && this.listItem[i2] != null) {
                this.listItem[i2].paintIconPP(g, this.postItem_PhiPhong_X[i2], this.postItem_PhiPhong_Y[i2]);
            }
            FontTeam f = FontTeam.numberSmall_green;
            if (this.listNum[i2] < this.miniItem || this.listNum[i2] == 0) {
                f = FontTeam.numberSmall_red;
            }
            f.drawString(g, String.valueOf(this.miniItem),
                    Util.cos(i2 * 60 + 30) * r / 1024 + (xbg + ws / 2) - 14 + this.xsai[i2] - 13,
                    Util.sin(i2 * 60 + 30) * r / 1024 + (yShowText1 + hShowText1 / 2) - 13 + 8, 0, false);
            if (this.FocusPhiPhong == i2) {
                g.setColor(-1);
                g.drawRect(this.postItem_PhiPhong_X[i2] + 2, this.postItem_PhiPhong_Y[i2] + 2, 21, 21, true);
            }
            ++i2;
        }
        if (idicon != -1) {
            if (this.typePhiPhong == 1 && this.itemPP != null) {
                this.itemPP.paintIconWearing(g, this.xcenter, this.ycenter);
            }
            if (this.typePhiPhong == 0) {
                ImageIcon img = GameData.getImgIcon((short) (idicon + Res.ID_ITEM));
                if (img != null && img.img != null) {
                    g.drawImage(img.img, this.xcenter, this.ycenter, 3, false);
                } else {
                    g.drawRegion(GameScr.imgloading, 0, GameCanvas.gameTick % 12 * ChangScr.himg, ChangScr.wimg,
                            ChangScr.himg, 0, this.xcenter, this.ycenter, 3, true);
                }
            }
        }
        if (!mSystem.isj2me) {
            g.setColor(-4034289);
            g.fillRect(this.x + this.wShowText - 15, this.y + this.sizeH + 2, 1, this.h - this.sizeH - 3, false);
            g.setColor(-11262464);
            g.fillRect(this.x + this.wShowText - 1 - 15, this.y + this.sizeH + 2 + 1, 1, this.h - this.sizeH - 3 - 2,
                    false);
            g.fillRect(this.x + this.wShowText + 1 - 15, this.y + this.sizeH + 2 + 1, 1, this.h - this.sizeH - 3 - 2,
                    false);
        }
        if (effDapDo != null) {
            effDapDo.paintTop(g, this.xcenter, this.ycenter);
        }
        this.paintEffect(g);
        if (xuCuongHoa > 0) {
            g.drawRegion(GameScr.imgMoney, 0, moneyType == 1 ? 0 : 7, 9, 7, 0, this.xyDapDo[1][0] - 15 - 58,
                    this.xyDapDo[1][1] + 19 + 15, 0, false);
            FontTeam.numberSmall_white.drawString(g, Res.getDotNumber(xuCuongHoa), this.xyDapDo[1][0] - 5 - 58,
                    this.xyDapDo[1][1] + 18 + 15, 0, false);
        }
    }

    public void paintCreateItem(mGraphics g) {
        int yShowText1 = this.yInfoWearing + 2;
        int xShowText1 = this.xInfoWearing;
        int wShowText1 = this.wInfoWearing + 2;
        int xbg = this.x + this.size + 9;
        int hShowText1 = this.hInfoWearing - 4 + (this.isAnimal ? this.size : 0) - this.size + this.hIP / 2 + 20;
        int ws = 135;
        g.setColor(-13232632);
        g.fillRect(xbg, yShowText1, ws, hShowText1, false);
        g.setColor(-1596632);
        g.fillRect(xbg + 1, yShowText1 + 1, ws - 2, hShowText1 - 2, false);
        g.setColor(-13232632);
        g.fillRect(xbg + 2, yShowText1 + 2, ws - 4, hShowText1 - 4, false);
        g.setColor(-14864849);
        g.fillRect(xbg + 3, yShowText1 + 3, ws - 6, hShowText1 - 6, false);
        g.drawRegion(GameScr.imgBoder[6], 0, 0, 9, 9, 7, xShowText1 + wShowText1 - 1, yShowText1 + hShowText1,
                mGraphics.BOTTOM | mGraphics.RIGHT, false);
        g.drawRegion(GameScr.imgBoder[6], 0, 0, 9, 9, 1, xbg, yShowText1 + hShowText1,
                mGraphics.BOTTOM | mGraphics.LEFT, false);
        g.drawRegion(GameScr.imgBoder[6], 0, 0, 9, 9, 0, xbg, yShowText1, mGraphics.TOP | mGraphics.LEFT, false);
        g.drawRegion(GameScr.imgBoder[6], 0, 0, 9, 9, 2, xShowText1 + wShowText1 - 1, yShowText1,
                mGraphics.TOP | mGraphics.RIGHT, false);
        g.drawRegion(GameScr.imgBoder[4], 0, 16, 16, 16, 2, -100, -100, mGraphics.BOTTOM | mGraphics.RIGHT, false);
        GameCanvas.resetTrans(g);
        int maxx = vecItemCreate.size();
        if (maxx < 45) {
            maxx = 45;
        }
        int t = maxx / this.colum + (MainChar.MaxInven % this.colum != 0 ? 1 : 0);
        int hip3 = 0;
        if (mSystem.isIP && mGraphics.zoomLevel == 3) {
            hip3 = -5;
        }
        this.scrDapdo.setStyle(t + 3, this.sizeDapdo + 2, this.xWearing[0] - 10, this.yWearing[0] + 9 + 10,
                this.colum * this.sizeDapdo, 5 * this.sizeDapdo, true, this.colum);
        g.ClipRec(this.xWearing[0] - 20, this.yWearing[0] + 3 + 20, 142, 74 + hip3 - 20);
        this.scrDapdo.setClip(g, this.xWearing[0] - 20, this.yWearing[0] + 3 + 20, 142, 74 + hip3 - 20);
        int i = 0;
        while (i < maxx) {
            g.setColor(-14458807);
            g.drawRect(this.xWearing[0] + i % this.colum * this.sizeDapdo + 2 - 5 - 10,
                    this.yWearing[0] + i / this.colum * this.sizeDapdo + 10 - 2 + 5 - 9 + 20, this.sizeDapdo,
                    this.sizeDapdo, true);
            ++i;
        }
        i = 0;
        while (i < vecItemCreate.size()) {
            Item it = (Item) vecItemCreate.elementAt(i);
            if (it != null) {
                it.paint(g, this.xWearing[0] + i % this.colum * this.sizeDapdo + 1,
                        this.yWearing[0] + i / this.colum * this.sizeDapdo + this.sizeDapdo - 9 + 20, true);
            }
            ++i;
        }
        if (this.slDapdo1 > -1) {
            this.paintFocus(g, this.xWearing[0] + this.slDapdo1 % this.colum * this.sizeDapdo + 2 - 2, 1
                            + this.yWearing[0] + this.slDapdo1 / this.colum * this.sizeDapdo + this.sizeDapdo / 2 + 4 + 20 - 1,
                    3, true);
        }
        mGraphics.resetTransAndroid(g);
        g.restoreCanvas();
        GameCanvas.resetTrans(g);
        if (!mSystem.isj2me) {
            g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 1, this.x + 1 + this.wShowText - 15, this.y + this.h,
                    mGraphics.BOTTOM | mGraphics.LEFT, false);
            g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 0, this.x + this.wShowText + 1 - 15, this.y + this.sizeH,
                    mGraphics.TOP | mGraphics.LEFT, false);
        }
        g.drawImage(GameScr.imgBackItem, this.xcenter, this.ycenter, 3, false);
        g.drawImage(GameScr.imgSao, this.xcenter, this.ycenter, 3, false);
        int r = 40;
        int i2 = 0;
        while (i2 < 6) {
            g.drawImage(GameScr.imgBackItem, Util.cos(i2 * 60 + 30) * r / 1024 + (xbg + ws / 2),
                    Util.sin(i2 * 60 + 30) * r / 1024 + (yShowText1 + hShowText1 / 2), 3, false);
            Item itp = (Item) vecMaterial.elementAt(i2);
            if (itp != null) {
                itp.paintIcon(g, Util.cos(i2 * 60 + 30) * r / 1024 + (xbg + ws / 2) - 14,
                        Util.sin(i2 * 60 + 30) * r / 1024 + (yShowText1 + hShowText1 / 2) - 13);
            }
            FontTeam.numberSmall_white.drawString(g, String.valueOf(this.miniItem),
                    Util.cos(i2 * 60 + 30) * r / 1024 + (xbg + ws / 2) - 14 + this.xsai[i2],
                    Util.sin(i2 * 60 + 30) * r / 1024 + (yShowText1 + hShowText1 / 2) - 13 + 8, 0, false);
            FontTeam.numberSmall_white.drawString(g, "/",
                    Util.cos(i2 * 60 + 30) * r / 1024 + (xbg + ws / 2) - 14 + this.xsai[i2] - 5,
                    Util.sin(i2 * 60 + 30) * r / 1024 + (yShowText1 + hShowText1 / 2) - 13 + 8, 0, false);
            FontTeam f = FontTeam.numberSmall_green;
            if (this.listNum[i2] < this.miniItem || this.listNum[i2] == 0) {
                f = FontTeam.numberSmall_red;
            }
            f.drawString(g, String.valueOf(this.listNum[i2]),
                    Util.cos(i2 * 60 + 30) * r / 1024 + (xbg + ws / 2) - 14 + this.xsai[i2] - 13,
                    Util.sin(i2 * 60 + 30) * r / 1024 + (yShowText1 + hShowText1 / 2) - 13 + 8, 0, false);
            ++i2;
        }
        if (idicon != -1) {
            ImageIcon img = GameData.getImgIcon((short) (idicon + Res.ID_ITEM));
            if (img != null && img.img != null) {
                g.drawImage(img.img, this.xcenter, this.ycenter, 3, false);
            } else {
                g.drawRegion(GameScr.imgloading, 0, GameCanvas.gameTick % 12 * ChangScr.himg, ChangScr.wimg,
                        ChangScr.himg, 0, this.xcenter, this.ycenter, 3, true);
            }
        }
        if (!mSystem.isj2me) {
            g.setColor(-4034289);
            g.fillRect(this.x + this.wShowText - 15, this.y + this.sizeH + 2, 1, this.h - this.sizeH - 3, false);
            g.setColor(-11262464);
            g.fillRect(this.x + this.wShowText - 1 - 15, this.y + this.sizeH + 2 + 1, 1, this.h - this.sizeH - 3 - 2,
                    false);
            g.fillRect(this.x + this.wShowText + 1 - 15, this.y + this.sizeH + 2 + 1, 1, this.h - this.sizeH - 3 - 2,
                    false);
        }
        if (effDapDo != null) {
            effDapDo.paintTop(g, this.xcenter, this.ycenter);
        }
        this.paintEffect(g);
    }

    public void paintEffect(mGraphics g) {
        int i = 0;
        while (i < this.vEffect.size()) {
            Effect e = (Effect) this.vEffect.elementAt(i);
            if (e != null) {
                e.paint(g);
            }
            ++i;
        }
        if (this.beginChedo) {
            Image img = EffectSkill.getImage(37);
            Image img2 = EffectSkill.getImage(42);
            try {
                if (img != null) {
                    g.drawRegion(img, 0, this.f * EffectSkill.h[37], EffectSkill.w[37], EffectSkill.h[37], 0,
                            this.xcenter, this.ycenter, mGraphics.VCENTER | mGraphics.HCENTER, false);
                }
                if (img2 != null) {
                    int i2 = 0;
                    while (i2 < 6) {
                        g.drawRegion(img2, 0, this.f * EffectSkill.h[42], EffectSkill.w[42], EffectSkill.h[42], 0,
                                Util.cos(i2 * 60 + this.goc) * this.r / 1024 + this.xcenter,
                                Util.sin(i2 * 60 + this.goc) * this.r / 1024 + this.ycenter,
                                mGraphics.VCENTER | mGraphics.HCENTER, false);
                        ++i2;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void paint(mGraphics g) {
        try {
            int j;
            int i;
            if (mSystem.isPC || mSystem.isIP && mGraphics.zoomLevel > 3) {
                if (this.lastSCR != null) {
                    this.lastSCR.paint(g);
                }
                GameCanvas.resetTrans(g);
                g.fillRectAlpha(0, 0, GameCanvas.w, GameCanvas.h, 0, 60, false);
                g.setClip(this.x, this.y - 5 + 40, this.w + this.wShowText - 15, this.h + 5 - 40);
                g.ClipRec(this.x, this.y - 5 + 40, this.w + this.wShowText - 15, this.h + 5 - 40);
                g.fillRect(0, 0, 0, 0, false);
                i = 0;
                while (i < (this.w + this.wShowText) / 32 + 1) {
                    j = 0;
                    while (j < this.h / 32 + 1) {
                        g.drawImage(GameScr.imgBgMainMenu, this.x + i * 32, this.y + j * 32 - 5, 0, true);
                        ++j;
                    }
                    ++i;
                }
                g.restoreCanvas();
            } else {
                GameCanvas.resetTrans(g);
                i = GameCanvas.w % 32;
                while (i < GameCanvas.w + 32) {
                    j = GameCanvas.h % 32;
                    while (j < GameCanvas.h + 32) {
                        g.drawImage(GameScr.imgBgMainMenu, GameCanvas.w - i, GameCanvas.h - j,
                                mGraphics.TOP | mGraphics.LEFT, false);
                        j += 32;
                    }
                    i += 32;
                }
            }
            int xbg = this.x + this.size + 8;
            int x000 = this.x;
            int wwz = GameCanvas.isTouch ? this.xShowText + this.wShowText - xbg - 1 : this.w - this.size - 8;
            GameCanvas.resetTrans(g);
            if (GameCanvas.w > 200) {
                g.setColor(-11262464);
                g.drawRect(this.x, this.y + this.sizeH, this.size + 6, this.h - this.sizeH - 1, false);
                g.drawRect(this.x + 2, this.y + this.sizeH + 2, this.size + 2, this.h - this.sizeH - 5, false);
                g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 7, this.x + this.size + 6, this.y + this.h - 1,
                        mGraphics.BOTTOM | mGraphics.RIGHT, false);
                g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 1, this.x, this.y + this.h - 1,
                        mGraphics.BOTTOM | mGraphics.LEFT, false);
                g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 0, this.x, this.y + this.sizeH,
                        mGraphics.TOP | mGraphics.LEFT, false);
                g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 2, this.x + this.size + 6, this.y + this.sizeH,
                        mGraphics.TOP | mGraphics.RIGHT, false);
                this.paintBgSub(g, this.x + this.size + 6, this.y + this.sizeH, wwz + 2, this.h - this.sizeH - 1,
                        false);
                if (!this.isPaintSub()) {
                    g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 7, this.x + this.size + 8 + wwz + 1, this.y + this.h,
                            mGraphics.BOTTOM | mGraphics.RIGHT, false);
                    g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 1, this.x + this.size + 8 - 2, this.y + this.h,
                            mGraphics.BOTTOM | mGraphics.LEFT, false);
                    g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 0, this.x + this.size + 8 - 2, this.y + this.sizeH,
                            mGraphics.TOP | mGraphics.LEFT, false);
                    g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 2, this.x + this.size + 8 + wwz + 1,
                            this.y + this.sizeH, mGraphics.TOP | mGraphics.RIGHT, false);
                }
                g.setColor(-4034289);
                g.drawRect(this.x + 1, this.y + this.sizeH + 1, this.size + 4, this.h - this.sizeH - 3, false);
            }
            if (GameCanvas.isTouch) {
                GameCanvas.resetTrans(g);
                int hc = mGraphics.getImageHeight(GameScr.imgButton[4]) / 2;
                int wc = mGraphics.getImageWidth(GameScr.imgButton[4]);
                g.drawRegion(GameScr.imgButton[4], 0, (this.isFocusClose ? 1 : 0) * hc, wc, hc, 0,
                        this.xShowText + this.wShowText - 21,
                        (this.y + this.sizeH - 14) / 2 + (this.y + this.sizeH - 14) / 2 - 7, 0, false);
            }
            GameCanvas.resetTrans(g);
            int www = (GameCanvas.isTouch ? this.xShowText + this.wShowText - 1 : this.w + this.x) - x000;
            if (!GameCanvas.isTouch) {
                www = this.w;
            }
            if (!GameCanvas.isTouch) {
                g.setColor(-9751532);
                g.drawRect(this.x, this.y + 2, www, this.sizeH - 5, false);
                g.setColor(-4034800);
                g.drawRect(this.x + 1, this.y + 2 + 1, www - 2, this.sizeH - 7, false);
                g.drawRect(this.x + 2, this.y + 2, www - 4, this.sizeH - 4, false);
                int i2 = 0;
                while (i2 < 7) {
                    g.drawRegion(GameScr.imgBoder[5], 0, 25, 12, 25, 0, GameCanvas.hw - 42 + i2 * 12,
                            this.y + this.sizeH / 2, mGraphics.VCENTER | mGraphics.LEFT, false);
                    ++i2;
                }
                g.drawRegion(GameScr.imgBoder[5], 0, 0, 12, 25, 0, GameCanvas.hw - 44, this.y + this.sizeH / 2,
                        mGraphics.VCENTER | mGraphics.LEFT, false);
                g.drawRegion(GameScr.imgBoder[5], 0, 0, 12, 25, 2, GameCanvas.hw + 44 + 1, this.y + this.sizeH / 2 + 1,
                        mGraphics.VCENTER | mGraphics.RIGHT, false);
                FontTeam.fontTile.drawString(g, this.currentTileMainTab, GameCanvas.w / 2, this.y + this.sizeH / 2 - 6,
                        2, false);
            }
            switch (this.indexMainTab) {
                case 0: {
                    int t = this.mShop.size() / this.colum + (this.mShop.size() % this.colum != 0 ? 1 : 0);
                    int ybg = this.y + this.h - 5 * this.size - 4 + this.hIP;
                    this.cmrItem.setStyle(t, this.size, xbg + 2, ybg, this.colum * this.size, 5 * this.size - this.hIP,
                            true, this.colum);
                    g.ClipRec(this.cmrItem.xPos - 1, this.cmrItem.yPos, this.cmrItem.width + 2,
                            this.cmrItem.height + 2);
                    this.cmrItem.setClip(g, this.cmrItem.xPos - 1, this.cmrItem.yPos, this.cmrItem.width + 2,
                            this.cmrItem.height + 2);
                    int i3 = 0;
                    while (i3 < this.mShop.size()) {
                        g.setColor(-16114410);
                        g.fillRect(xbg + i3 % this.colum * this.size + 2, ybg + i3 / this.colum * this.size, this.size,
                                this.size, true);
                        ++i3;
                    }
                    i3 = 0;
                    while (i3 < this.mShop.size()) {
                        g.setColor(-14458807);
                        g.drawRect(xbg + i3 % this.colum * this.size + 2, ybg + i3 / this.colum * this.size, this.size,
                                this.size, true);
                        if (i3 < this.mShop.size()) {
                            Item itp = (Item) this.mShop.elementAt(i3);
                            itp.paint(g, xbg + i3 % this.colum * this.size + this.size / 2 + 3,
                                    ybg + i3 / this.colum * this.size + this.size / 2, true);
                        }
                        ++i3;
                    }
                    if (selected > -1 && selected < this.mShop.size()) {
                        this.paintFocus(g, xbg + selected % this.colum * this.size + 2 + this.size / 2,
                                ybg + selected / this.colum * this.size + this.size / 2, 3, true);
                    }
                    mGraphics.resetTransAndroid(g);
                    g.restoreCanvas();
                    GameCanvas.resetTrans(g);
                    this.paintBgSub(g, this.cmrItem.xPos - 3, this.cmrItem.yPos - 3, this.cmrItem.width + 7,
                            this.cmrItem.height + 6, false);
                    break;
                }
                case 1: {
                    int t = MainChar.MaxInven / this.colum + (MainChar.MaxInven % this.colum != 0 ? 1 : 0);
                    int ybg = this.y + this.h - 5 * this.size - 4 + this.hIP;
                    this.cmrItem.setStyle(t, this.size, xbg + 2, ybg, this.colum * this.size, 5 * this.size - this.hIP,
                            true, this.colum);
                    g.ClipRec(this.cmrItem.xPos - 1, this.cmrItem.yPos, this.cmrItem.width + 2,
                            this.cmrItem.height + 2);
                    this.cmrItem.setClip(g, this.cmrItem.xPos - 1, this.cmrItem.yPos, this.cmrItem.width + 2,
                            this.cmrItem.height + 2);
                    int i4 = 0;
                    int startXBox = xbg + cmrItem.cmx;
                    int startYBox = ybg + cmrItem.cmy;
                    int endXBox = xbg + cmrItem.cmx + cmrItem.width;
                    int endyBox = ybg + cmrItem.cmy + cmrItem.height;
                    while (i4 < MainChar.MaxInven) {

                        int xxx = xbg + i4 % this.colum * this.size;
                        int yyy = ybg + i4 / this.colum * this.size;
                        int xxx2 = xxx + size;
                        int yyy2 = yyy + size;

                        if ((xxx + 2 >= startXBox && xxx - 2 < endXBox && yyy + 2 >= startYBox && yyy - 2 < endyBox)
                                || (xxx2 + 2 >= startXBox && xxx2 - 2 < endXBox && yyy2 + 2 >= startYBox
                                && yyy2 - 2 < endyBox)) {
                            g.setColor(-16114410);
                            g.fillRect(xxx + 2, yyy, this.size, this.size, true);
                        }
                        ++i4;
                    }
                    i4 = 0;

                    while (i4 < MainChar.MaxInven) {

                        int xxx = xbg + i4 % this.colum * this.size;
                        int yyy = ybg + i4 / this.colum * this.size;
                        int xxx2 = xxx + size;
                        int yyy2 = yyy + size;

                        if ((xxx + 2 >= startXBox && xxx - 2 < endXBox && yyy + 2 >= startYBox && yyy - 2 < endyBox)
                                || (xxx2 + 2 >= startXBox && xxx2 - 2 < endXBox && yyy2 + 2 >= startYBox
                                && yyy2 - 2 < endyBox)) {
                            g.setColor(-14458807);
                            g.drawRect(xxx, yyy, this.size, this.size, true);
                            if (i4 < Char.inventory.size()) {
                                Item it = (Item) Char.inventory.elementAt(i4);
                                it.paint(g, xxx + this.size / 2 + 3, yyy + this.size / 2, true);
                            }
                        }

                        ++i4;
                    }
                    if (selected > -1 && selected < Char.inventory.size()) {
                        this.paintFocus(g, xbg + selected % this.colum * this.size + 2 + this.size / 2,
                                ybg + selected / this.colum * this.size + this.size / 2, 3, true);
                    }
                    mGraphics.resetTransAndroid(g);
                    g.restoreCanvas();
                    GameCanvas.resetTrans(g);
                    this.paintBgSub(g, this.cmrItem.xPos - 3, this.cmrItem.yPos - 3, this.cmrItem.width + 7,
                            this.cmrItem.height + 6, false);
                    break;
                }
                case 11: {
                    this.paintPhiPhong(g);
                    break;
                }
                case 9: {
                    this.paintCreateItem(g);
                    break;
                }
                case 10: {
                    this.paintChuyenhoa(g);
                    break;
                }
                case 8: {
                    this.paintDapDo(g, false);
                    break;
                }
                case 2: {
                    this.paintCharWearing(g, true);
                    break;
                }
                case 12: {
                    this.paintPetWearing(g, true);
                    break;
                }
                case 4:{
                    this.paintMountWearing(g, true);
                    break;
                }
                case 3:
                case 5:
                case 6:
                case 7: {
                    if (this.isPaintSub()) {
                        switch (this.indexMainTab) {
                            case 3: {
                                if (this.isSkill) {
                                    this.paintSkill(g);
                                    break;
                                }
                                if (this.isFeatures || this.isCharWearing)
                                    break;
                                if (this.isQuest) {
                                    GameCanvas.resetTrans(g);
                                    int ww = GameCanvas.isTouch ? this.wShowText : 6 * this.size;
                                    this.paintListQuest(g, xbg, this.y + this.sizeH + 4, ww, false);
                                    break;
                                }
                                if (!this.isAnimal)
                                    break;
                                this.paintCharWearing(g, false);
                                break;
                            }
                            case 4: {
                                if (!this.isQuest)
                                    break;
                                GameCanvas.resetTrans(g);
                                int ww = GameCanvas.isTouch ? this.wShowText : 6 * this.size;
                                this.paintListQuest(g, xbg, this.y + this.sizeH + 4, ww, false);
                                break;
                            }
                            case 6: {
                                if (!this.isQuestClan)
                                    break;
                                int ww = GameCanvas.isTouch ? this.wShowText : 6 * this.size;
                                this.paintQuest(g, xbg, this.y + this.sizeH + 4, ww, true);
                            }
                        }
                        break;
                    }
                    if (this.indexMainTab == 5) {
                        int yyy = this.y + this.sizeH + 6;
                        this.cmrItem.setStyle(currnentTabDetail.length, 50, xbg - 1, yyy, this.colum * this.size,
                                this.h - this.sizeH - 6, true, 0);
                        this.cmrItem.setClip(g);
                        int i5 = 0;
                        while (i5 < currnentTabDetail.length) {
                            g.drawImage(GameScr.imgIconGold, xbg + 25, yyy + i5 * 50 + 20,
                                    mGraphics.VCENTER | mGraphics.HCENTER, false);
                            FontTeam.fontTile.drawString(g, currnentTabDetail[i5], xbg + 48,
                                    yyy + i5 * 50 + 20 - FontTeam.fontTile.getHeight() / 2, 0, false);
                            ++i5;
                        }
                        i5 = 0;
                        while (i5 < currnentTabDetail.length) {
                            g.drawImage(GameScr.imgBoder[6], xbg + 4, yyy + i5 * 50, 0, false);
                            g.drawRegion(GameScr.imgBoder[6], 0, 0, 20, 40, 2, xbg + 44, yyy + i5 * 50,
                                    mGraphics.TOP | mGraphics.RIGHT, false);
                            ++i5;
                        }
                        if (selected >= 0 && selected <= currnentTabDetail.length - 1) {
                            this.paintFocus(g, xbg + 4, yyy + selected * 50, 40, 40);
                        }
                        GameCanvas.resetTrans(g);
                        this.paintBgSub(g, xbg - 1, this.y + this.sizeH, this.size * this.colum + 7,
                                this.h - this.sizeH, false);
                        break;
                    }
                    if (this.indexMainTab == 12||this.indexMainTab == 4)
                        break;
                    GameCanvas.resetTrans(g);
                    int ww = GameCanvas.isTouch ? this.wShowText : 6 * this.size;
                    this.paintListQuest(g, xbg, this.y + this.sizeH + 4, ww, false);
                    if (!this.isPaintSub())
                        break;
                    this.paintBgSub(g, xbg - 1, this.y + this.sizeH, this.size * this.colum + 7, this.h - this.sizeH,
                            false);
                    break;
                }
            }
            if (this.isPaintSub()) {
                this.paintShowText(g);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        GameCanvas.resetTrans(g);
        if (this.indexMainTab == 0 || this.indexMainTab == 1 || this.isTabQuest()) {
            int h00 = this.h - 5 * this.size - 9 - this.sizeH;
            int yIP = 0;
            if (mSystem.isIP && mGraphics.zoomLevel == 3) {
                yIP = 3;
            }
            int yss = 3;
            if (this.isQuest) { // paint quest
//                Res.paintDlgDragonFullNew(g, this.xbg + this.countL + 6, this.y + this.sizeH + h00 / 2 + this.hIP / 2 + yIP + yss, 20, 40, 60, 60, GameScr.imgBk[0], false);
                yss = 0;
                g.drawImage(GameScr.imgArrow[0], this.xbg - this.countL + 6,
                        this.y + this.sizeH + h00 / 2 + this.hIP / 2 + yIP + yss, mGraphics.VCENTER | mGraphics.LEFT,
                        false);
                int w0 = mGraphics.getImageWidth(GameScr.imgArrow[0]);
                int h0 = mGraphics.getImageHeight(GameScr.imgArrow[0]);
                g.drawRegion(GameScr.imgArrow[0], 0, 0, w0, h0, 2, this.xbg + this.colum * this.size + this.countR - 6,
                        this.y + this.sizeH + h00 / 2 + this.hIP / 2 + yIP + yss, mGraphics.VCENTER | mGraphics.RIGHT,
                        false);
            }
            if (!this.isTabQuest()) {
                mFont.tahoma_7b_white.drawString(g, this.currentTileMainTab,
                        this.xbg + (this.size * this.colum + 7) / 2 - 3,
                        this.y + this.sizeH + h00 / 2 - 6 + this.hIP / 2 + yIP + yss - 1, 2, false);
            } else if (indexTypeQuest < QuestTile.length) {
//                mFont.tahoma_7b_white.drawString(g, QuestTile[indexTypeQuest],
//                        this.xbg + (this.size * this.colum + 7) / 2 - 3,
//                        this.y + this.sizeH + h00 / 2 - 6 + this.hIP / 2 + yIP + 1 + yss - 1, 2, false);
            }
        }
        if (GameCanvas.isTouch) {
            GameCanvas.resetTrans(g);
            int hc = mGraphics.getImageHeight(GameScr.imgButton[4]) / 2;
            int wc = mGraphics.getImageWidth(GameScr.imgButton[4]);
            g.drawRegion(GameScr.imgButton[4], 0, (this.isFocusClose ? 1 : 0) * hc, wc, hc, 0,
                    this.xShowText + this.wShowText - 21,
                    (this.y + this.sizeH - 14) / 2 + (this.y + this.sizeH - 14) / 2 - 7, 0, false);
            if (this.cmdSelectItem != null && this.isSetXYCmdSelect) {
                this.cmdSelectItem.paint(g);
            }
        }
        this.paintMoney(g);
        if (this.isTextmua) {
            this.paintPopUp(g, this.xShowText, this.y + this.hShowText - this.arrayText.length * 8);
        }
        this.paintIconMain(g);
        super.paint(g);
    }

    private void paintMountWearing(mGraphics g, boolean b) {
        int yShowText1 = this.yInfoWearing + 2;
        int xShowText1 = this.xInfoWearing;
        int wShowText1 = this.wInfoWearing - 4;
        int hShowText1 = this.hInfoWearing - 4 + (this.isAnimal ? this.size : 0) - this.size + this.hIP / 2;
        g.setColor(-13232632);
        g.fillRect(xShowText1, yShowText1, wShowText1, hShowText1, false);
        g.setColor(-1596632);
        g.fillRect(xShowText1 + 7, yShowText1 + 1, wShowText1 - 2, hShowText1 - 2, false);
        g.setColor(-13232632);
        g.fillRect(xShowText1 + 8, yShowText1 + 2, wShowText1 - 4, hShowText1 - 4, false);
        g.setColor(-14864849);
        g.fillRect(xShowText1 + 9, yShowText1 + 3, wShowText1 - 6, hShowText1 - 6, false);
        g.drawRegion(GameScr.imgBoder[6], 0, 0, 9, 9, 7, xShowText1 + wShowText1+6, yShowText1 + hShowText1,
                mGraphics.BOTTOM | mGraphics.RIGHT, false);
        g.drawRegion(GameScr.imgBoder[6], 0, 0, 9, 9, 1, xShowText1+6, yShowText1 + hShowText1,
                mGraphics.BOTTOM | mGraphics.LEFT, false);
        g.drawRegion(GameScr.imgBoder[6], 0, 0, 9, 9, 0, xShowText1+6, yShowText1, mGraphics.TOP | mGraphics.LEFT, false);
        g.drawRegion(GameScr.imgBoder[6], 0, 0, 9, 9, 2, xShowText1 + wShowText1+6, yShowText1,
                mGraphics.TOP | mGraphics.RIGHT, false);
        int xbg = this.x + this.size + 8;
        int ybg = yShowText1;
        if (GameCanvas.w > 200) {
            g.setColor(-9751532);
            int hh0 = hShowText1 + (this.isAnimal ? this.size : 0);
            g.fillRect(xbg + 3, ybg, 2 * this.size , hh0, false);
            g.fillRect(xbg, ybg + 3, 2 * this.size, hh0 - 6, false);
            g.setColor(-4891370);
            g.drawLine(xbg + 3, ybg + 3 + hh0 - 6 + 1, xbg + 2 * this.size - 6, ybg + 3 + hh0 - 6 + 1, false);
            g.setColor(-14864849);
            g.fillRect(xbg + 3, ybg + 3, 2 * this.size , hh0 - 6, false);
            g.setColor(-110);
            g.fillRect(xbg + 3, ybg + 1, 2 * this.size , 1, false);
            g.setColor(-4034289);
            g.fillRect(xbg + 1, ybg + 16, 1, hh0 - 18, false);
            g.fillRect(xbg - 2 + 2 * this.size+6, ybg + 16, 1, hh0 - 18, false);
        }
//        g.drawRegion(GameScr.imgBoder[4], 0, 0, 16, 16, 0, xbg, ybg, mGraphics.TOP | mGraphics.LEFT, false);
//        g.drawRegion(GameScr.imgBoder[4], 0, 0, 16, 16, 2, xbg + 2 * this.size, ybg, mGraphics.TOP | mGraphics.RIGHT,
//                false);
//        g.drawRegion(GameScr.imgBoder[4], 0, 16, 16, 16, 0, xbg, ybg += hShowText1 + (this.isAnimal ? this.size : 0),
//                mGraphics.BOTTOM | mGraphics.LEFT, false);
//        g.drawRegion(GameScr.imgBoder[4], 0, 16, 16, 16, 2, xbg + 2 * this.size, ybg,
//                mGraphics.BOTTOM | mGraphics.RIGHT, false);
        g.fillRect(0, 0, 0, 0, false);
        DataSkillEff partThuCuoi = GameScr.mainChar.loadPartPhiPhongThuCuoi(GameScr.mainChar.idHorse);
        if (partThuCuoi != null) {
            if (partThuCuoi != null && !GameScr.mainChar.isHorseNew) {
                if (GameCanvas.gameTick % 3 == 0) {
                    ++this.p1;
                    if (this.p1 >= Char.STANDFRAME[2].length) {
                        this.p1 = 0;
                    }
                    this.frame = Char.STANDFRAME[3][this.p1];
                }
                partThuCuoi.paintBottomPhiPhong(g, this.x + this.size*5+4, this.y + this.h - 2 * this.size - this.size / 2 - (GameCanvas.isSmallScreen ? 24 : 38) + this.hIP, 2, this.frame + frameThuCuoi * 52);
                partThuCuoi.paintTopPhiPhong(g, this.x + this.size*5+4, this.y + this.h - 2 * this.size - this.size / 2 - (GameCanvas.isSmallScreen ? 24 : 38) + this.hIP, 2, this.frame + frameThuCuoi * 52);

            }
            if (partThuCuoi != null && GameScr.mainChar.isHorseNew) {
                if (GameCanvas.gameTick % 3 == 0) {
                    ++this.p1;
                    if (this.p1 >= Char.A_Stand.length-1) {
                        this.p1 = 0;
                    }
                    Fhorse = Char.A_Stand[this.p1];
                }
                if (GameCanvas.gameTick % 5 == 0) {
                    int num = partThuCuoi.countTotalFrame() / 21;
                    if (num == 0) {
                        num = 1;
                    }
                    frameThuCuoiNew = (byte) ((frameThuCuoiNew + 1) % num);
                }
                partThuCuoi.paintBottomPhiPhong(g, this.x + this.size*5+4 + GameScr.mainChar.dxHorseNew, this.y + this.h - 2 * this.size - this.size / 2 - (GameCanvas.isSmallScreen ? 24 : 38) + this.hIP + GameScr.mainChar.dyHorseNew,0, getFrameHorse());
                partThuCuoi.paintTopPhiPhong(g, this.x + this.size*5+4 + GameScr.mainChar.dxHorseNew, this.y + this.h - 2 * this.size - this.size / 2 - (GameCanvas.isSmallScreen ? 24 : 38) + this.hIP + GameScr.mainChar.dyHorseNew, 0, getFrameHorse());
            }
        }
        g.drawImage(GameScr.imgBackItem, this.xbg+this.size-10, this.y + this.h-5*this.size-this.size+5,
                mGraphics.VCENTER | mGraphics.HCENTER, false);
        g.drawImage(GameScr.imgBackItem, this.xbg+this.size*2-8, this.y + this.h-5*this.size-this.size+5,
                mGraphics.VCENTER | mGraphics.HCENTER, false);

        g.drawImage(GameScr.imgBackItem, this.xbg+this.size-10, this.y + this.h-4*this.size-this.size+5,
                mGraphics.VCENTER | mGraphics.HCENTER, false);
        g.drawImage(GameScr.imgBackItem, this.xbg+this.size*2-8, this.y + this.h-4*this.size-this.size+5,
                mGraphics.VCENTER | mGraphics.HCENTER, false);
        g.drawImage(GameScr.imgBackItem, this.xbg+this.size-10, this.y + this.h-3*this.size-this.size+5,
                mGraphics.VCENTER | mGraphics.HCENTER, false);
        g.drawImage(GameScr.imgBackItem, this.xbg+this.size*2-8, this.y + this.h-3*this.size-this.size+5,
                mGraphics.VCENTER | mGraphics.HCENTER, false);

//        g.drawRegion(GameScr.imgW, 0, 0, 17, 17, 0, this.xbg+this.size-10,
//                this.y + this.h-5*this.size-this.size+5,
//                mGraphics.VCENTER | mGraphics.HCENTER, false);
//        g.drawRegion(GameScr.imgW, 0, 0, 17, 17, 0, this.xbg+this.size*2-8,
//                this.y + this.h-5*this.size-this.size+5, mGraphics.VCENTER | mGraphics.HCENTER, false);
//        g.drawRegion(GameScr.imgW, 0, 0, 17, 17, 0, this.xbg+this.size-10,
//                this.y + this.h-4*this.size-this.size+5,
//                mGraphics.VCENTER | mGraphics.HCENTER, false);
//        g.drawRegion(GameScr.imgW, 0, 0, 17, 17, 0, this.xbg+this.size*2-8,
//                this.y + this.h-4*this.size-this.size+5, mGraphics.VCENTER | mGraphics.HCENTER, false);
//        g.drawRegion(GameScr.imgW, 0, 0, 17, 17, 0, this.xbg+this.size-10,
//                this.y + this.h-3*this.size-this.size+5,
//                mGraphics.VCENTER | mGraphics.HCENTER, false);
//        g.drawRegion(GameScr.imgW, 0, 0, 17, 17, 0, this.xbg+this.size*2-8,
//                this.y + this.h-3*this.size-this.size+5, mGraphics.VCENTER | mGraphics.HCENTER, false);

        // Paint mount equipment items
        if (GameScr.mainChar.mount != null) {
            for (int i = 0; i < 6; i++) {
                Item mountItem = GameScr.mainChar.mount[i];
                if (mountItem != null) {
                    int row = i / 2;
                    int col = i % 2;
                    int xItem = this.xbg + this.size - 10 + col * (this.size + 2);
                    int yItem = this.y + this.h - 5 * this.size - this.size + 5 + row * this.size;
                    mountItem.paintIconWearing(g, xItem, yItem);
                }else{
                    int row = i / 2;
                    int col = i % 2;
                    int xItem = this.xbg + this.size - 10 + col * (this.size + 2);
                    int yItem = this.y + this.h - 5 * this.size - this.size + 5 + row * this.size;
                    g.drawRegion(GameScr.imgW, 0, 0, 17, 17, 0, xItem,
                            yItem, mGraphics.VCENTER | mGraphics.HCENTER, false);
                }
            }
        }

        // Paint focus on selected mount equipment
        if (selected > -1 && selected < 6 && !isFocusCharWearing && !isFocusDetailMenu) {
            int row = selected / 2;
            int col = selected % 2;
            int xFocus = this.xbg + this.size - 10 + col * (this.size + 2);
            int yFocus = this.y + this.h - 5 * this.size - this.size + 5 + row * this.size;
            this.paintFocus(g, xFocus, yFocus, 0, true);
        }

        g.fillRect(0, 0, 0, 0, false);
        this.paintMountCell(g);
        if (cmdChoan != null && !GameCanvas.isTouch) {
            cmdChoan.paint(g);
        }
    }

    public void paintMountCell(mGraphics g) {
        GameCanvas.resetTrans(g);
        int maxx = vecMountEat.size();
        int t = maxx / this.colum + (vecMountEat.size() % this.colum != 0 ? 1 : 0);
        int hip3 = 0;
        if (mSystem.isIP && mGraphics.zoomLevel == 3) {
            hip3 = -5;
        }
        this.scrDapdo.setStyle(t + 3, this.sizeDapdo + 2, this.xWearing[0] - 10, this.yWearing[0] + 9,
                this.colum * this.sizeDapdo, 5 * this.sizeDapdo, true, this.colum);
        g.ClipRec(this.xWearing[0] - 20, this.yWearing[0] + 3, 142, 74 + hip3);
        this.scrDapdo.setClip(g, this.xWearing[0] - 20, this.yWearing[0] + 3, 142, 74 + hip3);
        int i = 0;
        while (i < maxx) {
            g.setColor(-14458807);
            g.drawRect(this.xWearing[0] + i % this.colum * this.sizeDapdo + 2 - 5 - 10,
                    this.yWearing[0] + i / this.colum * this.sizeDapdo + 10 - 2 + 5 - 9, this.sizeDapdo, this.sizeDapdo,
                    true);
            ++i;
        }
        i = 0;
        while (i < vecMountEat.size()) {
            Item it = (Item) vecMountEat.elementAt(i);
            if (it != null) {
                it.paint(g, this.xWearing[0] + i % this.colum * this.sizeDapdo + 1,
                        this.yWearing[0] + i / this.colum * this.sizeDapdo + this.sizeDapdo - 9, true);
            }
            ++i;
        }
        if (GameCanvas.isTouch) {
            if (selected >= 6 && selected < vecMountEat.size() + 6) {
                int adjustedSelected = selected - 6;
                this.paintFocus(g, this.xWearing[0] + adjustedSelected % this.colum * this.sizeDapdo + 2 - 2,
                        1 + this.yWearing[0] + adjustedSelected / this.colum * this.sizeDapdo + this.sizeDapdo / 2 + 4, 3,
                        true);
            }
            this.paintFocus(g, this.xWearing[0] - 40, 1 + this.yWearing[0], 3, true);
        }
        if (!GameCanvas.isTouch && selected >= 6 && selected < vecMountEat.size() + 6) {
            int adjustedSelected = selected - 6;
            this.paintFocus(g, this.xWearing[0] + adjustedSelected % this.colum * this.sizeDapdo + 2 - 1,
                    this.yWearing[0] + adjustedSelected / this.colum * this.sizeDapdo + this.sizeDapdo / 2 + 4, 3, true);
        }
        mGraphics.resetTransAndroid(g);
        g.restoreCanvas();
        GameCanvas.resetTrans(g);
    }

    public int getFrameHorse() {
//        if (dir == 0) {
//            return Fhorse + frameThuCuoiNew * 21;
//        }
//        if (dir == 1) {
//            return Fhorse + 7 + frameThuCuoiNew * 21;
//        }
        return Fhorse + 14 + frameThuCuoiNew * 21;
    }

    public void paintIconMain(mGraphics g) {
        GameCanvas.resetTrans(g);
        if (mSystem.isPC || mSystem.isIP) {
            g.fillRect(0, 0, 0, 0, false);
        }
        this.scrMainmenu.setStyle(this.numtab, 30, this.x, this.y + 37, 30, this.h - 48, true, 1);
        g.ClipRec(this.x, this.y + 48, 30, this.h - 50);
        this.scrMainmenu.setClip(g, this.x, this.y + 48, 30, this.h - 50);
        if (this.indexMainTab != 8 && this.indexMainTab != 9 && this.indexMainTab != 10 && this.indexMainTab != 11) {
            int i = 0;
            while (i < this.numtab) {
                if (this.maptopaintMenuIcon[i] != -1) {
                    g.drawRegion(GameScr.imgMainMenu1, 0,
                            this.maptopaintMenuIcon[i] * (mGraphics.getImageHeight(GameScr.imgMainMenu1) / 8),
                            mGraphics.getImageWidth(GameScr.imgMainMenu1),
                            mGraphics.getImageHeight(GameScr.imgMainMenu1) / 8, 0, this.x + this.size / 2 + 3,
                            this.y + i * this.size + this.sizeH + this.size / 2 + (GameCanvas.w > 200 ? 10 : 8),
                            mGraphics.VCENTER | mGraphics.HCENTER, true);
                } else {
                    g.drawRegion(GameScr.peticon, 0, 0, 22, 22, 0, this.x + this.size / 2 + 3,
                            this.y + 5 * this.size + this.sizeH + this.size / 2 + (GameCanvas.w > 200 ? 10 : 8), 3,
                            true);
                }
                ++i;
            }
        }
        if (this.indexMainTab < this.maptopaintMenuIcon.length && this.indexMainTab != -1 && this.indexMainTab != 8
                && this.indexMainTab != 9 && this.indexMainTab != 10 && this.indexMainTab != 11) {
            int ys = 0;
            if (this.isQuest && GameCanvas.isTouch) {
                ys = 1;
            }
            g.drawRegion(GameScr.imgMainMenu, 0,
                    this.maptopaintMenuIcon[this.indexMainTab + ys]
                            * (mGraphics.getImageHeight(GameScr.imgMainMenu1) / 8),
                    mGraphics.getImageWidth(GameScr.imgMainMenu1), mGraphics.getImageHeight(GameScr.imgMainMenu1) / 8,
                    0, this.x + this.size / 2 + 3, this.y + (this.indexMainTab + ys) * this.size + this.sizeH
                            + this.size / 2 + (GameCanvas.w > 200 ? 10 : 8),
                    mGraphics.VCENTER | mGraphics.HCENTER, true);
        } else if (this.indexMainTab != -1 && this.indexMainTab != 8 && this.indexMainTab != 9
                && this.indexMainTab != 10 && this.indexMainTab != 11) {
            g.drawRegion(GameScr.peticon, 0, this.indexPet * 22, 22, 22, 0, this.x + this.size / 2 + 3,
                    this.y + 5 * this.size + this.sizeH + this.size / 2 + (GameCanvas.w > 200 ? 10 : 8), 3, true);
        }
        mGraphics.resetTransAndroid(g);
        g.restoreCanvas();
        GameCanvas.resetTrans(g);
    }

    private void paintQuest(mGraphics g, int x, int y, int ww, boolean isClan) {
    }

    private void paintCell(mGraphics g, int total, String[] info) {
    }

    public void paintBgSub(mGraphics g, int x, int y, int w, int h, boolean isBoder) {
        g.setColor(-9751532);
        g.drawRect(x, y, w, h, false);
        g.drawRect(x + 2, y + 2, w - 4, h - 4, false);
        if (isBoder) {
            g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 7, x + w, y + h, mGraphics.BOTTOM | mGraphics.RIGHT, false);
            g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 1, x, y + h, mGraphics.BOTTOM | mGraphics.LEFT, false);
            g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 0, x, y, mGraphics.TOP | mGraphics.LEFT, false);
            g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 2, x + w, y, mGraphics.TOP | mGraphics.RIGHT, false);
        }
        g.setColor(-4034289);
        g.drawRect(x + 1, y + 1, w - 2, h - 2, false);
    }

    public void showTextmua() {
        this.isTextmua = true;
        MainMenu.cmdBuy.caption = T.yes;
        this.canbuy = true;
        this.arrayText = mFont.tahoma_7_white.splitFontArray(this.textHoimua, this.wShowText - 15);
    }

    public void paintPopUp(mGraphics g, int xp, int yp) {
        int wp = this.wShowText;
        int hp = 0;
        hp = this.yShowText + this.hShowText - yp;
        g.setColor(-9751532);
        g.fillRect(xp + 2, yp, wp - 4, 1, false);
        g.setColor(-4034289);
        g.fillRect(xp + 1 + 2, yp + 1, wp - 2 - 4, 1, false);
        g.setColor(-9751532);
        g.fillRect(xp + 2 + 1, yp + 2, wp - 4 - 2, 1, false);
        g.setColor(-16114410);
        g.fillRect(xp + 3 + 2, yp + 3, wp - 6 - 2, hp - 6, false);
        int i = 0;
        while (i < this.arrayText.length) {
            mFont.tahoma_7_white.drawString(g, this.arrayText[i], xp + 10, yp + 10 + i * 15, 0, false);
            ++i;
        }
    }

    public void paintShowText(mGraphics g) {
        boolean isAlway;
        if (this.indexMainTab == 8 && effDapDo != null) {
            return;
        }
        GameCanvas.resetTrans(g);
        boolean bl = isAlway = GameCanvas.isTouch;
        if (this.isShowText || this.isSkill && GameCanvas.isTouch) {
            isAlway = true;
        }
        if (isAlway) {
            g.setColor(-9751532);
            g.fillRect(this.xShowText, this.yShowText, this.wShowText, this.hShowText, false);
            g.setColor(-4034289);
            g.fillRect(this.xShowText + 1, this.yShowText + 1, this.wShowText - 2, this.hShowText - 2, false);
            g.setColor(-9751532);
            g.fillRect(this.xShowText + 2, this.yShowText + 2, this.wShowText - 4, this.hShowText - 4, false);
            g.setColor(-16114410);
            g.fillRect(this.xShowText + 3, this.yShowText + 3, this.wShowText - 6, this.hShowText - 6, false);
            if (GameCanvas.isTouch) {
                g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 7, this.xShowText + this.wShowText,
                        this.yShowText + this.hShowText, mGraphics.BOTTOM | mGraphics.RIGHT, false);
                g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 1, this.xShowText, this.yShowText + this.hShowText,
                        mGraphics.BOTTOM | mGraphics.LEFT, false);
                g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 0, this.xShowText, this.yShowText,
                        mGraphics.TOP | mGraphics.LEFT, false);
                g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 2, this.xShowText + this.wShowText, this.yShowText,
                        mGraphics.TOP | mGraphics.RIGHT, false);
            }
        }
        if (!this.isShowText) {
            GameCanvas.resetTrans(g);
            if (this.vHanhTrang != null && GameCanvas.isTouch && this.vHanhTrang.size() > 0
                    && GameCanvas.currentDialog == null && !GameCanvas.menu.showMenu) {
                int k = 0;
                while (k < this.vHanhTrang.size()) {
                    mCommand cmd = (mCommand) this.vHanhTrang.elementAt(k);
                    if (cmd != null) {
                        cmd.paint(g);
                    }
                    ++k;
                }
            }
            return;
        }
        int yy = 0;
        int yKham = 0;
        int hCmdHanhTrang = 0;
        if (this.arrayKhamNgoc != null) {
            yKham = 20;
        }
        this.cmrShowText.setStyle(this.totalInfoshow + this.numItem * 2 + (this.indexPaintLineSkill > 0 ? 1 : 0) + 1,
                this.disString, this.xShowText, this.yShowText, this.wShowText,
                this.hShowText - 8 - yKham
                        - (GameCanvas.isTouch && !this.cmdSelectItem.caption.equals("") ? ScreenTeam.cmdH : 0)
                        - (GameCanvas.isTouch ? hCmdHanhTrang : 0),
                true, 0);
        g.ClipRec(this.cmrShowText.xPos, this.cmrShowText.yPos + 2, this.cmrShowText.width,
                this.cmrShowText.height + 4 + yKham);
        this.cmrShowText.setClip(g, this.cmrShowText.xPos, this.cmrShowText.yPos + 2, this.cmrShowText.width,
                this.cmrShowText.height + 4 + yKham);
        int i = 0;
        int num = 0;
        while (i < this.showText.size()) {
            InfoTextShow info = (InfoTextShow) this.showText.elementAt(i);
            if (info != null && info.info != null) {
                mFont f = info.f;
                if (!(i != 1
                        || this.indexMainTab != 1 && this.indexMainTab != 0 && this.indexMainTab != 2
                        && this.indexMainTab != 4 && this.indexMainTab != 8 && this.indexMainTab != 10 && this.indexMainTab != 11
                        || this.numItemStart <= 0 && this.numItemStart2 <= 0)) {
                    int k;
                    yy += this.disString;
                    if (this.laststar > 1) {
                        k = 0;
                        while (k < this.numItemStart) {
                            g.drawRegion(GameScr.imgStart, 0, 0, 10, 10, 0, this.xShowText + 15 + k * 11,
                                    this.yShowText + yy + 3, 3, true);
                            ++k;
                        }
                    }
                    if (this.isHalfstart) {
                        g.drawRegion(GameScr.imgStart, 0, 40, 10, 10, 0,
                                this.xShowText + 15 + (this.laststar > 1 ? this.numItemStart * 11 : 0),
                                this.yShowText + yy + 3, 3, true);
                    }
                    if (this.runStart) {
                        if (this.speedStart < this.numItemStart) {
                            g.drawRegion(GameScr.imgStart, 0, 10, 10, 10, 0, this.xShowText + 15 + this.speedStart * 11,
                                    this.yShowText + yy + 3, 3, true);
                        }
                        if (this.speedStart >= 1 && this.speedStart < this.numItemStart + 1) {
                            g.drawRegion(GameScr.imgStart, 0, 20, 10, 10, 0,
                                    this.xShowText + 15 + (this.speedStart - 1) * 11, this.yShowText + yy + 3, 3, true);
                        }
                        if (this.speedStart >= 2 && this.speedStart < this.numItemStart + 2) {
                            g.drawRegion(GameScr.imgStart, 0, 30, 10, 10, 0,
                                    this.xShowText + 15 + (this.speedStart - 2) * 11, this.yShowText + yy + 3, 3, true);
                        }
                    }
                    if (this.numItemStart2 > 0) {
                        yy += this.disString;
                        if (this.laststar2 > 1) {
                            k = 0;
                            while (k < this.numItemStart2) {
                                g.drawRegion(GameScr.imgStart, 0, 0, 10, 10, 0, this.xShowText + 15 + k * 11,
                                        this.yShowText + yy + 3, 3, true);
                                ++k;
                            }
                        }
                        if (this.isHalfstart2) {
                            g.drawRegion(GameScr.imgStart, 0, 40, 10, 10, 0,
                                    this.xShowText + 15 + (this.laststar2 > 1 ? this.numItemStart2 * 11 : 0),
                                    this.yShowText + yy + 3, 3, true);
                        }
                        if (this.runStart2) {
                            if (this.speedStart2 < this.numItemStart2) {
                                g.drawRegion(GameScr.imgStart, 0, 10, 10, 10, 0,
                                        this.xShowText + 15 + this.speedStart2 * 11, this.yShowText + yy + 3, 3, true);
                            }
                            if (this.speedStart2 >= 1 && this.speedStart2 < this.numItemStart2 + 1) {
                                g.drawRegion(GameScr.imgStart, 0, 20, 10, 10, 0,
                                        this.xShowText + 15 + (this.speedStart2 - 1) * 11, this.yShowText + yy + 3, 3,
                                        true);
                            }
                            if (this.speedStart2 >= 2 && this.speedStart2 < this.numItemStart2 + 2) {
                                g.drawRegion(GameScr.imgStart, 0, 30, 10, 10, 0,
                                        this.xShowText + 15 + (this.speedStart2 - 2) * 11, this.yShowText + yy + 3, 3,
                                        true);
                            }
                        }
                    }
                }
                if (num < numKhamNgoc && !(info.info.length == 0 || info.info[0].contains("Sức mạnh")
                        || info.info[0].contains("Thân pháp") || info.info[0].contains("Linh khí")
                        || info.info[0].contains("Sinh khí"))) {
                    g.setColor(Color.ORANGE.toIntBits());
                    g.drawRect(this.xShowText + this.wShowText - 25, this.yShowText + 35 + 20 * num, 15, 15, true);
                    if (this.idNgocKham != null && this.idNgocKham[num] != -1) {
                        ImageIcon img = GameData.getImgIcon((short) (this.idNgocKham[num] + Res.ID_ITEM));
                        if (img != null && img.img != null) {
                            g.drawImage(img.img, this.xShowText + this.wShowText - 26, this.yShowText + 35 + 20 * num,
                                    0, true);
                        }
                        int upgrade = 0;
                        int iconId = this.idNgocKham[num];
                        if (iconId >= 900 && iconId <= 905) {
                            upgrade = (iconId - 900 + 1) * 3;
                        } else if (iconId >= 906 && iconId <= 911) {
                            upgrade = (iconId - 906 + 1) * 3;
                        } else if (iconId >= 912 && iconId <= 917) {
                            upgrade = (iconId - 912 + 1) * 3;
                        } else if (iconId >= 918 && iconId <= 923) {
                            upgrade = (iconId - 918 + 1) * 3;
                        } else if (iconId >= 924 && iconId <= 929) {
                            upgrade = (iconId - 924 + 1) * 3;
                        } else if (iconId >= 930 && iconId <= 935) {
                            upgrade = (iconId - 930 + 1) * 3;
                        }
                        new Effect_UpLv_Item().paintUpgradeEffect(this.xShowText + this.wShowText - 25,
                                this.yShowText + 35 + 20 * num, upgrade, 15, g, -7);
                    }

                    num++;
                }
                if (info.info != null && info.info[info.info.length - 1] != null && f != null
                        && info.info[info.info.length - 1] != null) {
                    if (info.idCompare > -1 && info.idCompare == 3) {
                        f = mFont.tahoma_7_gray;
                    }
                    int ws = f.getWidth(info.info[info.info.length - 1]);
                    int j = 0;
                    while (j < info.info.length) {
                        if (this.isCharWearing && i == 0 && f == mFont.tahoma_7_white) {
                            f = mFont.tahoma_7b_white;
                        }
                        f.drawString(g, info.info[j], this.xShowText + 10, this.yShowText + 10 + yy, 0, true);
                        yy += this.disString;
                        ++j;
                    }
                    if (info.idCompare > -1 && info.idCompare != 3) {
                        g.drawImage(GameScr.imgSo[info.idCompare], this.xShowText + 10 + ws + 1,
                                this.yShowText + 12 + yy - this.disString, 0, false);
                    }
                }
            } else {
                g.setColor(-1136094);
                g.fillRect(this.xShowText + 15, this.yShowText + 10 + (yy += this.disString / 2), this.wShowText - 30,
                        1, true);
                yy += this.disString / 2;
            }
            ++i;
        }
        mGraphics.resetTransAndroid(g);
        g.restoreCanvas();
        GameCanvas.resetTrans(g);
        if (this.isUseCmr) {
            g.drawImage(GameScr.imgSo[2], this.xShowText + this.wShowText - 3, this.yShowText + this.hShowText - 13,
                    mGraphics.TOP | mGraphics.RIGHT, false);
            g.drawRegion(GameScr.imgSo[2], 0, 0, 7, 5, 3, this.xShowText + this.wShowText - 3, this.yShowText + 5,
                    mGraphics.TOP | mGraphics.RIGHT, false);
        }
        GameCanvas.resetTrans(g);
        if (this.vHanhTrang != null && GameCanvas.isTouch && this.vHanhTrang.size() > 0
                && GameCanvas.currentDialog == null && !GameCanvas.menu.showMenu) {
            int k = 0;
            while (k < this.vHanhTrang.size()) {
                mCommand cmd = (mCommand) this.vHanhTrang.elementAt(k);
                if (cmd != null) {
                    cmd.paint(g);
                }
                ++k;
            }
        }
    }

    public static boolean isDegit(char c) {
        return c >= '0' && c <= '9';
    }

    public void setAutoActionCmd(mCommand cmd) {
    }

    public void switchTabDapDo(int index) {
        ID_CUONG_HOA = (short) -1;
        effDapDo = null;
        textPercent = "";
        xuCuongHoa = 0;
        this.indexMainTab = index;
        this.slDapdo1 = -1;
        selected = -1;
        this.indexSubTab = 0;
        this.isCharWearing = false;
        this.isShowInFoChar = false;
        isFocusDetailMenu = false;
        this.isAnimal = false;
        this.isQuest = false;
        this.isFeatures = false;
        this.isHanhTrang = false;
        this.isSkill = false;
        this.isShowFill = false;
        this.isSkillClan = false;
        this.isQuestClan = false;
        itemStone = null;
        mItem = null;
        itemBaohiem = null;
        itemBua = null;
        this.numStone = 0;
        if (this.isMiniShop) {
            this.isHanhTrang = true;
        }
        this.reSetAllCmr();
        this.setPosScroll(this.indexMainTab, -1);
        this.setSelectTab("switchTabDapDo");
        this.setCmdCenter("switchTabDapDo");
        this.setTile();
        maxDap = vecItemDapDo.size();
        if (!GameCanvas.isTouch) {
            tabDapdo = 0;
            selected = -1;
            this.closeText();
            this.scrDapdo.moveTo(0);
            this.numItem = 0;
        }
        this.isFocusClose = false;
        this.currentTileMainTab = "C\u01b0\u1eddng H\u00f3a";
    }

    public void updateTouch() {
        block65: {
            block68: {
                int w0;
                int h0;
                int yTouch;
                int xTouch;
                int x0;
                int wc;
                block66: {
                    block67: {
                        if (delay >= 0) {
                            --delay;
                            return;
                        }
                        if (GameCanvas.currentDialog != null) {
                            return;
                        }
                        if (this.indexMainTab == 8) {
                            return;
                        }
                        this.isFocusClose = false;
                        wc = mGraphics.getImageWidth(GameScr.imgButton[4]);
                        if (GameCanvas.isPointer(this.xShowText + this.wShowText - 21,
                                (this.y + this.sizeH - 14) / 2 + (this.y + this.sizeH - 14) / 2 - 6, wc, wc, 0)
                                && GameCanvas.canTouch()) {
                            this.isFocusClose = true;
                            if (GameCanvas.isPointerJustRelease[0] && GameCanvas.isPointerClick[0]) {
                                this.doClose();
                                GameCanvas.isPointerClick[0] = false;
                            }
                        }
                        if (this.cmdSelectItem != null) {
                            this.cmdSelectItem.isFocus = false;
                            if (GameCanvas.isPointer(this.cmdSelectItem.x, this.cmdSelectItem.y,
                                    this.cmdSelectItem.wCmd, this.cmdSelectItem.hCmd, 0) && GameCanvas.canTouch()) {
                                this.cmdSelectItem.isFocus = false;
                                if (GameCanvas.isPointerJustRelease[0] && GameCanvas.isPointerClick[0]) {
                                    this.cmdSelectItem.performAction();
                                }
                            }
                        }
                        x0 = this.x + this.w - 7 - this.colum * this.size;
                        xTouch = this.x;
                        yTouch = this.y + this.sizeH + (GameCanvas.w > 200 ? 10 : 8);
                        h0 = (mainTab.length + 1) * this.size;
                        if (!isPet) {
                            h0 = mainTab.length * this.size;
                        }
                        w0 = x0 - this.x - 5;
                        if (this.indexMainTab != 0 && (!this.isHanhTrang || this.indexMainTab != 1))
                            break block66;
                        if (!GameCanvas.canTouch())
                            break block65;
                        if (!GameCanvas.isPointer(x0 - 15, this.y + this.sizeH + 3, 25, 28, 0))
                            break block67;
                        if (!GameCanvas.isPointerJustRelease[0] || !GameCanvas.isPointerClick[0])
                            break block65;
                        isChangeSubTab = true;
                        isFocusDetailMenu = true;
                        GameCanvas.keyPressedz[4] = true;
                        this.lastSelect = -1;
                        selected = -1;
                        break block65;
                    }
                    if (!GameCanvas.isPointer(x0 + this.size * this.colum - 40, this.y + this.sizeH + 3, 30, 28, 0)
                            || !GameCanvas.isPointerJustRelease[0] || !GameCanvas.isPointerClick[0])
                        break block65;
                    isChangeSubTab = true;
                    isFocusDetailMenu = true;
                    GameCanvas.keyPressedz[6] = true;
                    this.lastSelect = -1;
                    selected = -1;
                    break block65;
                }
                if (this.indexMainTab != 3)
                    break block68;
                if (this.isQuest) {
                    if (GameCanvas.canTouch()) {
                        if (GameCanvas.isPointer(x0 - 15, this.y + this.sizeH + 3, 25, 28, 0)) {
                            if (GameCanvas.isPointerJustRelease[0] && GameCanvas.isPointerClick[0]) {
                                isChangeSubTab = true;
                                isFocusDetailMenu = false;
                                this.countL = 5;
                                if (--indexTypeQuest < 0) {
                                    indexTypeQuest = 0;
                                }
                                this.closeText();
                                this.movecmrQuest();
                                this.lastSelect = -1;
                                selected = -1;
                                GameCanvas.isPointerClick[0] = false;
                            }
                        } else if (GameCanvas.isPointer(x0 + this.size * this.colum - 40, this.y + this.sizeH + 3, 30,
                                28, 0) && GameCanvas.isPointerJustRelease[0] && GameCanvas.isPointerClick[0]) {
                            isChangeSubTab = true;
                            isFocusDetailMenu = false;
                            this.countR = 5;
                            if (++indexTypeQuest > QuestTile.length - 1) {
                                indexTypeQuest = QuestTile.length - 1;
                            }
                            this.closeText();
                            this.movecmrQuest();
                            this.lastSelect = -1;
                            selected = -1;
                            GameCanvas.isPointerClick[0] = false;
                        }
                    }
                    if (selected != -1) {
                        indexQuest = selected;
                        if (ListQuest != null && indexTypeQuest >= 0 && indexTypeQuest < ListQuest.length) {
                            mVector currQuest = ListQuest[indexTypeQuest];
                            QuestInfo q = (QuestInfo) currQuest.elementAt(indexQuest);
                            mVector minfo = new mVector();
                            if (q != null) {
                                InfoTextShow in = new InfoTextShow(new String[] { q.name }, 0);
                                minfo.addElement(in);
                                String[] data = Util.split(q.info, "\n");
                                try {
                                    int i = 0;
                                    while (i < data.length) {
                                        if (data[i].length() > 5) {
                                            byte color = (byte) (data[i].charAt(0) - 48);
                                            if (!MainMenu.isDegit(data[i].charAt(0))) {
                                                color = 0;
                                            } else {
                                                data[i] = data[i].substring(1);
                                            }
                                            in = new InfoTextShow(new String[] { data[i] }, color);
                                            minfo.addElement(in);
                                        }
                                        ++i;
                                    }
                                } catch (Exception exception) {
                                    // empty catch block
                                }
                            }
                            this.setShowText(minfo, this.xShowText, this.yShowText, null, true, false);
                            selected = -1;
                            this.vHanhTrang.removeAllElements();
                            this.vHanhTrang.addElement(cmdMapScr);
                            if (q != null && q.status == 2) {
                                this.vHanhTrang.addElement(cmdHuyQuest);
                            }
                            this.SortCmdItem();
                        }
                    }
                } else if (this.isAnimal && !this.isShowFill || this.isSkill) {
                    int hh = (this.isSkill ? 6 : 0) + 21;
                    int hh1 = 0;
                    if (this.isSkill) {
                        hh1 = this.size * 2 - this.size / 2;
                    }
                    int i = 0;
                    while (i < this.xWearing.length) {
                        ++i;
                    }
                    if (GameCanvas.menu.showMenu) {
                        return;
                    }
                    if (this.isSkill) {
                        this.cmdSelectItem.setXY(-100, -100);
                    }
                    if (GameCanvas.isTouch
                            && GameCanvas.isPointer(this.xWearing[0] - this.size / 2, this.yWearing[0] + this.size + 7,
                            this.wInfoWearing + 20 + this.size, this.size * 5 - 10, 0)
                            && this.scrSkill.selectedItem != -1) {
                        if (selected != this.scrSkill.selectedItem) {
                            this.setCmdCenter("updateTouch 1");
                            this.setCmdShowText();
                            selected = this.scrSkill.selectedItem;
                            this.cmdSelectItem.performAction();
                        } else {
                            this.cmdSelectItem.performAction();
                        }
                    }
                } else if (this.isFeatures) {
                    int size1;
                    w0 = mGraphics.getImageWidth(GameScr.imgTextfileld);
                    h0 = mGraphics.getImageHeight(GameScr.imgTextfileld) / 2;
                    xTouch = this.x + this.w - 7 - this.colum * this.size - w0 / 2 + 6 * this.size / 2;
                    yTouch = this.y + this.sizeH + 16 - h0;
                    int n = size1 = GameCanvas.w < 200 ? 22 : 30;
                    if (GameCanvas.isPointer(xTouch, yTouch, w0, feaTures.length * size1, 0) && GameCanvas.canTouch()) {
                        selected = (GameCanvas.py[0] - yTouch) / size1;
                        if (GameCanvas.isPointerJustRelease[0] && GameCanvas.isPointerClick[0]) {
                            if (this.lastSelect != selected) {
                                this.setCmdCenter("updateTouch 2");
                                this.lastSelect = selected;
                            } else {
                                this.cmdSelectItem.performAction();
                            }
                        }
                    }
                }
                this.isFocusCloseItemFill = false;
                if (!this.isShowFill)
                    break block65;
                int hc1 = mGraphics.getImageHeight(GameScr.imgButton[4]) / 2;
                int wc1 = mGraphics.getImageWidth(GameScr.imgButton[4]);
                if (!GameCanvas.isPointer(this.xItemFill + this.wItemFill - wc - 2, this.yItemFill - 22, wc1, hc1, 0)
                        || !GameCanvas.canTouch())
                    break block65;
                this.isFocusCloseItemFill = true;
                if (!GameCanvas.isPointerJustRelease[0] || !GameCanvas.isPointerClick[0])
                    break block65;
                this.doClose();
                this.cmrItem.clear();
                this.lastSelect = -1;
                selected = -1;
                break block65;
            }
            if (this.indexMainTab == 1) {
                int i = 0;
                while (i < this.vHanhTrang.size()) {
                    mCommand cmd = (mCommand) this.vHanhTrang.elementAt(i);
                    if (cmd != null && this.getCmdPointerLast(cmd)) {
                        GameCanvas.isPointerJustRelease[0] = false;
                        cmd.performAction();
                        break;
                    }
                    Cout.println(String.valueOf(i) + " getCmdPointerLast(cmd)  " + this.getCmdPointerLast(cmd));
                    ++i;
                }
                int hh = (this.isSkill ? 6 : 0) + 21;
                int i2 = 0;
                while (i2 < this.xWearing.length) {
                    if (GameCanvas.isPointer(this.xWearing[i2], this.yWearing[i2], hh, hh, 0)
                            && GameCanvas.canTouch()) {
                        selected = i2;
                        if (this.isSkill) {
                            this.cmdSelectItem.setXY(-100, -100);
                        }
                        if (!GameCanvas.isPointerJustRelease[0] || !GameCanvas.isPointerClick[0])
                            break;
                        if (this.lastSelect != selected) {
                            this.setCmdCenter("updateTouch 3");
                            this.cmdShowText.performAction();
                            this.lastSelect = selected;
                        } else {
                            this.cmdSelectItem.performAction();
                        }
                        break;
                    }
                    ++i2;
                }
            } else if (this.indexMainTab == 2) {
                int hh = (this.isSkill ? 6 : 0) + 21;
                if (GameCanvas.isPointer(this.xbg, this.yInfoWearing, 2 * this.size, this.hInfoWearing - 36, 0)
                        && GameCanvas.canTouch()) {
                    int x00 = this.x + this.size / 2 + selected % this.colum * this.size + 16;
                    int y00 = 11 + this.y + selected / this.colum * this.size + this.size * 3 / 2 + this.size - 1;
                    mVector minfo = new mVector();
                    isFocusCharWearing = true;
                    isFocusDetailMenu = false;
                    minfo = this.infochar;
                    this.vHanhTrang.removeAllElements();
                    this.setShowText(minfo, x00, y00, null, false, true);
                    this.numItemStart = 0;
                    this.numKhamNgoc = 0;
                    this.idNgocKham = null;
                    selected = -1;
                    this.lastSelect = -1;
                }
                int i = 0;
                while (i < this.xyTiemNang.length) {
                    if (GameCanvas.isPointer(this.xyTiemNang[i][0] - this.size / 2,
                            this.xyTiemNang[i][1] - this.size / 2, this.size, this.size, 0) && GameCanvas.canTouch()) {
                        if (GameCanvas.isPointerJustRelease[0] && GameCanvas.isPointerClick[0]) {
                            GameCanvas.isPointerClick[0] = false;
                            selected = i;
                            mVector minfo = new mVector();
                            InfoTextShow in = new InfoTextShow(new String[] { T.infoTiemNang[selected] }, 0);
                            minfo.addElement(in);
                            this.setShowText(minfo, this.xyTiemNang[selected][0] + 12,
                                    this.xyTiemNang[selected][1] + 12, null, true, true);
                            this.vHanhTrang.removeAllElements();
                            if (Char.Diemtiemnang > 0 && !this.isTextmua) {
                                this.vHanhTrang.addElement(cmdcong1);
                                if (Char.Diemtiemnang >= 5) {
                                    this.vHanhTrang.addElement(cmdcong5);
                                }
                                if (Char.Diemtiemnang >= 10) {
                                    this.vHanhTrang.addElement(cmdcong10);
                                }
                                this.SortCmdItem();
                            }
                            this.lastSelect = selected;
                        }
                        isFocusCharWearing = false;
                        isFocusDetailMenu = true;
                        break;
                    }
                    ++i;
                }
                i = 0;
                while (i < this.xWearing.length) {
                    if (GameCanvas.isPointer(this.xWearing[i] - this.size / 2, this.yWearing[i], hh, hh, 0)
                            && GameCanvas.canTouch()) {
                        selected = i;
                        if (selected == 14) {
                            GameService.gI().dochangeCharWearing();
                        }
                        isFocusCharWearing = false;
                        isFocusDetailMenu = false;
                        this.vHanhTrang.removeAllElements();
                        if (this.isSkill) {
                            this.cmdSelectItem.setXY(-100, -100);
                        }
                        if (!GameCanvas.isPointerJustRelease[0] || !GameCanvas.isPointerClick[0])
                            break;
                        if (this.lastSelect != selected) {
                            this.setCmdCenter("updateTouch 4");
                            this.cmdShowText.performAction();
                            this.lastSelect = selected;
                        } else {
                            this.cmdSelectItem.performAction();
                        }
                        break;
                    }
                    ++i;
                }
            }
        }
    }

    public void doShowInfoItemWearing() {
        int x01 = this.x + this.size / 2 + selected % this.colum * this.size + 16;
        int y01 = 11 + this.y + selected / this.colum * this.size + this.size * 3 / 2 + this.size - 1;
        Item sItem1 = MainMenu.charWearing.equip[selected + this.indexWearing];
        if (sItem1 != null) {
            this.showItemInventoryInfo(sItem1, this.isSell, x01, y01, null);
        }
    }

    public void doShowInfoItemShop() {
        if (this.mShop.size() > 0) {
            int x0 = this.x + this.size / 2 + selected % this.colum * this.size + 16;
            int y0 = 11 + this.y + selected / this.colum * this.size + this.size * 3 / 2 + this.size - 1;
            Item sItem = (Item) this.mShop.elementAt(selected);
            if (sItem != null) {
                this.showItemInventoryInfo(sItem, this.isSell, x0, y0, null);
                this.left = cmdBuy;
            }
        }
    }

    public void doShowInfoItemInventory() {
        if (this.indexMainTab == 1 && selected >= 0 && Char.inventory.size() > 0) {
            int x0 = this.x + this.size / 2 + selected % this.colum * this.size + 16;
            int y0 = 11 + this.y + selected / this.colum * this.size + this.size * 3 / 2 + this.size - 1;
            Item sItem = (Item) Char.inventory.elementAt(selected);
            if (sItem != null) {
                int type = sItem.getTypeItem();
                Item itcp = null;
                if (POS_ITEM_IN_EQUIP[type] > -1) {
                    itcp = GameScr.mainChar.equip[POS_ITEM_IN_EQUIP[type]];
                }
                this.showItemInventoryInfo(sItem, this.isSell, x0, y0, itcp);
            }
        }
    }

    public void doShowInfoPet() {
        if (GameScr.mainChar.equip != null && GameScr.mainChar.equip[14] != null) {
            int x0 = this.x + this.size / 2 + selected % this.colum * this.size + 16;
            int y0 = 11 + this.y + selected / this.colum * this.size + this.size * 3 / 2 + this.size - 1;
            this.showItemInventoryInfo(GameScr.mainChar.equip[14], this.isSell, x0, y0, null);
        }
    }

    public void updatePetWearing() {
        if (!isPet) {
            return;
        }
        if (this.mpet != null) {
            this.mpet.updateMenu();
        }
        if (GameCanvas.isPointer(this.xbg, this.yInfoWearing, 2 * this.size, this.hInfoWearing - 36, 0)
                && GameCanvas.canTouch()) {
            this.FocusPet = -1;
            selected = -1;
            this.vHanhTrang.removeAllElements();
            this.isFocusPetWearing = true;
            if (GameScr.mainChar.equip != null && GameScr.mainChar.equip[14] != null) {
                int x0 = this.x + this.size / 2 + selected % this.colum * this.size + 16;
                int y0 = 11 + this.y + selected / this.colum * this.size + this.size * 3 / 2 + this.size - 1;
                this.showItemInventoryInfo(GameScr.mainChar.equip[14], this.isSell, x0, y0, null);
            }
        }
        ScrollResult r = this.scrDapdo.updateKey();
        this.scrDapdo.updatecm();
        if (!GameCanvas.isTouch) {
            Item sItem;
            int y0;
            int x0;
            if (GameCanvas.isKeyPressed(6)) {
                if (++selected > vecPetEat.size() - 1) {
                    selected = vecPetEat.size() - 1;
                }
                if (selected >= 0) {
                    if (selected < vecPetEat.size() - 1 && selected > 1 && selected % 5 == 0) {
                        this.scrDapdo.moveTo((selected / 5 + 1) * this.sizeDapdo);
                    }
                    x0 = this.x + this.size / 2 + selected % this.colum * this.size + 16;
                    y0 = 11 + this.y + selected / this.colum * this.size + this.size * 3 / 2 + this.size - 1;
                    sItem = (Item) vecPetEat.elementAt(selected);
                    if (sItem != null) {
                        this.showItemInventoryInfo(sItem, this.isSell, x0, y0, null);
                        this.setCmdCenter("updateDapDo 0");
                    }
                }
            }
            if (GameCanvas.isKeyPressed(4)) {
                if (selected >= 0) {
                    --selected;
                }
                if (selected < 0) {
                    selected = 0;
                }
                if (selected >= 0) {
                    x0 = this.x + this.size / 2 + selected % this.colum * this.size + 16;
                    y0 = 11 + this.y + selected / this.colum * this.size + this.size * 3 / 2 + this.size - 1;
                    sItem = (Item) vecPetEat.elementAt(selected);
                    if (sItem != null) {
                        this.showItemInventoryInfo(sItem, this.isSell, x0, y0, null);
                        this.setCmdCenter("updateDapDo 0");
                    }
                    if (selected < vecPetEat.size() - 1) {
                        this.scrDapdo.moveTo(selected / 5 * this.sizeDapdo);
                    }
                }
            }
        }
        if (GameCanvas.isKeyPressed(12) && !GameCanvas.isTouch && cmdChoan != null) {
            cmdChoan.performAction();
        }
        if (GameCanvas.isTouch && GameCanvas.isPointer(this.xWearing[0] - 20, this.yWearing[0] + 3, 142, 74, 0)
                && this.scrDapdo.selectedItem != -1 && selected != this.scrDapdo.selectedItem) {
            selected = this.scrDapdo.selectedItem;
            this.FocusPet = -1;
            this.isFocusPetWearing = false;
            this.vHanhTrang.removeAllElements();
            this.setCmdCenter("updateDapDo 0");
        }
    }

    public void updateMountWearing() {
        // Handle touch on mount display area
        if (GameCanvas.isPointer(this.xbg, this.yInfoWearing, 2 * this.size, this.hInfoWearing - 36, 0)
                && GameCanvas.canTouch()) {
            selected = -1;
            this.vHanhTrang.removeAllElements();
        }

        // Handle touch on mount character display to show equip[11] info
        int mountCharX = this.x + this.size * 5 + 4;
        int mountCharY = this.y + this.h - 2 * this.size - this.size / 2 - (GameCanvas.isSmallScreen ? 24 : 38) + this.hIP;
        if (GameCanvas.isPointer(mountCharX - this.size / 2, mountCharY - this.size, this.size * 2, this.size * 2, 0)
                && GameCanvas.canTouch()) {
            if (GameScr.mainChar.equip != null && GameScr.mainChar.equip[11] != null) {
                selected = -1;
                this.vHanhTrang.removeAllElements();
                this.showItemInventoryInfo(GameScr.mainChar.equip[11], false, mountCharX, mountCharY, null);
            }
        }

        // Handle touch on mount equipment slots (6 slots)
        if (GameCanvas.isTouch) {
            for (int i = 0; i < 6; i++) {
                int row = i / 2;
                int col = i % 2;
                int xSlot = this.xbg + this.size - 10 + col * (this.size + 2);
                int ySlot = this.y + this.h - 5 * this.size - this.size + 5 + row * this.size;
                if (GameCanvas.isPointer(xSlot - this.size / 2, ySlot - this.size / 2, this.size, this.size, 0)
                        && GameCanvas.canTouch()) {
                    selected = i;
                    this.vHanhTrang.removeAllElements();
                    this.setCmdCenter("updateMountEquip");
                    if (GameScr.mainChar.mount != null && GameScr.mainChar.mount[i] != null) {
                        int x0 = xSlot;
                        int y0 = ySlot;
                        this.showItemInventoryInfo(GameScr.mainChar.mount[i], false, x0, y0, null);
                    }
                    break;
                }
            }
        }

        ScrollResult r = this.scrDapdo.updateKey();
        this.scrDapdo.updatecm();

        if (!GameCanvas.isTouch) {
            Item sItem;
            int y0;
            int x0;

            // Key navigation down
            if (GameCanvas.isKeyPressed(6)) {
                ++selected;
                // Handle equipment slots (0-5)
                if (selected < 6) {
                    if (selected > 5) {
                        selected = 5;
                    }
                } else {
                    // Handle food items (6+)
                    if (selected > vecMountEat.size() + 5) {
                        selected = vecMountEat.size() + 5;
                    }
                }

                if (selected >= 0) {
                    if (selected < 6) {
                        // Show mount equipment info
                        if (GameScr.mainChar.mount != null && GameScr.mainChar.mount[selected] != null) {
                            int row = selected / 2;
                            int col = selected % 2;
                            x0 = this.xbg + this.size - 10 + col * (this.size + 2);
                            y0 = this.y + this.h - 5 * this.size - this.size + 5 + row * this.size;
                            this.showItemInventoryInfo(GameScr.mainChar.mount[selected], false, x0, y0, null);
                        }
                        this.setCmdCenter("updateMountEquip");
                    } else {
                        // Show food item info
                        int foodIndex = selected - 6;
                        if (foodIndex < vecMountEat.size() && foodIndex > 1 && foodIndex % 5 == 0) {
                            this.scrDapdo.moveTo((foodIndex / 5 + 1) * this.sizeDapdo);
                        }
                        x0 = this.x + this.size / 2 + selected % this.colum * this.size + 16;
                        y0 = 11 + this.y + selected / this.colum * this.size + this.size * 3 / 2 + this.size - 1;
                        sItem = (Item) vecMountEat.elementAt(foodIndex);
                        if (sItem != null) {
                            this.showItemInventoryInfo(sItem, this.isSell, x0, y0, null);
                            this.setCmdCenter("updateMountFood");
                        }
                    }
                }
            }

            // Key navigation up
            if (GameCanvas.isKeyPressed(4)) {
                if (selected >= 0) {
                    --selected;
                }
                if (selected < 0) {
                    selected = 0;
                }

                if (selected >= 0) {
                    if (selected < 6) {
                        // Show mount equipment info
                        if (GameScr.mainChar.mount != null && GameScr.mainChar.mount[selected] != null) {
                            int row = selected / 2;
                            int col = selected % 2;
                            x0 = this.xbg + this.size - 10 + col * (this.size + 2);
                            y0 = this.y + this.h - 5 * this.size - this.size + 5 + row * this.size;
                            this.showItemInventoryInfo(GameScr.mainChar.mount[selected], false, x0, y0, null);
                        }
                        this.setCmdCenter("updateMountEquip");
                    } else {
                        // Show food item info
                        int foodIndex = selected - 6;
                        x0 = this.x + this.size / 2 + selected % this.colum * this.size + 16;
                        y0 = 11 + this.y + selected / this.colum * this.size + this.size * 3 / 2 + this.size - 1;
                        sItem = (Item) vecMountEat.elementAt(foodIndex);
                        if (sItem != null) {
                            this.showItemInventoryInfo(sItem, this.isSell, x0, y0, null);
                            this.setCmdCenter("updateMountFood");
                        }
                        if (foodIndex < vecMountEat.size() - 1) {
                            this.scrDapdo.moveTo(foodIndex / 5 * this.sizeDapdo);
                        }
                    }
                }
            }
        }

        // Handle center key press
        if (GameCanvas.isKeyPressed(12) && !GameCanvas.isTouch && cmdChoan != null) {
            cmdChoan.performAction();
        }

        // Handle touch on food items
        if (GameCanvas.isTouch && GameCanvas.isPointer(this.xWearing[0] - 20, this.yWearing[0] + 3, 142, 74, 0)
                && this.scrDapdo.selectedItem != -1 && selected != this.scrDapdo.selectedItem + 6) {
            selected = this.scrDapdo.selectedItem + 6;
            this.vHanhTrang.removeAllElements();
            this.setCmdCenter("updateMountFood");
        }
    }

    @Override
    public void updateKey() {
        int to;
        int size;
        int total;
        ScrollResult r;
        if (GameCanvas.currentDialog != null) {
            return;
        }
        if (this.indexMainTab == 8) {
            return;
        }
        if (GameCanvas.isTouch) {
            this.updateTouch();
            this.updateMainTab();
        }
        if (GameCanvas.keyReleasedz[5] && !GameCanvas.isTouch) {
            if (this.indexMainTab == 12) {
                this.doShowInfoPet();
            } else if (this.indexMainTab == 1) {
                this.doShowInfoItemInventory();
            } else if (this.indexMainTab == 0) {
                this.doShowInfoItemShop();
            }else if (this.indexMainTab == 4) {
                this.doShowInfoItemMount();
            }
            GameCanvas.keyReleasedz[5] = false;
        }
        if (this.indexMainTab == 2 && mSystem.isj2me && GameCanvas.isKeyPressed(5)) {
            if (this.indexWearing <= 0) {
                cmdTB2.performAction();
            } else {
                cmdTB1.performAction();
            }
        }
        if (!this.isShowInFoChar || GameCanvas.isTouch && this.isShowFill) {
            if (this.cmrItem != null && !this.isFeatures && !this.isSkill) {
                r = this.cmrItem.updateKey();
                if (r.isDowning || r.isFinish) {
                    if (!this.isSkill) {
                        selected = r.selected;
                    }
                    if (this.isQuest) {
                        indexQuest = r.selected;
                    }
                }
                if (r.isFinish && GameCanvas.isTouch && r.selected != -1 && !this.isSkill) {
                    selected = r.selected;
                    this.cmdSelectItem.setXY(-100, -100);
                    this.isSetXYCmdSelect = true;
                    this.vHanhTrang.removeAllElements();
                    this.closeText();
                    switch (this.indexMainTab) {
                        case 0: {
                            if (this.mShop.size() <= 0)
                                break;
                            int x0 = this.x + this.size / 2 + selected % this.colum * this.size + 16;
                            int y0 = 11 + this.y + selected / this.colum * this.size + this.size * 3 / 2 + this.size
                                    - 1;
                            Item sItem = (Item) this.mShop.elementAt(selected);
                            if (sItem == null)
                                break;
                            this.showItemInventoryInfo(sItem, this.isSell, x0, y0, null);
                            this.vHanhTrang.removeAllElements();
                            this.vHanhTrang.addElement(cmdBuy);
                            MainMenu.cmdBuy.caption = !captionServer.equals("") ? captionServer : T.Buy;
                            this.canbuy = false;
                            this.SortCmdItem();
                            break;
                        }
                        case 3: {
                            if (selected <= currnentTabDetail.length - 1 && !this.isPaintSub()) {
                                isFocusDetailMenu = true;
                                this.setCmdCenter("updateKey 0");
                                if (this.center != null) {
                                    this.center.performAction();
                                }
                            }
                            if (this.isQuest) {
                                indexQuest = selected;
                                break;
                            }
                            if (!this.isShowFill)
                                break;
                            if (this.lastSelect != selected) {
                                this.lastSelect = selected;
                                this.setCmdCenter("updateKey 1");
                                this.cmdShowText = new mCommand("", this, -4);
                                this.timeAuToShowText = 2;
                                this.beGinShowText = false;
                                break;
                            }
                            this.setCmdCenter("updateKey 2");
                            this.cmdSelectItem.performAction();
                            break;
                        }
                        case 5: {
                            isFocusDetailMenu = true;
                            if (this.lastSelect != selected) {
                                this.lastSelect = selected;
                                this.setPosScroll(this.indexMainTab, selected);
                                this.setCmdCenter("updateKey 3");
                                break;
                            }
                            this.setCmdCenter("updateKey 4");
                            this.cmdSelectItem.performAction();
                            break;
                        }
                        case 4:
                        case 6:
                        case 7: {
                            isFocusDetailMenu = true;
                            if (this.lastSelect != selected) {
                                this.lastSelect = selected;
                                this.setCmdCenter("updateKey 5");
                                break;
                            }
                            this.setCmdCenter("updateKey 6");
                            this.cmdSelectItem.performAction();
                            break;
                        }
                        case 1: {
                            if (this.lastSelect != selected) {
                                this.lastSelect = selected;
                                this.setCmdCenter("updateKey 7");
                                this.cmdShowText.performAction();
                                break;
                            }
                            if (GameCanvas.isTouch) {
                                this.setCmdCenter("updateKey 8");
                                this.cmdShowText.performAction();
                                break;
                            }
                            this.cmdSelectItem.performAction();
                            break;
                        }
                        case 2: {
                            if (this.lastSelect != selected) {
                                this.lastSelect = selected;
                                this.setCmdCenter("updateKey 9");
                                this.cmdShowText.performAction();
                                break;
                            }
                            this.cmdSelectItem.performAction();
                        }
                    }
                }
                if (!this.isSkill) {
                    this.cmrItem.updatecm();
                }
            }
            if (this.cmrSubTab != null) {
                r = this.cmrSubTab.updateKey();
                if (!r.isDowning) {
                    boolean cfr_ignored_0 = r.isFinish;
                }
                boolean cfr_ignored_1 = r.isFinish;
                this.cmrSubTab.updatecm();
            }
        } else if (this.cmrShowInfoMainChar != null) {
            r = this.cmrShowInfoMainChar.updateKey();
            this.cmrShowInfoMainChar.updatecm();
        }
        r = this.cmrShowText.updateKey();
        this.cmrShowText.updatecm();
        if (!isFocusDetailMenu) {
            this.timeAuToShowText = 0;
        }
        --this.timeAuToShowText;
        if (this.timeAuToShowText <= 0) {
            this.timeAuToShowText = 0;
        }
        this.setAutoActionCmd(this.cmdShowText);
        if (GameCanvas.isTouch) {
            return;
        }
        if (this.isShowFill) {
            if (GameCanvas.isKeyPressed(4)) {
                this.isMoveQuest = false;
                if (this.isUseCmr) {
                    this.closeText();
                    this.isShowFill = false;
                    return;
                }
                if (--selected < 0) {
                    selected = this.totalItemFill.size() - 1;
                }
                this.cmdShowText = new mCommand("", this, -4);
                this.timeAuToShowText = 15;
                this.beGinShowText = false;
            } else if (GameCanvas.isKeyPressed(6)) {
                if (this.isUseCmr) {
                    this.closeText();
                    this.isShowFill = false;
                    return;
                }
                if (++selected > this.totalItemFill.size() - 1) {
                    selected = 0;
                }
                this.cmdShowText = new mCommand("", this, -4);
                this.timeAuToShowText = 15;
                this.beGinShowText = false;
            } else if (GameCanvas.isKeyPressed(2)) {
                if (this.isUseCmr) {
                    this.cmrShowText.moveCmrTo(-1);
                }
            } else if (GameCanvas.isKeyPressed(8) && this.isUseCmr) {
                this.cmrShowText.moveCmrTo(1);
            }
        } else if (GameCanvas.keyPressedz[6]) {
            this.isMoveQuest = false;
            GameCanvas.keyPressedz[6] = false;
            if (this.isQuest && !GameCanvas.isTouch) {
                this.countR = 5;
                indexTypeQuest = (indexTypeQuest + 1) % QuestTile.length;
                indexQuest = -1;
                this.movecmrQuest();
                return;
            }
            switch (this.indexMainTab) {
                case 2: {
                    if (!isChangeSubTab) {
                        isFocusCharWearing = true;
                        isChangeSubTab = true;
                        selected = -1;
                    } else if (isFocusCharWearing) {
                        this.isShowInFoChar = true;
                        this.indexShowInfo = 0;
                        isFocusCharWearing = false;
                        this.isShowInFoChar = true;
                        isFocusDetailMenu = true;
                        selected = 0;
                        this.closeText();
                    } else if (!isFocusCharWearing && isFocusDetailMenu) {
                        this.closeText();
                        if (++selected >= 4) {
                            selected = 0;
                            isFocusDetailMenu = false;
                        } else {
                            mVector minfo = new mVector();
                            InfoTextShow in = new InfoTextShow(new String[] { T.infoTiemNang[selected] }, 0);
                            minfo.addElement(in);
                            this.setShowText(minfo, this.xyTiemNang[selected][0] + 12,
                                    this.xyTiemNang[selected][1] + 12, null, true, true);
                        }
                    }
                    if (isFocusDetailMenu || !isChangeSubTab || isFocusCharWearing)
                        break;
                    if (!isChangeSubTab && selected == -1) {
                        isChangeSubTab = true;
                    }
                    if (!isChangeSubTab)
                        break;
                    if (this.isShowInFoChar) {
                        this.isShowInFoChar = false;
                        selected = -1;
                    }
                    if (this.isUseCmr) {
                        this.closeText();
                        break;
                    }
                    if (this.indexMainTab == 5)
                        break;
                    total = currnentTabDetail.length - 1;
                    if (this.isCharWearing) {
                        total = 15;
                    }
                    this.setCmdCenter("updateKey 10");
                    this.cmdShowText.performAction();
                    if (++selected <= total)
                        break;
                    selected = 0;
                    break;
                }
                case 0:
                case 1:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7: {
                    if (!isFocusDetailMenu) {
                        if (this.indexMainTab != 2) {
                            isFocusDetailMenu = true;
                            isChangeSubTab = true;
                            if (this.indexMainTab != 0 && !this.isHanhTrang) {
                                selected = 0;
                            } else if (this.indexMainTab == 0 || this.indexMainTab == 1 && this.isHanhTrang) {
                                selected = 0;
                                isChangeSubTab = false;
                            }
                        }
                        if (this.isTabQuest()) {
                            this.isQuest = true;
                        }
                        this.doSelectMainTabNotTouch();
                        break;
                    }
                    if (!isChangeSubTab && selected == -1) {
                        isChangeSubTab = true;
                    }
                    if (!isChangeSubTab) {
                        if (this.indexMainTab != 0 && (this.indexMainTab != 1 || !this.isHanhTrang))
                            break;
                        ++selected;
                        size = Char.inventory.size();
                        if (this.indexMainTab == 0) {
                            size = this.mShop.size();
                        }
                        if (selected > size - 1) {
                            selected = 0;
                        }
                        this.setCmdCenter("updateKey 11");
                        break;
                    }
                    if (this.isShowInFoChar) {
                        this.isShowInFoChar = false;
                        selected = -1;
                    }
                    if (this.isUseCmr) {
                        this.closeText();
                        break;
                    }
                    if (this.isHanhTrang && this.indexMainTab == 1 || this.indexMainTab == 0) {
                        ++this.indexSubTab;
                        if (this.indexSubTab > currnentTabDetail.length - 1) {
                            this.indexSubTab = currnentTabDetail.length - 1;
                        }
                        if (this.indexMainTab == 0 && this.isMiniShop) {
                            ++this.indexTabMiniShop;
                            if (this.indexTabMiniShop > currnentTabDetail.length - 1) {
                                this.indexTabMiniShop = currnentTabDetail.length - 1;
                            }
                        }
                        this.focusTab = (byte) this.indexSubTab;
                        this.setSelectTab("updateKey 6");
                        this.setCmdCenter("updateKey 12");
                        this.countR = 5;
                        break;
                    }
                    if (this.indexMainTab == 5)
                        break;
                    total = currnentTabDetail.length - 1;
                    if (this.isCharWearing) {
                        total = 15;
                    } else if (this.isSkill) {
                        total = GameScr.mainChar.getnSkill() - 1;
                    }
                    if (++selected > total) {
                        selected = 0;
                    }
                    if (this.isSkill) {
                        this.setCmdCenter("updateKey 13");
                    }
                    if (!this.isQuest || !this.isTabQuest())
                        break;
                    this.countR = 5;
                    indexTypeQuest = (indexTypeQuest + 1) % QuestTile.length;
                    indexQuest = -1;
                    this.movecmrQuest();
                }
            }
            if (GameCanvas.isTouch && !this.isQuest) {
                isChangeSubTab = false;
                isFocusDetailMenu = false;
            }
        } else if (GameCanvas.keyPressedz[4]) {
            GameCanvas.keyPressedz[4] = false;
            this.isMoveQuest = false;
            this.actionKey4();
            if (GameCanvas.isTouch) {
                isChangeSubTab = false;
                isFocusDetailMenu = false;
            }
        } else if (GameCanvas.keyPressedz[8]) {
            GameCanvas.keyPressedz[8] = false;
            if (this.indexMainTab == 2 && isChangeSubTab) {
                if (isFocusDetailMenu) {
                    if ((selected += 2) > 3) {
                        selected = 0;
                        isFocusDetailMenu = false;
                    } else {
                        mVector minfo = new mVector();
                        InfoTextShow in = new InfoTextShow(new String[] { T.infoTiemNang[selected] }, 0);
                        minfo.addElement(in);
                        this.setShowText(minfo, this.xyTiemNang[selected][0] + 12, this.xyTiemNang[selected][1] + 12,
                                null, true, true);
                    }
                } else if (isFocusCharWearing) {
                    if (this.isUseCmr) {
                        this.cmrShowText.moveCmrTo(1);
                    } else {
                        selected = 0;
                        isFocusCharWearing = false;
                    }
                } else if (!isFocusCharWearing && !isFocusDetailMenu) {
                    if ((selected += 5) > 14) {
                        selected = -1;
                        ++this.indexMainTab;
                        if (this.indexMainTab > this.numtab - 1) {
                            this.indexMainTab = 0;
                            if (this.isMiniShop) {
                                this.indexSubTab = this.indexTabMiniShop;
                            }
                        }
                        this.reSetAllCmr();
                        this.setPosScroll(this.indexMainTab, -1);
                        this.setSelectTab("updateKey 8");
                        this.setCmdCenter("updateKey 14");
                        this.setTile();
                    } else {
                        this.setCmdCenter("updateKey 15");
                        this.cmdShowText.performAction();
                    }
                }
                return;
            }
            if (this.isQuest) {
                if (isChangeSubTab) {
                    isChangeSubTab = false;
                    isFocusDetailMenu = true;
                    selected = -1;
                } else {
                    if (++selected > this.cmrItem.nITEM - 1) {
                        this.cmrItem.moveTo(selected / (this.cmrItem.nITEM - 1) * 20);
                        selected = 0;
                    }
                    if (this.isMoveQuest) {
                        if (indexQuest < ListQuest[indexTypeQuest].size()) {
                            ++indexQuest;
                        }
                        selected = indexQuest;
                        this.isMoveQuest = false;
                    }
                    indexQuest = selected;
                    this.closeText();
                }
                return;
            }
            if (!isFocusDetailMenu) {
                this.indexSubTab = 0;
                if (this.indexMainTab < this.numtab - 1) {
                    ++this.indexMainTab;
                }
                if (this.maptopaintMenuIcon[this.indexMainTab] == -1) {
                    if (this.maptopaintMenuIcon[this.indexMainTab] == -1) {
                        this.indexPet = 0;
                        this.updatepetItem();
                    } else {
                        this.indexMainTab = 0;
                        if (this.isMiniShop) {
                            this.indexSubTab = this.indexTabMiniShop;
                        }
                    }
                }
                this.isSkill = false;
                this.isQuest = false;
                selected = -1;
                this.reSetAllCmr();
                this.setPosScroll(this.indexMainTab, -1);
                this.setSelectTab("updateKey 81");
                this.setCmdCenter("updateKey 16");
                this.setTile();
                this.doSelectMainTabNotTouch();
            } else if (this.indexMainTab == 0 || this.indexMainTab == 1 && this.isHanhTrang) {
                if (!this.isUseCmr) {
                    if (!isChangeSubTab) {
                        selected += this.colum;
                        size = Char.inventory.size();
                        if (this.indexMainTab == 0) {
                            size = this.mShop.size();
                        }
                        if (selected / size >= 1) {
                            this.cmrItem.moveTo(selected / this.colum * size);
                            selected = 0;
                        }
                    }
                    if (isChangeSubTab) {
                        isChangeSubTab = false;
                        selected = 0;
                    }
                    this.setCmdCenter("updateKey 17");
                } else {
                    this.cmrShowText.moveCmrTo(1);
                }
            } else if (this.isUseCmr) {
                this.cmrShowText.moveCmrTo(1);
            } else if (this.isShowInFoChar) {
                to = 0;
                if (this.isAnimal) {
                    to = this.animalInfo.length - 1;
                } else if (this.infochar != null) {
                    to = this.totalInfoshow;
                }
                ++this.indexShowInfo;
                if (this.indexShowInfo > to) {
                    this.indexShowInfo = 0;
                }
            } else {
                total = currnentTabDetail.length - 1;
                if (this.isPaintSub()) {
                    if (this.isSkill) {
                        total = GameScr.mainChar.getnSkill() - 1;
                        this.closeText();
                    } else if (this.isFeatures) {
                        total = 4;
                    } else if (this.isCharWearing) {
                        total = 15;
                    } else if (this.isQuest) {
                        total = this.totalLineQuest;
                    }
                    this.setCmdShowText();
                }
                if (this.isCharWearing || this.isAnimal) {
                    if ((selected += 5) > 15) {
                        selected = 0;
                    }
                    this.setCmdCenter("updateKey 18");
                    this.cmdShowText.performAction();
                } else if (this.isSkill) {
                    if ((selected += 3) > total) {
                        selected = 0;
                    }
                    if (!GameCanvas.isTouch) {
                        this.setCmdCenter("updateKey 19");
                    }
                } else {
                    selected = this.indexMainTab != 5 ? (selected += this.isFeatures ? 1 : this.maxNumW) : ++selected;
                    if (selected > total) {
                        selected = 0;
                    }
                }
                if (this.isPaintSub() && this.isFeatures) {
                    if (selected == 0) {
                        this.setFocusTf(this.tfStrength);
                    } else if (selected == 1) {
                        this.setFocusTf(this.tfAgility);
                    } else if (selected == 2) {
                        this.setFocusTf(this.tfSpirit);
                    } else if (selected == 3) {
                        this.setFocusTf(this.tfHealth);
                    } else if (selected == 4) {
                        this.setFocusTf(this.tfLucky);
                    }
                }
            }
        } else if (GameCanvas.keyPressedz[2]) {
            if (this.indexMainTab == 12) {
                this.indexMainTab = this.numtab - 1;
            }
            this.indexPet = 0;
            GameCanvas.keyPressedz[2] = false;
            if (this.indexMainTab == 2) {
                boolean isNext = true;
                if (selected < 5 && selected > 1 && !isFocusDetailMenu) {
                    isFocusDetailMenu = true;
                    selected = 3;
                    isNext = false;
                } else if (!isFocusDetailMenu && selected < 2 && selected >= 0 && !isFocusCharWearing) {
                    isFocusCharWearing = true;
                    selected = -1;
                    isNext = false;
                } else if (selected >= 5 && !isFocusDetailMenu) {
                    selected -= 5;
                    this.setCmdCenter("updateKey 20");
                    this.cmdShowText.performAction();
                    isNext = false;
                } else if (isFocusDetailMenu) {
                    if ((selected -= 2) < 0) {
                        selected = -1;
                        isFocusCharWearing = false;
                    } else {
                        mVector minfo = new mVector();
                        InfoTextShow in = new InfoTextShow(new String[] { T.infoTiemNang[selected] }, 0);
                        minfo.addElement(in);
                        this.setShowText(minfo, this.xyTiemNang[selected][0] + 12, this.xyTiemNang[selected][1] + 12,
                                null, true, true);
                        isNext = false;
                    }
                }
                if (!isNext) {
                    return;
                }
            }
            if (this.isQuest) {
                --selected;
                if (this.isMoveQuest) {
                    if (indexQuest > 0) {
                        --indexQuest;
                    }
                    selected = indexQuest;
                    this.isMoveQuest = false;
                }
                indexQuest = selected;
                if (selected < 0) {
                    selected = 0;
                    isChangeSubTab = true;
                    isFocusDetailMenu = false;
                    this.cmrItem.selectedItem = -1;
                }
                this.closeText();
                return;
            }
            if (!isFocusDetailMenu) {
                boolean isSet = false;
                this.indexSubTab = 0;
                --this.indexMainTab;
                if (this.indexMainTab == 0) {
                    this.indexSubTab = this.indexTabMiniShop;
                }
                if (this.indexMainTab < 0) {
                    this.indexMainTab = this.numtab - 1;
                    if (this.isMiniShop) {
                        isSet = true;
                    }
                    this.indexPet = 0;
                    this.indexMainTab = 12;
                    this.updatepetItem();
                }
                this.isSkill = false;
                this.isQuest = false;
                selected = -1;
                this.reSetAllCmr();
                this.setPosScroll(this.indexMainTab, -1);
                this.setCmdCenter("updateKey 21");
                this.setSelectTab("updateKey 2");
                this.setTile();
                this.doSelectMainTabNotTouch();
            } else if (this.indexMainTab == 0 || this.indexMainTab == 1 && this.isHanhTrang) {
                if (!this.isUseCmr) {
                    if (!isChangeSubTab && (selected -= this.colum) < 0) {
                        selected = -1;
                        isChangeSubTab = true;
                    }
                    this.setCmdCenter("updateKey 22");
                } else {
                    this.cmrShowText.moveCmrTo(-1);
                }
            } else if (this.isShowInFoChar) {
                to = 0;
                if (this.isAnimal) {
                    to = this.animalInfo.length - 1;
                } else if (this.infochar != null) {
                    to = this.totalInfoshow;
                }
                --this.indexShowInfo;
                if (this.indexShowInfo < 0) {
                    this.indexShowInfo = to;
                }
            } else {
                total = currnentTabDetail.length - 1;
                if (this.isUseCmr) {
                    this.cmrShowText.moveCmrTo(-1);
                } else {
                    if (this.isPaintSub()) {
                        if (this.isSkill) {
                            total = GameScr.mainChar.getnSkill() - 1;
                            this.closeText();
                        } else if (this.isFeatures) {
                            total = 5;
                        } else if (this.isCharWearing) {
                            total = 15;
                        } else if (this.isQuest) {
                            total = this.totalLineQuest;
                        }
                        this.setCmdShowText();
                    }
                    if (this.isCharWearing || this.isAnimal) {
                        if ((selected -= 5) < 0) {
                            selected = -1;
                            this.isShowInFoChar = true;
                            this.closeText();
                            this.isAnimal = false;
                        } else {
                            this.setCmdCenter("updateKey 23");
                            this.cmdShowText.performAction();
                        }
                    } else if (this.isSkill) {
                        if ((selected -= 3) < 0) {
                            selected = total;
                        }
                        if (!GameCanvas.isTouch) {
                            this.setCmdCenter("updateKey 24");
                        }
                    } else {
                        selected = this.indexMainTab != 5 ? (selected -= this.isFeatures ? 1 : this.maxNumW)
                                : --selected;
                        if (selected < 0) {
                            selected = total;
                        }
                    }
                    if (this.isPaintSub() && this.isFeatures) {
                        if (selected == 0) {
                            this.setFocusTf(this.tfStrength);
                        } else if (selected == 1) {
                            this.setFocusTf(this.tfAgility);
                        } else if (selected == 2) {
                            this.setFocusTf(this.tfSpirit);
                        } else if (selected == 3) {
                            this.setFocusTf(this.tfHealth);
                        } else if (selected == 4) {
                            this.setFocusTf(this.tfLucky);
                        }
                    }
                }
            }
        } else if (GameCanvas.isKeyPressed(5)) {
            switch (this.indexMainTab) {
                case 3: {
                    this.center.performAction();
                    break;
                }
                case 4: {
                    this.center.performAction();
                }
            }
        }
        if (!GameCanvas.isTouch) {
            if (this.isQuest) {
                this.cmrItem.moveTo(indexQuest * this.sizeH);
            } else {
                if (this.indexMainTab != 0 && !this.isHanhTrang) {
                    int disY = 60;
                    if (this.isShowFill) {
                        this.cmrItem.moveTo(selected * this.size);
                    } else {
                        this.cmrItem.moveTo(selected / this.maxNumW * disY);
                    }
                } else {
                    this.cmrItem.moveTo(selected / this.colum * this.size);
                }
                if (this.indexMainTab != 0 && !this.isHanhTrang || this.isPaintSub()) {
                    this.cmrSubTab.moveTo(selected * 60);
                }
                if (this.isShowInFoChar) {
                    this.cmrShowInfoMainChar.moveTo(this.indexShowInfo * 12);
                }
            }
        }
        super.updateKey();
    }

    private void doShowInfoItemMount() {
        if (this.indexMainTab == 4 && selected >= 0) {
            // Check if selected is mount equipment (0-5)
            if (selected < 6) {
                if (GameScr.mainChar.mount != null && GameScr.mainChar.mount[selected] != null) {
                    int row = selected / 2;
                    int col = selected % 2;
                    int x0 = this.xbg + this.size - 10 + col * (this.size + 2);
                    int y0 = this.y + this.h - 5 * this.size - this.size + 5 + row * this.size;
                    this.showItemInventoryInfo(GameScr.mainChar.mount[selected], false, x0, y0, null);
                }
            }
            // Check if selected is mount food item (6+)
            else if (selected >= 6 && selected < vecMountEat.size() + 6) {
                int foodIndex = selected - 6;
                if (foodIndex >= 0 && foodIndex < vecMountEat.size()) {
                    int x0 = this.x + this.size / 2 + selected % this.colum * this.size + 16;
                    int y0 = 11 + this.y + selected / this.colum * this.size + this.size * 3 / 2 + this.size - 1;
                    Item sItem = (Item) vecMountEat.elementAt(foodIndex);
                    if (sItem != null) {
                        this.showItemInventoryInfo(sItem, false, x0, y0, null);
                    }
                }
            }
        }
    }

    public void actionKey4() {
        if (this.isQuest && !GameCanvas.isTouch) {
            if (this.isTabQuest()) {
                indexQuest = -1;
                if (--indexTypeQuest < 0) {
                    indexTypeQuest = 0;
                    this.isQuest = false;
                    isFocusDetailMenu = false;
                    isChangeSubTab = false;
                    this.left = null;
                }
                this.movecmrQuest();
            }
            return;
        }
        switch (this.indexMainTab) {
            case 2:
            case 8:
            case 9:
            case 10:
            case 11: {
                if (selected == 0 && !isFocusDetailMenu && !isFocusCharWearing) {
                    isFocusDetailMenu = true;
                    selected = 4;
                    this.closeText();
                } else if (isFocusCharWearing) {
                    isChangeSubTab = false;
                    isFocusCharWearing = false;
                    isFocusDetailMenu = false;
                    this.isShowInFoChar = false;
                    this.closeText();
                }
                if (isFocusDetailMenu) {
                    if (--selected < 0) {
                        selected = -1;
                        isFocusCharWearing = true;
                        isFocusDetailMenu = false;
                        break;
                    }
                    mVector minfo = new mVector();
                    InfoTextShow in = new InfoTextShow(new String[] { T.infoTiemNang[selected] }, 0);
                    minfo.addElement(in);
                    this.setShowText(minfo, this.xyTiemNang[selected][0] + 12, this.xyTiemNang[selected][1] + 12, null,
                            true, true);
                    break;
                }
                if (isFocusCharWearing) {
                    isFocusCharWearing = false;
                    isFocusDetailMenu = false;
                    break;
                }
                if (selected < 0)
                    break;
                if (this.isUseCmr) {
                    this.closeText();
                    break;
                }
                this.closeText();
                if (--selected < 0) {
                    isFocusDetailMenu = true;
                    break;
                }
                this.setCmdCenter("actionKey4 0");
                this.cmdShowText.performAction();
                break;
            }
            case 0:
            case 1:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7: {
                if (this.isUseCmr) {
                    this.closeText();
                    return;
                }
                if (!isChangeSubTab) {
                    if (!isFocusDetailMenu || this.indexMainTab != 0 && (this.indexMainTab != 1 || !this.isHanhTrang))
                        break;
                    if (selected % this.colum == 0) {
                        isFocusDetailMenu = false;
                        selected = -1;
                        if (!this.isMiniShop) {
                            if (this.isHanhTrang) {
                                currnentTabDetail = myInfo;
                            }
                            this.isHanhTrang = false;
                        }
                        this.setTile();
                        this.reSetAllCmr();
                    }
                    --selected;
                    this.setCmdCenter("actionKey4 1");
                    break;
                }
                if (this.isUseCmr) {
                    this.closeText();
                    break;
                }
                this.closeText();
                if ((this.indexMainTab == 3 || this.indexMainTab == 2 || this.indexMainTab == 8)
                        && (this.isQuest || this.isCharWearing || this.isAnimal || this.isSkill) && !this.isHanhTrang) {
                    if (this.isShowInFoChar) {
                        selected = -1;
                        this.indexSubTab = 0;
                        this.isCharWearing = false;
                        this.isShowInFoChar = false;
                        isFocusDetailMenu = false;
                        this.isAnimal = false;
                        this.setCmdCenter("actionKey4 2");
                    } else {
                        if (this.isCharWearing || this.isAnimal) {
                            if (selected % 6 == 0) {
                                isFocusDetailMenu = false;
                                isChangeSubTab = false;
                                selected = 0;
                                this.indexSubTab = 0;
                                this.isAnimal = false;
                                this.isCharWearing = false;
                            }
                        } else if (this.isSkill) {
                            if (selected % 3 == 0) {
                                isFocusDetailMenu = false;
                                isChangeSubTab = false;
                                selected = 0;
                                this.indexSubTab = 0;
                                this.isSkill = false;
                            }
                        } else if (this.isQuest && this.isTabQuest()) {
                            indexQuest = -1;
                            if (--indexTypeQuest < 0) {
                                indexTypeQuest = 0;
                                this.isQuest = false;
                                isFocusDetailMenu = false;
                                isChangeSubTab = false;
                                this.left = null;
                            }
                            this.movecmrQuest();
                        }
                        --selected;
                        this.setCmdCenter("actionKey4 3");
                    }
                    this.countL = 5;
                } else {
                    if (this.isFeatures) {
                        this.isFeatures = false;
                        selected = 0;
                        this.indexSubTab = 0;
                    } else if (this.isQuest) {
                        Cout.println(String.valueOf(isFocusDetailMenu) + " isQuest end  " + this.isQuest);
                        this.isQuest = false;
                        selected = 0;
                        this.indexSubTab = 0;
                    } else if (this.isQuestClan) {
                        this.isQuestClan = false;
                        selected = 0;
                        this.indexSubTab = 0;
                    }
                    if (this.indexMainTab == 0 || this.indexMainTab == 1 && this.isHanhTrang) {
                        --this.indexSubTab;
                        if (this.indexMainTab == 0 && this.isMiniShop) {
                            --this.indexTabMiniShop;
                            if (this.indexTabMiniShop < 0) {
                                this.indexTabMiniShop = 0;
                            }
                        }
                        if (this.indexSubTab < 0) {
                            isFocusDetailMenu = false;
                            isChangeSubTab = false;
                            selected = -1;
                            this.indexSubTab = 0;
                            if (!this.isMiniShop) {
                                if (this.isHanhTrang) {
                                    currnentTabDetail = myInfo;
                                }
                                this.isHanhTrang = false;
                            }
                            this.setTile();
                            this.reSetAllCmr();
                        }
                        this.focusTab = (byte) this.indexSubTab;
                        this.setSelectTab("actionKey4 1");
                        this.setCmdCenter("actionKey4 4");
                    } else if (selected % this.maxNumW == 0 || this.indexMainTab == 5) {
                        isFocusDetailMenu = false;
                        isChangeSubTab = false;
                        selected = -1;
                        this.indexSubTab = 0;
                        this.isSkill = false;
                    } else {
                        --selected;
                        this.setSelectTab("actionKey4 2");
                        this.setCmdCenter("actionKey4 5");
                    }
                    this.countL = 5;
                }
                if (isFocusDetailMenu)
                    break;
                this.setTile();
                break;
            }
        }
    }

    private void reSetAllCmr() {
        this.cmrItem.clear();
        this.cmrShowInfoMainChar.clear();
        this.cmrShowText.clear();
        this.cmrSubTab.clear();
    }

    public void setFocusTf(TField tf) {
        TField[] a = new TField[] { this.tfStrength, this.tfAgility, this.tfHealth, this.tfLucky, this.tfSpirit };
        int i = 0;
        while (i < a.length) {
            if (tf == a[i]) {
                tf.setFocus(true);
            } else {
                a[i].setFocus(false);
            }
            ++i;
        }
    }

    public void actionKey6() {
    }

    public void showQuest() {
        this.numItem = 4;
        this.isQuest = true;
        this.isSell = false;
        this.setQuestInfo();
        this.questInfo.removeAllElements();
        if (!GameCanvas.isTouch) {
            this.left = new mCommand("Xem", this, -3);
        }
        this.setImageCharWear();
        if (GameCanvas.isTouch) {
            this.indexMainTab = 3;
        }
        this.movecmrQuest();
        this.vHanhTrang.removeAllElements();
        this.vHanhTrang.addElement(cmdMapScr);
        this.SortCmdItem();
    }

    public void resetPopup() {
        this.isTextmua = false;
        MainMenu.cmdBuy.caption = T.Buy;
        MainMenu.cmdBuy.caption = !captionServer.equals("") ? captionServer : T.Buy;
        this.canbuy = false;
        if (this.isSkill) {
            this.vHanhTrang.removeAllElements();
            MainMenu.cmdCongDiem.caption = T.congDiem;
            this.vHanhTrang.addElement(cmdCongDiem);
            this.vHanhTrang.addElement(cmdGangphim);
            this.SortCmdItem();
            return;
        }
        if (this.isCharWearing) {
            this.vHanhTrang.removeAllElements();
            MainMenu.cmdcong1.caption = String.valueOf(T.congDiem);
//            MainMenu.cmdcong5.caption = String.valueOf(T.congDiem) + " 5";
//            MainMenu.cmdcong10.caption = String.valueOf(T.congDiem) + " 10";
            if (Char.Diemtiemnang > 0) {
                this.vHanhTrang.addElement(cmdcong1);
            }
            if (Char.Diemtiemnang >= 5) {
                this.vHanhTrang.addElement(cmdcong5);
            }
            if (Char.Diemtiemnang >= 10) {
                this.vHanhTrang.addElement(cmdcong10);
            }
            this.SortCmdItem();
            return;
        }
        if (this.indexMainTab == 1) {
            this.vHanhTrang.removeAllElements();
            if (cmdban != null) {
                MainMenu.cmdban.caption = T.ban;
            }
            this.vHanhTrang.addElement(cmdban);
            this.vHanhTrang.addElement(cmdsudung);
            if (cmdvut != null) {
                MainMenu.cmdvut.caption = T.vut;
            }
            this.vHanhTrang.addElement(cmdvut);
            if (this.isgangphim) {
                this.vHanhTrang.addElement(cmdGangphim);
            } else {
                this.vHanhTrang.addElement(cmdbanNhieu);
            }
            this.SortCmdItem();
        }
    }

    public void updatePhiPhong() {
        if (GameCanvas.gameTick % 80 == 0 && this.laststar2 > 1) {
            this.runStart2 = true;
        }
        if (this.runStart2 && GameCanvas.gameTick % 2 == 0) {
            ++this.speedStart2;
            if (this.speedStart2 > this.numItemStart2 + 2) {
                this.speedStart2 = 0;
                this.runStart2 = false;
            }
        }
        if (GameCanvas.gameTick % 80 == 0 && this.laststar > 1) {
            this.runStart = true;
        }
        if (this.runStart && GameCanvas.gameTick % 2 == 0) {
            ++this.speedStart;
            if (this.speedStart > this.numItemStart + 2) {
                this.speedStart = 0;
                this.runStart = false;
            }
        }
        if (GameCanvas.isKeyPressed(12)) {
            GameCanvas.menu.startAt(this.vHanhTrang, 2);
        }
        if (this.Waitcreate) {
            this.beginChedo = true;
            --this.wait;
            if (this.wait < 0) {
                this.Waitcreate = false;
                this.wait = 25;
                this.beginChedo = false;
                if (typeresult == 0) {
                    effDapDo = new DataSkillEff(50, this.xcenter, this.ycenter);
                }
                if (typeresult == 236) {
                    effDapDo = new DataSkillEff(236, this.xcenter, this.ycenter);
                }
                this.listNum = null;
                this.listNum = new int[6];
                vecMaterial.removeAllElements();
                this.listItem = null;
                this.listItem = new Item[6];
            }
        }
        if (effDapDo != null) {
            effDapDo.update();
            if (MainMenu.effDapDo.wantDestroy) {
                effDapDo = null;
            }
        }
        if (GameCanvas.isTouch) {
            int i = 0;
            while (i < this.vHanhTrang.size()) {
                mCommand cmd = (mCommand) this.vHanhTrang.elementAt(i);
                if (cmd != null && this.getCmdPointerLast(cmd)) {
                    GameCanvas.isPointerJustRelease[0] = false;
                    cmd.performAction();
                    break;
                }
                ++i;
            }
        }
        if (!GameCanvas.isTouch) {
            Item sItem;
            int x0;
            if (GameCanvas.isKeyPressed(6)) {
                this.closeText();
                if (this.slDapdo1 < vecItemCreate.size() - 1) {
                    ++this.slDapdo1;
                    if (this.slDapdo1 > 1 && this.slDapdo1 % 5 == 0) {
                        this.scrDapdo.moveTo((this.slDapdo1 / 5 + 1) * this.sizeDapdo);
                    }
                }
                x0 = this.x + this.size / 2 + this.slDapdo1 % this.colum * this.size + 16;
                int y0 = 11 + this.y + this.slDapdo1 / this.colum * this.size + this.size * 3 / 2 + this.size - 1;
                sItem = (Item) vecItemCreate.elementAt(this.slDapdo1);
                if (sItem != null) {
                    this.showItemInventoryInfo(sItem, this.isSell, x0, y0, null);
                    this.setCmdCenter("updateDapDo 0");
                }
            }
            if (GameCanvas.isKeyPressed(4)) {
                this.closeText();
                if (this.slDapdo1 > 0) {
                    --this.slDapdo1;
                    x0 = this.x + this.size / 2 + this.slDapdo1 % this.colum * this.size + 16;
                    int y0 = 11 + this.y + this.slDapdo1 / this.colum * this.size + this.size * 3 / 2 + this.size - 1;
                    sItem = (Item) vecItemCreate.elementAt(this.slDapdo1);
                    if (sItem != null) {
                        this.showItemInventoryInfo(sItem, this.isSell, x0, y0, null);
                        this.setCmdCenter("updateDapDo 0");
                    }
                    if (this.slDapdo1 > 1 && this.slDapdo1 % 5 == 0) {
                        this.scrDapdo.moveTo(this.slDapdo1 / 5 * this.sizeDapdo);
                    }
                }
            }
        }
        ScrollResult r = this.scrDapdo.updateKey();
        this.scrDapdo.updatecm();
        this.f = (this.f + 1) % 3;
        int i = 0;
        while (i < this.vEffect.size()) {
            Effect e = (Effect) this.vEffect.elementAt(i);
            if (e != null) {
                e.update();
                if (e.wantDestroy) {
                    this.vEffect.removeElement(e);
                }
            }
            ++i;
        }
        if (GameCanvas.gameTick % 2 == 0 && this.beginChedo) {
            EffectEnd ef = new EffectEnd(0, this.xcenter, this.ycenter);
            this.vEffect.addElement(ef);
        }
        if (GameCanvas.isTouch && GameCanvas.isPointer(this.xWearing[0] - 20, this.yWearing[0] + 3, 142, 74, 0)
                && this.scrDapdo.selectedItem != -1 && this.slDapdo1 != this.scrDapdo.selectedItem) {
            this.slDapdo1 = this.scrDapdo.selectedItem;
            selected = -1;
            this.FocusPhiPhong = -1;
            this.setCmdCenter("updateDapDo 0");
        }
        i = 0;
        while (i < this.postItem_PhiPhong_X.length) {
            if (GameCanvas.isPointer(this.postItem_PhiPhong_X[i] + 2, this.postItem_PhiPhong_Y[i] + 2, 21, 21, 0)) {
                this.FocusPhiPhong = i;
                this.slDapdo1 = -1;
                int x0 = this.x + this.size / 2 + this.slDapdo1 % this.colum * this.size + 16;
                int y0 = 11 + this.y + this.slDapdo1 / this.colum * this.size + this.size * 3 / 2 + this.size - 1;
                Item sItem = (Item) vecMaterial.elementAt(this.FocusPhiPhong);
                this.closeText();
                if (sItem != null) {
                    this.showItemInventoryInfo(sItem, this.isSell, x0, y0, null);
                    this.vHanhTrang.removeAllElements();
                    this.vHanhTrang.addElement(cmdlayraPhiPhong);
                    this.SortCmdItem();
                }
            }
            ++i;
        }
        if (GameCanvas.isPointer(this.xcenter - 11, this.ycenter - 11, 21, 21, 0)) {
            int x0 = this.x + this.size / 2 + this.slDapdo1 % this.colum * this.size + 16;
            int y0 = 11 + this.y + this.slDapdo1 / this.colum * this.size + this.size * 3 / 2 + this.size - 1;
            this.closeText();
            if (this.itemPP != null) {
                this.isTouchCenter = true;
                this.showItemInventoryInfo(this.itemPP, this.isSell, x0, y0, null);
                this.vHanhTrang.removeAllElements();
                this.vHanhTrang.addElement(cmdlayraPhiPhong);
                this.SortCmdItem();
            }
        }
    }

    public void updateCheDo() {
        if (GameCanvas.gameTick % 80 == 0 && this.laststar > 1) {
            this.runStart = true;
        }
        if (this.runStart && GameCanvas.gameTick % 2 == 0) {
            ++this.speedStart;
            if (this.speedStart > this.numItemStart + 2) {
                this.speedStart = 0;
                this.runStart = false;
            }
        }
        if (GameCanvas.isKeyPressed(12)) {
            GameCanvas.menu.startAt(this.vHanhTrang, 2);
        }
        if (this.Waitcreate) {
            this.beginChedo = true;
            --this.wait;
            if (this.wait < 0) {
                this.Waitcreate = false;
                this.wait = 25;
                this.beginChedo = false;
                if (typeresult == 0) {
                    effDapDo = new DataSkillEff(50, this.xcenter, this.ycenter);
                }
                if (typeresult == 236) {
                    effDapDo = new DataSkillEff(50, this.xcenter, this.ycenter);
                }
                this.listNum = null;
                this.listNum = new int[6];
                vecMaterial.removeAllElements();
            }
        }
        if (effDapDo != null) {
            effDapDo.update();
            if (MainMenu.effDapDo.wantDestroy) {
                effDapDo = null;
            }
        }
        if (GameCanvas.isTouch) {
            int i = 0;
            while (i < this.vHanhTrang.size()) {
                mCommand cmd = (mCommand) this.vHanhTrang.elementAt(i);
                if (cmd != null && this.getCmdPointerLast(cmd)) {
                    GameCanvas.isPointerJustRelease[0] = false;
                    cmd.performAction();
                    break;
                }
                ++i;
            }
        }
        if (!GameCanvas.isTouch) {
            Item sItem;
            int x0;
            if (GameCanvas.isKeyPressed(6)) {
                this.closeText();
                if (this.slDapdo1 < vecItemCreate.size() - 1) {
                    ++this.slDapdo1;
                    if (this.slDapdo1 > 1 && this.slDapdo1 % 5 == 0) {
                        this.scrDapdo.moveTo((this.slDapdo1 / 5 + 1) * this.sizeDapdo);
                    }
                }
                x0 = this.x + this.size / 2 + this.slDapdo1 % this.colum * this.size + 16;
                int y0 = 11 + this.y + this.slDapdo1 / this.colum * this.size + this.size * 3 / 2 + this.size - 1;
                sItem = (Item) vecItemCreate.elementAt(this.slDapdo1);
                if (sItem != null) {
                    this.showItemInventoryInfo(sItem, this.isSell, x0, y0, null);
                    this.setCmdCenter("updateDapDo 0");
                }
            }
            if (GameCanvas.isKeyPressed(4)) {
                this.closeText();
                if (this.slDapdo1 > 0) {
                    --this.slDapdo1;
                    x0 = this.x + this.size / 2 + this.slDapdo1 % this.colum * this.size + 16;
                    int y0 = 11 + this.y + this.slDapdo1 / this.colum * this.size + this.size * 3 / 2 + this.size - 1;
                    sItem = (Item) vecItemCreate.elementAt(this.slDapdo1);
                    if (sItem != null) {
                        this.showItemInventoryInfo(sItem, this.isSell, x0, y0, null);
                        this.setCmdCenter("updateDapDo 0");
                    }
                    if (this.slDapdo1 > 1 && this.slDapdo1 % 5 == 0) {
                        this.scrDapdo.moveTo(this.slDapdo1 / 5 * this.sizeDapdo);
                    }
                }
            }
        }
        ScrollResult r = this.scrDapdo.updateKey();
        this.scrDapdo.updatecm();
        this.f = (this.f + 1) % 3;
        int i = 0;
        while (i < this.vEffect.size()) {
            Effect e = (Effect) this.vEffect.elementAt(i);
            if (e != null) {
                e.update();
                if (e.wantDestroy) {
                    this.vEffect.removeElement(e);
                }
            }
            ++i;
        }
        if (GameCanvas.gameTick % 2 == 0 && this.beginChedo) {
            EffectEnd ef = new EffectEnd(0, this.xcenter, this.ycenter);
            this.vEffect.addElement(ef);
        }
        if (GameCanvas.isTouch && GameCanvas.isPointer(this.xWearing[0] - 20, this.yWearing[0] + 3, 142, 74, 0)
                && this.scrDapdo.selectedItem != -1 && this.slDapdo1 != this.scrDapdo.selectedItem) {
            this.slDapdo1 = this.scrDapdo.selectedItem;
            selected = -1;
            this.setCmdCenter("updateDapDo 0");
        }
    }

    public void updateChuyenHoa() {
        if (GameCanvas.gameTick % 80 == 0 && this.laststar > 1) {
            this.runStart = true;
        }
        if (this.runStart && GameCanvas.gameTick % 2 == 0) {
            ++this.speedStart;
            if (this.speedStart > this.numItemStart + 2) {
                this.speedStart = 0;
                this.runStart = false;
            }
        }
        if (GameCanvas.isKeyPressed(12)) {
            GameCanvas.menu.startAt(this.vHanhTrang, 2);
        }
        if (this.Waitcreate) {
            this.beginChedo = true;
            --this.wait;
            if (this.wait < 0) {
                this.Waitcreate = false;
                this.wait = 25;
                this.beginChedo = false;
                effDapDo = new DataSkillEff(50, this.xcenter, this.ycenter - 30);
                itemChuyenHoa1 = null;
                itemChuyenHoa2 = null;
                this.listNum = null;
                this.listNum = new int[6];
                vecMaterial.removeAllElements();
            }
        }
        if (effDapDo != null) {
            effDapDo.update();
            if (MainMenu.effDapDo.wantDestroy) {
                effDapDo = null;
            }
        }
        if (GameCanvas.isTouch) {
            int i = 0;
            while (i < this.vHanhTrang.size()) {
                mCommand cmd = (mCommand) this.vHanhTrang.elementAt(i);
                if (cmd != null && this.getCmdPointerLast(cmd)) {
                    GameCanvas.isPointerJustRelease[0] = false;
                    cmd.performAction();
                    break;
                }
                ++i;
            }
        }
        if (!GameCanvas.isTouch) {
            Item sItem;
            int x0;
            if (GameCanvas.isKeyPressed(6)) {
                this.closeText();
                if (this.slDapdo1 < vecItemCreate.size() - 1) {
                    ++this.slDapdo1;
                    if (this.slDapdo1 > 1 && this.slDapdo1 % 5 == 0) {
                        this.scrDapdo.moveTo((this.slDapdo1 / 5 + 1) * this.sizeDapdo);
                    }
                }
                x0 = this.x + this.size / 2 + this.slDapdo1 % this.colum * this.size + 16;
                int y0 = 11 + this.y + this.slDapdo1 / this.colum * this.size + this.size * 3 / 2 + this.size - 1;
                sItem = (Item) vecItemCreate.elementAt(this.slDapdo1);
                if (sItem != null) {
                    this.showItemInventoryInfo(sItem, this.isSell, x0, y0, null);
                    this.setCmdCenter("updateDapDo 0");
                }
            }
            if (GameCanvas.isKeyPressed(4)) {
                this.closeText();
                if (this.slDapdo1 > 0) {
                    --this.slDapdo1;
                    x0 = this.x + this.size / 2 + this.slDapdo1 % this.colum * this.size + 16;
                    int y0 = 11 + this.y + this.slDapdo1 / this.colum * this.size + this.size * 3 / 2 + this.size - 1;
                    sItem = (Item) vecItemCreate.elementAt(this.slDapdo1);
                    if (sItem != null) {
                        this.showItemInventoryInfo(sItem, this.isSell, x0, y0, null);
                        this.setCmdCenter("updateDapDo 0");
                    }
                    if (this.slDapdo1 > 1 && this.slDapdo1 % 5 == 0) {
                        this.scrDapdo.moveTo(this.slDapdo1 / 5 * this.sizeDapdo);
                    }
                }
            }
        }
        ScrollResult r = this.scrDapdo.updateKey();
        this.scrDapdo.updatecm();
        this.f = (this.f + 1) % 3;
        int i = 0;
        while (i < this.vEffect.size()) {
            Effect e = (Effect) this.vEffect.elementAt(i);
            if (e != null) {
                e.update();
                if (e.wantDestroy) {
                    this.vEffect.removeElement(e);
                }
            }
            ++i;
        }
        if (GameCanvas.gameTick % 2 == 0 && this.beginChedo) {
            EffectEnd ef = new EffectEnd(0, this.xcenter, this.ycenter - 25 + 10);
            this.vEffect.addElement(ef);
        }
        if (GameCanvas.isTouch && GameCanvas.isPointer(this.xWearing[0] - 20, this.yWearing[0] + 3, 142, 74, 0)
                && this.scrDapdo.selectedItem != -1 && this.slDapdo1 != this.scrDapdo.selectedItem) {
            this.tickChuyenhoa = -1;
            this.slDapdo1 = this.scrDapdo.selectedItem;
            selected = -1;
            this.setCmdCenter("updateDapDo 0");
        }
        if (GameCanvas.isPointer(this.xcenter - 11, this.ycenter - 11 - 30 + 5, 21, 21, 0)) {
            this.slDapdo1 = -1;
            if (itemChuyenHoa1 != null) {
                this.tickChuyenhoa = 0;
                int x0 = this.x + this.size / 2 + selected % this.colum * this.size + 16;
                int y0 = 11 + this.y + selected / this.colum * this.size + this.size * 3 / 2 + this.size - 1;
                this.setShowText(itemChuyenHoa1.getInfoItemShow(null, true), x0, y0, null, true, false);
            }
        }
        if (GameCanvas.isPointer(this.xcenter - 42 - 11 + 10, this.ycenter - 11 + 30, 21, 21, 0)) {
            this.slDapdo1 = -1;
            if (itemChuyenHoa1 != null) {
                this.tickChuyenhoa = 1;
                int x0 = this.x + this.size / 2 + selected % this.colum * this.size + 16;
                int y0 = 11 + this.y + selected / this.colum * this.size + this.size * 3 / 2 + this.size - 1;
                this.setShowText(itemChuyenHoa1.getInfoItemShow(null, true), x0, y0, null, true, false);
            }
        }
        if (GameCanvas.isPointer(this.xcenter + 42 - 11 - 10, this.ycenter - 11 + 30, 21, 21, 0)) {
            this.slDapdo1 = -1;
            if (itemChuyenHoa2 != null) {
                this.tickChuyenhoa = 2;
                int x0 = this.x + this.size / 2 + selected % this.colum * this.size + 16;
                int y0 = 11 + this.y + selected / this.colum * this.size + this.size * 3 / 2 + this.size - 1;
                this.setShowText(itemChuyenHoa2.getInfoItemShow(null, true), x0, y0, null, true, false);
            }
        }
    }

    public void updatepetItem() {
        this.indexPet = 1;
        this.scrDapdo.moveTo(0);
        this.setPosWearing(false);
        isChangeSubTab = false;
        this.vHanhTrang.removeAllElements();
        this.isHanhTrang = false;
        this.closeText();
        this.cmdSelectItem.setXY(-100, -100);
        this.isSetXYCmdSelect = false;
        this.indexMainTab = 12;
        this.vHanhTrang.removeAllElements();
        mSound.playSound(26, mSound.volumeSound);
        selected = -1;
        this.indexSubTab = 0;
        this.isCharWearing = false;
        this.isShowInFoChar = false;
        isFocusDetailMenu = false;
        this.isAnimal = false;
        this.isQuest = false;
        this.isFeatures = false;
        this.isHanhTrang = false;
        this.isSkill = false;
        this.isShowFill = false;
        this.isSkillClan = false;
        this.isQuestClan = false;
        if (this.isMiniShop) {
            this.isHanhTrang = true;
        }
        this.reSetAllCmr();
        this.setPosScroll(this.indexMainTab, -1);
        this.setSelectTab("updatepetItem");
        this.setCmdCenter("updatepetItem 0");
        this.setTile();
        if (this.indexMainTab == 3) {
            this.isSkill = true;
        }
        if (this.indexMainTab == 4) {
            // Initialize Mount tab state
            this.FocusPet = -1;
            selected = -1;
            this.isFocusCharWearing = false;
            isFocusDetailMenu = false;
            this.vHanhTrang.removeAllElements();
            this.setPosWearing(false);
            this.scrDapdo.moveTo(0);
            GameService.gI().doOpenUIMount();
        }
        this.doChangeInfo(true);
        GameCanvas.isPointerJustRelease[0] = false;
        GameCanvas.isPointerClick[0] = false;
        this.FocusPet = -1;
        selected = -1;
        this.isFocusPetWearing = true;
        if (GameScr.mainChar.equip != null && GameScr.mainChar.equip[14] != null) {
            int x00 = this.x + this.size / 2 + selected % this.colum * this.size + 16;
            int y0 = 11 + this.y + selected / this.colum * this.size + this.size * 3 / 2 + this.size - 1;
            this.showItemInventoryInfo(GameScr.mainChar.equip[14], this.isSell, x00, y0, null);
        }
        this.currentTileMainTab = "Thú nuôi";
    }

    public void updateMainTab() {
        if (mSystem.isIos && mGraphics.zoomLevel < 4 || mSystem.isj2me) {
            int index;
            ScrollResult rs = this.scrMainmenu.updateKey();
            this.scrMainmenu.updatecm();
            if (GameCanvas.isPointer(this.x, this.y + 45, 30, this.h - 47, 0) && this.scrMainmenu.selectedItem != -1
                    && this.indexMainTab != 9 && this.indexMainTab != 10 && this.indexMainTab != 8
                    && this.indexMainTab != 11 && (index = this.scrMainmenu.selectedItem) < this.numtab) {
                this.indexPet = 0;
                if (this.maptopaintMenuIcon[index] != -1) {
                    isChangeSubTab = false;
                    this.vHanhTrang.removeAllElements();
                    this.isHanhTrang = false;
                    this.closeText();
                    this.cmdSelectItem.setXY(-100, -100);
                    this.isSetXYCmdSelect = false;
                    if (index != this.indexMainTab) {
                        mSound.playSound(26, mSound.volumeSound);
                    }
                    if (index != 5) {
                        this.indexMainTab = index;
                    }
                    if (this.indexMainTab > mainTab.length - 1) {
                        this.indexMainTab = 0;
                        if (this.isMiniShop) {
                            this.indexSubTab = this.indexTabMiniShop;
                        }
                    }
                    selected = -1;
                    this.indexSubTab = 0;
                    this.isCharWearing = false;
                    this.isShowInFoChar = false;
                    isFocusDetailMenu = false;
                    this.isAnimal = false;
                    this.isQuest = false;
                    this.isFeatures = false;
                    this.isHanhTrang = false;
                    this.isSkill = false;
                    this.isShowFill = false;
                    this.isSkillClan = false;
                    this.isQuestClan = false;
                    if (this.isMiniShop) {
                        this.isHanhTrang = true;
                    }
                    this.reSetAllCmr();
                    this.setPosScroll(this.indexMainTab, -1);
                    this.setSelectTab("updateMainTab 1");
                    this.setCmdCenter("updatepetItem 0");
                    this.setTile();
                    if (this.indexMainTab == 3) {
                        this.isSkill = true;
                    }
                    if (this.indexMainTab == 4) {
                        // Initialize Mount tab state
                        this.FocusPet = -1;
                        selected = -1;
                        this.isFocusCharWearing = false;
                        isFocusDetailMenu = false;
                        this.vHanhTrang.removeAllElements();
                        this.setPosWearing(false);
                        this.scrDapdo.moveTo(0);
                        GameService.gI().doOpenUIMount();
                    }
                    this.doChangeInfo(true);
                    GameCanvas.isPointerJustRelease[0] = false;
                    GameCanvas.isPointerClick[0] = false;
                } else if (this.maptopaintMenuIcon[index] == -1 && isPet) {
                    this.indexPet = 1;
                    this.setPosWearing(false);
                    isChangeSubTab = false;
                    this.vHanhTrang.removeAllElements();
                    this.isHanhTrang = false;
                    this.closeText();
                    this.cmdSelectItem.setXY(-100, -100);
                    this.isSetXYCmdSelect = false;
                    this.indexMainTab = 12;
                    this.vHanhTrang.removeAllElements();
                    mSound.playSound(26, mSound.volumeSound);
                    selected = -1;
                    this.indexSubTab = 0;
                    this.isCharWearing = false;
                    this.isShowInFoChar = false;
                    isFocusDetailMenu = false;
                    this.isAnimal = false;
                    this.isQuest = false;
                    this.isFeatures = false;
                    this.isHanhTrang = false;
                    this.isSkill = false;
                    this.isShowFill = false;
                    this.isSkillClan = false;
                    this.isQuestClan = false;
                    if (this.isMiniShop) {
                        this.isHanhTrang = true;
                    }
                    this.reSetAllCmr();
                    this.setPosScroll(this.indexMainTab, -1);
                    this.setSelectTab("updateMainTab 2");
                    this.setCmdCenter("updatepetItem 0");
                    this.setTile();
                    if (this.indexMainTab == 3) {
                        this.isSkill = true;
                    }
                    if (this.indexMainTab == 4) {
                        // Initialize Mount tab state
                        this.FocusPet = -1;
                        selected = -1;
                        this.isFocusCharWearing = false;
                        isFocusDetailMenu = false;
                        this.vHanhTrang.removeAllElements();
                        this.setPosWearing(false);
                        this.scrDapdo.moveTo(0);
                        GameService.gI().doOpenUIMount();
                    }
                    this.doChangeInfo(true);
                    GameCanvas.isPointerJustRelease[0] = false;
                    GameCanvas.isPointerClick[0] = false;
                    this.FocusPet = -1;
                    selected = -1;
                    this.isFocusPetWearing = true;
                    if (GameScr.mainChar.equip != null && GameScr.mainChar.equip[14] != null) {
                        int x00 = this.x + this.size / 2 + selected % this.colum * this.size + 16;
                        int y0 = 11 + this.y + selected / this.colum * this.size + this.size * 3 / 2 + this.size - 1;
                        this.showItemInventoryInfo(GameScr.mainChar.equip[14], this.isSell, x00, y0, null);
                    }
                }
            }
        } else {
            int h0;
            int x0;
            int w0;
            int xTouch = this.x;
            int yTouch = this.y + this.sizeH + (GameCanvas.w > 200 ? 10 : 8);
            if (GameCanvas.isPointer(xTouch, yTouch,
                    (w0 = (x0 = this.x + this.w - 7 - this.colum * this.size) - this.x - 5) + 12 - this.size,
                    h0 = mainTab.length * this.size + 1, 0) && GameCanvas.canTouch()
                    && GameCanvas.isPointerJustRelease[0] && GameCanvas.isPointerClick[0] && this.indexMainTab != 9
                    && this.indexMainTab != 10 && this.indexMainTab != 8 && this.indexMainTab != 11) {
                int index = (GameCanvas.py[0] - yTouch) / this.size;
                this.indexPet = 0;
                if (this.maptopaintMenuIcon[index] != -1) {
                    isChangeSubTab = false;
                    this.vHanhTrang.removeAllElements();
                    this.isHanhTrang = false;
                    this.closeText();
                    this.cmdSelectItem.setXY(-100, -100);
                    this.isSetXYCmdSelect = false;
                    if (index != this.indexMainTab) {
                        mSound.playSound(26, mSound.volumeSound);
                    }
                    if (index != 5) {
                        this.indexMainTab = index;
                    }
                    if (this.indexMainTab > mainTab.length - 1) {
                        this.indexMainTab = 0;
                        if (this.isMiniShop) {
                            this.indexSubTab = this.indexTabMiniShop;
                        }
                    }
                    selected = -1;
                    this.indexSubTab = 0;
                    this.isCharWearing = false;
                    this.isShowInFoChar = false;
                    isFocusDetailMenu = false;
                    this.isAnimal = false;
                    this.isQuest = false;
                    this.isFeatures = false;
                    this.isHanhTrang = false;
                    this.isSkill = false;
                    this.isShowFill = false;
                    this.isSkillClan = false;
                    this.isQuestClan = false;
                    if (this.isMiniShop) {
                        this.isHanhTrang = true;
                    }
                    this.reSetAllCmr();
                    this.setPosScroll(this.indexMainTab, -1);
                    this.setSelectTab("updateMainTab 3");
                    this.setCmdCenter(" 0");
                    this.setTile();
                    if (this.indexMainTab == 3) {
                        this.isSkill = true;
                    }
                    if (this.indexMainTab == 4) {
                        // Initialize Mount tab state
                        this.FocusPet = -1;
                        selected = -1;
                        this.isFocusCharWearing = false;
                        isFocusDetailMenu = false;
                        this.vHanhTrang.removeAllElements();
                        this.setPosWearing(false);
                        this.scrDapdo.moveTo(0);
                        GameService.gI().doOpenUIMount();
                    }
                    this.doChangeInfo(true);
                    GameCanvas.isPointerJustRelease[0] = false;
                    GameCanvas.isPointerClick[0] = false;
                }
                if (this.maptopaintMenuIcon[index] == -1 && isPet) {
                    this.indexPet = 1;
                    this.setPosWearing(false);
                    isChangeSubTab = false;
                    this.vHanhTrang.removeAllElements();
                    this.isHanhTrang = false;
                    this.closeText();
                    this.cmdSelectItem.setXY(-100, -100);
                    this.isSetXYCmdSelect = false;
                    this.indexMainTab = 12;
                    this.vHanhTrang.removeAllElements();
                    mSound.playSound(26, mSound.volumeSound);
                    selected = -1;
                    this.indexSubTab = 0;
                    this.isCharWearing = false;
                    this.isShowInFoChar = false;
                    isFocusDetailMenu = false;
                    this.isAnimal = false;
                    this.isQuest = false;
                    this.isFeatures = false;
                    this.isHanhTrang = false;
                    this.isSkill = false;
                    this.isShowFill = false;
                    this.isSkillClan = false;
                    this.isQuestClan = false;
                    if (this.isMiniShop) {
                        this.isHanhTrang = true;
                    }
                    this.reSetAllCmr();
                    this.setPosScroll(this.indexMainTab, -1);
                    this.setSelectTab("updateMainTab 4");
                    this.setCmdCenter("updatemaintab 0");
                    this.setTile();
                    if (this.indexMainTab == 3) {
                        this.isSkill = true;
                    }
                    if (this.indexMainTab == 4) {
                        // Initialize Mount tab state
                        this.FocusPet = -1;
                        selected = -1;
                        this.isFocusCharWearing = false;
                        isFocusDetailMenu = false;
                        this.vHanhTrang.removeAllElements();
                        this.setPosWearing(false);
                        this.scrDapdo.moveTo(0);
                        GameService.gI().doOpenUIMount();
                    }
                    this.doChangeInfo(true);
                    GameCanvas.isPointerJustRelease[0] = false;
                    GameCanvas.isPointerClick[0] = false;
                    this.FocusPet = -1;
                    selected = -1;
                    this.isFocusPetWearing = true;
                    if (GameScr.mainChar.equip != null && GameScr.mainChar.equip[14] != null) {
                        int x00 = this.x + this.size / 2 + selected % this.colum * this.size + 16;
                        int y0 = 11 + this.y + selected / this.colum * this.size + this.size * 3 / 2 + this.size - 1;
                        this.showItemInventoryInfo(GameScr.mainChar.equip[14], this.isSell, x00, y0, null);
                    }
                }
            }
        }
    }

    @Override
    public void update() {
        if (this.isSkill) {
            ScrollResult rs = this.scrSkill.updateKey();
            this.scrSkill.updatecm();
        }
        if (this.indexPet == 1) {
            this.updatePetWearing();
        }
        if (this.indexMainTab == 4) {
            this.updateMountWearing();
        }
        if (GameCanvas.isKeyPressed(13)) {
            if (this.isShowText) {
                if (this.isSkill && selected > 0) {
                    isFocusDetailMenu = true;
                }
                this.closeText();
            } else {
                this.doClose();
            }
        }
        if (this.indexMainTab == 2 && selected == 14 && GameCanvas.isKeyPressed(5)) {
            GameService.gI().dochangeCharWearing();
        }
        if (GameCanvas.isPointerClick[0] && !GameCanvas.isPointer(this.xShowText + this.wShowText - 81 + 2,
                this.y + this.hShowText + 36, mCommand.wButtonCmd, mCommand.hButtonCmd, 0) && this.isTextmua) {
            this.resetPopup();
            return;
        }
        if (this.isTextmua) {
            ++this.countTextmua;
            if (this.countTextmua > 150) {
                this.countTextmua = 0;
                this.resetPopup();
            }
        }
        if (this.countL > 0) {
            --this.countL;
        }
        if (this.countR > 0) {
            --this.countR;
        }
        if (this.indexMainTab == 8) {
            this.isFocusClose = false;
            int wc = mGraphics.getImageWidth(GameScr.imgButton[4]);
            if (GameCanvas.isPointer(this.xShowText + this.wShowText - 21,
                    (this.y + this.sizeH - 14) / 2 + (this.y + this.sizeH - 14) / 2 - 6, wc, wc, 0)
                    && GameCanvas.canTouch()) {
                this.isFocusClose = true;
                if (GameCanvas.isPointerJustRelease[0] && GameCanvas.isPointerClick[0]) {
                    this.doClose();
                    GameCanvas.isPointerClick[0] = false;
                }
            }
            this.updateDapDo();
            if (this.lastSCR != null) {
                this.lastSCR.update();
            }
            return;
        }
        if (this.indexMainTab == 11) {
            this.isFocusClose = false;
            int wc = mGraphics.getImageWidth(GameScr.imgButton[4]);
            if (GameCanvas.isPointer(this.xShowText + this.wShowText - 21,
                    (this.y + this.sizeH - 14) / 2 + (this.y + this.sizeH - 14) / 2 - 6, wc, wc, 0)
                    && GameCanvas.canTouch()) {
                this.isFocusClose = true;
                if (GameCanvas.isPointerJustRelease[0] && GameCanvas.isPointerClick[0]) {
                    this.doClose();
                    GameCanvas.isPointerClick[0] = false;
                }
            }
            this.updatePhiPhong();
            if (this.lastSCR != null) {
                this.lastSCR.update();
            }
            return;
        }
        if (this.indexMainTab == 9) {
            this.isFocusClose = false;
            int wc = mGraphics.getImageWidth(GameScr.imgButton[4]);
            if (GameCanvas.isPointer(this.xShowText + this.wShowText - 21,
                    (this.y + this.sizeH - 14) / 2 + (this.y + this.sizeH - 14) / 2 - 6, wc, wc, 0)
                    && GameCanvas.canTouch()) {
                this.isFocusClose = true;
                if (GameCanvas.isPointerJustRelease[0] && GameCanvas.isPointerClick[0]) {
                    this.doClose();
                    GameCanvas.isPointerClick[0] = false;
                }
            }
            this.updateCheDo();
            if (this.lastSCR != null) {
                this.lastSCR.update();
            }
            return;
        }
        if (this.indexMainTab == 10) {
            this.isFocusClose = false;
            int wc = mGraphics.getImageWidth(GameScr.imgButton[4]);
            if (GameCanvas.isPointer(this.xShowText + this.wShowText - 21,
                    (this.y + this.sizeH - 14) / 2 + (this.y + this.sizeH - 14) / 2 - 6, wc, wc, 0)
                    && GameCanvas.canTouch()) {
                this.isFocusClose = true;
                if (GameCanvas.isPointerJustRelease[0] && GameCanvas.isPointerClick[0]) {
                    this.doClose();
                    GameCanvas.isPointerClick[0] = false;
                }
            }
            this.updateChuyenHoa();
            if (this.lastSCR != null) {
                this.lastSCR.update();
            }
            return;
        }
        if (this.isPaintSub() && this.isFeatures) {
            this.tfAgility.update();
            this.tfSpirit.update();
            this.tfStrength.update();
            this.tfHealth.update();
            this.tfLucky.update();
        }
        if (this.isAnimal && charWearing != null && MainMenu.charWearing.imgAnimal != null) {
            if (GameCanvas.gameTick % MainMenu.charWearing.timeChangeFrame == 0) {
                ++this.countFrame;
                if (this.countFrame > this.arrFrame[MainMenu.charWearing.numFrame == 3 ? 0 : 1].length - 1) {
                    this.countFrame = 0;
                }
            }
            this.idFrame = this.arrFrame[MainMenu.charWearing.numFrame == 3 ? 0 : 1][this.countFrame];
        }
        if (GameCanvas.gameTick % 3 == 0) {
            ++this.coutFc;
            if (this.coutFc >= 2) {
                this.coutFc = 0;
            }
        }
        if (GameCanvas.gameTick % 80 == 0 && this.laststar2 > 1) {
            this.runStart2 = true;
        }
        if (this.runStart2 && GameCanvas.gameTick % 2 == 0) {
            ++this.speedStart2;
            if (this.speedStart2 > this.numItemStart2 + 2) {
                this.speedStart2 = 0;
                this.runStart2 = false;
            }
        }
        if (GameCanvas.gameTick % 80 == 0 && this.laststar > 1) {
            this.runStart = true;
        }
        if (this.runStart && GameCanvas.gameTick % 2 == 0) {
            ++this.speedStart;
            if (this.speedStart > this.numItemStart + 2) {
                this.speedStart = 0;
                this.runStart = false;
            }
        }
        if (GameCanvas.isTouch) {
            int i = 0;
            while (i < this.vHanhTrang.size()) {
                mCommand cmd = (mCommand) this.vHanhTrang.elementAt(i);
                if (cmd != null && this.getCmdPointerLast(cmd)) {
                    GameCanvas.isPointerJustRelease[0] = false;
                    cmd.performAction();
                    break;
                }
                ++i;
            }
        }
        if (this.isQuest && this.isMoveQuest) {
            this.cmrItem.moveTo(indexQuest * this.sizeH);
        }
        if (GameCanvas.isPointer(0, 0, GameCanvas.w, GameCanvas.h, 0)) {
            this.isMoveQuest = false;
        }
        if (this.indexMainTab == 2) {
            charWearing.updateCharFrame();
        }
        if (this.isSkill) {
            if (GameCanvas.isKeyPressed(4)) {
                if (--selected < 0) {
                    selected = -1;
                    isFocusDetailMenu = false;
                } else {
                    this.setCmdCenter("update 1");
                    this.setCmdShowText();
                    if (selected > 1 && (selected + 1) % 3 == 0) {
                        this.scrSkill.moveTo((selected + 1) / 3 * 30);
                    }
                }
            } else if (GameCanvas.isKeyPressed(6)) {
                if (++selected > GameScr.vec_skill.size() - 1) {
                    selected = GameScr.vec_skill.size() - 1;
                }
                this.setCmdCenter("update 1");
                this.setCmdShowText();
                if (selected > 5 && (selected + 1) % 3 == 0) {
                    this.scrSkill.moveTo((selected + 1) / 3 * 30);
                }
            }
        }
        if (GameCanvas.isTouch) {
            super.update();
        }
        if (this.lastSCR != null) {
            this.lastSCR.update();
        }
    }

    public void updateDapDo() {
        int y0;
        if (GameCanvas.gameTick % 3 == 0) {
            ++this.coutFc;
            if (this.coutFc >= 2) {
                this.coutFc = 0;
            }
        }
        if (GameCanvas.gameTick % 80 == 0 && this.laststar > 1) {
            this.runStart = true;
        }
        if (this.runStart && GameCanvas.gameTick % 2 == 0) {
            ++this.speedStart;
            if (this.speedStart > this.numItemStart + 2) {
                this.speedStart = 0;
                this.runStart = false;
            }
        }
        if (effDapDo != null) {
            effDapDo.update();
            if (MainMenu.effDapDo.wantDestroy) {
                effDapDo = null;
            }
        }
        if (GameCanvas.isKeyPressed(12)) {
            GameCanvas.menu.startAt(this.vHanhTrang, 2);
        }
        if (GameCanvas.isTouch) {
            int i = 0;
            while (i < this.vHanhTrang.size()) {
                mCommand cmd = (mCommand) this.vHanhTrang.elementAt(i);
                if (cmd != null && this.getCmdPointerLast(cmd)) {
                    GameCanvas.isPointerJustRelease[0] = false;
                    cmd.performAction();
                    break;
                }
                ++i;
            }
        }
        ScrollResult r2 = this.cmrShowText.updateKey();
        this.cmrShowText.updatecm();
        if (this.numtab != 0) {
            this.numtab = 0;
        }
        if (!GameCanvas.isTouch) {
            Item sItem;
            if (GameCanvas.isKeyPressed(6)) {
                this.closeText();
                if (++selected >= 4 && tabDapdo == 0) {
                    tabDapdo = 1;
                    this.slDapdo1 = 0;
                    int x01 = this.x + this.size / 2 + this.slDapdo1 % this.colum * this.size + 16;
                    int y01 = 11 + this.y + this.slDapdo1 / this.colum * this.size + this.size * 3 / 2 + this.size - 1;
                    Item sItem1 = (Item) vecItemDapDo.elementAt(this.slDapdo1);
                    if (sItem1 != null) {
                        this.showItemInventoryInfo(sItem1, this.isSell, x01, y01, null);
                        this.setCmdCenter("updateDapDo 0");
                    }
                    return;
                }
                if (tabDapdo == 1 && this.slDapdo1 < vecItemDapDo.size() - 1) {
                    ++this.slDapdo1;
                    if (this.slDapdo1 > 1 && this.slDapdo1 % 5 == 0) {
                        this.scrDapdo.moveTo((this.slDapdo1 / 5 + 1) * this.sizeDapdo);
                    }
                }
                int x0 = this.x + this.size / 2 + this.slDapdo1 % this.colum * this.size + 16;
                y0 = 11 + this.y + this.slDapdo1 / this.colum * this.size + this.size * 3 / 2 + this.size - 1;
                sItem = (Item) vecItemDapDo.elementAt(this.slDapdo1);
                if (sItem != null) {
                    this.showItemInventoryInfo(sItem, this.isSell, x0, y0, null);
                    this.setCmdCenter("updateDapDo 0");
                }
            }
            if (GameCanvas.isKeyPressed(4)) {
                this.closeText();
                if (selected > 0) {
                    --selected;
                }
                if (selected < 4) {
                    tabDapdo = 0;
                }
                if (tabDapdo == 1 && this.slDapdo1 > 0) {
                    --this.slDapdo1;
                    int x0 = this.x + this.size / 2 + this.slDapdo1 % this.colum * this.size + 16;
                    y0 = 11 + this.y + this.slDapdo1 / this.colum * this.size + this.size * 3 / 2 + this.size - 1;
                    sItem = (Item) vecItemDapDo.elementAt(this.slDapdo1);
                    if (sItem != null) {
                        this.showItemInventoryInfo(sItem, this.isSell, x0, y0, null);
                        this.setCmdCenter("updateDapDo 0");
                    }
                    if (this.slDapdo1 > 1 && this.slDapdo1 % 5 == 0) {
                        this.scrDapdo.moveTo(this.slDapdo1 / 5 * this.sizeDapdo);
                    }
                }
            }
            if (GameCanvas.isKeyPressed(5)) {
                this.center.performAction();
            }
        }
        if (GameCanvas.isPointer(xposItem - this.size / 2, ypostItem - this.size / 2, this.size, this.size, 0)
                && GameCanvas.canTouch() && selected != 3) {
            this.closeText();
            selected = 3;
            int x0 = this.x + this.size / 2 + selected % this.colum * this.size + 16;
            y0 = 11 + this.y + selected / this.colum * this.size + this.size * 3 / 2 + this.size - 1;
            if (mItem != null) {
                this.showItemInventoryInfo(mItem, this.isSell, x0, y0, null);
                this.vHanhTrang.removeAllElements();
                if (this.numStone > 0 && itemStone != null) {
                    this.vHanhTrang.addElement(new mCommand(T.cuonghoa, this, 14));
                }
                this.SortCmdItem();
                this.setStart();
            }
        }
        ScrollResult r = this.scrDapdo.updateKey();
        this.scrDapdo.updatecm();
        if (GameCanvas.isTouch && GameCanvas.isPointer(this.xWearing[0] - 20, this.yWearing[0] + 3, 142, 74, 0)
                && this.scrDapdo.selectedItem != -1 && this.slDapdo1 != this.scrDapdo.selectedItem) {
            this.slDapdo1 = this.scrDapdo.selectedItem;
            selected = -1;
            this.setCmdCenter("updateDapDo 0");
        }
        if (this.lastSCR != null) {
            this.lastSCR.update();
        }
    }

    @Override
    public void switchToMe() {
        this.isMiniShop = false;
        super.switchToMe();
        this.init1();
        this.initName();
    }

    public void showListQuest() {
        this.indexMainTab = 4;
        this.showQuest();
        this.movecmrQuest1();
    }

    @Override
    public void switchToMe(boolean isReset) {
        super.switchToMe(isReset);
        this.initName();
    }

    @Override
    public void switchToMe(ScreenTeam lastSCR) {
        this.isMe = false;
        if (GameCanvas.currentScreen == this) {
            this.isMe = true;
            return;
        }
        this.isMiniShop = false;
        super.switchToMe(lastSCR);
        this.init1();
        this.vHanhTrang.removeAllElements();
        this.initName();
        if (isBeginQuest) {
            this.showListQuest();
        }
        if (isInven) {
            this.switchTabInven();
        }
        if (isCheDo && !GameCanvas.isTouch) {
            this.left.caption = T.tuychon;
        }
        if (isChuyenHoa && !GameCanvas.isTouch) {
            this.left.caption = T.tuychon;
        }
        if (isPhiPhong && !GameCanvas.isTouch) {
            this.left.caption = T.tuychon;
        }
    }

    public void switchTabInven() {
        this.indexMainTab = 1;
        selected = -1;
        this.indexSubTab = 0;
        this.isCharWearing = false;
        this.isShowInFoChar = false;
        isFocusDetailMenu = false;
        this.isAnimal = false;
        this.isQuest = false;
        this.isFeatures = false;
        this.isHanhTrang = false;
        this.isSkill = false;
        this.isShowFill = false;
        this.isSkillClan = false;
        this.isQuestClan = false;
        if (this.isMiniShop) {
            this.isHanhTrang = true;
        }
        this.reSetAllCmr();
        this.setPosScroll(this.indexMainTab, -1);
        this.setSelectTab("switchTabInven");
        this.setCmdCenter("switchTabInven");
        this.setTile();
        this.isFocusClose = false;
        if (GameCanvas.isTouch) {
            this.doChangeInfo(true);
        }
    }

    public void init1() {
        this.vEffect.removeAllElements();
        isShow = true;
        this.countTextmua = 0;
        this.indexPet = 0;
        int[] nArray = new int[6];
        nArray[1] = 1;
        nArray[2] = 2;
        nArray[3] = 3;
        nArray[4] = 7;
        nArray[5] = -1;
        this.maptopaintMenuIcon = nArray;
        mainTab = new String[] { "Cửa hàng", "Hành trang", "Trang bị", "Kỹ năng",
                "Thú cưỡi", "Th\u00fa nu\u00f4i" };
        myInfo = new String[] { " K\u1ef9 n\u0103ng", "Nhi\u1ec7m v\u1ee5" };
        bangHoi = new String[] { "Th\u00f4ng tin", "Tin nh\u1eafn", "K\u1ef9 n\u0103ng", " N.v\u1ee5", "Q.g\u00f3p",
                "T.vi\u00ean", "Top", "Chat", "R\u1eddi bang", "Gi\u1ea3i t\u00e1n", "\u0110\u0103ng k\u00fd" };
        hoat_dong = new String[] { "C\u00e2y th\u1ea7n", "C\u1ed5 v\u1eadt", "T.c th\u1ee7", "T.\u0111\u1ea1i gia",
                "T.server", "S\u1ef1 ki\u1ec7n", "S.th\u00fa", "C.th\u00e0nh", "Q.chi\u1ebfn" };
        settings = new String[] { "T.kenhTG", "Auto" };
        tabHanhTrang = new String[] { "T\u00fai 1", "T\u00fai 2", " T\u00fai 3" };
        feaTures = new String[] { "S\u1ee9c m\u1ea1nh ", "Kh\u00e9o l\u00e9o ", "Tinh th\u1ea7n ",
                "S\u1ee9c kh\u1ecfe ", "May m\u1eafn " };
        this.numtab = mainTab.length;
        if (GameCanvas.w <= 200) {
            this.maxNumW = 2;
        }
        this.cmrItem.clear();
        this.cmrSubTab.clear();
        this.cmrShowText.clear();
        this.cmrShowInfoMainChar.clear();
        this.size = 26;
        this.colum = 5;
        if (!GameCanvas.isTouch) {
            this.size = 26;
            if (GameCanvas.isScreenSize200) {
                this.size = 22;
            }
            this.w = this.size * (this.colum + 1) + 10;
            this.x = GameCanvas.w / 2 - this.w / 2;
            if (this.x < 2) {
                this.x = 2;
            }
        } else {
            this.size = 26;
            this.w = this.size * (this.colum + 2);
            this.x = GameCanvas.w / 2 - this.w;
        }
        this.w += 4;
        if (this.x < 2) {
            this.x = 2;
        }
        this.xbg = this.x + this.size + 8;
        if (GameCanvas.isTouch) {
            this.xShowText = this.x + this.w - 15;
            this.wShowText = this.w;
            if (this.xShowText + this.wShowText > GameCanvas.w - 2) {
                this.wShowText = GameCanvas.w - this.xShowText;
            }
            --this.wShowText;
        } else {
            this.wShowText = 4 * this.size;
        }
        this.h = this.size * (this.colum + 3);
        if (this.h > GameCanvas.h - hTab + 2) {
            this.h = GameCanvas.h - hTab + (GameCanvas.isTouch ? 8 : 0);
        }
        this.y = GameCanvas.h / 2 - this.h / 2;
        if (!GameCanvas.isTouch) {
            if (this.y + this.h > GameCanvas.h - hTab) {
                this.y -= hTab;
            }
            if (this.y < 2) {
                this.y = 2;
            }
        } else {
            if (this.y < 24) {
                this.y = 24;
            }
            if (this.y + this.h > GameCanvas.h) {
                this.h = GameCanvas.h - this.y;
            }
        }
        if (GameCanvas.isTouch) {
            this.y -= 30;
        }
        this.sizeH = this.size;
        if (GameCanvas.h > 200) {
            this.sizeH = 35;
        }
        if (GameCanvas.isTouch) {
            this.yShowText = this.y + this.sizeH;
            this.hShowText = this.h - this.sizeH;
        }
        this.isHanhTrang = false;
        isChangeSubTab = false;
        isFocusDetailMenu = false;
        this.isFeatures = false;
        selected = -1;
        this.indexMainTab = 0;
        currnentTabDetail = new String[] { "" };
        this.isSkillClan = false;
        this.isQuestClan = false;
        this.isQuest = false;
        this.isMoveQuest = false;
        this.setPosScroll(this.indexMainTab, -1);
        this.setTile();
        if (mSystem.isIP && mGraphics.zoomLevel == 3) {
            this.hIP = 15;
        }
        if (isDapdo) {
            this.switchTabDapDo(8);
        }
        if (isCheDo) {
            this.initCheDo();
        }
        if (isChuyenHoa) {
            this.initChuyenHoa();
        }
        if (isPhiPhong) {
            this.initPhiPhong();
        }
        int i = 0;
        while (i < GameCanvas.gameScr.actors.size()) {
            Actor ac = (Actor) GameCanvas.gameScr.actors.elementAt(i);
            if (ac != null && ac.catagory == 12 && ac.isMypet()) {
                this.mpet = new Pet(ac.getIDTem());
                break;
            }
            ++i;
        }
        cmdChoan = new mCommand(T.choan, this, 39);
        cmdChoan.setPos(-18, GameCanvas.h - 25);
    }

    public void initCheDo() {
        this.listNum = null;
        this.listNum = new int[6];
        vecMaterial.removeAllElements();
        this.setPosWearing(false);
        selected = -1;
        this.indexMainTab = 9;
        this.xcenter = this.x + this.wShowText / 2;
        this.ycenter = this.y + this.h / 2 + 15 + 2;
        int yShowText1 = this.yInfoWearing + 2;
        int xbg = this.x + this.size + 8;
        int hShowText1 = this.hInfoWearing - 4 + (this.isAnimal ? this.size : 0) - this.size + this.hIP / 2 + 20;
        int ws = 135;
        this.xcenter = xbg + ws / 2;
        this.ycenter = yShowText1 + hShowText1 / 2;
        this.r = 40;
        this.goc = 30;
        this.setCmdCenter("updateDapDo 0");
        cmdbovao = new mCommand(T.bovao, this, 29);
        cmdlayra = new mCommand(T.layra, this, 30);
        cmdtao = new mCommand(T.tao, this, 31);
        this.wait = 25;
        this.Waitcreate = false;
        idicon = -1;
        this.slDapdo1 = -1;
    }

    public void initPhiPhong() {
        this.listNum = null;
        this.itemPP = null;
        this.listNum = new int[6];
        vecMaterial.removeAllElements();
        this.setPosWearing(false);
        selected = -1;
        this.indexMainTab = 11;
        this.xcenter = this.x + this.wShowText / 2;
        this.ycenter = this.y + this.h / 2 + 15 + 2;
        int yShowText1 = this.yInfoWearing + 2;
        int xbg = this.x + this.size + 8;
        int hShowText1 = this.hInfoWearing - 4 + (this.isAnimal ? this.size : 0) - this.size + this.hIP / 2 + 20;
        int ws = 135;
        this.xcenter = xbg + ws / 2;
        this.ycenter = yShowText1 + hShowText1 / 2;
        this.r = 40;
        this.goc = 30;
        this.setCmdCenter("updateDapDo 0");
        cmdbovao = new mCommand(T.bovao, this, 35);
        cmdlayra = new mCommand(T.layra, this, 36);
        if (this.typePhiPhong == 0) {
            cmdtao = new mCommand(T.tao, this, 37);
        }
        if (this.typePhiPhong == 1) {
            cmdtao = new mCommand(T.nangcap, this, 37);
        }
        cmdlayraPhiPhong = new mCommand(T.layra, this, 38);
        this.wait = 25;
        this.Waitcreate = false;
        idicon = -1;
        this.slDapdo1 = -1;
        this.postItem_PhiPhong_X = new int[6];
        this.postItem_PhiPhong_Y = new int[6];
        int i = 0;
        while (i < this.postItem_PhiPhong_X.length) {
            this.postItem_PhiPhong_X[i] = Util.cos(i * 60 + 30) * this.r / 1024 + (xbg + ws / 2) - 12;
            this.postItem_PhiPhong_Y[i] = Util.sin(i * 60 + 30) * this.r / 1024 + (yShowText1 + hShowText1 / 2) - 13;
            ++i;
        }
        this.listItem = new Item[6];
        this.FocusPhiPhong = -1;
    }

    public void initChuyenHoa() {
        this.tickChuyenhoa = -1;
        itemChuyenHoa0 = null;
        itemChuyenHoa1 = null;
        itemChuyenHoa2 = null;
        this.listNum = null;
        this.listNum = new int[6];
        vecMaterial.removeAllElements();
        this.setPosWearing(false);
        selected = -1;
        this.indexMainTab = 10;
        this.xcenter = this.x + this.wShowText / 2;
        this.ycenter = this.y + this.h / 2 + 15 + 2;
        int yShowText1 = this.yInfoWearing + 2;
        int xbg = this.x + this.size + 8;
        int hShowText1 = this.hInfoWearing - 4 + (this.isAnimal ? this.size : 0) - this.size + this.hIP / 2 + 20;
        int ws = 135;
        this.xcenter = xbg + ws / 2;
        this.ycenter = yShowText1 + hShowText1 / 2;
        this.xSao = this.xcenter;
        this.ySao = this.ycenter + 3;
        this.ycenter -= 10;
        this.setCmdCenter("updateDapDo 0");
        cmdbovao = new mCommand(T.bovao, this, 32);
        cmdtao = new mCommand(T.chuyenhoa, this, 33);
        this.wait = 25;
        this.Waitcreate = false;
        idicon = -1;
        this.slDapdo1 = -1;
    }

    public void setCmdShowText() {
        switch (this.indexMainTab) {
            case 3: {
                if (!this.isSkill)
                    break;
                this.cmdShowText = new mCommand("", this, -1);
                this.timeAuToShowText = 15;
                this.beGinShowText = false;
                isFocusDetailMenu = true;
                break;
            }
            case 2:
            case 8:
            case 9:
            case 10:
            case 11: {
                this.cmdShowText = new mCommand("", this, -2);
                this.timeAuToShowText = 15;
                this.beGinShowText = false;
                break;
            }
        }
    }

    public void doCastBuffToActor(MainChar actor1, Actor actor2, int skillIdEff, boolean buff2Friend) {
        mVector menu = new mVector();
        if (buff2Friend) {
            String a = String.valueOf(actor1.ID) + ":" + skillIdEff;
            menu.addElement(new mCommand("Cho m\u00ecnh", (IActionListener) this, 43, (Object) a));
            String b = String.valueOf(actor2.ID) + ":" + skillIdEff;
            menu.addElement(new mCommand("Cho b\u1ea1n", (IActionListener) this, 44, (Object) b));
        } else {
            GameService.gI().useBuff(actor1.ID, (byte) 0, (byte) skillIdEff, (short) 0);
        }
        GameCanvas.menu.startAt(menu, 2);
    }

    public void doSelectItemCuongHoa() {
        if (this.slDapdo1 >= 0 && vecItemDapDo.size() > 0) {
            int x0 = this.x + this.size / 2 + this.slDapdo1 % this.colum * this.size + 16;
            int y0 = 11 + this.y + this.slDapdo1 / this.colum * this.size + this.size * 3 / 2 + this.size - 1;
            Item sItem = (Item) vecItemDapDo.elementAt(this.slDapdo1);
            if (sItem != null) {
                if (sItem.pos == -1) {
                    mItem = sItem;
                    if (itemStone != null && this.numStone > 0) {
                        this.doSendCuongHoa(0);
                    }
                } else if (sItem.pos == 0) {
                    if (itemStone != null && MainMenu.itemStone.ID != sItem.ID) {
                        this.numStone = 1;
                        itemStone = sItem;
                    }
                    if (itemStone == null) {
                        itemStone = sItem;
                        this.numStone = 1;
                    }
                } else if (sItem.pos == 1) {
                    if (itemBaohiem != null && MainMenu.itemBaohiem.ID != sItem.ID) {
                        itemBaohiem = sItem;
                    }
                    if (itemBaohiem == null) {
                        itemBaohiem = sItem;
                    }
                } else if (sItem.pos == 2) {
                    if (itemBua != null && MainMenu.itemBua.ID != sItem.ID) {
                        itemBua = sItem;
                    }
                    if (itemBua == null) {
                        itemBua = sItem;
                    }
                }
                this.showItemInventoryInfo(sItem, this.isSell, x0, y0, null);
                this.vHanhTrang.removeAllElements();
                if (!isHopda) {
                    if (itemStone != null && this.numStone > 0 && mItem != null) {
                        if (sItem.pos == 0 || sItem.pos == 2) {
                            this.doSendCuongHoa(0);
                        }
                        this.vHanhTrang.addElement(new mCommand(T.cuonghoa, this, 14));
                    }
                    if (sItem.pos == 0) {
                        this.vHanhTrang.addElement(new mCommand(T.bovao, this, 13));
                        if (this.numStone > 0) {
                            this.vHanhTrang.addElement(new mCommand(T.layra, this, 26));
                        }
                    }
                    if (sItem.pos == 1) {
                        this.vHanhTrang.addElement(new mCommand(T.layra, this, 27));
                    }
                    if (sItem.pos == 2) {
                        this.vHanhTrang.addElement(new mCommand(T.layra, this, 28));
                    }
                } else if (itemStone != null) {
                    this.doSendCuongHoa(0);
                    this.vHanhTrang.addElement(new mCommand(T.nangcap, this, 14));
                    this.vHanhTrang.addElement(new mCommand(T.bovao, this, 13));
                    if (this.numStone > 0) {
                        this.vHanhTrang.addElement(new mCommand(T.layra, this, 26));
                    }
                }
                this.SortCmdItem();
            }
        }
    }

    public void setCmdCenter(String pos) {
        this.cmdShowText = new mCommand("", GameCanvas.instance, -100);
        this.closeText();
        switch (this.indexMainTab) {
            case 5: {
                this.center = new mCommand("Chọn", (IActionListener) this, 0, xCCmd, yCCmd);
                if (GameCanvas.isTouch) {
                    this.center.resetCmd();
                }
                this.dosetIDCmdTouch("", 0);
                break;
            }
            case 3: {
                this.center = new mCommand(GameCanvas.isTouch ? "" : "Chọn", (IActionListener) this, 0, xCCmd,
                        yCCmd);
                if (this.isSkill) {
                    this.timeAuToShowText = 15;
                    this.beGinShowText = false;
                    if (GameCanvas.isTouch) {
                        this.cmdShowText = new mCommand("", this, -1);
                        this.center.resetCmd();
                        this.dosetIDCmdTouch("", 0);
                        break;
                    }
                    this.left = new mCommand("Xem", this, -101);
                    break;
                }
                if (this.isQuest) {
                    this.center = new mCommand("", this, 49);
                    break;
                }
                if (this.isAnimal) {
                    this.cmdShowText = new mCommand("", this, 53);
                    this.timeAuToShowText = 15;
                    this.beGinShowText = false;
                    this.center = new mCommand("Thay", (IActionListener) this, -5, xCCmd, yCCmd);
                    if (GameCanvas.isTouch) {
                        this.center.resetCmd();
                    }
                    this.dosetIDCmdTouch("", -5);
                    break;
                }
                if (this.isHanhTrang || !this.isFeatures || !GameCanvas.isTouch)
                    break;
                this.center.resetCmd();
                this.dosetIDCmdTouch("", 0);
                break;
            }
            case 0: {
                this.center = new mCommand(GameCanvas.isTouch ? "" : T.view, this, 2);
                if (this.mShop.size() <= 0 || !GameCanvas.isTouch)
                    break;
                int x0 = this.x + this.size / 2 + selected % this.colum * this.size + 16;
                int y0 = 11 + this.y + selected / this.colum * this.size + this.size * 3 / 2 + this.size - 1;
                Item sItem = (Item) this.mShop.elementAt(selected);
                if (sItem == null)
                    break;
                this.showItemInventoryInfo(sItem, this.isSell, x0, y0, null);
                this.left = cmdBuy;
                Cout.Debug("CMD BUY 111 ");
                break;
            }
            case 7: {
                this.center = new mCommand("Ch\u1ecdn", (IActionListener) this, 0, xCCmd, yCCmd);
                if (!GameCanvas.isTouch)
                    break;
                this.center.resetCmd();
                this.dosetIDCmdTouch("", 0);
                break;
            }
            case 4:{
                // Mount system
                this.timeAuToShowText = 10;
                this.beGinShowText = false;
                this.center = new mCommand(T.view, this, 2);

                if (selected >= 0) {
                    // Handle mount equipment slots (0-5)
                    if (selected < 6) {
                        if (GameScr.mainChar.mount != null && GameScr.mainChar.mount[selected] != null) {
                            int row = selected / 2;
                            int col = selected % 2;
                            int x0 = this.xbg + this.size - 10 + col * (this.size + 2);
                            int y0 = this.y + this.h - 5 * this.size - this.size + 5 + row * this.size;
                            this.showItemInventoryInfo(GameScr.mainChar.mount[selected], false, x0, y0, null);

                            // Add unequip command for equipped mount items
                            this.vHanhTrang.removeAllElements();
                            this.vHanhTrang.addElement(new mCommand("Tháo", this, 60));
                        }
                    }
                    // Handle mount food items (6+)
                    else if (selected >= 6 && selected < vecMountEat.size() + 6) {
                        int foodIndex = selected - 6;
                        int x0 = this.x + this.size / 2 + selected % this.colum * this.size + 16;
                        int y0 = 11 + this.y + selected / this.colum * this.size + this.size * 3 / 2 + this.size - 1;
                        Item sItem = (Item) vecMountEat.elementAt(foodIndex);
                        if (sItem != null) {
                            this.showItemInventoryInfo(sItem, false, x0, y0, null);

                            // Add use food command
                            this.vHanhTrang.removeAllElements();
                            this.vHanhTrang.addElement(new mCommand(T.choan, this, 61));
                        }
                    }
                }

                if (GameCanvas.isTouch) {
                    this.center.resetCmd();
                }

                this.SortCmdItem();
                break;
            }
            case 6: {
                this.center = new mCommand("Ch\u1ecdn", (IActionListener) this, 0, xCCmd, yCCmd);
                if (!GameCanvas.isTouch)
                    break;
                this.center.resetCmd();
                this.dosetIDCmdTouch("", 0);
                break;
            }
            case 12: {
                if (!isChangeSubTab && selected < this.list.size()) {
                    mCommand cmd = (mCommand) this.list.elementAt(selected);
                    if (cmd != null) {
                        this.cmdShowText = cmd;
                    }
                    this.dosetIDCmdTouch("", 2);
                }
                this.timeAuToShowText = 10;
                this.beGinShowText = false;
                this.center = new mCommand(T.view, this, 2);
                if (selected >= 0 && vecPetEat.size() > 0) {
                    int x0 = this.x + this.size / 2 + selected % this.colum * this.size + 16;
                    int y0 = 11 + this.y + selected / this.colum * this.size + this.size * 3 / 2 + this.size - 1;
                    Item sItem = (Item) vecPetEat.elementAt(selected);
                    if (sItem != null) {
                        this.showItemInventoryInfo(sItem, this.isSell, x0, y0, null);
                    }
                }
                if (GameCanvas.isTouch) {
                    this.center.resetCmd();
                }
                if (selected != -1) {
                    this.vHanhTrang.removeAllElements();
                    this.vHanhTrang.addElement(new mCommand(T.choan, this, 39));
                }
                this.SortCmdItem();
                break;
            }
            case 1: {
                if (!isChangeSubTab && selected < this.list.size()) {
                    mCommand cmd = (mCommand) this.list.elementAt(selected);
                    if (cmd != null) {
                        this.cmdShowText = cmd;
                    }
                    this.dosetIDCmdTouch("", 2);
                }
                this.timeAuToShowText = 10;
                this.beGinShowText = false;
                this.center = new mCommand(T.view, this, 2);
                if (!GameCanvas.isTouch)
                    break;
                if (selected >= 0 && Char.inventory.size() > 0) {
                    int x0 = this.x + this.size / 2 + selected % this.colum * this.size + 16;
                    int y0 = 11 + this.y + selected / this.colum * this.size + this.size * 3 / 2 + this.size - 1;
                    Item sItem = (Item) Char.inventory.elementAt(selected);
                    if (sItem != null) {
                        int type = sItem.getTypeItem();
                        Item itcp = null;
                        if (POS_ITEM_IN_EQUIP[type] > -1) {
                            itcp = GameScr.mainChar.equip[POS_ITEM_IN_EQUIP[type]];
                        }
                        this.showItemInventoryInfo(sItem, this.isSell, x0, y0, itcp);
                    }
                }
                this.center.resetCmd();
                break;
            }
            case 8: {
                if (!isChangeSubTab && this.slDapdo1 < this.list.size()) {
                    mCommand cmd = (mCommand) this.list.elementAt(this.slDapdo1);
                    if (cmd != null) {
                        this.cmdShowText = cmd;
                    }
                    this.dosetIDCmdTouch("", 2);
                }
                this.timeAuToShowText = 10;
                this.beGinShowText = false;
                this.center = new mCommand("", this, 2);
                textPercent = "";
                if (this.slDapdo1 >= 0 && vecItemDapDo.size() > 0) {
                    int x0 = this.x + this.size / 2 + this.slDapdo1 % this.colum * this.size + 16;
                    int y0 = 11 + this.y + this.slDapdo1 / this.colum * this.size + this.size * 3 / 2 + this.size - 1;
                    Item sItem = (Item) vecItemDapDo.elementAt(this.slDapdo1);
                    if (sItem != null && GameCanvas.isTouch) {
                        if (sItem.pos == -1) {
                            mItem = sItem;
                            if (itemStone != null && this.numStone > 0) {
                                this.doSendCuongHoa(0);
                            }
                        } else if (sItem.pos == 0) {
                            if (itemStone != null && MainMenu.itemStone.ID != sItem.ID) {
                                this.numStone = 1;
                                itemStone = sItem;
                            }
                            if (itemStone == null) {
                                itemStone = sItem;
                                this.numStone = 1;
                            }
                        } else if (sItem.pos == 1) {
                            if (itemBaohiem != null && MainMenu.itemBaohiem.ID != sItem.ID) {
                                itemBaohiem = sItem;
                            }
                            if (itemBaohiem == null) {
                                itemBaohiem = sItem;
                            }
                        } else if (sItem.pos == 2) {
                            if (itemBua != null && MainMenu.itemBua.ID != sItem.ID) {
                                itemBua = sItem;
                            }
                            if (itemBua == null) {
                                itemBua = sItem;
                            }
                        }
                        this.showItemInventoryInfo(sItem, this.isSell, x0, y0, null);
                        this.vHanhTrang.removeAllElements();
                        if (!isHopda) {
                            if (itemStone != null && this.numStone > 0 && mItem != null) {
                                if (sItem.pos == 0 || sItem.pos == 2) {
                                    this.doSendCuongHoa(0);
                                }
                                this.vHanhTrang.addElement(new mCommand(T.cuonghoa, this, 14));
                            }
                            if (sItem.pos == 0) {
                                this.vHanhTrang.addElement(new mCommand(T.bovao, this, 13));
                                if (this.numStone > 0) {
                                    this.vHanhTrang.addElement(new mCommand(T.layra, this, 26));
                                }
                            }
                            if (sItem.pos == 1) {
                                this.vHanhTrang.addElement(new mCommand(T.layra, this, 27));
                            }
                            if (sItem.pos == 2) {
                                this.vHanhTrang.addElement(new mCommand(T.layra, this, 28));
                            }
                        } else if (itemStone != null) {
                            this.doSendCuongHoa(0);
                            this.vHanhTrang.addElement(new mCommand(T.nangcap, this, 14));
                            this.vHanhTrang.addElement(new mCommand(T.bovao, this, 13));
                            if (this.numStone > 0) {
                                this.vHanhTrang.addElement(new mCommand(T.layra, this, 26));
                            }
                        }
                        this.SortCmdItem();
                    }
                }
                if (!GameCanvas.isTouch)
                    break;
                this.center.resetCmd();
                break;
            }
            case 10: {
                if (!isChangeSubTab && this.slDapdo1 < this.list.size()) {
                    mCommand cmd = (mCommand) this.list.elementAt(this.slDapdo1);
                    if (cmd != null) {
                        this.cmdShowText = cmd;
                    }
                    this.dosetIDCmdTouch("", 2);
                }
                this.timeAuToShowText = 10;
                this.beGinShowText = false;
                this.center = new mCommand("", this, 2);
                textPercent = "";
                if (this.slDapdo1 >= 0 && vecItemCreate.size() > 0) {
                    int x0 = this.x + this.size / 2 + this.slDapdo1 % this.colum * this.size + 16;
                    int y0 = 11 + this.y + this.slDapdo1 / this.colum * this.size + this.size * 3 / 2 + this.size - 1;
                    Item sItem = (Item) vecItemCreate.elementAt(this.slDapdo1);
                    if (sItem != null) {
                        this.vHanhTrang.removeAllElements();
                        if (sItem.pos != -1) {
                            this.vHanhTrang.addElement(cmdbovao);
                            this.vHanhTrang.addElement(cmdtao);
                        }
                        this.showItemInventoryInfo(sItem, this.isSell, x0, y0, null);
                        this.SortCmdItem();
                    }
                }
                if (!GameCanvas.isTouch)
                    break;
                this.center.resetCmd();
                break;
            }
            case 11: {
                if (!isChangeSubTab && this.slDapdo1 < this.list.size()) {
                    mCommand cmd = (mCommand) this.list.elementAt(this.slDapdo1);
                    if (cmd != null) {
                        this.cmdShowText = cmd;
                    }
                    this.dosetIDCmdTouch("", 2);
                }
                this.timeAuToShowText = 10;
                this.beGinShowText = false;
                this.center = new mCommand("", this, 2);
                textPercent = "";
                if (this.slDapdo1 >= 0 && vecItemCreate.size() > 0) {
                    int x0 = this.x + this.size / 2 + this.slDapdo1 % this.colum * this.size + 16;
                    int y0 = 11 + this.y + this.slDapdo1 / this.colum * this.size + this.size * 3 / 2 + this.size - 1;
                    Item sItem = (Item) vecItemCreate.elementAt(this.slDapdo1);
                    if (sItem != null) {
                        this.vHanhTrang.removeAllElements();
                        this.vHanhTrang.addElement(cmdbovao);
                        if (this.typePhiPhong == 1) {
                            this.vHanhTrang.addElement(cmdlayra);
                        }
                        this.vHanhTrang.addElement(cmdtao);
                        this.showItemInventoryInfo(sItem, this.isSell, x0, y0, null);
                        this.SortCmdItem();
                    }
                }
                if (!GameCanvas.isTouch)
                    break;
                this.center.resetCmd();
                break;
            }
            case 9: {
                if (!isChangeSubTab && this.slDapdo1 < this.list.size()) {
                    mCommand cmd = (mCommand) this.list.elementAt(this.slDapdo1);
                    if (cmd != null) {
                        this.cmdShowText = cmd;
                    }
                    this.dosetIDCmdTouch("", 2);
                }
                this.timeAuToShowText = 10;
                this.beGinShowText = false;
                this.center = new mCommand("", this, 2);
                textPercent = "";
                if (this.slDapdo1 >= 0 && vecItemCreate.size() > 0) {
                    int x0 = this.x + this.size / 2 + this.slDapdo1 % this.colum * this.size + 16;
                    int y0 = 11 + this.y + this.slDapdo1 / this.colum * this.size + this.size * 3 / 2 + this.size - 1;
                    Item sItem = (Item) vecItemCreate.elementAt(this.slDapdo1);
                    if (sItem != null) {
                        this.vHanhTrang.removeAllElements();
                        if (sItem.pos != -1) {
                            this.vHanhTrang.addElement(cmdbovao);
                            this.vHanhTrang.addElement(cmdlayra);
                            this.vHanhTrang.addElement(cmdtao);
                        }
                        this.showItemInventoryInfo(sItem, this.isSell, x0, y0, null);
                        this.SortCmdItem();
                    }
                }
                if (!GameCanvas.isTouch)
                    break;
                this.center.resetCmd();
                break;
            }
            case 2: {
                if (selected == -1) {
                    if (!isFocusCharWearing) {
                        int i = 0;
                        while (i < this.list.size()) {
                            mCommand cmd = (mCommand) this.list.elementAt(i);
                            if (cmd != null && cmd.p != null && ((SetInfoData) cmd.p).xx == selected) {
                                this.cmdShowText = cmd;
                            }
                            ++i;
                        }
                    }
                    this.timeAuToShowText = 15;
                    this.beGinShowText = false;
                    this.center = new mCommand("Thay", (IActionListener) this, -3, xCCmd, yCCmd);
                    if (GameCanvas.isTouch) {
                        this.center.resetCmd();
                    }
                    this.dosetIDCmdTouch("", -3);
                } else if (GameCanvas.isTouch) {
                    int x01 = this.x + this.size / 2 + selected % this.colum * this.size + 16;
                    int y01 = 11 + this.y + selected / this.colum * this.size + this.size * 3 / 2 + this.size - 1;
                    Item sItem1 = MainMenu.charWearing.equip[selected + this.indexWearing];
                    if (sItem1 != null) {
                        this.showItemInventoryInfo(sItem1, this.isSell, x01, y01, null);
                    }
                }
                this.vHanhTrang.removeAllElements();
                if (this.indexWearing <= 0) {
                    this.vHanhTrang.addElement(cmdTB2);
                } else if (this.indexWearing > 0) {
                    this.vHanhTrang.addElement(cmdTB1);
                }
                this.vHanhTrang.addElement(cmdThao);
                this.SortCmdItem();
            }
        }
    }

    public void doChangeWearing() {
        if (selected == -1) {
            if (!isFocusCharWearing) {
                int i = 0;
                while (i < this.list.size()) {
                    mCommand cmd = (mCommand) this.list.elementAt(i);
                    if (cmd != null && cmd.p != null && ((SetInfoData) cmd.p).xx == selected) {
                        this.cmdShowText = cmd;
                    }
                    ++i;
                }
            }
            this.timeAuToShowText = 15;
            this.beGinShowText = false;
            this.center = new mCommand("Thay", (IActionListener) this, -3, xCCmd, yCCmd);
            if (GameCanvas.isTouch) {
                this.center.resetCmd();
            }
            this.dosetIDCmdTouch("", -3);
        } else {
            int x01 = this.x + this.size / 2 + selected % this.colum * this.size + 16;
            int y01 = 11 + this.y + selected / this.colum * this.size + this.size * 3 / 2 + this.size - 1;
            Item sItem1 = MainMenu.charWearing.equip[selected + this.indexWearing];
            if (sItem1 != null) {
                this.showItemInventoryInfo(sItem1, this.isSell, x01, y01, null);
            }
        }
        this.vHanhTrang.removeAllElements();
        if (this.indexWearing <= 0) {
            this.vHanhTrang.addElement(cmdTB2);
        } else if (this.indexWearing > 0) {
            this.vHanhTrang.addElement(cmdTB1);
        }
        this.vHanhTrang.addElement(cmdThao);
        this.SortCmdItem();
    }

    private void setTextCharInfo() {
        this.infochar.removeAllElements();
        MainChar c = GameScr.mainChar;
        this.tile[0] = String.valueOf(c.level) + "+" + c.getPercent();
        this.infochar.addElement(new InfoTextShow(new String[] { c.name }, 0));
        this.infochar.addElement(new InfoTextShow(new String[] { "HP: " + c.hp + "/" + c.maxhp }, 0));
        this.infochar.addElement(new InfoTextShow(new String[] { "MP: " + c.mp + "/" + c.maxmp }, 0));
        boolean cout = false;
        int count2 = 0;
        int i = 0;
        while (i < GameScr.mainChar.options.size()) {
            ItemOption itop = (ItemOption) GameScr.mainChar.options.elementAt(i);
            String[] option = itop.getInfoShow(0);
            int j = 0;
            while (j < option.length) {
                this.infochar.addElement(new InfoTextShow(new String[] { option[j] }, itop.getColorPaint(false)));
                ++j;
            }
            count2 += option.length;
            ++i;
        }
        if (!GameCanvas.isTouch) {
            this.wShowText = 5 * this.size;
        }
    }

    public void doBuyShopSpecial() {
        this.left = new mCommand("Mua", this, 71);
        this.center = new mCommand("", this, 71);
        if (GameCanvas.isTouch) {
            this.left.caption = "";
            this.left.idAction = 1000;
            this.center.idAction = 1000;
        }
        this.dosetIDCmdTouch("", 71);
    }

    public void setPosScroll(int info_, int sl) {
        this.indexMainTab = info_;
        selected = sl;
        switch (this.indexMainTab) {
            case 0: {
                int num = 0;
                if (!this.isMiniShop) {
                    int i = 0;
                    while (i < Res.shopTemplate.size()) {
                        GemTemp shop = (GemTemp) Res.shopTemplate.elementAt(i);
                        if (shop.shopType + 1 > num) {
                            num = shop.shopType + 1;
                        }
                        ++i;
                    }
                    this.focusTab = 0;
                    this.setSelectTab("setPosScroll");
                    String[] tabShop = new String[5];
                    int i2 = 0;
                    while (i2 < tabShop.length) {
                        tabShop[i2] = "Qu\u1ea7y " + (i2 + 1);
                        ++i2;
                    }
                    currnentTabDetail = tabShop;
                }
                this.isSell = true;
                break;
            }
            case 3: {
                currnentTabDetail = myInfo;
                break;
            }
            case 5: {
                this.getInfoNap();
                currnentTabDetail = new String[this.inFoNap.size()];
                int i = 0;
                while (i < this.inFoNap.size()) {
                    mCommand cmd = (mCommand) this.inFoNap.elementAt(i);
                    MainMenu.currnentTabDetail[i] = cmd.caption;
                    ++i;
                }
                break;
            }
            case 7: {
                currnentTabDetail = hoat_dong;
                break;
            }
            case 4: {
                currnentTabDetail = settings;
                break;
            }
            case 6: {
                currnentTabDetail = bangHoi;
                break;
            }
            case 1: {
                this.focusTab = 0;
                this.setSelectTab("setPosScroll 1");
                tabHanhTrang = new String[4];
                int i = 0;
                while (i < 4) {
                    MainMenu.tabHanhTrang[i] = "T\u00fai " + (i + 1);
                    ++i;
                }
                currnentTabDetail = tabHanhTrang;
                if (this.isMiniShop)
                    break;
                this.isSell = false;
            }
        }
    }

    public int getCountPotion(int i) {
        return GameScr.mainChar.potions[i];
    }

    public mVector getInventori() {
        mVector menu = new mVector();
        int count = 0;
        int size5 = Char.inventory.size();
        int i = 0;
        while (i < size5) {
            int ii = count++;
            Item item = (Item) Char.inventory.elementAt(i);
            SetInfoData dt79 = new SetInfoData();
            dt79.itemInven1 = item;
            dt79.xx = ii;
            dt79.itemType = item.type;
            menu.addElement(new mCommand("", (IActionListener) this, 79, dt79, dt79));
            ++i;
        }
        return menu;
    }

    public mVector getWearing() {
        mVector menu = new mVector();
        int count = 0;
        if (MainMenu.charWearing.equip != null) {
            int size5 = MainMenu.charWearing.equip.length;
            int i = 0;
            while (i < size5) {
                int ii = count;
                Item item = MainMenu.charWearing.equip[i];
                if (item != null) {
                    SetInfoData dt45 = new SetInfoData();
                    dt45.itemInven1 = item;
                    dt45.xx = ii;
                    menu.addElement(new mCommand("", (IActionListener) this, 4, dt45, dt45));
                }
                ++count;
                ++i;
            }
        }
        return menu;
    }

    private void doSetLeftSellWeapon(int type) {
        this.left = new mCommand("Mua", (IActionListener) this, 117, (Object) String.valueOf(type));
        this.center = new mCommand("", (IActionListener) this, 117, (Object) String.valueOf(type));
    }

    private void setListPotion() {
        this.list.removeAllElements();
        int size0 = GameScr.shop.size();
    }

    private void doSetLeftPotion() {
        this.left = new mCommand("Mua", this, 124);
        this.center = new mCommand("", this, 124);
    }

    private void setListSellGemItem(mVector shop) {
        this.list.removeAllElements();
        int size0 = shop.size();
        int i = 0;
        while (i < size0) {
            GemTemp gem = (GemTemp) shop.elementAt(i);
            int ii = i;
            if (gem.isSell) {
                SetInfoData dt = new SetInfoData();
                dt.gemTempItem = gem;
                dt.xx = ii;
                this.list.addElement(new mCommand("", (IActionListener) this, 122, dt, dt));
            }
            ++i;
        }
        Cout.Debug("BUY COMMAND 222");
    }

    private void dosetLeftSellGemItem() {
        this.left = new mCommand("Mua", this, 119);
        this.center = new mCommand("", this, 119);
        Cout.Debug("BUY COMMAND 333");
    }

    public void doSubTab() {
        if (this.list.size() > 42) {
            int c = 0;
            if (this.list.size() % 42 == 0) {
                c = this.list.size() / 42;
            } else if (this.list.size() % 42 != 0) {
                c = this.list.size() / 42 + 1;
            }
            currnentTabDetail = new String[c];
            byte b = index[0];
            index = new byte[c];
            int i = 0;
            while (i < 5) {
                MainMenu.currnentTabDetail[i] = "Qu\u1ea7y " + (i + 1);
                MainMenu.index[i] = b;
                ++i;
            }
        }
    }

    private void setLeftKeepAndSellItem() {
        this.left = new mCommand(index[0] == 22 ? "Chuy\u1ec3n" : "Ch\u1ecdn", this, 31);
        this.center = new mCommand("", this, 31);
    }

    public void setLeftBuyItem() {
        this.left = new mCommand("Mua", this, 28);
        this.center = new mCommand("", this, 28);
        Cout.Debug("BUY COMMAND 111 ");
    }

    public void setMainTabSelect(String[] titlemaintab) {
        mainTab = titlemaintab;
        this.setTile();
    }

    public void resetAll() {
        this.list.removeAllElements();
        this.vHanhTrang.removeAllElements();
        this.left = new mCommand("", GameCanvas.instance, 0);
        block0: switch (this.indexMainTab) {
            case 0: {
                this.isCharWearing = false;
                this.isHanhTrang = false;
                if (this.isMiniShop) {
                    this.isPaintMoney = true;
                    switch (index[this.indexSubTab]) {
                        case 9: {
                            break block0;
                        }
                        case 10: {
                            break block0;
                        }
                        case 11:
                        case 12:
                        case 13: {
                            break block0;
                        }
                        case 14:
                        case 15:
                        case 16:
                        case 17:
                        case 18: {
                            break block0;
                        }
                        case 19: {
                            break block0;
                        }
                        case 22:
                        case 23: {
                            break block0;
                        }
                        case 24: {
                            this.setInfo(this.getListBuyItem(), "resetAll 0");
                            this.setLeftBuyItem();
                            break block0;
                        }
                    }
                    break;
                }
                if (this.isSkillClan)
                    break;
                this.getShopTemplate();
                this.setInfo(this.list, "resetAll 1");
                this.doBuyShopSpecial();
                break;
            }
            case 3: {
                this.isCharWearing = false;
                this.isHanhTrang = false;
                this.setPosWearing(true);
                break;
            }
            case 1: {
                this.setPosWearing(false);
                this.isHanhTrang = true;
                this.isPaintMoney = true;
                if (index[0] != 22 && index[0] != 23) {
                    this.setInfo(this.getInventori(), "resetAll 2");
                }
                this.left = new mCommand("Ch\u1ecdn", this, 2);
                if (!GameCanvas.isTouch)
                    break;
                this.left.caption = "";
                this.left.idAction = 1000;
                break;
            }
            case 2:
            case 8:
            case 9:
            case 10:
            case 11: {
                this.setPosWearing(false);
                charWearing = GameScr.mainChar;
                this.setTextCharInfo();
                isFocusCharWearing = false;
                isChangeSubTab = false;
                isFocusDetailMenu = false;
                this.isCharWearing = true;
                this.setInfo(this.getWearing(), "resetAll 3");
                this.left = new mCommand("Ch\u1ecdn", this, 2);
                if (!GameCanvas.isTouch)
                    break;
                this.left.caption = "";
                this.left.idAction = 1000;
            }
        }
    }

    public void resetAllInven() {
        if (this.indexMainTab != 1) {
            return;
        }
        this.list.removeAllElements();
        this.left = new mCommand("", GameCanvas.instance, 0);
        block0: switch (this.indexMainTab) {
            case 0: {
                this.isCharWearing = false;
                this.isHanhTrang = false;
                if (this.isMiniShop) {
                    this.isPaintMoney = true;
                    switch (index[this.indexSubTab]) {
                        case 9: {
                            break block0;
                        }
                        case 10: {
                            break block0;
                        }
                        case 11:
                        case 12:
                        case 13: {
                            break block0;
                        }
                        case 14:
                        case 15:
                        case 16:
                        case 17:
                        case 18: {
                            break block0;
                        }
                        case 19: {
                            break block0;
                        }
                        case 22:
                        case 23: {
                            break block0;
                        }
                        case 24: {
                            this.setInfo(this.getListBuyItem(), "resetAllInven 0");
                            this.setLeftBuyItem();
                            break block0;
                        }
                    }
                    break;
                }
                if (this.isSkillClan)
                    break;
                this.getShopTemplate();
                this.setInfo(this.list, "resetAllInven 1");
                this.doBuyShopSpecial();
                break;
            }
            case 3: {
                this.isCharWearing = false;
                this.isHanhTrang = false;
                this.setPosWearing(true);
                break;
            }
            case 1: {
                this.setPosWearing(false);
                this.isHanhTrang = true;
                this.isPaintMoney = true;
                if (index[0] != 22 && index[0] != 23) {
                    this.setInfo(this.getInventori(), "resetAllInven 2");
                }
                this.left = new mCommand("Ch\u1ecdn", this, 2);
                if (!GameCanvas.isTouch)
                    break;
                this.left.caption = "";
                this.left.idAction = 1000;
                break;
            }
            case 2:
            case 8:
            case 9:
            case 10:
            case 11: {
                this.setPosWearing(false);
                charWearing = GameScr.mainChar;
                isFocusCharWearing = false;
                isChangeSubTab = false;
                isFocusDetailMenu = false;
                this.isCharWearing = true;
                this.setInfo(this.getWearing(), " resetAllInven 3");
                this.left = new mCommand("Ch\u1ecdn", this, 2);
                if (!GameCanvas.isTouch)
                    break;
                this.left.caption = "";
                this.left.idAction = 1000;
            }
        }
    }

    public void setSelectTab(String pos) {
        this.countTextmua = 0;
        this.list.removeAllElements();
        this.left = new mCommand("", GameCanvas.instance, 0);
        block0: switch (this.indexMainTab) {
            case 0: {
                this.isCharWearing = false;
                this.isHanhTrang = false;
                if (this.isMiniShop) {
                    this.isPaintMoney = true;
                    switch (index[this.indexSubTab]) {
                        case 9: {
                            break block0;
                        }
                        case 10: {
                            break block0;
                        }
                        case 11:
                        case 12:
                        case 13: {
                            break block0;
                        }
                        case 14:
                        case 15:
                        case 16:
                        case 17:
                        case 18: {
                            break block0;
                        }
                        case 19: {
                            break block0;
                        }
                        case 22:
                        case 23: {
                            break block0;
                        }
                        case 24: {
                            this.setInfo(this.getListBuyItem(), "setSelectTab 0");
                            this.setLeftBuyItem();
                            break block0;
                        }
                    }
                    break;
                }
                if (this.isSkillClan)
                    break;
                this.getShopTemplate();
                this.setInfo(this.list, "setSelectTab 1");
                this.doBuyShopSpecial();
                break;
            }
            case 3: {
                this.isCharWearing = false;
                this.isHanhTrang = false;
                this.setPosWearing(true);
                break;
            }
            case 1: {
                if (selected > Char.inventory.size() - 1 || selected == -1) {
                    selected = 0;
                }
                this.setPosWearing(false);
                this.isHanhTrang = true;
                this.isPaintMoney = true;
                if (index[0] != 22 && index[0] != 23) {
                    this.setInfo(this.getInventori(), "setSelectTab 2");
                }
                this.left = new mCommand(T.chon, this, 2);
                if (GameCanvas.isTouch) {
                    this.left.caption = "";
                    this.left.idAction = 1000;
                }
                if (!GameCanvas.isTouch)
                    break;
                this.showinfonext();
                break;
            }
            case 2:
            case 8:
            case 9:
            case 10:
            case 11: {
                this.setPosWearing(false);
                charWearing = GameScr.mainChar;
                this.setTextCharInfo();
                isFocusCharWearing = false;
                isChangeSubTab = false;
                isFocusDetailMenu = false;
                this.isCharWearing = true;
                this.setInfo(this.getWearing(), "setSelectTab 3");
                this.left = new mCommand("Ch\u1ecdn", this, 2);
                if (!GameCanvas.isTouch)
                    break;
                this.left.caption = "";
                this.left.idAction = 1000;
            }
        }
    }

    private void getShopTemplate() {
        this.list.removeAllElements();
        int size0 = Res.shopTemplate.size();
        int i = 0;
        while (i < size0) {
            GemTemp shop = (GemTemp) Res.shopTemplate.elementAt(i);
            int ii = i;
            if (shop.shopType == this.focusTab && shop.isSell) {
                SetInfoData dt = new SetInfoData();
                dt.index = shop.id;
                dt.xx = ii;
                this.list.addElement(new mCommand("", (IActionListener) this, 73, dt, dt));
            }
            ++i;
        }
    }

    public void setInfo(mVector list, String pos) {
        this.list = list;
        this.setCmdCenter("setInfo");
    }

    public void getInfoNap() {
        if (GameCanvas.gameScr.decriptNap.size() == 0) {
            return;
        }
        this.inFoNap = new mVector();
        int i = 0;
        while (i < GameCanvas.gameScr.decriptNap.size()) {
            String syn = (String) GameCanvas.gameScr.syntaxNap.elementAt(i);
            String center = (String) GameCanvas.gameScr.centerNap.elementAt(i);
            String decription = (String) GameCanvas.gameScr.decriptNap.elementAt(i);
            String a = String.valueOf(syn) + ":" + center + ":" + decription;
            if (center.length() >= 4) {
                this.inFoNap.addElement(new mCommand((String) GameCanvas.gameScr.decriptNap.elementAt(i),
                        (IActionListener) this, 630, (Object) a));
            } else {
                this.inFoNap.addElement(new mCommand((String) GameCanvas.gameScr.decriptNap.elementAt(i),
                        (IActionListener) this, 660, (Object) a));
            }
            ++i;
        }
    }

    @Override
    public void paint(mGraphics g, int idAction, int x, int y, Object paint) {
        switch (idAction) {
            case 19: {
                break;
            }
            case 30: {
                break;
            }
            case 73: {
                SetInfoData dt73 = (SetInfoData) paint;
                FontTeam.smallFontColor[3].drawString(g,
                        String.valueOf(dt73.index > 1 ? (int) Res.getShopByID((short) ((short) dt73.index)).value : 1),
                        x + 10 - (this.size == 20 ? 4 : 0), y + 5 - (this.size == 20 ? 5 : 0), 1, false);
                break;
            }
            case 74: {
                SetInfoData dt74 = (SetInfoData) paint;
                break;
            }
            case 75: {
                SetInfoData dt75 = (SetInfoData) paint;
                break;
            }
            case 76: {
                SetInfoData dt76 = (SetInfoData) paint;
                if (dt76.gemTempItem.isEff == -1)
                    break;
                Res.paintRectColor(g, x - 9, y - 9, 17, 17, dt76.gemTempItem.count,
                        GemTemp.color[dt76.gemTempItem.isEff], -260847);
                dt76.gemTempItem.count += 3;
                if (dt76.gemTempItem.count <= 68)
                    break;
                dt76.gemTempItem.count = 0;
                break;
            }
            case 77: {
                SetInfoData dt77 = (SetInfoData) paint;
                break;
            }
            case 78: {
                SetInfoData dt78 = (SetInfoData) paint;
                break;
            }
            case 79: {
                SetInfoData dt79 = (SetInfoData) paint;
                break;
            }
            case 80: {
                break;
            }
            case 116: {
                break;
            }
            case 122: {
                SetInfoData dt122 = (SetInfoData) paint;
                break;
            }
            case 123: {
                SetInfoData dt123 = (SetInfoData) paint;
                break;
            }
            case 330: {
                break;
            }
            case 331: {
                break;
            }
            case 332: {
                break;
            }
        }
    }

    public void showMaincharInfo() {
        if (isFocusCharWearing) {
            int x00 = this.x + this.size / 2 + selected % this.colum * this.size + 16 + this.size * 2;
            int y00 = 11 + this.y + selected / this.colum * this.size;
            mVector minfo = this.infochar;
            this.setShowText(minfo, x00, y00, null, true, true);
            this.numItemStart = 0;
            this.numKhamNgoc = 0;
            this.idNgocKham = null;
        }
    }

    private void showItemInventoryInfo(Item item, boolean isSell, int x, int y, Item itCompare) {
        this.setShowText(item.getInfoItemShow(itCompare, true), x, y, null, true, false);
    }

    public void updateItemDapdo() {
        int x0 = this.x + this.size / 2 + selected % this.colum * this.size + 16;
        int y0 = 11 + this.y + selected / this.colum * this.size + this.size * 3 / 2 + this.size - 1;
        this.setShowText(mItem.getInfoItemShow(null, true), x0, y0, null, true, false);
        this.setStart();
    }

    public void doPutItemShop() {
        this.closeText();
    }

    public void doSelectedInventori() {
        mVector menu = new mVector();
        if (!GameCanvas.isTouch) {
            this.closeText();
        }
        int i = 0;
        while (i < Char.inventory.size()) {
            if (i == selected) {
                Item it = (Item) Char.inventory.elementAt(i);
                SetInfoData dt79 = new SetInfoData();
                dt79.itemInven1 = it;
                dt79.xx = i;
                dt79.itemType = it.type;
                cmdban = new mCommand(T.ban, (IActionListener) this, 102, (Object) dt79);
                cmdsudung = new mCommand(T.sudung, (IActionListener) this, -3, (Object) it);
                cmdvut = new mCommand(T.vut, (IActionListener) this, 108, (Object) it);
                menu.addElement(cmdban);
                menu.addElement(cmdsudung);
                menu.addElement(cmdvut);
                if (it.type != Item.TYPE_MP && it.type != Item.TYPE_HP && it.catagory != 4) {
                    menu.addElement(cmdbanNhieu);
                } else {
                    menu.addElement(cmdGangphim);
                }
                if (GameCanvas.isTouch)
                    break;
                GameCanvas.menu.startAt(menu, 2);
                break;
            }
            ++i;
        }
    }

    private boolean isWeapone(int type) {
        return type >= 3 && type <= 7;
    }

    protected void doUseItem() {
        Item item = (Item) Char.inventory.elementAt(selected);
        if (item != null) {
            if (item.type == Item.TYPE_HP || item.type == Item.TYPE_MP) {
                if (item.type == Item.TYPE_HP && GameScr.mainChar.hp < GameScr.mainChar.maxhp) {
                    GameService.gI().useItem(item.ID);
                    return;
                }
                if (item.type == Item.TYPE_MP && GameScr.mainChar.mp < GameScr.mainChar.maxmp) {
                    GameService.gI().useItem(item.ID);
                    return;
                }
            }
            if (item.quantity > 1) {
                // if (GameCanvas.inputDlg.tfInput == null)
                // GameCanvas.inputDlg.tfInput = new TField();
                // GameCanvas.inputDlg.setInfo("Nhập số lượng muốn sử dụng", new mCommand("Ok",
                // this, 2704), 1, 10, true);
                // GameCanvas.inputDlg.show();
                // GameCanvas.gameScr.hideGUI = 0;

                GameCanvas.inputDlg.setInfo(new String[] { "Nhập số lượng muốn sử dụng" }, "OK", new byte[] { 0 }, 100,
                        false, 0, 2704, "Số lượng", "");
                GameCanvas.inputDlg.show();
                GameCanvas.gameScr.hideGUI = 0;
                return;
            }

            GameService.gI().useItem(item.ID);
        }
    }

    public void doBuyItem() {
        if (this.isMiniShop) {
            this.focusTab = index[this.indexTabMiniShop];
        }
        this.readyBuy.size();
        if (this.focusTab != 21) {
            // empty if block
        }
    }

    protected void doGiveSkillToQuickSlot(int skillType) {
        mVector menuItems = new mVector();
        if (this.indexMainTab == 1) {
            Item item = (Item) Char.inventory.elementAt(selected);
            if (item != null && (item.type == Item.TYPE_HP || item.type == Item.TYPE_MP || item.catagory == 4)) {
                int i = 1;
                while (i < 5) {
                    SetInfoData dt = new SetInfoData();
                    dt.ID = item.ID;
                    dt.isSkill = false;
                    dt.idIcon = item.idIcon;
                    dt.typePotion = item.type;
                    menuItems.addElement(new mCommand(String.valueOf(T.phimso) + " " + (1 + i), (IActionListener) this,
                            42 + i, (Object) dt));
                    ++i;
                }
                GameCanvas.menu.startAt(menuItems, 2);
            }
        } else {
            boolean buff = false;
            SkillTemplate skill = (SkillTemplate) GameScr.vec_skill.elementAt(selected);
            if (skill != null) {
                if (skill.type == SkillTemplate.TYPE_PASSIVE) {
                    return;
                }
                if (skill.type == SkillTemplate.TYPE_BUFF) {
                    buff = true;
                }
                int i = 0;
                while (i < 5) {
                    int ii = i;
                    SetInfoData dt = new SetInfoData();
                    dt.index = skillType;
                    dt.iss = buff;
                    dt.xx = ii;
                    dt.isSkill = true;
                    menuItems.addElement(new mCommand(String.valueOf(T.phimso) + " " + (1 + i), (IActionListener) this,
                            42 + i, (Object) dt));
                    ++i;
                }
                GameCanvas.menu.startAt(menuItems, 2);
            }
        }
    }

    public boolean setSizeKeepItem(int i) {
        return false;
    }

    public mVector getListBuyItem() {
        mVector menu = new mVector();
        int size0 = this.sellItem.size();
        int i = 0;
        while (i < size0) {
            int ii = i++;
            menu.addElement(new mCommand("", (IActionListener) this, 30, String.valueOf(ii), String.valueOf(ii)));
        }
        return menu;
    }

    public void useItemByAmount(String amount) {
        try {
            String str = amount;
            if (str.equals(""))
                return;
            int count = Integer.parseInt(str);
            Item item = (Item) Char.inventory.elementAt(selected);
            if (item != null) {
                if (item.quantity < count || count < 1) {
                    GameCanvas.startOKDlg("Số lượng không hợp lệ.");
                    return;
                }
                for (int i = 0; i < count; i++) {
                    GameService.gI().useItem(item.ID);
                }
            }
        } catch (Exception e) {

        }
    }

    @Override
    public void perform(int idAction, Object p) {
        mVector minfo = new mVector();
        InfoTextShow in = null;
        String inFo = null;
        String[] array = null;
        int x0 = this.x + this.size / 2 + selected % this.colum * this.size + 16;
        int y0 = 11 + this.y + selected / this.colum * this.size + this.size * 3 / 2 + this.size - 1;
        Cout.Debug(" idAction " + idAction);
        mSystem.println("perform mainmenu " + idAction);
        block3: switch (idAction) {
            case -1000: {
                break;
            }
            case -100: {
                break;
            }
            case 79: {
                Item ite79 = (Item) Char.inventory.elementAt(selected);
                if (ite79 == null)
                    break;
                int type = ite79.getTypeItem();
                Item itcp = null;
                this.isgangphim = false;
                if (POS_ITEM_IN_EQUIP[type] > -1) {
                    itcp = GameScr.mainChar.equip[POS_ITEM_IN_EQUIP[type]];
                }
                this.showItemInventoryInfo(ite79, this.isSell, x0, y0, itcp);
                if (!GameCanvas.isTouch || p == null)
                    break;
                this.vHanhTrang.removeAllElements();
                cmdsudung = new mCommand(T.sudung, (IActionListener) this, -3, (Object) ite79);
                cmdvut = new mCommand(T.vut, (IActionListener) this, 108, (Object) ite79);
                cmdban = new mCommand(T.ban, (IActionListener) this, 102, (Object) ite79);
                this.vHanhTrang.addElement(cmdban);
                this.vHanhTrang.addElement(cmdsudung);
                this.vHanhTrang.addElement(cmdvut);
                if (ite79.type != Item.TYPE_MP && ite79.type != Item.TYPE_HP && ite79.catagory != 4) {
                    this.vHanhTrang.addElement(cmdbanNhieu);
                } else {
                    this.isgangphim = true;
                    this.vHanhTrang.addElement(cmdGangphim);
                }
                this.SortCmdItem();
                break;
            }
            case 4: {
                if (p == null)
                    break;
                SetInfoData dt45 = (SetInfoData) p;
                this.showItemInventoryInfo(dt45.itemInven1, this.isSell, x0, y0, null);
                break;
            }
            case 2: {
                if (this.indexMainTab == 8 && !GameCanvas.isTouch) {
                    this.doSelectItemCuongHoa();
                    return;
                }
                if (this.indexMainTab != 2 && this.indexMainTab != 8 && this.indexMainTab != 9
                        && this.indexMainTab != 10 && this.indexMainTab != 11) {
                    this.doAction2();
                    break;
                }
                if (isFocusCharWearing) {
                    int x00 = this.x + this.size / 2 + selected % this.colum * this.size + 16 + this.size * 2;
                    int y00 = 11 + this.y + selected / this.colum * this.size;
                    minfo = this.infochar;
                    mVector listcolorpaint = new mVector();
                    int i = 0;
                    while (i < GameScr.mainChar.options.size()) {
                        ItemOption itop = (ItemOption) GameScr.mainChar.options.elementAt(i);
                        String[] option = itop.getInfoShow(0);
                        short[] colorindex = itop.getColorPaint();
                        int j = 0;
                        while (j < colorindex.length) {
                            listcolorpaint.addElement(String.valueOf(colorindex[j]));
                            ++j;
                        }
                        ++i;
                    }
                    this.setShowText(minfo, x00, y00, null, true, true);
                    this.numItemStart = 0;
                    this.numKhamNgoc = 0;
                    this.idNgocKham = null;
                    break;
                }
                if (!isFocusCharWearing && isFocusDetailMenu) {
                    if (selected >= 4)
                        break;
                    this.dofireBasePoint();
                    break;
                }
                if (isFocusCharWearing || isFocusDetailMenu)
                    break;
                this.doAction2();
                this.doShowInfoItemWearing();
                break;
            }
            case -3: {
                if (this.indexMainTab == 3) {
                    if (this.indexWearing > 0)
                        break;
                    if (this.indexMainTab == 1) {
                        if (p != null) {
                            this.doUseItem();
                        }
                    } else if (this.isTabQuest()) {
                        String infoMain = "";
                        mVector currQuest = ListQuest[indexTypeQuest];
                        QuestInfo q = (QuestInfo) currQuest.elementAt(indexQuest);
                        if (q != null) {
                            in = new InfoTextShow(new String[] { q.name }, 0);
                            minfo.addElement(in);
                            String[] data = Util.split(q.info, "\n");
                            int i = 0;
                            while (i < data.length) {
                                byte color = (byte) (data[i].charAt(0) - 48);
                                if (!MainMenu.isDegit(data[i].charAt(0))) {
                                    color = 0;
                                } else {
                                    data[i] = data[i].substring(1);
                                }
                                in = new InfoTextShow(new String[] { data[i] }, color);
                                minfo.addElement(in);
                                ++i;
                            }
                            infoMain = String.valueOf(q.name) + " \n" + q.info;
                        }
                        if (!infoMain.equals("")) {
                            this.setShowText(minfo, this.x + this.colum * this.size, this.y + this.size * 2, null, true,
                                    false);
                        }
                    }
                    if (this.isSkill) {
                        this.showInfoSkill("perform -3");
                    }
                    if (this.isCharWearing && selected >= 0 && selected < MainMenu.charWearing.equip.length - 1
                            && MainMenu.charWearing.equip[selected] != null) {
                        this.showItemInventoryInfo(MainMenu.charWearing.equip[selected], this.isSell, x0, y0, null);
                    }
                    if (selected != 14)
                        break;
                    GameService.gI().dochangeCharWearing();
                    break;
                }
                if (this.indexMainTab == 1) {
                    if (p != null) {
                        this.doUseItem();
                    }
                } else if (this.isTabQuest()) {
                    String infoMain = "";
                    mVector currQuest = ListQuest[indexTypeQuest];
                    QuestInfo q = (QuestInfo) currQuest.elementAt(indexQuest);
                    if (q != null) {
                        in = new InfoTextShow(new String[] { q.name }, 0);
                        minfo.addElement(in);
                        String[] data = Util.split(q.info, "\n");
                        int i = 0;
                        while (i < data.length) {
                            byte color = (byte) (data[i].charAt(0) - 48);
                            if (!MainMenu.isDegit(data[i].charAt(0))) {
                                color = 0;
                            } else {
                                data[i] = data[i].substring(1);
                            }
                            in = new InfoTextShow(new String[] { data[i] }, color);
                            minfo.addElement(in);
                            ++i;
                        }
                        infoMain = String.valueOf(q.name) + " \n" + q.info;
                    }
                    if (!infoMain.equals("")) {
                        this.setShowText(minfo, this.x + this.colum * this.size, this.y + this.size * 2, null, true,
                                false);
                    }
                }
                if (this.isSkill) {
                    this.showInfoSkill("perform -3 2");
                }
                if (this.isCharWearing && selected >= 0 && selected < MainMenu.charWearing.equip.length - 1
                        && MainMenu.charWearing.equip[selected] != null) {
                    this.showItemInventoryInfo(MainMenu.charWearing.equip[selected], this.isSell, x0, y0, null);
                }
                if (selected != 14)
                    break;
                GameService.gI().dochangeCharWearing();
                break;
            }
            case -2: {
                SetInfoData dtshop = (SetInfoData) p;
                this.showItemInventoryInfo(dtshop.itemInven1, this.isSell, x0, y0, null);
                break;
            }
            case -1: {
                if (!GameCanvas.isTouch)
                    break;
                this.showInfoSkill("perform -1");
                break;
            }
            case 0: {
                this.doCmdCenter();
                if (!this.isSkill || !GameCanvas.isTouch)
                    break;
                this.showInfoSkill("perform 0");
                break;
            }
            case -101: {
                if (!this.isSkill)
                    break;
                this.showInfoSkill("perform -101");
                break;
            }
            case 1: {
                this.doClose();
                break;
            }
            case 3: {
                mVector menu = new mVector();
                menu.addElement(new mCommand("Ch\u1ecdn", this, 2));
                menu.addElement(new mCommand("Xem th\u00eam", this, 4));
                GameCanvas.menu.startAt(menu, 0);
                if (!this.isSkill)
                    break;
                this.showInfoSkill("perform 3");
                break;
            }
            case 5: {
                Item mit = (Item) this.mShop.elementAt(selected);
                if (mit == null)
                    break;
                if (!GameCanvas.isTouch) {
                    String str = String.valueOf(T.doyouwantbuy) + " " + mit.name + " " + T.withprice + " "
                            + mit.priceShop + " " + Item.moneyType[mit.priceType] + " " + T.no + " ?";
                    if (!captionServer.equals("") && !infoBuySellServer.equals("")) {
                        str = MainMenu.getInfoBuySell(captionServer, infoBuySellServer, mit);
                    }
                    GameCanvas.startYesNoDlg(str, new mCommand("", this, 7));
                    break;
                }
                if (!this.canbuy) {
                    this.textHoimua = String.valueOf(T.doyouwantbuy) + " " + mit.name + " " + T.withprice + " "
                            + mit.priceShop + " " + Item.moneyType[mit.priceType] + " " + T.no + " ?";
                    if (!captionServer.equals("") && !infoBuySellServer.equals("")) {
                        this.textHoimua = MainMenu.getInfoBuySell(captionServer, infoBuySellServer, mit);
                    }
                    this.showTextmua();
                    break;
                }
                this.canbuy = false;
                MainMenu.cmdBuy.caption = T.Buy;
                GameService.gI().buyItem(mit.ID, this.idNPC_Shop, this.catNPC_Shop, mit.catagory, 1);
                mSystem.println("send mua item to sv ");
                this.isTextmua = false;
                MainMenu.cmdBuy.caption = T.Buy;
                this.canbuy = false;
                break;
            }
            case 6: {
                int num1 = 1;
                try {
                    num1 = Integer.parseInt(GameCanvas.inputDlg.tfInput.getText());
                } catch (Exception e) {
                    num1 = 0;
                }
                if (num1 > 0) {
                    Item mit1 = (Item) this.mShop.elementAt(selected);
                    if (mit1 == null)
                        break;
                    String str = String.valueOf(T.doyouwantbuy) + " " + num1 + " " + mit1.name + " " + T.withprice + " "
                            + num1 * mit1.priceShop + " " + Item.moneyType[mit1.priceType] + " " + T.no + " ?";
                    SetInfoData info = new SetInfoData();
                    info.cat = mit1.catagory;
                    info.ID = mit1.ID;
                    info.num = num1;
                    GameCanvas.startYesNoDlg(str, new mCommand("", (IActionListener) this, 8, (Object) info));
                    break;
                }
                GameCanvas.endDlg();
                break;
            }
            case 7: {
                Item it7 = (Item) this.mShop.elementAt(selected);
                if (it7 != null) {
                    GameService.gI().buyItem(it7.ID, this.idNPC_Shop, this.catNPC_Shop, it7.catagory, 1);
                } else {
                    GameCanvas.endDlg();
                }
                GameCanvas.endDlg();
                break;
            }
            case 8: {
                SetInfoData info8 = (SetInfoData) p;
                GameService.gI().buyItem(info8.ID, this.idNPC_Shop, this.catNPC_Shop, info8.cat, info8.num);
                GameCanvas.endDlg();
                break;
            }
            case 9: {
                mVector vt9 = (mVector) p;
                GameCanvas.menu.startAt(vt9, 0);
                break;
            }
            case 10: {
                GameService.gI().receiveQuest(0, 0, 3);
                this.questInfo.removeAllElements();
                break;
            }
            case 11: {
                String str11 = GameCanvas.inputDlg.tfInput.getText();
                if (!str11.equals("")) {
                    GameCanvas.startYesNoDlg(T.chatngoc,
                            new mCommand(T.OK, (IActionListener) this, 12, (Object) str11));
                    break;
                }
                GameCanvas.endDlg();
                break;
            }
            case 12: {
                if (p == null)
                    break;
                String strTG = (String) p;
                GameService.gI().doChatWorld(strTG);
                GameCanvas.endDlg();
                break;
            }
            case 13: {
                Item sItem;
                if (this.slDapdo1 < 0 || vecItemDapDo.size() <= 0
                        || (sItem = (Item) vecItemDapDo.elementAt(this.slDapdo1)) == null)
                    break;
                if (sItem.pos == 0) {
                    if (itemStone == null) {
                        itemStone = sItem;
                    }
                    if (this.numStone >= 10 || itemStone != null && this.numStone >= MainMenu.itemStone.quantity
                            || textPercent.equals("100,0%")) {
                        GameCanvas.addNotify(T.khongthebothem, (byte) 0);
                        return;
                    }
                    int sizee = 10;
                    if (isHopda) {
                        sizee = 5;
                    }
                    if (this.numStone < sizee && this.numStone < MainMenu.itemStone.quantity) {
                        ++this.numStone;
                    }
                    this.vHanhTrang.removeAllElements();
                    if (itemStone != null && mItem != null) {
                        this.vHanhTrang.addElement(new mCommand(T.cuonghoa, this, 14));
                        this.doSendCuongHoa(0);
                    }
                    if (isHopda) {
                        this.vHanhTrang.addElement(new mCommand(T.nangcap, this, 14));
                        this.doSendCuongHoa(0);
                    }
                    this.vHanhTrang.addElement(new mCommand(T.bovao, this, 13));
                    this.vHanhTrang.addElement(new mCommand(T.layra, this, 26));
                    this.SortCmdItem();
                }
                if (sItem.pos == 1 && itemBaohiem == null) {
                    itemBaohiem = sItem;
                    this.vHanhTrang.removeAllElements();
                    if (itemStone != null && this.numStone > 0 && mItem != null) {
                        this.vHanhTrang.addElement(new mCommand(T.cuonghoa, this, 14));
                    }
                    this.vHanhTrang.addElement(new mCommand(T.layra, this, 27));
                    this.SortCmdItem();
                }
                if (sItem.pos != 2 || itemBua != null)
                    break;
                itemBua = sItem;
                this.vHanhTrang.removeAllElements();
                if (itemStone != null && this.numStone > 0 && mItem != null) {
                    this.doSendCuongHoa(0);
                    this.vHanhTrang.addElement(new mCommand(T.cuonghoa, this, 14));
                }
                this.vHanhTrang.addElement(new mCommand(T.layra, this, 28));
                this.SortCmdItem();
                break;
            }
            case 14: {
                if (isHopda) {
                    this.doSendCuongHoa(4);
                    break;
                }
                this.doSendCuongHoa(1);
                break;
            }
            case 15: {
                if (GameCanvas.isTouch) {
                    GameCanvas.mapScr.switchToMe(this.lastSCR);
                    break;
                }
                mVector v = new mVector();
                mVector currQuest1 = ListQuest[indexTypeQuest];
                QuestInfo qq = (QuestInfo) currQuest1.elementAt(indexQuest);
                if (qq != null && qq.status == 2) {
                    v.addElement(cmdHuyQuest);
                }
                v.addElement(cmdChangeMapScr);
                GameCanvas.menu.startAt(v, 0);
                break;
            }
            case 16: {
                break;
            }
            case 17: {
                mVector currQuest = ListQuest[indexTypeQuest];
                QuestInfo q = (QuestInfo) currQuest.elementAt(indexQuest);
                GameService.gI().receiveQuestNew(2, q.idQuest, q.mainsub);
                this.vHanhTrang.removeElement(cmdHuyQuest);
                break;
            }
            case 18: {
                GameCanvas.mapScr.switchToMe(this.lastSCR);
                break;
            }
            case 19: {
                if (Char.Diemtiemnang > 0) {
//                    if (!this.isTextmua) {
//                        String ct1;
//                        this.textHoimua = ct1 = String.valueOf(T.hoicong) + " " + 1 + " " + T.point + " "
//                                + T.nametiemnang[selected] + " " + T.khong + " ?";
//                        this.showTextmua();
//                        this.vHanhTrang.removeElement(cmdcong5);
//                        this.vHanhTrang.removeElement(cmdcong10);
//                        MainMenu.cmdcong1.caption = T.yes;
//                        this.SortCmdItem();
//                        break;
//                    }
//                    GameService.gI().SkillPoint(1, selected, 1);
//                    this.resetPopup();
//                    try {
//
//                        GameCanvas.inputDlg.setInfo("Nhập số điểm muốn cộng", new mCommand("Ok", this, 21), 1, 10, true);
//                        GameCanvas.inputDlg.show();
//                        GameCanvas.gameScr.hideGUI = 0;
//                    } catch (Exception e) {
//
//                    }

                    GameService.gI().SkillPoint(2, selected, 1);

                    break;
                }
                GameCanvas.addNotify(T.khongthecong, (byte) 0);
                break;
            }
            case 20: {
                if (Char.Diemtiemnang >= 5) {
                    if (!this.isTextmua) {
                        String ct2;
                        this.textHoimua = ct2 = String.valueOf(T.hoicong) + " " + 5 + " " + T.point + " "
                                + T.nametiemnang[selected] + " " + T.khong + " ?";
                        this.showTextmua();
                        this.vHanhTrang.removeElement(cmdcong1);
                        this.vHanhTrang.removeElement(cmdcong10);
                        MainMenu.cmdcong5.caption = T.yes;
                        this.SortCmdItem();
                        break;
                    }
                    GameService.gI().SkillPoint(1, selected, 5);
                    this.resetPopup();
                    break;
                }
                GameCanvas.addNotify(T.khongthecong, (byte) 0);
                break;
            }
            case 21: {
//                if (Char.Diemtiemnang >= 10) {
//                    if (!this.isTextmua) {
//                        String ct3;
//                        this.textHoimua = ct3 = String.valueOf(T.hoicong) + " " + 10 + " " + T.point + " "
//                                + T.nametiemnang[selected] + " " + T.khong + " ?";
//                        this.showTextmua();
//                        this.vHanhTrang.removeElement(cmdcong5);
//                        this.vHanhTrang.removeElement(cmdcong1);
//                        MainMenu.cmdcong10.caption = T.yes;
//                        this.SortCmdItem();
//                        break;
//                    }
//                    GameService.gI().SkillPoint(1, selected, 10);
//                    this.resetPopup();
//                    break;
//                }
//                GameCanvas.addNotify(T.khongthecong, (byte) 0);
                GameCanvas.endDlg();
                String str = GameCanvas.inputDlg.tfInput.getText();
                GameCanvas.endDlg();
                if (str.equals("") || str.isEmpty()) {
                    GameCanvas.addNotify(T.khongthecong, (byte) 0);
                    return;
                }
                int point = Integer.parseInt(str);
                if (point > Char.Diemtiemnang) {
                    GameCanvas.addNotify(T.khongthecong, (byte) 0);
                    return;
                }
                GameService.gI().SkillPoint(1, selected, point);
                break;
            }
            case 22: {
                GameService.gI().SkillPoint(1, selected, 1);
                GameCanvas.endDlg();
                break;
            }
            case 23: {
                GameService.gI().SkillPoint(1, selected, 5);
                GameCanvas.endDlg();
                break;
            }
            case 24: {
                GameService.gI().SkillPoint(1, selected, 10);
                GameCanvas.endDlg();
                break;
            }
            case 25: {
                GameService.gI().sellItem((byte) 0, (short) -1);
                break;
            }
            case 26: {
                textPercent = "";
                --this.numStone;
                if (this.numStone <= 0) {
                    this.numStone = 0;
                    itemStone = null;
                    this.vHanhTrang.removeAllElements();
                    this.vHanhTrang.addElement(new mCommand(T.bovao, this, 13));
                }
                if (itemStone != null && isHopda) {
                    this.doSendCuongHoa(0);
                }
                if (itemStone != null && mItem != null) {
                    this.doSendCuongHoa(0);
                }
                this.SortCmdItem();
                break;
            }
            case 27: {
                itemBaohiem = null;
                this.vHanhTrang.removeAllElements();
                if (itemStone != null && mItem != null && this.numStone > 0) {
                    this.vHanhTrang.addElement(new mCommand(T.cuonghoa, this, 14));
                }
                this.vHanhTrang.addElement(new mCommand(T.bovao, this, 13));
                this.SortCmdItem();
                break;
            }
            case 28: {
                itemBua = null;
                this.vHanhTrang.removeAllElements();
                if (itemStone != null && mItem != null && this.numStone > 0) {
                    this.vHanhTrang.addElement(new mCommand(T.cuonghoa, this, 14));
                    this.doSendCuongHoa(0);
                }
                if (itemStone != null && mItem != null) {
                    this.doSendCuongHoa(0);
                }
                this.vHanhTrang.addElement(new mCommand(T.bovao, this, 13));
                this.SortCmdItem();
                break;
            }
            case 29: {
                int p29;
                Item ite29 = (Item) vecItemCreate.elementAt(this.slDapdo1);
                if (ite29 == null)
                    break;
                if (!this.checkinVector(vecMaterial, ite29)) {
                    vecMaterial.addElement(ite29);
                }
                if ((p29 = this.getpost(vecMaterial, ite29)) == -1)
                    break;
                if (this.listNum[p29] == 0 && ite29.quantity >= this.miniItem) {
                    int n = p29;
                    this.listNum[n] = this.listNum[n] + this.miniItem;
                    break;
                }
                if (this.listNum[p29] < this.miniItem && ite29.quantity >= this.miniItem) {
                    int n = p29;
                    this.listNum[n] = this.listNum[n] + 1;
                    break;
                }
                GameCanvas.addNotify(T.khongthebothem, (byte) 0);
                break;
            }
            case 30: {
                Item ite30 = (Item) vecItemCreate.elementAt(this.slDapdo1);
                if (ite30 == null)
                    break;
                int p30 = this.getpost(vecMaterial, ite30);
                if (p30 != -1 && this.listNum[p30] == this.miniItem) {
                    this.listNum[p30] = 0;
                }
                if (p30 != -1 && this.listNum[p30] > 0) {
                    int n = p30;
                    this.listNum[n] = this.listNum[n] - 1;
                }
                if (p30 == -1 || this.listNum[p30] > 0)
                    break;
                vecMaterial.removeElementAt(p30);
                break;
            }
            case 31: {
                if (this.Waitcreate)
                    break;
                if (this.listNum != null && this.listNum.length > 5 && vecMaterial.size() > 5) {
                    GameService.gI().doCreateItem(vecMaterial, this.listNum);
                    break;
                }
                GameCanvas.addNotify(T.khongthetao, (byte) 0);
                break;
            }
            case 32: {
                Item itemch = (Item) vecItemCreate.elementAt(this.slDapdo1);
                if (itemch == null)
                    break;
                if (itemch.plus >= 10) {
                    itemChuyenHoa1 = itemch;
                }
                if (itemch.plus >= 4 && itemch.plus < 10) {
                    itemChuyenHoa0 = itemch;
                }
                if (!itemch.isPotion())
                    break;
                itemChuyenHoa2 = itemch;
                break;
            }
            case 33: {
                if (itemChuyenHoa0 != null && itemChuyenHoa1 != null) {
                    this.tickChuyenhoa = -1;
                    short id3 = -1;
                    if (itemChuyenHoa2 != null) {
                        id3 = MainMenu.itemChuyenHoa2.ID;
                    }
                    GameService.gI().doUpgradeItem((byte) 5, MainMenu.itemChuyenHoa0.ID, MainMenu.itemChuyenHoa1.ID,
                            id3);
                    break;
                }
                GameCanvas.addNotify(T.khongthechuyenhoa, (byte) 0);
                break;
            }
            case 34: {
                this.right.performAction();
                GameCanvas.endDlg();
                break;
            }
            case 35: {
                Item ite35;
                mSystem.println("perfom 35 ddd  " + this.typePhiPhong);
                if (this.typePhiPhong == 0) {
                    int p29;
                    Item ite352;
                    if (idicon != -1) {
                        idicon = -1;
                    }
                    if ((ite352 = (Item) vecItemCreate.elementAt(this.slDapdo1)) == null)
                        break;
                    if (!this.checkinVector(vecMaterial, ite352)) {
                        vecMaterial.addElement(ite352);
                    }
                    if ((p29 = this.getpost(vecMaterial, ite352)) == -1)
                        break;
                    if (this.listNum[p29] == 0 && ite352.quantity >= this.miniItem) {
                        int n = p29;
                        this.listNum[n] = this.listNum[n] + this.miniItem;
                        break;
                    }
                    if (this.listNum[p29] < this.miniItem && ite352.quantity >= this.miniItem) {
                        int n = p29;
                        this.listNum[n] = this.listNum[n] + 1;
                        break;
                    }
                    GameCanvas.addNotify(T.khongthebothem, (byte) 0);
                    break;
                }
                if (this.typePhiPhong != 1 || (ite35 = (Item) vecItemCreate.elementAt(this.slDapdo1)) == null)
                    break;
                if (this.CheckList(vecMaterial, ite35)) {
                    GameCanvas.startOKDlg(T.bandabo);
                    return;
                }
                if (ite35.isPotion()) {
                    if (idicon != -1) {
                        int p29;
                        if (!this.checkinVectorPhiPhong(vecMaterial, ite35)) {
                            vecMaterial.addElement(ite35);
                            int pos = this.getPost();
                            if (pos != -1 && ite35.quantity >= this.miniItem) {
                                this.listItem[pos] = ite35;
                                this.listNum[pos] = this.miniItem;
                                return;
                            }
                        }
                        if ((p29 = this.getpostPhiPhong(vecMaterial, ite35)) == -1)
                            break;
                        if (this.listNum[p29] == 0 && ite35.quantity >= this.miniItem) {
                            int n = p29;
                            this.listNum[n] = this.listNum[n] + this.miniItem;
                            break;
                        }
                        if (this.listNum[p29] < this.miniItem && ite35.quantity >= this.miniItem) {
                            int n = p29;
                            this.listNum[n] = this.listNum[n] + 1;
                            break;
                        }
                        GameCanvas.addNotify(T.khongthebothem, (byte) 0);
                        break;
                    }
                    GameCanvas.startOKDlg(T.bophiphongvao);
                    break;
                }
                idicon = ite35.idIcon;
                this.itemPP = ite35;
                GameService.gI().doCreatephiphong(1, ite35, null);
                vecMaterial.removeAllElements();
                this.listNum = null;
                this.listItem = new Item[6];
                this.listNum = new int[6];
                break;
            }
            case 36: {
                int p30;
                Item ite36;
                if (this.typePhiPhong == 0) {
                    Item ite362 = (Item) vecItemCreate.elementAt(this.slDapdo1);
                    if (ite362 == null)
                        break;
                    int p302 = this.getpost(vecMaterial, ite362);
                    if (p302 != -1 && this.listNum[p302] == this.miniItem) {
                        this.listNum[p302] = 0;
                    }
                    if (p302 != -1 && this.listNum[p302] > 0) {
                        int n = p302;
                        this.listNum[n] = this.listNum[n] - 1;
                    }
                    if (p302 == -1 || this.listNum[p302] > 0)
                        break;
                    vecMaterial.removeElementAt(p302);
                    break;
                }
                if (this.typePhiPhong != 1 || (ite36 = (Item) vecItemCreate.elementAt(this.slDapdo1)) == null
                        || (p30 = this.getpostPhiPhong(vecMaterial, ite36)) == -1)
                    break;
                vecMaterial.removeElement(ite36);
                this.listNum[p30] = 0;
                int i = 0;
                while (i < this.listItem.length) {
                    if (this.listItem[i] != null && this.listItem[i].ID == ite36.ID) {
                        this.listItem[i] = null;
                        break block3;
                    }
                    ++i;
                }
                break;
            }
            case 37: {
                if (this.typePhiPhong == 0) {
                    if (vecItemCreate.size() < 6) {
                        GameCanvas.addNotify(T.chuadunguyenlieu, (byte) 0);
                        break;
                    }
                    GameService.gI().doCreatephiphong(0, null, null);
                    break;
                }
                if (this.typePhiPhong != 1)
                    break;
                int i = 0;
                while (i < this.listItem.length) {
                    if (this.listItem[i] == null) {
                        GameCanvas.startOKDlg(T.chuadunguyenlieu);
                        return;
                    }
                    ++i;
                }
                GameService.gI().doCreatephiphong_Array(2, this.itemPP, this.listItem);
                break;
            }
            case 38: {
                int p30;
                Item ite38 = this.listItem[this.FocusPhiPhong];
                if (ite38 != null && (p30 = this.getpostPhiPhong(vecMaterial, ite38)) != -1) {
                    vecMaterial.removeElement(ite38);
                    this.listNum[p30] = 0;
                    this.listNum[p30] = 0;
                    int i = 0;
                    while (i < this.listItem.length) {
                        if (this.listItem[i] != null && this.listItem[i].ID == ite38.ID) {
                            this.listItem[i] = null;
                            break;
                        }
                        ++i;
                    }
                }
                this.closeText();
                break;
            }
            case 39: {
                short indexitem = 0;
                if (selected < 0)
                    break;
                Item itempet = (Item) vecPetEat.elementAt(selected);
                if (itempet != null) {
                    indexitem = itempet.ID;
                }
                GameService.gI().doPetEat(indexitem, (byte) 1);
                break;
            }
            case 40: {
                this.doGiveSkillToQuickSlot(selected);
                break;
            }
            case 41: {
                break;
            }
            case 42: {
                SetInfoData dt42 = (SetInfoData) p;
                if (dt42.isSkill) {
                    MainChar.CheckQuicSlotSkill(dt42.index);
                    MainChar.mQuickslot[0].setIsSkill(dt42.index, dt42.iss);
                } else {
                    MainChar.CheckQuicSlotPotion(dt42.typePotion);
                    MainChar.mQuickslot[0].setIsPotion((short) dt42.idIcon);
                }
                Rms.saveQuickSlot();
                break;
            }
            case 43: {
                SetInfoData dt43 = (SetInfoData) p;
                if (dt43.isSkill) {
                    MainChar.CheckQuicSlotSkill(dt43.index);
                    MainChar.mQuickslot[1].setIsSkill(dt43.index, dt43.iss);
                } else {
                    MainChar.CheckQuicSlotPotion(dt43.typePotion);
                    MainChar.mQuickslot[1].setIsPotion((short) dt43.idIcon);
                }
                Rms.saveQuickSlot();
                break;
            }
            case 44: {
                SetInfoData dt44 = (SetInfoData) p;
                if (dt44.isSkill) {
                    MainChar.CheckQuicSlotSkill(dt44.index);
                    MainChar.mQuickslot[2].setIsSkill(dt44.index, dt44.iss);
                } else {
                    MainChar.CheckQuicSlotPotion(dt44.typePotion);
                    MainChar.mQuickslot[2].setIsPotion((short) dt44.idIcon);
                }
                Rms.saveQuickSlot();
                break;
            }
            case 45: {
                SetInfoData dt45 = (SetInfoData) p;
                if (dt45.isSkill) {
                    MainChar.CheckQuicSlotSkill(dt45.index);
                    MainChar.mQuickslot[3].setIsSkill(dt45.index, dt45.iss);
                } else {
                    MainChar.CheckQuicSlotPotion(dt45.typePotion);
                    MainChar.mQuickslot[3].setIsPotion((short) dt45.idIcon);
                }
                Rms.saveQuickSlot();
                break;
            }
            case 46: {
                SetInfoData dt46 = (SetInfoData) p;
                if (dt46.isSkill) {
                    MainChar.CheckQuicSlotSkill(dt46.index);
                    MainChar.mQuickslot[4].setIsSkill(dt46.index, dt46.iss);
                } else {
                    MainChar.CheckQuicSlotPotion(dt46.typePotion);
                    MainChar.mQuickslot[4].setIsPotion((short) dt46.idIcon);
                }
                Rms.saveQuickSlot();
                break;
            }
            case 47: {
                this.doSelectedSkill();
                break;
            }
            case 48: {
                this.closeText();
                this.indexWearing = 0;
                this.vHanhTrang.removeAllElements();
                this.vHanhTrang.addElement(cmdTB2);
                this.vHanhTrang.addElement(cmdThao);
                this.SortCmdItem();
                break;
            }
            case 49: {
                this.closeText();
                this.indexWearing = (byte) 15;
                this.vHanhTrang.removeAllElements();
                this.vHanhTrang.addElement(cmdTB1);
                this.vHanhTrang.addElement(cmdThao);
                this.SortCmdItem();
                break;
            }
            case 50:
                Item sItem1 = MainMenu.charWearing.equip[selected + this.indexWearing];
                if (sItem1 != null) {
                    // todo tháo trang bị send -76
                    GameService.gI().removeEquipt((short) (selected + this.indexWearing));
                }
                break;
            case 52: {
                break;
            }
            case 60: {
                // Unequip mount item
                if (selected >= 0 && selected < 6) {
                    if (GameScr.mainChar.mount != null && GameScr.mainChar.mount[selected] != null) {
                        GameService.gI().removeMountEquip((byte) selected);
                    }
                }
                this.closeText();
                break;
            }
            case 61: {
                // Use mount food
                if (selected >= 6 && selected < vecMountEat.size() + 6) {
                    int foodIndex = selected - 6;
                    Item mountFood = (Item) vecMountEat.elementAt(foodIndex);
                    if (mountFood != null) {
                        GameService.gI().doMountEat(mountFood.ID, (byte) 1);
                    }
                }
                this.closeText();
                break;
            }
            case 62: {
                GameCanvas.gameScr.gameService
                        .kickOutParty(((PartyInfo) Char.party.elementAt((int) MainMenu.selected)).id, (byte) 0);
                break;
            }
            case 63: {
                GameCanvas.gameScr.gameService.kickOutParty(0, (byte) 1);
                break;
            }
            case 64: {
                GameCanvas.gameScr.gameService.kickOutParty(GameScr.mainChar.ID, (byte) 2);
                break;
            }
            case 71: {
                if (this.indexMainTab == 0) {
                    int aa71 = 0;
                    mVector mMenu = new mVector();
                    int i = 0;
                    while (i < this.mShop.size()) {
                        int ii = aa71++;
                        Item item = (Item) this.mShop.elementAt(i);
                        SetInfoData dt71 = new SetInfoData();
                        dt71.itemInven1 = item;
                        dt71.xx = ii;
                        mMenu.addElement(new mCommand("", (IActionListener) this, -2, dt71, dt71));
                        ++i;
                    }
                    this.setInfo(mMenu, "perform 71");
                } else {
                    this.doAction2();
                }
                if (!this.isSkill)
                    break;
                this.showInfoSkill("perform 71");
                break;
            }
            case 72: {
                break;
            }
            case 84: {
                break;
            }
            case 85: {
                break;
            }
            case 86: {
                break;
            }
            case 88: {
                break;
            }
            case 89: {
                break;
            }
            case 90: {
                break;
            }
            case 91: {
                break;
            }
            case 92: {
                SetInfoData dt92 = (SetInfoData) p;
                GameCanvas.startYesNoDlg(
                        "V\u1eadt ph\u1ea9m s\u1ebd m\u1ea5t khi b\u1ea1n b\u1ecf ra \u0111\u1ea5t. B\u1ea1n c\u00f3 mu\u1ed1n ti\u1ebfp t\u1ee5c kh\u00f4ng?",
                        new mCommand("", (IActionListener) this, 93, (Object) dt92));
                break;
            }
            case 93: {
                break;
            }
            case 102: {
                Item item102 = (Item) Char.inventory.elementAt(selected);
                if (item102 == null)
                    break;
                if (!this.isTextmua) {
                    String str;
                    this.textHoimua = str = String.valueOf(T.doyouwant) + " " + T.sell + " " + item102.name + " "
                            + T.withprice + " " + item102.priceShop + " " + Item.moneyType[item102.priceType];
                    this.vHanhTrang.removeAllElements();
                    MainMenu.cmdban.caption = T.yes;
                    this.vHanhTrang.addElement(cmdban);
                    this.showTextmua();
                    this.SortCmdItem();
                    break;
                }
                GameService.gI().sellItem((byte) 0, item102.ID);
                this.resetPopup();
                break;
            }
            case 103: {
                if (p != null) {
                    SetInfoData dt103 = (SetInfoData) p;
                    GameService.gI().sellItem((byte) 0, dt103.itemInven1.ID);
                    this.list.removeElementAt(dt103.index);
                }
                GameCanvas.endDlg();
                break;
            }
            case 104: {
                break;
            }
            case 106: {
                mVector menuItems = new mVector();
                int i = 0;
                while (i < 2) {
                    int ii = i;
                    String a = String.valueOf(ii) + ":" + (String) p;
                    menuItems.addElement(
                            new mCommand("Ph\u00edm s\u1ed1 " + (7 + 2 * i), (IActionListener) this, 107, (Object) a));
                    ++i;
                }
                GameCanvas.menu.startAt(menuItems, 2);
                break;
            }
            case 107: {
                inFo = (String) p;
                array = mSystem.getStringArray(inFo);
                Rms.saveQuickSlot();
                break;
            }
            case 108: {
                GameCanvas.endDlg();
                Item i108 = (Item) Char.inventory.elementAt(selected);
                if (i108 == null)
                    break;
                GameService.gI().sellItem((byte) 1, i108.ID);
                break;
            }
            case 109: {
                GameCanvas.endDlg();
                break;
            }
            case 110: {
                Char c = (Char) GameCanvas.gameScr.findCharByID(GameCanvas.gameScr.saveIDViewInfoAnimal);
                if (c != null) {
                    GameCanvas.gameScr.gameService.requestSomeOneInfo(c.ID, (byte) 1);
                } else {
                    this.right.performAction();
                }
                GameCanvas.endDlg();
                break;
            }
            case 113: {
                String str = GameCanvas.inputDlg.tfInput.getText();
                if (str.equals("")) {
                    GameCanvas.endDlg();
                    return;
                }
                try {
                    int value = Integer.parseInt(str);
                    if (value > Char.Diemtiemnang) {
                        GameCanvas.startOKDlg("B\u1ea1n ch\u1ec9 c\u00f2n " + Char.Diemtiemnang
                                + " \u0111i\u1ec3m ti\u1ec1m n\u0103ng");
                        return;
                    }
                    GameService.gI().SkillPoint(1, selected, value);
                    GameCanvas.endDlg();
                } catch (Exception e) {
                    GameCanvas.startOKDlg("C\u00f3 l\u1ed7i x\u1ea3y ra. Vui l\u00f2ng ch\u1ec9 nh\u1eadp s\u1ed1.");
                }
                break;
            }
            case 126: {
                if (!MainUnity.isLowGraphics) {
                    MainUnity.isLowGraphics = true;
                } else if (MainUnity.isLowGraphics) {
                    MainUnity.isLowGraphics = false;
                }
                GameCanvas.endDlg();
                break;
            }
            case 334: {
                if (Char.Skill_Point > 0) {
                    SkillTemplate skill = (SkillTemplate) GameScr.vec_skill.elementAt(selected);
                    if (skill == null)
                        break;
                    if (!this.isTextmua) {
                        String ttt;
                        this.textHoimua = ttt = String.valueOf(T.hoicong) + " " + T.point + " " + T.vao + " " + T.kinang
                                + " " + skill.name + " " + T.khong + " ?";
                        this.showTextmua();
                        MainMenu.cmdCongDiem.caption = T.yes;
                        this.vHanhTrang.removeElement(cmdGangphim);
                        this.SortCmdItem();
                        break;
                    }
                    GameService.gI().SkillPoint(0, selected, 1);
                    this.resetPopup();
                    break;
                }
                GameCanvas.addNotify(T.khongthecong, (byte) 0);
                break;
            }
            case 335: {
                GameService.gI().SkillPoint(0, selected, 1);
                GameCanvas.endDlg();
                break;
            }
            case 336: {
                int point = 0;
                try {
                    point = Integer.parseInt(GameCanvas.inputDlg.tfInput.getText());
                } catch (Exception e) {
                    point = 0;
                }
                SetInfoData s336 = (SetInfoData) p;
                if (point <= 0 || point > Char.Skill_Point) {
                    GameCanvas.addNotify(T.khongthecong, (byte) 0);
                    GameCanvas.endDlg();
                    return;
                }
                if (s336 != null && point > 0) {
                    Char.Skill_Point = (short) (Char.Skill_Point - point);
                    GameService.gI().SkillPoint(0, s336.index, point);
                }
                GameCanvas.endDlg();
            }
        }
    }

    private void doAction2() {
        if (!isChangeSubTab && selected > -1) {
            if (index[0] == 22 || index[0] == 23) {
                this.doPutItemShop();
            } else {
                this.doSelectedInventori();
            }
        }
    }

    public void closeShop() {
        this.doClose();
    }

    private void doClose() {
        mSound.playSound(26, mSound.volumeSound);
        selected = 0;
        this.miniItem = 0;
        this.itemPP = null;
        this.indexPet = 0;
        isBeginQuest = false;
        isInven = false;
        isDapdo = false;
        isHopda = false;
        isCheDo = false;
        isChuyenHoa = false;
        isPhiPhong = false;
        this.listNum = null;
        this.listNum = new int[6];
        vecMaterial.removeAllElements();
        vecItemCreate.removeAllElements();
        vecItemDapDo.removeAllElements();
        isShow = false;
        idicon = -1;
        if (this.isShowFill) {
            this.isShowFill = false;
            selected = 0;
            this.closeText();
            return;
        }
        this.isMiniShop = false;
        this.listTemp.removeAllElements();
        this.listEp = null;
        this.doBuyItem();
        this.isShowFill = false;
        this.focusTab = 0;
        if (this.lastSCR == GameCanvas.gameScr) {
            GameCanvas.gameScr.isStartAutoAttack = this.isRestartAutoFight;
            this.isRestartAutoFight = false;
        }
        this.lastSCR.switchToMe();
        this.list.removeAllElements();
        this.isSell = false;
        this.cmrItem.clear();
        this.cmrSubTab.clear();
        this.cmrShowText.clear();
        this.indexShowInfo = 0;
        this.cmrShowInfoMainChar.clear();
        isChangeSubTab = false;
        isFocusDetailMenu = false;
        this.isLoad = false;
        this.questInfo = null;
        this.isSkill = false;
        this.closeText();
        selected = -1;
        this.isShowInFoChar = false;
        this.indexMainTab = 0;
        currnentTabDetail = new String[] { "" };
        this.questInfo = null;
        this.infochar.removeAllElements();
        charWearing = null;
        this.isSell = false;
        this.indexMainTab = 0;
        this.indexShowInfo = 0;
        this.indexSubTab = 0;
    }

    public void questClan(String text1, byte finishQ) {
        this.questInfo = new mVector();
        String[] title = FontTeam.normalFont[0].splitFontBStrInLine(text1, 100);
        int i = 0;
        while (i < title.length) {
            this.questInfo.addElement(title[i]);
            ++i;
        }
        GameCanvas.endDlg();
    }

    public String getInfoQuestPaint(int type) {
        String info = "";
        if (type == 4) {
            try {
                Quest q = null;
                if (GameScr.allQuestCanReceive != null && indexTypeQuest == 0
                        && indexQuest < GameScr.allQuestCanReceive.size()) {
                    q = (Quest) GameScr.allQuestCanReceive.elementAt(indexQuest);
                } else if (GameScr.allQuestWorking != null && indexTypeQuest == 1
                        && indexQuest < GameScr.allQuestWorking.size()) {
                    q = (Quest) GameScr.allQuestWorking.elementAt(indexQuest);
                } else if (GameScr.allQuestFinish != null && indexTypeQuest == 2
                        && indexQuest < GameScr.allQuestFinish.size()) {
                    q = (Quest) GameScr.allQuestFinish.elementAt(indexQuest);
                }
                if (q != null) {
                    info = q.getInfoPaintScr();
                    info = info.replace('Â', 'â');
                    info = info.replace('Ô', 'ô');
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return info;
    }

    private void setQuestInfo() {
        int i;
        String[] data;
        this.questInfo = new mVector();
//        String infoMain = this.getInfoQuestPaint(0);
//        String infoSub = this.getInfoQuestPaint(1);
//        String infoClan = this.getInfoQuestPaint(2);
//        if (!infoMain.equals("")) {
//            data = Util.split(infoMain, "|");
//            i = 0;
//            while (i < data.length) {
//                this.questInfo.addElement(data[i]);
//                ++i;
//            }
//        }
//        if (!infoSub.equals("")) {
//            data = Util.split(infoSub, "|");
//            i = 0;
//            while (i < data.length) {
//                this.questInfo.addElement(data[i]);
//                ++i;
//            }
//        }
//        if (!infoClan.equals("")) {
//            data = Util.split(infoClan, "|");
//            i = 0;
//            while (i < data.length) {
//                this.questInfo.addElement(data[i]);
//                ++i;
//            }
//        }
    }

    public void setShowTextItemCharWearing() {
    }

    public static mVector setGroupOption(mVector infoTextShow) {
        mVector result = new mVector();
        mHashtable temp = new mHashtable();
        mVector vidcolor = new mVector();
        int i = 0;
        while (i < infoTextShow.size()) {
            InfoTextShow info = (InfoTextShow) infoTextShow.elementAt(i);
            mVector v = (mVector) temp.get(String.valueOf(info.color));
            if (v == null) {
                v = new mVector();
                temp.put(String.valueOf(info.color), v);
                vidcolor.addElement(String.valueOf(info.color));
            }
            v.addElement(info);
            ++i;
        }
        while (vidcolor.size() > 0) {
            i = 0;
            int idcomin = 100;
            int color = 100;
            while (i < vidcolor.size()) {
                int crId = Integer.parseInt((String) vidcolor.elementAt(i));
                if (color > crId) {
                    color = crId;
                    idcomin = i;
                }
                ++i;
            }
            if (idcomin == 100)
                continue;
            mVector v = (mVector) temp.get((String) vidcolor.elementAt(idcomin));
            if (v != null) {
                int j = 0;
                while (j < v.size()) {
                    result.addElement(v.elementAt(j));
                    ++j;
                }
            }
            vidcolor.removeElementAt(idcomin);
        }
        return result;
    }

    public static mVector setGroupFull(mVector infoTextShow) {
        mVector result = new mVector();
        result.addElement(infoTextShow.elementAt(0));
        mHashtable temp = new mHashtable();
        mVector vidcolor = new mVector();
        int i = 1;
        while (i < infoTextShow.size()) {
            InfoTextShow info = (InfoTextShow) infoTextShow.elementAt(i);
            mVector v = (mVector) temp.get(String.valueOf(info.color));
            if (v == null) {
                v = new mVector();
                temp.put(String.valueOf(info.color), v);
                vidcolor.addElement(String.valueOf(info.color));
            }
            v.addElement(info);
            ++i;
        }
        while (vidcolor.size() > 0) {
            i = 0;
            int idcomin = 100;
            int color = 100;
            while (i < vidcolor.size()) {
                int crId = Integer.parseInt((String) vidcolor.elementAt(i));
                if (color > crId) {
                    color = crId;
                    idcomin = i;
                }
                ++i;
            }
            if (idcomin == 100)
                continue;
            mVector v = (mVector) temp.get((String) vidcolor.elementAt(idcomin));
            if (v != null) {
                int j = 0;
                while (j < v.size()) {
                    result.addElement(v.elementAt(j));
                    ++j;
                }
            }
            vidcolor.removeElementAt(idcomin);
        }
        return result;
    }

    private void setShowText(mVector text, int x0, int y0, String[] arr, boolean isTile, boolean isSetGroup) {
        Item item;
        if (text == null && arr == null) {
            return;
        }
        if (!this.isQuest) {
            this.numItem = 0;
        }
        this.numItemStart = 0;
        this.numKhamNgoc = 0;
        this.idNgocKham = null;
        this.laststar = 0;
        this.speedStart = 0;
        this.runStart = false;
        this.isHalfstart = false;
        this.numItemStart2 = 0;
        this.laststar2 = 0;
        this.speedStart2 = 0;
        this.runStart2 = false;
        this.isHalfstart2 = false;
        this.closeText();
        this.isShowText = true;
        if (!GameCanvas.isTouch) {
            this.xShowText = x0 + this.size / 2;
            this.yShowText = y0 + this.size / 2;
        } else {
            this.yShowText = this.y + this.sizeH;
            this.hShowText = this.h - this.sizeH;
        }
        if (!GameCanvas.isTouch) {
            this.wShowText = 6 * this.size;
            if (this.indexMainTab == 3) {
                if (this.isCharWearing || this.isAnimal) {
                    this.wShowText = 3 * this.size;
                } else if (this.isSkill) {
                    this.wShowText = 4 * this.size;
                }
            } else if (this.indexMainTab == 2 || this.indexMainTab == 8 || this.indexMainTab == 9
                    || this.indexMainTab == 10 || this.indexMainTab == 11) {
                this.wShowText = 3 * this.size;
            }
            this.wShowText += 8;
        }
        this.totalInfoshow = 0;
        if (arr == null) {
            String[] data = null;
            int w0 = 0;
            this.showText = isSetGroup ? MainMenu.setGroupFull(text) : text;
            try {
                int i = 0;
                while (i < this.showText.size()) {
                    InfoTextShow info = (InfoTextShow) text.elementAt(i);
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
                        this.totalInfoshow = (byte) (this.totalInfoshow + info.info.length);
                        int ww = info.getMaxWidth();
                        w0 = w0 > ww ? w0 : ww;
                    } else {
                        this.totalInfoshow = (byte) (this.totalInfoshow + 1);
                    }
                    ++i;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (!GameCanvas.isTouch) {
                this.wShowText = w0 + 20;
            }
        }
        boolean isitemPhiPhong = false;
        if (!GameCanvas.isTouch) {
            this.hShowText = this.totalInfoshow * this.disString + 6;
            if (this.hShowText > 140) {
                this.hShowText = 140;
            }
            if (this.xShowText + this.wShowText > GameCanvas.w) {
                int ww = this.xShowText + this.wShowText - GameCanvas.w;
                this.xShowText -= ww + 4;
            }
            if (this.xShowText < 4) {
                this.xShowText = 4;
            }
            if (this.yShowText + this.hShowText > GameCanvas.h - hTab) {
                int hh = this.yShowText + this.hShowText - (GameCanvas.h - hTab);
                this.yShowText -= hh;
            }
            if (this.yShowText < 4) {
                this.yShowText = 4;
            }
            if (this.indexMainTab == 3 && (this.isCharWearing || this.isAnimal)) {
                this.yShowText = this.yInfoWearing + 2;
                this.xShowText = this.xInfoWearing;
                this.hShowText = this.hInfoWearing - 4 + (this.isAnimal ? this.size : 0);
            }
            this.hShowText += 20;
            if (this.hShowText < this.totalInfoshow * this.disString + 16) {
                this.isUseCmr = true;
                this.isShowFill = true;
            }
        }
        if (this.indexMainTab == 0 && (item = (Item) this.mShop.elementAt(selected)) != null) {
            this.numItemStart = item.plus;
            this.numKhamNgoc = item.numKhamNgoc;
            this.idNgocKham = item.idNgocKham;
            if (item.getTypeItem() == 55) {
                isitemPhiPhong = true;
            }
        }
        if (this.indexMainTab == 8) {
            item = (Item) vecItemDapDo.elementAt(this.slDapdo1);
            if (item != null) {
                this.numItemStart = item.plus;
                this.numKhamNgoc = item.numKhamNgoc;
                this.idNgocKham = item.idNgocKham;
                if (item.getTypeItem() == 55) {
                    isitemPhiPhong = true;
                }
            }
        } else if (this.indexMainTab == 1) {
            item = (Item) Char.inventory.elementAt(selected);
            if (item != null) {
                this.numItemStart = item.plus;
                this.numKhamNgoc = item.numKhamNgoc;
                this.idNgocKham = item.idNgocKham;
                if (item.getTypeItem() == 55) {
                    isitemPhiPhong = true;
                }
            }
        } else if (this.indexMainTab == 2) {
            if (selected > -1 && selected < MainMenu.charWearing.equip.length) {
                if (MainMenu.charWearing.equip[selected + this.indexWearing] != null) {
                    this.numItemStart = MainMenu.charWearing.equip[MainMenu.selected + this.indexWearing].plus;
                    this.numKhamNgoc = MainMenu.charWearing.equip[MainMenu.selected + this.indexWearing].numKhamNgoc;
                    this.idNgocKham = MainMenu.charWearing.equip[MainMenu.selected + this.indexWearing].idNgocKham;
                }
                if (MainMenu.charWearing.equip[selected + this.indexWearing] != null
                        && MainMenu.charWearing.equip[selected + this.indexWearing].getTypeItem() == 55) {
                    isitemPhiPhong = true;
                }
            }
        } else if (this.indexMainTab == 4) {
            // Mount tab - handle mount equipment (0-5) and mount food (6+)
            if (selected >= 0 && selected < 6) {
                // Mount equipment slots
                if (GameScr.mainChar.mount != null && GameScr.mainChar.mount[selected] != null) {
                    this.numItemStart = GameScr.mainChar.mount[selected].plus;
                    this.numKhamNgoc = GameScr.mainChar.mount[selected].numKhamNgoc;
                    this.idNgocKham = GameScr.mainChar.mount[selected].idNgocKham;
                    if (GameScr.mainChar.mount[selected].getTypeItem() == 55) {
                        isitemPhiPhong = true;
                    }
                }
            } else if (vecMountEat != null && selected >= 6 && selected < vecMountEat.size() + 6) {
                // Mount food items
                int foodIndex = selected - 6;
                Item mountFood = (Item) vecMountEat.elementAt(foodIndex);
                if (mountFood != null) {
                    this.numItemStart = mountFood.plus;
                    this.numKhamNgoc = mountFood.numKhamNgoc;
                    this.idNgocKham = mountFood.idNgocKham;
                    if (mountFood.getTypeItem() == 55) {
                        isitemPhiPhong = true;
                    }
                }
            }
        } else if (this.indexMainTab == 10) {
            item = (Item) vecItemCreate.elementAt(this.slDapdo1);
            if (item != null) {
                this.numItemStart = item.plus;
                this.numKhamNgoc = item.numKhamNgoc;
                this.idNgocKham = item.idNgocKham;
            }
            if (this.tickChuyenhoa == 0 && itemChuyenHoa0 != null) {
                this.numItemStart = MainMenu.itemChuyenHoa0.plus;
                this.numKhamNgoc = MainMenu.itemChuyenHoa0.numKhamNgoc;
                this.idNgocKham = MainMenu.itemChuyenHoa0.idNgocKham;
            }
            if (this.tickChuyenhoa == 1 && itemChuyenHoa1 != null) {
                this.numItemStart = MainMenu.itemChuyenHoa1.plus;
                this.numKhamNgoc = MainMenu.itemChuyenHoa1.numKhamNgoc;
                this.idNgocKham = MainMenu.itemChuyenHoa1.idNgocKham;
            }
            if (this.tickChuyenhoa == 2 && itemChuyenHoa2 != null) {
                this.numItemStart = MainMenu.itemChuyenHoa2.plus;
                this.numKhamNgoc = MainMenu.itemChuyenHoa2.numKhamNgoc;
                this.idNgocKham = MainMenu.itemChuyenHoa2.idNgocKham;
            }
        } else if (this.indexMainTab == 11) {
            Item sItem = (Item) vecItemCreate.elementAt(this.slDapdo1);
            if (sItem != null) {
                this.numItemStart = sItem.plus;
                this.numKhamNgoc = sItem.numKhamNgoc;
                this.idNgocKham = sItem.idNgocKham;
                if (sItem.getTypeItem() == 55) {
                    isitemPhiPhong = true;
                }
            }
            if (this.isTouchCenter) {
                if (this.itemPP != null) {
                    this.numItemStart = this.itemPP.plus;
                    this.numKhamNgoc = this.itemPP.numKhamNgoc;
                    this.idNgocKham = this.itemPP.idNgocKham;
                    if (this.itemPP.getTypeItem() == 55) {
                        isitemPhiPhong = true;
                    }
                }
                this.isTouchCenter = false;
            }
        }
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
        this.cmrShowText.clear();
        this.isShowText = false;
        this.isShowFill = false;
        if (!GameCanvas.isTouch) {
            this.hShowText = 0;
            this.wShowText = 0;
            this.xShowText = this.xbg;
        }
        this.isUseCmr = false;
    }

    public void closeText_invent() {
        if (this.indexMainTab == 1) {
            this.cmrShowText.clear();
            this.isShowText = false;
            if (!GameCanvas.isTouch) {
                this.hShowText = 0;
                this.wShowText = 0;
                this.xShowText = this.xbg;
            }
            this.isUseCmr = false;
        }
    }

    public void setImageCharWear() {
    }

    private void doSelectedSkill() {
        SkillTemplate sk;
        if (!GameCanvas.isTouch && (sk = (SkillTemplate) GameScr.vec_skill.elementAt(selected)) != null
                && Char.levelSkill[selected] >= 0) {
            mVector list1 = new mVector();
            if (Char.Skill_Point > 0) {
                list1.addElement(cmdCongDiem);
            }
            if (sk.type != SkillTemplate.TYPE_PASSIVE) {
                list1.addElement(cmdGangphim);
            }
            GameCanvas.menu.startAt(list1, 0);
        }
    }

    private void dofireBasePoint() {
        if (Char.Diemtiemnang == 0) {
            GameCanvas.addNotify(T.khongthecong, (byte) 0);
            GameCanvas.endDlg();
            return;
        }
        GameCanvas.inputDlg.setInfo("Nh\u1eadp s\u1ed1", new mCommand("", this, 113), 1, 10, true);
        GameCanvas.inputDlg.show();
    }

    public void movecmrQuest() {
        if (ListQuest != null && indexTypeQuest >= 0 && indexTypeQuest < ListQuest.length) {
            int mindex = -1;
            int i = 0;
            while (i < ListQuest[indexTypeQuest].size()) {
                QuestInfo q = (QuestInfo) ListQuest[indexTypeQuest].elementAt(i);
                if (q != null && q.status != 0) {
                    indexQuest = i;
                    selected = i;
                    this.isMoveQuest = true;
                    mindex = i;
                    break;
                }
                ++i;
            }
            if (mindex == -1) {
                indexQuest = 0;
                selected = 0;
                this.isMoveQuest = true;
            }
            if (GameCanvas.isTouch) {
                this.showinfoQuest();
            }
        }
    }

    public void showinfoQuest() {
        if (ListQuest != null && indexTypeQuest > 0 && indexTypeQuest < ListQuest.length) {
            mVector currQuest = ListQuest[indexTypeQuest];
            QuestInfo q = (QuestInfo) currQuest.elementAt(selected);
            mVector minfo = new mVector();
            if (q != null) {
                InfoTextShow in = new InfoTextShow(new String[] { q.name }, 0);
                minfo.addElement(in);
                String[] data = Util.split(q.info, "\n");
                try {
                    int i = 0;
                    while (i < data.length) {
                        byte color = (byte) (data[i].charAt(0) - 48);
                        if (!MainMenu.isDegit(data[i].charAt(0))) {
                            color = 0;
                        } else {
                            data[i] = data[i].substring(1);
                        }
                        if (data[i].length() > 5) {
                            in = new InfoTextShow(new String[] { data[i] }, color);
                            minfo.addElement(in);
                        }
                        ++i;
                    }
                } catch (Exception exception) {
                    // empty catch block
                }
            }
            this.setShowText(minfo, this.xShowText, this.yShowText, null, true, false);
        }
    }

    public void movecmrQuest1() {
        if (ListQuest != null && indexTypeQuest >= 0 && indexTypeQuest < ListQuest.length) {
            int mindex = -1;
            int j = 0;
            while (j < ListQuest.length) {
                int i = 0;
                while (i < ListQuest[j].size()) {
                    QuestInfo q = (QuestInfo) ListQuest[j].elementAt(i);
                    if (q != null && q.status != 0) {
                        indexQuest = i;
                        selected = i;
                        this.isMoveQuest = true;
                        indexTypeQuest = j;
                        mindex = i;
                        return;
                    }
                    ++i;
                }
                ++j;
            }
            if (mindex == -1) {
                indexQuest = 0;
                selected = 0;
                this.isMoveQuest = true;
            }
            if (GameCanvas.isTouch) {
                this.showinfoQuest();
            }
        }
    }

    public void doSelectMainTabNotTouch() {
        if (this.indexMainTab == 3) {
            this.isCharWearing = false;
            this.isHanhTrang = false;
            this.indexShowInfo = 0;
            this.setTile();
            this.isSkill = true;
            this.setPosWearing(true);
            isFocusDetailMenu = false;
            if (!GameCanvas.isTouch) {
                this.left = new mCommand(T.view, this, -1);
            }
            this.cmdShowText = new mCommand("", this, -1);
            this.timeAuToShowText = 15;
            this.beGinShowText = false;
            this.isShowFill = false;
            charWearing = GameScr.mainChar;
            this.setImageCharWear();
            selected = -1;
        }
//        else if (this.indexMainTab == 4) {
//            this.isSkill = false;
//            this.numItem = 4;
//            this.isSell = false;
//            this.setTile();
//            this.setQuestInfo();
//            this.questInfo.removeAllElements();
//            if (!GameCanvas.isTouch) {
//                this.left = new mCommand(T.info, this, -3);
//            }
//            this.center = new mCommand(GameCanvas.isTouch ? "" : T.tuychon, (IActionListener) this, 0, xCCmd, yCCmd);
//            this.setImageCharWear();
//            this.movecmrQuest();
//        }
    }

    private void doCmdCenter() {
        Cout.println(String.valueOf(selected) + " doCmdCenter " + this.indexMainTab);
        boolean isResetSelect = false;
        if (!isFocusDetailMenu) {
            return;
        }
        block0: switch (this.indexMainTab) {
            case 5: {
                mCommand cmd;
                if (selected < 0 || selected >= this.inFoNap.size()
                        || (cmd = (mCommand) this.inFoNap.elementAt(selected)) == null)
                    break;
                cmd.performAction();
                break;
            }
            case 3: {
                this.isCharWearing = false;
                this.isHanhTrang = false;
                this.indexShowInfo = 0;
                if (this.isQuest) {
                    cmdMapScr.performAction();
                }
                if (this.isPaintSub()) {
                    if (this.isSkill) {
                        this.doSelectedSkill();
                        break;
                    }
                    if (!this.isFeatures)
                        break;
                    this.dofireBasePoint();
                    break;
                }
                this.setTile();
                switch (selected) {
                    case 0: {
                        this.isSkill = true;
                        this.setPosWearing(true);
                        this.left = new mCommand("", this, -1);
                        this.cmdShowText = new mCommand("", this, -1);
                        this.timeAuToShowText = 15;
                        this.beGinShowText = false;
                        charWearing = GameScr.mainChar;
                        this.setImageCharWear();
                        selected = 0;
                        if (!GameCanvas.isTouch)
                            break;
                        selected = -1;
                        this.center.resetCmd();
                        this.dosetIDCmdTouch("", 0);
                        break;
                    }
                    case 2: {
                        this.isFeatures = true;
                        selected = 0;
                        this.setImageCharWear();
                        if (!GameCanvas.isTouch)
                            break;
                        selected = -1;
                        this.center.resetCmd();
                        this.dosetIDCmdTouch("", 0);
                        break;
                    }
                    case 1: {
                        this.numItem = 4;
                        this.isQuest = true;
                        this.isSell = false;
                        this.setQuestInfo();
                        this.questInfo.removeAllElements();
                        if (!GameCanvas.isTouch) {
                            this.left = new mCommand(T.info, this, -3);
                        }
                        this.center = new mCommand(GameCanvas.isTouch ? "" : T.tuychon, (IActionListener) this, 0,
                                xCCmd, yCCmd);
                        this.setImageCharWear();
                        this.movecmrQuest();
                    }
                }
                break;
            }
            case 7: {
                switch (selected) {
                    case 0: {
                        break block0;
                    }
                    case 1: {
                        break block0;
                    }
                    case 2: {
                        break block0;
                    }
                    case 3: {
                        break block0;
                    }
                    case 4: {
                        break block0;
                    }
                    case 5: {
                        break block0;
                    }
                    case 6: {
                        break block0;
                    }
                    case 7: {
                        break block0;
                    }
                }
                break;
            }
            case 4: {
                break;
            }
            case 6: {
                if (bangHoi.length == 2) {
                    switch (selected) {
                        case 0: {
                            break block0;
                        }
                    }
                    break;
                }
                switch (selected) {
                    case 0: {
                        break block0;
                    }
                    case 1: {
                        break block0;
                    }
                    case 2: {
                        break block0;
                    }
                    case 3: {
                        break block0;
                    }
                    case 4: {
                        break block0;
                    }
                    case 5: {
                        break block0;
                    }
                    case 6: {
                        break block0;
                    }
                    case 7: {
                        break block0;
                    }
                }
                break;
            }
            case 2:
            case 8:
            case 9:
            case 10:
            case 11: {
                mVector menu;
                Cout.println(" doCmdCenter trangbi");
                this.isSell = false;
                this.setPosWearing(false);
                this.setTextCharInfo();
                this.numItemStart = 0;
                this.numKhamNgoc = 0;
                this.idNgocKham = null;
                this.isShowInFoChar = true;
                mVector menuItem = menu = new mVector();
                if (menu.size() == 1) {
                    this.left = (mCommand) menu.elementAt(0);
                } else if (menu.size() == 2) {
                    this.left = new mCommand("Ch\u1ecdn", this, 9, menuItem, xLCmd, yLCmd);
                }
                this.center = new mCommand("", (IActionListener) this, -3, xCCmd, yCCmd);
                charWearing = GameScr.mainChar;
                this.setImageCharWear();
            }
        }
        if (!(this.isSkill || this.isFeatures || this.indexMainTab == 5 || this.isSkillClan || isResetSelect)) {
            selected = 0;
            if (this.isHanhTrang) {
                selected = -1;
            }
        }
    }

    public void RestItemHanhTrang() {
        this.setSelectTab("RestItemHanhTrang");
        GameCanvas.endDlg();
    }

    public void SortCmdItem() {
        int j = 0;
        while (j < this.vHanhTrang.size()) {
            mCommand cmd = (mCommand) this.vHanhTrang.elementAt(j);
            if (cmd != null) {
                cmd.x = this.xShowText + this.wShowText - 81 - j * 81 + 2;
                cmd.y = this.y + this.hShowText + 36;
            }
            ++j;
        }
    }

    private void paintListQuest(mGraphics g, int x, int y, int ww, boolean isClan) {
        if (ListQuest != null && indexTypeQuest >= 0 && indexTypeQuest < ListQuest.length) {
            mVector currQuest = ListQuest[indexTypeQuest];
            this.totalLineQuest = currQuest.size();
            this.cmrItem.setStyle(this.totalLineQuest + 1, this.sizeH, x + 3, (y += this.size + 4) + 10,
                    this.colum * this.size - 3, this.h - this.sizeH - this.size - 20, true, 1);
            g.ClipRec(x + 3 + 1, y + 10 + 1, this.colum * this.size - 3 - 2, this.h - this.sizeH - this.size - 20 - 2);
            this.cmrItem.setClip(g);
            int i = 0;
            while (i < currQuest.size()) {
                QuestInfo q = (QuestInfo) currQuest.elementAt(i);
                if (i == indexQuest) {
                    Res.paintFocus(g, x + 8, y + 5 + 8 + i * this.sizeH + 1, this.size * this.colum - 8);
                }
                mFont f = mFont.tahoma_7b_white;
                if (q.status == 0) {
                    f = mFont.tahoma_7b_brown;
                }
                String[] n = f.splitFontArray(q.name, this.size * this.colum - 10);
                int ys = 0;
                if (n.length > 1) {
                    ys = -10;
                }
                int j = 0;
                while (j < n.length) {
                    f.drawString(g, n[j], x + this.size * this.colum / 2, y + 12 + i * this.sizeH + 15 + j * 20 + ys, 2,
                            true);
                    ++j;
                }
                g.setColor(-3377408);
                if (i < currQuest.size() - 1) {
                    g.fillRect(x + 8, y + 5 + (i + 1) * this.sizeH + 8, this.size * this.colum - 8, 1, true);
                }
                if ((q.status == 1 || q.status == 2 || q.status == 4) && GameCanvas.gameTick / 4 % 4 == 0) {
                    String[] nr = mFont.tahoma_7b_red.splitFontArray(q.name, this.size * this.colum - 10);
                    int ysr = 0;
                    if (nr.length > 1) {
                        ysr = -10;
                    }
                    int j2 = 0;
                    while (j2 < n.length) {
                        mFont.tahoma_7b_orange.drawString(g, nr[j2], x + this.size * this.colum / 2,
                                y + 12 + i * this.sizeH + 15 + j2 * 20 + ysr, 2, true);
                        ++j2;
                    }
                }
                ++i;
            }
            mGraphics.resetTransAndroid(g);
            g.restoreCanvas();
            GameCanvas.resetTrans(g);
            this.paintBgSub(g, this.cmrItem.xPos - 4, this.cmrItem.yPos - 10, this.cmrItem.width + 10,
                    this.cmrItem.height + 10, false);
        }
    }

    public void showInfoSkill(String pos) {
        int xShowTextt = 0;
        int yShowTextt = 0;
        this.indexPaintLineSkill = -1;
        mVector minfo = new mVector();
        try {
            if (selected >= 0 && selected < GameScr.vec_skill.size()) {
                int i;
                SkillTemplate sk = (SkillTemplate) GameScr.vec_skill.elementAt(selected);
                byte lvCharhientai = Char.levelSkill[selected];
                minfo.addElement(new InfoTextShow(
                        new String[] { String.valueOf(sk.name) + (lvCharhientai > 0 ? " lv " + lvCharhientai : "") },
                        4));
                if (sk.decription != null && !sk.decription.equals("")) {
                    minfo.addElement(new InfoTextShow(new String[] { sk.decription }, 0));
                }
                minfo.addElement(
                        new InfoTextShow(new String[] { String.valueOf(T.type) + ": " + T.typeSkill[sk.type] }, 0));
                int n = selected > 0 ? 5 + (sk.type == SkillTemplate.TYPE_BUFF ? 1 : 0)
                        : (this.indexPaintLineSkill = 3);
                if (lvCharhientai > 0) {
                    if (sk.nTarget != null) {
                        minfo.addElement(new InfoTextShow(
                                new String[] { String.valueOf(T.somuctieu) + ": " + sk.nTarget[lvCharhientai - 1] },
                                0));
                    }
                    if (selected > 0) {
                        minfo.addElement(new InfoTextShow(
                                new String[] { String.valueOf(T.LvYeuCau) + ": " + sk.lvRequire[lvCharhientai - 1] },
                                0));
                        if (sk.type != SkillTemplate.TYPE_PASSIVE) {
                            minfo.addElement(new InfoTextShow(
                                    new String[] {
                                            String.valueOf(T.Tieuhaonoiluc) + ": " + sk.getmplose(lvCharhientai - 1) },
                                    0));
                        }
                    }
                    if (sk.type != SkillTemplate.TYPE_PASSIVE) {
                        minfo.addElement(new InfoTextShow(new String[] { String.valueOf(T.Thoigianhoi) + ": "
                                + Util.convertMilis2S(sk.getCoolDown(lvCharhientai - 1)) + "s" }, 0));
                    }
                    if (sk.type == SkillTemplate.TYPE_BUFF) {
                        minfo.addElement(new InfoTextShow(new String[] { String.valueOf(T.Thoigianhotro) + ": "
                                + Util.convertMilis2S(sk.timeLive[lvCharhientai - 1]) + "s" }, 0));
                    }
                }
                xShowTextt = this.xWearing[selected] - this.size / 2 + this.size;
                yShowTextt = this.yWearing[selected] + this.size;
                if (lvCharhientai < 0) {
                    minfo.addElement(
                            new InfoTextShow(new String[] { String.valueOf(T.LvYeuCau) + ": " + sk.lvRequire[0] }, 0));
                    minfo.addElement(new InfoTextShow(new String[] { T.chuamo }, 0));
                    ++this.indexPaintLineSkill;
                } else if (lvCharhientai == 0) {
                    minfo.addElement(
                            new InfoTextShow(new String[] { String.valueOf(T.LvYeuCau) + ": " + sk.lvRequire[0] }, 0));
                    minfo.addElement(new InfoTextShow(new String[] { T.chuahoc }, 0));
                    ++this.indexPaintLineSkill;
                } else {
                    mVector op = sk.getInfoOptions(lvCharhientai - 1);
                    i = 0;
                    while (i < op.size()) {
                        minfo.addElement(op.elementAt(i));
                        ++i;
                    }
                }
                minfo.addElement(new InfoTextShow(null, 0));
                if (Char.levelSkill[selected] < 10 && selected != 0 && lvCharhientai >= 0) {
                    minfo.addElement(new InfoTextShow(new String[] { "C\u1ea5p k\u1ebf: " + (lvCharhientai + 1) },
                            GameScr.mainChar.level >= sk.lvRequire[lvCharhientai] ? 4 : 6));
                    minfo.addElement(new InfoTextShow(
                            new String[] { String.valueOf(T.LvYeuCau) + ": " + sk.lvRequire[lvCharhientai] }, 0));
                    if (sk.nTarget != null) {
                        minfo.addElement(new InfoTextShow(
                                new String[] { String.valueOf(T.somuctieu) + ": " + sk.nTarget[lvCharhientai] }, 0));
                    }
                    if (sk.type != SkillTemplate.TYPE_PASSIVE) {
                        minfo.addElement(new InfoTextShow(
                                new String[] { String.valueOf(T.Tieuhaonoiluc) + ": " + sk.getmplose(lvCharhientai) },
                                0));
                    }
                    if (sk.type != SkillTemplate.TYPE_PASSIVE) {
                        minfo.addElement(new InfoTextShow(new String[] { String.valueOf(T.Thoigianhoi) + ": "
                                + Util.convertMilis2S(sk.coolDown[lvCharhientai]) + "s" }, 0));
                    }
                    if (sk.type == SkillTemplate.TYPE_BUFF) {
                        minfo.addElement(new InfoTextShow(new String[] { String.valueOf(T.Thoigianhotro) + ": "
                                + Util.convertMilis2S(sk.timeLive[lvCharhientai]) + "s" }, 0));
                    }
                    mVector info_next = sk.getInfoOptions(lvCharhientai);
                    i = 0;
                    while (i < info_next.size()) {
                        minfo.addElement(info_next.elementAt(i));
                        ++i;
                    }
                } else {
                    this.indexPaintLineSkill = -1;
                }
                this.vHanhTrang.removeAllElements();
                if (GameCanvas.isTouch && GameCanvas.isTouch) {
                    if (Char.Skill_Point > 0 && selected >= 0 && Char.levelSkill[selected] >= 0
                            && Char.levelSkill[selected] < 10 && selected > 0) {
                        this.vHanhTrang.addElement(cmdCongDiem);
                    }
                    if (Char.levelSkill[selected] > 0 && sk.type != SkillTemplate.TYPE_PASSIVE) {
                        this.vHanhTrang.addElement(cmdGangphim);
                    }
                    this.SortCmdItem();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.setShowText(minfo, xShowTextt, yShowTextt, null, true, false);
    }

    @Override
    public boolean isScrMainMenu() {
        return true;
    }

    public void showinfonext() {
        if (selected < 0) {
            return;
        }
        if (Char.inventory.size() > 0) {
            int x0 = this.x + this.size / 2 + selected % this.colum * this.size + 16;
            int y0 = 11 + this.y + selected / this.colum * this.size + this.size * 3 / 2 + this.size - 1;
            Item it = (Item) Char.inventory.elementAt(selected);
            int type = it.getTypeItem();
            Item itcp = null;
            this.isgangphim = false;
            if (POS_ITEM_IN_EQUIP[type] > -1) {
                itcp = GameScr.mainChar.equip[POS_ITEM_IN_EQUIP[type]];
            }
            this.showItemInventoryInfo(it, this.isSell, x0, y0, itcp);
            if (GameCanvas.isTouch) {
                mVector m = this.getInventori();
                mCommand cm = (mCommand) m.elementAt(0);
                SetInfoData dt79 = (SetInfoData) cm.p;
                Item it1 = (Item) Char.inventory.elementAt(selected);
                this.vHanhTrang.removeAllElements();
                cmdsudung = new mCommand(T.sudung, (IActionListener) this, -3, (Object) it1);
                cmdvut = new mCommand(T.vut, (IActionListener) this, 108, (Object) it1);
                cmdban = new mCommand(T.ban, (IActionListener) this, 102, (Object) dt79);
                this.vHanhTrang.addElement(cmdban);
                this.vHanhTrang.addElement(cmdsudung);
                this.vHanhTrang.addElement(cmdvut);
                if (it1.type != Item.TYPE_MP && it1.type != Item.TYPE_HP && it1.catagory != 4) {
                    this.vHanhTrang.addElement(new mCommand(T.bannhieu, (IActionListener) this, 25, null));
                } else {
                    this.isgangphim = true;
                    this.vHanhTrang.addElement(cmdGangphim);
                }
                this.SortCmdItem();
            }
        }
    }

    @Override
    public void doChangeInfo(boolean isMeTouch) {
        if (!isMeTouch) {
            if (this.isSkill) {
                this.showInfoSkill("doChangeInfo");
            } else if (!this.isCharWearing && this.indexMainTab == 2) {
                isFocusCharWearing = true;
                this.setTextCharInfo();
                this.showMaincharInfo();
            }
        } else if (this.indexMainTab == 2) {
            isFocusCharWearing = true;
            this.showMaincharInfo();
        } else if (this.indexMainTab == 1) {
            if (Char.inventory.size() > 0) {
                if (selected == -1 || selected > Char.inventory.size() - 1) {
                    selected = 0;
                }
                int x0 = this.x + this.size / 2 + selected % this.colum * this.size + 16;
                int y0 = 11 + this.y + selected / this.colum * this.size + this.size * 3 / 2 + this.size - 1;
                Item it = (Item) Char.inventory.elementAt(selected);
                int type = it.getTypeItem();
                Item itcp = null;
                this.isgangphim = false;
                if (POS_ITEM_IN_EQUIP[type] > -1) {
                    itcp = GameScr.mainChar.equip[POS_ITEM_IN_EQUIP[type]];
                }
                this.showItemInventoryInfo(it, this.isSell, x0, y0, itcp);
                if (GameCanvas.isTouch) {
                    mVector m = this.getInventori();
                    mCommand cm = (mCommand) m.elementAt(0);
                    SetInfoData dt79 = (SetInfoData) cm.p;
                    this.vHanhTrang.removeAllElements();
                    cmdban = new mCommand(T.ban, (IActionListener) this, 102, (Object) dt79);
                    cmdsudung = new mCommand(T.sudung, (IActionListener) this, -3, (Object) dt79.itemInven1);
                    cmdvut = new mCommand(T.vut, (IActionListener) this, 108, (Object) dt79.itemInven1);
                    this.vHanhTrang.addElement(cmdban);
                    this.vHanhTrang.addElement(cmdsudung);
                    this.vHanhTrang.addElement(cmdvut);
                    if (dt79.itemType != Item.TYPE_MP && dt79.itemType != Item.TYPE_HP) {
                        this.vHanhTrang.addElement(cmdbanNhieu);
                    } else {
                        this.isgangphim = true;
                        this.vHanhTrang.addElement(cmdGangphim);
                    }
                    this.SortCmdItem();
                }
            }
        } else if (this.indexMainTab == 0) {
            if (this.mShop.size() > 0) {
                selected = 0;
                int x0 = this.x + this.size / 2 + selected % this.colum * this.size + 16;
                int y0 = 11 + this.y + selected / this.colum * this.size + this.size * 3 / 2 + this.size - 1;
                Item sItem = (Item) this.mShop.elementAt(selected);
                if (sItem != null) {
                    this.showItemInventoryInfo(sItem, this.isSell, x0, y0, null);
                    this.vHanhTrang.removeAllElements();
                    this.vHanhTrang.addElement(cmdBuy);
                    mCommand cmdD = (mCommand) this.vHanhTrang.elementAt(0);
                    cmdD.caption = !captionServer.equals("") ? captionServer : T.Buy;
                    this.canbuy = false;
                    this.SortCmdItem();
                    Cout.Debug("CMD BUY 333 ");
                }
            }
        } else if (this.isSkill) {
            selected = 0;
            this.showInfoSkill("doChangeInfo 1");
        }
    }

    public void addPotiontoQL() {
    }

    public void Upgraderesult(int type) {
        ID_CUONG_HOA = (short) -1;
        if (type == 0) {
            effDapDo = new DataSkillEff(50, xposItem, ypostItem);
        }
        if (type == 1) {
            effDapDo = new DataSkillEff(236, xposItem, ypostItem);
        }
        this.numStone = 0;
        itemBaohiem = null;
        itemBua = null;
        itemStone = null;
        textPercent = "";
        xuCuongHoa = 0;
    }

    public void CreateItemresult(int type, int midicon) {
        typeresult = type;
        idicon = midicon;
        this.Waitcreate = true;
        xuCuongHoa = 0;
        int i = 0;
        while (i < 6) {
            WeaponsLazer wp = new WeaponsLazer(Util.cos(i * 60 + this.goc) * this.r / 1024 + this.xcenter,
                    Util.sin(i * 60 + this.goc) * this.r / 1024 + this.ycenter, this.xcenter, this.ycenter, 25, 1,
                    this.sizeEff[i]);
            this.vEffect.addElement(wp);
            ++i;
        }
        EffectEnd ef = new EffectEnd(0, this.xcenter, this.ycenter);
        this.vEffect.addElement(ef);
    }

    public void ChuyenHoaItemresult() {
        this.Waitcreate = true;
        WeaponsLazer wp = new WeaponsLazer(this.xcenter - 42 + 10, this.ycenter + 30, this.xcenter - 1,
                this.ycenter - 30 + 10, 25, 1, 3);
        this.vEffect.addElement(wp);
        if (itemChuyenHoa2 != null) {
            WeaponsLazer wp2 = new WeaponsLazer(this.xcenter + 42 - 10, this.ycenter + 30, this.xcenter + 1,
                    this.ycenter - 30 + 10, 25, 1, 3);
            this.vEffect.addElement(wp2);
        }
        EffectEnd ef = new EffectEnd(0, this.xcenter, this.ycenter - 25 + 10);
        this.vEffect.addElement(ef);
    }

    public void setStart() {
        if (mItem != null) {
            this.numItemStart = MainMenu.mItem.plus;
            if (this.numItemStart == 15) {
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
            this.numKhamNgoc = MainMenu.mItem.numKhamNgoc;
            this.idNgocKham = MainMenu.mItem.idNgocKham;
        }
    }

    public void doSendCuongHoa(int type) {
        mVector vecItem = new mVector();
        if (mItem != null) {
            vecItem.addElement(mItem);
        }
        if (itemStone != null) {
            vecItem.addElement(itemStone);
        }
        if (itemBaohiem != null) {
            vecItem.addElement(itemBaohiem);
        }
        if (itemBua != null) {
            vecItem.addElement(itemBua);
        }
        GameService.gI().doUpgradeItem((byte) type, (byte) this.numStone, vecItem);
    }

    public boolean checkinVector(mVector vec, Item ite) {
        int i = 0;
        while (i < vec.size()) {
            Item mit = (Item) vec.elementAt(i);
            if (mit != null && mit.idIcon == ite.idIcon) {
                return true;
            }
            ++i;
        }
        return false;
    }

    public boolean checkinVectorPhiPhong(mVector vec, Item ite) {
        int i = 0;
        while (i < vec.size()) {
            Item mit = (Item) vec.elementAt(i);
            if (mit != null && mit.ID == ite.ID) {
                return true;
            }
            ++i;
        }
        return false;
    }

    public void CreatePhiPhongresult(int type, int midicon) {
        typeresult = type;
        idicon = midicon;
        this.Waitcreate = true;
        int i = 0;
        while (i < 6) {
            WeaponsLazer wp = new WeaponsLazer(Util.cos(i * 60 + this.goc) * this.r / 1024 + this.xcenter,
                    Util.sin(i * 60 + this.goc) * this.r / 1024 + this.ycenter, this.xcenter, this.ycenter, 25, 1,
                    this.sizeEff[i]);
            this.vEffect.addElement(wp);
            ++i;
        }
        EffectEnd ef = new EffectEnd(0, this.xcenter, this.ycenter);
        this.vEffect.addElement(ef);
    }

    public int getpostPhiPhong(mVector vec, Item ite) {
        int i = 0;
        while (i < vec.size()) {
            Item mit = (Item) vec.elementAt(i);
            if (mit != null && mit.ID == ite.ID) {
                return i;
            }
            ++i;
        }
        return -1;
    }

    public int getPost() {
        int i = 0;
        while (i < this.listItem.length) {
            if (this.listItem[i] == null) {
                return i;
            }
            ++i;
        }
        return -1;
    }

    public boolean CheckList(mVector vec, Item ite) {
        int i = 0;
        while (i < vec.size()) {
            Item mit = (Item) vec.elementAt(i);
            if (mit != null && mit.pos == ite.pos) {
                return true;
            }
            ++i;
        }
        return false;
    }

    public int getpost(mVector vec, Item ite) {
        int i = 0;
        while (i < vec.size()) {
            Item mit = (Item) vec.elementAt(i);
            if (mit != null && mit.idIcon == ite.idIcon) {
                return i;
            }
            ++i;
        }
        return -1;
    }

    public String getPercent(int lvpercent) {
        return String.valueOf(lvpercent / 100) + "." + lvpercent % 100 + "%";
    }

    public boolean isTabQuest() {
        if (GameCanvas.isTouch) {
            return this.indexMainTab == 3 && this.isQuest;
        }
        return this.indexMainTab == 3 && this.isQuest;
    }
}
