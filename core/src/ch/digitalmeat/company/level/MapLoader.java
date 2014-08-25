package ch.digitalmeat.company.level;

import ch.digitalmeat.company.Assets;
import ch.digitalmeat.company.Constants;
import ch.digitalmeat.company.game.economy.Economy;
import ch.digitalmeat.company.game.economy.EconomyLoader;
import ch.digitalmeat.company.level.Tile.TerrainType;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.JsonValue;

public class MapLoader {
	private final TerrainLayerLoader terrainLoader = new TerrainLayerLoader();
	private EconomyLoader economyLoader = new EconomyLoader();
	private final SettlementsLoader settlementsLoader = new SettlementsLoader();

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
		loadTerrains(levelNameWithoutExtension, map);

		settlementsLoader.setMap(map);
		loadSettlements(levelNameWithoutExtension, map);
		return map;
	}

	private void loadTerrains(String levelNameWithoutExtension, GameMap map) {
		for (TerrainType terrain : TerrainType.values()) {
			terrainLoader.setType(terrain);
			String layerFile = levelNameWithoutExtension + "-" + terrain.levelExtension + ".png";
			loadLayer(map, layerFile, terrainLoader);
		}
	}

	private void loadSettlements(String levelNameWithoutExtension, GameMap map) {
		String layerFile = levelNameWithoutExtension + "-" + Constants.SETTLEMENTS_FILE_EXTENSION + ".png";
		loadLayer(map, layerFile, settlementsLoader);
	}

	private void loadLayer(GameMap map, String layerFile, LayerLoader layerLoader) {
		if (Assets.fileExists(layerFile)) {
			Pixmap pixmap = Assets.getPixmap(layerFile);
			if (isPixmapValid(map, pixmap)) {
				layerLoader.apply(map, pixmap);
			}
			pixmap.dispose();
		}
	}

	private boolean isPixmapValid(GameMap map, Pixmap pixmap) {
		return pixmap != null && map.width == pixmap.getWidth() && map.height == pixmap.getHeight();
	}

}
