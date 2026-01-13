package com.mchien.ngulong;

import lib.*;
import mchien.code.main.GameMidlet;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import javax.microedition.lcdui.Image;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class MyGdxGame implements ApplicationListener, InputProcessor {

   private final float TARGET_FPS = 1 / 30f;
   private SpriteBatch batch;
   public static GameMidlet gmidlet;
   private InputMultiplexer inputMultiplexer;
   private MyGdxGame.MyInputProcessor inputProcessor;
   private MyGdxGame.MyGestureHandler gestureHandler;
   public float zoom = 1.0F;
   public long timeUpdate;
   public static MyGdxGame me;
   private SpriteBatch batcher;
   private mGraphics g;
   public OrthographicCamera camera;
   public Viewport viewport;
   public static int WIDTH = 380;
   public static int HEIGHT;
   private static int SAFE_ZONE_TOP;
   private static int SAFE_ZONE_BOTTOM;

   public static String mainThreadName;
   public static boolean isStart;
   public static boolean isPause;
   public static boolean isPC = false;
   public static boolean isAndroid;
   public static final byte IPHONE_JB = 2;
   public static final byte IP_APPSTORE = 3;
   Image img;
   private Thread backgroundThread;
   private volatile boolean isBackgroundRunning = false;

   public static MyGdxGame gI(){
      if(me == null)
         me = new MyGdxGame();
      return me;
   }

   public static int getWidth() {
      return WIDTH;
   }

   public static int getHeight() {
      return HEIGHT;
   }

   public void initaliseInputProcessors() {
      this.inputMultiplexer = new InputMultiplexer();
      Gdx.input.setInputProcessor(this.inputMultiplexer);
      this.inputProcessor = new MyGdxGame.MyInputProcessor();
      this.gestureHandler = new MyGdxGame.MyGestureHandler();
      this.inputMultiplexer.addProcessor(new GestureDetector(this.gestureHandler));
      this.inputMultiplexer.addProcessor(this.inputProcessor);
   }

   public void create() {
      Rms.deleteRecordCompareToName();
      if(Gdx.app.getType() == Application.ApplicationType.Desktop)
         mSystem.isPC = true;
      else if(Gdx.app.getType() == Application.ApplicationType.Android)
         mSystem.isAndroid = true;
      else
         mSystem.isIos = true;
      if (Thread.currentThread().getName() != "Main") {
         Thread.currentThread().setName("Main");
      }
      Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.err.println("Ngoại lệ chưa được xử lý: " + e.getMessage());
                e.printStackTrace();
            }
        });
//      System.setOut(new PrintStream(new OutputStream() {
//         @Override
//         public void write(int b) throws IOException {
//
//         }
//      }));



      mainThreadName = Thread.currentThread().getName();
      this.initaliseInputProcessors();
      MyGdxGame.MyInputProcessor inputProcessor = new MyGdxGame.MyInputProcessor();
      Gdx.input.setInputProcessor(inputProcessor);
      MapKey.load();
      this.camera = new OrthographicCamera();
      this.camera.setToOrtho(true);
      SAFE_ZONE_TOP = Gdx.graphics.getSafeInsetTop();
      SAFE_ZONE_BOTTOM = Gdx.graphics.getSafeInsetBottom();
      WIDTH = Gdx.graphics.getWidth();
      HEIGHT = Gdx.graphics.getHeight() - SAFE_ZONE_TOP - SAFE_ZONE_BOTTOM;
      TCanvas.ScreenSize= Rms.LoadScreenSize();
      if(TCanvas.ScreenSize == 1&&!mSystem.isPC) {
         WIDTH = (int) ((double) WIDTH / (double) 2.5F);
         HEIGHT = (int) ((double) HEIGHT / (double) 2.5F);
      }
      this.viewport = new ExtendViewport((float) WIDTH, (float) HEIGHT, this.camera);
      this.viewport.apply();
      this.camera.update();

