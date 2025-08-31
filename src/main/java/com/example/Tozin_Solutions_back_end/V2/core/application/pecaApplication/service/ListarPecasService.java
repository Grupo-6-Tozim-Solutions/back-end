package com.example.Tozin_Solutions_back_end.V2.core.application.pecaApplication.service;


import com.example.Tozin_Solutions_back_end.V2.core.application.pecaApplication.dto.PecaOutput;
import com.example.Tozin_Solutions_back_end.V2.core.application.pecaApplication.port.PecaRepositoryPort;
import com.example.Tozin_Solutions_back_end.V2.core.application.pecaApplication.useCase.ListarPecasUseCase;
import com.example.Tozin_Solutions_back_end.V2.core.domain.PecaDomain.Peca;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListarPecasService implements ListarPecasUseCase {

    private final PecaRepositoryPort pecaRepositoryPort;

    public ListarPecasService(PecaRepositoryPort pecaRepositoryPort) {
        this.pecaRepositoryPort = pecaRepositoryPort;
    }

    @Override
    public List<PecaOutput> executar() {
        List<Peca> pecas = pecaRepositoryPort.buscarTodas();
        return pecas.stream()
                .map(this::toOutput)
                .collect(Collectors.toList());
    }

    private PecaOutput toOutput(Peca peca) {
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