package parking.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Multi-level parking facility supporting different number of slots per floor.
 * Uses a jagged array for flexible floor sizes.
 */
public class ParkingFacility {
    private final String[][] slots;          // vehicle regNo or null
    private final Map<String, ParkingTicket> activeTickets; // regNo -> ticket
    private int totalOccupied;

    public ParkingFacility(int[] slotsPerFloor) {
        if (slotsPerFloor == null || slotsPerFloor.length == 0) {
            throw new IllegalArgumentException("Must have at least one floor");
        }
        slots = new String[slotsPerFloor.length][];
        for (int f = 0; f < slotsPerFloor.length; f++) {
            if (slotsPerFloor[f] <= 0) {
                throw new IllegalArgumentException("Slots per floor must be positive");
            }
            slots[f] = new String[slotsPerFloor[f]];
        }
        activeTickets = new HashMap<>();
        totalOccupied = 0;
    }

    /** Returns {floor, slot} of the first free slot, or null if full. */
    public int[] allocate(String vehicleNo) {
        for (int f = 0; f < slots.length; f++) {
            for (int s = 0; s < slots[f].length; s++) {
                if (slots[f][s] == null) {
                    slots[f][s] = vehicleNo;
                    totalOccupied++;
                    return new int[]{f, s};
                }
            }
        }
        return null;
    }

    public boolean release(int floor, int slot) {
        if (floor < 0 || floor >= slots.length) return false;
        if (slot < 0 || slot >= slots[floor].length) return false;
        if (slots[floor][slot] == null) return false;
        slots[floor][slot] = null;
        totalOccupied--;
        return true;
    }

    public boolean isOccupied(int floor, int slot) {
        if (floor < 0 || floor >= slots.length) return false;
        if (slot < 0 || slot >= slots[floor].length) return false;
        return slots[floor][slot] != null;
    }

    public String getVehicleAt(int floor, int slot) {
        if (floor < 0 || floor >= slots.length) return null;
        if (slot < 0 || slot >= slots[floor].length) return null;
        return slots[floor][slot];
    }

    public int getFloorCount() {
        return slots.length;
    }

    public int getSlotsOnFloor(int floor) {
        if (floor < 0 || floor >= slots.length) return 0;
        return slots[floor].length;
    }

    public int getTotalOccupied() {
        return totalOccupied;
    }

    public int getTotalCapacity() {
        int cap = 0;
        for (String[] floor : slots) {
            cap += floor.length;
        }
        return cap;
    }

    public Map<String, ParkingTicket> getActiveTickets() {
        return activeTickets;
    }

    public void addTicket(ParkingTicket ticket) {
        activeTickets.put(ticket.getVehicle().getRegNo(), ticket);
    }

    public ParkingTicket removeTicket(String regNo) {
        return activeTickets.remove(regNo);
    }

    public ParkingTicket findTicket(String regNo) {
        return activeTickets.get(regNo.toUpperCase());
    }

    public void printStatus() {
        System.out.println("=== Parking Facility Status ===");
        System.out.println("Occupied: " + totalOccupied + " / " + getTotalCapacity());
        for (int f = 0; f < slots.length; f++) {
            System.out.print("Floor " + f + ": ");
            for (int s = 0; s < slots[f].length; s++) {
                System.out.print(slots[f][s] == null ? "[ ] " : "[" + slots[f][s] + "] ");
            }
            System.out.println();
        }
    }
}
