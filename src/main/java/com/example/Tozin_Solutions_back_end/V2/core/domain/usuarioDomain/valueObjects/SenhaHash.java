package com.example.Tozin_Solutions_back_end.V2.domain.usuarioDomain.valueObjects;

import com.example.Tozin_Solutions_back_end.V2.domain.usuarioDomain.excepetion.DomainException;

public record SenhaHash(String value) {
    public SenhaHash {
        if (value == null || value.isBlank()) throw new DomainException("Hash de senha vazio");
    }
    @Override public String toString() { return value; }
}
