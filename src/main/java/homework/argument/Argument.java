package homework.argument;

import java.math.BigDecimal;

public class Argument {

    private final String sourceCurrencyCode;
    private final String targetCurrencyCode;
    private final BigDecimal sourceAmount;
    private final boolean valid;

    public Argument(String sourceCurrencyCode, String targetCurrencyCode, BigDecimal sourceAmount, boolean valid) {
        this.sourceCurrencyCode = sourceCurrencyCode;
        this.targetCurrencyCode = targetCurrencyCode;
        this.sourceAmount = sourceAmount;
        this.valid = valid;
    }


}
