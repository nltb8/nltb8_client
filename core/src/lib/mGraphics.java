package lib;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import com.mchien.ngulong.MyGdxGame;
import java.util.Enumeration;
import javax.microedition.lcdui.Image;

public class mGraphics {
   public static int zoomLevel = 1;
   private float r;
   private float gl;
   private float b;
   private float a;
   public boolean isTranslate;
   public boolean isClip;
   public int clipX;
   public int clipY;
   public int clipW;
   public int clipH;
   public static final byte ZOOM_IOS = 2;
   public static final boolean isTrue = true;
   public static final boolean isFalse = false;
   private int clipTX;
   private int clipTY;
   int translateX;
   int translateY;
   public static int HCENTER = 1;
   public static int VCENTER = 2;
   public static int LEFT = 4;
   public static int RIGHT = 8;
   public static int TOP = 16;
   public static int BOTTOM = 32;
   public static final int TRANS_NONE = 0;
   public static final int TRANS_ROT90 = 5;
   public static final int TRANS_ROT180 = 3;
   public static final int TRANS_ROT270 = 6;
   public static final int TRANS_MIRROR = 2;
   public static final int TRANS_MIRROR_ROT90 = 7;
   public static final int TRANS_MIRROR_ROT180 = 1;
   public static final int TRANS_MIRROR_ROT270 = 4;
   public static mHashtable cachedTextures = new mHashtable();
   public SpriteBatch g;
   public boolean isRorate;
   public int xRotate;
   public int yRotate;
   public float rotation;
   int wImg;
   int hImg;

   public void translate(int tx, int ty) {
      tx *= zoomLevel;
      ty *= zoomLevel;
      this.translateX += tx;
      this.translateY += ty;
      this.isTranslate = true;
      if (this.translateX == 0 && this.translateY == 0) {
         this.isTranslate = false;
      }

   }

   public static int getImageWidth(Image img) {
      return img.getWidth();
   }

   public static mGraphics getGraphics(Image img) {
      return new mGraphics();
   }

   public static int getImageHeight(Image img) {
      return img.getHeight();
   }

   public void begin() {
      this.g.begin();
   }

   public void end() {
      this.isClip = false;
      this.translateX = 0;
      this.translateY = 0;
      this.isTranslate = false;
      this.isClip = false;
      this.g.end();
   }

   public int getTranslateX() {
      return this.translateX / zoomLevel;
   }

   public int getTranslateY() {
      return this.translateY / zoomLevel;
   }

   public void enableBlending(float alpha) {
      this.g.setColor(1.0F, 1.0F, 1.0F, alpha);
   }

   public void disableBlending() {
      this.g.setColor(1.0F, 1.0F, 1.0F, 1.0F);
   }

   public void setClip(int x, int y, int w, int h) {
      if (w <= 0) {
         w = 1;
      }

      if (h <= 0) {
         h = 1;
      }

      x *= zoomLevel;
      y *= zoomLevel;
      w *= zoomLevel;
      h *= zoomLevel;
      if (this.isTranslate) {
         x += this.translateX;
         y += this.translateY;
      }

      this.clipX = x;
      this.clipY = y;
      this.clipW = w;
      this.clipH = h;
      this.isClip = true;
   }

   public void beginClip() {
      Rectangle scissors = new Rectangle();
      Rectangle clipBounds = new Rectangle((float)this.clipX, (float)this.clipY, (float)this.clipW, (float)this.clipH);
      ScissorStack.calculateScissors(MyGdxGame.me.camera, this.g.getTransformMatrix(), clipBounds, scissors);
      ScissorStack.pushScissors(scissors);
   }

   public void endClip() {
   }

   public void endClip0() {
      this.g.flush();
      ScissorStack.popScissors();
   }

   public mGraphics(SpriteBatch g) {
      this.g = g;
   }

   public mGraphics() {
   }

   void cache(String key, Texture value) {
      if (cachedTextures.size() > 500) {
         Texture img;
         for(Enumeration k = cachedTextures.keys(); k.hasMoreElements(); img = null) {
            String k0 = (String)k.nextElement();
            img = (Texture)cachedTextures.get(k0);
            cachedTextures.remove(k0);
            cachedTextures.remove(img);
            img.dispose();
         }

         cachedTextures.clear();
         System.gc();
      }

      cachedTextures.put(key, value);
   }

