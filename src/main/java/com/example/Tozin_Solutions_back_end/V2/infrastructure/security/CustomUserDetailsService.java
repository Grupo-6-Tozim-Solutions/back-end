// infrastructure/adapter/security/CustomUserDetailsService.java
package com.example.Tozin_Solutions_back_end.V2.infrastructure.security;

import com.example.Tozin_Solutions_back_end.V2.core.application.usuarioApplication.port.UsuarioRepositoryPort;
import com.example.Tozin_Solutions_back_end.V2.core.domain.usuarioDomain.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    private final UsuarioRepositoryPort usuarioRepositoryPort;

    public CustomUserDetailsService(UsuarioRepositoryPort usuarioRepositoryPort) {
        this.usuarioRepositoryPort = usuarioRepositoryPort;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            Usuario usuario = usuarioRepositoryPort.buscarPorEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + email));

            return User.builder()
                    .username(usuario.getEmail().value())
                    .password(usuario.getSenhaHash().value())
                    .roles("USER")
                    .build();

        } catch (Exception e) {
            logger.error("Erro ao carregar usuário: {}", email, e);
            throw new UsernameNotFoundException("Erro ao carregar usuário", e);
        }
    }
}