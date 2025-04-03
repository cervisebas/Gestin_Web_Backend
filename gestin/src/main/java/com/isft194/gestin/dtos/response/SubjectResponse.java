package com.isft194.gestin.dtos.response;

import lombok.*;

@Data
public class SubjectResponse {

    private String name;
    private Boolean free;
    private Long year;
    private UserNecessaryResponse teacher;
}
