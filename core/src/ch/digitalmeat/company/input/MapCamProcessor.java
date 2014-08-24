package ch.digitalmeat.company.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MapCamProcessor implements InputProcessor {

	private final OrthographicCamera cam;

	private int rightClickPointer = -1;
	private Vector2 clickPosition = new Vector2();
	private Vector2 camPosition = new Vector2();

	private Viewport viewport;

	public MapCamProcessor(Viewport viewport, OrthographicCamera cam) {
		this.viewport = viewport;
		this.cam = cam;
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if (button == 1) {
			Gdx.app.log("Touch", screenX + "/" + screenY);
			rightClickPointer = pointer;
			clickPosition.set(screenX, screenY);
			camPosition.set(cam.position.x, cam.position.y);
			return true;
		}
		return false;
	}

	private Vector2 v = new Vector2();

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if (button == 1) {
			rightClickPointer = -1;
			return true;
		}
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		if (pointer == rightClickPointer) {
			// TODO: The scale is based on the screen scaling. extract that
			v.set(screenX, screenY).sub(clickPosition);
			Gdx.app.log("Delta", v.x + "/" + v.y);
			float scaleX = viewport.getWorldWidth() / viewport.getViewportWidth();
			float scaleY = viewport.getWorldWidth() / viewport.getViewportWidth();
			Gdx.app.log("Scale", scaleX + "/" + scaleY);
			v.scl(-scaleX, scaleY);
			cam.position.x = camPosition.x + v.x;
			cam.position.y = camPosition.y + v.y;
		}
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}
