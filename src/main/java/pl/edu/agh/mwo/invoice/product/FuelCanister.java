package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

public class FuelCanister extends ProductWithExcise {
    private static final int motherInLawDayOfMonth = 5;
    private static final Month motherInLawMonth = Month.MARCH;

    public FuelCanister(String name, BigDecimal price) {
        super(name, price,
                (LocalDate.now().getMonth() == motherInLawMonth && LocalDate.now().getDayOfMonth() == motherInLawDayOfMonth)
                ? BigDecimal.ZERO : new BigDecimal("0.23"), new BigDecimal("5.56"));
    }
}
