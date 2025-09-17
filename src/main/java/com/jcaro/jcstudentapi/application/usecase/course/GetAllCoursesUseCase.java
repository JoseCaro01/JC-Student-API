package com.jcaro.jcstudentapi.application.usecase.course;

import com.jcaro.jcstudentapi.domain.model.Course;
import com.jcaro.jcstudentapi.domain.repository.CourseRepository;

import java.util.List;

/**
 * Use case to retrieve all courses.
 * <p>
 * This class encapsulates the business logic for getting all courses
 * from the repository. It does not perform any validation or
 * transformation; it simply delegates to the CourseRepository.
 */
public class GetAllCoursesUseCase {

    private final CourseRepository courseRepository;

    public  GetAllCoursesUseCase(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    /**
     * Executes the use case to retrieve all courses.
     *
     * @return a list of all courses
     */
    public List<Course> execute() {
        return courseRepository.findAll();
    }
}
