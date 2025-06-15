package com.example.Tozin_Solutions_back_end.repository;

import com.example.Tozin_Solutions_back_end.model.LogMovimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LogMovimentacaoRepository extends JpaRepository<LogMovimentacao, Long> {

    List<LogMovimentacao> findByDataMovimentacaoBetween(LocalDateTime inicio, LocalDateTime fim);

    List<LogMovimentacao> findByNomePecaContainingIgnoreCase(String nomePeca);
}
