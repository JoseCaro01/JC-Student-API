package com.jcaro.jcstudentapi.application.usecase.student;

import com.jcaro.jcstudentapi.application.dto.student.StudentEvaluateProjectRequest;
import com.jcaro.jcstudentapi.application.exception.project.ProjectNotFoundException;
import com.jcaro.jcstudentapi.application.exception.student.StudentNotFoundException;
import com.jcaro.jcstudentapi.domain.model.Project;
import com.jcaro.jcstudentapi.domain.model.Student;
import com.jcaro.jcstudentapi.domain.model.StudentProject;
import com.jcaro.jcstudentapi.domain.repository.ProjectRepository;
import com.jcaro.jcstudentapi.domain.repository.StudentProjectRepository;
import com.jcaro.jcstudentapi.domain.repository.StudentRepository;

/**
 * Use case for evaluating a project for a student.
 */
public class EvaluateProjectUseCase {

    private final StudentRepository studentRepository;
    private final ProjectRepository projectRepository;
    private final StudentProjectRepository studentProjectRepository;

    public EvaluateProjectUseCase(StudentRepository studentRepository,
                                  ProjectRepository projectRepository,
                                  StudentProjectRepository studentProjectRepository) {
        this.studentRepository = studentRepository;
        this.projectRepository = projectRepository;
        this.studentProjectRepository = studentProjectRepository;
    }

    /**
     * Executes the evaluation of a student for a project.
     *
     * @param studentId the student id
     * @param projectId the project id
     * @param scores    the set of project scores
     *
     * @throws StudentNotFoundException if student don't exist
     * @throws ProjectNotFoundException if project don't exist
     */
    public void execute(Long studentId, Long projectId, StudentEvaluateProjectRequest scores) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException(studentId));

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException(projectId));

        StudentProject studentProject = studentProjectRepository
                .findByStudentIdAndProjectId(studentId, projectId)
                .orElse(new StudentProject(null ,student, project,
                        scores.codeQuality(),
                        scores.functionality(),
                        scores.security(),
                        scores.documentation(),
                        scores.deployment(),
                        scores.extra()
                ));

        studentProjectRepository.save(studentProject);
    }
}
