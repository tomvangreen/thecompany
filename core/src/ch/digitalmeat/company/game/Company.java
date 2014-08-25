package ch.digitalmeat.company.game;

import java.util.ArrayList;
import java.util.List;

import ch.digitalmeat.company.level.Tile;

import com.badlogic.gdx.Gdx;

public class Company {
	public int id;
	public String name;
	public boolean renderTerritory;

	public final List<Settlement> settlements = new ArrayList<Settlement>();

	public final List<Tile> territory = new ArrayList<Tile>();
	public final List<Person> population = new ArrayList<Person>();
	public final List<Expedition> expeditions = new ArrayList<Expedition>();

	public void tick() {
		for (Person person : population) {
			person.tick();
		}
		for (Settlement settlement : settlements) {
			settlement.tick();
		}
		for (Expedition expedition : expeditions) {
			expedition.tick();
		}
	}

	public void claim(Tile tile) {
		if (tile.company != null) {
			if (tile.company != this) {
				Gdx.app.log("Company", "Could not claim land. It already belongs to a different company.");
			}
			return;
		}
		tile.company = this;
		territory.add(tile);
	}
}
