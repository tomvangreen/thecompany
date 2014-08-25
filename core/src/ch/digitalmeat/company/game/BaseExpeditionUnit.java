package ch.digitalmeat.company.game;

public abstract class BaseExpeditionUnit implements ExpeditionUnit {
	
	protected float weight;
	
	protected float speed;
	
	protected float fightForce;
	
	protected float workForce;
	
	protected float lineOfSight;

	public BaseExpeditionUnit() {
		weight = 1f;
		speed = 1f;
		fightForce = 1f;
		workForce = 1f;
		lineOfSight = 1f;
	}

	@Override
	public float getWeight() {
		return weight;
	}

	@Override
	public float getSpeed() {
		return speed;
	}

	@Override
	public float getFightForce() {
		return fightForce;
	}

	@Override
	public float getWorkForce() {
		return workForce;
	}

	@Override
	public float getLineOfSight() {
		return lineOfSight;
	}
}
