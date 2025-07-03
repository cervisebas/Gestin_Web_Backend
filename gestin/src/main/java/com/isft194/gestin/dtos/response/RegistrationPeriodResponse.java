package com.isft194.gestin.dtos.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationPeriodResponse {
  private Long id;
  private LocalDateTime startDate;
  private LocalDateTime endDate;
  private Boolean itsForExams;
}
