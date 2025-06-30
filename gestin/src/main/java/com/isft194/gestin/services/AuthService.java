package com.isft194.gestin.services;

import com.isft194.gestin.dtos.request.AuthRequest;
import com.isft194.gestin.dtos.response.AuthResponse;
import com.isft194.gestin.exceptions.NotAuthenticatedException;
import com.isft194.gestin.exceptions.UserNotFoundException;
import com.isft194.gestin.interfaces.IAuthenticationFacade;
import com.isft194.gestin.jwt.CustomUserDetails;
import com.isft194.gestin.jwt.JwtUtil;
import com.isft194.gestin.models.User;
import com.isft194.gestin.models.UserSession;
import com.isft194.gestin.repositories.IUserRepository;
import com.isft194.gestin.repositories.IUserSessionRepository;
import com.isft194.gestin.security.AuthenticationFacade;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
public class AuthService {
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IUserSessionRepository userSessionRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private  AuthenticationFacade facade;

    @Autowired
    private AuthenticationManager authenticationManager;


    public AuthResponse login(AuthRequest request) throws BadCredentialsException, UserNotFoundException {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
            )
        );
        
        Optional<User> user = userRepository.findByEmail(request.getEmail());

        if (user.isEmpty()) {
            throw new UserNotFoundException("Usuario no encontrado.");
        }

        String token = jwtUtil.generateToken(user.get());

        UserSession session = new UserSession();
        session.setUser(user.get());
        session.setToken(token);
        userSessionRepository.save(session);

        return AuthResponse.builder()
            .token(token)
            .build();
    }

    public User getCurrentSession() throws NotAuthenticatedException {
        CustomUserDetails customUserDetails = (CustomUserDetails) facade.getAuthentication().getPrincipal();

        Optional<User> user = userRepository.findByEmail(customUserDetails.getUsername());

        if (user.isEmpty()) {
            throw new NotAuthenticatedException("Acceso denegado.");
        }
        
        return user.get();
    }

}