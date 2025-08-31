package com.example.Tozin_Solutions_back_end.V1.repository;

import com.example.Tozin_Solutions_back_end.V1.model.QuantidadePecaEmSofa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QuantidadePecaEmSofaRepository extends JpaRepository<QuantidadePecaEmSofa, Long> {

    Optional<QuantidadePecaEmSofa> findByIdSofaAndIdPeca(Long idSofa, Long idPeca);

    List<QuantidadePecaEmSofa> findAllByIdSofa(Long idSofa);

    void deleteAllByIdSofa(Long idSofa);

}
