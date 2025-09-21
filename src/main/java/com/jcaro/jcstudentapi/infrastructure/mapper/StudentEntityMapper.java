package com.jcaro.jcstudentapi.infrastructure.mapper;

import com.jcaro.jcstudentapi.infrastructure.persistence.entity.StudentEntity;
import com.jcaro.jcstudentapi.domain.model.Student;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * Manual mapper between StudentEntity (JPA) and Student (domain).
 * Keeps mapping minimal to core fields to avoid cycles; lists are initialized as empty.
 */
@Component
public class StudentEntityMapper {

    private final CourseEntityMapper courseEntityMapper;

    /**
     * Constructor that injects CourseEntityMapper to map
     * Course objects within Student.
     *
     * @param courseEntityMapper mapper to convert CourseEntity to Course
     */
    public StudentEntityMapper(CourseEntityMapper courseEntityMapper) {
        this.courseEntityMapper = courseEntityMapper;
    }

    /**
     * Converts between StudentEntity (JPA) and Student (domain).
     *
     * <p>This mapper handles the conversion of nested collections such as courses,
     * student assignments, and student projects using their respective mappers.
     * It ensures that the domain model remains independent of persistence concerns.</p>
     */
    public Student toDomain(StudentEntity entity) {
        if (entity == null) return null;

        return new Student(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getCourses().stream().map(courseEntityMapper::toDomain).toList()
        );

    }

    /**
     * Converts a Student domain model to a StudentEntity.
     *
     * <p>Maps nested collections of courses, student assignments, and student projects
     * to their corresponding entities using their respective mappers.</p>
     *
     * @param domain the Student domain model to convert
     * @return the corresponding StudentEntity, or null if the domain model is null
     */
    public StudentEntity toEntity(Student domain) {
        if (domain == null) return null;

        StudentEntity e = new StudentEntity();
        e.setId(domain.id());
        e.setName(domain.name());
        e.setEmail(domain.email());
        e.setCourses(domain.courses().stream().map(courseEntityMapper::toEntity).toList());
         return e;
    }

}
