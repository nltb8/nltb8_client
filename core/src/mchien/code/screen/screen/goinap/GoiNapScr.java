package mchien.code.screen.screen.goinap;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.microedition.lcdui.Image;

import lib.mGraphics;
import lib2.Message;
import lib2.TField;
import lib2.mFont;
import mchien.code.main.GameCanvas;
import mchien.code.model.IActionListener;
import mchien.code.model.ImageIcon;
import mchien.code.model.Item;
import mchien.code.model.ItemOption;
import mchien.code.model.ScrollResult;
import mchien.code.network.GameService;
import mchien.code.screen.Res;
import mchien.code.screen.screen.ChangScr;
import mchien.code.screen.screen.GameData;
import mchien.code.screen.screen.GameScr;
import mchien.code.screen.screen.ScreenTeam;

public class GoiNapScr extends ScreenTeam implements IActionListener {
    private static GoiNapScr _instance;
    private boolean isScrGiftCode;
    private short idBtnGiftCode;
    private short idBtnGiftCodeHold;
    private Image imgBtnGiftCode, imgBtnGiftCodeHold;

    public static GoiNapScr gI(){
        if(_instance == null)
            _instance = new GoiNapScr();
        return _instance;
    }

    public short idGoiNap;
    public int idBG, xBG, yBG, wBG, hBG;
    public int boxStartX, boxStartY, boxBorder, boxPadding;

    private int f1, f2, f3, f4, f5;
    private int fr1, fr2, fr3, fr4, fr5;
    public Image imgBG;
    private long timeLoadImg;

    private BoxItem[] boxs;

    private BoxItem boxSelet;
    private List<mcBtn> BtnTabs;
    public TField tfMoney;

    private boolean isFocusBtnGiftcode;
    private int xGiftcode, yGiftcode, wGiftcode, hGiftcode;

    public GoiNapScr(){
        init();
    }

    public void switchToMe(ScreenTeam lastSRC, int idBG){
        super.switchToMe(lastSRC);
        this.idBG = idBG;
        imgBG = null;
    }

    @Override
    public void init(){
        idBG = -1;
        imgBG = null;

        wBG = 336;
        hBG = 193;

        xBG = GameCanvas.w / 2 - (wBG / 2);
        yBG = GameCanvas.h / 2 - (hBG / 2);

        boxStartX = xBG + 120;
        boxStartY = yBG + 55;
        boxBorder = 31;
        boxPadding = 10;

        wGiftcode = 45;
        hGiftcode = 11;
        xGiftcode = xBG + 201;
        yGiftcode = yBG + 130;


        BtnTabs = new ArrayList<>();

        tfMoney = new TField();
        tfMoney.width = 73;
        tfMoney.height = ScreenTeam.ITEM_HEIGHT + 4;
        tfMoney.x = xBG + 186;
        tfMoney.y = yBG + 98;
        tfMoney.setStringNull(" ");
    }

    @Override
    public void updateKey(){
        if(idBG > -1 && imgBG == null)
            return;
        boolean isCheck = false;
        boolean isPointShowText = false;
        if(boxs != null){
            if(boxSelet != null && boxSelet.isShowText){

                ScrollResult r = boxSelet.cmrShowText.updateKey();
                if(r.isDowning || r.isFinish){
                    isPointShowText = true;
                    isCheck = true;
                }

                if(!isPointShowText && GameCanvas.isPointerJustRelease[0] && GameCanvas.isPointer(xBG, yBG, wBG, hBG, 0))
                    boxSelet.closeText();
            }

            if(!isCheck){
                for (BoxItem i : boxs){
                    if(i.updateKey()){
                        if(boxSelet != null && boxSelet != i)
                            boxSelet.closeText();
                        boxSelet = i;
                        isCheck = true;
                    }
                    else if(i.isPointerDown)
                        isCheck = true;
                }
            }
        }
        if(!isCheck && GameCanvas.isPointerJustRelease[0] && !GameCanvas.isPointer(xBG, yBG, wBG, hBG, 0) ){
            doClose();
        }
    }

    private void doClose() {
        if(boxSelet != null)
            boxSelet.closeText();
        boxSelet = null;
        this.lastSCR.switchToMe();
    }

