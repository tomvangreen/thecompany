package ch.digitalmeat.company.game.economy;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public class Education extends EconomyItem {
	public Cardinality required = Cardinality.Any;
	public List<Profession> prerequisites = new ArrayList<Profession>();

	public Education(Economy economy) {
		super(economy);
	}

	@Override
	protected void readSpecifics(Json json, JsonValue jsonData) {

	}

	public static enum Cardinality {
		Any, All
	}
}
