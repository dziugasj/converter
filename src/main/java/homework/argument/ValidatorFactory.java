package homework.argument;

import homework.rate.RateProvider;

public class ValidatorFactory {
    public Validator getValidator(RateProvider rateProvider) {
        return new ArgumentValidator(rateProvider);
    }
}
