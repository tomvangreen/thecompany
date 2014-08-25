package ch.digitalmeat.company.game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ch.digitalmeat.company.game.economy.Building;
import ch.digitalmeat.company.level.Tile;

import com.badlogic.gdx.Gdx;

public class Settlement {
	public final Company owner;
	public final SettlementType type;
	private final List<BuildingInstance> buildings = new ArrayList<BuildingInstance>();
	private final List<Tile> tiles = new ArrayList<Tile>();
	private float availableArea = 0;
	private float usedArea = 0;

	public Settlement(SettlementType type, Company owner) {
		this.type = type;
		this.owner = owner;
		if (owner != null) {
			owner.settlements.add(this);
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

	public void tick() {

	}
}
