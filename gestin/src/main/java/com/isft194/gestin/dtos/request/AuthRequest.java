package com.isft194.gestin.dtos.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthRequest {
    @NotBlank(message = "Es necesario incluir un correo electronico.")
    String email;

    @NotBlank(message = "Es necesario incluir una contraseña.")
    String password;
}
