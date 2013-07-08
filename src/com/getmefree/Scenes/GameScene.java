package com.getmefree.Scenes;

import org.andengine.engine.camera.hud.HUD;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.extension.physics.box2d.FixedStepPhysicsWorld;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.util.HorizontalAlign;
import org.andengine.util.color.Color;

import com.badlogic.gdx.math.Vector2;
import com.getmefree.Scenes.SceneManager.SceneType;

public class GameScene extends BaseScene {
	private HUD gameHUD;
	//which will be used for displaying game controller and score text.
	private Text scoretext;
	
	private int score = 0;

	private PhysicsWorld physicsworld;
	
	@Override
	public void createScene() {
		// TODO Auto-generated method stub
		createBackground();
		createHUD();
		createPhysics();
	}

	@Override
	public void onBackKeyPressed() {
		// TODO Auto-generated method stub
		SceneManager.getInstance().loadGameScene(engine);	
		}

	@Override
	public SceneType getSceneType() {
		// TODO Auto-generated method stub
		return SceneType.SCENE_GAME;
	}

	@Override
	public void disposeScene() {
		// TODO Auto-generated method stub
		camera.setHUD(null);
		camera.setCenter(30,15);
	}
	
	private void createBackground()
	{
		setBackground(new Background(Color.YELLOW));
	}

	private void createHUD()
	{
		gameHUD = new HUD();
		
		//Create scoreTest
		scoretext = new Text(20,420,resourceManager.font,"Score: 0123456789", new TextOptions(HorizontalAlign.LEFT),vbom);
		scoretext.setX(0);
		scoretext.setY(0);
		//scoretext.setAchorCenter(0,0);
		scoretext.setText("Score: 0");
		gameHUD.attachChild(scoretext);
		camera.setHUD(gameHUD);
	}
	private void addToScore(int i)
	{
		score +=i;
		scoretext.setText("Score : "+ score);
	}
	
	private void createPhysics()
	{
	/* We used FixedStepPhysicsWorld class, this class tries to achieve a specific amount of steps per second (in our case 60) 
	 * second parameter is our gravity parameter */
	
		physicsworld = new FixedStepPhysicsWorld(60,new Vector2(0,-17), false);
		registerUpdateHandler(physicsworld);
	}
}
