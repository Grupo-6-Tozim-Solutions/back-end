package com.example.Tozin_Solutions_back_end.V2.core.application.PecaApplication.service;

import com.example.Tozin_Solutions_back_end.V2.core.application.PecaApplication.dto.AtualizarPecaInput;
import com.example.Tozin_Solutions_back_end.V2.core.application.PecaApplication.dto.PecaOutput;
import com.example.Tozin_Solutions_back_end.V2.core.application.PecaApplication.port.PecaRepositoryPort;
import com.example.Tozin_Solutions_back_end.V2.core.application.PecaApplication.useCase.AtualizarPecaUseCase;
import com.example.Tozin_Solutions_back_end.V2.core.domain.PecaDomain.Peca;
import com.example.Tozin_Solutions_back_end.V2.core.domain.PecaDomain.excpetion.PecaNaoEncontradaException;
import com.example.Tozin_Solutions_back_end.V2.core.domain.PecaDomain.valueObjects.NomePeca;
import com.example.Tozin_Solutions_back_end.V2.core.domain.PecaDomain.valueObjects.Quantidade;
import com.example.Tozin_Solutions_back_end.V2.core.domain.usuarioDomain.excepetion.DomainException;
import org.springframework.stereotype.Service;

@Service
public class AtualizarPecaService implements AtualizarPecaUseCase {

    private final PecaRepositoryPort pecaRepositoryPort;

    public AtualizarPecaService(PecaRepositoryPort pecaRepositoryPort) {
        this.pecaRepositoryPort = pecaRepositoryPort;
    }

    @Override
    public PecaOutput executar(Long id, AtualizarPecaInput input) {
        Peca peca = pecaRepositoryPort.buscarPorId(id)
                .orElseThrow(() -> new PecaNaoEncontradaException(id));

        if (input.nome() != null) {
            if (pecaRepositoryPort.existePorNome(input.nome()) &&
                    !peca.getNome().value().equalsIgnoreCase(input.nome())) {
                throw new DomainException("Já existe uma peça com esse nome");
            }
            peca.atualizarNome(new NomePeca(input.nome()));
        }

        if (input.quantidadeMinima() != null) {
            peca.atualizarQuantidadeMinima(new Quantidade(input.quantidadeMinima()));
        }

        Peca pecaAtualizada = pecaRepositoryPort.salvar(peca);

        return new PecaOutput(
                pecaAtualizada.getId(),
                pecaAtualizada.getNome().value(),
                pecaAtualizada.getQuantidadeEstoque().value(),
                pecaAtualizada.getQuantidadeMinima().value(),
                pecaAtualizada.getTipo().name(),
                pecaAtualizada.getDataCadastro(),
                pecaAtualizada.isEstoqueCritico()
        );
    }
}
