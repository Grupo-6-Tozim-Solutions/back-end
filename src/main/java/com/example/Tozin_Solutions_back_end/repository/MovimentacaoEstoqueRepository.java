package com.example.Tozin_Solutions_back_end.repository;

import com.example.Tozin_Solutions_back_end.model.MovimentacaoEstoque;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovimentacaoEstoqueRepository extends JpaRepository<MovimentacaoEstoque, Long> {

   // List<MovimentacaoEstoque> findByPeca(Peca peca);
}
