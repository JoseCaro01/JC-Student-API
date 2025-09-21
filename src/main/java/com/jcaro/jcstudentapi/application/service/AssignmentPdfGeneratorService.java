package com.jcaro.jcstudentapi.application.service;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.List;
import com.jcaro.jcstudentapi.domain.model.Assignment;
import com.jcaro.jcstudentapi.domain.model.Course;
import com.jcaro.jcstudentapi.domain.model.Student;
import com.jcaro.jcstudentapi.domain.model.StudentAssignment;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public interface AssignmentPdfGeneratorService {

    public  ByteArrayInputStream generate(Student student, java.util.List<Assignment> courseAssignment, java.util.List<StudentAssignment> studentAssignment) throws DocumentException, IOException;

}
