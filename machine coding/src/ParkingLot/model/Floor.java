package ParkingLot.model;

import java.util.List;

public class Floor {

    private int floorId;
    private String parkingLotId;
    private List<Slot> slots;

    public Floor(int floorId, String parkingLotId, List<Slot> slots) {
        this.floorId = floorId;
        this.parkingLotId = parkingLotId;
        this.slots = slots;
    }

    public Floor(int floorId, String parkingLotId) {
        this.floorId = floorId;
        this.parkingLotId = parkingLotId;
    }

    public int getFloorId() {
        return floorId;
    }

    public void setFloorId(int floorId) {
        this.floorId = floorId;
    }

    public String getParkingLotId() {
        return parkingLotId;
    }

    public void setParkingLotId(String parkingLotId) {
        this.parkingLotId = parkingLotId;
    }

    public List<Slot> getSlots() {
        return slots;
    }

    public void setSlots(List<Slot> slots) {
        this.slots = slots;
    }
}