   public void clearCache() {
      Texture img;
      for(Enumeration k = cachedTextures.keys(); k.hasMoreElements(); img = null) {
         String k0 = (String)k.nextElement();
         img = (Texture)cachedTextures.get(k0);
         cachedTextures.remove(img);
         cachedTextures.remove(k0);
         img.dispose();
      }

      cachedTextures.clear();
      System.gc();
   }

   public void drawTextureRegion(Image tx, int x, int y, int alg) {
      x *= zoomLevel;
      y *= zoomLevel;
      if (this.isTranslate) {
         x += this.translateX;
         y += this.translateY;
      }

      this.g.draw(tx.tRegion, (float)x, (float)y);
   }

   public void fillRect(int x, int y, int w, int h, boolean useClip) {
      x *= zoomLevel;
      y *= zoomLevel;
      w *= zoomLevel;
      h *= zoomLevel;
      if (w >= 0 && h >= 0) {
         if (this.isTranslate) {
            x += this.translateX;
            y += this.translateY;
         }

         String key = "fr" + this.r + this.gl + this.b + this.a;
         Texture rgb_texture = (Texture)cachedTextures.get(key);
         if (rgb_texture == null) {
            Pixmap p = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
            p.setColor(this.r, this.b, this.gl, this.a);
            p.drawPixel(0, 0);
            rgb_texture = new Texture(p);
            p.dispose();
            this.cache(key, rgb_texture);
         }

         if (this.isClip && useClip) {
            this.beginClip();
         }

         this.g.draw(rgb_texture, (float)x, (float)y, 0.0F, 0.0F, 1.0F, 1.0F, (float)w, (float)h, 0.0F, 0, 0, 1, 1, false, false);
         if (this.isClip && useClip) {
            this.endClip0();
         }

      }
   }

   public float getAngle(Vector2 centerPt, Vector2 target) {
      float angle = (float)Math.toDegrees(Math.atan2((double)(target.y - centerPt.y), (double)(target.x - centerPt.x)));
      if (angle < 0.0F) {
         angle += 360.0F;
      }

      return angle;
   }

   public void drawLine(int x1, int y1, int x2, int y2, boolean useClip) {
      x1 *= zoomLevel;
      y1 *= zoomLevel;
      x2 *= zoomLevel;
      y2 *= zoomLevel;
      if (this.isTranslate) {
         x1 += this.translateX;
         y1 += this.translateY;
         x2 += this.translateX;
         y2 += this.translateY;
      }

      String key = "dl" + this.r + this.gl + this.b;
      Texture rgb_texture = (Texture)cachedTextures.get(key);
      if (rgb_texture == null) {
         Pixmap p = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
         p.setColor(this.r, this.b, this.gl, this.a);
         p.drawPixel(0, 0);
         rgb_texture = new Texture(p);
         p.dispose();
         this.cache(key, rgb_texture);
      }

      float xSl = (float)Math.sqrt((double)((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1)));
      int ySl = zoomLevel;
      Vector2 start = new Vector2((float)x1, (float)y1);
      Vector2 end = new Vector2((float)x2, (float)y2);
      float angle = this.getAngle(start, end);
      if (this.isClip && useClip) {
         this.beginClip();
      }

      this.g.draw(rgb_texture, (float)x1, (float)y1, 0.0F, 0.0F, 1.0F, 1.0F, xSl, (float)ySl, angle, 0, 0, 1, 1, false, false);
      if (this.isClip && useClip) {
         this.endClip0();
      }

   }

   public void setColor(int rgb) {
      float R = (float)(rgb >> 16 & 255);
      float B = (float)(rgb >> 8 & 255);
      float G = (float)(rgb & 255);
      this.b = B / 256.0F;
      this.gl = G / 256.0F;
      this.r = R / 256.0F;
      this.a = 1.0F;
   }

