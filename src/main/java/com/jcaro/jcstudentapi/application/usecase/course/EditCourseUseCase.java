package com.jcaro.jcstudentapi.application.usecase.course;

import com.jcaro.jcstudentapi.application.dto.course.CourseRequest;
import com.jcaro.jcstudentapi.application.exception.course.CourseNotFoundException;
import com.jcaro.jcstudentapi.application.mapper.CourseMapper;
import com.jcaro.jcstudentapi.domain.model.Course;
import com.jcaro.jcstudentapi.domain.repository.CourseRepository;

import java.util.NoSuchElementException;

/**
 * Use case to edit an existing course.
 * <p>
 * Checks if the course exists and updates its data in the repository.
 */
public class EditCourseUseCase {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;


    public EditCourseUseCase(CourseRepository courseRepository,CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }
    /**
     * Executes the edit operation on the given course.
     *
     * @param course the course with updated data
     * @return the updated course entity
     * @throws CourseNotFoundException if the course does not exist
     */
    public Course execute(Long id, CourseRequest course) {
        if (courseRepository.findById(id).isEmpty()) {
            throw new CourseNotFoundException(id);
        }
        return courseRepository.save(courseMapper.requestToDomain(course).withId(id));
    }
}
