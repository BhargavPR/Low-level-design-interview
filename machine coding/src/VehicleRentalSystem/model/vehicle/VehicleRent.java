package VehicleRentalSystem.model.vehicle;

import java.math.BigDecimal;

public class VehicleRent {

    private String branchName;
    private VehicleType vehicleType;
    private int amount;

    public VehicleRent(String branchName, VehicleType vehicleType, int amount) {
        this.branchName = branchName;
        this.vehicleType = vehicleType;
        this.amount = amount;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
