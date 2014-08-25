package ch.digitalmeat.company.game;

import java.util.ArrayList;
import java.util.List;

import ch.digitalmeat.company.game.economy.GoodComponent;
import ch.digitalmeat.company.game.economy.Profession;

public class Person extends BaseExpeditionUnit {
	
	public final List<Profession> professions = new ArrayList<Profession>();
	
	public float happiness = 0f;
	
	public PersonType personType = PersonType.Apprentice;
	
	public GoodComponent consumption;

	public static enum PersonType {
		// @formatter: off
		Apprentice
		, Worker
		, Soldier
		, Master
		, General
		, Scout;
		// @formatter: on
	}
}
