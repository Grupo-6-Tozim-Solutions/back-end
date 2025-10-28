package com.example.Tozin_Solutions_back_end.V2.core.application.logMovimentacaoApplication.service;
import com.example.Tozin_Solutions_back_end.V2.core.application.logMovimentacaoApplication.dto.LogMovimentacaoOutput;
import com.example.Tozin_Solutions_back_end.V2.core.application.logMovimentacaoApplication.dto.LogMovimentacaoPaginationRequest;
import com.example.Tozin_Solutions_back_end.V2.core.application.logMovimentacaoApplication.dto.LogMovimentacaoPaginationResponse;
import com.example.Tozin_Solutions_back_end.V2.core.application.logMovimentacaoApplication.port.LogMovimentacaoPort;
import com.example.Tozin_Solutions_back_end.V2.core.application.logMovimentacaoApplication.useCase.ListarLogsMovimentacaoPaginadosUseCase;
import com.example.Tozin_Solutions_back_end.V2.core.domain.logMovimentacaoDomain.LogMovimentacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListarLogsMovimentacaoPaginadosService implements ListarLogsMovimentacaoPaginadosUseCase {
    private final LogMovimentacaoPort logMovimentacaoPort;
    public ListarLogsMovimentacaoPaginadosService(LogMovimentacaoPort logMovimentacaoPort) {
        this.logMovimentacaoPort = logMovimentacaoPort;
    }

    @Override
    public LogMovimentacaoPaginationResponse executar(LogMovimentacaoPaginationRequest request) {
        Sort sort = Sort.by(
                request.sortDirection().equalsIgnoreCase("desc") ?
                        Sort.Direction.DESC : Sort.Direction.ASC,
                request.sortBy()
        );

        Pageable pageable = PageRequest.of(request.page() - 1, request.size(), sort);
        Page<LogMovimentacao> logsPage = logMovimentacaoPort.buscarTodosPaginados(
                pageable,
                request.filter(),
                request.tipoPeca(),
                request.acao()
        );
        List<LogMovimentacaoOutput> content = logsPage.getContent().stream()
                .map(this::toOutput)
                .collect(Collectors.toList());
        return new LogMovimentacaoPaginationResponse(
                content,
                request.page(),
                logsPage.getTotalPages(),
                logsPage.getTotalElements(),
                request.size()
        );
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