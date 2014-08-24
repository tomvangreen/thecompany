package ch.digitalmeat.company.gfx;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Cams {
	public final Viewport gameViewport;
	public final Viewport uiViewport;

	public final OrthographicCamera gameCam;
	public final OrthographicCamera uiCam;

	public Cams(int width, int height) {
		gameCam = new OrthographicCamera();
		gameViewport = new FitViewport(width, height, gameCam);

		uiCam = new OrthographicCamera();
		uiViewport = new FitViewport(width, height, uiCam);
	}

	public void resize(int width, int height) {
		gameViewport.update(width, height);
		uiViewport.update(width, height);
	}

	public void update(float deltaTime) {
		gameCam.update();
		uiCam.update();
	}
}