    @Override
    public void update(){
        if(idBG > -1 && imgBG == null){
            imgBG = loadImage(idBG);
            this.fr1 = (this.fr1 + 1) % ChangScr.frame1.length;
            this.fr2 = (this.fr2 + 1) % ChangScr.frame2.length;
            this.fr3 = (this.fr3 + 1) % ChangScr.frame3.length;
            this.fr4 = (this.fr4 + 1) % ChangScr.frame4.length;
            this.fr5 = (this.fr5 + 1) % ChangScr.frame5.length;
            this.f1 = ChangScr.frame1[this.fr1];
            this.f2 = ChangScr.frame2[this.fr2];
            this.f3 = ChangScr.frame3[this.fr3];
            this.f4 = ChangScr.frame4[this.fr4];
            this.f5 = ChangScr.frame5[this.fr5];
            return;
        }

        for (mcBtn i: BtnTabs){
            i.update();
        }

        if(isScrGiftCode){
            tfMoney.update();
            if(imgBtnGiftCode == null)
                imgBtnGiftCode = loadImage(idBtnGiftCode);
            else if(imgBtnGiftCodeHold == null)
                imgBtnGiftCodeHold = loadImage(idBtnGiftCodeHold);
            else if (GameCanvas.isPointer(xGiftcode, yGiftcode, wGiftcode, hGiftcode, 0) && GameCanvas.canTouch()) {
                this.isFocusBtnGiftcode = true;
                if (GameCanvas.isPointerJustRelease[0] && GameCanvas.isPointerClick[0]) {
                    GameCanvas.isPointerClick[0] = false;
                    sendGiftCode();
                }
            }
            else
                this.isFocusBtnGiftcode = false;
        }
        else {
            if(boxs != null){
                for (BoxItem i : boxs){
                    i.update();
                }
            }
        }
    }

    private void sendGiftCode() {
        String s = tfMoney.getText();
        if(s == null || s.isEmpty())
            GameCanvas.startOKDlg("Hãy nhập vào giftcode của bạn.");
        else
            GameService.gI().inputGiftCode(s);
    }

    @Override
    public boolean keyPress(int keyCode){
        if(isScrGiftCode)
            return this.tfMoney.keyPressed(keyCode);
        return false;
    }

    @Override
    public void paint(mGraphics g){
        if (this.lastSCR != null) {
            this.lastSCR.paint(g);
        }
        GameCanvas.resetTrans(g);
        if(imgBG == null){
            paintLoading(g);
            return;
        }
        g.drawImage(imgBG, xBG, yBG, 0, false);
        for (mcBtn i : BtnTabs){
            i.paint(g);
        }

        if(isScrGiftCode){
            tfMoney.paint(g);
            if(!isFocusBtnGiftcode && imgBtnGiftCode != null)
                g.drawImage(imgBtnGiftCode, xGiftcode, yGiftcode, 0, false);
            else if(isFocusBtnGiftcode && imgBtnGiftCodeHold != null)
                g.drawImage(imgBtnGiftCodeHold, xGiftcode, yGiftcode, 0, false);
        }
        else {
            if(boxs != null){
                for (BoxItem i : boxs){
                    i.paint(g, i == boxSelet);
                }
            }
        }

        GameCanvas.resetTrans(g);

        int xT = xBG+ (wBG/2);
        int yT = yBG+ hBG+5;
        mFont.tahoma_7b_yellow.drawString(g, "Bấm ra bên ngoài để đóng.",xT, yT , mGraphics.VCENTER, false);

        if(!isScrGiftCode && boxSelet != null)
            boxSelet.paintShowText(g);
    }

