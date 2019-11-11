package homework.currency;

import homework.rate.RateProvider;

import java.math.BigDecimal;
import java.math.MathContext;

import static java.math.BigDecimal.ONE;

public class CurrencyConverter implements Converter {
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

    @Override
    public BigDecimal convert(Currency source, Currency target, BigDecimal amount) {
        if (conversionIsRequired(source, target)) {
            return round(convertAmount(source, target, amount));
        } else {
            return amount;
        }
    }

    private BigDecimal convertAmount(Currency source, Currency target, BigDecimal amount) {
        if (source.equals(baseCurrency)) {
            return toTargetCurrency(amount, target);
        } else if (target.equals(baseCurrency)) {
            return toBaseCurrency(amount, source);
        } else {
            return toTargetCurrency(toBaseCurrency(amount, source), target);
        }
    }

    private boolean conversionIsRequired(Currency source, Currency target) {
        return !source.equals(target);
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
