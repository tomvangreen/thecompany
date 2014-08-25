package ch.digitalmeat.company.level;

import com.badlogic.gdx.math.Vector2;

public class DistanceTileMatcher implements TileMatcher {
	private DistanceFunction function;
	private float distance;
	private Tile tile;

	@Override
	public boolean matches(Tile other) {
		if (tile == null || other == null) {
			return false;
		}
		float otherDistance = function.getDistance(tile, other);
		// Gdx.app.log("DistanceTileMatcher", "" + otherDistance);
		return otherDistance <= distance;
	}

	public DistanceFunction getFunction() {
		return function;
	}

	public void setFunction(DistanceFunction function) {
		this.function = function;
	}

	public float getDistance() {
		return distance;
	}

	public void setDistance(float distance) {
		this.distance = distance;
	}

	public Tile getTile() {
		return tile;
	}

	public void setTile(Tile tile) {
		this.tile = tile;
	}

	public static enum DistanceFunction {
		Manhattan, Linear;
		private Vector2 v = new Vector2();

		public float getDistance(Tile a, Tile b) {
			if (this == Manhattan) {
				return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
			} else if (this == Linear) {
				return v.set(a.x, a.y).sub(b.x, b.y).len();
			}
			return 0;
		}
	}

}
