package parking.model;

import java.time.LocalDate;

public class OneDayPass implements Pass {
    private final LocalDate validDate;
    private final String holderName;

    public OneDayPass(String holderName, LocalDate validDate) {
        this.holderName = holderName;
        this.validDate = validDate;
    }

    @Override
    public boolean isValid() {
        return LocalDate.now().equals(validDate);
    }

    @Override
    public String getPassType() {
        return "OneDayPass";
    }

    public String getHolderName() {
        return holderName;
    }
}
