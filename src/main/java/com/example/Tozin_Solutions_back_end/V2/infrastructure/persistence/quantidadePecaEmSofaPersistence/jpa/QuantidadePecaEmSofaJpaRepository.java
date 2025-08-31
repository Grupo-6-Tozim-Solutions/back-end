package com.example.Tozin_Solutions_back_end.V2.infrastructure.persistence.quantidadePecaEmSofaPersistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuantidadePecaEmSofaJpaRepository extends JpaRepository<QuantidadePecaEmSofaJpaEntity, Long> {
    Optional<QuantidadePecaEmSofaJpaEntity> findBySofaIdAndPecaId(Long sofaId, Long pecaId);
    List<QuantidadePecaEmSofaJpaEntity> findBySofaId(Long sofaId);
    List<QuantidadePecaEmSofaJpaEntity> findByPecaId(Long pecaId);

    @Modifying
    @Query("DELETE FROM QuantidadePecaEmSofaJpaEntity q WHERE q.sofaId = :sofaId")
    void deleteAllBySofaId(@Param("sofaId") Long sofaId);

    @Modifying
    @Query("DELETE FROM QuantidadePecaEmSofaJpaEntity q WHERE q.sofaId = :sofaId AND q.pecaId = :pecaId")
    void deleteBySofaIdAndPecaId(@Param("sofaId") Long sofaId, @Param("pecaId") Long pecaId);
}