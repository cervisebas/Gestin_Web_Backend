package com.isft194.gestin.models;

import com.isft194.gestin.enums.Gender;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name="users")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String names;

    @Column(name = "last_names")
    private String lastNames;

    private Integer dni;

    private LocalDateTime birthdate;

    private String phone;
    
    @Column(name="gestin_id")
    private Long gestinId;

    private String email;

    private String password;

    private String birthplace;

    @Column(name="is_teacher")
    private Boolean isTeacher;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name="emergency_phone")
    private String emergencyPhone;
}
