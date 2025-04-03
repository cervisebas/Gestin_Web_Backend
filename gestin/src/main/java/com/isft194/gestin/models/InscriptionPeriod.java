package com.isft194.gestin.models;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "inscriptionPeriod")
@Builder
@Data @NoArgsConstructor @AllArgsConstructor
public class InscriptionPeriod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "startPeriod")
    private Date startPeriod;
    @Column(name = "endPeriod")
    private Date endPeriod;
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "inscriptionPeriod_exam", joinColumns = @JoinColumn(name = "inscriptionPeriod_id"), inverseJoinColumns = @JoinColumn(name = "exam_id"))
    private List<Exam> exams;

}
