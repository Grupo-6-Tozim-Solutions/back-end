package com.example.Tozin_Solutions_back_end.V2.core.application.logMovimentacaoApplication.dto;

public record LogMovimentacaoPaginationRequest(
        int page,
        int size,
        String sortBy,
        String sortDirection,
        String filter,
        String tipoPeca,
        String acao
) {
    public LogMovimentacaoPaginationRequest {
        if (page < 1) page = 1;
        if (size < 1) size = 10;
        if (sortBy == null || sortBy.isBlank()) sortBy = "dataMovimentacao";
        if (sortDirection == null || sortDirection.isBlank()) sortDirection = "desc";
        if (filter == null) filter = "";
        if (tipoPeca == null) tipoPeca = "";
        if (acao == null) acao = "";
    }

    public static LogMovimentacaoPaginationRequest defaultRequest() {
        return new LogMovimentacaoPaginationRequest(1, 10, "dataMovimentacao", "desc", "", "", "");
    }
}
