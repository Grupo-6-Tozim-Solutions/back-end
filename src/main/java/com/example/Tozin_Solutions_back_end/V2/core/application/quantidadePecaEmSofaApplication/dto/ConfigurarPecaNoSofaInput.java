package com.example.Tozin_Solutions_back_end.V2.core.application.quantidadePecaEmSofaApplication.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ConfigurarPecaNoSofaInput(
        @NotNull(message = "ID do sofá é obrigatório")
        Long sofaId,

        @NotNull(message = "ID da peça é obrigatório")
        Long pecaId,

        @NotNull(message = "Quantidade é obrigatória")
        @Positive(message = "Quantidade deve ser maior que zero")
        Double quantidade
) {}
