package homework.rate;

import com.google.common.collect.ImmutableMap;
import homework.currency.Currency;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

import static java.math.BigDecimal.valueOf;
import static java.util.Optional.ofNullable;

public class HardcodedRateProvider implements RateProvider {
    private final Map<Currency, BigDecimal> buyRateMap;
    private final Map<Currency, BigDecimal> sellRateMap;

    public HardcodedRateProvider() {
        buyRateMap = getHardcodedBuyMap();
        sellRateMap = getHardcodedSellMap();
    }

    @Override
    public Optional<BigDecimal> getBuyRate(Currency currency) {
        return ofNullable(buyRateMap.get(currency));
    }

    @Override
    public Optional<BigDecimal> getSellRate(Currency currency) {
        return ofNullable(sellRateMap.get(currency));
    }

    private Map<Currency, BigDecimal> getHardcodedBuyMap() {
        return ImmutableMap.<Currency, BigDecimal>builder()
                .put(new Currency("PLN"), valueOf(4.41430))
                .put(new Currency("USD"), valueOf(1.14480))
                .build();
    }

    private Map<Currency, BigDecimal> getHardcodedSellMap() {
        return ImmutableMap.<Currency, BigDecimal>builder()
                .put(new Currency("PLN"), valueOf(4.10560))
                .put(new Currency("USD"), valueOf(1.06190))
                .build();
    }
}
