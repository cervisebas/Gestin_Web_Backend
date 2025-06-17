package com.isft194.gestin.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "exam_vowels")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
