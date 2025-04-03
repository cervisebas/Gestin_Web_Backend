package com.isft194.gestin.dtos.request;

import lombok.Data;

import java.util.List;

@Data
public class CareerRequest {

    private Long id;
    private String name;
    private String turn;
    private List<SubjectRequest> subjectsRequest;
}
