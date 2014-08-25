package ch.digitalmeat.company.game;

import java.util.ArrayList;
import java.util.List;

import ch.digitalmeat.company.game.economy.Good.GoodType;
import ch.digitalmeat.company.level.Tile.TerrainType;

public class Vehicle {
	
	public float storageCapacity;
	
	public final List<GoodType> allowedGoods;

	public final List<TerrainType> passableTerrains;
	
	public float weight;
	
	public float speed;
	
	public float fightForce;
	
	public float workForce;
	
	public float lineOfSight;
	
	public final List<Vehicle> vehicles;
	
	public final List<Person> persons;

	public Vehicle() {
		storageCapacity = 0f;
		allowedGoods = new ArrayList<GoodType>();
		passableTerrains = new ArrayList<TerrainType>();
		weight = 1f;
		speed = 1f;
		fightForce = 1f;
		workForce = 1f;
		lineOfSight = 1f;
		vehicles = new ArrayList<Vehicle>();
		persons = new ArrayList<Person>();
	}

}
