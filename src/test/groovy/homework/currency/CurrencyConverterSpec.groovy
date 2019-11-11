package homework.currency

import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

import java.math.MathContext

import static java.math.MathContext.DECIMAL64

class CurrencyConverterSpec extends Specification {
    static final MathContext MATH_OPERATIONS_CONTEXT = DECIMAL64;
    static final String BASE_CURRENCY_CODE = "EUR"
    static final int SCALE = 2

    Currency baseCurrency = new Currency(BASE_CURRENCY_CODE)

    @Subject
    CurrencyConverter currencyConverter = new CurrencyConverter(MATH_OPERATIONS_CONTEXT, new Currency(BASE_CURRENCY_CODE), SCALE);

    @Unroll
    def 'xxx'() {
        given:
            ConversionParameter parameter = new ConversionParameter(new Currency(sourceCode), new Currency(targetCode), sourceRate, targetRate, givenAmount)
        when:
            BigDecimal convertedAmount = currencyConverter.convert(parameter)
        then:
            convertedAmount == expectedAmount
        where:
            sourceCode | targetCode | sourceRate | targetRate | givenAmount | expectedAmount
            'EUR'      | 'EUR'      | 1.0        | 1.0        | 200.00      | 200.00
    }
}