   public void setColor(int rgb, float alpha) {
      float R = (float)(rgb >> 16 & 255);
      float B = (float)(rgb >> 8 & 255);
      float G = (float)(rgb & 255);
      this.b = B / 256.0F;
      this.gl = G / 256.0F;
      this.r = R / 256.0F;
      this.a = alpha;
   }

   public void drawRegion(Image img, int x, int y, int arg3, int arg4, int arg5, int arg6, int arg7, int anchor, boolean useClip) {
      if (img != null) {
         x *= zoomLevel;
         y *= zoomLevel;
         arg6 *= zoomLevel;
         arg7 *= zoomLevel;
         arg3 *= zoomLevel;
         arg4 *= zoomLevel;
         this._drawRegion(img.texture, x, y, arg3, arg4, arg5, arg6, arg7, anchor, useClip, false);
      }
   }

   public void drawRegion(Image img, int x, int y, int arg3, int arg4, int arg5, int arg6, int arg7, int anchor, boolean isScale, boolean useClip) {
      if (img != null) {
         x *= zoomLevel;
         y *= zoomLevel;
         arg6 *= zoomLevel;
         arg7 *= zoomLevel;
         arg3 *= zoomLevel;
         arg4 *= zoomLevel;
         this._drawRegion(img.texture, x, y, arg3, arg4, arg5, arg6, arg7, anchor, useClip, isScale);
      }
   }

   public void drawRegionNotSetClip(Image img, int x, int y, int arg3, int arg4, int arg5, int arg6, int arg7, int anchor) {
      if (img != null) {
         x *= zoomLevel;
         y *= zoomLevel;
         arg6 *= zoomLevel;
         arg7 *= zoomLevel;
         arg3 *= zoomLevel;
         arg4 *= zoomLevel;
         this._drawRegion(img.texture, x, y, arg3, arg4, arg5, arg6, arg7, anchor, false, false);
      }
   }

   public void fillTriangle(int x1, int y1, int x2, int y2, int x3, int y3, boolean useClip) {
   }

