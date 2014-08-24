package ch.digitalmeat.company.level;

import ch.digitalmeat.company.level.Tile.TerrainType;

import com.badlogic.gdx.graphics.Color;

public class TerrainLayerLoader extends LayerLoader {
	private TerrainType type;

	@Override
	protected void handleTile(Tile tile, int pixel, Color color, int mapX, int mapY, int pixmapX, int pixmapY) {
		if (color.a > 0) {
			tile.type = getType();
		}
	}

	public TerrainType getType() {
		return type;
	}

	public void setType(TerrainType type) {
		this.type = type;
	}

	@Override
	protected void beforeLoad() {

	}

	@Override
	protected void afterLoad() {

	}

}
