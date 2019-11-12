package homework.rate


import spock.lang.Specification
import spock.lang.Subject

class RateProviderFactorySpec extends Specification {
    @Subject
    RateProviderFactory rateProviderFactory = new RateProviderFactory()

    def 'factory should return just HardcodedRateProvider instance'() {
        when:
            RateProvider rateProvider = rateProviderFactory.getRateProvider()
        then:
            rateProvider in HardcodedRateProvider
    }
}
