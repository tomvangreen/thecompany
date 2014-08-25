package ch.digitalmeat.company.level.loader;

import java.util.ArrayList;
import java.util.List;

import ch.digitalmeat.company.game.Settlement;
import ch.digitalmeat.company.game.Settlement.SettlementType;
import ch.digitalmeat.company.level.GameMap;
import ch.digitalmeat.company.level.Tile;
import ch.digitalmeat.company.level.TileListTileMatcher;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

public class SettlementsLoader extends LayerLoader {

	private final TileListTileMatcher matcher = new TileListTileMatcher();

	private List<Tile> result = new ArrayList<Tile>();

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

		while (matcher.list.size() > 0) {
			Tile tile = matcher.list.get(0);
			result.clear();
			map.floodFill(result, tile, matcher);
			map.createSettlement(SettlementType.City, result, tile.company);
			matcher.list.removeAll(result);
		}

		logOutputs();

		matcher.list.clear();
	}

	private void logOutputs() {
		Gdx.app.log(SettlementsLoader.class.getSimpleName(), "created " + map.getSettlements().size() + " settlements");
		int index = 1;
		for (Settlement s : map.getSettlements()) {
			Gdx.app.log("SettlementsLoader", "  Settlement " + index);
			for (Tile tile : s.tiles) {
				Gdx.app.log("SettlementsLoader", "    " + tile.x + "/" + (map.height - tile.y) + " -> company " + tile.company.id);
			}
			index++;
		}
	}

	public GameMap getMap() {
		return map;
	}

	public void setMap(GameMap map) {
		this.map = map;
	}

}
