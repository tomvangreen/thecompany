package ch.digitalmeat.company.event;

import ch.digitalmeat.company.event.GameSpeedEvent.GameSpeedEventListener;
import ch.digitalmeat.company.game.GameSpeed;

public class GameSpeedEvent implements GameEvent<GameSpeedEventListener> {

	public GameSpeed speed;

	@Override
	public void notify(GameSpeedEventListener listener) {
		listener.changeSpeed(speed);
	}

	public static interface GameSpeedEventListener {
		public void changeSpeed(GameSpeed speed);
	}

}
