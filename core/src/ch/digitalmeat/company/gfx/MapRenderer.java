package ch.digitalmeat.company.gfx;

import ch.digitalmeat.company.level.GameMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class MapRenderer extends Actor {
	private Color renderColor = new Color(Color.WHITE);
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
		Gdx.app.log("MapRenderer", "Parent Alpha: " + parentAlpha);
		if (map != null) {
			renderColor.set(getColor());
			renderColor.a *= parentAlpha;
			batch.setColor(renderColor);
			batch.draw(map.texture, getX(), getY());
		}
	}
}
