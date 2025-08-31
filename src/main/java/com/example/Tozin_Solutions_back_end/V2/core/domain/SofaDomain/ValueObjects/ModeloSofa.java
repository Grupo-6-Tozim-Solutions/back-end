package com.example.Tozin_Solutions_back_end.V2.core.domain.SofaDomain.ValueObjects;

import com.example.Tozin_Solutions_back_end.V2.core.domain.usuarioDomain.excepetion.DomainException;

public record ModeloSofa(String value) {
    public ModeloSofa {
        if (value == null || value.trim().isEmpty()) {
            throw new DomainException("O modelo do sofá não pode ser vazio");
        }
        String trimmedValue = value.trim();
        if (trimmedValue.length() < 3) {
            throw new DomainException("O modelo do sofá não pode ter menos de 3 caracteres");
        }
        if (trimmedValue.length() > 30) {
            throw new DomainException("O modelo do sofá não pode ter mais de 30 caracteres");
        }
        value = trimmedValue;
    }

    @Override
    public String toString() {
        return value;
    }
}
