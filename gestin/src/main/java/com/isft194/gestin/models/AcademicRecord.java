package com.isft194.gestin.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Entity
@Table(name = "academicRecord")
@Builder
@Data @NoArgsConstructor @AllArgsConstructor
public class AcademicRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Note")
    private Integer note;
    @Column(name = "final")
    private Long finalGrade;
    @Column(name = "firstQuarter")
    private Long firstQuarterGrade;
    @Column(name = "secondQuarter")
    private Long secondQuarterGrade;
    @Column(name = "passed")
    private Boolean passed;
    @ManyToOne
    @JoinColumn(name= "student_id")
    private User student;
    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

}

