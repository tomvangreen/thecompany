package ch.digitalmeat.company.game;

import ch.digitalmeat.company.level.TileList;

public class Settlement {
	public Company owner;
	public SettlementType type;
	public TileList tiles = new TileList();

	public static enum SettlementType {
		City, Harbor, Camp
	}
}
