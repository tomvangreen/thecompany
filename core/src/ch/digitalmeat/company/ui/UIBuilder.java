package ch.digitalmeat.company.ui;

import ch.digitalmeat.company.gfx.Stages;
import ch.digitalmeat.company.level.GameMap;

import com.badlogic.gdx.Gdx;

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
		Gdx.app.log("UI", "Create Main Menu");
		stages.ui.clear();
		mainMenu.build();
	}

	public void createGameUI(GameMap map) {
		Gdx.app.log("UI", "Create Game UI");
		stages.ui.clear();
		gameUI.build(map);

		gameUI.setSelectionItem(map.getSettlements().get(0), map.getSettlements().get(1));
	}
}
