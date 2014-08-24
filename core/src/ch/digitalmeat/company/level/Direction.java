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

}
