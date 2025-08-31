package com.example.Tozin_Solutions_back_end.V2.infrastructure.persistence.jpa;

import com.example.Tozin_Solutions_back_end.V2.core.application.usuarioApplication.port.UsuarioRepositoryPort;
import com.example.Tozin_Solutions_back_end.V2.core.domain.usuarioDomain.Usuario;
import com.example.Tozin_Solutions_back_end.V2.infrastructure.persistence.mapper.UsuarioEntityMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UsuarioJpaAdapter implements UsuarioRepositoryPort {

    private final UsuarioJpaRepository usuarioJpaRepository;

    public UsuarioJpaAdapter(UsuarioJpaRepository usuarioJpaRepository) {
        this.usuarioJpaRepository = usuarioJpaRepository;
    }

    @Override
    public Usuario salvar(Usuario usuario) {
        UsuarioJpaEntity entity = UsuarioEntityMapper.DomainToEntity(usuario);
        UsuarioJpaEntity savedEntity = usuarioJpaRepository.save(entity);;
        return UsuarioEntityMapper.EntityToDomain(savedEntity);
    }

    @Override
    public Optional<Usuario> buscarPorEmail(String email){
        return usuarioJpaRepository.findByEmail(email).map(UsuarioEntityMapper::EntityToDomain);
    }

    @Override
    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioJpaRepository.findById(id)
                .map(UsuarioEntityMapper::EntityToDomain);
    }

    @Override
    public boolean existePorEmail(String email) {
        return usuarioJpaRepository.existsByEmail(email);
    }
}
