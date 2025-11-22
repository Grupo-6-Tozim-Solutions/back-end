package com.example.Tozin_Solutions_back_end.V2.core.application.pecaApplication.service;
import com.example.Tozin_Solutions_back_end.V2.core.application.pecaApplication.dto.PecaOutput;
import com.example.Tozin_Solutions_back_end.V2.core.application.pecaApplication.dto.PecaPaginationRequest;
import com.example.Tozin_Solutions_back_end.V2.core.application.pecaApplication.dto.PecaPaginationResponse;
import com.example.Tozin_Solutions_back_end.V2.core.application.pecaApplication.port.PecaRepositoryPort;
import com.example.Tozin_Solutions_back_end.V2.core.application.pecaApplication.useCase.ListarPecasPaginadasUseCase;
import com.example.Tozin_Solutions_back_end.V2.core.domain.PecaDomain.Peca;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort; // ✅ CORRETO!
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListarPecasPaginadasService implements ListarPecasPaginadasUseCase {
    private final PecaRepositoryPort pecaRepositoryPort;

    public ListarPecasPaginadasService(PecaRepositoryPort pecaRepositoryPort) {
        this.pecaRepositoryPort = pecaRepositoryPort;
    }
    @Override
    @Cacheable(
            value = "pecasPaginadas",
            key = "T(String).valueOf(#request.page())" +
                    " + '-' + #request.size()" +
                    " + '-' + #request.sortBy()" +
                    " + '-' + #request.sortDirection()" +
                    " + '-' + #request.filter()"
    )
    public PecaPaginationResponse executar(PecaPaginationRequest request) {
        Sort sort = Sort.by(
                request.sortDirection().equalsIgnoreCase("desc") ?
                        Sort.Direction.DESC : Sort.Direction.ASC,
                request.sortBy()
        );
        Pageable pageable = PageRequest.of(request.page() - 1, request.size(), sort);
        Page<Peca> pecasPage = pecaRepositoryPort.buscarTodasPaginadas(pageable, request.filter());
        List<PecaOutput> content = pecasPage.getContent().stream()
                .map(this::toOutput)
                .collect(Collectors.toList());
        return new PecaPaginationResponse(
                content,
                request.page(),
                pecasPage.getTotalPages(),
                pecasPage.getTotalElements(),
                request.size()
        );
    }
    private PecaOutput toOutput(Peca peca) {
        return new PecaOutput(
                peca.getId(),
                peca.getNome().value(),
                peca.getQuantidadeEstoque().value(),
                peca.getQuantidadeMinima().value(),
                peca.getTipo().name(),
                peca.getDataCadastro(),
                peca.isEstoqueCritico()
        );
    }
}