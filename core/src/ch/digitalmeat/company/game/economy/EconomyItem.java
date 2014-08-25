package ch.digitalmeat.company.game.economy;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.JsonValue;

public abstract class EconomyItem implements Serializable {
	public final Economy economy;
	public String id;
	public String label;
	public String description;

	public EconomyItem(Economy economy) {
		this.economy = economy;
	}

	protected abstract void readSpecifics(Json json, JsonValue jsonData);

	@Override
	public void write(Json json) {
		throw new RuntimeException("Writing not supported");
	}

	@Override
	public void read(Json json, JsonValue node) {
		id = node.name;
		label = node.getString("label", "");
		description = node.getString("description", "");
		readSpecifics(json, node);
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "(" + id + ")";
	}
}
