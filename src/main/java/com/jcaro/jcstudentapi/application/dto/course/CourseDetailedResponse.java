package com.jcaro.jcstudentapi.application.dto.course;


import com.jcaro.jcstudentapi.application.dto.assignment.AssignmentResponse;
import com.jcaro.jcstudentapi.application.dto.project.ProjectResponse;
import com.jcaro.jcstudentapi.domain.model.Assignment;
import com.jcaro.jcstudentapi.domain.model.Project;
import com.jcaro.jcstudentapi.domain.model.Student;

import java.util.List;

/**
 * Data Transfer Object (DTO) for get Detailed Course.
 * <p>
 * This class belongs to the Application layer and defines the data structure
 * that the API clients must provide when sending course-related requests.
 * */
public record CourseDetailedResponse(
        Long id,
        String name,
        String programmingLanguage,
        List<Student> students,
        List<AssignmentResponse> assignments,
        List<ProjectResponse> projects
) {
}
