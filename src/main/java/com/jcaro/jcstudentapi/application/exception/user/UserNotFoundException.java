package com.jcaro.jcstudentapi.application.exception.user;

import com.jcaro.jcstudentapi.application.exception.BusinessException;
import com.jcaro.jcstudentapi.application.exception.ErrorCode;

/**
 * Exception thrown when a user is not found in the system
 * for a given identifier.
 */
public class UserNotFoundException extends BusinessException {
    public UserNotFoundException() {
        super("User not found", ErrorCode.NOT_FOUND);
    }
}
