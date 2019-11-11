package homework;

import homework.argument.ArgumentValidator;
import homework.argument.ArgumentWrapper;
import homework.argument.Validator;
import homework.currency.Converter;
import homework.currency.Currency;
import homework.currency.CurrencyConverter;
import homework.printer.CommandLinePrinter;
import homework.printer.Printer;
import homework.rate.RateProvider;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Locale;

import static java.util.Optional.of;

public class ConversionHandler {

    private  MathContext mathContext;
    private  int currencyScale;
    private  Locale defaultLocale;
    private  String defaultCurrencyCode;
    private  RateProvider rateProvider;
    private CommandLinePrinter commandLinePrinter;


    CurrencyConverter converter;


    public ConversionHandler(Validator validator, Converter converter, Printer printer) {


    }

    public ConversionHandler(MathContext mathContext, int currencyScale, Locale defaultLocale, String defaultCurrencyCode, RateProvider rateProvider, CommandLinePrinter commandLinePrinter) {
        this.mathContext = mathContext;
        this.currencyScale = currencyScale;
        this.defaultLocale = defaultLocale;
        this.defaultCurrencyCode = defaultCurrencyCode;
        this.rateProvider = rateProvider;
        this.commandLinePrinter = commandLinePrinter;


        validator = new ArgumentValidator(); // Gauti per factory
        converter = new CurrencyConverter(mathContext, new Currency(defaultCurrencyCode), currencyScale, rateProvider);

    }

    public void convertAndPrintResult(ArgumentWrapper argument) {
        of(argument)
                .filter(this::isValid)
                .map(this::convert)
                .ifPresentOrElse(this::printValue, () -> printFailureMessage(argument));
    }

    private boolean isValid(ArgumentWrapper argument) {
        return validator.isArgumentValid(argument);
    }

    private BigDecimal convert(ArgumentWrapper argument) {
        return converter.convert(new Currency(argument.getSource()), new Currency(argument.getTarget()), argument.getAmount());
    }

    private void printValue(BigDecimal value) {
        printer.printLocalizedNumber(value);
    }

    private void printFailureMessage(ArgumentWrapper argument) {
        printer.printMessage(validator.getFailureMessage(argument));
    }
}