   public void _drawRegion(Texture imgscr, int x_src, int y_src, int width, int height, int flip, int x_dest, int y_dest, int anchor, boolean isUseSetClip, boolean isScale) {
      if (imgscr != null) {
//         int wt = width * zoomLevel / 2;
//         int ht = height * zoomLevel / 2;
//         int wt = width * zoomLevel;
//         int ht = height * zoomLevel;
         int wt = width ;
         int ht = height ;
         if (this.isTranslate) {
            x_dest += this.translateX;
            y_dest += this.translateY;
         }

         if (!this.isClipWithWHZero()) {
            boolean flipX = false;
            boolean flipY = true;
            int scX = 1;
            int scY = 1;
            int y0 = 0;
            float orx = 0.0F;
            float ory = 0.0F;
            int ixA = 0;
            int iyA = 0;
            switch(anchor) {
               case 0:
               case 20:
                  ixA = 0;
                  iyA = 0;
                  break;
               case 3:
                  ixA = wt / 2;
                  iyA = ht / 2;
                  if (flip == 4 || flip == 7 || flip == 6 || flip == 5) {
                     ixA = ht / 2;
                     iyA = wt / 2;
                  }
                  break;
               case 6:
                  ixA = 0;
                  iyA = ht / 2;
                  if (flip == 4 || flip == 7 || flip == 6 || flip == 5) {
                     iyA = wt / 2;
                  }
                  break;
               case 10:
                  ixA = wt;
                  iyA = ht / 2;
                  if (flip == 4 || flip == 7 || flip == 6 || flip == 5) {
                     ixA = ht;
                     iyA = wt / 2;
                  }
                  break;
               case 17:
                  ixA = wt / 2;
                  if (flip == 4 || flip == 6 || flip == 5 || flip == 7) {
                     ixA = ht / 2;
                  }

                  iyA = 0;
                  break;
               case 24:
                  ixA = wt;
                  if (flip == 4 || flip == 6 || flip == 5 || flip == 7) {
                     ixA = ht;
                  }

                  iyA = 0;
                  break;
               case 33:
                  ixA = wt / 2;
                  iyA = ht;
                  if (flip == 4 || flip == 7 || flip == 6 || flip == 5) {
                     ixA = ht / 2;
                     iyA = wt;
                  }
                  break;
               case 36:
                  ixA = 0;
                  iyA = ht;
                  if (flip == 4 || flip == 7 || flip == 6 || flip == 5) {
                     iyA = wt;
                  }
                  break;
               case 40:
                  ixA = wt;
                  iyA = ht;
                  if (flip == 4 || flip == 7 || flip == 6 || flip == 5) {
                     ixA = ht;
                     iyA = wt;
                  }
            }

            x_dest -= ixA;
            y_dest -= iyA;
            int ix = 0;
            int iy = 0;
            switch(flip) {
               case 1:
                  flip = 0;
                  flipY = false;
                  break;
               case 2:
                  flip = 0;
                  flipX = true;
                  break;
               case 3:
                  flip = 180;
                  iy = -ht;
                  ix = -wt;
                  break;
               case 4:
                  flip = 90;
                  flipY = false;
                  flipX = false;
                  ix = -ht;
                  iy = 0;
                  scX = 1;
                  break;
               case 5:
                  flip = 90;
                  flipY = true;
                  flipX = false;
                  ix = -ht;
                  iy = 0;
                  scX = 1;
                  break;
               case 6:
                  flip = 90;
                  flipY = false;
                  flipX = true;
                  ix = -ht;
                  scY = 1;
                  break;
               case 7:
                  flip = 90;
                  flipY = true;
                  flipX = true;
                  ix = -ht;
                  scX = 1;
            }

            if (this.isClip && isUseSetClip) {
               this.beginClip();
            }

//            float valuef = (float)zoomLevel / 2.0F;
            float valuef = 1f;
            this.g.draw(imgscr, (float)(x_dest - ix), (float)(y_dest - iy), orx, ory, (float)width, (float)height, valuef, valuef, (float)flip, x_src, y_src, width, height, flipX, flipY);
            if (this.isClip && isUseSetClip) {
               this.endClip0();
            }

         }
      }
   }
   public void drawRegionSpire(Sprite sprite, int x_src, int y_src, int width, int height, int flip, int x_dest, int y_dest, int anchor) {
      if (sprite == null) {
         return;
      }
      x_src *= zoomLevel;
      y_src *= zoomLevel;
      width *= zoomLevel;
      height *= zoomLevel;
      x_dest *= zoomLevel;
      y_dest *= zoomLevel;
      if (isTranslate) {
         x_dest += translateX;
         y_dest += translateY;
      }

      // Xử lý anchor point
      int ixA = 0;
      int iyA = 0;
      switch (anchor) {
         case 0:
         case 20:
            break;
         case 3:
            ixA = width / 2;
            iyA = height / 2;
            if (flip == 4 || flip == 7 || flip == 6 || flip == 5) {
               ixA = height / 2;
               iyA = width / 2;
               break;
            }
            break;
         case 6:
            iyA = height / 2;
            if (flip == 4 || flip == 7 || flip == 6 || flip == 5) {
               iyA = width / 2;
               break;
            }
            break;
         case 10:
            ixA = width;
            iyA = height / 2;
            if (flip == 4 || flip == 7 || flip == 6 || flip == 5) {
               ixA = height;
               iyA = width / 2;
               break;
            }
            break;
         case 17:
            ixA = width / 2;
            if (flip == 4 || flip == 6 || flip == 5 || flip == 7) {
               ixA = height / 2;
            }
            break;
         case 24:
            ixA = width;
            if (flip == 4 || flip == 6 || flip == 5 || flip == 7) {
               ixA = height;
            }
            break;
         case 33:
            ixA = width / 2;
            iyA = height;
            if (flip == 4 || flip == 7 || flip == 6 || flip == 5) {
               ixA = height / 2;
               iyA = width;
               break;
            }
            break;
         case 36:
            iyA = height;
            if (flip == 4 || flip == 7 || flip == 6 || flip == 5) {
               iyA = width;
               break;
            }
            break;
         case 40:
            ixA = width;
            iyA = height;
            if (flip == 4 || flip == 7 || flip == 6 || flip == 5) {
               ixA = height;
               iyA = width;
               break;
            }
            break;
      }
      // [Code xử lý anchor giống như trong _drawRegion]

      int x_dest2 = x_dest - ixA;
      int y_dest2 = y_dest - iyA;

      // Xử lý flip và rotation
      boolean flipX = false;
      boolean flipY = true;
      float rotation = 0;
      int ix = 0;
      int iy = 0;

      switch (flip) {
         case 1:
            flipY = false;
            break;
         case 2:
            flipX = true;
            break;
         case 3:
            rotation = 180;
            iy = -height;
            ix = -width;
            break;
         case 4:
            rotation = 90;
            flipY = false;
            ix = -height;
            break;
         case 5:
            rotation = 90;
            ix = -height;
            break;
         case 6:
            rotation = 90;
            flipY = false;
            flipX = true;
            ix = -height;
            break;
         case 7:
            rotation = 90;
            flipX = true;
            ix = -height;
            break;
      }

      sprite.setPosition(x_dest2 - ix, y_dest2 - iy);
      sprite.setRotation(rotation);
      sprite.setFlip(flipX, flipY);
      sprite.setRegion(x_src, y_src, width, height);

      if (isClip) {
         beginClip();
      }
      sprite.draw(g);

      if (isClip) {
         g.flush();
         ScissorStack.popScissors();
      }
   }

