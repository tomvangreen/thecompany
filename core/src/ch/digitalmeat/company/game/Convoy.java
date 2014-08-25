package ch.digitalmeat.company.game;

import java.util.ArrayList;
import java.util.List;

public class Convoy extends BaseExpeditionUnit {

	public final List<Vehicle> vehicles = new ArrayList<Vehicle>();
	
	public final List<Waypoint> waypoints = new ArrayList<Waypoint>();

	@Override
	public float getWeight() {
		weight = 0f;
		for (Vehicle vehicle : vehicles) {
			weight += vehicle.getWeight();
		}
		return weight;
	}

	@Override
	public float getSpeed() {
		speed = Float.MAX_VALUE;
		for (Vehicle vehicle : vehicles) {
			float tmpSpeed = vehicle.getSpeed();
			if(speed < tmpSpeed) {
				speed = tmpSpeed;
			}
		}
		return super.getSpeed();
	}

	@Override
	public float getFightForce() {
		fightForce = 0f;
		for (Vehicle vehicle : vehicles) {
			fightForce += vehicle.getFightForce();
		}
		return super.getFightForce();
	}

	@Override
	public float getWorkForce() {
		workForce = 0f;
		for (Vehicle vehicle : vehicles) {
			workForce += vehicle.getWorkForce();
		}
		return super.getWorkForce();
	}
	
	@Override
	public float getLineOfSight() {
		lineOfSight = 0f;
		for (Vehicle vehicle : vehicles) {
			float tmpLos= vehicle.getSpeed();
			if(lineOfSight < tmpLos) {
				lineOfSight = tmpLos;
			}
		}
		return super.getSpeed();
	}

}
