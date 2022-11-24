package VehicleRentalSystem.strategy;

import VehicleRentalSystem.database.BookingDatabase;
import VehicleRentalSystem.database.BranchDatabase;
import VehicleRentalSystem.database.VehicleDatabase;
import VehicleRentalSystem.model.Booking;
import VehicleRentalSystem.model.Branch;
import VehicleRentalSystem.model.vehicle.Vehicle;
import VehicleRentalSystem.model.vehicle.VehicleRent;
import VehicleRentalSystem.model.vehicle.VehicleType;

import java.awt.print.Book;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LowestPriceBookingStrategy implements BookingStrategy {

    private final VehicleDatabase vehicleDatabase;
    private final BranchDatabase branchDatabase;
    private final BookingDatabase bookingDatabase;

    public LowestPriceBookingStrategy(VehicleDatabase vehicleDatabase,
                                      BranchDatabase branchDatabase,
                                      BookingDatabase bookingDatabase) {
        this.vehicleDatabase = vehicleDatabase;
        this.branchDatabase = branchDatabase;
        this.bookingDatabase = bookingDatabase;
    }

    @Override
    public Vehicle bookVehicle(VehicleType vehicleType, int startTime, int endTime) {
        List<Branch> branches = branchDatabase.getBranches();


        List<Vehicle> availableVehicles = new ArrayList<>();
        for (Branch branch : branches) {
            List<Vehicle> vehicles = branch.getVehicles().stream()
                    .filter(vehicle -> vehicle.getVehicleType().equals(vehicleType))
                    .collect(Collectors.toList());

            for (Vehicle vehicle : vehicles) {
                boolean isVehicleAvailable = isVehicleAvailable(vehicle.getVehicleId(), startTime, endTime);
                if (isVehicleAvailable) {
                    availableVehicles.add(vehicle);
                }
            }
        }

        if (availableVehicles.isEmpty()) {
            throw new RuntimeException("Vehicle not found");
        }

        Vehicle minPriceVehicle = null;
        int minPrice = Integer.MAX_VALUE;

        for (Vehicle vehicle : availableVehicles) {
            VehicleRent vehicleRent = vehicleDatabase.getVehicleRent(vehicle.getBranch(), vehicle.getVehicleType());
            if (vehicleRent != null && vehicleRent.getAmount() < minPrice) {
                minPrice = vehicleRent.getAmount();
                minPriceVehicle = vehicle;
            }
        }

        if (minPriceVehicle == null) {
            throw new RuntimeException("Vehicle not found");
        }

        Booking booking = new Booking(minPriceVehicle.getVehicleId(), startTime, endTime, minPrice);
        bookingDatabase.insertBooking(booking);

        System.out.println("Booking: " + booking.toString());
        System.out.println("Vehicle: " + minPriceVehicle.toString());
        return minPriceVehicle;
    }

    public boolean isVehicleAvailable(String vehicleId, int startTime, int endTime) {
        List<Booking> bookings = bookingDatabase.getBookingsByVehicleId(vehicleId);

        for (Booking booking : bookings) {
            int bookingStartTime = booking.getStartTime();
            int bookingEndTime = booking.getEndTime();

            if (!(startTime > bookingEndTime || endTime < bookingStartTime)) {
                return false;
            }
        }
        return true;
    }
}
