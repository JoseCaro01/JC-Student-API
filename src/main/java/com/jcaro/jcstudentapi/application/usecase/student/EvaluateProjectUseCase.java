package com.jcaro.jcstudentapi.application.usecase.student;

import com.jcaro.jcstudentapi.application.dto.studentProject.StudentProjectRequest;
import com.jcaro.jcstudentapi.application.exception.project.ProjectNotFoundException;
import com.jcaro.jcstudentapi.application.exception.student.StudentNotFoundException;
import com.jcaro.jcstudentapi.domain.model.Project;
import com.jcaro.jcstudentapi.domain.model.ScoreEnum;
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
     * @throws StudentNotFoundException if student don't exist
     * @throws ProjectNotFoundException if project don't exist
     */
    public void execute(Long studentId, Long projectId, StudentProjectRequest scores) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException(studentId));

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException(projectId));
        final ScoreEnum codeQuality = ScoreEnum.fromValue(scores.codeQuality());
        final ScoreEnum functionality = ScoreEnum.fromValue(scores.functionality());
        final ScoreEnum security = ScoreEnum.fromValue(scores.security());
        final ScoreEnum documentation = ScoreEnum.fromValue(scores.documentation());
        final ScoreEnum deployment = ScoreEnum.fromValue(scores.deployment());
        final ScoreEnum extra = ScoreEnum.fromValue(scores.extra());

        StudentProject studentProject = studentProjectRepository
                .findByStudentIdAndProjectId(studentId, projectId)
                .orElse(new StudentProject(null, student, project,
                        codeQuality, functionality, security, documentation, deployment, extra
                )).withCodeQuality(codeQuality)
                .withFunctionality(functionality)
                .withSecurity(security)
                .withDocumentation(documentation)
                .withDeployment(deployment)
                .withExtra(extra);

        studentProjectRepository.save(studentProject);
    }
}
