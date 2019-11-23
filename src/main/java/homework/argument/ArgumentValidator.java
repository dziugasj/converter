package homework.argument;

import homework.currency.Currency;
import homework.rate.RateProvider;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.function.Function;

public class ArgumentValidator implements Validator {
    private static final String SOURCE_CODE_MISSING = "Source currency code incorrect;";
    private static final String TARGET_CODE_MISSING = "Target currency code incorrect;";
    private static final String AMOUNT_MISSING = "Amount is incorrectly formatted or missing;";
    private static final String WRONG_PARAMETERS = "Wrong number of parameters;";
    private static final String USAGE_MESSAGE = "Usage: Convert <Currency pair> <Amount to convert>";

    private final RateProvider rateProvider;

    public ArgumentValidator(final RateProvider rateProvider) {
        this.rateProvider = rateProvider;
    }

    public boolean isArgumentValid(final ArgumentWrapper argument) {
        return argument.parameterCountIsValid()
                && sourceRateIsPresent(argument)
                && targetRateIsPresent(argument)
                && amountIsValid(argument.getAmount());
    }

    public String getFailureMessage(final ArgumentWrapper argument) {
        StringBuilder builder = new StringBuilder();

        if (argument.noParameters()) {
            addUsageMessage(argument, builder);
        } else {
            addSourceMessage(argument, builder);
            addTargetMessage(argument, builder);
            addAmountMessage(argument, builder);
            addParameterCountMessage(argument, builder);
        }

        return builder.toString();
    }

    private void addSourceMessage(ArgumentWrapper argument, StringBuilder builder) {
        if (!sourceRateIsPresent(argument)) {
            builder.append(SOURCE_CODE_MISSING);
        }
    }

    private void addTargetMessage(ArgumentWrapper argument, StringBuilder builder) {
        if (!targetRateIsPresent(argument)) {
            builder.append(TARGET_CODE_MISSING);
        }
    }

    private void addAmountMessage(ArgumentWrapper argument, StringBuilder builder) {
        if (!amountIsValid(argument.getAmount())) {
            builder.append(AMOUNT_MISSING);
        }
    }

    private void addParameterCountMessage(ArgumentWrapper argument, StringBuilder builder) {
        if (!argument.parameterCountIsValid()) {
            builder.append(WRONG_PARAMETERS);
        }
    }

    private void addUsageMessage(ArgumentWrapper argument, StringBuilder builder) {
        builder.append(USAGE_MESSAGE);
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

    private boolean sourceRateIsPresent(ArgumentWrapper argument) {
        return rateIsPresent(argument.getSource(), rateProvider::getBuyRate);
    }

    private boolean targetRateIsPresent(ArgumentWrapper argument) {
        return rateIsPresent(argument.getTarget(), rateProvider::getSellRate);
    }
}
