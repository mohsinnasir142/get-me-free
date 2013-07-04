package com.getmefree.Scenes;

import org.andengine.engine.Engine;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.ui.IGameInterface.OnCreateSceneCallback;

import com.getmefree.resources.ResourceManager;


/* This class will provide ways to manage the scenes,
 * switching between them, keeping track of current scene, scene type etc */


public class SceneManager {

	// Scenes
	private BaseScene SplashScene;
	private BaseScene SplashScene2;
	private BaseScene MenuScene;
	private BaseScene LoadingScene;
	private BaseScene GameScene;

	private static final SceneManager INSTANCE = new SceneManager();
	//First currentScene type will be Splash Screen.
	private SceneType currentSceneType = SceneType.SCENE_SPLASH;
	
	private BaseScene currentScene;

	private Engine engine = ResourceManager.getInstance().engine;
	//Creating an enumeration of all of our scenes, which will help us to manage all of the scenes.
	public enum SceneType
	{
		SCENE_SPLASH,
		SCENE_SPLASH2,
		SCENE_MENU,
		SCENE_LOADING,
		SCENE_GAME
	}
//Method to set the current scene within our game.
	public void setScene(BaseScene scene)
	{
		engine.setScene(scene);
		currentScene = scene;
		currentSceneType = scene.getSceneType();
		
	}
//Method to set the currentSceneType
	public void setScene(SceneType scenetype)
	{
		switch(scenetype)
		{
		case SCENE_MENU:
			setScene(MenuScene);
			break;
		case SCENE_SPLASH:
			setScene(SplashScene);
			break;
		case SCENE_SPLASH2:
			setScene(SplashScene2);
			break;
		case SCENE_LOADING:
			setScene(LoadingScene);
			break;
		case SCENE_GAME:
			setScene(GameScene);
			break;
		default:
			break;
		}
	}
	//return instance of the SceneManager Class
	public static SceneManager getInstance()
	{
		return INSTANCE;
	}
	//Method to return the currently set scene type.	
	public SceneType getCurrentSceneType()
	{
		return currentSceneType;
	}
	
	//Method to return the currently set scene.
	public BaseScene getCurrentScene()
	{
		return currentScene;
	}
	//method responsible for initializing scene. 
	//In the parameter we will use OnCreateSceneCallback, because we will need it in the activity.
	public void createSplashScene(OnCreateSceneCallback pOnCreateSceneCallback)
	{
	    ResourceManager.getInstance().loadSplashScreen();
	    SplashScene = new SplashScene();
	    currentScene = SplashScene;
	    pOnCreateSceneCallback.onCreateSceneFinished(SplashScene);
	}
	//method responsible for disposing splash scene
	//(to dispose it when it will not be longer needed, which means after we successfully loaded menu resources)
	private void disposeSplashScene()
	{
	    ResourceManager.getInstance().unloadSplashScreen();
	    SplashScene.disposeScene();
	    SplashScene = null;
	}
	
	public void createMenuScene()
	{
		ResourceManager.getInstance().loadMenuResources();
		MenuScene = new MainMenuScene();
		LoadingScene = new LoadingScene();
		SceneManager.getInstance().setScene(MenuScene);
		disposeSplashScene();
	}
	
	public void loadGameScene(final Engine mEngine)
	{
		setScene(LoadingScene);
		ResourceManager.getInstance().unloadMenuTextures();
//		mEngine.registerUpdateHandler(new TimerHandler(1.1f, new ITimerCallback() {
//			
//			@Override
//			public void onTimePassed(TimerHandler pTimerHandler) {
//				// TODO Auto-generated method stub
//				mEngine.unregisterUpdateHandler(pTimerHandler);
//				ResourceManager.getInstance().loadGameResources();
//				GameScene = new GameScene();
//				setScene(GameScene);
//			}
//		}));
	}
	// loading back to menu scene from game scene.
	public void loadingMenuScene(final Engine mEngine)
	{
		setScene(MenuScene);
		GameScene.disposeScene();
		ResourceManager.getInstance().unloadGameTexture();
		mEngine.registerUpdateHandler(new TimerHandler(0.1f,new ITimerCallback() {
			
			@Override
			public void onTimePassed(TimerHandler pTimerHandler) {
				// TODO Auto-generated method stub
					mEngine.unregisterUpdateHandler(pTimerHandler);
					ResourceManager.getInstance().loadMenuResources();
					setScene(MenuScene);
					
			}
		}));
	}
}
