package mchien.code.effect;

import mchien.code.effect.new_skill.EffectSkill;
import mchien.code.effect.new_skill.Effect_Sao_chop;
import mchien.code.effect.new_skill.Skill_Boss_BBCT_Nem_Cau;
import mchien.code.effect.new_skill.Skill_Boss_BBCT_Tha_Doi;
import mchien.code.effect.new_skill.Skill_Dao_T4;
import mchien.code.effect.new_skill.Skill_DefaultBoss_BBCT;
import mchien.code.effect.new_skill.Skill_Default_Boss_TruongDoTe;
import mchien.code.main.GameCanvas;
import mchien.code.model.Actor;
import mchien.code.model.BossBienBucCongTu;
import mchien.code.model.Effect;
import mchien.code.model.EffectManager;
import mchien.code.model.Point;
import mchien.code.model.arrow.Arrow;
import mchien.code.screen.Res;
import mchien.code.screen.Util;
import mchien.code.screen.Utils;
import mchien.code.screen.screen.GameScr;
import javax.microedition.lcdui.Image;
import lib.mGraphics;
import lib.mVector;

public class EffectSkillMonster extends Effect {
   private int idImage;
   private int dame;
   private Actor target;
   private Actor from;
   private Actor arrowTarget;
   public int transform;
   public short dx;
   public short dy;
   public short angle;
   public short xto;
   public short yto;
   public short pos;
   public byte frame;
   public byte frame1;
   public byte type;
   public byte effect;
   public byte Position;
   public byte style;
   private int[] xw;
   private int[] yw;
   private int[] Sdame;
   public static final byte CICLE = 0;
   public static final byte EFFECT = 1;
   public static final byte ARROW = 2;
   public static final byte EFF_BOSS_HAT_TUNG = 3;
   public static final byte EFF_BOSS_THA_RAN = 4;
   public static final byte EFF_BOSS_0 = 5;
   public static final byte EFF_BOSS_1 = 6;
   public static final byte EFF_BOSS_2 = 7;
   public static final byte EFF_BOSS_3 = 8;
   public static final byte EFF_BOSS_4 = 9;
   public static final byte EFF_BOSS_5 = 10;
   public static final byte EFF_BOSS_11 = 11;
   public static final byte EFF_BOSS_12 = 12;
   public static final byte EFF_BOSS_13 = 13;
   public static final byte EFF_BOSS_6X1 = 14;
   public static final byte EFF_BOSS_6X2 = 15;
   private mVector VecEff = new mVector();
   private mVector ntarget = new mVector();
   public boolean isJum;
   int x1000;
   int y1000;
   int toX;
   int toY;
   int vMax;
   int timeAddNum;
   int vx;
   int vy;
   int test;

