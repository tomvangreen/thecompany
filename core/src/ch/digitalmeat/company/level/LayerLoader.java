package ch.digitalmeat.company.level;

import ch.digitalmeat.company.level.Tile.TerrainType;

import com.badlogic.gdx.graphics.Pixmap;

public class LayerLoader {
	public boolean yDown;

	public LayerLoader() {
		this(false);
	}

	public LayerLoader(boolean yDown) {
		this.yDown = yDown;
	}

	public void applyLayer(GameMap map, Pixmap pixmap, TerrainType type) {

	}
}
