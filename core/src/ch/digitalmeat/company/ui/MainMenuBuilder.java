package ch.digitalmeat.company.ui;

import ch.digitalmeat.company.Assets;
import ch.digitalmeat.company.event.AppEvent.AppEventType;
import ch.digitalmeat.company.trigger.AppEventTrigger;

import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

public class MainMenuBuilder {
	public final Stage stage;

	public final TriggerEventListener exitListener = new TriggerEventListener(new AppEventTrigger(AppEventType.Exit));

	public MainMenuBuilder(Stage stage) {
		this.stage = stage;
	}

	public void build() {
		Table table = new Table(Assets.skin);
		table.setFillParent(true);
		table.padBottom(20);
		table.add("The Company", "big").padTop(10).padBottom(1).row();
		table.add("Connecting Worlds").padBottom(10f).expandY().align(Align.top).row();

		table.add(button("mission", "Start Game: Mission Mode", TriggerEventListener.SINK)).fillX().padBottom(10).row();
		table.add(button("endless", "Start Game: Endless Mode", TriggerEventListener.SINK)).fillX().padBottom(10).row();
		table.add(button("credits", "Credits", TriggerEventListener.SINK)).fillX().padBottom(10).row();
		table.add(button("exit", "Exit Game", exitListener)).fillX().padBottom(10).row();

		stage.addActor(table);
	}

	private TextButton button(String name, String label, EventListener listener) {
		TextButton button = new TextButton(label, Assets.skin);
		button.setName(name);
		button.addListener(listener);
		return button;
	}
}
