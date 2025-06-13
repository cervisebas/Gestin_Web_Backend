package com.isft194.gestin.models;

import jakarta.persistence.*;

import java.util.Date;

public class UserSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    private Date date;

    private String device;
}
