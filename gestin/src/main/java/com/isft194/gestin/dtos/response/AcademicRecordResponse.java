package com.isft194.gestin.dtos.response;

import lombok.Data;

@Data
public class AcademicRecordResponse {

    private Long id;
    private Integer note;
    private UserNecessaryResponse student;
    private SubjectResponse subject;

}
