package com.getmefree.Scenes;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ScaleMenuItemDecorator;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.util.GLState;

import android.R.menu;

import com.getmefree.Scenes.SceneManager.SceneType;

public class MainMenuScene extends BaseScene implements IOnMenuItemClickListener {

	private MenuScene menuScene;
	private final int MENU_PLAY = 0;
	private final int MENU_CONTINUE = 1;
	private final int MENU_SETTINGS = 2;
	private final int MENU_HIGH_SCORE = 3;
	private final int MENU_INSTRUCTIONS = 4;
	private final int MENU_EXIT = 5;
	
	@Override
	public void createScene() {
		// TODO Auto-generated method stub
		createBackground();
		createMenuChildScene();
	}

	@Override
	public void onBackKeyPressed() {
		// TODO Auto-generated method stub
		System.exit(0);
	}

	@Override
	public SceneType getSceneType() {
		// TODO Auto-generated method stub
		return SceneType.SCENE_MENU;
	}

	@Override
	public void disposeScene() {
		// TODO Auto-generated method stub
		
	}
	
	private void createBackground(){
		attachChild(new Sprite(30,15,resourceManager.menu_region,vbom)
		{
			@Override
			protected void preDraw(GLState pGLState, Camera pCamera) {
				// TODO Auto-generated method stub
				super.preDraw(pGLState, pCamera);
				pGLState.enableDither();
		}
			
		});
	}

	private void createMenuChildScene()
	{
		menuScene = new MenuScene(camera);
		menuScene.setPosition(0,50);
		final IMenuItem playMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_PLAY, resourceManager.play_region, vbom),1.2f, 1);
		final IMenuItem continueMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_CONTINUE, resourceManager.continue_region, vbom),1.2f, 1);
		final IMenuItem settingsMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_SETTINGS, resourceManager.setting_region, vbom),1.2f, 1);
		final IMenuItem highscoreMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_HIGH_SCORE, resourceManager.high_score_region, vbom),1.2f, 1);
		final IMenuItem instructionsMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_INSTRUCTIONS, resourceManager.instructions_region, vbom),1.2f, 1);
		final IMenuItem exitMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_EXIT, resourceManager.exit_region, vbom),1.2f, 1);
		
		menuScene.addMenuItem(playMenuItem);
		menuScene.addMenuItem(continueMenuItem);
		menuScene.addMenuItem(settingsMenuItem);
		menuScene.addMenuItem(highscoreMenuItem);
		menuScene.addMenuItem(instructionsMenuItem);
		menuScene.addMenuItem(exitMenuItem);
		
		menuScene.buildAnimations();
		menuScene.setBackgroundEnabled(false);
		
		playMenuItem.setPosition(playMenuItem.getX()+20, playMenuItem.getY()+40);
		continueMenuItem.setPosition(playMenuItem.getX()-2, continueMenuItem.getY()+40);
		settingsMenuItem.setPosition(playMenuItem.getX(), settingsMenuItem.getY()+40);
		highscoreMenuItem.setPosition(playMenuItem.getX(), highscoreMenuItem.getY()+40);
		instructionsMenuItem.setPosition(playMenuItem.getX(), instructionsMenuItem.getY()+40);
		exitMenuItem.setPosition(exitMenuItem.getX()+360, exitMenuItem.getY()-30);
		
		menuScene.setOnMenuItemClickListener(this);
		setChildScene(menuScene);
	}

	@Override
	public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem,
			float pMenuItemLocalX, float pMenuItemLocalY) {
		// TODO Auto-generated method stub
		switch(pMenuItem.getID())
		{
		case MENU_PLAY:
			SceneManager.getInstance().loadGameScene(engine);
			return true;
		case MENU_CONTINUE:
			return true;
		case MENU_SETTINGS:
			return true;
		case MENU_HIGH_SCORE:
			return true;
		case MENU_INSTRUCTIONS:
			return true;
		case MENU_EXIT:
			return true;
		
		
		default:
			return false;
		}
	}
}
