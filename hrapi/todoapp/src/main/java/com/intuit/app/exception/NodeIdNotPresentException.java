package com.intuit.app.exception;

public class NodeIdNotPresentException extends RuntimeException {

    private final String throwerThreadName;
    private ErrorCodes errorCode;

    public NodeIdNotPresentException(ErrorCodes errorCode, String defaultMessage) {
        super(defaultMessage);
        this.throwerThreadName = Thread.currentThread().getName();
        this.setErrorCode(errorCode);
    }

    public NodeIdNotPresentException(ErrorCodes errorCode, String message, Throwable cause) {
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
