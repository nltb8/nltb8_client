package mchien.code.screen.screen;

import java.util.ArrayList;
import java.util.List;

import lib.mGraphics;
import lib.mSystem;
import lib.mVector;
import lib2.TField;
import lib2.mFont;
import mchien.code.main.GameCanvas;
import mchien.code.model.IActionListener;
import mchien.code.model.Item;
import mchien.code.model.ItemOption;
import mchien.code.model.Scroll;
import mchien.code.model.ScrollResult;
import mchien.code.model.T;
import mchien.code.model.mCommand;
import mchien.code.network.GameService;
import mchien.code.screen.Res;

public class TradeScr extends ScreenTeam implements IActionListener {
    public int xPanel, yPanel;
    public int wPanel, hPanel;
    public int hNavigateBar, hContent;
    public int maginNar;

    public int xShowText, yContent, wShowText;

    public List<mcButton> buttons;
    public mcButton btnSelect;
    public int tabSelect;
    private Scroll cmrItem;
    private Scroll cmrShowText;

    private static TradeScr instance;
    private int colum, size;
    private int yShowText;
    private int hShowText;
    private int totalInfoshow;
    private mVector showText;
    private int numItemStart;
    private int numItemStart2;
    private int laststar2;
    private int laststar;
    private int disString;
    private boolean isHalfstart;
    private boolean isHalfstart2;
    private boolean isShowText;
    public TField tfMoney;

    public static mCommand cmdSelect, cmdRemove, cmdKhoa, cmdXong, cmdClose;
    public mVector itemBag, itemSend, itemReceive;
    public int xuReceive;
    public int maxInventory, maxItemTrade;

    public boolean isLock, isCanAccept;
    private boolean isFocusClose;
    private int xClose, yClose, wClose, hClose;
    private boolean isShow;

    public static TradeScr gI(){
        if(instance == null)
            instance = new TradeScr();
        return instance;
    }
    public TradeScr(){
        init();
    }

    @Override
    public void init(){
        wPanel = 300;
        hPanel = 200;
        xPanel = GameCanvas.w / 2 - (wPanel / 2);
        yPanel = GameCanvas.h / 2 - (hPanel / 2);

        hNavigateBar = 35;
        maginNar = 5;

        hContent = hPanel - (hNavigateBar + maginNar);

        colum = 7;
        size = 26;
        disString = 12;
        maxItemTrade = colum * 5;

        wShowText = wPanel - (colum * size) - 2;
        xShowText = xPanel + (wPanel - wShowText);
        yShowText = yContent = yPanel + hNavigateBar + maginNar;
        hShowText = hContent;

        int hc = mGraphics.getImageHeight(GameScr.imgButton[4]) / 2;
        int wc = mGraphics.getImageWidth(GameScr.imgButton[4]);
        xClose = xPanel + wPanel - wc;
        yClose = yPanel - hc - 3;
        hClose = hc;
        wClose = wc;


        buttons = new ArrayList<>();
        cmrItem = new Scroll();
        cmrShowText = new Scroll();
        showText = new mVector("Show text Item");
        itemSend = new mVector("Items Send");
        tfMoney = new TField();
        tfMoney.width = 100;
        tfMoney.height = ScreenTeam.ITEM_HEIGHT + 4;
        tfMoney.x = xPanel + 10;
        tfMoney.y = yContent + (25 - tfMoney.height);
        tfMoney.setStringNull("Nhập xu muốn gửi");

        cmdSelect = new mCommand(T.tuychon, this, 0);
        cmdRemove = new mCommand(T.layra, this, 1);
        cmdKhoa = new mCommand(T.Khoa, this, 2);
        cmdXong = new mCommand(T.DongY, this, 3);

        cmdSelect.setXY(xPanel+wPanel - cmdSelect.wCmd, yPanel + hPanel + 3);
        cmdKhoa.setXY(xPanel+wPanel - cmdKhoa.wCmd, yPanel + hPanel + 3);
        cmdXong.setXY(xPanel+wPanel - cmdXong.wCmd, yPanel + hPanel + 3);

        cmdRemove.setXY(xPanel+wPanel - cmdRemove.wCmd - cmdKhoa.wCmd-3, yPanel + hPanel + 3);

//        cmdClose = new mCommand()

        initBtn();

    }

