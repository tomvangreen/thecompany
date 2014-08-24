package ch.digitalmeat.company.level;

import java.util.ArrayList;
import java.util.List;

public class Map {
	public final int width;
	public final int height;

	private List<Tile> tiles = new ArrayList<Tile>();

	public Map(int width, int height) {
		this.width = width;
		this.height = height;

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				tiles.add(new Tile(x, y));
			}
		}
	}

	public Tile tile(int x, int y) {
		if (areCoordinatesValid(x, y)) {
			return tiles.get(y * width + x);
		}
		return null;
	}

	private boolean areCoordinatesValid(int x, int y) {
		return x > 0 && x < width && y > 0 && y < height;
	}
}
