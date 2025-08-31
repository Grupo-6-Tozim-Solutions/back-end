package com.example.Tozin_Solutions_back_end.V2.core.domain.SofaDomain.ValueObjects;

import com.example.Tozin_Solutions_back_end.V2.core.domain.usuarioDomain.excepetion.DomainException;

public record CaminhoImagem(String value) {
    public CaminhoImagem {
        if (value != null && value.length() > 255) {
            throw new DomainException("O caminho da imagem não pode ter mais de 255 caracteres");
        }
    }
    @Override
    public String toString() {
        return value != null ? value : "";
    }
}
