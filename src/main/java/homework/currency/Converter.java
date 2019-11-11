package homework.currency;

import java.math.BigDecimal;

public interface Converter {
    BigDecimal convert(Currency source, Currency target, BigDecimal amount);
}
