package homework.currency;

import java.math.MathContext;

import static java.math.MathContext.DECIMAL64;

public class ConverterFactory {
    private static final MathContext MATH_OPERATIONS_CONTEXT = DECIMAL64;
    private static final int CURRENCY_SCALE = 4;
    private static final String BASE_CURRENCY = "DKK";

    public Converter getConverter() {
        return new CurrencyConverter(
                MATH_OPERATIONS_CONTEXT,
                new Currency(BASE_CURRENCY),
                CURRENCY_SCALE);
    }
}
