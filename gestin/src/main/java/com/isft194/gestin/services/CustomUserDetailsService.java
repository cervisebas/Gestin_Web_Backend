package com.isft194.gestin.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.isft194.gestin.jwt.CustomUserDetails;
import com.isft194.gestin.models.User;
import com.isft194.gestin.repositories.IUserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
  @Autowired
  private IUserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    User user = userRepository.findByEmail(email)
      .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

    return new CustomUserDetails(user);
  }
}
