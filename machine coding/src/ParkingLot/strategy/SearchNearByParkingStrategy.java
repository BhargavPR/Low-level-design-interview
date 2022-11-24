package ParkingLot.strategy;

import ParkingLot.model.Floor;
import ParkingLot.model.ParkingLot;
import ParkingLot.model.Slot;
import ParkingLot.model.vehicle.VehicleType;

import java.util.List;

public class SearchNearByParkingStrategy implements SearchParkingStrategy {

    @Override
    public Slot searchParkingSlot(ParkingLot parkingLot, VehicleType vehicleType) {
        List<Floor> floors = parkingLot.getFloor();

        for (Floor floor : floors) {
            List<Slot> slots = floor.getSlots();
            for (Slot slot : slots) {
                if (slot.getVehicleType().equals(vehicleType) && slot.getParkedVehicle() == null) {
                    return slot;
                }
            }
        }
        return null;
    }

}
