package com.example.Tozin_Solutions_back_end.V2.core.application.sofaApplication.service;

import com.example.Tozin_Solutions_back_end.V2.core.application.sofaApplication.port.SofaRepositoryPort;
import com.example.Tozin_Solutions_back_end.V2.core.application.sofaApplication.useCase.DeletarSofaUseCase;
import com.example.Tozin_Solutions_back_end.V2.core.domain.SofaDomain.exception.SofaNaoEncontradoException;
import org.springframework.stereotype.Service;

@Service
public class DeletarSofaService implements DeletarSofaUseCase {

    private final SofaRepositoryPort sofaRepositoryPort;

    public DeletarSofaService(SofaRepositoryPort sofaRepositoryPort) {
        this.sofaRepositoryPort = sofaRepositoryPort;
    }

    @Override
    public void executar(Long id) {
        if (!sofaRepositoryPort.buscarPorId(id).isPresent()) {
            throw new SofaNaoEncontradoException(id);
        }
        sofaRepositoryPort.deletarPorId(id);
    }
}
