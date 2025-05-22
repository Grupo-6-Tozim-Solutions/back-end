package com.example.Tozin_Solutions_back_end.repository;

import com.example.Tozin_Solutions_back_end.model.Peca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PecaRepository extends JpaRepository<Peca, Long> {
    boolean existsByNomeIgnoreCase(String nome);
}
