package com.isft194.gestin.dtos.response;

import lombok.Data;

import java.util.Date;

@Data
public class UserResponse
{
    private Long id;
    //valor que se asocia al user de gestin
    private Long gestinId;
    private String type;
    private String mail;
    private String name;
    private String lastName;
    private String password;
    private String identityDoc;
    private Long phoneNumber;
    private Long emergencyPhoneNumber;
    private Date birthdate;
    private String placeOfBirth;
    private String gender;
}
