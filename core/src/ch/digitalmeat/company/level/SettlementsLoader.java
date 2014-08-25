package ch.digitalmeat.company.level;

import java.util.ArrayList;
import java.util.List;

import ch.digitalmeat.company.game.Settlement.SettlementType;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

public class SettlementsLoader extends LayerLoader {

	private final TileListTileMatcher matcher = new TileListTileMatcher();
	
	private List<Tile> result = new ArrayList<Tile>();
	
	private GameMap map;

	@Override
	protected void beforeLoad() {
		matcher.list.clear();
	}

	@Override
	protected void handleTile(Tile tile, int pixel, Color color, int mapX, int mapY, int pixmapX, int pixmapY) {
		if (color.a > 0) {
			matcher.list.add(tile);
		}
	}

	@Override
	protected void afterLoad() {
		
		while(matcher.list.size() > 0) {
			Tile tile = matcher.list.get(0);
			result.clear();
			map.floodFill(result, tile, matcher);
			map.createSettlement(SettlementType.City, result, tile.company);
			matcher.list.removeAll(result);
		}
		
		Gdx.app.log(SettlementsLoader.class.getSimpleName(), "created " + map.getSettlements().size() + " settlements");
		matcher.list.clear();
	}

	public GameMap getMap() {
		return map;
	}

	public void setMap(GameMap map) {
		this.map = map;
	}

}
