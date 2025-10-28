package com.example.Tozin_Solutions_back_end.V2.core.application.sofaApplication.useCase;

import com.example.Tozin_Solutions_back_end.V2.core.application.sofaApplication.dto.SofaPaginationRequest;
import com.example.Tozin_Solutions_back_end.V2.core.application.sofaApplication.dto.SofaPaginationResponse;

public interface ListarSofasPaginadosUseCase {
    SofaPaginationResponse executar(SofaPaginationRequest request);
}
