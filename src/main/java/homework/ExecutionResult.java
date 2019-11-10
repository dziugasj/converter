package homework;

public class ExecutionResult {
    private final boolean isSuccess;
    private final String errorMessage;
    private final String responseMessage;

    public ExecutionResult(boolean isSuccess, String errorMessage, String responseMessage) {
        this.isSuccess = isSuccess;
        this.errorMessage = errorMessage;
        this.responseMessage = responseMessage;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getSuccessMessage() {
        return responseMessage;
    }
}
