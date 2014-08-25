package ch.digitalmeat.company.ui;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.color;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import java.util.List;

import ch.digitalmeat.company.Assets;
import ch.digitalmeat.company.Colors;
import ch.digitalmeat.company.Constants;
import ch.digitalmeat.company.event.AppEvent.AppEventType;
import ch.digitalmeat.company.game.GameSpeed;
import ch.digitalmeat.company.game.Settlement;
import ch.digitalmeat.company.level.GameMap;
import ch.digitalmeat.company.trigger.AppEventTrigger;
import ch.digitalmeat.company.trigger.GameSpeedTrigger;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Array;

public class GameUIBuilder implements EventListener {

	private Stage stage;

	private Table sidePanel;
	private Table infoBar;

	public final TriggerEventListener buildingsListener = new TriggerEventListener(new AppEventTrigger(AppEventType.Exit));
	public final TriggerEventListener convoisListener = new TriggerEventListener(new AppEventTrigger(AppEventType.Exit));

	private TriggerEventListener controlPauseListener = new TriggerEventListener(new GameSpeedTrigger(GameSpeed.Pause));
	private TriggerEventListener controlPlayListener = new TriggerEventListener(new GameSpeedTrigger(GameSpeed.Normal));
	private TriggerEventListener controlFastListener = new TriggerEventListener(new GameSpeedTrigger(GameSpeed.Fast));
	private TriggerEventListener controlUltraListener = new TriggerEventListener(new GameSpeedTrigger(GameSpeed.Ultra));

	public SelectBox<InfoBarItem> itemSelector;

	private Table sideContent;

	private Table details;

	private Label time;

	private Label day;

	public GameUIBuilder(Stage stage) {
		this.stage = stage;
	}

	private Button controlButton(TextureRegion icon, EventListener listener) {
		Button button = new Button(Assets.skin, "rect");
		button.add(new Image(icon)).prefWidth(12).prefHeight(12);
		button.setWidth(12);
		button.setHeight(12);
		button.addListener(listener);
		return button;
	}

	public void build(GameMap map) {
		NinePatchDrawable background = new NinePatchDrawable(Assets.skin.getPatch("default-rect"));
		Table table = new Table(Assets.skin);
		table.setFillParent(true);
		infoBar = new Table(Assets.skin);
		infoBar.setBackground(background);
		infoBar.add(controlButton(Assets.controlPause, controlPauseListener));
		infoBar.add(controlButton(Assets.controlPlay, controlPlayListener));
		infoBar.add(controlButton(Assets.controlFast, controlFastListener));
		infoBar.add(controlButton(Assets.controlUltraFast, controlUltraListener));
		infoBar.add().expandX();
		day = new Label("Day 1", Assets.skin);
		time = new Label("0:00", Assets.skin);
		time.setAlignment(Align.right);
		infoBar.add(day).padRight(3);
		infoBar.add(time).prefWidth(30).padRight(3).align(Align.right);

		sidePanel = new Table(Assets.skin);
		sidePanel.setTouchable(Touchable.enabled);
		EventListener stopTouch = new EventListener() {

			@Override
			public boolean handle(Event event) {
				if (event instanceof InputEvent) {
					event.stop();
					return true;
				}
				return false;
			}

		};
		details = new Table(Assets.skin);
		details.add("YAY");
		Table mainTable = new Table(Assets.skin);
		mainTable.add(infoBar).expandX().fillX().row();
		mainTable.add(details).expand().fill().row();
		infoBar.setTouchable(Touchable.enabled);
		infoBar.addListener(stopTouch);
		sidePanel.addListener(stopTouch);
		sidePanel.setBackground(background);
		itemSelector = new SelectBox<InfoBarItem>(Assets.skin, "info");
		itemSelector.addListener(this);
		sidePanel.add(itemSelector).fillX().expandX().row();

		sideContent = new Table(Assets.skin);
		sidePanel.add(sideContent).pad(3).expandY().fill();
		table.add(mainTable).expand().fill().align(Align.top | Align.left);
		// table.add().expand();
		table.add(sidePanel).prefWidth(Constants.SIDEPANEL_WIDTH).expandY().fill();
		fadeIn(table, 0.5f, 2f);
		stage.getRoot().setColor(Color.WHITE);
		stage.addActor(table);
		tick(map.getTick());
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
		// itemSelector.setDisabled(itemSelector.getItems().size <= 1);
		sideContent.clearChildren();
		if (item instanceof Settlement) {
			sideContent.add(item.getLabel()).align(Align.left | Align.top).row();
			sideContent.add().expand().row();
			sideContent.add(button("Buildings", "Buildings", buildingsListener)).fillX().row();
			sideContent.add(button("Convois", "Convois", convoisListener)).fillX().row();
		}
	}

	private TextButton button(String name, String label, EventListener listener) {
		TextButton button = new TextButton(label, Assets.skin);
		button.setName(name);
		button.addListener(listener);
		return button;
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

	@Override
	public boolean handle(Event event) {
		if (event instanceof ChangeEvent) {
			if (event.getTarget() == itemSelector) {
				updateInfoPanel(itemSelector.getSelected());
				return true;
			}
		}
		return false;
	}

	public static interface InfoBarItem {
		public String getLabel();
	}

	public final static InfoBarItem EMPTY = new InfoBarItem() {

		@Override
		public String getLabel() {
			return "-";
		}

		@Override
		public String toString() {
			return getLabel();
		}

	};

	public void hideExisting() {

	}

	public void buildBuildings(Settlement settlement) {

	}

	public void tick(long tick) {
		int hours = (int) (tick % Constants.HOURS_PER_DAY);
		int days = (int) (tick / Constants.HOURS_PER_DAY) + 1;
		day.setText("Day: " + days);
		time.setText(hours + ":00");
	}
}
