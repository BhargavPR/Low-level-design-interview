package VehicleRentalSystem.strategy;

import VehicleRentalSystem.database.BookingDatabase;
import VehicleRentalSystem.database.BranchDatabase;
import VehicleRentalSystem.database.VehicleDatabase;

public class BookingStrategyContext {

    private final VehicleDatabase vehicleDatabase;
    private final BranchDatabase branchDatabase;
    private final BookingDatabase bookingDatabase;

    public BookingStrategyContext(VehicleDatabase vehicleDatabase,
                                  BranchDatabase branchDatabase,
                                  BookingDatabase bookingDatabase) {
        this.vehicleDatabase = vehicleDatabase;
        this.branchDatabase = branchDatabase;
        this.bookingDatabase = bookingDatabase;
    }

    public BookingStrategy bookingStrategy(String strategy) {
        if (strategy.equals(BookingStrategy.LOWEST_PRICE_BOOKING_STRATEGY)) {
            return new LowestPriceBookingStrategy(vehicleDatabase, branchDatabase, bookingDatabase);
        }
        return null;
    }
}
