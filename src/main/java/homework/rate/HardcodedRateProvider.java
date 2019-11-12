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
    public Optional<BigDecimal> getBuyRate(final Currency currency) {
        return ofNullable(buyRateMap.get(currency));
    }

    @Override
    public Optional<BigDecimal> getSellRate(final Currency currency) {
        return ofNullable(sellRateMap.get(currency));
    }

    private Map<Currency, BigDecimal> getHardcodedBuyMap() {
        return ImmutableMap.<Currency, BigDecimal>builder()
                .put(new Currency("EUR"), valueOf(7.4394))
                .put(new Currency("USD"), valueOf(6.6311))
                .put(new Currency("GBP"), valueOf(8.5285))
                .put(new Currency("SEK"), valueOf(0.7610))
                .put(new Currency("NOK"), valueOf(0.7840))
                .put(new Currency("CHF"), valueOf(6.8358))
                .put(new Currency("JPY"), valueOf(0.059740))
                .put(new Currency("DKK"), valueOf(1.0))
                .build();
    }

    private Map<Currency, BigDecimal> getHardcodedSellMap() {
        return ImmutableMap.<Currency, BigDecimal>builder()
                .put(new Currency("EUR"), valueOf(7.4394))
                .put(new Currency("USD"), valueOf(6.6311))
                .put(new Currency("GBP"), valueOf(8.5285))
                .put(new Currency("SEK"), valueOf(0.7610))
                .put(new Currency("NOK"), valueOf(0.7840))
                .put(new Currency("CHF"), valueOf(6.8358))
                .put(new Currency("JPY"), valueOf(0.059740))
                .put(new Currency("DKK"), valueOf(1.0))
                .build();
    }
}
