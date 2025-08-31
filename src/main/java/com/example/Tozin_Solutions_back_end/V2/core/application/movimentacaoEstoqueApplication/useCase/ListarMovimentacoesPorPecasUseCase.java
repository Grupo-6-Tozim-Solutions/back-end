package com.example.Tozin_Solutions_back_end.V2.core.application.movimentacaoEstoqueApplication.useCase;

import com.example.Tozin_Solutions_back_end.V2.core.application.movimentacaoEstoqueApplication.dto.MovimentacaoEstoqueOutput;

import java.util.List;

public interface ListarMovimentacoesPorPecasUseCase {
    List<MovimentacaoEstoqueOutput> executar(String nomePeca);
}
