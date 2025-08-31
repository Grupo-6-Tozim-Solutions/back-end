package com.example.Tozin_Solutions_back_end.V2.core.application.pecaApplication.useCase;

import com.example.Tozin_Solutions_back_end.V2.core.application.pecaApplication.dto.PecaOutput;

import java.util.List;

public interface ListarPecasUseCase {
    public List<PecaOutput> executar();
}
