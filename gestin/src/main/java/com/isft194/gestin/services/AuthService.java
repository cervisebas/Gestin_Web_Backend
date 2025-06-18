package com.isft194.gestin.services;

import com.isft194.gestin.dtos.request.LoginRequest;
import com.isft194.gestin.dtos.response.AuthResponse;
import com.isft194.gestin.exceptions.NotAuthenticatedException;
import com.isft194.gestin.jwt.JwtService;
import com.isft194.gestin.models.User;
import com.isft194.gestin.models.UserSession;
import com.isft194.gestin.repositories.IUserRepository;
import com.isft194.gestin.repositories.IUserSessionRepository;

import lombok.AllArgsConstructor;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class AuthService {
    private final IUserRepository userRepository;
    private final IUserSessionRepository userSessionRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) throws BadCredentialsException {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
            )
        );
        
        User user = userRepository.findByEmail(request.getEmail());
        String token = jwtService.getToken(user);

        UserSession session = new UserSession();
        session.setUser(user);
        session.setToken(token);
        userSessionRepository.save(session);

        return AuthResponse.builder()
            .token(token)
            .build();
    }

    public User getCurrentSession() throws NotAuthenticatedException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return (User) authentication.getPrincipal();
        }

        throw new NotAuthenticatedException("Acceso denegado.");
    }

}