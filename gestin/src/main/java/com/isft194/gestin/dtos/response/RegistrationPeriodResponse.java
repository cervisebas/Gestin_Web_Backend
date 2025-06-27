package com.isft194.gestin.dtos.response;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class RegistrationPeriodResponse {
  private Long id;
  private LocalDateTime startDate;
  private LocalDateTime endDate;
  private Boolean itsForExams;
}
