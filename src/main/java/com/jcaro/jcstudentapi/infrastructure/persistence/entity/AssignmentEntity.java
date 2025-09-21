package com.jcaro.jcstudentapi.infrastructure.persistence.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jcaro.jcstudentapi.domain.model.Course;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Table(name = "assignments")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AssignmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private boolean obligatory;
    @ManyToOne
    private CourseEntity course;
    @OneToMany(mappedBy = "assignment",cascade = CascadeType.ALL)
    private List<StudentAssignmentEntity> studentAssignmentList;


}
