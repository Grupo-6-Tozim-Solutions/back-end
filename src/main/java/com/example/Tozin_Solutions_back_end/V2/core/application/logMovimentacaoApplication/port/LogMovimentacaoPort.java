package com.example.Tozin_Solutions_back_end.V2.core.application.logMovimentacaoApplication.port;

import com.example.Tozin_Solutions_back_end.V2.core.domain.logMovimentacaoDomain.LogMovimentacao;

import java.time.LocalDateTime;
import java.util.List;

public interface LogMovimentacaoPort {
    List<LogMovimentacao> buscarTodos();
    List<LogMovimentacao> buscarPorPeriodo(LocalDateTime inicio, LocalDateTime fim);
    List<LogMovimentacao> buscarPorNomePeca(String nomePeca);
    LogMovimentacao buscarPorId(Long id);
}
