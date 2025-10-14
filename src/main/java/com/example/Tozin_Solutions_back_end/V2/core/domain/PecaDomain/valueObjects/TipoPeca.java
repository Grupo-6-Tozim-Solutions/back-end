package com.example.Tozin_Solutions_back_end.V2.core.domain.PecaDomain.valueObjects;

public enum TipoPeca {
    PECA,
    ESPUMA,
    TECIDO;
    public static TipoPeca fromString(String value) {
        return TipoPeca.valueOf(value.toUpperCase());
    }
}
