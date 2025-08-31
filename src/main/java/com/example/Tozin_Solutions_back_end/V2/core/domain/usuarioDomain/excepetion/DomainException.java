package com.example.Tozin_Solutions_back_end.V2.core.domain.usuarioDomain.excepetion;

public class DomainException extends RuntimeException {
    public DomainException(String message) {
        super(message);
    }
    public DomainException(String mensagem, Throwable cause){
        super(mensagem,cause);
    }
}
