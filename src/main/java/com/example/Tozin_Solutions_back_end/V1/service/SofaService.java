package com.example.Tozin_Solutions_back_end.service;

import com.example.Tozin_Solutions_back_end.dto.movimentacaoEstoqueDTO.RegistrarMovimentacaoDTO;
import com.example.Tozin_Solutions_back_end.dto.quantidadePecaEmSofaDTO.PecaComQuantidadeDTO;
import com.example.Tozin_Solutions_back_end.dto.quantidadePecaEmSofaDTO.RegistroQuantidadePecaEmSofaDTO;
import com.example.Tozin_Solutions_back_end.dto.sofaDTO.AdicaoPecaDTO;
import com.example.Tozin_Solutions_back_end.dto.sofaDTO.AtualizarSofaDTO;
import com.example.Tozin_Solutions_back_end.dto.sofaDTO.CadastrarSofaDTO;
import com.example.Tozin_Solutions_back_end.model.Peca;
import com.example.Tozin_Solutions_back_end.model.ProducaoSofa;
import com.example.Tozin_Solutions_back_end.model.QuantidadePecaEmSofa;
import com.example.Tozin_Solutions_back_end.model.Sofa;
import com.example.Tozin_Solutions_back_end.model.projection.ProducaoMensal;
import com.example.Tozin_Solutions_back_end.repository.LogMovimentacaoRepository;
import com.example.Tozin_Solutions_back_end.repository.ProducaoSofaRepository;
import com.example.Tozin_Solutions_back_end.repository.SofaRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class SofaService {

    @Autowired
    private SofaRepository repository;

    @Autowired
    private PecaService pecaService;

    @Autowired
    private ProducaoSofaRepository producaoSofaRepository;


    @Autowired
    private QuantidadePecaEmSofaService quantidadePecaEmSofaService;

    @Autowired
    private LogMovimentacaoRepository logMovimentacaoRepository;


    public Sofa salvarSofaComImagem(CadastrarSofaDTO dadosSofa, MultipartFile imagem) {
        Sofa sofa = new Sofa();
        sofa.setModelo(dadosSofa.getModelo());

        String pastaDestino = "uploads/sofas/";
        File pasta = new File(pastaDestino);
        if (!pasta.exists()) {
            pasta.mkdirs();
        }

        try {
            if (imagem != null && !imagem.isEmpty()) {
                String nomeImagem = System.currentTimeMillis() + "_" + imagem.getOriginalFilename();
                Path caminhoArquivo = Paths.get(pastaDestino + nomeImagem);
                Files.write(caminhoArquivo, imagem.getBytes());
                sofa.setCaminhoImagem("/" + pastaDestino + nomeImagem);
            } else {
                sofa.setCaminhoImagem("/uploads/sofas/imagem_padrao.jpg");
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar imagem", e);
        }

        return repository.save(sofa);
    }

    public List<Sofa> listarTodos() {
        return repository.findAll();
    }

    public Optional<Sofa> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Optional<Sofa> adicionarPeca(Long idSofa, List<AdicaoPecaDTO> pecasAssociadas) {
        Optional<Sofa> sofaEncontrado = repository.findById(idSofa);

        if (sofaEncontrado.isEmpty()) {
            return Optional.empty();
        }

        for (AdicaoPecaDTO pecaAssociada : pecasAssociadas) {
            Optional<Peca> peca = pecaService.buscarPorId(pecaAssociada.getIdPeca());

            if (peca.isEmpty()) {
                continue;
            }
            RegistroQuantidadePecaEmSofaDTO configuracao =
                    new RegistroQuantidadePecaEmSofaDTO(
                            idSofa,
                            pecaAssociada.getIdPeca(),
                            pecaAssociada.getQuantidade()
                    );

            quantidadePecaEmSofaService.salvarConfiguracao(configuracao);
        }

        return sofaEncontrado;
    }
    public List<PecaComQuantidadeDTO> listarPecaPorSofa(Long idSofa) {
        List<QuantidadePecaEmSofa> relacoes = quantidadePecaEmSofaService.listarPorIdSofa(idSofa);
        List<PecaComQuantidadeDTO> pecasComQuantidade = new ArrayList<>();

        for (QuantidadePecaEmSofa relacao : relacoes) {
            Optional<Peca> pecaOptional = pecaService.buscarPorId(relacao.getIdPeca());
            if (pecaOptional.isPresent()) {
                Peca peca = pecaOptional.get();
                pecasComQuantidade.add(new PecaComQuantidadeDTO(
                        peca.getId(),
                        peca.getNome(),
                        relacao.getQuantidadePeca(),
                        peca.getTipo() != null ? peca.getTipo().name() : null
                ));
            }
        }
        return pecasComQuantidade;
    }

    @Transactional
    public Optional<Sofa> removerPeca(Long idSofa, Long idPeca) {
        Optional<Sofa> sofa = repository.findById(idSofa);
        if (sofa.isEmpty()) {
            return Optional.empty();
        }

        Optional<Peca> peca = pecaService.buscarPorId(idPeca);
        Optional<QuantidadePecaEmSofa> configuracao = quantidadePecaEmSofaService.encontrarPorIdSofaEPeca(idSofa, idPeca);

        if (peca.isPresent() && configuracao.isPresent()) {
            pecaService.adicionarQuantidadeEstoque(peca.get().getId(), configuracao.get().getQuantidadePeca());
            quantidadePecaEmSofaService.removerConfiguracao(configuracao.get().getId());
        }

        return repository.findById(idSofa);
    }

    public Optional<Sofa> atualizarModeloEImagem(Long id, AtualizarSofaDTO novosDados, MultipartFile novaImagem) {
        return repository.findById(id)
                .map(sofa -> {
                    if (novosDados.getModelo() != null && !novosDados.getModelo().isEmpty()) {
                        sofa.setModelo(novosDados.getModelo());
                    }

                    if (novaImagem != null && !novaImagem.isEmpty()) {
                        String pastaDestino = "uploads/sofas/";
                        File pasta = new File(pastaDestino);
                        if (!pasta.exists()) {
                            pasta.mkdirs();
                        }
                        try {
                            String nomeImagem = System.currentTimeMillis() + "_" + novaImagem.getOriginalFilename();
                            Path caminhoArquivo = Paths.get(pastaDestino + nomeImagem);
                            Files.write(caminhoArquivo, novaImagem.getBytes());
                            sofa.setCaminhoImagem("/" + pastaDestino + nomeImagem);
                        } catch (IOException e) {
                            throw new RuntimeException("Erro ao salvar imagem", e);
                        }
                    }

                    return repository.save(sofa);
                });
    }

    @Transactional
    public void produzirSofa(Long idSofa, Integer quantidadeSofas) {
        List<PecaComQuantidadeDTO> pecas = listarPecaPorSofa(idSofa);

        for (PecaComQuantidadeDTO peca : pecas) {
            double quantidadeNecessaria = peca.getQuantidade() * quantidadeSofas;
            Peca pecaEstoque = pecaService.buscarPorId(peca.getId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Peça não encontrada: " + peca.getNome()));
            if (pecaEstoque.getQuantidadeEstoque() < quantidadeNecessaria) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Estoque insuficiente para a peça: " + peca.getNome() +
                                ". Necessário: " + quantidadeNecessaria +
                                ", disponível: " + pecaEstoque.getQuantidadeEstoque()
                );
            }
        }

        // 2º: Só desconta do estoque se passou na validação
        producaoSofaRepository.save(new ProducaoSofa(idSofa, LocalDateTime.now(), quantidadeSofas));
        for (PecaComQuantidadeDTO peca : pecas) {
            pecaService.removerQuantidadeEstoque(
                    peca.getId(),
                    peca.getQuantidade() * quantidadeSofas
            );
        }
    }

    public Map<Integer, Integer> getProducaoMensalAnoAtual() {
        List<ProducaoMensal> producaoPorMes = producaoSofaRepository.findProducaoAgrupadaPorMesAnoAtual();
        Map<Integer, Integer> resultado = IntStream.rangeClosed(1, 12)
                .boxed()
                .collect(Collectors.toMap(
                        mes -> mes,
                        mes -> 0
                ));
        producaoPorMes.forEach(item ->
                resultado.put(item.getMes(), item.getTotal())
        );
        return resultado;
    }

    public List<Map<String, Object>> getSofasMaisSaidaMes() {
        return logMovimentacaoRepository.findSofasMaisSaidaMes();
    }

    @Transactional
    public boolean deletarSofa(Long id) {
        Optional<Sofa> sofaOpt = repository.findById(id);
        if (sofaOpt.isEmpty()) return false;
        quantidadePecaEmSofaService.removerTodasPorSofaId(id);
        repository.deleteById(id);
        return true;
    }

}