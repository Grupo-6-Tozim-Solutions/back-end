// No LogMovimentacaoControllerV2.java
package com.example.Tozin_Solutions_back_end.V2.infrastructure.web.logMovimentacaoWeb;

import com.example.Tozin_Solutions_back_end.V2.core.application.logMovimentacaoApplication.dto.*;
import com.example.Tozin_Solutions_back_end.V2.core.application.logMovimentacaoApplication.useCase.BuscarLogsPorPeriodoUseCase;
import com.example.Tozin_Solutions_back_end.V2.core.application.logMovimentacaoApplication.useCase.ListarLogsMovimentacaoPaginadosUseCase;
import com.example.Tozin_Solutions_back_end.V2.core.application.logMovimentacaoApplication.useCase.ListarTodosLogsMovimentacaoUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v2/logs-movimentacao")
@Tag(name = "Logs de Movimentação V2", description = "Endpoint para consulta de histórico de movimentações")
public class LogMovimentacaoControllerV2 {

    private final ListarTodosLogsMovimentacaoUseCase listarTodosLogsMovimentacaoUseCase;
    private final BuscarLogsPorPeriodoUseCase buscarLogsPorPeriodoUseCase;
    private final ListarLogsMovimentacaoPaginadosUseCase listarLogsMovimentacaoPaginadosUseCase;

    public LogMovimentacaoControllerV2(ListarTodosLogsMovimentacaoUseCase listarTodosLogsMovimentacaoUseCase,
                                       BuscarLogsPorPeriodoUseCase buscarLogsPorPeriodoUseCase,
                                       ListarLogsMovimentacaoPaginadosUseCase listarLogsMovimentacaoPaginadosUseCase) {
        this.listarTodosLogsMovimentacaoUseCase = listarTodosLogsMovimentacaoUseCase;
        this.buscarLogsPorPeriodoUseCase = buscarLogsPorPeriodoUseCase;
        this.listarLogsMovimentacaoPaginadosUseCase = listarLogsMovimentacaoPaginadosUseCase;
    }

    @GetMapping
    @Operation(summary = "Lista todas as movimentações registradas no histórico")
    public ResponseEntity<List<LogMovimentacaoOutput>> listarTodos() {
        List<LogMovimentacaoOutput> logs = listarTodosLogsMovimentacaoUseCase.executar();
        return ResponseEntity.ok(logs);
    }

    @GetMapping("/listarPaginado")
    @Operation(summary = "Lista movimentações com paginação")
    public ResponseEntity<LogMovimentacaoPaginationResponse> listarPaginado(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "dataMovimentacao") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDirection,
            @RequestParam(required = false) String filter,
            @RequestParam(required = false) String tipoPeca,
            @RequestParam(required = false) String acao) {

        LogMovimentacaoPaginationRequest request = new LogMovimentacaoPaginationRequest(
                page, size, sortBy, sortDirection,
                filter != null ? filter : "",
                tipoPeca != null ? tipoPeca : "",
                acao != null ? acao : ""
        );

        LogMovimentacaoPaginationResponse response = listarLogsMovimentacaoPaginadosUseCase.executar(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/filtro/periodo")
    @Operation(summary = "Filtra movimentações por período")
    public ResponseEntity<List<LogMovimentacaoOutput>> filtrarPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fim) {
        List<LogMovimentacaoOutput> logs = buscarLogsPorPeriodoUseCase.executar(inicio, fim);
        return ResponseEntity.ok(logs);
    }
}