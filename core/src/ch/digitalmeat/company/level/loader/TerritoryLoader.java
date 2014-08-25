package ch.digitalmeat.company.level.loader;

import ch.digitalmeat.company.game.Company;
import ch.digitalmeat.company.level.Tile;

import com.badlogic.gdx.graphics.Color;

public class TerritoryLoader extends LayerLoader {
	private Company company;

	@Override
	protected void handleTile(Tile tile, int pixel, Color color, int mapX, int mapY, int pixmapX, int pixmapY) {
		if (color.a > 0) {
			company.claim(tile);
		}
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Override
	protected void beforeLoad() {

	}

	@Override
	protected void afterLoad() {

	}

}
