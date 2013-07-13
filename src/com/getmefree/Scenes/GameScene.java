package com.getmefree.Scenes;

import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.camera.hud.HUD;
import org.andengine.engine.camera.hud.controls.BaseOnScreenControl;
import org.andengine.engine.camera.hud.controls.DigitalOnScreenControl;
import org.andengine.engine.camera.hud.controls.BaseOnScreenControl.IOnScreenControlListener;
import org.andengine.engine.handler.physics.PhysicsHandler;
import org.andengine.entity.modifier.MoveXModifier;
import org.andengine.entity.modifier.MoveYModifier;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.AutoParallaxBackground;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.scene.background.ParallaxBackground.ParallaxEntity;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ScaleMenuItemDecorator;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.entity.util.FPSLogger;
import org.andengine.extension.physics.box2d.FixedStepPhysicsWorld;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.extension.physics.box2d.util.Vector2Pool;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.util.GLState;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.HorizontalAlign;
import org.andengine.util.color.Color;
import org.andengine.entity.sprite.ButtonSprite;
import org.andengine.entity.sprite.ButtonSprite.OnClickListener;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.hardware.SensorManager;
import android.opengl.GLES20;
import android.view.MotionEvent;
import android.widget.Toast;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

import com.getmefree.Activity.GameActivity;
import com.getmefree.Scenes.SceneManager.SceneType;
import com.getmefree.other.Bullet;
import com.getmefree.other.BulletPool;

public class GameScene extends BaseScene implements OnClickListener {
	private HUD gameHUD;
	//which will be used for displaying game controller and score text.
	private Text scoretext;
	
	private int score = 0;

	private PhysicsWorld physicsworld;
	
	public DigitalOnScreenControl mDigitalOnScreenControl;
	public Sprite jump;
	public Sprite fire;
	
	public PhysicsHandler physicsHandler;
	
	public AnimatedSprite player;
	
	public LinkedList bulletList;
	public int bulletcount;
	
	private PhysicsWorld mPhysicsWorld;
	
	public GameScene()
	{
		bulletList = new LinkedList();
	}
	
