package ch.digitalmeat.company.ui;

import ch.digitalmeat.company.gfx.Stages;
import ch.digitalmeat.company.level.GameMap;

public class UIBuilder {
	private final Stages stages;
	private final MainMenuBuilder mainMenu;
	private final GameUIBuilder gameUI;

	public UIBuilder(Stages stages) {
		this.stages = stages;
		mainMenu = new MainMenuBuilder(stages.ui);
		gameUI = new GameUIBuilder(stages.ui);
	}

	public void createMainMenu() {
		stages.ui.clear();
		mainMenu.build();
	}

	public void createGameUI(GameMap map) {
		stages.ui.clear();
		gameUI.build(map);

	}
}
