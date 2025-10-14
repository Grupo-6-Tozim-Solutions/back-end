package com.example.Tozin_Solutions_back_end.V2.core.domain.producaoSofaDomain.valueObjects;

import java.time.LocalDateTime;

public class DataCriacao {
    private final LocalDateTime value;

    public DataCriacao(LocalDateTime value) {
        if (value == null) {
            throw new IllegalArgumentException("Data de criação não pode ser nula");
        }
        this.value = value;
    }

    public LocalDateTime value() { return value; }
}
