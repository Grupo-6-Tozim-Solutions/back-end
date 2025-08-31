package com.example.Tozin_Solutions_back_end.V2.core.application.PecaApplication.useCase;

import com.example.Tozin_Solutions_back_end.V2.core.application.PecaApplication.dto.AtualizarPecaInput;
import com.example.Tozin_Solutions_back_end.V2.core.application.PecaApplication.dto.CadastrarPecaInput;
import com.example.Tozin_Solutions_back_end.V2.core.application.PecaApplication.dto.PecaOutput;

public interface AtualizarPecaUseCase {
    PecaOutput executar(Long id, AtualizarPecaInput atualizarPecaInput);
}
