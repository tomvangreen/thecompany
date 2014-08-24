package ch.digitalmeat.company.level;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;

public abstract class LayerLoader {
	public boolean yDown;
	private Color color = new Color();

	public LayerLoader() {
		this(false);
	}

	public LayerLoader(boolean yDown) {
		this.yDown = yDown;
	}

	protected abstract void beforeLoad();

	protected abstract void afterLoad();

	protected abstract void handleTile(Tile tile, int pixel, Color color, int mapX, int mapY, int pixmapX, int pixmapY);

	public void apply(GameMap map, Pixmap pixmap) {
		beforeLoad();
		for (int pixmapY = 0; pixmapY < map.height; pixmapY++) {
			for (int pixmapX = 0; pixmapX < map.height; pixmapX++) {
				int mapX = pixmapX;
				int mapY = pixmapY;

				if (yDown) {
					mapY = map.height - pixmapY;
				}

				Tile tile = map.tile(mapX, mapY);

				if (tile != null) {
					int pixel = pixmap.getPixel(pixmapX, pixmapY);
					Color.rgba8888ToColor(color, pixel);
					handleTile(tile, pixel, color, mapX, mapY, pixmapX, pixmapY);
				}

			}
		}
		afterLoad();
	}

}
