package ch.digitalmeat.company;

import java.util.ArrayList;
import java.util.List;

import ch.digitalmeat.company.level.GameMap;
import ch.digitalmeat.company.level.loader.MapLoader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

public class Assets {
	private static MapLoader mapLoader;
	public static JsonReader reader = new JsonReader();
	public static Json json = new Json();
	public static Texture whitePixel;
	public static Texture controls;
	public static TextureRegion controlPause;
	public static TextureRegion controlPlay;
	public static TextureRegion controlFast;
	public static TextureRegion controlUltraFast;

	public static Skin skin;

	private final static List<String> cities = new ArrayList<String>();

	public static void create() {
		mapLoader = new MapLoader();
		skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
		whitePixel = getTexture("blank.png");
		controls = new Texture("controls.png");
		controlPause = new TextureRegion(controls, 0, 0, 8, 8);
		controlPlay = new TextureRegion(controls, 8, 0, 8, 8);
		controlFast = new TextureRegion(controls, 16, 0, 8, 8);
		controlUltraFast = new TextureRegion(controls, 24, 0, 8, 8);
		String cityNames = Gdx.files.internal("cities.txt").readString();
		String[] names = cityNames.split("\n");
		for (String name : names) {
			name = name.replace("\r", "");
			cities.add(name);
		}
	}

	public static List<String> getCityNames() {
		return new ArrayList<String>(cities);
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
