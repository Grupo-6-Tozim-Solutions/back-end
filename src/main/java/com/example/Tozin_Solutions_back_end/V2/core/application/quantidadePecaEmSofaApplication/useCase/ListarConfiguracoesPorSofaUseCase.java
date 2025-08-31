package com.example.Tozin_Solutions_back_end.V2.core.application.quantidadePecaEmSofaApplication.useCase;

import com.example.Tozin_Solutions_back_end.V2.core.application.quantidadePecaEmSofaApplication.dto.ConfiguracaoPecaSofaOutput;

import java.util.List;

public interface ListarConfiguracoesPorSofaUseCase {
    List<ConfiguracaoPecaSofaOutput> executar(Long sofaId);
}
