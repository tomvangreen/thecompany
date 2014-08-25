package ch.digitalmeat.company.game.economy;

import java.util.HashMap;
import java.util.Map;

import ch.digitalmeat.company.game.Vehicle;

public class Economy {
	public final Map<String, Good> goods = new HashMap<String, Good>();
	public final Map<String, Profession> professions = new HashMap<String, Profession>();
	public final Map<String, IndustryProcess> processes = new HashMap<String, IndustryProcess>();
	public final Map<String, Building> buildings = new HashMap<String, Building>();
	public final Map<String, Education> educations = new HashMap<String, Education>();
	public final Map<String, Vehicle> vehicles = new HashMap<String, Vehicle>();
}
