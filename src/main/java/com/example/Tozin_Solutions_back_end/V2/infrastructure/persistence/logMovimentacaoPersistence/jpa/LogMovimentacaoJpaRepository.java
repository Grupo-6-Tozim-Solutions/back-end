package com.example.Tozin_Solutions_back_end.V2.infrastructure.persistence.logMovimentacaoPersistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Repository
public interface LogMovimentacaoJpaRepository extends JpaRepository<LogMovimentacaoJpaEntity, Long> {

    List<LogMovimentacaoJpaEntity> findByDataMovimentacaoBetween(LocalDateTime inicio, LocalDateTime fim);

    List<LogMovimentacaoJpaEntity> findByNomePecaContainingIgnoreCase(String nomePeca);

    @Query("""
        SELECT new map(l.nomePeca as name, SUM(l.quantidadeSaida) as value)
        FROM LogMovimentacaoJpaEntity l
        WHERE MONTH(l.dataMovimentacao) = MONTH(CURRENT_DATE)
          AND YEAR(l.dataMovimentacao) = YEAR(CURRENT_DATE)
          AND l.tipoPeca != 'SOFA'
        GROUP BY l.nomePeca
        ORDER BY SUM(l.quantidadeSaida) DESC
    """)
    List<Map<String, Object>> findPecasMaisSaidaMes();

    @Query("""
        SELECT new map(l.nomePeca as name, SUM(l.quantidadeSaida) as value)
        FROM LogMovimentacaoJpaEntity l
        WHERE l.tipoPeca = 'SOFA'
          AND MONTH(l.dataMovimentacao) = MONTH(CURRENT_DATE)
          AND YEAR(l.dataMovimentacao) = YEAR(CURRENT_DATE)
        GROUP BY l.nomePeca
        ORDER BY SUM(l.quantidadeSaida) DESC
    """)
    List<Map<String, Object>> findSofasMaisSaidaMes();
}
