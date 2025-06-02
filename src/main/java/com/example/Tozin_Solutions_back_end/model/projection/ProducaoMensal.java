package com.example.Tozin_Solutions_back_end.model.projection;

public interface ProducaoMensal {
    Integer getMes();        // Retorna o número do mês (1-12)
    Integer getTotal();      // Retorna a soma da produção no mês
}
