package homework.printer;

import java.math.BigDecimal;

public interface Printer {
    void printNumber(final BigDecimal value);

    void printMessage(final String failureMessage);
}
