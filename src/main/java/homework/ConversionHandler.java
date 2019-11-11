package homework;

import homework.argument.ArgumentWrapper;
import homework.argument.Validator;
import homework.currency.ConversionParameter;
import homework.currency.Converter;
import homework.currency.Currency;
import homework.printer.Printer;
import homework.rate.RateProvider;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.Optional.empty;
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
                .flatMap(o -> o)
                .map(this::convert)
                .ifPresentOrElse(this::printConvertedValue, () -> printFailureMessage(argument));
    }

    private boolean isValid(ArgumentWrapper argument) {
        return validator.isArgumentValid(argument);
    }

    private Optional<ConversionParameter> toConversionParameter(ArgumentWrapper argument) {
        Optional<Currency> sourceCurrency = argument.getSource().map(this::toCurrency);
        Optional<Currency> targetCurrency = argument.getTarget().map(this::toCurrency);
        Optional<BigDecimal> sourceRate = getSourceRate(argument);
        Optional<BigDecimal> targetRate = getTargetRate(argument);
        Optional<BigDecimal> amount = argument.getAmount();

        if (allValuesPresent(sourceCurrency, targetCurrency, sourceRate, targetRate, amount)) {
            return of(new ConversionParameter(
                    sourceCurrency.get(),
                    targetCurrency.get(),
                    sourceRate.get(),
                    targetRate.get(),
                    amount.get()));
        } else {
            return empty();
        }
    }

    private Optional<BigDecimal> getSourceRate(ArgumentWrapper argument) {
        return argument.getSource()
                .map(this::toCurrency)
                .map(rateProvider::getBuyRate)
                .flatMap(o -> o);
    }

    private Optional<BigDecimal> getTargetRate(ArgumentWrapper argument) {
        return argument.getTarget()
                .map(this::toCurrency)
                .map(rateProvider::getSellRate)
                .flatMap(o -> o);
    }

    private boolean allValuesPresent(Optional<Currency> sourceCurrency,
                                     Optional<Currency> targetCurrency,
                                     Optional<BigDecimal> sourceRate,
                                     Optional<BigDecimal> targetRate,
                                     Optional<BigDecimal> amount) {
        return Stream.of(sourceCurrency, targetCurrency, sourceRate, targetRate, amount)
                .allMatch(Optional::isPresent);
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
