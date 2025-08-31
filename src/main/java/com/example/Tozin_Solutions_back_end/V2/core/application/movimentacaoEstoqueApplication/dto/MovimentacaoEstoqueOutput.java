package com.example.Tozin_Solutions_back_end.V2.core.application.movimentacaoEstoqueApplication.dto;

import java.time.LocalDateTime;

public record MovimentacaoEstoqueOutput(
        Long id,
        LocalDateTime dataMovimentacao,
        String tipoPeca,
        String nomePeca,
        Double quantidadeEntrada,
        Double quantidadeSaida
) {
}