	@Override
	public void createScene() {
		// TODO Auto-generated method stub
		createBackground();
		//createParallaxBackground();
		createParallaxBackgroundwithdigitaltouchpad();
		levelloader();
		createHUD();
		createPhysics();
		createParallaxBackground();
		createHostages();
		createEnemy();
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

	private void createParallaxBackgroundwithdigitaltouchpad()
	{	
		final AutoParallaxBackground autoParallaxBackground = new AutoParallaxBackground(0, 0, 0, 0);
		autoParallaxBackground.attachParallaxEntity(new ParallaxEntity(0.0f, new Sprite(0, 480 - resourceManager.mParallaxLayerBack.getHeight(), resourceManager.mParallaxLayerBack, vbom)));
		autoParallaxBackground.attachParallaxEntity(new ParallaxEntity(-5.0f, new Sprite(0, 80, resourceManager.mParallaxLayerMid, vbom)));
		autoParallaxBackground.attachParallaxEntity(new ParallaxEntity(-10.0f, new Sprite(0, 480 - resourceManager.mParallaxLayerFront.getHeight(), resourceManager.mParallaxLayerFront, vbom)));
		setBackground(autoParallaxBackground);
		
//		final Scene scene = new Scene();
//		scene.setBackground(new Background(0.09804f, 0.6274f, 0.8784f));

jump = new ButtonSprite(630, 400, resourceManager.jump_button_texture_region,vbom,new OnClickListener() {
	
	@Override
	public void onClick(ButtonSprite pButtonSprite, float pTouchAreaLocalX,
			float pTouchAreaLocalY) {
		// TODO Auto-generated method stub
		jump.setScale(1.2f);
		jump(player);

	}
});

fire = new ButtonSprite(700, 400, resourceManager.fire_button_texture_region,vbom,new OnClickListener() {
	
	@Override
	public void onClick(ButtonSprite pButtonSprite, float pTouchAreaLocalX,
			float pTouchAreaLocalY) {
		// TODO Auto-generated method stub

		fire.setScale(1.2f);
		createBullets();
	}
});	
registerTouchArea(jump);
registerTouchArea(fire);
	setTouchAreaBindingOnActionDownEnabled(true);
		attachChild(jump);
		attachChild(fire);
		
		
		final float playerX = (800 - resourceManager.player1_texture_region.getWidth()) / 2;
		final float playerY = 480 - resourceManager.player1_texture_region.getHeight() - 5;

		/* Create two sprite and add it to the scene. */
		 player = new AnimatedSprite(playerX, playerY, resourceManager.player1_texture_region, vbom){
			@Override
			protected void preDraw(GLState pGLState, Camera pCamera) {
				// TODO Auto-generated method stub
				super.preDraw(pGLState, pCamera);
				pGLState.enableDither();
			}
		};	
		
		player.setScaleCenterY(resourceManager.player1_texture_region.getHeight());
		player.setScale(2);
		//player.animate(new long[]{200, 200, 200}, 3, 5, true);
		physicsHandler = new PhysicsHandler(player);
		player.registerUpdateHandler(physicsHandler);

		attachChild(player);


		
		mDigitalOnScreenControl = new DigitalOnScreenControl(0, 480 - resourceManager.touch_texture.getHeight(), camera, resourceManager.touch_texture, resourceManager.touchKnob_texture, 0.1f, vbom, new IOnScreenControlListener() {
			@Override
			public void onControlChange(final BaseOnScreenControl pBaseOnScreenControl, final float pValueX, final float pValueY) {
				//physicsHandler.setVelocity(pValueX * 100, pValueY * 100);
	
//				long animationTime=50;
//                long[] ANIMATE_DURATION = new long[3];
//                for(int i=0;i<3;i++)
//                {
//                        ANIMATE_DURATION[i]=animationTime;
//                }
//                if (pValueY == 1){
//                    // Up
//                    if (playerDirection != PlayerDirection.UP){
//                            player.animate(ANIMATE_DURATION, 0, 2, true);
//                            playerDirection = PlayerDirection.UP;
//                    }
//            }else if (pValueY == -1){
//                    // Down
//                    if (playerDirection != PlayerDirection.DOWN){
//                            player.animate(ANIMATE_DURATION, 9, 11, true);
//                            playerDirection = PlayerDirection.DOWN;
//                    }
//            }else if (pValueX == -1){
//                    // Left
//                    if (playerDirection != PlayerDirection.LEFT){
//                            player.animate(ANIMATE_DURATION, 3, 5, true);
//                            playerDirection = PlayerDirection.LEFT;
//                    }
//            }else if (pValueX == 1){
//                    // Right
//                    if (playerDirection != PlayerDirection.RIGHT){
//                            player.animate(ANIMATE_DURATION, 6, 8, true);
//                            playerDirection = PlayerDirection.RIGHT;
//                    }
//            }else{
//                    if (player.isAnimationRunning()){
//                            player.stopAnimation();
//                            playerDirection = PlayerDirection.NONE;
//                    }
//            }
//            // Set the player's velocity
//            mPlayerBody.setLinearVelocity(pValueX * PLAYER_VELOCITY, pValueY * PLAYER_VELOCITY);
//    
				
				
				
				
				if(pValueX==1){
					
					
					player.animate(new long[]{0,40,40},17,19,true);
					//player.animate(pFrameDurations, pFirstTileIndex, pLastTileIndex, pLoop)
					autoParallaxBackground.setParallaxChangePerSecond(4);

					
				}
				
				if(pValueX==-1){
					
			
					player.animate(new long[]{0,40,40}, 9,11,true);
					autoParallaxBackground.setParallaxChangePerSecond(-4);
					
					
				}
				
				if(pValueY==1){
					
					
					player.animate(new long[]{0,40,40}, 6,8,true);
					autoParallaxBackground.setParallaxChangePerSecond(0);
					
					
				}
				if(pValueY==-1){
					
				
					player.animate(new long[]{0,40,40}, 0,2,true);
					autoParallaxBackground.setParallaxChangePerSecond(0);
					
					
				}
//				if(pValueY==0 && pValueX==0 && player.isAnimationRunning()){
//					
//					
//					player.stopAnimation();
//					autoParallaxBackground.setParallaxChangePerSecond(0);
//				}
//				
				
				
			}
		});
		mDigitalOnScreenControl.getControlBase().setBlendFunction(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
		mDigitalOnScreenControl.getControlBase().setAlpha(0.5f);
		mDigitalOnScreenControl.getControlBase().setScaleCenter(0, 128);
		mDigitalOnScreenControl.getControlBase().setScale(1.25f);
		mDigitalOnScreenControl.getControlKnob().setScale(1.25f);
		mDigitalOnScreenControl.refreshControlKnobPosition();

		
		setChildScene(mDigitalOnScreenControl);
	}

	private void createParallaxBackground()
	{


		/* Calculate the coordinates for the face, so its centered on the camera. */
//		final AnimatedSprite enemy = new AnimatedSprite(playerX - 80, playerY, resourceManager.player2_texture_region, vbom);
//		enemy.setScaleCenterY(resourceManager.player2_texture_region.getHeight());
//		enemy.setScale(2);
//		enemy.animate(new long[]{200, 200, 200}, 3, 5, true);

	//	attachChild(enemy);

		
	}
	
	private void levelloader()
	{
		
	}
	
	private void createEnemy()
	{
		
	}
	
	private void createHostages()
	{
		
	}
	private void createBullets()
	{
//
//	    if(!moveable)
//			return;
//		GameScene scene = (GameScene) BaseActivity.getSharedInstance().mCurrentScene;
		 Bullet b = BulletPool.sharedBulletPool().obtainPoolItem();		
		 b.sprite.setPosition(player.getX() + player.getWidth()/2, player.getY()-4);
/*MoveXModifier, an entity modifier is a something that you register on entities [sprite/shapes] 
 *and when you register them they do some modifications, 
 *there are modifiers that rotates and others moves entities in certain ways.*/
		// MoveYModifier mod = new MoveYModifier(pDuration, pFromX, pToX);
		 MoveXModifier mod = new MoveXModifier(1.5f,b.sprite.getY(),b.sprite.getX()+500);
/*Our Modifier moves an entity along the Y axis by providing the duration 
 * it should move across between the initial and final positions you provide.
 * */		
		 b.sprite.setVisible(true);
		 b.sprite.detachSelf();
		 attachChild(b.sprite);
		 bulletList.add(b);
		 b.sprite.registerEntityModifier(mod);
		 bulletcount++;
	}

	public void jump(AnimatedSprite playerr)
	{
		mPhysicsWorld = new PhysicsWorld(new Vector2(0, SensorManager.GRAVITY_EARTH), false);
		final FixtureDef wallFixtureDef = PhysicsFactory.createFixtureDef(0,0,0);
		PhysicsFactory.createBoxBody(mPhysicsWorld, playerr, BodyType.StaticBody, wallFixtureDef);

			final Body playerBody = (Body)playerr.getUserData();

			final Vector2 velocity = Vector2Pool.obtain(playerBody.getLinearVelocity().x,12);
			playerBody.setLinearVelocity(velocity);
			//playerBody.setLinearVelocity(new Vector2(playerBody.getLinearVelocity().x, 12)); 
			Vector2Pool.recycle(velocity);
		
		//body = PhysicsFactory.createBoxBody(physicsworld, this, BodyType.DynamicBody, PhysicsFactory.createFixtureDef(0, 0, 0));

		//body.setUserData("player");
		//body.setFixedRotation(true);
	    
	//	physicsHandler.setLinearVelocity(new Vector2(body.getLinearVelocity().x, 12));
		//pl.setLinearVelocity(new Vector2(body.getLinearVelocity().x, 12)); 
	}

	@Override
	public void onClick(ButtonSprite pButtonSprite, float pTouchAreaLocalX,
			float pTouchAreaLocalY) {
		// TODO Auto-generated method stub
		
	}

}
