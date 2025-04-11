package com.example.Tozin_Solutions_back_end.dto.usuarioDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class CadastrarAtualizarUsuarioDTO {

    @NotBlank(message = "Nome não pode ser vazio!")
    private String nome;

    @NotBlank(message = "Email não pode ser vazio!")
    @Email(message = "Formato de email inválido!")
    private String email;

    @NotBlank(message = "Senha não pode ser vazia!")
    private String senha;

    public CadastrarAtualizarUsuarioDTO(String nome, String email, String senha) {
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
