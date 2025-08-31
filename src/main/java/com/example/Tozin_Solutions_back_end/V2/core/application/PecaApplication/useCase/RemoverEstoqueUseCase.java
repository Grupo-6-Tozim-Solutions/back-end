package com.example.Tozin_Solutions_back_end.V2.core.application.PecaApplication.useCase;

import com.example.Tozin_Solutions_back_end.V2.core.application.PecaApplication.dto.PecaOutput;
import com.example.Tozin_Solutions_back_end.V2.core.domain.PecaDomain.valueObjects.Quantidade;

public interface RemoverEstoqueUseCase {
    PecaOutput executar(Long id, Double quantidade);
}
