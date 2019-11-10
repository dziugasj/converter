package homework;

import homework.argument.Argument;
import homework.argument.ArgumentParser;
import homework.currency.Currency;
import homework.currency.CurrencyConverter;
import homework.rate.RateProvider;

import java.math.MathContext;
import java.util.Locale;

// TODO debiliskas pavadinimas, reikia pervardinti
public class Converter {

    private final MathContext mathContext;
    private final int currencyScale;
    private final Locale defaultLocale;
    private final String defaultCurrencyCode;
    private final RateProvider rateProvider;

    // TODO add builder ?
    public Converter(MathContext mathContext, int currencyScale, Locale defaultLocale, String defaultCurrencyCode, RateProvider rateProvider) {
        this.mathContext = mathContext;
        this.currencyScale = currencyScale;
        this.defaultLocale = defaultLocale;
        this.defaultCurrencyCode = defaultCurrencyCode;
        this.rateProvider = rateProvider;
    }

    public ExecutionResult convert(String[] args) {

        ArgumentParser argumentParser = new ArgumentParser(defaultLocale);
        Argument argument = argumentParser.getParsedArgument(args);




        //CurrencyResolver resolver;


        //currency.getSourceCurrencyCode();


        //currency.getTargetCurrencyCode();


        //resolver.resolveByCode()




        CurrencyConverter converter = new CurrencyConverter(mathContext, new Currency(defaultCurrencyCode), currencyScale, rateProvider);

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
