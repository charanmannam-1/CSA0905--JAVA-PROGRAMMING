package parking.model;

import java.time.LocalDateTime;
import java.time.Duration;

/**
 * Represents a parking ticket issued on entry.
 */
public class ParkingTicket {
    private static int nextTicketNo = 1001;

    private final int ticketNo;
    private final Vehicle vehicle;
    private final int floor;
    private final int slot;
    private final LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private double fee;
    private boolean active;

    public ParkingTicket(Vehicle vehicle, int floor, int slot) {
        this.ticketNo = nextTicketNo++;
        this.vehicle = vehicle;
        this.floor = floor;
        this.slot = slot;
        this.entryTime = LocalDateTime.now();
        this.active = true;
        this.fee = 0.0;
    }

    // For testing with fixed entry time
    public ParkingTicket(Vehicle vehicle, int floor, int slot, LocalDateTime entryTime) {
        this.ticketNo = nextTicketNo++;
        this.vehicle = vehicle;
        this.floor = floor;
        this.slot = slot;
        this.entryTime = entryTime;
        this.active = true;
        this.fee = 0.0;
    }

    public int getTicketNo() {
        return ticketNo;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public int getFloor() {
        return floor;
    }

    public int getSlot() {
        return slot;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public double getFee() {
        return fee;
    }

    public boolean isActive() {
        return active;
    }

    public void close(LocalDateTime exitTime, double fee) {
        if (!active) {
            throw new IllegalStateException("Ticket already closed");
        }
        this.exitTime = exitTime;
        this.fee = fee;
        this.active = false;
    }

    public long getDurationMinutes() {
        LocalDateTime end = (exitTime != null) ? exitTime : LocalDateTime.now();
        return Duration.between(entryTime, end).toMinutes();
    }

    public static int getNextTicketNo() {
        return nextTicketNo;
    }

    public static void resetTicketCounter(int start) {
        nextTicketNo = start;
    }

    @Override
    public String toString() {
        return "Ticket#" + ticketNo + " " + vehicle + " Floor:" + floor + " Slot:" + slot
                + " Entry:" + entryTime + (active ? " [ACTIVE]" : " Fee:Rs" + fee);
    }
}
