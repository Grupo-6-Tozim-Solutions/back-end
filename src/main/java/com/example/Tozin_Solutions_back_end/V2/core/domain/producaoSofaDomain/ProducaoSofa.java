package com.example.Tozin_Solutions_back_end.V2.core.domain.producaoSofaDomain;

import com.example.Tozin_Solutions_back_end.V2.core.domain.producaoSofaDomain.valueObjects.DataCriacao;
import com.example.Tozin_Solutions_back_end.V2.core.domain.producaoSofaDomain.valueObjects.QuantidadeProducao;

import java.time.LocalDateTime;

public class ProducaoSofa {
    private Long id;
    private Long sofaId;
    private QuantidadeProducao quantidade;
    private DataCriacao dataProducao;

    public ProducaoSofa(Long sofaId, QuantidadeProducao quantidade) {
        this.sofaId = sofaId;
        this.quantidade = quantidade;
        this.dataProducao = new DataCriacao(LocalDateTime.now());
    }

    public ProducaoSofa(Long id, Long sofaId, QuantidadeProducao quantidade, DataCriacao dataProducao) {
        this.id = id;
        this.sofaId = sofaId;
        this.quantidade = quantidade;
        this.dataProducao = dataProducao;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getSofaId() { return sofaId; }
    public QuantidadeProducao getQuantidade() { return quantidade; }
    public DataCriacao getDataProducao() { return dataProducao; }
}
