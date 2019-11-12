package homework.printer


import spock.lang.Specification
import spock.lang.Subject

class PrinterFactorySpec extends Specification {
    @Subject
    PrinterFactory printerFactory = new PrinterFactory()

    def 'factory should return just CommandLinePrinter instance'() {
        when:
            Printer printer = printerFactory.getPrinter()
        then:
            printer in CommandLinePrinter
    }
}
