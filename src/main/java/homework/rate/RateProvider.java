package homework.rate;

import homework.currency.Currency;

import java.math.BigDecimal;
import java.util.Optional;

public interface RateProvider {
    Optional<BigDecimal> getBuyRate(Currency currency);

    Optional<BigDecimal> getSellRate(Currency currency);
}
