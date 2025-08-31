package com.example.Tozin_Solutions_back_end.V2.core.application.sofaApplication.useCase;

import com.example.Tozin_Solutions_back_end.V2.core.application.sofaApplication.dto.AdicionarPecaInput;
import com.example.Tozin_Solutions_back_end.V2.core.application.sofaApplication.dto.SofaOutput;

public interface AdicionarPecaUseCase {
    SofaOutput executar(Long sofaId, AdicionarPecaInput input);
}