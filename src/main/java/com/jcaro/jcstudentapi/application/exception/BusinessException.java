package com.jcaro.jcstudentapi.application.exception;

/**
 * Base class for all business exceptions.
 * Use for domain/application exceptions that should result in a controlled error response.
 */
public abstract class BusinessException extends RuntimeException {
    private final ErrorCode code;

    public BusinessException(String message, ErrorCode code) {
        super(message);
        this.code = code;
    }

    public ErrorCode getCode() {
        return code;
    }
}
