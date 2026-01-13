package com.mchien.ngulong;

import android.Manifest;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ConfigurationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.mchien.ngulong.MyGdxGame;

import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import lib.Rms;
import lib.TCanvas;

public class AndroidLauncher extends AndroidApplication {
	private static final int REQUEST_CODE = 123;
	private static final int REQUEST_CODE_MANAGE_STORAGE = 124;
	public static Activity YULO;
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();


		initialize(MyGdxGame.gI(), config);
		TCanvas.ScreenSize = Rms.LoadScreenSize();
		YULO = this;
		requestPermission();
	}

	private void requestPermission() {
		List<String> permision = new ArrayList<>();
//		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R && ContextCompat.checkSelfPermission(this, Manifest.permission.MANAGE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
//			permision.add(Manifest.permission.MANAGE_EXTERNAL_STORAGE);
//		}
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
				ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ) {
			permision.add(Manifest.permission.READ_EXTERNAL_STORAGE);
			permision.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
		}
//		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//			if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_MEDIA_LOCATION) != PackageManager.PERMISSION_GRANTED ){
//				permision.add(Manifest.permission.ACCESS_MEDIA_LOCATION);
//			}
//		}
//		if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED ){
//			permision.add(Manifest.permission.RECORD_AUDIO);
//		}
		if(permision.isEmpty()) return;
		String[] per = new String[permision.size()];
		permision.toArray(per);
		ActivityCompat.requestPermissions(this, per, REQUEST_CODE);

//		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
//			// Android 11 trở lên: Sử dụng MANAGE_EXTERNAL_STORAGE
//			if (!Environment.isExternalStorageManager()) {
//				try {
//					Intent Environment = new
//							Intent().setAction(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION)
//							.setData(Uri.parse("package:" + getPackageName()));
//
//					if (Environment.resolveActivity(getPackageManager())!=null) {
//						startActivityForResult(Environment, REQUEST_CODE_MANAGE_STORAGE);
//					}
//					else {
//						Intent intent = new Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
//						startActivityForResult(intent, REQUEST_CODE_MANAGE_STORAGE);
//					}
//				} catch (Exception e) {
//					Intent intent = new Intent();
//					intent.setAction(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
//					startActivityForResult(intent, REQUEST_CODE_MANAGE_STORAGE);
//				}
//			}
//		}
	}
//	@Override
//	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//		if (requestCode == REQUEST_CODE) {
//			for(int i : grantResults){
//				if ( i == PackageManager.PERMISSION_GRANTED) {
//					// Quy?n ?? ???c c?p, th?c hi?n c?ng vi?c c?n thi?t ? ??y
//					// V¡§? d?: t?o th? m?c n?u kh?ng t?n t?i
//
//				} else {
//					// người dùng từ chối cấp quyền => out
//					System.exit(0);
//				}
//			}
//
//		}
//	}
}
