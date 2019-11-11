package homework.argument;

import java.math.BigDecimal;
import java.util.Optional;

public class ArgumentWrapper {
    private final String args[];

    public ArgumentWrapper(String[] args) {
        this.args = args;
    }

    public Optional<String> getSource() {
        return Optional.empty();
    }

    public Optional<String> getTarget() {
        return Optional.empty();
    }

    public Optional<BigDecimal> getAmount() {

        return Optional.empty();
    }
}
