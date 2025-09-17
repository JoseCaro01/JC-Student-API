package com.jcaro.jcstudentapi.application.exception.user;

import com.jcaro.jcstudentapi.application.exception.BusinessException;
import com.jcaro.jcstudentapi.application.exception.ErrorCode;

/**
 * Exception thrown when trying to create a User with an email
 * that already exists in the system.
 */
public class EmailAlreadyExistsException extends BusinessException {
    public EmailAlreadyExistsException(String email) {
        super("Email already exists: " + email, ErrorCode.BAD_REQUEST);
    }
}
