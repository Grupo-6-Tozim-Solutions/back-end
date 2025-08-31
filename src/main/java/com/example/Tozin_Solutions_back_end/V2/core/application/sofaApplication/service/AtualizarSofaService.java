package com.example.Tozin_Solutions_back_end.V2.core.application.sofaApplication.service;

import com.example.Tozin_Solutions_back_end.V2.core.application.sofaApplication.dto.AtualizarSofaInput;
import com.example.Tozin_Solutions_back_end.V2.core.application.sofaApplication.dto.SofaOutput;
import com.example.Tozin_Solutions_back_end.V2.core.application.sofaApplication.port.SofaRepositoryPort;
import com.example.Tozin_Solutions_back_end.V2.core.application.sofaApplication.useCase.AtualizarSofaUseCase;
import com.example.Tozin_Solutions_back_end.V2.core.domain.SofaDomain.Sofa;
import com.example.Tozin_Solutions_back_end.V2.core.domain.SofaDomain.ValueObjects.CaminhoImagem;
import com.example.Tozin_Solutions_back_end.V2.core.domain.SofaDomain.ValueObjects.ModeloSofa;
import com.example.Tozin_Solutions_back_end.V2.core.domain.SofaDomain.exception.SofaNaoEncontradoException;
import com.example.Tozin_Solutions_back_end.V2.core.domain.usuarioDomain.excepetion.DomainException;
import org.springframework.stereotype.Service;

@Service
public class AtualizarSofaService implements AtualizarSofaUseCase {

    private final SofaRepositoryPort sofaRepositoryPort;

    public AtualizarSofaService(SofaRepositoryPort sofaRepositoryPort) {
        this.sofaRepositoryPort = sofaRepositoryPort;
    }

    @Override
    public SofaOutput executar(Long id, AtualizarSofaInput input) {
        Sofa sofa = sofaRepositoryPort.buscarPorId(id)
                .orElseThrow(() -> new SofaNaoEncontradoException(id));

        if (input.modelo() != null) {
            if (sofaRepositoryPort.existePorModelo(input.modelo()) &&
                    !sofa.getModelo().value().equalsIgnoreCase(input.modelo())) {
                throw new DomainException("Já existe um sofá com esse modelo");
            }
            sofa.atualizarModelo(new ModeloSofa(input.modelo()));
        }

        if (input.caminhoImagem() != null) {
            sofa.atualizarCaminhoImagem(new CaminhoImagem(input.caminhoImagem()));
        }

        Sofa sofaAtualizado = sofaRepositoryPort.salvar(sofa);

        return new SofaOutput(
                sofaAtualizado.getId(),
                sofaAtualizado.getModelo().value(),
                sofaAtualizado.getCaminhoImagem().value(),
                sofaAtualizado.getDataCadastro()
        );
    }
}
