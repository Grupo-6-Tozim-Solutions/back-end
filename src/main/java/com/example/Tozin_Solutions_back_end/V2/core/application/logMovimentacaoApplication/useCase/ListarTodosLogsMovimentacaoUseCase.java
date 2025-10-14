package com.example.Tozin_Solutions_back_end.V2.core.application.logMovimentacaoApplication.useCase;

import com.example.Tozin_Solutions_back_end.V2.core.application.logMovimentacaoApplication.dto.LogMovimentacaoOutput;

import java.util.List;

public interface ListarTodosLogsMovimentacaoUseCase {
    List<LogMovimentacaoOutput> executar();
}
