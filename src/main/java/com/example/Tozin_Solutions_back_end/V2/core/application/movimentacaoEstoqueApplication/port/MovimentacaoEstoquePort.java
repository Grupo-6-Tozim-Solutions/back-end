package com.example.Tozin_Solutions_back_end.V2.core.application.movimentacaoEstoqueApplication.port;

import com.example.Tozin_Solutions_back_end.V2.core.application.movimentacaoEstoqueApplication.dto.MovimentacaoEstoqueInput;
import com.example.Tozin_Solutions_back_end.V2.core.application.movimentacaoEstoqueApplication.dto.MovimentacaoEstoqueOutput;
import com.example.Tozin_Solutions_back_end.V2.core.domain.MovimentacaoEstoqueDomain.MovimentacaoEstoque;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MovimentacaoEstoquePort {
    MovimentacaoEstoque salvar(MovimentacaoEstoque movimentacaoEstoque);
    Optional<MovimentacaoEstoque> buscarPorId(Long id);
    List<MovimentacaoEstoque> buscarTodas();
    void deletarPorPecaId(Long pecaId);

}
