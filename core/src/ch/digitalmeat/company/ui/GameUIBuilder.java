package ch.digitalmeat.company.ui;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.color;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;
import ch.digitalmeat.company.Assets;
import ch.digitalmeat.company.Colors;
import ch.digitalmeat.company.Constants;
import ch.digitalmeat.company.level.GameMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;

public class GameUIBuilder {

	private Stage stage;

	private Table sidePanel;
	private Table infoBar;

	public GameUIBuilder(Stage stage) {
		this.stage = stage;
	}

	public void build(GameMap map) {
		NinePatchDrawable background = new NinePatchDrawable(Assets.skin.getPatch("default-rect"));
		Table table = new Table(Assets.skin);
		table.setFillParent(true);
		infoBar = new Table(Assets.skin);
		infoBar.setBackground(background);
		infoBar.add("Monniz:");
		infoBar.add("1337").padRight(5);
		infoBar.add("Population:");
		infoBar.add("37").padRight(5);
		infoBar.add("Stuff:");
		infoBar.add("20");

		sidePanel = new Table(Assets.skin);
		sidePanel.setBackground(background);
		sidePanel.add("Yay");
		table.add(infoBar).expandX().fillX().align(Align.top | Align.left);
		// table.add().expand();
		table.add(sidePanel).prefWidth(Constants.SIDEPANEL_WIDTH).expandY().fill();
		fadeIn(table, 0.5f, 2f);
		stage.getRoot().setColor(Color.WHITE);
		stage.addActor(table);
		stage.addActor(new Actor() {
			@Override
			public void draw(Batch batch, float parent) {
				Gdx.app.log("UI", "Parent Alpha: " + parent);
			}
		});
	}

	private void fadeIn(Actor actor, float delayTime, float fadeTime) {
		actor.setColor(Colors.TRANSPARENT_WHITE);
		//@formatter:off
		actor.addAction(
			sequence(
				delay(delayTime)
				, color(Color.WHITE, fadeTime)
			)
		);
		//@formatter:on
	}

}
