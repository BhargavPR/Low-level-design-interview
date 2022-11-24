package BattleShip.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Ship {

    private String id;
    private String name;
    private List<Location> locations;

    private List<Location> damagedLocation;

    public Ship(String id, String name, List<Location> locations) {
        this.id = id;
        this.name = name;
        this.locations = locations;
        this.damagedLocation = new ArrayList<>();
    }

    public Ship(String name, List<Location> locations) {
        this(UUID.randomUUID().toString(), name, locations);
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

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public List<Location> getDamagedLocation() {
        return damagedLocation;
    }

    public void setDamagedLocation(List<Location> damagedLocation) {
        this.damagedLocation = damagedLocation;
    }

    @Override
    public String toString() {
        return "Ship{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", locations=" + locations +
                ", damagedLocation=" + damagedLocation +
                '}';
    }
}
