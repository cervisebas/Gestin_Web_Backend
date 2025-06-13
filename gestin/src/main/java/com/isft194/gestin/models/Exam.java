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
    private Long id;

    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "id_teacher")
    private User teacher;

    @ManyToOne
    @JoinColumn(name = "id_subject")
    private Subject subject;

}
