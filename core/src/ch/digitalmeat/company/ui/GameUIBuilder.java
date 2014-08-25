package ch.digitalmeat.company.ui;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.color;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import java.util.List;

import ch.digitalmeat.company.Assets;
import ch.digitalmeat.company.Colors;
import ch.digitalmeat.company.Constants;
import ch.digitalmeat.company.level.GameMap;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Array;

public class GameUIBuilder implements EventListener {

	private Stage stage;

	private Table sidePanel;
	private Table infoBar;

	public SelectBox<InfoBarItem> itemSelector;

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
		itemSelector = new SelectBox<InfoBarItem>(Assets.skin, "info");
		itemSelector.addListener(this);
		sidePanel.add(itemSelector).fillX().expandX().row();
		sidePanel.add("Yay").expandY();
		table.add(infoBar).expandX().fillX().align(Align.top | Align.left);
		// table.add().expand();
		table.add(sidePanel).prefWidth(Constants.SIDEPANEL_WIDTH).expandY().fill();
		fadeIn(table, 0.5f, 2f);
		stage.getRoot().setColor(Color.WHITE);
		stage.addActor(table);
	}

	public void setSelectionItems(List<InfoBarItem> items) {
		Array<InfoBarItem> newItems = new Array<InfoBarItem>();
		for (InfoBarItem item : items) {
			newItems.add(item);
		}
		itemSelector.setItems(newItems);
		updateInfoPanel(items.get(0));
	}

	public void setSelectionItem(InfoBarItem item) {
		Array<InfoBarItem> newItems = new Array<InfoBarItem>();
		newItems.add(item);
		itemSelector.setItems(newItems);
		updateInfoPanel(item);
	}

	public void setSelectionItem(InfoBarItem... items) {
		Array<InfoBarItem> newItems = new Array<InfoBarItem>();
		newItems.addAll(items);
		itemSelector.setItems(newItems);
		updateInfoPanel(items[0]);
	}

	public void updateInfoPanel(InfoBarItem item) {

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

	public static interface InfoBarItem {
		public String getLabel();
	}

	@Override
	public boolean handle(Event event) {
		if (event instanceof ChangeEvent) {
			if (event.getTarget() == itemSelector) {
				updateInfoPanel(itemSelector.getSelected());
			}
		}
		return false;
	}

}
