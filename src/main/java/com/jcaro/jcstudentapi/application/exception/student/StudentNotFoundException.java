package com.jcaro.jcstudentapi.application.exception.student;

import com.jcaro.jcstudentapi.application.exception.BusinessException;
import com.jcaro.jcstudentapi.application.exception.ErrorCode;

public class StudentNotFoundException  extends BusinessException{
    public StudentNotFoundException(Long id) {
        super("Student with id "+ id +  " not found", ErrorCode.NOT_FOUND);
    }
}
