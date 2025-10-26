package com.jcaro.jcstudentapi.application.usecase.course;


import com.jcaro.jcstudentapi.application.exception.course.CourseNotFoundException;
import com.jcaro.jcstudentapi.domain.model.Course;
import com.jcaro.jcstudentapi.domain.repository.CourseRepository;

/**
 * Use case for retrieving a Course by its id.
 */
public class GetCourseByIdUseCase {

    private final CourseRepository courseRepository;

    public GetCourseByIdUseCase(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    /**
     * Executes the retrieval of a course by id.
     *
     * @param id the course id
     * @return the course entity
     * @throws CourseNotFoundException if project does not exist
     */
    public Course execute(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException(id));
    }
}
