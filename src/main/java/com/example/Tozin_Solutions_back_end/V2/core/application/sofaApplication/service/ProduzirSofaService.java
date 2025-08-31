package com.example.Tozin_Solutions_back_end.V2.core.application.sofaApplication.service;

import com.example.Tozin_Solutions_back_end.V2.core.application.pecaApplication.port.PecaRepositoryPort;
import com.example.Tozin_Solutions_back_end.V2.core.application.quantidadePecaEmSofaApplication.port.QuantidadePecaEmSofaRepositoryPort;
import com.example.Tozin_Solutions_back_end.V2.core.application.sofaApplication.useCase.ProduzirSofaUseCase;
import com.example.Tozin_Solutions_back_end.V2.core.domain.PecaDomain.Peca;
import com.example.Tozin_Solutions_back_end.V2.core.domain.PecaDomain.valueObjects.Quantidade;
import com.example.Tozin_Solutions_back_end.V2.core.domain.QuantidadePecaEmSofaDomain.QuantidadePecaEmSofa;
import com.example.Tozin_Solutions_back_end.V2.core.domain.usuarioDomain.excepetion.DomainException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProduzirSofaService implements ProduzirSofaUseCase {

    private final QuantidadePecaEmSofaRepositoryPort configuracaoRepositoryPort;
    private final PecaRepositoryPort pecaRepositoryPort;

    public ProduzirSofaService(QuantidadePecaEmSofaRepositoryPort configuracaoRepositoryPort,
                               PecaRepositoryPort pecaRepositoryPort) {
        this.configuracaoRepositoryPort = configuracaoRepositoryPort;
        this.pecaRepositoryPort = pecaRepositoryPort;
    }

    @Override
    public void executar(Long sofaId, Integer quantidade) {
        if (quantidade <= 0) {
            throw new DomainException("Quantidade de produção deve ser maior que zero");
        }

        List<QuantidadePecaEmSofa> configuracoes = configuracaoRepositoryPort.buscarPorSofaId(sofaId);

        if (configuracoes.isEmpty()) {
            throw new DomainException("Sofá não possui peças configuradas");
        }

        for (QuantidadePecaEmSofa config : configuracoes) {
            Peca peca = pecaRepositoryPort.buscarPorId(config.getPecaId())
                    .orElseThrow(() -> new DomainException("Peça não encontrada: " + config.getPecaId()));

            Double quantidadeNecessaria = config.getQuantidade().value() * quantidade;

            if (peca.getQuantidadeEstoque().value() < quantidadeNecessaria) {
                throw new DomainException("Estoque insuficiente para a peça: " + peca.getNome().value() +
                        ". Necessário: " + quantidadeNecessaria + ", Disponível: " + peca.getQuantidadeEstoque().value());
            }

            peca.removerEstoque(new Quantidade(quantidadeNecessaria));
            pecaRepositoryPort.salvar(peca);
        }
    }
}
