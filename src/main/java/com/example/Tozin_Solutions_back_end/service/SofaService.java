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

        for (AdicaoPecaDTO pecaAssociada : pecasAssociadas) {
            Optional<Peca> peca = pecaService.buscarPorId(pecaAssociada.getIdPeca());
            Optional<QuantidadePecaEmSofa> jaExiste = quantidadePecaEmSofaService.encontrarPorIdSofaEPeca(idSofa, pecaAssociada.getIdPeca());

            if (peca.isPresent()) {
                // Se a peça já existe, apenas atualiza a quantidade
                if (jaExiste.isPresent()) {
                    RegistroQuantidadePecaEmSofaDTO configuracao =
                            new RegistroQuantidadePecaEmSofaDTO(idSofa, pecaAssociada.getIdPeca(), pecaAssociada.getQuantidade());
                    quantidadePecaEmSofaService.salvarConfiguracao(configuracao);
                } else {
                    // Se não existe, cria uma nova relação
                    RegistroQuantidadePecaEmSofaDTO configuracao =
                            new RegistroQuantidadePecaEmSofaDTO(idSofa, pecaAssociada.getIdPeca(), pecaAssociada.getQuantidade());
                    quantidadePecaEmSofaService.salvarConfiguracao(configuracao);
                }
            }
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

    public Optional<Sofa> removerPeca(Long idSofa, Long idPeca) {
        Optional<Peca> peca = pecaService.buscarPorId(idPeca);
        Optional<QuantidadePecaEmSofa> configuracao = quantidadePecaEmSofaService.encontrarPorIdSofaEPeca(idSofa, idPeca);

        if (peca.isPresent() && configuracao.isPresent()) {
            pecaService.adicionarQuantidadeEstoque(peca.get().getId(), configuracao.get().getQuantidadePeca());
            quantidadePecaEmSofaService.removerConfiguracao(configuracao.get().getIdPeca());
        }

        return repository.findById(idSofa);
    }

    public Optional<Sofa> atualizarDadosSofa(Long id, AtualizarSofaDTO novosDados) {
        return repository.findById(id)
                .map(sofa -> {
                    if (sofa.getModelo() != null && !novosDados.getModelo().isEmpty()) {
                        sofa.setModelo(novosDados.getModelo());
                    }
                    return repository.save(sofa);
                });
    }
}