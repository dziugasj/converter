package homework.argument;

public interface Validator {
    boolean isArgumentValid(ArgumentWrapper argument);

    String getFailureMessage(ArgumentWrapper argument);
}
