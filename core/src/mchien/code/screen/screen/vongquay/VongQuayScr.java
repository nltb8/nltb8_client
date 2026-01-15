package mchien.code.screen.screen.vongquay;

import javax.microedition.lcdui.Image;

import lib.mGraphics;
import mchien.code.main.GameCanvas;
import mchien.code.model.IActionListener;
import mchien.code.model.mCommand;
import mchien.code.network.GameService;
import mchien.code.screen.Util;
import mchien.code.screen.screen.*;
import mchien.code.model.ImageIcon;
import mchien.code.screen.Res;

public class VongQuayScr extends ScreenTeam implements IActionListener {

    private static VongQuayScr instance;
    public static VongQuayScr gI() {
        if (instance == null) instance = new VongQuayScr();
        return instance;
    }

    // ===== Paths =====
    private static final String PATH_BG_FULL   = "/hd/vq/bg_full.png";   // nền full / khung lớn
    private static final String PATH_LEFT      = "/hd/vq/panel_left.png"; // cột trái (optional)

    private static final String PATH_WHEEL     = "/hd/vq/wheel.png";     // vòng quay (xoay)
    private static final String PATH_RING      = "/hd/vq/ring.png";      // viền overlay (đứng yên)
    private static final String PATH_POINTER   = "/hd/vq/pointer.png";   // kim (đứng yên)
    private static final String PATH_CENTER    = "/hd/vq/center.png";    // nút giữa (đứng yên)

    private static final String PATH_Q         = "/hd/vq/q.png";         // placeholder slot
    private static final String PATH_HL        = "/hd/vq/hl.png";        // glow highlight (optional)

    private static final String PATH_BTN1_0    = "/hd/vq/btn_quay1_0.png";
    private static final String PATH_BTN1_1    = "/hd/vq/btn_quay1_1.png";
    private static final String PATH_BTN10_0   = "/hd/vq/btn_quay10_0.png";
    private static final String PATH_BTN10_1   = "/hd/vq/btn_quay10_1.png";
    private static final String PATH_CLOSE_0 = "/hd/vq/close_0.png"; // nút X thường
    private static final String PATH_CLOSE_1 = "/hd/vq/close_1.png"; // hover/nhấn (optional)

    // ===== Images =====
    private static Image imgBgFull, imgLeft;
    private static Image imgWheel, imgRing, imgPointer, imgCenter;
    private static Image imgQ, imgHL;
    private static Image imgBtn1_0, imgBtn1_1, imgBtn10_0, imgBtn10_1;
    private static Image imgClose0, imgClose1;


    // ===== Wheel config =====
    private final int SLOT = 10; // MU style thường 10-12, cho đẹp
    private final int[] sx = new int[SLOT];
    private final int[] sy = new int[SLOT];
    private final double[] baseAng = new double[SLOT];

    // ===== Layout (MU-style) =====
    private int bgX, bgY, bgW, bgH;
    private int closeX, closeY, closeW = 18, closeH = 18;
    private boolean closeFocus;

    // left panel
    private int leftX, leftY, leftW, leftH;

    // wheel area center
    private int cx, cy;
    private int radius;

    // pointer top
    private int pointerX, pointerY;
    private double pointerAngle = -Math.PI / 2; // KIM TRÊN ĐỈNH (quan trọng)

    // center button
    private int centerX, centerY, centerW, centerH;
    private boolean centerFocus;

    // buttons bottom (quay 1 / quay 10)
    private int btn1X, btn1Y, btn1W, btn1H;
    private int btn10X, btn10Y, btn10W, btn10H;
    private boolean btn1Focus, btn10Focus;

    // ===== Spin state =====
    private boolean spinning;
    private boolean waitingServer;

    private double rot;
    private double omega;
    private double omegaMin = 0.010;
    private double stopAngle = Double.NaN;

    // server result
    private boolean hasResult;
    private byte winSlot = -1;      // 0..SLOT-1
    private short winIconId = -1;
    private short winQuantity = 0;

    // highlight current slot under pointer
    private int pointerSlot = 0;

