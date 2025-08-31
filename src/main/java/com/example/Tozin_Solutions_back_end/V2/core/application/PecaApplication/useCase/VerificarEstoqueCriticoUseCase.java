package com.example.Tozin_Solutions_back_end.V2.core.application.PecaApplication.useCase;

import com.example.Tozin_Solutions_back_end.V2.core.application.PecaApplication.dto.PecaOutput;

import java.util.List;

public interface VerificarEstoqueCriticoUseCase {
    List<PecaOutput> executar();
}
