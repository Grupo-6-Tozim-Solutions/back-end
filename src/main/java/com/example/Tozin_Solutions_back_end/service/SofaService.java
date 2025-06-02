package com.example.Tozin_Solutions_back_end.service;

import com.example.Tozin_Solutions_back_end.dto.movimentacaoEstoqueDTO.RegistrarMovimentacaoDTO;
import com.example.Tozin_Solutions_back_end.dto.quantidadePecaEmSofaDTO.PecaComQuantidadeDTO;
import com.example.Tozin_Solutions_back_end.dto.quantidadePecaEmSofaDTO.RegistroQuantidadePecaEmSofaDTO;
import com.example.Tozin_Solutions_back_end.dto.sofaDTO.AdicaoPecaDTO;
import com.example.Tozin_Solutions_back_end.dto.sofaDTO.AtualizarSofaDTO;
import com.example.Tozin_Solutions_back_end.dto.sofaDTO.CadastrarSofaDTO;
import com.example.Tozin_Solutions_back_end.model.Peca;
import com.example.Tozin_Solutions_back_end.model.QuantidadePecaEmSofa;
import com.example.Tozin_Solutions_back_end.model.Sofa;
import com.example.Tozin_Solutions_back_end.repository.SofaRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
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
                continue; // ou lançar exceção se preferir
            }

            // Esta verificação agora é redundante pois o salvarConfiguracao já faz isso
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
                // Adiciona a quantidade que está sendo utilizada no sofá
                pecasComQuantidade.add(new PecaComQuantidadeDTO(
                        peca.getId(),
                        peca.getNome(),
                        relacao.getQuantidadePeca() // A quantidade associada ao sofá
                ));
            }
        }
        return pecasComQuantidade;
    }

    @Transactional
    public Optional<Sofa> removerPeca(Long idSofa, Long idPeca) {
        // Verifica se o sofá existe primeiro
        Optional<Sofa> sofa = repository.findById(idSofa);
        if (sofa.isEmpty()) {
            return Optional.empty();
        }

        Optional<Peca> peca = pecaService.buscarPorId(idPeca);
        Optional<QuantidadePecaEmSofa> configuracao = quantidadePecaEmSofaService.encontrarPorIdSofaEPeca(idSofa, idPeca);

        if (peca.isPresent() && configuracao.isPresent()) {
            // Devolve a quantidade ao estoque
            pecaService.adicionarQuantidadeEstoque(peca.get().getId(), configuracao.get().getQuantidadePeca());

            // Remove a configuração usando o ID correto (da relação, não da peça)
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
            // Remove do estoque a quantidade necessária para todos os sofás produzidos
            pecaService.removerQuantidadeEstoque(
                    peca.getId(),
                    peca.getQuantidade() * quantidadeSofas
            );
        }
    }

    @Transactional
    public boolean deletarSofa(Long id) {
        Optional<Sofa> sofaOpt = repository.findById(id);
        if (sofaOpt.isEmpty()) return false;

        // Remove todas as relações de peças do sofá
        quantidadePecaEmSofaService.removerTodasPorSofaId(id);

        // Remove o sofá
        repository.deleteById(id);
        return true;
    }

}