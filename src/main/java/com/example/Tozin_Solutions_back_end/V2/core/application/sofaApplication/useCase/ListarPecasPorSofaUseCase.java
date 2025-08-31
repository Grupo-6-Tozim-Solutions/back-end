package com.example.Tozin_Solutions_back_end.V2.core.application.sofaApplication.useCase;

import com.example.Tozin_Solutions_back_end.V2.core.application.sofaApplication.dto.PecaSofaOutput;

import java.util.List;

public interface ListarPecasPorSofaUseCase {
    List<PecaSofaOutput> executar(Long sofaId);
}
