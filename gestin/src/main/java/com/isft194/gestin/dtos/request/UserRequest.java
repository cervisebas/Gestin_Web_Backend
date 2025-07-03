package com.isft194.gestin.dtos.request;

import lombok.Data;
import java.time.LocalDateTime;

import com.isft194.gestin.enums.Gender;

@Data
public class UserRequest {
    private Long id;
    
    private String names;
    private String lastNames;
    private Integer dni;
    private LocalDateTime birthdate;
    private String phone;
    private Long gestinId;
    private String email;
    private String birthplace;
    private Boolean isTeacher;
    private Gender gender;
    private String password;
    private String emergencyPhone;
}
