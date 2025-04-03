package com.isft194.gestin.dtos.response;
import lombok.*;

@Data @AllArgsConstructor
public class StudentResponse {

    private String nameStudent;
    private String lastNameStudent;
    private Integer note;
}