    private void paintLoading(mGraphics g) {
        g.drawImage(ChangScr.loading1[this.f1], GameCanvas.w / 2, GameCanvas.h / 2 + mGraphics.getImageHeight(GameCanvas.getLogo()) / 2 + 10, 3, false);
        g.drawImage(ChangScr.loading2[this.f2], GameCanvas.w / 2, GameCanvas.h / 2 + mGraphics.getImageHeight(GameCanvas.getLogo()) / 2 + 10, 3, false);
        g.drawImage(ChangScr.loading3[this.f3], GameCanvas.w / 2, GameCanvas.h / 2 + mGraphics.getImageHeight(GameCanvas.getLogo()) / 2 + 10, 3, false);
        g.drawImage(ChangScr.loading4[this.f4], GameCanvas.w / 2, GameCanvas.h / 2 + mGraphics.getImageHeight(GameCanvas.getLogo()) / 2 + 10, 3, false);
        g.drawImage(ChangScr.loading5[this.f5], GameCanvas.w / 2, GameCanvas.h / 2 + mGraphics.getImageHeight(GameCanvas.getLogo()) / 2 + 10, 3, false);
    }

    @Override
    public void perform(int var1, Object var2) {

    }

    @Override
    public void paint(mGraphics var1, int var2, int var3, int var4, Object var5) {

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

    public void read(Message m) throws IOException {
        DataInputStream r = m.reader();

        byte b = r.readByte();
        switch (b){
            case 0:
                BtnTabs.clear();
                int count = r.readByte();
                final int tabSelectGoiNap = r.readByte();
                int wBtn = 53;
                int hBtn = 20;
                int x = xBG + 90;
                int y = yBG + 20;
                for(int i=0; i< count; i++){
                    if(i>0)
                        x+=wBtn;
                    int icon = r.readShort();
                    final int idTab = r.readByte();
                    BtnTabs.add(new mcBtn(icon, x, y, wBtn, hBtn, new Runnable() {
                        @Override
                        public void run() {
                            if(idTab != tabSelectGoiNap)
                                GameService.gI().changeTabGoiNap(idTab);
                        }
                    }));
                }



                this.idGoiNap = r.readShort();
                short idBG = r.readShort();
                byte cBox = r.readByte();
                boxs = new BoxItem[cBox];
                int stY = boxStartY;
                for(int i=0; i< cBox; i++){
                    if(i > 0)
                        stY += boxBorder + boxPadding;
                    boxs[i] = new BoxItem(idGoiNap, r.readByte(), r.readShort(), r.readShort(), boxStartX, stY);
                    int sizeItem = r.readByte();
                    for (int j=0; j< sizeItem; j++){
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
                        it0.quantity = m.reader().readInt();
                        int nOption = m.reader().readByte();
                        int k = 0;
                        while (k < nOption) {
                            ItemOption itoption = new ItemOption();
                            itoption.id = m.reader().readShort();
                            itoption.idColor = m.reader().readByte();
                            itoption.value = m.reader().readInt();
                            itoption.value2 = m.reader().readInt();
                            it0.options.addElement(itoption);
                            ++k;
                        }
                        it0.type = m.reader().readByte();
                        boxs[i].items.add(it0);
                    }
                    boxs[i].isActiveBtn = r.readBoolean();
                    boxs[i].iconBtn = r.readShort();
                    boxs[i].textBtn = r.readUTF();
                }

                if(boxs.length < 1){
                    isScrGiftCode = true;
                    short idBtnGiftCode = r.readShort();
                    short idBtnGiftCodeHold = r.readShort();
                    if(idBtnGiftCode != this.idBtnGiftCode)
                        imgBtnGiftCode = null;
                    if(idBtnGiftCodeHold != this.idBtnGiftCodeHold)
                        imgBtnGiftCodeHold = null;

                    this.idBtnGiftCode = idBtnGiftCode;
                    this.idBtnGiftCodeHold = idBtnGiftCodeHold;
                }
                else
                    isScrGiftCode = false;

                if(idBG != this.idBG){
                    imgBG = null;
                    this.idBG = idBG;
                }
                tfMoney.setText("");
                switchToMe(GameCanvas.gameScr);
                break;
            case 1://update btnBox
                short idxBox = r.readShort();
                short icon = r.readShort();
                String text = r.readUTF();
                boolean isActive = r.readBoolean();
                if(boxs != null ){
                    for (BoxItem bb: boxs){
                        if(bb.idBox == idxBox){
                            bb.isActiveBtn = isActive;
                            bb.iconBtn = icon;
                            bb.textBtn = text;
                            bb.imgBtn = null;
                            break;
                        }
                    }
                }
                break;
        }
    }
}