//      me = this;
      this.g = new mGraphics(new SpriteBatch());
      gmidlet = new GameMidlet();
      mSystem.println("---- " + Gdx.app.getType());
   }

   @Override
   public void render() {
      if (isBackgroundRunning) {
         return; // Không render khi đang pause
      }

      try{
          long startTime = System.nanoTime();

          Gdx.graphics.requestRendering();
           Gdx.gl.glClearColor(0.0F, 0.0F, 0.0F, 1.0F);
            Gdx.gl.glClear(16384);
            this.g.g.setProjectionMatrix(this.camera.combined);
            this.camera.zoom = this.zoom;
            this.camera.update();
            this.update();
            this.g.begin();
            GameMidlet.gameCanvas.paint(this.g);
            this.g.end();

          long elapsedTime = System.nanoTime() - startTime;
          float elapsedTimeInSeconds = elapsedTime / 1000000000f;
          float sleepTime = TARGET_FPS - elapsedTimeInSeconds;

          if (sleepTime > 0) {
             try {
                Thread.sleep((long)(sleepTime * 1000));
             } catch (InterruptedException e) {
             }
          }
       }catch(Exception e){
           e.printStackTrace();
       }

   }

   private void update() {
      Session_ME.update();
      GameMidlet.gameCanvas.update();
   }

   public void dispose() {
      stopBackgroundThread();
   }

   public boolean keyDown(int keycode) {
      int k = MapKey.map(keycode);
      GameMidlet var10000 = GameMidlet.instance;
      GameMidlet.gameCanvas.keyPressed(keycode);
      return false;
   }

   public boolean keyUp(int keycode) {
      int k = MapKey.map(keycode);
      GameMidlet var10000 = GameMidlet.instance;
      GameMidlet.gameCanvas.keyReleased(k);
      return false;
   }

   public boolean keyTyped(char character) {
      return false;
   }

   public boolean touchDown(int screenX, int screenY, int pointer, int button) {
      return false;
   }

   public boolean touchUp(int screenX, int screenY, int pointer, int button) {
      return false;
   }

   @Override
   public boolean touchCancelled(int i, int i1, int i2, int i3) {
      return false;
   }

   public boolean touchDragged(int screenX, int screenY, int pointer) {
      return false;
   }

   public boolean mouseMoved(int screenX, int screenY) {
      return false;
   }

   @Override
   public boolean scrolled(float v, float v1) {
      return false;
   }

   public boolean scrolled(int amount) {
      return false;
   }

   public void resize(int width, int height) {
      viewport.update(width, height, true);

   }

   public void pause() {
      if(Gdx.app.getType() == Application.ApplicationType.Desktop){
         // Dừng thread cũ nếu đang chạy
         stopBackgroundThread();

         // Bắt đầu thread mới
         isBackgroundRunning = true;
         backgroundThread = new Thread(() -> {
            while (isBackgroundRunning) {
               try {
                    long startTime = System.nanoTime();
                  Session_ME.update();
                  GameMidlet.gameCanvas.update();
                  long elapsedTime = System.nanoTime() - startTime;
                  float elapsedTimeInSeconds = elapsedTime / 1000000000f;
                  float sleepTime = TARGET_FPS - elapsedTimeInSeconds;

                  if (sleepTime > 0) {
                     try {
                        Thread.sleep((long)(sleepTime * 1000));
                     } catch (InterruptedException e) {
                        break;
                     }
                  }
               } catch (Exception e) {
                  System.err.println("Background thread error: " + e.getMessage());
                  e.printStackTrace();
               }
            }
         });

         backgroundThread.setName("BackgroundUpdate");
         backgroundThread.start();
      }
   }

   public void resume() {
      stopBackgroundThread();
   }
   private void stopBackgroundThread() {
      isBackgroundRunning = false;

      if (backgroundThread != null && backgroundThread.isAlive()) {
         try {
            // Interrupt thread
            backgroundThread.interrupt();

            // Đợi thread kết thúc (tối đa 2 giây)
            backgroundThread.join(2000);

            if (backgroundThread.isAlive()) {
               System.err.println("Warning: Background thread didn't stop properly");
            }
         } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
         }
      }

      backgroundThread = null;
   }

   public static void exitApp() {
      Gdx.app.exit();
   }

   class MyGestureHandler implements GestureDetector.GestureListener {
      public float initialScale = 1.0F;

      public boolean touchDown(float x, float y, int pointer, int button) {
         return false;
      }

      public boolean zoom(float initialDistance, float distance) {
         float var10000 = initialDistance / distance;
         return true;
      }

      public boolean tap(float x, float y, int count, int button) {
         return false;
      }

      public boolean longPress(float x, float y) {
         return false;
      }

      public boolean fling(float velocityX, float velocityY, int button) {
         return false;
      }

      public boolean pan(float x, float y, float deltaX, float deltaY) {
         return false;
      }

      public boolean panStop(float x, float y, int pointer, int button) {
         return false;
      }

      public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
         return false;
      }

        @Override
        public void pinchStop() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
   }

   class MyInputProcessor implements InputProcessor {
      public boolean scrolled(int amount) {
         if (amount > 0) {
         }

         if (amount < 0) {
         }

         return true;
      }

      public boolean keyDown(int keycode) {
         int k = MapKey.map(keycode);
         if ((Gdx.input.isKeyPressed(59) || Gdx.input.isKeyPressed(60)) && keycode == 9) {
            k = 64;
         }

         if (MyGdxGame.gmidlet != null && GameMidlet.gameCanvas != null) {
            GameMidlet.gameCanvas.keyPressed(k);
         }

         return false;
      }

      public boolean keyUp(int keycode) {
         int k = MapKey.map(keycode);
         GameMidlet.gameCanvas.keyReleased(k);
         return false;
      }

      public boolean keyTyped(char character) {
         return false;
      }

      public boolean touchDown(int screenX, int screenY, int pointer, int button) {
//         int rotation = Gdx.input.getRotation();
//         int oy;
//         if (rotation == 90) {
//            oy = screenX;
//            screenX = screenY;
//            screenY = Gdx.graphics.getHeight() - oy;
//         } else if (rotation == 270) {
//            oy = screenY;
//            screenY = screenX;
//            screenX = Gdx.graphics.getWidth() - oy;
//         }

         Vector3 touch = new Vector3((float)screenX, (float)screenY, 0.0F);
         MyGdxGame.this.camera.unproject(touch);
         int delX = (int)touch.x - screenX;
         int delY = (int)touch.y - screenY;
         if (pointer < 2) {
            GameMidlet.gameCanvas.pointerPressed(screenX + delX, screenY + delY, pointer);
         }

         return false;
      }

      public boolean touchUp(int screenX, int screenY, int pointer, int button) {
//         int rotation = Gdx.input.getRotation();
//         int oy;
//         if (rotation == 90) {
//            oy = screenX;
//            screenX = screenY;
//            screenY = Gdx.graphics.getHeight() - oy;
//         } else if (rotation == 270) {
//            oy = screenY;
//            screenY = screenX;
//            screenX = Gdx.graphics.getWidth() - oy;
//         }

         Vector3 touch = new Vector3((float)screenX, (float)screenY, 0.0F);
         MyGdxGame.this.camera.unproject(touch);
         int delX = (int)touch.x - screenX;
         int delY = (int)touch.y - screenY;
         if (pointer < 2) {
            GameMidlet.gameCanvas.pointerReleased(screenX + delX, screenY + delY, pointer);
         }

         return false;
      }

      @Override
      public boolean touchCancelled(int i, int i1, int i2, int i3) {
         return false;
      }

      public boolean touchDragged(int screenX, int screenY, int pointer) {
//         int rotation = Gdx.input.getRotation();
//         int oy;
//         if (rotation == 90) {
//            oy = screenX;
//            screenX = screenY;
//            screenY = Gdx.graphics.getHeight() - oy;
//         } else if (rotation == 270) {
//            oy = screenY;
//            screenY = screenX;
//            screenX = Gdx.graphics.getWidth() - oy;
//         }

         Vector3 touch = new Vector3((float)screenX, (float)screenY, 0.0F);
         MyGdxGame.this.camera.unproject(touch);
         int delX = (int)touch.x - screenX;
         int delY = (int)touch.y - screenY;
         if (pointer < 2) {
            GameMidlet.gameCanvas.pointerDragged(screenX + delX, screenY + delY, pointer);
         }

         return false;
      }

      public boolean mouseMoved(int screenX, int screenY) {
         return false;
      }

      @Override
      public boolean scrolled(float v, float v1) {
         return false;
      }
   }
}
