package ch.digitalmeat.company.game.economy;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public class Good extends EconomyItem {
	public float basePrice;
	public float weight;
	public GoodType type = GoodType.General;

	public Good(Economy economy) {
		super(economy);
	}

	@Override
	protected void readSpecifics(Json json, JsonValue jsonData) {
		basePrice = jsonData.getFloat("price");
		weight = jsonData.getFloat("weight", 1f);
		if (jsonData.has("type")) {
			String typeString = jsonData.getString("type");
			type = GoodType.valueOf(typeString);
		}
	}

	public static enum GoodType {
		General, Food, BuildingMaterial, Weapons
	}
}
