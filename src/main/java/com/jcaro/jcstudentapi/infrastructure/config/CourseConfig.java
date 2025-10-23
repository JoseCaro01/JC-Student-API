package com.jcaro.jcstudentapi.infrastructure.config;

import com.jcaro.jcstudentapi.application.mapper.AssignmentMapper;
import com.jcaro.jcstudentapi.application.mapper.CourseMapper;
import com.jcaro.jcstudentapi.application.mapper.ProjectMapper;
import com.jcaro.jcstudentapi.application.usecase.course.*;
import com.jcaro.jcstudentapi.domain.repository.AssignmentRepository;
import com.jcaro.jcstudentapi.domain.repository.CourseRepository;
import com.jcaro.jcstudentapi.domain.repository.ProjectRepository;
import com.jcaro.jcstudentapi.domain.repository.StudentRepository;
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
    public GetCourseByIdUseCase getCourseByIdUseCase(CourseRepository courseRepository) {
        return new GetCourseByIdUseCase(courseRepository);
    }

    @Bean
    public GetDetailedCourseUseCase getDetailedCourseUseCase(CourseRepository courseRepository, StudentRepository studentRepository, AssignmentRepository assignmentRepository, ProjectRepository projectRepository, CourseMapper courseMapper, AssignmentMapper assignmentMapper, ProjectMapper projectMapper) {
        return new GetDetailedCourseUseCase(courseRepository,studentRepository,assignmentRepository,projectRepository, courseMapper,assignmentMapper,projectMapper);
    }

    @Bean
    public RemoveCourseUseCase removeCourseUseCase(CourseRepository courseRepository) {
        return new RemoveCourseUseCase(courseRepository);
    }
}