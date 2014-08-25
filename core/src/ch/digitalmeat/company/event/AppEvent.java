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
		default:
			break;
		}
	}

	public static enum AppEventType {
		MainMenu, Credits, UnloadGame, LoadGame, Exit
	}
}
