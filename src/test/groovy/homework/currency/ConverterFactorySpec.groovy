package homework.currency


import spock.lang.Specification
import spock.lang.Subject

class ConverterFactorySpec extends Specification {
    @Subject
    ConverterFactory converterFactory = new ConverterFactory()

    def 'factory should return just CurrencyConverter instance'() {
        when:
            Converter converter = converterFactory.getConverter()
        then:
            converter in CurrencyConverter
    }
}
