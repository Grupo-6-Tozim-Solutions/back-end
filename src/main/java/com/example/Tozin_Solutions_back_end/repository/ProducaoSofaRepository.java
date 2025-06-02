package com.example.Tozin_Solutions_back_end.repository;

import com.example.Tozin_Solutions_back_end.model.ProducaoSofa;
import com.example.Tozin_Solutions_back_end.model.projection.ProducaoMensal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface ProducaoSofaRepository extends JpaRepository<ProducaoSofa, Long> {

    @Query("""
	SELECT MONTH(p.dataProducao) as mes, SUM(p.quantidade) as total
		FROM ProducaoSofa p
		WHERE YEAR(p.dataProducao) = YEAR(CURRENT_DATE)
           	GROUP BY MONTH(p.dataProducao)
           	ORDER BY MONTH(p.dataProducao)
	""")
    List<ProducaoMensal> findProducaoAgrupadaPorMesAnoAtual();

    // Sofás que mais saíram no mês (exemplo usando ProducaoSofa)
    @Query("""
    SELECT new map(s.modelo as name, SUM(p.quantidade) as value)
    FROM ProducaoSofa p
    JOIN Sofa s ON s.id = p.idSofa
    WHERE MONTH(p.dataProducao) = MONTH(CURRENT_DATE) AND YEAR(p.dataProducao) = YEAR(CURRENT_DATE)
    GROUP BY s.modelo
    ORDER BY SUM(p.quantidade) DESC
""")
    List<Map<String, Object>> findSofasMaisSaidaMes();

}
