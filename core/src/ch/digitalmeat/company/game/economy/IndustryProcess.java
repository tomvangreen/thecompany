package ch.digitalmeat.company.game.economy;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public class IndustryProcess extends EconomyItem {
	public List<GoodComponent> inputs = new ArrayList<GoodComponent>();
	public List<GoodComponent> outputs = new ArrayList<GoodComponent>();

	public IndustryProcess(Economy economy) {
		super(economy);
	}

	@Override
	protected void readSpecifics(Json json, JsonValue jsonData) {
		// TODO Auto-generated method stub

	}

}
