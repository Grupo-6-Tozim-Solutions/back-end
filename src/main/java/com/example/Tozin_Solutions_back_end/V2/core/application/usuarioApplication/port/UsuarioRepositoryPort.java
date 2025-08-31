package com.example.Tozin_Solutions_back_end.V2.core.application.usuarioApplication.port;

import com.example.Tozin_Solutions_back_end.V2.core.domain.usuarioDomain.Usuario;

import java.util.Optional;

public interface UsuarioRepositoryPort {
    Usuario salvar(Usuario usuario);
    Optional<Usuario> buscarPorEmail(String email);
    Optional<Usuario> buscarPorId(Long id);
    boolean existePorEmail(String email);
}
