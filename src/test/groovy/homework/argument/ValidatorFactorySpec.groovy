package homework.argument

import homework.rate.RateProvider
import spock.lang.Specification
import spock.lang.Subject

class ValidatorFactorySpec extends Specification {
    @Subject
    ValidatorFactory validatorFactory = new ValidatorFactory()

    def 'factory should return just ArgumentValidator instance'() {
        when:
            Validator validator = validatorFactory.getValidator(Mock(RateProvider))
        then:
            validator in ArgumentValidator
    }
}
