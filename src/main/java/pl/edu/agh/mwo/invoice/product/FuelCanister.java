package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

public class FuelCanister extends ProductWithExcise {
    private static final int motherInLawDayOfMonth = 5;
    private static final Month motherInLawMonth = Month.MARCH;

    private static final boolean isMotherInLawDayToday =
            LocalDate.now().getMonth() == motherInLawMonth
                    && LocalDate.now().getDayOfMonth() == motherInLawDayOfMonth;

    public FuelCanister(String name, BigDecimal price) {
        super(name, price, new BigDecimal("0.23"), new BigDecimal("5.56"));
    }

    @Override
    public BigDecimal getPriceWithTax() {
        if (isMotherInLawDayToday) {
            return this.getPrice()
                    .add(this.getPrice())
                    .add(this.getExcise());
        } else {
            return this.getPrice().multiply(this.getTaxPercent())
                    .add(this.getPrice())
                    .add(this.getExcise());
        }
    }
}
