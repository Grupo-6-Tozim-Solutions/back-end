package com.example.Tozin_Solutions_back_end.V2.core.application.pecaApplication.dto;

import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record AtualizarPecaInput(
        @Size(min = 3, max = 25, message = "Nome deve ter entre 3 e 25 caracteres")
        String nome,

        @PositiveOrZero(message = "Quantidade mínima não pode ser negativa")
        Double quantidadeMinima
) {
}
