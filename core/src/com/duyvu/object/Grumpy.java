package com.duyvu.object;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.duyvu.supporter.AssetLoader;

public class Grumpy extends Monster {
	private final int MOVINGSPEED = -400;
	private Vector2 position;
	private Vector2 velocity;
	private Vector2 sizeOfGrumpy;
	private float runTime = 0;
	private Rectangle boundingRectangle;

	public Grumpy(float width, float height){
		super(width, height);
		velocity = new Vector2(MOVINGSPEED, 0);
		position = new Vector2(getWidth()*3/2, height/4*3- getHeight()/8);
		sizeOfGrumpy = new Vector2(getWidth()/12, getHeight()/8);
		boundingRectangle = new Rectangle(position.x, position.y, sizeOfGrumpy.x, sizeOfGrumpy.y);
	}
	
	@Override
	public void update(float delta, float runTime){
		if (position.x < -getWidth()/2)
			setDead(true);
		position.add(velocity.cpy().scl(delta));
		boundingRectangle.set(position.x, position.y, getWidth()/12, getHeight()/8);
		this.runTime = runTime; 
	}
	
	@Override
	public void draw(SpriteBatch batcher){
		TextureRegion currentFrame = new TextureRegion(AssetLoader.grumpy.getKeyFrame(runTime));
		currentFrame.flip(false, true);
		batcher.draw(currentFrame, position.x, position.y, sizeOfGrumpy.x, sizeOfGrumpy.y);
	}

	@Override
	public Rectangle getBoundingRectangle() {
		return boundingRectangle;
	}

	@Override
	public void setBoundingRectangle(Rectangle boundingRectangle) {
		this.boundingRectangle = boundingRectangle;
	}
}
