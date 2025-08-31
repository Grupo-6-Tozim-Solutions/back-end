package com.example.Tozin_Solutions_back_end.V1.service;

import com.example.Tozin_Solutions_back_end.V1.dto.movimentacaoEstoqueDTO.RegistrarMovimentacaoDTO;
import com.example.Tozin_Solutions_back_end.V1.model.MovimentacaoEstoque;
import com.example.Tozin_Solutions_back_end.V1.repository.LogMovimentacaoRepository;
import com.example.Tozin_Solutions_back_end.V1.repository.MovimentacaoEstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class MovimentacaoEstoqueService {

    @Autowired
    private MovimentacaoEstoqueRepository repository;

    @Autowired
    private LogMovimentacaoRepository logMovimentacaoRepository;


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


    public List<Map<String, Object>> getPecasMaisSaidaMes() {
        return logMovimentacaoRepository.findPecasMaisSaidaMes();
    }

    public List<MovimentacaoEstoque> retornarTodas(){
        return repository.findAll();
    }


    public Optional<MovimentacaoEstoque> retornarPorId(Long id){
        return repository.findById(id);
    }
}
