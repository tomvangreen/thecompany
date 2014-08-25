package ch.digitalmeat.company.game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ch.digitalmeat.company.level.Tile;

public class Settlement {
	public final Company owner;
	public final SettlementType type;
	private final List<Tile> tiles = new ArrayList<Tile>();
	private float area = 0;

	public Settlement(SettlementType type, Company owner) {
		this.type = type;
		this.owner = owner;
		if (owner != null) {
			owner.settlements.add(this);
		}
	}

	public float area() {
		return area;
	}

	public void addTile(Tile tile) {
		tiles.add(tile);
		area += tile.type.buildableArea;
	}

	public void addTiles(Collection<Tile> tiles) {
		for (Tile tile : tiles) {
			tile.settlement = this;
			addTile(tile);
		}
	}

	public static enum SettlementType {
		City, Harbor, Camp
	}

	public Collection<Tile> tiles() {
		return tiles;
	}
}
