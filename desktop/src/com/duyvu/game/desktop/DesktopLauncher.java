package com.duyvu.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.duyvu.ninja.Ninja;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Ninja";
		config.useGL30 = false;
		config.width = 800;
		config.height = 480;
		new LwjglApplication(new Ninja(), config);
	}
}
