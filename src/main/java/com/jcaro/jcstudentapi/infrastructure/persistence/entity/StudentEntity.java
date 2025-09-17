package com.jcaro.jcstudentapi.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "students")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;

    @ManyToMany
    @JoinTable(name = "student_course")
    private List<CourseEntity> courses;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<StudentAssignmentEntity> studentAssignmentList;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<StudentProjectEntity> studentProjectList;
}