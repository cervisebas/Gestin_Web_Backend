package com.isft194.gestin.dtos.request;

import lombok.Data;

import java.util.List;

@Data
public class EnrolmentExamRequest {

    private Long id;
    private List<ExamRequest> examRequest;
    private UserRequest student;
}
