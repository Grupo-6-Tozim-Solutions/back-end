package com.example.Tozin_Solutions_back_end.V2.core.application.movimentacaoEstoqueApplication.dto;

import java.time.LocalDateTime;

public record MovimentacaoEstoqueOutput(
        Long id,
        LocalDateTime dataMovimentacao,
        Long pecaId,
        String nomePeca,
        String tipoPeca,
        Double quantidadeEntrada,
        Double quantidadeSaida
) {
}
