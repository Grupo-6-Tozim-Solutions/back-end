package com.example.Tozin_Solutions_back_end.V2.core.application.quantidadePecaEmSofaApplication.service;


import com.example.Tozin_Solutions_back_end.V2.core.application.quantidadePecaEmSofaApplication.dto.ConfiguracaoPecaSofaOutput;
import com.example.Tozin_Solutions_back_end.V2.core.application.quantidadePecaEmSofaApplication.dto.ConfigurarPecaNoSofaInput;
import com.example.Tozin_Solutions_back_end.V2.core.application.quantidadePecaEmSofaApplication.port.QuantidadePecaEmSofaRepositoryPort;
import com.example.Tozin_Solutions_back_end.V2.core.application.quantidadePecaEmSofaApplication.useCase.ConfigurarPecaNoSofaUseCase;
import com.example.Tozin_Solutions_back_end.V2.core.domain.PecaDomain.valueObjects.Quantidade;
import com.example.Tozin_Solutions_back_end.V2.core.domain.QuantidadePecaEmSofaDomain.QuantidadePecaEmSofa;
import org.springframework.stereotype.Service;

@Service
public class ConfigurarPecaNoSofaService implements ConfigurarPecaNoSofaUseCase {

    private final QuantidadePecaEmSofaRepositoryPort configuracaoRepositoryPort;

    public ConfigurarPecaNoSofaService(QuantidadePecaEmSofaRepositoryPort configuracaoRepositoryPort) {
        this.configuracaoRepositoryPort = configuracaoRepositoryPort;
    }

    @Override
    public ConfiguracaoPecaSofaOutput executar(ConfigurarPecaNoSofaInput input) {
        // Verifica se já existe configuração
        var configExistente = configuracaoRepositoryPort.buscarPorSofaIdEPecaId(
                input.sofaId(), input.pecaId());

        QuantidadePecaEmSofa configuracao;

        if (configExistente.isPresent()) {
            // Atualiza configuração existente
            configuracao = configExistente.get();
            configuracao.atualizarQuantidade(new Quantidade(input.quantidade()));
        } else {
            // Cria nova configuração
            configuracao = QuantidadePecaEmSofa.criarConfiguracao(
                    input.sofaId(), input.pecaId(), new Quantidade(input.quantidade()));
        }

        configuracao.validarConfiguracao();
        QuantidadePecaEmSofa configSalva = configuracaoRepositoryPort.salvar(configuracao);

        return new ConfiguracaoPecaSofaOutput(
                configSalva.getId(),
                configSalva.getSofaId(),
                configSalva.getPecaId(),
                configSalva.getQuantidade().value()
        );
    }
}