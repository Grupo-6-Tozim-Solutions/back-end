package com.example.Tozin_Solutions_back_end.V2.core.domain.usuarioDomain.valueObjects;

import com.example.Tozin_Solutions_back_end.V2.core.domain.usuarioDomain.excepetion.DomainException;

public record Email(String value) {
    public Email {
        if (value == null || value.isEmpty()) {
            throw new DomainException("Email em branco");
        }else if (!value.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new IllegalArgumentException("Email inválido");
        }else if (value.length() > 255) {
            throw new IllegalArgumentException("Email com mais de 255 caracteres");
        }
    }

    @Override public String toString() {
        return value;
    }
}
