package mchien.code.model;

import mchien.code.main.GameCanvas;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import javax.microedition.lcdui.Image;
import lib.Cout;
import lib.mGraphics;
import lib.mSystem;
import lib.mVector;

public class DataEffect {
   public mVector listFrame = new mVector();
   public mVector listAnima = new mVector();
   public SmallImage[] smallImage;
   public byte[] sequence;
   private short FrameWith;
   private short FrameHeight;
   private short idimg;
   public String name = "";
   public byte isFly = 0;
   public byte idShadow = 0;
   public static byte[][] indexAction = new byte[][]{{0, 0, 1, 2, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {4, 4, 5, 6, 7, 5, 5, 5, 5, 5, 5, 5, 5, 5}, {8, 8, 9, 10, 11, 9, 9, 9, 9, 9, 9, 9, 9, 9}};

   public DataEffect(byte[] array, int idimg, boolean isMonster) {
      this.idimg = (short)idimg;
      this.setDataType1(array);
   }

   public DataEffect(byte[] array) {
      this.setDataType1(array);
   }

   public void setDataType1(byte[] array) {
      short len = 0;
      DataInputStream dis = null;
      ByteArrayInputStream b = null;
      this.listFrame.removeAllElements();
      this.listAnima.removeAllElements();

      try {
         b = new ByteArrayInputStream(array);
         dis = new DataInputStream(b);
         int nSmallImage = dis.readByte();
         this.smallImage = new SmallImage[nSmallImage];

         int max_height;
         for(max_height = 0; max_height < nSmallImage; ++max_height) {
            this.smallImage[max_height] = new SmallImage(dis.readUnsignedByte(), dis.readUnsignedByte(), dis.readUnsignedByte(), dis.readUnsignedByte(), dis.readUnsignedByte());
         }

         max_height = 0;
         int hh = 0;
         int min_dy = -1000000;
         int nframe = dis.readShort();

         int i;
         for(i = 0; i < nframe; ++i) {
            byte npart = dis.readByte();
            mVector listPart = new mVector();

            for(int j = 0; j < npart; ++j) {
               PartFrame part = new PartFrame(dis.readShort(), dis.readShort(), dis.readByte());
               part.flip = dis.readByte();
               part.onTop = dis.readByte();
               listPart.addElement(part);
               if (i == 0) {
                  if (min_dy < part.dy + this.smallImage[part.idSmallImg].h) {
                     min_dy = part.dy + this.smallImage[part.idSmallImg].h;
                  }

                  if (max_height < GameCanvas.abs(part.dy)) {
                     max_height = GameCanvas.abs(part.dy);
                  }
               }
            }

            if (i == 0 && min_dy <= -5) {
               this.isFly = (byte)min_dy;
            }

            this.listFrame.addElement(new FrameEff(listPart, (mVector)null));
         }

         this.FrameWith = this.smallImage[0].w;
         this.FrameHeight = (short)max_height;
         len = dis.readShort();
         this.sequence = new byte[len];

         for(i = 0; i < len; ++i) {
            this.sequence[i] = (byte)dis.readShort();
         }

         len = dis.readByte();
         byte[] data = new byte[len];
         dis.read(data);
         Animation anim = new Animation(data);
         this.listAnima.addElement(anim);
         len = dis.readByte();
         data = new byte[len];
         dis.read(data);
         anim = new Animation(data);
         this.listAnima.addElement(anim);
         len = dis.readByte();
         data = new byte[len];
         dis.read(data);
         anim = new Animation(data);
         this.listAnima.addElement(anim);
         len = dis.readByte();
         data = new byte[len];
         dis.read(data);
         anim = new Animation(data);
         this.listAnima.addElement(anim);
         len = dis.readByte();
         data = new byte[len];
         dis.read(data);
         anim = new Animation(data);
         this.listAnima.addElement(anim);
         len = dis.readByte();
         data = new byte[len];
         dis.read(data);
         anim = new Animation(data);
         this.listAnima.addElement(anim);
         len = dis.readByte();
         data = new byte[len];
         dis.read(data);
         anim = new Animation(data);
         this.listAnima.addElement(anim);
         len = dis.readByte();
         data = new byte[len];
         dis.read(data);
         anim = new Animation(data);
         this.listAnima.addElement(anim);
         if (dis.available() > 0) {
            this.idShadow = dis.readByte();

            for(int j = 0; j < nframe; ++j) {
               FrameEff fr = (FrameEff)this.listFrame.elementAt(j);
               fr.xShadow = dis.readByte();
               fr.yShadow = dis.readByte();
            }
         }

         if (dis.available() > 0) {
            len = dis.readByte();
            data = new byte[len];
            dis.read(data);
            anim = new Animation(data);
            this.listAnima.addElement(anim);
            len = dis.readByte();
            data = new byte[len];
            dis.read(data);
            anim = new Animation(data);
            this.listAnima.addElement(anim);
            len = dis.readByte();
            data = new byte[len];
            dis.read(data);
            anim = new Animation(data);
            this.listAnima.addElement(anim);
            len = dis.readByte();
            data = new byte[len];
            dis.read(data);
            anim = new Animation(data);
            this.listAnima.addElement(anim);
         }
      } catch (Exception var27) {
         mSystem.println("id loi " + this.idimg);
         var27.printStackTrace();
      } finally {
         try {
            dis.close();
         } catch (Exception var26) {
         }

         try {
            b.close();
         } catch (Exception var25) {
         }

      }

   }

   public int getIndexAction(int action, int way) {
      return indexAction[way][action];
   }

   public Animation getAnim(int action, int way) {
      return (Animation)this.listAnima.elementAt(indexAction[way][action]);
   }

   public int getFrame(int f, int action, int way) {
      Animation anim = (Animation)this.listAnima.elementAt(indexAction[way][action]);
      return f < anim.frame.length ? anim.frame[f] : 0;
   }

   public void paintType2(mGraphics g, int idFrame, int x, int y, int way) {
   }

   public FrameEff getFrame(int idFrame) {
      FrameEff frame = (FrameEff)this.listFrame.elementAt(idFrame);
      return frame;
   }

   public byte[] getDxDyShadow(int idFrame) {
      FrameEff frame = (FrameEff)this.listFrame.elementAt(idFrame);
      return frame != null ? new byte[]{frame.xShadow, frame.yShadow} : new byte[2];
   }

   public void paintnoHeight(mGraphics g, int idFrame, int x, int y, int way, Image img, boolean isFly) {
      if (img != null) {
         FrameEff frame = (FrameEff)this.listFrame.elementAt(idFrame);
         if (frame == null) {
            Cout.Debug(this.listFrame.size() + "...NULLL frame " + idFrame);
         } else if (frame.listPartTop == null) {
            mSystem.println("null cho nay ne ----");
         }

         if (frame.listPartTop != null) {
            try {
               for(int i = 0; i < frame.listPartTop.size(); ++i) {
                  PartFrame part = (PartFrame)frame.listPartTop.elementAt(i);
                  SmallImage small = this.smallImage[part.idSmallImg];
                  int dx = part.dx;
                  int dy = part.dy;
                  if (isFly) {
                     dy += 35;
                  }

                  int w = small.w;
                  int h = small.h;
                  int xx = small.x;
                  int yy = small.y;
                  if (xx > img.getWidth()) {
                     xx = 0;
                  }

                  if (yy > img.getHeight()) {
                     yy = 0;
                  }

                  if (xx + w > img.getWidth()) {
                     w = img.getWidth() - xx;
                  }

                  if (way == 2) {
                     dx = -dx - w;
                  }

                  if (part.flip != 1) {
                     g.drawRegion(img, xx, yy, w, h, way, x + dx, y + dy, 0, false);
                  } else {
                     g.drawRegion(img, xx, yy, w, h, way == 2 ? 0 : 2, x + dx, y + dy, 0, false);
                  }
               }
            } catch (Exception var18) {
               System.out.println("loi dataeff: " + this.name);
               var18.printStackTrace();
            }

         }
      }
   }

   public void paintPet(mGraphics g, int idFrame, int x, int y, int way, Image img, int ysai) {
      if (img != null) {
         FrameEff frame = (FrameEff)this.listFrame.elementAt(idFrame);
         if (frame == null) {
            Cout.Debug(this.listFrame.size() + "...NULLL frame " + idFrame);
         } else if (frame.listPartTop == null) {
            mSystem.println("null cho nay ne ----");
         }

         if (frame.listPartTop != null) {
            try {
               for(int i = 0; i < frame.listPartTop.size(); ++i) {
                  PartFrame part = (PartFrame)frame.listPartTop.elementAt(i);
                  SmallImage small = this.smallImage[part.idSmallImg];
                  int dx = part.dx;
                  int dy = part.dy;
                  if (dy < -40) {
                     dy += ysai;
                  }

                  int w = small.w;
                  int h = small.h;
                  int xx = small.x;
                  int yy = small.y;
                  if (xx > img.getWidth()) {
                     xx = 0;
                  }

                  if (yy > img.getHeight()) {
                     yy = 0;
                  }

                  if (xx + w > img.getWidth()) {
                     w = img.getWidth() - xx;
                  }

                  if (yy + h > img.getHeight()) {
                     h = img.getHeight() - yy;
                  }

                  if (way == 2) {
                     dx = -dx - w;
                  }

                  if (part.flip != 1) {
                     g.drawRegion(img, xx, yy, w, h, way, x + dx, y + dy, 0, false);
                  } else {
                     g.drawRegion(img, xx, yy, w, h, way == 2 ? 0 : 2, x + dx, y + dy, 0, false);
                  }
               }
            } catch (Exception var18) {
               System.out.println("loi dataeff: " + this.name);
               var18.printStackTrace();
            }

         }
      }
   }

   public void paint(mGraphics g, int idFrame, int x, int y, int way, Image img) {
      if (img != null) {
         FrameEff frame = (FrameEff)this.listFrame.elementAt(idFrame);
         if (frame == null) {
            Cout.Debug(this.listFrame.size() + "...NULLL frame " + idFrame);
         } else if (frame.listPartTop == null) {
            mSystem.println("null cho nay ne ----");
         }

         if (frame.listPartTop != null) {
            try {
               for(int i = 0; i < frame.listPartTop.size(); ++i) {
                  PartFrame part = (PartFrame)frame.listPartTop.elementAt(i);
                  SmallImage small = this.smallImage[part.idSmallImg];
                  int dx = part.dx;
                  int w = small.w;
                  int h = small.h;
                  int xx = small.x;
                  int yy = small.y;
                  if (xx > img.getWidth()) {
                     xx = 0;
                  }

                  if (yy > img.getHeight()) {
                     yy = 0;
                  }

                  if (xx + w > img.getWidth()) {
                     w = img.getWidth() - xx;
                  }

                  if (yy + h > img.getHeight()) {
                     h = img.getHeight() - yy;
                  }

                  if (way == 2) {
                     dx = -dx - w;
                  }

                  if (part.flip != 1) {
                     g.drawRegion(img, xx, yy, w, h, way, x + dx, y + part.dy, 0, false);
                  } else {
                     g.drawRegion(img, xx, yy, w, h, way == 2 ? 0 : 2, x + dx, y + part.dy, 0, false);
                  }
               }
            } catch (Exception var16) {
               System.out.println("loi dataeff: " + this.name);
               var16.printStackTrace();
            }

         }
      }
   }

   public int getPointStart() {
      return this.isFly;
   }

   public short getWith() {
      return this.FrameWith;
   }

   public short getHeight() {
      return this.FrameHeight;
   }
}
