package homework

import homework.argument.Validator
import homework.currency.Converter
import homework.printer.Printer
import homework.rate.HardcodedRateProvider
import homework.rate.RateProvider
import spock.lang.Specification
import spock.lang.Subject

class ConversionHandlerSpec extends Specification {
    Validator validator = Mock()
    Converter converter = Mock()
    Printer printer = Mock()
    RateProvider rateProvider = Mock()

    @Subject
    ConversionHandler conversionHandler = new ConversionHandler(validator, converter, printer, rateProvider)

    def 'should convert'() {
        when:
            conversionHandler.convertAndPrintResult()
        then:
            rateProvider in HardcodedRateProvider
    }






}