    private VongQuayScr() {
        this.right = new mCommand("Đóng", this, 1);
        this.center = new mCommand("Quay", this, 2);
        this.left = null;
    }

    private void loadImgOnce() {
        if (imgBgFull == null) { try { imgBgFull = GameCanvas.loadImage(PATH_BG_FULL); } catch (Throwable t) { imgBgFull = null; } }
        if (imgLeft == null)   { try { imgLeft = GameCanvas.loadImage(PATH_LEFT); } catch (Throwable t) { imgLeft = null; } }

        if (imgWheel == null)  imgWheel = GameCanvas.loadImage(PATH_WHEEL);
        if (imgRing == null)   { try { imgRing = GameCanvas.loadImage(PATH_RING); } catch (Throwable t) { imgRing = null; } }
        if (imgPointer == null) imgPointer = GameCanvas.loadImage(PATH_POINTER);
        if (imgCenter == null)  imgCenter = GameCanvas.loadImage(PATH_CENTER);

        if (imgQ == null) imgQ = GameCanvas.loadImage(PATH_Q);
        if (imgHL == null) { try { imgHL = GameCanvas.loadImage(PATH_HL); } catch (Throwable t) { imgHL = null; } }

        if (imgBtn1_0 == null) imgBtn1_0 = GameCanvas.loadImage(PATH_BTN1_0);
        if (imgBtn1_1 == null) { try { imgBtn1_1 = GameCanvas.loadImage(PATH_BTN1_1); } catch (Throwable t) { imgBtn1_1 = null; } }

        if (imgBtn10_0 == null) imgBtn10_0 = GameCanvas.loadImage(PATH_BTN10_0);
        if (imgBtn10_1 == null) { try { imgBtn10_1 = GameCanvas.loadImage(PATH_BTN10_1); } catch (Throwable t) { imgBtn10_1 = null; } }

        // button sizes
        if (imgBtn1_0 != null) { btn1W = imgBtn1_0.getWidth(); btn1H = imgBtn1_0.getHeight(); }
        if (imgBtn10_0 != null){ btn10W = imgBtn10_0.getWidth(); btn10H = imgBtn10_0.getHeight(); }

        if (imgCenter != null) { centerW = imgCenter.getWidth(); centerH = imgCenter.getHeight(); }
        if (imgClose0 == null) imgClose0 = GameCanvas.loadImage(PATH_CLOSE_0);
        if (imgClose1 == null) { try { imgClose1 = GameCanvas.loadImage(PATH_CLOSE_1); } catch (Throwable t) { imgClose1 = null; } }

        if (imgClose0 != null) {
            closeW = imgClose0.getWidth();
            closeH = imgClose0.getHeight();
        }

    }

    @Override
    public void switchToMe(ScreenTeam lastSCR) {
        super.switchToMe(lastSCR);
        loadImgOnce();
        initSlotAngles();
        layoutMuStyle();
        resetState();
    }

    private void initSlotAngles() {
        // slot đều quanh vòng, start -90° cho slot 0 ở đỉnh
        double startAngle = -Math.PI / 2;
        double step = (Math.PI * 2) / SLOT;
        for (int i = 0; i < SLOT; i++) baseAng[i] = startAngle + i * step;
    }

