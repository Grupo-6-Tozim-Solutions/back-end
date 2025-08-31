package com.example.Tozin_Solutions_back_end.V2.core.application.pecaApplication.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record CadastrarPecaInput(
        @NotBlank(message = "Nome é obrigatório")
        @Size(min = 3, max = 25, message = "Nome deve ter entre 3 e 25 caracteres")
        String nome,

        @NotNull(message = "Quantidade em estoque é obrigatória")
        @PositiveOrZero(message = "Quantidade em estoque não pode ser negativa")
        Double quantidadeEstoque,

        @NotNull(message = "Quantidade mínima é obrigatória")
        @PositiveOrZero(message = "Quantidade mínima não pode ser negativa")
        Double quantidadeMinima,
        String tipo
) {}
