package homework.currency;

import java.math.BigDecimal;

public interface Converter {
    BigDecimal convert(final ConversionParameter parameter);
}
