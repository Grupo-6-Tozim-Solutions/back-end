package com.example.Tozin_Solutions_back_end.V2.core.application.usuarioApplication.service;

import com.example.Tozin_Solutions_back_end.V2.core.application.usuarioApplication.dto.UsuarioOutput;
import com.example.Tozin_Solutions_back_end.V2.core.application.usuarioApplication.port.UsuarioRepositoryPort;
import com.example.Tozin_Solutions_back_end.V2.core.application.usuarioApplication.useCase.BuscarUsuarioPorIdUseCase;
import com.example.Tozin_Solutions_back_end.V2.core.domain.usuarioDomain.Usuario;
import com.example.Tozin_Solutions_back_end.V2.core.domain.usuarioDomain.excepetion.UsuarioNaoEncontradoException;

public class BuscarPorUsuarioService implements BuscarUsuarioPorIdUseCase {

    private final UsuarioRepositoryPort usuarioRepositoryPort;

    public BuscarPorUsuarioService(UsuarioRepositoryPort usuarioRepositoryPort) {
        this.usuarioRepositoryPort = usuarioRepositoryPort;
    }

    public UsuarioOutput executar(Long id){

        Usuario usuario = usuarioRepositoryPort.buscarPorId(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(id));

        return new UsuarioOutput(
                usuario.getNome().value(),
                usuario.getEmail().value()
        );
    }
}
