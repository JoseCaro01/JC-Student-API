package com.jcaro.jcstudentapi.application.usecase.course;

import com.jcaro.jcstudentapi.application.exception.course.CourseNotFoundException;
import com.jcaro.jcstudentapi.domain.repository.CourseRepository;

import java.util.NoSuchElementException;

/**
 * Use case to remove a course by id.
 * <p>
 * Checks if the course exists in the repository before deletion.
 */
public class RemoveCourseUseCase {

    private final CourseRepository courseRepository;


    public RemoveCourseUseCase(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    /**
     * Executes the removal of the course with the given id.
     *
     * @param id the id of the course to remove
     * @throws CourseNotFoundException if the course does not exist
     */
    public void execute(Long id) {
        if (courseRepository.findById(id).isEmpty()) {
            throw new CourseNotFoundException(id);
        }
        courseRepository.deleteById(id);
    }
}
