package com.example.Tozin_Solutions_back_end.V2.core.application.usuarioApplication.useCase;

import com.example.Tozin_Solutions_back_end.V2.core.application.usuarioApplication.dto.AlterarSenhaInput;
import com.example.Tozin_Solutions_back_end.V2.core.application.usuarioApplication.dto.UsuarioOutput;

public interface AlterarSenhaUseCase {
    UsuarioOutput executar(AlterarSenhaInput alterarSenha);
}
