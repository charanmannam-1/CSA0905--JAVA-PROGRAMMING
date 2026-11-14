package parking.model;

import java.time.LocalTime;

/**
 * Delivery Van: charged at double the car rate during 9 AM - 5 PM.
 */
public class DeliveryVan extends Vehicle {
    private final LocalTime entryTimeOfDay;

    public DeliveryVan(String regNo) {
        super(regNo, "DeliveryVan");
        this.entryTimeOfDay = LocalTime.now();
    }

    public DeliveryVan(String regNo, LocalTime entryTimeOfDay) {
        super(regNo, "DeliveryVan");
        this.entryTimeOfDay = entryTimeOfDay;
    }

    @Override
    protected double ratePerHour() {
        // Base car rate is 30; double during business hours
        LocalTime start = LocalTime.of(9, 0);
        LocalTime end = LocalTime.of(17, 0);
        if (!entryTimeOfDay.isBefore(start) && entryTimeOfDay.isBefore(end)) {
            return 60.0; // double car rate
        }
        return 30.0;
    }
}
