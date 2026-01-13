package mchien.code.effect.new_skill;

import mchien.code.model.Actor;
import mchien.code.model.Effect;
import lib.mVector;

public class Skill_Bua_T7 extends Effect {
   mVector target = new mVector();

   public void setTarget(mVector target) {
      this.target = target;
   }

   public void update() {
      if (this.target.size() > 0) {
         if (this.target.size() > 1) {
            for(int i = 0; i < this.target.size(); ++i) {
               Actor ac = (Actor)this.target.elementAt(i);
               Effect_no.createHiEffect(ac.x, ac.y, 74);
            }
         } else if (this.target.size() == 1) {
            Actor ac = (Actor)this.target.elementAt(0);
            Effect_no.createHiEffect(ac.x, ac.y, 74);
         }
      }

      this.wantDestroy = true;
   }
}
