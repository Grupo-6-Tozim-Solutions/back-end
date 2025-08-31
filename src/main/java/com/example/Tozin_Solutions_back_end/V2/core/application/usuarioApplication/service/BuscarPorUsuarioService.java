package com.example.Tozin_Solutions_back_end.V2.application.usuarioApplication.service;

import com.example.Tozin_Solutions_back_end.V2.application.usuarioApplication.dto.UsuarioOutput;
import com.example.Tozin_Solutions_back_end.V2.application.usuarioApplication.repository.UsuarioRepository;
import com.example.Tozin_Solutions_back_end.V2.domain.usuarioDomain.Usuario;
import com.example.Tozin_Solutions_back_end.V2.domain.usuarioDomain.excepetion.UsuarioNaoEncontradoException;

public class BuscarPorUsuarioService {

    private final UsuarioRepository usuarioRepository;

    public BuscarPorUsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioOutput executar(Long id){

        Usuario usuario = usuarioRepository.buscarPorId(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(id));

        return new UsuarioOutput(
                usuario.getNome().value(),
                usuario.getEmail().value()
        );
    }
}
