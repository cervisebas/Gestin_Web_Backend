package com.isft194.gestin.dtos.request;


import lombok.*;


import java.time.LocalDateTime;

import com.isft194.gestin.models.Subject;
import com.isft194.gestin.models.User;

@Data
public class ExamRequest {
    private Long id;
    private LocalDateTime date;
    private User teacher;
    private Subject subject;
}
