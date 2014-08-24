package ch.digitalmeat.company.gfx;

import ch.digitalmeat.company.input.MapCamProcessor;
import ch.digitalmeat.company.level.GameMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Stages {
	public final Viewport gameViewport;
	public final Viewport uiViewport;

	public final OrthographicCamera gameCam;
	public final OrthographicCamera uiCam;

	public final Stage game;
	public final Stage ui;

	private InputMultiplexer plexer = new InputMultiplexer();
	private InputMultiplexer gamePlexer = new InputMultiplexer();
	private InputMultiplexer uiPlexer = new InputMultiplexer();
	private GameMap map;

	public Stages(int width, int height) {
		gameCam = new OrthographicCamera();
		gameViewport = new FitViewport(width, height, gameCam);
		game = new Stage(gameViewport);

		uiCam = new OrthographicCamera();
		uiViewport = new FitViewport(width, height, uiCam);
		ui = new Stage(uiViewport);

		plexer.addProcessor(uiPlexer);
		uiPlexer.addProcessor(ui);

		plexer.addProcessor(gamePlexer);
		gamePlexer.addProcessor(new MapCamProcessor(gameViewport, gameCam));
		gamePlexer.addProcessor(game);
		Gdx.input.setInputProcessor(plexer);
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

	public void loadMap(GameMap map) {
		unloadMap();
		this.map = map;
		game.clear();
		game.addActor(new MapRenderer(map));
	}

	public void unloadMap() {
		if (this.map != null) {

		}
	}
}
