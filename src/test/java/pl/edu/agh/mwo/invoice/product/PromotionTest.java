package pl.edu.agh.mwo.invoice.product;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PromotionTest {

    @Test
    public void testFuelProductPromotionForMotherInLawDay() {
        LocalDate todayDate = LocalDate.of(2023, 3, 5);
        try (MockedStatic<LocalDate> localDateMockedStatic = Mockito.mockStatic(LocalDate.class)) {
            localDateMockedStatic.when(() -> LocalDate.now()).thenReturn(todayDate);
            FuelCanister product = new FuelCanister("Benzyna 98", new BigDecimal("7.3"));
            Assert.assertThat(new BigDecimal("12.86"), Matchers.comparesEqualTo(product.getPriceWithTax()));
            localDateMockedStatic.clearInvocations();
        }
    }
}
