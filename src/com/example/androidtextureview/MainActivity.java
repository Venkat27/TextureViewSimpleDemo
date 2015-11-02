package com.example.androidtextureview;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.TextureView;
import android.view.TextureView.SurfaceTextureListener;
import android.widget.FrameLayout;
import java.io.IOException;

@SuppressLint("NewApi")
public class MainActivity extends Activity implements SurfaceTextureListener {

	private TextureView mTexture;
	private Camera mCamera;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mTexture = new TextureView(this);
		mTexture.setSurfaceTextureListener(this);
		setContentView(mTexture);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@SuppressLint("NewApi")
	@Override
	public void onSurfaceTextureAvailable(SurfaceTexture arg0, int arg1,
			int arg2) {
		mCamera = Camera.open();
		Camera.Size previewSize = mCamera.getParameters().getPreviewSize();
		mTexture.setLayoutParams(new FrameLayout.LayoutParams(
				previewSize.width, previewSize.height, Gravity.CENTER));
		try {
			mCamera.setPreviewTexture(arg0);
		} catch (IOException t) {
		}
		mCamera.startPreview();
		mTexture.setAlpha(1.0f);
		mTexture.setRotation(90.0f);
	}

	@Override
	public boolean onSurfaceTextureDestroyed(SurfaceTexture arg0) {
		mCamera.stopPreview();
		mCamera.release();
		return true;
	}

	@Override
	public void onSurfaceTextureSizeChanged(SurfaceTexture arg0, int arg1,
			int arg2) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onSurfaceTextureUpdated(SurfaceTexture arg0) {
		// TODO Auto-generated method stub
	}
}