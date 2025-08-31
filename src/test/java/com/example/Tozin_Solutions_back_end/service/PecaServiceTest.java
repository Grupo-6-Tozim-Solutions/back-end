package com.example.Tozin_Solutions_back_end.service;

import com.example.Tozin_Solutions_back_end.V1.service.MovimentacaoEstoqueService;
import com.example.Tozin_Solutions_back_end.V1.service.PecaService;
import com.example.Tozin_Solutions_back_end.V1.dto.pecaDTO.AtualizarPecaDTO;
import com.example.Tozin_Solutions_back_end.V1.dto.pecaDTO.CadastrarPecaDTO;
import com.example.Tozin_Solutions_back_end.V1.model.Peca;
import com.example.Tozin_Solutions_back_end.V1.repository.PecaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PecaServiceTest {

    @InjectMocks
    private PecaService pecaService;

    @Mock
    private PecaRepository repository;

    @Mock
    private MovimentacaoEstoqueService movimentacaoService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void deveCadastrarPeca_QuandoCadastroNomeValido() {
        CadastrarPecaDTO dto = new CadastrarPecaDTO("Parafuso", 100.0, 10.0);
        Peca peca = new Peca();
        peca.setNome("Parafuso");
        peca.setQuantidadeEstoque(100.0);
        peca.setQuantidadeMinima(10.0);

        when(repository.save(any(Peca.class))).thenReturn(peca);

        Peca result = pecaService.salvarPeca(dto);

        assertEquals("Parafuso", result.getNome());
        verify(repository).save(any(Peca.class));
    }

    @Test
    public void deveLancarErro_QuandoCadastroNomeVazio() {
        CadastrarPecaDTO dto = new CadastrarPecaDTO("", 100.0, 10.0);

        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> pecaService.salvarPeca(dto));
        assertEquals("400 BAD_REQUEST \"A peça deve ter um nome\"", ex.getMessage());
    }

    @Test
    public void deveLancarErro_QuandoCadastroNomeLongo() {
        String nomeLongo = "A".repeat(41);
        CadastrarPecaDTO dto = new CadastrarPecaDTO(nomeLongo, 100.0, 10.0);

        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> pecaService.salvarPeca(dto));
        assertEquals("400 BAD_REQUEST \"O nome da peça não pode ter mais de 40 caracteres\"", ex.getMessage());
    }

    @Test
    public void deveLancarErro_QuandoCadastroNomeCurto() {
        CadastrarPecaDTO dto = new CadastrarPecaDTO("AB", 100.0, 10.0);

        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> pecaService.salvarPeca(dto));
        assertEquals("400 BAD_REQUEST \"O nome da peça não pode ter menos de 3 caracteres\"", ex.getMessage());
    }

    @Test
    public void deveLancarErro_QuandoCadastroNomeJaExistente() {
        CadastrarPecaDTO dto = new CadastrarPecaDTO("Parafuso", 100.0, 10.0);

        when(repository.existsByNomeIgnoreCase("Parafuso")).thenReturn(true);

        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> pecaService.salvarPeca(dto));
        assertEquals("400 BAD_REQUEST \"Já existe uma peça com esse nome\"", ex.getMessage());
    }

    @Test
    public void deveAtualizarPeca_QuandoNomeValido() {
        AtualizarPecaDTO dto = new AtualizarPecaDTO("Parafuso Novo", 5.0);
        Peca peca = new Peca("Parafuso", 100.0, 10.0);

        when(repository.findById(1L)).thenReturn(Optional.of(peca));
        when(repository.save(any(Peca.class))).thenReturn(peca);

        Optional<Peca> atualizado = pecaService.atualizarPeca(1L, dto);

        assertTrue(atualizado.isPresent());
        assertEquals("Parafuso Novo", atualizado.get().getNome());
    }

    @Test
    public void deveLancarErro_QuandoAtualizarNomeLongo() {
        String nomeLongo = "A".repeat(26);
        AtualizarPecaDTO dto = new AtualizarPecaDTO(nomeLongo, 5.0);
        Peca peca = new Peca("Antigo", 100.0, 10.0);

        when(repository.findById(1L)).thenReturn(Optional.of(peca));

        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> pecaService.atualizarPeca(1L, dto));
        assertEquals("400 BAD_REQUEST \"O nome da peça não pode ter mais de 25 caracteres\"", ex.getMessage());
    }

    @Test
    public void deveLancarErro_QuandoAtualizarNomeCurto() {
        AtualizarPecaDTO dto = new AtualizarPecaDTO("A", 5.0);
        Peca peca = new Peca("Antigo", 100.0, 10.0);

        when(repository.findById(1L)).thenReturn(Optional.of(peca));

        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> pecaService.atualizarPeca(1L, dto));
        assertEquals("400 BAD_REQUEST \"O nome da peça não pode ter menos de 3 caracteres\"", ex.getMessage());
    }

    @Test
    public void deveLancarErro_QuandoAtualizarNomeJaExistente() {
        AtualizarPecaDTO dto = new AtualizarPecaDTO("Parafuso", 5.0);
        Peca peca = new Peca("Original", 100.0, 10.0);

        when(repository.findById(1L)).thenReturn(Optional.of(peca));
        when(repository.existsByNomeIgnoreCase("Parafuso")).thenReturn(true);

        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> pecaService.atualizarPeca(1L, dto));
        assertEquals("400 BAD_REQUEST \"Já existe uma peça com esse nome\"", ex.getMessage());
    }

    @Test
    public void deveLancarErro_QuandoAtualizarNomeVazio() {
        AtualizarPecaDTO dto = new AtualizarPecaDTO("", 5.0);
        Peca peca = new Peca("Antigo", 100.0, 10.0);

        when(repository.findById(1L)).thenReturn(Optional.of(peca));

        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> pecaService.atualizarPeca(1L, dto));
        assertEquals("400 BAD_REQUEST \"O nome da peça não pode estar vazio\"", ex.getMessage());
    }

}