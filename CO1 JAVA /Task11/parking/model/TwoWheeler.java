package parking.model;

public class TwoWheeler extends Vehicle {
    public TwoWheeler(String regNo) {
        super(regNo, "TwoWheeler");
    }

    @Override
    protected double ratePerHour() {
        return 10.0;
    }
}
