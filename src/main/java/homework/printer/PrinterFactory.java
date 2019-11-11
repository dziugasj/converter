package homework.printer;

public class PrinterFactory {
    public Printer getPrinter() {
        return new CommandLinePrinter();
    }
}
