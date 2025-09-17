package com.jcaro.jcstudentapi.infrastructure.config;

import com.jcaro.jcstudentapi.application.mapper.AssignmentMapper;
import com.jcaro.jcstudentapi.application.mapper.CourseMapper;
import com.jcaro.jcstudentapi.application.usecase.course.CreateCourseUseCase;
import com.jcaro.jcstudentapi.application.usecase.course.EditCourseUseCase;
import com.jcaro.jcstudentapi.application.usecase.course.GetAllCoursesUseCase;
import com.jcaro.jcstudentapi.application.usecase.course.RemoveCourseUseCase;
import com.jcaro.jcstudentapi.domain.repository.CourseRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CourseConfig {

    @Bean
    public CourseMapper courseMapper() {
        return new CourseMapper();
    }

    @Bean
    public CreateCourseUseCase createCourseUseCase(CourseRepository courseRepository, CourseMapper courseMapper) {
        return new CreateCourseUseCase(courseRepository, courseMapper);
    }

    @Bean
    public EditCourseUseCase editCourseUseCase(CourseRepository courseRepository, CourseMapper courseMapper) {
        return new EditCourseUseCase(courseRepository, courseMapper);
    }

    @Bean
    public GetAllCoursesUseCase getAllCoursesUseCase(CourseRepository courseRepository) {
        return new GetAllCoursesUseCase(courseRepository);
    }

    @Bean
    public RemoveCourseUseCase removeCourseUseCase(CourseRepository courseRepository) {
        return new RemoveCourseUseCase(courseRepository);
    }
}