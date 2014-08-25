package ch.digitalmeat.company.hint;

import ch.digitalmeat.company.game.Settlement;
import ch.digitalmeat.company.ui.GameUIBuilder;

public class BuildingsHint implements Hint {

	public final GameUIBuilder builder;
	public Settlement settlement;

	public BuildingsHint(GameUIBuilder builder) {
		this.builder = builder;
	}

	@Override
	public void show() {
		builder.buildBuildings(settlement);
	}

	@Override
	public void hide() {

	}

}
