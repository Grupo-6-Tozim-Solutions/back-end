package com.example.Tozin_Solutions_back_end.service;

import com.example.Tozin_Solutions_back_end.dto.quantidadePecaEmSofaDTO.RegistroQuantidadePecaEmSofaDTO;
import com.example.Tozin_Solutions_back_end.model.QuantidadePecaEmSofa;
import com.example.Tozin_Solutions_back_end.repository.QuantidadePecaEmSofaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuantidadePecaEmSofaService {

    @Autowired
    private QuantidadePecaEmSofaRepository repository;

    public QuantidadePecaEmSofa salvarConfiguracao(RegistroQuantidadePecaEmSofaDTO dados){
        Optional<QuantidadePecaEmSofa> existente = repository.findByIdSofaAndIdPeca(dados.getIdSofa(), dados.getIdPeca());
        QuantidadePecaEmSofa quantidadePecaEmSofa;
        if (existente.isPresent()) {
            // Atualiza a quantidade existente
            quantidadePecaEmSofa = existente.get();
            quantidadePecaEmSofa.setQuantidadePeca(dados.getQuantidade());
        } else {
            // Cria nova relação
            quantidadePecaEmSofa = new QuantidadePecaEmSofa();
            quantidadePecaEmSofa.setIdSofa(dados.getIdSofa());
            quantidadePecaEmSofa.setIdPeca(dados.getIdPeca());
            quantidadePecaEmSofa.setQuantidadePeca(dados.getQuantidade());
        }
        return repository.save(quantidadePecaEmSofa);
    }

    // Para listar todas as peças de um sofá
    public List<QuantidadePecaEmSofa> listarPorIdSofa(Long idSofa) {
        return repository.findAllByIdSofa(idSofa);
    }

    // Para buscar uma configuração específica
    public Optional<QuantidadePecaEmSofa> encontrarPorIdSofaEPeca(Long idSofa, Long idPeca) {
        return repository.findByIdSofaAndIdPeca(idSofa, idPeca);
    }

    @Transactional
    public void removerConfiguracao(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public void removerTodasPorSofaId(Long idSofa) {
        repository.deleteAllByIdSofa(idSofa);
    }
}