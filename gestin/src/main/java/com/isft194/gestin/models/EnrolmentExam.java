package com.isft194.gestin.models;

import lombok.*;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "enrolment_exam")
@Data
@Builder @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class EnrolmentExam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user-student_id", nullable = false)
    private User student;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "enrolment-exam_exam", joinColumns = @JoinColumn(name = "enrolment-exam_id"), inverseJoinColumns = @JoinColumn(name = "exam_id"))
    private List<Exam> exams = new ArrayList<>();
}