   public void resetRotate() {
      this.isRorate = false;
      this.xRotate = 0;
      this.yRotate = 0;
   }

   public void rotate(int pAngle, int x, int y) {
      if (pAngle != 0) {
         this.isRorate = true;
         this.rotation = (float)pAngle;
         this.xRotate = x;
         this.yRotate = y;
      }
   }

   public void drawImageMap(Image img, int x, int y) {
      if (img != null) {
         x *= zoomLevel;
         y *= zoomLevel;
         if (this.isTranslate) {
            x += this.translateX;
            y += this.translateY;
         }

         int w = img._getWidth();
         int h = img._getHeight();
         this.g.draw(img.texture, (float)x, (float)(y + h), (float)w, (float)(-h));
      }
   }

   public void drawImage(Image img, int x, int y, int anchor, boolean useClip) {
      x *= zoomLevel;
      y *= zoomLevel;
      this._drawRegion(img.texture, 0, 0, img._getWidth(), img._getHeight(), 0, x, y, anchor, useClip, false);
   }
   public void drawImage(Image img, int x, int y, int anchor, boolean useClip,int flip) {
      x *= zoomLevel;
      y *= zoomLevel;
      this._drawRegion(img.texture, 0, 0, img._getWidth(), img._getHeight(), flip, x, y, anchor, useClip, false);
   }

   public void _drawImage(Texture img, int x, int y, int anchor, boolean useClip) {
      if (img != null) {
         if (this.isTranslate) {
            x += this.translateX;
            y += this.translateY;
         }

         int ixA = 0;
         int iyA = 0;
         int iyA1 = 0;
         int ixA1 = 0;
         int w = img.getWidth();
         int h = img.getHeight();
         switch(anchor) {
            case 0:
            case 20:
               ixA = 0;
               ixA1 = w;
               iyA = h;
               iyA1 = -h;
               break;
            case 3:
               ixA = -w / 2;
               ixA1 = w;
               iyA = h / 2;
               iyA1 = -h;
               break;
            case 6:
               ixA = 0;
               ixA1 = w;
               iyA = h / 2;
               iyA1 = -h;
               break;
            case 10:
               ixA = -w;
               ixA1 = w;
               iyA = h / 2;
               iyA1 = -h;
               break;
            case 17:
               ixA = -w / 2;
               ixA1 = w;
               iyA = h;
               iyA1 = -h;
               break;
            case 24:
               ixA = -w;
               ixA1 = w;
               iyA = h;
               iyA1 = -h;
               break;
            case 33:
               ixA = -w / 2;
               ixA1 = w;
               iyA = 0;
               iyA1 = -h;
               break;
            case 36:
               ixA = 0;
               ixA1 = w;
               iyA = 0;
               iyA1 = -h;
               break;
            case 40:
               ixA = -w;
               ixA1 = w;
               iyA = 0;
               iyA1 = -h;
         }

         if (this.isClip && useClip) {
            this.beginClip();
         }

         this.g.draw(img, (float)(x + ixA), (float)(y + iyA), (float)ixA1, (float)iyA1);
         if (this.isClip && useClip) {
            this.endClip0();
         }

      }
   }

