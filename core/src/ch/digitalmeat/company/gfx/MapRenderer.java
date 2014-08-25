package ch.digitalmeat.company.gfx;

import ch.digitalmeat.company.Assets;
import ch.digitalmeat.company.Colors;
import ch.digitalmeat.company.Constants;
import ch.digitalmeat.company.game.Company;
import ch.digitalmeat.company.level.GameMap;
import ch.digitalmeat.company.level.Tile;

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
		if (map != null) {
			renderColor.set(getColor());
			renderColor.a *= parentAlpha;
			batch.setColor(renderColor);
			batch.draw(map.texture, getX(), getY());
			
			renderTerritories(batch, parentAlpha);
		}
	}

	private void renderTerritories(Batch batch, float parentAlpha) {
		for(Company company : map.getCompanies()) {
			renderColor.set(Colors.companyColors.get(company.id));
			renderColor.a = Constants.TERRITORY_LAYER_ALPHA * parentAlpha;
			batch.setColor(renderColor);
			
			if(company.renderTerritory) {
				for(Tile tile : company.territory) {
					batch.draw(Assets.whitePixel, tile.x, tile.y, 1f, 1f);
				}					
			}
		}
	}
}
