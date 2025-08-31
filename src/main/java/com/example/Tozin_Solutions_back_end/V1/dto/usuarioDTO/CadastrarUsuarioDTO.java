package com.example.Tozin_Solutions_back_end.V1.dto.usuarioDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class CadastrarUsuarioDTO {

    private String nome;

    private String email;

    private String senha;


    public CadastrarUsuarioDTO(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;

    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }



}

