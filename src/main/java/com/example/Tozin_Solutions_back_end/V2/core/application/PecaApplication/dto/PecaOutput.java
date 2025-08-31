package com.example.Tozin_Solutions_back_end.V2.core.application.PecaApplication.dto;

import java.time.LocalDateTime;

public record PecaOutput (
        Long id,
        String nome,
        Double quantidadeEstoque,
        Double quantidadeMinima,
        String tipo,
        LocalDateTime dataCadastro,
        Boolean estoqueCritico
){
}
