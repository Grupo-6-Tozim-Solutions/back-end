package com.example.Tozin_Solutions_back_end.V2.core.application.sofaApplication.port;

import com.example.Tozin_Solutions_back_end.V2.core.domain.SofaDomain.Sofa;

import java.util.List;
import java.util.Optional;


public interface SofaRepositoryPort {
    Sofa salvar(Sofa sofa);
    Optional<Sofa> buscarPorId(Long id);
    Optional<Sofa> buscarPorModelo(String modelo);
    List<Sofa> buscarTodos();
    boolean existePorModelo(String modelo);
    void deletarPorId(Long id);
}