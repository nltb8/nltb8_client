package com.mchien.ngulong.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mchien.ngulong.MyGdxGame;
import lib.Rms;
import lib.TCanvas;

public class DesktopLauncher {
	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.backgroundFPS = 27;
		config.foregroundFPS = 27;
		config.title = "Quy Tụ Anh Hùng";
		config.resizable = false;

//		config.addIcon("icon/iconapp16x16.png", Files.FileType.Internal);
//		config.addIcon("icon/iconapp32x32.png", Files.FileType.Internal);
//		config.addIcon("icon/iconapp64x64.png", Files.FileType.Internal);
//		config.addIcon("icon/iconapp128x128.png", Files.FileType.Internal);

		config.addIcon("icon/icon32_1.png", Files.FileType.Internal);
		config.addIcon("icon/icon128_1.png", Files.FileType.Internal);
		LwjglApplication app = new LwjglApplication(MyGdxGame.gI(), config);
		TCanvas.ScreenSize = Rms.LoadScreenSize();
		if (TCanvas.ScreenSize == 1) {
			config.width = 400;
			config.height = 300;
		} else {
			config.width = 1080;
			config.height = 650;
		}

	}
}