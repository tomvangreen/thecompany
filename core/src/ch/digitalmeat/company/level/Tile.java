package ch.digitalmeat.company.level;

public class Tile {
	public final int x;
	public final int y;

	public TerrainType type = TerrainType.Sea;

	public Tile(int x, int y) {
		this.x = x;
		this.y = y;
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
