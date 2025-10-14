package com.example.Tozin_Solutions_back_end.V2.core.application.sofaApplication.service;

import com.example.Tozin_Solutions_back_end.V2.core.application.quantidadePecaEmSofaApplication.port.QuantidadePecaEmSofaRepositoryPort;
import com.example.Tozin_Solutions_back_end.V2.core.application.sofaApplication.dto.AdicionarPecaInput;
import com.example.Tozin_Solutions_back_end.V2.core.application.sofaApplication.dto.SofaOutput;
import com.example.Tozin_Solutions_back_end.V2.core.application.sofaApplication.port.SofaRepositoryPort;
import com.example.Tozin_Solutions_back_end.V2.core.application.sofaApplication.useCase.AdicionarPecaUseCase;
import com.example.Tozin_Solutions_back_end.V2.core.domain.PecaDomain.valueObjects.Quantidade;
import com.example.Tozin_Solutions_back_end.V2.core.domain.QuantidadePecaEmSofaDomain.QuantidadePecaEmSofa;
import com.example.Tozin_Solutions_back_end.V2.core.domain.SofaDomain.Sofa;
import com.example.Tozin_Solutions_back_end.V2.core.domain.SofaDomain.exception.SofaNaoEncontradoException;
import org.springframework.stereotype.Service;

@Service
public class AdicionarPecaService implements AdicionarPecaUseCase {

    private final SofaRepositoryPort sofaRepositoryPort;
    private final QuantidadePecaEmSofaRepositoryPort configuracaoRepositoryPort;

    public AdicionarPecaService(SofaRepositoryPort sofaRepositoryPort,
                                QuantidadePecaEmSofaRepositoryPort configuracaoRepositoryPort) {
        this.sofaRepositoryPort = sofaRepositoryPort;
        this.configuracaoRepositoryPort = configuracaoRepositoryPort;
    }

    @Override
    public SofaOutput executar(Long sofaId, AdicionarPecaInput input) {
        Sofa sofa = sofaRepositoryPort.buscarPorId(sofaId)
                .orElseThrow(() -> new SofaNaoEncontradoException(sofaId));

        for (AdicionarPecaInput.PecaQuantidade peca : input.pecas()) {
            var configExistente = configuracaoRepositoryPort.buscarPorSofaIdEPecaId(sofaId, peca.pecaId());

            QuantidadePecaEmSofa configuracao;

            if (configExistente.isPresent()) {
                configuracao = configExistente.get();
                configuracao.atualizarQuantidade(new Quantidade(peca.quantidade()));
            } else {
                configuracao = QuantidadePecaEmSofa.criarConfiguracao(
                        sofaId, peca.pecaId(), new Quantidade(peca.quantidade()));
            }

            configuracaoRepositoryPort.salvar(configuracao);
        }

        Sofa sofaAtualizado = sofaRepositoryPort.buscarPorId(sofaId)
                .orElseThrow(() -> new SofaNaoEncontradoException(sofaId));

        return new SofaOutput(
                sofaAtualizado.getId(),
                sofaAtualizado.getModelo().value(),
                sofaAtualizado.getCaminhoImagem().value(),
                sofaAtualizado.getDataCadastro()
        );
    }
}