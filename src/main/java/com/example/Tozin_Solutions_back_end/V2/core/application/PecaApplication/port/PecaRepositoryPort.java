package com.example.Tozin_Solutions_back_end.V2.core.application.PecaApplication.port;

import com.example.Tozin_Solutions_back_end.V2.core.domain.PecaDomain.Peca;

import java.util.List;
import java.util.Optional;

public interface PecaRepositoryPort {
    Peca salvar(Peca peca);
    Optional<Peca> buscarPorId(Long id);
    Optional<Peca> buscarPorNome(String nome);
    List<Peca> buscarTodas();
    boolean existePorNome(String nome);
    void deletarPorId(Long id);
    List<Peca> buscarComEstoqueCritico();
}
