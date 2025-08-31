package com.example.Tozin_Solutions_back_end.V2.core.domain.QuantidadePecaEmSofaDomain;

import com.example.Tozin_Solutions_back_end.V2.core.domain.PecaDomain.valueObjects.Quantidade;
import com.example.Tozin_Solutions_back_end.V2.core.domain.usuarioDomain.excepetion.DomainException;

public class QuantidadePecaEmSofa {
    private Long id;
    private Long sofaId;
    private Long pecaId;
    private Quantidade quantidade;

    // Construtor
    private QuantidadePecaEmSofa(Long sofaId, Long pecaId, Quantidade quantidade) {
        this.sofaId = sofaId;
        this.pecaId = pecaId;
        this.quantidade = quantidade;
    }

    // Factory methods
    public static QuantidadePecaEmSofa criarConfiguracao(Long sofaId, Long pecaId, Quantidade quantidade) {
        return new QuantidadePecaEmSofa(sofaId, pecaId, quantidade);
    }

    public static QuantidadePecaEmSofa existente(Long id, Long sofaId, Long pecaId, Quantidade quantidade) {
        QuantidadePecaEmSofa config = new QuantidadePecaEmSofa(sofaId, pecaId, quantidade);
        config.id = id;
        return config;
    }

    // Comportamentos de domínio
    public void atualizarQuantidade(Quantidade novaQuantidade) {
        this.quantidade = novaQuantidade;
    }

    // Validação
    public void validarConfiguracao() {
        if (sofaId == null) {
            throw new DomainException("ID do sofá é obrigatório");
        }
        if (pecaId == null) {
            throw new DomainException("ID da peça é obrigatório");
        }
        if (quantidade == null) {
            throw new DomainException("Quantidade é obrigatória");
        }
    }

    // Getters
    public Long getId() { return id; }
    public Long getSofaId() { return sofaId; }
    public Long getPecaId() { return pecaId; }
    public Quantidade getQuantidade() { return quantidade; }

    public QuantidadePecaEmSofa comId(Long id) {
        return existente(id, sofaId, pecaId, quantidade);
    }
}
