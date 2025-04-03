package com.isft194.gestin.dtos.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
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
