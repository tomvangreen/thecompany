package ch.digitalmeat.company.level;

import java.util.ArrayList;
import java.util.List;

import ch.digitalmeat.company.game.Company;
import ch.digitalmeat.company.game.Settlement;
import ch.digitalmeat.company.game.Settlement.SettlementType;
import ch.digitalmeat.company.game.economy.Economy;

import com.badlogic.gdx.graphics.Texture;

public class GameMap {
	public final Economy economy;
	public final Texture texture;
	public final int width;
	public final int height;

	private List<Tile> tiles = new ArrayList<Tile>();
	
	private List<Settlement> settlements = new ArrayList<Settlement>();
	
	private List<Company> companies = new ArrayList<Company>();

	public GameMap(Texture texture, Economy economy) {
		this.economy = economy;
		this.texture = texture;
		this.width = texture.getWidth();
		this.height = texture.getHeight();

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				tiles.add(new Tile(this, x, y));
			}
		}
	}

	public Tile tile(int x, int y) {
		if (areCoordinatesValid(x, y)) {
			return tiles.get(y * width + x);
		}
		return null;
	}

	public boolean areCoordinatesValid(int x, int y) {
		return x > 0 && x < width && y > 0 && y < height;
	}

	public void floodFill(List<Tile> results, Tile tile, TileMatcher matcher) {
		if (!results.contains(tile) && matcher.matches(tile)) {
			results.add(tile);
			for (Direction direction : Direction.values()) {
				Tile neighbour = tile.neighbour(direction);
				if (neighbour != null) {
					floodFill(results, neighbour, matcher);
				}
			}
		}
	}

	public Settlement createSettlement(SettlementType type, List<Tile> tiles, Company company) {
		for(Tile tile : tiles) {
			if(tile.settlement != null) {
				throw new IllegalArgumentException("Tile already has a settlement");
			}
		}
		
		Settlement settlement = new Settlement();
		settlement.tiles = tiles;
		settlement.owner = company;
		
		for(Tile tile : tiles) {
			tile.settlement = settlement;
		}
		
		settlements.add(settlement);
		return settlement;
	}

	public List<Settlement> getSettlements() {
		return settlements;
	}

	public List<Company> getCompanies() {
		return companies;
	}
	
}
