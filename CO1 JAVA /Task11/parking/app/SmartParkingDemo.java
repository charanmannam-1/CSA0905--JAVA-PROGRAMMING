package parking.app;

import parking.model.*;
import parking.service.ParkingService;
import java.time.LocalDateTime;

/**
 * Demonstration of the complete Campus Smart-Parking System.
 */
public class SmartParkingDemo {
    public static void main(String[] args) {
        System.out.println("===== CAMPUS SMART-PARKING SYSTEM DEMO =====\n");

        // Facility: 3 floors with 4, 3, 2 slots (small for demo)
        ParkingFacility facility = new ParkingFacility(new int[]{4, 3, 2});
        ParkingService service = new ParkingService(facility);

        // Create vehicles of different types
        TwoWheeler bike1 = new TwoWheeler("TN01AB1001");
        Car car1 = new Car("TN02CD2002");
        ElectricCar ecar1 = new ElectricCar("TN03EF3003");
        TwoWheeler bike2 = new TwoWheeler("TN04GH4004");
        Car car2 = new Car("TN05IJ5005");

        // --- Normal operation ---
        System.out.println("--- Parking vehicles ---");
        LocalDateTime base = LocalDateTime.of(2026, 11, 14, 9, 0);
        service.parkVehicle(bike1, base);
        service.parkVehicle(car1, base.plusMinutes(10));
        service.parkVehicle(ecar1, base.plusMinutes(20));
        service.parkVehicle(bike2, base.plusMinutes(30));

        facility.printStatus();

        // --- Exit some vehicles ---
        System.out.println("\n--- Vehicles exiting ---");
        // Bike1 parked 90 minutes -> 2 hours * 10 = 20
        service.exitVehicle("TN01AB1001", base.plusMinutes(90), 0);

        // Car1 parked 130 minutes -> 3 hours * 30 = 90
        service.exitVehicle("TN02CD2002", base.plusMinutes(140), 0);

        // ElectricCar parked 130 minutes + 10 kWh charging
        // 3*30 = 90 + 12*10 = 120 -> total 210
        service.exitVehicle("TN03EF3003", base.plusMinutes(150), 10);

        facility.printStatus();
        System.out.println("Total collection so far: Rs " + service.getTotalCollection());

        // --- Boundary: last free slots ---
        System.out.println("\n--- Filling remaining slots ---");
        service.parkVehicle(car2, base.plusHours(2));
        service.parkVehicle(new TwoWheeler("TN06KL6006"), base.plusHours(2));
        service.parkVehicle(new Car("TN07MN7007"), base.plusHours(2));
        service.parkVehicle(new ElectricCar("TN08OP8008"), base.plusHours(2));
        service.parkVehicle(new Car("TN09QR9009"), base.plusHours(2));

        facility.printStatus();

        // Try one more - should fail
        try {
            service.parkVehicle(new TwoWheeler("TN10ST1010"));
        } catch (IllegalStateException e) {
            System.out.println("Expected error: " + e.getMessage());
        }

        // --- Invalid cases ---
        System.out.println("\n--- Invalid cases ---");
        try {
            service.exitVehicle("UNKNOWN");
        } catch (IllegalArgumentException e) {
            System.out.println("Expected error: " + e.getMessage());
        }

        try {
            service.parkVehicle(bike2); // already parked
        } catch (IllegalStateException e) {
            System.out.println("Expected error: " + e.getMessage());
        }

        // Zero-minute parking
        System.out.println("\n--- Zero-minute parking ---");
        // First free a slot
        service.exitVehicle("TN04GH4004", base.plusHours(3), 0);
        LocalDateTime now = base.plusHours(3);
        service.parkVehicle(new Car("TN11UV1111"), now);
        double feeZero = service.exitVehicle("TN11UV1111", now, 0); // 0 minutes -> 0 hours? 
        // Actually ceiling of 0/60 = 0, fee = 0
        System.out.println("Zero-minute fee: Rs " + feeZero);

        System.out.println("\n===== FINAL TOTAL COLLECTION: Rs " + service.getTotalCollection() + " =====");
    }
}
