package com.duyvu.object;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.duyvu.supporter.AssetLoader;

public class NinjaCharacter {
	private final int  JUMPSPEED = -1000;
	private final int  FALLINGSPEED = 500;
	private Vector2 position, velocity, sizeOfNinja;
	private boolean isDead;
	private boolean isJumping;
	private float jumpTime, dieTime, runTime, deadPoint;   //0.716s
	private float width, height;
	private Rectangle boundingRectangle;
	
	public NinjaCharacter(float width, float height){
		position = new Vector2(width/12, height/2);
		velocity = new Vector2(0, 0);
		sizeOfNinja = new Vector2(width/8, height/5 + height/16);
		boundingRectangle = new Rectangle(position.x, position.y, sizeOfNinja.x/4*3, sizeOfNinja.y);
		this.height = height;
		this.width = width;
		
		reset();
	}
	
	public void reset(){
		deadPoint = 0;
		jumpTime = 0;
		dieTime = 0;
		runTime = 0;
		setIsdead(false);
		setJumping(false);
	}
	
	public void jump(){
		if (isJumping())
			return;
		AssetLoader.jump.play(AssetLoader.soundVolume);
		jumpTime = 0;
		velocity.set(0, JUMPSPEED);
		setJumping(true);
	}
	
	public Rectangle getBoundingRectangle() {
		return boundingRectangle;
	}

	public void setBoundingRectangle(Rectangle boundingRectangle) {
		this.boundingRectangle = boundingRectangle;
	}

	public int getX(){
		return (int) position.x;
	}
	
	public int getY(){
		return (int) position.y;
	}
	
	public boolean isJumping() {
		return isJumping;
	}

	public void setJumping(boolean isJumping) {
		this.isJumping = isJumping;
	}

	public void update(float delta){
		runTime+= delta;
		if (isDead){
			dieTime += delta;
			if (position.y >= height/2) {
				position.y = height/2;
			}
			else {
				position.add(new Vector2(0, FALLINGSPEED).scl(delta));
			}
		}
		else if (isJumping()) {
			jumpTime += delta;
			position.add(velocity.cpy().scl(delta));
			if (position.y >= height/2 && velocity.y >0) {
				position.y = height/2;
				velocity.set(0, 0);
				setJumping(false);
			}
			else {
				velocity.add(0, height/10);
			}
		}
		boundingRectangle.set(position.x, position.y, sizeOfNinja.x/4*3, sizeOfNinja.y);
	}
	
	public void draw(SpriteBatch batcher){
		if (isDead()){
			TextureRegion currentFrame = new TextureRegion(AssetLoader.ninjaDie.getKeyFrame(dieTime));
			currentFrame.flip(false, true);
			batcher.draw(currentFrame, position.x, position.y, sizeOfNinja.x, sizeOfNinja.y);
		}
		else if (!isJumping()){
			TextureRegion currentFrame = new TextureRegion(AssetLoader.ninjaRun.getKeyFrame(runTime));
			currentFrame.flip(false, true);
			batcher.draw(currentFrame, position.x, position.y, sizeOfNinja.x, sizeOfNinja.y);
		}
		else{
			TextureRegion currentFrame = new TextureRegion(AssetLoader.ninjaJump.getKeyFrame(jumpTime));
			currentFrame.flip(false, true);
			batcher.draw(currentFrame, position.x, position.y, sizeOfNinja.x, sizeOfNinja.y);
		}
	}

	public boolean isDead() {
		return isDead;
	}

	public void setIsdead(boolean isDead) {
		if (isDead){
			AssetLoader.hit2.play(AssetLoader.soundVolume);
			AssetLoader.dead.play(AssetLoader.soundVolume);
			deadPoint = runTime;
		}
		this.isDead = isDead;
	}

	public float getRunTime() {
		return runTime;
	}

	public void setRunTime(float runTime) {
		this.runTime = runTime;
	}

	public float getDeadPoint() {
		return deadPoint;
	}

	public void setDeadPoint(float deadPoint) {
		this.deadPoint = deadPoint;
	}
	
}
