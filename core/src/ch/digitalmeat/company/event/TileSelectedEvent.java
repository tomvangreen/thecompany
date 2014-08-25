package ch.digitalmeat.company.event;

import ch.digitalmeat.company.event.TileSelectedEvent.TileSelectedEventListener;

public class TileSelectedEvent implements GameEvent<TileSelectedEventListener>{
	
	public int x;
	public int y;
	
	@Override
	public void notify(TileSelectedEventListener listener) {
		listener.tileSelected(x, y);
	}
	
	public static interface TileSelectedEventListener {
		void tileSelected(int x, int y);
	}

}
