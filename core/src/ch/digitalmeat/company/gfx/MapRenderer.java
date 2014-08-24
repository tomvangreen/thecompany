package ch.digitalmeat.company.gfx;

import ch.digitalmeat.company.level.GameMap;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class MapRenderer extends Actor {
	private GameMap map;

	public MapRenderer() {
		this(null);
	}

	public MapRenderer(GameMap map) {
		this.map = map;
		setWidth(map.width);
		setHeight(map.height);
	}

	public void setMap(GameMap map) {
		this.map = map;
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		if (map != null) {
			batch.draw(map.texture, getX(), getY());
		}
	}
}