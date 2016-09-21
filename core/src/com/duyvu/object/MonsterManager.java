package com.duyvu.object;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.duyvu.gameworld.GameWorld.GameState;
import com.duyvu.supporter.AssetLoader;

public class MonsterManager {
	private float width, height;
	private List<Monster> monsterList;
	private int lastTime = 0;
	private NinjaCharacter ninjaCharacter;
	private Kunai kunai;
	private GameState gameState;
	
	public MonsterManager(float width, float height){
		this.width = width;
		this.height = height;
		monsterList = new LinkedList<Monster>();
	}
	
	public void addMonster(Monster monster){
		monsterList.add(monster);
	}
	
	public void reset(){
		monsterList.clear();
		lastTime = 0;
	}
	
	public void update(float delta, float runTime){
		int i = 0;
		Random r = new Random();

		if ((int)runTime %10 == 0 && r.nextBoolean()){
			if ((int)runTime != lastTime){
				lastTime = (int)runTime;
				addMonster(new Bag(width, height));
			}
		} else	if ((int)runTime != lastTime){
			lastTime = (int)runTime;
			if (r.nextBoolean())
				addMonster(new Grumpy(width, height));
			if (r.nextBoolean()){
				int rand = r.nextInt(5);
				if (rand < 2)
					addMonster(new Bullet(width, height));
				else if (rand < 4)
					addMonster(new FishSpaceship(width, height));
				else 
					addMonster(new SmilingSpaceship(width, height));
			}
		}
		
		while (i<monsterList.size()){
			if (!monsterList.get(i).isExploded() && kunai.checkIfCollided(monsterList.get(i).getBoundingRectangle(), 
					monsterList.get(i).getValue() > 0) )
				monsterList.get(i).getDamaged();
			if (monsterList.get(i).isDead()){
				monsterList.remove(i);
				continue;
			}
			monsterList.get(i).update(delta, runTime);
			if (Intersector.overlaps(ninjaCharacter.getBoundingRectangle(), monsterList.get(i).getBoundingRectangle()) 
					&& ! monsterList.get(i).isExploded()){
				if (monsterList.get(i).getValue() == 0)
					ninjaCharacter.setIsdead(true);
				else {
					AssetLoader.chaching.play(AssetLoader.soundVolume);
					kunai.setKunaiNumber(kunai.getKunaiNumber()+monsterList.get(i).getValue());
					monsterList.get(i).setDead(true);
				}
			}
			i++;
		}
	}
	
	public void draw(SpriteBatch batcher){
		for(int i = 0; i < monsterList.size(); i++){
			monsterList.get(i).draw(batcher);
		}
	}
	
	public GameState getGameState() {
		return gameState;
	}

	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}

	public NinjaCharacter getNinjaCharacter() {
		return ninjaCharacter;
	}

	public void setNinjaCharacter(NinjaCharacter ninjaCharacter) {
		this.ninjaCharacter = ninjaCharacter;
	}

	public Kunai getKunai() {
		return kunai;
	}

	public void setKunai(Kunai kunai) {
		this.kunai = kunai;
	}
}
