package com.getmefree.resources;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.getmefree.Activity.GameActivity;

/*this class is responsible for loading/unloading our game resources (art, fonts, audio) 
 * it will also provide references to the most commonly needed objects
 * (camera, context, engine, VertexBufferObjectManager)
 */
public class ResourceManager {

	private static final ResourceManager INSTANCE = new ResourceManager();

	public Engine engine;
	public GameActivity gameActivity;
	public Camera camera;
	public VertexBufferObjectManager vbom;
	/*
	 * It creates "buffer objects" for vertex attributes in high-performance
	 * memory on the server side and provides same access functions to reference
	 * the arrays.
	 */

	public ITextureRegion splash_region;
	// TextureRegions are the areas inside a Texture that are used for the
	// sprite.
	
	public ITextureRegion menu_region;
	public ITextureRegion new_game_region;
	public ITextureRegion setting_region;
	public ITextureRegion Help_region;
	public ITextureRegion Exit_region;
	
	private BuildableBitmapTextureAtlas menuTextureAtlas;
	
	private BitmapTextureAtlas splashTextureAtlas;

	// BitmapTextureAtlas is the picture that is loaded into the memory. It will
	// be the final product.

	public static ResourceManager getInstance() {
		return INSTANCE;
	}

	public void loadMenuResources() {
		loadMenuGraphics();
		loadMenuAudio();
		loadMenuFonts();
	}

	public void loadGameResources() {
		loadGameGraphics();
		loadGameAudio();
		loadGameFonts();
	}

	// separate methods responsible for loading graphics, fonts and sounds
	private void loadGameGraphics() {

	}

	private void loadMenuGraphics() {

	}

	private void loadGameFonts() {

	}

	private void loadMenuFonts() {

	}

	private void loadGameAudio() {

	}

	private void loadMenuAudio() {

	}

	public void loadSplashScreen() {
		// BitmapTextureAtlasTextureRegionFactory is the device built into AndEngine that puts the textures together.
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		// new BitmapTextureAtlas(pTextureManager, pWidth, pHeight,pTextureOptions)
		splashTextureAtlas = new BitmapTextureAtlas(
				gameActivity.getTextureManager(), 512, 256,
				TextureOptions.BILINEAR);
		// BILINEAR is Slower than NEAREST but looks Better.
		// createFromAsset(pBitmapTextureAtlas, pContext, pAssetPath, pTextureX,  pTextureY)
		splash_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				splashTextureAtlas, gameActivity, "splashScene.jpg", 0, 0);
		/*the pBitmapTextureAtlas parameter specifies the texture atlas which we'd like the ITextureRegion object to be stored in.
		 * The pContext parameter allows the class to open the image from the gfx/ folder. 
		 * The pAssetPath parameter defines the name of the specific file we're looking for e.g splashScene.jpg. 
		 * And the final two parameters, pTextureX and pTextureY, define the location on the texture atlas
		 *  in which to place the ITextureRegion object. */
		splashTextureAtlas.load();
//load our ITextureRegion objects into memory.
	}

	public void unloadSplashScreen() {
		splashTextureAtlas.unload();
		//unload our ITextureRegion objects into memory
		splash_region = null;
	}

	// a prepareManager method -- use this method while initializing our game.
	public static void prepareManager(Engine engine, GameActivity gameActivity,
			Camera camera, VertexBufferObjectManager vbom) {
		getInstance().engine = engine;
		getInstance().camera = camera;
		getInstance().gameActivity = gameActivity;
		getInstance().vbom = vbom;

	}
}