   public EffectSkillMonster(int type, Actor from, mVector target, int[] dame) {
      this.type = (byte)type;
      this.from = from;
      this.ntarget = target;
      Actor ac = null;
      Skill_Default_Boss_TruongDoTe e;
      int i;
      int j;
      switch(type) {
      case 0:
         for(i = 0; i < this.ntarget.size(); ++i) {
            ac = (Actor)this.ntarget.elementAt(i);
            if (ac != null) {
               EffectSkill.createHiEfect(ac.x, ac.y - ac.getHeight() / 2, 30);
               GameCanvas.gameScr.startFlyText("-" + dame[i], 2, ac.x, ac.y - 35, -1, -2);
            }
         }

         this.wantDestroy = true;
      case 1:
      case 2:
      case 3:
      case 4:
      default:
         break;
      case 5:
         for(i = 0; i < this.ntarget.size(); ++i) {
            ac = (Actor)this.ntarget.elementAt(i);
            if (ac != null) {
               GameCanvas.gameScr.StartNewLightning(ac, 1);
               GameCanvas.gameScr.startFlyText("-" + dame[i], 2, ac.x, ac.y - 35, -1, -2);
               EffectSkill.createHiEfect(ac.x, ac.y - ac.getHeight() / 2, 30);
               EffectManager.addLowEffect(ac.x, ac.y, 55);
            }
         }

         this.wantDestroy = true;
         break;
      case 6:
         for(i = 0; i < this.ntarget.size(); ++i) {
            ac = (Actor)this.ntarget.elementAt(i);
            if (ac != null) {
               GameCanvas.gameScr.startNewMagicBeam(17, from, ac, from.x, from.y, dame[i] / 3, (int)157);

               for(j = 0; j < 2; ++j) {
                  GameCanvas.gameScr.startNewMagicBeam(17, from, ac, from.x + Res.random(32, 60), from.y + Res.random(32, 60), dame[i] / 3, (int)157);
               }
            }
         }

         this.wantDestroy = true;
         break;
      case 7:
         for(i = 0; i < this.ntarget.size(); ++i) {
            ac = (Actor)this.ntarget.elementAt(i);
            Skill_Dao_T4 n = new Skill_Dao_T4(from.x, from.y, ac.x, ac.y, i * 5, dame[i]);
            EffectManager.addHiEffect(n);
         }

         this.wantDestroy = true;
         break;
      case 8:
         Skill_Boss_BBCT_Nem_Cau sknc = new Skill_Boss_BBCT_Nem_Cau();
         sknc.type = 119;
         sknc.arrDame = dame;
         sknc.allTarget = this.ntarget;
         sknc.boss = (BossBienBucCongTu)from;
         EffectManager.addHiEffect(sknc);
         this.wantDestroy = true;
         break;
      case 9:
         Skill_Boss_BBCT_Tha_Doi sk = new Skill_Boss_BBCT_Tha_Doi(this.ntarget, (BossBienBucCongTu)from, dame);
         EffectManager.addHiEffect(sk);
         this.wantDestroy = true;
         break;
      case 10:
         for(j = 0; j < this.ntarget.size(); ++j) {
            ac = (Actor)this.ntarget.elementAt(j);
            Skill_DefaultBoss_BBCT ee = new Skill_DefaultBoss_BBCT(from.x, (short)(from.y - from.getHeight()), ac, (byte)96, (byte)45, (byte)-2, (byte)30, (int)(ac.getHeight() * 2 / 3));
            ee.dame = dame[j];
            ee.idEffectAuto = 221;
            ee.setOwner(from);
            EffectManager.addHiEffect(ee);
         }

         this.wantDestroy = true;
         break;
      case 11:
         for(j = 0; j < this.ntarget.size(); ++j) {
            ac = (Actor)this.ntarget.elementAt(j);
            e = new Skill_Default_Boss_TruongDoTe(ac, 235);
            e.dame = dame[j];
            e.setOwner(from);
            EffectManager.addHiEffect(e);
         }

         this.wantDestroy = true;
         break;
      case 12:
         this.wantDestroy = true;
         break;
      case 13:
         for(j = 0; j < this.ntarget.size(); ++j) {
            ac = (Actor)this.ntarget.elementAt(j);
            e = new Skill_Default_Boss_TruongDoTe(ac, 4);
            e.dame = dame[j];
            e.setOwner(from);
            EffectManager.addHiEffect(e);
         }

         this.wantDestroy = true;
         break;
      case 14:
         this.style = -1;
         this.Sdame = dame;
         Actor objBeKillMain = (Actor)target.elementAt(0);
         this.arrowTarget = objBeKillMain;
         if (objBeKillMain != null) {
            this.toX = objBeKillMain.x;
            this.toY = objBeKillMain.y;
         }

         this.y1000 = GameScr.cmy - Utils.random(10, 20);
         this.vMax = 18;
         this.fRemove = 20;
         this.timeAddNum = 11;
         this.create_Arrow_Rain();
         this.f = 0;
         this.fRemove = 10;
         break;
      case 15:
         this.style = -1;
      }

   }

   private void create_Arrow_Rain() {
      if (this.f == -1) {
         this.VecEff.removeAllElements();
      }

      for(int j = 0; j < this.ntarget.size(); ++j) {
         Actor obj = (Actor)this.ntarget.elementAt(j);
         if (obj != null && obj != null) {
            int tAR = Utils.random(1, 4);
            if (GameCanvas.lowGraphic) {
               tAR = Utils.random(1, 3);
            }

            for(int i = 0; i < tAR; ++i) {
               this.vMax = Utils.random(14, 18);
               Point p = new Point();
               p.x = obj.x - 70 + Utils.random_Am_0(15);
               p.y = this.y1000;
               int xdich = obj.x - p.x + Utils.random_Am_0(30);
               int ydich = obj.y - p.y + 8 + Utils.random_Am_0(10);
               if (ydich / this.vMax > 8) {
                  this.vMax = ydich / 8;
               }

               Point pf = new Point();
               pf = this.create_Speed(xdich, ydich, pf);
               p.vx = pf.vx;
               p.vy = pf.vy;
               p.fRe = pf.fRe;
               p.f = 0;
               this.VecEff.addElement(p);
            }
         }
      }

   }

