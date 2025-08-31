package com.example.Tozin_Solutions_back_end.V1.repository;

import com.example.Tozin_Solutions_back_end.V1.model.MovimentacaoEstoque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MovimentacaoEstoqueRepository extends JpaRepository<MovimentacaoEstoque, Long> {

    @Modifying
    @Query("DELETE FROM MovimentacaoEstoque m WHERE m.peca.id = :pecaId")
    void deleteByPecaId(@Param("pecaId") Long pecaId);

}
