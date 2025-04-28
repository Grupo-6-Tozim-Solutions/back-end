package com.example.Tozin_Solutions_back_end.dto.usuarioDTO;

public class UsuarioTokenDTO {
    private Long id;
    private String email;
    private String nome;
    private String token;

    public UsuarioTokenDTO() {
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getToken() {
        return token;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

