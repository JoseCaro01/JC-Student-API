package com.jcaro.jcstudentapi.application.usecase.course;

import com.jcaro.jcstudentapi.application.dto.course.CourseRequest;
import com.jcaro.jcstudentapi.application.mapper.CourseMapper;
import com.jcaro.jcstudentapi.domain.model.Course;
import com.jcaro.jcstudentapi.domain.repository.CourseRepository;

/**
 * Use case to create a new course.
 * <p>
 * Validates and persists a new Course entity in the repository.
 */
public class CreateCourseUseCase {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;


    public CreateCourseUseCase(CourseRepository courseRepository,CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

    /**
     * Executes the creation of a new course.
     *
     * @param course the course to create
     * @return the saved course with generated id
     */
    public Course execute(CourseRequest course) {
        return courseRepository.save(courseMapper.requestToDomain(course));
    }
}
