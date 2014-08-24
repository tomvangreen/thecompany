package ch.digitalmeat.company;

import ch.digitalmeat.company.gfx.Cams;
import ch.digitalmeat.company.gfx.MapRenderer;
import ch.digitalmeat.company.level.GameMap;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class App extends ApplicationAdapter {
	private SpriteBatch batch;
	private GameMap map;
	public Cams cams;

	@Override
	public void create() {
		Assets.create();
		batch = new SpriteBatch();
		cams = new Cams(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT);
		map = Assets.loadMap("levels/map-01/map-01");
		cams.game.addActor(new MapRenderer(map));
	}

	@Override
	public void render() {
		// Update
		float deltaTime = Gdx.graphics.getDeltaTime();
		cams.update(deltaTime);

		// Render
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		cams.draw();
	}

	@Override
	public void resize(int width, int height) {
		cams.resize(width, height);
	}
}
