package ch.digitalmeat.company;

import ch.digitalmeat.company.level.GameMap;
import ch.digitalmeat.company.level.loader.MapLoader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

public class Assets {
	private static MapLoader mapLoader;
	public static JsonReader reader = new JsonReader();
	public static Json json = new Json();

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

	public static JsonValue parseJson(String json) {
		return reader.parse(json);
	}

	public static JsonValue parseJsonFile(String file) {
		return parseJson(Gdx.files.internal(file).readString());
	}
}
