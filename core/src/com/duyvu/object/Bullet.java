package com.duyvu.object;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.duyvu.supporter.AssetLoader;

public class Bullet extends Monster {
	private final int MOVINGSPEED = -550;
	private Vector2 position;
	private Vector2 velocity;
	private Vector2 sizeofBullet;
	private float runTime = 0, explodeTime = 0;
	private Rectangle boundingRectangle;
	private int hp;

	public Bullet(float width, float height){
		super(width, height);
		velocity = new Vector2(MOVINGSPEED, 0);
		position = new Vector2(getWidth()*3/2, new Random().nextInt((int)height/2) + height/14);
		sizeofBullet = new Vector2(getWidth()/6, getHeight()/8);
		boundingRectangle = new Rectangle(position.x, position.y, sizeofBullet.x, sizeofBullet.y);
		hp = 1;
	}
	
	@Override
	public void getDamaged() {
		hp--;
		if (hp==0){
			setExploded(true);
		}
	}
	
	@Override
	public void update(float delta, float runTime){
		if (explodeTime >= 1f) 
			setDead(true);
		if (position.x < -getWidth()/2)
			setDead(true);
		position.add(velocity.cpy().scl(delta));
		boundingRectangle.set(position.x, position.y, sizeofBullet.x, sizeofBullet.y);
		this.runTime = runTime; 
		if (isExploded()){
			explodeTime += delta;
		}
	}
	
	@Override
	public void draw(SpriteBatch batcher){
		if (isExploded()){
			TextureRegion currentFrame = new TextureRegion(AssetLoader.explosion.getKeyFrame(explodeTime));
			batcher.draw(currentFrame, position.x, position.y, getWidth()/12, getWidth()/12);
		}
		else {
			TextureRegion currentFrame = new TextureRegion(AssetLoader.bullet.getKeyFrame(runTime));
			currentFrame.flip(true, true);
			batcher.draw(currentFrame, position.x, position.y, sizeofBullet.x, sizeofBullet.y);
		}
		
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
