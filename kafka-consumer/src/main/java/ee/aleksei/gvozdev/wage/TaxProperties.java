package ee.aleksei.gvozdev.wage;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Validated
@ConfigurationProperties(prefix = "wage.tax")
public class TaxProperties {

    @NotNull
    @DecimalMin(value = "0.0")
    @DecimalMax(value = "100")
    @Digits(integer = 2, fraction = 2)
    private BigDecimal incomeTaxPercent;

    public BigDecimal getIncomeTaxPercent() {
        return incomeTaxPercent;
    }

    public TaxProperties setIncomeTaxPercent(BigDecimal incomeTaxPercent) {
        this.incomeTaxPercent = incomeTaxPercent;
        return this;
    }
}
