package com.example.Tozin_Solutions_back_end.dto.usuarioDTO;

public class DadosUsuarioDTO {
    private String nome;
    private String email;

    public DadosUsuarioDTO(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }
}
