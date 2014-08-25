package ch.digitalmeat.company.game.economy;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public class Building extends EconomyItem {
	public float cost;
	public float area;
	public List<IndustryProcess> processes = new ArrayList<IndustryProcess>();
	public List<Education> educations = new ArrayList<Education>();
	public List<Effect> effects = new ArrayList<Effect>();

	public Building(Economy economy) {
		super(economy);
	}

	@Override
	protected void readSpecifics(Json json, JsonValue jsonData) {
		// TODO Auto-generated method stub

	}
}
