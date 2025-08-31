package com.example.Tozin_Solutions_back_end.V2.core.application.pecaApplication.useCase;

import com.example.Tozin_Solutions_back_end.V2.core.application.pecaApplication.dto.CadastrarPecaInput;
import com.example.Tozin_Solutions_back_end.V2.core.application.pecaApplication.dto.PecaOutput;

public interface CadastrarPecaUseCase {
    PecaOutput executar(CadastrarPecaInput cadastrarPecaInput);
}
