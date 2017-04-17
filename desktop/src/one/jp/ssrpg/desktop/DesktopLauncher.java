package one.jp.ssrpg.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import one.jp.ssrpg.GdxGame;

public class DesktopLauncher {

	public static final int HEIGHT = 400;
	public static final int WIDTH = 800;
	public static String TITLE = "SSRPG";

	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = HEIGHT;
		config.width = WIDTH;
		config.title = TITLE;
		new LwjglApplication(new GdxGame(), config);
	}

}
