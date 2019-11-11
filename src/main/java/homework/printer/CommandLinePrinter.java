package homework.printer;

import java.math.BigDecimal;

public class CommandLinePrinter implements Printer {

    @Override
    public void printNumber(BigDecimal value) {
        System.out.println(value);
    }

    @Override
    public void printMessage(String message) {
        System.out.println(message);
    }
}
