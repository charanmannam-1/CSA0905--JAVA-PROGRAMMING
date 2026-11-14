package parking.model;

/**
 * E-Bike: charged a flat fee regardless of duration.
 */
public class EBike extends Vehicle {
    private static final double FLAT_FEE = 20.0;

    public EBike(String regNo) {
        super(regNo, "EBike");
    }

    @Override
    protected double ratePerHour() {
        // Not used; we override calculateFee
        return 0;
    }

    @Override
    public double calculateFee(int minutes) {
        // Flat fee for e-bikes
        return FLAT_FEE;
    }
}
