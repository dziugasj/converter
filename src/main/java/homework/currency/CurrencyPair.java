package homework.currency;

public class CurrencyPair {
    private final Currency sourceCurrency;
    private final Currency targetCurrency;

    public CurrencyPair(Currency sourceCurrency, Currency targetCurrency) {
        this.sourceCurrency = sourceCurrency;
        this.targetCurrency = targetCurrency;
    }

    public Currency getSourceCurrency() {
        return sourceCurrency;
    }

    public Currency getTargetCurrency() {
        return targetCurrency;
    }
}
