package ch.digitalmeat.company;

import ch.digitalmeat.company.level.GameMap;
import ch.digitalmeat.company.level.MapLoader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Assets {
	private static MapLoader mapLoader;

	public static Skin skin;

	public static void create() {
		mapLoader = new MapLoader();
		skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
	}

	public static boolean fileExists(String path) {
		return Gdx.files.internal(path).exists();
	}

	public static Texture getTexture(String path) {
		return new Texture(Gdx.files.internal(path));
	}

	public static Pixmap getPixmap(String path) {
		return new Pixmap(Gdx.files.internal(path));
	}

	public static GameMap loadMap(String mapNameWithoutExtension) {
		return mapLoader.load(mapNameWithoutExtension);
	}
}
