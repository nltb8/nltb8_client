/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  code.model.Actor
 *  code.model.Effect
 *  code.model.Point
 *  code.screen.Util
 *  code.screen.Utils
 *  code.screen.screen.GameScr
 *  lib.mGraphics
 *  lib.mVector
 */
package mchien.code.model;

import mchien.code.model.Actor;
import mchien.code.model.Effect;
import mchien.code.model.Point;
import mchien.code.screen.Util;
import mchien.code.screen.Utils;
import mchien.code.screen.screen.GameScr;
import lib.mGraphics;
import lib.mVector;

public class Lightning
extends Effect {
    private int toX;
    private int toY;
    int fRemove;
    byte timeAddNum;
    byte type;
    mVector VecEff = new mVector();
    mVector VecSubEff = new mVector();

    public Lightning(Actor target) {
        this.target = target;
        if (target != null) {
            this.toX = target.x;
            this.toY = target.y;
        }
        this.fRemove = 5;
        this.timeAddNum = (byte)7;
        this.createLighting(this.toX + Utils.random_Am_0((int)20), GameScr.cmy - 5, this.toX, this.toY, true);
        this.type = 0;
    }

    public Lightning(Actor target, int type) {
        this.target = target;
        if (target != null) {
            this.toX = target.x;
            this.toY = target.y;
        }
        this.fRemove = 5;
        this.timeAddNum = (byte)7;
        this.createLighting(this.toX + Utils.random_Am_0((int)20), GameScr.cmy - 5, this.toX, this.toY, true);
        this.type = (byte)type;
    }

    public Lightning(Actor From, Actor target, int type) {
        this.target = target;
        if (target != null) {
            this.toX = target.x;
            this.toY = target.y;
        }
        this.fRemove = 5;
        this.timeAddNum = (byte)7;
        this.createLighting(From.x, From.y, this.toX, this.toY, true);
        this.type = (byte)type;
    }

    private void createLighting(int xFrom, int yFrom, int xTo, int yTo, boolean isEnd) {
        int gocmax = 0;
        int gocLight = 0;
        int gocpaint = 0;
        int maxline = Utils.getDistance((int)xFrom, (int)yFrom, (int)xTo, (int)yTo) / 15 + Utils.random((int)3);
        int tRandom = Utils.random((int)2);
        gocLight = Util.angle((int)(xTo - xFrom), (int)(yTo - yFrom));
        int lLight = 20;
        int i = 0;
        while (i < maxline) {
            Point p = new Point();
            if (i == 0) {
                p.x = xFrom * 1000;
                p.y = yFrom * 1000;
            } else {
                Point p2 = (Point)this.VecEff.elementAt(i - 1);
                lLight = 20 + Utils.random_Am_0((int)10);
                int t = Utils.getDistance((int)(p2.x / 1000), (int)(p2.y / 1000), (int)xTo, (int)yTo);
                if ((t < 25 || i == maxline - 1) && isEnd) {
                    p.x = xTo * 1000;
                    p.y = yTo * 1000;
                    this.VecEff.addElement((Object)p);
                    break;
                }
                gocpaint = gocLight;
                gocpaint = isEnd ? (Utils.abs((int)gocmax) > 50 ? (gocmax > 0 ? (gocpaint -= Utils.random((int)5, (int)50)) : (gocpaint += Utils.random((int)5, (int)50))) : (i % 2 == tRandom ? (gocpaint -= Utils.random((int)5, (int)50)) : (gocpaint += Utils.random((int)5, (int)50)))) : (Utils.abs((int)gocmax) > 50 ? (gocmax > 0 ? (gocpaint -= Utils.random((int)5, (int)50)) : (gocpaint += Utils.random((int)5, (int)50))) : (i % 2 == tRandom ? (gocpaint -= Utils.random((int)10, (int)40)) : (gocpaint += Utils.random((int)10, (int)40))));
                gocmax += gocpaint - gocLight;
                gocpaint = Util.fixangle((int)gocpaint);
                p.x = p2.x + Util.cos((int)gocpaint) * lLight;
                p.y = p2.y + Util.sin((int)gocpaint) * lLight;
                if (Utils.random((int)6) < 5) {
                    this.VecSubEff.addElement((Object)p);
                    Point ptam = new Point();
                    ptam = p;
                    int lSmall = 10;
                    int gocSmall = gocpaint;
                    int numSmall = Utils.random((int)3, (int)7);
                    int j = 0;
                    while (j < numSmall) {
                        lSmall = 5 + Utils.random_Am_0((int)3);
                        gocSmall = gocpaint;
                        gocSmall = j % 2 == tRandom ? (gocSmall -= Utils.random((int)10, (int)40)) : (gocSmall += Utils.random((int)10, (int)40));
                        gocSmall = Util.fixangle((int)gocSmall);
                        Point p3 = new Point();
                        p3.x = ptam.x + Util.cos((int)gocSmall) * lSmall;
                        p3.y = ptam.y + Util.sin((int)gocSmall) * lSmall;
                        this.VecSubEff.addElement((Object)p3);
                        ptam = p3;
                        ++j;
                    }
                    ptam.x = -1;
                    this.VecSubEff.addElement((Object)ptam);
                }
            }
            this.VecEff.addElement((Object)p);
            ++i;
        }
    }

    public void paint(mGraphics g) {
        switch (this.type) {
            case 0: {
                Point p2;
                Point p;
                if (this.f % 5 >= 2 && this.f % 5 != 3) break;
                int i = 1;
                while (i < this.VecEff.size()) {
                    p = (Point)this.VecEff.elementAt(i - 1);
                    p2 = (Point)this.VecEff.elementAt(i);
                    g.setColor(-463122);
                    g.drawLine(p.x / 1000, p.y / 1000 - 1, p2.x / 1000, p2.y / 1000 - 1, false);
                    g.drawLine(p.x / 1000, p.y / 1000 + 1, p2.x / 1000, p2.y / 1000 + 1, false);
                    g.drawLine(p.x / 1000, p.y / 1000 + 2, p2.x / 1000, p2.y / 1000 + 2, false);
                    g.setColor(-460760);
                    g.drawLine(p.x / 1000, p.y / 1000, p2.x / 1000, p2.y / 1000, false);
                    ++i;
                }
                i = 1;
                while (i < this.VecSubEff.size()) {
                    p = (Point)this.VecSubEff.elementAt(i - 1);
                    p2 = (Point)this.VecSubEff.elementAt(i);
                    if (p2.x != -1 && p.x != -1) {
                        g.setColor(-460760);
                        g.drawLine(p.x / 1000, p.y / 1000, p2.x / 1000, p2.y / 1000, false);
                        g.drawLine(p.x / 1000, p.y / 1000 + 1, p2.x / 1000, p2.y / 1000 + 1, false);
                    }
                    ++i;
                }
                break;
            }
            case 1: {
                Point p2;
                Point p;
                if (this.f % 5 >= 2 && this.f % 5 != 3) break;
                int i = 1;
                while (i < this.VecEff.size()) {
                    p = (Point)this.VecEff.elementAt(i - 1);
                    p2 = (Point)this.VecEff.elementAt(i);
                    g.setColor(-463122);
                    g.drawLine(p.x / 1000, p.y / 1000 - 1, p2.x / 1000, p2.y / 1000 - 1, false);
                    g.drawLine(p.x / 1000, p.y / 1000 + 1, p2.x / 1000, p2.y / 1000 + 1, false);
                    g.drawLine(p.x / 1000, p.y / 1000 + 2, p2.x / 1000, p2.y / 1000 + 2, false);
                    g.setColor(-513832);
                    g.drawLine(p.x / 1000, p.y / 1000, p2.x / 1000, p2.y / 1000, false);
                    ++i;
                }
                i = 1;
                while (i < this.VecSubEff.size()) {
                    p = (Point)this.VecSubEff.elementAt(i - 1);
                    p2 = (Point)this.VecSubEff.elementAt(i);
                    if (p2.x != -1 && p.x != -1) {
                        g.setColor(-513832);
                        g.drawLine(p.x / 1000, p.y / 1000, p2.x / 1000, p2.y / 1000, false);
                        g.drawLine(p.x / 1000, p.y / 1000 + 1, p2.x / 1000, p2.y / 1000 + 1, false);
                    }
                    ++i;
                }
                break;
            }
        }
    }

    public void update() {
        ++this.f;
        if (this.f >= this.fRemove) {
            this.wantDestroy = true;
        }
    }
}
