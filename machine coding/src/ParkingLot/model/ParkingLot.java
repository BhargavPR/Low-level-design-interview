package ParkingLot.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ParkingLot {

    private String id;
    private List<Floor> floor;

    public ParkingLot(String id, List<Floor> floor) {
        this.id = id;
        this.floor = floor;
    }

    public ParkingLot(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Floor> getFloor() {
        return floor;
    }

    public void setFloor(List<Floor> floor) {
        this.floor = floor;
    }
}
