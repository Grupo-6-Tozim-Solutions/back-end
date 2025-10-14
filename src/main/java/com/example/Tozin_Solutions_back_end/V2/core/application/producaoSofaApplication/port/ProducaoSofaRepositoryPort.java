package com.example.Tozin_Solutions_back_end.V2.core.application.producaoSofaApplication.port;

import com.example.Tozin_Solutions_back_end.V2.core.application.movimentacaoEstoqueApplication.dto.MovimentacaoEstoqueInput;
import com.example.Tozin_Solutions_back_end.V2.core.domain.producaoSofaDomain.ProducaoSofa;

import java.time.LocalDateTime;
import java.util.List;

public interface ProducaoSofaRepositoryPort {
    ProducaoSofa salvar(ProducaoSofa producaoSofa);
    List<ProducaoSofa> buscarPorSofaId(Long sofaId);
    List<ProducaoSofa> buscarPorPeriodo(LocalDateTime inicio, LocalDateTime fim);

}
