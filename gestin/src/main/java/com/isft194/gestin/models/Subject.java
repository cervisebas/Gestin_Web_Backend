package com.isft194.gestin.models;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "subjects")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "id_teacher")
    private User teacher;

    @ManyToOne()
    @JoinColumn(name = "id_carrer")
    private Career carrer;

    @Column(name = "year_in_career")
    private Integer year_in_career;

    @Column(name = "min_grade_to_pass")
    private Integer min_grade_to_pass;

    @Column(name = "is_practical")
    private Boolean is_practical;

    @Column(name = "is_promotional")
    private Boolean is_promotional;
}
