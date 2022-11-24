package ParkingLot.strategy;

import ParkingLot.model.ParkingLot;
import ParkingLot.model.Slot;
import ParkingLot.model.vehicle.VehicleType;

public interface SearchParkingStrategy {

    public static final String SEARCH_NEAR_BY_PARKING_STRATEGY = "SEARCH_NEAR_BY_PARKING_STRATEGY";

    Slot searchParkingSlot(ParkingLot parkingLot, VehicleType vehicleType);

}
