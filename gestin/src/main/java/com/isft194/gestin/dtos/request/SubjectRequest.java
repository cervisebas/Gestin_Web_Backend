package com.isft194.gestin.dtos.request;


import lombok.Data;



@Data
public class SubjectRequest {

    private Long id;
    private String name;
    private Boolean free;
    private Long year;
    private UserRequest teacher;
}
