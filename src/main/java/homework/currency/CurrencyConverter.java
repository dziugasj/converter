package homework.currency;

import java.math.BigDecimal;
import java.math.MathContext;

public class CurrencyConverter implements Converter {
    private final MathContext mathContext;
    private final Currency baseCurrency;
    private final int scale;

    public CurrencyConverter(MathContext mathContext, Currency baseCurrency, int scale) {
        this.mathContext = mathContext;
        this.baseCurrency = baseCurrency;
        this.scale = scale;
    }

    @Override
    public BigDecimal convert(ConversionParameter parameter) {
        if (conversionIsRequired(parameter.getSourceCurrency(), parameter.getTargetCurrency())) {
            return round(convertAmount(parameter));
        } else {
            return parameter.getAmount();
        }
    }

    private BigDecimal convertAmount(ConversionParameter parameter) {
        Currency source = parameter.getSourceCurrency();
        Currency target = parameter.getTargetCurrency();
        BigDecimal sourceRate = parameter.getSourceRate();
        BigDecimal targetRate = parameter.getTargetRate();
        BigDecimal amount = parameter.getAmount();

        if (source.equals(baseCurrency)) {
            return toTargetCurrency(amount, targetRate);
        } else if (target.equals(baseCurrency)) {
            return toBaseCurrency(amount, sourceRate);
        } else {
            return toTargetCurrency(toBaseCurrency(amount, sourceRate), targetRate);
        }
    }

    private boolean conversionIsRequired(Currency source, Currency target) {
        return !source.equals(target);
    }

    private BigDecimal toBaseCurrency(BigDecimal amount, BigDecimal rate) {
        return amount.divide(rate, mathContext);
    }

    private BigDecimal toTargetCurrency(BigDecimal amount, BigDecimal rate) {
        return amount.multiply(rate);
    }

    private BigDecimal round(BigDecimal amount) {
        return amount.setScale(scale, mathContext.getRoundingMode());
    }

}
