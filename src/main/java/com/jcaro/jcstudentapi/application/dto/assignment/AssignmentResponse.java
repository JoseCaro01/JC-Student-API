package com.jcaro.jcstudentapi.application.dto.assignment;

/**
 * Data Transfer Object (DTO) for Assignment.
 * <p>
 * This class belongs to the Application layer and defines the data structure
 * that the API clients must provide when sending assignment-related requests.
 * */
public record AssignmentResponse(Long id,
                                 String name,
                                 String description,
                                 boolean obligatory,
                                 Long courseId ) {

}
