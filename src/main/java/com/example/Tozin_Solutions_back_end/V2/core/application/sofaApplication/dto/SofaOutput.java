package com.example.Tozin_Solutions_back_end.V2.core.application.sofaApplication.dto;

import java.time.LocalDateTime;

public record SofaOutput(
        Long id,
        String modelo,
        String caminhoImagem,
        LocalDateTime dataCadastro
) {}
