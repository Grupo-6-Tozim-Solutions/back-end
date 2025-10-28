package com.example.Tozin_Solutions_back_end.V2.core.application.pecaApplication.useCase;

import com.example.Tozin_Solutions_back_end.V2.core.application.pecaApplication.dto.PecaPaginationRequest;
import com.example.Tozin_Solutions_back_end.V2.core.application.pecaApplication.dto.PecaPaginationResponse;

public interface ListarPecasPaginadasUseCase {
    PecaPaginationResponse executar(PecaPaginationRequest request);
}
