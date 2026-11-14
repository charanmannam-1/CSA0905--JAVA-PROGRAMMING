package parking.service;

import parking.model.*;
import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * Service layer that handles entry, exit and fee calculation.
 */
public class ParkingService {
    private final ParkingFacility facility;
    private double totalCollection;

    public ParkingService(ParkingFacility facility) {
        this.facility = facility;
        this.totalCollection = 0.0;
    }

    public ParkingTicket parkVehicle(Vehicle vehicle) {
        if (facility.findTicket(vehicle.getRegNo()) != null) {
            throw new IllegalStateException("Vehicle already parked: " + vehicle.getRegNo());
        }
        int[] pos = facility.allocate(vehicle.getRegNo());
        if (pos == null) {
            throw new IllegalStateException("Parking facility is full");
        }
        ParkingTicket ticket = new ParkingTicket(vehicle, pos[0], pos[1]);
        facility.addTicket(ticket);
        System.out.println("Parked " + vehicle + " at Floor " + pos[0] + " Slot " + pos[1]
                + " | Ticket #" + ticket.getTicketNo());
        return ticket;
    }

    public ParkingTicket parkVehicle(Vehicle vehicle, LocalDateTime entryTime) {
        if (facility.findTicket(vehicle.getRegNo()) != null) {
            throw new IllegalStateException("Vehicle already parked: " + vehicle.getRegNo());
        }
        int[] pos = facility.allocate(vehicle.getRegNo());
        if (pos == null) {
            throw new IllegalStateException("Parking facility is full");
        }
        ParkingTicket ticket = new ParkingTicket(vehicle, pos[0], pos[1], entryTime);
        facility.addTicket(ticket);
        System.out.println("Parked " + vehicle + " at Floor " + pos[0] + " Slot " + pos[1]
                + " | Ticket #" + ticket.getTicketNo());
        return ticket;
    }

    public double exitVehicle(String regNo) {
        return exitVehicle(regNo, LocalDateTime.now(), 0);
    }

    public double exitVehicle(String regNo, LocalDateTime exitTime, int chargingKWh) {
        ParkingTicket ticket = facility.findTicket(regNo);
        if (ticket == null) {
            throw new IllegalArgumentException("No active ticket for: " + regNo);
        }
        long minutes = java.time.Duration.between(ticket.getEntryTime(), exitTime).toMinutes();
        if (minutes < 0) minutes = 0;

        double fee = ticket.getVehicle().calculateFee((int) minutes);

        // Add charging fee if applicable
        if (ticket.getVehicle() instanceof Chargeable && chargingKWh > 0) {
            fee += ((Chargeable) ticket.getVehicle()).chargingFee(chargingKWh);
        }

        // Staff/Student monthly pass would be checked here (simplified: none for demo)
        facility.release(ticket.getFloor(), ticket.getSlot());
        ticket.close(exitTime, fee);
        facility.removeTicket(regNo);
        totalCollection += fee;

        System.out.println("Exit " + ticket.getVehicle() + " | Duration: " + minutes
                + " min | Fee: Rs " + fee);
        return fee;
    }

    public double getTotalCollection() {
        return totalCollection;
    }

    public ParkingFacility getFacility() {
        return facility;
    }
}
