package BattleShip.utils;

import BattleShip.constants.Constants;
import BattleShip.model.Location;
import BattleShip.model.Ship;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class GameUtils {

    public static Location generateShipLocation(int boardSize) {
        int row = (new Random().nextInt(boardSize + 1)) % (boardSize + 1);
        int column = (new Random().nextInt(boardSize + 1)) % (boardSize + 1);
        return new Location(row, column);
    }

    public static boolean isValidLocation(Location location, int boardSize) {
        int row = location.getRow();
        int column = location.getColumn();
        return row >= 1 && row <= boardSize && column >= 1 && column <= boardSize;
    }

    public static boolean isEqualLocation(Location sourceLocation, Location destinationLocation) {
        return sourceLocation.getRow() == destinationLocation.getRow() &&
                sourceLocation.getColumn() == destinationLocation.getColumn();
    }

    public static List<Ship> generateShips(int boardSize, int shipCount) {
        List<Ship> ships = new ArrayList<>();
        for (int i = 0; i < shipCount; i++) {
            List<Location> locations = generateLocations(5 - i, boardSize);
            ships.add(new Ship(UUID.randomUUID().toString(), locations));
        }
        return ships;
    }

    private static List<Location> generateLocations(int offset, int boardSize) {
        List<Location> locations = new ArrayList<>();
        int direction = new Random().nextInt() % 2;

        while (true) {
            Location location = generateShipLocation(boardSize);
            int row = location.getRow();
            int column = location.getColumn();

            if (direction == Constants.SHIP_DIRECTION_ROW && column + offset < boardSize) {
                for (int i = 0; i < offset; i++) {
                    locations.add(new Location(row, column + i));
                }
                return locations;
            } else if (direction == Constants.SHIP_DIRECTION_COLUMN && row + offset < boardSize) {
                for (int i = 0; i < offset; i++) {
                    locations.add(new Location(row + i, column));
                }
                return locations;
            }
        }
    }
}
