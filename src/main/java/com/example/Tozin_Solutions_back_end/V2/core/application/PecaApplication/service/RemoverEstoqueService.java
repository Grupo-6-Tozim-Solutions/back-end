package com.example.Tozin_Solutions_back_end.V2.core.application.PecaApplication.service;

import com.example.Tozin_Solutions_back_end.V2.core.application.PecaApplication.dto.PecaOutput;
import com.example.Tozin_Solutions_back_end.V2.core.application.PecaApplication.port.PecaRepositoryPort;
import com.example.Tozin_Solutions_back_end.V2.core.application.PecaApplication.useCase.RemoverEstoqueUseCase;
import com.example.Tozin_Solutions_back_end.V2.core.domain.PecaDomain.Peca;
import com.example.Tozin_Solutions_back_end.V2.core.domain.PecaDomain.excpetion.PecaNaoEncontradaException;
import com.example.Tozin_Solutions_back_end.V2.core.domain.PecaDomain.valueObjects.Quantidade;
import org.springframework.stereotype.Service;

@Service
public class RemoverEstoqueService implements RemoverEstoqueUseCase {

    private final PecaRepositoryPort pecaRepositoryPort;

    public RemoverEstoqueService(PecaRepositoryPort pecaRepositoryPort) {
        this.pecaRepositoryPort = pecaRepositoryPort;
    }

    @Override
    public PecaOutput executar(Long id, Double quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero");
        }

        Peca peca = pecaRepositoryPort.buscarPorId(id)
                .orElseThrow(() -> new PecaNaoEncontradaException(id));

        peca.removerEstoque(new Quantidade(quantidade));
        Peca pecaAtualizada = pecaRepositoryPort.salvar(peca);

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
