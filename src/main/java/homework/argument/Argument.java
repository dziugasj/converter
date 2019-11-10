package homework.argument;

import java.math.BigDecimal;

public class Argument {

    private final String sourceCurrencyCode;
    private final String targetCurrencyCode;
    private final BigDecimal sourceAmount;

    public Argument(String sourceCurrencyCode, String targetCurrencyCode, BigDecimal sourceAmount) {
        this.sourceCurrencyCode = sourceCurrencyCode;
        this.targetCurrencyCode = targetCurrencyCode;
        this.sourceAmount = sourceAmount;
    }

    public String getSourceCurrencyCode() {
        return sourceCurrencyCode;
    }

    public String getTargetCurrencyCode() {
        return targetCurrencyCode;
    }

    public BigDecimal getSourceAmount() {
        return sourceAmount;
    }
}
