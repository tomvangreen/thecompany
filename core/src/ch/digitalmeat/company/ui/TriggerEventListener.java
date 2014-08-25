package ch.digitalmeat.company.ui;

import ch.digitalmeat.company.trigger.Trigger;

import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;

public class TriggerEventListener implements EventListener {
	private final Trigger trigger;

	public TriggerEventListener(Trigger trigger) {
		this.trigger = trigger;
	}

	@Override
	public boolean handle(Event event) {
		if (event instanceof ChangeEvent) {
			trigger.trigger();
			return true;
		}
		return false;
	}

	public final static TriggerEventListener SINK = new TriggerEventListener(Trigger.SINK);
}
