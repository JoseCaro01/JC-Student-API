package com.jcaro.jcstudentapi.infrastructure.controller;

import com.jcaro.jcstudentapi.application.dto.project.ProjectRequest;
import com.jcaro.jcstudentapi.application.usecase.project.CreateProjectUseCase;
import com.jcaro.jcstudentapi.application.usecase.project.EditProjectUseCase;
import com.jcaro.jcstudentapi.application.usecase.project.GetAllProjectsUseCase;
import com.jcaro.jcstudentapi.application.usecase.project.RemoveProjectUseCase;
import com.jcaro.jcstudentapi.domain.model.Project;

import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final CreateProjectUseCase createProjectUseCase;
    private final EditProjectUseCase editProjectUseCase;
    private final GetAllProjectsUseCase getAllProjectsUseCase;
    private final RemoveProjectUseCase removeProjectUseCase;

    public ProjectController(CreateProjectUseCase createProjectUseCase,
                             EditProjectUseCase editProjectUseCase,
                             GetAllProjectsUseCase getAllProjectsUseCase,
                             RemoveProjectUseCase removeProjectUseCase) {
        this.createProjectUseCase = createProjectUseCase;
        this.editProjectUseCase = editProjectUseCase;
        this.getAllProjectsUseCase = getAllProjectsUseCase;
        this.removeProjectUseCase = removeProjectUseCase;
    }

    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody ProjectRequest project) {
        return new ResponseEntity<>(createProjectUseCase.execute(project),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Project> editProject(@PathVariable Long id, @RequestBody ProjectRequest project) {
        return new ResponseEntity<>(editProjectUseCase.execute(id,project),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Project>> getAllProjects(@RequestParam(name = "courseId", required = false) Long courseId) {
        return new ResponseEntity<>(getAllProjectsUseCase.execute(courseId),HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        removeProjectUseCase.execute(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
