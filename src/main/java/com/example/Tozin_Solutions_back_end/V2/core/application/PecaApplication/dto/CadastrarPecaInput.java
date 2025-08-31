package com.example.Tozin_Solutions_back_end.V2.core.application.PecaApplication.dto;

public record PecaInput(
        String nome,
        Double quantidadeEstoque,
        Double quantidadeMinima,
        String tipo
) {}
