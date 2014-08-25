package ch.digitalmeat.company.game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import ch.digitalmeat.company.game.economy.Building;
import ch.digitalmeat.company.game.economy.Cost;
import ch.digitalmeat.company.game.economy.Good;
import ch.digitalmeat.company.level.GameMap;
import ch.digitalmeat.company.level.Tile;
import ch.digitalmeat.company.ui.GameUIBuilder.InfoBarItem;

import com.badlogic.gdx.Gdx;

public class Settlement implements InfoBarItem {
	public final GameMap map;
	public final Company owner;
	public final SettlementType type;
	public final String name;
	private final List<BuildingInstance> buildings = new ArrayList<BuildingInstance>();
	private final List<Tile> tiles = new ArrayList<Tile>();
	private final List<VehicleInstance> vehicles = new ArrayList<VehicleInstance>();
	private final Map<Good, Float> resources = new HashMap<Good, Float>();
	private float availableArea = 0;
	private float usedArea = 0;

	public Settlement(GameMap map, SettlementType type, Company owner, String name) {
		this.map = map;
		this.type = type;
		this.owner = owner;
		this.name = name;
		if (owner != null) {
			owner.settlements.add(this);
		}
		initGoods();
	}
	
	public void initGoods() {
		for(Entry<String, Good> entry : map.economy.goods.entrySet()) {
			resources.put(entry.getValue(), 100f);
		}
	}

	public boolean createBuilding(Building building) {
		float area = building.area;
		if (area + usedArea > availableArea) {
			Gdx.app.log("Settlement", "Cannot add building '" + building.label + "' to settlement. Not enough space available.");
			return false;
		}
		BuildingInstance instance = new BuildingInstance(building, this);
		buildings.add(instance);
		usedArea += building.area;
		return true;
	}

	public float area() {
		return availableArea;
	}

	public void addTile(Tile tile) {
		tiles.add(tile);
		availableArea += tile.type.buildableArea;
		for (Tile claimTile : tiles) {
			map.claim(tile, owner, tiles.size());
		}
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

	public List<Tile> tiles() {
		return tiles;
	}

	public void tick() {
		for (BuildingInstance building : buildings) {
			building.tick();
		}
	}
	
	public Collection<Vehicle> getAvailableVehicles() {
		return map.economy.vehicles.values();
	}
	
	public void trade(VehicleInstance vehicleInstance, Settlement with) {
		if(canTrade(vehicleInstance.vehicle)) {
			this.vehicles.add(vehicleInstance);
			pay(vehicleInstance.vehicle.cost, with);
			Gdx.app.log(getClass().getSimpleName(), "Traded " + vehicleInstance.vehicle.label + ". You have now " + vehicles.size() + " vehicles.");
		}
		if(with != null) {
			with.vehicles.remove(vehicleInstance);
		}
	}
	
	public void pay(Cost cost, Settlement to) {
		removeCost(cost);
		if(to != null) {
			to.addCost(cost);
		}
	}
	
	public void addCost(Cost cost) {
		for(Entry<Good, Float> entry : cost.materialCosts.entrySet()) {
			Float current = resources.get(entry.getKey());
			current += entry.getValue();
			resources.put(entry.getKey(), current);
		}
	}
	
	public void removeCost(Cost cost) {
		for(Entry<Good, Float> entry : cost.materialCosts.entrySet()) {
			Float current = resources.get(entry.getKey());
			current -= entry.getValue();
			resources.put(entry.getKey(), current);
		}
	}
	
	public boolean canTrade(Vehicle vehicle) {
		Cost vehicleCost = vehicle.cost;
		boolean canTrade = true;
		for(Entry<Good, Float> entry : vehicleCost.materialCosts.entrySet()) {
			Float available = resources.get(entry.getKey());
			if(available == null) {
				return false;
			}
			canTrade = canTrade && available >= entry.getValue();
		}
		return canTrade;
	}

	@Override
	public String getLabel() {
		return name;
	}

	@Override
	public String toString() {
		return getLabel();
	}
}
