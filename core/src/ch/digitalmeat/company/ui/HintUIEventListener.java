package ch.digitalmeat.company.ui;

import ch.digitalmeat.company.hint.Hint;

import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;

public class HintUIEventListener implements EventListener {
	public final Hint hint;
	public final boolean show;

	public HintUIEventListener(Hint hint, boolean show) {
		this.hint = hint;
		this.show = show;
	}

	@Override
	public boolean handle(Event event) {
		if (event instanceof ChangeEvent) {
			if(show) {
				hint.show();
			} else {
				hint.hide();
			}
			return true;
		}
		return false;
	}

}
