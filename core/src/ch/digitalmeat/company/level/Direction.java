package ch.digitalmeat.company.level;

public enum Direction {
	//@formatter:off
	North(0)
	, East(1)
	, South(2)
	, West(3)
	;
	//@formatter:on

	public final int cardinality;

	Direction(int cardinality) {
		this.cardinality = cardinality;
	}

	public int getXOffset() {
		if (this == East) {
			return 1;
		} else if (this == West) {
			return -1;
		}
		return 0;
	}

	public int getYOffset() {
		if (this == North) {
			return 1;
		} else if (this == South) {
			return -1;
		}
		return 0;
	}

}
