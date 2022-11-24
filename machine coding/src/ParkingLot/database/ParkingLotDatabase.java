package ParkingLot.database;

import ParkingLot.model.ParkingLot;
import ParkingLot.model.Ticket;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotDatabase {

    private List<ParkingLot> parkingLots = new ArrayList<>();
    private List<Ticket> tickets = new ArrayList<>();

    private static ParkingLotDatabase INSTANCE = null;

    public static ParkingLotDatabase getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ParkingLotDatabase();
        }
        return INSTANCE;
    }

    public void insertParkingLot(ParkingLot parkingLot) {
        parkingLots.add(parkingLot);
    }

    public ParkingLot getParkingLot() {
        return parkingLots.get(0);
    }

    public void insertTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    public void removeTicket(Ticket ticket) {
        tickets.remove(ticket);
    }

    public Ticket getTicket(String id) {
        return tickets.stream()
                .filter(ticket -> ticket.getId().equals(id))
                .findAny()
                .orElse(null);
    }

}