    public void initBtn(){
        buttons.clear();
        int y = yPanel + (hNavigateBar / 2) - 12;
        int xStart = xPanel + 10;
        int w = 70;

        mcButton ht = new mcButton("Hành trang", xStart, y, w);
        buttons.add(ht);
        buttons.add(new mcButton("Bản thân", xStart += w+10, y, w));
        buttons.add(new mcButton("Đối phương", xStart += w+10, y, w));

        switchTab(0);
    }

    public void setItemBag(mVector itemBag){
        int size = itemBag.size();
        if(size > 0)
            maxInventory = size + (colum - size % colum);
        else
            maxInventory = 0;
        this.itemBag = itemBag;
    }
    public void setItemReceive(int xu, mVector items){
        this.xuReceive = xu;
        this.itemReceive = items;
        this.isCanAccept = true;
    }

    @Override
    public void perform(int var1, Object var2) {
        switch (var1){
            case 0:
                if(selected >=0 && selected < itemBag.size()){
                    Item it = (Item) itemBag.elementAt(selected);
                    if(it != null){
                            addItemSend(it, -1);
                    }
                }
                break;
            case 1:
                if(selected >=0 && selected < itemSend.size()){
                    Item it = (Item) itemSend.elementAt(selected);
                    removeItemSend(it);
                }
                break;
            case 2:
                if(isLock)
                    break;
                try{
                    int xu =0;
                    if(!tfMoney.getText().isEmpty())
                        xu = Integer.parseInt(tfMoney.getText());

                    if(xu < 0)
                        GameCanvas.startOKDlg("Số xu nhập vào không phù hợp.");
                    else
                        GameService.gI().TradeProces(0, xu, itemSend);
                }catch (Exception e){
                    GameCanvas.startOKDlg("Số xu nhập vào không phù hợp.");
                }
                break;
            case 3:
                if(isLock && isCanAccept)
                    GameService.gI().TradeProces(1);
                break;
            case 20:
                try{
                    String str = GameCanvas.inputDlg.tfInput.getText();
                    GameCanvas.endDlg();
                    if (str.equals(""))
                        return;
                    int count = Integer.parseInt(str);
                    if(count < 0)
                        GameCanvas.startOKDlg("Số lượng không phù hợp.");
                    else if(selected >=0 && selected < itemBag.size()){
                        Item it = (Item) itemBag.elementAt(selected);
                        if(it.quantity < count)
                            GameCanvas.startOKDlg("Số lượng không đủ.");
                        else
                            addItemSend(it, count);
                    }
                }catch (Exception e){
                    GameCanvas.startOKDlg("Có lỗi xảy ra, vui lòng chỉ nhập số.");
                }
                break;
        }
    }
    private void addItemSend(Item it, int count){
        for(int i=0; i<itemSend.size(); i++){
            Item item = (Item) itemSend.elementAt(i);
            if(item.ID == it.ID){
                GameCanvas.startOKDlg("Đã có item này.");
                return;
            }
        }
        Item it0 = new Item();
        it0.ID = it.ID;
        it0.catagory = it.catagory;
        it0.level = it.level;
        it0.charClazz = it.charClazz;
        it0.name = it.name;
        it0.lock = it.lock;
        it0.plus = it.plus;
        it0.idIcon = it.idIcon;
        it0.colorname = it.colorname;
        it0.cantrade = it.cantrade;
        it0.cansell = it.cansell;
        it0.priceShop = it.priceShop;
        if(count == -1)
            it0.quantity = it.quantity;
        else
            it0.quantity = count;
        for(int i=0; i< it.options.size(); i++){
            ItemOption op = (ItemOption) it.options.elementAt(i);
            ItemOption itoption = new ItemOption();
            itoption.id = op.id;
            itoption.idColor = op.idColor;
            itoption.value = op.value;
            itoption.value2 = op.value2;
            it0.options.addElement(itoption);
        }

        itemSend.add(it0);
    }
    private void removeItemSend(Item it){
        for(int i=0; i<itemSend.size(); i++){
            Item item = (Item) itemSend.elementAt(i);
            if(item.ID == it.ID){
                itemSend.removeElement(item);
                return;
            }
        }
    }

