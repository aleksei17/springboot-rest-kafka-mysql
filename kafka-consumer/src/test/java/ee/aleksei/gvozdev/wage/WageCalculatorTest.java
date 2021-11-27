package ee.aleksei.gvozdev.wage;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class WageCalculatorTest {

    private final TaxProperties taxProperties = mock(TaxProperties.class);
    private final WageCalculator wageCalculator = new WageCalculator(taxProperties);

    @Test
    void calculateBruttoWage() {
        when(taxProperties.getIncomeTaxPercent()).thenReturn(BigDecimal.TEN);

        BigDecimal actual = wageCalculator.calculateBruttoWage(BigDecimal.valueOf(1_000));

        BigDecimal expected = BigDecimal.valueOf(1_100);
        assertEquals(0, actual.compareTo(expected), "Expected: " + expected + "; actual: " + actual);
        assertEquals(2, actual.scale());
    }

    @Test
    void calculateBruttoWageWithFractions() {
        when(taxProperties.getIncomeTaxPercent()).thenReturn(BigDecimal.valueOf(20));

        BigDecimal actual = wageCalculator.calculateBruttoWage(new BigDecimal("1000.10"));

        BigDecimal expected = new BigDecimal("1200.12");
        assertEquals(0, actual.compareTo(expected), "Expected: " + expected + "; actual: " + actual);
        assertEquals(2, actual.scale());
    }
}