package com.intuit.app.exception;

public class LabelNotExistsException extends RuntimeException {

    private final String throwerThreadName;
    private ErrorCodes errorCode;

    public LabelNotExistsException(ErrorCodes errorCode, String defaultMessage) {
        super(defaultMessage);
        this.throwerThreadName = Thread.currentThread().getName();
        this.setErrorCode(errorCode);
    }

    public LabelNotExistsException(ErrorCodes errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
        this.throwerThreadName = Thread.currentThread().getName();
    }

    public String getThrowerThreadName() {
        return throwerThreadName;
    }

    public ErrorCodes getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCodes errorCode) {
        this.errorCode = errorCode;
    }
}
