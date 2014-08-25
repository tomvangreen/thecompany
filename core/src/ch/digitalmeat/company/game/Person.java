package ch.digitalmeat.company.game;

import java.util.ArrayList;
import java.util.List;

import ch.digitalmeat.company.game.economy.GoodComponent;
import ch.digitalmeat.company.game.economy.Profession;

public class Person {
	
	public final List<Profession> professions = new ArrayList<Profession>();
	
	public float happiness;
	
	public PersonType personType = PersonType.Apprentice;
	
	public GoodComponent consumption;
	
	public float weight;
	
	public float speed;
	
	public float fightForce;
	
	public float workForce;
	
	public float lineOfSight;

	public Person() {
		weight = 1f;
		speed = 1f;
		fightForce = 1f;
		workForce = 1f;
		lineOfSight = 1f;
		happiness = 1f;
	}

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
