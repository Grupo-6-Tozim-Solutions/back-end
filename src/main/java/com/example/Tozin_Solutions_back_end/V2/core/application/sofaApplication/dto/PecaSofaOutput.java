package com.example.Tozin_Solutions_back_end.V2.core.application.sofaApplication.dto;

public record PecaSofaOutput(
        Long configuracaoId,
        Long pecaId,
        String nomePeca,
        Double quantidade,
        String tipoPeca
) {}
