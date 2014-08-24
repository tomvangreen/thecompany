package ch.digitalmeat.company.level;

import java.util.ArrayList;
import java.util.List;

public class TileList {
	private List<Tile> tiles = new ArrayList<Tile>();

	public void add(Tile tile) {
		tiles.add(tile);
	}

	public void remove(Tile tile) {
		tiles.remove(tile);
	}

}
