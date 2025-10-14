package com.example.Tozin_Solutions_back_end.V2.core.application.pecaApplication.service;

import com.example.Tozin_Solutions_back_end.V2.core.application.movimentacaoEstoqueApplication.dto.MovimentacaoEstoqueInput;
import com.example.Tozin_Solutions_back_end.V2.core.application.movimentacaoEstoqueApplication.useCase.RegistrarMovimentacaoEstoqueUseCase;
import com.example.Tozin_Solutions_back_end.V2.core.application.pecaApplication.dto.PecaOutput;
import com.example.Tozin_Solutions_back_end.V2.core.application.pecaApplication.port.PecaRepositoryPort;
import com.example.Tozin_Solutions_back_end.V2.core.application.pecaApplication.useCase.RemoverEstoqueUseCase;
import com.example.Tozin_Solutions_back_end.V2.core.domain.PecaDomain.Peca;
import com.example.Tozin_Solutions_back_end.V2.core.domain.PecaDomain.excpetion.PecaNaoEncontradaException;
import com.example.Tozin_Solutions_back_end.V2.core.domain.PecaDomain.valueObjects.Quantidade;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


@Service
public class RemoverEstoqueService implements RemoverEstoqueUseCase {

    private final PecaRepositoryPort pecaRepositoryPort;
    private final RegistrarMovimentacaoEstoqueUseCase registrarMovimentacaoEstoqueUseCase;

    public RemoverEstoqueService(PecaRepositoryPort pecaRepositoryPort,
                                 RegistrarMovimentacaoEstoqueUseCase registrarMovimentacaoEstoqueUseCase) {
        this.pecaRepositoryPort = pecaRepositoryPort;
        this.registrarMovimentacaoEstoqueUseCase = registrarMovimentacaoEstoqueUseCase;
    }

    @Override
    @Transactional
    public PecaOutput executar(Long id, Double quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero");
        }

        Peca peca = pecaRepositoryPort.buscarPorId(id)
                .orElseThrow(() -> new PecaNaoEncontradaException(id));

        peca.removerEstoque(new Quantidade(quantidade));
        Peca pecaAtualizada = pecaRepositoryPort.salvar(peca);

        // ✅ Registrar movimentação de SAÍDA
        MovimentacaoEstoqueInput movimentacaoInput = new MovimentacaoEstoqueInput(
                peca.getId(),
                0.0,        // quantidadeEntrada
                quantidade  // quantidadeSaida
        );
        registrarMovimentacaoEstoqueUseCase.executar(movimentacaoInput);

        return new PecaOutput(
                pecaAtualizada.getId(),
                pecaAtualizada.getNome().value(),
                pecaAtualizada.getQuantidadeEstoque().value(),
                pecaAtualizada.getQuantidadeMinima().value(),
                pecaAtualizada.getTipo().name(),
                pecaAtualizada.getDataCadastro(),
                pecaAtualizada.isEstoqueCritico()
        );
    }
}
