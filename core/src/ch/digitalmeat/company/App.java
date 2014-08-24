package ch.digitalmeat.company;

import ch.digitalmeat.company.gfx.Stages;
import ch.digitalmeat.company.level.GameMap;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class App extends ApplicationAdapter {
	private SpriteBatch batch;
	private GameMap map;
	public Stages stages;

	@Override
	public void create() {
		Assets.create();
		batch = new SpriteBatch();
		stages = new Stages(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT);
		map = Assets.loadMap("levels/map-01/map-01");
		stages.loadMap(map);
	}

	@Override
	public void render() {
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
}
