package com.jcaro.jcstudentapi.application.dto.project;

import com.jcaro.jcstudentapi.domain.model.Course;

/**
 * Data Transfer Object (DTO) for Project.
 * <p>
 * This class belongs to the Application layer and defines the data structure
 * that the API clients must provide when sending assignment-related requests.
 * */
public record ProjectResponse(  Long id,
                                String name,
                                String description,
                                Long courseId) {
}
