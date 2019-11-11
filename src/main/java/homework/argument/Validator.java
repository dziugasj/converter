package homework.argument;

public interface Validator {
    boolean isArgumentValid(final ArgumentWrapper argument);

    String getFailureMessage(final ArgumentWrapper argument);
}
