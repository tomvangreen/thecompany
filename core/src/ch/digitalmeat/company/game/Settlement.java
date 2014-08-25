package ch.digitalmeat.company.game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ch.digitalmeat.company.game.economy.Building;
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

	public Collection<Tile> tiles() {
		return tiles;
	}

	public void tick() {
		for (BuildingInstance building : buildings) {
			building.tick();
		}
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
