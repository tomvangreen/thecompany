package ch.digitalmeat.company.ui;

import ch.digitalmeat.company.Assets;
import ch.digitalmeat.company.level.GameMap;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class GameUIBuilder {

	private Stage stage;

	public GameUIBuilder(Stage stage) {
		this.stage = stage;
	}

	public void build(GameMap map) {
		Table table = new Table(Assets.skin);
		table.setFillParent(true);
		table.add("Yay");
		stage.addActor(table);
	}

}
