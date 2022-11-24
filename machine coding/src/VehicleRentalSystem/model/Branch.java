package VehicleRentalSystem.model;

import VehicleRentalSystem.model.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Branch {

    private String id;
    private String name;

    private List<Vehicle> vehicles;

    public Branch(String id, String name) {
        this.id = id;
        this.name = name;
        this.vehicles = new ArrayList<>();
    }

    public Branch(String name) {
        this(UUID.randomUUID().toString(), name);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    @Override
    public String toString() {
        return "Branch{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
