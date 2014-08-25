package ch.digitalmeat.company.game;

import java.util.ArrayList;
import java.util.List;

public class Expedition {

	public final List<Vehicle> vehicles = new ArrayList<Vehicle>();

	public final List<Waypoint> waypoints = new ArrayList<Waypoint>();

	protected float weight;

	protected float speed;

	protected float fightForce;

	protected float workForce;

	protected float lineOfSight;

	public Expedition() {
		weight = 1f;
		speed = 1f;
		fightForce = 1f;
		workForce = 1f;
		lineOfSight = 1f;
	}

	public float getWeight() {
		weight = 0f;
		for (Vehicle vehicle : vehicles) {
			weight += vehicle.weight;
		}
		return weight;
	}

	public float getSpeed() {
		speed = Float.MAX_VALUE;
		for (Vehicle vehicle : vehicles) {
			if (speed < vehicle.speed) {
				speed = vehicle.speed;
			}
		}
		return speed;
	}

	public float getFightForce() {
		fightForce = 0f;
		for (Vehicle vehicle : vehicles) {
			fightForce += vehicle.fightForce;
		}
		return fightForce;
	}

	public float getWorkForce() {
		workForce = 0f;
		for (Vehicle vehicle : vehicles) {
			workForce += vehicle.workForce;
		}
		return workForce;
	}

	public float getLineOfSight() {
		lineOfSight = 0f;
		for (Vehicle vehicle : vehicles) {
			float tmpLos = vehicle.lineOfSight;
			if (lineOfSight < tmpLos) {
				lineOfSight = tmpLos;
			}
		}
		return lineOfSight;
	}

	public void tick() {
	}
}
