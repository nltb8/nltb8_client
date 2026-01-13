/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  code.main.GameCanvas
 *  code.model.mCommand
 *  code.screen.Util
 *  lib.mVector
 */
package mchien.code.model;

import mchien.code.main.GameCanvas;
import mchien.code.model.mCommand;
import mchien.code.screen.Util;
import lib.mVector;

public class ListNew {
    public int maxW;
    public int itemH;
    public int maxH;
    public int maxSize;
    public int x;
    public int y;
    public int value;
    public int cmtoX;
    public int cmx;
    public int cmdy;
    public int cmvy;
    public int cmxLim;
    private int pointerDownTime;
    private int pointerDownFirstX;
    private int[] pointerDownLastX = new int[3];
    private boolean pointerIsDowning;
    private boolean isDownWhenRunning;
    private int cmRun;
    mVector vecCmd;
    mCommand cmdCenter = null;
    public int w;
    int pa = 0;
    boolean trans = false;
    int cmvx;
    int cmdx;

    public ListNew(int x, int y, int maxW, int maxH, int itemH, int maxSize, int limX) {
        this.x = x;
        this.y = y;
        this.maxW = maxW;
        this.maxH = maxH;
        this.itemH = itemH;
        this.maxSize = maxSize;
        this.cmxLim = limX;
    }

    public void updateMenuKey() {
        boolean changeFocus = false;
        if (GameCanvas.keyPressedz[2]) {
            changeFocus = true;
            --this.value;
            if (this.value < 0) {
                this.value = this.maxSize - 1;
            }
            GameCanvas.clearKeyPressed((int)2);
            GameCanvas.clearKeyPressed((int)4);
        } else if (GameCanvas.keyPressedz[8]) {
            changeFocus = true;
            ++this.value;
            if (this.value > this.maxSize - 1) {
                this.value = this.maxSize - 1;
            }
            GameCanvas.clearKeyPressed((int)6);
            GameCanvas.clearKeyPressed((int)8);
        }
        if (changeFocus) {
            this.cmtoX = (this.value + 1) * this.itemH - this.maxH / 2;
            if (this.cmtoX > this.cmxLim) {
                this.cmtoX = this.cmxLim;
            }
            if (this.cmtoX < 0) {
                this.cmtoX = 0;
            }
            if (this.value == this.maxSize - 1 || this.value == 0) {
                this.cmx = this.cmtoX;
            }
        }
        this.update_Pos_UP_DOWN();
    }

    public void update_Pos_UP_DOWN() {
        int dx;
        if (GameCanvas.isPointerDown[0]) {
            if (!this.pointerIsDowning && GameCanvas.isPointer((int)this.x, (int)this.y, (int)this.maxW, (int)this.maxH, (int)0)) {
                int i = 0;
                while (i < this.pointerDownLastX.length) {
                    this.pointerDownLastX[0] = GameCanvas.py[0];
                    ++i;
                }
                this.pointerDownFirstX = GameCanvas.py[0];
                this.pointerIsDowning = true;
                this.isDownWhenRunning = this.cmRun != 0;
                this.cmRun = 0;
            } else if (this.pointerIsDowning) {
                ++this.pointerDownTime;
                if (this.pointerDownTime > 5 && this.pointerDownFirstX == GameCanvas.py[0] && !this.isDownWhenRunning) {
                    this.pointerDownFirstX = -1000;
                }
                if ((dx = GameCanvas.py[0] - this.pointerDownLastX[0]) != 0 && this.value != -1) {
                    this.value = -1;
                }
                int i = this.pointerDownLastX.length - 1;
                while (i > 0) {
                    this.pointerDownLastX[i] = this.pointerDownLastX[i - 1];
                    --i;
                }
                this.pointerDownLastX[0] = GameCanvas.py[0];
                this.cmtoX -= dx;
                if (this.cmtoX < 0) {
                    this.cmtoX = 0;
                }
                if (this.cmtoX > this.cmxLim) {
                    this.cmtoX = this.cmxLim;
                }
                if (this.cmx < 0 || this.cmx > this.cmxLim) {
                    dx /= 2;
                }
                this.cmx -= dx;
            }
        }
        if (GameCanvas.isPointerClick[0] && this.pointerIsDowning) {
            dx = GameCanvas.py[0] - this.pointerDownLastX[0];
            GameCanvas.isPointerClick[0] = false;
            if (Util.abs((int)dx) < 20 && Util.abs((int)(GameCanvas.py[0] - this.pointerDownFirstX)) < 20 && !this.isDownWhenRunning && GameCanvas.isPointerClick[0]) {
                this.cmRun = 0;
                this.cmtoX = this.cmx;
                this.pointerDownFirstX = -1000;
                this.pointerDownTime = 0;
            } else if (this.value != -1 && this.pointerDownTime > 5) {
                this.pointerDownTime = 0;
            } else if (this.value == -1 && !this.isDownWhenRunning) {
                if (this.cmx < 0) {
                    this.cmtoX = 0;
                } else if (this.cmx > this.cmxLim) {
                    this.cmtoX = this.cmxLim;
                } else {
                    int s = GameCanvas.py[0] - this.pointerDownLastX[0] + (this.pointerDownLastX[0] - this.pointerDownLastX[1]) + (this.pointerDownLastX[1] - this.pointerDownLastX[2]);
                    s = s > 10 ? 10 : (s < -10 ? -10 : 0);
                    this.cmRun = -s * 100;
                }
            }
            this.pointerIsDowning = false;
            this.pointerDownTime = 0;
            GameCanvas.isPointerClick[0] = false;
        }
    }

