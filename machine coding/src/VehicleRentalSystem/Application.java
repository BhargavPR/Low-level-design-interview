package VehicleRentalSystem;

import VehicleRentalSystem.action.Action;
import VehicleRentalSystem.database.BookingDatabase;
import VehicleRentalSystem.database.BranchDatabase;
import VehicleRentalSystem.database.VehicleDatabase;
import VehicleRentalSystem.model.vehicle.VehicleType;
import VehicleRentalSystem.service.BranchService;
import VehicleRentalSystem.service.VehicleService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("/Users/20033132/machine coding/machine coding/src/VehicleRentalSystem/input.txt"));

        VehicleDatabase vehicleDatabase = VehicleDatabase.getInstance();
        BranchDatabase branchDatabase = BranchDatabase.getInstance();
        BookingDatabase bookingDatabase = BookingDatabase.getInstance();

        VehicleService vehicleService = new VehicleService(branchDatabase, vehicleDatabase, bookingDatabase);
        BranchService branchService = new BranchService(branchDatabase);

        while (scanner.hasNextLine()) {
            String[] input = scanner.nextLine().split(" ");
            Action action = Action.valueOf(input[0]);
            switch (action) {
                case addBranch:
                    branchService.insertBranch(input[1]);
                    break;
                case addVehicle:
                    vehicleService.addVehicle(input[1], VehicleType.valueOf(input[2]), input[3]);
                    break;
                case allocatePrice:
                    vehicleService.allocatePrice(input[2], VehicleType.valueOf(input[1]), Integer.parseInt(input[3]));
                    break;
                case bookVehicle:
                    vehicleService.requestVehicle(VehicleType.valueOf(input[1]), Integer.parseInt(input[2]), Integer.parseInt(input[3]));
                    break;
                default:
                    break;
            }
        }

    }
}
