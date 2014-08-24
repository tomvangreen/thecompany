package ch.digitalmeat.company.level;

public class Tile {
	public final int x;
	public final int y;

	public TerrainType type = TerrainType.OpenSea;

	public Tile(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public static enum TerrainType {
		//@formatter:off
		OpenSea(0, false, 2f)
		, ShallowWater(1, false, 1f)
		, Beach(2, true, 2f)
		, Grass(3, true, 1f)
		, Wood(4, true, 4f)
		, LowMountains(5, true, 6f)
		, HighMountains(6, true, 20f) 
		;
		//@formatter:on

		public final int index;
		public final boolean isLand;
		public final float pathCost;

		TerrainType(int index, boolean isLand, float pathCost) {
			this.index = index;
			this.isLand = isLand;
			this.pathCost = pathCost;
		}
	}
}
