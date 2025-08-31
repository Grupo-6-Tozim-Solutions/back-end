package com.example.Tozin_Solutions_back_end.V2.domain.usuarioDomain.excepetion;

public class UsuarioNaoEncontradoException extends DomainException {

    public UsuarioNaoEncontradoException(Long id) {
        super("Usuário não encontrado com o id " + id);
    }

    public UsuarioNaoEncontradoException(String email) {
        super("Usuário não encontrado com o email " + email);
    }

}
