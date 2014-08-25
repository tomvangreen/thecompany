package ch.digitalmeat.company.game;

import java.util.ArrayList;
import java.util.List;

import ch.digitalmeat.company.level.Tile;

import com.badlogic.gdx.Gdx;

public class Company {
	public int id;
	public String name;
	public boolean renderTerritory = true;

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
				// Gdx.app.log("Company", "Could not claim land " + tile.x + "/"
				// + tile.y + ". It already belongs to a different company.");
			}
			return;
		}
		Gdx.app.log("Company " + id, "Claiming Land " + tile.x + "/" + tile.y);
		tile.company = this;
		territory.add(tile);
		tile.visibleFor.add(this);
	}

	public List<Settlement> getSettlements() {
		return settlements;
	}
}
