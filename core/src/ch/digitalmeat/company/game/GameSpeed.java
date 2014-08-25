package ch.digitalmeat.company.game;

public enum GameSpeed {
	Pause(-1f), Normal(1f), Fast(0.2f), Ultra(0.05f);

	public final float interval;

	GameSpeed(float speed) {
		this.interval = speed;
	}
}
