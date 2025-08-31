package com.example.Tozin_Solutions_back_end.V2.core.application.sofaApplication.dto;

import org.springframework.web.multipart.MultipartFile;

public record UploadImagemInput(
        CadastrarSofaInput sofa,
        MultipartFile imagem
) {}