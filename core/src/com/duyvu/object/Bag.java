package com.duyvu.object;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.duyvu.supporter.AssetLoader;

public class Bag extends Monster {
	private final int MOVINGSPEED = -400;
	private Vector2 position;
	private Vector2 velocity;
	private Vector2 sizeOfBag;
	private Rectangle boundingRectangle;

	public Bag(float width, float height){
		super(width, height);
		velocity = new Vector2(MOVINGSPEED, 0);
		position = new Vector2(getWidth()*3/2, height/2);
		sizeOfBag = new Vector2(getWidth()/12, getHeight()/8);
		boundingRectangle = new Rectangle(position.x, position.y, sizeOfBag.x, sizeOfBag.y);
		setValue(new Random().nextInt(15)+10);
	}
	
	@Override
	public void update(float delta, float runTime){
		if (position.x < -getWidth()/2)
			setDead(true);
		position.add(velocity.cpy().scl(delta));
		boundingRectangle.set(position.x, position.y, getWidth()/12, getHeight()/8);
	}
	
	@Override
	public void draw(SpriteBatch batcher){
		batcher.draw(AssetLoader.bag, position.x, position.y, sizeOfBag.x, sizeOfBag.y);
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
