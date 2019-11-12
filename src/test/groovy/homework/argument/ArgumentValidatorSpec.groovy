package homework.argument

import homework.rate.RateProvider
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

import static java.util.Optional.of
import static java.util.Optional.empty

class ArgumentValidatorSpec extends Specification {

    RateProvider rateProvider = createRateProviderMock()

    @Subject
    ArgumentValidator argumentValidator = new ArgumentValidator(rateProvider)

    @Unroll
    def 'should return valid argument only when all parameters are present'() {
        given:
            ArgumentWrapper argument = Mock() {
                getSource() >> source
                getTarget() >> target
                getAmount() >> amount
                parameterCountIsValid() >> true
            }
        when:
            def result = argumentValidator.isArgumentValid(argument)
        then:
            result == isValid
        where:
            source    | target    | amount     | isValid
            of("EUR") | of("PLN") | of(200.00) | true
            of("EUR") | of("PLN") | empty()    | false
            of("EUR") | empty()   | empty()    | false
            empty()   | empty()   | empty()    | false
    }

    @Unroll
    def 'should return valid argument only number is valid'() {
        given:
            ArgumentWrapper argument = Mock() {
                getSource() >> source
                getTarget() >> target
                getAmount() >> amount
                parameterCountIsValid() >> true
            }
        when:
            def result = argumentValidator.isArgumentValid(argument)
        then:
            result == isValid
        where:
            source    | target    | amount     | isValid
            of("EUR") | of("PLN") | of(200.00) | true
            of("EUR") | of("PLN") | of(0.0)    | false
            of("EUR") | of("PLN") | of(-50.00) | false
    }

    @Unroll
    def 'should return proper failure message'() {
        given:
            ArgumentWrapper argument = Mock() {
                getSource() >> source
                getTarget() >> target
                getAmount() >> amount
                parameterCountIsValid() >> paramerCountValid
                noParameters() >> noParametersPresent
            }
        when:
            def message = argumentValidator.getFailureMessage(argument)
        then:
            message == messageText
        where:
            source    | target    | amount     | noParametersPresent | paramerCountValid | messageText
            of("EUR") | of("PLN") | of(200.00) | false               | true              | ''
            of("EUR") | of("PLN") | empty()    | false               | true              | ArgumentValidator.AMOUNT_MISSING
            of("EUR") | empty()   | of(200.00) | false               | true              | ArgumentValidator.TARGET_CODE_MISSING
            empty()   | of("PLN") | of(200.00) | false               | true              | ArgumentValidator.SOURCE_CODE_MISSING
            of("EUR") | of("PLN") | of(200.00) | true                | true              | ArgumentValidator.USAGE_MESSAGE
            of("EUR") | of("PLN") | of(200.00) | false               | false             | ArgumentValidator.WRONG_PARAMETERS
    }

    private RateProvider createRateProviderMock() {
        Mock(RateProvider) {
            getBuyRate(_) >> of(1.00)
            getSellRate(_) >> of(1.00)
        }
    }
}
