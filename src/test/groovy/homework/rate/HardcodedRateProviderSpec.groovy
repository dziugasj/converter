package homework.rate

import homework.currency.Currency
import spock.lang.Specification
import spock.lang.Subject

class HardcodedRateProviderSpec extends Specification {
    @Subject
    HardcodedRateProvider hardcodedRateProvider = new HardcodedRateProvider()

    def 'should return empty buy rate when non existing currency passed'() {
        when:
            def rate = hardcodedRateProvider.getBuyRate(new Currency("XXX"))
        then:
            rate.isEmpty()
    }

    def 'should return empty sell rate when non existing currency passed'() {
        when:
            def rate = hardcodedRateProvider.getSellRate(new Currency("XXX"))
        then:
            rate.isEmpty()
    }
}
