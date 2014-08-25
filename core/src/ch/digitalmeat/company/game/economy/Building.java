package ch.digitalmeat.company.game.economy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public class Building extends EconomyItem {
	public float cost;
	public float area;
	public final List<IndustryProcess> processes = new ArrayList<IndustryProcess>();
	public final List<Education> educations = new ArrayList<Education>();
	public final List<Effect> effects = new ArrayList<Effect>();

	public Building(Economy economy) {
		super(economy);
	}

	@Override
	protected void readSpecifics(Json json, JsonValue jsonData) {
		cost = jsonData.getFloat("cost", 1);
		area = jsonData.getFloat("area", 1);
		loadList(processes, jsonData.get("processes"), economy.processes);
		loadList(educations, jsonData.get("educations"), economy.educations);
	}

	public final static <T> void loadList(List<T> list, JsonValue node, Map<String, T> map) {
		if (node == null) {
			return;
		}
		node = node.child;
		while (node != null) {
			T t = map.get(node.asString());
			if (t != null) {
				list.add(t);
			}
			node = node.next;
		}
	}

}
