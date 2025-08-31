package com.example.Tozin_Solutions_back_end.V2.core.application.quantidadePecaEmSofaApplication.service;

import com.example.Tozin_Solutions_back_end.V2.core.application.quantidadePecaEmSofaApplication.dto.ConfiguracaoPecaSofaOutput;
import com.example.Tozin_Solutions_back_end.V2.core.application.quantidadePecaEmSofaApplication.port.QuantidadePecaEmSofaRepositoryPort;
import com.example.Tozin_Solutions_back_end.V2.core.application.quantidadePecaEmSofaApplication.useCase.ListarConfiguracoesPorSofaUseCase;
import com.example.Tozin_Solutions_back_end.V2.core.domain.QuantidadePecaEmSofaDomain.QuantidadePecaEmSofa;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListarConfiguracoesPorSofaService implements ListarConfiguracoesPorSofaUseCase {

    private final QuantidadePecaEmSofaRepositoryPort configuracaoRepositoryPort;

    public ListarConfiguracoesPorSofaService(QuantidadePecaEmSofaRepositoryPort configuracaoRepositoryPort) {
        this.configuracaoRepositoryPort = configuracaoRepositoryPort;
    }

    @Override
    public List<ConfiguracaoPecaSofaOutput> executar(Long sofaId) {
        List<QuantidadePecaEmSofa> configuracoes = configuracaoRepositoryPort.buscarPorSofaId(sofaId);
        return configuracoes.stream()
                .map(this::toOutput)
                .collect(Collectors.toList());
    }

    private ConfiguracaoPecaSofaOutput toOutput(QuantidadePecaEmSofa config) {
        return new ConfiguracaoPecaSofaOutput(
                config.getId(),
                config.getSofaId(),
                config.getPecaId(),
                config.getQuantidade().value()
        );
    }
}