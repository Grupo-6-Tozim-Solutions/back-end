package com.example.Tozin_Solutions_back_end.service;

import com.example.Tozin_Solutions_back_end.dto.movimentacaoEstoqueDTO.RegistrarMovimentacaoDTO;
import com.example.Tozin_Solutions_back_end.model.MovimentacaoEstoque;
import com.example.Tozin_Solutions_back_end.model.Peca;
import com.example.Tozin_Solutions_back_end.repository.MovimentacaoEstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovimentacaoEstoqueService {

    @Autowired
    private MovimentacaoEstoqueRepository repository;

    public MovimentacaoEstoque registrarMovimentacao(RegistrarMovimentacaoDTO dadosMovimentacao){
        MovimentacaoEstoque movimentacao = new MovimentacaoEstoque();

        movimentacao.setQuantidadeEntrada(dadosMovimentacao.getQuantidadeEntrada());
        movimentacao.setQuantidadeSaida(dadosMovimentacao.getQuantidadeSaida());
        movimentacao.setPeca(dadosMovimentacao.getPeca());

        return repository.save(movimentacao);
    }

    public void deletarPorPecaId(Long pecaId) {
        repository.deleteByPecaId(pecaId);
    }


    public List<MovimentacaoEstoque> retornarTodas(){
        return repository.findAll();
    }

    public Optional<MovimentacaoEstoque> retornarPorId(Long id){
        return repository.findById(id);
    }
}
