package com.example.Tozin_Solutions_back_end.V2.infrastructure.persistence.UsuarioPersistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioJpaRepository extends JpaRepository<UsuarioJpaEntity,Long> {

    Optional<UsuarioJpaEntity> findByEmail(String email);
    boolean existsByEmail(String email);
    Optional<UsuarioJpaRepository> findByNome(String nome);
}