    @Override
    public void paint(mGraphics g){
        if (this.lastSCR != null) {
            this.lastSCR.paint(g);
        }
        GameCanvas.resetTrans(g);
        g.setClip(this.xPanel, this.yPanel, this.wPanel, this.hPanel);
        g.ClipRec(this.xPanel, this.yPanel, this.wPanel, this.hPanel);

        for(int _w = 0; _w < wPanel / 32 + 1; ++_w) {
            for(int _h = 0; _h < hPanel / 32 + 1; ++_h) {
                g.drawImage(GameScr.imgBgMainMenu, this.xPanel + _w * 32, this.yPanel + _h * 32 - 5, 0, true);
            }
        }
        for(mcButton b : buttons)
            b.paint(g);

        paintBox(g);
        //todo paint name
        paintTab(g);
        paintShowText(g);

        g.fillRect(0, 0, 0, 0, false);
        if (GameCanvas.isTouch) {
            g.drawRegion(GameScr.imgButton[4], 0, (this.isFocusClose ? 1 : 0) * hClose, wClose, hClose, 0, xClose, yClose, 0, false);
        }
        paintMoney(g);
        GameCanvas.resetTrans(g);
    }

    private void paintBox(mGraphics g){

        g.setColor(-11262464);
        this.paintBgSub(g, this.xPanel, this.yPanel, wPanel, this.hNavigateBar, true);

        this.paintBgSub(g, this.xPanel, this.yContent, wPanel - wShowText + 2, this.hContent, true);
        this.paintBgSub(g, this.xShowText, this.yContent, wShowText, this.hContent, true);


    }

