package com.example.Tozin_Solutions_back_end.V2.core.application.movimentacaoEstoqueApplication.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record MovimentacaoEstoqueInput(
   @NotNull(message = "Tipo de peça não pode ser vazio")
   String tipoPeca,

   @NotBlank(message = "Nome da pessoa não pode ser vazio")
   String nomePeca,

   @Positive(message = "Quantidade de entrada não pode ser negativa")
   Double quantidadeEntrada,

   @Positive(message = "Quantidade de entrada não pode ser negativa")
   Double quantidadeSaida
) {
}
