package com.example.Tozin_Solutions_back_end.service;

import com.example.Tozin_Solutions_back_end.dto.movimentacaoEstoqueDTO.RegistrarMovimentacaoDTO;
import com.example.Tozin_Solutions_back_end.dto.quantidadePecaEmSofaDTO.RegistroQuantidadePecaEmSofaDTO;
import com.example.Tozin_Solutions_back_end.dto.sofaDTO.AtualizarSofaDTO;
import com.example.Tozin_Solutions_back_end.dto.sofaDTO.CadastrarSofaDTO;
import com.example.Tozin_Solutions_back_end.model.Peca;
import com.example.Tozin_Solutions_back_end.model.QuantidadePecaEmSofa;
import com.example.Tozin_Solutions_back_end.model.Sofa;
import com.example.Tozin_Solutions_back_end.repository.SofaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SofaService {

    @Autowired
    private SofaRepository repository;

    @Autowired
    private PecaService pecaService;

    @Autowired
    private MovimentacaoEstoqueService movimentacaoService;

    @Autowired
    private QuantidadePecaEmSofaService quantidadePecaEmSofaService;

    public Sofa salvarSofa(@Valid  CadastrarSofaDTO dadosSofa){
        Sofa sofa = new Sofa();

        sofa.setModelo(dadosSofa.getModelo());
        sofa.setDescricao(dadosSofa.getDescricao());

        return repository.save(sofa);
    }

    public List<Sofa> listarTodos(){
        return repository.findAll();
    }

    public Optional<Sofa> buscarPorId(Long id){
        return repository.findById(id);
    }

    public Optional<Sofa> adicionarPeca(Long idSofa, Long idPeca, Integer quantidadePecas){
        Optional<Peca> peca = pecaService.buscarPorId(idPeca);

        RegistroQuantidadePecaEmSofaDTO configuracao = new RegistroQuantidadePecaEmSofaDTO(idSofa, idPeca, quantidadePecas);
        quantidadePecaEmSofaService.salvarConfiguracao(configuracao);

        pecaService.removerQuantidadeEstoque(peca.get().getId(), quantidadePecas);

        RegistrarMovimentacaoDTO movimentacao = new RegistrarMovimentacaoDTO(quantidadePecas, 0, peca.get());

        return repository.findById(idSofa)
                .map(sofa -> {
                    sofa.adicionarPeca(peca.get());
                    return repository.save(sofa);
                });
    }

    public List<Peca> listarPecaPorSofa(Long id){
        return repository.findById(id).get().getPecas();
    }

    public Optional<Sofa> removerPeca(Long idSofa, Long idPeca){
        Optional<Peca> peca = pecaService.buscarPorId(idPeca);

        Optional<QuantidadePecaEmSofa> configuracao = quantidadePecaEmSofaService.encontrarPorIdSofaEPeca(idSofa, idPeca);

        pecaService.adicionarQuantidadeEstoque(peca.get().getId(), configuracao.get().getQuantidadePeca());

        return repository.findById(idSofa)
                .map(sofa -> {
                    sofa.removerPeca(peca.get());
                    return repository.save(sofa);
                });
    }

    public Optional<Sofa> atualizarDadosSofa(Long id,AtualizarSofaDTO novosDados){
        return repository.findById(id)
                .map(sofa -> {
                    if(sofa.getModelo() != null && !novosDados.getModelo().isEmpty()){
                        sofa.setModelo(novosDados.getModelo());
                    }

                    if(sofa.getDescricao() != null && !novosDados.getDescricao().isEmpty()){
                        sofa.setDescricao(novosDados.getDescricao());
                    }

                    return repository.save(sofa);
                });
    }
}
