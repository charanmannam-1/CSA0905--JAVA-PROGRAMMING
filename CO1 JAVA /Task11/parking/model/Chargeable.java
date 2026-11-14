package parking.model;

/**
 * Interface for vehicles that can be charged (EVs).
 */
public interface Chargeable {
    double chargingFee(int kWh);

    /** Default rate if not overridden */
    default double getChargingRatePerKWh() {
        return 12.0;
    }
}
