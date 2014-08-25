package ch.digitalmeat.company.ui;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.color;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;
import ch.digitalmeat.company.Assets;
import ch.digitalmeat.company.Colors;
import ch.digitalmeat.company.event.AppEvent.AppEventType;
import ch.digitalmeat.company.trigger.AppEventTrigger;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

public class MainMenuBuilder {
	public final Stage stage;

	public final TriggerEventListener exitListener = new TriggerEventListener(new AppEventTrigger(AppEventType.Exit));
	public final TriggerEventListener startListener = new TriggerEventListener(new AppEventTrigger(AppEventType.StartGame));
	public final TriggerEventListener endlessListener = new TriggerEventListener(new AppEventTrigger(AppEventType.EndlessGame));

	public MainMenuBuilder(Stage stage) {
		this.stage = stage;
	}

	public void build() {
		Table table = new Table(Assets.skin);
		table.setFillParent(true);
		table.padBottom(20);
		Label title = new Label("The Company", Assets.skin, "big");
		fadeIn(title, 0, 1.5f);
		table.add(title).padTop(10).padBottom(1).row();
		Label subtitle = new Label("Connecting Worlds", Assets.skin);
		fadeIn(subtitle, 0.5f, 3f);
		table.add(subtitle).padBottom(10f).expandY().align(Align.top).row();

		table.add(button("mission", "Start Game: Mission Mode", startListener, 0)).fillX().padBottom(10).row();
		table.add(button("endless", "Start Game: Endless Mode", endlessListener, 1)).fillX().padBottom(10).row();
		table.add(button("credits", "Credits", TriggerEventListener.SINK, 2)).fillX().padBottom(10).row();
		table.add(button("exit", "Exit Game", exitListener, 3)).fillX().padBottom(10).row();

		stage.addActor(table);
	}

	private void fadeIn(Actor actor, float delayTime, float fadeTime) {
		actor.setColor(Colors.TRANSPARENT_WHITE);
		//@formatter:off
		actor.addAction(
			sequence(
				delay(delayTime)
				, Actions.forever(
					Actions.sequence(
						color(Color.WHITE, fadeTime)
						, color(Colors.MENU_FADE, fadeTime)
					)
				)
			)
		);
		//@formatter:on
	}

	private TextButton button(String name, String label, EventListener listener, int index) {
		TextButton button = new TextButton(label, Assets.skin);
		button.setName(name);
		button.addListener(listener);
		fadeIn(button, 1f + 0.5f * index, 3f);
		return button;
	}
}
