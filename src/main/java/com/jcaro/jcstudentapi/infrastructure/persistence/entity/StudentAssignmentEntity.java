package com.jcaro.jcstudentapi.infrastructure.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jcaro.jcstudentapi.domain.model.ScoreEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "students_assignments")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentAssignmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private StudentEntity student;

    @ManyToOne
    private AssignmentEntity assignment;

    @Enumerated(EnumType.STRING)
    private ScoreEnum score;
}
