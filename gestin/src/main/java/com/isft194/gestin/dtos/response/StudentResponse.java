package com.isft194.gestin.dtos.response;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponse {
    private String nameStudent;
    private String lastNameStudent;
    private Integer note;
}
