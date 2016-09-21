package com.duyvu.supporter;

import com.badlogic.gdx.InputProcessor;
import com.duyvu.gameworld.GameWorld;
import com.duyvu.gameworld.GameWorld.GameState;
import com.duyvu.object.InfoObject;
import com.duyvu.object.Kunai;
import com.duyvu.object.NinjaCharacter;
import com.duyvu.screen.GameScreen;

public class InputHandler implements InputProcessor{
	private float scaleFactorX;
	private float scaleFactorY;
	private NinjaCharacter ninjaCharacter;
	private Kunai kunai;
	private float midPointX, width, height;
	private GameWorld world;
	private GameScreen gameScreen;
	
	public InputHandler(GameWorld world, float scaleFactorX, float scaleFactorY, float midPointX){
		this.scaleFactorX = scaleFactorX;
		this.scaleFactorY = scaleFactorY;
		this.midPointX = scaleX((int) midPointX);
		this.world = world;
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

	public int scaleX(int x){
		return (int)(x / scaleFactorX);
	}
	
	public int scaleY(int y){
		return (int)(y / scaleFactorY);
	}
	
	public GameScreen getGameScreen() {
		return gameScreen;
	}

	public void setGameScreen(GameScreen gameScreen) {
		this.gameScreen = gameScreen;
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		screenX = scaleX(screenX);
		screenY = scaleY(screenY);
		if (world.getGameState() == GameState.PLAYING){
			if(!world.getInfoObject().getPauseButton().isTouchedDown(screenX, screenY)){
				if (screenX>midPointX){
					ninjaCharacter.jump();
				}
				else{
					kunai.throwWeapon(ninjaCharacter.getX(), ninjaCharacter.getY());
				}
			}
		}
		else if (world.getGameState() == GameState.PAUSE){
			world.getInfoObject().getResumeButton().isTouchedDown(screenX, screenY);
			world.getInfoObject().getMenuButton().isTouchedDown(screenX, screenY);
		}
		else if (world.getGameState() == GameState.GAMEOVER){
			return true;
		}
		return false;
	}
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		screenX = scaleX(screenX);
		screenY = scaleY(screenY);
		if (world.getGameState()== GameState.PLAYING){
			if (world.getInfoObject().getPauseButton().isTouchedUp(screenX, screenY))
				world.setGameState(GameState.PAUSE);
		}
		else if (world.getGameState()== GameState.PAUSE){
			if (world.getInfoObject().getMenuButton().isTouchedUp(screenX, screenY)){
				world.backToMenu();
			}
			else if (world.getInfoObject().getResumeButton().isTouchedUp(screenX, screenY)){
				world.setGameState(GameState.PLAYING);
			}
		}
		else if (world.getGameState() == GameState.GAMEOVER){
			if (world.getNinjaCharacter().getRunTime() - world.getNinjaCharacter().getDeadPoint()> 1){
				world.reset();
				gameScreen.reset();
			}
		}
		return false;
	}
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
