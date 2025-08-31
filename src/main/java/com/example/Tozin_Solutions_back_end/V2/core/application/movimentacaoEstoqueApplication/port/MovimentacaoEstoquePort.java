package com.example.Tozin_Solutions_back_end.V2.core.application.movimentacaoEstoqueApplication.port;

import com.example.Tozin_Solutions_back_end.V2.core.domain.MovimentacaoEstoqueDomain.MovimentacaoEstoque;

import java.util.List;

public interface MovimentacaoEstoquePort {
    MovimentacaoEstoque registrar(MovimentacaoEstoque movimentacaoEstoque);
    List<MovimentacaoEstoque> buscarTodasMovimentacoes();
    List<MovimentacaoEstoque> buscarMovimentacoesPorPeca(String nomePeca);
}
