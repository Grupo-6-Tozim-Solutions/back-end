package com.example.Tozin_Solutions_back_end.service;

import com.example.Tozin_Solutions_back_end.config.GerenciadorTokenJwt;
import com.example.Tozin_Solutions_back_end.dto.usuarioDTO.*;
import com.example.Tozin_Solutions_back_end.model.Usuario;
import com.example.Tozin_Solutions_back_end.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
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

    public UsuarioService(UsuarioRepository repository, AuthenticationManager authenticationManager, GerenciadorTokenJwt gerenciadorTokenJwt, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.authenticationManager = authenticationManager;
        this.gerenciadorTokenJwt = gerenciadorTokenJwt;
        this.passwordEncoder = passwordEncoder;
    }

    public DadosUsuarioDTO cadastrarUsuario(@Valid CadastrarUsuarioDTO dadosCadastro){
        if (!dadosCadastro.getEmail().contains("@")){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O email inserido precisa conter um arroba (@)");
        }

        if(dadosCadastro.getEmail().length() > 50 ){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O email não pode conter mais de 50 caracteres");
        }

        if (repository.findByEmail(dadosCadastro.getEmail()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O email inserido já é utilizado");
        }

        if(dadosCadastro.getSenha().length() < 4 ) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A senha não pode conter menos de 4 caracteres");
        }

        if(dadosCadastro.getSenha().length() > 20 ) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A senha não pode conter mais de 20 caracteres");
        }

        if(!Objects.equals(dadosCadastro.getSenha(), dadosCadastro.getConfirmarSenha())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "As senhas não coincidem");
        }

        if(dadosCadastro.getNome().length() < 3) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O nome não pode conter menos de 3 caracteres");
        }

        if(dadosCadastro.getNome().length() > 15) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O nome não pode conter mais de 15 caracteres");
        }

        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(dadosCadastro.getNome());
        novoUsuario.setEmail(dadosCadastro.getEmail());
        novoUsuario.setSenha(passwordEncoder.encode(dadosCadastro.getSenha()));

        repository.save(novoUsuario);

        return new DadosUsuarioDTO(novoUsuario.getId(), novoUsuario.getNome(), novoUsuario.getEmail());
    }


    public Optional<DadosUsuarioDTO> retornarPorId(Long id){
        return repository.findById(id)
                .map(usuario -> new DadosUsuarioDTO(usuario.getId(),usuario.getNome(), usuario.getEmail()));
    }

    public List<DadosUsuarioDTO> retornarTodos(){
        List<Usuario> usuarios = repository.findAll();
        List<DadosUsuarioDTO> dadosUsuarios = usuarios.stream()
                .map(usuario -> new DadosUsuarioDTO(usuario.getId(), usuario.getNome(), usuario.getEmail())).toList();
        return dadosUsuarios;
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
            return new DadosUsuarioDTO(atualizado.getId(), atualizado.getNome(), atualizado.getEmail());
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
                .orElseThrow(() -> new BadCredentialsException("Email ou senha incorretos"));
        if (!usuarioAutenticado.getSenha().equals(usuario.getSenha())) {
            throw new BadCredentialsException("Email ou senha incorretos");
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String token = gerenciadorTokenJwt.generateToken(authentication);

        return UsuarioMapper.of(usuarioAutenticado, token);
    }
}

