package com.example.Tozin_Solutions_back_end.V2.core.application.usuarioApplication.dto;

import com.example.Tozin_Solutions_back_end.V2.core.domain.usuarioDomain.valueObjects.SenhaHash;

public record LoginResponse(
        String token,
        Long id,
        String email,
        String nome) {
}
