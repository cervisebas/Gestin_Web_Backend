package com.isft194.gestin.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentSubjectResponse {
    private String studentName;
    private String subjectName;
}
