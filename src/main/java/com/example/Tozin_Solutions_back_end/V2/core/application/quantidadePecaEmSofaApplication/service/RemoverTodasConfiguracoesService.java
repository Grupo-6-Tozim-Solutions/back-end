package com.example.Tozin_Solutions_back_end.V2.core.application.quantidadePecaEmSofaApplication.service;

import com.example.Tozin_Solutions_back_end.V2.core.application.quantidadePecaEmSofaApplication.port.QuantidadePecaEmSofaRepositoryPort;
import com.example.Tozin_Solutions_back_end.V2.core.application.quantidadePecaEmSofaApplication.useCase.RemoverConfiguracaoUseCase;
import com.example.Tozin_Solutions_back_end.V2.core.application.quantidadePecaEmSofaApplication.useCase.RemoverTodasConfiguracoesUseCase;
import org.springframework.stereotype.Service;

@Service
public class RemoverTodasConfiguracoesService implements RemoverTodasConfiguracoesUseCase {

    private final QuantidadePecaEmSofaRepositoryPort configuracaoRepositoryPort;

    public RemoverTodasConfiguracoesService(QuantidadePecaEmSofaRepositoryPort configuracaoRepositoryPort) {
        this.configuracaoRepositoryPort = configuracaoRepositoryPort;
    }

    @Override
    public void executar(Long sofaId) {
        configuracaoRepositoryPort.deletarPorSofaId(sofaId);
    }
}
