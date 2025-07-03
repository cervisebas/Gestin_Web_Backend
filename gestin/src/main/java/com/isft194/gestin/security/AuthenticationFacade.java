package com.isft194.gestin.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.isft194.gestin.interfaces.IAuthenticationFacade;

@Component
public class AuthenticationFacade implements IAuthenticationFacade {
  public Authentication getAuthentication() {
    return SecurityContextHolder.getContext().getAuthentication();
  }
}
