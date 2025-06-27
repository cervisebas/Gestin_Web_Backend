package com.isft194.gestin.dtos.request;

import java.time.LocalDateTime;

import com.isft194.gestin.models.Subject;
import com.isft194.gestin.models.User;

import lombok.Data;

@Data
public class AcademicRecordRequest {
    private Long id;
    private User student;
    private Subject subject;
    private Integer grade;
    private String exam_record;
    private LocalDateTime accreditation_date;

}
