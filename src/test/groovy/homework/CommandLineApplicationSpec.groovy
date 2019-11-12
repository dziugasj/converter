package homework

import spock.lang.Specification

class CommandLineApplicationSpec extends Specification {
    def 'should run without exception when correct parameter passed'() {
        given:
            String[] correctParameter = ["EUR/PLN", "200.00"]
        when:
            CommandLineApplication.main(correctParameter)
        then:
            noExceptionThrown()
    }
}
