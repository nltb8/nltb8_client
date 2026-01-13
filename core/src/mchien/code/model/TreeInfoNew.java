package mchien.code.model;

import mchien.code.screen.Res;
import mchien.code.screen.screen.GameData;
import mchien.code.screen.screen.GameScr;
import lib.mGraphics;

public class TreeInfoNew {
   public short dx;
   public short dy;

   public TreeInfoNew(int dx, int dy) {
      this.dx = (short)dx;
      this.dy = (short)dy;
   }

   public void paint(mGraphics g, int x, int y, int id) {
      ImageIcon img = GameData.getImgIcon((short)(id + Res.ID_ITEM_MAP));

      try {
         if (img != null && img.img != null) {
            if (GameScr.isInScreen(x, y, mGraphics.getImageWidth(img.img), mGraphics.getImageHeight(img.img))) {
               g.drawImage(img.img, x, y, 0, false);
            }

            Res.maxHTree = img.img.getHeight() > Res.maxHTree ? img.img.getHeight() : Res.maxHTree;
            Res.maxWTree = img.img.getWidth() > Res.maxWTree ? img.img.getWidth() : Res.maxWTree;
         }
      } catch (Exception var7) {
         var7.printStackTrace();
      }

   }
}
