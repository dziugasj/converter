package homework.argument;

import homework.currency.Currency;
import homework.rate.RateProvider;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.function.Function;

import static java.util.stream.Stream.of;

public class ArgumentValidator implements Validator {
    private final RateProvider rateProvider;

    public ArgumentValidator(RateProvider rateProvider) {
        this.rateProvider = rateProvider;
    }

    public boolean isArgumentValid(ArgumentWrapper argument) {
        return isAllValuesFilled(argument)
                && rateIsPresent(argument.getSource(), rateProvider::getBuyRate)
                && rateIsPresent(argument.getTarget(), rateProvider::getSellRate)
                && amountIsValid(argument.getAmount());
    }

    private boolean isAllValuesFilled(ArgumentWrapper argument) {
        return of(argument.getSource(), argument.getTarget(), argument.getAmount())
                .filter(this::hasValue)
                .findFirst()
                .map(firstEmptyValue -> false)
                .orElse(true);
    }

    private boolean hasValue(Optional optional) {
        return !optional.isEmpty();
    }

    public String getFailureMessage(ArgumentWrapper argument) {
        StringBuilder builder = new StringBuilder();
        addSourceMessage(argument, builder);
        addTargetMessage(argument, builder);
        addAmountMessage(argument, builder);

        return builder.toString();
    }

    private void addSourceMessage(ArgumentWrapper argument, StringBuilder builder) {
        if (!rateIsPresent(argument.getSource(), rateProvider::getBuyRate)) {
            builder.append("Source currency code incorrect;");
        }
    }

    private void addTargetMessage(ArgumentWrapper argument, StringBuilder builder) {
        if (!rateIsPresent(argument.getTarget(), rateProvider::getSellRate)) {
            builder.append("Target currency code incorrect;");
        }
    }

    private void addAmountMessage(ArgumentWrapper argument, StringBuilder builder) {
        if (amountIsValid(argument.getAmount())) {
            builder.append("Amount is incorrectly formatted");
        }
    }

    private boolean rateIsPresent(Optional<String> code, Function<Currency, Optional<BigDecimal>> getRate) {
        return code
                .map(this::toCurrency)
                .map(currency -> toRate(currency, getRate))
                .flatMap(o -> o)
                .map(rate -> true)
                .orElse(false);
    }

    private Currency toCurrency(String code) {
        return new Currency(code);
    }

    private Optional<BigDecimal> toRate(Currency currency, Function<Currency, Optional<BigDecimal>> getRate) {
        return getRate.apply(currency);
    }

    private boolean amountIsValid(Optional<BigDecimal> amount) {
        return amount
                .map(this::amountIsPositive)
                .orElse(false);
    }

    private boolean amountIsPositive(BigDecimal amount) {
        return amount.compareTo(BigDecimal.ZERO) > 0;
    }
}
