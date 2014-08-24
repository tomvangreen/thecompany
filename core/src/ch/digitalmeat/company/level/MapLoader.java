package ch.digitalmeat.company.level;

import ch.digitalmeat.company.Assets;
import ch.digitalmeat.company.level.Tile.TerrainType;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

public class MapLoader {
	private final LayerLoader layerLoader = new LayerLoader();

	public GameMap load(String levelNameWithoutExtension) {
		String levelName = levelNameWithoutExtension + ".png";
		Texture levelTexture = Assets.getTexture(levelName);
		if (levelTexture == null) {
			throw new RuntimeException("Main level image has not been found: " + levelName);
		}
		GameMap map = new GameMap(levelTexture);
		for (TerrainType terrain : TerrainType.values()) {
			String layerFile = levelNameWithoutExtension + "-" + terrain.levelExtension + ".png";
			if (Assets.fileExists(layerFile)) {
				Pixmap pixmap = Assets.getPixmap(layerFile);
				if (isPixmapValid(map, pixmap)) {
					layerLoader.applyLayer(map, pixmap, terrain);
				}
			}
		}
		return map;
	}

	private boolean isPixmapValid(GameMap map, Pixmap pixmap) {
		return pixmap != null && map.width == pixmap.getWidth() && map.height == pixmap.getHeight();
	}
}
