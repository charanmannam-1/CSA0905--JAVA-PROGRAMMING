package parking.model;

public class ElectricCar extends Car implements Chargeable {
    public ElectricCar(String regNo) {
        super(regNo);
    }

    @Override
    public String getType() {
        return "ElectricCar";
    }

    @Override
    public String toString() {
        return "ElectricCar[" + getRegNo() + "]";
    }

    @Override
    public double chargingFee(int kWh) {
        if (kWh < 0) {
            throw new IllegalArgumentException("kWh cannot be negative");
        }
        return kWh * getChargingRatePerKWh();
    }
}
