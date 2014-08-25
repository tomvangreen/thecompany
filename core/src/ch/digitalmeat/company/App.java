package ch.digitalmeat.company;

import ch.digitalmeat.company.event.AppEvent;
import ch.digitalmeat.company.event.EventQueue;
import ch.digitalmeat.company.event.Events;
import ch.digitalmeat.company.event.GameSpeedEvent;
import ch.digitalmeat.company.event.GameSpeedEvent.GameSpeedEventListener;
import ch.digitalmeat.company.event.TileSelectedEvent;
import ch.digitalmeat.company.event.TileSelectedEvent.TileSelectedEventListener;
import ch.digitalmeat.company.game.GameSpeed;
import ch.digitalmeat.company.game.Settlement;
import ch.digitalmeat.company.gfx.Stages;
import ch.digitalmeat.company.level.GameMap;
import ch.digitalmeat.company.level.Tile;
import ch.digitalmeat.company.ui.GameUIBuilder;
import ch.digitalmeat.company.ui.UIBuilder;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class App extends ApplicationAdapter implements TileSelectedEventListener, GameSpeedEventListener {
	private SpriteBatch batch;
	private GameMap map;
	public Stages stages;
	public UIBuilder uiBuilder;
	public GameSpeed speed = GameSpeed.Pause;
	public GameSpeed lastSpeed = GameSpeed.Normal;

	@Override
	public void create() {
		EventQueue queue = Events.factory.getQueue();
		queue.listen(AppEvent.class, this);
		queue.listen(TileSelectedEvent.class, this);
		queue.listen(GameSpeedEvent.class, this);
		Assets.create();
		batch = new SpriteBatch();
		stages = new Stages(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT);

		uiBuilder = new UIBuilder(stages);
		uiBuilder.createMainMenu();
	}

	boolean positionedCam = false;

	public void startGame(boolean endless) {
		stages.fadeOut(new Runnable() {
			@Override
			public void run() {
				loadMap();
			}
		});
	}

	float tickTimer = 0f;

	@Override
	public void render() {
		Events.factory.getQueue().dispatch();

		if (map != null && !positionedCam) {
			Events.factory.cam(0.2f, true);
			positionedCam = true;
		}

		// Update
		float deltaTime = Gdx.graphics.getDeltaTime();
		stages.update(deltaTime);

		if (speed != GameSpeed.Pause) {
			tickTimer += deltaTime;
			while (tickTimer > speed.interval) {
				map.tick();
				uiBuilder.gameUI.tick(map.getTick());
				tickTimer -= speed.interval;
			}
		} else {
			tickTimer = 0;
		}

		// Render
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stages.draw();

	}

	@Override
	public void resize(int width, int height) {
		stages.resize(width, height);
	}

	public void exit() {
		Gdx.app.exit();
	}

	private Vector2 v = new Vector2();

	private void loadMap() {
		map = Assets.loadMap("levels/map-01/map-01");
		stages.game.clear();
		stages.ui.clear();
		stages.loadMap(map);
		uiBuilder.createGameUI(map);
		positionedCam = false;

		Settlement s = map.getCompanies().get(0).getSettlements().get(0);
		Tile tile = s.tiles().get(0);
		v.set(tile.x, tile.y);
		Events.factory.cam(v, false);
	}

	@Override
	public void tileSelected(int x, int y) {
		if (map != null) {
			Tile tile = map.tile(x, y);
			if (tile != null && tile.settlement != null) {
				uiBuilder.gameUI.setSelectionItem(tile.settlement);
			} else {
				uiBuilder.gameUI.setSelectionItem(GameUIBuilder.EMPTY);
			}
		}
	}

	@Override
	public void changeSpeed(GameSpeed speed) {
		this.speed = speed;
		if (speed != GameSpeed.Pause) {
			lastSpeed = speed;
		}
	}

	public void toggleSpeed() {
		if (speed == GameSpeed.Pause) {
			changeSpeed(lastSpeed);
		} else {
			changeSpeed(GameSpeed.Pause);
		}

	}

	public void toggleTerritories() {
		stages.mapRenderer.toggleTerritories();
	}

}
