package com.example.Tozin_Solutions_back_end.V2.core.application.pecaApplication.service;

import com.example.Tozin_Solutions_back_end.V2.core.application.movimentacaoEstoqueApplication.dto.MovimentacaoEstoqueInput;
import com.example.Tozin_Solutions_back_end.V2.core.application.movimentacaoEstoqueApplication.useCase.RegistrarMovimentacaoEstoqueUseCase;
import com.example.Tozin_Solutions_back_end.V2.core.application.pecaApplication.dto.PecaOutput;
import com.example.Tozin_Solutions_back_end.V2.core.application.pecaApplication.port.PecaRepositoryPort;
import com.example.Tozin_Solutions_back_end.V2.core.application.pecaApplication.useCase.AdicionarEstoqueUseCase;
import com.example.Tozin_Solutions_back_end.V2.core.domain.PecaDomain.Peca;
import com.example.Tozin_Solutions_back_end.V2.core.domain.PecaDomain.excpetion.PecaNaoEncontradaException;
import com.example.Tozin_Solutions_back_end.V2.core.domain.PecaDomain.valueObjects.Quantidade;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class AdicionarEstoqueService implements AdicionarEstoqueUseCase {

    private final PecaRepositoryPort pecaRepositoryPort;
    private final RegistrarMovimentacaoEstoqueUseCase registrarMovimentacaoEstoqueUseCase;

    public AdicionarEstoqueService(PecaRepositoryPort pecaRepositoryPort,
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

        peca.adicionarEstoque(new Quantidade(quantidade));
        Peca pecaAtualizada = pecaRepositoryPort.salvar(peca);

        // ✅ Registrar movimentação de ENTRADA
        MovimentacaoEstoqueInput movimentacaoInput = new MovimentacaoEstoqueInput(
                peca.getId(),
                quantidade, // quantidadeEntrada
                0.0        // quantidadeSaida
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
