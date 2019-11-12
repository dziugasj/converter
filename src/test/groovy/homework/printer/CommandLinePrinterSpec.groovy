package homework.printer

import spock.lang.Specification
import spock.lang.Subject

import static java.math.BigDecimal.ZERO

class CommandLinePrinterSpec extends Specification {
    @Subject
    CommandLinePrinter commandLinePrinter = new CommandLinePrinter()

    def 'should not throw exception when printing message'() {
        when:
            commandLinePrinter.printMessage("")
        then:
            noExceptionThrown()
    }

    def 'should not throw exception when printing number'() {
        when:
            commandLinePrinter.printNumber(ZERO)
        then:
            noExceptionThrown()
    }
}
