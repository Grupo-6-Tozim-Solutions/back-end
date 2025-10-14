package com.example.Tozin_Solutions_back_end.V2.core.application.movimentacaoEstoqueApplication.service;

import com.example.Tozin_Solutions_back_end.V2.core.application.movimentacaoEstoqueApplication.dto.MovimentacaoEstoqueOutput;
import com.example.Tozin_Solutions_back_end.V2.core.application.movimentacaoEstoqueApplication.port.MovimentacaoEstoquePort;
import com.example.Tozin_Solutions_back_end.V2.core.application.movimentacaoEstoqueApplication.useCase.ListarTodasMovimentacoesUseCase;
import com.example.Tozin_Solutions_back_end.V2.core.domain.MovimentacaoEstoqueDomain.MovimentacaoEstoque;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class ListarTodasMovimentacoesService implements ListarTodasMovimentacoesUseCase {

    private final MovimentacaoEstoquePort movimentacaoEstoquePort;

    public ListarTodasMovimentacoesService(MovimentacaoEstoquePort movimentacaoEstoquePort) {
        this.movimentacaoEstoquePort = movimentacaoEstoquePort;
    }

    @Override
    public List<MovimentacaoEstoqueOutput> executar() {
        List<MovimentacaoEstoque> movimentacoes = movimentacaoEstoquePort.buscarTodas();

        return movimentacoes.stream()
                .map(this::toOutput)
                .collect(Collectors.toList());
    }

    private MovimentacaoEstoqueOutput toOutput(MovimentacaoEstoque movimentacao) {
        return new MovimentacaoEstoqueOutput(
                movimentacao.getId(),
                movimentacao.getDataMovimentacao(),
                movimentacao.getPeca().getId(),
                movimentacao.getPeca().getNome().value(),
                movimentacao.getPeca().getTipo().name(),
                movimentacao.getQuantidadeEntrada().value(),
                movimentacao.getQuantidadeSaida().value()
        );
    }
}
