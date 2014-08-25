package ch.digitalmeat.company.game;

import java.util.ArrayList;
import java.util.List;

import ch.digitalmeat.company.game.economy.Cost;
import ch.digitalmeat.company.game.economy.Economy;
import ch.digitalmeat.company.game.economy.EconomyItem;
import ch.digitalmeat.company.game.economy.Good.GoodType;
import ch.digitalmeat.company.level.Tile.TerrainType;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public class Vehicle extends EconomyItem {
	
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
	
	public final Cost cost;

	public Vehicle(Economy economy) {
		super(economy);
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
		cost = new Cost();
	}

	@Override
	protected void readSpecifics(Json json, JsonValue jsonData) {
		storageCapacity = jsonData.getFloat("storageCapacity", 1);
		weight = jsonData.getFloat("weight", 1);
		speed = jsonData.getFloat("speed", 1);
		fightForce = jsonData.getFloat("fightForce", 1);
		workForce = jsonData.getFloat("workForce", 1);
		lineOfSight = jsonData.getFloat("lineOfSight", 1);
		readGoods(jsonData.get("allowedGoods"));
		readTerrains(jsonData.get("passableTerrains"));
		readCost(jsonData.get("cost"));
	}

	private void readTerrains(JsonValue jsonData) {
		jsonData = jsonData.child;
		while(jsonData != null) {
			passableTerrains.add(TerrainType.valueOf(jsonData.asString()));
			jsonData = jsonData.next;
		}
	}
	
	private void readGoods(JsonValue jsonData) {
		jsonData = jsonData.child;
		while(jsonData != null) {
			allowedGoods.add(GoodType.valueOf(jsonData.asString()));
			jsonData = jsonData.next;
		}
	}
	
	private void readCost(JsonValue jsonData) {
		jsonData = jsonData.child;
		while(jsonData != null) {
			JsonValue cost = jsonData.get(0);
			this.cost.materialCosts.put(economy.goods.get(cost.name), jsonData.getFloat(cost.name));
			jsonData = jsonData.next;
		}
	}

}
