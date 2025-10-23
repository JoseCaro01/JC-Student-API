package com.jcaro.jcstudentapi.application.mapper;

import com.jcaro.jcstudentapi.application.dto.assignment.AssignmentResponse;
import com.jcaro.jcstudentapi.application.dto.course.CourseDetailedResponse;
import com.jcaro.jcstudentapi.application.dto.project.ProjectResponse;
import com.jcaro.jcstudentapi.application.dto.student.StudentDetailedResponse;
import com.jcaro.jcstudentapi.application.dto.student.StudentRequest;
import com.jcaro.jcstudentapi.application.dto.studentAssignment.StudentAssignmentResponse;
import com.jcaro.jcstudentapi.application.dto.studentProject.StudentProjectResponse;
import com.jcaro.jcstudentapi.domain.model.Course;
import com.jcaro.jcstudentapi.domain.model.Student;
import com.jcaro.jcstudentapi.domain.model.StudentAssignment;
import com.jcaro.jcstudentapi.domain.model.StudentProject;

import java.util.Collections;
import java.util.List;

/**
 * Mapper for converting between StudentRequest DTOs and the Student domain model.
 * <p>
 * This class resides in the Application layer and ensures separation of concerns
 * by translating data between the API layer and the domain model, without using
 * any Spring dependencies.
 */
public class StudentMapper {

    /**
     * Converts a StudentRequest DTO into a Student domain object.
     * <p>
     * The ID is set to null since it will be generated in the persistence layer.
     * Courses are not mapped here because only their IDs are provided. The actual
     * resolution of course entities must be handled in the Application use case
     * (via CourseRepository).
     *
     * @param dto the StudentRequest DTO containing input data
     * @return a new Student domain object with basic information
     */
    public Student requestToDomain(StudentRequest dto) {
        return new Student(
                null,
                dto.name(),
                dto.email(),
                Collections.emptyList()
        );
    }

    /**
     * Converts a Course domain object into a CourseDetailedResponse DTO.
     * <p>
     *
     * @param student                    the Student domain object
     * @param courseDetailedResponses    the detailed Course DTO object
     * @param studentAssignmentResponses the studentAssignment list DTO object
     * @param studentProjectResponses    the studentProject list DTO object
     * @return a StudentDetailedResponse DTO for returning to API clients
     */
    public StudentDetailedResponse domainToStudentDetailedResponse(Student student, CourseDetailedResponse courseDetailedResponses, List<StudentAssignmentResponse> studentAssignmentResponses, List<StudentProjectResponse> studentProjectResponses) {
        return new StudentDetailedResponse(
                student.id(),
                student.name(),
                student.email(),
                student.courses(),
                courseDetailedResponses,
                studentAssignmentResponses,
                studentProjectResponses
        );
    }
}
