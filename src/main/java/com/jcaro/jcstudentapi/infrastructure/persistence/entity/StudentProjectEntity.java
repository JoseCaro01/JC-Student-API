package com.jcaro.jcstudentapi.infrastructure.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jcaro.jcstudentapi.domain.model.ScoreEnum;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "students_projects")
@Data
@NoArgsConstructor
public class StudentProjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JsonIgnore
    private StudentEntity student;
    @ManyToOne
    private ProjectEntity project;
    @Enumerated(EnumType.STRING)
    ScoreEnum codeQuality;
    @Enumerated(EnumType.STRING)
    ScoreEnum functionality;
    @Enumerated(EnumType.STRING)
    ScoreEnum security;
    @Enumerated(EnumType.STRING)
    ScoreEnum documentation;
    @Enumerated(EnumType.STRING)
    ScoreEnum deployment;
    @Enumerated(EnumType.STRING)
    ScoreEnum extra;



}
