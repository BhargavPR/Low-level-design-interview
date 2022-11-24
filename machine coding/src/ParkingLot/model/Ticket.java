package ParkingLot.model;

import ParkingLot.model.vehicle.Vehicle;

public class Ticket {

    private String id;
    private Vehicle vehicle;

    public Ticket(String id, Vehicle vehicle) {
        this.id = id;
        this.vehicle = vehicle;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
