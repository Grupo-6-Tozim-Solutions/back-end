package com.example.Tozin_Solutions_back_end.service;

import com.example.Tozin_Solutions_back_end.config.GerenciadorTokenJwt;
import com.example.Tozin_Solutions_back_end.dto.usuarioDTO.CadastrarUsuarioDTO;
import com.example.Tozin_Solutions_back_end.dto.usuarioDTO.LoginUsuarioDTO;
import com.example.Tozin_Solutions_back_end.model.Usuario;
import com.example.Tozin_Solutions_back_end.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UsuarioServiceTest {

    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private GerenciadorTokenJwt gerenciadorTokenJwt;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveLancarErro_QuandoEmailJaExiste() {
        CadastrarUsuarioDTO dto = new CadastrarUsuarioDTO("Daniel", "daniel@email.com", "senha123");

        when(usuarioRepository.findByEmail(dto.getEmail())).thenReturn(Optional.of(new Usuario()));

        assertThrows(ResponseStatusException.class, () -> {
            usuarioService.cadastrarUsuario(dto);
        });
    }

    @Test
    void deveLancarErro_QuandoEmailNaoTemArroba() {
        CadastrarUsuarioDTO dto = new CadastrarUsuarioDTO("João", "emailemail.com", "senha123");
        when(usuarioRepository.findByEmail(dto.getEmail())).thenReturn(Optional.empty());

        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> usuarioService.cadastrarUsuario(dto));
        assertEquals("O email inserido precisa conter um arroba (@)", ex.getReason());
    }

    @Test
    void deveLancarErro_QuandoEmailPassaDe50Caracteres() {
        String email = "muito.longo.email.que.passa.de.cinquenta.caracteres@email.com";
        CadastrarUsuarioDTO dto = new CadastrarUsuarioDTO("João", email, "senha123");
        when(usuarioRepository.findByEmail(dto.getEmail())).thenReturn(Optional.empty());

        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> usuarioService.cadastrarUsuario(dto));
        assertEquals("O email não pode conter mais de 50 caracteres", ex.getReason());
    }

    @Test
    void deveCadastrarUsuario_QuandoEmailValido() {
        CadastrarUsuarioDTO dto = new CadastrarUsuarioDTO("João", "joao@email.com", "senha123");
        when(usuarioRepository.findByEmail(dto.getEmail())).thenReturn(Optional.of(new Usuario()));

        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> usuarioService.cadastrarUsuario(dto));
    }


    @Test
    void deveLancarErro_QuandoSenhaCurta() {
        CadastrarUsuarioDTO dto = new CadastrarUsuarioDTO("João", "email@email.com", "123");
        when(usuarioRepository.findByEmail(dto.getEmail())).thenReturn(Optional.empty());

        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> usuarioService.cadastrarUsuario(dto));
        assertEquals("A senha não pode conter menos de 4 caracteres", ex.getReason());
    }

    @Test
    void deveLancarErro_QuandoSenhaLonga() {
        CadastrarUsuarioDTO dto = new CadastrarUsuarioDTO("João", "email@email.com", "123456789101112131415");
        when(usuarioRepository.findByEmail(dto.getEmail())).thenReturn(Optional.empty());

        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> usuarioService.cadastrarUsuario(dto));
        assertEquals("A senha não pode conter mais de 20 caracteres", ex.getReason());
    }

    @Test
    void deveCadastrarUsuario_QuandoSenhaValida() {
        CadastrarUsuarioDTO dto = new CadastrarUsuarioDTO("João", "joao@email.com", "12345");
        when(usuarioRepository.findByEmail(dto.getEmail())).thenReturn(Optional.of(new Usuario()));
        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> usuarioService.cadastrarUsuario(dto));
    }

    @Test
    void deveLancarErro_QuandoNomeMuitoCurto() {
        CadastrarUsuarioDTO dto = new CadastrarUsuarioDTO("Jo", "email@email.com", "senha123");
        when(usuarioRepository.findByEmail(dto.getNome())).thenReturn(Optional.empty());

        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> usuarioService.cadastrarUsuario(dto));
        assertEquals("O nome não pode conter menos de 3 caracteres", ex.getReason());
    }

    @Test
    void deveLancarErro_QuandoNomeMuitoLongo() {
        CadastrarUsuarioDTO dto = new CadastrarUsuarioDTO("João Silva dos Santos", "email@email.com", "senha123");
        when(usuarioRepository.findByNome(dto.getNome())).thenReturn(Optional.empty());

        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> usuarioService.cadastrarUsuario(dto));
        assertEquals("O nome não pode conter mais de 15 caracteres", ex.getReason());
    }

    @Test
    void deveLancarErro_QuandoNomeVazio() {
        CadastrarUsuarioDTO dto = new CadastrarUsuarioDTO("", "email@email.com", "senha123");
        when(usuarioRepository.findByNome(dto.getNome())).thenReturn(Optional.empty());

        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> usuarioService.cadastrarUsuario(dto));
        assertEquals("O nome não pode estar em branco", ex.getReason());
    }

    @Test
    void deveLancarErro_QuandoUsuarioNaoExisteNoLogin() {
        LoginUsuarioDTO dto = new LoginUsuarioDTO("inexistente@email.com", "senha123");
        when(usuarioRepository.findByEmail(dto.getEmail())).thenReturn(Optional.empty());

        Usuario usuario = new Usuario();
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());

        when(authenticationManager.authenticate(any())).thenThrow(new BadCredentialsException("Email ou senha incorretos"));

        BadCredentialsException ex = assertThrows(BadCredentialsException.class, () -> usuarioService.autenticar(usuario));
        assertEquals("Email ou senha incorretos", ex.getMessage());
    }

    @Test
    void deveLancarErro_QuandoSenhaIncorretaNoLogin() {

        CadastrarUsuarioDTO dtoC = new CadastrarUsuarioDTO("João", "email@email.com", "senha123");
        when(usuarioRepository.findByNome(dtoC.getNome())).thenReturn(Optional.empty());

        LoginUsuarioDTO dtoL = new LoginUsuarioDTO("email@email.com", "senha132");
        when(usuarioRepository.findByEmail(dtoL.getEmail())).thenReturn(Optional.empty());

        Usuario usuario = new Usuario();
        usuario.setEmail(dtoL.getEmail());
        usuario.setSenha(dtoL.getSenha());

        when(authenticationManager.authenticate(any())).thenThrow(new BadCredentialsException("Email ou senha incorretos"));

        BadCredentialsException ex = assertThrows(BadCredentialsException.class, () -> usuarioService.autenticar(usuario));
        assertEquals("Email ou senha incorretos", ex.getMessage());
    }
}
