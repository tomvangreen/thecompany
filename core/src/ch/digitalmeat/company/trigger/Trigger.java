package ch.digitalmeat.company.trigger;

public interface Trigger {
	public void trigger();

	public final static Trigger SINK = new Trigger() {
		@Override
		public void trigger() {
		}
	};
}
