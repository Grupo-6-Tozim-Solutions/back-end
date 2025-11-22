package com.example.Tozin_Solutions_back_end.V2.core.application.pecaApplication.service;

import com.example.Tozin_Solutions_back_end.V2.core.application.movimentacaoEstoqueApplication.dto.MovimentacaoEstoqueInput;
import com.example.Tozin_Solutions_back_end.V2.core.application.movimentacaoEstoqueApplication.useCase.RegistrarMovimentacaoEstoqueUseCase;
import com.example.Tozin_Solutions_back_end.V2.core.application.pecaApplication.dto.CadastrarPecaInput;
import com.example.Tozin_Solutions_back_end.V2.core.application.pecaApplication.dto.PecaOutput;
import com.example.Tozin_Solutions_back_end.V2.core.application.pecaApplication.port.PecaRepositoryPort;
import com.example.Tozin_Solutions_back_end.V2.core.application.pecaApplication.useCase.CadastrarPecaUseCase;
import com.example.Tozin_Solutions_back_end.V2.core.domain.PecaDomain.Peca;
import com.example.Tozin_Solutions_back_end.V2.core.domain.PecaDomain.valueObjects.NomePeca;
import com.example.Tozin_Solutions_back_end.V2.core.domain.PecaDomain.valueObjects.Quantidade;
import com.example.Tozin_Solutions_back_end.V2.core.domain.PecaDomain.valueObjects.TipoPeca;
import com.example.Tozin_Solutions_back_end.V2.core.domain.usuarioDomain.excepetion.DomainException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.CacheEvict;



@Service
public class CadastrarPecaService implements CadastrarPecaUseCase {

    private final PecaRepositoryPort pecaRepositoryPort;
    private final RegistrarMovimentacaoEstoqueUseCase registrarMovimentacaoEstoqueUseCase;

    public CadastrarPecaService(PecaRepositoryPort pecaRepositoryPort,
                                RegistrarMovimentacaoEstoqueUseCase registrarMovimentacaoEstoqueUseCase) {
        this.pecaRepositoryPort = pecaRepositoryPort;
        this.registrarMovimentacaoEstoqueUseCase = registrarMovimentacaoEstoqueUseCase;
    }

    @Override
    @Transactional
    @CacheEvict(value = "pecasPaginadas", allEntries = true)
    public PecaOutput executar(CadastrarPecaInput input) {
        if (pecaRepositoryPort.existePorNome(input.nome())) {
            throw new IllegalArgumentException("Já existe uma peça com esse nome");
        }

        Peca peca = Peca.criarPeca(
                new NomePeca(input.nome()),
                new Quantidade(input.quantidadeEstoque()),
                new Quantidade(input.quantidadeMinima()),
                TipoPeca.valueOf(input.tipo())
        );

        Peca pecaSalva = pecaRepositoryPort.salvar(peca);

        // ✅ Registrar movimentação inicial (entrada do estoque inicial)
        MovimentacaoEstoqueInput movimentacaoInput = new MovimentacaoEstoqueInput(
                pecaSalva.getId(),
                input.quantidadeEstoque(), // quantidadeEntrada (estoque inicial)
                0.0                       // quantidadeSaida
        );
        registrarMovimentacaoEstoqueUseCase.executar(movimentacaoInput);

        return new PecaOutput(
                pecaSalva.getId(),
                pecaSalva.getNome().value(),
                pecaSalva.getQuantidadeEstoque().value(),
                pecaSalva.getQuantidadeMinima().value(),
                pecaSalva.getTipo().name(),
                pecaSalva.getDataCadastro(),
                pecaSalva.isEstoqueCritico()
        );
    }
}