    /**
     * Layout MU-style:
     * - Nền full
     * - Cột trái (optional)
     * - Vòng quay lớn ở giữa-phải
     * - Kim ở trên đỉnh vòng
     * - Nút center nằm giữa vòng
     * - Nút quay 1 / quay 10 ở dưới vòng
     */
    private void layoutMuStyle() {
        bgX = GameCanvas.w / 2 - 55 - 50;
        bgY = GameCanvas.h /2 - 108;
        bgW = GameCanvas.w;
        bgH = GameCanvas.h;

        // left panel
        if (imgLeft != null) {
            leftW = imgLeft.getWidth();
            leftH = imgLeft.getHeight();
            leftX = 10;
            leftY = (GameCanvas.h - leftH) / 2;
        } else {
            leftW = 0;
        }
        // nút X ở góc trên phải của BG

        closeX = GameCanvas.w / 2 + 90;
        closeY = GameCanvas.h /2 - 128;

        // wheel center: ưu tiên nằm giữa, nhưng chừa chỗ panel trái
        int safeLeft = leftW > 0 ? (leftX + leftW + 10) : 10;
        int safeRight = GameCanvas.w - 10;

        int wheelAreaW = safeRight - safeLeft;
        int wheelAreaH = GameCanvas.h - 20;

        // tâm vòng
        cx = safeLeft + (int)(wheelAreaW * 0.60f) - 50;
        cy = 10 + (int)(wheelAreaH * 0.46f);

        // radius theo kích thước wheel image nếu có
        if (imgWheel != null) {
            radius = imgWheel.getWidth() / 2;
        } else {
            radius = (int)(Math.min(wheelAreaW, wheelAreaH) * 0.32f);
        }

        // nếu radius quá to so với màn => clamp
        int maxR = (int)(Math.min(wheelAreaW, wheelAreaH) * 0.38f);
        if (radius > maxR) radius = maxR;
        if (radius < 80) radius = 80;

        // center button
        centerX = 28;
        centerY = 28;

        // pointer top position (kim ở trên vòng)
        int px = cx + (int)(Math.cos(pointerAngle) * radius);
        int py = cy + (int)(Math.sin(pointerAngle) * radius);

        int pw = imgPointer != null ? imgPointer.getWidth() : 20;
        int ph = imgPointer != null ? imgPointer.getHeight() : 20;
        pointerX = px - pw / 2;
        pointerY = py - ph + 8; // đẩy kim xuống chút cho "cắm" vào vòng

        // buttons bottom
        int gap = 10;
        int totalW = btn1W + gap + btn10W;
        int startX = cx - totalW / 2;
        int yBtn = cy + radius + 14; // dưới vòng

        // clamp nếu xuống quá đáy
        if (yBtn + Math.max(btn1H, btn10H) > GameCanvas.h - 6) {
            yBtn = GameCanvas.h - Math.max(btn1H, btn10H) - 6;
        }

        btn1X = startX;
        btn1Y = yBtn;

        btn10X = startX + btn1W + gap;
        btn10Y = yBtn;
    }

    private void resetState() {
        spinning = false;
        waitingServer = false;

        rot = 0;
        omega = 0.28;
        stopAngle = Double.NaN;

        hasResult = false;
        winSlot = -1;
        winIconId = -1;
        winQuantity = 0;

        updateSlotPositions();
    }

    private void updateSlotPositions() {
        // slot nằm quanh vòng ở bán kính nhỏ hơn chút để đẹp (bám vào wheel)
        int slotR = (int)(radius * 0.78f);
        for (int i = 0; i < SLOT; i++) {
            double a = baseAng[i] + rot;
            sx[i] = cx + (int)(Math.cos(a) * slotR);
            sy[i] = cy + (int)(Math.sin(a) * slotR);
        }
        pointerSlot = findPointerSlot();
    }

    private int findPointerSlot() {
        int best = 0;
        double bestDiff = 999;
        double p = norm2pi(pointerAngle);

        for (int i = 0; i < SLOT; i++) {
            double a = norm2pi(baseAng[i] + rot);
            double diff = Math.abs(a - p);
            if (diff > Math.PI) diff = Math.PI * 2 - diff;
            if (diff < bestDiff) { bestDiff = diff; best = i; }
        }
        return best;
    }

    @Override
    public void update() {
        if (this.lastSCR != null) this.lastSCR.update();
        else if (GameCanvas.gameScr != null) GameCanvas.gameScr.update();

        super.update();
        if (!spinning) return;

        // chờ server: quay đều nhanh
        if (Double.isNaN(stopAngle)) {
            rot += omega;
            if (omega < 0.22) omega = 0.22;
            updateSlotPositions();
            return;
        }

        // có stopAngle: hãm dần
        double remain = stopAngle - rot;
        if (remain <= 0.001) {
            rot = stopAngle;
            spinning = false;
            omega = 0;
            updateSlotPositions();
            return;
        }

        double k = 0.10;
        double targetOmega = remain * k;
        if (targetOmega < omegaMin) targetOmega = omegaMin;
        if (targetOmega > omega) targetOmega = omega;

        omega = targetOmega;
        rot += omega;
        updateSlotPositions();
    }

