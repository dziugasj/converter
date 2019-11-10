package homework.currency;

import homework.rate.RateProvider;

import java.math.BigDecimal;
import java.math.MathContext;

import static java.math.BigDecimal.ONE;

public class CurrencyConverter {
    private final MathContext mathContext;
    private final Currency baseCurrency;
    private final int scale;
    private final RateProvider rateProvider;

    public CurrencyConverter(MathContext mathContext, Currency baseCurrency, int scale, RateProvider rateProvider) {
        this.mathContext = mathContext;
        this.baseCurrency = baseCurrency;
        this.scale = scale;
        this.rateProvider = rateProvider;
    }

    public BigDecimal convertCurrency(CurrencyPair pair, BigDecimal amount) {
        if (conversionIsRequired(pair)) {
            return round(convert(pair, amount));
        } else {
            return amount;
        }
    }

    private BigDecimal convert(CurrencyPair pair, BigDecimal amount) {
        Currency source = pair.getSourceCurrency();
        Currency target = pair.getTargetCurrency();

        if (source.equals(baseCurrency)) {
            return toTargetCurrency(amount, target);
        } else if (target.equals(baseCurrency)) {
            return toBaseCurrency(amount, source);
        } else {
            return toTargetCurrency(toBaseCurrency(amount, source), target);
        }
    }

    private boolean conversionIsRequired(CurrencyPair pair) {
        return !pair.getSourceCurrency().equals(pair.getTargetCurrency());
    }

    private BigDecimal toBaseCurrency(BigDecimal amount, Currency currency) {
        return amount.divide(getBuyRate(currency), mathContext);
    }

    private BigDecimal toTargetCurrency(BigDecimal amount, Currency currency) {
        return amount.multiply(getSellRate(currency));
    }

    private BigDecimal round(BigDecimal amount) {
        return amount.setScale(scale, mathContext.getRoundingMode());
    }

    private BigDecimal getBuyRate(Currency currency) {
        // TODO
        return rateProvider.getBuyRate(currency).orElse(ONE);
    }

    private BigDecimal getSellRate(Currency currency) {
        // TODO Maybe those can be improved ?
        return rateProvider.getSellRate(currency).orElse(ONE);
    }
}
