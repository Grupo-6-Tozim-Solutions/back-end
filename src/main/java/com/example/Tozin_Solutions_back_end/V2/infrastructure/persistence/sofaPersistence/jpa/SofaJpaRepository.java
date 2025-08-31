package com.example.Tozin_Solutions_back_end.V2.infrastructure.persistence.sofaPersistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SofaJpaRepository extends JpaRepository<SofaJpaEntity, Long> {
    Optional<SofaJpaEntity> findByModeloIgnoreCase(String modelo);
    boolean existsByModeloIgnoreCase(String modelo);
}
