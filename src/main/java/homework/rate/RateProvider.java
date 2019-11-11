package homework.rate;

import homework.currency.Currency;

import java.math.BigDecimal;
import java.util.Optional;

public interface RateProvider {
    Optional<BigDecimal> getBuyRate(final Currency currency);

    Optional<BigDecimal> getSellRate(final Currency currency);
}
