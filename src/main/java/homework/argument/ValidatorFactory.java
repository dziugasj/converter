package homework.argument;

import homework.rate.RateProvider;

public class ValidatorFactory {
    public Validator getValidator(final RateProvider rateProvider) {
        return new ArgumentValidator(rateProvider);
    }
}
