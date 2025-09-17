package com.jcaro.jcstudentapi.infrastructure.controller;

import com.jcaro.jcstudentapi.application.dto.course.CourseRequest;
import com.jcaro.jcstudentapi.application.usecase.course.CreateCourseUseCase;
import com.jcaro.jcstudentapi.application.usecase.course.EditCourseUseCase;
import com.jcaro.jcstudentapi.application.usecase.course.GetAllCoursesUseCase;
import com.jcaro.jcstudentapi.application.usecase.course.RemoveCourseUseCase;
import com.jcaro.jcstudentapi.domain.model.Course;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing courses.
 * <p>
 * This controller belongs to the Infrastructure layer and acts as the inbound adapter,
 * exposing HTTP endpoints for course-related operations.
 * It delegates the business logic to the corresponding use cases in the Application layer.
 */
@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CreateCourseUseCase createCourseUseCase;
    private final EditCourseUseCase editCourseUseCase;
    private final GetAllCoursesUseCase getAllCoursesUseCase;
    private final RemoveCourseUseCase removeCourseUseCase;

    public CourseController(
            CreateCourseUseCase createCourseUseCase,
            EditCourseUseCase editCourseUseCase,
            GetAllCoursesUseCase getAllCoursesUseCase,
            RemoveCourseUseCase removeCourseUseCase) {
        this.createCourseUseCase = createCourseUseCase;
        this.editCourseUseCase = editCourseUseCase;
        this.getAllCoursesUseCase = getAllCoursesUseCase;
        this.removeCourseUseCase = removeCourseUseCase;
    }

    /**
     * Creates a new course.
     *
     * @param course the request payload containing course details
     * @return the created {@link Course}
     */
    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody @Valid CourseRequest course) {
        Course createdCourse = createCourseUseCase.execute(course);
        return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
    }

    /**
     * Updates an existing course by its ID.
     *
     * @param id     the ID of the course to update
     * @param course the request payload containing updated course details
     * @return the updated {@link Course}
     */
    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody @Valid CourseRequest course) {
        Course updatedCourse = editCourseUseCase.execute(id, course);
        return new ResponseEntity<>(updatedCourse, HttpStatus.OK);
    }

    /**
     * Retrieves all courses.
     *
     * @return a list of {@link Course}
     */
    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = getAllCoursesUseCase.execute();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    /**
     * Removes a course by its ID.
     *
     * @param id the ID of the course to remove
     * @return {@link HttpStatus#NO_CONTENT} if deletion was successful
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeCourse(@PathVariable Long id) {
        removeCourseUseCase.execute(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