   public void drawRect(int x, int y, int w, int h, boolean useClip) {
      int xx = 1;
      this.fillRect(x, y, w, xx, useClip);
      this.fillRect(x, y, xx, h, useClip);
      this.fillRect(x + w, y, xx, h + 1, useClip);
      this.fillRect(x, y + h, w + 1, xx, useClip);
   }

   public void drawRoundRect(int x, int y, int w, int h, int a, int b, boolean useClip) {
      this.drawRect(x, y, w, h, useClip);
   }

   public void fillRoundRect(int x, int y, int w, int h, int a, int b, boolean useClip) {
      this.fillRect(x, y, w, h, useClip);
   }

   public void drawString(mVector total) {
   }

   public void drawStringNotSetClip(mVector total) {
   }

   public void drawString(String s, float x, float y, BitmapFont font, int al, boolean useClip) {
      if (!this.isClipWithWHZero()) {
         x *= (float)zoomLevel;
         y *= (float)zoomLevel;
         if (this.isTranslate) {
            x += (float)this.translateX;
            y += (float)this.translateY;
         }

         if (this.isClip && useClip) {
            this.beginClip();
         }

         font.draw(this.g, s, x, y, 0.0F, al, false);
         if (this.isClip && useClip) {
            this.endClip0();
         }

      }
   }

   public void setClipTrung(int x, int y, int w, int h) {
      x *= zoomLevel;
      y *= zoomLevel;
      w *= zoomLevel;
      h *= zoomLevel;
      if (this.isTranslate) {
         x += this.translateX;
         y += this.translateY;
      }

      this.clipX = x;
      this.clipY = y;
      this.clipW = w;
      this.clipH = h;
      this.isClip = true;
   }

   public static Image blend(Image img, float level, int color) {
      int[] var10000 = new int[img._getWidth() * img._getHeight()];
      img.texture.getTextureData().prepare();
      Pixmap a = img.texture.getTextureData().consumePixmap();
      int ww = img._getWidth();
      int hh = img._getHeight();
      int R = 255 & color >> 24;
      int B = 255 & color >> 16;
      int G = 255 & color >> 8;
      int A = 255 & color >> 0;

      for(int x = 0; x < ww; ++x) {
         for(int y = 0; y < hh; ++y) {
            int pixel = a.getPixel(x, y);
            if (pixel != -256) {
               float r = (float)(R - (pixel >> 24 & 255)) * level + (float)(pixel >> 24 & 255);
               float g = (float)(B - (pixel >> 16 & 255)) * level + (float)(pixel >> 16 & 255);
               float b = (float)(G - (pixel >> 8 & 255)) * level + (float)(pixel >> 8 & 255);
               float al = (float)(A - (pixel >> 0 & 255)) * level + (float)(pixel >> 0 & 255);
               if (r > 255.0F) {
                  r = 255.0F;
               }

               if (r < 0.0F) {
                  r = 0.0F;
               }

               if (g > 255.0F) {
                  g = 255.0F;
               }

               if (g < 0.0F) {
                  g = 0.0F;
               }

               if (b < 0.0F) {
                  b = 0.0F;
               }

               if (b > 255.0F) {
                  b = 255.0F;
               }

               int rr = (int)r;
               int gg = (int)g;
               int bb = (int)b;
               int aa = (int)al;
               a.setColor((rr << 24) + (gg << 16) + (bb << 8) + (aa << 0));
               a.drawPixel(x, y);
            }
         }
      }

      Image image = Image.createImage(ww, hh);
      image.texture = new Texture(a);
      a.dispose();
      return image;
   }

   public boolean isClipWithWHZero() {
      return this.isClip && (this.clipH == 0 || this.clipW == 0);
   }

