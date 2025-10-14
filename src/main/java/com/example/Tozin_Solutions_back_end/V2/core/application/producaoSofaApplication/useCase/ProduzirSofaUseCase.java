package com.example.Tozin_Solutions_back_end.V2.core.application.producaoSofaApplication.useCase;

public interface ProduzirSofaUseCase {
    void executar(Long sofaId, Integer quantidade);
}