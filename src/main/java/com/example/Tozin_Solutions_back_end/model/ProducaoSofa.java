package com.example.Tozin_Solutions_back_end.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class ProducaoSofa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long idSofa;
    private LocalDateTime dataProducao;
    private Integer quantidade;

    public ProducaoSofa(Long idSofa, LocalDateTime dataProducao, Integer quantidade) {
        this.idSofa = idSofa;
        this.dataProducao = dataProducao;
        this.quantidade = quantidade;
    }

    public ProducaoSofa() {}

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Long getId() {
        return id;
    }

    public Long getIdSofa() {
        return idSofa;
    }

    public LocalDateTime getDataProducao() {
        return dataProducao;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIdSofa(Long idSofa) {
        this.idSofa = idSofa;
    }

    public void setDataProducao(LocalDateTime dataProducao) {
        this.dataProducao = dataProducao;
    }
}
