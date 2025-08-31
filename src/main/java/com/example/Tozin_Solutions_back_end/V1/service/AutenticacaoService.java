package com.example.Tozin_Solutions_back_end.V1.service;

import com.example.Tozin_Solutions_back_end.V1.dto.usuarioDTO.UsuarioDetalhesDTO;
import com.example.Tozin_Solutions_back_end.V1.model.Usuario;
import com.example.Tozin_Solutions_back_end.V1.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuarioOptional = repository.findByEmail(username);

        if(usuarioOptional.isEmpty()){
            throw new UsernameNotFoundException("Usuário %s não encontrado".formatted(username));
        }

        return new UsuarioDetalhesDTO(usuarioOptional.get());
    }
}
