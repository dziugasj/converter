package homework.printer;

import java.math.BigDecimal;

public interface Printer {
    void printNumber(BigDecimal value);

    void printMessage(String failureMessage);
}
