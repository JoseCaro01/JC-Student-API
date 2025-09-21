package com.jcaro.jcstudentapi.infrastructure.persistence.adapter;

import com.jcaro.jcstudentapi.domain.model.StudentProject;
import com.jcaro.jcstudentapi.domain.repository.StudentProjectRepository;
import com.jcaro.jcstudentapi.infrastructure.mapper.StudentProjectEntityMapper;
import com.jcaro.jcstudentapi.infrastructure.persistence.entity.StudentProjectEntity;
import com.jcaro.jcstudentapi.infrastructure.persistence.repository.StudentProjectRepositoryJPA;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Adapter that implements the domain's StudentProjectRepository interface.
 * It translates between the domain model (StudentProject) and the JPA entity (StudentProjectEntity)
 * and uses the Spring Data JPA repository for persistence.
 */
@Repository
public class StudentProjectAdapter implements StudentProjectRepository {

    private final StudentProjectRepositoryJPA studentProjectJpaRepository;
    private final StudentProjectEntityMapper mapper;

    public StudentProjectAdapter(StudentProjectRepositoryJPA studentProjectJpaRepository,
                                 StudentProjectEntityMapper mapper) {
        this.studentProjectJpaRepository = studentProjectJpaRepository;
        this.mapper = mapper;
    }

    @Override
    public StudentProject save(StudentProject studentProject) {
        StudentProjectEntity entity = mapper.toEntity(studentProject);
        StudentProjectEntity savedEntity = studentProjectJpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<StudentProject> findById(Long id) {
        return studentProjectJpaRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public List<StudentProject> findAll() {
        return studentProjectJpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<StudentProject> findByStudentIdAndProjectId(Long studentId, Long projectId) {
        return studentProjectJpaRepository.findByStudentIdAndProjectId(studentId, projectId)
                .map(mapper::toDomain);
    }

    @Override
    public List<StudentProject> findByStudentIdAndCourseId(Long studentId, Long courseId) {
        return studentProjectJpaRepository.findByStudentIdAndProjectCourseId(studentId, courseId).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        studentProjectJpaRepository.deleteById(id);
    }
}
