package com.example.Tozin_Solutions_back_end.V2.core.application.sofaApplication.service;

import com.example.Tozin_Solutions_back_end.V2.core.application.sofaApplication.dto.CadastrarSofaInput;
import com.example.Tozin_Solutions_back_end.V2.core.application.sofaApplication.dto.SofaOutput;
import com.example.Tozin_Solutions_back_end.V2.core.application.sofaApplication.port.SofaRepositoryPort;
import com.example.Tozin_Solutions_back_end.V2.core.application.sofaApplication.useCase.CadastrarSofaUseCase;
import com.example.Tozin_Solutions_back_end.V2.core.domain.SofaDomain.Sofa;
import com.example.Tozin_Solutions_back_end.V2.core.domain.SofaDomain.ValueObjects.CaminhoImagem;
import com.example.Tozin_Solutions_back_end.V2.core.domain.SofaDomain.ValueObjects.ModeloSofa;
import com.example.Tozin_Solutions_back_end.V2.core.domain.usuarioDomain.excepetion.DomainException;
import org.springframework.stereotype.Service;

@Service
public class CadastrarSofaService implements CadastrarSofaUseCase {

    private final SofaRepositoryPort sofaRepositoryPort;

    public CadastrarSofaService(SofaRepositoryPort sofaRepositoryPort) {
        this.sofaRepositoryPort = sofaRepositoryPort;
    }

    @Override
    public SofaOutput executar(CadastrarSofaInput input) {
        if (sofaRepositoryPort.existePorModelo(input.modelo())) {
            throw new DomainException("Já existe um sofá com esse modelo");
        }

        ModeloSofa modelo = new ModeloSofa(input.modelo());
        CaminhoImagem caminhoImagem = new CaminhoImagem(input.caminhoImagem());

        Sofa sofa = Sofa.criarSofa(modelo, caminhoImagem);
        Sofa sofaSalvo = sofaRepositoryPort.salvar(sofa);

        return toOutput(sofaSalvo);
    }

    private SofaOutput toOutput(Sofa sofa) {
        return new SofaOutput(
                sofa.getId(),
                sofa.getModelo().value(),
                sofa.getCaminhoImagem().value(),
                sofa.getDataCadastro()
        );
    }
}
