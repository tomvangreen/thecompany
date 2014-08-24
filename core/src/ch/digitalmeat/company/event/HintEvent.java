package ch.digitalmeat.company.event;

import ch.digitalmeat.company.event.HintEvent.HintEventListener;
import ch.digitalmeat.company.ui.Hint;

public class HintEvent implements GameEvent<HintEventListener> {

	public Hint hint;
	public boolean show;

	@Override
	public void notify(HintEventListener listener) {
		listener.notifyHintEvent(hint, show);
	}

	public static interface HintEventListener {
		public void notifyHintEvent(Hint hint, boolean show);
	}
}
