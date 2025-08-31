package com.example.Tozin_Solutions_back_end.V1.dto.usuarioDTO;

import jakarta.validation.constraints.Email;

public class LoginUsuarioDTO {

    @Email
    private String email;

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
