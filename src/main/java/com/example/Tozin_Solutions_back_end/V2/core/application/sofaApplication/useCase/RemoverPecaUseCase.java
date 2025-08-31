package com.example.Tozin_Solutions_back_end.V2.core.application.sofaApplication.useCase;

import com.example.Tozin_Solutions_back_end.V2.core.application.sofaApplication.dto.SofaOutput;

public interface RemoverPecaUseCase {
    SofaOutput executar(Long sofaId, Long pecaId);
}
