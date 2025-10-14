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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class CadastrarSofaService implements CadastrarSofaUseCase {

    private final SofaRepositoryPort sofaRepositoryPort;
    private final ImageStorageService imageStorageService;

    public CadastrarSofaService(SofaRepositoryPort sofaRepositoryPort,
                                ImageStorageService imageStorageService) {
        this.sofaRepositoryPort = sofaRepositoryPort;
        this.imageStorageService = imageStorageService;
    }

    @Override
    public SofaOutput executar(CadastrarSofaInput input, MultipartFile imagem) {
        if (sofaRepositoryPort.existePorModelo(input.modelo())) {
            throw new DomainException("Já existe um sofá com esse modelo");
        }

        String caminhoImagemSalva = imageStorageService.salvarImagemSofa(imagem);

        ModeloSofa modelo = new ModeloSofa(input.modelo());
        CaminhoImagem caminhoImagem = new CaminhoImagem(caminhoImagemSalva);

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