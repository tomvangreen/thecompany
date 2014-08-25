package ch.digitalmeat.company.game;

import java.util.ArrayList;
import java.util.List;

import ch.digitalmeat.company.game.economy.Good.GoodType;
import ch.digitalmeat.company.level.Tile.TerrainType;

public class Vehicle extends BaseExpeditionUnit {
	
	public float storageCapacity;
	
	public final List<GoodType> allowedGoods;

	public final List<TerrainType> passableTerrains;

	public Vehicle() {
		storageCapacity = 0f;
		allowedGoods = new ArrayList<GoodType>();
		passableTerrains = new ArrayList<TerrainType>();
	}

}
