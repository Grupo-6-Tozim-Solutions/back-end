package com.example.Tozin_Solutions_back_end.V2.core.application.movimentacaoEstoqueApplication.service;

import com.example.Tozin_Solutions_back_end.V2.core.application.movimentacaoEstoqueApplication.dto.MovimentacaoEstoqueInput;
import com.example.Tozin_Solutions_back_end.V2.core.application.movimentacaoEstoqueApplication.dto.MovimentacaoEstoqueOutput;
import com.example.Tozin_Solutions_back_end.V2.core.application.movimentacaoEstoqueApplication.port.MovimentacaoEstoquePort;
import com.example.Tozin_Solutions_back_end.V2.core.application.movimentacaoEstoqueApplication.useCase.RegistrarMovimentacaoEstoqueUseCase;import com.example.Tozin_Solutions_back_end.V2.core.application.pecaApplication.dto.PecaOutput;
import com.example.Tozin_Solutions_back_end.V2.core.domain.MovimentacaoEstoqueDomain.MovimentacaoEstoque;
import com.example.Tozin_Solutions_back_end.V2.core.domain.MovimentacaoEstoqueDomain.valueObjects.QuantidadeEntrada;
import com.example.Tozin_Solutions_back_end.V2.core.domain.MovimentacaoEstoqueDomain.valueObjects.QuantidadeSaida;
import com.example.Tozin_Solutions_back_end.V2.core.domain.PecaDomain.valueObjects.NomePeca;
import com.example.Tozin_Solutions_back_end.V2.core.domain.PecaDomain.valueObjects.TipoPeca;
import org.springframework.stereotype.Service;

@Service
public class RegistrarMovimentacaoEstoqueService implements RegistrarMovimentacaoEstoqueUseCase {
    private final MovimentacaoEstoquePort movimentacaoEstoquePort;

    public RegistrarMovimentacaoEstoqueService(MovimentacaoEstoquePort movimentacaoEstoquePort) {
        this.movimentacaoEstoquePort = movimentacaoEstoquePort;
    }

    @Override
    public MovimentacaoEstoqueOutput executar(MovimentacaoEstoqueInput movimentacaoEstoqueInput) {
        TipoPeca tipoPeca = TipoPeca.fromString(movimentacaoEstoqueInput.tipoPeca());
        NomePeca nomePeca = new NomePeca(movimentacaoEstoqueInput.nomePeca());
        QuantidadeEntrada quantidadeEntrada = new QuantidadeEntrada(movimentacaoEstoqueInput.quantidadeEntrada());
        QuantidadeSaida quantidadeSaida = new QuantidadeSaida(movimentacaoEstoqueInput.quantidadeSaida());

        MovimentacaoEstoque movimentacaoEstoque = MovimentacaoEstoque.registrarMovimentacao(tipoPeca, nomePeca, quantidadeEntrada, quantidadeSaida);
        MovimentacaoEstoque movimentacaoRegistrada = movimentacaoEstoquePort.registrar(movimentacaoEstoque);


        return new MovimentacaoEstoqueOutput(
                movimentacaoRegistrada.getId(),
                movimentacaoRegistrada.getDataMovimentacao(),
                movimentacaoRegistrada.getTipoPeca().name(),
                movimentacaoRegistrada.getNomePeca().value(),
                movimentacaoRegistrada.getQuantidadeEntrada().value(),
                movimentacaoRegistrada.getQuantidadeSaida().value()
        );
    }
}
