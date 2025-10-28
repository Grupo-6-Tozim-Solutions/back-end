package com.example.Tozin_Solutions_back_end.V2.core.application.pecaApplication.port;

import com.example.Tozin_Solutions_back_end.V2.core.domain.PecaDomain.Peca;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
    Page<Peca> buscarTodasPaginadas(Pageable pageable, String filter);
}
