package ch.digitalmeat.company.desktop;

import ch.digitalmeat.company.App;
import ch.digitalmeat.company.Constants;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Constants.WINDOW_WIDTH;
		config.height = Constants.WINDOW_HEIGHT;
		new LwjglApplication(new App(), config);
	}
}
