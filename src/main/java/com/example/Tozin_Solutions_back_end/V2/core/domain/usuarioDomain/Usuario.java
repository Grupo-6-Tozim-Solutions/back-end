package com.example.Tozin_Solutions_back_end.V2.domain.usuarioDomain;

import com.example.Tozin_Solutions_back_end.V2.domain.usuarioDomain.excepetion.DomainException;
import com.example.Tozin_Solutions_back_end.V2.domain.usuarioDomain.valueObjects.Email;
import com.example.Tozin_Solutions_back_end.V2.domain.usuarioDomain.valueObjects.Nome;
import com.example.Tozin_Solutions_back_end.V2.domain.usuarioDomain.valueObjects.SenhaHash;

public class Usuario {
    private Long id;
    private Nome nome;
    private Email email;
    private SenhaHash senhaHash;

    private Usuario(Long id, Nome nome, Email email, SenhaHash senhaHash) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senhaHash = senhaHash;
    }

    public static Usuario criarUsuario(Nome nome, Email email, SenhaHash senhaHash) {
        return new Usuario(null, nome, email, senhaHash);
    }

    public static Usuario existente(Long id, Nome nome, Email email, SenhaHash senhaHash) {
        if (id == null) {
            throw new DomainException("o id não pode ser nulo para existente");
        }
        return new Usuario(id, nome, email, senhaHash);
    }
    public void alterarEmail(Email novoEmail) { this.email = novoEmail; }
    public void trocarSenha(SenhaHash novaHash) { this.senhaHash = novaHash; }

    public Long getId() { return id; }
    public Nome getNome() { return nome; }
    public Email getEmail() { return email; }
    public SenhaHash getSenhaHash() { return senhaHash; }

    public Usuario comId(Long id) { return existente(id, nome, email, senhaHash); }
}

