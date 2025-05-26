package com.example.Tozin_Solutions_back_end.repository;

import com.example.Tozin_Solutions_back_end.model.QuantidadePecaEmSofa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QuantidadePecaEmSofaRepository extends JpaRepository<QuantidadePecaEmSofa, Long> {

    Optional<QuantidadePecaEmSofa> findByIdSofaAndIdPeca(Long idSofa, Long idPeca);

    List<QuantidadePecaEmSofa> findAllByIdSofa(Long idSofa);
}
