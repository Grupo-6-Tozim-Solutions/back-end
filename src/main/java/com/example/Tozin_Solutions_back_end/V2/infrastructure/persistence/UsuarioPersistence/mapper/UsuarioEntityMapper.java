package com.example.Tozin_Solutions_back_end.V2.infrastructure.persistence.UsuarioPersistence.mapper;

import com.example.Tozin_Solutions_back_end.V2.core.domain.usuarioDomain.Usuario;
import com.example.Tozin_Solutions_back_end.V2.core.domain.usuarioDomain.valueObjects.Email;
import com.example.Tozin_Solutions_back_end.V2.core.domain.usuarioDomain.valueObjects.Nome;
import com.example.Tozin_Solutions_back_end.V2.core.domain.usuarioDomain.valueObjects.SenhaHash;
import com.example.Tozin_Solutions_back_end.V2.infrastructure.persistence.UsuarioPersistence.jpa.UsuarioJpaEntity;

import java.util.Objects;

public class UsuarioEntityMapper {

    public static UsuarioJpaEntity DomainToEntity(Usuario usuario) {
        if(Objects.isNull(usuario)){
            return null;
        }
        UsuarioJpaEntity userEntity = new UsuarioJpaEntity();
        userEntity.setNome(usuario.getNome().value());
        userEntity.setEmail(usuario.getEmail().value());
        userEntity.setSenhaHash(usuario.getSenhaHash().value());
        return userEntity;
    }

    public static Usuario EntityToDomain(UsuarioJpaEntity entity) {
        if(Objects.isNull(entity)){
            return null;
        }
        return Usuario.existente(
                entity.getId(),
                new Nome(entity.getNome()),
                new Email(entity.getEmail()),
                new SenhaHash(entity.getSenhaHash())
        );
    }
    public static void updateEntity(UsuarioJpaEntity entity, Usuario usuario) {
        if (usuario == null || entity == null) {
            return;
        }
        entity.setNome(usuario.getNome().value());
        entity.setEmail(usuario.getEmail().value());
        if (usuario.getSenhaHash() != null) {
            entity.setSenhaHash(usuario.getSenhaHash().value());
        }
    }
}
