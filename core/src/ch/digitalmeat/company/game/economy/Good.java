package ch.digitalmeat.company.game.economy;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public class Good extends EconomyItem {
	public float basePrice;
	public float weight;

	public Good(Economy economy) {
		super(economy);
	}

	@Override
	protected void readSpecifics(Json json, JsonValue jsonData) {
		basePrice = jsonData.getFloat("price");
		weight = jsonData.getFloat("weight", 1f);
	}
}
