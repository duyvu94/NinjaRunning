package com.duyvu.ninja;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.duyvu.screen.GameScreen;
import com.duyvu.screen.HelpScreen;
import com.duyvu.screen.MainScreen;
import com.duyvu.screen.SplashScreen;
import com.duyvu.supporter.AssetLoader;
import com.sun.javafx.applet.Splash;

public class Ninja extends Game {
	
	@Override
	public void create() {
		AssetLoader.create();
		SplashScreen splashScreen = new SplashScreen(this);
		MainScreen mainScreen = new MainScreen(this);
		GameScreen gameScreen = new GameScreen(this);
				
		splashScreen.setMainScreen(mainScreen);
		mainScreen.setGameScreen(gameScreen);
		gameScreen.setMainScreen(mainScreen);
		
		setScreen(splashScreen);
		mainScreen.setInputPriority();
	}

	
	@Override
	public void dispose () {
		super.dispose();
	}
}
