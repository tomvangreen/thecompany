package ch.digitalmeat.company.event;

public interface GameEvent<L> {
	public void notify(final L listener);
}