package com.example.Tozin_Solutions_back_end.V2.core.application.logMovimentacaoApplication.dto;

import java.time.LocalDateTime;

public record LogMovimentacaoOutput(
        Long id,
        LocalDateTime dataMovimentacao,
        String tipoPeca,
        String nomePeca,
        Integer quantidadeEntrada,
        Integer quantidadeSaida,
        LocalDateTime dataRegistro) {
}
