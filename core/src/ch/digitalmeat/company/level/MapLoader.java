package ch.digitalmeat.company.level;

import ch.digitalmeat.company.Assets;
import ch.digitalmeat.company.Constants;
import ch.digitalmeat.company.level.Tile.TerrainType;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

public class MapLoader {
	private final TerrainLayerLoader terrainLoader = new TerrainLayerLoader();
	private final SettlementsLoader settlementsLoader = new SettlementsLoader();

	public GameMap load(String levelNameWithoutExtension) {
		String levelName = levelNameWithoutExtension + ".png";
		Texture levelTexture = Assets.getTexture(levelName);
		if (levelTexture == null) {
			throw new RuntimeException("Main level image has not been found: " + levelName);
		}
		GameMap map = new GameMap(levelTexture);
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
