package ch.digitalmeat.company.game;


public class VehicleInstance {
	
	public final Vehicle vehicle;

	public VehicleInstance(Vehicle vehicleDef) {
		vehicle = vehicleDef.cpy();
	}
	
	public void tick() {
		
	}

}
