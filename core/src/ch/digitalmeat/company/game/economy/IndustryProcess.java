package ch.digitalmeat.company.game.economy;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public class IndustryProcess extends EconomyItem {
	public List<IndustryProcessComponent> inputs = new ArrayList<IndustryProcessComponent>();
	public List<IndustryProcessComponent> outputs = new ArrayList<IndustryProcessComponent>();

	public IndustryProcess(Economy economy) {
		super(economy);
	}

	@Override
	protected void readSpecifics(Json json, JsonValue jsonData) {
		// TODO Auto-generated method stub

	}

	public class IndustryProcessComponent {
		public Good good;
		public int quantity;

		public IndustryProcessComponent(Good good, int quantity) {
			this.good = good;
			this.quantity = quantity;
		}
	}

}
