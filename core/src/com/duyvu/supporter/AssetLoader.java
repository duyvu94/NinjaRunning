package com.duyvu.supporter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {
	
	public static TextureRegion[] runFrames;
	public static TextureAtlas ninjaAtlas, otherAtlas, monsterAtlas;
	public static Animation ninjaRun, ninjaJump, ninjaDie;
	public static Animation grumpy, bullet, explosion;
	public static Sprite kunai, fishSpaceship, smilingSpaceship;
	public static BitmapFont scoreFont, textFont, ntFont, calibriFont;
	public static Sprite startButtonUp, startButtonDown, settingButtonUp, settingButtonDown, pauseButton,
						resumeButtonUp, resumeButtonDown, menuButtonUp, menuButtonDown, musicOnButtonUp,
						musicOnButtonDown, musicOffButtonUp, musicOffButtonDown, soundOnButtonUp,
						soundOnButtonDown, soundOffButtonUp, soundOffButtonDown, backButtonUp,
						backButtonDown, helpButtonUp, helpButtonDown, leftTouch, rightTouch;
	public static Preferences prefs;
	public static Sound dead, jump, crank, ringing, throwing, expl, hit, chaching, hit2, press;   
	public static Music themes;
	public static float soundVolume;
	
	public static Sprite black, white, logo, menuScreen, background, land, bag;
	
	public static void setHighScore(int val) {
		prefs.putInteger("highScore", val);
		prefs.flush();
	}
	
	public static int getHighScore() {
		return prefs.getInteger("highScore");
	}
	
	public static void create(){
		ninjaAtlas = new TextureAtlas(Gdx.files.internal("NinjaPacker.pack"));
		otherAtlas = new TextureAtlas(Gdx.files.internal("OtherPacker.pack"));
		monsterAtlas = new TextureAtlas(Gdx.files.internal("MonsterPacker.pack"));
		
		ninjaRun = new Animation(0.03f, ninjaAtlas.findRegions("Run_"));
		ninjaRun.setPlayMode(PlayMode.LOOP);
		ninjaDie = new Animation(0.03f, ninjaAtlas.findRegions("Dead_"));
		ninjaJump = new Animation(0.07f, ninjaAtlas.findRegions("Jump_"));
		kunai = new Sprite(ninjaAtlas.findRegion("Kunai"));
		
		fishSpaceship = new Sprite(monsterAtlas.findRegion("fishspaceship"));
		smilingSpaceship = new Sprite(monsterAtlas.findRegion("smilingspaceship"));
		grumpy = new Animation(0.1f, monsterAtlas.findRegions("Grumpy"));
		grumpy.setPlayMode(PlayMode.LOOP);
		bullet = new Animation(0.1f, monsterAtlas.findRegions("bullet_"));
		bullet.setPlayMode(PlayMode.LOOP);
		
		TextureRegion expSheet = new TextureRegion(otherAtlas.findRegion("Explosion"));
		TextureRegion[][] temp = expSheet.split(expSheet.getRegionWidth()/12, expSheet.getRegionHeight());
		TextureRegion[] expFrames = new TextureRegion[10];
		for(int i = 2; i < 12; i++)
			expFrames[i-2] = temp[0][i];
		explosion = new Animation(0.1f, expFrames);
		
		black = new Sprite(otherAtlas.findRegion("black"));
		white = new Sprite(otherAtlas.findRegion("white"));
		logo = new Sprite(otherAtlas.findRegion("logo"));
		menuScreen = new Sprite(otherAtlas.findRegion("menu"));
		background = new Sprite(otherAtlas.findRegion("bg"));
		land = new Sprite(otherAtlas.findRegion("land"));
		bag = new Sprite(otherAtlas.findRegion("bag"));
		leftTouch = new Sprite(otherAtlas.findRegion("lefttouch"));
		rightTouch = new Sprite(otherAtlas.findRegion("righttouch"));
		
		startButtonUp = new Sprite(otherAtlas.findRegion("startbuttonup"));
		startButtonDown = new Sprite(otherAtlas.findRegion("startbuttondown"));
		pauseButton = new Sprite(otherAtlas.findRegion("pausebutton"));
		resumeButtonUp = new Sprite(otherAtlas.findRegion("resumebuttonup"));
		resumeButtonDown = new Sprite(otherAtlas.findRegion("resumebuttondown"));
		menuButtonUp = new Sprite(otherAtlas.findRegion("exitbuttonup"));
		menuButtonDown = new Sprite(otherAtlas.findRegion("exitbuttondown"));
		settingButtonUp = new Sprite(otherAtlas.findRegion("settingbuttonup"));
		settingButtonDown = new Sprite(otherAtlas.findRegion("settingbuttondown"));
		musicOnButtonUp = new Sprite(otherAtlas.findRegion("musiconbuttonup"));
		musicOnButtonDown = new Sprite(otherAtlas.findRegion("musiconbuttondown"));
		musicOffButtonUp = new Sprite(otherAtlas.findRegion("musicoffbuttonup"));
		musicOffButtonDown = new Sprite(otherAtlas.findRegion("musicoffbuttondown"));
		soundOnButtonUp = new Sprite(otherAtlas.findRegion("soundonbuttonup"));
		soundOnButtonDown = new Sprite(otherAtlas.findRegion("soundonbuttondown"));
		soundOffButtonUp = new Sprite(otherAtlas.findRegion("soundoffbuttonup"));
		soundOffButtonDown = new Sprite(otherAtlas.findRegion("soundoffbuttondown"));
		backButtonUp = new Sprite(otherAtlas.findRegion("backbuttonup"));
		backButtonDown = new Sprite(otherAtlas.findRegion("backbuttondown"));
		helpButtonUp = new Sprite(otherAtlas.findRegion("helpbuttonup"));
		helpButtonDown = new Sprite(otherAtlas.findRegion("helpbuttondown"));
		
		smilingSpaceship.flip(true, true);
		menuScreen.flip(false, true);
		logo.flip(false, true);
		fishSpaceship.flip(true, true);
		background.flip(false, true);
		land.flip(false, true);
		bag.flip(false, true);
		leftTouch.flip(false, true);
		rightTouch.flip(false, true);
		
		startButtonUp.flip(false, true);
		startButtonDown.flip(false, true);
		resumeButtonUp.flip(false, true);
		resumeButtonDown.flip(false, true);
		menuButtonUp.flip(false, true);
		menuButtonDown.flip(false, true);
		settingButtonUp.flip(false, true);
		settingButtonDown.flip(false, true);
		musicOnButtonUp.flip(false, true);
		musicOnButtonDown.flip(false, true);
		musicOffButtonUp.flip(false, true);
		musicOffButtonDown.flip(false, true);
		soundOnButtonUp.flip(false, true);
		soundOnButtonDown.flip(false, true);
		soundOffButtonUp.flip(false, true);
		soundOffButtonDown.flip(false, true);
		backButtonUp.flip(false, true);
		backButtonDown.flip(false, true);
		helpButtonUp.flip(false, true);
		helpButtonDown.flip(false, true);
		
		prefs = Gdx.app.getPreferences("NinjaRunning");
		if (!prefs.contains("highScore"))
			prefs.putInteger("highScore", 0);
		if (!prefs.contains("music"))
			prefs.putInteger("music", 1);
		if (!prefs.contains("sound"))
			prefs.putInteger("sound", 1);
		prefs.flush();
		
		scoreFont = new BitmapFont(Gdx.files.internal("scorefont.fnt"));
		scoreFont.getData().setScale(1, -1);
		textFont = new BitmapFont(Gdx.files.internal("whitetext.fnt"));
		textFont.getData().setScale(2, -2);
		ntFont = new BitmapFont(Gdx.files.internal("infofont.fnt"));
		ntFont.getData().setScale(1f, -1f);
		calibriFont = new BitmapFont(Gdx.files.internal("calibri.fnt"));
		calibriFont.getData().setScale(1, -1);
		
		themes = Gdx.audio.newMusic(Gdx.files.internal("theme.wav"));
		dead = Gdx.audio.newSound(Gdx.files.internal("die.wav"));
		crank = Gdx.audio.newSound(Gdx.files.internal("crank.wav"));
		ringing = Gdx.audio.newSound(Gdx.files.internal("ringing.wav"));
		jump = Gdx.audio.newSound(Gdx.files.internal("jump.wav"));
		throwing = Gdx.audio.newSound(Gdx.files.internal("throw.wav"));
		expl = Gdx.audio.newSound(Gdx.files.internal("explosion.wav"));
		hit = Gdx.audio.newSound(Gdx.files.internal("hit.wav"));
		hit2 = Gdx.audio.newSound(Gdx.files.internal("hit2.wav"));
		chaching = Gdx.audio.newSound(Gdx.files.internal("chaching.wav"));
		press = Gdx.audio.newSound(Gdx.files.internal("press.wav"));

		if (prefs.getInteger("music")==0){
			themes.setVolume(0);
		}
		else {
			themes.setVolume(1);
		}
		if (prefs.getInteger("sound")==0){
			soundVolume =0;
		}
		else{
			soundVolume =1;
		}
	}
}