    public void paintBgSub(mGraphics g, int x, int y, int w, int h, boolean isBoder) {
        g.setColor(-9751532);
        g.drawRect(x, y, w, h, false);
        g.drawRect(x + 2, y + 2, w - 4, h - 4, false);


        g.setColor(-4034289);
        g.drawRect(x + 1, y + 1, w - 2, h - 2, false);

        if (isBoder) {
            g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 7, x + w + 1, y + h + 1, mGraphics.BOTTOM | mGraphics.RIGHT, false);
            g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 1, x, y + h + 1, mGraphics.BOTTOM | mGraphics.LEFT, false);
            g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 0, x, y, mGraphics.TOP | mGraphics.LEFT, false);
            g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 2, x + w + 1, y, mGraphics.TOP | mGraphics.RIGHT, false);
        }

    }
    private void paintLine(mGraphics g, int x, int y, int x2, int y2){
        boolean row = y == y2;
        boolean colum = x==x2;
        g.setColor(-9751532);
        g.drawLine(x, y, x2, y2, false);
        g.drawLine(colum? x+2: x, row? y+2:y, colum? x2+2: x2, row? y2+2:y2, false);

        g.setColor(-4034289);
        g.drawLine(colum? x+1: x, row? y+1:y, colum? x2+1: x2, row? y2+1:y2, false);
    }

    private void paintTab(mGraphics g) {
        int wContent = wPanel - wShowText+1;
        int y = this.yContent+25;
        this.paintLine(g, this.xPanel+2, y, this.xPanel + wContent, y);

        switch (tabSelect){
            case 0:
                paintInventory(g, wContent, y);
                break;
            case 1:
                paintItemSend(g, wContent, y);
                break;
            case 2:
                paintItemRecive(g, wContent, y);
                break;
        }
        if(isLock && isCanAccept)
            cmdXong.paint(g);
    }

    private void paintItemRecive(mGraphics g,  int wContent, int y) {
        if(!isCanAccept)
            mFont.tahoma_7b_white.drawString(g, "Đối phương chưa khóa giao dịch", xPanel + (wContent/2), yContent + 13 - 6, mGraphics.VCENTER, false);
        else {
            int xss = 0;
            int yp = 0;
            yp = yContent + 13 - 3;
            xss = this.xPanel + 10;
            String xu = Res.getDotNumber(xuReceive);
            int ypp = 0;
            if (mSystem.isAndroid) {
                ypp = -3;
            }
            if (mSystem.isIP) {
                ypp = -1;
            }

            g.drawRegion(GameScr.imgMoney, 0, 7, 9, 7, 0, xss, yp, 0, false);
            mFont.tahoma_7b_white.drawString(g, xu, xss + 15, yp + ypp -1, 0, false);
            paintListItem(g, itemReceive, maxItemTrade, wContent, y);
        }
    }

    private void paintItemSend(mGraphics g,  int wContent, int y) {
        tfMoney.paint(g);
        paintListItem(g, itemSend, maxItemTrade, wContent, y);

        if(!isLock){
            cmdKhoa.paint(g);
            cmdRemove.paint(g);
        }
    }

    private void paintInventory(mGraphics g, int wContent, int y) {
        mFont.tahoma_7b_white.drawString(g, "Chọn vật phẩm để giao dịch", xPanel + (wContent/2), yContent + 13 - 6, mGraphics.VCENTER, false);

        paintListItem(g, itemBag, maxInventory, wContent, y);

        if(!isLock)
            cmdSelect.paint(g);
    }

    private void paintListItem(mGraphics g, mVector items, int maxInventory, int wContent, int y){
        mFont.tahoma_7_white.drawString(g, " ", -10, -10, 1, false);
        if(maxInventory <= 0 || items.size() <= 0){
            mFont.tahoma_7_white.drawString(g, "Không có vật phẩm nào.", xPanel + (wContent/2), y + ((hContent - 25) / 2 - 6), mGraphics.VCENTER, false);
            return;
        }
        int t = maxInventory / colum + (maxInventory % colum != 0 ? 1 : 0);
        int xbg = xPanel+2;
        int ybg = y+3;
        this.cmrItem.setStyle(t, size, xbg , ybg, colum * size, 5 * size , true, colum);
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
        if (selected > -1 && selected < items.size()) {
            g.drawImage(GameScr.imgfocus[3], xbg + selected % colum * size + size / 2, ybg + selected / colum * size + size / 2, 3, true);
        }
        mGraphics.resetTransAndroid(g);
        g.restoreCanvas();
        GameCanvas.resetTrans(g);
    }

    public void doShowInfoItem(Item sItem) {
        int x0 = this.xPanel + this.size / 2 + selected % this.colum * this.size + 16;
        int y0 = 11 + this.yPanel + selected / this.colum * this.size + this.size * 3 / 2 + this.size - 1;
        if (sItem != null) {
            this.showItemInventoryInfo(sItem, x0, y0);
        }
    }
    private void showItemInventoryInfo(Item item, int x, int y) {
        this.setShowText(item.getInfoItemShow(null, true), x, y, item, true, false);
    }

    private void setShowText(mVector text, int x0, int y0, Item item, boolean isTile, boolean isSetGroup) {
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

    public void paintMoney(mGraphics g) {
        int xss = 0;
        int yp = 0;
        yp = yClose +2;
        xss = this.xPanel + this.wPanel - 42;
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
    }

    @Override
    public void paint(mGraphics g, int var2, int var3, int var4, Object var5) {

    }

    @Override
    public void updateKey(){
        ScrollResult r = cmrItem.updateKey();
        if(r.isDowning || r.isFinish){
            selected = r.selected;
            if (selected > -1 && selected < itemBag.size())
                doShowInfoItem((Item)itemBag.elementAt(selected));
            else
                closeText();
        }
        cmrShowText.updateKey();

        if(GameCanvas.isTouch){
            this.isFocusClose = false;
            if (GameCanvas.isPointer(xClose, yClose, wClose, hClose, 0) && GameCanvas.canTouch()) {
                this.isFocusClose = true;
                if (GameCanvas.isPointerJustRelease[0] && GameCanvas.isPointerClick[0]) {
                    this.doClose(true);
                    GameCanvas.isPointerClick[0] = false;
                }
            }
        }
    }

    @Override
    public void update(){
        for(int i=0; i< buttons.size(); i++){
            mcButton b = buttons.get(i);
            if(btnSelect != b && b.isPointer()){
                switchTab(i);
                break;
            }
        }
        cmrItem.updatecm();
        cmrShowText.updatecm();
        switch (tabSelect){
            case 0:
                if(!isLock && getCmdPointerLast(cmdSelect))
                    cmdSelect.performAction();
                break;
            case 1:
                if(!isLock)
                    this.tfMoney.update();
                if(!isLock){
                    if(getCmdPointerLast(cmdKhoa))
                        cmdKhoa.performAction();
                    if(getCmdPointerLast(cmdRemove))
                        cmdRemove.performAction();
                }
                break;
        }
        if(isLock && isCanAccept && getCmdPointerLast(cmdXong))
            cmdXong.performAction();
    }

    @Override
    public boolean keyPress(int keyCode){
        if(tabSelect == 1 && !isLock)
            return this.tfMoney.keyPressed(keyCode);
        return false;
    }

    public void doClose(boolean isSendClose){
        if(isShow){
            if(isSendClose)
                GameService.gI().TradeProces(-2);
            this.closeText();
            this.lastSCR.switchToMe();
            isShow = false;
        }
    }
    public void doClose(){
        doClose(false);
    }

    @Override
    public void switchToMe(ScreenTeam lastSCR) {
        super.switchToMe(lastSCR);
        isShow = true;
        xuReceive = 0;
        isLock = false;
        isCanAccept = false;
        tfMoney.setText("");
        if(itemSend != null)
            itemSend.removeAllElements();
        if(itemReceive != null)
            itemReceive.removeAllElements();
        switchTab(0);
    }
    private void switchTab(int tab){
        if(tabSelect != tab){
            tabSelect = tab;
            closeText();
            if(btnSelect != null)
                btnSelect.isSelect = false;
            btnSelect = buttons.get(tab);
            btnSelect.isSelect = true;
        }
    }

    public static class mcButton{
        public int x, y, w, h;
        public String Tile;
        public boolean isSelect;

        public mcButton(String tile, int x, int y, int w){
            this.Tile = tile;
            this.x = x;
            this.y = y;
            this.w = w;
            this.h = 25;
            isSelect = false;
        }

        public void paint(mGraphics g){
            int yCenter = this.y + 13;
            int c = w / 12 + (w % 12 == 0? 0: 1);
            for(int i = 0; i < c; ++i) {
                if(i == 0 || i == c-1)
                    continue;
                g.drawRegion(GameScr.imgBoder[5], 0, 25, 12, 25, 0, x + i * 12, yCenter , mGraphics.VCENTER | mGraphics.LEFT, false);
            }

            g.drawRegion(GameScr.imgBoder[5], 0, 0, 12, 25, 0, x, yCenter , mGraphics.VCENTER | mGraphics.LEFT, false);
            int ys = 0;
            if (!mSystem.isj2me) {
                ys = -1;
            }

            g.drawRegion(GameScr.imgBoder[5], 0, 0, 12, 25, 2, x + w, yCenter, mGraphics.VCENTER | mGraphics.RIGHT, false);
            if(isSelect)
                mFont.tahoma_7b_white.drawString(g, this.Tile, x + (w/2), yCenter - 6, mGraphics.VCENTER, false);
            else
                FontTeam.fontTile.drawString(g, this.Tile, x + (w/2), yCenter - 6, mGraphics.VCENTER, false);

        }

        public boolean isPointer(){
            return GameCanvas.isPointer(x, y, w, h, 0);
        }
    }
}
