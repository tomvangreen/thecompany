package ch.digitalmeat.company.ui;

import ch.digitalmeat.company.game.Settlement;
import ch.digitalmeat.company.game.Vehicle;
import ch.digitalmeat.company.game.VehicleInstance;

import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;

public class BuyVehicleListener implements EventListener {
	
	public Settlement settlement;
	
	public Vehicle vehicle;

	public BuyVehicleListener(Settlement settlement, Vehicle vehicle) {
		this.settlement = settlement;
		this.vehicle = vehicle;
	}
	
	@Override
	public boolean handle(Event event) {
		if (event instanceof ChangeEvent) {
			settlement.trade(new VehicleInstance(vehicle), null);
			return true;
		}
		return false;
	}


}
