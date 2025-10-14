package com.example.Tozin_Solutions_back_end.V2.core.application.logMovimentacaoApplication.useCase;

import com.example.Tozin_Solutions_back_end.V2.core.application.logMovimentacaoApplication.dto.LogMovimentacaoOutput;

import java.time.LocalDateTime;
import java.util.List;

public interface BuscarLogsPorPeriodoUseCase {
    List<LogMovimentacaoOutput> executar(LocalDateTime inicio, LocalDateTime fim);
}
