package com.example.Tozin_Solutions_back_end.V2.core.application.sofaApplication.service;

import com.example.Tozin_Solutions_back_end.V2.core.application.pecaApplication.port.PecaRepositoryPort;
import com.example.Tozin_Solutions_back_end.V2.core.application.quantidadePecaEmSofaApplication.port.QuantidadePecaEmSofaRepositoryPort;
import com.example.Tozin_Solutions_back_end.V2.core.application.sofaApplication.dto.PecaSofaOutput;
import com.example.Tozin_Solutions_back_end.V2.core.application.sofaApplication.useCase.ListarPecasPorSofaUseCase;
import com.example.Tozin_Solutions_back_end.V2.core.domain.PecaDomain.Peca;
import com.example.Tozin_Solutions_back_end.V2.core.domain.QuantidadePecaEmSofaDomain.QuantidadePecaEmSofa;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListarPecasPorSofaService implements ListarPecasPorSofaUseCase {

    private final QuantidadePecaEmSofaRepositoryPort configuracaoRepositoryPort;
    private final PecaRepositoryPort pecaRepositoryPort;

    public ListarPecasPorSofaService(QuantidadePecaEmSofaRepositoryPort configuracaoRepositoryPort,
                                     PecaRepositoryPort pecaRepositoryPort) {
        this.configuracaoRepositoryPort = configuracaoRepositoryPort;
        this.pecaRepositoryPort = pecaRepositoryPort;
    }

    @Override
    public List<PecaSofaOutput> executar(Long sofaId) {
        List<QuantidadePecaEmSofa> configuracoes = configuracaoRepositoryPort.buscarPorSofaId(sofaId);

        return configuracoes.stream()
                .map(config -> {
                    Peca peca = pecaRepositoryPort.buscarPorId(config.getPecaId())
                            .orElseThrow(() -> new RuntimeException("Peça não encontrada: " + config.getPecaId()));

                    return new PecaSofaOutput(
                            config.getId(),
                            config.getPecaId(),
                            peca.getNome().value(),
                            config.getQuantidade().value(),
                            peca.getTipo().name()
                    );
                })
                .collect(Collectors.toList());
    }
}
