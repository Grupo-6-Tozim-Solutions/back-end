package com.example.Tozin_Solutions_back_end.V2.core.application.movimentacaoEstoqueApplication.service;

import com.example.Tozin_Solutions_back_end.V2.core.application.movimentacaoEstoqueApplication.dto.MovimentacaoEstoqueOutput;
import com.example.Tozin_Solutions_back_end.V2.core.application.movimentacaoEstoqueApplication.port.MovimentacaoEstoquePort;
import com.example.Tozin_Solutions_back_end.V2.core.application.movimentacaoEstoqueApplication.useCase.ListarTodasMovimentacoesUseCase;
import com.example.Tozin_Solutions_back_end.V2.core.domain.MovimentacaoEstoqueDomain.MovimentacaoEstoque;

import java.util.List;
import java.util.stream.Collectors;

public class ListarTodasMovimentacoesService implements ListarTodasMovimentacoesUseCase {
    private final MovimentacaoEstoquePort movimentacaoEstoquePort;

    public ListarTodasMovimentacoesService(MovimentacaoEstoquePort movimentacaoEstoquePort) {
        this.movimentacaoEstoquePort = movimentacaoEstoquePort;
    }

    @Override
    public List<MovimentacaoEstoqueOutput> executar() {
        List<MovimentacaoEstoque> movimentacoes = movimentacaoEstoquePort.buscarTodasMovimentacoes();

        return movimentacoes.stream().map(this::toOutput).collect(Collectors.toList());
    }

    private MovimentacaoEstoqueOutput toOutput(MovimentacaoEstoque movimentacaoEstoque){
        return new MovimentacaoEstoqueOutput(
                movimentacaoEstoque.getId(),
                movimentacaoEstoque.getDataMovimentacao(),
                movimentacaoEstoque.getTipoPeca().name(),
                movimentacaoEstoque.getNomePeca().value(),
                movimentacaoEstoque.getQuantidadeEntrada().value(),
                movimentacaoEstoque.getQuantidadeSaida().value()
        );
    }
}
