package com.example.Tozin_Solutions_back_end.V2.core.application.usuarioApplication.useCase;

import com.example.Tozin_Solutions_back_end.V2.core.application.usuarioApplication.dto.LoginRequest;
import com.example.Tozin_Solutions_back_end.V2.core.application.usuarioApplication.dto.LoginResponse;

public interface LoginUseCase {
    LoginResponse executar(LoginRequest loginRequest);
}
