package com.isft194.gestin.models;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "exams")
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long examId;

    @Column(name = "examDate")
    private Date examDate;

    @Column(name = "examTime")
    private LocalDateTime examTime;

    @OneToOne
    @JoinColumn(name = "teacher_id")
    private User teacher;

    @OneToOne
    @JoinColumn(name = "fistVocal_id")
    private User firstVocal;

    @OneToOne
    @JoinColumn(name = "secondVocal_id")
    private User secondVocal;

    @OneToOne
    @JoinColumn(name = "thirdVocal_id")
    private User thirdVocal;

    @OneToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

}
