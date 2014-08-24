package ch.digitalmeat.company;

import ch.digitalmeat.company.level.Map;
import ch.digitalmeat.company.level.MapLoader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

public class Assets {
	private static MapLoader mapLoader;

	public static void create() {
		mapLoader = new MapLoader();
	}

	public static Texture getTexture(String path) {
		return new Texture(Gdx.files.internal(path));
	}

	public static Pixmap getPixmap(String path) {
		return new Pixmap(Gdx.files.internal(path));
	}

	public static Map loadMap(String mapNameWithoutExtension) {
		return mapLoader.load(mapNameWithoutExtension);
	}

}
