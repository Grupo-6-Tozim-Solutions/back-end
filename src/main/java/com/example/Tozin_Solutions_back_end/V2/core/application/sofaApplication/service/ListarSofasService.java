package com.example.Tozin_Solutions_back_end.V2.core.application.sofaApplication.service;

import com.example.Tozin_Solutions_back_end.V2.core.application.sofaApplication.dto.SofaOutput;
import com.example.Tozin_Solutions_back_end.V2.core.application.sofaApplication.port.SofaRepositoryPort;
import com.example.Tozin_Solutions_back_end.V2.core.application.sofaApplication.useCase.ListarSofasUseCase;
import com.example.Tozin_Solutions_back_end.V2.core.domain.SofaDomain.Sofa;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListarSofasService implements ListarSofasUseCase {

    private final SofaRepositoryPort sofaRepositoryPort;

    public ListarSofasService(SofaRepositoryPort sofaRepositoryPort) {
        this.sofaRepositoryPort = sofaRepositoryPort;
    }

    @Override
    public List<SofaOutput> executar() {
        List<Sofa> sofas = sofaRepositoryPort.buscarTodos();
        return sofas.stream()
                .map(this::toOutput)
                .collect(Collectors.toList());
    }

    private SofaOutput toOutput(Sofa sofa) {
        return new SofaOutput(
                sofa.getId(),
                sofa.getModelo().value(),
                sofa.getCaminhoImagem().value(),
                sofa.getDataCadastro()
        );
    }
}