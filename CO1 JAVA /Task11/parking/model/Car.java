package parking.model;

public class Car extends Vehicle {
    public Car(String regNo) {
        super(regNo, "Car");
    }

    @Override
    protected double ratePerHour() {
        return 30.0;
    }
}
