package ParkingLot;

import ParkingLot.action.Action;
import ParkingLot.action.DisplayAction;
import ParkingLot.database.ParkingLotDatabase;
import ParkingLot.model.vehicle.VehicleType;
import ParkingLot.service.ParkingLotService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ParkingLotApplication {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("/Users/20033132/machine coding/machine coding/src/ParkingLot/input.txt"));

        ParkingLotDatabase parkingLotDatabase = ParkingLotDatabase.getInstance();
        ParkingLotService parkingLotService = new ParkingLotService(parkingLotDatabase);

        while (scanner.hasNextLine()) {
            String[] input = scanner.nextLine().split(" ");
            Action action = Action.valueOf(input[0]);
            switch (action) {
                case create_parking_lot:
                    parkingLotService.createParkingLot(input[1], Integer.parseInt(input[2]), Integer.parseInt(input[3]));
                    break;
                case park_vehicle:
                    parkingLotService.reserveParking(VehicleType.valueOf(input[1]), input[2], input[3]);
                    break;
                case unpark_vehicle:
                    parkingLotService.clearParking(input[1]);
                    break;
                case display:
                    parkingLotService.display(DisplayAction.valueOf(input[1]), VehicleType.valueOf(input[2]));
                    break;
            }
        }
    }
}