   public Point create_Speed(int xdich, int ydich, Point p) {
      if (ydich == 0) {
         ydich = 1;
      }

      if (xdich == 0) {
         xdich = 1;
      }

//      int vx = false;
//      int vy = false;
      int dis = Utils.getDistance(xdich, ydich) / this.vMax;
      if (dis == 0) {
         dis = 1;
      }

      int vx = xdich / dis;
      int vy = ydich / dis;
      if (Utils.abs(vx) > Utils.abs(xdich)) {
         vx = xdich;
      }

      if (Utils.abs(vy) > Utils.abs(ydich)) {
         vy = ydich;
      }

      if (this.arrowTarget != null) {
         this.toX = this.arrowTarget.x;
         this.toY = this.arrowTarget.y - this.arrowTarget.getHeight() / 2;
      }

      if (p != null) {
         p.vx = vx;
         p.vy = vy;
         p.toX = this.toX;
         p.toY = this.toY;
         p.fRe = dis;
      } else {
         this.fRemove = dis;
         this.vx = vx;
         this.vy = vy;
      }

      return p;
   }

   public EffectSkillMonster(int typeAttack, int idimage, Actor from, Actor target, int dame) {
      this.from = from;
      this.target = target;
      this.dame = dame;
      this.x = from.x;
      this.y = from.y;
      this.xto = target.x;
      int dy = 40;
      if (typeAttack == 2) {
         this.style = 2;
      }

      if (typeAttack == 3) {
         this.style = 0;
      }

      this.yto = (short)(target.y - dy);
      this.idImage = idimage;
      boolean var7;
      if (this.idImage == 103) {
         var7 = false;
      }

      if (this.idImage == 104) {
         var7 = true;
      }

      if (this.idImage == -1) {
         this.style = 0;
      }

      if (this.style == 2) {
         this.Position = 20;
         this.setspeed(this.Position);
      }

   }

   public void setspeed(int Positions) {
      this.dx = (short)(this.xto - this.x);
      this.dy = (short)(this.yto - 0 - this.y);
      this.angle = (short)Util.angle(this.dx, this.dy);
      int nPosition = (Math.abs(this.dx) + Math.abs(this.dy)) / 20;
      if (nPosition < 2) {
         nPosition = 2;
      }

      this.xw = new int[nPosition];
      this.yw = new int[nPosition];

      int d;
      for(d = 0; d < nPosition; ++d) {
         this.xw[d] = this.x + d * this.dx / nPosition;
         this.yw[d] = this.y + d * this.dy / nPosition;
      }

      d = Arrow.findDirIndexFromAngle(Util.angle(this.dx, -this.dy));
      this.frame1 = Arrow.FRAME[d];
      this.transform = Arrow.TRANSFORM[d];
   }

   public void paint(mGraphics g) {
      if (this.type != -1) {
         switch(this.type) {
         case 14:
            for(int i = 0; i < this.VecEff.size(); ++i) {
               Point p = (Point)this.VecEff.elementAt(i);
               if (p != null && !p.isRemove) {
                  if (p.dis == 0) {
                     g.setColor(10549488);
                     g.drawLine(p.x, p.y, p.x + 6, p.y + 8, false);
                     g.drawLine(p.x, p.y, p.x + 5, p.y + 8, false);
                  } else if (p.dis == 1) {
                     int var10000 = p.f;
                  }
               }
            }
         default:
            switch(this.style) {
            case 0:
            case 1:
               Image img1 = EffectSkill.getImage(this.idImage);
               if (img1 != null) {
                  g.drawRegion(img1, 0, this.frame * EffectSkill.getHight(this.idImage), EffectSkill.getWidth(this.idImage), EffectSkill.getHight(this.idImage), this.transform, this.x, this.y, mGraphics.VCENTER | mGraphics.HCENTER, false);
               }
               break;
            case 2:
               Image img = EffectSkill.getImage(this.idImage);
               if (img != null) {
                  g.drawRegion(img, 0, this.frame1 * EffectSkill.getHight(this.idImage), EffectSkill.getWidth(this.idImage), EffectSkill.getHight(this.idImage), this.transform, this.xw[this.pos], this.yw[this.pos], mGraphics.VCENTER | mGraphics.HCENTER, false);
               }
               break;
            case 3:
               for(int i = 0; i < this.VecEff.size(); ++i) {
                  Point p = (Point)this.VecEff.elementAt(i);
                  if (this.f <= this.fRemove) {
//                     int fpaint = false;
                     int partf = 3;
                     int fpaint;
                     if (this.f < 2) {
                        fpaint = this.f;
                     }

                     if (this.f > this.fRemove - 5) {
                        fpaint = this.fRemove - this.f;
                        partf = 5;
                     } else {
                        fpaint = 2;
                     }

                     Image img2 = EffectSkill.getImage(this.idImage);
                     if (img2 != null) {
                        g.drawRegion(img2, 0, this.frame * EffectSkill.getHight(this.idImage), EffectSkill.getWidth(this.idImage), EffectSkill.getHight(this.idImage) / partf * (fpaint + 1), 0, p.x, p.y, mGraphics.BOTTOM | mGraphics.HCENTER, false);
                     }
                  }
               }
            }

         }
      }
   }

