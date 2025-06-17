package com.isft194.gestin.models;

import jakarta.persistence.*;

public class ExamRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_exam")
    private Exam exam;

    @ManyToOne
    @JoinColumn(name = "id_student")
    private User student;

    @Column(name = "is_registered")
    private boolean isRegistered;
}
