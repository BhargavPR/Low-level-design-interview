package VehicleRentalSystem.database;

import VehicleRentalSystem.model.Booking;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookingDatabase {

    private final List<Booking> bookings = new ArrayList<>();

    private static BookingDatabase INSTANCE = null;

    public static BookingDatabase getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BookingDatabase();
        }
        return INSTANCE;
    }


    public void insertBooking(Booking booking) {
        bookings.add(booking);
    }

    public List<Booking> getBookingsByVehicleId(String vehicleId) {
        return bookings.stream()
                .filter(booking -> booking.getVehicleId().equals(vehicleId))
                .collect(Collectors.toList());
    }
}
