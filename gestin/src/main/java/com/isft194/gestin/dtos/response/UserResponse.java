package com.isft194.gestin.dtos.response;

import lombok.Data;

import java.time.LocalDateTime;

import com.isft194.gestin.enums.Gender;
import com.isft194.gestin.models.User;

@Data
public class UserResponse {
    public UserResponse(User data) {
        setId(data.getId());
        setNames(data.getNames());
        setLastNames(data.getLastNames());
        setDni(data.getDni());
        setBirthdate(data.getBirthdate());
        setPhone(data.getPhone());
        setGestinId(data.getGestinId());
        setEmail(data.getEmail());
        setBirthplace(data.getBirthplace());
        setIsTeacher(data.getIsTeacher());
        setGender(data.getGender());
        setEmergencyPhone(data.getEmergencyPhone());
    }

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
    private String emergencyPhone;
}
