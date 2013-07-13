package com.getmefree.Activity;


import com.getmefree.Scenes.GameScene;
import com.getmefree.Scenes.SceneManager;
import com.getmefree.resources.ResourceManager;

import org.andengine.engine.Engine;
import org.andengine.engine.LimitedFPSEngine;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.WakeLockOptions;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.ui.activity.BaseGameActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.provider.Settings.System;
import android.util.DisplayMetrics;
import android.view.KeyEvent;

public class GameActivity extends BaseGameActivity {

	private Camera camera;
	private ResourceManager resourceManager;
	private GameScene gameScene;
	private static final int DIALOG_ALLOWDIAGONAL_ID = 0;
	@Override
	public Engine onCreateEngine(EngineOptions pEngineOptions) {
		// TODO Auto-generated method stub

		// LimitedFPSEngine: Limited Frames Per Second Engine tries to achieve a
		// specific amount of updates per second.
		return new LimitedFPSEngine(pEngineOptions, 60);
	}

	@Override
	public EngineOptions onCreateEngineOptions() {
		// TODO Auto-generated method stub

		DisplayMetrics displaymetrics = new DisplayMetrics();
		// DisplayMetrics is a structure describing general information about a
		// display, such as its size, density, and font scaling.

		getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		// Window Manager Class and Display Class: Return the Display upon which
		// WindowManager is created.

		int CAMERA_WIDTH = displaymetrics.widthPixels;
		int CAMERA_HEIGHT = displaymetrics.heightPixels;

		// new Camera(pX, pY, pWidth, pHeight) --> pX and pY position of the
		// Platform.
		//camera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
		camera = new Camera(0,0,800,480);
		// Camera is the rectangle of the scene that is drawn on the screen.

		// EngineOptions engineOptions = new EngineOptions(pFullscreen,
		// pScreenOrientation, pResolutionPolicy, pCamera)
		EngineOptions engineOptions = new EngineOptions(true,
				ScreenOrientation.LANDSCAPE_FIXED, new RatioResolutionPolicy(
						CAMERA_WIDTH, CAMERA_HEIGHT), this.camera);
		// Enabling the Music and Sound in Game.
		engineOptions.getAudioOptions().setNeedsMusic(true).setNeedsSound(true);
		// Screen will remain awake while using.
		engineOptions.setWakeLockOptions(WakeLockOptions.SCREEN_ON);
		//enable dithering for the whole engine.
		//engineOptions.getRenderOptions().setDithering(true);
		
		return engineOptions;
	}

	// Resources include textures, sounds and music, and fonts.
	@Override
	public void onCreateResources(
			OnCreateResourcesCallback pOnCreateResourcesCallback)
			throws Exception {
		// initialize the resources manager and pass the required parameters in
		// prepareManager to ResourceManager

		ResourceManager.prepareManager(mEngine, this, camera,
				getVertexBufferObjectManager());
		resourceManager = ResourceManager.getInstance();
		pOnCreateResourcesCallback.onCreateResourcesFinished();

	}// we are required to make a call to the respective method callbacks in
		// order to proceed through the application's life cycle
		// A callback tells our program we are done with the method, and it
		// should move on to the next method.

	@Override
	public void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback)
			throws Exception {
		// TODO Auto-generated method stub
		SceneManager.getInstance().createSplashScene(pOnCreateSceneCallback);

	}

	@Override
	public void onPopulateScene(Scene pScene,
			OnPopulateSceneCallback pOnPopulateSceneCallback) throws Exception {
		// TODO Auto-generated method stub
		mEngine.registerUpdateHandler(new TimerHandler(0.4f, new ITimerCallback()
		{
	            public void onTimePassed(final TimerHandler pTimerHandler) 
	            {
	                mEngine.unregisterUpdateHandler(pTimerHandler);
	                SceneManager.getInstance().createMenuScene();
	            }
	    }));
	    pOnPopulateSceneCallback.onPopulateSceneFinished();
	}
	
	@Override
	public void onGameCreated() {
		this.showDialog(DIALOG_ALLOWDIAGONAL_ID);
	}

	@Override
	protected Dialog onCreateDialog(final int pID) {
		// TODO Auto-generated method stub
		switch(pID) {
		case DIALOG_ALLOWDIAGONAL_ID:
			return new AlertDialog.Builder(this)
			.setTitle("Setup...")
			.setMessage("Do you wish to allow diagonal directions on the OnScreenControl?")
			.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(final DialogInterface pDialog, final int pWhich) {
					gameScene.mDigitalOnScreenControl .setAllowDiagonal(true);
				}
			})
			.setNegativeButton("No", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(final DialogInterface pDialog, final int pWhich) {
					gameScene.mDigitalOnScreenControl.setAllowDiagonal(false);
				}
			})
			.create();
	}
	return super.onCreateDialog(pID);
	}



	
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		java.lang.System.exit(0);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(keyCode == KeyEvent.KEYCODE_BACK)
		{
			SceneManager.getInstance().getCurrentScene().onBackKeyPressed();
		}
		
		return false;
	}
	
	

}
