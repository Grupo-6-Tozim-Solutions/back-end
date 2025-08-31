package com.example.Tozin_Solutions_back_end.V2.core.domain.MovimentacaoEstoqueDomain;

import com.example.Tozin_Solutions_back_end.V2.core.domain.MovimentacaoEstoqueDomain.valueObjects.QuantidadeEntrada;
import com.example.Tozin_Solutions_back_end.V2.core.domain.MovimentacaoEstoqueDomain.valueObjects.QuantidadeSaida;
import com.example.Tozin_Solutions_back_end.V2.core.domain.PecaDomain.valueObjects.NomePeca;
import com.example.Tozin_Solutions_back_end.V2.core.domain.PecaDomain.valueObjects.TipoPeca;

import java.time.LocalDateTime;

public class MovimentacaoEstoque {
    private Long id;
    private LocalDateTime dataMovimentacao;
    private TipoPeca tipoPeca;
    private NomePeca nomePeca;
    private QuantidadeEntrada quantidadeEntrada;
    private QuantidadeSaida quantidadeSaida;

    private MovimentacaoEstoque(TipoPeca tipoPeca, NomePeca nomePeca, QuantidadeEntrada quantidadeEntrada, QuantidadeSaida quantidadeSaida) {
        this.dataMovimentacao = LocalDateTime.now();
        this.tipoPeca = tipoPeca;
        this.nomePeca = nomePeca;
        this.quantidadeEntrada = quantidadeEntrada;
        this.quantidadeSaida = quantidadeSaida;
    }

    private MovimentacaoEstoque(Long id, LocalDateTime dataMovimentacao, TipoPeca tipoPeca, NomePeca nomePeca, QuantidadeEntrada quantidadeEntrada, QuantidadeSaida quantidadeSaida) {
        this.id = id;
        this.dataMovimentacao = dataMovimentacao;
        this.tipoPeca = tipoPeca;
        this.nomePeca = nomePeca;
        this.quantidadeEntrada = quantidadeEntrada;
        this.quantidadeSaida = quantidadeSaida;
    }

    public static MovimentacaoEstoque registrarMovimentacao(TipoPeca tipo, NomePeca nome, QuantidadeEntrada quantidadeEntrada, QuantidadeSaida quantidadeSaida){
        return new MovimentacaoEstoque(tipo, nome, quantidadeEntrada, quantidadeSaida);
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDataMovimentacao() {
        return dataMovimentacao;
    }

    public TipoPeca getTipoPeca() {
        return tipoPeca;
    }

    public NomePeca getNomePeca() {
        return nomePeca;
    }

    public QuantidadeEntrada getQuantidadeEntrada() {
        return quantidadeEntrada;
    }

    public QuantidadeSaida getQuantidadeSaida() {
        return quantidadeSaida;
    }
}
