package ch.digitalmeat.company.level;

import java.util.HashSet;
import java.util.Set;

import ch.digitalmeat.company.game.Settlement;

public class Tile {
	public final int x;
	public final int y;
	public final GameMap map;

	public TerrainType type = TerrainType.Sea;
	public Settlement settlement;

	public final Set<Integer> visibleFor = new HashSet<Integer>();

	public Tile(GameMap map, int x, int y) {
		this.map = map;
		this.x = x;
		this.y = y;
	}

	public Tile neighbour(Direction direction) {
		return map.tile(x + direction.getXOffset(), y + direction.getYOffset());
	}

	public static enum TerrainType {
		//@formatter:off
		Sea(0, false, 2f, "sea")
		, Coast(1, false, 1f, "coast")
		, Beach(2, true, 2f, "beach")
		, Plains(3, true, 1f, "plains")
		, Woods(4, true, 4f, "woods")
		, Hills(5, true, 6f, "hills")
		, Mountains(6, true, 20f, "mountains") 
		;
		//@formatter:on

		public final int index;
		public final boolean isLand;
		public final float pathCost;
		public final String levelExtension;

		TerrainType(int index, boolean isLand, float pathCost, String levelExtension) {
			this.index = index;
			this.isLand = isLand;
			this.pathCost = pathCost;
			this.levelExtension = levelExtension;
		}
	}
}
