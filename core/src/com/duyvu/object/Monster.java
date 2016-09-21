package com.duyvu.object;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.duyvu.supporter.AssetLoader;

public class Monster {
	private float width, height;
	private boolean isDead, isExploded;
	private int value;
	
	public Monster(float width, float height){
		this.width = width;
		this.height = height;
		isDead = false;
		value = 0;
	}
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public void update(float delta, float runTime){
	}
	
	public void draw(SpriteBatch batcher){
	}

	public boolean isDead() {
		return isDead;
	}

	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}
	
	public Rectangle getBoundingRectangle(){
		return null;
	}
	
	public void setBoundingRectangle(Rectangle boundingRectangle) {
	}
	
	public void getDamaged(){
	}

	public boolean isExploded() {
		return isExploded;
	}

	public void setExploded(boolean isExploded) {
		if (isExploded){
			AssetLoader.expl.play(AssetLoader.soundVolume);
		}
		this.isExploded = isExploded;
	}
}
