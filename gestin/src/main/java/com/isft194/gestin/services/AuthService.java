package com.isft194.gestin.services;

import com.isft194.gestin.dtos.request.LoginRequest;
import com.isft194.gestin.dtos.response.AuthResponse;
import com.isft194.gestin.jwt.JwtService;
import com.isft194.gestin.models.User;
import com.isft194.gestin.repositories.IUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class AuthService {
    private final IUserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        // Autenticamos al usuario utilizando el AuthenticationManager.
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getMail(), request.getPassword())
        );

        // Recuperamos el usuario a partir de su correo.
        User user = userRepository.findByMail(request.getMail());
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        // Generamos el token con el UserDetails.
        String token = jwtService.getToken(user);

        // Retornamos la respuesta de autenticación.
        return AuthResponse.builder()
                .token(token)
                .build();
    }

}