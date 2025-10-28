package com.example.Tozin_Solutions_back_end.V2.core.application.logMovimentacaoApplication.port;

import com.example.Tozin_Solutions_back_end.V2.core.domain.logMovimentacaoDomain.LogMovimentacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface LogMovimentacaoPort {
    List<LogMovimentacao> buscarTodos();
    List<LogMovimentacao> buscarPorPeriodo(LocalDateTime inicio, LocalDateTime fim);
    List<LogMovimentacao> buscarPorNomePeca(String nomePeca);
    LogMovimentacao buscarPorId(Long id);
    Page<LogMovimentacao> buscarTodosPaginados(Pageable pageable, String filter, String tipoPeca, String acao);
}
