package com.jcaro.jcstudentapi.application.service;

import com.itextpdf.text.DocumentException;
import com.jcaro.jcstudentapi.domain.model.Student;
import com.jcaro.jcstudentapi.domain.model.StudentProject;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

public interface ProjectPdfGeneratorService  {

    public ByteArrayInputStream generate(Student student, List<StudentProject> studentProjects) throws DocumentException, IOException;

}
