package homework.argument;

import homework.rate.RateProvider;

public class ArgumentValidator implements Validator {
    private final RateProvider rateProvider;

    public ArgumentValidator(RateProvider rateProvider) {
        this.rateProvider = rateProvider;
    }

    public boolean isArgumentValid(ArgumentWrapper argument) {

        // TODO add implementation

        return true;
    }

    public String getFailureMessage(ArgumentWrapper argument) {

        return "";
    }

}
