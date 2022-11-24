package VehicleRentalSystem.model;

public class Booking {

    private String vehicleId;
    private int startTime;
    private int endTime;
    private int amount;

    public Booking(String vehicleId, int startTime, int endTime, int amount) {
        this.vehicleId = vehicleId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.amount = amount;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "vehicleId='" + vehicleId + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", amount=" + amount +
                '}';
    }
}
