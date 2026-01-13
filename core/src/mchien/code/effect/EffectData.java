package mchien.code.effect;

import mchien.code.main.GameCanvas;
import mchien.code.model.FrameEff;
import mchien.code.model.PartFrame;
import mchien.code.model.SmallImage;
import mchien.code.network.GameService;
import mchien.code.screen.Res;
import mchien.code.screen.screen.GameData;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import lib.Rms;
import lib.mSystem;
import lib.mVector;

public class EffectData {
   public byte[] data;
   public long timeremove;
   public long timeGetBack;
   public mVector listFrame = new mVector();
   public mVector listAnima = new mVector();
   public SmallImage[] smallImage;
   public byte[][] frameChar = new byte[4][];
   public byte[] sequence;
   public int fw;
   public int fh;
   public byte[] indexSplash = new byte[4];
   public boolean isLoadData = false;

   public void setdata(byte[] data) {
      if (!this.isLoadData) {
         this.loadData(data);
         this.isLoadData = true;
      }

   }

   public boolean loadFromRms(int idEff) {
      try {
         byte[] dataRms = Rms.loadRMS("eff" + idEff);
         if (dataRms != null) {
            DataInputStream dis = new DataInputStream(new ByteArrayInputStream(dataRms));
            short lenarr = (short)dis.available();
            byte[] data = new byte[lenarr];
            dis.read(data, 0, data.length);
            this.setdata(data);
            this.timeremove = mSystem.currentTimeMillis() + 60000L;
            GameData.listbyteData.put("" + idEff, this);

            try {
               dis.close();
            } catch (Exception var8) {
            }

            this.timeGetBack = mSystem.currentTimeMillis() + 10000L;

            try {
               dis.close();
            } catch (Exception var7) {
            }

            return true;
         }
      } catch (Exception var9) {
      }

      return false;
   }

   public void load(int idEff) {
      DataInputStream is = null;

      try {
         this.timeremove = mSystem.currentTimeMillis() + 60000L;
         if (mSystem.currentTimeMillis() - this.timeGetBack < 0L) {
            return;
         }

         if (this.loadFromRms(idEff)) {
            return;
         }

         is = mSystem.getResourceAsStream("/datahd/effskill/" + (idEff - Res.ID_START_SKILL));
         if (is != null) {
            DataInputStream dis = new DataInputStream(is);
            short lenarr = (short)dis.available();
            byte[] data = new byte[lenarr];
            dis.read(data, 0, data.length);
            this.setdata(data);
            this.timeremove = mSystem.currentTimeMillis() + 60000L;
            GameData.listbyteData.put("" + idEff, this);

            try {
               dis.close();
            } catch (Exception var18) {
            }

            this.timeGetBack = mSystem.currentTimeMillis() + 10000L;
            return;
         }

         EffectData data = new EffectData();
         this.timeremove = mSystem.currentTimeMillis() + 60000L;
         GameData.listbyteData.put("" + idEff, data);
         GameService.gI().doGetByteData(idEff, "effectdata getBydeData " + idEff);
         this.timeGetBack = mSystem.currentTimeMillis() + 10000L;
      } catch (Exception var19) {
         var19.printStackTrace();
         return;
      } finally {
         try {
            is.close();
         } catch (Exception var17) {
         }

      }

   }

   public void loadData(byte[] array) {
      if (array != null) {
         DataInputStream dis = null;

         try {
            boolean isnew = true;
            this.listFrame.removeAllElements();
            this.smallImage = null;
            dis = new DataInputStream(new ByteArrayInputStream(array));
            int nSmallImage = dis.readByte();
            this.smallImage = new SmallImage[nSmallImage];

            int max_height;
            for(max_height = 0; max_height < nSmallImage; ++max_height) {
               this.smallImage[max_height] = new SmallImage(dis.readUnsignedByte(), dis.readUnsignedByte(), dis.readUnsignedByte(), dis.readUnsignedByte(), dis.readUnsignedByte());
            }

            max_height = 0;
            int nframe = dis.readShort();

            for(int i = 0; i < nframe; ++i) {
               byte npart = dis.readByte();
               mVector listParttop = new mVector();
               mVector listPartbottom = new mVector();

               for(int j = 0; j < npart; ++j) {
                  PartFrame part = new PartFrame(dis.readShort(), dis.readShort(), dis.readByte());
                  if (isnew) {
                     part.flip = dis.readByte();
                     part.onTop = dis.readByte();
                  }

                  if (part.onTop == 0) {
                     listParttop.addElement(part);
                  } else {
                     listPartbottom.addElement(part);
                  }

                  if (max_height < GameCanvas.abs(part.dy)) {
                     max_height = GameCanvas.abs(part.dy);
                  }
               }

               this.listFrame.addElement(new FrameEff(listParttop, listPartbottom));
            }

            this.fw = this.smallImage[0].w;
            this.fh = (short)max_height;
            short len;
            if (isnew) {
               len = (short)dis.readUnsignedByte();
            } else {
               len = dis.readShort();
            }

            this.sequence = new byte[len];

            int i;
            for(i = 0; i < len; ++i) {
               this.sequence[i] = (byte)dis.readShort();
            }

            if (isnew) {
               dis.readByte();
               len = dis.readByte();
               this.frameChar[0] = new byte[len];

               for(i = 0; i < len; ++i) {
                  this.frameChar[0][i] = dis.readByte();
               }

               len = dis.readByte();
               this.frameChar[1] = new byte[len];

               for(i = 0; i < len; ++i) {
                  this.frameChar[1][i] = dis.readByte();
               }

               len = dis.readByte();
               this.frameChar[3] = new byte[len];

               for(i = 0; i < len; ++i) {
                  this.frameChar[3][i] = dis.readByte();
               }
            }

            this.isLoadData = true;

            try {
               this.indexSplash[0] = (byte)(this.frameChar[0].length - 7);
               this.indexSplash[1] = (byte)(this.frameChar[1].length - 7);
               this.indexSplash[2] = (byte)(this.frameChar[3].length - 7);
               this.indexSplash[3] = (byte)(this.frameChar[3].length - 7);
            } catch (Exception var22) {
            }

            this.indexSplash[0] = dis.readByte();
            this.indexSplash[1] = dis.readByte();
            this.indexSplash[2] = dis.readByte();
            this.indexSplash[3] = this.indexSplash[2];
         } catch (Exception var23) {
            var23.printStackTrace();
         } finally {
            try {
               dis.close();
            } catch (Exception var21) {
            }

         }

      }
   }
}
