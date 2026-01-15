/*
 * Decompiled with CFR 0.152.
 */
package mchien.code.screen.screen;

import mchien.code.effect.FlyText;
import mchien.code.effect.new_skill.EffectEnd;
import mchien.code.effect.new_skill.EffectSkill;
import mchien.code.helps.loadIP;
import mchien.code.main.GameCanvas;
import mchien.code.model.*;
import mchien.code.model.arrow.Arrow;
import mchien.code.model.arrow.BaBuran;
import mchien.code.model.arrow.IArrow;
import mchien.code.model.arrow.MagicBeam;
import mchien.code.model.arrow.WeaponsAngleBoss;
import mchien.code.model.arrow.WeaponsFire;
import mchien.code.model.arrow.WeaponsLazer;
import mchien.code.network.GameService;
import mchien.code.screen.MenuLogin;
import mchien.code.screen.MsgChat;
import mchien.code.screen.Res;
import mchien.code.screen.SkillTemplate;
import mchien.code.screen.Util;
import mchien.code.screen.Utils;
import mchien.code.screen.event.EventScreen;
import mchien.code.screen.event.MainEvent;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.microedition.lcdui.Image;

import lib.Cout;
import lib.MainUnity;
import lib.Rms;
import lib.Session_ME;
import lib.Tilemap;
import lib.Tree;
import lib.mGraphics;
import lib.mHashtable;
import lib.mRandom;
import lib.mSound;
import lib.mSystem;
import lib.mVector;
import lib2.Message;
import lib2.TField;
import lib2.mFont;
import mchien.code.screen.screen.vongquay.VongQuayScr;

