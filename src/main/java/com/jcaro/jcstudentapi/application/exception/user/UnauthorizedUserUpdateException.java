package com.jcaro.jcstudentapi.application.exception.user;

import com.jcaro.jcstudentapi.application.exception.BusinessException;
import com.jcaro.jcstudentapi.application.exception.ErrorCode;

/**
 * Exception thrown when a user tries to update another user
 * without the proper permissions.
 */
public class UnauthorizedUserUpdateException extends BusinessException {

    public UnauthorizedUserUpdateException() {
        super("You are not authorized to update this user", ErrorCode.FORBIDDEN);
    }
}
