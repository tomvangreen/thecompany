package ch.digitalmeat.company.game;

import java.util.ArrayList;
import java.util.List;

import ch.digitalmeat.company.level.Tile;

public class Company {
	public int id;
	public String name;

	public final List<Settlement> settlements = new ArrayList<Settlement>();
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
		tile.company = this;
	}
}
