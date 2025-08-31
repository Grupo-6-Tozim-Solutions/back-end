package com.example.Tozin_Solutions_back_end.V2.core.application.quantidadePecaEmSofaApplication.service;

import com.example.Tozin_Solutions_back_end.V2.core.application.quantidadePecaEmSofaApplication.port.QuantidadePecaEmSofaRepositoryPort;
import com.example.Tozin_Solutions_back_end.V2.core.application.quantidadePecaEmSofaApplication.useCase.RemoverConfiguracaoUseCase;
import org.springframework.stereotype.Service;

@Service
public class RemoverConfiguracaoService implements RemoverConfiguracaoUseCase {

    private final QuantidadePecaEmSofaRepositoryPort configuracaoRepositoryPort;

    public RemoverConfiguracaoService(QuantidadePecaEmSofaRepositoryPort configuracaoRepositoryPort) {
        this.configuracaoRepositoryPort = configuracaoRepositoryPort;
    }

    @Override
    public void executar(Long id) {
        configuracaoRepositoryPort.deletarPorId(id);
    }
}
