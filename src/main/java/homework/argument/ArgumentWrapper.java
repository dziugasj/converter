package homework.argument;

import java.math.BigDecimal;
import java.util.Optional;

import static java.util.Optional.*;

public class ArgumentWrapper {
    private final String args[];

    public ArgumentWrapper(String[] args) {
        this.args = args;
    }

    public Optional<String> getSource() {
        return getCodeByEntry(0);
    }

    public Optional<String> getTarget() {
        return getCodeByEntry(1);
    }

    public Optional<BigDecimal> getAmount() {
        if (parameterCountIsValid()) {
            return toBigDecimal(args[1]);
        } else {
            return empty();
        }
    }

    public boolean parameterCountIsValid() {
        return args.length == 2 && getPair().length == 2;
    }

    public boolean noParameters() {
        return args.length == 0;
    }

    private String[] getPair() {
        return args[0].split("/");
    }

    private Optional<String> getCodeByEntry(int entry) {
        String[] pair = getPair();
        if (pair.length == 2) {
            return ofNullable(getPair()[entry]);
        } else {
            return empty();
        }
    }

    private Optional<BigDecimal> toBigDecimal(String amount) {
        try {
            return of(new BigDecimal(amount));
        } catch (NumberFormatException | NullPointerException e) {
            return empty();
        }
    }
}
