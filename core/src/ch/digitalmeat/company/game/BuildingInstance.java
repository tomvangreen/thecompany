package ch.digitalmeat.company.game;

import ch.digitalmeat.company.game.economy.Building;

public class BuildingInstance {
	public final Building building;
	public final Settlement settlement;

	public BuildingInstance(Building building, Settlement settlement) {
		this.building = building;
		this.settlement = settlement;
	}

	public void tick() {

	}
}
