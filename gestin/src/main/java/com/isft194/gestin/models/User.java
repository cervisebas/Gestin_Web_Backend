package com.isft194.gestin.models;


import lombok.*;
import jakarta.persistence.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="user")
@Builder @Data @NoArgsConstructor @AllArgsConstructor
public class User implements UserDetails
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //valor que se asocia al user de gestin
    @Column(name="gestinId")
    private Long gestinId;
    @Column(name="is_teacher")
    private Boolean isTeacher;

    private String email;

    private String name;

    private String lastName;

    private String password;

    private Integer dni;

    private Long phone;

    @Column(name="emergency_phone")
    private Long emergencyPhone;

    private Date birthdate;

    private String birthplace;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    public enum Gender{
        MALE,FEMALE,OTHER
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
