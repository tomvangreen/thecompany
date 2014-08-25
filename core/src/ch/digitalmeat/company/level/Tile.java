package ch.digitalmeat.company.level;

import java.util.ArrayList;
import java.util.List;

import ch.digitalmeat.company.game.Company;
import ch.digitalmeat.company.game.Settlement;

public class Tile {
	public final int x;
	public final int y;
	public final GameMap map;

	public TerrainType type = TerrainType.Sea;
	public Settlement settlement;

	public Company company;

	public final List<Company> visibleFor = new ArrayList<Company>();

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
		Sea(0, false, 2f, "sea", 0f)
		, Coast(1, false, 1f, "coast", 0f)
		, Beach(2, true, 2f, "beach", 5f)
		, Plains(3, true, 1f, "plains", 20f)
		, Woods(4, true, 4f, "woods", 10f)
		, Hills(5, true, 6f, "hills", 15f)
		, Mountains(6, true, 20f, "mountains", 5f) 
		;
		//@formatter:on

		public final int index;
		public final boolean isLand;
		public final float pathCost;
		public final String levelExtension;
		public final float buildableArea;

		TerrainType(int index, boolean isLand, float pathCost, String levelExtension, float buildableArea) {
			this.index = index;
			this.isLand = isLand;
			this.pathCost = pathCost;
			this.levelExtension = levelExtension;
			this.buildableArea = buildableArea;
		}
	}

	@Override
	public String toString() {
		return "Tile(" + x + "," + y + ")";
	}

}
