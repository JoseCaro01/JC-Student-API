package com.jcaro.jcstudentapi.application.usecase.project;

import com.jcaro.jcstudentapi.application.dto.project.ProjectRequest;
import com.jcaro.jcstudentapi.application.exception.assignment.AssignmentNotFoundException;
import com.jcaro.jcstudentapi.application.exception.course.CourseNotFoundException;
import com.jcaro.jcstudentapi.application.exception.project.ProjectNotFoundException;
import com.jcaro.jcstudentapi.application.mapper.ProjectMapper;
import com.jcaro.jcstudentapi.domain.model.Course;
import com.jcaro.jcstudentapi.domain.model.Project;
import com.jcaro.jcstudentapi.domain.repository.CourseRepository;
import com.jcaro.jcstudentapi.domain.repository.ProjectRepository;

/**
 * Use case for updating a Project.
 */
public class EditProjectUseCase {

    private final ProjectRepository projectRepository;
    private final CourseRepository courseRepository;
    private final ProjectMapper projectMapper;

    public EditProjectUseCase(ProjectRepository projectRepository, CourseRepository courseRepository, ProjectMapper projectMapper) {
        this.projectRepository = projectRepository;
        this.courseRepository = courseRepository;
        this.projectMapper = projectMapper;
    }

    /**
     * Updates a project after validating existence of both Project and Course.
     *
     * @param project the project to update
     * @return the updated project
     * @throws CourseNotFoundException if the course does not exist
     * @throws ProjectNotFoundException if the project does not exist
     */
    public Project execute(Long id, ProjectRequest project) {

        if (projectRepository.findById(id).isEmpty()) {
            throw new ProjectNotFoundException(id);
        }
        if (project.courseId() == null) {
            throw new CourseNotFoundException(-1L);
        }

        final Course course = courseRepository.findById(project.courseId()).orElseThrow(() -> new CourseNotFoundException(project.courseId()));

        return projectRepository.save(projectMapper.requestToDomain(project, course).withId(id));
    }
}
