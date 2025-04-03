package com.isft194.gestin.dtos.response;

import lombok.Data;

@Data
public class UserNecessaryResponse {

    private Long id;
    //private Long gestinId;
    private String type;
    private String name;
    private String lastName;
    private String mail;
}
