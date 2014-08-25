package ch.digitalmeat.company.level;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ch.digitalmeat.company.Assets;
import ch.digitalmeat.company.game.Company;
import ch.digitalmeat.company.game.Settlement;
import ch.digitalmeat.company.game.Settlement.SettlementType;
import ch.digitalmeat.company.game.economy.Economy;
import ch.digitalmeat.company.level.DistanceTileMatcher.DistanceFunction;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class GameMap {
	public final Economy economy;
	public final Texture texture;
	public final int width;
	public final int height;

	private long ticks;

	private final List<Tile> tiles = new ArrayList<Tile>();

	private final List<Settlement> settlements = new ArrayList<Settlement>();

	private final List<Company> companies = new ArrayList<Company>();

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
		return x >= 0 && x < width && y >= 0 && y < height;
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

	private List<String> cityNames;

	private Random random = new Random();

	public String pickCityName() {
		if (cityNames == null || cityNames.size() == 0) {
			cityNames = Assets.getCityNames();
		}
		int pick = 0;
		if (cityNames.size() > 1) {
			pick = random.nextInt(cityNames.size());
		}
		return cityNames.remove(pick);
	}

	public Settlement createSettlement(SettlementType type, List<Tile> tiles, Company company) {
		validateTilesForSettlement(tiles);

		Settlement settlement = new Settlement(this, SettlementType.City, company, pickCityName());
		settlement.addTiles(tiles);
		settlements.add(settlement);

		return settlement;
	}

	private void validateTilesForSettlement(List<Tile> tiles) {
		for (Tile tile : tiles) {
			if (tile.settlement != null) {
				throw new IllegalArgumentException("Tile already has a settlement");
			}
		}
	}

	public List<Settlement> getSettlements() {
		return settlements;
	}

	public List<Company> getCompanies() {
		return companies;
	}

	public void tick() {
		ticks++;
		Gdx.app.log("GameMap", "Tick " + ticks);
		for (Company company : companies) {
			company.tick();
		}
	}

	private final List<Tile> temp = new ArrayList<Tile>();
	private final DistanceTileMatcher distanceMatcher = new DistanceTileMatcher();

	public void claim(Tile tile, Company company, float distance) {
		if (distance == 0) {
			company.claim(tile);
			return;
		}
		distanceMatcher.setTile(tile);
		distanceMatcher.setDistance(distance);
		distanceMatcher.setFunction(DistanceFunction.Manhattan);
		floodFill(temp, tile, distanceMatcher);
		for (Tile candidate : temp) {
			company.claim(candidate);
		}
	}

	public long getTick() {
		return ticks;
	}
}