    @Override
    public void updateKey() {
        // click nửa trái => đóng (tuỳ bạn bỏ)
//        if (GameCanvas.isPointerJustRelease[0] && GameCanvas.isPointerClick[0]) {
//            if (GameCanvas.px[0] < GameCanvas.w / 2) {
//                consumePointer();
//                closeMe();
//                return;
//            }
//        }

        // hover
        centerFocus = isPointerIn(centerX, centerY, centerW, centerH);
        btn1Focus = isPointerIn(btn1X, btn1Y, btn1W, btn1H);
        btn10Focus = isPointerIn(btn10X, btn10Y, btn10W, btn10H);
        closeFocus = isPointerIn(closeX, closeY, closeW, closeH);
        // click
        if (closeFocus && GameCanvas.isPointerJustRelease[0] && GameCanvas.isPointerClick[0]) {
            consumePointer();
            closeMe();
            return;
        }
        if (GameCanvas.isPointerJustRelease[0] && GameCanvas.isPointerClick[0]) {
            int px = GameCanvas.px[0];
            int py = GameCanvas.py[0];

            // center spin (QUAY NGAY)
            centerX = bgX / 2;
            centerY = bgY / 2;
            if (centerW > 0 && px >= centerX && px <= centerX + centerW && py >= centerY && py <= centerY + centerH) {
                consumePointer();
                onPressSpin(1); // center default quay 1 (bạn thích thì đổi)
                return;
            }

            // quay 1
            if (px >= btn1X && px <= btn1X + btn1W && py >= btn1Y && py <= btn1Y + btn1H) {
                consumePointer();
                onPressSpin(1);
                return;
            }

            // quay 10
            if (px >= btn10X && px <= btn10X + btn10W && py >= btn10Y && py <= btn10Y + btn10H) {
                consumePointer();
                onPressSpin(10);
                return;
            }
        }

        super.updateKey();
    }

    private boolean isPointerIn(int x, int y, int w, int h) {
        return GameCanvas.isPointer(x, y, w, h, 0);
    }

    private void consumePointer() {
        GameCanvas.isPointerClick[0] = false;
        GameCanvas.isPointerJustRelease[0] = false;
    }

    private void onPressSpin(int count) {
        if (spinning || waitingServer) return;

        waitingServer = true;
        startSpinAnimOnly();

        // ✅ bạn map sang cmd thật của bạn
        // Nếu bạn chỉ có requestSpin() thì tự tách:
        // if (count==1) requestSpin(); else requestSpin10();
        GameService.gI().requestSpin(count);
    }

    private void startSpinAnimOnly() {
        spinning = true;
        hasResult = false;
        winSlot = -1;
        winIconId = -1;
        winQuantity = 0;

        omega = 0.28;
        stopAngle = Double.NaN;

        updateSlotPositions();
    }

    // Server callback
    public void onResult(byte slotIndex, short idIcon, short quantity) {
        waitingServer = false;

        this.hasResult = true;
        this.winSlot = slotIndex;
        this.winIconId = idIcon;
        this.winQuantity = quantity;

        // dừng sao cho winSlot nằm đúng dưới kim (trên đỉnh)
        double desiredRot = norm2pi(pointerAngle - baseAng[winSlot]);
        double curRot = norm2pi(rot);
        double delta = desiredRot - curRot;
        if (delta < 0) delta += Math.PI * 2;

        stopAngle = rot + Math.PI * 2 * 2 + delta;
    }

    private double norm2pi(double a) {
        double twoPi = Math.PI * 2;
        a %= twoPi;
        if (a < 0) a += twoPi;
        return a;
    }

