package ch.digitalmeat.company;

import ch.digitalmeat.company.event.AppEvent;
import ch.digitalmeat.company.event.Events;
import ch.digitalmeat.company.gfx.Stages;
import ch.digitalmeat.company.level.GameMap;
import ch.digitalmeat.company.ui.UIBuilder;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class App extends ApplicationAdapter {
	private SpriteBatch batch;
	private GameMap map;
	public Stages stages;
	public UIBuilder uiBuilder;

	@Override
	public void create() {
		Events.factory.getQueue().listen(AppEvent.class, this);
		Assets.create();
		batch = new SpriteBatch();
		stages = new Stages(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT);

		uiBuilder = new UIBuilder(stages);
		uiBuilder.createMainMenu();
	}

	public void startGame(boolean endless) {
		stages.fadeOut(new Runnable() {
			@Override
			public void run() {
				loadMap();
			}
		});
	}

	@Override
	public void render() {
		Events.factory.getQueue().dispatch();

		// Update
		float deltaTime = Gdx.graphics.getDeltaTime();
		stages.update(deltaTime);

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

	private void loadMap() {
		map = Assets.loadMap("levels/map-01/map-01");
		stages.game.clear();
		stages.ui.clear();
		stages.loadMap(map);
		uiBuilder.createGameUI(map);
	}
}
