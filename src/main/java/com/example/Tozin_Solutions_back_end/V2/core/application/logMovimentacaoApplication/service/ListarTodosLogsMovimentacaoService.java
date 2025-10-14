package com.example.Tozin_Solutions_back_end.V2.core.application.logMovimentacaoApplication.service;

import com.example.Tozin_Solutions_back_end.V2.core.application.logMovimentacaoApplication.dto.LogMovimentacaoOutput;
import com.example.Tozin_Solutions_back_end.V2.core.application.logMovimentacaoApplication.port.LogMovimentacaoPort;
import com.example.Tozin_Solutions_back_end.V2.core.application.logMovimentacaoApplication.useCase.ListarTodosLogsMovimentacaoUseCase;
import com.example.Tozin_Solutions_back_end.V2.core.domain.logMovimentacaoDomain.LogMovimentacao;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListarTodosLogsMovimentacaoService implements ListarTodosLogsMovimentacaoUseCase {

    private final LogMovimentacaoPort logMovimentacaoPort;

    public ListarTodosLogsMovimentacaoService(LogMovimentacaoPort logMovimentacaoPort) {
        this.logMovimentacaoPort = logMovimentacaoPort;
    }

    @Override
    public List<LogMovimentacaoOutput> executar() {
        List<LogMovimentacao> logs = logMovimentacaoPort.buscarTodos();

        return logs.stream()
                .map(this::toOutput)
                .collect(Collectors.toList());
    }

    private LogMovimentacaoOutput toOutput(LogMovimentacao log) {
        return new LogMovimentacaoOutput(
                log.getId(),
                log.getDataMovimentacao(),
                log.getTipoPeca(),
                log.getNomePeca(),
                log.getQuantidadeEntrada(),
                log.getQuantidadeSaida(),
                log.getDataRegistro()
        );
    }
}
