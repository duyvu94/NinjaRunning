package com.duyvu.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.duyvu.gameworld.GameRenderer;
import com.duyvu.gameworld.GameWorld;
import com.duyvu.gameworld.GameWorld.GameState;
import com.duyvu.object.Kunai;
import com.duyvu.supporter.InputHandler;

public class GameScreen implements Screen{
	private InputHandler inputHandler;
	private GameRenderer renderer;
	private GameWorld world;
	private float runTime;
	private MainScreen mainScreen;
	
	public GameScreen(Game myGame){
		float screenWidth = Gdx.graphics.getWidth();
		float screenHeight = Gdx.graphics.getHeight();
		float gameHeight = 480;
		float gameWidth = screenWidth / (screenHeight / gameHeight);
		
		world = new GameWorld(myGame, gameWidth, gameHeight);
		inputHandler = new InputHandler(world, Gdx.graphics.getWidth()/gameWidth, Gdx.graphics.getHeight()/gameHeight, screenWidth/2);
		renderer = new GameRenderer(world, gameWidth, gameHeight);
		
		world.setRenderer(renderer);
		inputHandler.setNinjaCharacter(world.getNinjaCharacter());
		inputHandler.setKunai(world.getKunai());
		inputHandler.setGameScreen(this);
		runTime = 0;
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}
	
	public void setInputPriority(){
		Gdx.input.setInputProcessor(inputHandler);
		reset();
	}
	
	public void reset(){
		runTime = 0;
	}

	@Override
	public void render(float delta) {
		runTime+= delta;
		world.update(delta, runTime);
		renderer.render(delta, runTime);
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	public MainScreen getMainScreen() {
		return mainScreen;
	}

	public void setMainScreen(MainScreen mainScreen) {
		this.mainScreen = mainScreen;
		world.setMainScreen(mainScreen);
	}
	
}
