package com.jcaro.jcstudentapi.application.exception;

/**
 * Enum representing business error codes for API responses.
 * These are mapped to HTTP status codes by the global exception handler.
 */

public enum ErrorCode {
    BAD_REQUEST(400),
    NOT_FOUND(404),
    FORBIDDEN(403);

    ErrorCode(int code) {

    }


}
