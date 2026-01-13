package mchien.code.screen.screen;

import mchien.code.main.GameCanvas;
import mchien.code.model.IActionListener;
import mchien.code.model.MainTeam;
import lib.mGraphics;

public abstract class Dialog extends MainTeam implements IActionListener {
   public void paint(mGraphics g) {
      super.paint(g);
   }

   public void keyPress(int keyCode) {
   }

   public void update() {
      super.updateKey();
   }

   public void show() {
      GameCanvas.currentDialog = this;
   }
}
