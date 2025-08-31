package com.example.Tozin_Solutions_back_end.V2.core.application.sofaApplication.service;

import com.example.Tozin_Solutions_back_end.V2.core.application.sofaApplication.dto.SofaOutput;
import com.example.Tozin_Solutions_back_end.V2.core.application.sofaApplication.port.SofaRepositoryPort;
import com.example.Tozin_Solutions_back_end.V2.core.application.sofaApplication.useCase.BuscarSofaPorIdUseCase;
import com.example.Tozin_Solutions_back_end.V2.core.domain.SofaDomain.Sofa;
import com.example.Tozin_Solutions_back_end.V2.core.domain.SofaDomain.exception.SofaNaoEncontradoException;
import org.springframework.stereotype.Service;

@Service
public class BuscarSofaPorIdService implements BuscarSofaPorIdUseCase {

    private final SofaRepositoryPort sofaRepositoryPort;

    public BuscarSofaPorIdService(SofaRepositoryPort sofaRepositoryPort) {
        this.sofaRepositoryPort = sofaRepositoryPort;
    }

    @Override
    public SofaOutput executar(Long id) {
        Sofa sofa = sofaRepositoryPort.buscarPorId(id)
                .orElseThrow(() -> new SofaNaoEncontradoException(id));

        return new SofaOutput(
                sofa.getId(),
                sofa.getModelo().value(),
                sofa.getCaminhoImagem().value(),
                sofa.getDataCadastro()
        );
    }
}
