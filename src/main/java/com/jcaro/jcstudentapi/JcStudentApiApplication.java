package com.jcaro.jcstudentapi;


import com.jcaro.jcstudentapi.domain.model.*;
import com.jcaro.jcstudentapi.domain.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;


@SpringBootApplication
public class JcStudentApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(JcStudentApiApplication.class, args);
    }


    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    CommandLineRunner run(UserRepository userRepository, RoleRepository roleRepository, CourseRepository courseRepository, StudentRepository studentRepository, AssignmentRepository assignmentRepository
            , ProjectRepository projectRepository) {
        return args -> {
            final Role userRole = roleRepository.save(new Role(null, "ROLE_USER"));
            final Role adminRole = roleRepository.save(new Role(null, "ROLE_ADMIN"));
            final User jose = userRepository.save(new User(null, "Jose Alfonso", "Caro Romero", "jcaroromeroprog@gmail.com", passwordEncoder().encode(System.getenv("ADMIN_PASSWORD")),
                    LocalDateTime.now(), List.of(adminRole)));
            final User guest = userRepository.save(new User(null, "Guest", "User", "guestuser@gmail.com", passwordEncoder().encode("guestuser"),
                    LocalDateTime.now(), List.of(userRole)));
            final Course course = new Course(null, "Course 1", "Flutter");
            final Course savedCourse = courseRepository.save(course);
            final Student student = new Student(null, "Jose Caro", "guestuser@gmail.com", List.of(savedCourse));
            studentRepository.save(student);
            final Assignment assignment = new Assignment(null, "Task 1", "Description 1", false, savedCourse);
            assignmentRepository.save(assignment);
            final Project project = new Project(null, "Project 1", "Description project", savedCourse);
            projectRepository.save(project);
        };

    }

}
