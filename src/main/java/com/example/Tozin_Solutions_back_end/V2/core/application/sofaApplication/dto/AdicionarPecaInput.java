package com.example.Tozin_Solutions_back_end.V2.core.application.sofaApplication.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

public record AdicionarPecaInput(
        @NotNull(message = "Lista de peças é obrigatória")
        List<PecaQuantidade> pecas
) {
    public record PecaQuantidade(
            @NotNull(message = "ID da peça é obrigatório")
            Long pecaId,

            @NotNull(message = "Quantidade é obrigatória")
            @Positive(message = "Quantidade deve ser maior que zero")
            Double quantidade
    ) {}
}