    public void updatePos_LEFT_RIGHT() {
        int dx;
        if (GameCanvas.isPointerDown[0]) {
            if (!this.pointerIsDowning && GameCanvas.isPointer((int)this.x, (int)this.y, (int)this.maxW, (int)this.maxH, (int)0)) {
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
                }
                if ((dx = GameCanvas.px[0] - this.pointerDownLastX[0]) != 0 && this.value != -1) {
                    this.value = -1;
                }
                int i = this.pointerDownLastX.length - 1;
                while (i > 0) {
                    this.pointerDownLastX[i] = this.pointerDownLastX[i - 1];
                    --i;
                }
                this.pointerDownLastX[0] = GameCanvas.px[0];
                this.cmtoX -= dx;
                if (this.cmtoX < 0) {
                    this.cmtoX = 0;
                }
                if (this.cmtoX > this.cmxLim) {
                    this.cmtoX = this.cmxLim;
                }
                if (this.cmx < 0 || this.cmx > this.cmxLim) {
                    dx /= 2;
                }
                this.cmx -= dx;
            }
        }
        if (GameCanvas.isPointerClick[0] && this.pointerIsDowning) {
            dx = GameCanvas.px[0] - this.pointerDownLastX[0];
            GameCanvas.isPointerClick[0] = false;
            if (Util.abs((int)dx) < 20 && Util.abs((int)(GameCanvas.px[0] - this.pointerDownFirstX)) < 20 && !this.isDownWhenRunning && GameCanvas.isPointerClick[0]) {
                this.cmRun = 0;
                this.cmtoX = this.cmx;
                this.pointerDownFirstX = -1000;
                this.pointerDownTime = 0;
            } else if (this.value != -1 && this.pointerDownTime > 5) {
                this.pointerDownTime = 0;
            } else if (this.value == -1 && !this.isDownWhenRunning) {
                if (this.cmx < 0) {
                    this.cmtoX = 0;
                } else if (this.cmx > this.cmxLim) {
                    this.cmtoX = this.cmxLim;
                } else {
                    int s = GameCanvas.px[0] - this.pointerDownLastX[0] + (this.pointerDownLastX[0] - this.pointerDownLastX[1]) + (this.pointerDownLastX[1] - this.pointerDownLastX[2]);
                    s = s > 10 ? 10 : (s < -10 ? -10 : 0);
                    this.cmRun = -s * 100;
                }
            }
            this.pointerIsDowning = false;
            this.pointerDownTime = 0;
            GameCanvas.isPointerClick[0] = false;
        }
    }

    public void moveCamera() {
        if (this.cmRun != 0 && !this.pointerIsDowning) {
            this.cmtoX += this.cmRun / 100;
            if (this.cmtoX < 0) {
                this.cmtoX = 0;
            } else if (this.cmtoX > this.cmxLim) {
                this.cmtoX = this.cmxLim;
            } else {
                this.cmx = this.cmtoX;
            }
            this.cmRun = this.cmRun * 9 / 10;
            if (this.cmRun < 100 && this.cmRun > -100) {
                this.cmRun = 0;
            }
        }
        if (this.cmx != this.cmtoX && !this.pointerIsDowning) {
            this.cmvx = this.cmtoX - this.cmx << 2;
            this.cmdx += this.cmvx;
            this.cmx += this.cmdx >> 4;
            this.cmdx &= 0xF;
        }
    }

    public void updateMenu() {
        this.moveCamera();
        this.updateMenuKey();
    }
}
