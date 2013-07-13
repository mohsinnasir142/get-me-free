package com.getmefree.other;

import org.andengine.entity.primitive.Rectangle;
import org.andengine.util.color.Color;

import com.getmefree.resources.ResourceManager;

public class Bullet {

	/* if you want to use images instead, 
	you could do so by just changing the type of sprite to Sprite
	and making adjustments inside this class */
	
	public Rectangle sprite;
	
	public Bullet()
	{
		// 10×10 square Bullets
		sprite = new Rectangle(0, 0,5,5,ResourceManager.getInstance().vbom);
		              //   Red   Green  Blue
		sprite.setColor(Color.YELLOW);
	}
}
