package homework.argument

import spock.lang.Specification
import spock.lang.Unroll

class ArgumentWrapperSpec extends Specification {
    @Unroll
    def 'should determine when parameters ar empty or not'() {
        given:
            ArgumentWrapper argumentWrapper = new ArgumentWrapper(array);
        when:
            boolean result = argumentWrapper.noParameters()
        then:
            result == emptyParameters
        where:
            array             | emptyParameters
            [] as String[]    | true
            ["x"] as String[] | false
    }

    @Unroll
    def 'should determine when parameters count is valid'() {
        given:
            ArgumentWrapper argumentWrapper = new ArgumentWrapper(array);
        when:
            def result = argumentWrapper.parameterCountIsValid()
        then:
            result == isValidCount
        where:
            array                                     | isValidCount
            [] as String[]                            | false
            ["EUR"] as String[]                       | false
            ["EUR/PLN"] as String[]                   | false
            ["EUR/PLN", "200.00"] as String[]         | true
            ["EUR/PLN", "200.00", "more"] as String[] | false
    }

    @Unroll
    def 'should get amount when it is correctly formatted and present'() {
        given:
            ArgumentWrapper argumentWrapper = new ArgumentWrapper(array);
        when:
            def amount = argumentWrapper.getAmount()
        then:
            amount.isPresent() == haveAmout
        where:
            array                              | haveAmout
            ["EUR/PLN", "200.00"] as String[]  | true
            ["EUR/PLN", "20X0.00"] as String[] | false
            ["EUR/PLN"] as String[]            | false
    }

    @Unroll
    def 'should get source currency when it is present'() {
        given:
            ArgumentWrapper argumentWrapper = new ArgumentWrapper(array);
        when:
            def currency = argumentWrapper.getAmount()
        then:
            currency.isPresent() == haveCurrency
        where:
            array                             | haveCurrency
            ["EUR/PLN", "200.00"] as String[] | true
            ["PLN", "200.00"] as String[]     | false
            ["PLN"] as String[]               | false
    }

    @Unroll
    def 'should get target currency when it is present'() {
        given:
            ArgumentWrapper argumentWrapper = new ArgumentWrapper(array);
        when:
            def currency = argumentWrapper.getAmount()
        then:
            currency.isPresent() == haveCurrency
        where:
            array                             | haveCurrency
            ["EUR/PLN", "200.00"] as String[] | true
            ["PLN", "200.00"] as String[]     | false
            ["PLN"] as String[]               | false
    }
}
