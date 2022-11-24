package ParkingLot.service;

import ParkingLot.action.DisplayAction;
import ParkingLot.database.ParkingLotDatabase;
import ParkingLot.model.Floor;
import ParkingLot.model.ParkingLot;
import ParkingLot.model.Slot;
import ParkingLot.model.Ticket;
import ParkingLot.model.vehicle.Vehicle;
import ParkingLot.model.vehicle.VehicleType;
import ParkingLot.strategy.SearchParkingStrategy;
import ParkingLot.strategy.StrategyContext;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ParkingLotService {

    private ParkingLotDatabase parkingLotDatabase;

    public ParkingLotService(ParkingLotDatabase parkingLotDatabase) {
        this.parkingLotDatabase = parkingLotDatabase;
    }

    public void createParkingLot(String parkingLotId, int floorCount, int slotCount) {
        ParkingLot parkingLot = new ParkingLot(parkingLotId);

        List<Floor> floors = new ArrayList<>();
        for (int floorId = 1; floorId <= floorCount; floorId++) {
            List<Slot> slots = new ArrayList<>();
            for (int slotId = 1; slotId <= slotCount; slotId++) {
                slots.add(new Slot(slotId, floorId, parkingLot.getId(), getVehicleTypeFromSlotId(slotId)));
            }
            floors.add(new Floor(floorId, parkingLot.getId(), slots));
        }

        parkingLot.setFloor(floors);
        parkingLotDatabase.insertParkingLot(parkingLot);
    }

    public void reserveParking(VehicleType vehicleType, String vehicleId, String color) {
        ParkingLot parkingLot = parkingLotDatabase.getParkingLot();
        Vehicle vehicle = new Vehicle(vehicleId, color, vehicleType);

        StrategyContext strategyContext = new StrategyContext();
        SearchParkingStrategy searchParkingStrategy = strategyContext.getSearchParkingStrategy(SearchParkingStrategy.SEARCH_NEAR_BY_PARKING_STRATEGY);

        Slot slot = searchParkingStrategy.searchParkingSlot(parkingLot, vehicleType);

        if (slot == null) {
            System.out.println("Parking Lot Full");
            return;
        }

        slot.setParkedVehicle(vehicle);

        Ticket ticket = new Ticket(getTicketId(slot.getParkingLotId(), slot.getFloorId(), slot.getId()), vehicle);
        parkingLotDatabase.insertTicket(ticket);

        System.out.println("Parked vehicle. Ticket ID: " + ticket.getId());
    }

    public void clearParking(String ticketId) {
        Ticket ticket = parkingLotDatabase.getTicket(ticketId);
        if (ticket == null) {
            System.out.println("Invalid Ticket");
            return;
        }

        String[] ids = ticket.getId().split("_");
        String parkingLotId = ids[0];
        int floorId = Integer.parseInt(ids[1]);
        int slotId = Integer.parseInt(ids[2]);

        ParkingLot parkingLot = parkingLotDatabase.getParkingLot();
        Floor floor = getFloorById(floorId, parkingLot.getFloor());

        if (floor == null) {
            System.out.println("Invalid Ticket");
            return;
        }

        Slot slot = getSlotById(slotId, floor.getSlots());

        if (slot == null) {
            System.out.println("Invalid Ticket");
            return;
        }

        slot.setParkedVehicle(null);
        parkingLotDatabase.removeTicket(ticket);

        System.out.println("Unparked vehicle with Registration Number: " + ticket.getVehicle().getVehicleId());
    }

    public void display(DisplayAction displayAction, VehicleType vehicleType) {
        if (displayAction.equals(DisplayAction.free_slots)) {
            printFreeSlots(vehicleType);
        } else if (displayAction.equals(DisplayAction.free_count)) {
            printFreeCount(vehicleType);
        } else if (displayAction.equals(DisplayAction.occupied_slots)) {
            printOccupiedSlot(vehicleType);
        }
    }

    private void printFreeCount(VehicleType vehicleType) {
        ParkingLot parkingLot = parkingLotDatabase.getParkingLot();
        for (Floor floor : parkingLot.getFloor()) {
            List<Slot> slots = floor.getSlots();
            long count = slots
                    .stream()
                    .filter(slot -> slot.getParkedVehicle() == null && slot.getVehicleType().equals(vehicleType))
                    .count();
            String builder = "No. of free slots for " + vehicleType.toString() + " on Floor " + floor.getFloorId() + ": " + count;
            System.out.println(builder);
        }
    }

    private void printFreeSlots(VehicleType vehicleType) {
        ParkingLot parkingLot = parkingLotDatabase.getParkingLot();

        for (Floor floor : parkingLot.getFloor()) {
            List<Slot> slots = floor.getSlots()
                    .stream()
                    .filter(slot -> slot.getParkedVehicle() == null && slot.getVehicleType().equals(vehicleType))
                    .collect(Collectors.toList());

            StringBuilder builder = new StringBuilder();
            builder.append("Free slots for ");
            builder.append(vehicleType.toString()).append(" on Floor").append(" ").append(floor.getFloorId()).append(": ");
            slots.forEach(slot -> {
                builder.append(slot.getId()).append(", ");
            });
            System.out.println(builder);
        }
    }

    private void printOccupiedSlot(VehicleType vehicleType) {
        ParkingLot parkingLot = parkingLotDatabase.getParkingLot();

        for (Floor floor : parkingLot.getFloor()) {
            List<Slot> slots = floor.getSlots()
                    .stream()
                    .filter(slot -> slot.getParkedVehicle() != null && slot.getVehicleType().equals(vehicleType))
                    .collect(Collectors.toList());

            StringBuilder builder = new StringBuilder();
            builder.append("Occupied slots for ");
            builder.append(vehicleType.toString()).append(" on Floor").append(" ").append(floor.getFloorId()).append(": ");
            slots.forEach(slot -> {
                builder.append(slot.getId()).append(", ");
            });
            System.out.println(builder);
        }
    }

    public Floor getFloorById(int floorId, List<Floor> floors) {
        return floors.stream().filter(floor -> floor.getFloorId() == floorId).findAny().orElse(null);
    }

    public Slot getSlotById(int slotId, List<Slot> slots) {
        return slots.stream().filter(slot -> slot.getId() == slotId).findAny().orElse(null);
    }

    private String getTicketId(String parkingLotId, int floorId, int slotId) {
        return parkingLotId + "_" + floorId + "_" + slotId;
    }

    public VehicleType getVehicleTypeFromSlotId(int slotId) {
        if (slotId == 1) {
            return VehicleType.TRUCK;
        } else if (slotId == 2 || slotId == 3) {
            return VehicleType.BIKE;
        }
        return VehicleType.CAR;
    }
}
