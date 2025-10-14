package com.example.Tozin_Solutions_back_end.V2.core.application.movimentacaoEstoqueApplication.service;

import com.example.Tozin_Solutions_back_end.V2.core.application.movimentacaoEstoqueApplication.dto.MovimentacaoEstoqueInput;
import com.example.Tozin_Solutions_back_end.V2.core.application.movimentacaoEstoqueApplication.dto.MovimentacaoEstoqueOutput;
import com.example.Tozin_Solutions_back_end.V2.core.application.movimentacaoEstoqueApplication.port.MovimentacaoEstoquePort;
import com.example.Tozin_Solutions_back_end.V2.core.application.movimentacaoEstoqueApplication.useCase.RegistrarMovimentacaoEstoqueUseCase;import com.example.Tozin_Solutions_back_end.V2.core.application.pecaApplication.dto.PecaOutput;
import com.example.Tozin_Solutions_back_end.V2.core.application.pecaApplication.port.PecaRepositoryPort;
import com.example.Tozin_Solutions_back_end.V2.core.domain.MovimentacaoEstoqueDomain.MovimentacaoEstoque;
import com.example.Tozin_Solutions_back_end.V2.core.domain.MovimentacaoEstoqueDomain.valueObjects.QuantidadeEntrada;
import com.example.Tozin_Solutions_back_end.V2.core.domain.MovimentacaoEstoqueDomain.valueObjects.QuantidadeSaida;
import com.example.Tozin_Solutions_back_end.V2.core.domain.PecaDomain.Peca;
import com.example.Tozin_Solutions_back_end.V2.core.domain.PecaDomain.excpetion.PecaNaoEncontradaException;
import com.example.Tozin_Solutions_back_end.V2.core.domain.PecaDomain.valueObjects.NomePeca;
import com.example.Tozin_Solutions_back_end.V2.core.domain.PecaDomain.valueObjects.TipoPeca;
import org.springframework.stereotype.Service;

@Service
public class RegistrarMovimentacaoEstoqueService implements com.example.Tozin_Solutions_back_end.V2.core.application.movimentacaoEstoqueApplication.useCase.RegistrarMovimentacaoEstoqueUseCase {

    private final MovimentacaoEstoquePort movimentacaoEstoquePort;
    private final PecaRepositoryPort pecaRepositoryPort;

    public RegistrarMovimentacaoEstoqueService(MovimentacaoEstoquePort movimentacaoEstoquePort,
                                               PecaRepositoryPort pecaRepositoryPort) {
        this.movimentacaoEstoquePort = movimentacaoEstoquePort;
        this.pecaRepositoryPort = pecaRepositoryPort;
    }

    @Override
    public MovimentacaoEstoqueOutput executar(MovimentacaoEstoqueInput input) {
        // ✅ DEBUG DETALHADO
        System.out.println("=== DEBUG REGISTRAR MOVIMENTACAO ===");
        System.out.println("Input recebido:");
        System.out.println("  pecaId: " + input.pecaId());
        System.out.println("  quantidadeEntrada: " + input.quantidadeEntrada());
        System.out.println("  quantidadeSaida: " + input.quantidadeSaida());
        System.out.println("  Tipo quantidadeSaida: " + (input.quantidadeSaida() != null ? input.quantidadeSaida().getClass().getName() : "null"));

        // Buscar peça
        Peca peca = pecaRepositoryPort.buscarPorId(input.pecaId())
                .orElseThrow(() -> new PecaNaoEncontradaException(input.pecaId()));

        MovimentacaoEstoque movimentacao;
        if (input.quantidadeEntrada() > 0) {
            System.out.println("  Criando ENTRADA com valor: " + input.quantidadeEntrada());
            movimentacao = MovimentacaoEstoque.registrarEntrada(
                    peca,
                    new QuantidadeEntrada(input.quantidadeEntrada())
            );
        } else if (input.quantidadeSaida() > 0) {
            System.out.println("  Criando SAIDA com valor: " + input.quantidadeSaida());
            // ✅ VERIFICAÇÃO EXTRA ANTES de criar QuantidadeSaida
            if (input.quantidadeSaida() <= 0) {
                System.out.println("  ERRO: quantidadeSaida é <= 0: " + input.quantidadeSaida());
                throw new IllegalArgumentException("Quantidade de saída deve ser positiva: " + input.quantidadeSaida());
            }
            movimentacao = MovimentacaoEstoque.registrarSaida(
                    peca,
                    new QuantidadeSaida(input.quantidadeSaida())
            );
        } else {
            throw new IllegalArgumentException("Pelo menos uma quantidade (entrada ou saída) deve ser positiva");
        }

        MovimentacaoEstoque movimentacaoSalva = movimentacaoEstoquePort.salvar(movimentacao);

        System.out.println("=== FIM DEBUG REGISTRAR MOVIMENTACAO ===");
        return toOutput(movimentacaoSalva);
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
