package com.duyvu.gameworld;

import com.badlogic.gdx.Game;
import com.duyvu.object.InfoObject;
import com.duyvu.object.Kunai;
import com.duyvu.object.MonsterManager;
import com.duyvu.object.NinjaCharacter;
import com.duyvu.object.OtherObject;
import com.duyvu.screen.MainScreen;
import com.duyvu.supporter.AssetLoader;

public class GameWorld {
	private Game game;
	private MainScreen mainScreen;
	private GameRenderer renderer;
	private NinjaCharacter ninjaCharacter;
	private OtherObject otherObject;
	private MonsterManager monsterManager;
	private InfoObject infoObject;
	private Kunai kunai;
	private float runTime;
	
	public enum GameState{
		READY, PLAYING, PAUSE, GAMEOVER
	}
	
	public GameState gameState;
	
	public GameWorld(Game myGame, float width, float height){
		game = myGame;
		gameState = GameState.READY;
		ninjaCharacter = new NinjaCharacter(width, height);
		otherObject = new OtherObject(width, height);
		kunai = new Kunai(width, height);
		infoObject = new InfoObject(width, height);
		infoObject.setKunai(kunai);
		monsterManager = new MonsterManager(width, height);
		monsterManager.setNinjaCharacter(ninjaCharacter);
		monsterManager.setKunai(kunai);
		monsterManager.setGameState(gameState);
		
	}
	
	public void setRenderer(GameRenderer render){
		renderer = render;
		renderer.setNinjaCharacter(ninjaCharacter);
		renderer.setOtherObject(otherObject);
		renderer.setKunai(kunai);
		renderer.setInfoObject(infoObject);
		render.setMonsterManager(monsterManager);
	}
	
	public void reset(){
		infoObject.reset();
		ninjaCharacter.reset();
		kunai.reset();
		otherObject.reset();
		monsterManager.reset();
		gameState = GameState.READY;
		renderer.setLoadingTransparency(true);
	}
	
	public MainScreen getMainScreen() {
		return mainScreen;
	}

	public void setMainScreen(MainScreen mainScreen) {
		this.mainScreen = mainScreen;
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

	public InfoObject getInfoObject() {
		return infoObject;
	}

	public void setInfoObject(InfoObject infoObject) {
		this.infoObject = infoObject;
	}
	
	public void backToMenu(){
		reset();
		game.setScreen(mainScreen);
		mainScreen.setInputPriority();
	}

	public void update(float delta, float runTime){
		if (gameState == GameState.READY){
			if (!renderer.isLoadingTransparency())
				gameState= GameState.PLAYING;
		}
		else if (gameState == GameState.PLAYING){
			ninjaCharacter.update(delta);
			otherObject.update(delta);
			kunai.update(delta);
		
			monsterManager.update(delta, runTime);
			infoObject.update(delta);
		
			if (ninjaCharacter.isDead()){
				gameState = GameState.GAMEOVER;
			}
		}
		else if (gameState == GameState.PAUSE){
			
		}
		else if (gameState == GameState.GAMEOVER){
			ninjaCharacter.update(delta);
			if (AssetLoader.getHighScore()< infoObject.getScore())
				AssetLoader.setHighScore(infoObject.getScore());
		}
	}
}
