package VehicleRentalSystem.strategy;

import VehicleRentalSystem.model.vehicle.Vehicle;
import VehicleRentalSystem.model.vehicle.VehicleType;

public interface BookingStrategy {

    public static final String LOWEST_PRICE_BOOKING_STRATEGY = "LOWEST_PRICE_BOOKING_STRATEGY";

    Vehicle bookVehicle(VehicleType vehicleType, int startTime, int endTime);

}
