package homework.currency;

import java.math.BigDecimal;

public class ConversionParameter {
    private final Currency sourceCurrency;
    private final Currency targetCurrency;
    private final BigDecimal sourceRate;
    private final BigDecimal targetRate;
    private final BigDecimal amount;

    public ConversionParameter(Currency sourceCurrency, Currency targetCurrency, BigDecimal sourceRate, BigDecimal targetRate, BigDecimal amount) {
        this.sourceCurrency = sourceCurrency;
        this.targetCurrency = targetCurrency;
        this.sourceRate = sourceRate;
        this.targetRate = targetRate;
        this.amount = amount;
    }

    public Currency getSourceCurrency() {
        return sourceCurrency;
    }

    public Currency getTargetCurrency() {
        return targetCurrency;
    }

    public BigDecimal getSourceRate() {
        return sourceRate;
    }

    public BigDecimal getTargetRate() {
        return targetRate;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
