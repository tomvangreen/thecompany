package ch.digitalmeat.company.event;

import ch.digitalmeat.company.event.CameraEvent.CameraEventListener;

import com.badlogic.gdx.math.Vector2;

public class CameraEvent implements GameEvent<CameraEventListener> {

	public final Vector2 position = new Vector2();
	public float zoom = 1f;
	public boolean setZoom;
	public boolean setPosition;
	public boolean targetMode;

	@Override
	public void notify(CameraEventListener listener) {
		if (targetMode) {
			if (setPosition) {
				listener.setCamPositionTarget(position);
			}
			if (setZoom) {
				listener.setCamZoomTarget(zoom);
			}
		} else {
			if (setPosition) {
				listener.setCamPosition(position);
			}
			if (setZoom) {
				listener.setCamZoom(zoom);
			}
		}
	}

	public static interface CameraEventListener {
		public void setCamPosition(Vector2 position);

		public void setCamZoom(float zoom);

		public void setCamPositionTarget(Vector2 position);

		public void setCamZoomTarget(float zoom);
	}
}
