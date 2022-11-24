package ParkingLot.model.vehicle;

public class Vehicle {

    private String vehicleId;
    private String color;
    private VehicleType vehicleType;

    public Vehicle(String vehicleId, String color, VehicleType vehicleType) {
        this.vehicleId = vehicleId;
        this.color = color;
        this.vehicleType = vehicleType;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }
}
