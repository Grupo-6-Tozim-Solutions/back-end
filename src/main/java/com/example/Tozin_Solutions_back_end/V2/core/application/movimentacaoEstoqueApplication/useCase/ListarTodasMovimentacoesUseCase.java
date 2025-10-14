package com.example.Tozin_Solutions_back_end.V2.core.application.movimentacaoEstoqueApplication.useCase;

import com.example.Tozin_Solutions_back_end.V2.core.application.movimentacaoEstoqueApplication.dto.MovimentacaoEstoqueOutput;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface ListarTodasMovimentacoesUseCase {
    List<MovimentacaoEstoqueOutput> executar();
}
