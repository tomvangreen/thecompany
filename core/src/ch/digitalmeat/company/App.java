package ch.digitalmeat.company;

import ch.digitalmeat.company.gfx.Cams;
import ch.digitalmeat.company.level.Map;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class App extends ApplicationAdapter {
	private SpriteBatch batch;
	private Map map;
	public Cams cams;

	@Override
	public void create() {
		Assets.create();
		batch = new SpriteBatch();
		cams = new Cams(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT);
		map = Assets.loadMap("levels/map-01/map-01");
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if (map != null) {
			float deltaTime = Gdx.graphics.getDeltaTime();
			cams.update(deltaTime);
			batch.setProjectionMatrix(cams.gameCam.combined);
			batch.begin();
			batch.draw(map.texture, 0, 0);
			batch.end();
		}
	}

	@Override
	public void resize(int width, int height) {
		cams.resize(width, height);
	}
}
