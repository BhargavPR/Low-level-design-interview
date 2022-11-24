package BattleShip.model;

import java.util.List;

public class Board {

    private final int size;
    private List<Ship> ships;

    public Board(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public List<Ship> getShips() {
        return ships;
    }

    public void setShips(List<Ship> ships) {
        this.ships = ships;
    }

    @Override
    public String toString() {
        return "Board{" +
                "size=" + size +
                ", ships=" + ships +
                '}';
    }
}
