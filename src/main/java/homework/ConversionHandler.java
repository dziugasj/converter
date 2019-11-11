package homework;

import homework.argument.ArgumentWrapper;
import homework.argument.Validator;
import homework.currency.Converter;
import homework.currency.Currency;
import homework.printer.Printer;

import java.math.BigDecimal;

import static java.util.Optional.of;

public class ConversionHandler {
    private final Validator validator;
    private final Converter converter;
    private final Printer printer;

    public ConversionHandler(Validator validator, Converter converter, Printer printer) {
        this.validator = validator;
        this.converter = converter;
        this.printer = printer;
    }

    public void convertAndPrintResult(ArgumentWrapper argument) {
        of(argument)
                .filter(this::isValid)
                .map(this::convert)
                .ifPresentOrElse(this::printConvertedValue, () -> printFailureMessage(argument));
    }

    private boolean isValid(ArgumentWrapper argument) {
        return validator.isArgumentValid(argument);
    }

    private BigDecimal convert(ArgumentWrapper argument) {
        return converter.convert(new Currency(argument.getSource()), new Currency(argument.getTarget()), argument.getAmount());
    }

    private void printConvertedValue(BigDecimal value) {
        printer.printNumber(value);
    }

    private void printFailureMessage(ArgumentWrapper argument) {
        printer.printMessage(validator.getFailureMessage(argument));
    }
}
