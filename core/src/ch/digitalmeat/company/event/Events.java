package ch.digitalmeat.company.event;

import ch.digitalmeat.company.event.AppEvent.AppEventType;
import ch.digitalmeat.company.game.GameSpeed;
import ch.digitalmeat.company.hint.Hint;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class Events {
	public final static Events factory = new Events(new EventQueue());

	private final EventQueue queue;

	public Events(EventQueue queue) {
		this.queue = queue;
	}

	public Events hint(Hint hint, boolean show) {
		HintEvent event = new HintEvent();
		event.hint = hint;
		event.show = show;
		queue.queue(event);
		return this;
	}

	public Events cam(Vector2 position, float zoom, boolean setPosition, boolean setZoom, boolean targetMode) {
		CameraEvent event = new CameraEvent();
		if (position != null) {
			event.position.set(position);
		}
		event.zoom = zoom;
		event.setPosition = setPosition;
		event.setZoom = setZoom;
		event.setPosition = setPosition;
		event.setZoom = setZoom;
		event.targetMode = targetMode;
		queue.queue(event);
		return this;
	}

	public Events cam(Vector2 position, boolean targetMode) {
		return cam(position, 0f, true, false, targetMode);
	}

	public Events cam(Vector2 position) {
		return cam(position, false);
	}

	public Events cam(float zoom, boolean targetMode) {
		return cam(null, zoom, false, true, targetMode);
	}

	public Events cam(float zoom) {
		return cam(null, false);
	}

	public Events selectTile(int x, int y) {
		TileSelectedEvent event = new TileSelectedEvent();
		event.x = x;
		event.y = y;
		queue.queue(event);
		Gdx.app.log("Select Tile", x + "/" + y);
		return this;
	}

	public Events app(AppEventType type) {
		AppEvent event = new AppEvent();
		event.type = type;
		queue.queue(event);
		return this;
	}

	public Events speed(GameSpeed speed) {
		GameSpeedEvent event = new GameSpeedEvent();
		event.speed = speed;
		queue.queue(event);
		return this;
	}

	public EventQueue getQueue() {
		return queue;
	}

}