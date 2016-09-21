package com.duyvu.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.duyvu.gameworld.GameWorld.GameState;
import com.duyvu.object.InfoObject;
import com.duyvu.object.Kunai;
import com.duyvu.object.MonsterManager;
import com.duyvu.object.NinjaCharacter;
import com.duyvu.object.OtherObject;
import com.duyvu.supporter.AssetLoader;
import com.duyvu.ui.SimpleButton;

public class GameRenderer {
	private GameWorld gameWorld;
	private float gameWidth, gameHight;
	private SpriteBatch batcher;
	private NinjaCharacter ninjaCharacter;
	private OtherObject otherObject;
	private Kunai kunai;
	private InfoObject infoObject;
	private MonsterManager monsterManager;
	private float transparency = 0;
	private boolean loadingTransparency = true;
	
	private OrthographicCamera cam;
	
	public GameRenderer(GameWorld gameWorld, float gameWidth, float gameHight){
		this.gameWorld = gameWorld;
		this.gameWidth = gameWidth;
		this.gameHight = gameHight;
		
		cam = new OrthographicCamera();
		cam.setToOrtho(true, gameWidth, gameHight);
		
		batcher = new SpriteBatch();
		batcher.setProjectionMatrix(cam.combined);
		
	}
	
	public void render(float delta, float runTime){
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	
		batcher.begin();
		
		if (gameWorld.getGameState() == GameState.READY){
			AssetLoader.textFont.getData().setScale(2, -2);
			AssetLoader.dead.stop();
			
			if (transparency==0 || (transparency<1 && transparency+delta>=1)|| (transparency<2 && transparency+delta>=2))
				AssetLoader.crank.play(AssetLoader.soundVolume);
			
			if (transparency<3 && transparency+delta>=3)
				AssetLoader.ringing.play(AssetLoader.soundVolume);
			 
			if (transparency <= 1) {
				batcher.setColor(0, 0, 0, 1);
				batcher.draw(AssetLoader.black, 0, 0, gameWidth, 480);
				batcher.setColor(0, 0, 0, transparency);
				AssetLoader.textFont.draw(batcher, "3", gameWidth / 2 - 30, 180);
				batcher.draw(AssetLoader.black, 0, 0, gameWidth, 480);
			} else if (transparency > 1 && transparency <= 2) {
				batcher.setColor(0, 0, 0, 1);
				batcher.draw(AssetLoader.black, 0, 0, gameWidth, 480);
				batcher.setColor(0, 0, 0, transparency - 1);
				AssetLoader.textFont.draw(batcher, "2", gameWidth / 2 - 30, 180);
				batcher.draw(AssetLoader.black, 0, 0, gameWidth, 480);
			} else if (transparency > 2 && transparency <= 3) {
				batcher.setColor(0, 0, 0, 1);
				batcher.draw(AssetLoader.black, 0, 0, gameWidth, 480);
				batcher.setColor(0, 0, 0, transparency - 2);
				AssetLoader.textFont.draw(batcher, "1", gameWidth / 2 - 20, 180);
				batcher.draw(AssetLoader.black, 0, 0, gameWidth, 480);
			} else {
				batcher.setColor(0, 0, 0, 1);
				batcher.draw(AssetLoader.black, 0, 0, gameWidth, 480);
				batcher.setColor(0, 0, 0, transparency - 3);
				AssetLoader.textFont.draw(batcher, "READY", gameWidth / 2 - 200, 180);
				batcher.draw(AssetLoader.black, 0, 0, gameWidth, 480);
			}
			batcher.setColor(1, 1, 1, 1);
			transparency += delta;
			if (transparency >= 4)
				setLoadingTransparency(false);
		}
		
		else if (gameWorld.getGameState() == GameState.PLAYING){
			otherObject.draw(batcher);
			ninjaCharacter.draw(batcher);
			kunai.draw(batcher);
			monsterManager.draw(batcher);
			infoObject.draw(batcher);
		}
		
		else if (gameWorld.getGameState() == GameState.PAUSE){
			otherObject.draw(batcher);
			ninjaCharacter.draw(batcher);
			kunai.draw(batcher);
			monsterManager.draw(batcher);
			infoObject.draw(batcher);
			
			batcher.setColor(1, 1, 1, 0.6f);
			batcher.draw(AssetLoader.black, 0, 0, 2000, 1000);
			batcher.setColor(1, 1, 1, 1);
			SimpleButton continueButton = infoObject.getResumeButton();
			SimpleButton menuButton = infoObject.getMenuButton();
			if (!continueButton.isPressed()) {
				batcher.draw(AssetLoader.resumeButtonUp, continueButton.getX(), continueButton.getY(),
						continueButton.getWidth(), continueButton.getHeight());
			} else
				batcher.draw(AssetLoader.resumeButtonDown, continueButton.getX(), continueButton.getY(),
						continueButton.getWidth(), continueButton.getHeight());
			if (!menuButton.isPressed()) {
				batcher.draw(AssetLoader.menuButtonUp, menuButton.getX(), menuButton.getY(), menuButton.getWidth(),
						menuButton.getHeight());
			} else
				batcher.draw(AssetLoader.menuButtonDown, menuButton.getX(), menuButton.getY(), menuButton.getWidth(),
						menuButton.getHeight());
		}
		
		else if (gameWorld.getGameState() == GameState.GAMEOVER ){
			transparency = 0;
			otherObject.draw(batcher);
			ninjaCharacter.draw(batcher);
			monsterManager.draw(batcher);
			if (getNinjaCharacter().getRunTime()-  getNinjaCharacter().getDeadPoint() < 1){
				batcher.end();
				return;
			}
			batcher.setColor(1, 1, 1, 0.6f);
			batcher.draw(AssetLoader.black, 0, 0, 2000, 1000);

			int hc = AssetLoader.getHighScore();
			AssetLoader.textFont.getData().setScale(1f, -1f);
			AssetLoader.textFont.draw(batcher, "HIGH SCORE: " + hc,
					gameWidth / 2 - 240 - (int) Math.log10(hc == 0 ? 1 : hc) * 10, 140);
			AssetLoader.ntFont.draw(batcher, "your score: " + infoObject.getScore(),
					gameWidth / 2 - 100 - (int) Math.log10(hc == 0 ? 1 : hc) * 10, 240);
			AssetLoader.ntFont.draw(batcher, "Touch to play again!", gameWidth / 2 - 178, 400);
			batcher.setColor(1, 1, 1, 1);
		}
		
		
		batcher.end();
	}

	public boolean isLoadingTransparency() {
		return loadingTransparency;
	}

	public void setLoadingTransparency(boolean loadingTransparency) {
		if (loadingTransparency){
			transparency = 0;
		}
		this.loadingTransparency = loadingTransparency;
	}

	public InfoObject getInfoObject() {
		return infoObject;
	}

	public void setInfoObject(InfoObject infoObject) {
		this.infoObject = infoObject;
	}

	public MonsterManager getMonsterManager() {
		return monsterManager;
	}

	public void setMonsterManager(MonsterManager monsterManager) {
		this.monsterManager = monsterManager;
	}

	public Kunai getKunai() {
		return kunai;
	}

	public void setKunai(Kunai kunai) {
		this.kunai = kunai;
	}

	public NinjaCharacter getNinjaCharacter() {
		return ninjaCharacter;
	}

	public void setNinjaCharacter(NinjaCharacter ninjaCharacter) {
		this.ninjaCharacter = ninjaCharacter;
	}

	public OtherObject getOtherObject() {
		return otherObject;
	}

	public void setOtherObject(OtherObject otherObject) {
		this.otherObject = otherObject;
	}

}
