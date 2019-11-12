package homework

import homework.argument.ArgumentWrapper
import homework.argument.Validator
import homework.currency.Converter
import homework.printer.Printer
import homework.rate.RateProvider
import spock.lang.Specification
import spock.lang.Subject

import static java.math.BigDecimal.TEN
import static java.math.BigDecimal.valueOf
import static java.util.Optional.of

class ConversionHandlerSpec extends Specification {
    static final String FAILURE_MESSAGE = "some error"
    static final BigDecimal CONVERTED_VALUE = TEN

    Validator validator = Mock()
    Converter converter = Mock()
    Printer printer = Mock()
    RateProvider rateProvider = Mock()
    ArgumentWrapper argumentWrapper = Mock()

    @Subject
    ConversionHandler conversionHandler = new ConversionHandler(validator, converter, printer, rateProvider)

    def 'setup'() {
        argumentWrapper = createArgumentWrapperMock()

        converter.convert(_) >> CONVERTED_VALUE

        rateProvider.getSellRate(_) >> of(valueOf(1.22))
        rateProvider.getBuyRate(_) >> of(valueOf(1.33))
    }

    def 'should print error message'() {
        given:
            validator.isArgumentValid(argumentWrapper) >> false
            validator.getFailureMessage(argumentWrapper) >> FAILURE_MESSAGE
        when:
            conversionHandler.convertAndPrintResult(argumentWrapper)
        then:
            1 * printer.printMessage(FAILURE_MESSAGE)
    }

    def 'should print converted value'() {
        given:
            validator.isArgumentValid(argumentWrapper) >> true
            validator.getFailureMessage(argumentWrapper) >> FAILURE_MESSAGE
        when:
            conversionHandler.convertAndPrintResult(argumentWrapper)
        then:
            1 * printer.printNumber(CONVERTED_VALUE)
            0 * printer.printMessage(FAILURE_MESSAGE)
    }

    private ArgumentWrapper createArgumentWrapperMock() {
        Mock(ArgumentWrapper) {
            getSource() >> of("EUR")
            getTarget() >> of("PLN")
            getAmount() >> of(TEN)
        }
    }
}
