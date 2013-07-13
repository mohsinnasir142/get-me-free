package com.getmefree.Scenes;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.text.Text;
import org.andengine.util.color.Color;

import com.getmefree.Scenes.SceneManager.SceneType;

public class LoadingScene extends BaseScene {

	@Override
	public void createScene() {
		// TODO Auto-generated method stub
		setBackground(new Background(Color.BLACK));
		//attachChild(new Text(400, 120, resourceManager.font, "Loading....",vbom));
		/* Quickly twinkling face. */
		//final AnimatedSprite loadingsprite = new AnimatedSprite(10, 50, resourceManager.loading_region,vbom);
		//loadingsprite.animate(100);
		//attachChild(loadingsprite);

	}

	@Override
	public void onBackKeyPressed() {
		// TODO Auto-generated method stub
		//means we do not perform any actions
		return;
	}

	@Override
	public SceneType getSceneType() {
		// TODO Auto-generated method stub
		return SceneType.SCENE_LOADING;
	}

	@Override
	public void disposeScene() {
		// TODO Auto-generated method stub
		
	}

}
