package ch.digitalmeat.company.game;

import java.util.ArrayList;
import java.util.List;

import ch.digitalmeat.company.game.economy.Good;


public class VehicleInstance {
	
	public final Vehicle vehicle;
	
	public float loadedWeight;
	
	public final List<Good> goods;

	public VehicleInstance(Vehicle vehicle) {
		this.vehicle = vehicle;
		this.goods = new ArrayList<Good>();
	}
	
	public void tick() {
		
	}
	
	public Float getCurrentCapacity() {
		return vehicle.storageCapacity - loadedWeight;
	}
	
	public void loadGood(Good good, float amount) {
		float loadingWeight = good.weight * amount;
		goods.add(good);
		loadedWeight += loadingWeight;
	}
	
	public boolean canLoadGood(Good good, float amount) {
		float loadingWeight = good.weight * amount;
		return loadingWeight + loadedWeight <= vehicle.storageCapacity;
	}

}
