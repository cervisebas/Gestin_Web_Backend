package com.isft194.gestin.dtos.request;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class RegistrationPeriodRequest {
  private Long id;
  private LocalDateTime startDate;
  private LocalDateTime endDate;
  private Boolean itsForExams;
}
