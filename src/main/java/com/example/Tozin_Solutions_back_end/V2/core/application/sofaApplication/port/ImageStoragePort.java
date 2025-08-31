package com.example.Tozin_Solutions_back_end.V2.core.application.sofaApplication.port;

import org.springframework.web.multipart.MultipartFile;

public interface ImageStoragePort {
    String armazaenarImagem(MultipartFile imagem, String pastaDestino);
    void deletarImagem(String caminhoImagem);
}
