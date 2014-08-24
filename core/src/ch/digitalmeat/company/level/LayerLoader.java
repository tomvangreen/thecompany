package ch.digitalmeat.company.level;

import ch.digitalmeat.company.level.Tile.TerrainType;

import com.badlogic.gdx.graphics.Color;
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
		Color pixelColor = new Color();
		
		for (int y = 0; y < map.height; y++) {
			for (int x = 0; x < map.height; x++) {

				int tilesY = y;

				if (yDown) {
					tilesY = map.height - y;
				}
				
				int pixmapY = map.height - y;

				Tile tile = map.tile(x, tilesY);

				if (tile != null) {
					int pixel = pixmap.getPixel(x, pixmapY);
					
					Color.rgba8888ToColor(pixelColor, pixel);
					if (pixelColor.a > 0) {
						tile.type = type;
					}
				}

			}
		}
	}

}
