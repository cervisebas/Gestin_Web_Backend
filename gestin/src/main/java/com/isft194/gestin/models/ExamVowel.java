package com.isft194.gestin.models;

import jakarta.persistence.*;

public class ExamVowel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_teacher")
    private User idTeacher;

    @ManyToOne
    @JoinColumn(name = "id_exam")
    private Exam exam;

}
