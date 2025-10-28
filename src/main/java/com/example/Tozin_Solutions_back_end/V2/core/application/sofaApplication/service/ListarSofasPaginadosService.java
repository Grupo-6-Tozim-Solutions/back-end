// ListarSofasPaginadosService.java
package com.example.Tozin_Solutions_back_end.V2.core.application.sofaApplication.service;

import com.example.Tozin_Solutions_back_end.V2.core.application.sofaApplication.dto.SofaOutput;
import com.example.Tozin_Solutions_back_end.V2.core.application.sofaApplication.dto.SofaPaginationRequest;
import com.example.Tozin_Solutions_back_end.V2.core.application.sofaApplication.dto.SofaPaginationResponse;
import com.example.Tozin_Solutions_back_end.V2.core.application.sofaApplication.port.SofaRepositoryPort;
import com.example.Tozin_Solutions_back_end.V2.core.application.sofaApplication.useCase.ListarSofasPaginadosUseCase;
import com.example.Tozin_Solutions_back_end.V2.core.domain.SofaDomain.Sofa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListarSofasPaginadosService implements ListarSofasPaginadosUseCase {

    private final SofaRepositoryPort sofaRepositoryPort;

    public ListarSofasPaginadosService(SofaRepositoryPort sofaRepositoryPort) {
        this.sofaRepositoryPort = sofaRepositoryPort;
    }

    @Override
    public SofaPaginationResponse executar(SofaPaginationRequest request) {
        // Configurar paginação e ordenação
        Sort sort = Sort.by(
                request.sortDirection().equalsIgnoreCase("desc") ?
                        Sort.Direction.DESC : Sort.Direction.ASC,
                request.sortBy()
        );

        Pageable pageable = PageRequest.of(request.page() - 1, request.size(), sort);

        // Buscar sofás paginados
        Page<Sofa> sofasPage = sofaRepositoryPort.buscarTodosPaginados(pageable, request.filter());

        // Converter para DTO
        List<SofaOutput> content = sofasPage.getContent().stream()
                .map(this::toOutput)
                .collect(Collectors.toList());

        return new SofaPaginationResponse(
                content,
                request.page(),
                sofasPage.getTotalPages(),
                sofasPage.getTotalElements(),
                request.size()
        );
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