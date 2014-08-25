package ch.digitalmeat.company.game;

public interface ExpeditionUnit {
	
	float getSpeed();
	
	float getWeight();
	
	float getFightForce();
	
	float getWorkForce();

	public abstract float getLineOfSight();

}