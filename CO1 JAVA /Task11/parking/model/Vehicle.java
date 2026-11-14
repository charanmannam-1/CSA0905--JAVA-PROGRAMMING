package parking.model;

/**
 * Abstract base class for all vehicles.
 */
public abstract class Vehicle {
    private final String regNo;
    private final String type;

    protected Vehicle(String regNo, String type) {
        if (regNo == null || regNo.trim().isEmpty()) {
            throw new IllegalArgumentException("Registration number cannot be empty");
        }
        this.regNo = regNo.trim().toUpperCase();
        this.type = type;
    }

    public String getRegNo() {
        return regNo;
    }

    public String getType() {
        return type;
    }

    /** Rate per hour in Rs. Subclasses must provide their own rate. */
    protected abstract double ratePerHour();

    /**
     * Every started hour is charged in full (ceiling of minutes/60).
     */
    public double calculateFee(int minutes) {
        if (minutes < 0) {
            throw new IllegalArgumentException("Minutes cannot be negative");
        }
        int hours = (minutes + 59) / 60; // ceiling division
        return hours * ratePerHour();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vehicle)) return false;
        Vehicle other = (Vehicle) o;
        return regNo.equals(other.regNo);
    }

    @Override
    public int hashCode() {
        return regNo.hashCode();
    }

    @Override
    public String toString() {
        return type + "[" + regNo + "]";
    }
}
