package com.example.Tozin_Solutions_back_end.dto.usuarioDTO;

public class DadosUsuarioDTO {
    private Long id;
    private String nome;
    private String email;

    public DadosUsuarioDTO(Long id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }
}
