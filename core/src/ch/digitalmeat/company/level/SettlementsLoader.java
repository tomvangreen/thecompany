package ch.digitalmeat.company.level;

import java.util.ArrayList;
import java.util.List;

import ch.digitalmeat.company.game.Settlement.SettlementType;
import ch.digitalmeat.company.level.GameMap.TileMatcher;

import com.badlogic.gdx.graphics.Color;

public class SettlementsLoader extends LayerLoader {
	private List<Tile> tiles = new ArrayList<Tile>();

	private List<Tile> result = new ArrayList<Tile>();
	
	private GameMap map;

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
		while(tiles.size() > 0) {
			Tile tile = tiles.get(0);
			result.clear();
			map.floodFill(result, tile, null);
			map.createSettlement(SettlementType.City, result, null);
			tiles.removeAll(result);
			
		}
		tiles.clear();
	}

	public GameMap getMap() {
		return map;
	}

	public void setMap(GameMap map) {
		this.map = map;
	}

}
