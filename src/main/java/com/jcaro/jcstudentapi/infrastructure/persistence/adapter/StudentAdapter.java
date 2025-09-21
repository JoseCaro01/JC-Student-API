package com.jcaro.jcstudentapi.infrastructure.persistence.adapter;

import com.jcaro.jcstudentapi.domain.model.Student;
import com.jcaro.jcstudentapi.domain.repository.StudentRepository;
import com.jcaro.jcstudentapi.infrastructure.mapper.StudentEntityMapper;
import com.jcaro.jcstudentapi.infrastructure.persistence.entity.StudentEntity;
import com.jcaro.jcstudentapi.infrastructure.persistence.repository.StudentRepositoryJPA;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Adapter that implements the domain's StudentRepository interface.
 * It translates between the domain model (Student) and the JPA entity (StudentEntity)
 * and uses the Spring Data JPA repository for persistence.
 */
@Repository
public class StudentAdapter implements StudentRepository {

    private final StudentRepositoryJPA studentRepositoryJPA;
    private final StudentEntityMapper mapper;

    public StudentAdapter(StudentRepositoryJPA studentRepositoryJPA, StudentEntityMapper mapper) {
        this.studentRepositoryJPA = studentRepositoryJPA;
        this.mapper = mapper;
    }

    @Override
    public Student save(Student student) {
        StudentEntity entity = mapper.toEntity(student);
        StudentEntity savedEntity = studentRepositoryJPA.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Student> findById(Long id) {
        return studentRepositoryJPA.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public List<Student> findAll() {
        return studentRepositoryJPA.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Student> findByCourseId(Long courseId) {
        return studentRepositoryJPA.findByCoursesId(courseId).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Student> findByAssignmentId(Long assignmentId) {
        return studentRepositoryJPA.findByAssignmentId(assignmentId).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Student> findByProjectId(Long projectId) {
        return studentRepositoryJPA.findByProjectId(projectId).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        studentRepositoryJPA.deleteById(id);
    }
}
