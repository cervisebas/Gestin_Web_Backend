package com.isft194.gestin.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExamResponse {
    private Long id;
    private Date examDate;
    private LocalDateTime examTime;
    private UserResponse teacher;
    private UserResponse firstVocal;
    private UserResponse secondVocal;
    private UserResponse thirdVocal;
    private SubjectResponse subject;
}
