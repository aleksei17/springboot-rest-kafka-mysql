package ee.aleksei.gvozdev.wage;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class WageCalculator {

    private final TaxProperties taxProperties;

    public WageCalculator(TaxProperties taxProperties) {
        this.taxProperties = taxProperties;
    }

    /**
     * brutto wage = netto wage * (1 + tax / 100)
     */
    public BigDecimal calculateBruttoWage(BigDecimal nettoWage) {
        BigDecimal decimalTax = taxProperties.getIncomeTaxPercent().divide(BigDecimal.valueOf(100L), 2, RoundingMode.HALF_UP);
        BigDecimal nettoWithTax = BigDecimal.ONE.add(decimalTax);
        return nettoWage.multiply(nettoWithTax).setScale(2, RoundingMode.HALF_UP);
    }
}