public class GameScr
        extends ScreenTeam
        implements IActionListener {
    public static boolean showDamageRanking = false;
    public static mVector damageRankingList = new mVector();
    private long lastTimeDie;
    private int countRevive;

    // Class để lưu thông tin xếp hạng
    public static class DamageRankingInfo {
        public String clanName;
        public long damage;
        public int iconId;

        public DamageRankingInfo(String clanName, long damage, int iconId) {
            this.clanName = clanName;
            this.damage = damage;
            this.iconId = iconId;
        }
    }

    public static String nameMap = "";
    public static final byte KILL_MOSTER = 0;
    public static final byte GET_ITEM = 2;
    public static final byte TRANSPORT = 1;
    public int gssx;
    public int gssy;
    public int gssw;
    public int gssh;
    public int gssxe;
    public int gssye;
    int[] CMVIBRATEX = new int[]{-1, 2, 1, -2};
    int[] CMVIBRATEY = new int[]{-3, 2, -1, 1};
    public static byte[] SERVER_ID = new byte[3];
    int currentCmVibrate = -1;
    public static String version = "2.9.9";
    public static int idNPCshopInven;
    public static int catNPCshopInven;
    public static boolean lockcmx;
    public static boolean lockcmy;
    public mVector actors = new mVector();
    public mVector npc_limit = new mVector();
    public static mVector arrowsUp;
    public mVector arrowsDown = new mVector();
    public int hideGUI = 0;
    public boolean readyGetGameLogic = true;
    public boolean isFillScr = false;
    public boolean isCloud;
    public static mVector shop;
    public int timerung;
    public int timeQouckSlot;
    public static String[] strHelpNPC;
    public static byte StepHelpServer;
    boolean changeSinceLastUpdate;
    public static int gsw;
    public static int gsh;
    public static int gscmdBarY;
    public static int gshw;
    public static int gshh;
    public static int gswd3;
    public static int gshd3;
    public static int gsw2d3;
    public static int gsh2d3;
    public static int gsw3d4;
    public static int gsh3d4;
    public static int gswd6;
    public static int gshd6;
    public mVector vecCharParty = new mVector();
    public static mVector serverInfo;
    public GameService gameService = GameService.gI();
    public String[] flyTextString;
    public int[] flyTextX;
    public int[] flyTextY;
    public int[] flyTextDx;
    public int[] flyTextDy;
    public int[] flyTextState;
    public int[] flyTextColor;
    public static mVector VecInfoServer;
    public static mVector VecTime;
    public short saveIDViewInfoAnimal = (short) -1;
    public static Image backFocus;
    public static Image imghpmp;
    public static Image imglv;
    public static Image backNum;
    public static Image imgRect;
    public static Image imgLbtn;
    public static Image imgLbtnFocus;
    public static Image imgLbtn2;
    public static Image imgLbtnFocus2;
    public static Image imgAnalog1;
    public static Image imgAnalog2;
    public static Image imgMSG;
    public static Image imgPK;
    public static Image imgBackArena;
    public static Image imgTexFeil;
    public static Image imgtinhanh;
    public static Image imgthulinh;
    public static Image imgEvent;
    public static Image imgSao;
    public static mRandom r;
    public boolean chatMode = false;
    public boolean isBuffAuto;
    public boolean chatWorld;
    public TField tfChat = new TField();
    public TField tfChatWorld = new TField();
    mVector chatHistory = new mVector();
    int CHAT_HISTORY_SIZE = 5;
    public static int tempIDActorParty;
    public short questID = 1;
    public short current_npc_talk = (short) -1;
    public int indexContent = -1;
    public int countMainCharAttack;
    public short idNpcReceive = (short) 5;
    public static byte idItemClanquest;
    public static Position posNpcReceiveClan;
    public boolean receiveQuest = false;
    public boolean ispaint12Plus;
    public boolean isDis = false;
    public String[] content_quest = null;
    public static String info_gif_quest;
    public static String titleQuest;
    public static Image[] imgfocus;
    public static Image[] imgCong;
    public static Image[] imgTouchMove;
    public static Image[] imgAnalog;

    public static Image[] imgSo;
    public static Image[] imgBoder;
    public static Image[] imgBk;
    public static Image[] imgArrow;
    public static Image[] imgMenuList;
    public static Image[] imgArrowMenu;
    public static Image[] imgFly;
    public static Image[] imgMenu;
    public static Image[] imgMenuListClan;
    public static Image[] imgMenuListHD;
    public static Image[] imgMenuListSettings;
    public static Image[] imgButton;
    public static Image[] imghealth;
    public static Image[] imgShadow;
    public static Image[] imgCloud;
    public static Image bt_Speak;
    public static Image imgWater;
    public static Image imgBlock;
    public static Image coloritem;
    public static Image imgquest;
    public static Image imgStart;
    public static Image imgBackItem;
    public static Image imgTextfileld;
    public static Image imgStone;
    public static Image imgBkEp;
    public static Image imgPointMinimap;
    public static Image imgPointQuest;
    public static Image imgIconGold;
    public static Image imgCheck;
    public static Image imgIncrease;
    public static Image coloritem_small;
    public static Image imgMoney;
    public static Image imgPoint;
    public static Image imgInfo;
    public static Image imgHpMonster;
    public static Image imgW;
    public static Image imgIconTN;
    public static Image imgMainMenu;
    public static Image imgMainMenu1;
    public static Image imgBgMainMenu;
    public static Image imgloading;
    public static Image imghand;
    public static Image imgBack;
    public static Image statusInfo;
    public static Image imgCheckevent;
    public static Image imgPlus;
    public static Image imgauto;
    public static Image peticon;
    public static Image petinfo;
    public static Image imgNguhanh;
    public Position posNpcRequest;
    public static mVector vec_skill;
    public mVector vecCloud = new mVector();
    int yMsgChat = 0;
    int toYchat = -18;
    int startMsg;
    public int indexBuff = -1;
    public int timetouch;
    private boolean isTouchMenu;
    private boolean isAutoChangeFocus;
    public static long timeBuff;
    public static int indexTabSlot;
    public static int xSlot;
    public static int idMapPK;
    public static int iTaskAuto;
    public Actor focusedActor;
    public static byte xArena;
    public static byte yArena;
    public static byte wArena;
    public static byte hArena;
    public static int arena;
    public static int[] timeChiemThanh;
    public static int[] curTimeCT;
    public static short[] idChiemThanh;
    public static String[] nameChiemThanh;
    public static String[] nameClan;
    public static String[] questContent;
    public static String[] textQuest;
    public static byte CountQuestConten;
    public static byte numMSG;
    private static boolean isFindChar;
    private static boolean isActorMove;
    private static boolean isFindMonster;
    private static boolean isQuest;
    public static boolean isGost;
    public static mVector effAnimate;
    public static mVector effManager;
    public static mVector msgWorld;
    public static mVector savemsgWorld;
    public static byte typeOptionFocus;
    public static GameScr me;
    public static boolean isIntro;
    public static boolean instruction;
    public static boolean isSetinfo;
    public static boolean canmove;
    public static int CountIntro;
    public static int CountMoveFirst;
    public static int steep;
    public static int Next;
    public static mVector VecInfoChar;
    public static final byte STATE_FINISH = 1;
    public static final byte STATE_WORKING = 2;
    public static final byte STATE_UN_RECEIVE = 0;
    public static final byte STATE_DONE = 3;
    public int xSaveAuto;
    public int ySaveAuto;
    public int countTouchmove;
    public int xp;
    public int yp;
    public static mVector allQuestCanReceive;
    public static mVector allQuestWorking;
    public static mVector allQuestFinish;
    public static Quest mainQuest;
    public static Quest subQuest;
    public static Quest clanQuest;
    public static mHashtable hashQuestCanReceive;
    public static mHashtable hashQuestWorking;
    public static mHashtable hashQuestFinish;
    public static mHashtable hashEffInfo;
    int[] mKeyMove = new int[]{4, 6, 2, 8};
    public static int timeremoveItem;
    public static DataSkillEff[] dataColorItem;
    public mVector ALL_SKILL = new mVector();
    public int charclass = 0;
    public int fFocus;
    public int tickicon;
    public int waitFocus;
    public int idMapColor;
    public static int numMess;
    public static int hShowEvent;
    public static int wShowEvent;
    public static int timeEvent;
    MainEvent eventShow = null;
    public static mVector vFlytext;
    public static int lastMap;
    public String textinfomainChar = "";
    public int inDexfont = 0;
    public int tickpaintFont;
    public int tickHP;
    public int tickMP;
    public int inDexHP;
    public int inDexMP;
    public int tickConnect;
    public static Image imgGhost;
    public static Image[] imgBigMap;
    public static Actor Ghost;
    public static short[] TIME_BETWEEN_ATTACK;
    public static long coolDown;
    public static String[] infoAutoGetItem;
    public short[] idNPC_Sell;
    public mCommand cmdLogin;
    public mCommand cmdmenu;
    public mCommand cmdskip;
    public mCommand cmdchat;
    public mcCmd cmdAuto;
    public mcCmd cmdGoiNap;
    public mcCmd cmdVongQuay;
    public mCommand cmdChangeFocus;
    public mCommand cmdCloseChat;
    public mCommand cmdDochat;
    public mCommand cmdhoisinhtaicho;
    public mCommand cmdvelang;
    public mCommand cmdwait;
    public mCommand cmdDisconect;
    public byte countQuest;
    public static final byte INFO_CLAN = 0;
    public static final byte INVITE_MEMBER = 1;
    public static final byte ACCEPT_MEMBER = 2;
    public static final byte KICK_MEMBER = 3;
    public static final byte DEL_CLAN = 4;
    public static final byte OUT_CLAN = 5;
    public static final byte PROMOTE_MEMBER = 6;
    public static final byte DONATE_XU = 7;
    public static final byte DONATE_LUONG = 8;
    public static final byte PHAT_LUONG_CA_NHAN = 9;
    public static final byte PHAT_LUONG_CHUC_VU = 10;
    public static final byte CLAN_INFO_FOR_MEMBER = 11;
    public static final byte CLAN_INVENTORY = 12;
    public static mCommand cmdTinden;
    public static CharCountDown charcountdonw;
    public short idIconX2;
    public static int xL;
    public static int xR;
    public static int xU;
    public static int xD;
    public static int yL;
    public static int yR;
    public static int yU;
    public static int yD;
    public static int yTouchBar;
    public static int xChat;
    public static int yChat;
    public static int wChat;
    public static int hChat;
    public static int xFire;
    public static int yFire;
    public static int xSkill1;
    public static int ySkill1;
    public static int xSkill2;
    public static int ySkill2;
    public static int xSkill3;
    public static int ySkill3;
    public static int xSkill4;
    public static int ySkill4;
    public static int xMenu;
    public static int yMenu;
    public static int xMove;
    public static int yMove;
    public static int xCenter;
    public static int yCenter;
    public static int xTouchMove;
    public static int yTouchMove;
    public static int yms;
    public static int xpress;
    public static int ypress;
    public static String[] SV_IP;
    public static String[] PORT;
    public int[] ypc;
    public static int R;
    public static int angle;
    public static int curAngle;
    public static boolean isTouchMove;
    boolean isPress;
    int idtouch = -1;
    public static int[] disFlytext;
    public static int tickFly;
    int HPBARW;
    int EXPBARW;
    int HPBARW_MONSTER;
    private Position posMiniMap;
    public static int wMiniMap;
    public static int hMiniMap;
    private Position[] posQuickSlot;
    private int[] numPC;
    public long lastTimePing;
    public long pingDelay;
    private int pingNextIn = 10;
    public static long timeStandWhenAuto;
    short idmap = 0;
    byte idskilltest = 0;
    int lvmap = 0;
    public static long countdowm;
    public static boolean startCountdow;
    public static byte bossfire;
    public static Monster Bossintro;
    public boolean isBigFocus;
    public boolean isTouchChat;
    int xMsgServer = 0;
    static final int[][] C;
    public mVector nearActors = new mVector();
    public static byte[][] DX;
    public static byte[][] DY;
    public static final byte MAX_INVENTORY = 42;
    public int[] QUICKSLOT_KEY = new int[]{1, 3, 5, 7, 9};
    public int[] QUICKSLOT_KEY_PC = new int[]{5, 1, 3, 7, 9};
    boolean finishQuest = false;
    public static boolean autoFight;
    public static boolean saveAutoFight;
    public static boolean isBeginAutoBuff;
    public static boolean isAutoBomHp;
    public static boolean isAutoBomMp;
    public static boolean autoBomHMP;
    public boolean isAutoRangeWhenAuto;
    public static long timeDelayHpMp;
    public static Quest currQuest;
    public static int timeDelayTask;
    public static boolean cheat;
    int[] SLOTINDEXOFKEY;
    public mVector vecSkill;
    public int indexSkill;
    boolean first;
    public static int timeRemovePos;
    public int a;
    public int b;
    public int coutChangeFocusWhenDoing;
    boolean paintMapPopup;
    mVector menuItems;
    private int[] colorPaint;
    private static int[] colorMini;
    public static int dx;
    public static int dy;
    public static int timeVibrateScreen;
    byte idInfo;
    byte xInfo;
    byte yInfo;
    public static int[] postSkill;
    public int indexTouch;
    public static int[] ColorHpFocus;
    public static int[] ColorHpFocus1;
    public short map;
    public boolean isNewVersionAvailable;
    public static boolean isDisConect;
    public static boolean isMeLogin;
    public static long timeReconnect;
    public static int paintCay;
    public static int paintChar;
    public mVector decriptNap;
    public mVector syntaxNap;
    public mVector centerNap;
    public static int pTicket;
    public static int cmtoYmini;
    public static int cmyMini;
    public static int cmdyMini;
    public static int cmvyMini;
    public static int cmtoXMini;
    public static int cmxMini;
    public static int cmdxMini;
    public static int cmvxMini;
    public static long timeTranMini;
    private Position posCam;
    private Target tg;
    public short xBeginFrame;
    public short yBeginFrame;
    public short xTheendFrame;
    public short yTheendFrame;
    public static byte[][] mapFind;
    public static byte xStart;
    public static byte yStart;
    public static boolean isFixBugAutoQuest;
    public int iTaskAutoLast;
    mVector listMSGServer;
    public SkillClan[] skillClan;
    public SkillClan[] skillClanPrivate;
    public static byte[][] TYPE_MP_HP;
    public static int[][] VALUE_MP_HP;
    public static boolean isSendFinishQuest;
    int treeIndex;
    int actorIndex;
    int npcLimit;
    public static int[] DELTACAMERAX;
    public static int[] DELTACAMERAY;
    int[] xChange;
    int countChang;
    public static mVector PosNPCQuest;
    byte lastDir;
    byte dir;
    byte countStep;
    short moveToX;
    short moveToY;
    public mVector vMonster;
    public static mHashtable ID_BOSS;
    public static mHashtable ALL_SKILL_TEMPLATE_BOSS;
    public static short dtmove;
    public static long timeMove;
    public static int[] postLoginX;
    public static int[] postLoginY;
    public static Location[] allLocation;
    public static MainChar mainChar;
    public static byte[] idCharClazz;
    public static String[] nameClazz;
    public static int[] xxx;
    public static int[] yyy;
    public static short[] ToxFirst;
    public static short[] ToyFirst;
    int[] xit;
    int[] yit;
    int[] xLogin;
    int[] yLogin;
    int[] xit_mons;
    public static int[] postCharLoginX;
    public static int[] postCharLoginY;
    public static mVector vecCharintro;
    int count;
    int tickwait;
    int wait;
    public static mVector tam;
    public static boolean ismovefirst;
    public static boolean fireIntro;
    public static int[] posRange;
    public int[] possIntroX;
    public int[] possIntroY;
    public mVector charlogin;
    public static int[][] arrSkill;
    public static int co;
    public static int timepopup;
    public static int clazz;
    public static int SteepCount;
    public static int chatcount;
    public static boolean canfire;
    public static boolean finishTalk;
    public int countAuto;
    public mVector itemPick;
    public int indexKeyTouchAuto;
    public int xStartAuto;
    public int yStartAuto;
    public int rangeAuto;
    public int indexIdFocus;
    public boolean isStartAutoAttack;
    public boolean beginAuto;
    public boolean isFindNexTarget;
    public boolean isMovebyTouch;
    public static boolean isnextmap;
    public long WaitTips;

    static {
        arrowsUp = new mVector();
        shop = new mVector();
        strHelpNPC = null;
        StepHelpServer = 0;
        serverInfo = new mVector();
        VecInfoServer = new mVector();
        VecTime = new mVector();
        r = new mRandom(mSystem.currentTimeMillis());
        tempIDActorParty = -1;
        idItemClanquest = (byte) -1;
        info_gif_quest = null;
        titleQuest = "";
        vec_skill = new mVector();
        timeBuff = -1L;
        indexTabSlot = 0;
        xSlot = 0;
        idMapPK = -1;
        iTaskAuto = 0;
        isFindChar = false;
        isActorMove = false;
        isFindMonster = false;
        isQuest = false;
        effAnimate = new mVector();
        msgWorld = new mVector();
        savemsgWorld = new mVector();
        typeOptionFocus = 0;
        instruction = false;
        isSetinfo = false;
        CountIntro = 0;
        CountMoveFirst = 0;
        VecInfoChar = new mVector();
        allQuestCanReceive = new mVector();
        allQuestWorking = new mVector();
        allQuestFinish = new mVector();
        hashQuestCanReceive = new mHashtable();
        hashQuestWorking = new mHashtable();
        hashQuestFinish = new mHashtable();
        hashEffInfo = new mHashtable();
        dataColorItem = new DataSkillEff[4];
        hShowEvent = 0;
        wShowEvent = 122;
        vFlytext = new mVector();
        lastMap = -1;
        TIME_BETWEEN_ATTACK = new short[]{2000, 500, 1000, 1500, 1000};
        coolDown = 0L;
        SV_IP = new String[]{"27.0.14.122", "115.84.183.56", "127.0.0.1", "127.0.0.1", "127.0.0.1"};
        PORT = new String[]{"19129", "19129", "19129", "19129", "19129", "19129", "19129"};
        disFlytext = new int[]{15, 25, 35, 40};
        countdowm = 0L;
        startCountdow = false;
        C = new int[][]{{-90, 90, -90, 90}, {-90, 90, -90, 90}, {-90, 90, -90, 90}, {-90, 90, -90, 90}};
        byte[][] byArrayArray = new byte[4][];
        byte[] byArray = new byte[4];
        byArray[2] = -48;
        byArray[3] = 48;
        byArrayArray[0] = byArray;
        byte[] byArray2 = new byte[4];
        byArray2[2] = -32;
        byArray2[3] = 32;
        byArrayArray[1] = byArray2;
        byte[] byArray3 = new byte[4];
        byArray3[2] = -16;
        byArray3[3] = 16;
        byArrayArray[2] = byArray3;
        byArrayArray[3] = new byte[4];
        DX = byArrayArray;
        byte[][] byArrayArray2 = new byte[4][];
        byte[] byArray4 = new byte[4];
        byArray4[0] = 48;
        byArray4[1] = -48;
        byArrayArray2[0] = byArray4;
        byte[] byArray5 = new byte[4];
        byArray5[0] = 32;
        byArray5[1] = -32;
        byArrayArray2[1] = byArray5;
        byte[] byArray6 = new byte[4];
        byArray6[0] = 16;
        byArray6[1] = -16;
        byArrayArray2[2] = byArray6;
        byArrayArray2[3] = new byte[4];
        DY = byArrayArray2;
        autoFight = false;
        currQuest = null;
        timeDelayTask = 50;
        cheat = false;
        colorMini = new int[]{6898216, 11897430, 14469298};
        dx = 0;
        dy = 0;
        timeVibrateScreen = 0;
        ColorHpFocus = new int[]{15829117, 15756897, 15685704, 15089711, 13904665, 11409416};
        ColorHpFocus1 = new int[]{10943139, 7399019, 4509758, 2799908, 1416463, 555523};
        isDisConect = false;
        isMeLogin = false;
        timeReconnect = System.currentTimeMillis();
        paintCay = 1;
        paintChar = 1;
        pTicket = 1000;
        TYPE_MP_HP = new byte[2][];
        VALUE_MP_HP = new int[2][];
        isSendFinishQuest = false;
        int[] nArray = new int[4];
        nArray[2] = -20;
        nArray[3] = 20;
        DELTACAMERAX = nArray;
        int[] nArray2 = new int[4];
        nArray2[0] = 20;
        nArray2[1] = -20;
        DELTACAMERAY = nArray2;
        PosNPCQuest = new mVector();
        dtmove = (short) 250;
        timeMove = System.currentTimeMillis();
        postLoginX = new int[]{192, 200, 204, 208, 168, 176};
        postLoginY = new int[]{704, 712, 716, 720, 256, 256};
        allLocation = null;
        mainChar = new MainChar();
        byte[] byArray7 = new byte[5];
        byArray7[0] = 1;
        byArray7[2] = 3;
        byArray7[3] = 2;
        byArray7[4] = 4;
        idCharClazz = byArray7;
        nameClazz = new String[]{"hoalongbang", "truclamtu", "vocucdao", "thuylienvien", "machucoc"};
        int[] nArray3 = new int[5];
        nArray3[3] = 50;
        nArray3[4] = 50;
        xxx = nArray3;
        yyy = new int[]{60, 120, 180, 90, 150};
        ToxFirst = new short[]{315, 343, 520, 496};
        ToyFirst = new short[]{160, 344, 159, 335};
        postCharLoginX = new int[]{688, 224, 192, 592, 496};
        postCharLoginY = new int[]{704, 672, 176, 416, 80};
        vecCharintro = new mVector();
        tam = new mVector();
        posRange = new int[]{-16, 16, -32, 32, 10, 48, 5, -48};
        int[][] nArrayArray = new int[5][];
        int[] nArray4 = new int[3];
        nArray4[1] = 1;
        nArray4[2] = 2;
        nArrayArray[0] = nArray4;
        int[] nArray5 = new int[3];
        nArray5[1] = 9;
        nArray5[2] = 10;
        nArrayArray[1] = nArray5;
        int[] nArray6 = new int[3];
        nArray6[1] = 5;
        nArray6[2] = 6;
        nArrayArray[2] = nArray6;
        int[] nArray7 = new int[3];
        nArray7[1] = 7;
        nArray7[2] = 8;
        nArrayArray[3] = nArray7;
        int[] nArray8 = new int[3];
        nArray8[1] = 3;
        nArray8[2] = 4;
        nArrayArray[4] = nArray8;
        arrSkill = nArrayArray;
        co = 0;
        isnextmap = false;
    }

    private int soLanLat;

    public static GameScr gI() {
        return me == null ? (me = new GameScr()) : me;
    }

    public static void CheckIP() {

        // if (mSystem.isIos) {
        // SV_IP = new String[]{"5l.teamobi.com", "5l2.teamobi.com"};
        // }
        Rms.LoadIndexSv();
        loadIP.load();
    }

    public void initTouchMove() {
        if (!GameCanvas.isTouch) {
            return;
        }
        yTouchBar = GameCanvas.h - 100;
        xL = 3;
        yms = 0;
        xTouchMove = 52;
        yTouchMove = yTouchBar + yms;
        // Trong hàm khởi tạo hoặc reset
        xpress = 20 + mGraphics.getImageWidth(imgAnalog[1]) / 2;
        ypress = yTouchMove + mGraphics.getImageHeight(imgAnalog[1]) / 2;
        yL = yTouchBar + 17;
        xU = 37;
        xMove = 37;
        yMove = GameCanvas.h - 50;
        yU = yTouchBar - 17;
        xR = 72;
        yR = yL;
        xD = xU;
        yD = yTouchBar + 52;
        xCenter = xU;
        yCenter = yU;
        int x00 = GameCanvas.w - 62;
        int y00 = yTouchBar + 15;
        xFire = x00 + 10;
        yFire = y00 + 15;
        xSkill1 = x00 - 16;
        ySkill1 = y00 + 62;
        xSkill2 = x00 - 16;
        ySkill2 = y00 + 25;
        xSkill3 = x00 + 4;
        ySkill3 = y00 - 6;
        xSkill4 = x00 + 40;
        ySkill4 = y00 - 15;
        xChat = 4;
        yChat = 40;
        wChat = 30;
        hChat = 30;
        xMenu = GameCanvas.w / 2 - 22;
        yMenu = GameCanvas.h - 17;
        if (mSystem.isPC) {
            xFire = GameCanvas.w - 50;
            yFire = GameCanvas.h - 49 - 2;
            xSkill1 = GameCanvas.w - 70;
            ySkill1 = GameCanvas.h - 23 - 2;
            xSkill2 = GameCanvas.w - 110;
            ySkill2 = GameCanvas.h - 23 - 2;
            xSkill3 = GameCanvas.w - 150;
            ySkill3 = GameCanvas.h - 23 - 2;
            xSkill4 = GameCanvas.w - 190;
            ySkill4 = GameCanvas.h - 23 - 2;
            this.ypc = new int[5];
            this.ypc[0] = -7;
            this.ypc[1] = -12;
            this.ypc[2] = -12;
            this.ypc[3] = -12;
            this.ypc[4] = -10;
            this.numPC = new int[5];
            xMenu = (GameCanvas.w - ySkill4) / 2 + 40;
            this.numPC[0] = xFire + 21;
            this.numPC[1] = xSkill1;
            this.numPC[2] = xSkill2;
            this.numPC[3] = xSkill3;
            this.numPC[4] = xSkill4;
        }
        curAngle = -1;
    }

    public void paintAnalog(mGraphics g) {
        if (this.isMovebyTouch)
            return;
        if (mSystem.isPC)
            return;
        if (GameCanvas.menuSelectitem.showMenu)
            return;
        if (GameCanvas.menu2.isShow)
            return;
        if (GameCanvas.menu.showMenu)
            return;
        if (MainChar.blockkey)
            return;
        if (this.hideGUI == 2)
            return;
        if (GameCanvas.currentDialog != null)
            return;
        if (this.chatMode || this.chatWorld)
            return;

        // Vẽ background analog
        int bgX = 20;
        int bgY = yTouchMove;
        g.drawImage(imgAnalog[1], bgX, bgY, 0, false);

        // Vẽ stick TẠI vị trí xpress, ypress (đã tính sẵn trong update)
        int bgWidth = mGraphics.getImageWidth(imgAnalog[1]);
        int bgHeight = mGraphics.getImageHeight(imgAnalog[1]);
        int stickWidth = mGraphics.getImageWidth(imgAnalog[0]);
        int stickHeight = mGraphics.getImageHeight(imgAnalog[0]);

        // Vẽ stick căn giữa tại (xpress, ypress)
        g.drawImage(imgAnalog[0],
                xpress - stickWidth / 2,
                ypress - stickHeight / 2,
                0, false);

        if (!Tilemap.isMapIntro() && !GameCanvas.isTouch) {
            g.drawImage(imgMenu[this.isTouchMenu ? 1 : 0], xMenu, yMenu, 0, false);
        }
    }

    @Override
    public void switchToMe() {
        super.switchToMe();
        GameCanvas.keyHold[2] = false;
        this.init();
        vecCharintro.removeAllElements();
        this.charlogin.removeAllElements();
        tam.removeAllElements();
    }

    public mVector getSkilltarget(int rangeSkill, int maxtarget, byte cat) {
        Actor ac;
        int i;
        mVector ntarget = new mVector();
        if (isIntro) {
            if (this.focusedActor != null && this.focusedActor.catagory == 1) {
                ntarget.addElement(this.focusedActor);
            }
            int i2 = 0;
            while (i2 < this.actors.size()) {
                Actor ac2 = (Actor) this.actors.elementAt(i2);
                if (ac2 != null && ac2.catagory == 1 && ac2.getState() != 5 && ac2.getState() != 1) {
                    ntarget.addElement(ac2);
                    ac2.DropHP(100);
                }
                ++i2;
            }
            return ntarget;
        }
        if (!Res.inRangeActor(mainChar, this.focusedActor, rangeSkill)) {
            return ntarget;
        }
        if (cat == 1) {
            if (this.focusedActor.getState() != 8 && this.focusedActor.getState() != 5
                    && this.focusedActor.getState() != 1 && mainChar.setFireChar(this.focusedActor)) {
                ntarget.addElement(this.focusedActor);
            }
            if (maxtarget == 1) {
                return ntarget;
            }
            i = 0;
            while (i < this.actors.size()) {
                ac = (Actor) this.actors.elementAt(i);
                if (ac != null && ac.getState() != 8 && !ac.equals(this.focusedActor) && ac.getState() != 5
                        && ac.getState() != 1 && Res.inRangeActor(GameScr.mainChar.x, GameScr.mainChar.y,
                        ac.getXmonster(), ac.getYmonster(), rangeSkill)) {
                    if (ntarget.size() >= maxtarget)
                        break;
                    if (mainChar.setFireChar(ac)) {
                        ntarget.addElement(ac);
                    }
                }
                ++i;
            }
        }
        if (cat == 0) {
            if (this.focusedActor.equals(mainChar)) {
                ntarget.removeAllElements();
                return ntarget;
            }
            if (this.focusedActor.getState() != 3 && mainChar.setFireChar(this.focusedActor)) {
                ntarget.addElement(this.focusedActor);
            }
            if (maxtarget == 1) {
                return ntarget;
            }
            i = 0;
            while (i < this.actors.size()) {
                ac = (Actor) this.actors.elementAt(i);
                if (ac != null && ac.catagory == 0 && !ac.equals(this.focusedActor) && ac.getState() != 3
                        && Utils.getDistance(mainChar, ac) <= rangeSkill && ac.canFocus() && mainChar.setFireChar(ac)) {
                    if (ntarget.size() >= maxtarget)
                        break;
                    ntarget.addElement(ac);
                }
                ++i;
            }
        }
        return ntarget;
    }

    public void checkAutoAttack(byte isauto) {
        if (isauto == 1 && GameCanvas.menuSelectitem.isAutoDanh[0] && !Tilemap.ismapLang) {
            this.beginAuto = true;
        }
    }

    public void doAttack(int idskill, int idQuickSlot) {
        if (idskill == -1) {
            return;
        }
        if (this.focusedActor == null) {
            return;
        }
        if (GameScr.mainChar.state == 3) {
            return;
        }
        if (this.focusedActor != null && this.focusedActor.catagory != 0 && this.focusedActor.catagory != 1) {
            return;
        }
        mVector mons = new mVector();
        SkillTemplate skill = (SkillTemplate) vec_skill.elementAt(idskill);
        if (skill != null) {
            short ntarget = skill.getTarget(MainChar.levelSkill[idskill]);
            int mplose = skill.getmplose(MainChar.levelSkill[idskill]);
            if (mplose > GameScr.mainChar.mp && !isIntro) {
                GameCanvas.addNotify(T.khongdump, (byte) 0);
                return;
            }
            if (!Res.inRangeActor(mainChar, this.focusedActor, skill.range)) {
                mainChar.Flash(this.focusedActor.x, this.focusedActor.y, skill.range);
                GameScr.mainChar.dir = Util.findDirection(GameScr.mainChar.x, GameScr.mainChar.y, this.focusedActor.x,
                        this.focusedActor.y);
                return;
            }
            mons = this.getSkilltarget(skill.range, ntarget, this.focusedActor.catagory);
            if (mons.size() <= 0) {
                return;
            }
            if (mons != null && mons.size() > 0) {
                if (this.focusedActor != null) {
                    GameScr.mainChar.dir = Util.findDirection(mainChar, this.focusedActor);
                } else {
                    Actor act = (Actor) mons.elementAt(0);
                    GameScr.mainChar.dir = Util.findDirection(mainChar, act);
                }
                this.countMainCharAttack = (this.countMainCharAttack + 1) % 100;
                mainChar.startAttackMainChar(mons, skill.idSkillCode, skill.idEffStartSkill, this.countMainCharAttack);
                int time = 0;
                if (!Tilemap.isMapIntro()) {
                    if (this.focusedActor.catagory == 1) {
                        GameService.gI().attackMonster(mons, idskill, this.countMainCharAttack);
                    } else if (this.focusedActor.catagory == 0) {
                        GameService.gI().attackPlayer(mons, idskill, this.countMainCharAttack);
                    }
                    if (!this.beginAuto) {
                        this.beginAuto = true;
                        this.xSaveAuto = GameScr.mainChar.x;
                        this.ySaveAuto = GameScr.mainChar.y;
                    }
                    this.indexKeyTouchAuto = (this.indexKeyTouchAuto + 1) % 5;
                    coolDown = mSystem.currentTimeMillis() + (long) TIME_BETWEEN_ATTACK[GameScr.mainChar.clazz];
                    time = TIME_BETWEEN_ATTACK[GameScr.mainChar.clazz];
                    int i = 0;
                    while (i < MainChar.mQuickslot.length) {
                        if (MainChar.mQuickslot[i] != null && !isGost) {
                            MainChar.mQuickslot[i].setCoolDownChar(time);
                        }
                        ++i;
                    }
                }
                if (!isGost) {
                    MainChar.mQuickslot[idQuickSlot].startCoolDown(time);
                }
            }
        }
    }

    public void doTouchQuickSlot(int id) {
        if (MainChar.blockkey) {
            return;
        }
        if (coolDown - mSystem.currentTimeMillis() > 0L) {
            return;
        }
        if (GameCanvas.isTouch && !mSystem.isPC) {
            id -= 4;
        }
        if (this.chatMode && !GameCanvas.isTouch) {
            return;
        }
        if (this.focusedActor != null && this.focusedActor.isNPC() && id == 0) {
            this.doFire();
            return;
        }
        if (this.focusedActor != null && this.focusedActor.getTypeMove() == 5) {
            GameService.gI().startCountdown(this.focusedActor.catagory, this.focusedActor.ID);
            return;
        }
        if (id < 0 || id > MainChar.mQuickslot.length - 1) {
            return;
        }
        QuickSlot ql = MainChar.mQuickslot[id];
        if (this.focusedActor != null && this.focusedActor.catagory == 0 && !this.focusedActor.isNPC()
                && !this.focusedActor.beFire()) {
            if (Tilemap.ismapLang) {
                this.doParty();
                return;
            }
            if (!mainChar.setFireChar(this.focusedActor)) {
                this.doParty();
                return;
            }
        }
        if (ql.quickslotType == 2) {
            if (ql.typePotion == Item.TYPE_HP && GameScr.mainChar.hp >= GameScr.mainChar.maxhp) {
                return;
            }
            if (ql.typePotion == Item.TYPE_MP && GameScr.mainChar.mp >= GameScr.mainChar.maxmp) {
                return;
            }
            if (ql.canUsePotion()) {
                GameService.gI().useItem(ql.getidPotion());
                ql.startCoolDown(0);
            }
        } else if (ql.quickslotType == 1) {
            if (ql != null && ql.canfight() && ql.isBuff()) {
                if (!mainChar.canFire() || !mainChar.canUseSkill()) {
                    return;
                }
                SkillTemplate skill = (SkillTemplate) vec_skill.elementAt(ql.getSkillType());
                if (skill != null && ql.canfight() && mainChar.canFire()) {
                    int mplose = skill.getmplose(MainChar.levelSkill[ql.getSkillType()]);
                    if (mplose > GameScr.mainChar.mp && !isIntro) {
                        GameCanvas.addNotify(T.khongdump, (byte) 0);
                        return;
                    }
                    mainChar.startBuff(skill.idEffStartSkill);
                    ql.startCoolDown(0);
                    if (!this.beginAuto) {
                        this.beginAuto = true;
                        this.xSaveAuto = GameScr.mainChar.x;
                        this.ySaveAuto = GameScr.mainChar.y;
                    }
                    this.indexKeyTouchAuto = (this.indexKeyTouchAuto + 1) % 5;
                    GameService.gI().use_Buff(ql.getSkillType());
                }
                return;
            }
            if (this.focusedActor != null && this.focusedActor.catagory > 2) {
                if (!mainChar.isFullInven()) {
                    GameService.gI().getDropableFromGround(this.focusedActor.ID);
                }
                return;
            }
            if (this.focusedActor != null && this.focusedActor.isNPC() && id == 0) {
                this.doFire();
                return;
            }
            if (!mainChar.canFire() || !mainChar.canUseSkill()) {
                return;
            }
            if (ql != null && ql.canfight() && !ql.isBuff()) {
                this.doAttack(ql.getSkillType(), id);
            }
        }
    }

    public void doUseItem(int itemType) {
    }

    public void updateTouchMove() {
        if (charcountdonw != null) {
            return;
        }
        if (isIntro && GameScr.mainChar.posTransRoad != null) {
            return;
        }
        if (GameCanvas.menuSelectitem.showMenu) {
            return;
        }
        if (GameCanvas.currentDialog != null) {
            return;
        }
        if (GameCanvas.menu2.isShow || GameCanvas.menu.showMenu || GameCanvas.currentDialog != null) {
            return;
        }
        int rangle = 34;
        this.isPress = false;
        int i = 0;
        while (i < GameCanvas.isPointerDown.length) {
            if (!this.isMovebyTouch) {
                if (GameCanvas.isPointerDown[i] && !mSystem.isPC) {
                    this.idtouch = i;
                    if (GameCanvas.isPointer(0, yTouchMove - 10, 100, 84, i)) {
                        if (Utils.getDistance(xpress + 32, ypress + 32, GameCanvas.px[i], GameCanvas.py[i]) > 10) {
                            int gocMove = Util.angle(GameCanvas.px[i] - (xpress + 32),
                                    GameCanvas.py[i] - (ypress + 32));
                            int value = 0;
                            value = gocMove > 45 && gocMove <= 135 ? 3
                                    : (gocMove > 135 && gocMove <= 225 ? 0 : (gocMove > 225 && gocMove <= 315 ? 2 : 1));
                            GameCanvas.clearKeyHold();
                            if (value == 0) {
                                keyTouch = 2;
                            } else if (value == 1) {
                                keyTouch = 1;
                            } else if (value == 2) {
                                keyTouch = 0;
                            } else if (value == 3) {
                                keyTouch = 3;
                            }
                            this.isPress = true;
                            this.timetouch = 3;
                            this.timeQouckSlot = 3;
                            GameCanvas.keyHold[this.mKeyMove[value]] = true;
                        } else {
                            GameCanvas.clearKeyHold();
                        }
                    }
                } else if (this.idtouch == i) {
                    this.idtouch = -1;
                    xpress = 20;
                    ypress = yTouchMove;
                    this.countTouchmove = 0;
                    this.xp = -1;
                    this.yp = -1;
                }
            }
            if (GameCanvas.isPointerHoldIn(0, 0, rangle, rangle, i)) {
                keyTouch = 9;
                this.isPress = true;
            } else {
                boolean cfr_ignored_0 = GameCanvas.isPointerDown[i];
            }
            if (GameCanvas.isPointerClick[i] && GameCanvas.isPointer(xFire, yFire, 42, 42, i)
                    && GameCanvas.canTouch()) {
                this.timeQouckSlot = 3;
                keyTouch = 4;
                if (mSystem.isPC) {
                    keyTouch -= 4;
                }
                this.doTouchQuickSlot(keyTouch);
                this.isPress = true;
                if (GameCanvas.isPointerJustRelease[i] && GameCanvas.isPointerClick[i] && !mSystem.isPC) {
                    GameCanvas.keyPressedz[5] = true;
                }
                GameCanvas.isPointerClick[i] = false;
            }
            if (GameCanvas.isPointerClick[i] && GameCanvas.isPointer(xSkill1 - 15, ySkill1 - 15, 30, 30, 0)
                    && GameCanvas.canTouch()) {
                this.timeQouckSlot = 3;
                keyTouch = 5;
                if (mSystem.isPC) {
                    keyTouch -= 4;
                }
                this.doTouchQuickSlot(keyTouch);
                this.isPress = true;
                if (GameCanvas.isPointerJustRelease[i] && GameCanvas.isPointerClick[i]) {
                    GameCanvas.keyPressedz[1] = true;
                }
                GameCanvas.isPointerClick[i] = false;
            }
            if (GameCanvas.isPointerClick[i] && GameCanvas.isPointer(xSkill2 - 15, ySkill2 - 15, 30, 30, i)
                    && GameCanvas.canTouch()) {
                this.timeQouckSlot = 3;
                keyTouch = 6;
                if (mSystem.isPC) {
                    keyTouch -= 4;
                }
                this.doTouchQuickSlot(keyTouch);
                this.isPress = true;
                if (GameCanvas.isPointerJustRelease[i] && GameCanvas.isPointerClick[i]) {
                    GameCanvas.keyPressedz[3] = true;
                }
                GameCanvas.isPointerClick[i] = false;
            }
            if (GameCanvas.isPointerClick[i] && GameCanvas.isPointerHoldIn(xSkill3 - 15, ySkill3 - 15, 30, 30, i)
                    && GameCanvas.canTouch()) {
                this.timeQouckSlot = 3;
                keyTouch = 7;
                if (mSystem.isPC) {
                    keyTouch -= 4;
                }
                this.doTouchQuickSlot(keyTouch);
                this.isPress = true;
                if (GameCanvas.isPointerJustRelease[i] && GameCanvas.isPointerClick[i]) {
                    GameCanvas.keyPressedz[7] = true;
                }
                GameCanvas.isPointerClick[i] = false;
            }
            if (GameCanvas.isPointerClick[i] && GameCanvas.isPointerHoldIn(xSkill4 - 15, ySkill4 - 15, 30, 30, i)
                    && GameCanvas.canTouch()) {
                this.timeQouckSlot = 3;
                keyTouch = 8;
                if (mSystem.isPC) {
                    keyTouch -= 4;
                }
                this.doTouchQuickSlot(keyTouch);
                this.isPress = true;
                if (GameCanvas.isPointerJustRelease[i] && GameCanvas.isPointerClick[i]) {
                    GameCanvas.keyPressedz[9] = true;
                }
                GameCanvas.isPointerClick[0] = false;
            }
            if (!GameCanvas.isPointerDown[i] && keyTouch == 9 && this.right != null) {
                this.right.performAction();
            }
            if (GameCanvas.isPointerJustRelease[i]) {
                GameCanvas.keyHold[8] = false;
                GameCanvas.keyHold[6] = false;
                GameCanvas.keyHold[4] = false;
                GameCanvas.keyHold[2] = false;
            }
            ++i;
        }
    }

    public static int abs(int i) {
        return i > 0 ? i : -i;
    }

    public static void loadBegin() {
        try {
            try {
                ReadMessenge.versionImage = Byte.parseByte(Rms.loadRMSString("vsImg"));
            } catch (Exception exception) {
                // empty catch block
            }
            imgfocus = new Image[4];
            int i = 0;
            while (i < imgfocus.length) {
                GameScr.imgfocus[i] = GameCanvas.loadImage("/interface/f" + i + ".png");
                ++i;
            }
            backNum = GameCanvas.loadImage("/interface/backNum.png");
            backFocus = GameCanvas.loadImage("/interface/back.png");
            imgTexFeil = GameCanvas.loadImage("/hd/imgTextfileld.png");
            ChatPopup.imgPopup = GameCanvas.loadImage("/interface/popup.png");
            imgBackArena = GameCanvas.loadImage("/interface/khu.png");
            imgSao = GameCanvas.loadImage("/interface/sao.png");
            bt_Speak = GameCanvas.loadImage("/interface/bt_Speak.png");
            imgPK = GameCanvas.loadImage("/interface/pk.png");
            imgRect = GameCanvas.loadImage("/interface/rect.png");
            imgMSG = GameCanvas.loadImage("/interface/msg.png");
            imgWater = GameCanvas.loadImage("/interface/water.png");
            imgBlock = GameCanvas.loadImage("/interface/lock.png");
            coloritem = GameCanvas.loadImage("/interface/color.png");
            coloritem_small = GameCanvas.loadImage("/interface/color1.png");
            imgStart = GameCanvas.loadImage("/interface/start.png");
            imgquest = GameCanvas.loadImage("/interface/quest.png");
            if (imgButton == null) {
                imgShadow = new Image[6];
                i = 0;
                while (i < imgShadow.length) {
                    GameScr.imgShadow[i] = GameCanvas.loadImage("/interface/b" + i + ".png");
                    ++i;
                }
                imgCloud = new Image[2];
                i = 0;
                while (i < imgCloud.length) {
                    GameScr.imgCloud[i] = GameCanvas.loadImage("/interface/c" + i + ".png");
                    ++i;
                }
                imgBack = GameCanvas.loadImage("/interface/backinfo.png");
                imghealth = new Image[4];
                i = 0;
                while (i < imghealth.length) {
                    GameScr.imghealth[i] = GameCanvas.loadImage("/interface/m" + i + ".png");
                    ++i;
                }
                imghand = GameCanvas.loadImage("/interface/selected_hand.png");
                imgBkEp = GameCanvas.loadImage("/hd/bg1.png");
                imgButton = new Image[7];
                i = 0;
                while (i < imgButton.length) {
                    GameScr.imgButton[i] = GameCanvas.loadImage("/hd/imgCommand" + i + ".png");
                    ++i;
                }
                imgBackItem = GameCanvas.loadImage("/hd/imgBackItem.png");
                imgPoint = GameCanvas.loadImage("/interface/move.png");
                statusInfo = GameCanvas.loadImage("/hd/statusInfo.png");
                imgTouchMove = new Image[4];
                i = 0;
                while (i < 4) {
                    GameScr.imgTouchMove[i] = GameCanvas.loadImage("/hd/k" + i + ".png");
                    ++i;
                }
                imgAnalog = new Image[2];
                i = 0;
                while (i < 2) {
                    GameScr.imgAnalog[i] = GameCanvas.loadImage("/hd/sprAnalog" + i + ".png");
                    ++i;
                }
                imgMainMenu = GameCanvas.loadImage("/hd/mainmenu.png");
                imgMainMenu1 = GameCanvas.loadImage("/hd/mainmenu1.png");
                imgBgMainMenu = GameCanvas.loadImage("/hd/bg.png");
                imgSo = new Image[4];
                GameScr.imgSo[0] = GameCanvas.loadImage("/hd/ar0.png");
                GameScr.imgSo[1] = GameCanvas.loadImage("/hd/ar1.png");
                GameScr.imgSo[2] = GameCanvas.loadImage("/hd/ar2.png");
                GameScr.imgSo[3] = GameCanvas.loadImage("/hd/ar3.png");
                imgBoder = new Image[10];
                i = 0;
                while (i < imgBoder.length) {
                    GameScr.imgBoder[i] = GameCanvas.loadImage("/hd/bd/imgBD" + i + ".png");
                    ++i;
                }
                imgBk = new Image[3];
                i = 0;
                while (i < imgBk.length) {
                    GameScr.imgBk[i] = GameCanvas.loadImage("/hd/imgBackground" + i + ".png");
                    ++i;
                }
                imgArrow = new Image[2];
                i = 0;
                while (i < imgArrow.length) {
                    GameScr.imgArrow[i] = GameCanvas.loadImage("/hd/imgArrowBig" + i + ".png");
                    ++i;
                }
                imgMoney = GameCanvas.loadImage("/hd/money.png");
                imgMenuList = new Image[2];
                i = 0;
                while (i < imgMenuList.length) {
                    GameScr.imgMenuList[i] = GameCanvas.loadImage("/hd/main/" + (i == 0 ? 3 : 5) + ".png");
                    ++i;
                }
                imgArrowMenu = new Image[2];
                i = 0;
                while (i < imgArrowMenu.length) {
                    GameScr.imgArrowMenu[i] = GameCanvas.loadImage("/hd/imgArrowMenu" + i + ".png");
                    ++i;
                }
                imgTextfileld = GameCanvas.loadImage("/hd/imgTextfileld.png");
                imgW = GameCanvas.loadImage("/hd/iconCharWearing.png");
                imgIconTN = GameCanvas.loadImage("/hd/iconTN.png");
                imgMenu = new Image[2];
                i = 0;
                while (i < 2) {
                    GameScr.imgMenu[i] = GameCanvas.loadImage("/hd/menu/bt_menu" + i + ".png");
                    ++i;
                }
                imgIconGold = GameCanvas.loadImage("/hd/imggold.png");
                imgloading = GameCanvas.loadImage("/interface/waiting.png");
                imgCheck = GameCanvas.loadImage("/hd/check.png");
                imgIncrease = GameCanvas.loadImage("/hd/increase.png");
                imgPointMinimap = GameCanvas.loadImage("/w.png");
                imgPointQuest = GameCanvas.loadImage("/q.png");
                imgCheckevent = GameCanvas.loadImage("/check.png");
                imgPlus = GameCanvas.loadImage("/plus.png");
                imgauto = GameCanvas.loadImage("/interface/auto.png");
                imgtinhanh = GameCanvas.loadImage("/0.png");
                imgthulinh = GameCanvas.loadImage("/1.png");
                imglv = GameCanvas.loadImage("/lv.png");
                imghpmp = GameCanvas.loadImage("/interface/hpmp.png");
                imgEvent = GameCanvas.loadImage("/event.png");
                if (MainMenu.isPet) {
                    peticon = GameCanvas.loadImage("/peticon.png");
                }
                petinfo = GameCanvas.loadImage("/petinfo.png");
                imgNguhanh = GameCanvas.loadImage("/h.png");
                imgFly = new Image[4];
                i = 0;
                while (i < imgFly.length) {
                    GameScr.imgFly[i] = GameCanvas.loadImage("/text/" + i + ".png");
                    ++i;
                }
                ChangScr.load();
            }
        } catch (Exception e) {
            System.out.println(" loi load hinh GameScr");
        }
    }

    public void addChat(ChatInfo ci) {
        if (this.chatHistory.size() > 50) {
            this.chatHistory.removeElementAt(0);
            this.yMsgChat -= 18;
            this.toYchat -= 18;
        }
        this.chatHistory.addElement(ci);
        this.toYchat += 18;
    }

    public final void loadFlyText() {
        this.flyTextX = new int[15];
        this.flyTextY = new int[15];
        this.flyTextDx = new int[15];
        this.flyTextDy = new int[15];
        this.flyTextState = new int[15];
        this.flyTextColor = new int[15];
        this.flyTextString = new String[15];
        int i = 0;
        while (i < 15) {
            this.flyTextState[i] = -1;
            ++i;
        }
    }

    public static void addFlyText(String text, int x, int y, int colorText, boolean isFont3d) {
        FlyText t = new FlyText(text, x, y, colorText, isFont3d, disFlytext[tickFly]);
        tickFly = (tickFly + 1) % (disFlytext.length - 1);
        vFlytext.addElement(t);
    }

    public final void startFlyText(String flyString, int flyColor, int x, int y, int dx, int dy) {
        if (flyString.equals("-0") || flyString.equals("- 0")) {
            return;
        }
        int n = -1;
        int i = 0;
        while (i < 15) {
            if (this.flyTextState[i] == -1) {
                n = i;
                break;
            }
            ++i;
        }
        if (n == -1) {
            return;
        }
        this.flyTextString[n] = flyString;
        this.flyTextX[n] = x;
        this.flyTextY[n] = y;
        this.flyTextDx[n] = dx * (r.nextInt(2) == 1 ? -2 : 2);
        this.flyTextDy[n] = dy;
        this.flyTextState[n] = 0;
        this.flyTextColor[n] = flyColor;
    }

    public final void startFlyText(String flyString, int flyColor, int x, int y, int dx, int dy, int dir) {
        if (flyString.equals("-0") || flyString.equals("- 0")) {
            return;
        }
        int n = -1;
        int i = 0;
        while (i < 15) {
            if (this.flyTextState[i] == -1) {
                n = i;
                break;
            }
            ++i;
        }
        if (n == -1) {
            return;
        }
        this.flyTextString[n] = flyString;
        this.flyTextX[n] = x;
        this.flyTextY[n] = y;
        this.flyTextDx[n] = dx * dir;
        this.flyTextDy[n] = dy;
        this.flyTextState[n] = 0;
        this.flyTextColor[n] = flyColor;
    }

    public final void updateFlyText() {
        int i = 0;
        while (i < 15) {
            if (this.flyTextState[i] != -1) {
                int n = i;
                this.flyTextState[n] = this.flyTextState[n] + Util.abs(this.flyTextDy[i]);
                if (this.flyTextState[i] > 30) {
                    this.flyTextState[i] = -1;
                }
                int n2 = i;
                this.flyTextX[n2] = this.flyTextX[n2] + this.flyTextDx[i];
                int n3 = i;
                this.flyTextY[n3] = this.flyTextY[n3] + this.flyTextDy[i];
            }
            ++i;
        }
        i = 0;
        while (i < vFlytext.size()) {
            FlyText t = (FlyText) vFlytext.elementAt(i);
            if (t != null) {
                t.update();
                if (t.wantDestroy) {
                    vFlytext.removeElement(t);
                }
            }
            ++i;
        }
    }

    public void paintFlyText(mGraphics g) {
        int i = 0;
        while (i < 15) {
            if (this.flyTextState[i] != -1) {
                if (this.flyTextColor[i] != 0) {
                    if (this.flyTextColor[i] == 2) {
                        if (mSystem.isAndroid) {
                            mFont.tahoma_7_black.drawString(g, this.flyTextString[i], this.flyTextX[i] + 1,
                                    this.flyTextY[i] + 1, 0, false);
                            mFont.tahoma_7_red.drawString(g, this.flyTextString[i], this.flyTextX[i], this.flyTextY[i],
                                    0, false);
                        } else {
                            mFont.paintStaccato_red(g, this.flyTextString[i], this.flyTextX[i], this.flyTextY[i], 0,
                                    false);
                        }
                    } else if (mSystem.isAndroid) {
                        mFont.tahoma_7_black.drawString(g, this.flyTextString[i], this.flyTextX[i] + 1,
                                this.flyTextY[i] + 1, 0, false);
                        mFont.tahoma_7_yellow.drawString(g, this.flyTextString[i], this.flyTextX[i], this.flyTextY[i],
                                0, false);
                    } else {
                        mFont.paintStaccato_yeallow(g, this.flyTextString[i], this.flyTextX[i], this.flyTextY[i], 0,
                                false);
                    }
                } else if (mSystem.isAndroid) {
                    mFont.tahoma_7_black.drawString(g, this.flyTextString[i], this.flyTextX[i] + 1,
                            this.flyTextY[i] + 1, 0, false);
                    mFont.tahoma_7_yellow.drawString(g, this.flyTextString[i], this.flyTextX[i], this.flyTextY[i], 0,
                            false);
                } else {
                    mFont.paintStaccato_yeallow(g, this.flyTextString[i], this.flyTextX[i], this.flyTextY[i], 0, false);
                }
            }
            ++i;
        }
        i = 0;
        while (i < vFlytext.size()) {
            FlyText t = (FlyText) vFlytext.elementAt(i);
            if (t != null) {
                t.paint(g);
            }
            ++i;
        }
    }

    @Override
    public void init() {
        this.initTouchMove();
        this.tfChat.x = 2;
        this.tfChat.y = GameCanvas.h - (GameCanvas.isTouch ? 30 : 40);
        this.tfChat.width = GameCanvas.w - 4;
        if (mSystem.isPC) {
            this.tfChat.y -= 12;
            this.tfChat.x = 25;
            this.tfChat.width = GameCanvas.w - 4 - 46;
        }
        this.tfChat.setStringNull(T.chatcongcong);
        this.tfChatWorld.x = 2;
        this.tfChatWorld.y = GameCanvas.h - (GameCanvas.isTouch ? 30 : 40);
        this.tfChatWorld.width = GameCanvas.w - 4;
        this.tfChatWorld.height = 25;
        this.tfChatWorld.setStringNull(T.hoiKTG);
        if (mSystem.isPC) {
            this.tfChatWorld.y -= 12;
            this.tfChatWorld.x = 25;
            this.tfChatWorld.width = GameCanvas.w - 4 - 46;
        }
        if (this.posQuickSlot == null) {
            this.posQuickSlot = new Position[5];
            int i = 0;
            while (i < 5) {
                this.posQuickSlot[i] = new Position();
                ++i;
            }
        }
        this.posQuickSlot[0].x = GameCanvas.hw - 47;
        this.posQuickSlot[0].y = GameCanvas.h - 19;
        this.posQuickSlot[1].x = GameCanvas.hw - 28;
        this.posQuickSlot[1].y = GameCanvas.h - 19;
        this.posQuickSlot[2].x = GameCanvas.hw - 8;
        this.posQuickSlot[2].y = GameCanvas.h - 20;
        this.posQuickSlot[3].x = GameCanvas.hw + 12;
        this.posQuickSlot[3].y = GameCanvas.h - 19;
        this.posQuickSlot[4].x = GameCanvas.hw + 31;
        this.posQuickSlot[4].y = GameCanvas.h - 19;
        this.setPosMiniMap();
        this.isTouchMenu = false;
        this.cmdmenu = new mCommand("", this, 1);
        this.cmdskip = new mCommand("", this, 0);
        if (GameCanvas.isTouch) {
            this.cmdskip.setXY(5, 0);
            this.cmdskip.caption = T.boqua;
            this.cmdskip.setLine();
        }
        if (!GameCanvas.isTouch) {
            this.left = this.cmdmenu;
            this.cmdskip.caption = T.boqua;
        }
        this.cmdchat = new mCommand("", this, 3);
        this.cmdchat.setindexImage(5);
        this.cmdchat.setXY(GameCanvas.w - 25 + 1, hMiniMap);

        this.cmdGoiNap = new mcCmd("", this, 110);
        this.cmdGoiNap.setImgBase("/hd/menu/mqt_0.png");
        this.cmdGoiNap.setImgSelect("/hd/menu/mqt_1.png");
        this.cmdGoiNap.setXYWH(50, 43, 34, 34);

        this.cmdAuto = new mcCmd("", this, 111);
        this.cmdAuto.setImgBase("/hd/menu/auto_0.png");
        this.cmdAuto.setImgSelect("/hd/menu/auto_1.png");
        this.cmdAuto.setXYWH(10, 40, 34, 34);

        this.cmdVongQuay = new mcCmd("", this, 112); // chọn action id chưa dùng
        this.cmdVongQuay.setImgBase("/hd/menu/spin_0.png");
        this.cmdVongQuay.setImgSelect("/hd/menu/spin_1.png");

//         đặt cạnh nút mqt: lệch sang phải đúng bằng width + khoảng cách
        this.cmdVongQuay.setXYWH(50 + 34 + 4, 43, 34, 34); // +4 là khoảng hở

        this.cmdChangeFocus = new mCommand("", this, 7);
        this.cmdChangeFocus.setindexImage(6);
        this.cmdChangeFocus.setXY(GameCanvas.w - 8 - this.cmdChangeFocus.wCmd + 2, GameCanvas.h - 145);
        if (!GameCanvas.isTouch) {
            int mid;
            int xdiv = 35;
            postSkill = new int[5];
            GameScr.postSkill[2] = mid = GameCanvas.w / 2;
            GameScr.postSkill[1] = mid - xdiv;
            GameScr.postSkill[0] = mid - xdiv * 2;
            GameScr.postSkill[3] = mid + xdiv;
            GameScr.postSkill[4] = mid + xdiv * 2;
        }
        this.cmdCloseChat = new mCommand(T.close, this, 20);
        this.cmdDochat = new mCommand("Chat", this, 21);
    }

    public GameScr() {
        int[] nArray = new int[10];
        nArray[3] = 1;
        nArray[5] = 2;
        nArray[7] = 3;
        nArray[9] = 4;
        this.SLOTINDEXOFKEY = nArray;
        this.vecSkill = new mVector();
        this.indexSkill = 0;
        this.first = false;
        this.a = 0;
        this.b = 0;
        this.paintMapPopup = false;
        this.menuItems = new mVector();
        this.colorPaint = new int[]{16520709, 16499718, 396543, 1101907};
        this.idInfo = 0;
        this.indexTouch = 0;
        this.map = (short) -1;
        this.decriptNap = new mVector();
        this.syntaxNap = new mVector();
        this.centerNap = new mVector();
        this.tg = new Target();
        this.xBeginFrame = 0;
        this.yBeginFrame = 0;
        this.xTheendFrame = 0;
        this.itemPick = new mVector();
        this.yTheendFrame = 0;
        this.treeIndex = 0;
        this.actorIndex = 0;
        this.npcLimit = 0;
        int[] nArray2 = new int[3];
        nArray2[0] = 4;
        nArray2[2] = -4;
        this.xChange = nArray2;
        this.lastDir = 0;
        this.dir = 0;
        this.countStep = 0;
        this.moveToX = 0;
        this.moveToY = 0;
        this.vMonster = new mVector();
        this.xit = new int[]{814, 774, 854, 814, 774};
        this.yit = new int[]{259, 275, 319, 339, 319};
        this.xLogin = new int[]{5, 10, 15, 20, 25};
        this.yLogin = new int[]{5, 10, 15, 20, 25};
        this.xit_mons = new int[]{1004, 774, 854, 914, 774};
        this.count = 0;
        this.possIntroX = new int[]{-30, 30, -40, 40, -50, 50, -60, 60, -70, 70};
        this.possIntroY = new int[]{-10, 10, -15, 15, -20, 20, -25, 25, 5, -5};
        this.charlogin = new mVector();
        this.rangeAuto = 100;
        this.loadCamera();
        this.countQuest = 0;
        this.tfChat = new TField();
        this.tfChat.height = ScreenTeam.ITEM_HEIGHT + (GameCanvas.isTouch ? 8 : 2);
        this.tfChat.setFocus(true);
        timeremoveItem = 60;
        this.init();
        this.isBuffAuto = false;
        this.isAutoChangeFocus = true;
        this.loadFlyText();
        this.setPosMiniMap();
        this.HPBARW = 45;
        this.EXPBARW = 71;
        this.HPBARW_MONSTER = 50;
        if (this.HPBARW_MONSTER < 30) {
            this.HPBARW_MONSTER = 30;
        }
        this.xMsgServer = GameCanvas.w - 20;
        this.indexKeyTouchAuto = 0;
        this.WaitTips = mSystem.currentTimeMillis() + 60000L;
    }

    public void startIntro() {
        countdowm = 0L;
        startCountdow = false;
        bossfire = 0;
        steep = 0;
        isIntro = true;
        isSetinfo = false;
        EffectManager.lowEffects.removeAllElements();
        EffectManager.hiEffects.removeAllElements();
        this.cmdLogin = new mCommand(T.login, this, 106);
        if (GameCanvas.isTouch) {
            this.cmdLogin.setPos(GameCanvas.w - this.cmdLogin.wCmd / 2 - 17, 0);
            this.cmdLogin.setLine();
        }
    }

    public void setPosMiniMap() {
        if (Tilemap.imgMap == null) {
            return;
        }
        hMiniMap = GameCanvas.h / 5;
        if (hMiniMap > 100) {
            hMiniMap = 100;
        }
        this.posMiniMap = new Position(GameCanvas.hw + 50, GameCanvas.h - deltaY - hMiniMap);
        wMiniMap = GameCanvas.w - this.posMiniMap.x - 1;
        if (wMiniMap > 100) {
            wMiniMap = 100;
        }
        if (Tilemap.imgMap != null) {
            if (wMiniMap > Tilemap.imgMap.getWidth()) {
                wMiniMap = Tilemap.imgMap.getWidth();
            }
            if (hMiniMap > Tilemap.imgMap.getHeight()) {
                hMiniMap = Tilemap.imgMap.getHeight();
            }
        }
        this.posMiniMap.x = GameCanvas.w - wMiniMap;
        this.posMiniMap.y = 0;
    }

    public void loadCamera() {
        gsw = GameCanvas.w;
        gsh = GameCanvas.h;
        gshw = gsw >> 1;
        gshh = gsh >> 1;
        gswd3 = gsw / 3;
        gshd3 = gsh / 3;
        gsw2d3 = 2 * gsw / 3;
        gsh2d3 = 2 * gsh / 3;
        gsw3d4 = 3 * gsw / 4;
        gsh3d4 = 3 * gsh / 4;
        gswd6 = gsw / 6;
        gshd6 = gsh / 6;
        this.gssw = (gsw >> 4) + 2;
        this.gssh = (gsh >> 4) + 2;
        if (gsw % 24 != 0) {
            ++this.gssw;
        }
        cmxLim = (Tilemap.w << 4) - gsw;
        cmyLim = (Tilemap.h << 4) - gsh;
        if (mainChar != null) {
            cmx = cmtoX = GameScr.mainChar.x - gshw;
            cmy = cmtoY = GameScr.mainChar.y - gshh;
        }
        if (cmx < 0) {
            cmx = 0;
        }
        if (cmx > cmxLim) {
            cmx = cmxLim;
        }
        if (cmy < 0) {
            cmy = 0;
        }
        if (cmy > cmyLim) {
            cmy = cmyLim;
        }
        this.gssx = (cmx >> 4) - 1;
        if (this.gssx < 0) {
            this.gssx = 0;
        }
        this.gssy = cmy >> 4;
        this.gssxe = this.gssx + this.gssw;
        this.gssye = this.gssy + this.gssh;
        if (this.gssy < 0) {
            this.gssy = 0;
        }
        if (this.gssye > Tilemap.h - 1) {
            this.gssye = Tilemap.h - 1;
        }
        lockcmy = false;
        lockcmx = false;
        if (Tilemap.pxw < gsw + 32) {
            lockcmx = true;
            cmx = -(gsw - Tilemap.pxw) >> 1;
        }
        if (Tilemap.pxh < gsh) {
            lockcmy = true;
            cmy = -(gsh - Tilemap.pxh) >> 1;
        }
    }

    public final void updateCamera() {
        if (cmx != cmtoX && !lockcmx) {
            cmvx = cmtoX - cmx << 2;
            cmdx &= 0xF;
            if ((cmx += (cmdx += cmvx) >> 4) < 0) {
                cmx = 0;
            }
            if (cmx > cmxLim) {
                cmx = cmxLim;
            }
        }
        if (cmy != cmtoY && !lockcmy) {
            cmvy = cmtoY - cmy << 2;
            cmdy &= 0xF;
            if ((cmy += (cmdy += cmvy) >> 4) < 0) {
                cmy = 0;
            }
            if (cmy > cmyLim) {
                cmy = cmyLim;
            }
        }
        if (this.currentCmVibrate >= 0) {
            cmx += this.CMVIBRATEX[this.currentCmVibrate];
            cmy += this.CMVIBRATEY[this.currentCmVibrate];
            ++this.currentCmVibrate;
            if (this.currentCmVibrate == 4) {
                this.currentCmVibrate = -1;
            }
            if (cmx < 0) {
                cmx = 0;
            }
            if (cmx > cmxLim) {
                cmx = cmxLim;
            }
            if (cmy < 0) {
                cmy = 0;
            }
            if (cmy > cmyLim) {
                cmy = cmyLim;
            }
        }
        this.gssx = (cmx >> 4) - 1;
        if (this.gssx < 0) {
            this.gssx = 0;
        }
        this.gssy = cmy >> 4;
        this.gssxe = this.gssx + this.gssw;
        if (this.gssxe > Tilemap.w) {
            this.gssxe = Tilemap.w;
        }
        if (this.gssye >= Tilemap.h) {
            this.gssye = Tilemap.h;
        }
        this.gssye = this.gssy + this.gssh;
        if (this.gssy < 0) {
            this.gssy = 0;
        }
        if (this.gssye > Tilemap.h - 1) {
            this.gssye = Tilemap.h;
        }
    }

    public void updateMainCharWhenAuto() {
        if (!autoFight) {
            this.isAutoRangeWhenAuto = false;
            return;
        }
        if (timeStandWhenAuto - mSystem.currentTimeMillis() / 1000L <= 0L) {
            timeStandWhenAuto = mSystem.currentTimeMillis() / 1000L + 10L;
        }
    }

    void tesskill() {
        if (GameCanvas.isKeyPressed(5)) {
            this.doAttack(this.idskilltest, 0);
        }
        if (GameCanvas.isKeyPressed(0)) {
            this.idskilltest = (byte) (this.idskilltest + 1);
            if (this.idskilltest > 26) {
                this.idskilltest = 1;
            }
        }
        if (GameCanvas.isKeyPressed(9)) {
            this.idskilltest = (byte) (this.idskilltest - 1);
            if (this.idskilltest < 1) {
                this.idskilltest = (byte) 26;
            }
        }
        if (GameCanvas.isKeyPressed(7)) {
            GameScr.mainChar.clazz = (byte) (GameScr.mainChar.clazz + 1);
            if (GameScr.mainChar.clazz > 5) {
                GameScr.mainChar.clazz = 0;
            }
            mainChar.setInfoWearing(Char.idPartTest[GameScr.mainChar.clazz][0]);
        }
    }

    public void updateIntro() {
        Actor ac;
        int i;
        if (this.cmdskip != null && GameCanvas.isTouch && this.getCmdPointerLast(this.cmdskip)
                && Tilemap.isMapIntro()) {
            GameCanvas.isPointerJustRelease[0] = false;
            if (this.cmdskip != null) {
                this.cmdskip.performAction();
            }
        }
        if (this.cmdLogin != null && GameCanvas.isTouch && this.getCmdPointerLast(this.cmdLogin)
                && Tilemap.isMapIntro()) {
            GameCanvas.isPointerJustRelease[0] = false;
            if (this.cmdLogin != null) {
                this.cmdLogin.performAction();
            }
        }
        if (timepopup >= 0) {
            --timepopup;
        }
        if (!isSetinfo) {
            ++CountIntro;
        }
        if (!isSetinfo && CountIntro > 30) {
            this.createCharIntro();
            isSetinfo = true;
        }
        if (isIntro && CountIntro <= 90 && !fireIntro) {
            ++CountMoveFirst;
        }
        if (!fireIntro && CountMoveFirst > 90 && steep == 0) {
            if (timepopup <= 0 && !finishTalk) {
                this.doTalk();
            }
            if (timepopup <= 0 && finishTalk) {
                this.doTalk();
            }
            if (GameCanvas.isKeyPressed(5) || GameCanvas.isPointerClick[0]) {
                timepopup = 0;
                i = 0;
                while (i < vecCharintro.size()) {
                    ac = (Actor) vecCharintro.elementAt(i);
                    if (ac != null) {
                        ac.removechat();
                    }
                    ++i;
                }
            }
        }
        if (fireIntro && steep == 0) {
            this.doAttackChartest();
        }
        if (!fireIntro && steep == 4) {
            this.doAttackChartest();
        }
        if (steep == 1) {
            if (this.tickwait > 0) {
                --this.tickwait;
            }
            if (this.tickwait <= 0) {
                Bossintro = GameScr.createBossOffline();
                this.actors.addElement(Bossintro);
                SteepCount = 2;
                Bossintro.moveTo((short) 950, (short) 259);
                ++steep;
                mainChar.resetAllSkill();
                mainChar.moveTo((short) 854, (short) 259);
                MainChar.blockkey = true;
                i = 0;
                while (i < vecCharintro.size()) {
                    ac = (Actor) vecCharintro.elementAt(i);
                    if (ac != null) {
                        ac.resetAllSkill();
                        ac.moveTo((short) this.xit[i], (short) (this.yit[i] - 20));
                    }
                    ++i;
                }
            }
        }
        if (Bossintro != null && Bossintro.getState() == 2 && steep == 2) {
            GameScr.addChat(Bossintro, T.hungcanh[0], 20);
            timepopup = 20;
            ++steep;
        }
        if (steep == 3) {
            SteepCount = 2;
            if (timepopup <= 0) {
                this.doTalk();
            }
            if (clazz == 5 && timepopup <= 0) {
                ++steep;
            }
            Bossintro.setState(0);
        }
        if (Bossintro != null && steep == 4 && GameCanvas.gameTick % 25 == 0) {
            mVector ntarget = new mVector();
            int i2 = 0;
            while (i2 < this.actors.size()) {
                Actor ac2 = (Actor) this.actors.elementAt(i2);
                if (ac2 != null && ac2.catagory == 0 && Res.inRangeActor(Bossintro, ac2, 150) && ac2.getState() != 3) {
                    ntarget.addElement(ac2);
                }
                ++i2;
            }
            if (ntarget.size() > 0) {
                Bossintro.setmuntiTarget(ntarget);
                Bossintro.startAttack();
                bossfire = (byte) (bossfire + 1);
            }
        }
        if (bossfire >= 20 && mainChar.getState() != 3) {
            this.StartNewLightning(mainChar);
            mainChar.actorDie();
        }
        if (mainChar.getState() == 3) {
            int i3 = 0;
            while (i3 < this.actors.size()) {
                Actor ac3 = (Actor) this.actors.elementAt(i3);
                if (ac3 != null && !ac3.equals(mainChar) && ac3.catagory == 0 && ac3.getState() != 3) {
                    this.StartNewLightning(ac3);
                    ac3.resetAllSkill();
                    ac3.actorDie();
                    vecCharintro.removeElement(ac3);
                }
                ++i3;
            }
        }
        if (bossfire >= 20 && !startCountdow) {
            GameScr.addChat(Bossintro, T.hungcanh[2], 200);
            Bossintro.moveTo(GameScr.mainChar.x, GameScr.mainChar.y);
            countdowm = 5000L + mSystem.currentTimeMillis();
            startCountdow = true;
            ++steep;
            Bossintro.setState(0);
        }
        if (startCountdow && countdowm - mSystem.currentTimeMillis() <= 0L) {
            CharSelectScr.gI().switchToMe();
            vecCharintro.removeAllElements();
            this.actors.removeAllElements();
            EffectManager.lowEffects.removeAllElements();
            EffectManager.hiEffects.removeAllElements();
            GameData.listbyteData.clear();
            GameData.listImgIcon.clear();
            Bossintro = null;
            isIntro = false;
        }
        if (Bossintro != null) {
            this.focusedActor = Bossintro;
        }
    }

    public static boolean isInScreen(Actor obj) {
        return obj.getX() >= cmx - obj.getWidth() && obj.getX() <= cmx + GameCanvas.w + obj.getWidth()
                && obj.getY() >= cmy - obj.getHeight() && obj.getY() <= cmy + GameCanvas.h + obj.getHeight() * 3 / 2;
    }

    public static boolean isInScreen(int x, int y, int w, int h) {
        return x >= cmx - w && x <= cmx + GameCanvas.w && y >= cmy - h && y <= cmy + GameCanvas.h;
    }

    public static void playNew() {
        arrowsUp.removeAllElements();
        GameCanvas.gameScr.actors.removeAllElements();
        vecCharintro.removeAllElements();
        GameCanvas.gameScr.charlogin.removeAllElements();
        EffectManager.lowEffects.removeAllElements();
        EffectManager.hiEffects.removeAllElements();
        String add = ServerListScr.address[ServerListScr.index];
        String port = String.valueOf(ServerListScr.port[ServerListScr.index]);
        GameCanvas.gameScr.onMapOffline(39, 0, 0);
        GameCanvas.gameScr.startIntro();
        String user = "-1";
        String pass = "-1";
        GameCanvas.loginScr.doLogin2(user, pass, add, port);
        CountIntro = 0;
        Next = 0;
        CountMoveFirst = 0;
        finishTalk = false;
        co = 0;
        timepopup = 0;
        SteepCount = 0;
        chatcount = 0;
        clazz = 0;
        canmove = false;
        ismovefirst = false;
        fireIntro = false;
        tam.removeAllElements();
    }

    public void updateParty() {
    }

    @Override
    public void update() {
        try {
            Actor ac;
            IArrow a;
            if (!(GameScr.mainChar.hp > 0 && mainChar.getState() != 3
                    || !GameCanvas.isPointerClick[0] && !GameCanvas.isKeyPressed(5) || GameCanvas.menu2.isShow)) {
                this.comHome();
            }
            if (!Session_ME.gI().connected && !this.isDis && !isIntro && GameCanvas.currentScreen != null
                    && GameCanvas.currentScreen.equals(GameCanvas.gameScr)) {
                this.isDis = true;
                this.onDisconnect("gamescr");
            }
            this. updateClound();
            if (this.tickHP >= 0) {
                --this.tickHP;
            }
            if (this.tickMP >= 0) {
                --this.tickMP;
            }
            if (this.tickpaintFont >= 0) {
                --this.tickpaintFont;
            }
            if (this.timeQouckSlot >= 0) {
                --this.timeQouckSlot;
            }
            this.tickicon = (this.tickicon + 1) % 40;
            this.updateEvent();
            this.updateTime();
            if (charcountdonw != null) {
                charcountdonw.update();
                if (GameScr.charcountdonw.WantDestroy) {
                    charcountdonw = null;
                }
            }
            if (GameCanvas.isTouch && GameCanvas.currentScreen == this && !isIntro) {
                if (this.cmdchat != null && this.getCmdPointerLast(this.cmdchat)) {
                    GameCanvas.isPointerJustRelease[0] = false;
                    if (this.cmdchat != null) {
                        this.cmdchat.performAction();
                    }
                }
                if (this.cmdChangeFocus != null && !mSystem.isPC && this.getCmdPointerLast(this.cmdChangeFocus)) {
                    GameCanvas.isPointerJustRelease[0] = false;
                    if (this.cmdChangeFocus != null) {
                        this.cmdChangeFocus.performAction();
                    }
                }

                if (cmdAuto != null) {
                    cmdAuto.update();
                }

                if (cmdGoiNap != null) {
                    cmdGoiNap.update();
                }
                if (cmdVongQuay != null) {
                    cmdVongQuay.update();
                }
            }
            if (isIntro) {
                this.updateIntro();
            }
            if (--timeRemovePos < 0) {
                timeRemovePos = 0;
            }
            if (this.tg != null) {
                this.tg.update();
            }
            this.updateCamera();
            cmtoX = GameScr.mainChar.x - gshw + DELTACAMERAX[GameScr.mainChar.dir];
            cmtoY = GameScr.mainChar.y - gshh + DELTACAMERAY[GameScr.mainChar.dir] - 20;
            this.updateCmMini();
            int i = arrowsUp.size() - 1;
            while (i >= 0) {
                a = (IArrow) arrowsUp.elementAt(i);
                if (a != null) {
                    a.update();
                    if (a.wantDestroy) {
                        arrowsUp.removeElementAt(i);
                    }
                }
                --i;
            }
            i = this.arrowsDown.size() - 1;
            while (i >= 0) {
                a = (IArrow) this.arrowsDown.elementAt(i);
                if (a != null) {
                    a.update();
                    if (a.wantDestroy) {
                        this.arrowsDown.removeElementAt(i);
                    }
                }
                --i;
            }
            PosNPCQuest.removeAllElements();
            mVector removeactor = new mVector();
            int i2 = this.actors.size() - 1;
            while (i2 >= 0) {
                Actor a2 = (Actor) this.actors.elementAt(i2);
                if (a2 != null) {
                    if (a2.wantDestroy) {
                        if (a2.equals(this.focusedActor)) {
                            this.focusedActor = null;
                        }
                        removeactor.addElement(a2);
                    } else {
                        if (a2.isNPC()) {
                            this.getAllPosNPCMInimap(a2);
                        }
                        if (a2.catagory == 2 || a2.catagory == 10 || a2.catagory == 11 || a2.catagory == 12) {
                            a2.update();
                        } else {
                            a2.update();
                            if (a2.wantDestroy && a2.getTimeLive() == -1) {
                                if (this.focusedActor != null && a2 != null && this.focusedActor.equals(a2)) {
                                    this.focusedActor = null;
                                }
                                if (!(isFindChar || isActorMove || isFindMonster || a2.catagory == 100)) {
                                    if (a2.catagory == 0) {
                                        a2.setDie();
                                    }
                                    removeactor.addElement(a2);
                                }
                            }
                        }
                    }
                }
                --i2;
            }
            Util.quickSort(this.actors);
            if (GameCanvas.gameTick % 3 == 0 && (ac = this.FindFocusActorAuto()) != null && ac.canFocusMonster()
                    && GameCanvas.currentDialog == null && ac.catagory != 0 && !ac.isNPC()) {
                this.focusedActor = ac;
            }
            if (this.focusedActor != null && (this.focusedActor.catagory == 100 || this.focusedActor.wantDestroy)) {
                this.focusedActor = null;
            }
            EffectManager.lowEffects.updateAll();
            EffectManager.hiEffects.updateAll();
            this.updateFlyText();
            if (this.pingNextIn > 0) {
                --this.pingNextIn;
                if (this.pingNextIn == 0) {
                    this.lastTimePing = mSystem.currentTimeMillis();
                }
            }
            if (this.chatMode && GameCanvas.currentScreen == this) {
                this.tfChat.update();
            }
            if (this.chatWorld && GameCanvas.currentScreen == this) {
                this.tfChatWorld.update();
            }
            if (this.indexBuff != -1 && timeBuff != -1L && mSystem.currentTimeMillis() > timeBuff) {
                this.indexBuff = -1;
                timeBuff = -1L;
            }
            if (xSlot > 0 && (xSlot -= Res.wKhung / 10) < 0) {
                xSlot = 0;
            }
            i = 0;
            while (i < removeactor.size()) {
                if (removeactor.elementAt(i) != null) {
                    this.actors.removeElement(removeactor.elementAt(i));
                }
                ++i;
            }
            this.updateMsgServer();
            this.setRemoveIconImg();
            this.timerung = this.timerung > 0 ? --this.timerung : 0;
            if (Tilemap.isOfflineMap && this.map == 29) {
                if (GameScr.mainChar.x >= this.xBeginFrame - 16 && GameScr.mainChar.x <= this.xTheendFrame + 40
                        && GameScr.mainChar.y >= this.yBeginFrame - 16
                        && GameScr.mainChar.y <= this.yTheendFrame + 40) {
                    this.focusedActor = this.isFocusMyGround(mainChar);
                } else {
                    int size6 = this.actors.size();
                    Actor obj = null;
                    int i3 = 0;
                    while (i3 < size6) {
                        obj = (Actor) this.actors.elementAt(i3);
                        if (obj != null && obj.catagory == 10) {
                            obj.setIsFocus(false);
                        }
                        ++i3;
                    }
                }
            }
            if (savemsgWorld.size() > 30) {
                savemsgWorld.removeElementAt(0);
            }
            if (GameScr.mainChar.state != 3 && autoFight && GameCanvas.currentScreen instanceof GameScr
                    && GameCanvas.currentDialog != null && !GameScr.mainChar._isDie && Tilemap.lv != 0
                    && Tilemap.lv != 201 && Tilemap.lv != 70 && Tilemap.lv != 80) {
                GameCanvas.currentDialog = null;
            }
            if (Tilemap.lv == 0 || Tilemap.lv == 70 || Tilemap.lv == 80) {
                autoFight = false;
            }
            if (GameScr.mainChar.posTransRoad != null && this.posCam != null) {
                this.tg.x = (short) this.posCam.x;
                this.tg.y = (short) this.posCam.y;
            }
            if (!mainChar.isDie() && GameCanvas.menuSelectitem.isAutoDanh[0] && this.beginAuto
                    && GameScr.mainChar.state != 3) {
                this.AutoDanh();
                this.AutoBuff();
                this.AutoNhat();
            }
            if (GameCanvas.menuSelectitem.isTabFocus[4] && !mainChar.isDie()) {
                this.AutoPetEat();
            }
            if (GameCanvas.menuSelectitem.isTabFocus[6] && mainChar.hp <= 0) {
                this.AutoHoiSinh();
            }
            if (GameCanvas.menuSelectitem.isTabFocus[5] && !mainChar.isDie()) {
                this.AutoSell();
            }
            if (GameCanvas.menuSelectitem.isAutoDanh[1]) {
                this.AutoBomHP(GameCanvas.menuSelectitem.indexPerHp);
            }
            if (GameCanvas.menuSelectitem.isAutoDanh[3]) {
                this.AutoBomMP(GameCanvas.menuSelectitem.indexPerMp);
            }
            if (GameCanvas.gameTick % 20 == 0) {
                DataSkillEff.checkremove();
            }
            if (numMSG > 0 && GameCanvas.isTouch && mainChar.getState() != 3 && GameCanvas.isPointerClick[0]
                    && GameCanvas.isPointer(GameCanvas.w - 5 - wMiniMap - imgMSG.getWidth(),
                    hMiniMap - imgMSG.getHeight() / 2, 18, 20, 0)) {
                int t;
                numMSG = 0;
                if (this.eventShow != null && (t = EventScreen.setIndexEvent(this.eventShow.nameEvent,
                        (byte) this.eventShow.IDCmd)) >= 0) {
                    GameCanvas.mevent.idSelect = t;
                }
                GameCanvas.mevent.init();
                GameCanvas.mevent.switchToMe(this);
                GameCanvas.isPointerClick[0] = false;
            }
            if (GameCanvas.isKeyPressed(10) && !this.chatMode) {
                this.chatMode = true;
                this.right = this.tfChat.cmdClear;
                this.left = this.cmdCloseChat;
                this.tfChat.clearAllText();
                this.center = this.cmdDochat;
                GameCanvas.clearKeyHold();
            }
            if (GameCanvas.isKeyPressed(10) && !this.chatWorld) {
                this.chatMode = true;
                this.right = this.tfChatWorld.cmdClear;
                this.left = this.cmdCloseChat;
                this.tfChatWorld.clearAllText();
                this.center = this.cmdDochat;
                GameCanvas.clearKeyHold();
            }
            if (Ghost != null) {
                if (this.focusedActor == null) {
                    this.focusedActor = Ghost;
                }
                if (this.focusedActor != null && !this.focusedActor.equals(Ghost)) {
                    this.focusedActor = Ghost;
                }
            }
            if (!(this.chatMode || GameCanvas.menu.showMenu || GameCanvas.menu2.isShow
                    || GameCanvas.menuSelectitem.showMenu || GameCanvas.currentDialog != null || MainMenu.isShow
                    || isIntro || GameCanvas.currentScreen != this || !GameCanvas.isKeyPressed(13))) {
                this.cmdChangeFocus.performAction();
            }
            if (hShowEvent <= 0 && isQuest && GameCanvas.isPointerClick[0] && GameCanvas.isTouch && !isIntro
                    && mainChar.getState() != 3 && GameCanvas.currentScreen == this
                    && (this.focusedActor == null || this.focusedActor != null && this.focusedActor.isNPC())
                    && GameCanvas.isPointer(GameCanvas.w - mGraphics.getImageWidth(imgPointQuest) - 5 - wMiniMap, 5, 30,
                    30, 0)) {
                MainMenu.isBeginQuest = true;
                MainMenu.delay = 5;
                MainMenu.gI().switchToMe(this);
                MainMenu.gI().PutItemSHop(shop);
                this.countQuest = 0;
            }
            super.updateKey();
            if (this.cmdDisconect != null) {
                ++this.tickConnect;
                if (this.tickConnect > 10) {
                    this.cmdDisconect.performAction();
                    this.cmdDisconect = null;
                    this.tickConnect = 0;
                }
            }
            this.updateParty();
            if (effAnimate.size() > 0) {
                AnimateEffect.updateWind();
                AnimateEffect ani = null;
                int i4 = 0;
                while (i4 < effAnimate.size()) {
                    ani = (AnimateEffect) effAnimate.elementAt(i4);
                    if (ani != null) {
                        ani.update();
                    }
                    ++i4;
                }
            }
        } catch (Exception exception) {
            // empty catch block
        }
    }

    private void AutoSell() {
        try {
            List<Item> items = new ArrayList<>();
            for (int i = 0; i < Char.inventory.size(); i++) {
                Item it = (Item) Char.inventory.elementAt(i);
                if (it != null && it.plus == 0 && it.colorname < 4 && MainMenu.POS_ITEM_IN_EQUIP[it.type] > -1
                        && MainMenu.POS_ITEM_IN_EQUIP[it.type] < 10) {
                    items.add(it);
                }
            }
            if (items.size() == 0)
                return;
            for (Item item : items) {
                GameService.gI().sellItem((byte) 0, item.ID);
            }
        } catch (Exception e) {

        }
    }

    private void AutoHoiSinh() {
        try {
            if (GameScr.mainChar.luong < 2 || GameScr.mainChar.hp > 0)
                return;
            countRevive++;
            if(countRevive == 1){
                lastTimeDie = mSystem.currentTimeMillis() + 1000;
            }else if(countRevive > 1 &&lastTimeDie < mSystem.currentTimeMillis()) {
                countRevive = 0;
                GameService.gI().Dynamic_Menu((short) 9994, (byte) 0, (byte) 2);
            }
        } catch (Exception e) {

        }
    }

    public void updatefocus() {
        if (isIntro) {
            this.fFocus = 0;
            return;
        }
        if (this.isAutoChangeFocus) {
            if (this.focusedActor != null && this.focusedActor.catagory == 0) {
                this.focusedActor.beFire();
            }
            if (this.focusedActor != null && this.focusedActor.isNPC()
                    && (this.focusedActor.isBangKhu() || this.focusedActor.isTruRong())
                    && Utils.getDistance(mainChar, this.focusedActor) > 60) {
                this.focusedActor = null;
            }
            if (this.focusedActor != null && Utils.getDistance(mainChar, this.focusedActor) > 192) {
                this.focusedActor = null;
                this.isAutoChangeFocus = false;
            }
        }
        if (this.focusedActor != null) {
            if (this.focusedActor.catagory == 0 || this.focusedActor.catagory == 1) {
                if (this.focusedActor.getMaxHp() > 0) {
                    long objFocushp = this.focusedActor.getHp();
                    long tile = 4L;
                    long maxHP = objFocushp * tile;
                    this.fFocus = (byte) (maxHP / (long) this.focusedActor.getMaxHp());
                    if (this.fFocus > 3) {
                        this.fFocus = 3;
                    }
                    this.fFocus = (byte) Math.abs(this.fFocus - 3);
                }
            } else {
                this.fFocus = 0;
            }
        } else {
            this.fFocus = 0;
        }
        if (Ghost == null) {
            if (GameCanvas.currentDialog != null) {
                return;
            }
            if ((this.focusedActor == null
                    || this.focusedActor != null && Utils.getDistance(mainChar, this.focusedActor) > 192)
                    && !this.isAutoChangeFocus) {
                this.isAutoChangeFocus = true;
            }
            if (this.waitFocus >= 0) {
                --this.waitFocus;
            }
            if (this.isAutoChangeFocus && this.waitFocus <= 0 && !this.beginAuto) {
                Actor ac1;
                int min = 100000000;
                int index = -1;
                this.waitFocus = 30;
                int i = 0;
                while (i < this.actors.size()) {
                    int dist;
                    Actor ac = (Actor) this.actors.elementAt(i);
                    if (ac != null && !ac.equals(mainChar) && (ac.catagory != 0 || ac.isNPC() || ac.beFire())
                            && (ac.catagory != 1 || ac.getState() != 1 && ac.getState() != 5 && ac.getState() != 8
                            && ac.getTypeMove() != 4 && ac.getTypeMove() != 6)
                            && (dist = Utils.getDistance(mainChar, ac)) < min) {
                        min = dist;
                        index = i;
                    }
                    ++i;
                }
                if (index == -1) {
                    this.isAutoChangeFocus = false;
                }
                if (index != -1 && !(ac1 = (Actor) this.actors.elementAt(index)).equals(this.focusedActor)) {
                    if (ac1.catagory == 1 && (ac1.getState() == 1 || ac1.getState() == 5 || ac1.getState() == 8
                            || ac1.getTypeMove() == 4 || ac1.getTypeMove() == 6)) {
                        return;
                    }
                    if (ac1.isNPC() && Utils.getDistance(ac1, mainChar) > 60) {
                        return;
                    }
                    if (ac1.canFocus()) {
                        this.focusedActor = ac1;
                    }
                }
            }
        }
    }

    public void updateTime() {
        int i = 0;
        while (i < VecTime.size()) {
            TimecountDown t = (TimecountDown) VecTime.elementAt(i);
            if (t != null) {
                t.update();
                if (t.wantdestroy) {
                    VecTime.removeElement(t);
                }
            }
            ++i;
        }
    }

    public void paintCharParty(mGraphics g) {
        int size = this.vecCharParty.size();
        if (size > 0) {
            int i = 0;
            while (i < size) {
                Actor ac = (Actor) this.vecCharParty.elementAt(i);
                if (ac != null) {
                    mFont.tahoma_7_black.drawString(g, ac.getName(), 3, 50 + i * 20 + 1 - 3, 0, false);
                    mFont.tahoma_7_white.drawString(g, ac.getName(), 2, 50 + i * 20 - 3, 0, false);
                    int w = 30;
                    g.setColor(0);
                    g.fillRect(2, 62 + i * 20 - 3, w, 3, false);
                    int wpPaint = 0;
                    long tile = w;
                    long objFocushp = ac.getHp();
                    long maxHp = tile * objFocushp;
                    wpPaint = (int) (maxHp / (long) ac.getMaxHp());
                    if (wpPaint <= 3) {
                        wpPaint = 3;
                    } else if (wpPaint > w) {
                        wpPaint = w;
                    }
                    g.setColor(-8780515);
                    g.fillRect(3, 62 + i * 20 + 1 - 3, w - 2, 1, false);
                    g.setColor(-645304);
                    g.fillRect(3, 62 + i * 20 + 1 - 3, wpPaint - 2, 1, false);
                }
                ++i;
            }
        }
    }

    public void paintTime(mGraphics g) {
        int i = 0;
        while (i < VecTime.size()) {
            TimecountDown t = (TimecountDown) VecTime.elementAt(i);
            if (t != null) {
                t.paint(g, GameCanvas.w, hMiniMap + i * 18 + imgButton[5].getHeight() / 2);
            }
            ++i;
        }
    }

    public boolean isPointerPressInside(int x, int y, int w, int h, int type) {
        if (type == 0) {
            this.isBigFocus = false;
        } else if (type == 1) {
            this.isTouchChat = false;
        }
        if (GameCanvas.isPointerHoldIn(x, y, w, h, 0)) {
            if (GameCanvas.isPointerDown[0]) {
                if (type == 0) {
                    this.isBigFocus = true;
                } else if (type == 1) {
                    this.isTouchChat = true;
                }
            }
            if (GameCanvas.isPointerJustRelease[0] && GameCanvas.isPointerClick[0]) {
                return true;
            }
        }
        return false;
    }

    public Actor isFocusMyGround(Actor ac) {
        Actor result = null;
        if (Tilemap.isOfflineMap) {
            int i = 0;
            while (i < this.actors.size()) {
                Actor obj = (Actor) this.actors.elementAt(i);
                if (obj.catagory == 10) {
                    if (ac.x >= obj.x + 2 && ac.x <= obj.x + 30 && ac.y >= obj.y && ac.y + 2 <= obj.y + 30) {
                        obj.setIsFocus(true);
                        result = obj;
                    } else {
                        obj.setIsFocus(false);
                    }
                }
                ++i;
            }
        }
        return result;
    }

    private void updateMsgServer() {
        if (this.listMSGServer != null && this.listMSGServer.size() > 0) {
            this.xMsgServer -= 2;
            String str = (String) this.listMSGServer.elementAt(0);
            if (this.xMsgServer + FontTeam.arialFontW.getWidth(str) < 0) {
                this.listMSGServer.removeElementAt(0);
                this.xMsgServer = GameCanvas.w - 20;
            }
        }
    }

    private void setRemoveIconImg() {
        Enumeration k = GameData.listImgIcon.keys();
        while (k.hasMoreElements()) {
            String key = (String) k.nextElement();
            ImageIcon img = (ImageIcon) GameData.listImgIcon.get(key);
            if (img.isLoad || mSystem.currentTimeMillis() - img.timeRemove < 0L)
                continue;
            img.reset();
            GameData.listImgIcon.remove(key);
        }
    }

    public static int getIdImgQuest(int idnpc) {
        int type;
        String str = (String) hashQuestFinish.get(String.valueOf(idnpc));
        String str2 = (String) hashQuestWorking.get(String.valueOf(idnpc));
        String str3 = (String) hashQuestCanReceive.get(String.valueOf(idnpc));
        if (str != null) {
            type = -1;
            try {
                type = Integer.parseInt(str);
            } catch (Exception e) {
                type = -1;
            }
            if (type == 1) {
                return 2;
            }
        }
        if (str2 != null) {
            type = -1;
            try {
                type = Integer.parseInt(str2);
            } catch (Exception e) {
                type = -1;
            }
            if (type != 1) {
                return 1;
            }
        }
        if (str3 != null) {
            type = -1;
            try {
                type = Integer.parseInt(str3);
            } catch (Exception e) {
                type = -1;
            }
            if (type != 1) {
                return 0;
            }
        }
        return -1;
    }

    private void updateCmMini() {
        if (Tilemap.w >= wMiniMap || Tilemap.h >= hMiniMap) {
            if (cmyMini != cmtoYmini) {
                cmvyMini = cmtoYmini - cmyMini << 2;
                cmyMini += (cmdyMini += cmvyMini) >> 4;
                cmdyMini &= 0xF;
            }
            if (cmxMini != cmtoXMini) {
                cmvxMini = cmtoXMini - cmxMini << 2;
                cmxMini += (cmdxMini += cmvxMini) >> 4;
                cmdxMini &= 0xF;
            }
        }
    }

    public boolean isAutoFight() {
        return !mainChar.isDie() && GameCanvas.menuSelectitem.isAutoDanh[0] && !Tilemap.ismapLang && this.beginAuto;
    }

    public Actor FindFocusActorAuto() {
        int left = GameScr.mainChar.x + C[GameScr.mainChar.dir][0];
        int right = GameScr.mainChar.x + C[GameScr.mainChar.dir][1];
        int up = GameScr.mainChar.y + C[GameScr.mainChar.dir][2];
        int down = GameScr.mainChar.y + C[GameScr.mainChar.dir][3];
        int shorted = 10000;
        int shortedIndex = -1;
        this.nearActors.removeAllElements();
        int size0 = this.actors.size();
        Actor a = null;
        int i = 0;
        while (i < size0) {
            a = (Actor) this.actors.elementAt(i);
            if (!(a.x <= left || a.x >= right || a.y <= up || a.y >= down
                    || a.catagory == 0 && a.ID == GameScr.mainChar.ID
                    || a.isMonster() && (a.getState() == 1 || a.getState() == 5 || a.getState() == 8) || !a.canFocus()
                    || (a.isBangKhu() || a.isTruRong()) && Utils.getDistance(a, mainChar) < 32
                    || this.isAutoFight() && (a.getState() == 3 || a.catagory == 3 && mainChar.isFullInven())
                    || !mainChar.isDie() && GameCanvas.menuSelectitem.isAutoDanh[0] && !Tilemap.ismapLang
                    && this.beginAuto && a.catagory > 2 && mainChar.isFullInven()
                    || (typeOptionFocus == 1
                    ? a.catagory == 0 && (!a.isKiller() || a.isNPC())
                    || a.catagory != 1 && a.catagory == 0 && !a.isKiller()
                    : (typeOptionFocus == 2 ? !a.isNPC()
                    : (typeOptionFocus == 3
                    ? a.catagory == 0 && a.getIdClan() == GameScr.mainChar.idClan || a.isNPC()
                    : typeOptionFocus == 4
                    && (a.catagory == 0 && a.getNation() == GameScr.mainChar.nationID
                    || a.isNPC())))))) {
                this.nearActors.addElement(a);
                int distance = Util.abs(GameScr.mainChar.x - a.x) + Util.abs(GameScr.mainChar.y - a.y);
                if (a.catagory == 3 || a.catagory == 4) {
                    distance <<= 1;
                }
                if (distance < shorted) {
                    shorted = distance;
                    shortedIndex = i;
                }
            }
            ++i;
        }
        if (shortedIndex == -1) {
            return null;
        }
        if (this.focusedActor == null && shortedIndex < this.actors.size()) {
            return (Actor) this.actors.elementAt(shortedIndex);
        }
        if (!GameCanvas.instance.hasPointerEvents() && shortedIndex < this.actors.size()) {
            if (this.nearActors.contains(this.focusedActor)) {
                return this.focusedActor;
            }
            return (Actor) this.actors.elementAt(shortedIndex);
        }
        if (Util.distance(GameScr.mainChar.x, GameScr.mainChar.y, this.focusedActor.x,
                this.focusedActor.y) > GameCanvas.w / 2) {
            this.focusedActor = null;
        }
        return this.focusedActor;
    }

    public Actor findFocusActor() {
        int left = GameScr.mainChar.x + C[GameScr.mainChar.dir][0];
        int right = GameScr.mainChar.x + C[GameScr.mainChar.dir][1];
        int up = GameScr.mainChar.y + C[GameScr.mainChar.dir][2];
        int down = GameScr.mainChar.y + C[GameScr.mainChar.dir][3];
        int shorted = 10000;
        int shortedIndex = -1;
        this.nearActors.removeAllElements();
        int size0 = this.actors.size();
        Actor a = null;
        int i = 0;
        while (i < size0) {
            a = (Actor) this.actors.elementAt(i);
            if (!(a.x <= left || a.x >= right || a.y <= up || a.y >= down
                    || a.catagory == 0 && a.ID == GameScr.mainChar.ID
                    || a.isMonster() && (a.getState() == 1 || a.getState() == 5 || a.getState() == 8) || !a.canFocus()
                    || (a.isBangKhu() || a.isTruRong()) && Utils.getDistance(a, mainChar) < 32 || a.getState() == 8
                    || this.isAutoFight() && (a.getState() == 3 || a.catagory == 3 && mainChar.isFullInven())
                    || !mainChar.isDie() && GameCanvas.menuSelectitem.isAutoDanh[0] && !Tilemap.ismapLang
                    && this.beginAuto && a.catagory > 2 && mainChar.isFullInven()
                    || (typeOptionFocus == 1
                    ? a.catagory == 0 && (!a.isKiller() || a.isNPC())
                    || a.catagory != 1 && a.catagory == 0 && !a.isKiller()
                    : (typeOptionFocus == 2 ? !a.isNPC()
                    : (typeOptionFocus == 3
                    ? a.catagory == 0 && a.getIdClan() == GameScr.mainChar.idClan || a.isNPC()
                    : typeOptionFocus == 4
                    && (a.catagory == 0 && a.getNation() == GameScr.mainChar.nationID
                    || a.isNPC())))))) {
                this.nearActors.addElement(a);
                int distance = Util.abs(GameScr.mainChar.x - a.x) + Util.abs(GameScr.mainChar.y - a.y);
                if (a.catagory == 3 || a.catagory == 4) {
                    distance <<= 1;
                }
                if (distance < shorted) {
                    shorted = distance;
                    shortedIndex = i;
                }
            }
            ++i;
        }
        if (shortedIndex == -1) {
            return null;
        }
        if (this.focusedActor == null && shortedIndex < this.actors.size()) {
            return (Actor) this.actors.elementAt(shortedIndex);
        }
        if (!GameCanvas.instance.hasPointerEvents() && shortedIndex < this.actors.size()) {
            if (this.nearActors.contains(this.focusedActor)) {
                return this.focusedActor;
            }
            return (Actor) this.actors.elementAt(shortedIndex);
        }
        if (Util.distance(GameScr.mainChar.x, GameScr.mainChar.y, this.focusedActor.x,
                this.focusedActor.y) > GameCanvas.w / 2) {
            this.focusedActor = null;
        }
        return this.focusedActor;
    }

    public Actor findFocusActorTouch() {
        int left = GameScr.mainChar.x + C[GameScr.mainChar.dir][0];
        int right = GameScr.mainChar.x + C[GameScr.mainChar.dir][1];
        int up = GameScr.mainChar.y + C[GameScr.mainChar.dir][2];
        int down = GameScr.mainChar.y + C[GameScr.mainChar.dir][3];
        int shorted = 10000;
        int shortedIndex = -1;
        this.nearActors.removeAllElements();
        int size0 = this.actors.size();
        Actor a = null;
        int i = 0;
        while (i < size0) {
            a = (Actor) this.actors.elementAt(i);
            if (!(a.x <= left || a.x >= right || a.y <= up || a.y >= down
                    || a.catagory == 0 && a.ID == GameScr.mainChar.ID
                    || a.isMonster() && (a.getState() == 1 || a.getState() == 5 || a.getState() == 8) || !a.canFocus()
                    || a.getState() == 8
                    || this.isAutoFight() && (a.getState() == 3 || a.catagory == 3 && mainChar.isFullInven())
                    || !mainChar.isDie() && GameCanvas.menuSelectitem.isAutoDanh[0] && !Tilemap.ismapLang
                    && this.beginAuto && a.catagory > 2 && mainChar.isFullInven()
                    || (typeOptionFocus == 1
                    ? a.catagory == 0 && (!a.isKiller() || a.isNPC())
                    || a.catagory != 1 && a.catagory == 0 && !a.isKiller()
                    : (typeOptionFocus == 2 ? !a.isNPC()
                    : (typeOptionFocus == 3
                    ? a.catagory == 0 && a.getIdClan() == GameScr.mainChar.idClan || a.isNPC()
                    : typeOptionFocus == 4
                    && (a.catagory == 0 && a.getNation() == GameScr.mainChar.nationID
                    || a.isNPC())))))) {
                this.nearActors.addElement(a);
                int distance = Util.abs(GameScr.mainChar.x - a.x) + Util.abs(GameScr.mainChar.y - a.y);
                if (a.catagory == 3 || a.catagory == 4) {
                    distance <<= 1;
                }
                if (distance < shorted) {
                    shorted = distance;
                    shortedIndex = i;
                }
            }
            ++i;
        }
        if (shortedIndex == -1) {
            return null;
        }
        if (this.focusedActor == null && shortedIndex < this.actors.size()) {
            return (Actor) this.actors.elementAt(shortedIndex);
        }
        if (!GameCanvas.instance.hasPointerEvents() && shortedIndex < this.actors.size()) {
            if (this.nearActors.contains(this.focusedActor)) {
                return this.focusedActor;
            }
            return (Actor) this.actors.elementAt(shortedIndex);
        }
        if (Util.distance(GameScr.mainChar.x, GameScr.mainChar.y, this.focusedActor.x,
                this.focusedActor.y) > GameCanvas.w / 2) {
            this.focusedActor = null;
        }
        return this.focusedActor;
    }

    public void changeToNextFocusActor(boolean isBack) {
        int i;
        int nextIndex;
        if (this.focusedActor == null) {
            this.focusedActor = this.findFocusActor();
            if (this.focusedActor != null && this.focusedActor.catagory == 100) {
                this.focusedActor = null;
            }
            return;
        }
        if (isBack && !this.focusedActor.isKiller() && this.isAutoFight() && typeOptionFocus == 1) {
            this.focusedActor = this.findFocusActor();
            if (this.focusedActor != null && this.focusedActor.isKiller()) {
                return;
            }
        }
        if ((nextIndex = (i = this.nearActors.indexOf(this.focusedActor)) + 1) >= this.nearActors.size()
                || nextIndex < 0) {
            nextIndex = 0;
        }
        if (this.nearActors.size() > 0) {
            this.focusedActor = (Actor) this.nearActors.elementAt(nextIndex);
        }
        if (this.focusedActor != null && this.focusedActor.catagory == 100) {
            this.focusedActor = null;
        }
    }

    public static void addChat(Actor ac, String str, int time) {
        if (str.equals("")) {
            return;
        }
        ac.chat = new ChatPopup(time, str, 1);
        ac.chat.setPos(ac.x, ac.y - ac.getHeight());
    }

    public void set_npc_request(short type) {
        if (this.idNpcReceive != -1) {
            type = this.idNpcReceive;
        } else {
            this.current_npc_talk = type;
        }
        int size0 = this.actors.size();
        Actor ac = null;
        int i = 0;
        while (i < size0) {
            ac = (Actor) this.actors.elementAt(i);
            if (ac instanceof NPC && ((NPC) ac).type == type) {
                this.posNpcRequest = new Position(ac.x, ac.y);
                return;
            }
            ++i;
        }
    }

    public void paintPosNPCQuest(mGraphics g) {
        int i = 0;
        while (i < PosNPCQuest.size()) {
            Position p = (Position) PosNPCQuest.elementAt(i);
            this.paintPoint(g, p, p.indexColor);
            ++i;
        }
    }

    public void removeFocusWhenPutKey() {
        if (this.map == 17 && GameScr.mainChar.isDoing) {
            this.focusedActor = null;
            this.coutChangeFocusWhenDoing = 0;
        }
    }

    public mVector getTargetFirst() {
        mVector target = new mVector();
        int i = 0;
        while (i < this.actors.size()) {
            Actor act = (Actor) this.actors.elementAt(i);
            if (act.catagory == 1) {
                target.addElement(act);
                return target;
            }
            ++i;
        }
        return target;
    }
    public void updateAnalog() {
        if (charcountdonw != null) {
            return;
        }
        if (isIntro && GameScr.mainChar.posTransRoad != null) {
            return;
        }
        if (GameCanvas.menuSelectitem.showMenu) {
            return;
        }
        if (GameCanvas.currentDialog != null) {
            return;
        }
        if (GameCanvas.menu2.isShow || GameCanvas.menu.showMenu || GameCanvas.currentDialog != null) {
            return;
        }

        int rangle = 34;
        this.isPress = false;

        int i = 0;
        while (i < GameCanvas.isPointerDown.length) {
            if (!this.isMovebyTouch) {
                // ✅ THÊM: Kiểm tra isPointerDown trước
                if (GameCanvas.isPointerDown[i] && GameCanvas.isPointer(0, yTouchMove - 10, 100, 84, i)) {
                    // ✅ Set idtouch khi bắt đầu touch
                    if (this.idtouch == -1) {
                        this.idtouch = i;
                    }

                    // Chỉ xử lý nếu đúng ngón tay đang touch
                    if (this.idtouch == i) {
                        // Tính center của background
                        int bgX = 20;
                        int bgY = yTouchMove;
                        int bgWidth = mGraphics.getImageWidth(imgAnalog[1]);
                        int bgHeight = mGraphics.getImageHeight(imgAnalog[1]);
                        int centerX = bgX + bgWidth / 2;
                        int centerY = bgY + bgHeight / 2;

                        int touchX = GameCanvas.px[i];
                        int touchY = GameCanvas.py[i];

                        // Tính khoảng cách và góc
                        int dx = touchX - centerX;
                        int dy = touchY - centerY;
                        int distance = (int) Math.sqrt(dx * dx + dy * dy);

                        if (distance > 10) {
                            // Giới hạn bán kính
                            int maxRadius = bgWidth / 2 - 5;

                            if (distance > maxRadius) {
                                // Nếu vượt quá, giới hạn lại
                                xpress = centerX + (dx * maxRadius) / distance;
                                ypress = centerY + (dy * maxRadius) / distance;
                            } else {
                                // Nếu trong vùng, dùng trực tiếp vị trí touch
                                xpress = touchX;
                                ypress = touchY;
                            }

                            // Tính góc
                            int angle = Util.angle(dx, dy);
                            int value = angle > 45 && angle <= 135 ? 3
                                    : (angle > 135 && angle <= 225 ? 0
                                    : (angle > 225 && angle <= 315 ? 2 : 1));

                            GameCanvas.clearKeyHold();
                            keyTouch = (value == 0) ? 2 : (value == 1) ? 1 : (value == 2) ? 0 : 3;

                            this.isPress = true;
                            this.timetouch = 3;
                            this.timeQouckSlot = 3;
                            GameCanvas.keyHold[this.mKeyMove[value]] = true;
                        } else {
                            // Reset về center khi không đủ khoảng cách
                            xpress = centerX;
                            ypress = centerY;
                            GameCanvas.clearKeyHold();
                        }
                    }
                }
                // ✅ Khi KHÔNG còn touch (thả tay)
                else if (!GameCanvas.isPointerDown[i] && this.idtouch == i) {
                    this.idtouch = -1;

                    // Reset stick về GIỮA background
                    int bgX = 20;
                    int bgY = yTouchMove;
                    int bgWidth = mGraphics.getImageWidth(imgAnalog[1]);
                    int bgHeight = mGraphics.getImageHeight(imgAnalog[1]);

                    xpress = bgX + bgWidth / 2;
                    ypress = bgY + bgHeight / 2;

                    this.countTouchmove = 0;
                    this.xp = -1;
                    this.yp = -1;

                    // Xóa tất cả phím di chuyển
                    GameCanvas.clearKeyHold();
                }
            }

            // Các xử lý nút khác (giữ nguyên)
            if (GameCanvas.isPointerHoldIn(0, 0, rangle, rangle, i)) {
                keyTouch = 9;
                this.isPress = true;
            }

            if (GameCanvas.isPointerClick[i] && GameCanvas.isPointer(xFire, yFire, 42, 42, i)
                    && GameCanvas.canTouch()) {
                this.timeQouckSlot = 3;
                keyTouch = 4;
                if (mSystem.isPC) {
                    keyTouch -= 4;
                }
                this.doTouchQuickSlot(keyTouch);
                this.isPress = true;
                if (GameCanvas.isPointerJustRelease[i] && GameCanvas.isPointerClick[i] && !mSystem.isPC) {
                    GameCanvas.keyPressedz[5] = true;
                }
                GameCanvas.isPointerClick[i] = false;
            }

            if (GameCanvas.isPointerClick[i] && GameCanvas.isPointer(xSkill1 - 15, ySkill1 - 15, 30, 30, 0)
                    && GameCanvas.canTouch()) {
                this.timeQouckSlot = 3;
                keyTouch = 5;
                if (mSystem.isPC) {
                    keyTouch -= 4;
                }
                this.doTouchQuickSlot(keyTouch);
                this.isPress = true;
                if (GameCanvas.isPointerJustRelease[i] && GameCanvas.isPointerClick[i]) {
                    GameCanvas.keyPressedz[1] = true;
                }
                GameCanvas.isPointerClick[i] = false;
            }

            if (GameCanvas.isPointerClick[i] && GameCanvas.isPointer(xSkill2 - 15, ySkill2 - 15, 30, 30, i)
                    && GameCanvas.canTouch()) {
                this.timeQouckSlot = 3;
                keyTouch = 6;
                if (mSystem.isPC) {
                    keyTouch -= 4;
                }
                this.doTouchQuickSlot(keyTouch);
                this.isPress = true;
                if (GameCanvas.isPointerJustRelease[i] && GameCanvas.isPointerClick[i]) {
                    GameCanvas.keyPressedz[3] = true;
                }
                GameCanvas.isPointerClick[i] = false;
            }

            if (GameCanvas.isPointerClick[i] && GameCanvas.isPointerHoldIn(xSkill3 - 15, ySkill3 - 15, 30, 30, i)
                    && GameCanvas.canTouch()) {
                this.timeQouckSlot = 3;
                keyTouch = 7;
                if (mSystem.isPC) {
                    keyTouch -= 4;
                }
                this.doTouchQuickSlot(keyTouch);
                this.isPress = true;
                if (GameCanvas.isPointerJustRelease[i] && GameCanvas.isPointerClick[i]) {
                    GameCanvas.keyPressedz[7] = true;
                }
                GameCanvas.isPointerClick[i] = false;
            }

            if (GameCanvas.isPointerClick[i] && GameCanvas.isPointerHoldIn(xSkill4 - 15, ySkill4 - 15, 30, 30, i)
                    && GameCanvas.canTouch()) {
                this.timeQouckSlot = 3;
                keyTouch = 8;
                if (mSystem.isPC) {
                    keyTouch -= 4;
                }
                this.doTouchQuickSlot(keyTouch);
                this.isPress = true;
                if (GameCanvas.isPointerJustRelease[i] && GameCanvas.isPointerClick[i]) {
                    GameCanvas.keyPressedz[9] = true;
                }
                GameCanvas.isPointerClick[i] = false;
            }

            if (!GameCanvas.isPointerDown[i] && keyTouch == 9 && this.right != null) {
                this.right.performAction();
            }

            if (GameCanvas.isPointerJustRelease[i]) {
                GameCanvas.keyHold[8] = false;
                GameCanvas.keyHold[6] = false;
                GameCanvas.keyHold[4] = false;
                GameCanvas.keyHold[2] = false;
            }

            ++i;
        }
    }

    @Override
    public void updateKey() {
        this.updatefocus();
        this.isTouchMenu = false;
        if (GameCanvas.currentDialog == null && GameCanvas.isPointer(xMenu, yMenu, mGraphics.getImageWidth(imgMenu[0]),
                mGraphics.getImageHeight(imgMenu[0]), 0) && GameCanvas.canTouch() && !Tilemap.isMapIntro()) {
            this.isTouchMenu = true;
            if (!GameCanvas.menu.showMenu && GameCanvas.currentDialog == null && !GameCanvas.menu2.isShow
                    && GameCanvas.isPointerJustRelease[0] && GameCanvas.isPointerClick[0] && mainChar.getState() != 3) {
                GameCanvas.menuSelectitem.startAt();
            }
        }
        if (GameCanvas.isPointer(0, 0, 35, 35, 0) && !Tilemap.isMapIntro() && GameCanvas.isPointerJustRelease[0]
                && GameCanvas.isPointerClick[0]) {
            this.cmdmenu.performAction();
        }
        if (this.chatMode && GameCanvas.isTouch && GameCanvas.isPointerClick[0]) {
            switch (GameCanvas.collisionCmdBar()) {
                case 0: {
                    GameCanvas.clearKeyHold();
                    GameCanvas.clearKeyPressed();
                    this.chatMode = false;
                    GameCanvas.isPointerClick[0] = false;
                    return;
                }
                case 1: {
                    String text = this.tfChat.getText();
                    this.tfChat.setText("");
                    this.doChatFromMe(text);
                    GameCanvas.isPointerClick[0] = false;
                    return;
                }
                case 2: {
                    this.tfChat.clear();
                    GameCanvas.isPointerClick[0] = false;
                    return;
                }
            }
        }
        if (this.chatWorld && GameCanvas.isTouch && GameCanvas.isPointerClick[0]) {
            switch (GameCanvas.collisionCmdBar()) {
                case 0: {
                    GameCanvas.clearKeyHold();
                    GameCanvas.clearKeyPressed();
                    this.chatWorld = false;
                    GameCanvas.isPointerClick[0] = false;
                    return;
                }
                case 1: {
                    String text = this.tfChatWorld.getText();
                    this.tfChatWorld.setText("");
                    this.doChatFromMe(text);
                    GameCanvas.isPointerClick[0] = false;
                    return;
                }
                case 2: {
                    this.tfChatWorld.clear();
                    GameCanvas.isPointerClick[0] = false;
                    return;
                }
            }
        }
        if (this.chatMode && GameCanvas.isTouch) {
            if (GameCanvas.isPointerClick[0] && GameCanvas.py[0] < this.tfChat.y) {
                this.chatMode = false;
                this.tfChat.setText("");
                GameCanvas.isPointerClick[0] = false;
            }
            return;
        }
        if (this.chatWorld && GameCanvas.isTouch) {
            if (GameCanvas.isPointerClick[0] && GameCanvas.py[0] < this.tfChatWorld.y) {
                this.chatWorld = false;
                this.tfChatWorld.setText("");
                GameCanvas.isPointerClick[0] = false;
            }
            return;
        }
        if (!this.isTouchMenu) {
            this.updateAnalog();
            if (GameCanvas.instance.hasPointerEvents() && !this.isPress) {
                this.updatePoint();
            }
        }
        if (!mSystem.isPC && !GameCanvas.isTouch) {
            int i = 0;
            while (i < this.QUICKSLOT_KEY.length) {
                if (GameCanvas.isKeyPressed(this.QUICKSLOT_KEY[i])) {
                    if (GameScr.mainChar.state == 3
                            || this.focusedActor != null && this.focusedActor.isNPC() && i == 2) {
                        this.doFire();
                    } else {
                        this.doTouchQuickSlot(i);
                    }
                    break;
                }
                ++i;
            }
        } else if (mSystem.isPC) {
            int i = 0;
            while (i < this.QUICKSLOT_KEY_PC.length) {
                if (GameCanvas.isKeyPressed(this.QUICKSLOT_KEY_PC[i])) {
                    if (GameScr.mainChar.state == 3
                            || this.focusedActor != null && this.focusedActor.isNPC() && i == 0) {
                        this.doFire();
                    } else {
                        this.doTouchQuickSlot(i);
                    }
                    break;
                }
                ++i;
            }
        }
        if (GameCanvas.isKeyPressed(11)) {
            GameCanvas.menuSelectitem.startAt();
            return;
        }
        if (autoFight) {
            isBeginAutoBuff = this.indexBuff != -1 && timeBuff - mSystem.currentTimeMillis() / 1000L > 0L;
        }
        if (GameScr.mainChar.state != 3 && (autoFight || autoBomHMP) && !GameScr.mainChar._isDie && Tilemap.lv != 0
                && Tilemap.lv != 201 && Tilemap.lv != 70 && Tilemap.lv != 80 && !Tilemap.isOfflineMap) {
            if (GameCanvas.keyPressedz[7]) {
                GameCanvas.keyPressedz[7] = false;
                this.doFire();
            }
            if (GameCanvas.keyPressedz[9]) {
                GameCanvas.keyPressedz[9] = false;
                this.doFire();
            }
        }
        if (GameCanvas.menu.showMenu) {
            GameCanvas.keyPressedz[5] = false;
            return;
        }
        if (GameCanvas.isKeyPressed(2) || GameCanvas.isKeyPressed(4) || GameCanvas.isKeyPressed(6)
                || GameCanvas.isKeyPressed(8)) {
            return;
        }
        if (GameScr.mainChar.state == 1 || GameScr.mainChar.state == 0 || GameScr.mainChar.state == 4) {
            this.doActorMove();
        }
    }

    public boolean checkCanChangeMap(int x, int y, int dir) {
        return false;
    }

    public void comHome() {
        GameCanvas.endDlg();
        this.indexBuff = -1;
        timeBuff = 0L;
        GameService.gI().comeHomeWhenDie();
    }

    public void doFire() {
        if (GameScr.mainChar.state == 3 && !isIntro) {
            return;
        }
        GameScr.mainChar.posTransRoad = null;
        if (this.focusedActor != null && this.focusedActor.catagory > 2) {
            if (!mainChar.isFullInven()) {
                GameService.gI().getDropableFromGround(this.focusedActor.ID);
            }
            return;
        }
        if (this.focusedActor != null && this.focusedActor.catagory == 1) {
            this.vecSkill.removeAllElements();
            if (GameCanvas.isTouch) {
                SetInfoData sd;
                int i = 0;
                while (i < MainChar.mQuickslot.length) {
                    if (MainChar.mQuickslot[i] != null && MainChar.mQuickslot[i].quickslotType == 1) {
                        SetInfoData s = new SetInfoData();
                        s.index = i + 4;
                        this.vecSkill.addElement(s);
                    }
                    ++i;
                }
                if (this.vecSkill.size() == 0) {
                    return;
                }
                if (this.vecSkill.size() == 1) {
                    sd = (SetInfoData) this.vecSkill.elementAt(0);
                    if (sd != null) {
                        if (mSystem.isPC) {
                            sd.index -= 4;
                        }
                        this.doTouchQuickSlot(sd.index);
                        return;
                    }
                } else {
                    sd = (SetInfoData) this.vecSkill.elementAt(this.indexSkill);
                    if (sd != null) {
                        if (mSystem.isPC) {
                            sd.index -= 4;
                        }
                        this.doTouchQuickSlot(sd.index);
                        ++this.indexSkill;
                        if (this.indexSkill > this.vecSkill.size() - 1) {
                            this.indexSkill = 0;
                        }
                        return;
                    }
                }
            }
        }
        if (this.focusedActor != null && this.focusedActor.isNpcChar()) {
            if (this.focusedActor.getStrTalk().equals("") || this.focusedActor.getCmdName().equals("")) {
                GameService.gI().requestNPCInfo(this.focusedActor.getIDNpc());
                return;
            }
            if (this.focusedActor != null && !this.focusedActor.getStrTalk().equals("")) {
                GameScr.mainChar.posTransRoad = null;
                if (this.focusedActor.getStateQuest() == 1 || this.focusedActor.getStateQuest() == 2
                        || this.focusedActor.getStateQuest() == 0) {
                    mVector menu = new mVector();
                    SetInfoData s5 = new SetInfoData();
                    s5.ID = this.focusedActor.getIDNpc();
                    s5.idIcon = this.focusedActor.getIDicon();
                    s5.stateQuest = this.focusedActor.getStateQuest();
                    s5.str = this.focusedActor.getStrTalk();
                    mCommand cmd2 = new mCommand(T.nhiemvu, (IActionListener) this, 11, (Object) s5);
                    if (this.isNPCshop((short) this.focusedActor.getIDNpc())) {
                        mSound.playSound(28, mSound.volumeSound);
                        mCommand cmd1 = new mCommand(T.trochuyen, this, 10);
                        menu.addElement(cmd1);
                        menu.addElement(cmd2);
                        GameCanvas.menu.startAt_MenuOption(menu, -1, -1, this.focusedActor.getStrTalk(),
                                this.focusedActor.getIDicon());
                        GameCanvas.endDlg();
                    } else {
                        if (this.focusedActor.getStateQuest() == 0 || this.focusedActor.getStateQuest() == 1) {
                            mSound.playSound(28, mSound.volumeSound);
                        }
                        cmd2.performAction();
                    }
                } else {
                    GameService.gI().requestNPCInfo(this.focusedActor.getIDNpc());
                }
            }
            return;
        }
    }

    public int getMpHpPutKey(int type, int idPotion) {
        int i = 0;
        while (i < TYPE_MP_HP[type].length) {
            if (TYPE_MP_HP[type][i] == idPotion) {
                return VALUE_MP_HP[type][i];
            }
            ++i;
        }
        return 0;
    }

    public void doParty() {
        if (this.focusedActor != null) {
            if (this.focusedActor.catagory == 0) {
                mVector menuItems = new mVector();
                menuItems.addElement(new mCommand(T.tfocus[0], 19));
                menuItems.addElement(new mCommand(T.tfocus[1], 16));
                menuItems.addElement(new mCommand(T.tfocus[2], 15));
                menuItems.addElement(new mCommand(T.tfocus[3], 22));
                menuItems.addElement(new mCommand(T.tfocus[4], 23));
                menuItems.addElement(new mCommand(T.tfocus[5], 101));
                if (mainChar.canInvite() && this.focusedActor.getidClan() == -1) {
                    menuItems.addElement(new mCommand(T.moivaobang, 24));
                }
                GameCanvas.menu.startAt(menuItems, 2);
                return;
            }
            GameCanvas.keyPressedz[5] = false;
        } else {
            this.unRideHorse();
        }
    }

    public void unRideHorse() {
        if (GameScr.mainChar.useHorse != -1) {
            mCommand cmd = new mCommand("C\u00f3", 21);
            GameCanvas.startYesNoDlg(
                    "Ng\u1ef1a s\u1ebd b\u1ecb m\u1ea5t n\u1ebfu r\u01b0\u01a1ng \u0111\u1ed3 kh\u00f4ng c\u00f2n ch\u1ed7. B\u1ea1n c\u00f3 mu\u1ed1n xu\u1ed1ng ng\u1ef1a kh\u00f4ng ?",
                    cmd);
        }
    }

    public void doHuyBanHang() {
        GameCanvas.endDlg();
        mCommand cmd = new mCommand("C\u00f3", 108);
        GameCanvas.startYesNoDlg("B\u1ea1n c\u00f3 mu\u1ed1n h\u1ee7y gian h\u00e0ng kh\u00f4ng?", cmd);
    }

    public void doNapXu() {
        if (this.decriptNap.size() == 0) {
            GameCanvas.startOKDlg("Ch\u1ee9c n\u0103ng n\u00e0y hi\u1ec7n \u0111ang t\u1ea1m kh\u00f3a");
            return;
        }
    }

    public void doNapMoney(String decription) {
        mVector menuItem = new mVector();
        menuItem.addElement(new mCommand("N\u1ea1p l\u01b0\u1ee3ng", 70, decription));
        menuItem.addElement(new mCommand("N\u1ea1p xu", 71, decription));
        GameCanvas.menu.startAt(menuItem, 3);
    }

    private void paintPoint(mGraphics g, Position pos, int index) {
        if (pos != null) {
            int x1 = pos.x / 16;
            int y1 = pos.y / 16;
            if (x1 - 4 <= cmxMini) {
                x1 = cmxMini + 4;
            } else if (x1 + 6 > cmxMini + wMiniMap) {
                x1 = cmxMini + wMiniMap - 6;
            }
            if (y1 - 4 <= cmyMini) {
                y1 = cmyMini + 4;
            } else if (y1 + 6 > cmyMini + hMiniMap) {
                y1 = cmyMini + hMiniMap - 6;
            }
            g.setColor(this.colorPaint[index]);
            g.fillRect(x1, y1, 3, 3, false);
            g.setColor(-1);
            g.drawRect(x1 - 1, y1 - 1, 4, 4, false);
        }
    }

    public void paintMiniMap(mGraphics g, mVector npc) {
        if (Tilemap.w < GameCanvas.w / 16 && Tilemap.h < GameCanvas.h / 16) {
            return;
        }
        GameCanvas.resetTrans(g);
        if (this.indexBuff != -1 && timeBuff - System.currentTimeMillis() / 1000L > 0L) {
            GameData.imgSkillIcon.drawFrame(this.indexBuff, GameCanvas.w, GameCanvas.h - hMiniMap - deltaY, 0,
                    mGraphics.BOTTOM | mGraphics.RIGHT, g);
            FontTeam.smallFontColor[0].drawString(g, String.valueOf((timeBuff - System.currentTimeMillis()) / 1000L),
                    GameCanvas.w - 10, GameCanvas.h - hMiniMap - deltaY - 28, 2, false);
        }
        g.translate(this.posMiniMap.x, this.posMiniMap.y);
        int x0 = GameScr.mainChar.x / 16;
        int y0 = GameScr.mainChar.y / 16;
        int i = 0;
        while (i < 3) {
            g.setColor(colorMini[i]);
            g.drawRect(i, i, wMiniMap - i * 2, hMiniMap - i * 2, true);
            ++i;
        }
        int xts = g.getTranslateX();
        int yts = g.getTranslateY();
        mGraphics.resetTransAndroid(g);
        g.setClip(3, 3, wMiniMap - 5, hMiniMap - 5);
        g.saveCanvas();
        g.translateAndroid(xts, yts);
        g.ClipRec2(3, 3, wMiniMap - 5, hMiniMap - 5);
        g.translate(-cmxMini, -cmyMini);
        if (Tilemap.imgMap != null) {
            g.drawImage(Tilemap.imgMap, 0, 0, 0, true);
            g.setColor(4004892);
            int i2 = 0;
            while (i2 < allLocation.length) {
                g.fillRect(GameScr.allLocation[i2].x / 16 - GameScr.allLocation[i2].vx * 2,
                        GameScr.allLocation[i2].y / 16 - GameScr.allLocation[i2].vy * 2, 3, 3, false);
                ++i2;
            }
            i2 = 0;
            while (i2 < 3) {
                g.setColor(colorMini[i2]);
                g.drawRect(i2, i2, wMiniMap - i2 * 2, hMiniMap - i2 * 2, true);
                ++i2;
            }
            i2 = 0;
            while (i2 < npc.size()) {
                Actor ac = (Actor) npc.elementAt(i2);
                g.setColor(ac.getColorMiniMap());
                g.fillRect(ac.x / 16 - 1, ac.y / 16 - 1, 3, 3, true);
                ++i2;
            }
        }
        g.setColor(0xFFFFFF);
        g.fillRect(x0, y0 - 2, 5, 5, true);
        g.setColor(255);
        g.fillRect(x0 + 1, y0 - 1, 3, 3, true);
        this.paintPosNPCQuest(g);
        this.paintPoint(g, posNpcReceiveClan, 1);
        g.setColor(16516117);
        if (GameScr.mainChar.posTransRoad != null) {
            if (this.posCam != null) {
                g.setColor(15198737);
                g.fillRect(this.posCam.x, this.posCam.y, 3, 3, false);
                this.tg.x = (short) this.posCam.x;
                this.tg.y = (short) this.posCam.y;
            }
            int size1 = GameScr.mainChar.posTransRoad.length;
            int i3 = 0;
            while (i3 < size1) {
                g.setColor(15198737);
                byte xx = (byte) (xStart + (GameScr.mainChar.posTransRoad[i3] >> 8));
                byte yy = (byte) (yStart + (GameScr.mainChar.posTransRoad[i3] & 0xFF));
                if (xx != -1) {
                    g.fillRect(xx + 1, yy + 1, 1, 1, false);
                }
                ++i3;
            }
        }
        g.setColor(16317005);
        int size2 = Tilemap.pointPop.size();
        mGraphics.resetTransAndroid(g);
        g.restoreCanvas();
        GameCanvas.resetTrans(g);
        FontTeam.normalFontColor.drawString(g, nameMap, 1, GameCanvas.h - mFont.tahoma_7b_black.getHeight() * 2, 0,
                false);
    }

    public void VibrateScreen(mGraphics g) {
        dx = 0;
        dy = 0;
        if (timeVibrateScreen > 0) {
            if (timeVibrateScreen > 100) {
                dy = Res.random_Am_0(3);
                if (timeVibrateScreen == 101) {
                    timeVibrateScreen = 0;
                }
            } else {
                dy = Res.random_Am_0(3);
                dx = Res.random_Am(0, 2);
            }
            --timeVibrateScreen;
        }
        g.translate(dx, dy);
    }

    public static void Font3d(mGraphics g, String str, int x, int y, int ar, mFont font) {
        mFont.tahoma_7b_black.drawString(g, str, x + 1, y + 1, ar, true);
        font.drawString(g, str, x, y, ar, true);
    }

    public static void Font3dNormal(mGraphics g, String str, int x, int y, int ar, mFont font, boolean isClip) {
        mFont.tahoma_7_black.drawString(g, str, x + 1, y + 1, ar, isClip);
        font.drawString(g, str, x, y, ar, isClip);
    }

    public static void Font3d(mGraphics g, String str, int x, int y, int ar, mFont font, boolean isClip) {
        mFont.tahoma_7b_black.drawString(g, str, x + 1, y + 1, ar, isClip);
        font.drawString(g, str, x, y, ar, isClip);
    }

    @Override
    public void paint(mGraphics g) {
        try {
            IArrow arrow;
            if (!this.readyGetGameLogic) {
                return;
            }
            GameCanvas.resetTrans(g);
            this.VibrateScreen(g);
            if (this.isFillScr) {
                return;
            }
            if (this.idMapColor != -1) {
                g.setColor(-16333831);
                g.fillRect(0, 0, GameCanvas.w, GameCanvas.h, false);
            }
            if (!lockcmx) {
                // empty if block
            }
            int rung = 0;
            if (this.timerung > 0) {
                rung = r.nextInt(2);
            }
            g.translate(-cmx + rung, -cmy - rung);
            try {
                this.paintClound(g, 0);
                Tilemap.paintTile(g);
            } catch (Exception exception) {
                // empty catch block
            }
            if (GameScr.mainChar.posTransRoad != null && this.posCam != null) {
                this.tg.paint(g);
            }
            EffectManager.lowEffects.paintAll(g);
            int i = 0;
            while (i < this.arrowsDown.size()) {
                arrow = (IArrow) this.arrowsDown.elementAt(i);
                if (arrow != null) {
                    arrow.paint(g);
                }
                ++i;
            }
            int treeIndex1 = 0;
            int actorIndex = 0;
            try {
                if (Tilemap.trees != null) {
                    int b = this.gssy - (Res.maxHTree / (Tilemap.size * mGraphics.zoomLevel) + 1);
                    while (b < this.gssye + (Res.maxHTree / (Tilemap.size * mGraphics.zoomLevel) + 1)) {
                        if (paintCay == 1) {
                            int sizez = Tilemap.trees.size();
                            while (treeIndex1 < sizez) {
                                Tree t = (Tree) Tilemap.trees.elementAt(treeIndex1);
                                if (t == null)
                                    break;
                                if (t.y == b) {
                                    if (t.checkInCmx(cmx) && !t.isLow(1)) {
                                        TreeTopBottom tre = null;
                                        if (!mSystem.isj2me && (tre = (TreeTopBottom) Tilemap.treeTop_bottom
                                                .get(String.valueOf(b) + t.x)) != null) {
                                            tre.paintBottom(g);
                                        }
                                        t.paint(g);
                                        if (!mSystem.isj2me && tre != null) {
                                            tre.paintTop(g, b);
                                        }
                                    }
                                } else if (t.y > b)
                                    break;
                                ++treeIndex1;
                            }
                        }
                        int size3 = this.actors.size();
                        if (b >= this.gssy && b <= this.gssye + 2) {
                            while (actorIndex < size3) {
                                Actor actor = (Actor) this.actors.elementAt(actorIndex);
                                if (actor != null && actor.y >> 4 > b)
                                    break;
                                if (actor != null && GameScr.isInScreen(actor) && !actor.wantDestroy) {
                                    actor.paint(g);
                                }
                                ++actorIndex;
                            }
                        }
                        ++b;
                    }
                    Tilemap.paintTileTop(g);
                }
            } catch (Exception b) {
                // b.printStackTrace();
                // empty catch block
            }
            this.paintClound(g, 1);
            this.paintFocus(g);
            int i2 = 0;
            while (i2 < arrowsUp.size()) {
                arrow = (IArrow) arrowsUp.elementAt(i2);
                if (arrow != null) {
                    arrow.paint(g);
                }
                ++i2;
            }
            Actor ac = null;
            mVector allnpc = new mVector();
            int size5 = this.actors.size();
            int i3 = 0;
            while (i3 < size5) {
                ac = (Actor) this.actors.elementAt(i3);
                if (ac != null) {
                    ac.paintName(g);
                }
                if (ac != null && ac.isNPC()) {
                    allnpc.addElement(ac);
                }
                ++i3;
            }
            if (allLocation != null) {
                i3 = 0;
                while (i3 < allLocation.length) {
                    byte typepaint = 0;
                    if (allLocation[i3] != null) {
                        if (GameScr.allLocation[i3].x >= Tilemap.w * 16 - 64) {
                            typepaint = 1;
                        }
                        if (GameScr.allLocation[i3].y <= 0 || GameScr.allLocation[i3].y >= Tilemap.h * 16 - 64) {
                            typepaint = 2;
                        }
                        allLocation[i3].paint(g, typepaint);
                    }
                    ++i3;
                }
            }
            EffectManager.hiEffects.paintAll(g);
            try {
                this.paintFlyText(g);
            } catch (Exception i22) {
                // empty catch block
            }
            if (effAnimate.size() > 0 && !Tilemap.isOfflineMap && effAnimate.size() > 0) {
                AnimateEffect ani = null;
                int i4 = 0;
                while (i4 < effAnimate.size()) {
                    ani = (AnimateEffect) effAnimate.elementAt(i4);
                    if (ani != null) {
                        ani.paint(g);
                    }
                    ++i4;
                }
            }
            try {
                if (this == GameCanvas.currentScreen) {
                    this.paintMiniMap(g, allnpc);
                    this.paintQuickSlot(g);
                }
            } catch (Exception ani) {
                // empty catch block
            }
            try {
                this.paintFocusActorInfo(g);
                GameCanvas.resetTrans(g);
            } catch (Exception ani) {
                // empty catch block
            }
            this.paintMainCharInfo(g);
            super.paint(g);
            if (this.listMSGServer != null && this.listMSGServer.size() > 0) {
                this.paintMsgServer(g);
            }
            GameCanvas.resetTrans(g);
            if (Tilemap.isMapIntro() && GameCanvas.isTouch && this.cmdLogin != null) {
                this.cmdskip.paint(g);
                this.cmdLogin.paint(g);
            }
            GameCanvas.resetTrans(g);
            this.paintcmd(g);
            this.paintShowEvent(g);
            if (this.chatMode) {
                this.tfChat.paint(g);
            }
            if (this.chatWorld) {
                this.tfChatWorld.paint(g);
            }
            this.paintArena(g);
            this.paintGhost(g);
            if (charcountdonw != null) {
                charcountdonw.paint(g, GameCanvas.w / 2 - 31, GameCanvas.h - 30);
            }
            this.paintTime(g);
            int fq = 0;
            if (!Tilemap.isMapPhoban() && hShowEvent <= 0 && isQuest && !isIntro && mainChar.getState() != 3
                    && GameCanvas.currentScreen == this
                    && (this.focusedActor == null || this.focusedActor != null && this.focusedActor.isNPC())) {
                if (this.tickicon > 20) {
                    fq = 1;
                }
                g.drawRegion(imgPointQuest, 0, fq * 19, 18, 19, 0,
                        GameCanvas.w - mGraphics.getImageWidth(imgPointQuest) - 5 - wMiniMap, 5, 0, false);
            }
            if (numMSG > 0 && !isIntro && mainChar.getState() != 3 && GameCanvas.currentScreen != null
                    && GameCanvas.currentScreen.equals(this)) {
                int fms = 0;
                if (this.tickicon < 20) {
                    fms = 1;
                }
                g.drawRegion(imgMSG, 0, fms * 13, 18, 13, 0, GameCanvas.w - 5 - wMiniMap - imgMSG.getWidth(),
                        hMiniMap - imgMSG.getHeight() / 2, 0, false);
            }
            this.paintIcon(g);
            if (mSystem.isPC && GameCanvas.menu2.isShow && GameCanvas.currentScreen != MainMenu.gI() && !isIntro) {
                g.fillRectAlpha(0, 0, GameCanvas.w, GameCanvas.h, 0, 60, false);
            }
            if (GameCanvas.isTouch && GameCanvas.currentScreen == this) {
                this.paintAnalog(g);
            }
            this.paintIconX2(g);
            try {
            } catch (Exception exception) {
            }
            paintDamageRanking(g);
        } catch (Exception exception) {
            // empty catch block
        }
    }

    // Method vẽ bảng xếp hạng với khung nền giống Menu2
    public void paintDamageRanking(mGraphics g) {
        if (!showDamageRanking) {
            return;
        }


        GameCanvas.resetTrans(g);
        // Kích thước bảng
        int panelWidth = 120;
        int itemHeight = 20;
        int headerHeight = 28;
        int panelHeight = (5 * itemHeight) + 20;

        // Vị trí bên trái giữa màn hình
        int panelX = 10;
        int panelY = GameCanvas.h / 2 - (panelHeight + headerHeight) / 2 + 30;

        // Vẽ khung chính giống Menu2
        Res.paintDlgDragonFullNew(g, panelX, panelY, panelWidth, panelHeight, 60, 60, GameScr.imgBk[0], false);

        // Vẽ header giống Menu2
        g.setColor(-9751532);
        g.fillRect(panelX, panelY - headerHeight, panelWidth, headerHeight, false);
        int i = 0;
        while (i < 3) {
            g.setColor(Res.nColor[i]);
            g.drawRect(panelX + i, panelY - headerHeight + i, panelWidth - i * 2, headerHeight - i * 2, false);
            ++i;
        }

        // Vẽ viền header giống Menu2
        i = 0;
        while (i < 7) {
            g.drawRegion(GameScr.imgBoder[5], 0, 25, 12, 25, 0, panelX + panelWidth / 2 - 42 + i * 12, panelY - headerHeight / 2, mGraphics.VCENTER | mGraphics.LEFT, false);
            ++i;
        }
        g.drawRegion(GameScr.imgBoder[5], 0, 0, 12, 25, 0, panelX + panelWidth / 2 - 44, panelY - headerHeight / 2, mGraphics.VCENTER | mGraphics.LEFT, false);
        int ys = 0;
        if (!mSystem.isj2me) {
            ys = -1;
        }
        g.drawRegion(GameScr.imgBoder[5], 0, 0, 12, 25, 2, panelX + panelWidth / 2 + 44, panelY - headerHeight / 2 + 1 + ys, mGraphics.VCENTER | mGraphics.RIGHT, false);

        // Vẽ title
        FontTeam.fontTile.drawString(g, "Bảng Xếp Hạng", panelX + panelWidth / 2, panelY - headerHeight / 2 - 6, 2, false);

        // Vẽ góc khung giống Menu2
        g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 7, panelX + panelWidth + 1, panelY + 1, mGraphics.BOTTOM | mGraphics.RIGHT, false);
        g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 1, panelX, panelY + 1, mGraphics.BOTTOM | mGraphics.LEFT, false);
        g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 0, panelX, panelY - headerHeight, mGraphics.TOP | mGraphics.LEFT, false);
        g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 2, panelX + panelWidth + 1, panelY - headerHeight, mGraphics.TOP | mGraphics.RIGHT, false);

        // Vẽ nội dung bảng xếp hạng
        g.ClipRec(panelX + 6, panelY + 5, panelWidth - 12, panelHeight - 10);
        int currentY = panelY + 10;
        for (int j = 0; j < damageRankingList.size() && j < 10; j++) {
            DamageRankingInfo info = (DamageRankingInfo) damageRankingList.elementAt(j);
            ImageIcon img;
            if (info.iconId != -1 && (img = GameData.getImgIcon((short) (info.iconId + Res.ID_ICON_CLAN))) != null && img.img != null) {
                Item.eff_UpLv.paintUpgradeEffect(panelX + 16 + 5, currentY + itemHeight / 2, 15, 16, g, 1);
                g.drawImage(img.img, panelX + 10 + 5, currentY + itemHeight / 2 - 5, 2, false);
            }
//            FontTeam.numberSmall_white.drawString(g, String.valueOf(info.rank), panelX + 16, currentY + itemHeight / 2-5, 2, false);
            mFont.tahoma_7b_white.drawString(g, "[" + info.clanName + "]", panelX + 40, currentY + itemHeight / 2 - 6, 0, true);
            String damageText = getTextPaintHP(info.damage);
            FontTeam.numberSmall_yeallow.drawString(g, damageText, panelX + panelWidth - 15, currentY + itemHeight / 2 - 5, 1, false);
            if (j < damageRankingList.size() - 1) {
                g.setColor(Res.nColor[1]);
                g.fillRect(panelX + 8, currentY + itemHeight - 1, panelWidth - 16, 1, true);
            }

            currentY += itemHeight;
        }

        mGraphics.resetTransAndroid(g);
        g.restoreCanvas();
    }

    public void paintIconX2(mGraphics g) {
        ImageIcon img;
        if (this.idIconX2 != -1 && !isIntro && (img = GameData.getImgIcon(this.idIconX2)) != null && img.img != null) {
            g.drawImage(img.img, 125, 2, 0, false);
            if (MainChar.itemOptionMainChar != null && MainChar.itemOptionMainChar.value > 0) {
                FontTeam.numberSmall_white.drawString(g, String.valueOf(MainChar.itemOptionMainChar.value), 143, 6, 0,
                        false);
            }
        }
    }

    public void paintGhost(mGraphics g) {
        if (!isGost) {
            return;
        }
        g.drawImage(imgGhost, GameCanvas.w - 104, 35, 0, false);
    }

    public void paintArena(mGraphics g) {
        if (Tilemap.isMapIntro()) {
            return;
        }
        if (isIntro) {
            return;
        }
        if (GameCanvas.currentScreen != null && GameCanvas.currentScreen != this) {
            return;
        }
        if (this.hideGUI == 2) {
            return;
        }
        if (GameCanvas.menuSelectitem.showMenu) {
            return;
        }
        if (GameCanvas.menu.showMenu) {
            return;
        }
        if (MainChar.blockkey) {
            return;
        }
        if (GameCanvas.currentDialog != null) {
            return;
        }
        if (this.chatMode || this.chatWorld) {
            return;
        }
        if (GameCanvas.menu2.isShow) {
            return;
        }
        if (GameCanvas.currentScreen != null && GameCanvas.currentScreen != this) {
            return;
        }
        g.drawImage(imgBackArena, 2, GameCanvas.h - 5 - 4, 0, false);
        FontTeam.numberSmall_green.drawString(g, "" + arena, 2 + mGraphics.getImageWidth(imgBackArena) + 2,
                GameCanvas.h - 5 - 5, 0, false);
        this.paintCharParty(g);
    }

    public void paintcmd(mGraphics g) {
        if (GameScr.mainChar.lv < 10 && this.cmdmenu != null) {
            this.cmdmenu.paint(g);
        }
        if (!isIntro && GameCanvas.currentScreen == this) {
            if (this.cmdchat != null)
                this.cmdchat.paint(g);

            if (this.cmdAuto != null)
                this.cmdAuto.paint(g);

            if (this.cmdGoiNap != null)
                this.cmdGoiNap.paint(g);

            if (this.cmdVongQuay != null)
                this.cmdVongQuay.paint(g);
        }

    }

    public void paintSkillTest(mGraphics g) {
        String t = "";
        if (this.idskilltest >= 1 && this.idskilltest < 5) {
            t = "Thi\u1ebfu L\u00e2m: ";
        } else if (this.idskilltest >= 5 && this.idskilltest < 12) {
            t = "Ng\u0169 \u0110\u1ed9c: ";
        } else if (this.idskilltest >= 12 && this.idskilltest < 17) {
            t = "Nga Mi: ";
        } else if (this.idskilltest >= 17 && this.idskilltest < 21) {
            t = "V\u00f5 \u0110ang: ";
        } else if (this.idskilltest >= 21 && this.idskilltest <= 26) {
            t = "C\u00e1i Bang: ";
        }
        mFont.tahoma_7b_white.drawString(g, String.valueOf(t) + this.idskilltest, GameCanvas.w / 2, 50, 3, false);
    }

    public boolean canPaintNPC_SV(Actor npc) {
        return npc.x + npc.getWidth() / 2 >= cmx && npc.x - npc.getWidth() / 2 <= cmx + GameCanvas.w
                && npc.y - npc.getHeight() <= cmy + GameCanvas.h && npc.y >= cmy;
    }

    private void paintMsgServer(mGraphics g) {
        GameCanvas.resetTrans(g);
        if (!GameCanvas.isTouch) {
            g.drawImage(Res.imgCmdBar, 0, GameCanvas.h + 3, mGraphics.LEFT | mGraphics.BOTTOM, false);
        }
        g.setClip(15, GameCanvas.h - 20, GameCanvas.w - 30, 20);
        g.ClipRec(15, GameCanvas.h - 20, GameCanvas.w - 30, 20);
        String str = (String) this.listMSGServer.elementAt(0);
        FontTeam.arialFontW.drawString(g, str, this.xMsgServer, GameCanvas.h - 10 - FontTeam.arialFontW.getHeight() / 2,
                0, false);
        g.restoreCanvas();
        g.setClip(0, 0, GameCanvas.h, GameCanvas.w);
    }

    public void doChat() {
        String text;
        if (this.chatMode) {
            if (GameCanvas.isTouch) {
                text = this.tfChat.getText();
                this.chatMode = false;
                if (text.equals("")) {
                    return;
                }
                this.tfChat.setText("");
                this.doChatFromMe(text);
                // this.tfChat.setFocus(true);
            } else {
                text = this.tfChat.getText();
                if (text.equals("")) {
                    return;
                }
                this.tfChat.setText("");
                this.doChatFromMe(text);
                // this.tfChat.setFocus(true);
            }
        }
        if (this.chatWorld) {
            text = this.tfChatWorld.getText();
            this.chatWorld = false;
            if (text.equals("")) {
                return;
            }
            this.tfChatWorld.setText("");
            this.tfChatWorld.setFocus(false);
            GameService.gI().doChatWorld(text);
        }
    }

    private void doChatFromMe(String text) {
        GameScr.mainChar.chat = null;
        GameScr.addChat(mainChar, text, 50);
        MessageScr.gI().addTab(String.valueOf(GameScr.mainChar.name) + ": " + text, null, 0);
        if (text.equals("lathinh")) {
            GameCanvas.endDlg();
            GameCanvas.inputDlg.setInfo("Nhập số vé cần lật", new mCommand("Ok", this, 2704), 1, 10, true);
            GameCanvas.inputDlg.show();
            GameCanvas.gameScr.hideGUI = 0;
            return;
        }
        GameService.gI().chat(text);
        if (text != null && mSystem.isj2me && text.equals("pchar")) {
            Char.paintOrtherChar = !Char.paintOrtherChar;
        }
    }

    public static String getTextPaintHP(long num) {
        if (num >= 1000000000) {
            long n1 = (num / 1000000000);
            long n2 = (num / 100000000 % 10);
            if (n2 != 0) {
                return String.valueOf(n1) + "." + n2 + "b";
            }
            return String.valueOf(n1) + "b";
        }
        if (num >= 1000000) {
            int n1 = (int) (num / 1000000);
            int n2 = (int) (num / 100000 % 10);
            if (n2 != 0) {
                return String.valueOf(n1) + "." + n2 + "m";
            }
            return String.valueOf(n1) + "m";
        }
        if (num >= 1000) {
            int n1 = (int) (num / 1000);
            int n2 = (int) (num / 100 % 10);
            if (n2 != 0) {
                return String.valueOf(n1) + "." + n2 + "k";
            }
            return String.valueOf(n1) + "k";
        }
        return String.valueOf(num);
    }

    private void paintMainCharInfo(mGraphics g) {
        String tm;
        if (GameCanvas.isTouch && mGraphics.zoomLevel > 1 && !mSystem.isPC
                && GameCanvas.currentScreen.equals(MainMenu.gI())) {
            return;
        }
        if (Tilemap.isMapIntro()) {
            return;
        }
        GameCanvas.resetTrans(g);
        g.drawImage(imghealth[0], 0, 0, mGraphics.TOP | mGraphics.LEFT, true);
        int h123 = 34;
        int xp = 47;
        g.drawRegion(imghealth[2], 0, 0, mGraphics.getImageWidth(imghealth[2]),
                mGraphics.getImageHeight(imghealth[2]) / 2, 0, xp, h123 - 30, 0, false);
        g.drawRegion(imghealth[2], 0, mGraphics.getImageHeight(imghealth[2]) / 2, mGraphics.getImageWidth(imghealth[2]),
                mGraphics.getImageHeight(imghealth[2]) / 2, 0, xp, h123 - 19, 0, false);
        int hpPaint = 0;
        int mpPaint = 0;
        int exppaint = 0;
        if (GameScr.mainChar.hp > 0 && GameScr.mainChar.maxhp > 0) {
            hpPaint = (int) (GameScr.mainChar.hp * 60 / GameScr.mainChar.maxhp);
            if (hpPaint <= 0) {
                hpPaint = 1;
            } else if (hpPaint > 60) {
                hpPaint = 60;
            }
            g.drawRegion(imghealth[1], 0, 0, hpPaint, mGraphics.getImageHeight(imghealth[1]) / 2, 0, xp + 1, h123 - 29,
                    0, true);
        }
        if (GameScr.mainChar.mp > 0 && GameScr.mainChar.maxmp > 0) {
            mpPaint = GameScr.mainChar.mp * 60 / GameScr.mainChar.maxmp;
            if (mpPaint <= 0) {
                mpPaint = 1;
            } else if (mpPaint > 60) {
                mpPaint = 60;
            }
            g.drawRegion(imghealth[1], 0, mGraphics.getImageHeight(imghealth[1]) / 2, mpPaint,
                    mGraphics.getImageHeight(imghealth[1]) / 2, 0, xp + 1, h123 - 18, 0, true);
        }
        if (GameScr.mainChar.delay >= 0) {
            int[] t = mainChar.getTime();
            FontTeam.normalFont[0].drawString(g, String.valueOf(t[0]) + " : " + t[1], 5, h123 + 10, 0, false);
        }
        if (!(tm = mainChar.getGoldTime()).equals("")) {
            FontTeam.normalFont[0].drawString(g, tm, 5, h123 + 20, 0, false);
            FontTeam.smallFontColor[0].drawString(g, Char.goldTime, 5, h123 + 34, 0, false);
        }
        if (GameCanvas.gameTick % 10 > 3) {
            if (GameScr.mainChar.basePointLeft > 0) {
                g.drawImage(imgCong[0], GameCanvas.w - 2, h123 + 5, mGraphics.RIGHT | mGraphics.TOP, false);
            }
            if (GameScr.mainChar.skillPointLeft > 0) {
                g.drawImage(imgCong[1], GameCanvas.w - 2, h123 + 16, mGraphics.RIGHT | mGraphics.TOP, false);
            }
        }
        GameCanvas.resetTrans(g);
        g.setClip(5, 1, 30, 34);
        g.ClipRec(5, 1, 30, 34);
        if (mSystem.isPC || mSystem.isIP) {
            g.fillRect(0, 0, 0, 0, false);
        }
        mainChar.paint(g, 17, 60, 1);
        g.restoreCanvas();
        GameCanvas.resetTrans(g);
        FontTeam ftHP = FontTeam.numberSmall_white;
        int yadd = 0;
        boolean isPcX1 = false;
        if (isPcX1) {
            ftHP = FontTeam.smallFont;
            yadd = 2;
        }
        if (this.tickHP > 0) {
            if (this.inDexHP == 1) {
                ftHP = FontTeam.numberSmall_green;
            } else if (this.inDexHP == 2) {
                ftHP = FontTeam.numberSmall_red;
            }
        }
        ftHP.drawString(g,
                String.valueOf(GameScr.getTextPaintHP(GameScr.mainChar.hp)) + "/"
                        + GameScr.getTextPaintHP(GameScr.mainChar.maxhp),
                xp + 30, h123 - 31 + 2 + dy - 1 + yadd, 2, false);
        FontTeam ftMP = FontTeam.numberSmall_white;
        if (isPcX1) {
            ftMP = FontTeam.smallFont;
        }
        if (this.tickMP > 0) {
            if (this.inDexMP == 1) {
                ftHP = FontTeam.numberSmall_green;
            } else if (this.inDexMP == 2) {
                ftHP = FontTeam.numberSmall_red;
            }
        }
        ftMP.drawString(g,
                String.valueOf(GameScr.getTextPaintHP(GameScr.mainChar.mp)) + "/"
                        + GameScr.getTextPaintHP(GameScr.mainChar.maxmp),
                xp + 30, h123 - 20 + 2 + dy - 1 + yadd, 2, false);
        FontTeam fExp = FontTeam.numberSmall_white;
        if (isPcX1) {
            fExp = FontTeam.smallFont;
        }
        fExp.drawString(g, String.valueOf(GameScr.mainChar.level) + "+" + mainChar.getPercent(), 48,
                h123 - 11 + 2 + yadd, 0, false);
        g.drawImage(imglv, 36, h123 - 11 + 3, 0, false);
        if (GameScr.mainChar.lvpercent > 0) {
            exppaint = GameScr.mainChar.lvpercent / 10 * this.EXPBARW / 100;
            if (mSystem.isIP) {
                g.setColor(-14429405);
                g.fillRect(35, h123, exppaint, 1, false);
                g.setColor(-15836390);
                g.fillRect(35, h123, exppaint, 1, false);
            } else {
                g.setColor(-14429405);
                g.fillRect(35, h123, exppaint, 1, false);
            }
        }
        if (this.tickpaintFont > 0) {
            FontTeam ft = FontTeam.numberSmall_white;
            if (this.inDexfont == 1) {
                ft = FontTeam.numberSmall_red;
            } else if (this.inDexfont == 2) {
                ft = FontTeam.numberSmall_green;
            } else if (this.inDexfont == 3) {
                ft = FontTeam.numberSmall_blue;
            }
            ft.drawString(g, this.textinfomainChar, 90, h123 - 9, 0, false);
        }
    }

    private void paintQuickSlot(mGraphics g) {
        int i;
        if (this.hideGUI == 2) {
            return;
        }
        if (GameCanvas.menuSelectitem.showMenu) {
            return;
        }
        if (GameCanvas.menu.showMenu) {
            return;
        }
        if (MainChar.blockkey) {
            return;
        }
        if (GameCanvas.currentDialog != null) {
            return;
        }
        if (this.chatMode || this.chatWorld) {
            return;
        }
        if (GameCanvas.menu2.isShow) {
            return;
        }
        GameCanvas.resetTrans(g);
        if (GameCanvas.currentScreen != this) {
            return;
        }
        int hImg = mGraphics.getImageHeight(imgTouchMove[3]) / 2;
        int wImg = mGraphics.getImageWidth(imgTouchMove[3]);
        if (GameCanvas.isTouch) {
            int h0 = mGraphics.getImageHeight(imgTouchMove[2]) / 2;
            int w0 = mGraphics.getImageWidth(imgTouchMove[2]);
            int[] xx = new int[]{xFire + 6, xSkill1 - 14, xSkill2 - 14, xSkill3 - 14, xSkill4 - 14};
            int[] yy = new int[]{yFire + 6, ySkill1 - 14, ySkill2 - 14, ySkill3 - 14, ySkill4 - 14};
            g.drawRegion(imgTouchMove[2], 0, h0, w0, h0, 0, xFire + w0 / 2, yFire + h0 / 2,
                    mGraphics.VCENTER | mGraphics.HCENTER, false);
            if (!mSystem.isPC) {
                if (keyTouch == 4) {
                    g.drawRegion(imgTouchMove[2], 0, 0, w0, h0, 0, xFire + w0 / 2, yFire + h0 / 2,
                            mGraphics.VCENTER | mGraphics.HCENTER, false);
                }
            } else if (keyTouch == 0) {
                g.drawRegion(imgTouchMove[2], 0, 0, w0, h0, 0, xFire + w0 / 2, yFire + h0 / 2,
                        mGraphics.VCENTER | mGraphics.HCENTER, false);
            }
            g.drawRegion(imgTouchMove[3], 0, hImg, wImg, hImg, 0, xSkill1, ySkill1,
                    mGraphics.VCENTER | mGraphics.HCENTER, false);
            g.drawRegion(imgTouchMove[3], 0, hImg, wImg, hImg, 0, xSkill2, ySkill2,
                    mGraphics.VCENTER | mGraphics.HCENTER, false);
            g.drawRegion(imgTouchMove[3], 0, hImg, wImg, hImg, 0, xSkill3, ySkill3,
                    mGraphics.VCENTER | mGraphics.HCENTER, false);
            g.drawRegion(imgTouchMove[3], 0, hImg, wImg, hImg, 0, xSkill4, ySkill4,
                    mGraphics.VCENTER | mGraphics.HCENTER, false);
            if (!mSystem.isPC) {
                if (keyTouch == 5) {
                    g.drawRegion(imgTouchMove[3], 0, 0, wImg, hImg, 0, xSkill1, ySkill1,
                            mGraphics.VCENTER | mGraphics.HCENTER, false);
                }
                if (keyTouch == 6) {
                    g.drawRegion(imgTouchMove[3], 0, 0, wImg, hImg, 0, xSkill2, ySkill2,
                            mGraphics.VCENTER | mGraphics.HCENTER, false);
                }
                if (keyTouch == 7) {
                    g.drawRegion(imgTouchMove[3], 0, 0, wImg, hImg, 0, xSkill3, ySkill3,
                            mGraphics.VCENTER | mGraphics.HCENTER, false);
                }
                if (keyTouch == 8) {
                    g.drawRegion(imgTouchMove[3], 0, 0, wImg, hImg, 0, xSkill4, ySkill4,
                            mGraphics.VCENTER | mGraphics.HCENTER, false);
                }
            } else {
                if (keyTouch == 1) {
                    g.drawRegion(imgTouchMove[3], 0, 0, wImg, hImg, 0, xSkill1, ySkill1,
                            mGraphics.VCENTER | mGraphics.HCENTER, false);
                }
                if (keyTouch == 2) {
                    g.drawRegion(imgTouchMove[3], 0, 0, wImg, hImg, 0, xSkill2, ySkill2,
                            mGraphics.VCENTER | mGraphics.HCENTER, false);
                }
                if (keyTouch == 3) {
                    g.drawRegion(imgTouchMove[3], 0, 0, wImg, hImg, 0, xSkill3, ySkill3,
                            mGraphics.VCENTER | mGraphics.HCENTER, false);
                }
                if (keyTouch == 4) {
                    g.drawRegion(imgTouchMove[3], 0, 0, wImg, hImg, 0, xSkill4, ySkill4,
                            mGraphics.VCENTER | mGraphics.HCENTER, false);
                }
            }
            if (this.cmdChangeFocus != null && !isIntro && !mSystem.isPC) {
                this.cmdChangeFocus.paint(g);
            }
            int i2 = 0;
            while (i2 < MainChar.mQuickslot.length) {
                if (i2 == 0) {
                    if (this.focusedActor != null && !mainChar.setFireChar(this.focusedActor) && !isIntro) {
                        g.drawImage(bt_Speak, xx[i2] + 3, yy[i2] + 3, 0, false);
                    } else if (!mSystem.isPC) {
                        MainChar.mQuickslot[i2].paint(g, xx[i2] + 2, yy[i2] + 2, 0);
                    } else {
                        MainChar.mQuickslot[i2].paint(g, xx[i2] + 2, yy[i2] + 2, this.ypc[i2]);
                    }
                } else if (!mSystem.isPC) {
                    MainChar.mQuickslot[i2].paint(g, xx[i2] + 2, yy[i2] + 2, 0);
                } else {
                    MainChar.mQuickslot[i2].paint(g, xx[i2] + 2, yy[i2] + 2, this.ypc[i2]);
                }
                ++i2;
            }
        } else {
            i = 0;
            while (i < postSkill.length) {
                MainChar.mQuickslot[i].paint(g, postSkill[i], GameCanvas.h - 20, 0);
                g.drawRegion(imgTouchMove[3], 0, hImg, wImg, hImg, 0, postSkill[i], GameCanvas.h - 20,
                        mGraphics.VCENTER | mGraphics.HCENTER, false);
                ++i;
            }
        }
        if (mSystem.isPC) {
            i = 0;
            while (i < this.numPC.length) {
                FontTeam.numberSmall_white.drawString(g, String.valueOf(i + 1), this.numPC[i], yFire + 41, 2, false);
                ++i;
            }
        }
        if (keyTouch != -1 && this.timeQouckSlot <= 0 && this.countTouchmove == 0) {
            keyTouch = -1;
        }
        if (!Tilemap.isMapIntro() && GameCanvas.isTouch) {
            g.drawImage(imgMenu[this.isTouchMenu ? 1 : 0], xMenu, yMenu, 0, false);
        }
    }

    public static int getColorFocus(int charLv, int FocusActorLv, byte cat) {
        if (cat == 0) {
            if (charLv < FocusActorLv) {
                int value = Utils.abs(charLv - FocusActorLv);
                if (value >= 15) {
                    return ColorHpFocus[5];
                }
                if (value > 10) {
                    return ColorHpFocus[4];
                }
                if (value > 5) {
                    return ColorHpFocus[3];
                }
            } else if (charLv > FocusActorLv) {
                int value = Utils.abs(charLv - FocusActorLv);
                if (value >= 15) {
                    return ColorHpFocus[0];
                }
                if (value > 10) {
                    return ColorHpFocus[1];
                }
                if (value > 5) {
                    return ColorHpFocus[2];
                }
            }
            return ColorHpFocus[0];
        }
        if (cat == 1) {
            if (charLv < FocusActorLv) {
                int value = Utils.abs(charLv - FocusActorLv);
                if (value >= 15) {
                    return ColorHpFocus1[5];
                }
                if (value > 10) {
                    return ColorHpFocus1[4];
                }
                if (value > 5) {
                    return ColorHpFocus1[3];
                }
            } else if (charLv > FocusActorLv) {
                int value = Utils.abs(charLv - FocusActorLv);
                if (value >= 15) {
                    return ColorHpFocus1[0];
                }
                if (value > 10) {
                    return ColorHpFocus1[1];
                }
                if (value > 5) {
                    return ColorHpFocus1[2];
                }
            }
            return ColorHpFocus1[0];
        }
        return 0;
    }

    public void paintFocus(mGraphics g) {
        if (this.focusedActor == null) {
            return;
        }
        if (this.focusedActor != null && Utils.getDistance(mainChar, this.focusedActor) > 192) {
            return;
        }
        if (isIntro) {
            if (this.focusedActor != null) {
                g.drawRegion(imgfocus[2], 0, this.fFocus * 10, 15, 10, 0, this.focusedActor.x,
                        this.focusedActor.y - this.focusedActor.getHeight() + GameCanvas.gameTick % 7, 3, false);
            }
            return;
        }
        if (this.focusedActor != null) {
            if (this.focusedActor.catagory == 1 && this.focusedActor.getState() == 8) {
                return;
            }
            if (this.focusedActor.isNpcChar()) {
                if (this.focusedActor.chat == null) {
                    g.drawRegion(imgfocus[2], 0, this.fFocus * 10, 15, 10, 0, this.focusedActor.x,
                            this.focusedActor.y - this.focusedActor.getHeight() - 20 + GameCanvas.gameTick % 7, 3,
                            false);
                }
                return;
            }
            if (this.focusedActor.catagory == 2 || this.focusedActor.catagory > 2 || this.focusedActor.isNpcChar()) {
                if (this.focusedActor.catagory > 2) {
                    if (this.focusedActor.chat == null) {
                        g.drawRegion(imgfocus[2], 0, this.fFocus * 10, 15, 10, 0, this.focusedActor.x,
                                this.focusedActor.y - 20 + GameCanvas.gameTick % 7, 3, false);
                    }
                } else if (this.focusedActor.chat == null) {
                    g.drawRegion(imgfocus[2], 0, this.fFocus * 10, 15, 10, 0, this.focusedActor.x,
                            this.focusedActor.y - this.focusedActor.getHeight() - 30 + GameCanvas.gameTick % 7, 3,
                            false);
                }
            } else if (this.focusedActor.getHp() >= 0 && this.focusedActor.getMaxHp() > 0
                    && this.focusedActor.chat == null) {
                int yss = 0;
                if (this.focusedActor.catagory == 1 && this.focusedActor.getColorName() > 0) {
                    yss = -10;
                }
                g.drawRegion(imgfocus[2], 0, this.fFocus * 10, 15, 10, 0, this.focusedActor.x,
                        this.focusedActor.y - this.focusedActor.getHeight() + GameCanvas.gameTick % 7 + yss, 3, false);
            }
        }
    }

    private void paintFocusActorInfo(mGraphics g) {
        if (isIntro) {
            return;
        }
        if (this.focusedActor != null && Utils.getDistance(mainChar, this.focusedActor) > 192) {
            return;
        }
        if (this.focusedActor.isNPC()) {
            return;
        }
        if (this.focusedActor == null) {
            GameCanvas.resetTrans(g);
            GameScr.Font3d(g, nameMap, GameCanvas.w - 2, 2, 1, mFont.tahoma_7b_white, false);
        }
        if (this.focusedActor != null && this.focusedActor.catagory == 1 && this.focusedActor.getHp() < 0) {
            return;
        }
        if (this.focusedActor != null) {
            if (this.focusedActor.catagory == 1 && this.focusedActor.getState() == 8) {
                return;
            }
            if (this.focusedActor.isNpcChar()) {
                GameCanvas.resetTrans(g);
                GameScr.Font3d(g, this.focusedActor.getName(), GameCanvas.w - 2 - wMiniMap, 2, 1, mFont.tahoma_7b_white,
                        false);
                return;
            }
            if (this.focusedActor.catagory == 2 || this.focusedActor.catagory > 2 || this.focusedActor.isNpcChar()) {
                if (this.focusedActor.catagory <= 2) {
                    GameCanvas.resetTrans(g);
                    GameScr.Font3d(g, this.focusedActor.getName(), GameCanvas.w - 2 - wMiniMap, 2, 1,
                            mFont.tahoma_7b_white, false);
                }
            } else if (this.focusedActor.getHp() >= 0 && this.focusedActor.getMaxHp() > 0) {
                GameCanvas.resetTrans(g);
                if (this.focusedActor.getTypeMove() == 5) {
                    mFont f = mFont.tahoma_7b_white;
                    if (this.focusedActor.getColorName() == 1) {
                        f = mFont.tahoma_7b_green;
                    }
                    if (this.focusedActor.getColorName() == 2) {
                        f = mFont.tahoma_7b_orange;
                    }
                    if (this.focusedActor.getColorName() == 3) {
                        f = mFont.tahoma_7b_yellow;
                    }
                    GameScr.Font3d(g, this.focusedActor.getName(), GameCanvas.w - 5 - wMiniMap, 2, 1, f, false);
                } else {
                    g.drawRegion(imghealth[2], 0, 0, 62, 9, 0, GameCanvas.w - 65 - wMiniMap, 14, 0, false);
                    int w = mGraphics.getImageWidth(imghealth[1]);
                    int wpPaint = 0;
                    long tile = w;
                    long objFocushp = this.focusedActor.getHp();
                    long maxHp = tile * objFocushp;
                    wpPaint = (int) (maxHp / (long) this.focusedActor.getMaxHp());
                    if (wpPaint <= 3) {
                        wpPaint = 3;
                    } else if (wpPaint > w) {
                        wpPaint = w;
                    }
                    mFont f = mFont.tahoma_7b_white;
                    if (this.focusedActor.getColorName() == 1) {
                        f = mFont.tahoma_7b_green;
                    }
                    if (this.focusedActor.getColorName() == 2) {
                        f = mFont.tahoma_7b_orange;
                    }
                    if (this.focusedActor.getColorName() == 3) {
                        f = mFont.tahoma_7b_yellow;
                    }
                    g.drawRegion(imghealth[1], 0, 0, wpPaint, 7, 0, GameCanvas.w - 64 - wMiniMap, 15, 0, false);
                    FontTeam.numberSmall_white.drawString(g,
                            String.valueOf(GameScr.getTextPaintHP(this.focusedActor.getHp())) + "/"
                                    + GameScr.getTextPaintHP(this.focusedActor.getMaxHp()),
                            GameCanvas.w - 64 + 30 - wMiniMap, 14, 2, false);
                    int xs = 0;
                    int xnguhanh = 0;
                    if (this.focusedActor.catagory == 1 && this.focusedActor.getHeNguHanh() > -1) {
                        xnguhanh = 10;
                    }
                    if (this.focusedActor.getColorName() != 0) {
                        mFont.name_Black.drawString(g, this.focusedActor.getName(), GameCanvas.w - 2 + 1 - 3 - wMiniMap,
                                2, 1, false);
                        mFont.name_White.drawString(g, this.focusedActor.getName(), GameCanvas.w - 2 - 3 - wMiniMap, 1,
                                1, false);
                        xs = FontTeam.numberSmall_white.getWidth("" + this.focusedActor.getLevel()) + 2;
                        FontTeam.numberSmall_white.drawString(g, "" + this.focusedActor.getLevel(),
                                GameCanvas.w - 6 - wMiniMap, 24, 1, false);
                    } else {
                        GameScr.Font3d(g, this.focusedActor.getName(), GameCanvas.w - 2 - 3 - xnguhanh - wMiniMap, 1, 1,
                                f, false);
                        if (this.focusedActor.getColorName() == 1) {
                            FontTeam.numberSmall_green.drawString(g, "" + this.focusedActor.getLevel(),
                                    GameCanvas.w - 6 - wMiniMap, 24, 1, false);
                        } else if (this.focusedActor.getColorName() == 3) {
                            FontTeam.numberSmall_yeallow.drawString(g, "" + this.focusedActor.getLevel(),
                                    GameCanvas.w - 6 - wMiniMap, 24, 1, false);
                        } else if (this.focusedActor.getColorName() == 0) {
                            FontTeam.numberSmall_white.drawString(g, "" + this.focusedActor.getLevel(),
                                    GameCanvas.w - 6 - wMiniMap, 24, 1, false);
                        } else {
                            GameScr.Font3d(g, "" + this.focusedActor.getLevel(), GameCanvas.w - 5 - wMiniMap, 21, 1, f,
                                    false);
                        }
                        xs = f.getWidth("" + this.focusedActor.getLevel()) + 2;
                    }
                    if (this.focusedActor != null && this.focusedActor.catagory == 1
                            && this.focusedActor.getHeNguHanh() > -1) {
                        g.drawRegion(imgNguhanh, 0, 10 * this.focusedActor.getHeNguHanh(), 10, 10, 0,
                                GameCanvas.w - 12 - wMiniMap, 2, 1, false);
                    }
                    g.drawImage(imglv, GameCanvas.w - 15 - xs - wMiniMap, 25, 0, false);
                }
            }
        }
    }

    public void startNewMagicBeam(int type, Actor from, Actor to, int x, int y, int power, byte effect) {
        MagicBeam a = new MagicBeam();
        ((IArrow) a).set(type, x, y, power, effect, from, to);
        arrowsUp.addElement(a);
    }

    public void startNewMagicBeam(int type, Actor from, Actor to, int x, int y, int power, byte effect, int angle) {
        MagicBeam a = new MagicBeam();
        ((IArrow) a).set(type, x, y, power, effect, from, to);
        ((IArrow) a).setAngle(angle);
        arrowsUp.addElement(a);
    }

    public void startNewMagicBeam(int type, Actor from, Actor to, int x, int y, int power, byte effect, int angle,
                                  int indexhead) {
        MagicBeam a = new MagicBeam();
        ((IArrow) a).set(type, x, y, power, effect, from, to);
        ((IArrow) a).setAngle(angle);
        ((IArrow) a).setIDHEAD(indexhead);
        arrowsUp.addElement(a);
    }

    public void startNewMagicBeam(int type, Actor from, Actor to, int x, int y, int power, int eff) {
        MagicBeam a = new MagicBeam();
        ((IArrow) a).set(type, x, y, power, from, to, eff);
        ((IArrow) a).setAngle(angle);
        arrowsUp.addElement(a);
    }

    public void startNewMagicBeam_New(int type, Actor to, int x, int y, int power, int eff) {
        MagicBeam a = new MagicBeam();
        ((IArrow) a).set(type, x, y, power, (byte) -1, null, to);
        ((IArrow) a).setAngle(angle);
        arrowsUp.addElement(a);
    }

    public void startNewArrow(int x, int y, Actor owner, Actor target, int power, int eff0, int eff1, int eff2,
                              int endEff, int typeEnd, int speed) {
        Arrow a = new Arrow();
        a.set(x, y, owner, target, power, eff0, eff1, eff2, endEff, (byte) typeEnd, (byte) speed);
        arrowsUp.addElement(a);
    }

    public void startNewArrow(int x, int y, Actor owner, Actor target, int power, int eff0, int eff1, int eff2,
                              int endEff, int typeEnd, int speed, int idEff) {
        Arrow a = new Arrow();
        a.idEff = idEff;
        a.set(x, y, owner, target, power, eff0, eff1, eff2, endEff, (byte) typeEnd, (byte) speed);
        arrowsUp.addElement(a);
    }

    public void startNewArrow_Dow(int x, int y, Actor owner, Actor target, int power, int eff0, int eff1, int eff2,
                                  int endEff, int typeEnd, int speed) {
        Arrow a = new Arrow();
        a.set(x, y, owner, target, power, eff0, eff1, eff2, endEff, (byte) typeEnd, (byte) speed);
        this.arrowsDown.addElement(a);
    }

    public void startDragon(int x, int y, Actor target, int power, int eff0, int endEff, int follow) {
        Arrow a = new Arrow();
        a.setDragon(x, y, target, power, eff0, endEff, follow);
        this.arrowsDown.addElement(a);
    }

    public void StartNewBaburan(int type, Actor from, Actor to, int x, int y, int power, byte effect, int imgIndex) {
        BaBuran b = new BaBuran(imgIndex);
        b.set(type, x, y, power, effect, from, to);
        arrowsUp.addElement(b);
    }

    public void StartNewEffectEnd(int type, int x, int y) {
        EffectEnd eff = new EffectEnd(type, x, y);
        EffectManager.addHiEffect(eff);
    }

    public void StartNewEffectEnd_Low(int type, int x, int y) {
        EffectEnd eff = new EffectEnd(type, x, y);
        EffectManager.addLowEffect(eff);
    }

    public void StartNewLightning(Actor target) {
        Lightning light = new Lightning(target);
        EffectManager.addHiEffect(light);
    }

    public void StartNewLightning(Actor from, Actor target, int type) {
        Lightning light = new Lightning(from, target, type);
        EffectManager.addHiEffect(light);
    }

    public void StartNewLightning(Actor target, int type) {
        Lightning light = new Lightning(target, type);
        EffectManager.addHiEffect(light);
    }

    public void StartNewLazer(int x, int y, int xto, int yto, Actor from, Actor acto) {
        WeaponsLazer wp = new WeaponsLazer(x, y, xto, yto, from, acto);
        EffectManager.addHiEffect(wp);
    }

    public Actor findCharByID(short id) {
        isFindChar = true;
        int i = this.actors.size() - 1;
        while (i >= 0) {
            Actor a = (Actor) this.actors.elementAt(i);
            if (a.catagory == 0 && a.ID == id) {
                isFindChar = false;
                return a;
            }
            --i;
        }
        isFindChar = false;
        return null;
    }

    public Monster findMonsterByID(short id) {
        isFindMonster = true;
        Actor a = null;
        int i = this.actors.size() - 1;
        while (i >= 0) {
            a = (Actor) this.actors.elementAt(i);
            if (a.catagory == 1 && a.ID == id) {
                isFindMonster = false;
                return (Monster) a;
            }
            --i;
        }
        isFindMonster = false;
        return null;
    }

    public Actor findActorByID(short id, byte cat) {
        Actor a = null;
        int i = this.actors.size() - 1;
        while (i >= 0) {
            a = (Actor) this.actors.elementAt(i);
            if (a.ID == id && a.catagory == cat) {
                return a;
            }
            --i;
        }
        return null;
    }

    private Actor findItemByID(short id, short cat) {
        Actor a = null;
        int i = this.actors.size() - 1;
        while (i >= 0) {
            a = (Actor) this.actors.elementAt(i);
            if (a.catagory == cat && a.ID == id) {
                return a;
            }
            --i;
        }
        return null;
    }

    private Dropable findPotionByID(short id, int catagory) {
        int i = this.actors.size() - 1;
        while (i >= 0) {
            Actor a = (Actor) this.actors.elementAt(i);
            if (a.catagory == catagory && a.ID == id) {
                return (Dropable) a;
            }
            --i;
        }
        return null;
    }

    public void onMonsterInfo(MonsterInfo monsterInfo) {
        if (!this.readyGetGameLogic) {
            return;
        }
        Monster m = this.findMonsterByID(monsterInfo.id);
        if (m != null) {
            m.setInfo(monsterInfo);
        }
    }

    public void charOutGame(short charID) {
        Actor a = null;
        int i = 0;
        while (i < this.vecCharParty.size()) {
            a = (Actor) this.vecCharParty.elementAt(i);
            if (a != null && a.catagory == 0 && a.ID == charID) {
                this.vecCharParty.removeElement(a);
            }
            ++i;
        }
        i = 0;
        while (i < this.actors.size()) {
            a = (Actor) this.actors.elementAt(i);
            if (a.catagory == 0 && a.ID == charID) {
                a.wantDestroy = true;
                this.removepet(a.ID);
                EffectManager.addHiDataeffectSkill(156, (int) a.x, (int) a.y, 1);
                break;
            }
            ++i;
        }
    }

    public void removepet(short charID) {
        int i = 0;
        while (i < GameCanvas.gameScr.actors.size()) {
            Actor ac = (Actor) GameCanvas.gameScr.actors.elementAt(i);
            if (ac != null && ac.catagory == 12 && ac.ID == charID) {
                ac.wantDestroy = true;
            }
            ++i;
        }
    }

    public void onConnectOK() {
        if (!mSystem.isj2me) {
            GameService.gI().doRequestDataChar(Res.VERSION_CHUNK, -1);
        }
    }

    public void onConnectFail() {
        if (GameCanvas.currentScreen != MenuLogin.gI()) {
            ChangScr.gI().switchToMe();
            if (GameCanvas.currentDialog == null) {
                GameCanvas.startOKDlg(T.khongtheketnoi, new mCommand("", this, 72));
            }
        } else {
            GameCanvas.gameScr.clearloadMap();
            GameScr.removeAllchat();
            MenuLogin.gI().switchToMe();
        }
        this.cmdDisconect = null;
        if (!isMeLogin) {
            isDisConect = true;
            timeReconnect = System.currentTimeMillis() + 30000L;
        } else {
            isDisConect = false;
        }
        if (!isMeLogin) {
            isDisConect = true;
            timeReconnect = System.currentTimeMillis() + 30000L;
        } else {
            isDisConect = false;
        }
    }

    public void onDisconnect(String pos) {
        this.cmdDisconect = new mCommand("", this, 73);
        this.tickConnect = 0;
        mainChar.removeAllEff();
        if (!isMeLogin) {
            isDisConect = true;
            timeReconnect = System.currentTimeMillis() + 30000L;
        } else {
            isDisConect = false;
        }
        GameCanvas.gameScr.clearloadMap();
        GameScr.removeAllchat();
        MenuLogin.gI().switchToMe();
        GameCanvas.hideAllDialog();
    }

    public void onViewAnimalInfo(Char ch) {
    }

    public void onAnimalWearingInfo(short charID, mVector items, Image imgAnimal0, byte numFrame0, int wimg0, int himg0,
                                    byte timeChangeFrame0) {
    }

    public void onGetItemFromGround(short whoGet, short id, short cat) {
        Actor item = this.findItemByID(id, cat);
        if (item != null) {
            Char c;
            if (this.focusedActor != null && this.focusedActor.equals(item)) {
                this.focusedActor = null;
            }
            if ((c = (Char) this.findCharByID(whoGet)) != null) {
                item.setXYflyto(c.x, (short) (c.y - 30));
            } else {
                item.wantDestroy = true;
            }
        }
    }

    public void dellPotionQuest() {
        int i = 10;
        while (i < 14) {
            GameScr.mainChar.potions[i] = 0;
            ++i;
        }
    }

    public void onNPCKeepItem(mVector itemBag) {
        Char.itembag = itemBag;
        this.showWindow(0, true, new byte[]{22});
    }

    public void onNPCSellGemItem(mVector gem) {
        byte[] byArray = new byte[2];
        byArray[1] = 10;
        this.showWindow(1, true, byArray);
    }

    public void oncancelTrade() {
        this.cancelTrade();
        GameCanvas.startOKDlg("Giao d\u1ecbch b\u1ecb h\u1ee7y.");
    }

    private void cancelTrade() {
        GameScr.mainChar.isTrade = false;
        GameScr.mainChar.tItems.removeAllElements();
        GameScr.mainChar.rItems.removeAllElements();
        if (MainChar.listPotion != null) {
            int i = 0;
            while (i < MainChar.listPotion.length) {
                MainChar.listPotion[i].numTrade = 0;
                ++i;
            }
        }
    }

    public void onListShopOfNPC(byte[] idShop) {
        GameCanvas.currentDialog = null;
        mVector menu = new mVector();
        int i = 0;
        while (i < 3) {
            int ii = i;
            String a = String.valueOf(ii) + ":" + idShop[idShop.length - 1] + ":" + idShop[idShop.length - 2];
            menu.addElement(new mCommand("Gian h\u00e0ng " + (i + 1), 85, a));
            ++i;
        }
        GameCanvas.menu.startAt(menu, 3);
    }

    public void showWindow(int focus, boolean isSell, byte[] index) {
        byte cfr_ignored_0 = index[focus];
        byte cfr_ignored_1 = index[focus];
        byte cfr_ignored_2 = index[focus];
        if (GameCanvas.currentScreen != MainMenu.gI()) {
            MainMenu.gI().switchToMe(this);
        }
        focus = 0;
        MainMenu.gI().setInfo(focus, isSell, index);
    }

    public static void loadConfig(int type) {
        switch (type) {
            case 0: {
                paintCay = 0;
                paintChar = 1;
                break;
            }
            case 1:
            case 2: {
                paintCay = 1;
                paintChar = 1;
                Char.paintOrtherChar = true;
                break;
            }
            case 3: {
                paintCay = 0;
                paintChar = 0;
                break;
            }
            case 4: {
                paintCay = 0;
                Char.paintOrtherChar = false;
            }
        }
    }

    public void showConfig() {
        mVector menuItem = new mVector();
        String[] str = new String[]{"Thấp", "V\u1eeba", "Cao", "R\u1ea5t th\u1ea5p"};
        int i = 0;
        while (i < 4) {
            int ii = i;
            mCommand cmd = new mCommand(str[i], 89, String.valueOf(ii));
            menuItem.addElement(cmd);
            ++i;
        }
        GameCanvas.menu.startAt(menuItem, 3);
    }

    public void setNPC_server_limit(String name0, short ID0, short idImg0, short x0, short y0, short wimg0, short himg0,
                                    byte nFrame0, byte typeLimit) {
        if (typeLimit != 1) {
            return;
        }
        int size0 = this.npc_limit.size();
        Actor ac = null;
        int i = 0;
        while (i < size0) {
            ac = (Actor) this.npc_limit.elementAt(i);
            if (ac != null && ac.catagory == 2) {
                NPC npc = (NPC) ac;
                if (npc.npcType == 1) {
                    Npc_Server npc_sever = (Npc_Server) npc;
                    if (npc_sever.ID == ID0) {
                        npc_sever.name = name0;
                        npc_sever.type = npc_sever.ID = ID0;
                        npc_sever.idImg = idImg0;
                        npc_sever.x = x0;
                        npc_sever.y = y0;
                        npc_sever.width = wimg0;
                        npc_sever.height = himg0;
                        npc_sever.nFrame = nFrame0;
                        npc.typeLimit = typeLimit;
                        return;
                    }
                }
            }
            ++i;
        }
        Npc_Server bos = new Npc_Server();
        bos.name = name0;
        bos.type = bos.ID = ID0;
        bos.idImg = idImg0;
        bos.x = x0;
        bos.y = y0;
        bos.width = wimg0;
        bos.height = himg0;
        bos.nFrame = nFrame0;
        bos.typeLimit = typeLimit;
        this.npc_limit.addElement(bos);
    }

    public void setNPC_server(String name0, short ID0, short idImg0, short x0, short y0, short wimg0, short himg0,
                              byte nFrame0, byte typeLimit) {
        this.setNPC_server_limit(name0, ID0, idImg0, x0, y0, wimg0, himg0, nFrame0, typeLimit);
        int size0 = this.actors.size();
        Actor ac = null;
        int i = 0;
        while (i < size0) {
            ac = (Actor) this.actors.elementAt(i);
            if (ac != null && ac.catagory == 2) {
                NPC npc = (NPC) ac;
                if (npc.npcType == 1) {
                    Npc_Server npc_sever = (Npc_Server) npc;
                    if (npc_sever.ID == ID0) {
                        npc_sever.name = name0;
                        npc_sever.type = npc_sever.ID = ID0;
                        npc_sever.idImg = idImg0;
                        npc_sever.x = x0;
                        npc_sever.y = y0;
                        npc_sever.width = wimg0;
                        npc_sever.height = himg0;
                        npc_sever.nFrame = nFrame0;
                        npc.typeLimit = typeLimit;
                        return;
                    }
                }
            }
            ++i;
        }
        Npc_Server bos = new Npc_Server();
        bos.name = name0;
        bos.type = bos.ID = ID0;
        bos.idImg = idImg0;
        bos.x = x0;
        bos.y = y0;
        bos.width = wimg0;
        bos.height = himg0;
        bos.nFrame = nFrame0;
        bos.typeLimit = typeLimit;
        this.actors.addElement(bos);
    }

    public boolean checkMoveLimit(int xTo, int yTo) {
        if (this.npc_limit.size() == 0) {
            return false;
        }
        int size0 = this.npc_limit.size();
        Actor a = null;
        int i = 0;
        while (i < size0) {
            Npc_Server npc;
            a = (Actor) this.npc_limit.elementAt(i);
            if (a != null && xTo >= (npc = (Npc_Server) a).getLimxL() && xTo <= npc.getLimxR() && yTo >= npc.getLimyU()
                    && yTo <= npc.getLimyD()) {
                return true;
            }
            ++i;
        }
        return false;
    }

    private void updatePoint() {
        if (GameCanvas.menuSelectitem.showMenu) {
            return;
        }
        if (isIntro && !canmove) {
            return;
        }
        if (GameCanvas.msgchat != null && GameCanvas.msgchat.isShow) {
            return;
        }
        if (MainChar.blockkey) {
            return;
        }
        if (isTouchMove) {
            return;
        }
        if (GameCanvas.currentDialog != null) {
            return;
        }
        if (GameCanvas.menu.showMenu) {
            return;
        }
        if (GameCanvas.menu2.isShow) {
            return;
        }
        if (GameCanvas.isPointer(xFire, yFire, 50, 50, 0)) {
            return;
        }
        if (GameCanvas.isPointer(xSkill4 - 14, ySkill4 - 14, 28, 28, 0)) {
            return;
        }
        if (GameCanvas.isPointer(xSkill3 - 14, ySkill3 - 14, 28, 28, 0)) {
            return;
        }
        if (GameCanvas.isPointer(xSkill2 - 14, ySkill2 - 14, 28, 28, 0)) {
            return;
        }
        if (GameCanvas.isPointer(xSkill1 - 14, ySkill1 - 14, 28, 28, 0)) {
            return;
        }
        if (GameCanvas.isPointer(GameCanvas.w - mGraphics.getImageWidth(imgPointQuest) - 5 - wMiniMap, 5, 30, 30, 0)) {
            return;
        }
        if (charcountdonw != null) {
            return;
        }
        if (GameCanvas.isPointer(GameCanvas.w - 30, 40, 30, 30, 0) && !mSystem.isPC) {
            return;
        }
        if (this.timetouch >= 0) {
            --this.timetouch;
        }
        if (GameCanvas.isPointer(0, 0, GameCanvas.w, GameCanvas.h, 0)) {
            if (GameCanvas.isPointer(this.posMiniMap.x, this.posMiniMap.y, wMiniMap, hMiniMap, 0)
                    && GameCanvas.isPointerClick[0]) {
//                int dx = GameCanvas.pxLast[0] - GameCanvas.px[0];
//                int dy = GameCanvas.pyLast[0] - GameCanvas.py[0];
//                if (Util.abs(dy) < 10 && Util.abs(dx) < 10) {
//                    if (this.posCam == null) {
//                        this.posCam = new Position(0, 0);
//                    }
//                    this.posCam.x = (short) (cmtoXMini + GameCanvas.px[0] - this.posMiniMap.x);
//                    this.posCam.y = (short) (cmtoYmini + GameCanvas.py[0] - this.posMiniMap.y);
//                    GameScr.mainChar.posTransRoad = null;
//                    if (!Tilemap.tileTypeAtPixel(this.posCam.x * 16 + 2, this.posCam.y * 16 + 2, 2)
//                            && !this.checkMoveLimit(this.posCam.x * 16 + 2, this.posCam.y * 16 + 2)) {
//                        GameScr.mainChar.xTo = GameScr.mainChar.x;
//                        GameScr.mainChar.yTo = GameScr.mainChar.y;
//                        GameScr.mainChar.xBegin = GameScr.mainChar.x;
//                        GameScr.mainChar.yBegin = GameScr.mainChar.y;
//                        this.tg.index = (byte) 8;
//                        GameScr.mainChar.posTransRoad = this.updateFindRoad(GameScr.mainChar.x / 16,
//                                GameScr.mainChar.y / 16, this.posCam.x, this.posCam.y);
//                        GameScr.mainChar.countRoad = 0;
//                        GameCanvas.isPointerClick[0] = false;
//                        return;
//                    }
//                }
                if (GameCanvas.isTouch) {
                    GameCanvas.mapScr.switchToMe(this);
                    return;
                }
//                mVector v = new mVector();
//                mVector currQuest1 = ListQuest[indexTypeQuest];
//                QuestInfo qq = (QuestInfo) currQuest1.elementAt(indexQuest);
//                if (qq != null && qq.status == 2) {
//                    v.addElement(cmdHuyQuest);
//                }
//                v.addElement(cmdChangeMapScr);
//                GameCanvas.menu.startAt(v, 0);
            }
            this.isAutoChangeFocus = false;
            if (this.timetouch < 0 && GameCanvas.isPointerClick[0]) {
                int aa = cmx + GameCanvas.px[0];
                int bb = cmy + GameCanvas.py[0];
                if (this.focusedActor != null) {
                    if (this.focusedActor.catagory != 10) {
                        int size0 = this.actors.size();
                        Actor ac = null;
                        int i = 0;
                        while (i < size0) {
                            ac = (Actor) this.actors.elementAt(i);
                            if (Util.abs(ac.x - aa) <= 20 && Util.abs(ac.y - 20 - bb) <= 40) {
                                this.isAutoChangeFocus = false;
                                this.beginAuto = false;
                                GameCanvas.isPointerClick[0] = false;
                                if (this.focusedActor.ID == ac.ID) {
                                    if (mainChar.setFireChar(this.focusedActor)) {
                                        this.doFire();
                                    }
                                    if (this.focusedActor.catagory == 1) {
                                        this.doFire();
                                        return;
                                    }
                                    if (this.focusedActor.isNPC()) {
                                        this.doFire();
                                        return;
                                    }
                                    if (ac.catagory == 0) {
                                        if (((Char) this.focusedActor).idBot == -1 && this.canParty()) {
                                            if (!mainChar.setFireChar(this.focusedActor)) {
                                                this.doParty();
                                            }
                                            return;
                                        }
                                        this.doTouchQuickSlot(4);
                                        return;
                                    }
                                    if (this.focusedActor.catagory != 2 || ac.catagory != 2) {
                                        this.doFire();
                                        if (GameCanvas.menuSelectitem.isAutoDanh[0] && this.focusedActor.catagory != 2
                                                || ac.catagory != 2) {
                                            if (!this.isStartAutoAttack) {
                                                this.xStartAuto = this.focusedActor.x;
                                                this.yStartAuto = this.focusedActor.y;
                                            }
                                            this.isStartAutoAttack = true;
                                        } else {
                                            this.isStartAutoAttack = true;
                                        }
                                        return;
                                    }
                                    if (((NPC) this.focusedActor).type == ((NPC) ac).type) {
                                        if (((NPC) ac).type != 4) {
                                            this.doFire();
                                            return;
                                        }
                                        if (((NPC) this.focusedActor).idLinhGac == ((NPC) ac).idLinhGac) {
                                            this.doFire();
                                            return;
                                        }
                                        if (ac.ID != GameScr.mainChar.ID) {
                                            this.focusedActor = ac;
                                            this.doFire();
                                            return;
                                        }
                                    }
                                }
                                if (ac.ID != GameScr.mainChar.ID) {
                                    boolean isContinue = false;
                                    if (typeOptionFocus == 1) {
                                        if (ac.catagory == 0 && ac.isNPC()) {
                                            isContinue = true;
                                        }
                                    } else if (typeOptionFocus == 2) {
                                        if (!ac.isNPC()) {
                                            isContinue = true;
                                        }
                                    } else if (typeOptionFocus == 3) {
                                        if (ac.catagory == 0 || ac.isNPC()) {
                                            isContinue = true;
                                        }
                                    } else if (typeOptionFocus == 4 && (ac.catagory == 0 || ac.isNPC())) {
                                        isContinue = true;
                                    }
                                    if (!ac.canFocus()) {
                                        isContinue = true;
                                    }
                                    if (!isContinue) {
                                        this.focusedActor = ac;
                                        this.isAutoChangeFocus = false;
                                    }
                                    if (Util.abs(ac.x - GameScr.mainChar.x) > 30
                                            || Util.abs(ac.y - GameScr.mainChar.y) > 30) {
                                        if ((this.focusedActor.catagory != 2 || ac.catagory != 2)
                                                && GameCanvas.menuSelectitem.isAutoDanh[0]) {
                                            this.isStartAutoAttack = true;
                                        }
                                        if (this.isMovebyTouch) {
                                            this.findRoad(ac.x, ac.y);
                                        }
                                        this.isAutoChangeFocus = false;
                                        if (GameScr.mainChar.posTransRoad != null) {
                                            this.beginAuto = false;
                                        }
                                    }
                                    if (ac.canFocus()) {
                                        this.focusedActor = ac;
                                    }
                                    if (GameCanvas.menuSelectitem.isAutoDanh[0] && this.focusedActor.catagory != 2
                                            || ac.catagory != 2) {
                                        this.isStartAutoAttack = true;
                                    }
                                }
                                return;
                            }
                            ++i;
                        }
                    } else if (GameScr.mainChar.x >= this.xBeginFrame && GameScr.mainChar.x <= this.xTheendFrame + 32
                            && GameScr.mainChar.y >= this.yBeginFrame && GameScr.mainChar.y <= this.yTheendFrame + 32) {
                        int size1 = this.actors.size();
                        Actor ac = null;
                        int i = 0;
                        while (i < size1) {
                            ac = (Actor) this.actors.elementAt(i);
                            if (aa >= ac.x + 2 && aa <= ac.x + 30 && bb >= ac.y && bb <= ac.y + 30) {
                                GameCanvas.isPointerClick[0] = false;
                                if (this.focusedActor.ID != -1 && this.focusedActor.ID == ac.ID) {
                                    if (GameScr.mainChar.state == 0) {
                                        this.doFire();
                                    }
                                    return;
                                }
                                if (ac.ID != GameScr.mainChar.ID && (this.isMovebyTouch || mSystem.isPC)) {
                                    this.findRoad(aa, bb);
                                    if (GameScr.mainChar.posTransRoad != null) {
                                        this.beginAuto = false;
                                    }
                                }
                                return;
                            }
                            ++i;
                        }
                    }
                } else {
                    int size0 = this.actors.size();
                    Actor ac = null;
                    int i = 0;
                    while (i < size0) {
                        ac = (Actor) this.actors.elementAt(i);
                        if (ac != null && ac.canFocus() && Util.abs(ac.x - aa) <= 20 && Util.abs(ac.y - 20 - bb) <= 40
                                && !ac.equals(mainChar)) {
                            if (ac.isNPC()) {
                                this.focusedActor = ac;
                            }
                            if (ac.catagory == 1 && !ac.canFocusMonster()) {
                                this.focusedActor = ac;
                            }
                            if (ac.catagory == 0) {
                                this.focusedActor = ac;
                            }
                            this.isAutoChangeFocus = false;
                            this.beginAuto = false;
                        }
                        ++i;
                    }
                }
                if (this.isMovebyTouch || mSystem.isPC) {
                    this.findRoad(aa, bb);
                    if (GameScr.mainChar.posTransRoad != null) {
                        if (mainChar.isNPC()) {
                            GameScr.mainChar.posTransRoad = null;
                            this.doHuyBanHang();
                        }
                        this.beginAuto = false;
                    }
                }
            }
        }
    }

    public void findRoad(int aa, int bb) {
        if (!mainChar.mcanmove()) {
            return;
        }
        aa /= 16;
        bb /= 16;
        if (!Tilemap.tileTypeAtPixel(cmx + GameCanvas.px[0], cmy + GameCanvas.py[0], 2) || isIntro) {
            if (this.posCam == null) {
                this.posCam = new Position(0, 0);
            }
            this.posCam.x = (short) aa;
            this.posCam.y = (short) bb;
            GameCanvas.isPointerClick[0] = false;
            GameScr.mainChar.xTo = GameScr.mainChar.x;
            GameScr.mainChar.yTo = GameScr.mainChar.y;
            GameScr.mainChar.xBegin = GameScr.mainChar.x;
            GameScr.mainChar.yBegin = GameScr.mainChar.y;
            this.tg.index = 0;
            GameScr.mainChar.posTransRoad = MainUnity.isJava
                    ? this.updateFindRoad(GameScr.mainChar.x / 16, GameScr.mainChar.y / 16, aa, bb)
                    : this.updateFindRoad(GameScr.mainChar.x / 16, GameScr.mainChar.y / 16, aa, bb);
            GameScr.mainChar.countRoad = 0;
        }
    }

    public void findRoad2(int aa, int bb) {
        if (!mainChar.mcanmove()) {
            return;
        }
        aa /= 16;
        bb /= 16;
        if (this.posCam == null) {
            this.posCam = new Position(0, 0);
        }
        this.posCam.x = (short) aa;
        this.posCam.y = (short) bb;
        GameScr.mainChar.xTo = GameScr.mainChar.x;
        GameScr.mainChar.yTo = GameScr.mainChar.y;
        GameScr.mainChar.xBegin = GameScr.mainChar.x;
        GameScr.mainChar.yBegin = GameScr.mainChar.y;
        this.tg.index = (byte) 8;
        System.out.println("tim duong di findroad 2 " + bb);
        GameScr.mainChar.posTransRoad = this.updateFindRoad(GameScr.mainChar.x / 16, GameScr.mainChar.y / 16, aa, bb);
        GameScr.mainChar.countRoad = 0;
        timeRemovePos = 100;
    }

    private int setDis(int x1, int y1, int x2, int y2) {
        return Util.abs(x1 - x2) + Util.abs(y1 - y2);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private short[] updateFindRoad(int xF, int yF, int xTo, int yTo) {
        if (xTo == 0)
            return null;
        if (yTo == 0) {
            return null;
        }
        try {
            if (Util.distance(xF * 16, yF * 16, xTo * 16, yTo * 16) <= 16) {
                return null;
            }
            if (!mainChar.canFire()) {
                mSystem.println("khong the tim duong ne " + mainChar.canFire());
                return null;
            }
            xStart = (byte) cmxMini;
            yStart = (byte) cmyMini;
            xF = (short) (xF - xStart);
            yF = (short) (yF - yStart);
            xTo = (short) (xTo - xStart);
            yTo = (short) (yTo - yStart);
            int s = mapFind.length;
            int i = 0;
            while (i < s) {
                int j = 0;
                while (j < mapFind[i].length) {
                    int aa = (yStart + j) * Tilemap.w + (xStart + i);
                    if (aa < Tilemap.type.length - 1) {
                        GameScr.mapFind[i][j] = (byte) (Tilemap.type[aa] == 2 ? -1 : 0);
                    }
                    ++j;
                }
                ++i;
            }
            int index = 0;
            short xFrom = (short) xF;
            short yFrom = (short) yF;
            short xGoc = xFrom;
            short yGoc = yFrom;
            if (xFrom < 0)
                return null;
            if (xFrom >= mapFind.length) {
                return null;
            }
            if (xFrom < mapFind.length && yFrom < mapFind[xFrom].length) {
                GameScr.mapFind[xFrom][yFrom] = 1;
            }
            index = 2;
            short iL = (short) mapFind.length;
            short jL = (short) mapFind[0].length;
            int count = 0;
            while (true) {
                if (++count > 100) {
                    if (iTaskAuto == 2) {
                        this.iTaskAutoLast = iTaskAuto;
                        isFixBugAutoQuest = true;
                        count = 0;
                    }
                    iTaskAuto = 1;
                    Cout.println("Cout DAY ROI COUNT------: " + count);
                    return null;
                }
                short xCur = -1;
                short yCur = -1;
                if (xFrom + 1 < iL && mapFind[xFrom + 1][yFrom] == 0) {
                    GameScr.mapFind[xFrom + 1][yFrom] = (byte) index;
                    xCur = (short) (xFrom + 1);
                    yCur = yFrom;
                }
                if (xFrom - 1 >= 0 && mapFind[xFrom - 1][yFrom] == 0) {
                    GameScr.mapFind[xFrom - 1][yFrom] = (byte) index;
                    if (xCur != -1) {
                        if (this.setDis(xCur, yCur, xTo, yTo) > this.setDis(xFrom - 1, yFrom, xTo, yTo)) {
                            xCur = (short) (xFrom - 1);
                            yCur = yFrom;
                        }
                    } else {
                        xCur = (short) (xFrom - 1);
                        yCur = yFrom;
                    }
                }
                if (yFrom + 1 < jL && mapFind[xFrom][yFrom + 1] == 0) {
                    GameScr.mapFind[xFrom][yFrom + 1] = (byte) index;
                    if (xCur != -1) {
                        if (this.setDis(xCur, yCur, xTo, yTo) > this.setDis(xFrom, yFrom + 1, xTo, yTo)) {
                            xCur = xFrom;
                            yCur = (short) (yFrom + 1);
                        }
                    } else {
                        xCur = xFrom;
                        yCur = (short) (yFrom + 1);
                    }
                }
                if (yFrom - 1 >= 0 && mapFind[xFrom][yFrom - 1] == 0) {
                    GameScr.mapFind[xFrom][yFrom - 1] = (byte) index;
                    if (xCur != -1) {
                        if (this.setDis(xCur, yCur, xTo, yTo) > this.setDis(xFrom, yFrom - 1, xTo, yTo)) {
                            xCur = xFrom;
                            yCur = (short) (yFrom - 1);
                        }
                    } else {
                        xCur = xFrom;
                        yCur = (short) (yFrom - 1);
                    }
                }
                int aa = -1;
                if (xCur != -1) {
                    aa = 0;
                    xFrom = xCur;
                    yFrom = yCur;
                } else {
                    yFrom = 1000;
                    xFrom = 1000;
                }
                short i2 = 0;
                while (i2 < iL) {
                    short j = 0;
                    while (j < jL) {
                        if (mapFind[i2][j] > 1 && this.setContinue(i2, j, mapFind) && mapFind[i2][j]
                                + this.setDis(i2, j, xTo, yTo) < index + this.setDis(xFrom, yFrom, xTo, yTo)) {
                            xFrom = i2;
                            yFrom = j;
                            index = mapFind[i2][j];
                            aa = 0;
                        }
                        j = (short) (j + 1);
                    }
                    i2 = (short) (i2 + 1);
                }
                if (xFrom == xTo && yFrom == yTo) {
                    if (index >= 127) {
                        return null;
                    }
                    break;
                }
                if (aa != 0) {
                    Cout.println("TOI DAY ROI------: " + xCur);
                    return null;
                }
                index = (short) (index + 1);
            }
            int ia = 0;
            short[] success = new short[index];
            while (true) {
                success[ia] = (short) ((xFrom << 8) + yFrom);
                if (xFrom + 1 < iL && mapFind[xFrom + 1][yFrom] == mapFind[xFrom][yFrom] - 1) {
                    xFrom = (short) (xFrom + 1);
                } else if (xFrom - 1 >= 0 && mapFind[xFrom - 1][yFrom] == mapFind[xFrom][yFrom] - 1) {
                    xFrom = (short) (xFrom - 1);
                } else if (yFrom + 1 < jL && mapFind[xFrom][yFrom + 1] == mapFind[xFrom][yFrom] - 1) {
                    yFrom = (short) (yFrom + 1);
                } else if (yFrom - 1 >= 0 && mapFind[xFrom][yFrom - 1] == mapFind[xFrom][yFrom] - 1) {
                    yFrom = (short) (yFrom - 1);
                }
                if (xFrom == xGoc && yFrom == yGoc) {
                    return success;
                }
                ++ia;
            }
        } catch (Exception e) {
            return null;
        }
    }

    private boolean setContinue(int i, int j, byte[][] mapFind) {
        if (i + 1 < mapFind.length && mapFind[i + 1][j] == 0) {
            return true;
        }
        if (i - 1 >= 0 && mapFind[i - 1][j] == 0) {
            return true;
        }
        if (j + 1 < mapFind[i].length && mapFind[i][j + 1] == 0) {
            return true;
        }
        return j - 1 >= 0 && mapFind[i][j - 1] == 0;
    }

    public int getIndexClassFromItemID(int itemID) {
        if (itemID >= 79 && itemID <= 113) {
            return (itemID - 79) / 7;
        }
        if (itemID >= 174 && itemID <= 213) {
            return (itemID - 174) / 8;
        }
        if (itemID >= 214 && itemID <= 263) {
            return (itemID - 214) / 10;
        }
        return 0;
    }

    public void onMSGServer(String readUTF) {
        if (this.listMSGServer == null) {
            this.listMSGServer = new mVector();
        }
        this.listMSGServer.addElement(readUTF);
    }

    public void onSkillClan(SkillClan[] skillclan, SkillClan[] skillClanPrivate) {
        this.skillClan = skillclan;
        this.skillClanPrivate = skillClanPrivate;
    }

    public static int isNpcQuest(int idNpc) {
        return -1;
    }

    public void getAllPosNPCMInimap(Actor npc) {
    }

    public static mVector getAllQuestNpc(int idNpc) {
        mVector allquest = new mVector();
        return allquest;
    }

    public void setPosNPC(int id, int indexColor, int x, int y) {
        int i = 0;
        while (i < PosNPCQuest.size()) {
            Position p = (Position) PosNPCQuest.elementAt(i);
            if (p != null && p.id == id) {
                return;
            }
            ++i;
        }
        Position pp = new Position();
        pp.id = id;
        pp.indexColor = (short) indexColor;
        pp.x = x;
        pp.y = y;
        PosNPCQuest.addElement(pp);
    }

    public void onFruit(Message msg) {
    }

    public static void removeAllchat() {
        VecInfoServer.removeAllElements();
        MsgChat.vecChatTab.removeAllElements();
    }

    @Override
    public void perform(int idAction, Object p) {
        mSystem.println("index perfom gamescr " + idAction);
        switch (idAction) {
            case 2704:
                try {
                    String str = GameCanvas.inputDlg.tfInput.getText();
                    GameCanvas.endDlg();
                    if (str.equals("") || str.isEmpty())
                        return;
                    LatHinh.lap = Integer.parseInt(str);
                    GameCanvas.endDlg();
                    GameCanvas.inputDlg.setInfo("Nhập tốc độ lật hình", new mCommand("Ok", this, 2705), 1, 10, true);
                    GameCanvas.inputDlg.show();
                    GameCanvas.gameScr.hideGUI = 0;
                } catch (Exception e) {

                }
                break;
            case 2705:
                try {
                    String str = GameCanvas.inputDlg.tfInput.getText();
                    GameCanvas.endDlg();
                    if (str.equals("") || str.isEmpty())
                        return;
                    LatHinh.time = Integer.parseInt(str);
                    (new Thread(new LatHinh(LatHinh.lap))).start();
                } catch (Exception e) {

                }
                break;
            case 0: {
                CharSelectScr.gI().switchToMe();
                Bossintro = null;
                isIntro = false;
                MainChar.blockkey = false;
                EffectManager.lowEffects.removeAllElements();
                EffectManager.hiEffects.removeAllElements();
                vecCharintro.removeAllElements();
                GameData.listbyteData.clear();
                GameData.listImgIcon.clear();
                this.actors.removeAllElements();
                break;
            }
            case 1: {
                if (Tilemap.isMapIntro()) {
                    mVector list = new mVector();
                    list.addElement(this.cmdLogin);
                    if (!GameCanvas.isTouch) {
                        list.addElement(this.cmdskip);
                    }
                    GameCanvas.menu.startAt(list, 0);
                    break;
                }
                if (!mainChar.isNPC()) {
                    MainMenu.captionServer = "";
                    MainMenu.infoBuySellServer = "";
                }
                MainMenu.isInven = true;
                MainMenu.gI().switchToMe(this);
                MainMenu.gI().isRestartAutoFight = this.isStartAutoAttack;
                this.isStartAutoAttack = false;
                MainMenu.gI().PutItemSHop(shop);
                MainMenu.gI().idNPC_Shop = idNPCshopInven;
                MainMenu.gI().catNPC_Shop = catNPCshopInven;
                this.changeToNextFocusActor(false);
                break;
            }
            case 2: {
                this.doFire();
                break;
            }
            case 3: {
                this.chatMode = true;
                this.tfChat.doChangeToTextBox();
                if (!mSystem.isPC)
                    break;
                this.tfChat.setFocus(true);
                break;
            }
            case 4: {
                GameCanvas.endDlg();
                break;
            }
            case 5: {
                if (this.focusedActor != null) {
                    if (this.focusedActor.getStateQuest() == 1 || this.focusedActor.getStateQuest() == 2
                            || this.focusedActor.getStateQuest() == 0) {
                        mVector menu = new mVector();
                        SetInfoData s5 = new SetInfoData();
                        s5.ID = this.focusedActor.getIDNpc();
                        s5.idIcon = this.focusedActor.getIDicon();
                        s5.stateQuest = this.focusedActor.getStateQuest();
                        mCommand cmd1 = new mCommand(T.trochuyen, this, 10);
                        mCommand cmd2 = new mCommand(T.nhiemvu, (IActionListener) this, 11, (Object) s5);
                        menu.addElement(cmd1);
                        menu.addElement(cmd2);
                        GameCanvas.menu.startAt(menu, 2);
                    } else {
                        GameService.gI().requestNPCInfo(this.focusedActor.getIDNpc());
                    }
                }
                GameCanvas.endDlg();
                break;
            }
            case 6: {
                break;
            }
            case 7: {
                this.isAutoChangeFocus = false;
                boolean findAll = false;
                if (Ghost != null) {
                    return;
                }
                if (!mainChar.isDie() && GameCanvas.menuSelectitem.isAutoDanh[0] && !Tilemap.ismapLang
                        && this.beginAuto) {
                    Actor ac = this.findFocusActorTouch();
                    if (ac != null && ac.canFocusMonster() && GameCanvas.currentDialog == null && ac.canFocus()) {
                        this.focusedActor = ac;
                    }
                    return;
                }
                this.changeToNextFocusActor(false);
                break;
            }
            case 9: {
                break;
            }
            case 10: {
                GameService.gI().requestNPCInfo(this.focusedActor.getIDNpc());
                GameCanvas.endDlg();
                break;
            }
            case 11: {
                mCommand cmd;
                SetInfoData s12;
                Quest q;
                mVector menu;
                SetInfoData s11 = (SetInfoData) p;
                if (s11 == null)
                    break;
                if (s11.stateQuest == 0 && allQuestCanReceive.size() > 0) {
                    menu = new mVector();
                    int i = 0;
                    while (i < allQuestCanReceive.size()) {
                        q = (Quest) allQuestCanReceive.elementAt(i);
                        if (q != null && q.idNpcReceive == this.focusedActor.getIDNpc()) {
                            s12 = new SetInfoData();
                            s12.stateQuest = s11.stateQuest;
                            s12.idIcon = s11.idIcon;
                            s12.ID = this.focusedActor.getIDNpc();
                            s12.idQuest = i;
                            cmd = new mCommand(String.valueOf(T.nhiemvu) + " " + q.name, (IActionListener) this, 12,
                                    (Object) s12);
                            menu.addElement(cmd);
                        }
                        ++i;
                    }
                    if (menu.size() == 1) {
                        mCommand cmd1 = (mCommand) menu.elementAt(0);
                        if (cmd1 != null) {
                            cmd1.performAction();
                        }
                    } else {
                        GameCanvas.menu.startAt_MenuOption(menu, -1, -1, s11.str, s11.idIcon);
                    }
                }
                if (s11.stateQuest == 2 && allQuestWorking.size() > 0) {
                    menu = new mVector();
                    int i = 0;
                    while (i < allQuestWorking.size()) {
                        q = (Quest) allQuestWorking.elementAt(i);
                        if (q != null && q.idNpcResponse == this.focusedActor.getIDNpc()) {
                            s12 = new SetInfoData();
                            s12.stateQuest = s11.stateQuest;
                            s12.idIcon = s11.idIcon;
                            s12.idQuest = i;
                            s12.ID = this.focusedActor.getIDNpc();
                            cmd = new mCommand(String.valueOf(T.nhiemvu) + " " + q.name, (IActionListener) this, 12,
                                    (Object) s12);
                            menu.addElement(cmd);
                        }
                        ++i;
                    }
                    if (menu.size() == 1) {
                        mCommand cmd1 = (mCommand) menu.elementAt(0);
                        if (cmd1 != null) {
                            cmd1.performAction();
                        }
                    } else {
                        GameCanvas.menu.startAt_MenuOption(menu, -1, -1, s11.str, s11.idIcon);
                    }
                }
                if (s11.stateQuest != 1 || allQuestFinish.size() <= 0)
                    break;
                menu = new mVector();
                int i = 0;
                while (i < allQuestFinish.size()) {
                    q = (Quest) allQuestFinish.elementAt(i);
                    if (q != null && q.idNpcResponse == this.focusedActor.getIDNpc()) {
                        s12 = new SetInfoData();
                        s12.stateQuest = s11.stateQuest;
                        s12.idIcon = s11.idIcon;
                        s12.idQuest = i;
                        s12.ID = this.focusedActor.getIDNpc();
                        cmd = new mCommand(String.valueOf(T.nhiemvu) + " " + q.name, (IActionListener) this, 12,
                                (Object) s12);
                        menu.addElement(cmd);
                    }
                    ++i;
                }
                if (menu.size() == 1) {
                    mCommand cmd1 = (mCommand) menu.elementAt(0);
                    if (cmd1 == null)
                        break;
                    cmd1.performAction();
                    break;
                }
                GameCanvas.menu.startAt_MenuOption(menu, -1, -1, s11.str, s11.idIcon);
                break;
            }
            case 12: {
                Quest q12;
                SetInfoData s112 = (SetInfoData) p;
                questContent = null;
                CountQuestConten = 0;
                if (s112.stateQuest == 0) {
                    Quest q122 = (Quest) allQuestCanReceive.elementAt(s112.idQuest);
                    if (q122 != null && q122.idNpcReceive == this.focusedActor.getIDNpc()) {
                        questContent = new String[q122.content.size()];
                        s112.idQuest = q122.idQuest;
                        s112.str = q122.name;
                        s112.mainSub = q122.main_sub;
                        s112.type = q122.type;
                        int i = 0;
                        while (i < questContent.length) {
                            String str = (String) q122.content.elementAt(i);
                            if (str != null) {
                                GameScr.questContent[i] = str;
                            }
                            ++i;
                        }
                    }
                } else if (s112.stateQuest == 2) {
                    Quest q123 = (Quest) allQuestWorking.elementAt(s112.idQuest);
                    if (q123 != null && q123.idNpcResponse == this.focusedActor.getIDNpc()) {
                        s112.idQuest = q123.idQuest;
                        s112.str = q123.name;
                        s112.decript = q123.decript;
                        s112.mainSub = q123.main_sub;
                        s112.type = q123.type;
                        GameScr.addChat(this.focusedActor, q123.decript, 100);
                    }
                } else if (s112.stateQuest == 1 && (q12 = (Quest) allQuestFinish.elementAt(s112.idQuest)) != null
                        && q12.idNpcResponse == this.focusedActor.getIDNpc()) {
                    questContent = new String[q12.rescontent.size()];
                    s112.idQuest = q12.idQuest;
                    s112.str = q12.name;
                    s112.decript = q12.decript;
                    s112.mainSub = q12.main_sub;
                    s112.type = q12.type;
                    int i = 0;
                    while (i < questContent.length) {
                        String str = (String) q12.rescontent.elementAt(i);
                        if (str != null) {
                            GameScr.questContent[i] = str;
                        }
                        ++i;
                    }
                }
                if (questContent == null)
                    break;
                GameCanvas.StartNextDiaLog(questContent[CountQuestConten],
                        new mCommand(T.next, (IActionListener) this, 13, (Object) s112), this.focusedActor.getIDicon());
                break;
            }
            case 13: {
                SetInfoData s13 = (SetInfoData) p;
                if (s13.stateQuest == 0) {
                    if ((CountQuestConten = (byte) (CountQuestConten + 1)) > questContent.length - 1) {
                        if (s13.type == 3) {
                            GameService.gI().receiveQuestNew(1, s13.idQuest, s13.mainSub);
                            GameCanvas.endDlg();
                        } else {
                            GameService.gI().receiveQuestNew(0, s13.idQuest, s13.mainSub);
                            GameCanvas.endDlg();
                        }
                    } else {
                        GameCanvas.StartNextDiaLog(questContent[CountQuestConten],
                                new mCommand(T.next, (IActionListener) this, 13, (Object) s13),
                                this.focusedActor.getIDicon());
                    }
                }
                if (s13.stateQuest == 1) {
                    if ((CountQuestConten = (byte) (CountQuestConten + 1)) > questContent.length - 1) {
                        isSendFinishQuest = true;
                        GameCanvas.endDlg();
                        GameService.gI().receiveQuestNew(1, s13.idQuest, s13.mainSub);
                    } else {
                        GameCanvas.StartNextDiaLog(questContent[CountQuestConten],
                                new mCommand(T.next, (IActionListener) this, 13, (Object) s13),
                                this.focusedActor.getIDicon());
                    }
                }
                if (s13.stateQuest != 2)
                    break;
                GameCanvas.startOKDlg(s13.decript);
                break;
            }
            case 14: {
                SetInfoData s14 = (SetInfoData) p;
                GameService.gI().receiveQuestNew(0, s14.idQuest, s14.mainSub);
                GameCanvas.endDlg();
                break;
            }
            case 15: {
                if (this.focusedActor == null)
                    break;
                GameCanvas.addChat(this.focusedActor.getName(), "", true);
                break;
            }
            case 16: {
                GameService.gI().Friend((byte) 0, this.focusedActor.getName());
                break;
            }
            case 101:
                GameService.gI().TradeProces(-1, this.focusedActor.getName());
                break;
            case 17: {
                MainEvent ev = EventScreen.setEvent(this.eventShow.nameEvent, (byte) this.eventShow.IDCmd);
                if (ev != null) {
                    GameCanvas.mevent.doEvent(false, ev);
                }
                if (timeEvent > 40) {
                    timeEvent = 40;
                }
                numMSG = 0;
                timeEvent = 0;
                break;
            }
            case 19: {
                if (this.focusedActor == null)
                    break;
                if (this.focusedActor.isNPC()) {
                    this.doFire();
                    break;
                }
                this.gameService.requestSomeOneInfo(this.focusedActor.ID, (byte) 0);
                break;
            }
            case 20: {
                this.chatMode = false;
                this.left = this.cmdmenu;
                this.right = null;
                this.center = null;
                break;
            }
            case 21: {
                this.doChat();
                break;
            }
            case 22: {
                GameService.gI().doCreateParty((byte) 1, this.focusedActor.ID, (short) -1, "");
                break;
            }
            case 23: {
                GameService.gI().dosendThachdau((byte) 0, this.focusedActor.ID);
                break;
            }
            case 24: {
                GameService.gI().Clan((byte) 1, this.focusedActor.ID);
                break;
            }
            case 25: {
                break;
            }
            case 26: {
                break;
            }
            case 72: {
                GameCanvas.endDlg();
                if (GameCanvas.currentScreen == MenuLogin.gI())
                    break;
                Session_ME.gI().close();
                GameCanvas.gameScr.clearloadMap();
                GameScr.removeAllchat();
                MenuLogin.gI().switchToMe();
                break;
            }
            case 73: {
                this.cmdDisconect = null;
                GameCanvas.endDlg();
                Session_ME.gI().close();
                GameCanvas.gameScr.clearloadMap();
                GameScr.removeAllchat();
                MenuLogin.gI().switchToMe();
                this.isDis = false;
                break;
            }
            case 97: {
                break;
            }
            case 98: {
                break;
            }
            case 99: {
                break;
            }
            case 100: {
                break;
            }
            case 106: {
                GameCanvas.endDlg();
                Res.resetImgMonsTemp();
                isIntro = false;
                MainChar.blockkey = false;
                MenuLogin.gI().switchToMe();
                break;
            }
            case 107: {
                GameCanvas.endDlg();
                isIntro = false;
                MainChar.blockkey = false;
                Session_ME.gI().close();
                Res.resetImgMonsTemp();
                MenuLogin.gI().switchToMe();
                break;
            }
            case 108: {
                GameService.gI().doHuybanHang();
                GameCanvas.endDlg();
                break;
            }
            case 110:
                GameService.gI().changeTabGoiNap(0);
                break;
            case 111:
                GameCanvas.menuSelectitem.startAt();
                GameCanvas.menuSelectitem.perform(6, null);
                break;
            case 112:
                // mở UI vòng quay
                VongQuayScr.gI().switchToMe(this);
                break;

        }
    }

    @Override
    public void paint(mGraphics g, int idAction, int x, int y, Object paint) {
        String inFo = null;
        switch (idAction) {
            case 90: {
                inFo = (String) paint;
                GameData.paintIcon(g, Short.parseShort(inFo), x, y);
                break;
            }
        }
    }

    private void doActorMove() {
        if (charcountdonw != null) {
            return;
        }
        if (!mainChar.mcanmove()) {
            return;
        }
        if (GameCanvas.currentDialog != null) {
            return;
        }
        if (this.chatMode) {
            return;
        }
        if (GameCanvas.menu2.isShow) {
            return;
        }
        if (GameScr.mainChar.state == 3) {
            return;
        }
        if (isIntro && GameScr.mainChar.posTransRoad != null) {
            return;
        }
        if (MainChar.blockkey) {
            return;
        }
        boolean changeDirection = false;
        if (GameCanvas.isKeyPressed(2)) {
            changeDirection = true;
            this.dir = 1;
        }
        if (GameCanvas.isKeyPressed(4)) {
            changeDirection = true;
            this.dir = (byte) 2;
        }
        if (GameCanvas.isKeyPressed(6)) {
            changeDirection = true;
            this.dir = (byte) 3;
        }
        if (GameCanvas.isKeyPressed(8)) {
            changeDirection = true;
            this.dir = (byte) 4;
        }
        boolean move = false;
        boolean getKeyMove = false;
        short oldx = GameScr.mainChar.x;
        short oldy = GameScr.mainChar.y;
        short olddir = GameScr.mainChar.dir;
        int xx = 0;
        int yy = 0;
        short v = GameScr.mainChar.speed;
        if (GameCanvas.keyHold[2]) {
            if (mainChar.isNPC()) {
                this.doHuyBanHang();
                return;
            }
            this.beginAuto = false;
            this.removeFocusWhenPutKey();
            autoFight = false;
            yy = -16;
            move = true;
            getKeyMove = true;
            GameScr.mainChar.dir = 1;
            this.moveToX = GameScr.mainChar.x;
            this.moveToY = (short) (GameScr.mainChar.y - v);
            GameScr.mainChar.posTransRoad = null;
            if (this.moveToY <= 0) {
                changeDirection = false;
                this.moveToY = GameScr.mainChar.y;
                return;
            }
            try {
                if (Tilemap.tileTypeAtPixel(this.moveToX, this.moveToY, 2)) {
                    this.moveToY = GameScr.mainChar.y;
                    if (mainChar.setWay(0, -8)) {
                        this.changeSinceLastUpdate = true;
                        GameScr.mainChar.comeHome = false;
                        return;
                    }
                    changeDirection = false;
                    move = false;
                }
            } catch (Exception e) {
                changeDirection = false;
                this.moveToY = GameScr.mainChar.y;
                e.printStackTrace();
            }
        } else if (GameCanvas.keyHold[8]) {
            if (mainChar.isNPC()) {
                this.doHuyBanHang();
                return;
            }
            this.beginAuto = false;
            this.removeFocusWhenPutKey();
            autoFight = false;
            yy = 16;
            move = true;
            getKeyMove = true;
            GameScr.mainChar.dir = 0;
            this.moveToX = GameScr.mainChar.x;
            GameScr.mainChar.posTransRoad = null;
            this.moveToY = (short) (GameScr.mainChar.y + v);
            if (this.moveToY > Tilemap.h * 16) {
                changeDirection = false;
                this.moveToY = GameScr.mainChar.y;
                return;
            }
            try {
                if (Tilemap.tileTypeAtPixel(this.moveToX, this.moveToY, 2)) {
                    this.moveToY = GameScr.mainChar.y;
                    if (mainChar.setWay(0, 8)) {
                        this.changeSinceLastUpdate = true;
                        GameScr.mainChar.comeHome = false;
                        return;
                    }
                    changeDirection = false;
                    move = false;
                }
            } catch (Exception e) {
                changeDirection = false;
                this.moveToY = GameScr.mainChar.y;
                e.printStackTrace();
            }
        } else if (GameCanvas.keyHold[4]) {
            if (mainChar.isNPC()) {
                this.doHuyBanHang();
                return;
            }
            this.beginAuto = false;
            this.removeFocusWhenPutKey();
            autoFight = false;
            xx = -16;
            move = true;
            getKeyMove = true;
            GameScr.mainChar.dir = (short) 2;
            this.moveToX = (short) (GameScr.mainChar.x - v);
            this.moveToY = GameScr.mainChar.y;
            GameScr.mainChar.posTransRoad = null;
            if (this.moveToX <= 0) {
                changeDirection = false;
                this.moveToX = GameScr.mainChar.x;
                return;
            }
            try {
                if (Tilemap.tileTypeAtPixel(this.moveToX, this.moveToY, 2)) {
                    this.moveToX = GameScr.mainChar.x;
                    if (mainChar.setWay(-8, 0)) {
                        this.changeSinceLastUpdate = true;
                        GameScr.mainChar.comeHome = false;
                        return;
                    }
                    changeDirection = false;
                    move = false;
                }
            } catch (Exception e) {
                changeDirection = false;
                this.moveToX = GameScr.mainChar.x;
                e.printStackTrace();
            }
        } else if (GameCanvas.keyHold[6]) {
            if (mainChar.isNPC()) {
                this.doHuyBanHang();
                return;
            }
            this.beginAuto = false;
            this.removeFocusWhenPutKey();
            autoFight = false;
            xx = 16;
            move = true;
            getKeyMove = true;
            GameScr.mainChar.dir = (short) 3;
            this.moveToY = GameScr.mainChar.y;
            GameScr.mainChar.posTransRoad = null;
            this.moveToX = (short) (GameScr.mainChar.x + v);
            if (this.moveToX >= Tilemap.w * 16) {
                changeDirection = false;
                this.moveToX = GameScr.mainChar.x;
                return;
            }
            try {
                if (Tilemap.tileTypeAtPixel(this.moveToX, this.moveToY, 2)) {
                    this.moveToX = GameScr.mainChar.x;
                    if (mainChar.setWay(8, 0)) {
                        this.changeSinceLastUpdate = true;
                        GameScr.mainChar.comeHome = false;
                        return;
                    }
                    changeDirection = false;
                    move = false;
                }
            } catch (Exception e) {
                changeDirection = false;
                this.moveToX = GameScr.mainChar.x;
                e.printStackTrace();
            }
        }
        if (GameScr.mainChar.beStune) {
            return;
        }
        if (getKeyMove && this.checkCanChangeMap(oldx + xx, oldy + yy, olddir)) {
            return;
        }
        if (move) {
            GameScr.mainChar.sendMove = false;
            if (this.moveToX < 0) {
                this.moveToX = GameScr.mainChar.x;
            }
            if (this.moveToY < 0) {
                this.moveToY = GameScr.mainChar.y;
            }
            if (this.moveToX > Tilemap.w * 16) {
                this.moveToX = GameScr.mainChar.x;
            }
            if (this.moveToY > Tilemap.h * 16) {
                this.moveToX = GameScr.mainChar.y;
            }
            mainChar.moveTo(this.moveToX, this.moveToY);
            if (saveAutoFight && typeOptionFocus == 1) {
                GameScr.mainChar.lastXAuto = this.moveToX;
                GameScr.mainChar.lastYAuto = this.moveToY;
            }
            this.changeSinceLastUpdate = true;
            GameScr.mainChar.comeHome = false;
            this.countStep = (byte) (this.countStep + 1);
        }
    }

    public void findAllmonster() {
        int i = 0;
        while (i < this.actors.size()) {
            Actor mst = (Actor) this.actors.elementAt(i);
            if (mst.catagory == 1) {
                this.vMonster.addElement(mst);
            }
            ++i;
        }
    }

    public static Actor CreateActor(byte catagory, short type, short id, short x, short y, byte typePK) {
        Actor ac = null;
        if (catagory == 0) {
            ac = new Char();
            ac.setTypePK(typePK);
            GameService.gI().requestCharInfo(id);
            if (type == -1) {
                ac.setDir((byte) Res.random(3));
            }
        } else if (catagory == 1) {
            String key = String.valueOf(type);
            Object _idBoss = ID_BOSS.get(key);
            if (_idBoss != null) {
                if (type == 91) {
                    ac = new BossTruongDoTe(type);
                } else if (type == 101) {
                    ac = new BossBienBucCongTu(type);
                }
                if (type == 74) {
                    ac = new Boss_6x(type);
                }
                ac = type == 139 ? new Boss_Nguoi_Tuyet(type) : new BossGame(type);
            } else {
                ac = new Monster(type);
            }
            GameService.gI().requestMonsterInfo(id);
            ac.setTypePK(typePK);
        } else if (catagory > 2) {
            ac = new Item();
            ac.setTimelive(mSystem.currentTimeMillis() + (long) (timeremoveItem * 1000));
            ((Item) ac).idIcon = type;
            ac.catagory = catagory;
            GameService.gI().requestItemInfo(id);
        }
        if (ac != null) {
            ac.catagory = catagory;
            ac.ID = id;
            ac.x = x;
            ac.y = y;
        }
        return ac;
    }

    public void onMonsterTemplateOffline() {
        try {
            int nmonster = 78;
            int i = 0;
            while (i < nmonster) {
                String name = "Quai 1";
                short idtemplate = (short) i;
                int typemove = 0;
                MonsterTemplate monsterTemplate = new MonsterTemplate(idtemplate, name, typemove, i);
                MonsterTemplate.ALL_MONSTER_TEMPLATE.put("" + idtemplate, monsterTemplate);
                ++i;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createMonsterOffline() {
        Monster mons;
        this.actors.removeAllElements();
        this.onMonsterTemplateOffline();
        int i = 0;
        while (i < postLoginX.length) {
            mons = new Monster(1);
            mons.catagory = 1;
            mons.ID = (short) i;
            mons.x = (short) postLoginX[i];
            mons.y = (short) postLoginY[i];
            mons.xFirst = (short) postLoginX[i];
            mons.yFirst = (short) postLoginY[i];
            mons.saveXfirst = (short) postLoginX[i];
            mons.saveYfirst = (short) postLoginY[i];
            mons.hp = 2500;
            mons.level = (short) 10;
            mons.he = 0;
            mons.maxhp = 2500;
            mons.timeLive = 5000;
            mons.height = (short) 30;
            mons.name = "Mons " + i;
            mons.speed = (short) Res.random(3, 5);
            mons.setCanrevives(false);
            GameCanvas.gameScr.actors.addElement(mons);
            ++i;
        }
        i = 0;
        while (i < 5) {
            mons = new Monster(1);
            mons.catagory = 1;
            mons.ID = (short) i;
            mons.x = (short) (this.xLogin[i] + 688);
            mons.y = (short) (this.yLogin[i] + 96);
            mons.xFirst = (short) (this.xLogin[i] + 672);
            mons.yFirst = (short) (this.yLogin[i] + 90);
            mons.saveXfirst = (short) (this.xLogin[i] + 688);
            mons.saveYfirst = (short) (this.yLogin[i] + 96);
            mons.hp = 2500;
            mons.level = (short) 10;
            mons.he = 0;
            mons.maxhp = 2500;
            mons.timeLive = 5000;
            mons.height = (short) 30;
            mons.name = "Mons " + i;
            mons.speed = (short) Res.random(3, 5);
            mons.setCanrevives(false);
            GameCanvas.gameScr.actors.addElement(mons);
            ++i;
        }
    }

    public void onMapOffline(int idMapload, int xc, int yc) {
        isIntro = true;
        CountIntro = 0;
        fireIntro = false;
        CountMoveFirst = 0;
        GameCanvas.instance.loadSize();
        this.onMainCharInfoOffline();
        this.onCharWearing();
        this.actors.removeAllElements();
        vecCharintro.removeAllElements();
        nameMap = "";
        this.focusedActor = null;
        GameScr.mainChar.x = 0;
        GameScr.mainChar.y = (short) 217;
        GameScr.mainChar.xTo = GameScr.mainChar.x;
        GameScr.mainChar.yTo = GameScr.mainChar.y;
        MainChar.blockkey = true;
        canmove = false;
        allLocation = null;
        nameMap = "Demo";
        GameScr.mainChar.clazz = (byte) 3;
        mainChar.setInfoWearing(Char.idPartTest[GameScr.mainChar.clazz][0]);
        GameData.removeAllImgTree();
        Tilemap.loadMap(idMapload, null);
        this.actors.addElement(mainChar);
        this.onMonsterTemplateOffline();
        int i = 0;
        while (i < 5) {
            Monster mons = new Monster(54);
            mons.catagory = 1;
            mons.ID = (short) i;
            int xss = 0;
            mons.x = (short) (this.xit_mons[i] - 100 - xss);
            mons.y = (short) (this.yit[i] - 35);
            mons.xFirst = (short) (this.xit_mons[i] - 100 - xss);
            mons.yFirst = (short) (this.yit[i] - 35);
            mons.saveXfirst = (short) (this.xit_mons[i] - 100 - xss);
            mons.saveYfirst = (short) (this.yit[i] - 35 - xss);
            mons.hp = 2500;
            mons.level = (short) 10;
            mons.he = 0;
            mons.maxhp = 2500;
            mons.timeLive = 5000;
            mons.height = (short) 30;
            mons.name = "Mons " + i;
            mons.speed = (short) Res.random(3, 5);
            mons.setCanrevives(false);
            GameCanvas.gameScr.actors.addElement(mons);
            ++i;
        }
        GameCanvas.instance.loadSize();
        GameCanvas.gameScr.loadCamera();
        GameCanvas.gameScr.switchToMe();
    }

    public static Monster createBossOffline() {
        Monster mons = new Monster(72);
        mons.catagory = 1;
        mons.ID = 0;
        mons.x = (short) 950;
        mons.y = (short) 248;
        mons.xFirst = (short) 950;
        mons.yFirst = (short) 275;
        mons.saveXfirst = (short) 700;
        mons.saveYfirst = (short) 275;
        mons.hp = 500000;
        mons.level = (short) 10;
        mons.he = 0;
        mons.maxhp = 500000;
        mons.timeLive = 500000;
        mons.height = (short) 30;
        mons.name = T.namehungcanh;
        mons.isbossOffline = true;
        return mons;
    }

    public void createCharIntro() {
        MainChar chartest = null;
        int i = 0;
        while (i < 5) {
            chartest = new MainChar();
            chartest.ID = (short) i;
            chartest.name = nameClazz[i];
            chartest.hp = 100;
            chartest.maxhp = 100;
            chartest.mp = 100;
            chartest.maxmp = 100;
            chartest.x = 0;
            chartest.y = (short) (150 + yyy[i]);
            chartest.clazz = idCharClazz[i];
            chartest.moveTo((short) (260 + xxx[i]), chartest.y);
            chartest.setInfoWearing(Char.idPartTest[chartest.clazz][0]);
            this.actors.addElement(chartest);
            vecCharintro.addElement(chartest);
            chartest = null;
            i = (short) (i + 1);
        }
        mainChar.moveTo((short) 288, (short) 217);
    }

    public void createCharLogin() {
        this.actors.removeAllElements();
        vecCharintro.removeAllElements();
        this.charlogin.removeAllElements();
        MainChar chartest = null;
        int i = 0;
        while (i < 5) {
            chartest = new MainChar();
            chartest.ID = (short) i;
            chartest.name = nameClazz[i];
            chartest.hp = 100;
            chartest.maxhp = 100;
            chartest.mp = 100;
            chartest.maxmp = 100;
            chartest.x = (short) postCharLoginX[i];
            chartest.y = (short) postCharLoginY[i];
            chartest.clazz = idCharClazz[i];
            chartest.speed = (short) 7;
            chartest.setInfoWearing(Char.idPartTest[chartest.clazz][0]);
            this.actors.addElement(chartest);
            vecCharintro.addElement(chartest);
            chartest = null;
            i = (short) (i + 1);
        }
    }

    public void doAttackChartest() {
        Actor ac;
        boolean haveMonster = false;
        MainChar.blockkey = false;
        int i = 0;
        while (i < this.actors.size()) {
            Actor mac = (Actor) this.actors.elementAt(i);
            if (mac != null && mac.catagory == 1 && mac.getState() != 5 && mac.getState() != 8) {
                haveMonster = true;
                break;
            }
            ++i;
        }
        if (!haveMonster) {
            this.tickwait = 15;
            ++steep;
            fireIntro = false;
        }
        if (tam.size() <= 0) {
            i = 0;
            while (i < vecCharintro.size()) {
                ac = (Actor) vecCharintro.elementAt(i);
                tam.addElement(ac);
                ac.startAttack(this.getTarget(GameScr.getidSkill(ac.getClazz())), GameScr.getidSkill(ac.getClazz()));
                ++i;
            }
        }
        if (this.wait >= 0) {
            --this.wait;
            return;
        }
        if (tam.size() > 0) {
            i = 0;
            while (i < tam.size()) {
                ac = (Actor) tam.elementAt(i);
                if (ac.getState() == 0 && ac != null && ac.getCoolDown() <= 0L && ac.getState() != 3) {
                    int j;
                    mVector tg = this.getTarget(GameScr.getidSkill(ac.getClazz()), ac);
                    Actor mons = null;
                    if (tg.size() > 1) {
                        j = 0;
                        while (j < tg.size()) {
                            int min = 1000000;
                            Actor mons1 = (Actor) tg.elementAt(j);
                            if (mons1 != null && Res.inRangeActor(ac, mons1, min)) {
                                min = Res.getRange(ac, mons1);
                                mons = mons1;
                            }
                            ++j;
                        }
                    } else {
                        mons = (Actor) tg.elementAt(0);
                    }
                    if (mons != null) {
                        if (Util.distance(ac.x, ac.y, mons.x, mons.y) > 90) {
                            if (Bossintro == null) {
                                this.wait = 10;
                            }
                            ac.moveTo(mons.x, mons.y);
                            return;
                        }
                        if (Bossintro != null) {
                            if (r.random(0, 10) < 5) {
                                ac.setDir((byte) Util.findDirection(ac, mons));
                                ac.startAttack(tg, GameScr.getidSkill(ac.getClazz()));
                                ac.setCoolDown(2);
                                j = 0;
                                while (j < tg.size()) {
                                    Actor mac = (Actor) tg.elementAt(j);
                                    if (mac != null) {
                                        mac.DropHP(100);
                                    }
                                    ++j;
                                }
                            } else {
                                if (Bossintro == null) {
                                    ac.moveTo((short) (mons.x + r.random(20, 25) * (r.random(0, 2) == 0 ? 1 : -1)),
                                            (short) (mons.y + r.random(10, 30) * (r.random(0, 2) == 0 ? 1 : -1)));
                                } else {
                                    ac.moveTo(
                                            (short) (mons.x + this.possIntroX[Res.random(this.possIntroX.length - 1)]),
                                            (short) (mons.y + this.possIntroY[Res.random(this.possIntroY.length - 1)]));
                                }
                                ac.setCoolDown(1);
                            }
                        } else {
                            ac.setDir((byte) Util.findDirection(ac, mons));
                            ac.startAttack(tg, GameScr.getidSkill(ac.getClazz()));
                            ac.setCoolDown(r.random(0, 6));
                            j = 0;
                            while (j < tg.size()) {
                                Actor mac = (Actor) tg.elementAt(j);
                                if (mac != null) {
                                    mac.DropHP(5);
                                }
                                ++j;
                            }
                        }
                    }
                }
                ++i;
            }
        }
    }

    public void doAttackLogin() {
        if (GameCanvas.gameTick % 50 == 0) {
            Actor ac = null;
            if (this.charlogin.size() == 0) {
                int i = 0;
                while (i < this.actors.size()) {
                    ac = (Actor) this.actors.elementAt(i);
                    if (ac != null && ac.catagory == 0) {
                        this.charlogin.addElement(ac);
                    }
                    ++i;
                }
            }
            int j = 0;
            while (j < this.charlogin.size()) {
                ac = (Actor) this.charlogin.elementAt(j);
                if (ac != null) {
                    mVector mtg = new mVector();
                    int i = 0;
                    while (i < this.actors.size()) {
                        Actor mons = (Actor) this.actors.elementAt(i);
                        if (mons != null && mons.catagory == 1 && Util.distance(ac.x, ac.y, mons.x, mons.y) <= 120) {
                            mtg.addElement(mons);
                            break;
                        }
                        ++i;
                    }
                    if (mtg.size() > 0) {
                        ac.startAttack(mtg, GameScr.getidSkill(ac.getClazz()));
                    }
                }
                ++j;
            }
        }
    }

    public mVector getTarget(int id) {
        mVector mons = new mVector();
        int i = 0;
        while (i < this.actors.size()) {
            Actor mac = (Actor) this.actors.elementAt(i);
            if (mac != null && mac.getHp() > 0 && mac.catagory == 1 && mac.getState() != 5 && mac.getState() != 8
                    && mac.getState() != 1) {
                mons.addElement(mac);
                if (id == 0) {
                    return mons;
                }
            }
            ++i;
        }
        return mons;
    }

    public mVector getTarget(int id, Actor from) {
        mVector mons = new mVector();
        if (Bossintro != null) {
            mons.addElement(Bossintro);
            return mons;
        }
        if (id == 0) {
            Actor mons_ad = null;
            int i = 0;
            while (i < this.actors.size()) {
                int min;
                Actor mac = (Actor) this.actors.elementAt(i);
                if (mac != null && mac.getHp() > 0 && mac.catagory == 1 && mac.getState() != 5 && mac.getState() != 8
                        && mac.getState() != 1 && Res.inRangeActor(from, mac, min = 1000000)) {
                    min = Res.getRange(from, mac);
                    mons_ad = mac;
                }
                ++i;
            }
            if (mons_ad != null) {
                mons.addElement(mons_ad);
                return mons;
            }
        } else {
            int i = 0;
            while (i < this.actors.size()) {
                Actor mac = (Actor) this.actors.elementAt(i);
                if (mac != null && mac.getHp() > 0 && mac.catagory == 1 && mac.getState() != 5 && mac.getState() != 8
                        && mac.getState() != 1) {
                    mons.addElement(mac);
                }
                ++i;
            }
        }
        return mons;
    }

    public static int getidSkill(int clazz) {
        return arrSkill[clazz][r.nextInt(arrSkill[clazz].length)];
    }

    public void doTalk() {
        switch (SteepCount) {
            case 0: {
                Actor ac;
                if (clazz <= 5 && (ac = (Actor) vecCharintro.elementAt(clazz)) != null) {
                    GameScr.addChat(ac, T.Texintro[ac.getClazz()][Next], 30);
                    if (clazz == 0 || clazz == 1 || clazz == 2 || clazz == 3) {
                        ++Next;
                    }
                    if (clazz == 4) {
                        ++clazz;
                    }
                    timepopup = 30;
                    if (ac.getClazz() == Char.CAI_BANG && Next == 5) {
                        ++clazz;
                        Next = 0;
                    }
                    if (ac.getClazz() == Char.THIEU_LAM && Next == 2) {
                        ++clazz;
                        Next = 0;
                    }
                    if (ac.getClazz() == Char.NGA_MI && Next == 2) {
                        ++clazz;
                        Next = 0;
                    }
                    if (ac.getClazz() == Char.VO_DANG && Next == 2) {
                        ++clazz;
                        Next = 0;
                    }
                }
                if (clazz != 5 || timepopup > 0)
                    break;
                ++clazz;
                timepopup = 3;
                ++SteepCount;
                this.findRoad(666, 168);
                int i = 0;
                while (i < vecCharintro.size()) {
                    Actor ac2 = (Actor) vecCharintro.elementAt(i);
                    if (ac2 != null) {
                        GameScr.addChat(ac2, T.di, 40);
                        finishTalk = true;
                    }
                    ++i;
                }
                break;
            }
            case 1: {
                clazz = 0;
                Next = 0;
                this.moveAndfire();
                canmove = true;
                break;
            }
            case 2: {
                Actor ac = (Actor) vecCharintro.elementAt(clazz);
                if (ac == null)
                    break;
                GameScr.addChat(ac, T.talkwhenfight[ac.getClazz()][0], 20);
                timepopup = 20;
                if (clazz == 4) {
                    GameScr.addChat(Bossintro, T.hungcanh[1], 10);
                }
                if (++clazz != 5)
                    break;
                GameScr.addChat(Bossintro, T.hungcanh[3], 10);
            }
        }
    }

    public void moveAndfire() {
        int index;
        Actor ac;
        int j;
        if (!ismovefirst) {
            j = 0;
            while (j < vecCharintro.size()) {
                ac = (Actor) vecCharintro.elementAt(j);
                index = 0;
                index = j % 2 == 0 ? 0 : 1;
                ac.moveTo(ToxFirst[index], ToyFirst[index]);
                ++j;
            }
            ismovefirst = true;
        }
        if (ismovefirst && co <= 0) {
            j = 0;
            while (j < vecCharintro.size()) {
                ac = (Actor) vecCharintro.elementAt(j);
                if (!(ac.x != ToxFirst[0] && ac.x != ToxFirst[1] || ac.y != ToyFirst[0] && ac.y != ToyFirst[1])) {
                    index = 3;
                    if (j % 2 == 0) {
                        index = 2;
                    }
                    ac.moveTo(ToxFirst[index], ToyFirst[index]);
                }
                ++j;
            }
            canfire = true;
        }
        if (canfire) {
            mVector target = this.getTargetFirst();
            int j2 = 0;
            while (j2 < vecCharintro.size()) {
                Actor ac2 = (Actor) vecCharintro.elementAt(j2);
                if (!(ac2.x != ToxFirst[2] && ac2.x != ToxFirst[3] || ac2.y != ToyFirst[2] && ac2.y != ToyFirst[3])) {
                    ac2.startAttack(target, 0, -1);
                    ++co;
                }
                ++j2;
            }
        }
        if (co == 5) {
            fireIntro = true;
        }
    }

    public void onMainCharInfoOffline() {
        try {
            GameScr.mainChar.ID = 0;
            GameScr.mainChar.name = "anhhung";
            GameScr.mainChar.hp = 100;
            GameScr.mainChar.maxhp = 100;
            GameScr.mainChar.mp = 100;
            GameScr.mainChar.maxmp = 100;
            MainChar.loadQuickSlot();
        } catch (Exception exception) {
            // empty catch block
        }
    }

    public void onCharWearing() {
        try {
            short id = 0;
            Actor ac = this.findActor(id, 0);
            if (id == GameScr.mainChar.ID) {
                ac = mainChar;
            }
            if (ac != null) {
                short[] sArray = new short[6];
                sArray[3] = -1;
                sArray[4] = -1;
                sArray[5] = -1;
                short[] listpart = sArray;
                ac.setInfoWearing(listpart);
            }
        } catch (Exception exception) {
            // empty catch block
        }
    }

    public Actor findActor(short id, int cat) {
        int i = 0;
        while (i < this.actors.size()) {
            Actor ac = (Actor) this.actors.elementAt(i);
            if (ac.ID == id && ac.catagory == cat) {
                return ac;
            }
            ++i;
        }
        return null;
    }

    @Override
    public boolean isGameScreen() {
        return true;
    }

    public static void addEffectByDir(int dir, int clazz, Actor mychar) {
        if (dir == 1) {
            if (clazz == Char.NGA_MI || clazz == Char.VO_DANG || clazz == Char.THIEU_LAM) {
                EffectSkill.createHiEfect(mychar.x, mychar.y - 60 - mychar.getYfly(), 76);
            }
            if (clazz == Char.NGU_DOC) {
                EffectSkill.createHiEfect(mychar.x, mychar.y - 70 - mychar.getYfly(), 77);
            }
            if (clazz == Char.CAI_BANG) {
                EffectSkill.createHiEfect(mychar.x, mychar.y - 70 - mychar.getYfly(), 78);
            }
        } else if (dir == 0) {
            if (clazz == Char.NGA_MI || clazz == Char.VO_DANG || clazz == Char.THIEU_LAM) {
                EffectSkill.createHiEfectWithTransform(mychar.x, mychar.y - mychar.getYfly(), 76, 3);
            }
            if (clazz == Char.NGU_DOC) {
                EffectSkill.createHiEfectWithTransform(mychar.x, mychar.y - mychar.getYfly(), 77, 3);
            }
            if (clazz == Char.CAI_BANG) {
                EffectSkill.createHiEfectWithTransform(mychar.x, mychar.y - mychar.getYfly(), 78, 3);
            }
        } else if (dir == 2) {
            if (clazz == Char.NGA_MI || clazz == Char.VO_DANG || clazz == Char.THIEU_LAM) {
                EffectSkill.createHiEfectWithTransform(mychar.x - 35, mychar.y - 40 - mychar.getYfly(), 76, 6);
            }
            if (clazz == Char.NGU_DOC) {
                EffectSkill.createHiEfectWithTransform(mychar.x - 35, mychar.y - 30 - mychar.getYfly(), 77, 6);
            }
            if (clazz == Char.CAI_BANG) {
                EffectSkill.createHiEfectWithTransform(mychar.x - 35, mychar.y - 40 - mychar.getYfly(), 78, 6);
            }
        } else if (dir == 3) {
            if (clazz == Char.NGA_MI || clazz == Char.VO_DANG || clazz == Char.THIEU_LAM) {
                EffectSkill.createHiEfectWithTransform(mychar.x + 25, mychar.y - 40 - mychar.getYfly(), 76, 5);
            }
            if (clazz == Char.NGU_DOC) {
                EffectSkill.createHiEfectWithTransform(mychar.x + 35, mychar.y - 30 - mychar.getYfly(), 77, 5);
            }
            if (clazz == Char.CAI_BANG) {
                EffectSkill.createHiEfectWithTransform(mychar.x + 25, mychar.y - 40 - mychar.getYfly(), 78, 5);
            }
        }
    }

    public void findNearTarget() {
        Actor ac1;
        if (isGost) {
            return;
        }
        int min = 100000000;
        int index = -1;
        int i = 0;
        int xChar = mainChar.x;
        int yChar = mainChar.y;
        while (i < this.actors.size()) {
            int dist;
            Actor ac = (Actor) this.actors.elementAt(i);
            if (ac != null && ac.catagory == 1 && ac.getTypeMove() != 5
                    && (dist = Utils.getDistance(mainChar, ac)) < min) {
                min = dist;
                index = i;
            }
            ++i;
        }
        if (index != -1 && !(ac1 = (Actor) this.actors.elementAt(index)).equals(this.focusedActor) && ac1.catagory == 1
                && ac1.canFocusMonster()) {
            this.focusedActor = ac1;
        }
    }


    public void AutoDanh() {
        if (charcountdonw != null) {
            return;
        }
        if (isIntro) {
            return;
        }
        if (isGost) {
            return;
        }
        if (this.focusedActor != null && this.focusedActor.getTypeMove() == 5) {
            this.findNearTarget();
        }
        if (this.focusedActor != null && !this.focusedActor.beFire() && !mainChar.setFireChar(this.focusedActor)) {
            this.findNearTarget();
        }
//        if (this.focusedActor != null && Utils.getDistance(mainChar, this.focusedActor) > 120) {
//            this.findNearTarget();
//        }
        if (this.focusedActor != null && this.focusedActor.getTypeMove() == 5) {
            this.findNearTarget();
        }
        if (this.focusedActor == null) {
            this.findNearTarget();
        }
        if (GameScr.mainChar.posTransRoad != null) {
            return;
        }
        if (coolDown - mSystem.currentTimeMillis() > 0L) {
            return;
        }
        if (this.focusedActor != null && !Res.inRangeActor(mainChar, this.focusedActor, 80)&&this.focusedActor instanceof Monster) {
            mainChar.x = this.focusedActor.x;
            mainChar.y = this.focusedActor.y;
            GameService.gI().moveChar(mainChar.x, mainChar.y);
            return;
        }
        this.countAuto = mainChar.getState() == 0 ? ++this.countAuto : 0;
        if (mainChar.canFire() && mainChar.canUseSkill()) {
            int rangemove;
//            if (this.countAuto > 100) {
//                this.countAuto = 0;
//                this.findRoad(this.xSaveAuto, this.ySaveAuto);
//            }
//            if ((rangemove = Utils.getDistance(GameScr.mainChar.x, GameScr.mainChar.y, this.xSaveAuto,
//                    this.ySaveAuto)) >= 160 && GameScr.mainChar.posTransRoad == null) {
//                this.findRoad(this.xSaveAuto, this.ySaveAuto);
//            }
            if (this.focusedActor != null && this.focusedActor.catagory == 1) {
                int[] key = new int[] { 4, 5, 6, 7, 8, 9 };
                QuickSlot ql = MainChar.mQuickslot[this.indexKeyTouchAuto];
                if (ql.idSkill == -1 || !ql.canfight()) {
                    this.indexKeyTouchAuto = (this.indexKeyTouchAuto + 1) % 5;
                    return;
                }
                if (ql.quickslotType == 1) {
                    int mkeytouch;
                    int n = mkeytouch = GameCanvas.isTouch ? key[this.indexKeyTouchAuto] : this.indexKeyTouchAuto;
                    if (mSystem.isPC) {
                        mkeytouch -= 4;
                    }
                    SkillTemplate skill = (SkillTemplate) vec_skill.elementAt(ql.getSkillType());
                    if (skill.type == SkillTemplate.TYPE_ATTACK
                            && !Res.inRangeActor(mainChar, this.focusedActor, skill.range)) {
                        this.findRoad(this.focusedActor.x, this.focusedActor.y);
                        if (GameScr.mainChar.posTransRoad != null) {
                            return;
                        }
                        if (GameScr.mainChar.posTransRoad == null) {
                            this.findNearTarget();
                        }
                    }
                    this.doTouchQuickSlot(mkeytouch);
                }
            }
        }
    }


    public void AutoNhat() {
        if (mainChar.isFullInven() || !GameCanvas.menuSelectitem.isAutoDanh[6]) {
            return;
        }
        Actor item = getItemDrop();
        if (item != null) {
            mainChar.x = item.x;
            mainChar.y = item.y;
            GameService.gI().moveChar(mainChar.x, mainChar.y);
            GameService.gI().getDropableFromGround(item.ID);
        }
    }

    public void AutoPetEat() {
        try {
            if (!GameCanvas.menuSelectitem.isTabFocus[4]) {
                return;
            }
            Item item = mainChar.equip[11];
            if (item == null || item.name.contains("Cấp 15")) {
                return;
            }
            Item eat = findItemEat();
            if (eat == null) {
                return;
            }
            ItemOption op = item.getOptionById(336);
            if (op == null || op.value >= 10000) {
                return;
            }
            GameService.gI().sellItem((byte) 2, eat.ID);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Item findItemEat() {
        for (int i = 0; i < mainChar.inventory.size(); i++) {
            Item it = (Item) mainChar.inventory.elementAt(i);
            if (it != null && it.plus == 0 && it.colorname == 4 && MainMenu.POS_ITEM_IN_EQUIP[it.type] > -1
                    && MainMenu.POS_ITEM_IN_EQUIP[it.type] < 10) {
                return it;
            }
        }
        return null;
    }

    public Actor getItemDrop() {
        int i = 0;
        while (i < this.actors.size()) {
            Actor ac = (Actor) this.actors.elementAt(i);
            if (ac != null && ac.catagory > 2 && !this.itemPick.contains(ac.ID)) {
                this.itemPick.addElement(ac.ID);
                return ac;
            }
            ++i;
        }
        return null;
    }

    public void AutoBomHP(int maxhp) {
        if (mainChar.getState() == 3) {
            return;
        }
        if (GameScr.mainChar.maxhp <= 0) {
            return;
        }
        if (GameScr.mainChar.hp >= GameScr.mainChar.maxhp) {
            return;
        }
        if (GameScr.mainChar.maxhp > 0 && GameScr.mainChar.hp * 10 / GameScr.mainChar.maxhp < maxhp
                && mSystem.currentTimeMillis() - MainChar.timeStartCoolDow_HP > 2000L) {
            MainChar.timeStartCoolDow_HP = mSystem.currentTimeMillis();
            int i = 0;
            while (i < MainChar.mQuickslot.length) {
                if (MainChar.mQuickslot[i] != null && MainChar.mQuickslot[i].typePotion == Item.TYPE_HP
                        && MainChar.mQuickslot[i].canUsePotion()) {
                    GameService.gI().useItem(MainChar.mQuickslot[i].getidPotion());
                    MainChar.mQuickslot[i].startCoolDown(0);
                    return;
                }
                ++i;
            }
            i = 0;
            while (i < MainChar.MaxInven) {
                Item it;
                if (i < Char.inventory.size() && (it = (Item) Char.inventory.elementAt(i)) != null
                        && it.type == Item.TYPE_HP) {
                    GameService.gI().useItem(it.ID);
                    break;
                }
                ++i;
            }
        }
    }

    public void AutoBomMP(int maxmp) {
        if (mainChar.getState() == 3) {
            return;
        }
        if (GameScr.mainChar.maxmp <= 0) {
            return;
        }
        if (GameScr.mainChar.mp >= GameScr.mainChar.maxmp) {
            return;
        }
        if (GameScr.mainChar.mp * 10 / GameScr.mainChar.maxmp < maxmp
                && mSystem.currentTimeMillis() - MainChar.timeStartCoolDow_MP > 2000L) {
            MainChar.timeStartCoolDow_MP = mSystem.currentTimeMillis();
            int i = 0;
            while (i < MainChar.mQuickslot.length) {
                if (MainChar.mQuickslot[i] != null && MainChar.mQuickslot[i].typePotion == Item.TYPE_MP
                        && MainChar.mQuickslot[i].canUsePotion()) {
                    GameService.gI().useItem(MainChar.mQuickslot[i].getidPotion());
                    MainChar.mQuickslot[i].startCoolDown(0);
                    return;
                }
                ++i;
            }
            i = 0;
            while (i < MainChar.MaxInven) {
                if (i < Char.inventory.size()) {
                    Item it = (Item) Char.inventory.elementAt(i);
                    if (it.type == Item.TYPE_MP) {
                        GameService.gI().useItem(it.ID);
                        break;
                    }
                }
                ++i;
            }
        }
    }

    @Override
    public boolean keyPress(int keyCode) {
        boolean swallow;
        if (this.tfChat.isFocus && this.chatMode && (swallow = this.tfChat.keyPressed(keyCode))) {
            return true;
        }
        if (this.tfChatWorld.isFocus && this.chatWorld && (swallow = this.tfChatWorld.keyPressed(keyCode))) {
            return true;
        }
        return super.keyPress(keyCode);
    }

    public void AutoBuff() {
        if (charcountdonw != null) {
            return;
        }
        if (mainChar.getState() == 3) {
            return;
        }
        long now = mSystem.currentTimeMillis();
        if (GameScr.mainChar.state == 0) {
            int i = 0;
            while (i < GameCanvas.menuSelectitem.idSkillBuff.length) {
                long times = GameCanvas.menuSelectitem.CoolDownSkillBuff[i] - now;
                if (times <= 0L) {
                    int j = 0;
                    while (j < MainChar.mQuickslot.length) {
                        if (MainChar.mQuickslot[j] != null && MainChar.mQuickslot[j].isBuff()
                                && MainChar.mQuickslot[j].idSkill == GameCanvas.menuSelectitem.idSkillBuff[i][0]) {
                            return;
                        }
                        ++j;
                    }
                    if (GameCanvas.menuSelectitem.idSkillBuff[i][0] != -1
                            && GameCanvas.menuSelectitem.CoolDownSkillBuff[i] - now <= 0L) {
                        if (Char.levelSkill[GameCanvas.menuSelectitem.idSkillBuff[i][0]] <= 0)
                            break;
                        SkillTemplate skill = (SkillTemplate) vec_skill
                                .elementAt(GameCanvas.menuSelectitem.idSkillBuff[i][0]);
                        try {
                            if (!skill.isSkillBuff()
                                    || skill.mp[Char.levelSkill[GameCanvas.menuSelectitem.idSkillBuff[i][0]]] > GameScr.mainChar.mp)
                                break;
                            GameCanvas.menuSelectitem.CoolDownSkillBuff[i] = now
                                    + (long) skill.coolDown[Char.levelSkill[GameCanvas.menuSelectitem.idSkillBuff[i][0]]
                                    - 1];
                            GameCanvas.menuSelectitem.idSkillBuff[i][3] = skill.coolDown[Char.levelSkill[GameCanvas.menuSelectitem.idSkillBuff[i][0]]
                                    - 1];
                            mainChar.startBuff(GameCanvas.menuSelectitem.idSkillBuff[i][1]);
                            GameService.gI().use_Buff(GameCanvas.menuSelectitem.idSkillBuff[i][0]);
                            mSystem.println("buff ne eeeeee");
                        } catch (Exception exception) {
                        }
                        break;
                    }
                }
                ++i;
            }
        }
    }

    public void paintIcon(mGraphics g) {
        if (isIntro) {
            return;
        }
        if (GameCanvas.currentScreen != null && GameCanvas.currentScreen != this) {
            return;
        }
        if (this.hideGUI == 2) {
            return;
        }
        if (GameCanvas.menuSelectitem.showMenu) {
            return;
        }
        if (GameCanvas.menu.showMenu) {
            return;
        }
        if (MainChar.blockkey) {
            return;
        }
        if (GameCanvas.currentDialog != null) {
            return;
        }
        if (this.chatMode || this.chatWorld) {
            return;
        }
        if (GameCanvas.menu2.isShow) {
            return;
        }
        int xss = -10;
        if (Char.Diemtiemnang > 0 || Char.Skill_Point > 0 && Char.Max_Skill_Learn > 1) {
            g.drawImage(imgPlus, 67 + xss + 10, GameCanvas.h - 5 - 4, 0, false);
        }
        if (GameCanvas.menuSelectitem.isAutoDanh[0]) {
            g.drawImage(imgauto, 79 + xss + 8, GameCanvas.h - 5 - 4, 0, false);
        }
        FontTeam.numberSmall_white.drawString(g,
                String.valueOf(GameScr.mainChar.x / 16) + "/" + GameScr.mainChar.y / 16, 34, GameCanvas.h - 5 - 5, 0,
                false);
    }

    public void paintShowEvent(mGraphics g) {
        if (this.eventShow != null || hShowEvent > 0) {
            GameCanvas.resetTrans(g);
            int x = GameCanvas.w - wShowEvent - 4;
            int yev = -6 + hShowEvent + 3;
            int hevemt = 35;
            int yt = 0;
            x = 0;
            yt = imghealth[0].getHeight() + 3;
            g.setColor(-11197952);
            g.fillRect(x - 2, (yev += imghealth[0].getHeight() + 3) - hevemt - 2, wShowEvent + 4, hevemt + 4, false);
            g.setColor(-3377408);
            g.fillRect(x - 1, yev - hevemt - 1, wShowEvent + 2, hevemt + 2, false);
            g.setClip(x, yev - hevemt, wShowEvent, hevemt);
            g.ClipRec(x, yev - hevemt, wShowEvent, hevemt);
            int i = 0;
            while (i < wShowEvent / 32 + 1) {
                int j = 0;
                while (j < hevemt / 32 + 1) {
                    g.drawImage(imgBgMainMenu, x + i * 32, yev - hevemt + j * 32 - 5, 0, true);
                    ++j;
                }
                ++i;
            }
            g.restoreCanvas();
            GameCanvas.resetTrans(g);
            g.setColor(-14194607);
            g.drawRegion(imgBoder[1], 0, 0, 8, 8, 7, x + wShowEvent, yev, mGraphics.BOTTOM | mGraphics.RIGHT, false);
            g.drawRegion(imgBoder[1], 0, 0, 8, 8, 1, x, yev, mGraphics.BOTTOM | mGraphics.LEFT, false);
            g.drawRegion(imgBoder[1], 0, 0, 8, 8, 0, x, yev - hevemt, mGraphics.TOP | mGraphics.LEFT, false);
            g.drawRegion(imgBoder[1], 0, 0, 8, 8, 2, x + wShowEvent, yev - hevemt, mGraphics.TOP | mGraphics.RIGHT,
                    false);
            g.drawImage(imgCheckevent, x + wShowEvent - 8 - 5, yev - 17, 3, false);
            if (this.eventShow != null) {
                if (mSystem.isAndroid) {
                    mFont.mfont_tile_Android.drawString(g, this.eventShow.nameEvent, x + 8, -35 + hShowEvent + 3 + yt,
                            0, false);
                    mFont.mfont_tile_Android.drawString(g, this.eventShow.contentEvent, x + 8,
                            -35 + hShowEvent + 13 + 3 + yt, 0, false);
                } else {
                    FontTeam.fontTile.drawString(g, this.eventShow.nameEvent, x + 8, -35 + hShowEvent + 3 + yt, 0,
                            false);
                    FontTeam.fontTile.drawString(g, this.eventShow.contentEvent, x + 8, -35 + hShowEvent + 13 + 3 + yt,
                            0, false);
                }
            }
        }
    }

    public void updateEvent() {
        if (EventScreen.vecEventShow.size() > 0) {
            if (this.eventShow == null) {
                this.eventShow = (MainEvent) EventScreen.vecEventShow.elementAt(0);
                timeEvent = 130;
                hShowEvent = 0;
            } else {
                if (--timeEvent <= 0) {
                    this.eventShow = null;
                    EventScreen.vecEventShow.removeElementAt(0);
                }
                if (hShowEvent < 35) {
                    hShowEvent += 10;
                }
                if (hShowEvent > 41) {
                    hShowEvent = 41;
                }
            }
            int x = GameCanvas.w - wShowEvent - 2;
            int yev = -6 + hShowEvent;
            int hevemt = 35;
            x = 0;
            if (GameCanvas.isPointer(x, (yev += imghealth[0].getHeight() + 3) - hevemt, wShowEvent, hevemt, 0)
                    && this.eventShow != null) {
                MainEvent ev = EventScreen.setEvent(this.eventShow.nameEvent, (byte) this.eventShow.IDCmd);
                if (ev != null) {
                    GameCanvas.mevent.doEvent(false, ev);
                }
                if (timeEvent > 40) {
                    timeEvent = 40;
                }
                GameCanvas.isPointerJustRelease[0] = false;
            }
            if (GameCanvas.isKeyPressed(9)) {
                GameCanvas.clearKeyHold(9);
                int t = EventScreen.setIndexEvent(this.eventShow.nameEvent, (byte) this.eventShow.IDCmd);
                if (t >= 0) {
                    GameCanvas.mevent.idSelect = t;
                }
                GameCanvas.mevent.init();
                GameCanvas.mevent.switchToMe(this);
            }
        } else if (hShowEvent > 0) {
            hShowEvent -= 20;
        }
    }

    public boolean canParty() {
        if (Tilemap.ismapLang) {
            return true;
        }
        if (GameScr.mainChar.typePK == 6) {
            return true;
        }
        if (GameScr.mainChar.typePK == -1 && this.focusedActor.getTypePK() != 0 && this.focusedActor.getTypePK() != 6) {
            return true;
        }
        return GameScr.mainChar.typePK != -1 && GameScr.mainChar.typePK != 0 && GameScr.mainChar.typePK != 6
                && (GameScr.mainChar.typePK == this.focusedActor.getTypePK() || this.focusedActor.getTypePK() == -1);
    }

    public static void checkQuest() {
        isQuest = false;
        int i = 0;
        while (i < MainMenu.ListQuest.length) {
            int j = 0;
            while (j < MainMenu.ListQuest[i].size()) {
                QuestInfo q = (QuestInfo) MainMenu.ListQuest[i].elementAt(j);
                if (q != null && (q.status == 1 || q.status == 2 || q.status == 4)) {
                    isQuest = true;
                    String[] data = Util.split(q.info, "\n");
                    textQuest = null;
                    textQuest = data.length > 7 ? mFont.name_Black.splitFontArray(data[7], 110)
                            : mFont.name_Black.splitFontArray(q.info, 110);
                    return;
                }
                ++j;
            }
            ++i;
        }
    }

    public void QuestAgain() {
        if (this.focusedActor != null) {
            mCommand cmd;
            SetInfoData s12;
            Quest q;
            int i;
            mVector menu;
            if (this.focusedActor.getStateQuest() == 0 && allQuestCanReceive.size() > 0) {
                menu = new mVector();
                i = 0;
                while (i < allQuestCanReceive.size()) {
                    q = (Quest) allQuestCanReceive.elementAt(i);
                    if (q != null && q.idNpcReceive == this.focusedActor.getIDNpc()) {
                        s12 = new SetInfoData();
                        s12.stateQuest = this.focusedActor.getStateQuest();
                        s12.idIcon = this.focusedActor.getIDicon();
                        s12.ID = this.focusedActor.getIDNpc();
                        s12.idQuest = i;
                        cmd = new mCommand(q.name, (IActionListener) this, 12, (Object) s12);
                        menu.addElement(cmd);
                    }
                    ++i;
                }
                GameCanvas.menu.startAt_MenuOption(menu, -1, -1, this.focusedActor.getStrTalk(),
                        this.focusedActor.getIDicon());
            }
            if (this.focusedActor.getStateQuest() == 2 && allQuestWorking.size() > 0) {
                menu = new mVector();
                i = 0;
                while (i < allQuestWorking.size()) {
                    q = (Quest) allQuestWorking.elementAt(i);
                    if (q != null && q.idNpcResponse == this.focusedActor.getIDNpc()) {
                        s12 = new SetInfoData();
                        s12.stateQuest = this.focusedActor.getStateQuest();
                        s12.idIcon = this.focusedActor.getIDicon();
                        s12.idQuest = i;
                        s12.ID = this.focusedActor.getIDNpc();
                        cmd = new mCommand(q.name, (IActionListener) this, 12, (Object) s12);
                        menu.addElement(cmd);
                    }
                    ++i;
                }
                GameCanvas.menu.startAt_MenuOption(menu, -1, -1, this.focusedActor.getStrTalk(),
                        this.focusedActor.getIDicon());
            }
            if (this.focusedActor.getStateQuest() == 1 && allQuestFinish.size() > 0) {
                menu = new mVector();
                i = 0;
                while (i < allQuestFinish.size()) {
                    q = (Quest) allQuestFinish.elementAt(i);
                    if (q != null && q.idNpcResponse == this.focusedActor.getIDNpc()) {
                        s12 = new SetInfoData();
                        s12.stateQuest = this.focusedActor.getStateQuest();
                        s12.idIcon = this.focusedActor.getIDicon();
                        s12.idQuest = i;
                        s12.ID = this.focusedActor.getIDNpc();
                        cmd = new mCommand(q.name, (IActionListener) this, 12, (Object) s12);
                        menu.addElement(cmd);
                    }
                    ++i;
                }
                GameCanvas.menu.startAt_MenuOption(menu, -1, -1, this.focusedActor.getStrTalk(),
                        this.focusedActor.getIDicon());
            }
        }
    }

    public boolean isNPCshop(short id) {
        if (this.idNPC_Sell != null) {
            int i = 0;
            while (i < this.idNPC_Sell.length) {
                if (id == this.idNPC_Sell[i]) {
                    return true;
                }
                ++i;
            }
        }
        return false;
    }

    public void clearloadMap() {
        isnextmap = false;
        Bossintro = null;
        isIntro = false;
        MainChar.blockkey = false;
        if (vecCharintro != null) {
            vecCharintro.removeAllElements();
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
        nameMap = "";
        GameCanvas.gameScr.focusedActor = null;
        GameData.removeAllImgTree();
        GameData.listbyteData.clear();
        GameData.listImgIcon.clear();
        imgBigMap = null;
        MonsterTemplate.ALL_DATE_NEW_MONSTER.clear();
    }

    public static void paintinfo18plush(mGraphics g) {
        if (!GameCanvas.gameScr.ispaint12Plus) {
            return;
        }
        if (mSystem.isPC || mSystem.isIP) {
            g.fillRect(0, 0, 0, 0, false);
        }
        GameCanvas.resetTrans(g);
        int xpaint = 115;
        int ypaint = 0;
        if (GameCanvas.currentScreen != GameCanvas.gameScr) {
            xpaint = 0;
        }
        if (isIntro) {
            xpaint = 0;
            ypaint = 15;
        }
        int wStr = mFont.tahoma_7_white.getWidth(
                "12+ Ch\u01a1i qu\u00e1 180 ph\u00fat m\u1ed7i ng\u00e0y s\u1ebd h\u1ea1i s\u1ee9c kh\u1ecfe.");
        g.setClip(xpaint - 2, ypaint, wStr, 15);
        g.ClipRec(xpaint - 2, ypaint, wStr, 15);
        g.drawImage(imgBack, xpaint, ypaint, 0, true);
        g.drawImage(imgBack, xpaint + 140, ypaint, 0, true);
        mFont.tahoma_7_white.drawString(g,
                "12+ Ch\u01a1i qu\u00e1 180 ph\u00fat m\u1ed7i ng\u00e0y s\u1ebd h\u1ea1i s\u1ee9c kh\u1ecfe.", xpaint,
                ypaint + 3, 0, true);
        g.restoreCanvas();
        GameCanvas.resetTrans(g);
    }

    public static boolean canupdatenotify() {
        return GameCanvas.notify != null && !isIntro && (GameCanvas.currentScreen == ShowinfoItem.gI()
                || GameCanvas.currentScreen == ScreenClan.gI() || GameCanvas.currentScreen == InfoOtherCharScr.gI()
                || GameCanvas.currentScreen == GameCanvas.msgchat || GameCanvas.currentScreen == GameCanvas.gameScr
                || GameCanvas.currentScreen == MainMenu.gI() || GameCanvas.currentScreen == ShopHairScreen.gI())
                && GameCanvas.currentScreen != ChangScr.gI();
    }

    public static void playSound1() {
        if (Tilemap.lv == 0) {
            mSound.playMus(0, mSound.volumeMusic, true);
        }
        if (Tilemap.lv != 0) {
            if (Tilemap.idTile == 0) {
                mSound.playMus(1, mSound.volumeMusic, true);
            }
            if (Tilemap.idTile == 5) {
                mSound.playMus(2, mSound.volumeMusic, true);
            }
            if (Tilemap.idTile == 4) {
                mSound.playMus(3, mSound.volumeMusic, true);
            }
        }
    }

    public void updateTips() {
    }

    public static void onWeather(byte weather2, int number, int timeLimit) {
        if (weather2 != -1) {
            if (weather2 == 2) {
                effAnimate.addElement(new AnimateEffect(weather2, true, number, timeLimit));
                effAnimate.addElement(new AnimateEffect((byte) 0, true, number, timeLimit));
            } else {
                effAnimate.addElement(new AnimateEffect(weather2, true, number, timeLimit));
            }
        } else {
            int i = 0;
            while (i < effAnimate.size()) {
                AnimateEffect ef = (AnimateEffect) effAnimate.elementAt(i);
                ef.isStop = true;
                ++i;
            }
        }
    }

    public void createCloud() {
        int i = 0;
        while (i < 6) {
            Point p = new Point(Tilemap.w * 16 + r.random(50, 100), 70 + 16 * r.nextInt(Tilemap.h), r.random(1, 3),
                    r.nextInt(2));
            this.vecCloud.addElement(p);
            ++i;
        }
    }

    public void paintClound(mGraphics g, int player) {
        if (!this.isCloud) {
            return;
        }
        int i = 0;
        while (i < this.vecCloud.size()) {
            Point p = (Point) this.vecCloud.elementAt(i);
            if (p != null && p.player == player) {
                g.drawImage(imgCloud[p.id], p.x, p.y, 0, false);
            }
            ++i;
        }
    }

    public void updateClound() {
        if (!this.isCloud) {
            return;
        }
        int i = 0;
        while (i < this.vecCloud.size()) {
            Point p = (Point) this.vecCloud.elementAt(i);
            if (p != null) {
                p.x -= p.speed;
                if (p.x <= -(mGraphics.getImageWidth(imgCloud[p.id]) + 100)) {
                    p.x = p.sx + r.random(100, 200);
                    p.y = 70 + 16 * r.nextInt(Tilemap.h);
                    p.player = (byte) r.nextInt(2);
                    p.speed = r.random(1, 3);
                }
            }
            ++i;
        }
    }

    public static Actor isHavePet(short petid) {
        int i = 0;
        while (i < GameCanvas.gameScr.actors.size()) {
            Actor ac = (Actor) GameCanvas.gameScr.actors.elementAt(i);
            if (ac != null && ac.catagory == 12 && ac.ID == petid) {
                return ac;
            }
            ++i;
        }
        return null;
    }

    public void startWeapsAngleBoss(int angle, int power, Actor acFr, Actor acTo, int type, boolean isUpDown, int addw,
                                    int addh, int xbg, int ybg, boolean lastActo) {
        if (isUpDown) {
            if (acFr != null && acTo != null) {
                WeaponsAngleBoss a = new WeaponsAngleBoss(angle, power, acFr, acTo, type, addw, addh, xbg, ybg,
                        lastActo);
                arrowsUp.addElement(a);
            }
        } else {
            WeaponsAngleBoss a = new WeaponsAngleBoss(angle, power, acFr, acTo, type, addw, addh, xbg, ybg, lastActo);
            this.arrowsDown.addElement(a);
        }
    }

    public void SkillofBoss_Snake(Actor acFr, Actor acTo, int w, int h) {
        int i = 0;
        while (i < 12) {
            this.startWeapsAngleBoss(i * 30, 10, acFr, acTo, 0, true, w, h, 0, 0, i == 11);
            ++i;
        }
        this.timerung = 20;
    }

    public void creatWeaponFire(Actor live_From, Actor live_To, int type) {
        WeaponsFire w = new WeaponsFire(live_From, live_To, type);
        if (live_From.getDir() != 1) {
            arrowsUp.addElement(w);
        } else {
            this.arrowsDown.addElement(w);
        }
    }

    class Ticker {
        int x;
        int y;
        int y1;
        public boolean end = false;
        public String info = "";

        public Ticker(String info, int x, int y) {
            this.info = info;
            this.x = x;
            this.y = y;
        }

        public void paint(mGraphics g) {
            FontTeam.normalFont[0].drawString(g, this.info, this.x, this.y, 0, false);
        }

        public void update() {
            if (this.x + FontTeam.normalFont[0].getWidth(this.info) + 10 < 0) {
                this.y1 -= 2;
            }
            if (this.y1 < -18) {
                this.end = true;
            }
            this.x -= 2;
        }
    }
}
