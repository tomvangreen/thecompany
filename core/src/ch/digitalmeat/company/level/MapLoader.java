package ch.digitalmeat.company.level;

import ch.digitalmeat.company.Assets;
import ch.digitalmeat.company.level.Tile.TerrainType;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

public class MapLoader {
	private final LayerLoader layerLoader = new LayerLoader();

	public Map load(String levelNameWithoutExtension) {
		String levelName = levelNameWithoutExtension + ".png";
		Texture levelTexture = Assets.getTexture(levelName);
		if (levelTexture == null) {
			throw new RuntimeException("Main level image has not been found: " + levelName);
		}
		Map map = new Map(levelTexture.getWidth(), levelTexture.getHeight());
		for (TerrainType terrain : TerrainType.values()) {
			String layerFile = levelNameWithoutExtension + "-" + terrain.levelExtension + ".png";
			Pixmap pixmap = Assets.getPixmap(layerFile);
			layerLoader.applyLayer(map, pixmap, terrain);
		}
		return map;
	}
}
