package com.example.Tozin_Solutions_back_end.repository;

import com.example.Tozin_Solutions_back_end.model.MovimentacaoEstoque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface MovimentacaoEstoqueRepository extends JpaRepository<MovimentacaoEstoque, Long> {

    @Modifying
    @Query("DELETE FROM MovimentacaoEstoque m WHERE m.peca.id = :pecaId")
    void deleteByPecaId(@Param("pecaId") Long pecaId);

    // Peças que mais saíram no mês
    @Query("""
    SELECT new map(m.peca.nome as name, SUM(m.quantidadeSaida) as value)
    FROM MovimentacaoEstoque m
    WHERE MONTH(m.data) = MONTH(CURRENT_DATE) AND YEAR(m.data) = YEAR(CURRENT_DATE)
    GROUP BY m.peca.nome
    ORDER BY SUM(m.quantidadeSaida) DESC
""")
    List<Map<String, Object>> findPecasMaisSaidaMes();

    // List<MovimentacaoEstoque> findByPeca(Peca peca);
}
