package com.example.Tozin_Solutions_back_end.service;

import com.example.Tozin_Solutions_back_end.dto.usuarioDTO.*;
import com.example.Tozin_Solutions_back_end.model.Usuario;
import com.example.Tozin_Solutions_back_end.repository.UsuarioRepository;
import com.example.Tozin_Solutions_back_end.config.GerenciadorTokenJwt;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    public DadosUsuarioDTO cadastrarUsuario(@Valid CadastrarUsuarioDTO dadosCadastro) {

        // Verificar se já existe e-mail cadastrado
        if (repository.findByEmail(dadosCadastro.getEmail()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O email inserido já é utilizado");
        }

        // Validação de email
        String email = dadosCadastro.getEmail();
        if (!email.contains("@")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O email inserido precisa conter um arroba (@)");
        }
        if (email.length() > 50) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O email não pode conter mais de 50 caracteres");
        }

        // Validação de senha
        String senha = dadosCadastro.getSenha();
        if (senha.length() < 4 || senha.length() > 20) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A senha deve conter entre 4 e 20 caracteres");
        }

        // Validação de confirmar senha
        if (!senha.equals(dadosCadastro.getConfirmarSenha())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "As senhas não coincidem.");
        }

        // Validação de nome
        String nome = dadosCadastro.getNome();
        if (nome.length() < 3 || nome.length() > 15) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O nome deve conter entre 3 e 15 caracteres");
        }

        // Cadastro
        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(nome);
        novoUsuario.setEmail(email);
        novoUsuario.setSenha(passwordEncoder.encode(senha));

        repository.save(novoUsuario);

        return new DadosUsuarioDTO(novoUsuario.getId(), novoUsuario.getNome(), novoUsuario.getEmail());
    }

    public UsuarioTokenDTO autenticar(Usuario usuario) {
        try {
            final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(
                    usuario.getEmail(), usuario.getSenha());

            final Authentication authentication = authenticationManager.authenticate(credentials);

            Usuario usuarioAutenticado = repository.findByEmail(usuario.getEmail())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nome ou senha incorretos"));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            final String token = gerenciadorTokenJwt.generateToken(authentication);

            return UsuarioMapper.of(usuarioAutenticado, token);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nome ou senha incorretos");
        }
    }


}
