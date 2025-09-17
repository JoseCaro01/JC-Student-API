package com.jcaro.jcstudentapi.application.exception.project;

import com.jcaro.jcstudentapi.application.exception.BusinessException;
import com.jcaro.jcstudentapi.application.exception.ErrorCode;

public class ProjectNotFoundException extends BusinessException {

    public ProjectNotFoundException(Long id) {
        super("Project with id "+ id +  " not found", ErrorCode.NOT_FOUND);
    }
}

