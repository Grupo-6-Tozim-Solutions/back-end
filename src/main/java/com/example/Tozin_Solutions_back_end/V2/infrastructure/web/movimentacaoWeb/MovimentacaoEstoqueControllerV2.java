package com.example.Tozin_Solutions_back_end.V2.infrastructure.web.movimentacaoWeb;

import com.example.Tozin_Solutions_back_end.V2.core.application.movimentacaoEstoqueApplication.dto.MovimentacaoEstoqueInput;
import com.example.Tozin_Solutions_back_end.V2.core.application.movimentacaoEstoqueApplication.dto.MovimentacaoEstoqueOutput;
import com.example.Tozin_Solutions_back_end.V2.core.application.movimentacaoEstoqueApplication.useCase.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v2/movimentacoes-estoque")
@Tag(name = "Movimentações de Estoque V2", description = "Endpoint para gerenciamento de movimentações de estoque")
public class MovimentacaoEstoqueControllerV2 {

    private final RegistrarMovimentacaoEstoqueUseCase registrarMovimentacaoEstoqueUseCase;
    private final ListarTodasMovimentacoesUseCase listarTodasMovimentacoesUseCase;

    public MovimentacaoEstoqueControllerV2(RegistrarMovimentacaoEstoqueUseCase registrarMovimentacaoEstoqueUseCase,
                                           ListarTodasMovimentacoesUseCase listarTodasMovimentacoesUseCase) {
        this.registrarMovimentacaoEstoqueUseCase = registrarMovimentacaoEstoqueUseCase;
        this.listarTodasMovimentacoesUseCase = listarTodasMovimentacoesUseCase;
    }

    @PostMapping
    @Operation(summary = "Registra uma nova movimentação de estoque")
    public ResponseEntity<MovimentacaoEstoqueOutput> registrarMovimentacao(
            @RequestBody @Valid MovimentacaoEstoqueInput input) {
        MovimentacaoEstoqueOutput output = registrarMovimentacaoEstoqueUseCase.executar(input);
        return ResponseEntity.ok(output);
    }

    @GetMapping
    @Operation(summary = "Lista todas as movimentações de estoque")
    public ResponseEntity<List<MovimentacaoEstoqueOutput>> listarTodasMovimentacoes() {
        List<MovimentacaoEstoqueOutput> output = listarTodasMovimentacoesUseCase.executar();
        return ResponseEntity.ok(output);
    }
}