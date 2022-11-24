package VehicleRentalSystem.service;

import VehicleRentalSystem.database.BookingDatabase;
import VehicleRentalSystem.database.BranchDatabase;
import VehicleRentalSystem.database.VehicleDatabase;
import VehicleRentalSystem.model.Branch;
import VehicleRentalSystem.model.vehicle.Vehicle;
import VehicleRentalSystem.model.vehicle.VehicleType;
import VehicleRentalSystem.strategy.BookingStrategy;
import VehicleRentalSystem.strategy.BookingStrategyContext;

public class VehicleService {

    private final BranchDatabase branchDatabase;
    private final VehicleDatabase vehicleDatabase;
    private final BookingDatabase bookingDatabase;

    public VehicleService(BranchDatabase branchDatabase,
                          VehicleDatabase vehicleDatabase,
                          BookingDatabase bookingDatabase) {
        this.branchDatabase = branchDatabase;
        this.vehicleDatabase = vehicleDatabase;
        this.bookingDatabase = bookingDatabase;
    }

    public void addVehicle(String vehicleId, VehicleType vehicleType, String branchName) {
        Branch branch = branchDatabase.getBranchByName(branchName);
        if (branch == null) {
            throw new RuntimeException("Branch does not exists");
        }

        Vehicle vehicle = new Vehicle(vehicleId, vehicleType, branch);
        branch.getVehicles().add(vehicle);
        vehicleDatabase.insertVehicle(vehicle);
    }

    public void allocatePrice(String branchName, VehicleType vehicleType, int price) {
        Branch branch = branchDatabase.getBranchByName(branchName);
        if (branch == null) {
            throw new RuntimeException("Branch does not exists");
        }

        vehicleDatabase.insertPrice(vehicleType, price, branchName);
    }

    public void requestVehicle(VehicleType vehicleType, int startTime, int endTime) {
        BookingStrategyContext context = new BookingStrategyContext(vehicleDatabase, branchDatabase, bookingDatabase);
        BookingStrategy bookingStrategy = context.bookingStrategy(BookingStrategy.LOWEST_PRICE_BOOKING_STRATEGY);
        bookingStrategy.bookVehicle(vehicleType, startTime, endTime);
    }

}
