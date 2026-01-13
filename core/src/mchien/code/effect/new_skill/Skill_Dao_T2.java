package mchien.code.effect.new_skill;

import mchien.code.main.GameCanvas;
import mchien.code.model.Actor;
import mchien.code.model.Char;
import mchien.code.model.Effect;
import mchien.code.model.EffectManager;
import mchien.code.screen.Util;
import lib.mVector;

public class Skill_Dao_T2 extends Effect {
   Char c;
   mVector mst = new mVector();
   short radian;
   short[] xx = new short[]{-10, 10, 0};
   short[] yy = new short[]{-10, 10, 0};

   public Skill_Dao_T2(Char c, mVector mst) {
      this.c = c;
      this.mst = mst;
   }

   public void update() {
      EffectSkill.createLowEfect(Util.cos(this.radian) * 30 / 1024 + this.c.x, Util.sin(this.radian) * 30 / 1024 + this.c.y - 15, 73);
      this.radian = (short)(this.radian + 45);
      if (this.radian >= 360) {
         this.radian = 0;

         for(int i = 0; i < this.mst.size(); ++i) {
            Actor mt = (Actor)this.mst.elementAt(i);
            Skill_AEO_DUA_DAO_5 skill = new Skill_AEO_DUA_DAO_5(this.c.x + this.xx[GameCanvas.random(0, this.xx.length)], this.c.y + this.yy[GameCanvas.random(0, this.yy.length)], this.c, mt, (byte)3);
            EffectManager.addHiEffect(skill);
         }

         this.wantDestroy = true;
      }

   }
}
