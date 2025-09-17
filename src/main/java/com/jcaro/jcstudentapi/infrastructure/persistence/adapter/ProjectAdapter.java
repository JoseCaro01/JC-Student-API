package com.jcaro.jcstudentapi.infrastructure.persistence.adapter;

import com.jcaro.jcstudentapi.domain.model.Project;
import com.jcaro.jcstudentapi.domain.repository.ProjectRepository;
import com.jcaro.jcstudentapi.infrastructure.mapper.ProjectEntityMapper;
import com.jcaro.jcstudentapi.infrastructure.persistence.entity.ProjectEntity;
import com.jcaro.jcstudentapi.infrastructure.persistence.repository.ProjectRepositoryJPA;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Adapter that implements the domain's ProjectRepository interface.
 * It translates between the domain model (Project) and the JPA entity (ProjectEntity)
 * and uses the Spring Data JPA repository for persistence.
 */
@Repository
public class ProjectAdapter implements ProjectRepository {

    private final ProjectRepositoryJPA projectJpaRepository;
    private final ProjectEntityMapper mapper;

    public ProjectAdapter(ProjectRepositoryJPA projectJpaRepository, ProjectEntityMapper mapper) {
        this.projectJpaRepository = projectJpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Project save(Project project) {
        ProjectEntity entity = mapper.toEntity(project);
        ProjectEntity savedEntity = projectJpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Project> findById(Long id) {
        return projectJpaRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public List<Project> findAll() {
        return projectJpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Project> findByCourseId(Long courseId) {
        return projectJpaRepository.findByCourseId(courseId).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        projectJpaRepository.deleteById(id);
    }
}
