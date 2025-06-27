package com.isft194.gestin.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "exam_registrations")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExamRegistration {
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_exam")
    private Exam exam;

    @ManyToOne
    @JoinColumn(name = "id_student")
    private User student;

    @Column(name = "is_registered")
    private Boolean isRegistered;
}
