package ch.digitalmeat.company.trigger;

import ch.digitalmeat.company.event.AppEvent.AppEventType;
import ch.digitalmeat.company.event.Events;

public class AppEventTrigger implements Trigger {
	public AppEventType type;

	public AppEventTrigger(AppEventType type) {
		this.type = type;
	}

	@Override
	public void trigger() {
		Events.factory.app(type);
	}
}
