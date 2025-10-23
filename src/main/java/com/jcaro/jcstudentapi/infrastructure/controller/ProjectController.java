package com.jcaro.jcstudentapi.infrastructure.controller;

import com.jcaro.jcstudentapi.application.dto.project.ProjectRequest;
import com.jcaro.jcstudentapi.application.dto.project.ProjectResponse;
import com.jcaro.jcstudentapi.application.usecase.project.*;
import com.jcaro.jcstudentapi.domain.model.Project;

import org.springframework.data.repository.query.Param; // Nota: @Param es para interfaces Repository, se reemplaza con @RequestParam en Controllers.
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing projects.
 * <p>
 * This controller acts as the inbound adapter for the Project aggregate,
 * exposing HTTP endpoints for project-related CRUD (Create, Read, Update, Delete) operations.
 * It delegates all business logic to the corresponding use cases in the Application layer.
 */
@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final CreateProjectUseCase createProjectUseCase;
    private final EditProjectUseCase editProjectUseCase;
    private final GetAllProjectsUseCase getAllProjectsUseCase;
    private final GetProjectByIdUseCase getProjectByIdUseCase;
    private final RemoveProjectUseCase removeProjectUseCase;


    public ProjectController(CreateProjectUseCase createProjectUseCase,
                             EditProjectUseCase editProjectUseCase,
                             GetAllProjectsUseCase getAllProjectsUseCase,
                             GetProjectByIdUseCase getProjectByIdUseCase,
                             RemoveProjectUseCase removeProjectUseCase) {
        this.createProjectUseCase = createProjectUseCase;
        this.editProjectUseCase = editProjectUseCase;
        this.getAllProjectsUseCase = getAllProjectsUseCase;
        this.getProjectByIdUseCase = getProjectByIdUseCase;
        this.removeProjectUseCase = removeProjectUseCase;
    }

    /**
     * Creates a new project.
     *
     * @param project The request payload containing project details ({@link ProjectRequest}).
     * @return The created project details ({@link ProjectResponse}) with HTTP status 201 (Created).
     */
    @PostMapping
    public ResponseEntity<ProjectResponse> createProject(@RequestBody ProjectRequest project) {
        return new ResponseEntity<>(createProjectUseCase.execute(project), HttpStatus.CREATED);
    }

    /**
     * Updates an existing project by its ID.
     *
     * @param id The ID of the project to update.
     * @param project The request payload containing updated project details ({@link ProjectRequest}).
     * @return The updated project details ({@link ProjectResponse}) with HTTP status 200 (OK).
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProjectResponse> editProject(@PathVariable Long id, @RequestBody ProjectRequest project) {
        return new ResponseEntity<>(editProjectUseCase.execute(id, project), HttpStatus.OK);
    }

    /**
     * Retrieves a list of projects, optionally filtered by the associated course ID.
     *
     * @param courseId The optional ID of the course to filter projects by.
     * @return A list of project details ({@link ProjectResponse}) with HTTP status 200 (OK).
     */
    @GetMapping
    public ResponseEntity<List<ProjectResponse>> getAllProjects(@RequestParam(name = "courseId", required = false) Long courseId) {
        return new ResponseEntity<>(getAllProjectsUseCase.execute(courseId), HttpStatus.OK);
    }

    /**
     * Retrieves a single project by its ID.
     *
     * @param id The ID of the project to obtain.
     * @return The project details ({@link ProjectResponse}) with HTTP status 200 (OK).
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponse> getProject(@PathVariable Long id) {
        return new ResponseEntity<>(getProjectByIdUseCase.execute(id), HttpStatus.OK);
    }

    /**
     * Removes a project by its ID.
     *
     * @param id The ID of the project to remove.
     * @return {@link HttpStatus#NO_CONTENT} if deletion was successful.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        removeProjectUseCase.execute(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}