    private void closeMe() {
        if (this.lastSCR != null) this.lastSCR.switchToMe(this);
        else GameCanvas.gameScr.switchToMe(this);
    }

    @Override
    public void paint(mGraphics g) {
        if (this.lastSCR != null) this.lastSCR.paint(g);
        GameCanvas.resetTrans(g);

        // 1) BG full
        if (imgBgFull != null) g.drawImage(imgBgFull, bgX, bgY, 0, false);

        // 2) left panel (optional)
        if (imgLeft != null) g.drawImage(imgLeft, leftX, leftY, 0, false);

        // 3) wheel (xoay)
        if (imgWheel != null) {
            // Nếu lib/mGraphics của bạn không hỗ trợ rotate image,
            // wheel.png nên là “vòng nền đứng yên”, còn slot mới là thứ tạo cảm giác quay.
            // => Ở đây vẫn vẽ wheel đứng yên để đẹp.
            g.drawImage(imgWheel, cx, cy, 3, false);
        }

        // 4) slot items (chạy quanh)
        int hlIndex = pointerSlot;
        for (int i = 0; i < SLOT; i++) {
            int x = sx[i];
            int y = sy[i];

            if (imgHL != null && i == hlIndex) {
                g.drawImage(imgHL, x, y, 3, false);
            }

            // dừng rồi hiện icon trúng ở ô winSlot
            if (!spinning && hasResult && i == winSlot && winIconId >= 0) {
                paintIconByIdIcon(g, winIconId, winQuantity, x, y, 3);
            } else {
                if (imgQ != null) g.drawImage(imgQ, x, y, 3, false);
                else FontTeam.normalFont[0].drawString(g, "?", x, y, 2, false);
            }
        }

        // 5) ring overlay (đứng yên)
        if (imgRing != null) g.drawImage(imgRing, cx, cy, 3, false);

        // 6) center button (đứng yên)
        if (imgCenter != null) g.drawImage(imgCenter, centerX, centerY, 0, false);

        // 7) pointer on top
        if (imgPointer != null) g.drawImage(imgPointer, pointerX, pointerY, 0, false);

        // 8) buttons bottom (quay 1 / quay 10)
        Image b1 = (btn1Focus && imgBtn1_1 != null) ? imgBtn1_1 : imgBtn1_0;
        if (b1 != null) g.drawImage(b1, btn1X, btn1Y, 0, false);

        Image b10 = (btn10Focus && imgBtn10_1 != null) ? imgBtn10_1 : imgBtn10_0;
        if (b10 != null) g.drawImage(b10, btn10X, btn10Y, 0, false);

        // 9) status text
        if (waitingServer) {
            FontTeam.normalFont[0].drawString(g, "Đang kiểm tra vé...", cx - 60, btn1Y - 18, 0, false);
        }
        Image c = (closeFocus && imgClose1 != null) ? imgClose1 : imgClose0;
        if (c != null) g.drawImage(c, closeX, closeY, 0, false);

    }

    private void paintIconByIdIcon(mGraphics g, short idIcon, short qty, int x, int y, int anchor) {
        ImageIcon img = GameData.getImgIcon((short)(idIcon + Res.ID_ITEM));
        if (img != null && img.img != null) {
            g.drawImage(img.img, x, y, anchor, false);

            if (qty > 1) {
                int ds = 13;
                FontTeam.numberSmall_yeallow.drawString(
                        g,
                        Util.getKMBNumber((long)qty),
                        x + ds - 3,
                        y + ds - FontTeam.numberSmall_yeallow.getHeight(),
                        1,
                        false
                );
            }
        } else {
            g.drawRegion(GameScr.imgloading,
                    0,
                    GameCanvas.gameTick % 12 * ChangScr.himg,
                    ChangScr.wimg,
                    ChangScr.himg,
                    0,
                    x,
                    y,
                    anchor,
                    false
            );
        }
    }

    @Override
    public void perform(int idAction, Object p) {
        if (idAction == 1) closeMe();
    }

    @Override
    public void paint(mGraphics g, int x, int y, int w, Object p) { }
}
