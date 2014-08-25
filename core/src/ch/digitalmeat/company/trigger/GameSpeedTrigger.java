package ch.digitalmeat.company.trigger;

import ch.digitalmeat.company.event.Events;
import ch.digitalmeat.company.game.GameSpeed;

public class GameSpeedTrigger implements Trigger {
	public GameSpeed speed;

	public GameSpeedTrigger(GameSpeed speed) {
		this.speed = speed;
	}

	@Override
	public void trigger() {
		Events.factory.speed(speed);
	}
}
