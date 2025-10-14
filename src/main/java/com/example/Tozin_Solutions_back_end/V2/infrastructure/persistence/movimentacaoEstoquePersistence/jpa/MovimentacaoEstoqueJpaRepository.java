package com.example.Tozin_Solutions_back_end.V2.infrastructure.persistence.movimentacaoEstoquePersistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MovimentacaoEstoqueJpaRepository extends JpaRepository<MovimentacaoEstoqueJpaEntity, Long> {

    @Modifying
    @Query("DELETE FROM MovimentacaoEstoqueJpaEntity m WHERE m.peca.id = :pecaId")
    void deleteByPecaId(@Param("pecaId") Long pecaId);

    List<MovimentacaoEstoqueJpaEntity> findByPecaId(Long pecaId);
}
