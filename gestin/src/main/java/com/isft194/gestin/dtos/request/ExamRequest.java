package com.isft194.gestin.dtos.request;


import lombok.*;


import java.time.LocalDateTime;
import java.util.Date;

@Data
public class ExamRequest {

    private Long id;
    private Date examDate;
    private LocalDateTime examTime;
    private UserRequest teacher;
    private UserRequest firstVocal;
    private UserRequest secondVocal;
    private UserRequest thirdVocal;
    private SubjectRequest subject;
}
