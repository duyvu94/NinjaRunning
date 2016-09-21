package com.duyvu.object;
import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.duyvu.supporter.AssetLoader;

public class Kunai {
	private final int MOVINGSPEED = 500; 
	private List<Vector2> position;
	private List<Rectangle> bounding;
	private float width, height;
	private int kunaiNumber;
	
	public Kunai(float width, float height){
		position = new LinkedList<Vector2>();
		bounding = new LinkedList<Rectangle>();
		this.width = width;
		this.height = height;
		kunaiNumber = 30;
	}
	
	public void reset(){
		position.clear();
		kunaiNumber = 30;
	}
	
	public boolean checkIfCollided(Rectangle boundingRectangle, boolean isKunai){
		for(int i = 0; i < position.size(); i++){
			if (Intersector.overlaps(boundingRectangle, bounding.get(i))){
				if (!isKunai){
					AssetLoader.hit.play(AssetLoader.soundVolume);
					position.remove(i);
					bounding.remove(i);
				}
				return true;
			}
		}
		return false;
	}
	
	public void throwWeapon(int pX, int pY){
		AssetLoader.throwing.play(AssetLoader.soundVolume);
		if (kunaiNumber == 0)
			return;
		kunaiNumber--;
		position.add(new Vector2(pX+ width/10, pY + height/14));
		bounding.add(new Rectangle(pX+ width/10, pY + height/14, width/12, height/24));
	}
	
	public void update(float delta){
		for(int i = 0; i < position.size(); i++){
			position.set(i, position.get(i).cpy().add(new Vector2(MOVINGSPEED, 0).scl(delta)));
			bounding.set(i, bounding.get(i).set(position.get(i).x, position.get(i).y, width/12, height/24));
		}
		
		if (position.size() >0 && position.get(0).x > width){
			position.remove(0);
			bounding.remove(0);
		}
	}
	
	public void draw(SpriteBatch batcher){
		for(Vector2 pos : position){
			batcher.draw(AssetLoader.kunai, pos.x, pos.y, width/12, height/24);
		}
	}

	public int getKunaiNumber() {
		return kunaiNumber;
	}

	public void setKunaiNumber(int kunaiNumber) {
		this.kunaiNumber = kunaiNumber;
	}
}
