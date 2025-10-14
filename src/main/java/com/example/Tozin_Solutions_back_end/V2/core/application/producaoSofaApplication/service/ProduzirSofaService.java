package com.example.Tozin_Solutions_back_end.V2.core.application.producaoSofaApplication.service;

import com.example.Tozin_Solutions_back_end.V2.core.application.movimentacaoEstoqueApplication.dto.MovimentacaoEstoqueInput;
import com.example.Tozin_Solutions_back_end.V2.core.application.movimentacaoEstoqueApplication.port.MovimentacaoEstoquePort;
import com.example.Tozin_Solutions_back_end.V2.core.application.movimentacaoEstoqueApplication.useCase.RegistrarMovimentacaoEstoqueUseCase;
import com.example.Tozin_Solutions_back_end.V2.core.application.pecaApplication.port.PecaRepositoryPort;
import com.example.Tozin_Solutions_back_end.V2.core.application.producaoSofaApplication.port.ProducaoSofaRepositoryPort;
import com.example.Tozin_Solutions_back_end.V2.core.application.quantidadePecaEmSofaApplication.port.QuantidadePecaEmSofaRepositoryPort;
import com.example.Tozin_Solutions_back_end.V2.core.application.producaoSofaApplication.useCase.ProduzirSofaUseCase;
import com.example.Tozin_Solutions_back_end.V2.core.domain.PecaDomain.Peca;
import com.example.Tozin_Solutions_back_end.V2.core.domain.PecaDomain.valueObjects.Quantidade;
import com.example.Tozin_Solutions_back_end.V2.core.domain.QuantidadePecaEmSofaDomain.QuantidadePecaEmSofa;
import com.example.Tozin_Solutions_back_end.V2.core.domain.producaoSofaDomain.ProducaoSofa;
import com.example.Tozin_Solutions_back_end.V2.core.domain.producaoSofaDomain.valueObjects.QuantidadeProducao;
import com.example.Tozin_Solutions_back_end.V2.core.domain.usuarioDomain.excepetion.DomainException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProduzirSofaService implements ProduzirSofaUseCase {

    private final QuantidadePecaEmSofaRepositoryPort configuracaoRepositoryPort;
    private final PecaRepositoryPort pecaRepositoryPort;
    private final ProducaoSofaRepositoryPort producaoSofaRepositoryPort;
    private final RegistrarMovimentacaoEstoqueUseCase registrarMovimentacaoEstoqueUseCase;

    public ProduzirSofaService(QuantidadePecaEmSofaRepositoryPort configuracaoRepositoryPort,
                               PecaRepositoryPort pecaRepositoryPort,
                               ProducaoSofaRepositoryPort producaoSofaRepositoryPort,
                               RegistrarMovimentacaoEstoqueUseCase registrarMovimentacaoEstoqueUseCase) {
        this.configuracaoRepositoryPort = configuracaoRepositoryPort;
        this.pecaRepositoryPort = pecaRepositoryPort;
        this.producaoSofaRepositoryPort = producaoSofaRepositoryPort;
        this.registrarMovimentacaoEstoqueUseCase = registrarMovimentacaoEstoqueUseCase;
    }

    @Override
    @Transactional
    public void executar(Long sofaId, Integer quantidade) {
        if (quantidade <= 0) {
            throw new DomainException("Quantidade de produção deve ser maior que zero");
        }

        List<QuantidadePecaEmSofa> configuracoes = configuracaoRepositoryPort.buscarPorSofaId(sofaId);

        if (configuracoes.isEmpty()) {
            throw new DomainException("Sofá não possui peças configuradas");
        }

        // Valida estoque primeiro
        for (QuantidadePecaEmSofa config : configuracoes) {
            Peca peca = pecaRepositoryPort.buscarPorId(config.getPecaId())
                    .orElseThrow(() -> new DomainException("Peça não encontrada: " + config.getPecaId()));

            Double quantidadeNecessaria = config.getQuantidade().value() * quantidade;

            if (peca.getQuantidadeEstoque().value() < quantidadeNecessaria) {
                throw new DomainException("Estoque insuficiente para a peça: " + peca.getNome().value() +
                        ". Necessário: " + quantidadeNecessaria + ", Disponível: " + peca.getQuantidadeEstoque().value());
            }
        }

        // Se passou da validação, realiza as operações
        // 1. Registra a produção
        ProducaoSofa producao = new ProducaoSofa(sofaId, new QuantidadeProducao(quantidade));
        producaoSofaRepositoryPort.salvar(producao);

        // 2. Remove do estoque e registra movimentação
        for (QuantidadePecaEmSofa config : configuracoes) {
            Peca peca = pecaRepositoryPort.buscarPorId(config.getPecaId()).get();
            Double quantidadeNecessaria = config.getQuantidade().value() * quantidade;

            // Remove do estoque
            peca.removerEstoque(new Quantidade(quantidadeNecessaria));
            pecaRepositoryPort.salvar(peca);

            // ✅ Use o use case que já funciona
            MovimentacaoEstoqueInput movimentacaoInput = new MovimentacaoEstoqueInput(
                    peca.getId(),
                    0.0,
                    Math.abs(quantidadeNecessaria) // Garante valor absoluto
            );

            // Este use case já tem toda a validação correta
            registrarMovimentacaoEstoqueUseCase.executar(movimentacaoInput);
        }
    }
}