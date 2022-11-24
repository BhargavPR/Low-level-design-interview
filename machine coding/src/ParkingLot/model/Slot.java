package ParkingLot.model;

import ParkingLot.model.vehicle.Vehicle;
import ParkingLot.model.vehicle.VehicleType;

public class Slot {

    private int id;
    private int floorId;
    private String parkingLotId;
    private VehicleType vehicleType;
    private Vehicle parkedVehicle;


    public Slot(int id, int floorId, String parkingLotId, VehicleType vehicleType) {
        this.id = id;
        this.floorId = floorId;
        this.parkingLotId = parkingLotId;
        this.vehicleType = vehicleType;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Vehicle getParkedVehicle() {
        return parkedVehicle;
    }

    public void setParkedVehicle(Vehicle parkedVehicle) {
        this.parkedVehicle = parkedVehicle;
    }
}
