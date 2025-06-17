package com.isft194.gestin.models;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "academic_records")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AcademicRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name= "id_student")
    private User student;
    
    @ManyToOne
    @JoinColumn(name = "id_subject")
    private Subject subject;

    @Column(name = "grade")
    private Integer grade;
    
    @Column(name = "exam_record")
    private String exam_record;
    
    @Column(name = "accreditation_date")
    private LocalDateTime accreditation_date;
}

