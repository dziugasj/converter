package homework.rate

import spock.lang.Specification
import spock.lang.Subject

class HardcodedRateProviderSpec extends Specification {

    @Subject
    HardcodedRateProvider hardcodedRateProvider = new HardcodedRateProvider()



    def 'should return proper buy rate'() {
        when:
            RateProvider rateProvider = hardcodedRateProvider.getBuyRate()
        then:
            rateProvider in HardcodedRateProvider
    }


    def 'should return proper sell rate'() {
        when:
            RateProvider rateProvider = rateProviderFactory.getRateProvider()
        then:
            rateProvider in HardcodedRateProvider
    }





}
