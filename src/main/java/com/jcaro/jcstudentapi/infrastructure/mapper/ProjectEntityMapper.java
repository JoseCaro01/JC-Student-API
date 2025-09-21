package com.jcaro.jcstudentapi.infrastructure.mapper;

import com.jcaro.jcstudentapi.domain.model.Project;
import com.jcaro.jcstudentapi.domain.model.Course;
import com.jcaro.jcstudentapi.domain.model.StudentProject;
import com.jcaro.jcstudentapi.infrastructure.persistence.entity.ProjectEntity;
import com.jcaro.jcstudentapi.infrastructure.persistence.entity.CourseEntity;
import com.jcaro.jcstudentapi.infrastructure.persistence.entity.StudentProjectEntity;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Maps between ProjectEntity (JPA) and Project (domain).
 * This mapper ensures the domain model stays free of persistence concerns.
 */
@Component
public class ProjectEntityMapper {

    private final CourseEntityMapper courseEntityMapper;

    /**
     * Constructor that injects CourseEntityMapper to map
     * Course objects within Assignment.
     *
     * @param courseEntityMapper mapper to convert CourseEntity to Course
     */
    public ProjectEntityMapper(CourseEntityMapper courseEntityMapper) {
        this.courseEntityMapper = courseEntityMapper;
    }

    /**
     * Converts a ProjectEntity to a Project domain model.
     *
     * @param entity the ProjectEntity to convert
     * @return the corresponding Project domain model
     */
    public Project toDomain(ProjectEntity entity) {
        if (entity == null) {
            return null;
        }

        return new Project(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                courseEntityMapper.toDomain(entity.getCourse())
        );
    }

    /**
     * Converts a Project domain model to a ProjectEntity.
     *
     * @param domain the Project domain model to convert
     * @return the corresponding ProjectEntity
     */
    public ProjectEntity toEntity(Project domain) {
        if (domain == null) {
            return null;
        }

        ProjectEntity entity = new ProjectEntity();
        entity.setId(domain.id());
        entity.setName(domain.name());
        entity.setDescription(domain.description());
        entity.setCourse(courseEntityMapper.toEntity(domain.course()));

        return entity;
    }


}
