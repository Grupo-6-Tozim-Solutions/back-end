package com.example.Tozin_Solutions_back_end.V2.infrastructure.persistence.producaoSofaPersistence.jpa;

import com.example.Tozin_Solutions_back_end.V2.core.domain.projectionDomain.ProducaoMensal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ProducaoSofaJpaRepository extends JpaRepository<ProducaoSofaJpaEntity, Long> {

    List<ProducaoSofaJpaEntity> findBySofaId(Long sofaId);

    List<ProducaoSofaJpaEntity> findByDataProducaoBetween(LocalDateTime inicio, LocalDateTime fim);

    @Query("SELECT MONTH(p.dataProducao) as mes, SUM(p.quantidade) as total " +
            "FROM ProducaoSofaJpaEntity p " +
            "WHERE YEAR(p.dataProducao) = YEAR(CURRENT_DATE) " +
            "GROUP BY MONTH(p.dataProducao)")
    List<ProducaoMensal> findProducaoAgrupadaPorMesAnoAtual();

    @Query("SELECT p FROM ProducaoSofaJpaEntity p WHERE YEAR(p.dataProducao) = YEAR(CURRENT_DATE)")
    List<ProducaoSofaJpaEntity> findByAnoAtual();
}