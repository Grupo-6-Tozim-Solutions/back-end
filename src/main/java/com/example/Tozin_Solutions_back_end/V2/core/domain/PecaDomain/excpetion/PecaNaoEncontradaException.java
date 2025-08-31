package com.example.Tozin_Solutions_back_end.V2.core.domain.PecaDomain.excpetion;

import com.example.Tozin_Solutions_back_end.V2.core.domain.usuarioDomain.excepetion.DomainException;

public class PecaNaoEncontradaException extends DomainException {
    public PecaNaoEncontradaException(Long id){
        super("Peça não encontrada com o id " + id);
    }

    public PecaNaoEncontradaException(String nome){
        super("Peça não encontrada com o nome " + nome);
    }
}
