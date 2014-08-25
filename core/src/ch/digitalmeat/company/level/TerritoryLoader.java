package ch.digitalmeat.company.level;

import java.util.ArrayList;
import java.util.List;

import ch.digitalmeat.company.game.Company;

import com.badlogic.gdx.graphics.Color;

public class TerritoryLoader extends LayerLoader {
	List<Tile> territory = new ArrayList<Tile>();
	private Company company;

	@Override
	protected void handleTile(Tile tile, int pixel, Color color, int mapX, int mapY, int pixmapX, int pixmapY) {
		if (color.a > 0) {
			tile.company = this.company;
			territory.add(tile);
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
