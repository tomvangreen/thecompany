package ch.digitalmeat.company.level;

import java.util.ArrayList;
import java.util.List;

public class TileListTileMatcher implements TileMatcher {
	public final List<Tile> list = new ArrayList<Tile>();

	@Override
	public boolean matches(Tile tile) {
		return list.contains(tile);
	}
}