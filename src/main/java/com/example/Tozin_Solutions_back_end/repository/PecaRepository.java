package com.example.Tozin_Solutions_back_end.repository;

import com.example.Tozin_Solutions_back_end.model.Peca;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PecaRepository extends JpaRepository<Peca, Long> {
}
