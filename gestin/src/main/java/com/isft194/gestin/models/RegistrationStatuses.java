package com.isft194.gestin.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "registration_statuses")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationStatuses {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @Column(name = "enable_exams")
  private String enableExams;

  @Column(name = "enable_subject")
  private String enableSubject;
}
