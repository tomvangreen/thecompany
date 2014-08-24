package ch.digitalmeat.company.level;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Color;

public class SettlementsLoader extends LayerLoader {
	private List<Tile> tiles = new ArrayList<Tile>();

	@Override
	protected void beforeLoad() {
		tiles.clear();
	}

	@Override
	protected void handleTile(Tile tile, int pixel, Color color, int mapX, int mapY, int pixmapX, int pixmapY) {
		if (color.a > 0) {
			tiles.add(tile);
		}
	}

	@Override
	protected void afterLoad() {

		tiles.clear();
	}

}
