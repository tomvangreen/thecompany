package ch.digitalmeat.company.gfx;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Cams {
	public final Viewport gameViewport;
	public final Viewport uiViewport;

	public final OrthographicCamera gameCam;
	public final OrthographicCamera uiCam;

	public final Stage game;
	public final Stage ui;

	public Cams(int width, int height) {
		gameCam = new OrthographicCamera();
		gameViewport = new FitViewport(width, height, gameCam);
		game = new Stage(gameViewport);

		uiCam = new OrthographicCamera();
		uiViewport = new FitViewport(width, height, uiCam);
		ui = new Stage(uiViewport);
	}

	public void resize(int width, int height) {
		gameViewport.update(width, height);
		uiViewport.update(width, height);
	}

	public void update(float deltaTime) {
		gameCam.update();
		game.act(deltaTime);
		uiCam.update();
		ui.act(deltaTime);
	}

	public void draw() {
		game.draw();
		ui.draw();
	}
}
