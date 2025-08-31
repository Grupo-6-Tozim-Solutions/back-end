package com.example.Tozin_Solutions_back_end.V2.core.application.pecaApplication.useCase;

import com.example.Tozin_Solutions_back_end.V2.core.application.pecaApplication.dto.PecaOutput;

public interface RemoverEstoqueUseCase {
    PecaOutput executar(Long id, Double quantidade);
}
