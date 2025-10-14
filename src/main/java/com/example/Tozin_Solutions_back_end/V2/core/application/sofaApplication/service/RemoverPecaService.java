package com.example.Tozin_Solutions_back_end.V2.core.application.sofaApplication.service;

import com.example.Tozin_Solutions_back_end.V2.core.application.quantidadePecaEmSofaApplication.port.QuantidadePecaEmSofaRepositoryPort;
import com.example.Tozin_Solutions_back_end.V2.core.application.sofaApplication.dto.SofaOutput;
import com.example.Tozin_Solutions_back_end.V2.core.application.sofaApplication.port.SofaRepositoryPort;
import com.example.Tozin_Solutions_back_end.V2.core.application.sofaApplication.useCase.RemoverPecaUseCase;
import com.example.Tozin_Solutions_back_end.V2.core.domain.SofaDomain.Sofa;
import com.example.Tozin_Solutions_back_end.V2.core.domain.SofaDomain.exception.SofaNaoEncontradoException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class RemoverPecaService implements RemoverPecaUseCase {

    private final SofaRepositoryPort sofaRepositoryPort;
    private final QuantidadePecaEmSofaRepositoryPort configuracaoRepositoryPort;

    public RemoverPecaService(SofaRepositoryPort sofaRepositoryPort,
                              QuantidadePecaEmSofaRepositoryPort configuracaoRepositoryPort) {
        this.sofaRepositoryPort = sofaRepositoryPort;
        this.configuracaoRepositoryPort = configuracaoRepositoryPort;
    }

    @Override
    @Transactional
    public SofaOutput executar(Long sofaId, Long pecaId) {
        Sofa sofa = sofaRepositoryPort.buscarPorId(sofaId)
                .orElseThrow(() -> new SofaNaoEncontradoException(sofaId));

        configuracaoRepositoryPort.deletarPorSofaIdEPecaId(sofaId, pecaId);

        Sofa sofaAtualizado = sofaRepositoryPort.buscarPorId(sofaId)
                .orElseThrow(() -> new SofaNaoEncontradoException(sofaId));

        return new SofaOutput(
                sofaAtualizado.getId(),
                sofaAtualizado.getModelo().value(),
                sofaAtualizado.getCaminhoImagem().value(),
                sofaAtualizado.getDataCadastro()
        );
    }
}
