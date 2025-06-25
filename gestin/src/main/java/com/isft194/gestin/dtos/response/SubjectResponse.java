package com.isft194.gestin.dtos.response;

import com.isft194.gestin.models.Career;
import com.isft194.gestin.models.User;

import lombok.Data;

@Data
public class SubjectResponse {
    private Long id;
    private String name;
    private User teacher;
    private Career carrer;
    private Integer year_in_career;
    private Integer min_grade_to_pass;
    private Boolean is_practical;
    private Boolean is_promotional;
}
