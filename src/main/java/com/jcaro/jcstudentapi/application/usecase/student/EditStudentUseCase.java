package com.jcaro.jcstudentapi.application.usecase.student;

import com.jcaro.jcstudentapi.application.dto.student.StudentRequest;
import com.jcaro.jcstudentapi.application.exception.course.CourseNotFoundException;
import com.jcaro.jcstudentapi.application.exception.student.StudentNotFoundException;
import com.jcaro.jcstudentapi.application.mapper.StudentMapper;
import com.jcaro.jcstudentapi.domain.model.Course;
import com.jcaro.jcstudentapi.domain.model.Student;
import com.jcaro.jcstudentapi.domain.repository.CourseRepository;
import com.jcaro.jcstudentapi.domain.repository.StudentRepository;

import java.util.List;

/**
 * Use case for editing an existing Student.
 * <p>
 * Ensures that the student exists before updating, and validates that all
 * provided course IDs are valid. Courses are fully resolved from the repository
 * before persisting the updated student.
 */
public class EditStudentUseCase {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final StudentMapper studentMapper;

    public EditStudentUseCase(StudentRepository studentRepository,
                              CourseRepository courseRepository,
                              StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.studentMapper = studentMapper;
    }

    /**
     * Updates an existing student.
     *
     * @param id      the ID of the student to update
     * @param request the DTO containing updated student data
     * @return the updated and persisted {@link Student}
     * @throws StudentNotFoundException if the student does not exist
     * @throws CourseNotFoundException  if one or more provided courses do not exist
     */
    public Student execute(Long id, StudentRequest request) {
        // Validate existence of the student
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));

        // Resolve courses
        List<Course> courses = request.coursesId().stream()
                .map(courseId -> courseRepository.findById(courseId)
                        .orElseThrow(() -> new CourseNotFoundException(courseId)))
                .toList();

        // Map new values to domain, reusing existing student ID
        Student updatedStudent = studentMapper.requestToDomain(request,courses,existingStudent.assignments(),existingStudent.projects())
                .withId(existingStudent.id());

        // Persist and return
        return studentRepository.save(updatedStudent);
    }
}
