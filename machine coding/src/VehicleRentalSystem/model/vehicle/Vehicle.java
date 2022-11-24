package VehicleRentalSystem.model.vehicle;

import VehicleRentalSystem.model.Branch;

public class Vehicle {

    private String vehicleId;
    private VehicleType vehicleType;

    private Branch branch;

    public Vehicle(String vehicleId, VehicleType vehicleType, Branch branch) {
        this.vehicleId = vehicleId;
        this.vehicleType = vehicleType;
        this.branch = branch;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleId='" + vehicleId + '\'' +
                ", vehicleType=" + vehicleType +
                ", branch=" + branch +
                '}';
    }
}
