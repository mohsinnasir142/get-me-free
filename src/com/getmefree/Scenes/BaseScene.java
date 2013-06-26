package com.getmefree.Scenes;

import org.andengine.engine.Engine;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.Scene;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.getmefree.resources.ResourceManager;
import com.getmefree.Scenes.SceneManager.SceneType;

import android.app.Activity;

/* this class will be a basic representation of each created scene in our game, 
 * it is an abstract class, which will handle basic scene logic.*/

public abstract class BaseScene extends Scene {
	protected Engine engine;
	protected Activity activity;
	protected ResourceManager resourceManager;
	protected Camera camera;
	protected VertexBufferObjectManager vbom;
	
	//Constructor
	public BaseScene()
	{
		this.resourceManager = ResourceManager.getInstance();
		this.engine = resourceManager.engine;
		this.activity = resourceManager.gameActivity;
		this.camera = resourceManager.camera;
		this.vbom = resourceManager.vbom;
		createScene();
		
	}
	//Abstraction
	public abstract void createScene();
	public abstract void onBackKeyPressed();
	public abstract SceneType getSceneType();
	public abstract void disposeScene();
}
