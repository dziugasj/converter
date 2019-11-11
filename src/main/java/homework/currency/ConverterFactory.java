package homework.currency;

import homework.rate.RateProvider;

import java.math.MathContext;

import static java.math.MathContext.DECIMAL64;

public class ConverterFactory {
    private static final MathContext MATH_OPERATIONS_CONTEXT = DECIMAL64;
    private static final int CURRENCY_SCALE = 2;
    private static final String BASE_CURRENCY = "DKK";

    public Converter getConverter(RateProvider rateProvider) {
        return new CurrencyConverter(
                MATH_OPERATIONS_CONTEXT,
                new Currency(BASE_CURRENCY),
                CURRENCY_SCALE,
                rateProvider);
    }
}
