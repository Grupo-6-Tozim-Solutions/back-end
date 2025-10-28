package com.example.Tozin_Solutions_back_end.V2.infrastructure.persistence.PecaPersistence.Jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PecaJpaRepository extends JpaRepository<PecaJpaEntity, Long> {

    Optional<PecaJpaEntity> findByNomeIgnoreCase(String nome);

    boolean existsByNomeIgnoreCase(String nome);

    List<PecaJpaEntity> findAllByOrderByNomeAsc();

    @Query("SELECT p FROM PecaJpaEntity p WHERE p.quantidadeEstoque <= p.quantidadeMinima ORDER BY p.quantidadeEstoque ASC")
    List<PecaJpaEntity> findPecasComEstoqueCritico();

    @Query("SELECT p FROM PecaJpaEntity p WHERE LOWER(p.nome) LIKE LOWER(CONCAT('%', :nome, '%')) ORDER BY p.nome")
    List<PecaJpaEntity> findByNomeContainingIgnoreCase(String nome);


    Page<PecaJpaEntity> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
}
