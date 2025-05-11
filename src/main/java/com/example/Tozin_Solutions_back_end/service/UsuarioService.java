package com.example.Tozin_Solutions_back_end.service;

import com.example.Tozin_Solutions_back_end.config.GerenciadorTokenJwt;
import com.example.Tozin_Solutions_back_end.dto.usuarioDTO.*;
import com.example.Tozin_Solutions_back_end.model.Usuario;
import com.example.Tozin_Solutions_back_end.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private GerenciadorTokenJwt gerenciadorTokenJwt;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public DadosUsuarioDTO cadastrarUsuario(@Valid CadastrarUsuarioDTO dadosCadastro){
        Usuario novoUsuario = new Usuario();

        novoUsuario.setNome(dadosCadastro.getNome());
        novoUsuario.setEmail(dadosCadastro.getEmail());
        novoUsuario.setSenha(passwordEncoder.encode(dadosCadastro.getSenha()));

        repository.save(novoUsuario);

        return new DadosUsuarioDTO(dadosCadastro.getNome(), dadosCadastro.getEmail());
    }

    public Optional<DadosUsuarioDTO> retornarPorId(Long id){
        return repository.findById(id)
                .map(usuario -> new DadosUsuarioDTO(usuario.getNome(), usuario.getEmail()));
    }

    public List<Usuario> retornarTodos(){
        return repository.findAll();
    }

    public Optional<DadosUsuarioDTO> atualizarUsuario(Long id, AtualizarUsuarioDTO dadosRecebidos) {
        return repository.findById(id).map(usuario -> {
            if(dadosRecebidos.getNome() != null && !dadosRecebidos.getNome().isEmpty()){
                usuario.setNome(dadosRecebidos.getNome());
            }

            if (dadosRecebidos.getEmail() != null && !dadosRecebidos.getEmail().isEmpty()){
                usuario.setEmail(dadosRecebidos.getEmail());
            }

            if(dadosRecebidos.getSenha() != null && !dadosRecebidos.getSenha().isEmpty()){
                usuario.setSenha(dadosRecebidos.getSenha());
            }

            Usuario atualizado = repository.save(usuario);
            return new DadosUsuarioDTO(atualizado.getNome(), atualizado.getEmail());
        });
    }

    public void desativarUsuario(Long id){
        repository.deleteById(id);
    }

    public UsuarioTokenDTO autenticar(Usuario usuario){
        final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(
                usuario.getEmail(), usuario.getSenha());

        final Authentication authentication = this.authenticationManager.authenticate(credentials);

        Usuario usuarioAutenticado = repository.findByEmail(usuario.getEmail())
                .orElseThrow(() -> new ResponseStatusException(404, "Email do usuário não cadastrado", null));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String token = gerenciadorTokenJwt.generateToken(authentication);

        return UsuarioMapper.of(usuarioAutenticado, token);
    }
}

