package com.example.Tozin_Solutions_back_end.V2.core.application.sofaApplication.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImageStorageService {

    public String salvarImagem(MultipartFile imagem, String pastaDestino) {
        java.io.File pasta = new java.io.File(pastaDestino);
        if (!pasta.exists()) {
            pasta.mkdirs();
        }

        try {
            if (imagem != null && !imagem.isEmpty()) {
                String nomeImagem = System.currentTimeMillis() + "_" + imagem.getOriginalFilename();
                Path caminhoArquivo = Paths.get(pastaDestino + nomeImagem);
                Files.write(caminhoArquivo, imagem.getBytes());
                return "/" + pastaDestino + nomeImagem;
            } else {
                return "/uploads/sofas/imagem_padrao.jpg";
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar imagem", e);
        }
    }

    public String salvarImagemSofa(MultipartFile imagem) {
        return salvarImagem(imagem, "uploads/sofas/");
    }

}
