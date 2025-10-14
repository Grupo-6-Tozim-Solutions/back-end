package com.example.Tozin_Solutions_back_end.V2.core.domain.MovimentacaoEstoqueDomain;

import com.example.Tozin_Solutions_back_end.V2.core.domain.MovimentacaoEstoqueDomain.valueObjects.QuantidadeEntrada;
import com.example.Tozin_Solutions_back_end.V2.core.domain.MovimentacaoEstoqueDomain.valueObjects.QuantidadeSaida;
import com.example.Tozin_Solutions_back_end.V2.core.domain.PecaDomain.Peca;
import com.example.Tozin_Solutions_back_end.V2.core.domain.PecaDomain.valueObjects.NomePeca;
import com.example.Tozin_Solutions_back_end.V2.core.domain.PecaDomain.valueObjects.TipoPeca;

import java.time.LocalDateTime;

public class MovimentacaoEstoque {
    private Long id;
    private LocalDateTime dataMovimentacao;
    private Peca peca;
    private QuantidadeEntrada quantidadeEntrada;
    private QuantidadeSaida quantidadeSaida;

    private MovimentacaoEstoque(Peca peca, QuantidadeEntrada quantidadeEntrada, QuantidadeSaida quantidadeSaida) {
        this.dataMovimentacao = LocalDateTime.now();
        this.peca = peca;
        this.quantidadeEntrada = quantidadeEntrada;
        this.quantidadeSaida = quantidadeSaida;
    }

    public static MovimentacaoEstoque registrarEntrada(Peca peca, QuantidadeEntrada quantidadeEntrada) {
        if (peca == null) {
            throw new IllegalArgumentException("Peça não pode ser nula");
        }
        if (quantidadeEntrada == null || quantidadeEntrada.value() <= 0) {
            throw new IllegalArgumentException("Quantidade de entrada deve ser positiva");
        }
        return new MovimentacaoEstoque(peca, quantidadeEntrada, new QuantidadeSaida(0.0));
    }

    public static MovimentacaoEstoque registrarSaida(Peca peca, QuantidadeSaida quantidadeSaida) {
        if (peca == null) {
            throw new IllegalArgumentException("Peça não pode ser nula");
        }
        if (quantidadeSaida == null || quantidadeSaida.value() <= 0) {
            throw new IllegalArgumentException("Quantidade de saída deve ser positiva");
        }
        return new MovimentacaoEstoque(peca, new QuantidadeEntrada(0.0), quantidadeSaida);
    }

    public Long getId() { return id; }
    public LocalDateTime getDataMovimentacao() { return dataMovimentacao; }
    public Peca getPeca() { return peca; }
    public QuantidadeEntrada getQuantidadeEntrada() { return quantidadeEntrada; }
    public QuantidadeSaida getQuantidadeSaida() { return quantidadeSaida; }

    public void setId(Long id) { this.id = id; }
    public void setDataMovimentacao(LocalDateTime dataMovimentacao) { this.dataMovimentacao = dataMovimentacao; }
}