   public void onArrowTouchTarget() {
      EffectSkill.createHiEfect(this.xto, this.yto + 10, 30);
   }

   public void update() {
      switch(this.type) {
      case 14:
         ++this.f;

         int i;
         for(i = 0; i < this.VecEff.size(); ++i) {
            Point p = (Point)this.VecEff.elementAt(i);
            if (p.dis == 0) {
               p.update();
               if (p.f >= p.fRe) {
                  p.dis = 1;
                  p.f = 0;
                  if (Utils.random(4) == 0) {
                     Effect_Sao_chop ef = new Effect_Sao_chop(p.x, p.y, 24);
                     EffectManager.addHiEffect(ef);
                  }
               }
            } else if (p.dis == 1) {
               ++p.f;
               if (p.f >= 2 && this.test == 0) {
                  this.VecEff.removeElement(p);
                  --i;
               }
            }
         }

         if (this.f < this.fRemove) {
            if (!GameCanvas.lowGraphic || GameCanvas.gameTick % 2 == 0) {
               this.y1000 = GameScr.cmy - Utils.random(10, 20);
               this.create_Arrow_Rain();
            }
         } else if (this.VecEff.size() == 0) {
            for(i = 0; i < this.ntarget.size(); ++i) {
               Actor ac = (Actor)this.ntarget.elementAt(i);
               if (ac != null) {
                  EffectSkill.createHiEfect(ac.x, ac.y - ac.getHeight() / 2, 30);
                  GameCanvas.gameScr.startFlyText("-" + this.Sdame[i], 2, ac.x, ac.y - 35, -1, -2);
               }
            }

            this.wantDestroy = true;
         }
      case 15:
      default:
         switch(this.style) {
         case 0:
         case 1:
            ++this.frame;
            if (this.frame > EffectSkill.getFrame(this.idImage) - 1) {
               this.frame = 0;
            }

            if (this.target != null) {
               this.xto = this.target.x;
               this.yto = (short)(this.target.y - 20);
            } else {
               this.wantDestroy = true;
            }

            this.dx = (short)(this.xto - this.x);
            this.dy = (short)(this.yto - 0 - this.y);
            this.angle = (short)Util.angle(this.dx, this.dy);
            short vx = (short)(12 * Util.cos(this.angle) >> 10);
            short vy = (short)(12 * Util.sin(this.angle) >> 10);
            this.x += vx;
            this.y += vy;
            short dx1 = (short)Math.abs(this.x - this.xto);
            short dy1 = (short)Math.abs(this.y - this.yto);
            boolean isMatchX = false;
            boolean isMatchY = false;
            int disx = Utils.getDistance(this.x, this.y, this.xto, this.yto);
            if (dx1 <= vx) {
               this.x = this.xto;
               isMatchX = true;
            }

            if (dy1 <= vy) {
               this.y = this.yto;
               isMatchY = true;
            }

            if (isMatchX && isMatchY || disx < 20) {
               this.wantDestroy = true;
               GameCanvas.gameScr.startFlyText("-" + this.dame, 2, this.xto + 0, this.yto - 15, -1, -2);
               if (this.target != null) {
                  this.onArrowTouchTarget();
                  if (GameScr.r.nextInt(11) < 12) {
                     this.target.jum();
                  }
               }
            }

            if (this.idImage == -1 && GameCanvas.gameTick % 3 == 0) {
               EffectManager.addLowDataeffectSkill(4, this.x, this.y + 3, 1);
            }

            if (this.style == 1) {
               EffectSkill.createHiEfect(this.x, this.y - 20, this.idImage);
            }
            break;
         case 2:
            if (this.pos < this.xw.length) {
               ++this.pos;
            }

            if (this.pos >= this.xw.length) {
               this.pos = (short)(this.xw.length - 1);
               this.xw[this.pos] = this.xto;
               this.yw[this.pos] = this.yto;
               this.wantDestroy = true;
               this.onArrowTouchTarget();
               if (this.target != null) {
                  this.target.jum();
               }

               GameCanvas.gameScr.startFlyText("-" + this.dame, 2, this.xto + 0, this.yto - 15, -1, -2);
            }
            break;
         case 3:
            ++this.f;

            for(int k = 0; k < this.VecEff.size(); ++k) {
               Point p = (Point)this.VecEff.elementAt(k);
               if (this.f == 1) {
                  GameScr.timeVibrateScreen = 103;
               }

               ++p.f;
            }

            if (this.f >= this.fRemove) {
               this.wantDestroy = true;
            }
         }

      }
   }
}
