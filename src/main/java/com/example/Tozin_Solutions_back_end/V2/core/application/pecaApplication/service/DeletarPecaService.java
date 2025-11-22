package com.example.Tozin_Solutions_back_end.V2.core.application.pecaApplication.service;

import com.example.Tozin_Solutions_back_end.V2.core.application.pecaApplication.port.PecaRepositoryPort;
import com.example.Tozin_Solutions_back_end.V2.core.application.pecaApplication.useCase.DeletarPecaUseCase;
import com.example.Tozin_Solutions_back_end.V2.core.domain.PecaDomain.excpetion.PecaNaoEncontradaException;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.CacheEvict;

@Service
@CacheEvict(value = "pecasPaginadas", allEntries = true)
public class DeletarPecaService implements DeletarPecaUseCase {

    private final PecaRepositoryPort pecaRepositoryPort;

    public DeletarPecaService(PecaRepositoryPort pecaRepositoryPort) {
        this.pecaRepositoryPort = pecaRepositoryPort;
    }

    @Override

    public void executar(Long id) {
        if (!pecaRepositoryPort.buscarPorId(id).isPresent()) {
            throw new PecaNaoEncontradaException(id);
        }
        pecaRepositoryPort.deletarPorId(id);
    }
}
