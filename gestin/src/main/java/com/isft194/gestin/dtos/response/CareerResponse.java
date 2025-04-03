package com.isft194.gestin.dtos.response;

import lombok.Data;

@Data
public class CareerResponse {

    private String name;
    private String turn;
    private SubjectsResponse subjectsResponse;
}
