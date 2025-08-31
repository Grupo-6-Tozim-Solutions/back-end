package com.example.Tozin_Solutions_back_end.V2.core.domain.SofaDomain.exception;

import com.example.Tozin_Solutions_back_end.V2.core.domain.usuarioDomain.excepetion.DomainException;

public class SofaNaoEncontradoException extends DomainException {
    public SofaNaoEncontradoException(Long id) {
        super("Sofá não encontrado com ID: " + id);
    }
}
