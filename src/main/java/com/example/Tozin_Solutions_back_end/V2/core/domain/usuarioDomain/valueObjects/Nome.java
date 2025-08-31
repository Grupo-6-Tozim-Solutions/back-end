package com.example.Tozin_Solutions_back_end.V2.core.domain.usuarioDomain.valueObjects;

import com.example.Tozin_Solutions_back_end.V2.core.domain.usuarioDomain.excepetion.DomainException;

public record Nome(String value) {
    public Nome{
        if (value == null || value.isBlank())  {
            throw new DomainException("Nome em branco");
        }else if (value.length() < 3) {
            throw new DomainException("Nome com menos de 3 caracteres");
        }else if(value.length() > 30 ) {
            throw new DomainException("Nome com mais de 15 caracteres");
        }
    }

    @Override public String toString() {
        return value;
    }

}
