package com.duyvu.object;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.duyvu.supporter.AssetLoader;

public class OtherObject {
	private final int BGSPEED = -50;
	private final int LANDSPEED = -400;
	
	private float gameWidth, gameHeight;
	
	private Vector2 land1, land2, bg1, bg2, landVelocity, bgVelocity;
	
	public OtherObject(float width, float height){
		gameWidth = width;
		gameHeight = height;
		
		land1 = new Vector2(0, height/2 + height/4);
		land2 = new Vector2(width/2*3, height/2 + height/4);
		bg1 = new Vector2(0, 0);
		bg2 = new Vector2(width/2*3, 0);
		
		landVelocity = new Vector2(LANDSPEED, 0);
		bgVelocity = new Vector2(BGSPEED, 0);
	}
	
	public void reset(){
		land1.set(0, gameHeight/2 + gameHeight/4);
		land2.set(gameWidth/2*3, gameHeight/2 + gameHeight/4);
		bg1.set(0, 0);
		bg2.set(gameWidth/2*3, 0);
	}
	
	public void update(float delta){
		land1.add(landVelocity.cpy().scl(delta));
		land2.add(landVelocity.cpy().scl(delta));
		
		if (land1.x < -gameWidth/2*3){
			land1.x = land2.x+ gameWidth/2*3;
		}
		
		if (land2.x < -gameWidth/2*3){
			land2.x = land1.x+ gameWidth/2*3;
		}
		
		bg1.add(bgVelocity.cpy().scl(delta));
		bg2.add(bgVelocity.cpy().scl(delta));
		
		if (bg1.x < - gameWidth/2*3){
			bg1.x = bg2.x + gameWidth / 2 * 3;
		}
		
		if (bg2.x < - gameWidth/2*3){
			bg2.x = bg1.x + gameWidth / 2 * 3;
		}
	}
	
	public void draw(SpriteBatch batcher){
		batcher.draw(AssetLoader.background, bg1.x, bg1.y, gameWidth/2*3, gameHeight/4*3);
		batcher.draw(AssetLoader.background, bg2.x, bg2.y, gameWidth/2*3, gameHeight/4*3);
		
		batcher.draw(AssetLoader.land, land1.x, land1.y, gameWidth/2*3, gameHeight/4);
		batcher.draw(AssetLoader.land, land2.x, land2.y, gameWidth/2*3, gameHeight/4);
	}
}
