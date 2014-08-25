package ch.digitalmeat.company.gfx;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.alpha;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;
import ch.digitalmeat.company.event.CameraEvent;
import ch.digitalmeat.company.event.CameraEvent.CameraEventListener;
import ch.digitalmeat.company.event.Events;
import ch.digitalmeat.company.input.MapInputProcessor;
import ch.digitalmeat.company.level.GameMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Stages implements CameraEventListener {
	public final Viewport gameViewport;
	public final Viewport uiViewport;

	public final OrthographicCamera gameCam;
	public final OrthographicCamera uiCam;

	public final Stage game;
	public final Stage ui;

	private final Vector2 camTarget = new Vector2();
	private float targetZoom = 1f;

	private InputMultiplexer plexer = new InputMultiplexer();
	private InputMultiplexer gamePlexer = new InputMultiplexer();
	private InputMultiplexer uiPlexer = new InputMultiplexer();
	private GameMap map;

	public Stages(int width, int height) {
		Events.factory.getQueue().listen(CameraEvent.class, this);

		gameCam = new OrthographicCamera();
		gameViewport = new FitViewport(width, height, gameCam);
		game = new Stage(gameViewport);
		camTarget.set(gameCam.position.x, gameCam.position.y);

		uiCam = new OrthographicCamera();
		uiViewport = new FitViewport(width, height, uiCam);
		ui = new Stage(uiViewport);

		plexer.addProcessor(uiPlexer);
		uiPlexer.addProcessor(ui);

		plexer.addProcessor(gamePlexer);
		gamePlexer.addProcessor(new MapInputProcessor(gameViewport, gameCam));
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

		updateGameCam(deltaTime);
	}

	private Vector2 v = new Vector2();

	private void updateGameCam(float deltaTime) {
		if (camTarget != null) {
			Vector3 position = gameCam.position;
			v.set(camTarget).sub(position.x, position.y).scl(deltaTime);
			position.x += v.x;
			position.y += v.y;
		}
		float difference = gameCam.zoom - targetZoom;
		gameCam.zoom -= difference * deltaTime * 4;
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
		game.addAction(Actions.fadeIn(1f));
	}

	public void unloadMap() {
		if (this.map != null) {

		}
	}

	@Override
	public void setCamPosition(Vector2 position) {
		gameCam.position.x = position.x;
		gameCam.position.y = position.y;
		gameCam.update();
		setCamPositionTarget(position);
	}

	@Override
	public void setCamZoom(float zoom) {
		gameCam.zoom = zoom;
		gameCam.update();
		setCamZoomTarget(zoom);
	}

	@Override
	public void setCamPositionTarget(Vector2 position) {
		this.camTarget.set(position);
	}

	@Override
	public void setCamZoomTarget(float zoom) {
		this.targetZoom = zoom;
	}

	public void createFade(Runnable runnable) {
		//@formatter:off
		game.addAction(alpha(0f, 1f));
		ui.addAction(
			sequence(
				alpha(0f, 1f)
				, run(runnable)
				, alpha(1f, 1f)
				
			)
		);
		//@formatter:on
	}
}
