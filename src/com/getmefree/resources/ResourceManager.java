package com.getmefree.resources;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder.TextureAtlasBuilderException;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;
import org.andengine.util.debug.Debug;

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
	
	public Font font;
	/*
	 * It creates "buffer objects" for vertex attributes in high-performance
	 * memory on the server side and provides same access functions to reference
	 * the arrays.
	 */

	public ITextureRegion splash_region;
	// TextureRegions are the areas inside a Texture that are used for the
	// sprite.
	
	public ITextureRegion menu_region;
	
	// buttons
	public ITextureRegion play_region;
	public ITextureRegion continue_region;
	public ITextureRegion high_score_region;
	public ITextureRegion setting_region;
	public ITextureRegion instructions_region;
	public ITextureRegion exit_region;
	
	public TiledTextureRegion loading_region;
	
	private BuildableBitmapTextureAtlas menuTextureAtlas;
	
	private BitmapTextureAtlas splashTextureAtlas;
	
	private BuildableBitmapTextureAtlas loadingTextureAtlas;
	
	private BitmapTextureAtlas playerTextureAtlas;
	private BitmapTextureAtlas touchtextureAtlas;
	
	public ITextureRegion player_texture_region;
	public ITextureRegion touch_texture;
	public ITextureRegion touchKnob_texture;
	
	
	private BitmapTextureAtlas animatedsprite_texture_atlas;
	
	public TiledTextureRegion player1_texture_region;
	public TiledTextureRegion player2_texture_region;
	
	private BitmapTextureAtlas BackgroundTexture;

	public ITextureRegion mParallaxLayerBack;
	public ITextureRegion mParallaxLayerMid;
	public ITextureRegion mParallaxLayerFront;

	//public ITexture mainFontTexture;
	// BitmapTextureAtlas is the picture that is loaded into the memory. It will
	// be the final product.
	
	private BitmapTextureAtlas bullettextureAtlas;
	
	public ITextureRegion bullet_texture_region;
	
	private BitmapTextureAtlas enemytextureAtlas;
	
	public ITextureRegion enemy_texture_region;
	private BitmapTextureAtlas hostagetextureAtlas;
	
	public ITextureRegion hostage_texture_region;

	private BitmapTextureAtlas firebuttontextureAtlas;
	
	public ITextureRegion fire_button_texture_region;
	public ITextureRegion jump_button_texture_region;
	
	public static ResourceManager getInstance() {
		return INSTANCE;
	}

	public void loadMenuResources() {
		loadMenuGraphics();
		loadMenuAudio();
		loadMenuFonts();
		loadGameLoadingSprite();
	}

	public void loadGameResources() {
		loadGameGraphics();
		loadGameAudio();
		loadGameFonts();
	}

	// separate methods responsible for loading graphics, fonts and sounds
	private void loadGameGraphics() {
		loadGameResource();
		loadBackgroundResource();
	}

	private void loadGameResource(){
	BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");


	touchtextureAtlas = new BitmapTextureAtlas(gameActivity.getTextureManager(), 256, 128, TextureOptions.BILINEAR);
	touch_texture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(touchtextureAtlas,gameActivity, "onscreen_controlbase.png", 0, 0);
	touchKnob_texture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(touchtextureAtlas, gameActivity, "onscreen_control_knob.png", 128, 0);
	touchtextureAtlas.load();
	
	
	firebuttontextureAtlas = new BitmapTextureAtlas(gameActivity.getTextureManager(), 128, 128,TextureOptions.BILINEAR);
	fire_button_texture_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(firebuttontextureAtlas, gameActivity,"fire.png",60,0 );
	jump_button_texture_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(firebuttontextureAtlas, gameActivity, "jump.png",0,0);
	firebuttontextureAtlas.load();
	}
	private void loadBackgroundResource()
	{
	BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
	
	animatedsprite_texture_atlas = new BitmapTextureAtlas(gameActivity.getTextureManager(), 1024,1024,TextureOptions.BILINEAR);
	player1_texture_region = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(animatedsprite_texture_atlas, gameActivity, "Hero.png", 0, 0,9,7);
										//	BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(pBitmapTextureAtlas, pAssetManager, pAssetPath, pTextureX, pTextureY, pTileColumns, pTileRows)
	//player2_texture_region = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(animatedsprite_texture_atlas, gameActivity, "animatedsprite1.png", 73, 0, 12, 2);
	//BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(pBitmapTextureAtlas, pAssetManager, pAssetPath, pTextureX, pTextureY, pTileColumns, pTileRows)
	animatedsprite_texture_atlas.load();

	BackgroundTexture = new BitmapTextureAtlas(gameActivity.getTextureManager(), 1024, 1024);
	mParallaxLayerFront = BitmapTextureAtlasTextureRegionFactory.createFromAsset(BackgroundTexture, gameActivity, "background-street.png", 0, 0);
	mParallaxLayerBack = BitmapTextureAtlasTextureRegionFactory.createFromAsset(BackgroundTexture, gameActivity, "background.png", 0, 188);
	mParallaxLayerMid = BitmapTextureAtlasTextureRegionFactory.createFromAsset(BackgroundTexture, gameActivity, "parallax_background_layer_mid.png", 0, 669);
	BackgroundTexture.load();
	}
	
	private void loadMenuGraphics() {
//same as splash menu graphics method
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/menu/");
		menuTextureAtlas = new BuildableBitmapTextureAtlas(gameActivity.getTextureManager(), 1024,1024,TextureOptions.BILINEAR);
		menu_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, gameActivity,"menu.jpg");
		
		play_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, gameActivity, "play.png");
		continue_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, gameActivity, "continuebutton.png");
		setting_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, gameActivity, "settings.png");
		high_score_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, gameActivity, "highscore.png");
		instructions_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, gameActivity, "instructions.png");
		exit_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, gameActivity, "exit.png");
