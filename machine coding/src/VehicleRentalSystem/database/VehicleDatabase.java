package VehicleRentalSystem.database;

import VehicleRentalSystem.model.Branch;
import VehicleRentalSystem.model.vehicle.Vehicle;
import VehicleRentalSystem.model.vehicle.VehicleRent;
import VehicleRentalSystem.model.vehicle.VehicleType;

import java.util.ArrayList;
import java.util.List;

public class VehicleDatabase {

    private final List<Vehicle> vehicles = new ArrayList<>();
    private final List<VehicleRent> vehicleRents = new ArrayList<>();

    private static VehicleDatabase INSTANCE = null;

    public static VehicleDatabase getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new VehicleDatabase();
        }
        return INSTANCE;
    }

    public void insertVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    public void insertPrice(VehicleType vehicleType, int price, String branchName) {
        vehicleRents.add(new VehicleRent(branchName, vehicleType, price));
    }

    public VehicleRent getVehicleRent(Branch branch, VehicleType vehicleType) {
        return vehicleRents.stream()
                .filter(vehicleRent -> vehicleRent.getVehicleType().equals(vehicleType) &&
                        vehicleRent.getBranchName().equals(branch.getName()))
                .findAny()
                .orElse(null);
    }
}