   public void fillRect(int x, int y, int w, int h, int color, int alpha, boolean useClip) {
      x *= zoomLevel;
      y *= zoomLevel;
      w *= zoomLevel;
      h *= zoomLevel;
      if (w >= 0 && h >= 0 && !this.isClipWithWHZero()) {
         if (this.isTranslate) {
            x += this.translateX;
            y += this.translateY;
         }

         String key = "fr" + this.r + this.gl + this.b + this.a + color;
         Texture rgb_texture = (Texture)cachedTextures.get(key);
         if (rgb_texture == null) {
            this.setColor(color, 0.5F);
            Pixmap p = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
            p.setColor(this.r, this.b, this.gl, this.a);
            p.drawPixel(0, 0);
            rgb_texture = new Texture(p);
            p.dispose();
            this.cache(key, rgb_texture);
         }

         if (this.isClip && useClip) {
            this.beginClip();
         }

         this.g.draw(rgb_texture, (float)x, (float)y, 0.0F, 0.0F, 1.0F, 1.0F, (float)w, (float)h, 0.0F, 0, 0, 1, 1, false, false);
         if (this.isClip && useClip) {
            this.endClip0();
         }

      }
   }

   public void fillRectAlpha(int x, int y, int w, int h, int tran, int alpha, boolean useClip) {
      x *= zoomLevel;
      y *= zoomLevel;
      w *= zoomLevel;
      h *= zoomLevel;
      int color = 0;
      if (w >= 0 && h >= 0 && !this.isClipWithWHZero()) {
         if (this.isTranslate) {
            x += this.translateX;
            y += this.translateY;
         }

         String key = "fr" + this.r + this.gl + this.b + this.a + color;
         Texture rgb_texture = (Texture)cachedTextures.get(key);
         if (rgb_texture == null) {
            this.setColor(color, 0.5F);
            Pixmap p = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
            p.setColor(this.r, this.b, this.gl, this.a);
            p.drawPixel(0, 0);
            rgb_texture = new Texture(p);
            p.dispose();
            this.cache(key, rgb_texture);
         }

         if (this.isClip && useClip) {
            this.beginClip();
         }

         this.g.draw(rgb_texture, (float)x, (float)y, 0.0F, 0.0F, 1.0F, 1.0F, (float)w, (float)h, 0.0F, 0, 0, 1, 1, false, false);
         if (this.isClip && useClip) {
            this.endClip0();
         }

      }
   }

   public void ClipRec(int x, int y, int Width, int Height) {
   }

   public void ClipRec2(int x, int y, int Width, int Height) {
   }

   public void translateAndroid(int x, int y) {
   }

   public static void resetTransAndroid(mGraphics g) {
   }

   public void saveCanvas() {
   }

   public void restoreCanvas() {
   }

   public void fillRecAlpla(int x, int y, int w, int h, int color) {
      this.drawRecAlpa(0, 0, Tilemap.w * 16, y, color);
      this.drawRecAlpa(0, y, x, Tilemap.h * 16 - y, color);
      this.drawRecAlpa(x, y + h, Tilemap.w * 16 - x, Tilemap.h * 16 - (y + h), color);
      this.drawRecAlpa(x + w, y, Tilemap.w * 24 - (x + w), h, color);
   }

   public void drawRecAlpa(int x, int y, int w, int h, int color) {
      x *= zoomLevel;
      y *= zoomLevel;
      w *= zoomLevel;
      h *= zoomLevel;
      if (w >= 0 && h >= 0) {
         if (this.isTranslate) {
            x += this.translateX;
            y += this.translateY;
         }

         this.setColor(color);
         this.a = 0.5F;
         String key = "fr" + this.r + this.gl + this.b + this.a;
         Texture rgb_texture = (Texture)cachedTextures.get(key);
         if (rgb_texture == null) {
            Pixmap p = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
            p.setColor(this.r, this.b, this.gl, this.a);
            p.drawPixel(0, 0);
            rgb_texture = new Texture(p);
            p.dispose();
            this.cache(key, rgb_texture);
         }

         this.g.draw(rgb_texture, (float)x, (float)y, 0.0F, 0.0F, 1.0F, 1.0F, (float)w, (float)h, 0.0F, 0, 0, 1, 1, false, false);
      }
   }
}
