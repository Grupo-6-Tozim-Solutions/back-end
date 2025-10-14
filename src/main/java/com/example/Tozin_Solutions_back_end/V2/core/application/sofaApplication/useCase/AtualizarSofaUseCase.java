package com.example.Tozin_Solutions_back_end.V2.core.application.sofaApplication.useCase;

import com.example.Tozin_Solutions_back_end.V2.core.application.sofaApplication.dto.AtualizarSofaInput;
import com.example.Tozin_Solutions_back_end.V2.core.application.sofaApplication.dto.SofaOutput;
import org.springframework.web.multipart.MultipartFile;

public interface AtualizarSofaUseCase {
    SofaOutput executar(Long id, AtualizarSofaInput input, MultipartFile imagem);
}
