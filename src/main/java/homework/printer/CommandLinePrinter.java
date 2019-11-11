package homework.printer;

import java.math.BigDecimal;

public class CommandLinePrinter implements Printer {

    @Override
    public void printNumber(final BigDecimal value) {
        System.out.println(value);
    }

    @Override
    public void printMessage(final String message) {
        System.out.println(message);
    }
}
