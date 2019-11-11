package homework.argument;

import java.math.BigDecimal;
import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.ofNullable;

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
            return ofNullable(args[1])
                    .map(s -> new BigDecimal(s))
                    .or(() -> empty());
        } else {
            return empty();
        }
    }

    public boolean parameterCountIsValid() {
        return args.length == 2 && getPair().length == 2;
    }

    private String[] getPair() {
        return args[0].split("/");
    }

    private Optional<String> getCodeByEntry(int entry) {
        if (parameterCountIsValid()) {
            return ofNullable(getPair()[entry]);
        } else {
            return empty();
        }
    }
}
