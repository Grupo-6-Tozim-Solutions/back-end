package com.example.Tozin_Solutions_back_end.service;

import com.example.Tozin_Solutions_back_end.dto.pecaDTO.AtualizarPecaDTO;
import com.example.Tozin_Solutions_back_end.dto.pecaDTO.CadastrarPecaDTO;
import com.example.Tozin_Solutions_back_end.model.Peca;
import com.example.Tozin_Solutions_back_end.repository.PecaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PecaService {

    @Autowired
    private PecaRepository repository;

    public Peca salvarPeca(@Valid CadastrarPecaDTO dadosNovaPeca){
        Peca novaPeca = new Peca();

        novaPeca.setNome(dadosNovaPeca.getNome());
        novaPeca.setQuantidadeEstoque(dadosNovaPeca.getQuantidadeEstoque());
        novaPeca.setQuantidadeMinima(dadosNovaPeca.getQuantidadeMinima());

        repository.save(novaPeca);
        return novaPeca;
    }

    public List<Peca> listarTodas(){
        return repository.findAll();
    }

    public Optional<Peca> buscarPorId(Long id){
        return repository.findById(id);
    }

    public Optional<Peca> atualizarEstoque(Long id, Integer quantidadeAdicional){
        return repository.findById(id).map(peca -> {
            if (quantidadeAdicional != null){
                peca.setQuantidadeEstoque(peca.getQuantidadeEstoque() + quantidadeAdicional);
            }
            return repository.save(peca);
        });
    }

    public List<Peca> verificarPecasComValorCritico(){
        List<Peca> todasPecas = repository.findAll();

        List<Peca> pecasComValorCritico = todasPecas.stream()
                .filter(peca -> peca.getQuantidadeEstoque() <= peca.getQuantidadeMinima()).toList();

        return pecasComValorCritico;
    }

    public Optional<Peca> atualizarPeca(Long id,@Valid AtualizarPecaDTO novosDados){
        return repository.findById(id).map(peca -> {
            if(novosDados.getNome() != null && !novosDados.getNome().isEmpty()){
                peca.setNome(novosDados.getNome());
            }

            if (novosDados.getQuantidadeMinima() != null){
                peca.setQuantidadeMinima(novosDados.getQuantidadeMinima());
            }

            return repository.save(peca);
        });
    }

    public Boolean deletarPeca(Long id){
        if (repository.existsById(id)){
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
