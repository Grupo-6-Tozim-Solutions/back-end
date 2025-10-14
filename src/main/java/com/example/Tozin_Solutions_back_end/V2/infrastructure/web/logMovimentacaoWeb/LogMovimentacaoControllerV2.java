package com.example.Tozin_Solutions_back_end.V2.infrastructure.web.logMovimentacaoWeb;

import com.example.Tozin_Solutions_back_end.V2.core.application.logMovimentacaoApplication.dto.LogMovimentacaoOutput;
import com.example.Tozin_Solutions_back_end.V2.core.application.logMovimentacaoApplication.useCase.BuscarLogsPorPeriodoUseCase;
import com.example.Tozin_Solutions_back_end.V2.core.application.logMovimentacaoApplication.useCase.ListarTodosLogsMovimentacaoUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v2/logs-movimentacao")
@Tag(name = "Logs de Movimentação V2", description = "Endpoint para consulta de histórico de movimentações")
public class LogMovimentacaoControllerV2 {

    private final ListarTodosLogsMovimentacaoUseCase listarTodosLogsMovimentacaoUseCase;
    private final BuscarLogsPorPeriodoUseCase buscarLogsPorPeriodoUseCase;

    public LogMovimentacaoControllerV2(ListarTodosLogsMovimentacaoUseCase listarTodosLogsMovimentacaoUseCase,
                                       BuscarLogsPorPeriodoUseCase buscarLogsPorPeriodoUseCase) {
        this.listarTodosLogsMovimentacaoUseCase = listarTodosLogsMovimentacaoUseCase;
        this.buscarLogsPorPeriodoUseCase = buscarLogsPorPeriodoUseCase;
    }

    @GetMapping
    @Operation(summary = "Lista todas as movimentações registradas no histórico")
    public ResponseEntity<List<LogMovimentacaoOutput>> listarTodos() {
        List<LogMovimentacaoOutput> logs = listarTodosLogsMovimentacaoUseCase.executar();
        return ResponseEntity.ok(logs);
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
