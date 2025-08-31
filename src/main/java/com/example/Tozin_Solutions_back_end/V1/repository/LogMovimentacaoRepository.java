package com.example.Tozin_Solutions_back_end.V1.repository;

import com.example.Tozin_Solutions_back_end.V1.model.LogMovimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Repository
public interface LogMovimentacaoRepository extends JpaRepository<LogMovimentacao, Long> {

    List<LogMovimentacao> findByDataMovimentacaoBetween(LocalDateTime inicio, LocalDateTime fim);

    List<LogMovimentacao> findByNomePecaContainingIgnoreCase(String nomePeca);


    @Query("""
        SELECT new map(l.nomePeca as name, SUM(l.quantidadeSaida) as value)
        FROM LogMovimentacao l
        WHERE MONTH(l.dataMovimentacao) = MONTH(CURRENT_DATE)
          AND YEAR(l.dataMovimentacao) = YEAR(CURRENT_DATE)
        GROUP BY l.nomePeca
        ORDER BY SUM(l.quantidadeSaida) DESC
    """)
    List<Map<String, Object>> findPecasMaisSaidaMes();

    @Query("""
        SELECT new map(l.nomePeca as name, SUM(l.quantidadeSaida) as value)
        FROM LogMovimentacao l
        WHERE l.tipoPeca = 'SOFA'
          AND MONTH(l.dataMovimentacao) = MONTH(CURRENT_DATE)
          AND YEAR(l.dataMovimentacao) = YEAR(CURRENT_DATE)
        GROUP BY l.nomePeca
        ORDER BY SUM(l.quantidadeSaida) DESC
    """)
    List<Map<String, Object>> findSofasMaisSaidaMes();
}
