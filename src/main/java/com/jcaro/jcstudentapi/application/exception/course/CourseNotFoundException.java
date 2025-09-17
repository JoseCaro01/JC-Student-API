package com.jcaro.jcstudentapi.application.exception.course;

import com.jcaro.jcstudentapi.application.exception.BusinessException;
import com.jcaro.jcstudentapi.application.exception.ErrorCode;

/**
 * Exception thrown when a Course is not found in the system
 * for a given identifier.
 */
public class CourseNotFoundException extends BusinessException {

    public CourseNotFoundException(Long id) {
        super("Course with id "+ id +  " not found", ErrorCode.NOT_FOUND);
    }
}
