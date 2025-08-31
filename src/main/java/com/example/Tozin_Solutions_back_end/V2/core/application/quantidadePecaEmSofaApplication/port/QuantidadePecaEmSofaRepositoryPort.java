package com.example.Tozin_Solutions_back_end.V2.core.application.quantidadePecaEmSofaApplication.port;

import com.example.Tozin_Solutions_back_end.V2.core.domain.QuantidadePecaEmSofaDomain.QuantidadePecaEmSofa;

import java.util.List;
import java.util.Optional;

public interface QuantidadePecaEmSofaRepositoryPort {
    QuantidadePecaEmSofa salvar(QuantidadePecaEmSofa configuracao);
    Optional<QuantidadePecaEmSofa> buscarPorId(Long id);
    Optional<QuantidadePecaEmSofa> buscarPorSofaIdEPecaId(Long sofaId, Long pecaId);
    List<QuantidadePecaEmSofa> buscarPorSofaId(Long sofaId);
    List<QuantidadePecaEmSofa> buscarPorPecaId(Long pecaId);
    void deletarPorId(Long id);
    void deletarPorSofaId(Long sofaId);
    void deletarPorSofaIdEPecaId(Long sofaId, Long pecaId);
}
