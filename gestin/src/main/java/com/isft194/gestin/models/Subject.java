package com.isft194.gestin.models;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "subject")
@Builder @Data @NoArgsConstructor @AllArgsConstructor
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "isFree")
    private Boolean free;

    @Column(name = "year")
    private Long year;

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    private User teacher;
}
