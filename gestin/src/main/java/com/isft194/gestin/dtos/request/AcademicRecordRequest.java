package com.isft194.gestin.dtos.request;

import lombok.Data;

@Data
public class AcademicRecordRequest {

    private Long id;
    private Integer note;
    private Long finalGrade;
    private Long firstQuarterGrade;
    private Long secondQuarterGrade;
    private Boolean passed;
    private UserRequest userRequestId;
    private SubjectRequest subjectRequestId;

}
