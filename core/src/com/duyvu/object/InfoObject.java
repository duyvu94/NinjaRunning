package com.duyvu.object;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.duyvu.supporter.AssetLoader;
import com.duyvu.ui.SimpleButton;

public class InfoObject {
	private float gameWidth, gameHeight;
	private Kunai kunai;
	private int score;
	private SimpleButton pauseButton, menuButton, resumeButton;
	private float runTime;
	
	public InfoObject(float width, float height){
		gameWidth = width;
		gameHeight = height;
		score = 0;
		runTime = 0;
		pauseButton = new SimpleButton(width/2-30, 10, 60, 60, AssetLoader.pauseButton, AssetLoader.pauseButton);
		menuButton = new SimpleButton(width/2-100, 260, 200, 45, AssetLoader.menuButtonUp, AssetLoader.menuButtonDown);
		resumeButton = new SimpleButton(width/2-100, 160, 200, 45, AssetLoader.resumeButtonUp, AssetLoader.resumeButtonDown);
	}
	
	public void reset(){
		score = 0;
		runTime = 0;
	}
	
	public void update(float delta){
		runTime += delta;
		score = (int) runTime;
	}
	
	public void draw(SpriteBatch batcher){
		batcher.draw(AssetLoader.kunai, gameWidth/6, gameHeight/20, gameWidth/15, gameHeight/30);
		AssetLoader.scoreFont.draw(batcher, kunai.getKunaiNumber()+"", gameWidth/12, gameHeight/26);
		batcher.draw(pauseButton.getButtonUp(), pauseButton.getX(), pauseButton.getY(), pauseButton.getWidth(), pauseButton.getHeight());
		AssetLoader.scoreFont.draw(batcher, "Score: " + score, gameWidth - gameWidth/5 - gameWidth/40, gameHeight/26);
	}

	public Kunai getKunai() {
		return kunai;
	}

	public void setKunai(Kunai kunai) {
		this.kunai = kunai;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public SimpleButton getPauseButton() {
		return pauseButton;
	}

	public void setPauseButton(SimpleButton pauseButton) {
		this.pauseButton = pauseButton;
	}

	public SimpleButton getMenuButton() {
		return menuButton;
	}

	public void setMenuButton(SimpleButton menuButton) {
		this.menuButton = menuButton;
	}

	public SimpleButton getResumeButton() {
		return resumeButton;
	}

	public void setResumeButton(SimpleButton resumeButton) {
		this.resumeButton = resumeButton;
	}
	
}