//	
		try{																									// border spacing
			this.menuTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(1,2,1));
			this.menuTextureAtlas.load();
			
		}
		catch(final TextureAtlasBuilderException e)
		{
			Debug.e(e);
		}
	}
	
	private void loadBulletResources()
	{
		
	}
	
	private void loadEnemyResources()
	{
		
		
	}
	
	private void loadHostageResources()
	{
		
	}
	
	private void loadGameFonts() {

	}

	private void loadMenuFonts() {
		FontFactory.setAssetBasePath("font/");
		final ITexture mainFontTexture = new BitmapTextureAtlas(gameActivity.getTextureManager(), 256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		//FontFactory.createStrokeFromAsset(pFontManager, pTexture, pAssetManager, pAssetPath, pSize, pAntiAlias, pColor, pStrokeWidth, pStrokeColor, pStrokeOnly)
		//font = FontFactory.createStrokeFromAsset(gameActivity.getFontManager(), mainFontTexture, gameActivity.getAssets(), "font.ttf", 50, true, Color.WHITE, 2, Color.BLACK);
		font=FontFactory.createStrokeFromAsset(gameActivity.getFontManager(), mainFontTexture, gameActivity.getAssets(), "loading.ttf", 20, true, Color.BLACK_ABGR_PACKED_INT, 2, Color.WHITE_ABGR_PACKED_INT);
		font.load();
	}

	private void loadGameLoadingSprite()
	{
		FontFactory.setAssetBasePath("gfx/loading/");
		loadingTextureAtlas = new BuildableBitmapTextureAtlas(gameActivity.getTextureManager(), 1024,512,TextureOptions.BILINEAR);
		
	//	loading_region = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(loadingTextureAtlas, gameActivity.getAssets(),"spriteloadingscene1.png", 13,1);
		try {
			this.loadingTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 0, 1));
			this.loadingTextureAtlas.load();
		} catch (final TextureAtlasBuilderException e) {
			Debug.e(e);
		}
	
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

	public void loadMenuTextures()
	{
		menuTextureAtlas.load();
	}
	
	public void unloadMenuTextures()
	{
		menuTextureAtlas.unload();
	}
	
	public void unloadGameTexture()
	{
		
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
