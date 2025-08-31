package com.example.Tozin_Solutions_back_end.V2.core.application.pecaApplication.service;

import com.example.Tozin_Solutions_back_end.V2.core.application.pecaApplication.dto.CadastrarPecaInput;
import com.example.Tozin_Solutions_back_end.V2.core.application.pecaApplication.dto.PecaOutput;
import com.example.Tozin_Solutions_back_end.V2.core.application.pecaApplication.port.PecaRepositoryPort;
import com.example.Tozin_Solutions_back_end.V2.core.application.pecaApplication.useCase.CadastrarPecaUseCase;
import com.example.Tozin_Solutions_back_end.V2.core.domain.PecaDomain.Peca;
import com.example.Tozin_Solutions_back_end.V2.core.domain.PecaDomain.valueObjects.NomePeca;
import com.example.Tozin_Solutions_back_end.V2.core.domain.PecaDomain.valueObjects.Quantidade;
import com.example.Tozin_Solutions_back_end.V2.core.domain.PecaDomain.valueObjects.TipoPeca;
import com.example.Tozin_Solutions_back_end.V2.core.domain.usuarioDomain.excepetion.DomainException;
import org.springframework.stereotype.Service;

@Service
public class CadastrarPecaService implements CadastrarPecaUseCase {

    private final PecaRepositoryPort pecaRepositoryPort;
    public CadastrarPecaService(PecaRepositoryPort pecaRepositoryPort) {
        this.pecaRepositoryPort = pecaRepositoryPort;
    }

    public PecaOutput executar(CadastrarPecaInput cadastrarPecaInput) {

        if (pecaRepositoryPort.existePorNome(cadastrarPecaInput.nome())){
            throw  new DomainException("Já existe um Peca com esse nome.");
        }

        NomePeca nome = new NomePeca(cadastrarPecaInput.nome());
        Quantidade quantidadeEstoque = new Quantidade(cadastrarPecaInput.quantidadeEstoque());
        Quantidade quantidadeMinima = new Quantidade(cadastrarPecaInput.quantidadeMinima());
        TipoPeca tipoPeca = TipoPeca.fromString(cadastrarPecaInput.tipo());

        Peca peca = Peca.criarPeca(nome, quantidadeEstoque, quantidadeMinima, tipoPeca);
        Peca pecaSalva = pecaRepositoryPort.salvar(peca);

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
