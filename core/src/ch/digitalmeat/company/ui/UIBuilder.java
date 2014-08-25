package ch.digitalmeat.company.ui;

import ch.digitalmeat.company.gfx.Stages;

public class UIBuilder {
	private final Stages stages;
	private final MainMenuBuilder mainMenu;

	public UIBuilder(Stages stages) {
		this.stages = stages;
		mainMenu = new MainMenuBuilder(stages.ui);
	}

	public void createMainMenu() {
		stages.ui.clear();
		mainMenu.build();
	}
}
