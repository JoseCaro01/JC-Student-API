package com.jcaro.jcstudentapi.infrastructure.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "courses")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String programmingLanguage;
    @ManyToMany(mappedBy = "courses",cascade = CascadeType.ALL)
    private List<StudentEntity> studentList;
    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL)
    private List<AssignmentEntity> assignmentList;
    @OneToOne(mappedBy = "course",cascade = CascadeType.ALL)
    private ProjectEntity project;

}
