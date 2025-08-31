package com.example.Tozin_Solutions_back_end.V2.core.application.quantidadePecaEmSofaApplication.useCase;

import com.example.Tozin_Solutions_back_end.V2.core.application.quantidadePecaEmSofaApplication.dto.ConfiguracaoPecaSofaOutput;
import com.example.Tozin_Solutions_back_end.V2.core.application.quantidadePecaEmSofaApplication.dto.ConfigurarPecaNoSofaInput;

public interface ConfigurarPecaNoSofaUseCase {
    ConfiguracaoPecaSofaOutput executar(ConfigurarPecaNoSofaInput input);
}
