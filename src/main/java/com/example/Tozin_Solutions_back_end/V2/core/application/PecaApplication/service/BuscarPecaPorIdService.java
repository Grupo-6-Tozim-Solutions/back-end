package com.example.Tozin_Solutions_back_end.V2.core.application.PecaApplication.service;

import com.example.Tozin_Solutions_back_end.V2.core.application.PecaApplication.dto.PecaOutput;
import com.example.Tozin_Solutions_back_end.V2.core.application.PecaApplication.port.PecaRepositoryPort;
import com.example.Tozin_Solutions_back_end.V2.core.application.PecaApplication.useCase.BuscarPecaPorIdUseCase;
import com.example.Tozin_Solutions_back_end.V2.core.domain.PecaDomain.Peca;
import com.example.Tozin_Solutions_back_end.V2.core.domain.PecaDomain.excpetion.PecaNaoEncontradaException;
import org.springframework.stereotype.Service;

@Service
public class BuscarPecaPorIdService implements BuscarPecaPorIdUseCase {

    private final PecaRepositoryPort pecaRepositoryPort;

    public BuscarPecaPorIdService(PecaRepositoryPort pecaRepositoryPort) {
        this.pecaRepositoryPort = pecaRepositoryPort;
    }

    @Override
    public PecaOutput executar(Long id) {
        Peca peca = pecaRepositoryPort.buscarPorId(id)
                .orElseThrow(() -> new PecaNaoEncontradaException(id));

        return new PecaOutput(
                peca.getId(),
                peca.getNome().value(),
                peca.getQuantidadeEstoque().value(),
                peca.getQuantidadeMinima().value(),
                peca.getTipo().name(),
                peca.getDataCadastro(),
                peca.isEstoqueCritico()
        );
    }
}
