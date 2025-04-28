package com.example.Tozin_Solutions_back_end.dto.usuarioDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class LoginUsuarioDTO {
    @Email
    private String email;

    @NotBlank
    private String senha;

    public LoginUsuarioDTO(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }
}
