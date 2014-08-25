package ch.digitalmeat.company.level;

import ch.digitalmeat.company.Assets;
import ch.digitalmeat.company.game.economy.Economy;
import ch.digitalmeat.company.game.economy.EconomyLoader;
import ch.digitalmeat.company.level.Tile.TerrainType;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.JsonValue;

public class MapLoader {
	private final TerrainLayerLoader terrainLoader = new TerrainLayerLoader();
	private EconomyLoader economyLoader = new EconomyLoader();

	public GameMap load(String levelNameWithoutExtension) {
		String levelName = levelNameWithoutExtension + ".png";
		Texture levelTexture = Assets.getTexture(levelName);
		if (levelTexture == null) {
			throw new RuntimeException("Main level image has not been found: " + levelName);
		}

		String economyFile = levelNameWithoutExtension + ".json";
		JsonValue value = Assets.parseJsonFile(economyFile);
		Economy economy = economyLoader.load(value);
		if (value == null) {
			throw new RuntimeException("Could not open level economy file.");
		}

		GameMap map = new GameMap(levelTexture, economy);
		for (TerrainType terrain : TerrainType.values()) {
			String layerFile = levelNameWithoutExtension + "-" + terrain.levelExtension + ".png";
			if (Assets.fileExists(layerFile)) {
				Pixmap pixmap = Assets.getPixmap(layerFile);
				if (isPixmapValid(map, pixmap)) {
					terrainLoader.setType(terrain);
					terrainLoader.apply(map, pixmap);
				}
				pixmap.dispose();
			}
		}
		return map;
	}

	private boolean isPixmapValid(GameMap map, Pixmap pixmap) {
		return pixmap != null && map.width == pixmap.getWidth() && map.height == pixmap.getHeight();
	}

}
