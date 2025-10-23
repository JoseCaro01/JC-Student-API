package com.jcaro.jcstudentapi.application.dto.student;

import com.jcaro.jcstudentapi.application.dto.course.CourseDetailedResponse;
import com.jcaro.jcstudentapi.application.dto.studentAssignment.StudentAssignmentResponse;
import com.jcaro.jcstudentapi.application.dto.studentProject.StudentProjectResponse;
import com.jcaro.jcstudentapi.domain.model.Course;
import com.jcaro.jcstudentapi.domain.model.StudentAssignment;
import com.jcaro.jcstudentapi.domain.model.StudentProject;

import java.util.List;


/**
 * Data Transfer Object (DTO) for get Detailed Student.
 * <p>
 * This class belongs to the Application layer and defines the data structure
 * that the API clients must provide when sending student-related requests.
 * */
public record StudentDetailedResponse(

        Long id,

        String name,

        String email,

        List<Course> courses,

        CourseDetailedResponse detailedCourse,

        List<StudentAssignmentResponse> studentAssignment,

        List<StudentProjectResponse> studentProject


) {
}

