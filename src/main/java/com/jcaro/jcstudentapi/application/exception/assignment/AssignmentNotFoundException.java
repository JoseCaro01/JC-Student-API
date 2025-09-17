package com.jcaro.jcstudentapi.application.exception.assignment;

import com.jcaro.jcstudentapi.application.exception.BusinessException;
import com.jcaro.jcstudentapi.application.exception.ErrorCode;

/**
 * Exception thrown when a Assignment is not found in the system
 * for a given identifier.
 */
public class AssignmentNotFoundException extends BusinessException {

    public AssignmentNotFoundException(Long id) {
        super("Assignment with id "+ id +  " not found", ErrorCode.NOT_FOUND);
    }

}
