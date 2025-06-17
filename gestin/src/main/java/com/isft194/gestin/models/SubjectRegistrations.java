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
@Table(name = "subject_registration")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubjectRegistrations {
  @Id
  @ManyToOne
  @JoinColumn(name = "id_subject")
  private Subject subject;
  
  @Id
  @ManyToOne
  @JoinColumn(name = "id_student")
  private User student;
  
  @Column(name = "is_registered")
  private Boolean isRegistered;
  
  @ManyToOne
  @JoinColumn(name = "id_status")
  private RegistrationStatuses status;
  
  @Column(name = "enrollment_year")
  private Integer enrollmentYear;
}
