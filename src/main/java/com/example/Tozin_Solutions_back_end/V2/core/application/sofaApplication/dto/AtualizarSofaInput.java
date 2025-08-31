package com.example.Tozin_Solutions_back_end.V2.core.application.sofaApplication.dto;

import jakarta.validation.constraints.Size;

public record AtualizarSofaInput(
        @Size(min = 3, max = 30, message = "Modelo deve ter entre 3 e 30 caracteres")
        String modelo,

        String caminhoImagem
) {}
