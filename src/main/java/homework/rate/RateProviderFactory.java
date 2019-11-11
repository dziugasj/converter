package homework.rate;

public class RateProviderFactory {
    public RateProvider getRateProvider() {
        return new HardcodedRateProvider();
    }
}
