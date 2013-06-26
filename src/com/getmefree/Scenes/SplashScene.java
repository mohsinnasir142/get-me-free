package com.getmefree.Scenes;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.util.GLState;

import com.getmefree.Scenes.SceneManager.SceneType;

public class SplashScene extends BaseScene {

	private Sprite splash;
	
	@Override
	public void createScene() {
		// TODO Auto-generated method stub
	/*The Sprite uses a texture  which is taken from a TextureRegion.
	 * First and second parameter - sprite`s [X, Y] coordinates / / third parameter - ITextureRegion of the sprite
	 * forth parameter - vertex buffer object manager, you will probably need most common one, which can be received by: mEngine*/
		splash = new Sprite(0,0,resourceManager.splash_region,vbom){	
//Having problem with poor texture quality and banding in GLES2 
//just override preDraw() of your Sprite and enable dithering.
			@Override
			protected void preDraw(GLState pGLState, Camera pCamera) {
				// TODO Auto-generated method stub
				super.preDraw(pGLState, pCamera);
				pGLState.enableDither();
			}
		};
		
		splash.setScale(1.5f);
		//Sets the scale factor to be used when displaying a splash video or image.
		//When using a value of 0 the scale factor is chosen such that the window is filled (default).
		splash.setPosition(240, 160);
		//Sets the position of the splash window. 
		//This only has effect when is a separate normal splash window is used. Default Windows determines the position.
		attachChild(splash);
		//attach sprite to the scene.
	}

	@Override
	public void onBackKeyPressed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SceneType getSceneType() {
		// TODO Auto-generated method stub
		return SceneType.SCENE_SPLASH;
	}

	@Override
	public void disposeScene() {
		// TODO Auto-generated method stub
		splash.detachSelf();
	    splash.dispose();
	    //dispose of the splash sprite, and detach it from the scene.
	    this.detachSelf();
	    this.dispose();
		
	}

}
