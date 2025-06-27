package com.isft194.gestin.dtos.response;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegistrationPeriodResponse {
  private Long id;
  private LocalDateTime startDate;
  private LocalDateTime endDate;
  private Boolean itsForExams;
}
