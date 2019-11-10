package homework;

import homework.argument.Argument;
import homework.argument.ArgumentParser;
import homework.rate.RateProvider;

import java.math.MathContext;
import java.util.Locale;

public class CommandLineConverter {

    private final MathContext MATH_OPERATIONS_CONTEXT;
    private final int CURRENCY_PRECISION;
    private final Locale DEFAULT_LOCALE;
    private final String DEFAULT_CURRENCY_CODE;
    private final RateProvider RATE_PROVIDER;

    // TODO add builder
    public CommandLineConverter(MathContext MATH_OPERATIONS_CONTEXT, int CURRENCY_PRECISION, Locale DEFAULT_LOCALE, String DEFAULT_CURRENCY_CODE, RateProvider RATE_PROVIDER) {
        this.MATH_OPERATIONS_CONTEXT = MATH_OPERATIONS_CONTEXT;
        this.CURRENCY_PRECISION = CURRENCY_PRECISION;
        this.DEFAULT_LOCALE = DEFAULT_LOCALE;
        this.DEFAULT_CURRENCY_CODE = DEFAULT_CURRENCY_CODE;
        this.RATE_PROVIDER = RATE_PROVIDER;
    }



    public ExecutionResult convert(String[] args) {


        ArgumentParser argumentParser = new ArgumentParser();
        Argument argument = argumentParser.getParsedArgument(args);




        //CurrencyResolver resolver;


        //currency.getSourceCurrencyCode();


        //currency.getTargetCurrencyCode();


        //resolver.resolveByCode()




        // TODO cia jau turim zinoti jog valiutos existuoja. Plius reikia paduoti baseCurrency.
        //CurrencyConverter converter = new CurrencyConverter(MathContext, baseCurrency, precision, rateProvider);

        //BigDecimal convertedAmount = converter.convert(fromCurrency, toCurrency, amount);




        /*
        if (argument.isValid()) {
            return Converter.convert();
        } else {
            return ErrorMessageResolver.getErrorMessage(argument);
        }

         */


        return new ExecutionResult(true, null, null);
    }
}
