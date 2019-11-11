package homework;

import homework.argument.ArgumentWrapper;
import homework.argument.Validator;
import homework.currency.ConversionParameter;
import homework.currency.Converter;
import homework.currency.Currency;
import homework.printer.Printer;
import homework.rate.RateProvider;

import java.math.BigDecimal;

import static java.util.Optional.of;

public class ConversionHandler {
    private final Validator validator;
    private final Converter converter;
    private final Printer printer;
    private final RateProvider rateProvider;

    public ConversionHandler(Validator validator, Converter converter, Printer printer, RateProvider rateProvider) {
        this.validator = validator;
        this.converter = converter;
        this.printer = printer;
        this.rateProvider = rateProvider;
    }

    public void convertAndPrintResult(ArgumentWrapper argument) {
        of(argument)
                .filter(this::isValid)
                .map(this::toConversionParameter)
                .map(this::convert)
                .ifPresentOrElse(this::printConvertedValue, () -> printFailureMessage(argument));
    }

    private boolean isValid(ArgumentWrapper argument) {
        return validator.isArgumentValid(argument);
    }

    private ConversionParameter toConversionParameter(ArgumentWrapper argument) {

        // TODO fix this

        Currency sourceCurrency = toCurrency(argument.getSource().get());
        Currency targetCurrency = toCurrency(argument.getTarget().get());
        BigDecimal sourceRate = rateProvider.getBuyRate(sourceCurrency).orElseThrow(() -> new RuntimeException("No buy rate"));
        BigDecimal targetRate = rateProvider.getSellRate(targetCurrency).orElseThrow(() -> new RuntimeException("No sell rate"));

        return new ConversionParameter(sourceCurrency, targetCurrency, sourceRate, targetRate, argument.getAmount().get());
    }

    private Currency toCurrency(String code) {
        return new Currency(code);
    }

    private BigDecimal convert(ConversionParameter parameter) {
        return converter.convert(parameter);
    }

    private void printConvertedValue(BigDecimal value) {
        printer.printNumber(value);
    }

    private void printFailureMessage(ArgumentWrapper argument) {
        printer.printMessage(validator.getFailureMessage(argument));
    }
}
