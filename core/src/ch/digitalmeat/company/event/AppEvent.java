package ch.digitalmeat.company.event;

import ch.digitalmeat.company.App;

public class AppEvent implements GameEvent<App> {

	public AppEventType type;

	@Override
	public void notify(App listener) {
		switch (type) {
		case Exit:
			listener.exit();
			break;
		case StartGame:
			listener.startGame(false);
			break;
		case EndlessGame:
			listener.startGame(true);
			break;
		default:
			break;
		}
	}

	public static enum AppEventType {
		Credits, UnloadGame, StartGame, EndlessGame, Exit, SpeedPause, SpeedNormal, SpeedFast, SpeedUltra
	}
}
