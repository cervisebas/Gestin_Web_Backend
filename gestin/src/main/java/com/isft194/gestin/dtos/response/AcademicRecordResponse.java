package com.isft194.gestin.dtos.response;

import java.time.LocalDateTime;

import com.isft194.gestin.models.Subject;
import com.isft194.gestin.models.User;

import lombok.Data;

@Data
public class AcademicRecordResponse {
    private Long id;
    private User student;
    private Subject subject;
    private Integer grade;
    private String exam_record;
    private LocalDateTime accreditation_date;
}
