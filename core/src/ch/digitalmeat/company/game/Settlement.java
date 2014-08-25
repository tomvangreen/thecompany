package ch.digitalmeat.company.game;

import java.util.ArrayList;
import java.util.List;

import ch.digitalmeat.company.level.Tile;

public class Settlement {
	public Company owner;
	public SettlementType type;
	public List<Tile> tiles = new ArrayList<Tile>();

	public static enum SettlementType {
		City, Harbor, Camp
	}
}
