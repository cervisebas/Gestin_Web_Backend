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
    @Column(name="type")
    private String type;
    @Column(name="mail")
    private String mail;
    @Column(name="name")
    private String name;
    @Column(name="lastName")
    private String lastName;
    @Column(name="password")
    private String password;
    @Column(name="identityDoc")
    private String identityDoc;
    @Column(name="phone")
    private Long phoneNumber;
    @Column(name="emergencyPhoneNum")
    private Long emergencyPhoneNumber;
    @Column(name="birthdate")
    private Date birthdate;
    @Column(name="placeOfBirth")
    private String placeOfBirth;
    @Column(name="gender")
    private String gender;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return mail;
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
