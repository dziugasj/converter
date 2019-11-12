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

    @Subject
    CurrencyConverter currencyConverter = new CurrencyConverter(MATH_OPERATIONS_CONTEXT, new Currency(BASE_CURRENCY_CODE), SCALE);

    def 'should return the same amount when target and source currencies are the same'() {
        given:
            ConversionParameter parameter = new ConversionParameter(new Currency(sourceCode), new Currency(targetCode), sourceRate, targetRate, givenAmount)
        when:
            BigDecimal convertedAmount = currencyConverter.convert(parameter)
        then:
            convertedAmount == expectedAmount
        where:
            sourceCode | targetCode | sourceRate | targetRate | givenAmount | expectedAmount
            'EUR'      | 'EUR'      | 1.2221     | 1.0545     | 200.00      | 200.00
    }

    @Unroll
    def 'should properly convert amount using given rates'() {
        given:
            ConversionParameter parameter = new ConversionParameter(new Currency(sourceCode), new Currency(targetCode), sourceRate, targetRate, givenAmount)
        when:
            BigDecimal convertedAmount = currencyConverter.convert(parameter)
        then:
            convertedAmount == expectedAmount
        where:
            sourceCode | targetCode | sourceRate | targetRate | givenAmount | expectedAmount
            'EUR'      | 'PLN'      | 1.000      | 4.12120    | 200.00      | 824.24
            'PLN'      | 'EUR'      | 4.43030    | 1.000      | 800.00      | 180.57
            'PLN'      | 'USD'      | 4.43030    | 1.06010    | 500.00      | 119.64
    }
}
